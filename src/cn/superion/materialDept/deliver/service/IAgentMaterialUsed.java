package cn.superion.materialDept.deliver.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.MaterialPatsMaster;

/**
 * 代销物资使用服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IAgentMaterialUsed {
	/**
	 * 根据查找条件，获取当前符合条件的代销物资领用申请单列表， 客户端申请或确认模块查询时调用，确认模块输入住院号时查询
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginApplyDate 起始申请日期</li>
	 *            <li>endApplyDate 结束申请日期</li>
	 *            <li>patientId 病人标识号</li>
	 *            <li>inpNo 住院号</li>
	 *            <li>personName 病人姓名</li>
	 *            <li>deptCode 所在科室</li>
	 *            <li>wardCode 所在病区</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 主记录ID列表
	 */
	ReObject findApplyMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前代销物资领用申请单的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return MaterialPatsMaster,{@code List<MaterialPatsDetail>},预交金余额
	 */
	ReObject findApplyDetailById(String fstrAutoId);

	/**
	 * 保存代销物资领用申请单据
	 * 
	 * @param master
	 *            病人使用材料主记录
	 * @param details
	 *            病人使用材料明细记录列表
	 * @return MaterialPatsMaster,{@code List<MaterialPatsDetail>}
	 */
	ReObject saveApply(MaterialPatsMaster master,
			List<MaterialPatsDetail> details);

	/**
	 * 删除当前未审核的代销物资领用申请单
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return
	 */
	ReObject deleteApply(String fstrAutoId);

	/**
	 * 确认病人使用代销物资信息;扣减虚拟库存数量，并写一张采购入库单和一张科室领用出库单，并写病人费用，如有必要，需写HIS病人费用
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @param details
	 *            病人使用材料明细记录列表
	 * @return 预交金余额,MaterialPatsMaster
	 */
	ReObject verify(String fstrAutoId, List<MaterialPatsDetail> details);

	/**
	 * 弃审当前已审核过的病人使用代销物资信息,校验HIS费用未结算，如未结算，写HIS费用红单，且清空费用相关信息,并增加虚拟库存数量，
	 * 写一张采购入库单红单和科室领用出库单红单
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 预交金余额
	 */
	ReObject cancelVerify(String fstrAutoId);

	/**
	 * 根据条件，查询已审核的病人使用代销物资记录，当前状态为1
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>wardCode 所在病区</li>
	 *            <li>inpNo 住院号</li>
	 *            <li>deptCode 所在科室</li>
	 *            <li>beginVerifyDate 起始确认日期</li>
	 *            <li>endVerifyDate 结束确认日期</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return {@code List<MaterialPatsMaster>}
	 */
	ReObject findVerifiedListByCondition(ParameterObject fparameter);

	/**
	 * 根据病人使用代销物资记录autoId,查询代销物资明细列表
	 * 
	 * @param fstrAutoId
	 * @return {@code List<MaterialPatsDetail>}
	 */
	ReObject findDetailById(String fstrAutoId);

	/**
	 * 汇总病人使用代销材料记录，生成一张已审核的采购入库单据和一张已审核的领用出库单据
	 * 
	 * @param fstrAutoIds
	 *            病人使用代销材料记录autoId
	 * @return 入库单主记录MaterialRdsMaster,入库明细列表{@code List<MaterialRdsDetail>},出库单主记录MaterialRdsMaster,出库明细列表{@code
	 *         List<MaterialRdsDetail>}
	 */
	ReObject saveRDBill(String[] fstrAutoIds);

	/**
	 * 根据病人标识号或住院号查询病人基本信息, 客户端申请模块输入住院号时调用，病人费用记帐模块输入住院号时调用
	 * 
	 * @param fstrPatientId
	 *            病人标识号或住院号
	 * @return 病人信息PatsVisit
	 */
	ReObject findByPatientId(String fstrPatientId);

}
