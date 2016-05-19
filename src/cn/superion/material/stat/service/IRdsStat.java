package cn.superion.material.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 收发存汇总服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IRdsStat {
	/**
	 * 根据条件统计收发存汇总，包含期初，入库，出库，结存数量
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginYearMonth 起始月份yyyy-MM</li>
	 *            <li>endYearMonth 结束月份yyyy-MM</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginCurrentStockAmount 起始结存数量</li>
	 *            <li>endCurrentStockAmount 结束结存数量</li>
	 *            </ul>
	 * @return 返回{@code List<RdsStatistic>}
	 */
	ReObject findRdsStatListByCondition(ParameterObject fparameter);
	ReObject findRdsStatListByConditionMonth(ParameterObject fparameter);
}
