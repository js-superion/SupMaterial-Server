package cn.superion.materialDept.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.dao.VMaterialRdsDeptDAO;
import cn.superion.materialDept.stat.entity.StockStatistic;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 入库汇总服务实现
 * @author 曹国魁
 *
 */
public class ReceiveStatImpl implements IReceiveStat {
	private VMaterialRdsDeptDAO vMaterialRdsDeptDAO;
	
	public VMaterialRdsDeptDAO getvMaterialRdsDeptDAO() {
		return vMaterialRdsDeptDAO;
	}

	public void setvMaterialRdsDeptDAO(VMaterialRdsDeptDAO vMaterialRdsDeptDAO) {
		this.vMaterialRdsDeptDAO = vMaterialRdsDeptDAO;
	}

	@Override
	public ReObject findReceiveStatListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，按供应商，物资类别，物资编码，物资规格等汇总入库明细");
		fparameter.getConditions().put("rdFlag", RdConstant.R);
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<StockStatistic> data = vMaterialRdsDeptDAO.addUpDeptRdsByCondition(user.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

}
