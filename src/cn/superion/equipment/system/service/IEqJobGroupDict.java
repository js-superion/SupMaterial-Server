package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqJobGroupDict;
/**
 * 作业小组字典服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqJobGroupDict {
	/**
	 * 查询所有的作业小组
	 * @return {@code List<EqJobGroupDict>}
	 */
	ReObject findAll();

	/**
	 * 保存作业小组
	 * @param isAdd 是否新增
	 * @param eqJobGroupDict
	 * @return
	 */
	ReObject save(boolean isAdd, EqJobGroupDict eqJobGroupDict);

	/**
	 * 删除作业小组
	 * @param jobCode
	 * @return
	 */
	ReObject del(String jobCode);
}
