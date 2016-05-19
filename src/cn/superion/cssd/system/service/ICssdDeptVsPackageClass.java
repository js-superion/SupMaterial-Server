package cn.superion.cssd.system.service;

import cn.superion.base.ReObject;
public interface ICssdDeptVsPackageClass {
	
	/**
	 * <p>
	 * 功能说明：根据科室编码，查询科室授权包分类列表。
	 * </p>
	 * <p>
	 * 用法说明：本方法由客户端通过远程对象调用。
	 * </p>
	 * 
	 * @param 参数说明
	 *         fstrDeptCode  部门编码
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>data 类型为{@code List<SysUser>} </li>
	 *         </ul>
	 */ 
	public ReObject findPackageCLassListByDeptCode(String fstrDeptCode);
	/**
	 * 新建的时候，调用该方法，确定改科室下是否有重复的分类编码。
	 * @param fstrDeptCode
	 * @param fstrClassCode
	 * @return
	 */
	public ReObject findByDeptCodeClassCode(String fstrDeptCode, String fstrClassCode);
	/**
	 * <p>
	 * 功能说明：新增科室对照包分类。
	 * </p>
	 * <p>
	 * 用法说明：新增删科室对照包分类调用
	 * </p>
	 * 
	 * @param 参数说明
	 *            fstrDeptCode    部门编码
	 *            fstrClassCodes   包分类编码集合
	 *            
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"新增包分类对照"</li>
	 *         <li>data 值为null </li>
	 *         </ul>
	 */
	public ReObject saveDeptVsPackageClass(String fstrDeptCode,String fstrClassCode,String fstrClassName);
	
	/**
	 * <p>
	 * 功能说明：删除功能。
	 * </p>
	 * <p>
	 * 用法说明：删除科室对照包分类。
	 * </p>
	 * 
	 * @param 参数说明
	 *            fstrDeptCode   部门编码
	 *            fstrClassCode   包分类编码
	 * @return 返回值说明 ReObject包含属性：
	 *         <ul>
	 *         <li>action 值为"删除科室对照包分类"</li>
	 *         <li>data 值为null </li>
	 *         </ul>
	 */
	public ReObject delDeptVsPackageClassById(String fstrDeptCode,String fstrClassCode);


}
