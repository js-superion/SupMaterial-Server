package cn.superion.material.list.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 采购发票列表服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IInvoiceList {
	/**
	 * 根据查找条件，获取当前符合条件的采购发票单据列表,分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>beginInvoiceNo 起始发票号</li>
	 *            <li>endInvoiceNo 结束发票号</li>
	 *            <li>beginInvoiceDate 起始发票日期</li>
	 *            <li>endInvoiceDate 结束发票日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginTradePrice 起始进价范围</li>
	 *            <li>endTradePrice 结束进价范围</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialInvoice>}
	 */
	ReObject findInvoiceDetailListByCondition(ParameterObject fparameter);
}
