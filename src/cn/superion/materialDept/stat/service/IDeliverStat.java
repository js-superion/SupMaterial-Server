package cn.superion.materialDept.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
/**
 * 出库汇总服务接口
 * @author 曹国魁
 *
 */
public interface IDeliverStat {
	/**
	 * 根据条件，按物资类别，物资编码，物资规格等汇总出库明细，客户端按部门、物资分类、物资编码作为分组汇总列
	 * @param fparameter 参数包含：
	 *            <ul>
	 *            <li>beginBillDate 起始日期</li>
	 *            <li>endBillDate 结束日期</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return 返回{@code List<StockStatistic>}
	 */
	ReObject findDeliverStatListByCondition(ParameterObject fparameter);
}
