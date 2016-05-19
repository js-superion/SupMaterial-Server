package cn.superion.equipment.system.service;

import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqFaultReasonDict;
/**
 * 故障原因字典服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqFaultReasonDict {
	/**
	 * 查询所有的故障原因
	 * @return {@code List<EqFaultReasonDict>}
	 */
	ReObject findAll();

	/**
	 * 保存故障原因
	 * @param isAdd 是否新增
	 * @param eqFaultReasonDict
	 * @return
	 */
	ReObject save(boolean isAdd, EqFaultReasonDict eqFaultReasonDict);

	/**
	 * 删除故障原因
	 * @param reasonCode
	 * @return
	 */
	ReObject del(String reasonCode);
}
