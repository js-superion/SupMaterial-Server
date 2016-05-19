package cn.superion.cssd.common;


import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;

/**
 * 供应室公共服务接口
 * 
 * @author
 * 
 */
public interface ICommCssdService {
	

	/**
	 * 根据输入的拼音码或五笔码查询文件分类字典列表
	 * 
	 * @param fparameter
	 * @return {@code List<CssdFileClassDict>}
	 */
	ReObject findCssdFileClassByCondition(ParameterObject fparameter);

	/**
	 * 查询所有的文件分类字典
	 * 
	 * @return {@code List<Map<String,String>>} map内有两个entry
	 *         <ul>
	 *         <li>fileClass 文件分类编码</li>
	 *         <li>fileClassName 文件分类名称</li>
	 *         </ul>
	 */
	ReObject findAllCssdFileClass();
	
	/**
	 * 根据输入的拼音码或五笔码查询物品包分类字典
	 * @param fparameter
	 * @return {@code List<CssdPackageClassDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdPackageClassDictByCondition(ParameterObject fparameter);
	
	
	ReObject findCssdPackageClassDictByCode(String fstrCode);
	
	/**
	 * 根据输入的拼音码或五笔码查询物品包装方式字典
	 * @param fparameter
	 * @return {@code List<CssdPackageModeDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdPackageModeDictByCondition(ParameterObject fparameter);
	
	/**
	 * 根据输入的拼音码或五笔码查询化学指示物字典
	 * @param fparameter
	 * @return {@code List<CssdChemistryMaterialDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdChemistryMaterialDictByCondition(ParameterObject fparameter);
	
	/**
	 * 根据输入的拼音码或五笔码查询消毒液字典
	 * @param fparameter
	 * @return {@code List<CssdAntisepsisDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdAntisepsisDictByCondition(ParameterObject fparameter);
	
	/**
	 * 根据输入的拼音码或五笔码查询设备字典
	 * @param fparameter
	 * @return {@code List<CssdEquipmentDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdEquipmentDictByCondition(ParameterObject fparameter);
	
	/**
	 * 根据输入的拼音码或五笔码查询灭菌类型字典
	 * @param fparameter
	 * @return {@code List<CssdSterilizeTypeDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdSterilizeTypeDictByCondition(ParameterObject fparameter);

	
	/**
	 * 根据输入的拼音码或五笔码查询送检标本字典
	 * @param fparameter
	 * @return {@code List<CssdSpecimenDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdSpecimenDictByCondition(ParameterObject fparameter);
	
	/**
	 * 根据标本编码查询送检项目字典
	 * @param fstrSpecimenCode
	 * @return {@code List<CssdTestItemDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdTestItemDictBySpecimenCode(String fstrSpecimenCode);
	
	/**
	 * 根据输入的拼音码或五笔码查询化学检测类型字典
	 * @param fparameter
	 * @return {@code List<CssdChemistryTypeDict>}
	 * @author 曹国魁
	 */
	ReObject findCssdChemistryTypeDictByCondition(ParameterObject fparameter);
	
	/**
	 * 下载文件
	 * @param fstrAutoId
	 * @param fstrSysFileName
	 * @param fstrModual
	 * @return
	 */
	ReObject downloadFile(String fstrAutoId, String fstrSysFileName,
			String fstrModual);

	/**
	 * 根据输入简码查询有效物品包字典
	 * @param fparameter
	 * @return {@code List<CssdPackageDict>}
	 */
	ReObject findCssdPackageByIntputCode(ParameterObject fparameter);
	ReObject findCssdPackageByIntputCode1(ParameterObject fparameter);
	
	/**
	 * 根据科室授权的包分类查对应的包记录
	 * @param fparameter
	 * @return
	 */
	ReObject findCssdPackageByIntputCode2(ParameterObject fparameter);
	
	/**
	 * 根据物品包编码查询物品包明细视图
	 * @param fstrPackageId
	 * @return {@code List<VCssdPackageDictDetail>}
	 * @author 曹国魁
	 */
	ReObject findVCssdPackageDictDetailByPackageId(String fstrPackageId);
	
	/**
	 * 根据物品包编号查询物品包信息,不限物品包的状态
	 * @param fstrPackageNo 物品包编号
	 * @return CssdStockMaster
	 * @author 曹国魁
	 */
	ReObject findStockMasterByPackageNo(String fstrPackageNo);
		
	//根据条码查询物品包主记录和明细记录信息
	//ReObject findStockByPackageNo(String fstrPackageNo);
	
	//根据条码查询物品包明显记录信息
	//ReObject findStockDetailByPackageNo(String fstrPackageNo);
	
	ReObject uploadFileToTmp(String fstrFileName,byte[] faryFileData);
	
	public ReObject findCssdPackageDetailByMainId(ParameterObject fparameter);
	
	ReObject findCssdEquipmentByInputCode(ParameterObject fparameter);
	ReObject findAllCssdEquipment();

	public ReObject findVCssdPackageDictDetailByPackageId(ParameterObject fparameter) ;
	
	public ReObject findAllPackage();
}
