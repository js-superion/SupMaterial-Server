package cn.superion.material.list.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 采购请购列表服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IApplyList {
	/**
	 * 根据查找条件，获取当前符合条件的采购计划单据列表,分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>dataSource 数据来源</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginBillNo 开始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>deptCode 部门</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>beginTradePrice 起始进价范围</li>
	 *            <li>endTradePrice 结束进价范围</li>
	 *            <li>salerCode 供应商</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialPlan>}
	 */
	ReObject findPlanDetailListByCondition(ParameterObject fparameter);
}
