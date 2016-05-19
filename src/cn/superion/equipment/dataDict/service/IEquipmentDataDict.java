package cn.superion.equipment.dataDict.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 设备基础字典查询
 * 
 * @author jszzj
 * 
 */
public interface IEquipmentDataDict {
	/**
	 * <p>
	 * 功能说明：获取设备基础字典，客户端登录时进行加载；
	 * </p>
	 * 
	 * @param 无
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"设备基础字典"</li>
	 *         <li>data 类型{HasMap}</li>
	 *         <li>key---eqClass ：设备分类</li>
	 *         <li>key---eqStatusDict ：状态字典</li>
	 *         <li>...</li>
	 *         </ul>
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findEquipmentDataDict();

	/**
	 * <p>
	 * 功能说明：根据五笔/拼音码，查询字典列表（分页）
	 * </p>
	 * 
	 * @param fParameters
	 *            参数对象包含：
	 *            <ul>
	 *            <li>start : 数据开始的索引</li>
	 *            <li>key---page : 当前页</li>
	 *            <li>key---itemsPerPage : 每页条数</li>
	 *            <li>查询条件 conditions Map 内容如下：</li>
	 *            <li>key---phoInputCode : 拼音简码 同五笔只能选用一个</li>
	 *            <li>key---fiveInputCode : 五笔简码</li>
	 *            <li>key---entityClassName : 实体类路径+类名</li>
	 *            <li>key---serverOrderField : 排序字段</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查询字典列表"</li>
	 *         <li>data 类型{List<?>}</li>
	 *         </ul>
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findByInputCode(ParameterObject fparameter);
	
	
	/**
	 * 根据设备编号查询设备台账信息
	 *
	 * @param fstrEquipmentCode
	 * @return EqEquipment
	 */
	ReObject findEquipmentByEquipmentCode(String fstrEquipmentCode);
	
	/**
	 * 根据设备编号查询设备的作业内容列表
	 * @param fstrEquipmentCode
	 * @return {@code List<EqJobContent>}
	 */
	ReObject findJobContentByEquipmentCode(String fstrEquipmentCode);
}
