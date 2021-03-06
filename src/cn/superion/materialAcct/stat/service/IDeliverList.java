package cn.superion.materialAcct.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 出库报告单服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IDeliverList {
	/**
	 * 根据条件查询出库报告单列表，分页
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>deptCode 部门</li>
	 *            <li>beginBillDate1 单据起始日期1</li>
	 *            <li>endBillDate1 单据起始日期2</li>
	 *            <li>beginBillDate2 单据终止日期1</li>
	 *            <li>endBillDate2 单据终止日期2</li>
	 *            <li>accounter 记账人</li>
	 *            <li>beginAccountDate 起始记账日期</li>
	 *            <li>endAccountDate 结束记账日期</li>
	 *            </ul>
	 * @return {@code List<MaterialRdsAcctMaster>}
	 */
	ReObject findDeliverListByCondition(ParameterObject fparameter);
	
	/**
	 * 根据核算单号查询核算明细列表
	 * @param fstrAcctBillNo
	 * @return {@code List<MaterialRdsDetail>}
	 */
	ReObject findByAcctBillNo(String fstrAcctBillNo);
}
