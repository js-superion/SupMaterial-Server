package cn.superion.material.receive.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialApplyDetail;
import cn.superion.material.entity.MaterialApplyMaster;

/**
 * 特殊入库申请服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IApply {
	/**
	 * 根据查找条件，获取特殊入库单据信息
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始申请单号</li>
	 *            <li>endBillNo 结束申请单号</li>
	 *            <li>beginBillDate 起始申请日期</li>
	 *            <li>endBillDate 结束申请日期</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            </ul>
	 * @return 返回主记录ID列表
	 */
	ReObject findApplyListByCondition(ParameterObject fparameter);

	/**
	 * 查看特殊入库单据详细信息记录
	 * @param fstrAutoId
	 *            物殊入库主记录ID
	 * @return 返回MaterialApplyMaster,{@code List<MaterialApplyDetail>}
	 */
	ReObject findApplyDetailById(String fstrAutoId);
	
	/**
	 * 保存新增或修改后的特殊入库信息,无系统参数控制是否自动审核
	 * @param master 物殊主记录 
	 * @param details 物殊明细记录
	 * @return 返回MaterialApplyMaster,{@code List<MaterialApplyDetail>}
	 */
	ReObject saveApply(MaterialApplyMaster master,List<MaterialApplyDetail> details);
	
	/**
	 * 删除当前未审核的特殊入库单据
	 * @param fstrAutoId 主记录ID
	 * @return
	 */
	ReObject deleteApply(String fstrAutoId);
	
	/**
	 * 审核当前的特殊入库信息
	 * @param fstrAutoId 主记录ID
	 * @return 返回MaterialApplyMaster
	 */
	ReObject verifyApply(String fstrAutoId);
}
