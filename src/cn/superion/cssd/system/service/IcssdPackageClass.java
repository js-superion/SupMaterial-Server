package cn.superion.cssd.system.service;

import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdPackageClassDict;

public interface IcssdPackageClass 
{
	ReObject savePackageClass(CssdPackageClassDict cssdPackageClassDict,String fstrType);
	
	ReObject findPackageClassDict();
	
	ReObject findMaxClassCodeByParentCode (String parentCode);
	
	ReObject delPackageClassByClassCode(String classCode);
}
