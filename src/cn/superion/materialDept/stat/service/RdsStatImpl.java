package cn.superion.materialDept.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.MaterialRdsStockDAO;
import cn.superion.material.stat.entity.RdsStatistic;
import cn.superion.system.entity.SysUser;
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
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<RdsStatistic> data = materialRdsStockDAO.addUpByCondition(user.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
