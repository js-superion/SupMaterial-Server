package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqJobTypeDict;

/**
 * 作业类型字典服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqJobTypeDict {
	/**
	 * 查询所有的作业类型
	 * @return {@code List<EqJobTypeDict>}
	 */
	ReObject findAll();

	/**
	 * 保存作业类型
	 * @param isAdd 是否新增
	 * @param eqJobTypeDict
	 * @return
	 */
	ReObject save(boolean isAdd, EqJobTypeDict eqJobTypeDict);

	/**
	 * 删除作业类型
	 * @param typeCode
	 * @return
	 */
	ReObject del(String typeCode);
}
