package cn.superion.materialDept.other.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.entity.MaterialRejectDetailDept;
import cn.superion.materialDept.entity.MaterialRejectMasterDept;

/**
 * 物资报损服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IReject {
	/**
	 * 根据查找条件，获取当前符合条件的物资报损单据列表
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>beginBillDate 起始报损日期</li>
	 *            <li>endBillDate 结束报损日期</li>
	 *            <li>beginBillNo 起始报损单号</li>
	 *            <li>endBillNo 结束报损单号</li>
	 *            <li>outDeptCode 报损部门</li>
	 *            <li>personId 经手人</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回记录表中的ID
	 */
	ReObject findRejectMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前物资报损单的详细信息记录
	 * @param fstrAutoId 主记录ID
	 * @return 返回MaterialRejectMasterDept,{@code List<MaterialRejectDetailDept>}
	 */
	ReObject findRejectDetailById(String fstrAutoId);
	
	/**
	 * 保存当前物资报损单信息
	 * @param master 物资报损主记录
	 * @param details 物资报损明细记录列表
	 * @return 返回MaterialRejectMasterDept,{@code List<MaterialRejectDetailDept>}
	 */
	ReObject saveReject(MaterialRejectMasterDept master,List<MaterialRejectDetailDept> details);
	
	/**
	 * 删除当前未审核的物资报损单
	 * @param fstrAutoId 主记录ID
	 * @return
	 */
	ReObject deleteReject(String fstrAutoId);
	
	/**
	 * 审核当前的物资报损单，并写出库主记录，出库明细记录
	 * @param fstrAutoId 主记录ID
	 * @return 返回MaterialRejectMasterDept
	 */
	ReObject verifyReject(String fstrAutoId);
	
	/**
	 * 根据报损单主记录ID写报损出库红单（报损单已审核并且报损出库单已审核--已更新库存量）
	 * 或删除报损单(报损单未审核,以及报损单已审核，但报损出库单还未审核)--报损单还没有单据类型字段
	 * @param fstrAutoId
	 * @return
	 */
	Object[] writeRejectDeliverRed(String fstrAutoId);
}
