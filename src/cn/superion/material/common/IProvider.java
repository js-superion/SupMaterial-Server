package cn.superion.material.common;

import java.util.List;

import cn.superion.center.provider.entity.*;
import cn.superion.base.*;

/**
 * <p>
 * 供应商档案维护服务接口
 * </p>
 * <p>
 * 包含新增、修改、删除、保存、查找等相关功能
 * </p>
 * 
 * @version 1.0
 * @author 袁成
 * @date 2009-12-24
 * 
 */
public interface IProvider {
	/**
	 * <p>
	 * 功能说明：查询供应商档案列表。
	 * </p>
	 * <p>
	 * 用法说明：本方法由客户端通过远程对象调用。
	 * </p>
	 * 
	 * @param 参数说明
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查询供应商档案列表"</li>
	 *         <li>data List<fCdProvider></li>
	 *         </ul>
	 * 
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findProviderListByCondtion(ParameterObject fparameter);

	/**
	 * <p>
	 * 功能说明：查看供应商档案信息。
	 * </p>
	 * <p>
	 * 用法说明：本方法由客户端通过远程对象调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            fstrProviderId 供应商id
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查看供应商档案信息"</li>
	 *         <li>data List<fCdProvider></li>
	 *         </ul>
	 * 
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findProviderById(String fstrProviderId);

	/**
	 * <p>
	 * 功能说明：查看供应商档案信息。
	 * </p>
	 * <p>
	 * 用法说明：本方法由客户端通过远程对象调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            fstrProviderCode 供应商档案编码
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查看供应商档案信息"</li>
	 *         <li>data List<fCdProvider></li>
	 *         </ul>
	 * 
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findProviderByProviderCode(String fstrProviderCode);

	/**
	 * <p>
	 * 功能说明：保存或修改供应商档案信息。
	 * </p>
	 * <p>
	 * 用法说明：本方法由客户端通过远程对象调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            fCdDuty 供应商档案对象 fstrType 标识保存还是修改 "add" 保存 "update" 修改
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"保存或修改供应商档案"</li>
	 *         <li>data null</li>
	 *         </ul>
	 * 
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject saveProvider(CdProvider fCdProvider, String fstrType);

	/**
	 * <p>
	 * 功能说明：删除供应商档案，先判断此类别是否存在子类别，如果存在，则不能删除；、 此类别下面是否存在人员，如果存在，不能删除。
	 * </p>
	 * <p>
	 * 用法说明：本方法由客户端通过远程对象调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            fstrClassCode 类别编码
	 *@return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"删除供应商档案"</li>
	 *         <li>data null</li>
	 *         </ul>
	 * 
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject deleteProviderById(String fstrProviderId);

	/**
	 * <p>
	 * 功能说明：查询供应商档案信息列表。
	 * </p>
	 * <p>
	 * 用法说明：本方法由客户端通过远程对象调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            fstrProviderClass 供应商分类编码
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查看供应商档案信息列表"</li>
	 *         <li>data List<fCdProvider></li>
	 *         </ul>
	 * 
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findProviderByProvideClass(String fstrProviderClass);

	/**
	 * <p>
	 * 功能说明：查询供应商档案信息列表。
	 * </p>
	 * <p>
	 * 用法说明：本方法由客户端通过远程对象调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *            fstrProviderClass 供应商分类编码 fstrUnitsCode 单位编码
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查看供应商档案信息列表"</li>
	 *         <li>data List<fCdProvider></li>
	 *         </ul>
	 * 
	 * @throws 异常说明
	 *             系统异常，不需要捕获
	 */
	ReObject findProviderByProvideClass(String fstrProviderClass,
			String fstrUnitsCode);

	/**
	 * <p>
	 * 功能说明：保存当前供应商基本、附件、产品相关信息，并返回供应商基本信息。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>CdProvider---fCdProvider</li>
	 *            <li>List<CdProviderFiles>---flstCdProviderFiles</li>
	 *            <li>List<CdProviderMaterial>---flstCdProviderMaterial</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"保存供应商信息"</li>
	 *         <li>data 类型为{@code List<CdProvider>}</li>
	 *         </ul>
	 */
	ReObject save(CdProvider fCdProvider,
			List<CdProviderFiles> flstCdProviderFiles,
			List<CdProviderMaterial> flstCdProviderMaterial);

	/**
	 * <p>
	 * 功能说明：根据供应商ID查询当前供应商附件信息
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrProviderId</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"供应商附件信息列表"</li>
	 *         <li>data 类型为{@code List<CdProviderFiles>}</li>
	 *         </ul>
	 */
	ReObject findProviderFilesById(String fstrProviderId);

	/**
	 * <p>
	 * 功能说明：根据供应商ID查询当前供应商产品信息
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrProviderId</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"供应商产品信息列表"</li>
	 *         <li>data 类型为{@code List<CdProviderMaterial>}</li>
	 *         </ul>
	 */
	ReObject findProviderMaterialById(String fstrProviderId);
	
	/**
	 * 根据附件路径，下载指定附件
	 * @param fstrFlag 1：供应商附件；2：供应商授权物资附件
	 * @param fstrFilePath
	 * @return 返回附件的byte[]
	 */
	public ReObject downLoadFile(String fstrFlag,String fstrFilePath);
	
	/**
	 * <p>
	 * 功能说明：根据输入编码，查询供应商列表。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>ParameterObject---fparameter : 包含输入编码</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"查找供应商字典列表"</li>
	 *         <li>data 类型为{@code List<CdProvider>}</li>
	 *         </ul>
	 */	
	ReObject findProviderDictListByInputCode(ParameterObject fparameter);
	
	/**
	 * <p>
	 * 功能说明：根据根据供应商id冻结供应商。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrProviderId : 供应商Id</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"冻结供应商"</li>
	 *         </ul>
	 */	
	ReObject freezeProviderById(String fstrProviderId);	
	
	/**
	 * <p>
	 * 功能说明：根据根据供应商id解冻供应商。
	 * </p>
	 * 
	 * @param 参数说明
	 *            <ul>
	 *            <li>String---fstrProviderId : 供应商Id</li>
	 *            </ul>
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"解冻供应商"</li>
	 *         </ul>
	 */	
	ReObject unFreezeProviderById(String fstrProviderId);		
}
