package cn.superion.cssd.quality.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdBiologyDetialDAO;
import cn.superion.cssd.dao.CssdBiologyFilesDAO;
import cn.superion.cssd.dao.CssdBiologyMasterDAO;
import cn.superion.cssd.entity.CssdBiologyDetial;
import cn.superion.cssd.entity.CssdBiologyMaster;
import cn.superion.util.SessionUtil;

public class CssdBiologyImpl implements ICssdBiology {
	private CssdBiologyDetialDAO cssdBiologyDetialDAO;
	private CssdBiologyFilesDAO cssdBiologyFilesDAO;
	private CssdBiologyMasterDAO cssdBiologyMasterDAO;
	private IPicFileUtil picFileUtil;
	private String modual="biology";

	@Override
	public ReObject checkBiologyMaster(String fstrAutoId,String fstrCurrentState) {
		ReObject ro = new ReObject("审核物理检测记录");
		CssdBiologyMaster cssdBiologyMaster=cssdBiologyMasterDAO.findById(fstrAutoId);
		cssdBiologyMaster.setConfirmDate(new Date());
		cssdBiologyMaster.setConfirmPerson(SessionUtil.getSysUser().getUserName());
		cssdBiologyMaster.setCurrentStatus(fstrCurrentState);
		cssdBiologyMasterDAO.update(cssdBiologyMaster);
		return ro;
	}

	@Override
	public ReObject delBiologyMaster(String fstrAutoId) {
		ReObject ro = new ReObject("删除物理检测记录");
		cssdBiologyMasterDAO.deleteByAutoId(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject findBiologyMasterBySpecimenInRange(ParameterObject fparam) {
		Map<String,Object> lmapCondition= fparam.getConditions();
		String specimenName=(String)lmapCondition.get("specimenName");
		Date ldateFrom=(Date)lmapCondition.get("dateFrom");
		Date ldateTo=(Date)lmapCondition.get("dateTo");
		ReObject ro = new ReObject("查询生物检测记录");
		List<CssdBiologyMaster> llstBiologyMasters=cssdBiologyMasterDAO.findByEquipNameInDateRange(
				SessionUtil.getUnitsCode(),specimenName,
				ldateFrom, ldateTo,ro,fparam.getStart(),fparam.getItemsPerPage());
		ro.setData(llstBiologyMasters);
		return ro;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findBiologyDetailByMainAutoId(String fstrMainAutoId) {
		ReObject ro = new ReObject("查询生物检测记录");
		List<CssdBiologyDetial> llstBiologyDetails=cssdBiologyDetialDAO.findByMainAutoId(fstrMainAutoId);
		ro.setData(llstBiologyDetails);
		return ro;	
	}

	@Override
	public ReObject saveBiologyMaster(CssdBiologyMaster fbiologyMaster,
			List<CssdBiologyDetial> fbiologyDetails) {
		ReObject ro = new ReObject("保存物理检测记录");
		fbiologyMaster.setUnitsCode(SessionUtil.getUnitsCode());
		fbiologyMaster.setReportDate(new Date());
		fbiologyMaster.setReportPerson(SessionUtil.getSysUser().getUserName());
		cssdBiologyMasterDAO.attachDirty(fbiologyMaster);
		cssdBiologyDetialDAO.saveDetails(fbiologyMaster.getAutoId(),fbiologyDetails);
		picFileUtil.savePic(fbiologyMaster.getAutoId(),modual, fbiologyMaster.getPics());
		return ro;
	}
	
	@Override
	public ReObject  loadPic(String fstrBiologyMasterAutoId) {
		ReObject ro = new ReObject("调用图片数据");
		try{
		   ro.setData(picFileUtil.loadPic(fstrBiologyMasterAutoId, modual));
		}catch(Exception e){
			e.printStackTrace();
			ro.setError(e.getMessage());
		}
		return ro;
	}
	public void setCssdBiologyDetialDAO(
			CssdBiologyDetialDAO cssdBiologyDetialDAO) {
		this.cssdBiologyDetialDAO = cssdBiologyDetialDAO;
	}

	public CssdBiologyDetialDAO getCssdBiologyDetialDAO() {
		return cssdBiologyDetialDAO;
	}

	public void setCssdBiologyFilesDAO(CssdBiologyFilesDAO cssdBiologyFilesDAO) {
		this.cssdBiologyFilesDAO = cssdBiologyFilesDAO;
	}

	public CssdBiologyFilesDAO getCssdBiologyFilesDAO() {
		return cssdBiologyFilesDAO;
	}

	public void setCssdBiologyMasterDAO(
			CssdBiologyMasterDAO cssdBiologyMasterDAO) {
		this.cssdBiologyMasterDAO = cssdBiologyMasterDAO;
	}

	public CssdBiologyMasterDAO getCssdBiologyMasterDAO() {
		return cssdBiologyMasterDAO;
	}

	public IPicFileUtil getPicFileUtil() {
		return picFileUtil;
	}

	public void setPicFileUtil(IPicFileUtil picFileUtil) {
		this.picFileUtil = picFileUtil;
	}

	

}
