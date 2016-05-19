package cn.superion.material.deliver.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;

/**
 * 其他出库服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IOtherDeliver {
	/**
	 * 根据查找条件，获取当前符合条件的其他出库单据列表
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>beginBillDate 起始出库日期</li>
	 *            <li>endBillDate 结束出库日期</li>
	 *            <li>storageCode 仓库</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>rdType 出库类别</li>
	 *            <li>billNo 出库单号</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回主记录表中的ID
	 */
	ReObject findOtherMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前其他出库单的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回MaterialRdsMaster,{@code List<MaterialRdsDetail>}
	 */
	ReObject findOtherDetailById(String fstrAutoId);

	/**
	 * 保存当前其他出库单信息
	 * 
	 * @param master
	 *            其他出库主记录
	 * @param details
	 *            其他出库明细记录列表
	 * @return 返回MaterialRdsMaster,{@code List<MaterialRdsDetail>}
	 */
	ReObject saveRdsOther(MaterialRdsMaster master,
			List<MaterialRdsDetail> details);

	/**
	 * 审核当前的其他出库单信息
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回MaterialRdsMaster
	 */
	ReObject verifyRdsOther(String fstrAutoId);
	
	/**
	 * 弃审
	 * @param fstrAutoId 收发存主记录ID
	 * @return
	 */
	ReObject cancelVerifyRds(String fstrAutoId);

	/**
	 * 删除当前未审核的其他出库单据
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return
	 */
	ReObject deleteRdsOther(String fstrAutoId);

	/**
	 * 过滤其他未审核的出库单据，（业务类型在203,204,205范围里）,分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>operationNo 单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>operationType 业务类型</li>
	 *            </ul>
	 * @return {@code List<MaterialRdsMaster>}
	 */
	ReObject findRdsMasterOtherByCondition(ParameterObject fparameter);
	
	/**
	 * 查看当前其他出库单的详细信息记录
	 * @param fstrAutoId 主记录ID
	 * @return {@code List<MaterialRdsDetail>}
	 */
	ReObject findRdsDetailByMainAutoId(String fstrAutoId);
	/**
	 * 更新打印状态
	 * @param fstrAutoId
	 * @return
	 */
	ReObject updateRdsPrintSign(String fstrAutoId);
}
