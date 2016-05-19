package cn.superion.cssd.quality.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdChemistryDetail;
import cn.superion.cssd.entity.CssdChemistryMaster;

public interface ICssdChemistry {
	ReObject findChemistryMasterByChemTypeInRange(ParameterObject fparam);
	ReObject findBiologyDetailByMainAutoId(String fstrMainAutoId);
	
	ReObject saveChemistryMaster(CssdChemistryMaster fchemMaster,List<CssdChemistryDetail> fchemistryDetails);
	
	ReObject  loadPic(String fstrAutoId);
	
	ReObject  delChemistryMaster(String fstrAutoId);
	
	ReObject  checkChemistryMaster(String fstrAutoId,String fstrCurrentState);
	
	ReObject  findCssdStockMasterByPackageNo(String fstrPackageNo);
	ReObject findChemistryDetailByMainAutoid(String fstrAutoId);	
}
