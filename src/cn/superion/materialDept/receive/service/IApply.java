package cn.superion.materialDept.receive.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialProvideDetail;
import cn.superion.material.entity.MaterialProvideMaster;

/**
 * 物资领用服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IApply {
	/**
	 * 根据查找条件，获取当前符合条件的物资领用申请列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回主记录ID列表
	 */
	ReObject findApplyMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前物资领用申请单的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return MaterialProvideMaster,{@code List<MaterialProvideDetail>}
	 */
	ReObject findApplyDetailById(String fstrAutoId);

	/**
	 * 保存当前物资领用申请信息
	 * 
	 * @param fmaster
	 *            领用申请主记录
	 * @param fdetails
	 *            领用申请明细记录列表
	 * @return MaterialProvideMaster,{@code List<MaterialProvideDetail>}
	 */
	ReObject saveApply(MaterialProvideMaster fmaster,
			List<MaterialProvideDetail> fdetails);

	/**
	 * 删除当前未审核的物资领用申请单据
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject deleteApply(String fstrAutoId);

	/**
	 * 审核当前的物资领用申请信息
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject verifyApply(String fstrAutoId,String status);

	/**
	 * 过滤已审的领用出库单
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>salerCode 供应商</li>
	 *            </ul>
	 * @return 领用出库单主记录ID列表
	 */
	ReObject findDeliverListByCondition(ParameterObject fparameter);

	/**
	 * 根据领用出库单主记录ID查询领用物资明细列表和物资领用申请明细列表
	 * 
	 * @param fstrAutoId
	 * @return MaterialRdsMaster,{@code List<MaterialRdsDetail>},MaterialProvideMaster,{@code List<MaterialProvideDetail>}
	 */
	ReObject findDeliverDetail(String fstrAutoId);

	/**
	 * 根据领用出库单主记录ID确认当前的物资领用入科信息,蓝字单据和红字单据都要更新领用卡帐户余额，蓝字单据还需要写物资领用入科
	 * 
	 * @param fstrAutoId
	 * @return 物资领用卡帐户余额,MaterialRdsMasterDept,{@code List<MaterialRdsDetailDept>}
	 */
	ReObject verifyDeptReceive(String fstrAutoId);
	
	/**
	 * 根据卡号返回物资领用卡帐户余额
	 * @param fstrCardCode 领用卡号
	 * @return
	 */
	ReObject findAccountRemain(String fstrCardCode);
	
	/**
	 * 查询当前用户所属科室的物资领用卡列表
	 * @return {@code List<CdMaterialCard>}
	 */
	ReObject findCurrentMaterialCard();
	
	ReObject findDeptByStorageCode(String storageCode);
	
	/**
	 * 弃审
	 * @param fstrAutoId 领用申请主记录ID
	 * @return
	 * ryh 2013.1.7
	 */
	ReObject cancelApply(String fstrAutoId);
	
	/**
	 * 查询物资虚拟库存
	 * 
	 * @param storageCode
	 * @param materialId
	 * @return
	 */
	ReObject findVirtualAmountByMaterialId(String storageCode,String materialId);
}
