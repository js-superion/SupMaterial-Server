package cn.superion.materialDept.list.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 报损单据列表服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IRejectList {
	/**
	 * 根据查找条件，获取当前符合条件的报损单据列表，分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>beginBillNo 起始报损单号</li>
	 *            <li>endBillNo 结束报损单号</li>
	 *            <li>beginBillDate 起始报损日期</li>
	 *            <li>endBillDate 结束报损日期</li>
	 *            <li>outDeptCode 报损部门</li>
	 *            <li>personId 经手人</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialRejectDept>}
	 */
	ReObject findRejectDetailListByCondition(ParameterObject fparameter);
}
