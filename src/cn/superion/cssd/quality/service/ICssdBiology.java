package cn.superion.cssd.quality.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdBiologyDetial;
import cn.superion.cssd.entity.CssdBiologyMaster;

public interface ICssdBiology {
	ReObject findBiologyMasterBySpecimenInRange(ParameterObject fparam);
	
	ReObject findBiologyDetailByMainAutoId(String fstrMainAutoId);
	
	ReObject saveBiologyMaster(CssdBiologyMaster fbiologyMaster,List<CssdBiologyDetial> fbiologyDetails);
	
	ReObject  loadPic(String fstrAutoId);
	
	ReObject  delBiologyMaster(String fstrAutoId);
	
	ReObject  checkBiologyMaster(String fstrAutoId,String fstrCurrentState);
}
