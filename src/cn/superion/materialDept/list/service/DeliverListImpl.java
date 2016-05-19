package cn.superion.materialDept.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.VMaterialRdsDeptDAO;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 出库单据列表服务实现
 * @author 曹国魁
 *
 */
public class DeliverListImpl implements IDeliverList {
	private VMaterialRdsDeptDAO vMaterialRdsDeptDAO;
	
	public VMaterialRdsDeptDAO getvMaterialRdsDeptDAO() {
		return vMaterialRdsDeptDAO;
	}

	public void setvMaterialRdsDeptDAO(VMaterialRdsDeptDAO vMaterialRdsDeptDAO) {
		this.vMaterialRdsDeptDAO = vMaterialRdsDeptDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findDeliverDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的出库单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", "2");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		Object[] objs = vMaterialRdsDeptDAO.findByCondition(start,limit,user.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
