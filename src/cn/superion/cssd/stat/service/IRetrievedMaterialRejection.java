package cn.superion.cssd.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 物品报损统计服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IRetrievedMaterialRejection {
	/**
	 * 根据条件，获取当前符合条件的物品包回收时的物品报损统计列表
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>deptCode 回收科室</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            </ul>
	 * @return {@code List<Map<String, Object>>},map里包含
	 *         <ul>
	 *         <li>deptCode 回收科室</li>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 物资规格</li>
	 *         <li>materialUnits 物资单位</li>
	 *         <li>tradeMoney 金额</li>
	 *         <li>amount 报损数量</li>
	 *         <li>factoryCode 生产厂家</li>
	 *         </ul>
	 */
	ReObject findByCondition(ParameterObject fparameter);
}
