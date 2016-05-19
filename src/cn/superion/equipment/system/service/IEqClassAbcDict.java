package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqClassAbcDict;
/**
 * 设备ABC类别字典服务接口
 * @author 曹国魁
 *
 */
public interface IEqClassAbcDict {
	/**
	 * 查询所有的设备ABC类别
	 * @return {@code List<EqClassAbcDict>}
	 */
	ReObject findAll();
	
	/**
	 * 保存设备ABC类别
	 * @param isAdd 是否新增
	 * @param eqClassAbcDict
	 * @return
	 */
	ReObject save(boolean isAdd,EqClassAbcDict eqClassAbcDict);
	
	/**
	 * 删除设备ABC类别
	 * @param classCode
	 * @return
	 */
	ReObject del(String classCode);
}
