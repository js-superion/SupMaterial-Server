package cn.superion.equipment.ledger.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqChangeDetail;
import cn.superion.equipment.entity.EqChangeMaster;

/**
 * 设备变更服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqChange {
	/**
	 * 根据条件，查询设备变更主记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始变更单号</li>
	 *            <li>endBillNo 结束变更单号</li>
	 *            <li>beginBillDate 起始变更日期</li>
	 *            <li>endBillDate 结束变更日期</li>
	 *            <li>changeType 变更类型</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginEquipmentCode 起始设备编码</li>
	 *            <li>endEquipmentCode 结束设备编码</li>
	 *            <li>changeContent 变更内容</li>
	 *            </ul>
	 * @return 设备变更主记录ID列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);
	
	/**
	 * 根据变更主记录ID查询变更记录
	 * @param fstrAutoId
	 * @return EqChangeMaster,{@code List<EqChangeDetail>}
	 */
	ReObject findByAutoId(String fstrAutoId);
	
	/**
	 * 保存变更记录
	 * @param master
	 * @param details
	 * @return EqChangeMaster
	 */
	ReObject save(EqChangeMaster master,List<EqChangeDetail> details);
	
	/**
	 * 审核未审核的变更记录，并更新设备台账的内容,
	 * 变更内容编号：1--作业部门；2--使用部门；3--制度时间；4--保修截止时间；5--设备原值；6--位置；7--设备状态
	 * @param fstrAutoId
	 * @return EqChangeMaster
	 */
	ReObject verify(String fstrAutoId);
	
	/**
	 * 删除未审核的变更记录
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);
}
