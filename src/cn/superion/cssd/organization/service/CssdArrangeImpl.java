package cn.superion.cssd.organization.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdWorkClassDictDAO;
import cn.superion.center.config.dao.CdWorkClassTimeDictDAO;
import cn.superion.center.config.entity.CdWorkClassDict;
import cn.superion.center.config.entity.CdWorkClassTimeDict;
import cn.superion.center.deptPerson.dao.CdPersonDictDAO;
import cn.superion.center.deptPerson.entity.CdPersonDict;
import cn.superion.cssd.dao.CssdPersonArrangeDAO;
import cn.superion.cssd.entity.CssdPersonArrange;
import cn.superion.util.SessionUtil;

public class CssdArrangeImpl implements ICssdArrange {
	private CssdPersonArrangeDAO cssdPersonArrangeDAO;
	private CdPersonDictDAO cdPersonDictDAO;
	private CdWorkClassDictDAO cdWorkClassDictDAO;
	private CdWorkClassTimeDictDAO cdWorkClassTimeDictDAO;

	@Override
	public ReObject findPersonArrangeListByCondition(ParameterObject fparam) {
		ReObject ro = new ReObject("查询排班列表");
		Map<String, Object> lmapCondition = fparam.getConditions();
		List<CssdPersonArrange> llstArranges = cssdPersonArrangeDAO
				.findArrangeListByCondition(lmapCondition, SessionUtil
						.getUnitsCode());
		ro.setData(llstArranges);
		return ro;
	}

	@Override
	public ReObject findPersonListByCondition(ParameterObject fparam) {
		ReObject ro = new ReObject("查询人员列表");
		Map<String, Object> lmapCondition = fparam.getConditions();
		String lstrCondition = "";
		String lstrPersonName = (String) lmapCondition.get("personName");
		String lstrPersonCode = (String) lmapCondition.get("personCode");
		if (lstrPersonName == null || lstrPersonName.trim().equals("")) {
			lstrCondition = " where personCode like '" + lstrPersonCode + "%'";
		} else {
			lstrCondition = " where name like '" + lstrPersonName + "%'";
		}
		lstrCondition += " and unitsCode='" + SessionUtil.getUnitsCode()
				+ "' and deptCode='" + SessionUtil.getSysUser().getDeptCode()
				+ "'";
		List<CdPersonDict> llstArranges = cdPersonDictDAO
				.findByCondition(lstrCondition);
		ro.setData(llstArranges);
		return ro;
	}

	@Override
	public ReObject findWorkClassList() {
		ReObject ro = new ReObject("查询班次列表");
		List<CdWorkClassDict> llstCdWorkClasss = cdWorkClassDictDAO
				.findByProperty("unitsCode", SessionUtil.getUnitsCode());
		ro.setData(llstCdWorkClasss);
		return ro;
	}

	@Override
	public ReObject findWorkClassTimesByMainId(String fstrMainId) {
		ReObject ro = new ReObject("查询时间段列表");
		List<CdWorkClassTimeDict> llstCdWorkClassTimes = cdWorkClassTimeDictDAO
				.findByProperty("mainAutoId", fstrMainId);
		ro.setData(llstCdWorkClassTimes);
		return ro;
	}

	@Override
	public ReObject saveWorkClassAndTimeSpan(CdWorkClassDict fcdWorkClassDict,
			List<CdWorkClassTimeDict> flstTimesSpans) {
		ReObject ro = new ReObject("保存班次设置信息");
		cdWorkClassDictDAO.attachDirty(fcdWorkClassDict);
		cdWorkClassTimeDictDAO.saveTimeSpans(fcdWorkClassDict.getAutoId(),
				flstTimesSpans);
		return ro;
	}

	@Override
	public ReObject delWorkClassById(String fstrAutoId) {
		ReObject ro = new ReObject("删除班次设置信息");
		cdWorkClassDictDAO.deleteById(fstrAutoId);
		cdWorkClassTimeDictDAO.delByMainAutoId(fstrAutoId);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findPersonArrangeByPersonId(String fstrPersonId) {
		ReObject ro = new ReObject("查询班次设置信息");
		List<CssdPersonArrange> llstArranges = cssdPersonArrangeDAO
				.findByPersonId(fstrPersonId, SessionUtil.getUnitsCode());
		ro.setData(llstArranges);
		return ro;
	}

	@Override
	public ReObject savePersonArrangeList(List<CssdPersonArrange> flstArranges) {
		ReObject ro = new ReObject("保存排班信息");
		// cssdPersonArrangeDAO.deleteByPersonId(fstrPersonId,SessionUtil.getUnitsCode());
		for (CssdPersonArrange cssdPersonArrange : flstArranges) {
			String autoId = cssdPersonArrange.getAutoId();
			boolean isAdd = autoId == null || "".equals(autoId);
			if (isAdd) {
				// 新增
				cssdPersonArrange.setOperateDate(new Date());
				cssdPersonArrange.setOperator(SessionUtil.getPersonId());
				cssdPersonArrange.setUnitsCode(SessionUtil.getUnitsCode());
				cssdPersonArrangeDAO.save(cssdPersonArrange);
			}
		}
		// String unitsCode=SessionUtil.getUnitsCode();
		// cssdPersonArrangeDAO.saveOrUpdateArrangeList(unitsCode, fstrPersonId,
		// flstArranges);
		return ro;
	}

	@Override
	public ReObject saveModPersonArrangeList(String fstrPersonId,
			List<CssdPersonArrange> flstArranges) {
		ReObject ro = new ReObject("修改排班信息");
		cssdPersonArrangeDAO.deleteByPersonId(fstrPersonId, SessionUtil
				.getUnitsCode());
		for (CssdPersonArrange cssdPersonArrange : flstArranges) {
			cssdPersonArrange.setOperateDate(new Date());
			cssdPersonArrange.setOperator(SessionUtil.getPersonId());
			cssdPersonArrange.setUnitsCode(SessionUtil.getUnitsCode());
			cssdPersonArrange.setPersonId(fstrPersonId);
			cssdPersonArrangeDAO.save(cssdPersonArrange);
		}
		return ro;
	}

	@Override
	public ReObject findPersonArrangeByPersonIdInRange(String fstrPersonId,
			Date fdateFrom, Date fdateTo) {
		ReObject ro = new ReObject("删除班次设置信息");
		List<CssdPersonArrange> llstArranges = cssdPersonArrangeDAO
				.findByPersonIdInRange(fstrPersonId,
						SessionUtil.getUnitsCode(), fdateFrom, fdateTo);
		ro.setData(llstArranges);
		return ro;
	}

	public void setCssdPersonArrangeDAO(
			CssdPersonArrangeDAO cssdPersonArrangeDAO) {
		this.cssdPersonArrangeDAO = cssdPersonArrangeDAO;
	}

	public CssdPersonArrangeDAO getCssdPersonArrangeDAO() {
		return cssdPersonArrangeDAO;
	}

	public CdPersonDictDAO getCdPersonDictDAO() {
		return cdPersonDictDAO;
	}

	public void setCdPersonDictDAO(CdPersonDictDAO cdPersonDictDAO) {
		this.cdPersonDictDAO = cdPersonDictDAO;
	}

	public void setCdWorkClassDictDAO(CdWorkClassDictDAO cdWorkClassDictDAO) {
		this.cdWorkClassDictDAO = cdWorkClassDictDAO;
	}

	public CdWorkClassDictDAO getCdWorkClassDictDAO() {
		return cdWorkClassDictDAO;
	}

	public void setCdWorkClassTimeDictDAO(
			CdWorkClassTimeDictDAO cdWorkClassTimeDictDAO) {
		this.cdWorkClassTimeDictDAO = cdWorkClassTimeDictDAO;
	}

	public CdWorkClassTimeDictDAO getCdWorkClassTimeDictDAO() {
		return cdWorkClassTimeDictDAO;
	}

}
