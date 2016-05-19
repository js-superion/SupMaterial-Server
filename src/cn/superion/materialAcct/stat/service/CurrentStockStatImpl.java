package cn.superion.materialAcct.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.util.SessionUtil;
/**
 * 当前库存查询服务实现
 * @author 曹国魁
 *
 */
public class CurrentStockStatImpl implements ICurrentStockStat {
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}
	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}
	@Override
	public ReObject findCurrentStockByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取物资现存量信息");
		List<Object> data = materialCurrentStockDAO.addUpByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
