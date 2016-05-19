package cn.superion.cssd.organization.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdCheckFiles;
import cn.superion.cssd.entity.CssdCheckMaster;

public interface ICssdCheck {
	ReObject findCheckMasterListByCondition(ParameterObject fparam);
	
	ReObject saveCheckMaster(CssdCheckMaster fcheckMaster,List<CssdCheckFiles> fcheckFiles);
	
	ReObject findCheckFileByAutoId(String fstrAutoId);
	
	ReObject delCheckMaster(String fstrAutoId);
	
	ReObject checkCheckMaster(String fstrAutoId,String fstrCurrentState);

}
