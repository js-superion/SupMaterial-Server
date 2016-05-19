package cn.superion.cssd.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdStockDetailDAO;
import cn.superion.cssd.dao.CssdStockMasterDAO;
import cn.superion.cssd.entity.CssdStockDetail;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.util.SessionUtil;
/**
 * 当前现存量服务实现
 * @author 曹国魁
 *
 */
public class CurrentStockStatImpl implements ICurrentStockStat {
	private CssdStockMasterDAO cssdStockMasterDAO;
	private CssdStockDetailDAO cssdStockDetailDAO;
	public CssdStockMasterDAO getCssdStockMasterDAO() {
		return cssdStockMasterDAO;
	}

	public void setCssdStockMasterDAO(CssdStockMasterDAO cssdStockMasterDAO) {
		this.cssdStockMasterDAO = cssdStockMasterDAO;
	}

	public CssdStockDetailDAO getCssdStockDetailDAO() {
		return cssdStockDetailDAO;
	}

	public void setCssdStockDetailDAO(CssdStockDetailDAO cssdStockDetailDAO) {
		this.cssdStockDetailDAO = cssdStockDetailDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findCurrentStockListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询物品包记录");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = cssdStockMasterDAO.findCurrentByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
//		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(0, 10000);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findDetailByPackageNo(String fstrPackageNo) {
		ReObject ro = new ReObject("根据物品包编号查询物品包明细记录");
		List<CssdStockDetail> data = cssdStockDetailDAO.findByPackageNo(fstrPackageNo);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findSterilizeDetailBySterilizeAutoId(
			String fstrSterilizeAutoId) {
		ReObject ro = new ReObject("查询同锅次，锅号的灭菌包列表");
		List<CssdStockMaster> data = cssdStockMasterDAO.findBySterilizeAutoId(SessionUtil.getUnitsCode(), fstrSterilizeAutoId);
		ro.setData(data);
		return ro;
	}

}
