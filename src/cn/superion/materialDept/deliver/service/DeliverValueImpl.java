package cn.superion.materialDept.deliver.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.materialDept.dao.CommSysParameterDAO;
import cn.superion.materialDept.dao.MaterialPatsDetailDAO;
import cn.superion.materialDept.dao.MaterialPatsMasterDAO;
import cn.superion.materialDept.dao.MaterialRdsDetailDeptDAO;
import cn.superion.materialDept.dao.VMaterialPatsDAO;
import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.MaterialPatsMaster;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.materialDept.entity.PatsBillMaster;
import cn.superion.materialDept.entity.PatsVisit;
import cn.superion.materialDept.entity.VMaterialPats;
import cn.superion.materialDept.entity.VMaterialRdsDept;
import cn.superion.materialDept.his.service.IHisServiceFacade;
import cn.superion.system.dao.SysUnitInforDAO;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class DeliverValueImpl implements IDeliverValue {
//	private Log log = LogFactory.getLog(PatsFeeImpl.class);
	//2：费用记账
	private static final String BILL_TYPE = "2";
	private MaterialPatsMasterDAO materialPatsMasterDAO;
	private MaterialPatsDetailDAO materialPatsDetailDAO;
	private VMaterialPatsDAO vMaterialPatsDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;
	private IHisServiceFacade hisServiceFacade;
	private MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO; 
	private CdSysParamDAO cdSysParamDAO;
	private SysUnitInforDAO sysUnitInforDAO;
	private CommSysParameterDAO commSysParameterDAO;
	
	public CommSysParameterDAO getCommSysParameterDAO() {
		return commSysParameterDAO;
	}

	public void setCommSysParameterDAO(CommSysParameterDAO commSysParameterDAO) {
		this.commSysParameterDAO = commSysParameterDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public VMaterialPatsDAO getvMaterialPatsDAO() {
		return vMaterialPatsDAO;
	}

	public void setvMaterialPatsDAO(VMaterialPatsDAO vMaterialPatsDAO) {
		this.vMaterialPatsDAO = vMaterialPatsDAO;
	}

	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
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
	public ReObject deletePatsFee(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的记账单据");
		MaterialPatsMaster master = materialPatsMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("记账单据不存在！");
		List<MaterialPatsDetail> details=materialPatsDetailDAO.findByMainAutoId(fstrAutoId);
		
		for (MaterialPatsDetail detail:details){
			if(detail.getRefundDate()!=null){
				throw new RuntimeException("该单据中的物资:["+detail.getMaterialName()+"]已收费不能删除！");
			}
			if(detail.getFactInSign()!=null && detail.getFactInSign().equals("1")){
				throw new RuntimeException("该单据中的物资:["+detail.getMaterialName()+"]已办理实际入库不能删除！");
			}
		}
		
		materialPatsDetailDAO.delByMainAutoId(fstrAutoId);
		materialPatsMasterDAO.delete(master);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findPatsFeeByPatientId(String fstrPatientId) {
		ReObject ro = new ReObject("根据病人标识号或住院号查询病人基本信息和费用记帐列表");
		PatsVisit pv = hisServiceFacade.getHisPatService().findInPatInfo(fstrPatientId);
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String storageCode = "V" + user.getDeptCode();
		List<VMaterialPats> patsFeelist = vMaterialPatsDAO.findByPatientId(BILL_TYPE,unitsCode,storageCode,fstrPatientId);
		List data = new ArrayList();
		data.add(pv);
	    data.add(patsFeelist);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findPatsFeeDetailById(String fstrAutoId,String fstrRefundSign) {
		ReObject ro = new ReObject("查看当前记账单的详细信息记录");
		MaterialPatsMaster master = materialPatsMasterDAO.findById(fstrAutoId);
		List<MaterialPatsDetail> details = null;
		Double prepaymentsLeft = 0d;
		if(master != null){
			details = materialPatsDetailDAO.findByIdAndSign(fstrAutoId);
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
	public ReObject findPatsFeeMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的记账单列表");
		SysUser user = SessionUtil.getSysUser();
		//虚拟仓库就是科室自己
		fparameter.getConditions().put("storageCode","V" + user.getDeptCode());
		List<Object> data = materialPatsMasterDAO.findAutoIdsByCondition(user.getUnitsCode(),BILL_TYPE,fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject refundFee(String fstrAutoId,String[] fstrAutoIds) {
		ReObject ro = new ReObject("对已记帐的病人使用材料费用退费");
		
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String appCode = user.getAppCode();

//		Date curDate = DateUtil.getCurDate();
		Date curDate = cdSysParamDAO.getSysDate();
		
		//退费有效天数
		String refundPara=cdSysParamDAO.findByParaCode(unitsCode,
				appCode,"0701", "");
		long refundDays = 0;
		if(refundPara !=null && !refundPara.equals("")){
			refundDays = Long.valueOf(refundPara);
		}
		
		MaterialPatsMaster patMaster=materialPatsMasterDAO.findById(fstrAutoId);
		
		String fstrPatientId=patMaster.getPatientId();
		
		//不需要退费的明细
		List<MaterialPatsDetail> noRefundDetails = materialPatsDetailDAO.findNoByAutoIds(fstrAutoIds,fstrAutoId);
		//需要退费的明细
		List<MaterialPatsDetail> refundDetails = materialPatsDetailDAO.findByAutoIds(fstrAutoIds);
		
		if(refundDetails.isEmpty()){
			throw new RuntimeException("费用明细不存在，不能退费！");
		}
		for (MaterialPatsDetail detail:refundDetails){
			if(refundDays>0){
				long accountDate=detail.getAccountDate().getTime();
				long curTime=curDate.getTime();
				long spaceDays=curTime-accountDate;
				refundDays=refundDays*24*60*60*1000;
				if(spaceDays > refundDays){
					throw new RuntimeException("物资["+detail.getMaterialName()+"]不在退费的有效时间内！");
				}
			}
			if(detail.getFactInSign().equals("1")){
				throw new RuntimeException("物资["+detail.getMaterialName()+"]已入库，不能退费！");
			}
			if("1".equals(detail.getRefundSign())){
				throw new RuntimeException("费用[系统标识号："+detail.getAutoId()+"]已退费，不能再次退费！");
			}
		}
		
		
		//加"V"表示虚拟仓库
		String storageCode = "V" + user.getDeptCode();
		
		if(noRefundDetails.size()==0){
			//明细全部退费，则删除整个单据		
			materialPatsMasterDAO.delete(patMaster);
			materialPatsDetailDAO.delByMainAutoId(patMaster.getAutoId());
			fstrAutoId="";
		}
		else{
			//部分退费则只删除单条明细
			materialPatsDetailDAO.delByAutoId(fstrAutoIds);
		}
		
		//红字出库明细记录
		List<MaterialRdsDetailDept> redDDetails = new ArrayList<MaterialRdsDetailDept>();
		for(MaterialPatsDetail detail : refundDetails){
			redDDetails.add(buildMaterialRdsDetailDept(detail,false));
		}
		//红字销售出库主记录
		MaterialRdsMasterDept redDMaster = buildRedMaterialRdsMasterDept(unitsCode,storageCode,RdConstant.D,RdConstant.D_SALE,personId);
		deptCommMaterialServiceImpl.save(redDMaster,redDDetails);
		List<MaterialPatsDetail> redPatsDetails = new ArrayList<MaterialPatsDetail>();
		//要写HIS红字费用的病人使用材料蓝字明细记录
		List<MaterialPatsDetail> blueHisPatsDetails = new ArrayList<MaterialPatsDetail>();
		for(MaterialPatsDetail bluePatsDetail : refundDetails){
			if(bluePatsDetail.getHisBillNo() != null && !"".equals(bluePatsDetail.getHisBillNo())){
				blueHisPatsDetails.add(bluePatsDetail);
			}else{
				redPatsDetails.add(bluePatsDetail.writeRedPatsDetail());
			}
		}
		if(!blueHisPatsDetails.isEmpty()){
			//写HIS费用,返回病人使用材料红字明细记录
			redPatsDetails.addAll(hisServiceFacade.getHisBillService().writeRed(new PatsBillMaster(unitsCode,fstrPatientId), blueHisPatsDetails));
		}
		Double prepaymentsLeft = hisServiceFacade.getHisPatService().findPrepaymentLeft(fstrPatientId);
		List<Object> data = new ArrayList<Object>();
		data.add(fstrAutoId);
		data.add(prepaymentsLeft);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject savePatsFee(MaterialPatsMaster fmaster,
			List<MaterialPatsDetail> fdetails) {
		ReObject ro = new ReObject("保存记账单据信息");
		if(fmaster == null)
			throw new RuntimeException("记账单据主记录不能为空！");
		if(fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("记账单据明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		Date curDate = new Date();
		String storageCode = "V" + user.getDeptCode();
		if(storageCode == null || "".equals(storageCode)){
			storageCode = "0";
		}
		fmaster.setStorageCode(storageCode);
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
					throw new RuntimeException("记账单据编号["+billNo+"]在单位["+unitsCode+"],仓库["+storageCode+"]下有重复");
				}
			}
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			fmaster.setCurrentStatus("0");
			materialPatsMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		}else{
			//修改
			MaterialPatsMaster original = materialPatsMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的记账单据主记录！");
			}
//			if(!"0".equals(original.getCurrentStatus())){
//				throw new RuntimeException("记账单据已审核，不能修改！");
//			}
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
			if(detail.getFactInSign() == null || detail.getFactInSign().equals("")){
				detail.setFactInSign("0");
			}
			detail.setRefundSign("");
			materialPatsDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId,String[] fstrDetailAutoIds) {
		ReObject ro = new ReObject("审核当前的账单信息");
		MaterialPatsMaster master = materialPatsMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("记账单据不存在！");
//		if(!"0".equals(master.getCurrentStatus())){
//			throw new RuntimeException("记账单据已审核，不能审核！");
//		}
		String patientId = master.getPatientId();
		String unitsCode = master.getUnitsCode();
		String personId = SessionUtil.getPersonId();
		Date curDate = new Date();
		
		//是否住院病人
		boolean isInp = "2".equals(master.getPatientType());
		//出库明细记录
		List<MaterialRdsDetailDept> dDetails = new ArrayList<MaterialRdsDetailDept>();
		//写HIS费用依赖的病人使用材料明细
		List<MaterialPatsDetail> hisPatsDetails = new ArrayList<MaterialPatsDetail>();
		
		PatsVisit patsVisit=hisServiceFacade.getHisPatService().findInPatInfo(patientId);

		//欠费标准
		String paramBill=commSysParameterDAO.findById(unitsCode, "0111", "0406");
		Double stopMoeny=paramBill==null || paramBill.equals("") ? 0d : Double.valueOf(paramBill);
		//HIS定义的材料分类编码
		String reckoClassParam=commSysParameterDAO.findById(unitsCode, "0111", "0416");
		String[] reckoClass=reckoClassParam.split(",");
		//欠费材料费是否限价
		String stopBillOver=commSysParameterDAO.findById(unitsCode, "0111", "0417");
		//欠费材料费限价
		String paramValue=commSysParameterDAO.findById(unitsCode, "0111", "0418");
		Double maxClassMoeny=paramValue==null || paramValue.equals("") ? 0d : Double.valueOf(paramValue);//欠费时允许的最大的材料费用
		
		Double curPatsClassBillMoney=0d;//本次明细中所有的材料费
		
		for(String autoId : fstrDetailAutoIds){
			
			MaterialPatsDetail detail=materialPatsDetailDAO.findById(autoId);
			
			if(detail.getRefundSign()!=null && detail.getRefundSign().equals("0")){
				throw new RuntimeException("物资"+detail.getMaterialName()+"[条形码:"+detail.getBarCode()+"]已收费，不能重复收费！");
			}
			VMaterialRdsDept stock=materialRdsDetailDeptDAO.findByBarCode(detail.getBarCode(),false);
			if(stock == null){
				throw new RuntimeException("当前库存中没有可使用的物资"+detail.getMaterialName()+"[条形码:"+detail.getBarCode()+"]！");
			}
			stock=materialRdsDetailDeptDAO.findByBarCode(detail.getBarCode(),true);
			if(stock == null){
				throw new RuntimeException("物资"+detail.getMaterialName()+"[条形码:"+detail.getBarCode()+"]已被使用！");
			}
			CdMaterialDict madt = materialRdsDetailDeptDAO.findByMaterialId(detail.getMaterialId());
			if(isInp){
				if(madt == null){
					throw new RuntimeException("物资"+detail.getMaterialName()+"[id:"+detail.getMaterialId()+"]在系统中不存在！");
				}

				//材料费用 ryh 13.01.21
				String _reckoClass=madt.getReckoClass();
				if(_reckoClass!=null && !_reckoClass.equals("")){
					for(String recko:reckoClass){
						if(recko.equals(_reckoClass)){
							curPatsClassBillMoney+=detail.getRetailMoney();
							break;
						}
					}
				}
				
				Double retailPrice = madt.getRetailPrice();
//				if(madt.getHisCode() == null || "".equals(madt.getHisCode())){
//					throw new RuntimeException("物资"+detail.getMaterialName()+"[id:"+detail.getMaterialId()+"]未做HIS费用对照，不能计HIS病人费用！");
//				}else{
//					retailPrice = hisServiceFacade.getHisBaseDictService().findPrice(madt.getHisClass(), madt.getHisCode(), madt.getHisSpec(), madt.getHisUnits());
					detail.setHisClass(madt.getHisClass());
					detail.setHisCode(madt.getHisCode());
					detail.setHisSpec(madt.getHisSpec());
					detail.setHisUnits(madt.getHisUnits());
					hisPatsDetails.add(detail);
//				}
				detail.setAccountDate(curDate);
				detail.setAccounter(personId);
				detail.updateRetailPrice(retailPrice);
				detail.setRefundSign("0");
				//改写收费标志
				materialPatsDetailDAO.updateRefundSignById(autoId,"0");
				
				//改写入库记录的出库标志
				MaterialRdsDetailDept rDetail=materialRdsDetailDeptDAO.findByRByBarCode(detail.getBarCode());
				rDetail.setOutAmount(detail.getAmount());
				rDetail.setOutSign("1");
			}
			MaterialRdsDetailDept detailDept=new MaterialRdsDetailDept();
			detailDept=buildMaterialRdsDetailDept(detail,true);
			//构造销售出库单明细记录
			dDetails.add(detailDept);
		}
		
		if(stopBillOver!=null && stopBillOver.equals("1")){
			if(patsVisit.getPrepaymentsLeft()<stopMoeny){
				if(maxClassMoeny<curPatsClassBillMoney){
					throw new RuntimeException("当前材料费["+curPatsClassBillMoney+"]超过材料费上限["+maxClassMoeny+"]，不能继续收费！");
				}
			}
		}
		
		//生成一张已审核的销售出库（202）单
		MaterialRdsMasterDept dMaster = buildMaterialRdsMasterDept(RdConstant.D,RdConstant.D_SALE,personId,master);
		deptCommMaterialServiceImpl.save(dMaster,dDetails);
		//更新记帐单据
//		master.setCurrentStatus("1");
		master.setVerifyDate(new Date());
		master.setVerifier(personId);
		//业务号为出库单autoId
		master.setOperationNo(dMaster.getAutoId());
		//写HIS费用
		if(!hisPatsDetails.isEmpty()){ 
			hisServiceFacade.getHisBillService().save(master.buildPatsBillMaster(), hisPatsDetails);
		}
		Double prepaymentsLeft = hisServiceFacade.getHisPatService().findPrepaymentLeft(patientId);
		List<Object> data = new ArrayList<Object>();
		data.add(prepaymentsLeft);
		data.add(master);
		ro.setData(data);
		return ro;
	}
	
	private MaterialRdsMasterDept buildMaterialRdsMasterDept(String rdFlag,String operationType,String personId,MaterialPatsMaster pmaster){
		Date curDate = new Date();
		MaterialRdsMasterDept master = new MaterialRdsMasterDept();
		master.setUnitsCode(pmaster.getUnitsCode());
		master.setStorageCode(pmaster.getStorageCode());
		master.setBillDate(curDate);
		master.setInvoiceType("1");
		master.setRdFlag(rdFlag);
		//master.setRdType("");
		master.setOperationType(operationType);
		//申请科室，也是执行科室，也是病人所在科室
		master.setDeptCode(pmaster.getDeptCode());
//		master.setSupplyDeptCode()
		master.setPersonId(personId);
		master.setMaker(personId);
		master.setMakeDate(curDate);
		master.setVerifier(personId);
		master.setVerifyDate(curDate);
		master.setCurrentStatus("1");
		return master;
	}
	
	private MaterialRdsMasterDept buildRedMaterialRdsMasterDept(String unitsCode,String storageCode,String rdFlag,String operationType,String personId){
		Date curDate = new Date();
		MaterialRdsMasterDept redMaster = new MaterialRdsMasterDept();
		redMaster.setUnitsCode(unitsCode);
		redMaster.setStorageCode(storageCode);
		redMaster.setBillDate(curDate);
		redMaster.setInvoiceType("2");
		redMaster.setRdFlag(rdFlag);
		//TODO rdType
		//master.setRdType("");
		redMaster.setOperationType(operationType);
		//申请科室，也是执行科室，也是病人所在科室
		redMaster.setDeptCode(storageCode);
		//master.setSupplyDeptCode()
		redMaster.setPersonId(personId);
		redMaster.setMaker(personId);
		redMaster.setMakeDate(curDate);
		redMaster.setVerifier(personId);
		redMaster.setVerifyDate(curDate);
		redMaster.setCurrentStatus("1");
		return redMaster;
	}
	
	/**
	 * 以病人使用材料明细为模板，构造收发存明细
	 * @param pdetail
	 * @param isBlue 是否蓝字单据
	 * @return
	 */
	private MaterialRdsDetailDept buildMaterialRdsDetailDept(MaterialPatsDetail pdetail,boolean isBlue){
		MaterialRdsDetailDept detail = new MaterialRdsDetailDept();
		detail.setMaterialClass(pdetail.getMaterialClass());
		detail.setBarCode(pdetail.getBarCode());
		detail.setMaterialId(pdetail.getMaterialId());
		detail.setMaterialCode(pdetail.getMaterialCode());
		detail.setMaterialName(pdetail.getMaterialName());
		detail.setMaterialSpec(pdetail.getMaterialSpec());
		detail.setMaterialUnits(pdetail.getMaterialUnits());
		detail.setAmount(isBlue ? pdetail.getAmount() : -pdetail.getAmount());
		detail.setTradePrice(pdetail.getTradePrice());
		detail.setTradeMoney(pdetail.getTradeMoney());
		detail.setOutAmount(detail.getAmount());
		detail.setOutSign("1");
		detail.setRetailPrice(pdetail.getRetailPrice());
		detail.setRetailMoney(isBlue ? pdetail.getRetailMoney() : -pdetail.getRetailMoney());
		detail.setWholeSalePrice(pdetail.getWholeSalePrice());
		detail.setWholeSaleMoney(pdetail.getWholeSaleMoney());
		detail.setFactoryCode(pdetail.getFactoryCode());
		detail.setMadeDate(pdetail.getMadeDate());
		detail.setBatch(pdetail.getBatch());
		detail.setAvailDate(pdetail.getAvailDate());
		detail.setAgentSign("1");
		detail.setHighValueSign("1");
		detail.setChargeSign(pdetail.getChargeSign());
		detail.setMaterialBarCode(pdetail.getMaterialBarCode());
		detail.setIsGive(detail.getIsGive());
		return detail;
	}

	@Override
	public ReObject findMaterialDetailByBarCode(String fstrBarCode){
		ReObject ro = new ReObject("根据物资条形码查询物资明细");
		
		VMaterialRdsDept detail=materialRdsDetailDeptDAO.findByBarCode(fstrBarCode,false);
		if(detail==null){
			throw new RuntimeException("没有查询到条形码["+fstrBarCode+"]对应的物资信息！");
		}
		detail=materialRdsDetailDeptDAO.findByBarCode(fstrBarCode,true);
		if(detail==null){
			throw new RuntimeException("条形码["+fstrBarCode+"]对应的物资已被使用！");
		}
		
		List<VMaterialRdsDept> data=new ArrayList<VMaterialRdsDept>();
		data.add(detail);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findPatsVisitByInpNo(String fstrInpNo) {
		ReObject ro = new ReObject("根据病人标识号或住院号查询病人基本信息");
		PatsVisit pv = hisServiceFacade.getHisPatService().findInPatInfo(fstrInpNo);
		List data = new ArrayList();
		data.add(pv);
		ro.setData(data);
		return ro;
	}

	public MaterialRdsDetailDeptDAO getMaterialRdsDetailDeptDAO() {
		return materialRdsDetailDeptDAO;
	}

	public void setMaterialRdsDetailDeptDAO(
			MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO) {
		this.materialRdsDetailDeptDAO = materialRdsDetailDeptDAO;
	}

	public SysUnitInforDAO getSysUnitInforDAO() {
		return sysUnitInforDAO;
	}

	public void setSysUnitInforDAO(SysUnitInforDAO sysUnitInforDAO) {
		this.sysUnitInforDAO = sysUnitInforDAO;
	}
}
