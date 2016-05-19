package cn.superion.cssd.quality.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdSterilizeEffect;
import cn.superion.cssd.entity.CssdSterilizeEffectDetail;

public interface ICssdSterilization {
	ReObject findVSterilizationByResultInRange(ParameterObject fparam);
	ReObject findSteriliQulityDetailByMainAutoid(String fstrAutoId) ;
	ReObject saveSterilizeEffect(CssdSterilizeEffect fchemMaster,List<CssdSterilizeEffectDetail> fchemistryDetails);
	
	
	ReObject  delSterilizeEffect(String fstrAutoId);
	
	ReObject  checkSterilizeEffect(String fstrAutoId,String fstrCurrentState);
	ReObject delSterilizeEffectDetail(String fstrAutoId);
}
