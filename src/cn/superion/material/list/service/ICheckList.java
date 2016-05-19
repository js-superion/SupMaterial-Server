package cn.superion.material.list.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 盘点单据列表服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ICheckList {
	/**
	 * 根据查找条件，获取当前符合条件的盘点单据列表,分页查询
	 * 
	 * @param fparameter
	 *            参数包含
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始盘点单号</li>
	 *            <li>endBillNo 结束盘点单号</li>
	 *            <li>beginBillDate 起始盘点日期</li>
	 *            <li>endBillDate 结束盘点日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 经手人</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialCheck>}
	 */
	ReObject findCheckDetailListByCondition(ParameterObject fparameter);
}
