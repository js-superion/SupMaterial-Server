package cn.superion.equipment.run.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqRunDetail;
import cn.superion.equipment.entity.EqRunMaster;

/**
 * 运行记录服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqRun {
	/**
	 * 根据条件，查询运行记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始运行记录单号</li>
	 *            <li>endBillNo 结束运行记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始位置/设备编码</li>
	 *            <li>endObjectCode 结束位置/设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginStartDate 起始开始日期</li>
	 *            <li>endStartDate 结束开始日期</li>
	 *            <li>beginEndDate 起始终止日期</li>
	 *            <li>endEndDate 结束终止日期</li>
	 *            <li>runStatus 运行状态</li>
	 *            </ul>
	 * @return 运行记录ID列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);

	/**
	 * 根据条件，查询运行记录表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始运行记录单号</li>
	 *            <li>endBillNo 结束运行记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始位置/设备编码</li>
	 *            <li>endObjectCode 结束位置/设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginStartDate 起始开始日期</li>
	 *            <li>endStartDate 结束开始日期</li>
	 *            <li>beginEndDate 起始终止日期</li>
	 *            <li>endEndDate 结束终止日期</li>
	 *            <li>runStatus 运行状态</li>
	 *            </ul>
	 * @return {@code List<VEqRun>}
	 */
	ReObject findDetailListByCondition(ParameterObject fparameter);

	/**
	 * 根据运行记录ID，查询运行记录
	 * 
	 * @param fstrAutoId
	 * @return EqRunMaster,{@code List<EqRunDetail>}
	 */
	ReObject findByAutoId(String fstrAutoId);

	/**
	 * 保存运行记录
	 * 
	 * @param master
	 *            运行主记录
	 * @param details
	 *            运行明细记录列表
	 * @return EqRunMaster
	 */
	ReObject save(EqRunMaster master, List<EqRunDetail> details);

	/**
	 * 审核未审核的运行记录
	 * 
	 * @param fstrAutoId
	 * @return EqRunMaster
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 删除未审核的运行记录
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);

	/**
	 * 运行统计，统计各个设备的利用率，故障率，计划停机时间，故障时间，运行时间，制度时间，当前运行状态
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>startDate 开始日期</li>
	 *            <li>endDate 终止日期</li>
	 *            <li>usedDept 使用部门</li>
	 *            </ul>
	 * @return
	 */
	ReObject findRunStat(ParameterObject fparameter);
}
