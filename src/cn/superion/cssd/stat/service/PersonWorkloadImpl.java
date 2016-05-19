package cn.superion.cssd.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.VCssdPersonWorkloadDAO;
import cn.superion.util.SessionUtil;
/**
 * 人员工作量查询服务实现
 * @author 曹国魁
 *
 */
public class PersonWorkloadImpl implements IPersonWorkload {
	private VCssdPersonWorkloadDAO vCssdPersonWorkloadDAO;
	
	public VCssdPersonWorkloadDAO getvCssdPersonWorkloadDAO() {
		return vCssdPersonWorkloadDAO;
	}

	public void setvCssdPersonWorkloadDAO(
			VCssdPersonWorkloadDAO vCssdPersonWorkloadDAO) {
		this.vCssdPersonWorkloadDAO = vCssdPersonWorkloadDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的人员工作列表");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = vCssdPersonWorkloadDAO.findWorkloadByCondition(start,limit,SessionUtil.getUnitsCode(),fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

}
