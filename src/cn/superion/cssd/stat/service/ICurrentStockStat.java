package cn.superion.cssd.stat.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 当前现存量服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ICurrentStockStat {
	/**
	 * 查询物品包记录,分页查询
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>packageClass 物品包分类</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginPackageId 起始物品包编码</li>
	 *            <li>endPackageId 结束物品包编码</li>
	 *            <li>beginAvailDate 起始有效期至</li>
	 *            <li>endAvailDate 结束有效期至</li>
	 *            <li>sterilizeStatus 灭菌状态</li>
	 *            </ul>
	 * 
	 * @return {@code List<CssdStockMaster>}
	 */
	ReObject findCurrentStockListByCondition(ParameterObject fparameter);

	/**
	 * 根据物品包编号查询物品包明细记录
	 * 
	 * @param fstrPackageNo
	 *            物品包编号
	 * @return {@code List<CssdStockDetail>}
	 */
	ReObject findDetailByPackageNo(String fstrPackageNo);

	/**
	 * 查询同锅次，锅号的灭菌包列表
	 * 
	 * @param fstrSterilizeAutoId
	 *            物品包灭菌主记录ID
	 * @return {@code List<CssdStockMaster>}
	 */
	ReObject findSterilizeDetailBySterilizeAutoId(String fstrSterilizeAutoId);
}
