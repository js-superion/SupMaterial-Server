package cn.superion.material.purchase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialOrderDetailDAO;
import cn.superion.material.dao.MaterialOrderMasterDAO;
import cn.superion.material.dao.MaterialPlanDetailDAO;
import cn.superion.material.dao.VMaterialPlanDAO;
import cn.superion.material.entity.MaterialOrderDetail;
import cn.superion.material.entity.MaterialOrderMaster;
import cn.superion.material.entity.MaterialPlanDetail;
import cn.superion.material.entity.VMaterialPlan;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 采购订单服务实现
 * @author 曹国魁
 *
 */
public class OrderImpl implements IOrder {
	private static final Log log = LogFactory.getLog(OrderImpl.class);
	private VMaterialPlanDAO vMaterialPlanDAO;
	private MaterialPlanDetailDAO materialPlanDetailDAO;
	private MaterialOrderMasterDAO materialOrderMasterDAO;
	private MaterialOrderDetailDAO materialOrderDetailDAO;
	private CdSysParamDAO cdSysParamDAO;

	private ICommMaterialService commMaterialServiceImpl;
	public MaterialPlanDetailDAO getMaterialPlanDetailDAO() {
		return materialPlanDetailDAO;
	}

	public void setMaterialPlanDetailDAO(MaterialPlanDetailDAO materialPlanDetailDAO) {
		this.materialPlanDetailDAO = materialPlanDetailDAO;
	}

	public VMaterialPlanDAO getvMaterialPlanDAO() {
		return vMaterialPlanDAO;
	}

	public void setvMaterialPlanDAO(VMaterialPlanDAO vMaterialPlanDAO) {
		this.vMaterialPlanDAO = vMaterialPlanDAO;
	}

	public MaterialOrderMasterDAO getMaterialOrderMasterDAO() {
		return materialOrderMasterDAO;
	}

	public void setMaterialOrderMasterDAO(
			MaterialOrderMasterDAO materialOrderMasterDAO) {
		this.materialOrderMasterDAO = materialOrderMasterDAO;
	}

	public MaterialOrderDetailDAO getMaterialOrderDetailDAO() {
		return materialOrderDetailDAO;
	}

	public void setMaterialOrderDetailDAO(
			MaterialOrderDetailDAO materialOrderDetailDAO) {
		this.materialOrderDetailDAO = materialOrderDetailDAO;
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

	@Override
	public ReObject delOrder(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的订单");
		MaterialOrderMaster master = materialOrderMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("订单不存在！");
		if(materialOrderDetailDAO.checkUsedStatus(fstrAutoId)){
			throw new RuntimeException("采购订单明细记录已审核或执行或关闭，采购订单不能删除！");
		}
		//还原采购计划的已生成订单数和状态
		List<MaterialOrderDetail> originalDetails = materialOrderDetailDAO.findByMainAutoId(fstrAutoId);
		for(MaterialOrderDetail originalDetail : originalDetails){
			updateOrderAmount(false,true,originalDetail);
		}
		materialOrderDetailDAO.delByMainAutoId(fstrAutoId);
		materialOrderMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findOrderByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询采购订单信息");
		List<Object> data = materialOrderMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findOrderDetail(String fstrAutoId) {
		ReObject ro = new ReObject("查询采购订单明细列表");
		MaterialOrderMaster master = materialOrderMasterDAO.findById(fstrAutoId);
		List<MaterialOrderDetail> details = materialOrderDetailDAO.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveOrder(MaterialOrderMaster fmaster,
			List<MaterialOrderDetail> fdetails) {
		ReObject ro = new ReObject("保存采购订单信息");
		if(fmaster == null)
			throw new RuntimeException("采购订单主记录不能为空！");
		if(fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("采购订单明细记录不能为空！");
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
		for(MaterialOrderDetail detail : fdetails){
			totalCosts += detail.getTradeMoney();
		}
		fmaster.setTotalCosts(totalCosts);
		if(autoId == null || "".equals(autoId)){
			//新增
			String billNo = fmaster.getBillNo();
			if(billNo == null || "".equals(billNo)){
				fmaster.setBillNo(commMaterialServiceImpl.getNextBillNo(RdConstant.OTHERS,storageCode));
			}else{
				//新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if(!materialOrderMasterDAO.checkBillNoUnique(unitsCode,storageCode,billNo)){
					throw new RuntimeException("手工输入的单据编号["+billNo+"]在单位["+unitsCode+"],仓库["+storageCode+"]下有重复");
				}
			}
			if(fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			if(fmaster.getPersonId() == null || "".equals(fmaster.getPersonId()))
				fmaster.setPersonId(personId);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			isVerified = "1".equals(loadCheckSysParam(unitsCode,appCode));
			if(isVerified){
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(personId);
			}
			materialOrderMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		}else{
			//修改
			MaterialOrderMaster original = materialOrderMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的采购订单主记录！");
			}
			if(materialOrderDetailDAO.checkUsedStatus(autoId)){
				throw new RuntimeException("采购订单明细记录已审核或执行或关闭，采购订单不能修改！");
			}
			isVerified = "1".equals(loadCheckSysParam(unitsCode,appCode));
			if(isVerified){
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(personId);
			}
			//先还原采购计划的已生成订单数
			List<MaterialOrderDetail> originalDetails = materialOrderDetailDAO.findByMainAutoId(autoId);
			for(MaterialOrderDetail originalDetail : originalDetails){
				updateOrderAmount(false,false,originalDetail);
			}
			materialOrderMasterDAO.merge(fmaster);
			materialOrderDetailDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for(MaterialOrderDetail detail : fdetails){
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			detail.setCurrentStatus(isVerified?"1":"0");
			materialOrderDetailDAO.save(detail);
			updateOrderAmount(true,true,detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}
	
	/**
	 * 更新采购计划已生成订单数和状态
	 * @param isPlus 是更新还是还原，保存时是更新；删除时是还原
	 * @param isUpdateCurrentStatus 是否更新状态
	 * @param detail 订单明细
	 */
	private void updateOrderAmount(boolean isPlus,boolean isUpdateCurrentStatus,MaterialOrderDetail detail){
		String planMasterAutoId = detail.getSourceBillNo();
		Short planDetailSerialNo = detail.getSourceSerialNo();
		if(planMasterAutoId != null && !"".equals(planMasterAutoId) && planDetailSerialNo != null && planDetailSerialNo > 0){
			MaterialPlanDetail planDetail = materialPlanDetailDAO.findByMainAutoIdAndSerialNo(planMasterAutoId,planDetailSerialNo);
			if(planDetail == null){
				log.warn("不存在采购计划主记录ID["+planMasterAutoId+"]和采购计划明细记录serialNo["+planDetailSerialNo+"]的采购计划明细记录，无法更新已生成订单数！");
			}else{
				Double orderAmount = planDetail.getOrderAmount();
				if(orderAmount == null)
					orderAmount = 0d;
				if(isPlus)
					orderAmount += detail.getAmount();
				else
					orderAmount -= detail.getAmount();
				planDetail.setOrderAmount(orderAmount);
				if(isUpdateCurrentStatus){
					if(isPlus){
						if(orderAmount.equals(planDetail.getAmount())){
							//当数量=已开票数时,已开票标志为1
							planDetail.setCurrentStatus("3");
						}else{
							planDetail.setCurrentStatus("2");
						}
					}else{
						if(orderAmount <= 0){
							planDetail.setCurrentStatus("1");
						}else{
							planDetail.setCurrentStatus("2");
						}
					}
				}
			}
		}
	}
	
	/**
	 * 加载系统参数
	 * @param unitsCode
	 * @param appCode
	 * @return
	 */
	private String loadCheckSysParam(String unitsCode,String appCode){
		return cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_PURCHASE_ORDER_CHK,"0");
	}

	@Override
	public ReObject verifyOrder(String fstrAutoId) {
		ReObject ro = new ReObject("审核订单");
		MaterialOrderMaster original = materialOrderMasterDAO.findById(fstrAutoId);
		if(original == null){
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的采购订单主记录！");
		}
		if(materialOrderDetailDAO.checkUsedStatus(fstrAutoId)){
			throw new RuntimeException("采购订单明细记录已审核或执行或关闭，采购订单不能审核！");
		}
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		materialOrderDetailDAO.verify(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findPlanDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤采购计划或采购请购");
		String dataSource = (String)fparameter.getConditions().get("dataSource");
		if(dataSource == null || "".equals(dataSource))
			throw new RuntimeException("数据来源不能为空！");
		List<VMaterialPlan> data = vMaterialPlanDAO.findByCheckCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
