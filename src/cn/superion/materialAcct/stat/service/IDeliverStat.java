package cn.superion.materialAcct.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 出库汇总服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IDeliverStat {
	/**
	 * 根据条件，汇总出库物资的批发金额和零售金额,三种汇总方式： 1.按领用部门； 2.按物资编码； 3.按领用部门，物资编码；
	 * 客户端按部门、物资分类、物资编码作为分组汇总列
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始出库单号</li>
	 *            <li>endBillNo 结束出库单号</li>
	 *            <li>beginBillDate 起始日期</li>
	 *            <li>endBillDate 结束日期</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>beginMaterialClass 起始物资分类</li>
	 *            <li>endMaterialClass 结束物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>accounter 记账人</li>
	 *            <li>beginAccountDate 起始记账日期</li>
	 *            <li>endAccountDate 结束记账日期</li>
	 *            <li>statFlag 汇总方式
	 *            <ul>
	 *            <li>1 按领用部门</li>
	 *            <li>2 按物资编码</li>
	 *            <li>3 按领用部门，物资编码</li>
	 *            </ul>
	 *            </li>
	 *            </ul>
	 * @return 返回{@code List<Map<String,Object>>}，包含：
	 *         <ul>
	 *         <li>按领用部门汇总方式返回：
	 *         <ul>
	 *         <li>deptCode 领用部门</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         </ul>
	 *         </li>
	 *         <li>
	 *         按物资编码汇总方式返回：
	 *         <ul>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         </ul>
	 *         </li>
	 *         <li>
	 *         按领用部门,物资编码汇总方式返回：
	 *         <ul>
	 *         <li>deptCode 领用部门</li>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         </ul>
	 *         </li>
	 *         </ul>
	 */
	ReObject findDeliverStatByCondition(ParameterObject fparameter);
	
	ReObject findDeliverStatByAccount(ParameterObject fparameter);
}
