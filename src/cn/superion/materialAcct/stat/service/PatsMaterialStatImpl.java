package cn.superion.materialAcct.stat.service;

import java.util.ArrayList;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.dao.VMaterialPatsDAO;

public class PatsMaterialStatImpl implements IPatsMaterialStat {

	private VMaterialPatsDAO vMaterialPatsDAO;
	@Override
	public ReObject findPatsStatListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询病人使用材料信息");
		
//		SysUser user = SessionUtil.getSysUser();
		Object[] objs= vMaterialPatsDAO.findPatsMaterialAcctByCondition(fparameter.getConditions());
		
		List<Object> dataList=new ArrayList<Object>();
		//一般高值耗材
		dataList.add(objs[0]);
		//特殊高值耗材
		dataList.add(objs[1]);
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
