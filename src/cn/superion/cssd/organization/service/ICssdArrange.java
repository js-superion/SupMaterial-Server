package cn.superion.cssd.organization.service;

import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.entity.CdWorkClassDict;
import cn.superion.center.config.entity.CdWorkClassTimeDict;
import cn.superion.cssd.entity.CssdPersonArrange;

public interface ICssdArrange {
	ReObject findPersonArrangeListByCondition(ParameterObject fparam);

	ReObject findPersonListByCondition(ParameterObject fparam);
	
	ReObject findWorkClassList();
	ReObject findWorkClassTimesByMainId(String fstrMainId) ;
	
	ReObject delWorkClassById(String fstrAutoId);
	
	/**
	 * 保存班次设置信息
	 * 
	 * @param  CdWorkClassDict fcdWorkClassDict 班次<br>
	 *         List<CdWorkClassTimeDict> flstTimesSpans 时间段集合
	 * @return 
	 */	
	ReObject saveWorkClassAndTimeSpan(CdWorkClassDict fcdWorkClassDict,List<CdWorkClassTimeDict> flstTimesSpans);

	ReObject findPersonArrangeByPersonId(String fstrPersonId);
	ReObject findPersonArrangeByPersonIdInRange(String fstrPersonId,Date fdateFrom,Date fdateTo);
	
	ReObject savePersonArrangeList(List<CssdPersonArrange> flstArranges);
	ReObject saveModPersonArrangeList(String fstrPersonId,List<CssdPersonArrange> flstArranges);
}
