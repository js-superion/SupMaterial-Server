package cn.superion.material.purchase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialPlanDetailDAO;
import cn.superion.material.dao.MaterialPlanMasterDAO;
import cn.superion.material.dao.MaterialRdsStockDAO;
import cn.superion.material.entity.MaterialPlanDetail;
import cn.superion.material.entity.MaterialPlanMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 采购计划服务实现
 * @author 曹国魁
 *
 */
public class PlanImpl implements IPlan {
	private CdMaterialDictDAO cdMaterialDictDAO; 
	private MaterialCurrentStockDAO materialCurrentStockDAO; 
	private MaterialRdsStockDAO materialRdsStockDAO;
	private MaterialPlanMasterDAO materialPlanMasterDAO;
	private MaterialPlanDetailDAO materialPlanDetailDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService commMaterialServiceImpl;
	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}

	public MaterialRdsStockDAO getMaterialRdsStockDAO() {
		return materialRdsStockDAO;
	}

	public void setMaterialRdsStockDAO(MaterialRdsStockDAO materialRdsStockDAO) {
		this.materialRdsStockDAO = materialRdsStockDAO;
	}

	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}

	public ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public MaterialPlanDetailDAO getMaterialPlanDetailDAO() {
		return materialPlanDetailDAO;
	}

	public void setMaterialPlanDetailDAO(MaterialPlanDetailDAO materialPlanDetailDAO) {
		this.materialPlanDetailDAO = materialPlanDetailDAO;
	}

	public MaterialPlanMasterDAO getMaterialPlanMasterDAO() {
		return materialPlanMasterDAO;
	}

	public void setMaterialPlanMasterDAO(MaterialPlanMasterDAO materialPlanMasterDAO) {
		this.materialPlanMasterDAO = materialPlanMasterDAO;
	}

	@Override
	public ReObject autoBuildMaterialPlan(ParameterObject fparameter) {
		ReObject ro = new ReObject("自动编制采购计划");
		String unitsCode = SessionUtil.getUnitsCode();
		//按出库量计算计划采购量时的比例系数
		Object orate = fparameter.getConditions().get("rate"); 
		Double rate = orate==null?null:Double.valueOf(orate.toString());
		//物资档案字典条件：
		String materialClass = (String)fparameter.getConditions().get("materialClass");
		String materialCode = (String)fparameter.getConditions().get("materialCode");
		StringBuilder cond = new StringBuilder(" where unitsCode='").append(unitsCode).append("'");
		if(materialClass != null && !"".equals(materialClass)){
			cond.append(" and materialClass='").append(materialClass).append("'");
		}
		if(materialCode != null && !"".equals(materialCode)){
			cond.append(" and materialCode='").append(materialCode).append("'");
		}
		List<CdMaterialDict> dictList = cdMaterialDictDAO.findByCondition(cond.toString());
		List<MaterialPlanDetail> planDetails = new ArrayList<MaterialPlanDetail>();
		for(CdMaterialDict dict : dictList){
			//安全库存量
			Double safeStockAmount = dict.getSafeStockAmount();
			//计划采购数量
			Double planAmount = 0d;
			if("1".equals(dict.getHighValueSign())){
				//高值耗材
				//当前库存结存
				Double stockAmount = materialCurrentStockDAO.findAmount(unitsCode, dict.getMaterialId());
				planAmount = safeStockAmount - stockAmount;
			}else{
				//非高值耗材
				if(rate == null || rate <= 0){
					throw new RuntimeException("参数比例系数不能为空！");
				}
				//出库量
				Double deliverAmount = materialRdsStockDAO.findAllDeliverAmount(unitsCode,dict.getMaterialId(),fparameter.getConditions()); 
				planAmount = rate*deliverAmount;
			}
			if(planAmount > 0){
				MaterialPlanDetail planDetail = buildMaterialPlanDetail(dict,planAmount);
				planDetails.add(planDetail);
			}
		}
		ro.setData(planDetails);
		return ro;
	}

	/**
	 * 根据物资档案构造采购计划明细
	 * @param dict
	 * @param planAmount
	 * @return
	 */
	private MaterialPlanDetail buildMaterialPlanDetail(CdMaterialDict dict,
			Double planAmount) {
		MaterialPlanDetail planDetail = new MaterialPlanDetail();
		planDetail.setMaterialClass(dict.getMaterialClass());
		planDetail.setMaterialId(dict.getMaterialId());
		planDetail.setMaterialCode(dict.getMaterialCode());
		planDetail.setMaterialName(dict.getMaterialName());
		planDetail.setMaterialSpec(dict.getMaterialSpec());
		planDetail.setMaterialUnits(dict.getMaterialUnits());
		planDetail.setAmount(planAmount);
		planDetail.setTradePrice(dict.getTradePrice());
		planDetail.setTradeMoney(dict.getTradePrice()==null?0d:dict.getTradePrice()*planAmount);
		planDetail.setRetailPrice(dict.getRetailPrice());
		planDetail.setRetailMoney(dict.getRetailPrice()==null?0d:dict.getRetailPrice()*planAmount);
		return planDetail;
	}

	@Override
	public ReObject deleteMaterialPlan(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的采购计划单据");
		MaterialPlanMaster master = materialPlanMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("采购计划单不存在！");
		if(materialPlanDetailDAO.checkUsedStatus(fstrAutoId)){
			throw new RuntimeException("采购计划明细记录已审核或执行或关闭，采购计划单不能删除！");
		}
		materialPlanDetailDAO.delByMainAutoId(fstrAutoId);
		materialPlanMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findMaterialPlanDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前采购计划编制单的详细信息记录");
		MaterialPlanMaster master = materialPlanMasterDAO.findById(fstrAutoId);
		List<MaterialPlanDetail> details = materialPlanDetailDAO.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findMaterialPlanMasterListByCondition(
			ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的计划单据列表");
		String dataSource = (String)fparameter.getConditions().get("dataSource");
		if(dataSource == null || "".equals(dataSource)){
			throw new RuntimeException("条件数据来源不能为空！");
		}
		List<Object> data = materialPlanMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findMaterialPlanDetailListByCondition(
			ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的计划单据列表");
		String dataSource = (String)fparameter.getConditions().get("dataSource");
		if(dataSource == null || "".equals(dataSource)){
			throw new RuntimeException("条件数据来源不能为空！");
		}
		List<Object> data = materialPlanDetailDAO.findDetailsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveMaterialPlan(MaterialPlanMaster fmaster,
			List<MaterialPlanDetail> fdetails) {
		ReObject ro = new ReObject("保存当前采购计划信息");
		if(fmaster == null)
			throw new RuntimeException("采购计划主记录不能为空！");
		if(fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("采购计划明细记录不能为空！");
		String dataSource = fmaster.getDataSource(); 
		if(dataSource == null || "".equals(dataSource))
			throw new RuntimeException("采购计划主记录属性数据来源不能为空！");
		if(!dataSource.equals("1") && !dataSource.equals("2"))
			throw new RuntimeException("采购计划主记录属性数据来源值错误,应为1或2");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String appCode = user.getAppCode();
		Date curDate = new Date();
		String storageCode = fmaster.getStorageCode();
		if(storageCode == null || "".equals(storageCode)){
			storageCode = "0";
			fmaster.setStorageCode(storageCode);
		}
		String autoId = fmaster.getAutoId(); 
		boolean isVerified = false;
		//累计金额
		Double totalCosts = 0d;
		for(MaterialPlanDetail detail : fdetails){
			totalCosts += detail.getTradeMoney();
		}
		fmaster.setTotalCosts(totalCosts);
		if(autoId == null || "".equals(autoId)){
			//新增
			String billNo = fmaster.getBillNo();
			if(billNo == null || "".equals(billNo)){
				fmaster.setBillNo(commMaterialServiceImpl.getNextBillNo(RdConstant.OTHERS,storageCode));
			}
			//新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
			if(!materialPlanMasterDAO.checkBillNoUnique(unitsCode,storageCode,billNo)){
				throw new RuntimeException("手工输入的单据编号["+billNo+"]在单位["+unitsCode+"],仓库["+storageCode+"]下有重复");
			}
			if(fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			if(fmaster.getPersonId() == null || "".equals(fmaster.getPersonId()))
				fmaster.setPersonId(personId);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			isVerified = "1".equals(loadCheckSysParam(unitsCode,appCode,dataSource));
			if(isVerified){
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(personId);
			}
			materialPlanMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		}else{
			//修改
			MaterialPlanMaster original = materialPlanMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的采购计划主记录！");
			}
			if(materialPlanDetailDAO.checkUsedStatus(autoId)){
				throw new RuntimeException("采购计划明细记录已审核或执行或关闭，采购计划单不能修改！");
			}
			isVerified = "1".equals(loadCheckSysParam(unitsCode,appCode,dataSource));
			if(isVerified){
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(personId);
			}
			materialPlanMasterDAO.merge(fmaster);
			materialPlanDetailDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for(MaterialPlanDetail detail : fdetails){
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			detail.setCurrentStatus(isVerified?"1":"0");
			materialPlanDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}
	
	/**
	 * 加载系统参数
	 * @param unitsCode
	 * @param appCode
	 * @param dataSource
	 * @return
	 */
	private String loadCheckSysParam(String unitsCode,String appCode,String dataSource){
		if("1".equals(dataSource)){
			return cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_PURCHASE_PLAN_CHK,"0");
		}
		if("2".equals(dataSource)){
			return cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_PURCHASE_APPLY_CHK,"0");
		}
		return null;
	}

	@Override
	public ReObject verifyMaterialPlan(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的采购计划编制信息");
		MaterialPlanMaster original = materialPlanMasterDAO.findById(fstrAutoId);
		if(original == null){
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的采购计划主记录！");
		}
		if(materialPlanDetailDAO.checkUsedStatus(fstrAutoId)){
			throw new RuntimeException("采购计划明细记录已审核或执行或关闭，采购计划单不能审核！");
		}
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		materialPlanDetailDAO.verify(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

}
