package cn.superion.material.stat.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.dao.VMaterialRdsDAO;
import cn.superion.material.entity.VMaterialRds;
import cn.superion.util.SessionUtil;
/**
 * 流水帐查询服务实现
 * @author 曹国魁
 *
 */
public class CurrentAccountStatImpl implements ICurrentAccountStat {
	private VMaterialRdsDAO vMaterialRdsDAO;
	public VMaterialRdsDAO getvMaterialRdsDAO() {
		return vMaterialRdsDAO;
	}
	public void setvMaterialRdsDAO(VMaterialRdsDAO vMaterialRdsDAO) {
		this.vMaterialRdsDAO = vMaterialRdsDAO;
	} 
	@Override
	public ReObject findCurrentAccountListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，查询一个时间段内物资的出入库情况");
		List<VMaterialRds> data = vMaterialRdsDAO.findByCondition2(SessionUtil.getUnitsCode(),fparameter.getConditions()," order by billDate");
		ro.setData(data);
		return ro;
	}

}
