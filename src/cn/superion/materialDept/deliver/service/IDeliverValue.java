package cn.superion.materialDept.deliver.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.MaterialPatsMaster;

/**
 * 高值耗材出库服务接口
 * 
 * @author 芮玉红
 * 
 */
public interface IDeliverValue {

	/**
	 * 根据查找条件，获取当前符合条件的记账单列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginMakeDate 起始单据日期</li>
	 *            <li>endMakeDate 结束单据日期</li>
	 *            <li>patientId 病人标识号</li>
	 *            <li>inpNo 住院号</li>
	 *            <li>personName 病人姓名</li>
	 *            <li>deptCode 所在科室</li>
	 *            <li>wardCode 所在病区</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 记账单主记录ID列表
	 */
	ReObject findPatsFeeMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前记账单的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return MaterialPatsMaster,{@code List<MaterialPatsDetail>},预交金余额
	 */
	ReObject findPatsFeeDetailById(String fstrAutoId,String fstrRefundSign);

	/**
	 * 保存记账单据
	 * 
	 * @param master
	 *            病人使用材料主记录
	 * @param details
	 *            病人使用材料明细记录列表
	 * @return MaterialPatsMaster,{@code List<MaterialPatsDetail>}
	 */
	ReObject savePatsFee(MaterialPatsMaster master,
			List<MaterialPatsDetail> details);

	/**
	 * 删除当前未审核的记账单据
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return
	 */
	ReObject deletePatsFee(String fstrAutoId);

	/**
	 * 审核当前的账单信息，写HIS病人费用，并写出库主记录、出库明细记录，改写现存量
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 预交金余额,MaterialPatsMaster
	 */
	ReObject verify(String fstrAutoId,String[] fstrDetailAutoIds);

	/**
	 * 对已记帐的病人使用材料费用退费，更新退费标志为1，并写使用材料负数记录，以及HIS病人费用红单，并且写科室物资出库红单
	 * 
	 * @param fstrAutoId 病人标识号或住院号
	 * @param fstrAutoIds 费用明细记录ID数组
	 * @return 预交金余额
	 */
	ReObject refundFee(String fstrAutoId,String[] fstrAutoIds);

	/**
	 * 根据病人标识号或住院号查询病人基本信息和费用记帐列表， 客户端费用记帐作废模块输入住院号时调用
	 * 
	 * @param fstrPatientId 病人标识号或住院号 
	 * @return PatsVisit,{@code List<VMaterialPats>}
	 */
	ReObject findPatsFeeByPatientId(String fstrPatientId);
	
	/**
	 * 根据物资条形码查询物资明细
	 * @param fstrBarCode
	 * @return
	 */
	ReObject findMaterialDetailByBarCode(String fstrBarCode);
	
	/**
	 * 根据病人住院号查询病人在院信息
	 * @param fstrInpNo
	 * @return
	 */
	ReObject findPatsVisitByInpNo(String fstrInpNo);
}
