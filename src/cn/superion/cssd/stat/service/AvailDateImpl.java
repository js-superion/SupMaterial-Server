package cn.superion.cssd.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdStockMasterDAO;
import cn.superion.util.SessionUtil;
/**
 * 无菌包效期服务实现
 * @author 曹国魁
 *
 */
public class AvailDateImpl implements IAvailDate {
	private CssdStockMasterDAO cssdStockMasterDAO;
	public CssdStockMasterDAO getCssdStockMasterDAO() {
		return cssdStockMasterDAO;
	}
	public void setCssdStockMasterDAO(CssdStockMasterDAO cssdStockMasterDAO) {
		this.cssdStockMasterDAO = cssdStockMasterDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findAvailDateListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询保质期过期或临近的无菌包列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = cssdStockMasterDAO.findByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
