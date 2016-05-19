package cn.superion.material.list.service;
import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialOrderDAO;
import cn.superion.util.SessionUtil;
/**
 * 采购订单列表服务实现
 * @author 曹国魁
 *
 */
public class OrderListImpl implements IOrderList {
	private VMaterialOrderDAO vMaterialOrderDAO;
	public VMaterialOrderDAO getvMaterialOrderDAO() {
		return vMaterialOrderDAO;
	}
	public void setvMaterialOrderDAO(VMaterialOrderDAO vMaterialOrderDAO) {
		this.vMaterialOrderDAO = vMaterialOrderDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findOrderDetailListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的采购订单单据列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = vMaterialOrderDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
