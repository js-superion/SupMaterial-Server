package cn.superion.materialDept.deliver.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.dao.MaterialPatsDetailDAO;
import cn.superion.materialDept.dao.MaterialPatsMasterDAO;
import cn.superion.materialDept.dao.VMaterialPatsDAO;
import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.MaterialPatsMaster;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.materialDept.entity.PatsBillMaster;
import cn.superion.materialDept.entity.PatsVisit;
import cn.superion.materialDept.entity.VMaterialPats;
import cn.superion.materialDept.his.service.IHisServiceFacade;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 病人费用记帐服务实现
 * @author 曹国魁
 *
 */
public class PatsFeeImpl implements IPatsFee {
	private Log log = LogFactory.getLog(PatsFeeImpl.class);
	//2：费用记账
	private static final String BILL_TYPE = "2";
	private MaterialPatsMasterDAO materialPatsMasterDAO;
	private MaterialPatsDetailDAO materialPatsDetailDAO;
	private VMaterialPatsDAO vMaterialPatsDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;
	private cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl;
	private IHisServiceFacade hisServiceFacade;
	
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
		if(!"0".equals(master.getCurrentStatus())){
			throw new RuntimeException("记账单据已审核，不能删除！");
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
		String storageCode = user.getDeptCode();
		List<VMaterialPats> patsFeelist = vMaterialPatsDAO.findByPatientId(BILL_TYPE,unitsCode,storageCode,fstrPatientId);
		List data = new ArrayList();
		data.add(pv);
	    data.add(patsFeelist);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findPatsFeeDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前记账单的详细信息记录");
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
	public ReObject findPatsFeeMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的记账单列表");
		SysUser user = SessionUtil.getSysUser();
		//虚拟仓库就是科室自己
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<Object> data = materialPatsMasterDAO.findAutoIdsByCondition(user.getUnitsCode(),BILL_TYPE,fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject refundFee(String fstrPatientId,String[] fstrAutoIds) {
		ReObject ro = new ReObject("对已记帐的病人使用材料费用退费");
		List<MaterialPatsDetail> patsDetails = materialPatsDetailDAO.findByAutoIds(fstrAutoIds);
		if(patsDetails.isEmpty()){
			throw new RuntimeException("费用明细不存在，不能退费！");
		}
		Date curDate = new Date();
		SysUser user = SessionUtil.getSysUser();
		String personId = user.getPersonId();
		String unitsCode = user.getUnitsCode();
		String storageCode = user.getDeptCode();
		
		//红字出库明细记录
		List<MaterialRdsDetailDept> redDDetails = new ArrayList<MaterialRdsDetailDept>();
		for(MaterialPatsDetail detail : patsDetails){
			if("1".equals(detail.getRefundSign())){
				throw new RuntimeException("费用[系统标识号："+detail.getAutoId()+"]已退费，不能再次退费！");
			}
			detail.setRefundSign("1");
			detail.setRefundOperator(personId);
			detail.setRefundDate(curDate);
			redDDetails.add(buildMaterialRdsDetailDept(detail,false));
		}
		//红字销售出库主记录
		MaterialRdsMasterDept redDMaster = buildRedMaterialRdsMasterDept(unitsCode,storageCode,RdConstant.D,RdConstant.D_SALE,personId);
		deptCommMaterialServiceImpl.save(redDMaster,redDDetails);
		List<MaterialPatsDetail> redPatsDetails = new ArrayList<MaterialPatsDetail>();
		//要写HIS红字费用的病人使用材料蓝字明细记录
		List<MaterialPatsDetail> blueHisPatsDetails = new ArrayList<MaterialPatsDetail>();
		for(MaterialPatsDetail bluePatsDetail : patsDetails){
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
		//写病人使用材料红字明细记录
		Map<String,Integer> tmpSerialNoMap = new HashMap<String,Integer>();
		for(MaterialPatsDetail redPatsDetail : redPatsDetails){
			String mainAutoId = redPatsDetail.getMainAutoId();
			if(!tmpSerialNoMap.containsKey(mainAutoId)){
				tmpSerialNoMap.put(mainAutoId,Integer.valueOf(materialPatsDetailDAO.findMaxSerialNo(mainAutoId)));
			}
			tmpSerialNoMap.put(mainAutoId,(tmpSerialNoMap.get(mainAutoId)+1));
			redPatsDetail.setSerialNo(Short.valueOf(tmpSerialNoMap.get(mainAutoId).toString()));
			materialPatsDetailDAO.save(redPatsDetail);
		}
		Double prepaymentsLeft = hisServiceFacade.getHisPatService().findPrepaymentLeft(fstrPatientId);
		List<Object> data = new ArrayList<Object>();
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
		String storageCode = user.getDeptCode();
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
			if(!"0".equals(original.getCurrentStatus())){
				throw new RuntimeException("记账单据已审核，不能修改！");
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
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的账单信息");
		MaterialPatsMaster master = materialPatsMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("记账单据不存在！");
		if(!"0".equals(master.getCurrentStatus())){
			throw new RuntimeException("记账单据已审核，不能审核！");
		}
		String patientId = master.getPatientId();
		String unitsCode = master.getUnitsCode();
		String personId = SessionUtil.getPersonId();
		Date curDate = new Date();
		List<MaterialPatsDetail> details = materialPatsDetailDAO.findByMainAutoId(fstrAutoId);
		//是否住院病人
		boolean isInp = "2".equals(master.getPatientType());
		//出库明细记录
		List<MaterialRdsDetailDept> dDetails = new ArrayList<MaterialRdsDetailDept>();
		//写HIS费用依赖的病人使用材料明细
		List<MaterialPatsDetail> hisPatsDetails = new ArrayList<MaterialPatsDetail>();
		for(MaterialPatsDetail detail : details){
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
			}
			//构造销售出库单明细记录
			dDetails.add(buildMaterialRdsDetailDept(detail,true));
		}
		//生成一张已审核的销售出库（202）单
		MaterialRdsMasterDept dMaster = buildMaterialRdsMasterDept(RdConstant.D,RdConstant.D_SALE,personId,master);
		deptCommMaterialServiceImpl.save(dMaster,dDetails);
		//更新记帐单据
		master.setCurrentStatus("1");
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
		//TODO rdType
		//master.setRdType("");
		master.setOperationType(operationType);
		//申请科室，也是执行科室，也是病人所在科室
		master.setDeptCode(pmaster.getDeptCode());
		//master.setSupplyDeptCode()
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
		detail.setRetailPrice(pdetail.getRetailPrice());
		detail.setRetailMoney(isBlue ? pdetail.getRetailMoney() : -pdetail.getRetailMoney());
		detail.setFactoryCode(pdetail.getFactoryCode());
		detail.setMadeDate(pdetail.getMadeDate());
		detail.setBatch(pdetail.getBatch());
		detail.setAvailDate(pdetail.getAvailDate());
		detail.setAgentSign("0");
		return detail;
	}

}
