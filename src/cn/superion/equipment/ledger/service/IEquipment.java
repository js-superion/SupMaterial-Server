package cn.superion.equipment.ledger.service;

import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqEquipment;
import cn.superion.equipment.entity.EqEquipmentFiles;

/**
 * 设备台账服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEquipment {
	/**
	 * 根据条件，查询设备台账主记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginEquipmentCode 起始设备编码</li>
	 *            <li>endEquipmentCode 结束设备编码</li>
	 *            <li>equipmentName 设备名称</li>
	 *            <li>beginClassAbc 起始设备ABC分类编码</li>
	 *            <li>endClassAbc 结束设备ABC分类编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginFatherCode 起始父设备编码</li>
	 *            <li>endFatherCode 结束父设备编码</li>
	 *            <li>equipmentStatus 设备状态</li>
	 *            <li>positionCode 位置</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>supplier 供应商</li>
	 *            <li>manufacturer 制造商</li>
	 *            <li>beginDateOfProduction 起始出厂日期</li>
	 *            <li>endDateOfProduction 结束出厂日期</li>
	 *            <li>beginDateOfUsed 起始使用日期</li>
	 *            <li>endDateOfUsed 结束使用日期</li>
	 *            <li>jobDept 作业部门</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            </ul>
	 * @return 设备台账主记录ID列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);

	/**
	 * 根据条件，查询设备台账列表，分页
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginEquipmentCode 起始设备编码</li>
	 *            <li>endEquipmentCode 结束设备编码</li>
	 *            <li>equipmentName 设备名称</li>
	 *            <li>beginClassAbc 起始设备ABC分类编码</li>
	 *            <li>endClassAbc 结束设备ABC分类编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginFatherCode 起始父设备编码</li>
	 *            <li>endFatherCode 结束父设备编码</li>
	 *            <li>equipmentStatus 设备状态</li>
	 *            <li>positionCode 位置</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>supplier 供应商</li>
	 *            <li>manufacturer 制造商</li>
	 *            <li>beginDateOfProduction 起始出厂日期</li>
	 *            <li>endDateOfProduction 结束出厂日期</li>
	 *            <li>beginDateOfUsed 起始使用日期</li>
	 *            <li>endDateOfUsed 结束使用日期</li>
	 *            <li>jobDept 作业部门</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            </ul>
	 * @return 设备台账主记录列表
	 */
	ReObject findByCondition(ParameterObject fparameter);

	/**
	 * 根据设备台账主记录ID查询设备台账卡片
	 * 
	 * @param fstrAutoId
	 * @return EqEquipment,{@code List<EqEquipmentFiles>}
	 */
	ReObject findByAutoId(String fstrAutoId);

	/**
	 * 保存设备台账信息
	 * 
	 * @param equipment
	 * @param equipmentFiles 前台需准确设置文件编号fileNo(应保证唯一)
	 * @return EqEquipment,{@code List<EqEquipmentFiles>}
	 */
	ReObject save(EqEquipment equipment, List<EqEquipmentFiles> equipmentFiles);

	/**
	 * 审核未审核的设备台账
	 * 
	 * @param fstrAutoId
	 * @return EqEquipment
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 删除未审核的设备台账
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);
	
	/**
	 * 根据附件路径，下载指定附件
	 * @param fstrFilePath
	 * @return 返回附件的byte[]
	 */
	public ReObject downLoadFile(String fstrFilePath);
	
	public ReObject fillPrintData (Map<String,Object> data);
	
	public ReObject saveImportData(Map fmap);
}
