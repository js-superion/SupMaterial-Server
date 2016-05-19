package cn.superion.equipment.run.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqRepairApplyDAO;
import cn.superion.equipment.entity.EqRepairApply;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class EqRepairApplyImpl implements IEqRepairApply {

	private EqRepairApplyDAO eqRepairApplyDAO;
	private IEqBillNoService eqBillNoService;
	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的记录");
		EqRepairApply master = eqRepairApplyDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此运行记录[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(master.getStatus())){
			throw new RuntimeException("运行记录已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		eqRepairApplyDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据条件，查询记录ID列表");
		List<Object> data = eqRepairApplyDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据运行记录ID，查询记录");
		EqRepairApply master = eqRepairApplyDAO.findById(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询记录ID列表");
		List<EqRepairApply> data = eqRepairApplyDAO.findListByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(EqRepairApply master) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("保存运行记录");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String deptName = user.getDeptName();
		String autoId = master.getAutoId();
		if(autoId == null || "".equals(autoId) ){
			//新增
			String billNo = master.getBillNo();
			if(billNo == null || "".equals(billNo)){
				master.setBillNo(eqBillNoService.getNextBillNo("8"));
			}else{
				//校验运行单号唯一
				if(!eqRepairApplyDAO.checkBillNoUnique(unitsCode,billNo)){
					throw new RuntimeException("手工输入的运行单号[" + billNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			master.setUnitsCode(unitsCode);
			master.setStatus("0");
			master.setDeptName(deptName);
			master.setMaker(user.getPersonId());
			master.setMakeDate(new Timestamp(new Date().getTime()));
			eqRepairApplyDAO.save(master);
			autoId = master.getAutoId();

		}else{
			//修改
			EqRepairApply original = eqRepairApplyDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在此运行记录[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getStatus())){
				throw new RuntimeException("运行记录已审核[系统标识号："+autoId+"]，不能修改！");
			}
			eqRepairApplyDAO.merge(master);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("审核未审核的申请单");
		EqRepairApply master = eqRepairApplyDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("该申请单的[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(master.getStatus())){
			throw new RuntimeException("该申请单的已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		master.setStatus("1");
		master.setVerifier(SessionUtil.getPersonId());
		master.setVerifyDate(new Timestamp(new Date().getTime()));
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	public EqRepairApplyDAO getEqRepairApplyDAO() {
		return eqRepairApplyDAO;
	}

	public void setEqRepairApplyDAO(EqRepairApplyDAO eqRepairApplyDAO) {
		this.eqRepairApplyDAO = eqRepairApplyDAO;
	}

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

}
