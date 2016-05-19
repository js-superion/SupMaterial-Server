package cn.superion.equipment.job.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqFaultDetailDAO;
import cn.superion.equipment.dao.EqJobBillDAO;
import cn.superion.equipment.dao.EqJobBillItemDAO;
import cn.superion.equipment.dao.EqJobBillItemPartDAO;
import cn.superion.equipment.dao.EqJobContentDAO;
import cn.superion.equipment.dao.EqJobContentItemDAO;
import cn.superion.equipment.dao.EqJobContentItemPartDAO;
import cn.superion.equipment.dao.EqJobPlanDAO;
import cn.superion.equipment.dao.EqJobPlanItemDAO;
import cn.superion.equipment.dao.EqJobPlanItemPartDAO;
import cn.superion.equipment.entity.EqFaultDetail;
import cn.superion.equipment.entity.EqJobBill;
import cn.superion.equipment.entity.EqJobBillFault;
import cn.superion.equipment.entity.EqJobBillItem;
import cn.superion.equipment.entity.EqJobBillItemPart;
import cn.superion.equipment.entity.EqJobContent;
import cn.superion.equipment.entity.EqJobContentItem;
import cn.superion.equipment.entity.EqJobContentItemPart;
import cn.superion.equipment.entity.EqJobPlan;
import cn.superion.equipment.entity.EqJobPlanItem;
import cn.superion.equipment.entity.EqJobPlanItemPart;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 作业单服务实�?
 * @author 曹国�?
 *
 */
public class EqJobBillImpl implements IEqJobBill {
	private static final String RCPT_FLAG = "4";
	private EqJobBillDAO eqJobBillDAO;
	private EqJobBillItemDAO eqJobBillItemDAO;
	private EqJobBillItemPartDAO eqJobBillItemPartDAO;
	private EqFaultDetailDAO eqFaultDetailDAO;
	private IEqBillNoService eqBillNoService;
	private EqJobContentDAO eqJobContentDAO;
	private EqJobContentItemDAO eqJobContentItemDAO;
	private EqJobContentItemPartDAO eqJobContentItemPartDAO;
	private EqJobPlanDAO eqJobPlanDAO;
	private EqJobPlanItemDAO eqJobPlanItemDAO;
	private EqJobPlanItemPartDAO eqJobPlanItemPartDAO;
	
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


	public EqJobBillDAO getEqJobBillDAO() {
		return eqJobBillDAO;
	}

	public void setEqJobBillDAO(EqJobBillDAO eqJobBillDAO) {
		this.eqJobBillDAO = eqJobBillDAO;
	}

	public EqJobBillItemDAO getEqJobBillItemDAO() {
		return eqJobBillItemDAO;
	}

	public void setEqJobBillItemDAO(EqJobBillItemDAO eqJobBillItemDAO) {
		this.eqJobBillItemDAO = eqJobBillItemDAO;
	}

	public EqJobBillItemPartDAO getEqJobBillItemPartDAO() {
		return eqJobBillItemPartDAO;
	}

	public void setEqJobBillItemPartDAO(EqJobBillItemPartDAO eqJobBillItemPartDAO) {
		this.eqJobBillItemPartDAO = eqJobBillItemPartDAO;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的作业");
		EqJobBill bill = eqJobBillDAO.findById(fstrAutoId);
		if(bill == null){
			throw new RuntimeException("不存在此作业单[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(bill.getCurrentStatus())){
			throw new RuntimeException("作业单已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		//清除作业单解决故�?
		eqFaultDetailDAO.cleanByJobBillAutoId(fstrAutoId);
		//删除作业单项目备�?
		eqJobBillItemPartDAO.delByAutoId(fstrAutoId);
		//删除作业单项�?
		eqJobBillItemDAO.delByAutoId(fstrAutoId);
		//删除作业�?
		eqJobBillDAO.delete(bill);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询作业单主记录ID列表");
		List<Object> data = eqJobBillDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据作业单主记录ID查询作业�?以及相关信息");
		EqJobBill bill = eqJobBillDAO.findById(fstrAutoId);
		List<EqJobBillItem> itemList = eqJobBillItemDAO.findByAutoId(fstrAutoId);
		List<EqJobBillItemPart> partList = eqJobBillItemPartDAO.findByAutoId(fstrAutoId);
		List<EqFaultDetail> faultList = eqFaultDetailDAO.findByJobBillAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(bill);
		data.add(itemList);
		data.add(partList);
		data.add(faultList);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询作业单列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = eqJobBillDAO.findListByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject save(EqJobBill bill, List<EqJobBillItem> itemList,
			List<EqJobBillItemPart> partList, List<EqJobBillFault> faultList) {
		ReObject ro = new ReObject("保存作业单");
		String autoId = bill.getAutoId();
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		if(autoId == null || "".equals(autoId)){
			//新增
			String jobBillNo = bill.getJobBillNo();
			if(jobBillNo == null || "".equals(jobBillNo)){
				bill.setJobBillNo(eqBillNoService.getNextBillNo(RCPT_FLAG));
			}else{
				//校验作业单号唯一
				if(!eqJobBillDAO.checkJobBillNoUnique(unitsCode,jobBillNo)){
					throw new RuntimeException("手工输入的作业单号[" + jobBillNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			bill.setUnitsCode(unitsCode);
			bill.setCurrentStatus("0");
			bill.setMaker(user.getPersonId());
			bill.setMakeDate(new Date());
			bill.setSourceData("1");
			eqJobBillDAO.save(bill);
			autoId = bill.getAutoId();
		}else{
			//修改
			//校验未审�?
			EqJobBill original = eqJobBillDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此作业单[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("作业单已审核[系统标识号："+autoId+"]，不能修改！");
			}
			eqJobBillDAO.merge(bill);
			//先删除明细记�?
			//清除作业单解决故�?
			eqFaultDetailDAO.cleanByJobBillAutoId(autoId);
			//删除作业单项目备�?
			eqJobBillItemPartDAO.delByAutoId(autoId);
			//删除作业单项�?
			eqJobBillItemDAO.delByAutoId(autoId);
		}
		for(EqJobBillItem item : itemList){
			item.setAutoId(autoId);
			eqJobBillItemDAO.save(item);
		}
		for(EqJobBillItemPart part : partList){
			part.setAutoId(autoId);
			eqJobBillItemPartDAO.save(part);
		}
		//如果有故障记录的话，更新故障明细记录
		if(faultList != null && !faultList.isEmpty()){
			for(EqJobBillFault fault : faultList){
				EqFaultDetail detail = eqFaultDetailDAO.findById(fault.getFaultNo(),fault.getFaultSerialNo());
				if(detail != null){
					detail.setCreateJobBillSign("1");
					detail.setJobBillAutoId(autoId);
					detail.setJobCode(bill.getJobCode());
					detail.setJobName(bill.getJobName());
				}
			}
		}
		List<Object> data = new ArrayList<Object>();
		data.add(bill);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核未审核的作业单");
		EqJobBill bill = eqJobBillDAO.findById(fstrAutoId);
		if(bill == null){
			throw new RuntimeException("不存在此作业单[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(bill.getCurrentStatus())){
			throw new RuntimeException("作业单已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		bill.setCurrentStatus("1");
		bill.setVerifier(SessionUtil.getPersonId());
		bill.setVerifyDate(new Date());
		List<Object> data = new ArrayList<Object>();
		data.add(bill);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findFaultByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件过滤故障记录");
		fparameter.getConditions().put("createJobBillSign", "0");
		fparameter.getConditions().put("jobStatus", "0");
		List<EqFaultDetail> data = eqFaultDetailDAO.findVerifiedByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public EqJobBill buildJobBill(EqFaultDetail faultDetail) {
		String jobCode = faultDetail.getJobCode();
		if(jobCode == null || "".equals(jobCode)){
			throw new RuntimeException(String.format("故障记录[系统标识号：%s,序号�?s]没有设置作业编码，不能生成作业单！",faultDetail.getAutoId(),faultDetail.getSerialNo()));
		}
		String unitsCode = SessionUtil.getUnitsCode();
		EqJobContent content = eqJobContentDAO.findByJobCode(unitsCode,jobCode);
		if(content == null){
			throw new RuntimeException("故障记录的作业内容[作业编码："+jobCode+"]不存在，不能生成作业单！");
		}
		EqJobBill bill = content.buildJobBill();
		bill.setJobBillNo(eqBillNoService.getNextBillNo(RCPT_FLAG));
		bill.setCurrentStatus("0");
		bill.setMaker(SessionUtil.getPersonId());
		bill.setMakeDate(new Date());
		bill.setSourceData("2");
		//作业�?
		eqJobBillDAO.save(bill);
		String billAutoId = bill.getAutoId();
		//作业单项�?
		String contentAutoId = content.getAutoId();
		List<EqJobContentItem> contentItemList = eqJobContentItemDAO.findByAutoId(contentAutoId);
		for(EqJobContentItem item : contentItemList){
			EqJobBillItem billItem = item.buildJobBillItem();
			billItem.setAutoId(billAutoId);
			eqJobBillItemDAO.save(billItem);
		}
		//作业备件
		List<EqJobContentItemPart> contentItemPartList = eqJobContentItemPartDAO.findByAutoId(contentAutoId);
		for(EqJobContentItemPart part : contentItemPartList){
			EqJobBillItemPart billItemPart = part.buildJobBillItemPart();
			billItemPart.setAutoId(billAutoId);
			eqJobBillItemPartDAO.save(billItemPart);
		}
		return bill;
	}

	@Override
	public ReObject findJobPlanByPlanNo(String fstrPlanNo) {
		ReObject ro = new ReObject("根据作业计划单号查询作业计划，以及相关信息");
		EqJobPlan plan = eqJobPlanDAO.findByPlanNo(SessionUtil.getUnitsCode(),fstrPlanNo);
		if(plan == null){
			ro.setError("不存在此作业计划单号:"+fstrPlanNo);
			return ro;
		}
		String autoId = plan.getAutoId();
		List<EqFaultDetail> faultList = eqFaultDetailDAO.findByJobPlanAutoId(autoId);
		if(faultList != null && !faultList.isEmpty()){
			for(EqFaultDetail detail : faultList){
				if("1".equals(detail.getCreateJobBillSign())){
					ro.setError("此作业计划[单号:"+fstrPlanNo+"]引用的故障明细记录[系统标识号："+detail.getAutoId()+",序号:"+detail.getSerialNo()+"]已生成了作业单");
					return ro;
				}
			}
		}
		List<EqJobPlanItem> itemList = eqJobPlanItemDAO.findByAutoId(autoId);
		List<EqJobPlanItemPart> partList = eqJobPlanItemPartDAO.findByAutoId(autoId);
		List<Object> data = new ArrayList<Object>();
		data.add(plan);
		data.add(itemList);
		data.add(partList);
		data.add(faultList);
		ro.setData(data);
		return ro;
	}
}
