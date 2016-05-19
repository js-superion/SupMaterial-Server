/**
 * 
 */
package cn.superion.equipment.spare.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.dao.VEqFaultDAO;
import cn.superion.util.SessionUtil;

/**
 * 备件需求统计服务实现
 * 
 * @author 李攀
 * @author 曹国魁
 * 
 */
public class EqSpareRequireImpl implements IEqSpareRequire {
	private VEqFaultDAO vEqFaultDAO;

	public VEqFaultDAO getvEqFaultDAO() {
		return vEqFaultDAO;
	}

	public void setvEqFaultDAO(VEqFaultDAO vEqFaultDAO) {
		this.vEqFaultDAO = vEqFaultDAO;
	}

	@Override
	public ReObject findByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询备件需求统计");
		List<Object> data = vEqFaultDAO.addUpByCondition(SessionUtil
				.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
