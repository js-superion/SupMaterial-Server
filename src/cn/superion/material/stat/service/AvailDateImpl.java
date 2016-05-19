package cn.superion.material.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.util.SessionUtil;
/**
 * 保质期预警服务实现
 * @author 曹国魁
 *
 */
public class AvailDateImpl implements IAvailDate {
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
	public ReObject findAvailDateListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询保质期过期或临近的库存物资列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = materialCurrentStockDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
