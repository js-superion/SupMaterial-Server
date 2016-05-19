package cn.superion.materialDept.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.VMaterialRejectDeptDAO;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class RejectListImpl implements IRejectList {
	private VMaterialRejectDeptDAO vMaterialRejectDeptDAO;
	
	public VMaterialRejectDeptDAO getvMaterialRejectDeptDAO() {
		return vMaterialRejectDeptDAO;
	}

	public void setvMaterialRejectDeptDAO(
			VMaterialRejectDeptDAO vMaterialRejectDeptDAO) {
		this.vMaterialRejectDeptDAO = vMaterialRejectDeptDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findRejectDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的报损单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", "1");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		Object[] objs = vMaterialRejectDeptDAO.findByCondition(start,limit,user.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
