package cn.superion.materialDept.other.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.materialDept.entity.MaterialCheckMasterDept;
import cn.superion.materialDept.entity.MaterialCheckDetailDept;

/**
 * 盘点服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface ICheck {
	/**
	 * 根据查找条件，获取当前盘点单据信息
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>beginBillNo 起始盘点单号</li>
	 *            <li>endBillNo 结束盘点单号</li>
	 *            <li>beginBillDate 起始盘点日期</li>
	 *            <li>endBillDate 结束盘点日期</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return 返回主记录ID
	 */
	ReObject findCheckMasterListByCondition(ParameterObject fparameter);

	/**
	 * 查看盘点单据详细信息记录
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回MaterialCheckMasterDept,{@code List<MaterialCheckDetailDept>}
	 */
	ReObject findCheckDetailList(String fstrAutoId);

	/**
	 * 保存盘点单据信息
	 * 
	 * @param fmaster
	 *            盘点单主记录
	 * @param fdetails
	 *            盘点单明细记录集合
	 * @return 返回MaterialCheckMasterDept,{@code List<MaterialCheckDetailDept>}
	 */
	ReObject saveCheck(MaterialCheckMasterDept fmaster,
			List<MaterialCheckDetailDept> fdetails);

	/**
	 * 删除当前未审核的盘点单据
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return
	 */
	ReObject delCheck(String fstrAutoId);

	/**
	 * 审核当前的物资盘点信息,并写盘盈入库单和盘亏出库单
	 * 
	 * @param fstrAutoId
	 *            主记录ID
	 * @return 返回MaterialCheckMasterDept
	 */
	ReObject verifyCheck(String fstrAutoId);

	/**
	 * 查找当前需要盘点的物资相关信息,零库存分两种情况;1.物资库存表中不存在，而物资档案字典中存在；2.物资库存表中存在，但库存数为零
	 * 
	 * @param fparameter
	 *            参数包含：
	 *            <ul>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginAvailDate 起始有效期限</li>
	 *            <li>endAvailDate 结束有效期限</li>
	 *            <li>anearNum 临近天数,与有效日期范围互斥</li>
	 *            <li>agentSign 是否代销</li>
	 *            <li>checkZeroSign 账面为零是否盘点，0：否；1：是</li>
	 *            </ul>
	 * @return 返回{@code List<MaterialCheckDetailDept>}
	 */
	ReObject findCheckMaterial(ParameterObject fparameter);
}
