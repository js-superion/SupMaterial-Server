package cn.superion.material.list.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 出库单据列表服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IDeliverList {
	/**
	 * 根据查找条件，获取当前符合条件的出库单据列表,分页查询
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo 起始出库单号</li>
	 *            <li>endBillNo 结束出库单号</li>
	 *            <li>beginBillDate 起始出库日期</li>
	 *            <li>endBillDate 结束出库日期</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>personId 申请人</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialRds>}
	 */
	ReObject findDeliverDetailListByCondition(ParameterObject fparameter);
	
	public ReObject findData();
	
	public ReObject findData(String fstrGroupFields);
}
