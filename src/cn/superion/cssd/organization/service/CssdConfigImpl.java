package cn.superion.cssd.organization.service;

import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.deptPerson.dao.CdPersonDictDAO;
import cn.superion.center.deptPerson.entity.CdPersonDict;
import cn.superion.cssd.dao.CssdPersonTrainDAO;
import cn.superion.cssd.dao.CssdPersonTrainFilesDAO;
import cn.superion.cssd.entity.CssdPersonTrain;
import cn.superion.cssd.entity.CssdPersonTrainFiles;
import cn.superion.util.SessionUtil;

public class CssdConfigImpl implements ICssdConfig{
    private CdPersonDictDAO cdPersonDictDao;
    private CssdPersonTrainDAO cssdPersonTrainDAO;
    private CssdPersonTrainFilesDAO cssdPersonTrainFilesDAO;
	@Override
	public ReObject findPersonByCodeAndName(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的人员列表");
		fparameter.setItemsPerPage(1000);
		Map<String,Object> lmapConditon=fparameter.getConditions();
		String lstrConditon=" where unitsCode='%s' and deptCode='%s' and personCode like '%s%s' and name like '%s%s' ";
		lstrConditon=String.format(lstrConditon, SessionUtil.getUnitsCode(),
				SessionUtil.getSysUser().getDeptCode(),
				lmapConditon.get("personCode"),"%",lmapConditon.get("name"),"%");
		List<CdPersonDict> llstPersons=cdPersonDictDao.findByCondition(lstrConditon, 0, fparameter.getItemsPerPage());
		ro.setData(llstPersons);
		return ro;
	}

	@Override
	public ReObject findTrainListByPersonId(String personId) {
		ReObject ro = new ReObject("根据人员Id查找对应的培训列表");
		String unitscode=SessionUtil.getUnitsCode();
		List<CssdPersonTrain> llstTrains=cssdPersonTrainDAO.findByUnitsCodePersonId(unitscode, personId);
		ro.setData(llstTrains);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findTrainFileByTrainAutoId(String fstrTrainAutoId) {
		ReObject ro = new ReObject("通过培训记录查询相关附件");
		List<CssdPersonTrainFiles> llstFiles= cssdPersonTrainFilesDAO.findByProperty("mainAutoId", fstrTrainAutoId);
		ro.setData(llstFiles);
		return ro;		
	}

	@Override
	public ReObject savePersonAndTrains(CdPersonDict fperson,
			List<CssdPersonTrain> flstTrains) {
		ReObject ro = new ReObject("保存人员信息和培训信息");

		String personId = SessionUtil.getPersonId();
		String unitsCode=SessionUtil.getUnitsCode();

		CdPersonDict cdPersonDict = cdPersonDictDao.findById(personId);
		if (cdPersonDict==null){
			ro.setError("该用户无权增加人员信息权限！");
			return ro;
		}
		fperson.setUnitsCode(unitsCode);
		fperson.setDeptCode(cdPersonDict.getDeptCode());
		fperson.setSubDeptCode(cdPersonDict.getSubDeptCode());
		fperson.setPersonClass(cdPersonDict.getPersonClass());
		
		cdPersonDictDao.attachDirty(fperson);
		cssdPersonTrainDAO.saveOrUpdateTrainList(unitsCode,fperson.getPersonId(),flstTrains);
		
		ro.setAction("保存人员信息和培训信息");
		return ro;
	}

	@Override
	public ReObject delPerson(String fstrPersonId) {
		cdPersonDictDao.delById(fstrPersonId);
		cssdPersonTrainDAO.deleteByPersonId(fstrPersonId,SessionUtil.getUnitsCode());
		ReObject ro = new ReObject("删除人员信息和培训信息");
		return ro;
	}

	@Override
	public ReObject checkPerson(String personId,String fstrCurrentState) {
		cdPersonDictDao.updateAuditingSignPersonById(fstrCurrentState,personId,SessionUtil.getUnitsCode());
		ReObject ro = new ReObject("审核人员信息");
		return ro;
	}
	
	
	public void setCdPersonDictDao(CdPersonDictDAO cdPersonDictDao) {
		this.cdPersonDictDao = cdPersonDictDao;
	}

	public CdPersonDictDAO getCdPersonDictDao() {
		return cdPersonDictDao;
	}

	public void setCssdPersonTrainDAO(CssdPersonTrainDAO cssdPersonTrainDAO) {
		this.cssdPersonTrainDAO = cssdPersonTrainDAO;
	}

	public CssdPersonTrainDAO getCssdPersonTrainDAO() {
		return cssdPersonTrainDAO;
	}

	public void setCssdPersonTrainFilesDAO(CssdPersonTrainFilesDAO cssdPersonTrainFilesDAO) {
		this.cssdPersonTrainFilesDAO = cssdPersonTrainFilesDAO;
	}

	public CssdPersonTrainFilesDAO getCssdPersonTrainFilesDAO() {
		return cssdPersonTrainFilesDAO;
	}





}
