
package cn.superion.equipment.spare.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 备件需求统计接口服务
 * @author 李攀
 * @author 曹国魁
 *
 */
public interface IEqSpareRequire {
	

	/**
	 *  根据条件查询备件需求统计记录
	 *  @param fparameter 包含：
	 *            <ul>
	 *            <li>beginRequireFinishDate 起始需求日期 </li>
	 *            <li>endRequireFinishDate 结束需求日期</li>
	 *            <li>beginSparePartCode 起始备件编码</li>
	 *            <li>endSparePartCode 结束备件编码</li>
	 *            </ul>
	 *  
	 *  @return  配件编码、配件名称、规格、制造商、数量、金额
	 */

	ReObject findByCondition(ParameterObject fparameter);

	

}
