package cn.superion.cssd.organization.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdCheckFilesDAO;
import cn.superion.cssd.dao.CssdCheckMasterDAO;
import cn.superion.cssd.entity.CssdCheckFiles;
import cn.superion.cssd.entity.CssdCheckMaster;
import cn.superion.util.SessionUtil;

public class CssdCheckImpl implements ICssdCheck{
    private CssdCheckMasterDAO cssdCheckMasterDAO;
    private CssdCheckFilesDAO cssdCheckFilesDAO;
	@Override
	public ReObject findCheckMasterListByCondition(ParameterObject fparam) {
		Map<String, Object> condtion= fparam.getConditions();
		Date fileDateFrom=(Date)condtion.get("checkDateFrom");
		Date fileDateTo=(Date)condtion.get("checkDateTo");
		ReObject ro = new ReObject("查询检查评价列表");		
		List<CssdCheckMaster> llstChecks= cssdCheckMasterDAO.findByCheckDate(SessionUtil.getUnitsCode(),fileDateFrom,fileDateTo,
				fparam.getStart(),fparam.getItemsPerPage(),ro);
		ro.setData(llstChecks);
		return ro;
	}
	@Override
	public ReObject saveCheckMaster(CssdCheckMaster fcheckMaster,
			List<CssdCheckFiles> fcheckFiles) {
		ReObject ro = new ReObject("保存检查评价和附件记录");	
		fcheckMaster.setUnitsCode(SessionUtil.getUnitsCode());
		fcheckMaster.setCreateDate(new Date());
		fcheckMaster.setCreatePerson(SessionUtil.getPersonId());
		cssdCheckMasterDAO.attachDirty(fcheckMaster);
		cssdCheckFilesDAO.saveCheckFiles(fcheckMaster,fcheckFiles);
		return ro;
	}	
	
	public void setCssdCheckMasterDAO(CssdCheckMasterDAO cssdCheckMasterDAO) {
		this.cssdCheckMasterDAO = cssdCheckMasterDAO;
	}
	public CssdCheckMasterDAO getCssdCheckMasterDAO() {
		return cssdCheckMasterDAO;
	}
	public void setCssdCheckFilesDAO(CssdCheckFilesDAO cssdCheckFilesDAO) {
		this.cssdCheckFilesDAO = cssdCheckFilesDAO;
	}
	public CssdCheckFilesDAO getCssdCheckFilesDAO() {
		return cssdCheckFilesDAO;
	}
	@Override
	public ReObject checkCheckMaster(String fstrAutoId,String fstrCurrentState) {
		ReObject ro = new ReObject("审核检查信息");
		CssdCheckMaster cssdCheckMaster=cssdCheckMasterDAO.findById(fstrAutoId);
		cssdCheckMaster.setVerifyDate(new Date());
		cssdCheckMaster.setVerifier(SessionUtil.getPersonId());
		cssdCheckMaster.setCurrentStuats(fstrCurrentState);
		cssdCheckMasterDAO.update(cssdCheckMaster);		
		return ro;		
	}
	@Override
	public ReObject delCheckMaster(String fstrAutoId) {
		cssdCheckMasterDAO.delById(fstrAutoId);
		cssdCheckFilesDAO.deleteByMainAutoId(fstrAutoId);
		ReObject ro = new ReObject("删除检查信息和附件信息");
		return ro;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findCheckFileByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("通过检查ID获取相关附件");
		List<CssdCheckFiles> llstFiles= cssdCheckFilesDAO.findByProperty("mainAutoId", fstrAutoId);
		ro.setData(llstFiles);
		return ro;	
	}


}
