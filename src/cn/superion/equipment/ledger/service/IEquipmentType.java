package cn.superion.equipment.ledger.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqEquipmentType;

/**
 * 设备类型台账服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEquipmentType {
	/**
	 * 查询设备类别下的设备类型台账
	 * @param classCode 设备类别编码
	 * @return {@code List<EqEquipmentType>}
	 */
	ReObject findByClassCode(String classCode);

	/**
	 * 保存设备类型台账，设备类型编码唯一
	 * @param eqType
	 * @return EqEquipmentType
	 */
	ReObject save(EqEquipmentType eqType);
	
	/**
	 * 审核未审核的设备类型台账
	 * @param fstrAutoId
	 * @return EqEquipmentType
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 删除未审核的设备类型台账
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);
}
