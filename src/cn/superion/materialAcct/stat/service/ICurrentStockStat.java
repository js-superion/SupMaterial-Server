package cn.superion.materialAcct.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 当前库存查询服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ICurrentStockStat {
	/**
	 * 根据查找条件，获取物资现存量信息
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>anearNum 临近天数</li>
	 *            <li>stockAmountFlag 汇总数量标志
	 *            <ul>
	 *            <li>1 >0</li>
	 *            <li>2 =0</li>
	 *            <li>3 <0</li>
	 *            <li>4 >=0</li>
	 *            <li>5 <=0</li>
	 *            <li>6 >0 or <0,即不等于0</li>
	 *            </ul>
	 *            </li>
	 *            </ul>
	 * @return {@code List<Map<String,Object>>},包含：
	 *         <ul>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>amount 库存数量</li>
	 *         <li>saleMoney 批发价金额</li>
	 *         <li>retailMoney 零售价金额</li>
	 *         </ul>
	 */
	ReObject findCurrentStockByCondition(ParameterObject fparameter);
}
