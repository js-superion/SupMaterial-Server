package cn.superion.materialDept.stat.service;

import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.MaterialCurrentStockDeptDAO;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 现存量查询服务实现
 * @author 曹国魁
 *
 */
public class CurrentStockStatImpl implements ICurrentStockStat {
	private MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO;
	
	public MaterialCurrentStockDeptDAO getMaterialCurrentStockDeptDAO() {
		return materialCurrentStockDeptDAO;
	}

	public void setMaterialCurrentStockDeptDAO(
			MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO) {
		this.materialCurrentStockDeptDAO = materialCurrentStockDeptDAO;
	}

	@Override
	public ReObject findCurrentStockListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取物资现存量信息");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<Map<String, Object>> data = materialCurrentStockDeptDAO.findMapByCondition(user.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);	
		return ro;
	}

}
