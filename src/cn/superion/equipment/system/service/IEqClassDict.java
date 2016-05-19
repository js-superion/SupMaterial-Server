package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqClassDict;

/**
 * 设备类别字典服务接口
 * @author 曹国魁
 *
 */
public interface IEqClassDict {
	/**
	 * 查询所有的设备类别
	 * @return {@code List<EqClassDict>}
	 */
	ReObject findAll();
	
	/**
	 * 保存设备类别，设备类别编码唯一
	 * @param isAdd 是否新增
	 * @param eqClassDict
	 * @return
	 */
	ReObject save(boolean isAdd,EqClassDict eqClassDict);
	
	/**
	 * 删除设备类别
	 * @param classCode
	 * @return
	 */
	ReObject del(String classCode);
}
