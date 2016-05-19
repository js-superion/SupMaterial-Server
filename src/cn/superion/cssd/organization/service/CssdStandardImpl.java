package cn.superion.cssd.organization.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdWorkRuleFilesDAO;
import cn.superion.cssd.dao.CssdWorkRuleMasterDAO;
import cn.superion.cssd.entity.CssdPersonTrainFiles;
import cn.superion.cssd.entity.CssdWorkRuleFiles;
import cn.superion.cssd.entity.CssdWorkRuleMaster;
import cn.superion.util.SessionUtil;

public class CssdStandardImpl implements ICssdStandard{
	private CssdWorkRuleFilesDAO cssdWorkRuleFilesDAO;
	private CssdWorkRuleMasterDAO cssdWorkRuleMasterDAO;
	@Override
	public ReObject findWorkRuleMasterListByCondition(ParameterObject fparameter) {
		Map<String, Object> condtion= fparameter.getConditions();
		String classCode=(String)condtion.get("classCode");
		Date fileDateFrom=(Date)condtion.get("fileDateFrom");
		Date fileDateTo=(Date)condtion.get("fileDateTo");
		ReObject ro = new ReObject("查询文件类型列表");		
		List<CssdWorkRuleMaster> llstFiles= cssdWorkRuleMasterDAO.findByClassCodeFileDate(classCode,fileDateFrom,fileDateTo,
				fparameter.getStart(),fparameter.getItemsPerPage(),ro);
		ro.setData(llstFiles);
		return ro;
	}
	@Override
	public ReObject saveWorkRuleMaster(CssdWorkRuleMaster fworkRuleMaster,
			List<CssdWorkRuleFiles> fworkRuleFiles) {
		ReObject ro = new ReObject("保存工作规范和附件记录");	
		fworkRuleMaster.setUnitsCode(SessionUtil.getUnitsCode());
		fworkRuleMaster.setCreateDate(new Date());
		fworkRuleMaster.setCreatePerson(SessionUtil.getPersonId());
		cssdWorkRuleMasterDAO.attachDirty(fworkRuleMaster);
		cssdWorkRuleFilesDAO.saveWorkRuleFiles(fworkRuleMaster,fworkRuleFiles);
		return ro;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findWorkRuleFileByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("通过规范ID获取相关附件");
		List<CssdPersonTrainFiles> llstFiles= cssdWorkRuleFilesDAO.findByProperty("mainAutoId", fstrAutoId);
		ro.setData(llstFiles);
		return ro;	
	}
	@Override
	public ReObject delWorkRule(String fstrAutoId) {
		cssdWorkRuleMasterDAO.delById(fstrAutoId);
		cssdWorkRuleFilesDAO.deleteByMainAutoId(fstrAutoId);
		ReObject ro = new ReObject("删除规范信息和附件信息");
		return ro;
	}

	public void setCssdWorkRuleFilesDAO(CssdWorkRuleFilesDAO cssdWorkRuleFilesDAO) {
		this.cssdWorkRuleFilesDAO = cssdWorkRuleFilesDAO;
	}
	public CssdWorkRuleFilesDAO getCssdWorkRuleFilesDAO() {
		return cssdWorkRuleFilesDAO;
	}
	public void setCssdWorkRuleMasterDAO(CssdWorkRuleMasterDAO cssdWorkRuleMasterDAO) {
		this.cssdWorkRuleMasterDAO = cssdWorkRuleMasterDAO;
	}
	public CssdWorkRuleMasterDAO getCssdWorkRuleMasterDAO() {
		return cssdWorkRuleMasterDAO;
	}
	@Override
	public ReObject checkWorkRule(String fstrAutoId,String fstrCurrentState) {
		ReObject ro = new ReObject("审核人员信息");
		CssdWorkRuleMaster cssdWorkRuleMaster=cssdWorkRuleMasterDAO.findById(fstrAutoId);
		cssdWorkRuleMaster.setVerifyDate(new Date());
		cssdWorkRuleMaster.setVerifier(SessionUtil.getPersonId());
		cssdWorkRuleMaster.setCurrentStuats(fstrCurrentState);
		cssdWorkRuleMasterDAO.update(cssdWorkRuleMaster);		
		return ro;
	}


}
