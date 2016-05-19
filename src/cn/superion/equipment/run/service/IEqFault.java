package cn.superion.equipment.run.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqFaultDetail;
import cn.superion.equipment.entity.EqFaultMaster;

/**
 * 故障记录服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqFault {
	/**
	 * 根据条件，查询故障记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始故障记录单号</li>
	 *            <li>endBillNo 结束故障记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginEquipmentCode 起始设备编码</li>
	 *            <li>endEquipmentCode 结束设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>positionCode 位置</li>
	 *            <li>workingShift 班次</li>
	 *            <li>beginStartDate 起始故障开始日期</li>
	 *            <li>endStartDate 结束故障开始日期</li>
	 *            <li>beginEndDate 起始故障结束日期</li>
	 *            <li>endEndDate 结束故障结束日期</li>
	 *            <li>faultType 故障类型</li>
	 *            <li>faultReason 故障原因</li>
	 *            <li>jobStatus 作业完成情况</li>
	 *            </ul>
	 * @return 故障记录ID列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);

	/**
	 * 根据条件，查询故障记录表,分页
	 * 
	 * @param fparameter 包含：
	 *            <ul>
	 *            <li>beginBillNo 起始故障记录单号</li>
	 *            <li>endBillNo 结束故障记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginEquipmentCode 起始设备编码</li>
	 *            <li>endEquipmentCode 结束设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>positionCode 位置</li>
	 *            <li>workingShift 班次</li>
	 *            <li>beginStartDate 起始故障开始日期</li>
	 *            <li>endStartDate 结束故障开始日期</li>
	 *            <li>beginEndDate 起始故障结束日期</li>
	 *            <li>endEndDate 结束故障结束日期</li>
	 *            <li>faultType 故障类型</li>
	 *            <li>faultReason 故障原因</li>
	 *            <li>jobStatus 作业完成情况</li>
	 *            </ul>
	 * @return {@code List<VEqFault>}
	 */
	ReObject findDetailListByCondition(ParameterObject fparameter);

	/**
	 * 根据故障记录ID，查询故障记录
	 * 
	 * @param fstrAutoId
	 * @return EqFaultMaster,{@code List<EqFaultDetail>}
	 */
	ReObject findByAutoId(String fstrAutoId);

	/**
	 * 保存故障记录
	 * 
	 * @param master
	 *            故障主记录
	 * @param details
	 *            故障明细记录列表
	 * @return EqFaultMaster
	 */
	ReObject save(EqFaultMaster master, List<EqFaultDetail> details);

	/**
	 * 审核未审核的故障记录
	 * 
	 * @param fstrAutoId
	 * @return EqFaultMaster
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 删除未审核的故障记录
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);

	/**
	 * 生成作业计划或作业单
	 * 
	 * @param fstrAutoId
	 * @return {@code List<EqFaultDetail>}
	 */
	ReObject saveAsJobPlan(String fstrAutoId);
	
	/**
	 * 生成作业计划或作业单
	 * 
	 * @param fstrAutoId
	 * @param toJobPlanFaultList 生成作业计划的故障明细列表
	 * @param toJobBillFaultList 生成作业单的故障明细列表
	 * @return {@code List<EqFaultDetail>}
	 */
	ReObject saveAsJobPlan(String fstrAutoId,List<EqFaultDetail> toJobPlanFaultList,List<EqFaultDetail> toJobBillFaultList);
}
