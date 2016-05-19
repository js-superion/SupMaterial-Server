package cn.superion.materialDept.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.VMaterialCheckDeptDAO;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 盘点单据列表服务实现
 * @author 曹国魁
 *
 */
public class CheckListImpl implements ICheckList {
	private VMaterialCheckDeptDAO vMaterialCheckDeptDAO;
	
	public VMaterialCheckDeptDAO getvMaterialCheckDeptDAO() {
		return vMaterialCheckDeptDAO;
	}

	public void setvMaterialCheckDeptDAO(VMaterialCheckDeptDAO vMaterialCheckDeptDAO) {
		this.vMaterialCheckDeptDAO = vMaterialCheckDeptDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findCheckDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的盘点单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", "1");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		Object[] objs = vMaterialCheckDeptDAO.findByCondition(start,limit,user.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
