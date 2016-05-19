package cn.superion.material.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialRdsDAO;
import cn.superion.material.entity.VMaterialRds;
import cn.superion.util.SessionUtil;
/**
 * 库存台帐服务实现
 * @author 曹国魁
 *
 */
public class StockAccountImpl implements IStockAccount {
	private VMaterialRdsDAO vMaterialRdsDAO;
	public VMaterialRdsDAO getvMaterialRdsDAO() {
		return vMaterialRdsDAO;
	}
	public void setvMaterialRdsDAO(VMaterialRdsDAO vMaterialRdsDAO) {
		this.vMaterialRdsDAO = vMaterialRdsDAO;
	} 
	
	@Override
	public ReObject findStockAccountListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询库存物资ID列表");
		List<Object> data =  vMaterialRdsDAO.findMaterialIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findMaterialStockAccountListByCondition(
			String fstrMaterialId, ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，查询仓库指定物资的收发存明细");
		fparameter.getConditions().put("materialId", fstrMaterialId);
		fparameter.getConditions().put("currentStatus", "0");
		List<VMaterialRds> data = vMaterialRdsDAO.findByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions()," order by verifyDate,serialNo");
		ro.setData(data);
		return ro;
	}

	

}
