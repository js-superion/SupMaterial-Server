package cn.superion.material.list.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialInvoiceDAO;
import cn.superion.util.SessionUtil;
/**
 * 采购发票列表服务实现
 * @author 曹国魁
 *
 */
public class InvoiceListImpl implements IInvoiceList {
	private VMaterialInvoiceDAO vMaterialInvoiceDAO;
	public VMaterialInvoiceDAO getvMaterialInvoiceDAO() {
		return vMaterialInvoiceDAO;
	}
	public void setvMaterialInvoiceDAO(VMaterialInvoiceDAO vMaterialInvoiceDAO) {
		this.vMaterialInvoiceDAO = vMaterialInvoiceDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findInvoiceDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的采购发票单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = vMaterialInvoiceDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
