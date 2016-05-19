package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqChangeTypeDict;

/**
 * 变更类型字典服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqChangeTypeDict {
	/**
	 * 查询所有的变更类型
	 * @return {@code List<EqChangeTypeDict>}
	 */
	ReObject findAll();

	/**
	 * 保存变更类型
	 * @param isAdd 是否新增
	 * @param eqChangeTypeDict
	 * @return
	 */
	ReObject save(boolean isAdd, EqChangeTypeDict eqChangeTypeDict);

	/**
	 * 删除变更类型
	 * @param typeCode
	 * @return
	 */
	ReObject del(String typeCode);
	
	/**
	 * 根据类型编码查找变更类型
	 * @param typeCode
	 * @return EqChangeTypeDict
	 */
	ReObject findByCode(String typeCode);
}
