package cn.superion.materialDept.receive.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;

/**
 * 其他入库服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IOtherReceive {
	/**
	 * 根据查找条件，获取当前符合条件的入库单据列表
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>beginBillNo 起始入库单号</li>
	 *            <li>endBillNo 结束入库单号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            <li>beginTradePrice 起始进价</li>
	 *            <li>endTradePrice 结束进价</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回入库主记录ID列表
	 */
	ReObject findRdsMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前其他入库单的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return MaterialRdsMasterDept,{@code List<MaterialRdsDetailDept>}
	 */
	ReObject findRdsDetailById(String fstrAutoId);

	/**
	 * 删除当前未审核的手工编制的其他入库单据
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return
	 */
	ReObject deleteRds(String fstrAutoId);

	/**
	 * 过滤其他入库生单，如已审核的盘盈入库单，分页查询
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>operationNo 单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            </ul>
	 * @return {@code List<MaterialRdsMasterDept>}
	 */
	ReObject findRdsMasterByCondition(ParameterObject fparameter);

	/**
	 * 查看当前其他入库单的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return {@code List<MaterialRdsDetailDept>}
	 */
	ReObject findRdsDetailByMainAutoId(String fstrAutoId);

	/**
	 * 保存当前其他入库信息；根据系统参数决定是否改写现存量表
	 * 
	 * @param master
	 *            收发存主记录
	 * @param details
	 *            收发存明细记录列表
	 * @return MaterialRdsMasterDept,{@code List<MaterialRdsDetailDept>}
	 */
	ReObject saveOtherRds(MaterialRdsMasterDept master,
			List<MaterialRdsDetailDept> details);

	/**
	 * 保存当前期初入库信息；根据系统参数决定是否改写现存量表
	 * 
	 * @param master
	 *            收发存主记录
	 * @param details
	 *            收发存明细记录列表
	 * @return MaterialRdsMasterDept,{@code List<MaterialRdsDetailDept>}
	 */
	ReObject saveInitialRds(MaterialRdsMasterDept master,
			List<MaterialRdsDetailDept> details);

	/**
	 * 根据查找条件，获取当前符合条件的期初入库单据列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始入库单号</li>
	 *            <li>endBillNo 结束入库单号</li>
	 *            <li>beginBillDate 起始入库日期</li>
	 *            <li>endBillDate 结束入库日期</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>agentSign 是否代销</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 经手人</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 主记录ID列表
	 */
	ReObject findRdsMasterInitiallList(ParameterObject fparameter);

	/**
	 * 审核入库单据，包含其他入库和期初入库
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return MaterialRdsMasterDept
	 */
	ReObject verifyRds(String fstrAutoId);
}
