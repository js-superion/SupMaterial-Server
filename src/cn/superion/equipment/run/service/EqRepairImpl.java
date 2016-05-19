package cn.superion.equipment.run.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqRepairApplyDAO;
import cn.superion.equipment.dao.EqRepairDAO;
import cn.superion.equipment.entity.EqRepair;
import cn.superion.equipment.entity.EqRepairApply;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class EqRepairImpl implements IEqRepair {

	private EqRepairDAO eqRepairDAO;
	private EqRepairApplyDAO eqRepairApplyDAO;
	private IEqBillNoService eqBillNoService;
	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的记录");
		EqRepair master = eqRepairDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此记录[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(master.getStatus())){
			throw new RuntimeException("记录已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		eqRepairDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据条件，查询记录ID列表");
		List<Object> data = eqRepairDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据记录ID，查询记录");
		EqRepair master = eqRepairDAO.findById(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findDetailListByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据条件，查询记录ID列表");
		List<EqRepair> data = eqRepairDAO.findListByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(EqRepair master) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("保存维修记录");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String autoId = master.getAutoId();
		if(autoId == null || "".equals(autoId) ){
			//新增
			String billNo = master.getBillNo();
			if(billNo == null || "".equals(billNo)){
				master.setBillNo(eqBillNoService.getNextBillNo("8"));
			}else{
				//校验运行单号唯一
				if(!eqRepairDAO.checkBillNoUnique(unitsCode,billNo)){
					throw new RuntimeException("手工输入的运行单号[" + billNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			//校验是否生成维修记录
			if(master.getSourceAutoId()!=null && !"".equals(master.getSourceAutoId())){
				int size= eqRepairDAO.findBySourceAutoId(master.getSourceAutoId()).size();
				if(size > 0){
					throw new RuntimeException("autoid为：" +master.getSourceAutoId() + "的申请单已生成对应的报修单");
				}
				
			}
			master.setUnitsCode(unitsCode);
			master.setStatus("0");
			master.setMaker(user.getPersonId());
			master.setMakeDate(new Timestamp(new Date().getTime()));
			eqRepairDAO.save(master);
			autoId = master.getAutoId();

		}else{
			//修改
			EqRepair original = eqRepairDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在记录[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getStatus())){
				throw new RuntimeException("运行记录已审核[系统标识号："+autoId+"]，不能修改！");
			}
			master.setBillNo(original.getBillNo());
			eqRepairDAO.merge(master);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("审核未审核的维修单");
		EqRepair master = eqRepairDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("该维修单的[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(master.getStatus())){
			throw new RuntimeException("该维修单已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		master.setStatus("1");
		if(master.getSourceAutoId()!=null &&!"".equals(master.getSourceAutoId()) ){
			//更新申请单中的状态
			EqRepairApply apply = eqRepairApplyDAO.findById(master.getSourceAutoId());
			apply.setStatus("2");
		}
		master.setVerifier(SessionUtil.getPersonId());
		master.setVerifyDate(new Timestamp(new Date().getTime()));
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}

	public EqRepairDAO getEqRepairDAO() {
		return eqRepairDAO;
	}

	public void setEqRepairDAO(EqRepairDAO eqRepairDAO) {
		this.eqRepairDAO = eqRepairDAO;
	}

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	public EqRepairApplyDAO getEqRepairApplyDAO() {
		return eqRepairApplyDAO;
	}

	public void setEqRepairApplyDAO(EqRepairApplyDAO eqRepairApplyDAO) {
		this.eqRepairApplyDAO = eqRepairApplyDAO;
	}


}
