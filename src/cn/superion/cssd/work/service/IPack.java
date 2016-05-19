package cn.superion.cssd.work.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdPackageDict;
import cn.superion.cssd.entity.CssdPackageMaster;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.cssd.entity.CssdWashDetail;

/**
 * 物品打包登记服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IPack {
	/**
	 * 根据查找条件，获取当前符合条件的物品打包列表
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>packager 包装人</li>
	 *            <li>sterilizeNo 灭菌锅号</li>
	 *            <li>beginSterilizeDate 起始灭菌日期</li>
	 *            <li>endSterilizeDate 结束灭菌日期</li>
	 *            <li>packageNo 物品包号</li>
	 *            <li>sterilizeType 灭菌类型</li>
	 *            </ul>
	 * @return 返回主记录ID列表
	 */
	ReObject findMasterIdListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前物品打包登记的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            物品打包主记录ID
	 * @return CssdPackageMaster,{@code List<CssdStockMaster>}
	 */
	ReObject findDetailById(String fstrAutoId);

	/**
	 * 保存当前物品打包信息
	 * 
	 * @param fmaster
	 *            物品打包主记录
	 * @param flstStockMaster
	 *            物品包记录，包含物品包明细记录列表List<CssdStockDetail>
	 * @return CssdPackageMaster,{@code List<CssdStockMaster>}
	 */
	ReObject save(CssdPackageMaster fmaster,
			List<CssdStockMaster> flstStockMaster,List<CssdWashDetail> mAutoid);

	/**
	 * 删除当前未审核的物品打包单据
	 * 
	 * @param fstrAutoId 物品打包主记录ID
	 * @return
	 */
	ReObject deleteById(String fstrAutoId);

	/**
	 * 审核当前的物品打包信息
	 * 
	 * @param fstrAutoId 物品打包主记录ID
	 * @return CssdPackageMaster
	 */
	ReObject verify(String fstrAutoId);
	/**
	 * 过滤清洗包明细信息
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginWashDate 起始清洗日期，精确到秒</li>
	 *            <li>endWashDate 终止清洗日期，精确到秒</li>
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
	ReObject findWashMaterialStatByCondition(ParameterObject fparameter);
	
	List<CssdPackageDict> findPackageDictListByClass(String fstrPackageId);
	
	/**
	 * 根据申请部门查询可用物品包
	 */ 
	ReObject findSterilizedByProvideAutoId(ParameterObject fparameter);
}
