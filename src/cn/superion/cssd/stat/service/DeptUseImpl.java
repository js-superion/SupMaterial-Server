package cn.superion.cssd.stat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.VCssdDeliverStockDAO;
import cn.superion.cssd.dao.VCssdDeliverStockDetailDAO;
import cn.superion.cssd.entity.VCssdDeliverStock;
import cn.superion.util.SessionUtil;
/**
 * 科室领用统计服务实现
 * @author 曹国魁
 *
 */
public class DeptUseImpl implements IDeptUse {
	private VCssdDeliverStockDAO vCssdDeliverStockDAO;
	private VCssdDeliverStockDetailDAO vCssdDeliverStockDetailDAO;
	public VCssdDeliverStockDAO getvCssdDeliverStockDAO() {
		return vCssdDeliverStockDAO;
	}
	public void setvCssdDeliverStockDAO(VCssdDeliverStockDAO vCssdDeliverStockDAO) {
		this.vCssdDeliverStockDAO = vCssdDeliverStockDAO;
	}
	public VCssdDeliverStockDetailDAO getvCssdDeliverStockDetailDAO() {
		return vCssdDeliverStockDetailDAO;
	}
	public void setvCssdDeliverStockDetailDAO(
			VCssdDeliverStockDetailDAO vCssdDeliverStockDetailDAO) {
		this.vCssdDeliverStockDetailDAO = vCssdDeliverStockDetailDAO;
	}
	@Override
	public ReObject findByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的科室领用列表");
		List<Map<String, Object>> list1 = vCssdDeliverStockDAO.findByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		List<Map<String, Object>> list2 = vCssdDeliverStockDetailDAO.addUpByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		List<Object> data = new ArrayList<Object>();
		data.add(list1);
		data.add(list2);
		ro.setData(data);
		return ro;
	}
	public ReObject findByCondition2(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的科室领用列表");
		List<Map<String, Object>> list1 = vCssdDeliverStockDAO.findByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
//		List<Map<String, Object>> list2 = vCssdDeliverStockDetailDAO.addUpByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		List<Object> data = new ArrayList<Object>();
		data.add(list1);
//		data.add(list2);
		ro.setData(data);
		return ro;
	}
}
