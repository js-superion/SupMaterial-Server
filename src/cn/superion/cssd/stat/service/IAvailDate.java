package cn.superion.cssd.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 无菌包效期服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IAvailDate {
	/**
	 * 根据条件，查询保质期过期或临近的无菌包列表，分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginPackageNo 起始物品包编号</li>
	 *            <li>endPackageNo 结束物品包编号</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>overdueNum 过期天数</li>
	 *            <li>anearNum 临近天数</li>
	 *            </ul>
	 *            条件过期天数，临近天数和失效日期范围是互斥条件
	 * @return {@code List<CssdStockMaster>}
	 */
	ReObject findAvailDateListByCondition(ParameterObject fparameter);
}
