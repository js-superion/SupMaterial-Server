package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqRunStatusDict;
/**
 * 设备运行状态字典服务接口
 * @author 曹国魁
 *
 */
public interface IEqRunStatusDict {
	/**
	 * 查询所有的设备运行状态
	 * @return {@code List<EqRunStatusDict>}
	 */
	ReObject findAll();
	
	/**
	 * 保存设备运行状态
	 * @param isAdd 是否新增
	 * @param eqRunStatusDict
	 * @return
	 */
	ReObject save(boolean isAdd,EqRunStatusDict eqRunStatusDict);
	
	/**
	 * 删除设备运行状态
	 * @param statusCode
	 * @return
	 */
	ReObject del(String statusCode);
}
