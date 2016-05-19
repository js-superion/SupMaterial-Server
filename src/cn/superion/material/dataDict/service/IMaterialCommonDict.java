package cn.superion.material.dataDict.service;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 物资字典公用接口，供前台加载物资字典所用
 * 
 * @author jszzj
 * 
 */
public interface IMaterialCommonDict {
	/**
	 * <p>
	 * 功能说明：根据五笔/拼音码查询物资字典列表（分页）
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
	 *            <li>key---storageCode : 仓库编码</li>
	 *            <li>key---fixedSign : 固定资产标志</li>
	 *            <li>key---cssdSign : 供应室标志</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查询物资字典列表"</li>
	 *         <li>data 类型{List<CdMaterialDict>}</li>
	 *         </ul>
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findByInputCode(ParameterObject fparameter);
	ReObject findByInputCodeDF(ParameterObject fparameter); 
	
	/**
	 * <p>
	 * 功能说明：查询物资分类
	 * </p>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查询物资分类字典列表"</li>
	 *         <li>data 类型{List<CdMaterialClass>}</li>
	 *         </ul>
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findMaterialClass();
	
	
	/**
	 * <p>
	 * 功能说明：根据物资分类查询物资字典
	 * </p>
	 * 
	 * @param fstrMaterialClass 物资分类编码
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"根据物资分类查询物资字典"</li>
	 *         <li>data 类型{List<CdMaterialDict>}</li>
	 *         </ul>
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findMaterialDictByClass(ParameterObject fparameter);
	
	/**
	 * <p>
	 * 功能说明：获取系统常用的公共数据字典，客户端登录后进行加载;
	 * </p>
	 */
	ReObject findAdvanceDict();
}
