package cn.superion.material.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.util.SessionUtil;
/**
 * 安全库存预警服务实现
 * @author 曹国魁
 *
 */
public class SafeStockImpl implements ISafeStock {
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}
	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findSafeStockListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询结存数量小于安全库存量的物资列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = materialCurrentStockDAO.findUnsafeStockByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
