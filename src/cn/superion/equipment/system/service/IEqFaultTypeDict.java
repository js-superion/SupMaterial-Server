package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqFaultTypeDict;

/**
 * 故障类型字典服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqFaultTypeDict {
	/**
	 * 查询所有的故障类型
	 * @return {@code List<EqFaultTypeDict>}
	 */
	ReObject findAll();

	/**
	 * 保存故障类型
	 * @param isAdd 是否新增
	 * @param eqFaultTypeDict
	 * @return
	 */
	ReObject save(boolean isAdd, EqFaultTypeDict eqFaultTypeDict);

	/**
	 * 删除故障类型
	 * @param typeCode
	 * @return
	 */
	ReObject del(String typeCode);
}
