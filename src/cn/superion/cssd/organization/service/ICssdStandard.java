package cn.superion.cssd.organization.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdWorkRuleFiles;
import cn.superion.cssd.entity.CssdWorkRuleMaster;


public interface ICssdStandard {
	ReObject findWorkRuleMasterListByCondition(ParameterObject fparameter);
	
	ReObject saveWorkRuleMaster(CssdWorkRuleMaster fworkRuleMaster,List<CssdWorkRuleFiles> fworkRuleFiles);
	
	ReObject findWorkRuleFileByAutoId(String fstrAutoId);
	
	ReObject delWorkRule(String fstrAutoId);
	
	ReObject checkWorkRule(String fstrAutoId,String fstrCurrentState);
	
}
