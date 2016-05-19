package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqNationClassDict;

/**
 * 设备类别字典服务接口
 * @author 曹国魁
 *
 */
public interface IEqNationClassDict {
	/**
	 * 查询所有的设备类别
	 * @return {@code List<EqNationClassDict>}
	 */
	ReObject findAll();
	
	/**
	 * 保存设备国家类别，设备国家类别编码唯一
	 * @param isAdd 是否新增
	 * @param eqNationClassDict
	 * @return
	 */
	ReObject save(boolean isAdd,EqNationClassDict eqNationClassDict);
	
	/**
	 * 删除设备类别
	 * @param classCode
	 * @return
	 */
	ReObject del(String classCode);
}
