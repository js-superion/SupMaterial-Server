package cn.superion.cssd.quality.service;

import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdWashQualityDetail;
import cn.superion.cssd.entity.CssdWashQualityMaster;

public interface ICssdWashQuality {
	ReObject findVWashMasterByResultInRange(ParameterObject fparam);
	
	ReObject findWashQulityDetailByMainAutoid(String fstrAutoId);

	ReObject checkWashMaste(String fstrAutoId,String fstrCurrentState);

	ReObject delWashMaste(String fstrAutoId);

	ReObject saveWashMaste(CssdWashQualityMaster fMaster,
			List<CssdWashQualityDetail> fDetails);

	ReObject delWashDetailById(String fstrAutoId);
}
