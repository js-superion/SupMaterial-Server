package cn.superion.material.purchase.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialPlanDetail;
import cn.superion.material.entity.MaterialPlanMaster;

/**
 * 采购计划服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IPlan {
	/**
	 * 根据查找条件，获取当前符合条件的计划单据列表
	 * 
	 * @param fparameter
	 *            包含条件：
	 *            <ul>
	 *            <li>dataSource 标示计划或请购</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo起始单据编号</li>
	 *            <li>endBillNo结束单据编号</li>
	 *            <li>beginBillDate起始单据日期</li>
	 *            <li>endBillDate结束单据日期</li>
	 *            <li>beginAdviceBookDate起始订货日期</li>
	 *            <li>endAdviceBookDate结束订货日期</li>
	 *            <li>beginMaterialCode起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>salerCode供应商</li>
	 *            <li>currentStatus当前状态</li>
	 *            </ul>
	 * @return 主记录ID列表
	 */
	ReObject findMaterialPlanMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前采购计划编制单的详细信息记录
	 * 
	 * @param fstrAutoId
	 * @return 返回MaterialPlanMaster,{@code List<MaterialPlanDetail>}
	 */
	ReObject findMaterialPlanDetailById(String fstrAutoId);

	/**
	 * 自动编制采购计划
	 * 
	 * 
	 * @param fparameter 参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库编号</li>
	 *            <li>beginYearMonth 起始月份yyyy-MM</li>
	 *            <li>endYearMonth 结束月份yyyy-MM</li>
	 *            <li>rate 比例系数</li>
	 *            <li>materialClass 物资类别编码（可为空）</li>
	 *            <li>materialCode 物资编码（可为空）</li>
	 *            </ul>
	 * @return 返回{@code List<MaterialPlanDetail>}
	 */
	ReObject autoBuildMaterialPlan(ParameterObject fparameter);

	/**
	 * 保存当前采购计划信息
	 * 
	 * @param fmaster
	 *            采购计划主记录 属性数据来源非空
	 * @param fdetails
	 *            采购计划明细记录列表
	 * @return 返回MaterialPlanMaster,{@code List<MaterialPlanDetail>}
	 */
	ReObject saveMaterialPlan(MaterialPlanMaster fmaster,
			List<MaterialPlanDetail> fdetails);

	/**
	 * 删除当前未审核的采购计划单据
	 * 
	 * @param fstrAutoId
	 *            采购计划主记录ID
	 * @return
	 */
	ReObject deleteMaterialPlan(String fstrAutoId);

	/**
	 * 审核当前的采购计划编制信息
	 * 
	 * @param fstrAutoId
	 *            采购计划主记录ID
	 * @return 返回MaterialPlanMaster
	 */
	ReObject verifyMaterialPlan(String fstrAutoId);
	/**
	 * 查询采购计划----汇总表
	 * @param fparameter
	 * @return
	 */
	ReObject findMaterialPlanDetailListByCondition(ParameterObject fparameter);
}
