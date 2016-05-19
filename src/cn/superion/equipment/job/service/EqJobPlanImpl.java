package cn.superion.equipment.job.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqFaultDetailDAO;
import cn.superion.equipment.dao.EqJobContentDAO;
import cn.superion.equipment.dao.EqJobContentItemDAO;
import cn.superion.equipment.dao.EqJobContentItemPartDAO;
import cn.superion.equipment.dao.EqJobPlanDAO;
import cn.superion.equipment.dao.EqJobPlanItemDAO;
import cn.superion.equipment.dao.EqJobPlanItemPartDAO;
import cn.superion.equipment.entity.EqFaultDetail;
import cn.superion.equipment.entity.EqJobContent;
import cn.superion.equipment.entity.EqJobContentItem;
import cn.superion.equipment.entity.EqJobContentItemPart;
import cn.superion.equipment.entity.EqJobPlan;
import cn.superion.equipment.entity.EqJobPlanFault;
import cn.superion.equipment.entity.EqJobPlanItem;
import cn.superion.equipment.entity.EqJobPlanItemPart;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 作业计划服务实现
 * @author 曹国魁
 *
 */
public class EqJobPlanImpl implements IEqJobPlan {
	private static final String RCPT_FLAG = "3";
	private EqJobPlanDAO eqJobPlanDAO;
	private EqJobPlanItemDAO eqJobPlanItemDAO;
	private EqJobPlanItemPartDAO eqJobPlanItemPartDAO;
	private EqFaultDetailDAO eqFaultDetailDAO;
	private IEqBillNoService eqBillNoService;
	private EqJobContentDAO eqJobContentDAO;
	private EqJobContentItemDAO eqJobContentItemDAO;
	private EqJobContentItemPartDAO eqJobContentItemPartDAO;
	
	
	public EqJobContentDAO getEqJobContentDAO() {
		return eqJobContentDAO;
	}

	public void setEqJobContentDAO(EqJobContentDAO eqJobContentDAO) {
		this.eqJobContentDAO = eqJobContentDAO;
	}

	public EqJobContentItemDAO getEqJobContentItemDAO() {
		return eqJobContentItemDAO;
	}

	public void setEqJobContentItemDAO(EqJobContentItemDAO eqJobContentItemDAO) {
		this.eqJobContentItemDAO = eqJobContentItemDAO;
	}

	public EqJobContentItemPartDAO getEqJobContentItemPartDAO() {
		return eqJobContentItemPartDAO;
	}

	public void setEqJobContentItemPartDAO(
			EqJobContentItemPartDAO eqJobContentItemPartDAO) {
		this.eqJobContentItemPartDAO = eqJobContentItemPartDAO;
	}

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	public EqFaultDetailDAO getEqFaultDetailDAO() {
		return eqFaultDetailDAO;
	}

	public void setEqFaultDetailDAO(EqFaultDetailDAO eqFaultDetailDAO) {
		this.eqFaultDetailDAO = eqFaultDetailDAO;
	}

	public EqJobPlanDAO getEqJobPlanDAO() {
		return eqJobPlanDAO;
	}

	public void setEqJobPlanDAO(EqJobPlanDAO eqJobPlanDAO) {
		this.eqJobPlanDAO = eqJobPlanDAO;
	}

	public EqJobPlanItemDAO getEqJobPlanItemDAO() {
		return eqJobPlanItemDAO;
	}

	public void setEqJobPlanItemDAO(EqJobPlanItemDAO eqJobPlanItemDAO) {
		this.eqJobPlanItemDAO = eqJobPlanItemDAO;
	}

	public EqJobPlanItemPartDAO getEqJobPlanItemPartDAO() {
		return eqJobPlanItemPartDAO;
	}

	public void setEqJobPlanItemPartDAO(EqJobPlanItemPartDAO eqJobPlanItemPartDAO) {
		this.eqJobPlanItemPartDAO = eqJobPlanItemPartDAO;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的作业计划");
		EqJobPlan plan = eqJobPlanDAO.findById(fstrAutoId);
		if(plan == null){
			throw new RuntimeException("不存在此作业计划[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(plan.getCurrentStatus())){
			throw new RuntimeException("作业计划已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		//清除作业计划解决故障
		eqFaultDetailDAO.cleanByJobPlanAutoId(fstrAutoId);
		//删除作业计划项目备件
		eqJobPlanItemPartDAO.delByAutoId(fstrAutoId);
		//删除作业计划项目
		eqJobPlanItemDAO.delByAutoId(fstrAutoId);
		//删除作业计划
		eqJobPlanDAO.delete(plan);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询作业计划主记录ID列表");
		List<Object> data = eqJobPlanDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据作业计划主记录ID查询作业计划,以及相关信息");
		EqJobPlan plan = eqJobPlanDAO.findById(fstrAutoId);
		List<EqJobPlanItem> itemList = eqJobPlanItemDAO.findByAutoId(fstrAutoId);
		List<EqJobPlanItemPart> partList = eqJobPlanItemPartDAO.findByAutoId(fstrAutoId);
		List<EqFaultDetail> faultList = eqFaultDetailDAO.findByJobPlanAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(plan);
		data.add(itemList);
		data.add(partList);
		data.add(faultList);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询作业计划列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = eqJobPlanDAO.findListByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject save(EqJobPlan plan, List<EqJobPlanItem> itemList,
			List<EqJobPlanItemPart> partList, List<EqJobPlanFault> faultList) {
		ReObject ro = new ReObject("保存作业计划");
		String autoId = plan.getAutoId();
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		if(autoId == null || "".equals(autoId)){
			//新增
			String jobPlanNo = plan.getJobPlanNo();
			if(jobPlanNo == null || "".equals(jobPlanNo)){
				plan.setJobPlanNo(eqBillNoService.getNextBillNo(RCPT_FLAG));
			}else{
				//校验作业计划单号唯一
				if(!eqJobPlanDAO.checkJobPlanNoUnique(unitsCode,jobPlanNo)){
					throw new RuntimeException("手工输入的作业计划单号[" + jobPlanNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			plan.setUnitsCode(unitsCode);
			plan.setCurrentStatus("0");
			plan.setMaker(user.getPersonId());
			plan.setMakeDate(new Date());
			plan.setSourceData("1");
			eqJobPlanDAO.save(plan);
			autoId = plan.getAutoId();
		}else{
			//修改
			//校验未审核
			EqJobPlan original = eqJobPlanDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此作业计划[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("作业计划已审核[系统标识号："+autoId+"]，不能修改！");
			}
			eqJobPlanDAO.merge(plan);
			//先删除明细记录
			//清除作业计划解决故障
			eqFaultDetailDAO.cleanByJobPlanAutoId(autoId);
			//删除作业计划项目备件
			eqJobPlanItemPartDAO.delByAutoId(autoId);
			//删除作业计划项目
			eqJobPlanItemDAO.delByAutoId(autoId);
		}
		for(EqJobPlanItem item : itemList){
			item.setAutoId(autoId);
			eqJobPlanItemDAO.save(item);
		}
		for(EqJobPlanItemPart part : partList){
			part.setAutoId(autoId);
			eqJobPlanItemPartDAO.save(part);
		}
		//如果有故障记录的话，更新故障明细记录
		if(faultList != null && !faultList.isEmpty()){
			for(EqJobPlanFault fault : faultList){
				EqFaultDetail detail = eqFaultDetailDAO.findById(fault.getFaultNo(),fault.getFaultSerialNo());
				if(detail != null){
					detail.setCreateJobPlanSign("1");
					detail.setJobPlanAutoId(autoId);
					detail.setJobCode(plan.getJobCode());
					detail.setJobName(plan.getJobName());
				}
			}
		}
		List<Object> data = new ArrayList<Object>();
		data.add(plan);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核未审核的作业计划");
		EqJobPlan plan = eqJobPlanDAO.findById(fstrAutoId);
		if(plan == null){
			throw new RuntimeException("不存在此作业计划[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(plan.getCurrentStatus())){
			throw new RuntimeException("作业计划已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		plan.setCurrentStatus("1");
		plan.setVerifier(SessionUtil.getPersonId());
		plan.setVerifyDate(new Date());
		List<Object> data = new ArrayList<Object>();
		data.add(plan);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findFaultByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件过滤故障记录");
		fparameter.getConditions().put("createJobPlanSign", "0");
		fparameter.getConditions().put("jobStatus", "0");
		List<EqFaultDetail> data = eqFaultDetailDAO.findVerifiedByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public EqJobPlan buildJobPlan(EqFaultDetail faultDetail) {
		String jobCode = faultDetail.getJobCode();
		if(jobCode == null || "".equals(jobCode)){
			throw new RuntimeException(String.format("故障记录[系统标识号：%s,序号：%s]没有设置作业编码，不能生成作业计划！",faultDetail.getAutoId(),faultDetail.getSerialNo()));
		}
		String unitsCode = SessionUtil.getUnitsCode();
		EqJobContent content = eqJobContentDAO.findByJobCode(unitsCode,jobCode);
		if(content == null){
			throw new RuntimeException("故障记录的作业内容[作业编码："+jobCode+"]不存在，不能生成作业计划！");
		}
		EqJobPlan plan = content.buildJobPlan();
		plan.setJobPlanNo(eqBillNoService.getNextBillNo(RCPT_FLAG));
		plan.setCurrentStatus("0");
		plan.setMaker(SessionUtil.getPersonId());
		plan.setMakeDate(new Date());
		plan.setSourceData("2");
		//作业计划
		eqJobPlanDAO.save(plan);
		String planAutoId = plan.getAutoId();
		//作业项目
		String contentAutoId = content.getAutoId();
		List<EqJobContentItem> contentItemList = eqJobContentItemDAO.findByAutoId(contentAutoId);
		for(EqJobContentItem item : contentItemList){
			EqJobPlanItem planItem = item.buildJobPlanItem();
			planItem.setAutoId(planAutoId);
			eqJobPlanItemDAO.save(planItem);
		}
		//作业备件
		List<EqJobContentItemPart> contentItemPartList = eqJobContentItemPartDAO.findByAutoId(contentAutoId);
		for(EqJobContentItemPart part : contentItemPartList){
			EqJobPlanItemPart planItemPart = part.buildJobPlanItemPart();
			planItemPart.setAutoId(planAutoId);
			eqJobPlanItemPartDAO.save(planItemPart);
		}
		return plan;
	}

	@Override
	public ReObject findJobContentByJobCode(String fstrJobCode) {
		ReObject ro = new ReObject("根据作业编码查询作业内容以及相关信息");
		EqJobContent content = eqJobContentDAO.findByJobCode(SessionUtil.getUnitsCode(),fstrJobCode);
		//作业项目
		List<EqJobContentItem> contentItemList = null;
		//作业备件
		List<EqJobContentItemPart> contentItemPartList = null;
		if(content != null){
			String contentAutoId = content.getAutoId();
			contentItemList = eqJobContentItemDAO.findByAutoId(contentAutoId);
			contentItemPartList = eqJobContentItemPartDAO.findByAutoId(contentAutoId);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(content);
		data.add(contentItemList);
		data.add(contentItemPartList);
		ro.setData(data);
		return ro;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ReObject findJobPlanNos(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("查询已审核过的作业计划单号");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = eqJobPlanDAO.findJobPlanNos(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
