package cn.superion.material.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.dao.CdMaterialDictLogDAO;
import cn.superion.center.material.entity.CdMaterialClass;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.center.material.entity.CdMaterialDictLog;
import cn.superion.center.provider.dao.CdProviderDAO;
import cn.superion.center.provider.entity.CdProvider;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.system.dao.SysUnitInforDAO;
import cn.superion.system.entity.SysUnitInfor;
import cn.superion.system.entity.SysUser;
import cn.superion.util.BaseToolDAO;
import cn.superion.util.SessionUtil;
import cn.superion.util.StringUtil;

public class MaterialDictImpl implements IMaterialDict {
	private CdMaterialDictDAO cdMaterialDictDAO;
	private CdMaterialDictLogDAO cdMaterialDictLogDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO; 
	private CdProviderDAO cdProviderDAO;
	public static final String UNITS_CODE_SOUTH = ReadPropertiesFile
	.getValue("UNITS_CODE_SOUTH");
	public static final String UNITS_CODE_NORTH = ReadPropertiesFile
	.getValue("UNITS_CODE_NORTH");
	public static final String NORTH_STORAGE = ReadPropertiesFile
	.getValue("NORTH_STORAGE"); //北院仓库的编码
	public static final String SOUTH_STORAGE = ReadPropertiesFile
	.getValue("SOUTH_STORAGE");//南院仓库的编码
	private BaseToolDAO toolDAO;

	public CdProviderDAO getCdProviderDAO() {
		return cdProviderDAO;
	}

	public void setCdProviderDAO(CdProviderDAO cdProviderDAO) {
		this.cdProviderDAO = cdProviderDAO;
	}

	public BaseToolDAO getToolDAO() {
		return toolDAO;
	}

	public void setToolDAO(BaseToolDAO toolDAO) {
		this.toolDAO = toolDAO;
	}
	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}

	private CdSysParamDAO cdSysParamDAO;
	private SysUnitInforDAO sysUnitInforDAO;

	public SysUnitInforDAO getSysUnitInforDAO() {
		return sysUnitInforDAO;
	}

	public void setSysUnitInforDAO(SysUnitInforDAO sysUnitInforDAO) {
		this.sysUnitInforDAO = sysUnitInforDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	@Override
	public ReObject deleteMaterialDict(String fstrMaterialId) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("删除物资档案信息");

		String strUnitsCode = SessionUtil.getUnitsCode();
		//查看现存量表，若存在记录，并弹出提示
		Object[] obj = materialCurrentStockDAO.findByUnitsCodeAndMaterialId(strUnitsCode, fstrMaterialId);
		if(obj != null){
			reObject.setError("该物资已发生库存，不能删除!");
			return reObject;
		}
		cdMaterialDictDAO.delById(strUnitsCode, fstrMaterialId);
		return reObject;
	}

	@Override
	public ReObject findByBarCode(String fstrBarCode) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("查找物资档案列表");

		String strUnitsCode = SessionUtil.getUnitsCode();
		List<CdMaterialDict> lstCdMaterialCd = cdMaterialDictDAO.findByBarCode(
				strUnitsCode, fstrBarCode);
		reObject.setData(lstCdMaterialCd);

		return reObject;
	}

	@Override
	public ReObject findByIntputCode(String fstrInputCode, String fstrInputType) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("查找物资档案列表");
		int limit = 100;
		String strUnitsCode = SessionUtil.getUnitsCode();
		List<CdMaterialDict> lstCdMaterialCd = cdMaterialDictDAO
				.findByInputCode("", strUnitsCode, fstrInputCode,
						fstrInputType, limit);
		reObject.setData(lstCdMaterialCd);
		return reObject;
	}

	@Override
	public ReObject findMaterialDictListByMaterialClass(
			ParameterObject fparameter) {
		ReObject reObject = new ReObject();
		reObject.setAction("查找物资档案列表");

		String strUnitsCode = SessionUtil.getUnitsCode();

		// 读取Map中的参数
		Map<String, Object> map = fparameter.getConditions();
		String materialClass = (String) map.get("materialClass");

		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");

		// 组装查询条件
		StringBuilder condition = new StringBuilder();
		condition.append(" where unitsCode = '" + strUnitsCode + "'");
		// 物资分类
		if (materialClass != null && !"".equals("materialClass")) {
			condition
					.append(" and materialClass like '" + materialClass + "%'");
		}
		// 拼音简码
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			condition
					.append("' and phoInputCode like '" + phoInputCode + "%')");
		}
		if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			condition.append("' and fiveInputCode like '" + fiveInputCode
					+ "%')");
		}

		// 查询结果并返回
		List<CdMaterialDict> lstCdMaterialDicts = cdMaterialDictDAO
				.findByCondition(condition.toString());
		reObject.setData(lstCdMaterialDicts);

		return reObject;
	}

	@Override
	public ReObject findMaterialDictLogById(String fstrMaterialId) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("查找物资档案日志");

		String strUnitsCode = SessionUtil.getUnitsCode();
		StringBuilder condition = new StringBuilder();
		condition.append(" where unitsCode = '" + strUnitsCode + "'");
		condition.append(" and materialId = '" + fstrMaterialId + "')");

		// 查询结果并返回
		List<CdMaterialDictLog> lstCdMaterialDictLogs = cdMaterialDictLogDAO
				.findByCondition(condition.toString());
		reObject.setData(lstCdMaterialDictLogs);

		return reObject;
	}

	@Override
	public ReObject saveMateriaDict(CdMaterialDict cdMaterialDict) {
		ReObject reObject = new ReObject();
		reObject.setAction("新增或修改物资档案信息");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String application = user.getAppCode();
		String personId = user.getPersonId();
		String materialId = cdMaterialDict.getMaterialId();
		boolean isAdd = materialId == null || materialId.equals("");
		String materialCode = cdMaterialDict.getMaterialCode();
		if (cdMaterialDict.getMaterialClass() == null
				|| "".equals(cdMaterialDict.getMaterialClass())) {
			reObject.setError("物资分类不能为空！");
			return reObject;
		}
		if (cdMaterialDict.getMaterialName() == null
				|| "".equals(cdMaterialDict.getMaterialName())) {
			reObject.setError("物资名称不能为空！");
			return reObject;
		}
		boolean isCompatHisCodeRule = "1".equals(cdSysParamDAO.findByParaCode(unitsCode, application, "0802","1"));
		if (materialCode == null || "".equals(materialCode)) {
			// 重新生成物资编码
			if(isCompatHisCodeRule){
				materialCode = StringUtil.lpad(cdMaterialDictDAO.nextValue("seq_item_code").toString(),7);
			}else{
				materialCode = cdMaterialDictDAO.getMaxMaterialCode(unitsCode);
			}
			cdMaterialDict.setMaterialCode(materialCode);
		} else {
			// 验证当前物资编码是否存在
			if (isAdd && !cdMaterialDictDAO.checkMaterialCodeUnique(unitsCode,
					materialCode, materialId)) {
				reObject.setError("物资编码已经存在！");
				return reObject;
			}
		}
		cdMaterialDict.setUnitsCode(unitsCode);
		cdMaterialDict.setModifyPerson(personId);
		cdMaterialDict.setModifyDate(new Date());
		if (isAdd) {
			// 新增
			if(isCompatHisCodeRule){
				materialId = unitsCode + StringUtil.lpad(cdMaterialDictDAO.nextValue("seq_clinic_id").toString(),7);
			}else{
				materialId = cdMaterialDictDAO.nextValue("SEQ_MATERIAL_DICT_ID").toString();
			}
			cdMaterialDict.setStartDate(new Date());
			cdMaterialDict.setMaterialId(materialId);
			cdMaterialDict.setCreatePerson(personId);
			cdMaterialDict.setCreateDate(new Date());
			cdMaterialDict.setFreezeSign("0");
			// 保存物资档案
			cdMaterialDictDAO.save(cdMaterialDict);
//			//同步到南院
//			CdMaterialDict newItem = new CdMaterialDict();
//			newItem.setUnitsCode(UNITS_CODE_SOUTH);
//			try {
//				BeanUtils.copyProperties(cdMaterialDict, newItem);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			cdMaterialDictDAO.save(newItem);
		} else {
			// 修改
			cdMaterialDictDAO.update(cdMaterialDict);
		}
		//同步其他单位字典数据
		synchOtherUnitDict(isAdd,application,cdMaterialDict);
		List<CdMaterialDict> lstReturn = new ArrayList<CdMaterialDict>();
		lstReturn.add(cdMaterialDict);
		reObject.setData(lstReturn);
		return reObject;
	}
	
	/**
	 * 同步其他单位字典数据
	 * @param isAdd 是否新增
	 * @param application 
	 * @param cdMaterialDict 字典模板
	 */
	@SuppressWarnings("unchecked")
	private void synchOtherUnitDict(boolean isAdd,String application,CdMaterialDict cdMaterialDict){
		//合并
		Map  storages = new HashMap<String, String>();
		storages.put("SOUTH_STORAGE",SOUTH_STORAGE);
		storages.put("NORTH_STORAGE", NORTH_STORAGE);//{SOUTH_STORAGE,NORTH_STORAGE};
		String unitsCode = cdMaterialDict.getUnitsCode();
		String paraGroup=cdSysParamDAO.findByParaCode(unitsCode, application, "0801");
		boolean isGroupUsed = paraGroup!=null && "1".equals(paraGroup) ? true : false;
		String synchMaterialClassCode = cdSysParamDAO.findByParaCode(unitsCode, application, "0803");
		//是否为要同步的物资
		boolean isSynchMaterial = synchMaterialClassCode==null || "".equals(synchMaterialClassCode.trim());
		if(!isSynchMaterial){
			String[] synchMCCAry = synchMaterialClassCode.split(",");
			for(String mcc : synchMCCAry){
				if(cdMaterialDict.getMaterialClass().startsWith(mcc.trim())){
					isSynchMaterial = true;
					break;
				}
			}
		}
		String idSuffix = cdMaterialDict.getMaterialId();
		
		if(idSuffix!=null && idSuffix.length()>unitsCode.length()){
			idSuffix = cdMaterialDict.getMaterialId().substring(unitsCode.length());
		}
		List<SysUnitInfor> units = sysUnitInforDAO.findAll();
		for(SysUnitInfor unit : units){
			if(!unit.getUnitsCode().equals(unitsCode) && "1".equals(unit.getEndSign())){
				if(isGroupUsed && isSynchMaterial){
					CdMaterialDict dict = new CdMaterialDict();
					BeanUtils.copyProperties(cdMaterialDict, dict);
					dict.setMaterialId(unit.getUnitsCode()+idSuffix);
					dict.setUnitsCode(unit.getUnitsCode());
					dict.setFreezeSign("0");	
					
					CdProvider provider=new CdProvider();
					List<CdProvider> providerList=new ArrayList<CdProvider>();
					String factoryCode=dict.getFactoryCode();//生产厂家
					if(factoryCode!=null && !factoryCode.equals("")){
						provider=cdProviderDAO.findById(factoryCode);
						if(provider!=null){
							providerList=cdProviderDAO.findByProviderCode(unit.getUnitsCode(), provider.getProviderCode());
							if(providerList!=null && providerList.size()>0){
								dict.setFactoryCode(providerList.get(0).getProviderId());
							}
						}
					}
					String providerCode=dict.getMainProvider();//供应商
					if(providerCode!=null && !providerCode.equals("")){
						provider=cdProviderDAO.findById(providerCode);
						if(provider!=null){
							providerList=cdProviderDAO.findByProviderCode(unit.getUnitsCode(), provider.getProviderCode());
							if(providerList!=null && providerList.size()>0){
								dict.setMainProvider(providerList.get(0).getProviderId());
							}
						}
					}
					
					if(dict.getUnitsCode().equals(UNITS_CODE_NORTH)){
						dict.setStorageDefault(storages.get("NORTH_STORAGE").toString());
					}
					if(dict.getUnitsCode().equals(UNITS_CODE_SOUTH)){
						dict.setStorageDefault(storages.get("SOUTH_STORAGE").toString());
					}
					if(isAdd){
						cdMaterialDictDAO.save(dict);
					}else{
						cdMaterialDictDAO.update(dict);
					}
				}
			}
		}
	}

	@Override
	public ReObject findMaterialDicByCode(String fstrMaterialCode) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("查找物资档案列表");

		String strUnitsCode = SessionUtil.getUnitsCode();
		List<CdMaterialDict> lstCdMaterialCd = cdMaterialDictDAO
				.findByMaterialCode(strUnitsCode, fstrMaterialCode);

		reObject.setData(lstCdMaterialCd);

		return reObject;
	}

	@Override
	public ReObject findByIntputCode(ParameterObject fparameter) {
		System.out.println("生生世世11");
		ReObject ro = new ReObject("根据输入简码查询物资字典");
		Map<String, Object> map = fparameter.getConditions();
		String lstrInputType = map.get("phoInputCode") == null ? "2" : "1";
		;
		String lstrExtCondition = addSignConToWhereClause(map);
		int limit = fparameter.getItemsPerPage();
		String lstrInputCode = (String) (map.get("phoInputCode") == null ? map
				.get("fiveInputCode") : map.get("phoInputCode"));
		String lstrUnitsCode = SessionUtil.getUnitsCode();
		List<CdMaterialDict> lstCdMaterialDict = cdMaterialDictDAO
				.findByInputCode(lstrExtCondition, lstrUnitsCode,
						lstrInputCode, lstrInputType, limit);
		ro.setData(lstCdMaterialDict);
		return ro;
	}

	private String addSignConToWhereClause(Map<String, Object> map) {
		String cssdSign = map.get("cssdSign") == null ? "0" : (String) map
				.get("cssdSign");
		String lstrExtCondition = " ";
		if ("1".equals(cssdSign)) {
			lstrExtCondition += " and cssdSign='" + cssdSign + "'";
		}
		String healthSign = map.get("healthSign") == null ? "0" : (String) map
				.get("healthSign");
		if ("1".equals(healthSign)) {
			lstrExtCondition += " and healthSign='" + healthSign + "'";
		}
		String logisticSign = map.get("logisticSign") == null ? "0"
				: (String) map.get("logisticSign");
		if ("1".equals(logisticSign)) {
			lstrExtCondition += " and logisticSign='" + logisticSign + "'";
		}
		String fixedSign = map.get("fixedSign") == null ? "0" : (String) map
				.get("fixedSign");
		if ("1".equals(fixedSign)) {
			lstrExtCondition += " and fixedSign='" + fixedSign + "'";
		}
		return lstrExtCondition;
	}

	@Override
	public ReObject findAllMeaterialClass() {
		ReObject ro = new ReObject("查询物资分类字典");
		List<CdMaterialClass> lstCdMaterialClass = cdMaterialDictDAO
				.findAllMeaterialClass();
		ro.setData(lstCdMaterialClass);
		return ro;
	}

	@Override
	public ReObject findMaterialDictListByClassCode(String fstrClassCode) {
		ReObject reObject = new ReObject();
		reObject.setAction("查找物资类别字典");

		List<CdMaterialDict> lstCdMaterialClasses = cdMaterialDictDAO
				.findByProperty("materialClass", fstrClassCode);
		reObject.setData(lstCdMaterialClasses);
		return reObject;
	}

	@Override
	public ReObject findMaterialDictListByInputCode(ParameterObject fparameter) {
		ReObject reObject = new ReObject("根据输入码查询物资字典");
		Map<String, Object> condition = fparameter.getConditions();
		String whereCluse = "";
		if (condition.get("phoInputCode") == null) {
			whereCluse = " fiveInputCode like '"
					+ condition.get("fiveInputCode") + "%' ";
		} else {
			whereCluse = " phoInputCode like '" + condition.get("phoInputCode")
					+ "%' ";
		}
		String lstrUnitsCode = SessionUtil.getUnitsCode();
		whereCluse += " and unitsCode='" + lstrUnitsCode + "'";
		List<CdMaterialDict> lstCdMaterialDict = cdMaterialDictDAO
				.findMaterialDictListByWhereCluse(whereCluse);
		reObject.setData(lstCdMaterialDict);
		return reObject;
	}

	/**
	 * 根据条件查询物资字典 修改者：周作建 2011.06.18 修改查询语句走索引；
	 */
	@Override
	public ReObject findMaterialDictListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件查询物资字典列表");
		Map<String, Object> condition = fparameter.getConditions();
		String materialClass = (String) condition.get("materialClass");
		String storageCode = (String) condition.get("storageCode");
		String phoInputCode = (String) condition.get("phoInputCode");
		String fiveInputCode = (String) condition.get("fiveInputCode");

		StringBuilder whereClause = new StringBuilder("unitsCode='").append(
				SessionUtil.getUnitsCode()).append("'");
		if (materialClass != null && !"".equals(materialClass)) {
			whereClause.append(" and materialClass='").append(materialClass)
					.append("'");
		}
		if (storageCode != null && !"".equals(storageCode)) {
			whereClause.append(" and storageDefault='").append(storageCode)
					.append("'");
		}
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			whereClause.append(" and (phoInputCode like '").append(phoInputCode)
					.append("%'");
			whereClause.append(" or generalPhoInputCode like '"
					+ phoInputCode + "%')");
		}
		if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			whereClause.append(" and (fiveInputCode like'")
					.append(fiveInputCode).append("%'");
			whereClause.append(" or generalFiveInputCode like '"
					+ fiveInputCode + "%')");
		}
		List<CdMaterialDict> lstCdMaterialDict = cdMaterialDictDAO
				.findMaterialDictListByWhereCluse(whereClause.toString());
		ro.setData(lstCdMaterialDict);
		return ro;
	}
	
	public ReObject findByInputCodeExt(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject();
		ro.setAction("查询字典列表");

		StringBuilder conditions = new StringBuilder();

		Map<String, Object> fmap = fparameter.getConditions();
		int start = fparameter.getStart();
		int limits = fparameter.getItemsPerPage();

		if (fmap == null) {
			return ro;
		}
		// 单位编码
		String unitsCode = (String) fmap.get("comboValue");

		if(unitsCode==null || unitsCode.equals("")){
			unitsCode=SessionUtil.getUnitsCode();
		}
		String phoInputCode = (String) fmap.get("phoInputCode");
		String fiveInputCode = (String) fmap.get("fiveInputCode");

		String deptCode = (String) (fmap.get("deptCode") == null ? "" : fmap
				.get("deptCode"));
		String providerChargeDept = (String)fmap.get("providerChargeDept");	
		
		//往来单位类型,物资高值耗材相关模块使用 2012.12.11 ryh
		String providerClass = (String)fmap.get("providerClass");	
		
		// 排序字段
		String orderField = (String) fmap.get("serverOrderField");
		// 类全名
		String classFullName = (String) fmap.get("entityClassName");

		conditions.append(" where unitsCode = '" + unitsCode + "'");
		// 五笔/拼音码只能选一个
		if (null != phoInputCode && !phoInputCode.equals("")) {
			phoInputCode = phoInputCode.toLowerCase();
			conditions.append(" and phoInputCode like '" + phoInputCode + "%'");
		}
		if (null != fiveInputCode && !fiveInputCode.equals("")) {
			fiveInputCode = fiveInputCode.toLowerCase();
			conditions.append(" and fiveInputCode like '" + fiveInputCode
					+ "%'");
		}
		if(providerChargeDept!=null){
			conditions.append(" and chargeDept = '" + providerChargeDept
					+ "'");
		}
		
		if(providerClass!=null && !providerClass.equals("")){
			conditions.append(" and providerClass = '" + providerClass
					+ "'");
		}
		// 适用范围
//		String applyRange = (String) fmap.get("comboValue");
//		if ("1".equals(applyRange)) {
//			// 全院
//			conditions.append(" and applyRange ='" + applyRange + "'");
//		} else if ("2".equals(applyRange)) {
//			// 科室
//			conditions.append(" and deptCode = '" + deptCode + "'");
//			conditions.append(" and applyRange='" + applyRange + "'");
//		} else if ("3".equals(applyRange)) {
//			String personId = SessionUtil.getPersonId();
//			conditions.append(" and personId='" + personId + "'");
//			conditions.append(" and applyRange='" + applyRange + "'");
//		}
		try {
			// 类
			Class<?> className = Class.forName(classFullName.toString());

			// 根据类名，重新增加特别的附加条件
			conditions = addConditions(conditions, fmap, className
					.getSimpleName());

			// 排序
			if (orderField != null && orderField.toString().trim().length() > 0) {
				conditions.append(" order by " + orderField);
			}

			List<?> result = toolDAO.findByCondition(className, conditions
					.toString(), start, limits);

			ro.setData(result);
			ro.setCount(toolDAO.countByCondition(className, conditions
					.toString()), limits);

		} catch (ClassNotFoundException re) {
			re.printStackTrace();
			ro.setError("查询字典信息失败！");
			return ro;
		}

		return ro;
	}
	
	private StringBuilder addConditions(StringBuilder conditions,
			Map<String, Object> fmap, String className) {
		if (className.equals("CdDeptDict")) {
			// 部门档案
			String clinicType = (String) fmap.get("clinicType");
			String deptSign = (String) fmap.get("deptSign");
			String groupSign = (String) fmap.get("groupSign");

			if (clinicType != null && clinicType.toString().trim().length() > 0) {
				// 临床科室类型
				conditions.append(" and clinicType = '" + clinicType + "'");
			}

			if (deptSign != null && deptSign.toString().trim().length() > 0) {
				// 临床科室标志
				conditions.append(" and deptSign = '" + deptSign + "'");
			}
			if (groupSign != null && groupSign.toString().trim().length() > 0) {
				// 诊疗组标志
				conditions.append(" and groupSign = '" + groupSign + "'");
			}
			return conditions;

		} else if (className.equals("CdPersonDict")) {
			// 人员档案
			String dnSign = (String) fmap.get("dnSign");
			if (dnSign != null && dnSign.toString().trim().length() > 0) {
				conditions.append(" and dnSign = '" + dnSign + "'");
			}
			return conditions;

		} else if (className.equals("CdStorageDict")) {
			// 仓库档案
			String storageProperty = (String) fmap.get("storageProperty");
			if (storageProperty != null
					&& storageProperty.toString().trim().length() > 0) {
				conditions.append(" and storageProperty = '" + storageProperty
						+ "'");
			}
			return conditions;
		} else if (className.equals("CdMainDescriDict")
				|| className.equals("CdFramExamDict")
				|| className.equals("CdUsedDiseaseDict")) {
			// 医嘱相关
			conditions.append(" and stopSign = '0'");
			return conditions;
		}
		return conditions;
	}
	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}

	public CdMaterialDictLogDAO getCdMaterialDictLogDAO() {
		return cdMaterialDictLogDAO;
	}

	public void setCdMaterialDictLogDAO(
			CdMaterialDictLogDAO cdMaterialDictLogDAO) {
		this.cdMaterialDictLogDAO = cdMaterialDictLogDAO;
	}
}
