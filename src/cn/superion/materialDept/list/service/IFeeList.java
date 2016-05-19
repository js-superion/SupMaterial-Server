package cn.superion.materialDept.list.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 病人费用列表服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IFeeList {
	/**
	 * 根据查找条件，获取当前符合条件的病人费用单据列表,分页查询
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>patientId 病人标识号</li>
	 *            <li>inpNo 住院号</li>
	 *            <li>personName 病人姓名</li>
	 *            <li>deptCode 所在科室</li>
	 *            <li>beginAccountDate 起始记帐日期</li>
	 *            <li>endAccountDate 结束记帐日期</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginRetailPrice 起始单价</li>
	 *            <li>endRetailPrice 结束单价</li>
	 *            <li>accounter 记账人</li>
	 *            </ul>
	 * @return {@code List<VMaterialPats>}
	 */
	ReObject findFeeDetailListByCondition(ParameterObject fparameter);
}
