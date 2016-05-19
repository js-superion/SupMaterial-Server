package cn.superion.materialDept.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 现存量查询服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ICurrentStockStat {
	/**
	 * 根据查找条件，获取物资现存量信息
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>minStockAmount 现存量下限</li>
	 *            <li>maxStockAmount 现存量上限</li>
	 *            <li>beginAvailDate 起始有效期限</li>
	 *            <li>endAvailDate 结束有效期限</li>
	 *            </ul>
	 * @return 返回{@code List<MaterialCurrentStockDept>}
	 */
	ReObject findCurrentStockListByCondition(ParameterObject fparameter);
}
