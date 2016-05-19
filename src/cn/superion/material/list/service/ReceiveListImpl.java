package cn.superion.material.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialRdsDAO;
import cn.superion.util.SessionUtil;
/**
 * 入库单据列表服务实现
 * @author 曹国魁
 *
 */
public class ReceiveListImpl implements IReceiveList {
	private VMaterialRdsDAO vMaterialRdsDAO;
	public VMaterialRdsDAO getvMaterialRdsDAO() {
		return vMaterialRdsDAO;
	}
	public void setvMaterialRdsDAO(VMaterialRdsDAO vMaterialRdsDAO) {
		this.vMaterialRdsDAO = vMaterialRdsDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findReceiveDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的入库单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", "1");
		Object[] objs = vMaterialRdsDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
