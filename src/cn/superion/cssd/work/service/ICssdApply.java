package cn.superion.cssd.work.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdProvideDetail;
import cn.superion.cssd.entity.CssdProvideMaster;

/**
 * 物资领用服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ICssdApply {
	/**
	 * 根据查找条件，获取当前符合条件的物资领用申请列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
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
	 * @return CssdProvideMaster,{@code List<CssdProvideDetail>}
	 */
	ReObject findApplyDetailById(String fstrAutoId);

	/**
	 * 保存当前物资领用申请信息
	 * 
	 * @param fmaster
	 *            领用申请主记录
	 * @param fdetails
	 *            领用申请明细记录列表
	 * @return CssdProvideMaster,{@code List<CssdProvideDetail>}
	 */
	ReObject saveApply(CssdProvideMaster fmaster,
			List<CssdProvideDetail> fdetails);

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
	ReObject verifyApply(String fstrAutoId);

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
	ReObject findApplyListByCondition(ParameterObject fparameter);


	/**
	 * 根据领用出库单主记录ID确认当前的物资领用入科信息,蓝字单据和红字单据都要更新领用卡帐户余额，蓝字单据还需要写物资领用入科
	 * 
	 * @param fstrAutoId
	 * @return 物资领用卡帐户余额,MaterialRdsMasterDept,{@code List<MaterialRdsDetailDept>}
	 */
//	ReObject verifyDeptReceive(String fstrAutoId);
	
	/**
	 * 供应室不希望出现红字单据，该方法对审核的单据进行弃审，改变当前状态为新建
	 * @param fstrAutoId CSSD_PROVIDE_MASTER中的autoId
	 * @return
	 */
	ReObject cancelVerifyApply(String fstrAutoId);
}
