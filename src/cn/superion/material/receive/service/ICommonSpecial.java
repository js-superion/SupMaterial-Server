package cn.superion.material.receive.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;

public interface ICommonSpecial {

	/**
	 * 一般耗材入库业务，根据查找条件，获取当前符合条件的 一般耗材单据列表
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>operationType 业务类型,期初入库业务时非空</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>beginBillNo 起始入库单号</li>
	 *            <li>endBillNo 结束入库单号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            <li>beginOrderNo 起始订单号</li>
	 *            <li>endOrderNo 结束订单号</li>
	 *            <li>beginArrivalNo 结束到货号</li>
	 *            <li>endArrivalNo 结束到货号</li>
	 *            <li>operationNo 业务号</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回主记录ID列表
	 */
	ReObject findRdsMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前 一般耗材入库单的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回MaterialRdsMaster,{@code List<MaterialRdsDetail>}
	 */
	ReObject findRdsDetailById(String fstrAutoId);

	/**
	 * 保存当前 一般耗材入库信息,反写采购订单已入库数和当前状态或特殊入库状态，手工填写的入库单据，需校验供应商授权物资
	 * 
	 * @param master
	 *            收发存主记录
	 * @param details
	 *            收发存明细记录列表
	 * @return 返回MaterialRdsMaster,{@code List<MaterialRdsDetail>}
	 */
	ReObject saveRds(MaterialRdsMaster master, List<MaterialRdsDetail> details);
	
	/**
	 * 删除当前未审核的入库单据,采购业务时先还原采购订单已入库数和当前状态或特殊入库状态
	 * 
	 * @param fstrAutoId
	 *            收发存主记录ID
	 * @return
	 */
	ReObject deleteRds(String fstrAutoId);

	/**
	 * 审核当前的入库单信息,改写当前现存量表
	 * 
	 * @param fstrAutoId
	 *            收发存主记录ID
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
	 * 根据主记录ID查询收发存明细
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回{@code List<MaterialRdsDetail>}
	 */
	ReObject findRdsDetailByMainAutoId(String fstrAutoId);

	/**
	 *  一般耗材入库业务，过滤查询已审核或已执行的采购订单,分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>beginBillDate 起始订单日期</li>
	 *            <li>endBillDate 结束订单日期</li>
	 *            <li>billNo 订单号</li>
	 *            </ul>
	 * 
	 * @return 返回{@code List<MaterialOrderMaster>}
	 */
	ReObject findOrderMasterList(ParameterObject fparameter);
	
	/**
	 * 根据订单主记录ID查询订单明细列表
	 * @param fstrAutoId 订单主记录ID 
	 * @return 返回{@code List<MaterialOrderDetail>}
	 */
	ReObject findOrderDetailList(String fstrAutoId);
	
	/**
	 * 采购入库业务，过滤查询已审核的特殊入库申请单,分页查询
	 * @param fparameter 参数包含：
	 *            <ul>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>beginBillDate 起始申请日期</li>
	 *            <li>endBillDate 结束申请日期</li>
	 *            <li>billNo 申请单号</li>
	 *            </ul>
	 * @return 返回{@code List<MaterialApplyMaster>}
	 */
	ReObject findApplyMasterList(ParameterObject fparameter);
	
	/**
	 * 根据特殊入库申请主记录ID查询申请明细列表
	 * @param fstrAutoId 特殊入库申请主记录ID
	 * @return 返回{@code List<MaterialApplyDetail>}
	 */
	ReObject findApplyDetailList(String fstrAutoId);
	
	/**
	 *  一般耗材入库业务，手工入库时校验物资授权
	 * @param fparameter 参数包含：
	 *            <ul>
	 *            <li>materialId 物资ID</li>
	 *            <li>salerCode 供应商编码</li>
	 *            </ul>
	 * @return 返回Boolean,true:有授权;false:无授权
	 */
	ReObject findCheckSaleProduct(ParameterObject fparameter);
}
