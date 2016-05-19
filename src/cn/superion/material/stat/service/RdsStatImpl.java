package cn.superion.material.stat.service;

import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.MaterialRdsStockDAO;
import cn.superion.material.stat.entity.RdsStatistic;
import cn.superion.util.SessionUtil;
/**
 * 收发存汇总服务实现
 * @author 曹国魁
 *
 */
public class RdsStatImpl implements IRdsStat {
	private MaterialRdsStockDAO materialRdsStockDAO;
	public MaterialRdsStockDAO getMaterialRdsStockDAO() {
		return materialRdsStockDAO;
	}
	public void setMaterialRdsStockDAO(MaterialRdsStockDAO materialRdsStockDAO) {
		this.materialRdsStockDAO = materialRdsStockDAO;
	}
	@Override
	public ReObject findRdsStatListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件统计收发存汇总");
		String isFee = fparameter.getConditions().get("isFee").toString();
		List<RdsStatistic> data = null;
		if(isFee.equals("true")){
			data = materialRdsStockDAO.addUpByConditionFee(SessionUtil.getUnitsCode(),fparameter.getConditions());
		}else{
			data = materialRdsStockDAO.addUpByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsStatListByConditionMonth(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件统计收发存汇总");
		String isFee = fparameter.getConditions().get("isFee").toString();
		List<Map<String,Object>> data = null;
		if(isFee.equals("true")){
			data = materialRdsStockDAO.addUpByConditionFeeMonth(SessionUtil.getUnitsCode(),fparameter.getConditions());
		}else{
		//	data = materialRdsStockDAO.addUpByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		}
		ro.setData(data);
		return ro;
	}
}
