package cn.superion.equipment.job.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqFaultDetail;
import cn.superion.equipment.entity.EqJobBill;
import cn.superion.equipment.entity.EqJobBillFault;
import cn.superion.equipment.entity.EqJobBillItem;
import cn.superion.equipment.entity.EqJobBillItemPart;

/**
 * 作业单服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqJobBill {
	/**
	 * 根据条件，查询作业单主记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginJobBillNo 起始作业单号</li>
	 *            <li>endJobBillNo 结束作业单号</li>
	 *            <li>beginJobPlanNo 起始作业计划单号</li>
	 *            <li>endJobPlanNo 结束作业计划单号</li>
	 *            <li>beginJobCode 起始作业编码</li>
	 *            <li>endJobCode 结束作业编码</li>
	 *            <li>beginJobType 起始作业类型编码</li>
	 *            <li>endJobType 结束作业类型编码</li>
	 *            <li>objectType 对象类型</li>
	 *           <li>usedDept 使用部门</li>
	 *            <li>jobDept 作业部门</li>
	 *            <li>beginFactStartDate 起始实际开始日期</li>
	 *            <li>endFactStartDate 结束实际开始日期</li>
	 *            <li>beginFactEndDate 起始实际终止日期</li>
	 *            <li>endFactEndDate 结束实际终止日期</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            </ul>
	 * @return 作业单主记录ID列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);

	/**
	 * 根据条件，查询作业单列表，分页
	 * 
	 * @param fparameter 包含：
	 *            <ul>
	 *            <li>beginJobBillNo 起始作业单号</li>
	 *            <li>endJobBillNo 结束作业单号</li>
	 *            <li>beginJobPlanNo 起始作业计划单号</li>
	 *            <li>endJobPlanNo 结束作业计划单号</li>
	 *            <li>beginJobCode 起始作业编码</li>
	 *            <li>endJobCode 结束作业编码</li>
	 *            <li>beginJobType 起始作业类型编码</li>
	 *            <li>endJobType 结束作业类型编码</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始位置/设备编码</li>
	 *            <li>endObjectCode 结束位置/设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginPlanStartDate 起始计划开始日期</li>
	 *            <li>endPlanStartDate 结束计划开始日期</li>
	 *            <li>beginPlanEndDate 起始计划终止日期</li>
	 *            <li>endPlanEndDate 结束计划终止日期</li>
	 *            <li>beginFactStartDate 起始实际开始日期</li>
	 *            <li>endFactStartDate 结束实际开始日期</li>
	 *            <li>beginFactEndDate 起始实际终止日期</li>
	 *            <li>endFactEndDate 结束实际终止日期</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginJobGroup 起始作业小组编码</li>
	 *            <li>endJobGroup 结束作业小组编码</li>
	 *            <li>beginSparePartCode 起始备件编码</li>
	 *            <li>endSparePartCode 结束备件编码</li>
	 *            <li>beginFaultBillNo 起始故障记录单号</li>
	 *            <li>endFaultBillNo 结束故障记录单号</li>
	 *            </ul>
	 * @return {@code List<EqJobBill>}
	 */
	ReObject findByCondition(ParameterObject fparameter);

	/**
	 * 根据作业单ID查询作业单，以及相关信息
	 * 
	 * @param fstrAutoId
	 * @return EqJobBill,{@code List<EqJobBillItem>},{@code
	 *         List<EqJobBillItemPart>},{@code List<EqFaultDetail>}
	 */
	ReObject findByAutoId(String fstrAutoId);

	/**
	 * 保存作业单
	 * 
	 * @param bill
	 *            作业单
	 * @param itemList
	 *            作业单项目列表
	 * @param partList
	 *            作业单项目备件列表
	 * @param faultList
	 *            作业单解决故障列表
	 *            作业单解决故障列表 由作业计划引用的故障记录或作业单直接引用的故障记录
	 */
	ReObject save(EqJobBill bill, List<EqJobBillItem> itemList,
			List<EqJobBillItemPart> partList, List<EqJobBillFault> faultList);

	/**
	 * 审核未审核的作业单
	 * 
	 * @param fstrAutoId
	 * @return EqJobBill
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 删除未审核的作业单
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);
	
	/**
	 * 根据条件过滤故障记录，隐藏条件：生成作业计划标志为2
	 * 根据条件过滤故障记录，隐藏条件：生成作业计单标志为0
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>equipmentCode 设备编码</li>
	 *            <li>beginStartDate 起始故障开始日期</li>
	 *            <li>endStartDate 结束故障开始日期</li>
	 *            <li>usedDept 使用部门</li>
	 *            </ul>
	 * @return {@code List<EqFaultDetail>}
	 *            
	 *            
	 */
	ReObject findFaultByCondition(ParameterObject fparameter);

	/**
	 * 由故障记录生成作业单
	 * @param faultDetail 故障记录
	 * @return
	 */
	EqJobBill buildJobBill(EqFaultDetail faultDetail);
	
	/** 
	* 根据作业计划单号查询作业计划，以及相关信息，需判断作业计划引用的故障记录的生成作业单标志为0
	 * @param fstrPlanNo 作业计划单号
	 * @return EqJobPlan,{@code List<EqJobPlanItem>},{@code List<EqJobPlanItemPart>},{@code List<EqFaultDetail>}
	 */
	ReObject findJobPlanByPlanNo(String fstrPlanNo);
}






















































