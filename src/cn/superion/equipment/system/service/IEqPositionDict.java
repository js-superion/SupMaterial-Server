package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqPositionDict;

/**
 * 设备位置字典服务接口
 * @author 曹国魁
 *
 */
public interface IEqPositionDict {
	/**
	 * 查询所有的设备位置
	 * @return {@code List<EqPositionDict>}
	 */
	ReObject findAll();
	
	/**
	 * 保存设备位置
	 * @param isAdd 是否新增
	 * @param eqPositionDict
	 * @return
	 */
	ReObject save(boolean isAdd,EqPositionDict eqPositionDict);
	
	/**
	 * 删除设备位置
	 * @param positionCode
	 * @return
	 */
	ReObject del(String positionCode);
}
