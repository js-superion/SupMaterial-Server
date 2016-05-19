package cn.superion.cssd.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 人员工作量查询服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IPersonWorkload {
	/**
	 * 根据查找条件，获取当前符合条件的人员工作列表,分页查询
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>personId 工作人员</li>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginPackageNo 起始物品包编号</li>
	 *            <li>endPackageNo 结束物品包编号</li>
	 *            </ul>
	 * @return {@code List<PersonWorkload>}
	 */
	ReObject findListByCondition(ParameterObject fparameter);
}
