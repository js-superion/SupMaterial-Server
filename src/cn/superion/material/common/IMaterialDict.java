package cn.superion.material.common;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.material.entity.CdMaterialDict;

/**
 * 物资档案字典的维护； 包括物资字典进行维护增加、修改、删除等方法
 * 
 * @author zzj
 * 
 */
public interface IMaterialDict {

	/**
	 * <p>
	 * 功能说明：根据物资档案分类、五笔简码或拼音简码查找当前物资字典。
	 * </p>
	 * <p>
	 * 用法说明：客户端点击左边的物资分类树结点时调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>Key---materialClass : 物资分类</li>
	 *            <li>Key---phoInputCode : 拼音码</li>
	 *            <li>Key---fiveInputCode : 五笔码</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查找物资档案列表"</li>
	 *         <li>data 类型为{@code List<CdMaterialDict>}</li>
	 *         </ul>
	 */
	ReObject findMaterialDictListByMaterialClass(ParameterObject fparameter);

	/**
	 * <p>
	 * 功能说明：新增或修改物资档案信息。
	 * </p>
	 * <p>
	 * 用法说明：客户端点击保存时调用
	 * </p>
	 * 
	 * @param fCdDrugDict
	 *            物资档案
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"新增或修改物资档案信息"</li>
	 *         <li>data 值为List<CdMaterialDict></li>
	 *         </ul>
	 */
	ReObject saveMateriaDict(CdMaterialDict cdMaterialDict);
	ReObject findByInputCodeExt(ParameterObject parameterObject);
	/**
	 * <p>
	 * 功能说明：删除物资档案信息。
	 * </p>
	 * <p>
	 * 用法说明：客户端点击删除时调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            fstrMaterialId 物资ID
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"删除物资档案信息"</li>
	 *         <li>data 值为null</li>
	 *         </ul>
	 */
	ReObject deleteMaterialDict(String fstrMaterialId);

	/**
	 * <p>
	 * 功能说明：查找物资档案修改日志。
	 * </p>
	 * <p>
	 * 用法说明：客户端调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrMaterialId : 物资ID</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查找物资档案日志"</li>
	 *         <li>data 值为List<CdMaterialDictLog></li>
	 *         </ul>
	 */
	ReObject findMaterialDictLogById(String fstrMaterialId);

	/**
	 * <p>
	 * 功能说明：根据输入简码（拼音简码、五笔简码）查找当前物资字典。
	 * </p>
	 * <p>
	 * 用法说明：客户端调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrInputCode : 输入简码</li>
	 *            <li>String---fstrInputType : 输入法类型</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查找物资档案列表"</li>
	 *         <li>data 值为List<CdMaterialDict></li>
	 *         </ul>
	 */
	ReObject findByIntputCode(String fstrInputCode, String fstrInputType);

	/**
	 * <p>
	 * 功能说明：根据条形码查找当前物资字典。
	 * </p>
	 * <p>
	 * 用法说明：客户端调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrBarCode : 输入简码</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查找物资档案列表"</li>
	 *         <li>data 值为List<CdMaterialDict></li>
	 *         </ul>
	 */
	ReObject findByBarCode(String fstrBarCode);

	/**
	 * <p>
	 * 功能说明：根据物资编码查找当前物资字典。
	 * </p>
	 * <p>
	 * 用法说明：客户端调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrMaterialCode : 物资编码</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查找物资档案列表"</li>
	 *         <li>data 值为List<CdMaterialDict></li>
	 *         </ul>
	 */
	ReObject findMaterialDicByCode(String fstrMaterialCode);

	/**
	 * 根据输入简码查询物资字典
	 * 
	 * @param fparameter
	 *            包含
	 *            <ul>
	 *            <li>String---fiveInputCode : 输入简码</li>
	 *            <li>String---phoInputCode : 输入法类型</li>
	 *            </ul>
	 * @return 返回{@code List<CdMaterialDict>}
	 */
	ReObject findByIntputCode(ParameterObject fparameter);

	/**
	 * 查询并返回所有的物资类别数据
	 * 
	 * @return 返回{@code List<CdMaterialClass>}
	 */
	ReObject findAllMeaterialClass();

	/**
	 * <p>
	 * 功能说明：根据上级编码，查询物资分类列表。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrParentCode : 上级编码</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查找物资类别字典列表"</li>
	 *         <li>data 类型为{@code List<CdMaterialClass>}</li>
	 *         </ul>
	 */
	ReObject findMaterialDictListByClassCode(String fstrClassCode);

	/**
	 * <p>
	 * 功能说明：根据输入编码，查询物资列表。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>ParameterObject---fparameter : 包含输入编码</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查找物资字典列表"</li>
	 *         <li>data 类型为{@code List<CdMaterialDict>}</li>
	 *         </ul>
	 */
	ReObject findMaterialDictListByInputCode(ParameterObject fparameter);

	/**
	 * 根据条件查询物资字典列表
	 * 
	 * @param fparameter
	 *            包含：
	 *            <ul>
	 *            <li>materialClass 物资分类</li>
	 *            <li>storageCode 所属仓库</li>
	 *            <li>phoInputCode 拼音码</li>
	 *            <li>fiveInputCode 五笔码</li>
	 *            </ul>
	 * @return {@code List<CdMaterialDict>}
	 */
	ReObject findMaterialDictListByCondition(ParameterObject fparameter);
}
