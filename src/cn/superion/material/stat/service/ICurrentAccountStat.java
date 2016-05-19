package cn.superion.material.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 流水帐查询服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ICurrentAccountStat {
	/**
	 * 根据查找条件，查询一个时间段内物资的出入库情况
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 收发类别</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            <li>maker 制单人</li>
	 *            <li>verifier 审核人</li>
	 *            </ul>
	 * @return 返回{@code List<VMaterialRds>}
	 */
	ReObject findCurrentAccountListByCondition(ParameterObject fparameter);
}
