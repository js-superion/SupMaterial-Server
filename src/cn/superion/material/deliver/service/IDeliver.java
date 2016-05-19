package cn.superion.material.deliver.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;

/**
 * 物资领用出库服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IDeliver {
	/**
	 * 根据查找条件，获取当前符合条件的出库单据列表
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
	 * 			  </ul>	
	 * @return 返出库单主记录ID列表
	 */
	ReObject findRdsMasterListByCondition(ParameterObject fparameter);
	ReObject updateProviderDetail(ParameterObject fparameter);
	ReObject findProvideByCondition(ParameterObject fparameter);
	/**
	 * 查看当前出库单的详细信息记录,含科室领用单明细记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回MaterialRdsMaster,{@code List<MaterialRdsDetail>},{@code
	 *         List<MaterialProvideDetail>}
	 */
	ReObject findRdsDetailById(String fstrAutoId);

	/**
	 * 保存改写当前科室领用申请单据,如为自动发放，需更新科室领用单主记录状态和明细记录已发数量，
	 * 支持手工写蓝字单据和红字单据，也支持过滤科室领用申请单，若为蓝字，则为自动出库；若为红字，则为科室退货入库；
	 * 如过滤科室领用申请单，则业务号不为空，主记录的sourceAutoId记录来源主记录的autoId;明细记录的sourceAutoId记录来源单据明细记录autoId
	 * 
	 * @param master
	 *            出库主记录
	 * @param details
	 *            出库明细记录列表，如为自动发放，此参数仅包含科室领用单明细记录信息，如autoId,mainAutoId，非实际出库明细记录;
	 *            科室退货入库，即红字单据时明细记录的数量和金额必须是负数
	 * @return MaterialRdsMaster, {@code List<MaterialRdsDetail>}
	 */
	ReObject saveRds(MaterialRdsMaster master, List<MaterialRdsDetail> details);

	/**
	 * 删除当前未审核的出库单据,如为自动发放，需还原新科室领用单主记录状态和明细记录已发数量
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return
	 */
	ReObject deleteRds(String fstrAutoId);

	/**
	 * 审核当前的出库单信息
	 * 
	 * @param fstrAutoId
	 * @return 返回MaterialRdsMaster
	 */
	ReObject verifyRds(String fstrAutoId);
	
	/**
	 * 弃审
	 * @param fstrAutoId 收发存主记录ID
	 * @return
	 */
	ReObject cancelVerifyRds(String fstrAutoId);

	/**
	 * 过滤科室领用领申请单据,分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 当前仓库</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据编号</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>billNo 领用单号</li>
	 *            </ul>
	 * @return {@code List<MaterialProvideMaster>}
	 */
	ReObject findProvide(ParameterObject fparameter);

	/**
	 * 查找当前科室领用领申请明细单
	 * 
	 * @param fstrMainAutoId
	 *            科室领用单主记录ID
	 * @return {@code List<MaterialProvideDetail>}
	 */
	ReObject findProvideDetailByMainAutoId(String fstrMainAutoId);
	
	/**
	 * 根据当前出库数量系统参数先进先出自动分配当前出库数量的数据列表
	 * @param fstrMaterialId 物资ID
	 * @param fstrStorageCode 仓库编码
	 * @param amount 申请批准数量
	 * @return 返回{@code List<MaterialRdsDetail>}
	 */
	ReObject findCurrentStockByIdStorageAmount(String fstrMaterialId,String fstrStorageCode,Double amount);


	/**
	 * 一般高值耗材：根据条形码查询物资库存信息
	 * @param fstrBarCode
	 * @return
	 */
	ReObject findMaterialDetailByBarCode(String fstrBarCode);
	
	
	/**
	 * 库存表中是否存在该条形码
	 * @param fstrBarCode
	 * @return
	 */
	ReObject findMaterialStockByBarCode(String fstrBarCode);
	/**
	 * 更新打印状态
	 * @param fstrAutoId
	 * @return
	 */
	ReObject updateRdsPrintSign(String fstrAutoId);
}
