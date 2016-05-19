package cn.superion.material.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialCheckDAO;
import cn.superion.util.SessionUtil;
/**
 * 盘点单据列表服务实现
 * @author 曹国魁
 *
 */
public class CheckListImpl implements ICheckList {
	private VMaterialCheckDAO vMaterialCheckDAO;
	public VMaterialCheckDAO getvMaterialCheckDAO() {
		return vMaterialCheckDAO;
	}
	public void setvMaterialCheckDAO(VMaterialCheckDAO vMaterialCheckDAO) {
		this.vMaterialCheckDAO = vMaterialCheckDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findCheckDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的盘点单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", "1");
		Object[] objs = vMaterialCheckDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
