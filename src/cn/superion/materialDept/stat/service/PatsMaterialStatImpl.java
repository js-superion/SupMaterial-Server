package cn.superion.materialDept.stat.service;

import java.util.ArrayList;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.VMaterialPatsDAO;

public class PatsMaterialStatImpl implements IPatsMaterialStat {

	private VMaterialPatsDAO vMaterialPatsDAO;
	@Override
	public ReObject findPatsStatListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询当前科室病人使用材料信息");
		
//		SysUser user = SessionUtil.getSysUser();
		Object[] objs= vMaterialPatsDAO.findPatsMaterialDeptByCondition(fparameter.getConditions());
		
		List<Object> dataList=new ArrayList<Object>();
		dataList.add(objs[0]);
		ro.setData(dataList);
		return ro;
	}
	public VMaterialPatsDAO getvMaterialPatsDAO() {
		return vMaterialPatsDAO;
	}
	public void setvMaterialPatsDAO(VMaterialPatsDAO vMaterialPatsDAO) {
		this.vMaterialPatsDAO = vMaterialPatsDAO;
	}

}
