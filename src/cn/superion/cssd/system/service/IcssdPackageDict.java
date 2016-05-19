package cn.superion.cssd.system.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdPackageDict;
import cn.superion.cssd.entity.CssdPackageDictDetail;

public interface IcssdPackageDict 
{
	/**
	 * 保存物品包
	 * @param fCssdPackageDict
	 * @param flstCssdPackageDictDetail
	 * @return
	 */
	ReObject savePackageDict(CssdPackageDict fCssdPackageDict,List<CssdPackageDictDetail> flstCssdPackageDictDetail);
	/**
	 * 查询所有物品文类信息
	 * @return
	 */
	ReObject findPackageClassDict();
	/**
	 * 根据条件查询包下的物品明细清单
	 * @param parameter
	 * @return
	 */
	ReObject findPackageDictListByClass(ParameterObject fparameter);
	/**
	 * 删除物品包
	 * @param fstrPackageId
	 * @return
	 */
	ReObject delPackageDictById(String fstrPackageId);
	/**
	 *查找物品包明细记录
	 * @param fstrPackageId
	 * @return
	 */
	ReObject findPackageDetailByCode(String fstrPackageId);
	
	/**
	 * 按照物品包Id查询详细记录
	 * @param PackageId 物品包编码
	 * @return
	 */
	ReObject findPackageId(List<Object> packageId);
}
