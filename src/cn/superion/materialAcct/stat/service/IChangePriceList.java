/**
 * 
 */
package cn.superion.materialAcct.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 调价报告单服务接口
 * 
 * @author 李攀
 * 
 */
public interface IChangePriceList {

	/**
	 * 根据条件查询调价报告单列表，分页
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始调价单号</li>
	 *            <li>endBillNo 结束调价单号</li>
	 *            <li>beginBillDate 单据起始日期</li>
	 *            <li>endBillDate 单据起始日期</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            调价明细表
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return {@code List<MaterialChangePriceMaster>}
	 */
	ReObject findChangeListByCondition(ParameterObject fparameter);

	/**
	 * 根据调价单号查询调价明细列表
	 * 
	 * @param fstrBillNo
	 * @return {@code List<MaterialChangePriceDetail>}
	 */
	ReObject findByChangeBillNo(String fstrBillNo);
	
	/**
	 * 查询符合条件的
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>salerCode 调价单位（供货单位）</li>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>beginBillDate 单据起始日期</li>
	 *            <li>endBillDate 单据结束日期</li>
	 * @return {@code List<Object>}
	 */
	ReObject findChangePriceListByCondition(ParameterObject para);

}
