package cn.superion.material.purchase.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialInvoiceDetail;
import cn.superion.material.entity.MaterialInvoiceMaster;

/**
 * 采购发票服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IInvoice {
	/**
	 * 查询采购发票信息
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>invoiceType 发票类型</li>
	 *            <li>beginInvoiceNo 起始发票号</li>
	 *            <li>endInvoiceNo 结束发票号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginInvoiceDate 起始发票日期</li>
	 *            <li>endInvoiceDate 结束发票日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginTradePrice 最小进价</li>
	 *            <li>endTradePrice 最大进价</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * 
	 * @return 返回主记录ID列表
	 */
	ReObject findMaterialInvoiceByCondition(ParameterObject fparameter);

	/**
	 * 查询采购发票明细列表
	 * 
	 * @param fstrAutoId
	 * @return 返回MaterialInvoiceMaster,{@code List<MaterialInvoiceDetail>}
	 */
	ReObject findMaterialInvoiceDetail(String fstrAutoId);

	/**
	 * 保存采购发票信息，并更新入库明细表中所对应的已开票数及开票标志
	 * 
	 * @param master
	 *            主记录
	 * @param details
	 *            明细记录列表
	 * @return
	 */
	ReObject saveMaterialInvoice(MaterialInvoiceMaster master,
			List<MaterialInvoiceDetail> details);

	/**
	 * 删除未审核的发票，先还原入库明细表中所对应的已开票数及开票标志
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return
	 */
	ReObject delMaterialInvoice(String fstrAutoId);

	/**
	 * 审核发票
	 * 
	 * @param fstrAutoId
	 * @return 返回MaterialInvoice
	 */
	ReObject verifyMaterialInvoice(String fstrAutoId);

	/**
	 * 查询未开票的入库明细 ,隐藏条件：已开票标志='0'
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>billNo 入库单号</li>
	 *            <li>salerCode 供应商</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            </ul>
	 * 
	 * @return 返回{@code List<VMaterialRds>}
	 */
	ReObject findRdsInvoice(ParameterObject fparameter);
}
