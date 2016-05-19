package cn.superion.materialAcct.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 入库汇总服务接口
 * @author 曹国魁
 *
 */
public interface IReceiveStat {
	/**
	 * 根据查找条件，汇总入库物资的进价金额，实价金额，批发金额和零售金额，三种汇总方式： 1.按供应商； 2.按物资编码； 3.按供应商，物资编码；
	 * 客户端按供应商、物资分类、物资编码作为分组汇总列
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始入库单号</li>
	 *            <li>endBillNo 结束入库单号</li>
	 *            <li>beginInvoiceDate 起始发票日期</li>
	 *            <li>endInvoiceDate 结束发票日期</li>
	 *            <li>salerCode 供应商</li>
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
	 *            <li>1 按供应商</li>
	 *            <li>2 按物资编码</li>
	 *            <li>3 按供应商，物资编码</li>
	 *            </ul>
	 *            </li>
	 *            </ul>
	 * @return 返回{@code List<Map<String,Object>>}，包含：
	 *         <ul>
	 *         <li>按供应商汇总方式返回：
	 *         <ul>
	 *         <li>salerCode 供应商</li>
	 *         <li>tradeMoney 进价金额</li>
	 *         <li>factTradeMoney 实价金额</li>
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
	 *         <li>tradeMoney 进价金额</li>
	 *         <li>factTradeMoney 实价金额</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         </ul>
	 *         </li>
	 *         <li>
	 *         按供应商,物资编码汇总方式返回：
	 *         <ul>
	 *         <li>salerCode 供应商</li>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>tradeMoney 进价金额</li>
	 *         <li>factTradeMoney 实价金额</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         </ul>
	 *         </li>
	 *         </ul>
	 */
	ReObject findReceiveStatListByCondition(ParameterObject fparameter);
}
