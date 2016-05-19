package cn.superion.equipment.job.service;

import java.util.List;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.entity.EqJobContent;
import cn.superion.equipment.entity.EqJobContentItem;
import cn.superion.equipment.entity.EqJobContentItemPart;

/**
 * 作业内容服务接口
 * 
 * @author 曹国魁
 * 
 */
public interface IEqJobContent {
	/**
	 * 根据条件，查询作业内容主记录ID列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>beginJobCode 起始作业编码</li>
	 *            <li>endJobCode 结束作业编码</li>
	 *            <li>jobName 作业名称</li>
	 *            <li>beginJobType 起始作业类型编码</li>
	 *            <li>endJobType 结束作业类型编码</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始对象编码</li>
	 *            <li>endObjectCode 结束对象编码</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginStartDate 起始开始日期</li>
	 *            <li>endStartDate 结束开始日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return 作业内容主记录ID列表
	 */
	ReObject findAutoIdsByCondition(ParameterObject fparameter);

	/**
	 * 根据作业内容主记录ID查询作业内容
	 * 
	 * @param fstrAutoId
	 * @return EqJobContent,{@code List<EqJobContentItem>},{@code
	 *         List<EqJobContentItemPart>}
	 */
	ReObject findByAutoId(String fstrAutoId);

	/**
	 * 保存作业内容
	 * 
	 * @param content
	 *            作业内容
	 * @param itemList
	 *            作业内容项目列表
	 * @param partList
	 *            作业内容项目备件列表
	 * @return EqJobContent
	 */
	ReObject save(EqJobContent content, List<EqJobContentItem> itemList,
			List<EqJobContentItemPart> partList);

	/**
	 * 审核作业内容
	 * 
	 * @param fstrAutoId
	 * @return EqJobContent
	 */
	ReObject verify(String fstrAutoId);

	/**
	 * 删除未审核的作业内容
	 * 
	 * @param fstrAutoId
	 * @return
	 */
	ReObject del(String fstrAutoId);

	/**
	 * 根据设备类型查找备件明细
	 * 
	 * @param fstrEquipmentType
	 *            设备类型
	 * @return List<EqSparePartsDetail>
	 */

	ReObject findPartsDetailByEquipmentType(String fstrEquipmentType);

}
