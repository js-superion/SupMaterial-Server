package cn.superion.equipment.run.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqMeasureDetail;
import cn.superion.equipment.entity.EqMeasureMaster;

/**
 * 测量记录服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqMeasure {
	/**
	 * 根据条件，查询测量主记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始测量点记录单号</li>
	 *            <li>endBillNo 结束测量点记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginPointCode 起始测量点编码</li>
	 *            <li>endPointCode 结束测量点编码</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始位置/设备编码</li>
	 *            <li>endObjectCode 结束位置/设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginStandard 起始标准值</li>
	 *            <li>endStandard 结束标准值</li>
	 *            </ul>
	 * 
	 * @return 测量主记录ID列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);

	/**
	 * 根据条件，查询测量点记录明细列表,分页
	 * 
	 * @param fparameter  包含：
	 *            <ul>
	 *            <li>beginBillNo 起始测量点记录单号</li>
	 *            <li>endBillNo 结束测量点记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginPointCode 起始测量点编码</li>
	 *            <li>endPointCode 结束测量点编码</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始位置/设备编码</li>
	 *            <li>endObjectCode 结束位置/设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginStandard 起始标准值</li>
	 *            <li>endStandard 结束标准值</li>
	 *            </ul>
	 * @return {@code List<VEqMeasure>}
	 */
	ReObject findDetailListByCondition(ParameterObject fparameter);

	/**
	 * 根据测量主记录ID，查询测量记录
	 * 
	 * @param fstrAutoId
	 * @return EqMeasureMaster,{@code List<EqMeasureDetail>}
	 */
	ReObject findByAutoId(String fstrAutoId);

	/**
	 * 保存测量记录
	 * 
	 * @param master
	 *            测量主记录
	 * @param details
	 *            测量明细记录列表
	 * @return EqMeasureMaster
	 */
	ReObject save(EqMeasureMaster master, List<EqMeasureDetail> details);

	/**
	 * 审核未审核的测量记录
	 * 
	 * @param fstrAutoId
	 * @return EqMeasureMaster
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 删除未审核的测量记录
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);
}
