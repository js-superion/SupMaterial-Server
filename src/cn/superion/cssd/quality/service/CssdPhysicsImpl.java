package cn.superion.cssd.quality.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdPhysicsFilesDAO;
import cn.superion.cssd.dao.CssdPhysicsMasterDAO;
import cn.superion.cssd.entity.CssdPhysicsMaster;
import cn.superion.util.SessionUtil;

public class CssdPhysicsImpl implements ICssdPhysics{
    private CssdPhysicsMasterDAO cssdPhysicsMasterDAO;
    private CssdPhysicsFilesDAO cssdPhysicsFilesDAO;
    private IPicFileUtil picFileUtil;
    private String modual="physics";
	@Override
	public ReObject findPhysicsByEquipNameInRange(ParameterObject fparam) {
		Map<String,Object> lmapCondition= fparam.getConditions();
		String lstrEquipName=(String)lmapCondition.get("equipmentName");
		Date ldateFrom=(Date)lmapCondition.get("dateFrom");
		Date ldateTo=(Date)lmapCondition.get("dateTo");
		ReObject ro = new ReObject("查询物理检测记录");
		List<CssdPhysicsMaster> llstPhysics=cssdPhysicsMasterDAO.findByEquipNameInDateRange(
				SessionUtil.getUnitsCode(),lstrEquipName,
				ldateFrom, ldateTo,ro,fparam.getStart(),fparam.getItemsPerPage());
		ro.setData(llstPhysics);
		return ro;	
	}
	public ReObject  loadPic(String fstrPhyMasterAutoId) {
		ReObject ro = new ReObject("调用图片数据");
		try{
		   ro.setData(picFileUtil.loadPic(fstrPhyMasterAutoId, modual));
		}catch(Exception e){
			e.printStackTrace();
			ro.setError(e.getMessage());
		}
		return ro;
	}
	@Override
	public ReObject savePhysicsMaster(CssdPhysicsMaster fphysicsMaster) {
		ReObject ro = new ReObject("保存物理检测记录");
		fphysicsMaster.setUnitsCode(SessionUtil.getUnitsCode());
		fphysicsMaster.setOperateDate(new Date());
		fphysicsMaster.setOperator(SessionUtil.getPersonId());
		cssdPhysicsMasterDAO.attachDirty(fphysicsMaster);
		picFileUtil.savePic(fphysicsMaster.getAutoId(),modual, fphysicsMaster.getPics());
		return ro;
	}	
	
	@Override
	public ReObject checkPhysicsMaster(String fstrAutoId,String fstrCurrentState) {
		ReObject ro = new ReObject("审核物理检测记录");
		
		CssdPhysicsMaster cssdPhysicsMaster=cssdPhysicsMasterDAO.findById(fstrAutoId);
		cssdPhysicsMaster.setVerifyDate(new Date());
		cssdPhysicsMaster.setVerifier(SessionUtil.getPersonId());
		cssdPhysicsMaster.setCurrentStatus(fstrCurrentState);
		cssdPhysicsMasterDAO.update(cssdPhysicsMaster);		
		return ro;
	}
	@Override
	public ReObject delPhysicsMaster(String fstrAutoId) {
		ReObject ro = new ReObject("删除物理检测记录");
		cssdPhysicsMasterDAO.deleteByAutoId(fstrAutoId);
		return ro;
	}
	
	public void setCssdPhysicsMasterDAO(CssdPhysicsMasterDAO cssdPhysicsMasterDAO) {
		this.cssdPhysicsMasterDAO = cssdPhysicsMasterDAO;
	}
	public CssdPhysicsMasterDAO getCssdPhysicsMasterDAO() {
		return cssdPhysicsMasterDAO;
	}
	public void setCssdPhysicsFilesDAO(CssdPhysicsFilesDAO cssdPhysicsFilesDAO) {
		this.cssdPhysicsFilesDAO = cssdPhysicsFilesDAO;
	}
	public CssdPhysicsFilesDAO getCssdPhysicsFilesDAO() {
		return cssdPhysicsFilesDAO;
	}

	public void setPicFileUtil(IPicFileUtil picFileUtil) {
		this.picFileUtil = picFileUtil;
	}

	public IPicFileUtil getPicFileUtil() {
		return picFileUtil;
	}


}
