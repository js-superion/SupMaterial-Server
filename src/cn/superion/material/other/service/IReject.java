package cn.superion.material.other.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialRejectDetail;
import cn.superion.material.entity.MaterialRejectMaster;

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
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillDate 起始报损日期</li>
	 *            <li>endBillDate 结束报损日期</li>
	 *            <li>beginBillNo 起始报损单号</li>
	 *            <li>endBillNo 结束报损单号</li>
	 *            <li>deptCode 部门</li>
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
	 * @return 返回MaterialRejectMaster,{@code List<MaterialRejectDetail>}
	 */
	ReObject findRejectDetailById(String fstrAutoId);
	
	/**
	 * 保存当前物资报损单信息
	 * @param master 物资报损主记录
	 * @param details 物资报损明细记录列表
	 * @return 返回MaterialRejectMaster,{@code List<MaterialRejectDetail>}
	 */
	ReObject saveReject(MaterialRejectMaster master,List<MaterialRejectDetail> details);
	
	/**
	 * 删除当前未审核的物资报损单
	 * @param fstrAutoId 主记录ID
	 * @return
	 */
	ReObject deleteReject(String fstrAutoId);
	
	/**
	 * 审核当前的物资报损单，并写出库主记录，出库明细记录
	 * @param fstrAutoId 主记录ID
	 * @return 返回MaterialRejectMaster
	 */
	ReObject verifyReject(String fstrAutoId);
}
