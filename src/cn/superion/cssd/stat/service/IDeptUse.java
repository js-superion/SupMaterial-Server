package cn.superion.cssd.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 科室领用统计服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IDeptUse {
	/**
	 * 根据查找条件，获取当前符合条件的科室领用列表
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>deptCode 领用科室</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>overdueNum 过期天数</li>
	 *            <li>anearNum 临近天数</li>
	 *            </ul>
	 * @return {@code List<Object>}
	 *         <ul>
	 *         <li>index:0 -- {@code List<VCssdDeliverStock>}</li>
	 *         <li>index:1 -- {@code List<Map<String,Object>>},map里包含：
	 *         <ul>
	 *         <li>deptCode 领用科室</li>
	 *         <li>materialCode 物品编码</li>
	 *         <li>materialName 物品名称</li>
	 *         <li>factoryCode 生产厂家</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>amount 数量</li>
	 *         <li>tradeMoney 金额</li>
	 *         </ul>
	 *         </li>
	 *         </ul>
	 */
	ReObject findByCondition(ParameterObject fparameter);
	
	ReObject findByCondition2(ParameterObject fparameter);
}
