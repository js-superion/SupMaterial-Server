package cn.superion.material.purchase.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialOrderDetail;
import cn.superion.material.entity.MaterialOrderMaster;

/**
 * 采购订单服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IOrder {
	/**
	 * 查询采购订单信息
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo 起始订单编号</li>
	 *            <li>endBillNo 结束订单编号</li>
	 *            <li>beginBillDate 起始订单日期</li>
	 *            <li>endBillDate 结束订单日期</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * 
	 * @return 主记录ID列表
	 */
	ReObject findOrderByCondition(ParameterObject fparameter);

	/**
	 * 查询采购订单明细列表
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回MaterialOrderMaster,{@code List<MaterialOrderDetail>}
	 */
	ReObject findOrderDetail(String fstrAutoId);

	/**
	 * 保存采购订单信息,并反写采购计划已生成订单数和当前状态
	 * 
	 * @param fmaster
	 *            采购订单处理主记录
	 * @param fdetails
	 *            采购订单处理明细记录列表
	 * @return 返回MaterialOrderMaster,{@code List<MaterialOrderDetail>}
	 */
	ReObject saveOrder(MaterialOrderMaster fmaster,
			List<MaterialOrderDetail> fdetails);

	/**
	 * 删除未审核的订单,先还原采购计划已生成订单数和当前状态
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return
	 */
	ReObject delOrder(String fstrAutoId);

	/**
	 * 审核订单
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回MaterialOrderMaster
	 */
	ReObject verifyOrder(String fstrAutoId);

	/**
	 * 过滤采购计划,当前状态为审核或执行
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>dataSource 数据来源</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>salerCode 供应商</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginRequireDate 起始需求日期</li>
	 *            <li>endRequireDate 结束需求日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialPlan>}
	 */
	ReObject findPlanDetailListByCondition(ParameterObject fparameter);

}
