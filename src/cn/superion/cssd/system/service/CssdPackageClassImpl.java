package cn.superion.cssd.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdPackageClassDictDAO;
import cn.superion.cssd.entity.CssdPackageClassDict;
import cn.superion.cssd.entity.CssdPackageDictDetail;

public class CssdPackageClassImpl implements IcssdPackageClass {
	private static final Log log = LogFactory
			.getLog(CssdPackageClassImpl.class);

	private CssdPackageClassDictDAO cssdPackageClassDictDAO;

	public CssdPackageClassDictDAO getCssdPackageClassDictDAO() {
		return cssdPackageClassDictDAO;
	}

	public void setCssdPackageClassDictDAO(
			CssdPackageClassDictDAO cssdPackageClassDictDAO) {
		this.cssdPackageClassDictDAO = cssdPackageClassDictDAO;
	}

	@Override
	public ReObject delPackageClassByClassCode(String classCode) {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("删除物品包分类信息");
		boolean flag=true;
		try {
			List<CssdPackageClassDict> cssClassList=cssdPackageClassDictDAO.findByProperty("parentCode", classCode);
			List<CssdPackageDictDetail> cssRuleMasterList=cssdPackageClassDictDAO.findByPropertyMaster("packageClass", classCode);
			if(null !=cssClassList && cssClassList.size()>0){
				flag=false;
				obj.setError("此物品包分类下存在子文件分类，不能删除！");
			}
			else if(null !=cssRuleMasterList && cssRuleMasterList.size()>0)
			{
				flag=false;
				obj.setError("此物品包分类在物品包档案字典中被引用，不能删除！");
			}
			else
			{
				CssdPackageClassDict cssdf=cssdPackageClassDictDAO.findById(classCode);		
				//删除当前结点
				cssdPackageClassDictDAO.delete(cssdf);
				if(null != cssdf.getParentCode() && !cssdf.getParentCode().equals("00")){
					//判断父级结点是否存在子结点
					CssdPackageClassDict pCssdFileClassDict=cssdPackageClassDictDAO.findById(cssdf.getParentCode());
					List<CssdPackageClassDict> bProviderClassList=cssdPackageClassDictDAO.findByProperty("parentCode",cssdf.getParentCode());
					if(null == bProviderClassList || bProviderClassList.size()==0){
						pCssdFileClassDict.setEndSign("1");
					}
				}
			}
		} catch (RuntimeException re) {
			log.error("删除文件分类信息失败", re);
			obj.setError("删除文件分类信息失败");
			flag=false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;
	}

	@Override
	public ReObject findPackageClassDict() {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("查询所有物品包分类列表");
		boolean flag=true;
		try {
			List<CssdPackageClassDict> list=cssdPackageClassDictDAO.findFileTypeAll();
			obj.setData(list);
		} catch (RuntimeException re) {
			log.error("查询所有文本分类列表失败", re);
			obj.setError("查询所有文本分类列表失败"+re);
			flag=false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;			
	}

	@Override
	public ReObject findMaxClassCodeByParentCode(String parentCode) {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("根据父级编码查询子节点中最大的classCode");
		boolean flag=true;
		try {
			String classCode=cssdPackageClassDictDAO.findMaxClassCodeById(parentCode);
			List<String> list=new ArrayList<String>();
			list.add(classCode);
			obj.setData(list);
		} catch (RuntimeException re) {
			log.error("根据父级编码查询子节点中最大的classCode失败", re);
			obj.setError("根据父级编码查询子节点中最大的classCode失败");
			flag=false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;			
	}

	//保存
	@Override
	public ReObject savePackageClass(CssdPackageClassDict cssdPackageClassDict,
			String fstrType) {
		// TODO Auto-generated method stub
		ReObject obj = new ReObject();
		obj.setAction("保存物品包分类信息");
		boolean flag = true;
		try {
			if (fstrType != null && fstrType.equals("add")) {
				String classCode = cssdPackageClassDict.getClassCode();
				List<CssdPackageClassDict> list = cssdPackageClassDictDAO
						.findByProperty("parentCode", classCode);
				if (list == null || list.size() == 0) {
					cssdPackageClassDict.setEndSign("1");
					if (cssdPackageClassDict.getParentCode() == null
							|| cssdPackageClassDict.getParentCode()
									.equals("00")) {
						cssdPackageClassDict.setParentCode("00");
						cssdPackageClassDict.setCodeLevel("1");
					} else {
						CssdPackageClassDict cfc = cssdPackageClassDictDAO
								.findById(cssdPackageClassDict.getParentCode());
						cssdPackageClassDict.setCodeLevel(""
								+ (Integer.parseInt(cfc.getCodeLevel()) + 1));
						if (cfc.getEndSign().equals("1")) {
							cfc.setEndSign("1");
						}
					}
					cssdPackageClassDictDAO.save(cssdPackageClassDict);
				} else {
					flag = false;
					obj.setError("已经存在相同的分类编码!");
				}
			} else {
				obj.setAction("修改文本分类信息");
				cssdPackageClassDictDAO.updateFileType(cssdPackageClassDict);
			}
		} catch (RuntimeException re) {
			log.error("失败", re);
			obj.setError("失败" + re);
			flag = false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;
	}

}
