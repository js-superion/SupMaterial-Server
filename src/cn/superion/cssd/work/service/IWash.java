package cn.superion.cssd.work.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdWashDetail;
import cn.superion.cssd.entity.CssdWashMaster;

/**
 * 物品清洗消毒服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IWash {
	/**
	 * 根据查找条件，获取当前符合条件的物品清洗消毒列表
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>equipmentCode 清洗设备编号</li>
	 *            <li>enzymeChroma 酶浓度是否合格</li>
	 *            <li>personId 清洗人员</li>
	 *            </ul>
	 * @return 返回主记录ID列表
	 */
	ReObject findMasterIdListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前物品清洗消毒的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            清洗消毒主记录ID
	 * @return CssdWashMaster,{@code List<CssdWashDetail>}
	 */
	ReObject findDetailById(String fstrAutoId);

	/**
	 * 保存当前物品清洗消毒信息，根据过滤回收物品包物资的日期范围更新物品回收明细记录的清洗系统标识号
	 * 
	 * @param fmaster
	 *            清洗消毒主记录
	 * @param flstDetail
	 *            清洗消毒明细记录列表
	 * @return CssdWashMaster,{@code List<CssdWashDetail>}
	 */
	ReObject save(CssdWashMaster fmaster, List<CssdWashDetail> flstDetail);

	/**
	 * 删除当前未审核的物品清洗消毒单据
	 * 
	 * @param fstrAutoId
	 *            清洗消毒主记录ID
	 * @return
	 */
	ReObject deleteById(String fstrAutoId);

	/**
	 * 审核当前的物品清洗消毒信息
	 * 
	 * @param fstrAutoId
	 *            清洗消毒主记录ID
	 * @return
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 过滤回收物品包物资
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginRetrieveDate 起始回收日期，精确到秒</li>
	 *            <li>endRetrieveDate 终止回收日期，精确到秒</li>
	 *            </ul>
	 * @return {@code List<Map<String,Object>>},包含：
	 *         <ul>
	 *         <li>packageName 物品包名称</li>
	 *         <li>materialClass 物资分类</li>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>amount 实回数量</li>
	 *         </ul>
	 */
	ReObject findRetrieveMaterialStatByCondition(ParameterObject fparameter);
}
