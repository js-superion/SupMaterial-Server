package cn.superion.cssd.system.service;
import java.util.List;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdDeptVsPackageClassDAO;
import cn.superion.cssd.entity.CssdDeptVsPackageClass;
import cn.superion.util.SessionUtil;

public class CssdDeptVsPackageClassImpl implements ICssdDeptVsPackageClass {
	private CssdDeptVsPackageClassDAO cssdDeptVsPackageClassDAO;
	
	public CssdDeptVsPackageClassDAO getCssdDeptVsPackageClassDAO() {
		return cssdDeptVsPackageClassDAO;
	}

	public void setCssdDeptVsPackageClassDAO(
			CssdDeptVsPackageClassDAO cssdDeptVsPackageClassDAO) {
		this.cssdDeptVsPackageClassDAO = cssdDeptVsPackageClassDAO;
	}

	@Override
	public ReObject findPackageCLassListByDeptCode(String fstrDeptCode) {
		ReObject ro = new ReObject();
		ro.setAction("查询科室对照包分类");
		String unitsCode = SessionUtil.getUnitsCode();
		List<CssdDeptVsPackageClass>  lst=cssdDeptVsPackageClassDAO.findByDeptCode(unitsCode,fstrDeptCode);
		ro.setData(lst);
		return ro;
	}
	public ReObject findByDeptCodeClassCode(String fstrDeptCode,String classCode) {
		ReObject ro = new ReObject();
		ro.setAction("查询该科室下是否有重复的分类编码");
		String unitsCode = SessionUtil.getUnitsCode();
		List<CssdDeptVsPackageClass>  lst=cssdDeptVsPackageClassDAO.findByDeptCode(unitsCode,fstrDeptCode,classCode);
		ro.setData(lst);
		return ro;
	}
	@Override
	public ReObject saveDeptVsPackageClass(String fstrDeptCode, String fstrClassCode,String fstrClassName) {
		ReObject ro = new ReObject();
		ro.setAction("保存科室授权用户");
		String unitsCode=SessionUtil.getUnitsCode();
		CssdDeptVsPackageClass su=new CssdDeptVsPackageClass(unitsCode,fstrDeptCode,fstrClassCode,fstrClassName);				
		cssdDeptVsPackageClassDAO.save(su);
		return ro;
	}
	
	@Override
	public ReObject delDeptVsPackageClassById(String fstrDeptCode, String fstrClassCode) {
		ReObject ro = new ReObject();
		ro.setAction("删除科室用户信息");
		String unitsCode=SessionUtil.getUnitsCode();
		cssdDeptVsPackageClassDAO.delById(unitsCode, fstrDeptCode, fstrClassCode);
		return ro;
	}
	
}
