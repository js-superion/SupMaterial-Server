package cn.superion.equipment.run.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqEquipmentFiles;
import cn.superion.equipment.entity.EqRepair;
import cn.superion.equipment.entity.EqTest;
import cn.superion.equipment.entity.EqTestFiles;

public interface IEqTest {
	/**
	 * 根据条件，查询运行记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始单号</li>
	 *            <li>endBillNo 结束单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>runStatus 运行状态</li>
	 *            </ul>
	 * @return 保修申请单id列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);
	ReObject uploadFileToTmp(String fstrFileName, byte[] faryFileData);
	ReObject downLoadFile(String fstrFilePath);
	/**
	 * 根据条件，查询检测记录表
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
	 * @return {@code List<EqRepair>}
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
	ReObject save(EqTest master,
			List<EqTestFiles> equipmentFiles);

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
}
