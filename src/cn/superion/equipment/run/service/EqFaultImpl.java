package cn.superion.equipment.run.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqFaultDetailDAO;
import cn.superion.equipment.dao.EqFaultMasterDAO;
import cn.superion.equipment.dao.VEqFaultDAO;
import cn.superion.equipment.entity.EqFaultDetail;
import cn.superion.equipment.entity.EqFaultMaster;
import cn.superion.equipment.entity.EqJobBill;
import cn.superion.equipment.entity.EqJobPlan;
import cn.superion.equipment.job.service.IEqJobBill;
import cn.superion.equipment.job.service.IEqJobPlan;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 故障记录服务实现
 * @author 曹国魁
 *
 */
public class EqFaultImpl implements IEqFault {
	private static final String OPERATION_INFO = "由行号为%s的故障记录生成";
	private static final String SAVE_AS_PLAN_OPERATION_RESULT = "生成作业计划成功";
	private static final String SAVE_AS_BILL_OPERATION_RESULT = "生成作业单成功";
	private EqFaultMasterDAO eqFaultMasterDAO;
	private EqFaultDetailDAO eqFaultDetailDAO;
	private VEqFaultDAO vEqFaultDAO;
	private IEqBillNoService eqBillNoService;
	private IEqJobPlan eqJobPlanImpl;
	private IEqJobBill eqJobBillImpl;
	public IEqJobPlan getEqJobPlanImpl() {
		return eqJobPlanImpl;
	}

	public void setEqJobPlanImpl(IEqJobPlan eqJobPlanImpl) {
		this.eqJobPlanImpl = eqJobPlanImpl;
	}

	public IEqJobBill getEqJobBillImpl() {
		return eqJobBillImpl;
	}

	public void setEqJobBillImpl(IEqJobBill eqJobBillImpl) {
		this.eqJobBillImpl = eqJobBillImpl;
	}

	public EqFaultMasterDAO getEqFaultMasterDAO() {
		return eqFaultMasterDAO;
	}

	public void setEqFaultMasterDAO(EqFaultMasterDAO eqFaultMasterDAO) {
		this.eqFaultMasterDAO = eqFaultMasterDAO;
	}

	public EqFaultDetailDAO getEqFaultDetailDAO() {
		return eqFaultDetailDAO;
	}

	public void setEqFaultDetailDAO(EqFaultDetailDAO eqFaultDetailDAO) {
		this.eqFaultDetailDAO = eqFaultDetailDAO;
	}

	public VEqFaultDAO getvEqFaultDAO() {
		return vEqFaultDAO;
	}

	public void setvEqFaultDAO(VEqFaultDAO vEqFaultDAO) {
		this.vEqFaultDAO = vEqFaultDAO;
	}

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的故障记录");
		EqFaultMaster master = eqFaultMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此故障记录[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("故障记录已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		eqFaultDetailDAO.delByAutoId(fstrAutoId);
		eqFaultMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询故障主记录ID列表");
		List<Object> data = eqFaultMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据故障主记录ID，查询故障记录");
		EqFaultMaster master = eqFaultMasterDAO.findById(fstrAutoId);
		List<EqFaultDetail> details = eqFaultDetailDAO.findByAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询故障记录明细列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = vEqFaultDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject save(EqFaultMaster master, List<EqFaultDetail> details) {
		ReObject ro = new ReObject("保存故障记录");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String autoId = master.getAutoId();
		if(autoId == null || "".equals(autoId) ){
			//新增
			String billNo = master.getBillNo();
			if(billNo == null || "".equals(billNo)){
				master.setBillNo(eqBillNoService.getNextBillNo("6"));
			}else{
				//校验故障单号唯一
				if(!eqFaultMasterDAO.checkBillNoUnique(unitsCode,billNo)){
					throw new RuntimeException("手工输入的故障单号[" + billNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			master.setUnitsCode(unitsCode);
			master.setCurrentStatus("0");
			master.setMaker(user.getPersonId());
			master.setMakeDate(new Date());
			eqFaultMasterDAO.save(master);
			autoId = master.getAutoId();

		}else{
			//修改
			EqFaultMaster original = eqFaultMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此故障记录[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("故障记录已审核[系统标识号："+autoId+"]，不能修改！");
			}
			eqFaultMasterDAO.merge(master);
			//先删除明细记录
			eqFaultDetailDAO.delByAutoId(autoId);
		}
		short i = 0;
		for(EqFaultDetail detail : details){
			detail.setAutoId(autoId);
			detail.setSerialNo(++i);
			detail.setCreateJobPlanSign("0");
			detail.setCreateJobBillSign("0");
			eqFaultDetailDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveAsJobPlan(String fstrAutoId) {
		ReObject ro = new ReObject("生成作业计划或作业单");
		EqFaultMaster master = eqFaultMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此故障记录[系统标识号："+fstrAutoId+"],不能生成作业计划或作业单！");
		}
		if("0".equals(master.getCurrentStatus())){
			throw new RuntimeException("故障记录未审核[系统标识号："+fstrAutoId+"]，不能生成作业计划或作业单！");
		}
		List<EqFaultDetail> data = new ArrayList<EqFaultDetail>();
		//查询要生成作业计划的故障明细列表
		List<EqFaultDetail> toJobPlanFaultList = eqFaultDetailDAO.findToJobPlanFaultList(fstrAutoId);
		if(toJobPlanFaultList != null && !toJobPlanFaultList.isEmpty()){
			for(EqFaultDetail detail : toJobPlanFaultList){
				EqJobPlan plan = eqJobPlanImpl.buildJobPlan(detail);
				detail.setJobPlanAutoId(plan.getAutoId());
				detail.setOperateResult(SAVE_AS_PLAN_OPERATION_RESULT);
				detail.setOperateInfo(String.format(OPERATION_INFO, detail.getSerialNo()));
				detail.setRemark(plan.getJobPlanNo());
			}
			data.addAll(toJobPlanFaultList);
		}
		//查询要生成作业单的故障明细列表
		List<EqFaultDetail> toJobBillFaultList = eqFaultDetailDAO.findToJobBillFaultList(fstrAutoId);
		if(toJobBillFaultList != null && !toJobBillFaultList.isEmpty()){
			for(EqFaultDetail detail : toJobBillFaultList){
				EqJobBill bill = eqJobBillImpl.buildJobBill(detail);
				detail.setJobBillAutoId(bill.getAutoId());
				detail.setOperateResult(SAVE_AS_BILL_OPERATION_RESULT);
				detail.setOperateInfo(String.format(OPERATION_INFO, detail.getSerialNo()));
				detail.setRemark(bill.getJobBillNo());
			}
			data.addAll(toJobBillFaultList);
		}
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject saveAsJobPlan(String fstrAutoId,
			List<EqFaultDetail> toJobPlanFaultList, List<EqFaultDetail> toJobBillFaultList) {
		ReObject ro = new ReObject("生成作业计划或作业单");
		EqFaultMaster master = eqFaultMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此故障记录[系统标识号："+fstrAutoId+"],不能生成作业计划或作业单！");
		}
		if("0".equals(master.getCurrentStatus())){
			throw new RuntimeException("故障记录未审核[系统标识号："+fstrAutoId+"]，不能生成作业计划或作业单！");
		}
		List<EqFaultDetail> data = new ArrayList<EqFaultDetail>();
		if(toJobPlanFaultList != null && !toJobPlanFaultList.isEmpty()){
			for(EqFaultDetail detail : toJobPlanFaultList){
				EqFaultDetail originalDetail = eqFaultDetailDAO.findById(fstrAutoId, detail.getSerialNo());
				if(originalDetail != null){
					if("1".equals(originalDetail.getCreateJobPlanSign())){
						throw new RuntimeException("故障明细记录[系统标识号："+fstrAutoId+",序号："+detail.getSerialNo()+"]已生成作业计划，不能重复生成！");	
					}
					if("1".equals(originalDetail.getCreateJobBillSign())){
						throw new RuntimeException("故障明细记录[系统标识号："+fstrAutoId+",序号："+detail.getSerialNo()+"]已生成作业单，不能重复生成！");	
					}
					EqJobPlan plan = eqJobPlanImpl.buildJobPlan(detail);
					detail.setJobPlanAutoId(plan.getAutoId());
					detail.setOperateResult(SAVE_AS_PLAN_OPERATION_RESULT);
					detail.setOperateInfo(String.format(OPERATION_INFO, detail.getSerialNo()));
					detail.setRemark(plan.getJobPlanNo());
					eqFaultDetailDAO.merge(detail);
				}
			}
			data.addAll(toJobPlanFaultList);
		}
		if(toJobBillFaultList != null && !toJobBillFaultList.isEmpty()){
			for(EqFaultDetail detail : toJobBillFaultList){
				EqFaultDetail originalDetail = eqFaultDetailDAO.findById(fstrAutoId, detail.getSerialNo());
				if(originalDetail != null){
					if("1".equals(originalDetail.getCreateJobPlanSign())){
						throw new RuntimeException("故障明细记录[系统标识号："+fstrAutoId+",序号："+detail.getSerialNo()+"]已生成作业计划，不能重复生成！");	
					}
					if("1".equals(originalDetail.getCreateJobBillSign())){
						throw new RuntimeException("故障明细记录[系统标识号："+fstrAutoId+",序号："+detail.getSerialNo()+"]已生成作业单，不能重复生成！");	
					}
					EqJobBill bill = eqJobBillImpl.buildJobBill(detail);
					detail.setJobBillAutoId(bill.getAutoId());
					detail.setOperateResult(SAVE_AS_BILL_OPERATION_RESULT);
					detail.setOperateInfo(String.format(OPERATION_INFO, detail.getSerialNo()));
					detail.setRemark(bill.getJobBillNo());
					eqFaultDetailDAO.merge(detail);
				}
			}
			data.addAll(toJobBillFaultList);
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核未审核的故障记录");
		EqFaultMaster master = eqFaultMasterDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此故障记录[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("故障记录已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		master.setCurrentStatus("1");
		master.setVerifier(SessionUtil.getPersonId());
		master.setVerifyDate(new Date());
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

}
