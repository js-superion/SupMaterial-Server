package cn.superion.cssd.work.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdSterilizeMaster;
import cn.superion.cssd.entity.CssdStockMaster;

/**
 * 物品灭菌登记服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ISterilize {
	/**
	 * 根据查找条件，获取当前符合条件的物品灭菌列表
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>personId 灭菌人员</li>
	 *            <li>beginPackageId 起始物品包编码</li>
	 *            <li>endPackageId 结束物品包编码</li>
	 *            <li>packageId 物品包编码</li>
	 *            </ul>
	 * @return 返回主记录ID列表
	 */
	ReObject findMasterIdListByCondition(ParameterObject fparameter);

	/**
	 * 查看当前物品灭菌登记的详细信息记录
	 * 
	 * @param fstrAutoId
	 *            灭菌主记录ID
	 * @return CssdSterilizeMaster,{@code List<CssdStockMaster>}
	 */
	ReObject findDetailById(String fstrAutoId);

	/**
	 * 保存当前物品灭菌信息； 保存灭菌记录，并更新物品包的当前状态为1，以及灭菌主记录ID，灭菌序号，灭菌结果
	 * 
	 * @param fmaster
	 *            灭菌主记录
	 * @param flstStockMaster
	 *            物品包记录列表
	 * @return CssdSterilizeMaster,{@code List<CssdStockMaster>}
	 */
	ReObject save(CssdSterilizeMaster fmaster,
			List<CssdStockMaster> flstStockMaster);

	/**
	 * 删除当前未审核的物品灭菌单据
	 * 
	 * @param fstrAutoId
	 *            灭菌主记录ID
	 * @return
	 */
	ReObject deleteById(String fstrAutoId);

	/**
	 * 审核当前的物品灭菌信息
	 * 
	 * @param fstrAutoId
	 *            灭菌主记录ID
	 * @return CssdSterilizeMaster
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 根据物品包编号或拼音码，五笔码查询已打包的物品包信息
	 * 
	 * @param ParameterObject
	 *            包含packageNo,phoInputCode,fiveInputCode
	 * @return {@code List<CssdStockMaster>}
	 */
	ReObject findStockMasterByPackageNo(ParameterObject fparameter);

	/**
	 * 根据条件，过滤已打包审核的物品包列表，分页查询
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>billNo 单据编号</li>
	 *            <li>beginBillDate 起始打包日期</li>
	 *            <li>endBillDate 结束打包日期</li>
	 *            <li>sterilizeNo 灭菌锅号</li>
	 *            <li>sterilizeOrder 灭菌锅次</li>
	 *            </ul>
	 * @return {@code List<CssdStockMaster>}
	 */
	ReObject findPackedByCondition(ParameterObject fparameter);
	
	/**
	 * 根据物品包拼音码，五笔码查询已打包的物品包信息
	 * @param fparameter
	 * @return {@code List<CssdStockMaster>}
	 */
	ReObject findPackedByInputCode(ParameterObject fparameter);
}
