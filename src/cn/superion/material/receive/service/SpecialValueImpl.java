package cn.superion.material.receive.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.materialDept.dao.MaterialCurrentStockDeptDAO;
import cn.superion.materialDept.dao.MaterialPatsDetailDAO;
import cn.superion.materialDept.dao.MaterialPatsMasterDAO;
import cn.superion.materialDept.dao.MaterialRdsDetailDeptDAO;
import cn.superion.materialDept.entity.MaterialCurrentStockDept;
import cn.superion.materialDept.entity.MaterialPatsMaster;
import cn.superion.materialDept.entity.VMaterialPats;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class SpecialValueImpl implements ISpecialValue {

//	private Log log = LogFactory.getLog(AgentMaterialUsedImpl.class);
	private MaterialPatsMasterDAO materialPatsMasterDAO;
	private MaterialPatsDetailDAO materialPatsDetailDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService commMaterialServiceImpl;
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;
	private MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO;

	@Override
	public ReObject findMaterialValueDetailByCondition(ParameterObject fparam) {
		ReObject ro = new ReObject("根据条件查询病人使用高值耗材的明细记录");
		
		Map<String, Object> conditions=fparam.getConditions();
		List<VMaterialPats> detail=materialPatsDetailDAO.findValueDetailByCondition(conditions);
		ro.setData(detail);
		return ro;
	}


	@Override
	public ReObject saveMaterialRds(MaterialRdsMaster fmaster,String[] fstrAutoIds,List<VMaterialPats> fPatsDetails) {
		ReObject ro = new ReObject("汇总病人使用代销材料记录，生成一张已审核的采购入库单据和一张已审核的领用出库单据");
		SysUser user = SessionUtil.getSysUser();
		String personId = user.getPersonId();
		String storageCode = null;
		String appCode = RdConstant.APP_CODE_STORAGE_MATERIAL;
//		String deptCode = "";
		
		//办理实际入库的单位
		String curUnitsCode = user.getUnitsCode();
		for(String autoId : fstrAutoIds){
			MaterialPatsMaster original = materialPatsMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号["+autoId+"]的高值耗材物资使用主记录！");
			}
			original.setCurrentStatus("1");
			//虚拟入库的单位
			String patsUnitsCode = original.getUnitsCode();
			storageCode = original.getStorageCode();

			//入库单明细记录
			List<MaterialRdsDetail> rDetails = new ArrayList<MaterialRdsDetail>();
			//出库明细记录
			List<MaterialRdsDetail> dDetails = new ArrayList<MaterialRdsDetail>();
			for(VMaterialPats detail : fPatsDetails){
				if(detail.getAutoId().equals(autoId)){
					Double useAmount = detail.getAmount(); 
					if(useAmount > 0){
						//查询虚拟库存数量
						MaterialCurrentStockDept stock = materialCurrentStockDeptDAO.findByUniqueIndex(patsUnitsCode, storageCode, detail.getMaterialId(), detail.getBatch(), detail.getBarCode());
						//入库时的当前库存量
						Double curStockAmount = stock == null ? 0d : stock.getAmount();
						//出库时的当前库存量
						Double curStockAmount2 = 0d;
						
						curStockAmount += useAmount; 
						curStockAmount2 = curStockAmount - useAmount;

						CdMaterialDict material=materialRdsDetailDeptDAO.findByMaterialId(detail.getMaterialId());
						//构造采购入库单明细记录
						rDetails.add(buildMaterialRdsDetail(detail,curStockAmount,stock,material));
						//构造领用出库单明细记录
						dDetails.add(buildMaterialRdsDetail(detail,curStockAmount2,stock,material));
						detail.setFactInSign("1");
						materialPatsDetailDAO.updateFactInSignById(detail.getDetailAutoId(),"1");
						
					}
				}
			}

			//生成一张已审核的采购入库单
			MaterialRdsMaster rMaster = buildMaterialRdsMaster(RdConstant.R,RdConstant.R_AGENCY,personId,SessionUtil.getUnitsCode(),fmaster);
			//根据系统参数来确定入库类别
			rMaster.setRdType(cdSysParamDAO.findByParaCode(curUnitsCode, appCode, RdConstant.SYS_PARA_CODE_AGENT_R_TYPE));
			saveRds(rMaster,rDetails);
			
			//病人所在的病区写出入库单的科室  ryh 2012.10.27
			fmaster.setDeptCode(original.getWardCode());
			
			MaterialRdsMaster dMaster = buildMaterialRdsMaster(RdConstant.D,RdConstant.D_DELIVER,personId,curUnitsCode,fmaster);
			//根据系统参数来确定出库类别
			dMaster.setRdType(cdSysParamDAO.findByParaCode(curUnitsCode, appCode, RdConstant.SYS_PARA_CODE_AGENT_D_TYPE));
			//出库主记录的业务号写入库主记录的autoId
			dMaster.setOperationNo(rMaster.getAutoId());
			//出库明细记录的来源入库系统标识号写入库明细记录的autoId
			for(int i = 0 ;i < dDetails.size(); i++){
				dDetails.get(i).setSourceInputAutoId(rDetails.get(i).getAutoId());
			}
			saveRds(dMaster,dDetails);
			
			//业务号为出库单autoId
			original.setOperationNo(dMaster.getAutoId());
		}
	
		return ro;
	}

	
	private MaterialRdsMaster buildMaterialRdsMaster(String rdFlag,String operationType,String personId,String unitsCode,MaterialRdsMaster fmaster){
		Date curDate = new Date();
		MaterialRdsMaster master = new MaterialRdsMaster();
		master.setUnitsCode(unitsCode);
		master.setStorageCode(fmaster.getStorageCode());
		master.setBillNo(commMaterialServiceImpl.getNextBillNo(rdFlag, fmaster.getStorageCode()));
		master.setBillDate(curDate);
		master.setInvoiceType("1");
		master.setRdFlag(rdFlag);
		master.setOperationType(operationType);
		master.setDeptCode(fmaster.getDeptCode());
		master.setPersonId(personId);
		//供应商信息应追溯物资代销入库记录中的
		//master.setSalerCode(salerCode)
		//master.setSalerName(salerName);
		master.setMaker(personId);
		master.setMakeDate(curDate);
		master.setVerifier(personId);
		master.setVerifyDate(curDate);
		master.setCurrentStatus("1");
		master.setSalerCode(fmaster.getSalerCode());
		master.setSalerName(fmaster.getSalerName());
		return master;
	}
	
	
	/**
	 * 以代销物资明细为模板，构造收发存明细
	 * @param pdetail
	 * @param curStockAmount
	 * @param stock 获取进价
	 * @return
	 */
	private MaterialRdsDetail buildMaterialRdsDetail(VMaterialPats pdetail,Double curStockAmount,MaterialCurrentStockDept stock,
			CdMaterialDict material){
		MaterialRdsDetail detail = new MaterialRdsDetail();
		detail.setMaterialClass(pdetail.getMaterialClass());
		detail.setBarCode(pdetail.getBarCode());
		detail.setMaterialId(pdetail.getMaterialId());
		detail.setMaterialCode(pdetail.getMaterialCode());
		detail.setMaterialName(pdetail.getMaterialName());
		detail.setMaterialSpec(pdetail.getMaterialSpec());
		detail.setMaterialUnits(pdetail.getMaterialUnits());
		
		detail.setAmount(pdetail.getAmount());
		detail.setPackageAmount(pdetail.getAmount());
		//赠送耗材不需要开发票，但是需要写入核算数量
		detail.setAcctAmount(pdetail.getAmount());
		detail.setAmountPerPackage(detail.getAmount());
		
		if(stock != null){
			detail.setTradePrice(stock.getTradePrice());
			if(stock.getTradePrice() != null)
				detail.setTradeMoney(stock.getTradePrice() * detail.getAmount());
		}
		detail.setRetailPrice(pdetail.getRetailPrice());
		detail.setRetailMoney(pdetail.getRetailMoney());
		detail.setTradePrice(pdetail.getTradePrice());
		detail.setTradeMoney(pdetail.getTradeMoney());
		detail.setWholeSalePrice(pdetail.getWholeSalePrice());
		detail.setWholeSaleMoney(pdetail.getWholeSaleMoney());
		
		detail.setFactoryCode(pdetail.getFactoryCode());
		detail.setMadeDate(pdetail.getMadeDate());
		detail.setBatch(pdetail.getBatch());
		detail.setAvailDate(pdetail.getAvailDate());
		detail.setCurrentStockAmount(curStockAmount);
		detail.setHighValueSign("1");
		detail.setAgentSign("1");
		detail.setAcctBillNo("0");
		detail.setCurrentStatus("1");
		detail.setInvoiceNo(pdetail.getInvoiceNo());
		detail.setInvoiceDate(pdetail.getInvoiceDate());
		detail.setChargeSign(pdetail.getChargeSign());
		detail.setClassOnAccount(pdetail.getClassOnAccount());
		detail.setMaterialBarCode(pdetail.getMaterialBarCode());
		detail.setIsGive(pdetail.getIsGive());
		detail.setPackageSpec(material.getPackageSpec());
		detail.setPackageUnits(material.getPackageUnits());
		detail.setFactTradePrice(detail.getTradePrice());
		detail.setFactTradeMoney(detail.getTradeMoney());
		detail.setRebateRate(material.getRebateRate());
		detail.setRetailPrice(material.getRetailPrice());
		detail.setRetailMoney(material.getRetailPrice() * detail.getAmount());
//		if()
		detail.setOutAmount(detail.getAmount());
		return detail;
	}
	
	/**
	 * 写收发存主记录和明细记录
	 * @param fmaster
	 * @param fdetails
	 */
	private void saveRds(MaterialRdsMaster fmaster,List<MaterialRdsDetail> fdetails){
		//保存收发存主记录
		materialRdsMasterDAO.save(fmaster);
		String mainAutoId = fmaster.getAutoId();
		// 收发存保存明细记录
		Short i = 0;
		for (MaterialRdsDetail detail : fdetails) {
			detail.setSerialNo(++i);
			String batch = detail.getBatch();
			if(batch == null || "".equals(batch)){
				batch = "0";
				detail.setBatch(batch);
			}
			String barCode = detail.getBarCode();
			if(barCode == null || "".equals(barCode)){
				barCode = "0";
				detail.setBarCode(barCode);
			}
			detail.setMainAutoId(mainAutoId);
			if(fmaster.getRdFlag()!=null && fmaster.getRdFlag().equals("1")){
				detail.setOutAmount(0d);
				detail.setOutSign("0");
			}
			else{
				detail.setOutAmount(detail.getAmount());
				detail.setOutSign("1");
			}
			materialRdsDetailDAO.save(detail);
		}
	}
	
	public MaterialPatsMasterDAO getMaterialPatsMasterDAO() {
		return materialPatsMasterDAO;
	}

	public void setMaterialPatsMasterDAO(MaterialPatsMasterDAO materialPatsMasterDAO) {
		this.materialPatsMasterDAO = materialPatsMasterDAO;
	}

	public MaterialPatsDetailDAO getMaterialPatsDetailDAO() {
		return materialPatsDetailDAO;
	}

	public void setMaterialPatsDetailDAO(MaterialPatsDetailDAO materialPatsDetailDAO) {
		this.materialPatsDetailDAO = materialPatsDetailDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	public MaterialRdsMasterDAO getMaterialRdsMasterDAO() {
		return materialRdsMasterDAO;
	}

	public void setMaterialRdsMasterDAO(MaterialRdsMasterDAO materialRdsMasterDAO) {
		this.materialRdsMasterDAO = materialRdsMasterDAO;
	}

	public MaterialRdsDetailDAO getMaterialRdsDetailDAO() {
		return materialRdsDetailDAO;
	}

	public void setMaterialRdsDetailDAO(MaterialRdsDetailDAO materialRdsDetailDAO) {
		this.materialRdsDetailDAO = materialRdsDetailDAO;
	}

	public MaterialCurrentStockDeptDAO getMaterialCurrentStockDeptDAO() {
		return materialCurrentStockDeptDAO;
	}

	public void setMaterialCurrentStockDeptDAO(
			MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO) {
		this.materialCurrentStockDeptDAO = materialCurrentStockDeptDAO;
	}


	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}


	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}


	public MaterialRdsDetailDeptDAO getMaterialRdsDetailDeptDAO() {
		return materialRdsDetailDeptDAO;
	}


	public void setMaterialRdsDetailDeptDAO(
			MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO) {
		this.materialRdsDetailDeptDAO = materialRdsDetailDeptDAO;
	}

}
