package cn.superion.material.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
/**
 * 出库汇总服务接口
 * @author 曹国魁
 *
 */
public interface IDeliverStat {
	/**
	 * 根据条件，按领用部门，物资类别，物资编码，物资规格等汇总出库明细，客户端按部门、物资分类、物资编码作为分组汇总列
	 * @param fparameter 参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillDate 起始日期</li>
	 *            <li>endBillDate 结束日期</li>
	 *            <li>deptCode 供应单位</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialRds>}
	 */
	ReObject findDeliverStatListByCondition(ParameterObject fparameter);
	/**
	 * 按部门分组汇总分类费用。
	 * @param fparameter
	 * @return
	 */
	ReObject findDeliverStatListByDept(ParameterObject fparameter);
	/**
	 * 只查部门
	 * @param fparameter
	 * @return
	 */
	ReObject findDeliverStatListByDeptNew(ParameterObject fparameter);
	/**
	 * 查询计算机仓库物资类别
	 * @param fparameter
	 * @return
	 */
	ReObject findDeliverStatListByDeptComputer(ParameterObject fparameter);
	/**
	 * 查询计算机分类费用
	 * @param fparameter
	 * @return
	 */
	ReObject findDeliverStatListByDeptComputerFee(ParameterObject fparameter);
	/**
	 * 查询医疗物资类别
	 * @param fparameter
	 * @return
	 */
	ReObject findDeliverStatListByDeptMedical(ParameterObject fparameter);
	/**
	 * 查询医疗物资分类费用
	 * @param fparameter
	 * @return
	 */
	ReObject findDeliverStatListByDeptMedicalFee(ParameterObject fparameter);
	/**
	 * 查询计价类别分类费用
	 * @param fparameter
	 * @return
	 */
	ReObject findDeliverCountClassListByDeptMedicalFee(ParameterObject fparameter);
	/**
	 * 查询计价类别入库分类费用
	 * @param fparameter
	 * @return
	 */
	ReObject findDeliverCountClassListByDeptMedicalReceiveFee(ParameterObject fparameter);
}
