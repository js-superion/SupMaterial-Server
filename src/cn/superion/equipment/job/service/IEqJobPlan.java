package cn.superion.equipment.job.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqFaultDetail;
import cn.superion.equipment.entity.EqJobPlan;
import cn.superion.equipment.entity.EqJobPlanFault;
import cn.superion.equipment.entity.EqJobPlanItem;
import cn.superion.equipment.entity.EqJobPlanItemPart;

/**
 * 作业计划服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqJobPlan {
	/**
	 * 根据条件，查询作业计划主记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginJobPlanNo 起始作业计划单号</li>
	 *            <li>endJobPlanNo 结束作业计划单号</li>
	 *            <li>beginJobCode 起始作业编码</li>
	 *            <li>endJobCode 结束作业编码</li>
	 *            <li>beginJobType 起始作业类型编码</li>
	 *            <li>endJobType 结束作业类型编码</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginPlanStartDate 起始计划开始日期</li>
	 *            <li>endPlanStartDate 结束计划开始日期</li>
	 *            <li>beginPlanEndDate 起始计划终止日期</li>
	 *            <li>endPlanEndDate 结束计划终止日期</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>jobDept 作业部门</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            </ul>
	 * @return 作业计划主记录ID列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);

	/**
	 * 根据条件，查询作业计划列表,分页
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
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
	 * @return {@code List<EqJobPlan>}
	 */
	ReObject findByCondition(ParameterObject fparameter);

	/**
	 * 根据作业计划主记录ID查询作业计划,以及相关信息
	 * 
	 * @param fstrAutoId
	 * @return EqJobPlan,{@code List<EqJobPlanItem>},{@code
	 *         List<EqJobPlanItemPart>},{@code List<EqFaultDetail>}
	 */
	ReObject findByAutoId(String fstrAutoId);

	/**
	 * 保存作业计划
	 * 
	 * @param plan
	 *            作业计划
	 * @param itemList
	 *            作业计划项目列表
	 * @param partList
	 *            作业计划项目备件列表
	 * @param faultList
	 *            作业计划解决故障列表
	 * @return EqJobPlan
	 */
	ReObject save(EqJobPlan plan, List<EqJobPlanItem> itemList,
			List<EqJobPlanItemPart> partList, List<EqJobPlanFault> faultList);

	/**
	 * 审核未审核的作业计划
	 * 
	 * @param fstrAutoId
	 * @return EqJobPlan
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 删除未审核的作业计划
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);

	/**
	 * 根据条件过滤故障记录，隐藏条件：生成作业计划标志为0，生成作业单标志不加条件（允许补计划）
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>equipmentCode 设备编码</li>
	 *            <li>beginStartDate 起始故障开始日期</li>
	 *            <li>endStartDate 结束故障开始日期</li>
	 *            <li>usedDept 使用部门</li>
	 *            </ul>
	 * @return {@code List<EqFaultDetail>}
	 */
	ReObject findFaultByCondition(ParameterObject fparameter);
	
	/**
	 * 由故障记录生成作业计划
	 * @param faultDetail 故障记录
	 */
	EqJobPlan buildJobPlan(EqFaultDetail faultDetail);
	
	/**
	 * 根据作业编码查询作业内容以及相关信息
	 * @param fstrJobCode 作业内容编码
	 * @return EqJobContent,{@code List<EqJobContentItem>},{@code List<EqJobContentItemPart>}
	 */
	ReObject findJobContentByJobCode(String fstrJobCode);
	
	/**
	 * 查询已审核过的作业计划单号，返回给前台的弹出窗体，供用户选择
	 * @return EqJobPlan,{@code List<EqJobPlan>}
	 * <li>objName 作业名称</li>
	 */
	ReObject findJobPlanNos(ParameterObject fparameter);
	
}
