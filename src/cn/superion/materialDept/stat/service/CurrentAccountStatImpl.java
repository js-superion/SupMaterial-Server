package cn.superion.materialDept.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.VMaterialRdsDeptDAO;
import cn.superion.materialDept.entity.VMaterialRdsDept;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 流水帐查询服务实现
 * @author 曹国魁
 *
 */
public class CurrentAccountStatImpl implements ICurrentAccountStat {
	private VMaterialRdsDeptDAO vMaterialRdsDeptDAO;
	
	public VMaterialRdsDeptDAO getvMaterialRdsDeptDAO() {
		return vMaterialRdsDeptDAO;
	}

	public void setvMaterialRdsDeptDAO(VMaterialRdsDeptDAO vMaterialRdsDeptDAO) {
		this.vMaterialRdsDeptDAO = vMaterialRdsDeptDAO;
	}

	@Override
	public ReObject findCurrentAccountListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，查询一个时间段内物资的出入库情况");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<VMaterialRdsDept> data = vMaterialRdsDeptDAO.findByCondition(user.getUnitsCode(),fparameter.getConditions()," order by billDate");
		ro.setData(data);
		return ro;
	}

}
