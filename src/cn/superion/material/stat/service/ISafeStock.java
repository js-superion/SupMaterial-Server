package cn.superion.material.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 安全库存预警服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ISafeStock {
	/**
	 * 查询结存数量小于安全库存量的物资列表,分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>maerialClass 物资分类</li>
	 *            </ul>
	 * @return 返回{@code List<SafeStockStatistic>}
	 */
	ReObject findSafeStockListByCondition(ParameterObject fparameter);
}
