package cn.superion.cssd.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdFileClassDictDAO;
import cn.superion.cssd.entity.CssdFileClassDict;
import cn.superion.cssd.entity.CssdWorkRuleMaster;

public class CssdFileTypeImpl implements IcssdFileType
{
	private static final Log log = LogFactory.getLog(CssdFileTypeImpl.class);
	private CssdFileClassDictDAO cssdFileClassDictDAo;

	public CssdFileClassDictDAO getCssdFileClassDictDAo() {
		return cssdFileClassDictDAo;
	}

	public void setCssdFileClassDictDAo(CssdFileClassDictDAO cssdFileClassDictDAo) {
		this.cssdFileClassDictDAo = cssdFileClassDictDAo;
	}

	//保存方法
	@Override
	public ReObject saveFileType(CssdFileClassDict cssdFileClassDict,String fstrParentCode) {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("保存文本分类信息");
		boolean flag=true;
		try {
			if(fstrParentCode!=null&&fstrParentCode.equals("add"))
			{
				String classCode=cssdFileClassDict.getClassCode();
				List<CssdFileClassDict> list=cssdFileClassDictDAo
				.findByProperty("parentCode", classCode);
				if(list==null||list.size()==0)
				{
					cssdFileClassDict.setEndSign("1");
					if(cssdFileClassDict.getParentCode()==null||cssdFileClassDict.getParentCode().equals("00"))
					{
						cssdFileClassDict.setParentCode("00");
						cssdFileClassDict.setCodeLevel("1");
					}
					else
					{
						CssdFileClassDict cfc=cssdFileClassDictDAo.findById(cssdFileClassDict.getParentCode());
						cssdFileClassDict.setCodeLevel(""+(Integer.parseInt(cfc.getCodeLevel())+1));
						if(cfc.getEndSign().equals("1")){
							cfc.setEndSign("1");
						}
					}
					cssdFileClassDictDAo.save(cssdFileClassDict);
				}else {
					flag=false;
					obj.setError("已经存在相同的分类编码!");
				}
			}else {
				obj.setAction("修改文本分类信息");
				cssdFileClassDictDAo.updateFileType(cssdFileClassDict);
			}
		} 	catch (RuntimeException re) {
			log.error("失败", re);
			obj.setError("失败"+re);
			flag=false;
			throw re;
		}
		 obj.setSuccess(flag);
		 return obj;
	}

	//查询所有方法
	@Override
	public ReObject findFileTypeAll() {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("查询所有文本分类列表");
		boolean flag=true;
		try {
			List<CssdFileClassDict> list=cssdFileClassDictDAo.findAll();
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
	public ReObject findMaxClassCodeByParentCode(String ParentCode) {
		ReObject obj=new ReObject();
		obj.setAction("根据父级编码查询子节点中最大的classCode");
		boolean flag=true;
		try {
			String classCode=cssdFileClassDictDAo.findMaxClassCodeById(ParentCode);
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

	@Override
	public ReObject delFileTypeByClassCode(String classCode) {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("删除文件分类信息");
		boolean flag=true;
		try {
			List<CssdFileClassDict> cssClassList=cssdFileClassDictDAo.findByProperty("parentCode", classCode);
			List<CssdWorkRuleMaster> cssRuleMasterList=cssdFileClassDictDAo.findByPropertyMaster("classCode", classCode);
			if(null !=cssClassList && cssClassList.size()>0){
				flag=false;
				obj.setError("此文件分类下存在子文件分类，不能删除！");
			}
			else if(null !=cssRuleMasterList && cssRuleMasterList.size()>0)
			{
				flag=false;
				obj.setError("此文件分类在工作规范记录中被引用，不能删除！");
			}
			else
			{
				CssdFileClassDict cssdf=cssdFileClassDictDAo.findById(classCode);		
				//删除当前结点
				cssdFileClassDictDAo.delete(cssdf);
				if(null != cssdf.getParentCode() && !cssdf.getParentCode().equals("00")){
					//判断父级结点是否存在子结点
					CssdFileClassDict pCssdFileClassDict=cssdFileClassDictDAo.findById(cssdf.getParentCode());
					List<CssdFileClassDict> bProviderClassList=cssdFileClassDictDAo.findByProperty("parentCode",cssdf.getParentCode());
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
	
}
