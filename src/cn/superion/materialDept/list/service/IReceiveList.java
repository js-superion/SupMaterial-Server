package cn.superion.materialDept.list.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 入库单据列表服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IReceiveList {
	/**
	 * 根据查找条件，获取当前符合条件的入库单据列表,分页查询
	 * 
	 * @param fparameter
	 *            参数包括
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>billNo 入库单号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            <li>supplyDeptCode 供应单位</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginTradePrice 起始进价</li>
	 *            <li>endTradePrice 结束进价</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>beginAvailDate 起始有效日期</li>
	 *            <li>endAvailDate 结束有效日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialRdsDept>}
	 */
	ReObject findReceiveDetailListByCondition(ParameterObject fparameter);
}
