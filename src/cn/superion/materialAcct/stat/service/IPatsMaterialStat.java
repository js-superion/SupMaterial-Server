package cn.superion.materialAcct.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;


public interface IPatsMaterialStat {

	/**
	 * 查询病人使用材料信息
	 * @param fparameter
	 * @return
	 */
	ReObject findPatsStatListByCondition(ParameterObject fparameter);
}
