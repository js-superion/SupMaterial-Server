package cn.superion.materialAcct.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialRdsAcctMasterDAO;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.util.SessionUtil;
/**
 * 出库报告单服务实现
 * @author 曹国魁
 *
 */
public class DeliverListImpl implements IDeliverList {
	private MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	
	public MaterialRdsDetailDAO getMaterialRdsDetailDAO() {
		return materialRdsDetailDAO;
	}

	public void setMaterialRdsDetailDAO(MaterialRdsDetailDAO materialRdsDetailDAO) {
		this.materialRdsDetailDAO = materialRdsDetailDAO;
	}

	public MaterialRdsAcctMasterDAO getMaterialRdsAcctMasterDAO() {
		return materialRdsAcctMasterDAO;
	}

	public void setMaterialRdsAcctMasterDAO(
			MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO) {
		this.materialRdsAcctMasterDAO = materialRdsAcctMasterDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findDeliverListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件查询出库报告单列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		fparameter.getConditions().put("rdFlag", RdConstant.D);
		Object[] objs = materialRdsAcctMasterDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findByAcctBillNo(String fstrAcctBillNo) {
		ReObject ro = new ReObject("根据核算单号查询核算明细列表");
		List<MaterialRdsDetail> data = materialRdsDetailDAO.findByAcctBillNo(fstrAcctBillNo);
		ro.setData(data);
		return ro;
	}

}
