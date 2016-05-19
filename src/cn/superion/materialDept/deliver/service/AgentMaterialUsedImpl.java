package cn.superion.materialDept.deliver.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.materialDept.dao.MaterialPatsDetailDAO;
import cn.superion.materialDept.dao.MaterialPatsMasterDAO;
import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.MaterialPatsMaster;
import cn.superion.materialDept.entity.PatsVisit;
import cn.superion.materialDept.his.service.IHisServiceFacade;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 代销物资使用服务实现
 * @author 曹国魁
 *
 */
public class AgentMaterialUsedImpl implements IAgentMaterialUsed {
	//1：代销领用
	private static final String BILL_TYPE = "1";
	private Log log = LogFactory.getLog(AgentMaterialUsedImpl.class);
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private MaterialPatsMasterDAO materialPatsMasterDAO;
	private MaterialPatsDetailDAO materialPatsDetailDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;
	private CdSysParamDAO cdSysParamDAO;
	private cn.superion.material.common.ICommMaterialService commMaterialServiceImpl;
	private cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl;
	private IHisServiceFacade hisServiceFacade;
	
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

	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
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

	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public cn.superion.material.common.ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			cn.superion.material.common.ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	public cn.superion.materialDept.common.service.ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	public IHisServiceFacade getHisServiceFacade() {
		return hisServiceFacade;
	}

	public void setHisServiceFacade(IHisServiceFacade hisServiceFacade) {
		this.hisServiceFacade = hisServiceFacade;
	}

	@Override
	public ReObject cancelVerify(String fstrAutoId) {
		ReObject ro = new ReObject("弃审当前已审核过的病人使用代销物资信息");
		MaterialPatsMaster original = materialPatsMasterDAO.findById(fstrAutoId);
		if(original == null){
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的代销物资领用申请主记录！");
		}
		if(!"1".equals(original.getCurrentStatus())){
			throw new RuntimeException("代销物资领用申请还未确认或已生成入出库单据，不能弃审！");
		}
		String patientId = original.getPatientId();
		//String unitsCode = original.getUnitsCode();
		//String storageCode = original.getStorageCode();
		//SysUser user = SessionUtil.getSysUser();
		//String personId = user.getPersonId();
		//是否住院病人
		boolean isInp = "2".equals(original.getPatientType());
		List<MaterialPatsDetail> originalDetails = materialPatsDetailDAO.findUsedByMainAutoId(fstrAutoId);
		if(originalDetails.isEmpty()){
			throw new RuntimeException("病人使用材料明细记录列表中应至少有一个使用数量大于零的记录！");
		}
		//红字病人使用材料明细记录
		List<MaterialPatsDetail> redPatsDetails = new ArrayList<MaterialPatsDetail>();
		//List<MaterialCurrentStock> tmpStocks = new ArrayList<MaterialCurrentStock>();
		for(MaterialPatsDetail bluePatsDetail : originalDetails){
			if(isInp){
				if(bluePatsDetail.getHisBillNo() != null && !"".equals(bluePatsDetail.getHisBillNo())){
					//构造HIS费用红字记录
					redPatsDetails.add(bluePatsDetail.writeRedPatsDetail());
				}
			}
			bluePatsDetail.setHisBillNo(null);
			bluePatsDetail.setRetailPrice(null);
			bluePatsDetail.setRetailMoney(null);
			bluePatsDetail.setHisClass(null);
			bluePatsDetail.setHisCode(null);
			bluePatsDetail.setHisSpec(null);
			bluePatsDetail.setHisUnits(null);
			bluePatsDetail.setAccountDate(null);
			bluePatsDetail.setAccounter(null);
			//增加虚拟库存
			/*MaterialCurrentStock stock = materialCurrentStockDAO.findByUniqueIndex(unitsCode, storageCode, bluePatsDetail.getMaterialId(), bluePatsDetail.getBatch(), bluePatsDetail.getBarCode());
			Double curStockAmount = stock == null ? 0d : stock.getAmount();
			Double curStockAmount2 = curStockAmount + bluePatsDetail.getAmount();
			if(stock == null){
				stock = buildMaterialCurrentStock(unitsCode,storageCode,bluePatsDetail);
				materialCurrentStockDAO.save(stock);
			}else{
				stock.setAmount(curStockAmount2);
			}
			tmpStocks.add(stock);*/
		}
		/*if(original.getOperationNo() == null || "".equals(original.getOperationNo())){
			throw new RuntimeException("代销领用物资申请单业务号为空，不能跟踪蓝字领用出库单！");
		}
		//生成一张已审核的领用出库红单
		Object[] objs1 = commMaterialServiceImpl.findById(original.getOperationNo());
		MaterialRdsMaster blueDMaster = (MaterialRdsMaster) objs1[0];
		if(blueDMaster == null){
			throw new RuntimeException("代销领用物资申请单的蓝字领用出库单不存在！");
		}
		if(blueDMaster.getOperationNo() == null || "".equals(blueDMaster.getOperationNo())){
			throw new RuntimeException("蓝字领用出库单业务号为空，不能跟踪蓝字采购入库单！");
		}
		
		Object[] objs2 = commMaterialServiceImpl.findById(blueDMaster.getOperationNo());
		MaterialRdsMaster blueRMaster = (MaterialRdsMaster) objs2[0];
		if(blueRMaster == null){
			throw new RuntimeException("代销领用物资申请单的蓝字采购入库单不存在！");
		}
		Date curDate = new Date();
		//领用出库单写红字
		MaterialRdsMaster redDMaster = buildRedMaterialRdsMaster(blueDMaster,curDate,personId);
		List<MaterialRdsDetail> redDDetails = buildRedMasterialRdsDetailList(unitsCode,storageCode,(List<MaterialRdsDetail>)objs1[1],tmpStocks,true);
		//采购入库单写红字
		MaterialRdsMaster redRMaster = buildRedMaterialRdsMaster(blueRMaster,curDate,personId);
		List<MaterialRdsDetail> redRDetails = buildRedMasterialRdsDetailList(unitsCode,storageCode,(List<MaterialRdsDetail>)objs2[1],tmpStocks,false);
		saveRds(redRMaster,redRDetails);
		saveRds(redDMaster,redDDetails);*/
		//更新领用单据
		original.setCurrentStatus("0");
		original.setVerifyDate(null);
		original.setVerifier(null);
		original.setOperationNo(null);
		//写HIS红字费用
		if(!redPatsDetails.isEmpty()){
			hisServiceFacade.getHisBillService().save(original.buildPatsBillMaster(), redPatsDetails);
		}
		Double prepaymentsLeft = hisServiceFacade.getHisPatService().findPrepaymentLeft(patientId);
		List<Object> data = new ArrayList<Object>();
		data.add(prepaymentsLeft);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject deleteApply(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的代销物资领用申请单");
		MaterialPatsMaster master = materialPatsMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("代销物资领用申请不存在！");
		if(!"0".equals(master.getCurrentStatus())){
			throw new RuntimeException("代销物资领用申请已确认，不能删除！");
		}
		materialPatsDetailDAO.delByMainAutoId(fstrAutoId);
		materialPatsMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findApplyDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前代销物资领用申请单的详细信息记录");
		MaterialPatsMaster master = materialPatsMasterDAO.findById(fstrAutoId);
		List<MaterialPatsDetail> details = null;
		Double prepaymentsLeft = 0d;
		if(master != null){
			details = materialPatsDetailDAO.findByMainAutoId(fstrAutoId);
			prepaymentsLeft = hisServiceFacade.getHisPatService().findPrepaymentLeft(master.getPatientId());
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		data.add(prepaymentsLeft);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findApplyMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的代销物资领用申请单列表");
		SysUser user = SessionUtil.getSysUser();
		List<Object> data = materialPatsMasterDAO.findAutoIdsByCondition(user.getUnitsCode(),BILL_TYPE,fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveApply(MaterialPatsMaster fmaster,
			List<MaterialPatsDetail> fdetails) {
		ReObject ro = new ReObject("保存代销物资领用申请信息");
		if(fmaster == null)
			throw new RuntimeException("代销物资领用申请主记录不能为空！");
		if(fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("代销物资领用申请明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		Date curDate = new Date();
		String storageCode = fmaster.getStorageCode();
		if(storageCode == null || "".equals(storageCode)){
			storageCode = "0";
			fmaster.setStorageCode(storageCode);
		}
		fmaster.setBillType(BILL_TYPE);
		String autoId = fmaster.getAutoId(); 
		if(autoId == null || "".equals(autoId)){
			//新增
			String billNo = fmaster.getBillNo();
			if(billNo == null || "".equals(billNo)){
				fmaster.setBillNo(deptCommMaterialServiceImpl.getNextBillNo(RdConstant.D));
			}else{
				//新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if(!materialPatsMasterDAO.checkBillNoUnique(unitsCode,storageCode,billNo)){
					throw new RuntimeException("代销物资领用申请单据编号["+billNo+"]在单位["+unitsCode+"],仓库["+storageCode+"]下有重复");
				}
			}
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			fmaster.setCurrentStatus("0");
			if(fmaster.getApplyDate() == null){
				fmaster.setApplyDate(curDate);
			}
			materialPatsMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		}else{
			//修改
			MaterialPatsMaster original = materialPatsMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的代销物资领用申请主记录！");
			}
			if(!"0".equals(original.getCurrentStatus())){
				throw new RuntimeException("代销物资领用申请已确认，不能修改！");
			}
			fmaster.setCurrentStatus("0");
			materialPatsMasterDAO.merge(fmaster);
			materialPatsDetailDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for(MaterialPatsDetail detail : fdetails){
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			if(detail.getBatch() == null || detail.getBatch().equals("")){
				detail.setBatch("0");
			}
			if(detail.getBarCode() == null || detail.getBarCode().equals("")){
				detail.setBarCode("0");
			}
			if(detail.getRefundSign() == null || detail.getRefundSign().equals("")){
				detail.setRefundSign("0");
			}
			materialPatsDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId, List<MaterialPatsDetail> details) {
		ReObject ro = new ReObject("确认病人使用代销物资信息,并记账");
		MaterialPatsMaster original = materialPatsMasterDAO.findById(fstrAutoId);
		if(original == null){
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的代销物资领用申请主记录！");
		}
		if(!"0".equals(original.getCurrentStatus())){
			throw new RuntimeException("代销物资领用申请已确认，不能再次确认！");
		}
		String patientId = original.getPatientId();
		String unitsCode = original.getUnitsCode();
		SysUser user = SessionUtil.getSysUser();
		Date curDate = new Date();
		String personId = user.getPersonId();
		//是否住院病人
		boolean isInp = "2".equals(original.getPatientType());
		List<MaterialPatsDetail> factPatsDetails = new ArrayList<MaterialPatsDetail>();
		Short curSerialNo = 0;
		//写HIS费用依赖的病人使用材料明细
		List<MaterialPatsDetail> hisPatsDetails = new ArrayList<MaterialPatsDetail>();
		for(MaterialPatsDetail detail : details){
			Double useAmount = detail.getAmount(); 
			if(useAmount > 0){
				String oDetailAutoId = detail.getAutoId();
				MaterialPatsDetail originalDetail = oDetailAutoId == null || oDetailAutoId.equals("") ? null : materialPatsDetailDAO.findById(detail.getAutoId());
				if(originalDetail == null){
					//确认时有可能是增加
					detail.setMainAutoId(fstrAutoId);
					if(curSerialNo == 0){
						curSerialNo = materialPatsDetailDAO.findMaxSerialNo(fstrAutoId);
					}
					detail.setSerialNo(++curSerialNo);
					if(detail.getBatch() == null || detail.getBatch().equals("")){
						detail.setBatch("0");
					}
					if(detail.getBarCode() == null || detail.getBarCode().equals("")){
						detail.setBarCode("0");
					}
					materialPatsDetailDAO.save(detail);
					//throw new RuntimeException("代销物资明细[系统标识号："+detail.getAutoId()+"]不存在!");
				}
				if(isInp){
					CdMaterialDict madt = cdMaterialDictDAO.findById(unitsCode, detail.getMaterialId());
					if(madt == null){
						throw new RuntimeException("物资"+detail.getMaterialName()+"[id:"+detail.getMaterialId()+"]在系统中不存在！");
					}
					Double retailPrice = madt.getRetailPrice();
					if(madt.getHisCode() == null || "".equals(madt.getHisCode())){
						log.warn("物资"+detail.getMaterialName()+"[id:"+detail.getMaterialId()+"]未做HIS费用对照，不能计HIS病人费用！");
					}else{
						retailPrice = hisServiceFacade.getHisBaseDictService().findPrice(madt.getHisClass(), madt.getHisCode(), madt.getHisSpec(), madt.getHisUnits());
						detail.setHisClass(madt.getHisClass());
						detail.setHisCode(madt.getHisCode());
						detail.setHisSpec(madt.getHisSpec());
						detail.setHisUnits(madt.getHisUnits());
						hisPatsDetails.add(detail);
					}
					detail.setAccountDate(curDate);
					detail.setAccounter(personId);
					detail.updateRetailPrice(retailPrice);
					factPatsDetails.add(detail);
				}
			}
		}
		//更新领用单据
		original.setCurrentStatus("1");
		original.setVerifyDate(new Date());
		original.setVerifier(personId);
		//写HIS费用
		if(!hisPatsDetails.isEmpty()){
			hisServiceFacade.getHisBillService().save(original.buildPatsBillMaster(), hisPatsDetails);
		}
		//更新病人使用材料明细的HIS费用对照信息
		for(MaterialPatsDetail detail : factPatsDetails){
			materialPatsDetailDAO.merge(detail);
		}
		Double prepaymentsLeft = hisServiceFacade.getHisPatService().findPrepaymentLeft(patientId);
		List<Object> data = new ArrayList<Object>();
		data.add(prepaymentsLeft);
		data.add(original);
		ro.setData(data);
		return ro;
	}
	
	/**
	 * 以代销物资明细为模板，构造物资当前库存
	 * @param unitsCode
	 * @param storageCode
	 * @param detail 代销物资明细
	 * @return
	 */
	private MaterialCurrentStock buildMaterialCurrentStock(String unitsCode,String storageCode,MaterialPatsDetail detail){
		MaterialCurrentStock stock = new MaterialCurrentStock();
		stock.setUnitsCode(unitsCode);
    	stock.setStorageCode(storageCode);
    	stock.setMaterialClass(detail.getMaterialClass());
    	stock.setBarCode(detail.getBarCode());
    	stock.setMaterialId(detail.getMaterialId());
    	stock.setMaterialCode(detail.getMaterialCode());
    	stock.setMaterialName(detail.getMaterialName());
    	stock.setMaterialSpec(detail.getMaterialSpec());
    	stock.setMaterialUnits(detail.getMaterialUnits());
    	stock.setAmount(detail.getAmount());
    	stock.setFactoryCode(detail.getFactoryCode());
    	stock.setMadeDate(detail.getMadeDate());
    	stock.setBatch(detail.getBatch());
    	stock.setAvailDate(detail.getAvailDate());
		return stock;
	}
	
	private MaterialRdsMaster buildMaterialRdsMaster(String rdFlag,String operationType,String personId,String unitsCode,String storageCode){
		Date curDate = new Date();
		MaterialRdsMaster master = new MaterialRdsMaster();
		master.setUnitsCode(unitsCode);
		master.setStorageCode(storageCode);
		master.setBillNo(commMaterialServiceImpl.getNextBillNo(rdFlag, storageCode));
		master.setBillDate(curDate);
		master.setInvoiceType("1");
		master.setRdFlag(rdFlag);
		master.setOperationType(operationType);
		//申请科室，也是执行科室，也是病人所在科室
		//master.setDeptCode(deptCode);
		master.setPersonId(personId);
		//供应商信息应追溯物资代销入库记录中的
		//master.setSalerCode(salerCode)
		//master.setSalerName(salerName);
		master.setMaker(personId);
		master.setMakeDate(curDate);
		master.setVerifier(personId);
		master.setVerifyDate(curDate);
		master.setCurrentStatus("1");
		return master;
	}
	
	/**
	 * 以代销物资明细为模板，构造收发存明细
	 * @param pdetail
	 * @param curStockAmount
	 * @param stock 获取进价，对于代销物资不托管的，该参数可能为null
	 * @return
	 */
	private MaterialRdsDetail buildMaterialRdsDetail(MaterialPatsDetail pdetail,Double curStockAmount,MaterialCurrentStock stock){
		MaterialRdsDetail detail = new MaterialRdsDetail();
		detail.setMaterialClass(pdetail.getMaterialClass());
		detail.setBarCode(pdetail.getBarCode());
		detail.setMaterialId(pdetail.getMaterialId());
		detail.setMaterialCode(pdetail.getMaterialCode());
		detail.setMaterialName(pdetail.getMaterialName());
		detail.setMaterialSpec(pdetail.getMaterialSpec());
		detail.setMaterialUnits(pdetail.getMaterialUnits());
		detail.setAmount(pdetail.getAmount());
		if(stock != null){
			detail.setTradePrice(stock.getTradePrice());
			if(stock.getTradePrice() != null)
				detail.setTradeMoney(stock.getTradePrice() * detail.getAmount());
		}
		detail.setRetailPrice(pdetail.getRetailPrice());
		detail.setRetailMoney(pdetail.getRetailMoney());
		detail.setFactoryCode(pdetail.getFactoryCode());
		detail.setMadeDate(pdetail.getMadeDate());
		detail.setBatch(pdetail.getBatch());
		detail.setAvailDate(pdetail.getAvailDate());
		detail.setCurrentStockAmount(curStockAmount);
		detail.setAgentSign("0");
		return detail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findByPatientId(String fstrPatientId) {
		ReObject ro = new ReObject("根据病人标识号或住院号查询病人基本信息");
		PatsVisit pv = hisServiceFacade.getHisPatService().findInPatInfo(fstrPatientId);
		List data = new ArrayList();
		data.add(pv);
		ro.setData(data);
		return ro;
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
			materialRdsDetailDAO.save(detail);
		}
	}
	
	@SuppressWarnings("unused")
	private MaterialRdsMaster buildRedMaterialRdsMaster(MaterialRdsMaster blueMaster,Date curDate,String personId){
		MaterialRdsMaster redMaster = blueMaster.writeRed();
		redMaster.setMakeDate(curDate);
		redMaster.setMaker(personId);
		redMaster.setVerifyDate(curDate);
		redMaster.setVerifier(personId);
		redMaster.setCurrentStatus("1");
		return redMaster;
	}
	
	/**
	 * 
	 * @param blueDetails
	 * @param tmpStocks 
	 * @param isDeliver 是否出库
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<MaterialRdsDetail> buildRedMasterialRdsDetailList(String unitsCode,String storageCode,List<MaterialRdsDetail> blueDetails,
			List<MaterialCurrentStock> tmpStocks,boolean isDeliver){
		List<MaterialRdsDetail> redDetails = new ArrayList<MaterialRdsDetail>();
		for(MaterialRdsDetail blueDetail : blueDetails){
			MaterialRdsDetail redDetail = blueDetail.writeRed();
			MaterialCurrentStock stock = tmpStocks.get(tmpStocks.indexOf(new MaterialCurrentStock(unitsCode,storageCode,blueDetail.getBarCode(),blueDetail.getMaterialId(),blueDetail.getBatch())));
			if(stock != null){
				Double amount = isDeliver ? stock.getAmount() : stock.getAmount() - blueDetail.getAmount();
				redDetail.setCurrentStockAmount(amount);
			}else{
				log.warn("库存记录不存在，无法更新收发存红单明细记录的当前库存量！");
			}
			redDetails.add(redDetail);
		}
		return redDetails;
	}

	@Override
	public ReObject saveRDBill(String[] fstrAutoIds) {
		ReObject ro = new ReObject("汇总病人使用代销材料记录，生成一张已审核的采购入库单据和一张已审核的领用出库单据");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String storageCode = null;
		String appCode = RdConstant.APP_CODE_STORAGE_MATERIAL;
		//加载系统参数：是否允许零出库
		boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0").equals("1"); 
		//入库单明细记录
		List<MaterialRdsDetail> rDetails = new ArrayList<MaterialRdsDetail>();
		//出库明细记录
		List<MaterialRdsDetail> dDetails = new ArrayList<MaterialRdsDetail>();
		//是否代销物资托管
		boolean isAgentTrusteeship = "1".equals(cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_AGENT_TRUSTEESHIP,"0"));
		for(String autoId : fstrAutoIds){
			MaterialPatsMaster original = materialPatsMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的代销物资领用申请主记录！");
			}
			if(!"1".equals(original.getCurrentStatus())){
				throw new RuntimeException("代销物资领用申请未确认或已生成入出库单据，不能生成入出库单！");
			}
			original.setCurrentStatus("2");
			unitsCode = original.getUnitsCode();
			storageCode = original.getStorageCode();
			List<MaterialPatsDetail> details = materialPatsDetailDAO.findByMainAutoId(autoId);
			for(MaterialPatsDetail detail : details){
				Double useAmount = detail.getAmount(); 
				if(useAmount > 0){
					//查询虚拟库存数量
					MaterialCurrentStock stock = materialCurrentStockDAO.findByUniqueIndex(unitsCode, storageCode, detail.getMaterialId(), detail.getBatch(), detail.getBarCode());
					//入库时的当前库存量
					Double curStockAmount = stock == null ? 0d : stock.getAmount();
					//出库时的当前库存量
					Double curStockAmount2 = 0d;
					if(isAgentTrusteeship){
						//托管，需更新当前库存量
						if(!isZeroDeliver){
							if(curStockAmount < useAmount){
								throw new RuntimeException("物资"+detail.getMaterialName()+"[id:"+detail.getMaterialId()+",条码:"+detail.getBarCode()+",批号:"+detail.getBatch()+"]的当前库存量"+curStockAmount+"小于使用量"+useAmount+",系统参数不允许零出库！");
							}
						}
						//扣减虚拟库存数量
						curStockAmount2 = curStockAmount - useAmount;
						if(stock == null){
							stock = buildMaterialCurrentStock(unitsCode,storageCode,detail);
							materialCurrentStockDAO.save(stock);
						}else{
							stock.setAmount(curStockAmount2);
						}
					}else{//非托管，无需更新当前库存量
						curStockAmount += useAmount; 
						curStockAmount2 = curStockAmount - useAmount;
					}
					//构造采购入库单明细记录
					rDetails.add(buildMaterialRdsDetail(detail,curStockAmount,stock));
					//构造领用出库单明细记录
					dDetails.add(buildMaterialRdsDetail(detail,curStockAmount2,stock));
				}
			}
		}
		if(rDetails.isEmpty()){
			throw new RuntimeException("病人使用材料明细记录列表中应至少有一个使用数量大于零的记录！");
		}
		//生成一张已审核的采购入库单
		MaterialRdsMaster rMaster = buildMaterialRdsMaster(RdConstant.R,RdConstant.R_PURCHASE,personId,unitsCode,storageCode);
		//根据系统参数来确定入库类别
		rMaster.setRdType(cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_AGENT_R_TYPE));
		saveRds(rMaster,rDetails);
		MaterialRdsMaster dMaster = buildMaterialRdsMaster(RdConstant.D,RdConstant.D_DELIVER,personId,unitsCode,storageCode);
		//根据系统参数来确定出库类别
		dMaster.setRdType(cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_AGENT_D_TYPE));
		//出库主记录的业务号写入库主记录的autoId
		dMaster.setOperationNo(rMaster.getAutoId());
		//出库明细记录的来源入库系统标识号写入库明细记录的autoId
		for(int i = 0 ;i < dDetails.size(); i++){
			dDetails.get(i).setSourceInputAutoId(rDetails.get(i).getAutoId());
		}
		saveRds(dMaster,dDetails);
		//病人使用材料主记录关联出库单主记录autoId
		for(String autoId : fstrAutoIds){
			MaterialPatsMaster original = materialPatsMasterDAO.findById(autoId);
			//业务号为出库单autoId
			original.setOperationNo(dMaster.getAutoId());
		}
		List<Object> data = new ArrayList<Object>();
		data.add(rMaster);
		data.add(rDetails);
		data.add(dMaster);
		data.add(dDetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查询代销物资明细列表");
		List<MaterialPatsDetail> data = materialPatsDetailDAO.findByMainAutoId(fstrAutoId);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findVerifiedListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询已审核的病人使用代销物资记录");
		fparameter.getConditions().put("currentStatus", "1");
		List<MaterialPatsMaster> data = materialPatsMasterDAO.findAgentListByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
