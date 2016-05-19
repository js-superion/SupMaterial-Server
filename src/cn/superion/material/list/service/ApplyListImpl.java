package cn.superion.material.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialPlanDAO;
import cn.superion.util.SessionUtil;
/**
 * 采购请购列表服务实现
 * @author 曹国魁
 *
 */
public class ApplyListImpl implements IApplyList {
	private VMaterialPlanDAO vMaterialPlanDAO; 
	public VMaterialPlanDAO getvMaterialPlanDAO() {
		return vMaterialPlanDAO;
	}
	public void setvMaterialPlanDAO(VMaterialPlanDAO vMaterialPlanDAO) {
		this.vMaterialPlanDAO = vMaterialPlanDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findPlanDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的采购计划单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = vMaterialPlanDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
