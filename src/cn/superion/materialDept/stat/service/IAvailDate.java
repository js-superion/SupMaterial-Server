package cn.superion.materialDept.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 保质期预警服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IAvailDate {
	/**
	 * 根据条件，查询保质期过期或临近的库存物资列表，分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>materialClass 物资类型</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>overdueNum 过期天数</li>
	 *            <li>anearNum 临近天数</li>
	 *            </ul>
	 *            条件过期天数，临近天数和失效日期范围是互斥条件
	 * @return 返回{@code List<MaterialCurrentStockDept>}
	 */
	ReObject findAvailDateListByCondition(ParameterObject fparameter);
}
