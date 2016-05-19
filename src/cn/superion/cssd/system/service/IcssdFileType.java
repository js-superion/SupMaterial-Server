package cn.superion.cssd.system.service;

import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdFileClassDict;

public interface IcssdFileType
{

	ReObject saveFileType(CssdFileClassDict cssdFileClassDict,String fstrParentCode);
	
	ReObject findFileTypeAll();
	
	ReObject findMaxClassCodeByParentCode(String ClassCode);
	
	ReObject delFileTypeByClassCode(String ClassCode);
}
