package cn.superion.materialAcct.stat.service;

import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.MaterialCurrentStockDeptDAO;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class ValueStockStatImpl implements IValueStockStat {

private MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO;
	
	public MaterialCurrentStockDeptDAO getMaterialCurrentStockDeptDAO() {
		return materialCurrentStockDeptDAO;
	}

	public void setMaterialCurrentStockDeptDAO(
			MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO) {
		this.materialCurrentStockDeptDAO = materialCurrentStockDeptDAO;
	}

	@Override
	public ReObject findValueStockByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取高值耗材现存量信息");
		SysUser user = SessionUtil.getSysUser();
		List<Map<String, Object>> data = materialCurrentStockDeptDAO.findValueMapByCondition(user.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);	
		return ro;
	}

}
