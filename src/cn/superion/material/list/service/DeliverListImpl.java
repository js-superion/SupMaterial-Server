package cn.superion.material.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialRdsDAO;
import cn.superion.material.entity.VMaterialRds;
import cn.superion.util.SessionUtil;
/**
 * 出库单据列表服务实现
 * @author 曹国魁
 *
 */
public class DeliverListImpl implements IDeliverList {
	private VMaterialRdsDAO vMaterialRdsDAO;
	public VMaterialRdsDAO getvMaterialRdsDAO() {
		return vMaterialRdsDAO;
	}
	public void setvMaterialRdsDAO(VMaterialRdsDAO vMaterialRdsDAO) {
		this.vMaterialRdsDAO = vMaterialRdsDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findDeliverDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的出库单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", "2");
		Object[] objs = vMaterialRdsDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}
	
	public ReObject findData() {
		ReObject ro = new ReObject("");
		List<VMaterialRds> list= vMaterialRdsDAO.findAll();
		ro.setData(list);
		return ro;
	}
	
	public ReObject findData(String fstrGroupFields) {
		ReObject ro = new ReObject("");
		List list= vMaterialRdsDAO.findGroupBy(fstrGroupFields); 
		ro.setData(list);
		return ro;
	}
}
