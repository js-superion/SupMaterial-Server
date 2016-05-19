package cn.superion.cssd.quality.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdWashQualityDetailDAO;
import cn.superion.cssd.dao.CssdWashQualityMasterDAO;
import cn.superion.cssd.dao.VCssdWashQualityDAO;
import cn.superion.cssd.entity.CssdWashQualityDetail;
import cn.superion.cssd.entity.CssdWashQualityMaster;
import cn.superion.cssd.entity.VCssdWashQuality;
import cn.superion.util.SessionUtil;

public class CssdWashQualityImpl implements ICssdWashQuality{

	private CssdWashQualityMasterDAO cssdWashQualityMasterDAO;
	private VCssdWashQualityDAO vCssdWashQualityDAO;
	private CssdWashQualityDetailDAO cssdWashQualityDetailDAO;
	@Override
	public ReObject findVWashMasterByResultInRange(ParameterObject fparam) {
		Map<String,Object> lmapCondition= fparam.getConditions();
		String resultValue=(String)lmapCondition.get("resultValue");
		Date ldateFrom=(Date)lmapCondition.get("dateFrom");
		Date ldateTo=(Date)lmapCondition.get("dateTo");
		ReObject ro = new ReObject("查询清洗检测记录");
		List<VCssdWashQuality> llstMasters=vCssdWashQualityDAO.findVWashMasterByResultInRange(
				SessionUtil.getUnitsCode(),resultValue,
				ldateFrom, ldateTo,ro,fparam.getStart(),fparam.getItemsPerPage());
		ro.setData(llstMasters);
		return ro;	
	}

	@Override
	public ReObject checkWashMaste(String fstrAutoId,String fstrCurrentState) {
		ReObject ro = new ReObject("审核清洗检测记录");
		CssdWashQualityMaster cssdMaster=cssdWashQualityMasterDAO.findById(fstrAutoId);
		cssdMaster.setVerifyDate(new Date());
		cssdMaster.setVerifier(SessionUtil.getPersonId());
		cssdMaster.setCurrentStatus(fstrCurrentState);
		cssdWashQualityMasterDAO.update(cssdMaster);
		return ro;
	}

	@Override
	public ReObject delWashMaste(String fstrAutoId) {
		ReObject ro = new ReObject("删除清洗检测记录");
		//删除明细------
		cssdWashQualityDetailDAO.deleteById(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject delWashDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("删除清洗检测记录");
		//删除明细------
		cssdWashQualityDetailDAO.deleteById(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject saveWashMaste(CssdWashQualityMaster fMaster,
			List<CssdWashQualityDetail> fDetails) {
		ReObject ro = new ReObject("保存清洗检测记录");
		fMaster.setUnitsCode(SessionUtil.getUnitsCode());
		cssdWashQualityMasterDAO.attachDirty(fMaster);
		cssdWashQualityDetailDAO.saveDetails(fMaster.getAutoId(),fDetails);
		return ro;
	}

	@Override
	public ReObject findWashQulityDetailByMainAutoid(String fstrAutoId) {
		ReObject ro = new ReObject("通过主记录ID查询明细清洗记录");
		List<CssdWashQualityDetail> llstDetails= cssdWashQualityDetailDAO.findByProperty("mainAutoId", fstrAutoId);
		ro.setData(llstDetails);
		return ro;
	}
	public void setvCssdWashQualityDAO(VCssdWashQualityDAO vCssdWashQualityDAO) {
		this.vCssdWashQualityDAO = vCssdWashQualityDAO;
	}

	public VCssdWashQualityDAO getvCssdWashQualityDAO() {
		return vCssdWashQualityDAO;
	}

	public void setCssdWashQualityDetailDAO(CssdWashQualityDetailDAO cssdWashQualityDetailDAO) {
		this.cssdWashQualityDetailDAO = cssdWashQualityDetailDAO;
	}

	public CssdWashQualityDetailDAO getCssdWashQualityDetailDAO() {
		return cssdWashQualityDetailDAO;
	}

	public CssdWashQualityMasterDAO getCssdWashQualityMasterDAO() {
		return cssdWashQualityMasterDAO;
	}

	public void setCssdWashQualityMasterDAO(
			CssdWashQualityMasterDAO cssdWashQualityMasterDAO) {
		this.cssdWashQualityMasterDAO = cssdWashQualityMasterDAO;
	}


}
