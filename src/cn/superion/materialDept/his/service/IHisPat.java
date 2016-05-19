package cn.superion.materialDept.his.service;

import cn.superion.materialDept.entity.PatsVisit;

/**
 * HIS病人信息服务接口
 * @author 曹国魁
 *
 */
public interface IHisPat {
	/**
	 * 根据病人标识号查询门诊病人信息,暂不实现
	 * @param fstrPatientId
	 * @return
	 */
	PatsVisit findOutPatInfo(String fstrPatientId);
	
	/**
	 * 根据病人标识号查询住院病人信息
	 * @param fstrPatientId
	 * @return 返回病人信息（包含基本信息和当前住院信息）
	 */
	PatsVisit findInPatInfo(String fstrPatientId);
	
	/**
	 * 根据病人标识号查询预交金余额
	 * @param fstrPatientId
	 * @return
	 */
	Double findPrepaymentLeft(String fstrPatientId);
}
