package cn.superion.equipment.spare.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqSparePartsDetail;
import cn.superion.equipment.entity.EqSparePartsMaster;

/**
 * 备件清单记录接口服务
 * 
 * @author 李攀
 * 
 * @author 周作建
 * @version 1.1
 * @date 2011-05-27
 */
public interface IEqSpareParts {
	/**
	 * <p>
	 * 保存设备备件主记录信息及明细信息
	 * </p>
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>EqSparePartsMaster master</li>
	 *            <li>List<EqSparePartsDetail> detail</li>
	 *            </ul>
	 * 
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"保存设备备件信息"</li>
	 *         <li>data 值为{master}</li>
	 *         </ul>
	 */
	ReObject save(EqSparePartsMaster master, List<EqSparePartsDetail> detail);

	/**
	 * <p>
	 * 根据设备编码查询备件明细记录
	 * </p>
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>String fstrEquipmentTypeCode</li>
	 *            </ul>
	 * 
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查询备件信息"</li>
	 *         <li>data 值为{List<EqSparePartsDetail>}</li>
	 *         </ul>
	 */
	ReObject findByEquipmentTypeCode(String fstrEquipmentTypeCode);

	/**
	 * <p>
	 * 审核未审核的设备备件记录
	 * </p>
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>String fstrEquipmentTypeCode</li>
	 *            </ul>
	 * 
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"审核备件信息"</li>
	 *         <li>data 值为{EqSparePartsMaster}</li>
	 *         </ul>
	 */
	ReObject verify(String fstrEquipmentTypeCode);

	/**
	 * <p>
	 * 删除未审核的设备备件记录
	 * </p>
	 * 
	 * @param fparam参数说明
	 *            <ul>
	 *            <li>String fstrEquipmentTypeCode</li>
	 *            </ul>
	 * 
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"删除备件信息"</li>
	 *         <li>data 值为{null}</li>
	 *         </ul>
	 */
	ReObject del(String fstrEquipmentTypeCode);

	/**
	 * 根据条件查询备件明细记录
	 * 
	 * @param fparameter
	 * 
	 * @return {@code List<EqSparePartsDetail>}
	 */

	ReObject findAutoIdsCondition(ParameterObject fparameter);

}
