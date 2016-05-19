package cn.superion.materialAcct.daily.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialRdsDetail;

/**
 * 出库报告单接口
 * 
 * @author 李攀 修改者：周作建 2011.06.12
 */
public interface IDeliver {

	/**
	 * 根据条件查询入库报告单明细记录
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>startDate 单据开始日期</li>
	 *            <li>endDate 单据结束日期</li>
	 *            </ul>
	 * @return {List<MaterialRdsDetail>}
	 * 
	 */
	ReObject findReportByCondition(ParameterObject fparameter);
	ReObject findReportByCondition1(ParameterObject fparameter);
	/**
	 * <p>
	 * 保存采购入库单
	 * </p>
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>storageCode 仓库编码 string</li>
	 *            <li>salerCode 供应商编码或部门编码 string</li>
	 *            <li>salerName 供应商名称或部门名称 string</li>
	 *            <li>salerSign 供应商标志 boolean</li>
	 *            <li>startDate 单据开始日期 date</li>
	 *            <li>endDate 单据结束日期 date</li>
	 *            <li>List<MaterialRdsDetail> detail</li>
	 *            </ul>
	 * @return 返回值说明ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"保存入库报告单"</li>
	 *         <li>data 值为{null}</li>
	 *         </ul>
	 * 
	 */

	ReObject saveReport(ParameterObject fparameter,
			List<MaterialRdsDetail> details);
	
	/**
	 * 保存特殊高值耗材的出库报告单
	 * 
	 * @param fparameter
	 * @param details
	 * @return
	 */
	ReObject saveReportValue(ParameterObject fparameter,
			List<MaterialRdsDetail> details);
}
