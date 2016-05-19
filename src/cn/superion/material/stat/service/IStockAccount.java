package cn.superion.material.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 库存台帐服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IStockAccount {
	/**
	 * 查询库存物资ID列表，只能是已审核单据的物资
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>billNo 单据号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 经手人</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            </ul>
	 * @return 返回物资ID列表
	 */
	ReObject findStockAccountListByCondition(ParameterObject fparameter);
	
	/**
	 * 根据查找条件，查询仓库指定物资的收发存明细
	 * @param fstrMaterialId 物资ID
	 * @param fparameter 参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>billNo 单据号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 经手人</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialRds>}
	 */
	ReObject findMaterialStockAccountListByCondition(String fstrMaterialId,ParameterObject fparameter);
}
