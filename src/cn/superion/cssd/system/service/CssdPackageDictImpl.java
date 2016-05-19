package cn.superion.cssd.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.cssd.dao.CssdPackageClassDictDAO;
import cn.superion.cssd.dao.CssdPackageDictDAO;
import cn.superion.cssd.dao.CssdPackageDictDetailDAO;
import cn.superion.cssd.dao.VCssdPackageDictDetailDAO;
import cn.superion.cssd.entity.CssdPackageClassDict;
import cn.superion.cssd.entity.CssdPackageDict;
import cn.superion.cssd.entity.CssdPackageDictDetail;
import cn.superion.cssd.entity.CssdStockDetail;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.cssd.entity.VCssdPackageDictDetail;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class CssdPackageDictImpl implements IcssdPackageDict 
{
	private static final Log log = LogFactory.getLog(CssdPackageDictImpl.class);
	private CssdPackageDictDAO	cssdPackageDictDAO; 
	private CssdPackageDictDetailDAO cssdPackageDictDetailDAO;
	private CssdPackageClassDictDAO cssdPackageClassDictDAO;
	private VCssdPackageDictDetailDAO vCssdPackageDictDetailDAO;

	public VCssdPackageDictDetailDAO getvCssdPackageDictDetailDAO() {
		return vCssdPackageDictDetailDAO;
	}

	public void setvCssdPackageDictDetailDAO(
			VCssdPackageDictDetailDAO vCssdPackageDictDetailDAO) {
		this.vCssdPackageDictDetailDAO = vCssdPackageDictDetailDAO;
	}

	public CssdPackageClassDictDAO getCssdPackageClassDictDAO() {
		return cssdPackageClassDictDAO;
	}

	public void setCssdPackageClassDictDAO(
			CssdPackageClassDictDAO cssdPackageClassDictDAO) {
		this.cssdPackageClassDictDAO = cssdPackageClassDictDAO;
	}

	public CssdPackageDictDetailDAO getCssdPackageDictDetailDAO() {
		return cssdPackageDictDetailDAO;
	}

	public void setCssdPackageDictDetailDAO(
			CssdPackageDictDetailDAO cssdPackageDictDetailDAO) {
		this.cssdPackageDictDetailDAO = cssdPackageDictDetailDAO;
	}

	public CssdPackageDictDAO getCssdPackageDictDAO() {
		return cssdPackageDictDAO;
	}

	public void setCssdPackageDictDAO(CssdPackageDictDAO cssdPackageDictDAO) {
		this.cssdPackageDictDAO = cssdPackageDictDAO;
	}

	@Override
	public ReObject savePackageDict(CssdPackageDict fCssdPackageDict,
			List<CssdPackageDictDetail> flstCssdPackageDictDetail) {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("保存物品包档案信息");
		boolean flag=true;
		
		try {
			if(fCssdPackageDict==null)
				throw new RuntimeException("物品包档案字典不能为空！");
			if(flstCssdPackageDictDetail==null||flstCssdPackageDictDetail.isEmpty())
				throw new RuntimeException("物品包档案字典明细不能为空！");
			if(fCssdPackageDict.getPackageClass()==null||fCssdPackageDict.equals(""))
				throw new RuntimeException("物品包分类不能为空！");
			if(fCssdPackageDict.getStopDate()==null)
				throw new RuntimeException("停用日期不能为空！");
			SysUser user = SessionUtil.getSysUser();
			String unitsCode = user.getUnitsCode();
			String personId=user.getPersonId();
			String packageId=fCssdPackageDict.getPackageId();
			if(packageId==null||"".equals(packageId))
			{
				//新增
				packageId = cssdPackageDictDAO.nextValue("SEQ_CSSD_PACKAGE_DICT").toString();	
				fCssdPackageDict.setPackageId(packageId);
				fCssdPackageDict.setUnitsCode(unitsCode);
				fCssdPackageDict.setStartDate(new Date());
				fCssdPackageDict.setCreatePerson(personId);
				fCssdPackageDict.setCreateDate(new Date());
				cssdPackageDictDAO.save(fCssdPackageDict);
				short i=0;
				for(CssdPackageDictDetail cdd:flstCssdPackageDictDetail)
				{
					if(cdd.getMaterialClass()==null||cdd.getMaterialClass().equals(""))
						throw new RuntimeException("物资分类不能为空！");
					if(cdd.getMaterialId()==null||cdd.getMaterialId().equals(""))
						throw new RuntimeException("物资ID不能为空！");
					if(cdd.getAmount()<=0)
						throw new RuntimeException("物品包档案字典明细,物品数量不能小与零！");
					cdd.setPackageId(packageId);
					cdd.setSerialNo(i++);
					cdd.setUnitsCode(unitsCode);
					cssdPackageDictDetailDAO.save(cdd);
				}
			}
			else
			{
				//修改
				for(CssdPackageDictDetail cdd:flstCssdPackageDictDetail)
				{
					if(cdd.getMaterialClass()==null||cdd.getMaterialClass().equals(""))
						throw new RuntimeException("物资分类不能为空！");
					if(cdd.getMaterialId()==null||cdd.getMaterialId().equals(""))
						throw new RuntimeException("物资ID不能为空！");
					if(cdd.getAmount()<=0)
						throw new RuntimeException("物品包档案字典明细,物品数量不能小与零！");
				}
				cssdPackageDictDAO.update(fCssdPackageDict);
				cssdPackageDictDetailDAO.delete(unitsCode, packageId);
				short i=0;
				for(CssdPackageDictDetail cdd:flstCssdPackageDictDetail)
				{
					cdd.setPackageId(packageId);
					cdd.setSerialNo(i++);
					cdd.setUnitsCode(unitsCode);
					cssdPackageDictDetailDAO.save(cdd);
				}
			}
		} catch (RuntimeException re) {
			// TODO Auto-generated catch block
			log.error("失败", re);
			obj.setError("失败"+re);
			flag=false;
			throw re;
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fCssdPackageDict);
		data.add(flstCssdPackageDictDetail);
		obj.setData(data);
		obj.setSuccess(flag);
		return obj;
	}
	
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
	public ReObject findPackageDictListByClass(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物品包列表");
		String unitsCode=SessionUtil.getUnitsCode();
		Map<String,Object> map=fparameter.getConditions();
		Object packageClass=map.get("packageClass")==null?"":map.get("packageClass");
		Object packageId=map.get("packageId")==null?"":map.get("packageId");
		Object fiveInputCode=map.get("fiveInputCode")==null?"":map.get("fiveInputCode");
		Object phoInputCode=map.get("phoInputCode")==null?"":map.get("phoInputCode");
		int start=fparameter.getStart();
		int itemsPerPage=fparameter.getItemsPerPage();
		// 组装查询条件
		String advanceHql=fparameter.getConditionSql();
		StringBuilder condition=new StringBuilder();
		condition.append(" where unitsCode = '"+unitsCode+"'");
		if(!packageClass.equals("") && packageClass.toString().trim().length()>0&& !packageClass.equals("00"))
			condition.append(" and packageClass like '" + packageClass + "%'");
		if(!packageId.equals("") && packageId.toString().trim().length()>0)
			condition.append(" and packageId =: packageId");
		if(!phoInputCode.equals("") && phoInputCode.toString().trim().length()>0)
			condition.append(" and phoInputCode like '"+phoInputCode+"%')");
		if(!fiveInputCode.equals("") && fiveInputCode.toString().trim().length()>0)
			condition.append(" and fiveInputCode like '"+fiveInputCode+"%')");
		
		if(advanceHql!=null && advanceHql.trim().length()>0)
			condition.append(advanceHql);
		List<CssdPackageDict> list = cssdPackageDictDAO.findByCondition(condition.toString(), start, itemsPerPage);
		int count=cssdPackageDictDAO.countByCondition(condition.toString());
		ro.setData(list);
		ro.setCount(count,itemsPerPage);
		return ro;
		
//		int start=parameter.getStart();
//		int limit=parameter.getItemsPerPage();
//		Object[] objs=cssdPackageDictDAO.findAutoIdsByCondition(start,limit, SessionUtil.getUnitsCode(), parameter.getConditions());
//		int count=Integer.parseInt(objs[0].toString());
//		ro.setCount(count, limit);
//		ro.setData((List)objs[1]);
//		return ro;
	}

	@Override
	public ReObject delPackageDictById(String fstrPackageId) {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("删除物品包字典信息");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		boolean flag=true;
		
		List<CssdStockMaster> cssdPackageDictList=cssdPackageDictDAO.findByPropertyMaster("packageNo", fstrPackageId);
		try {
			if(cssdPackageDictList!=null&&cssdPackageDictList.size()>0)
			{
				flag=false;
				obj.setError("此包已使用，不能删除！");
			}
			else
			{
				CssdPackageDict cssdf=cssdPackageDictDAO.findById(unitsCode,fstrPackageId);		
				//删除当前结点
				cssdPackageDictDAO.delete(cssdf);
			}
		} catch (RuntimeException re) {
			log.error("删除物品包信息失败", re);
			obj.setError("删除物品包信息失败");
			flag=false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;
	}

	@Override
	public ReObject findPackageDetailByCode(String fstrPackageId) {
		// TODO Auto-generated method stub
		ReObject obj=new ReObject();
		obj.setAction("按照PackageId查询物品包明细信息");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		if(fstrPackageId==null||fstrPackageId.equals(""))
			throw new RuntimeException("物品包档案字典明细,packageId不能为空！");	
		List<VCssdPackageDictDetail> vCssdPackageDictDetail=vCssdPackageDictDetailDAO.findByPackageId(unitsCode,fstrPackageId);
		obj.setData(vCssdPackageDictDetail);
		return obj;
	}

	@Override
	public ReObject findPackageId(List<Object> packageId) {
		// TODO Auto-generated method stubCSSD_STOCK_MASTER
		ReObject obj=new ReObject();
		obj.setAction("查看物品包详细信息");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		List<CssdStockMaster> cssdStockMasterList=new ArrayList<CssdStockMaster>();
		for(int i=0;i<packageId.size();i++)
		{
			Object[] o=(Object[])(packageId.get(i));
			//物品包ID
			String fstrPackageId = (String) o[1];
			//物品包数量
			Integer i1 = (Integer) o[2];
			//清洗主记录Id
			String autoId= (String) o[3];
			for(int j=0;j<i1;j++)
			{
				//查数据库 
				//明细
				List<VCssdPackageDictDetail> cssdPackageDictDetailList= new ArrayList<VCssdPackageDictDetail>();
				cssdPackageDictDetailList= vCssdPackageDictDetailDAO.findByPackageId(unitsCode, fstrPackageId);
				//主记录
				List<CssdPackageDict> cssdPackageDictList= new ArrayList<CssdPackageDict>();
				cssdPackageDictList=cssdPackageClassDictDAO.findPackageIdM(unitsCode, fstrPackageId);
				cssdStockMasterList.add(turn(cssdPackageDictList.get(0),cssdPackageDictDetailList,autoId));
			}
		}
		obj.setData(cssdStockMasterList);
		return obj;
	}
	
	/**
	 * 转型
	 * @param cssdPackageDict
	 * @param cssdPackageDictDetailList CSSD_STOCK_DETAIL
	 * @return
	 */
	public CssdStockMaster turn(CssdPackageDict cssdPackageDict,List<VCssdPackageDictDetail> cssdPackageDictDetailList,String autoId)
	{
		List<CssdStockDetail> cssdStockDetailList=new ArrayList<CssdStockDetail>();
		CssdStockMaster cssdStockMaster=new CssdStockMaster();
		cssdStockMaster.setUnitsCode(cssdPackageDict.getUnitsCode());
		cssdStockMaster.setPackageClass(cssdPackageDict.getPackageClass());
		cssdStockMaster.setPackageId(cssdPackageDict.getPackageId());
		cssdStockMaster.setPackageName(cssdPackageDict.getPackageName());
		cssdStockMaster.setPackageMode(cssdPackageDict.getPackageMode());
		cssdStockMaster.setPackageUnits(cssdPackageDict.getPackageUnits());
		cssdStockMaster.setTradePrice(cssdPackageDict.getTradePrice());
		cssdStockMaster.setMaterialFee(cssdPackageDict.getMaterialFee());
		cssdStockMaster.setSterilizeFee(cssdPackageDict.getSterilizeFee());
		for (VCssdPackageDictDetail cssdPackageDictDetail : cssdPackageDictDetailList) {
			CssdStockDetail cssdStockDetail=new CssdStockDetail();
			cssdStockDetail.setMaterialClass(cssdPackageDictDetail.getMaterialClass());
			cssdStockDetail.setMaterialId(cssdPackageDictDetail.getMaterialId());
			cssdStockDetail.setMaterialCode(cssdPackageDictDetail.getMaterialCode());
			cssdStockDetail.setMaterialName(cssdPackageDictDetail.getMaterialName());
			cssdStockDetail.setMaterialSpec(cssdPackageDictDetail.getMaterialSpec());
			cssdStockDetail.setMaterialUnits(cssdPackageDictDetail.getMaterialUnits());
			cssdStockDetail.setFactoryCode(cssdPackageDictDetail.getFactoryCode());
			cssdStockDetail.setTradePrice(cssdPackageDictDetail.getTradePrice());
			cssdStockDetail.setAmount(cssdPackageDictDetail.getAmount());
			cssdStockDetail.setTradeMoney(Double.parseDouble(cssdPackageDictDetail.getTradeMoney().toString()));
			cssdStockDetail.setMaterialSign(cssdPackageDictDetail.getMaterialSign());
			cssdStockDetail.setSourceAutoId(autoId);
			cssdStockDetailList.add(cssdStockDetail);
		}
		cssdStockMaster.setStockDetailList(cssdStockDetailList);
		return cssdStockMaster;
	}
	
}
