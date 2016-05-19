package cn.superion.cssd.quality.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdChemistryDetailDAO;
import cn.superion.cssd.dao.CssdChemistryFilesDAO;
import cn.superion.cssd.dao.CssdChemistryMasterDAO;
import cn.superion.cssd.dao.CssdChemistryMaterialDictDAO;
import cn.superion.cssd.dao.CssdStockMasterDAO;
import cn.superion.cssd.entity.CssdChemistryDetail;
import cn.superion.cssd.entity.CssdChemistryMaster;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.util.SessionUtil;

public class CssdChemistryImpl implements ICssdChemistry{
    private CssdChemistryDetailDAO cssdChemistryDetailDAO;
    private CssdChemistryMasterDAO cssdChemistryMasterDAO;
    private CssdChemistryFilesDAO cssdChemistryFilesDAO;
    private CssdChemistryMaterialDictDAO cssdChemistryMaterialDictDAO;
    private CssdStockMasterDAO cssdStockMasterDAO;
    private IPicFileUtil picFileUtil;
    private String modual="chemistry";
	@Override
	public ReObject findChemistryMasterByChemTypeInRange(ParameterObject fparam) {
		Map<String,Object> lmapCondition= fparam.getConditions();
		String chemistryType=(String)lmapCondition.get("chemistryType");
		Date ldateFrom=(Date)lmapCondition.get("dateFrom");
		Date ldateTo=(Date)lmapCondition.get("dateTo");
		ReObject ro = new ReObject("查询化学检测记录");
		List<CssdChemistryMaster> llstChemistryMasters=cssdChemistryMasterDAO.findBychemistryTypeInDateRange(
				SessionUtil.getUnitsCode(),chemistryType,
				ldateFrom, ldateTo,ro,fparam.getStart(),fparam.getItemsPerPage());
		ro.setData(llstChemistryMasters);
		return ro;	
	} 
	
	

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findChemistryDetailByMainAutoid(String fstrAutoId) {
		ReObject ro = new ReObject("通过主记录ID查询明细灭菌记录");
		List<CssdChemistryDetail> llstDetails= cssdChemistryDetailDAO.findByProperty("mainAutoId", fstrAutoId);
		ro.setData(llstDetails);
		return ro;
	}	
	
	
	@Override
	public ReObject checkChemistryMaster(String fstrAutoId,String fstrCurrentState) {
		ReObject ro = new ReObject("审核化学检测记录");
		CssdChemistryMaster cssdChemistryMaster=cssdChemistryMasterDAO.findById(fstrAutoId);
		cssdChemistryMaster.setVerifyDate(new Date());
		cssdChemistryMaster.setVerifier(SessionUtil.getPersonId());
		cssdChemistryMaster.setCurrentStatus(fstrCurrentState);
		cssdChemistryMasterDAO.update(cssdChemistryMaster);
		return ro;
	}

	@Override
	public ReObject delChemistryMaster(String fstrAutoId) {
		ReObject ro = new ReObject("删除化学检测记录");
		//删除明细------
		cssdChemistryMasterDAO.deleteByAutoId(fstrAutoId);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findBiologyDetailByMainAutoId(String fstrMainAutoId) {
		ReObject ro = new ReObject("查询化学检测记录");
		List<CssdChemistryDetail> llstChemistryDetails=cssdChemistryDetailDAO.findByMainAutoId(fstrMainAutoId);
		ro.setData(llstChemistryDetails);
		return ro;	
	}

	@Override
	public ReObject loadPic(String fstrAutoId) {
		ReObject ro = new ReObject("调用图片数据");
		try{
		   ro.setData(picFileUtil.loadPic(fstrAutoId, modual));
		}catch(Exception e){
			e.printStackTrace();
			ro.setError(e.getMessage());
		}
		return ro;
	}

	@Override
	public ReObject saveChemistryMaster(CssdChemistryMaster fchemMaster,
			List<CssdChemistryDetail> fchemistryDetails) {
		ReObject ro = new ReObject("保存化学检测记录");
		fchemMaster.setUnitsCode(SessionUtil.getUnitsCode());
		fchemMaster.setOperateDate(new Date());
		fchemMaster.setOperator(SessionUtil.getPersonId());
		cssdChemistryMasterDAO.attachDirty(fchemMaster);
		cssdChemistryDetailDAO.saveDetails(fchemMaster.getAutoId(),fchemistryDetails);
		picFileUtil.savePic(fchemMaster.getAutoId(),modual, fchemMaster.getPics());
		return ro;
	}   
	@Override
	public ReObject findCssdStockMasterByPackageNo(String fstrPackageNo) {
		fstrPackageNo=fstrPackageNo.trim();
		ReObject ro = new ReObject("根据包号物品包主记录");
		CssdStockMaster lmaster= cssdStockMasterDAO.findById( fstrPackageNo);
		List<CssdStockMaster> llstMasters=new ArrayList<CssdStockMaster>();
		llstMasters.add(lmaster);
		ro.setData(llstMasters);
		return ro;
	}	
	
	public CssdChemistryDetailDAO getCssdChemistryDetailDAO() {
		return cssdChemistryDetailDAO;
	}
	public void setCssdChemistryDetailDAO(
			CssdChemistryDetailDAO cssdChemistryDetailDAO) {
		this.cssdChemistryDetailDAO = cssdChemistryDetailDAO;
	}
	public CssdChemistryMasterDAO getCssdChemistryMasterDAO() {
		return cssdChemistryMasterDAO;
	}
	public void setCssdChemistryMasterDAO(
			CssdChemistryMasterDAO cssdChemistryMasterDAO) {
		this.cssdChemistryMasterDAO = cssdChemistryMasterDAO;
	}
	public CssdChemistryFilesDAO getCssdChemistryFilesDAO() {
		return cssdChemistryFilesDAO;
	}
	public void setCssdChemistryFilesDAO(CssdChemistryFilesDAO cssdChemistryFilesDAO) {
		this.cssdChemistryFilesDAO = cssdChemistryFilesDAO;
	}
	public CssdChemistryMaterialDictDAO getCssdChemistryMaterialDictDAO() {
		return cssdChemistryMaterialDictDAO;
	}
	public void setCssdChemistryMaterialDictDAO(
			CssdChemistryMaterialDictDAO cssdChemistryMaterialDictDAO) {
		this.cssdChemistryMaterialDictDAO = cssdChemistryMaterialDictDAO;
	}
	public void setPicFileUtil(IPicFileUtil picFileUtil) {
		this.picFileUtil = picFileUtil;
	}
	public IPicFileUtil getPicFileUtil() {
		return picFileUtil;
	}

	public void setCssdStockMasterDAO(CssdStockMasterDAO cssdStockMasterDAO) {
		this.cssdStockMasterDAO = cssdStockMasterDAO;
	}
	public CssdStockMasterDAO getCssdStockMasterDAO() {
		return cssdStockMasterDAO;
	}



}
