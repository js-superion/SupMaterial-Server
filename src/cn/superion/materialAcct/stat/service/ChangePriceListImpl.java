package cn.superion.materialAcct.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.MaterialChangePriceDetailDAO;
import cn.superion.material.dao.MaterialChangePriceMasterDAO;
import cn.superion.material.entity.MaterialChangePriceDetail;
import cn.superion.util.SessionUtil;

public class ChangePriceListImpl implements IChangePriceList {
	private MaterialChangePriceMasterDAO materialChangePriceMasterDAO;
	private MaterialChangePriceDetailDAO materialChangePriceDetailDAO;

	@Override
	public ReObject findByChangeBillNo(String fstrBillNo) {
		ReObject ro = new ReObject("根据调价单号查询调价明细列表");
		List<MaterialChangePriceDetail> data = materialChangePriceDetailDAO
				.findByProperty("mainAutoId", fstrBillNo);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findChangeListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据调价查询调价报告单列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", "3");
		Object[] objs = materialChangePriceMasterDAO.findByCondition(start,
				limit, SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}
	
	@Override
	public ReObject findChangePriceListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询符合条件的调价记录列表");
		List<Object> data = materialChangePriceMasterDAO.findByCondition(SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}
	public void setMaterialChangePriceMasterDAO(
			MaterialChangePriceMasterDAO materialChangePriceMasterDAO) {
		this.materialChangePriceMasterDAO = materialChangePriceMasterDAO;
	}

	public MaterialChangePriceMasterDAO getMaterialChangePriceMasterDAO() {
		return materialChangePriceMasterDAO;
	}

	public MaterialChangePriceDetailDAO getMaterialChangePriceDetailDAO() {
		return materialChangePriceDetailDAO;
	}

	public void setMaterialChangePriceDetailDAO(
			MaterialChangePriceDetailDAO materialChangePriceDetailDAO) {
		this.materialChangePriceDetailDAO = materialChangePriceDetailDAO;
	}

}
