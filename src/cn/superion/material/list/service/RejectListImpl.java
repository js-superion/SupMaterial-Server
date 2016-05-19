package cn.superion.material.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialRejectDAO;
import cn.superion.util.SessionUtil;

public class RejectListImpl implements IRejectList {
	private VMaterialRejectDAO vMaterialRejectDAO;
	public VMaterialRejectDAO getvMaterialRejectDAO() {
		return vMaterialRejectDAO;
	}
	public void setvMaterialRejectDAO(VMaterialRejectDAO vMaterialRejectDAO) {
		this.vMaterialRejectDAO = vMaterialRejectDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findRejectDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的报损单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", "1");
		Object[] objs = vMaterialRejectDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
