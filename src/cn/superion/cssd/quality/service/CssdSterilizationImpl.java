package cn.superion.cssd.quality.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdSterilizeEffectDAO;
import cn.superion.cssd.dao.CssdSterilizeEffectDetailDAO;
import cn.superion.cssd.dao.VCssdSterilizeEffectDAO;
import cn.superion.cssd.entity.CssdSterilizeEffect;
import cn.superion.cssd.entity.CssdSterilizeEffectDetail;
import cn.superion.cssd.entity.VCssdSterilizeEffect;
import cn.superion.util.SessionUtil;

public class CssdSterilizationImpl implements ICssdSterilization{

	private CssdSterilizeEffectDAO cssdSterilizeEffectDAO;
	private CssdSterilizeEffectDetailDAO cssdSterilizeEffectDetailDAO; 
	private VCssdSterilizeEffectDAO vCssdSterilizeEffectDAO;
	@Override
	public ReObject findVSterilizationByResultInRange(ParameterObject fparam) {
		Map<String,Object> lmapCondition= fparam.getConditions();
		String resultValue=(String)lmapCondition.get("resultValue");
		Date ldateFrom=(Date)lmapCondition.get("dateFrom");
		Date ldateTo=(Date)lmapCondition.get("dateTo");
		ReObject ro = new ReObject("查询灭菌检测记录");
		List<VCssdSterilizeEffect> llstMasters=vCssdSterilizeEffectDAO.findVSterilizationByResultInRange(
				SessionUtil.getUnitsCode(),resultValue,
				ldateFrom, ldateTo,ro,fparam.getStart(),fparam.getItemsPerPage());
		ro.setData(llstMasters);
		return ro;	
	}

	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findSteriliQulityDetailByMainAutoid(String fstrAutoId) {
		ReObject ro = new ReObject("通过主记录ID查询明细灭菌记录");
		List<CssdSterilizeEffectDetail> llstDetails= cssdSterilizeEffectDetailDAO.findByProperty("mainAutoId", fstrAutoId);
		ro.setData(llstDetails);
		return ro;
	}
	@Override
	public ReObject saveSterilizeEffect(CssdSterilizeEffect fMaster,
			List<CssdSterilizeEffectDetail> fDetails) {
		ReObject ro = new ReObject("保存灭菌检测记录");
		fMaster.setUnitsCode(SessionUtil.getUnitsCode());
		cssdSterilizeEffectDAO.attachDirty(fMaster);
		cssdSterilizeEffectDetailDAO.saveDetails(fMaster.getAutoId(),fDetails);
		return ro;
	}

	@Override
	public ReObject checkSterilizeEffect(String fstrAutoId,String fstrCurrentState) {
		ReObject ro = new ReObject("审核灭菌检测记录");
		CssdSterilizeEffect cssdMaster=cssdSterilizeEffectDAO.findById(fstrAutoId);
		cssdMaster.setVerifyDate(new Date());
		cssdMaster.setVerifier(SessionUtil.getPersonId());
		cssdMaster.setCurrentStatus(fstrCurrentState);
		cssdSterilizeEffectDAO.update(cssdMaster);
		return ro;
	}

	@Override
	public ReObject delSterilizeEffect(String fstrAutoId) {
		ReObject ro = new ReObject("删除灭菌检测明细记录");
		//删除明细------
		cssdSterilizeEffectDAO.deleteByAutoId(fstrAutoId);
		return ro;
	}
	@Override
	public ReObject delSterilizeEffectDetail(String fstrAutoId) {
		ReObject ro = new ReObject("删除灭菌检测明细记录");
		//删除明细------
		cssdSterilizeEffectDetailDAO.deleteByAutoId(fstrAutoId);
		return ro;
	}
	public void setCssdSterilizeEffectDAO(CssdSterilizeEffectDAO cssdSterilizeEffectDAO) {
		this.cssdSterilizeEffectDAO = cssdSterilizeEffectDAO;
	}

	public CssdSterilizeEffectDAO getCssdSterilizeEffectDAO() {
		return cssdSterilizeEffectDAO;
	}

	public void setCssdSterilizeEffectDetailDAO(
			CssdSterilizeEffectDetailDAO cssdSterilizeEffectDetailDAO) {
		this.cssdSterilizeEffectDetailDAO = cssdSterilizeEffectDetailDAO;
	}

	public CssdSterilizeEffectDetailDAO getCssdSterilizeEffectDetailDAO() {
		return cssdSterilizeEffectDetailDAO;
	}

	public void setvCssdSterilizeEffectDAO(VCssdSterilizeEffectDAO vCssdSterilizeEffectDAO) {
		this.vCssdSterilizeEffectDAO = vCssdSterilizeEffectDAO;
	}

	public VCssdSterilizeEffectDAO getvCssdSterilizeEffectDAO() {
		return vCssdSterilizeEffectDAO;
	}

}
