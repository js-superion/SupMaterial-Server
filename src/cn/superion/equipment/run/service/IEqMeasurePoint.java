package cn.superion.equipment.run.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqMeasurePoint;

/**
 * 测量点服务接口
 * @author 曹国魁
 *
 */
public interface IEqMeasurePoint {
	/**
	 * 查询所有的测量点
	 * @return {@code List<EqMeasurePoint>}
	 */
	ReObject findAll();
	
	/**
	 * 根据条件，查询测量点列表，分页
	 * @param fparameter
	 * @return {@code List<EqMeasurePoint>}
	 */
	ReObject findByCondition(ParameterObject fparameter);
	
	/**
	 * 保存测量点信息
	 * @param isAdd 是否新增
	 * @param measurePoint
	 * @return
	 */
	ReObject save(boolean isAdd,EqMeasurePoint measurePoint);
	
	/**
	 * 删除测量点信息
	 * @param pointCode
	 * @return
	 */
	ReObject del(String pointCode);
}
