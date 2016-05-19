package cn.superion.cssd.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 物品包追溯服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ITrace {
	/**
	 * 根据查找条件，获取当前符合条件的物品包列表，分页查询
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginPackageId 起始物品包编码</li>
	 *            <li>endPackageId 结束物品包编码</li>
	 *            <li>beginAvailDate 起始有效期至</li>
	 *            <li>endAvailDate 结束有效期至</li>
	 *            <li>sterilizeStatus 灭菌状态</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return {@code List<CssdStockMaster>}
	 */
	ReObject findStockMasterListByCondition(ParameterObject fparameter);
}
