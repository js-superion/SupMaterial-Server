package cn.superion.materialDept.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.VMaterialPatsDAO;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 病人费用列表服务实现
 * @author 曹国魁
 *
 */
public class FeeListImpl implements IFeeList {
	private VMaterialPatsDAO vMaterialPatsDAO;
	public VMaterialPatsDAO getvMaterialPatsDAO() {
		return vMaterialPatsDAO;
	}
	public void setvMaterialPatsDAO(VMaterialPatsDAO vMaterialPatsDAO) {
		this.vMaterialPatsDAO = vMaterialPatsDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findFeeDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的病人费用单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		SysUser user = SessionUtil.getSysUser();
		Object[] objs = vMaterialPatsDAO.findByConditions(start,limit,user.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
