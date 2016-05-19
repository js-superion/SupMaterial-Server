package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqStatusDict;

/**
 * 设备状态字典服务接口
 * @author 曹国魁
 *
 */
public interface IEqStatusDict {
	/**
	 * 查询所有的设备状态
	 * @return {@code List<EqStatusDict>}
	 */
	ReObject findAll();
	
	/**
	 * 保存设备状态
	 * @param isAdd 是否新增
	 * @param eqStatusDict
	 * @return
	 */
	ReObject save(boolean isAdd,EqStatusDict eqStatusDict);
	
	/**
	 * 删除设备状态
	 * @param statusCode
	 * @return
	 */
	ReObject del(String statusCode);
}
