package cn.superion.material.dataDict.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.center.config.dao.CdStorageDictDAO;
import cn.superion.center.config.entity.CdStorageDict;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialClass;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.util.SessionUtil;

public class MaterialCommonDictImpl implements IMaterialCommonDict {
	private CdMaterialDictDAO cdMaterialDictDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private CdStorageDictDAO cdStorageDictDAO;
	public static String TIMEZONE = "GMT+08:00";
	//物资分类，泰州是91，东方医院不需要
	public static final String MATERIAL_CLASS = ReadPropertiesFile
	.getValue("MATERIAL_CLASS");
	
	@Override
	public ReObject findByInputCode(ParameterObject fparameter) {
		ReObject ro = new ReObject();
		ro.setAction("查询物资字典列表");

		String unitsCode = SessionUtil.getSysUser().getUnitsCode();
		
		Map<String, Object> map = fparameter.getConditions();
		Object fiveInputCode = map.get("fiveInputCode") == null ? "" : map
				.get("fiveInputCode");
		Object phoInputCode = map.get("phoInputCode") == null ? "" : map
				.get("phoInputCode");
		
		// 仓库编码
		String storageCode = (String) map.get("storageCode");
		//单位编码来自仓库所在单位
		if(storageCode!=null && !storageCode.equals("")){
			List<CdStorageDict> storageList = cdStorageDictDAO.findByProperty("storageCode", storageCode);
			if(storageList.size() !=1){
				throw new RuntimeException("请检查仓库字典，编码为:"+storageCode+"不存在或有重复！");
			}
			unitsCode = storageList.get(0).getUnitsCode();
		}
		
		// 固定资产标志
		String fixedSign = (String) map.get("fixedSign");
		// 供应室物资标志
		String cssdSign = (String) map.get("cssdSign");
		String storageMaterialSign = (String) map.get("storageMaterialSign");
		// 通用拼音简码
		String generalPhoInputCode = "";
		// 通用五笔简码
		String generalFiveInputCode = "";
		
		int start = fparameter.getStart();
		int itemsPerPage = fparameter.getItemsPerPage();

		StringBuilder condition = new StringBuilder();
		condition.append(" where materialClass like '"+MATERIAL_CLASS+"%' and freezeSign = '0' and unitsCode = '" + unitsCode + "'"); //将冻结的过滤掉，根据要求

		if (!phoInputCode.equals("")
				&& phoInputCode.toString().trim().length() > 0) {
			phoInputCode = phoInputCode.toString().toLowerCase();
			generalPhoInputCode = (String) phoInputCode;

			condition.append(" and (phoInputCode like '%" + phoInputCode + "%'");
			condition.append(" or generalPhoInputCode like '%"
					+ generalPhoInputCode + "%')");
		}
		if (!fiveInputCode.equals("")
				&& fiveInputCode.toString().trim().length() > 0) {
			fiveInputCode = fiveInputCode.toString().toLowerCase();
			generalFiveInputCode = (String) fiveInputCode;
			condition.append(" and (fiveInputCode like '%" + fiveInputCode
					+ "%'");
			condition.append(" or generalFiveInputCode like '%"
					+ generalFiveInputCode + "%')");
		}

		if (fixedSign != null && fixedSign.length() > 0) {
			condition.append(" and fixedSign = '" + fixedSign + "'");
		}
		
		if (storageMaterialSign != null && storageMaterialSign.length() > 0) {
			condition.append(" and storageMaterialSign = '"+storageMaterialSign+"'");
		}
		if (cssdSign != null && cssdSign.length() > 0) {
			condition.append(" and cssdSign = '" + cssdSign + "'");
		}
		if (storageCode != null && storageCode.length() > 0) {
			//二级库
			String subStorageCode = storageCode.substring(0, storageCode.length());
			condition.append(" and storageDefault = '" + subStorageCode + "'");
		}
		List<CdMaterialDict> lstCdMaterialDicts = cdMaterialDictDAO
				.findByCondition(condition.toString(), start, itemsPerPage);
		int count = cdMaterialDictDAO.countByCondition(condition.toString());
		// 如果仓库编码不为空查询当前库存量
		if(storageCode != null && storageCode.length() > 0){
			for (CdMaterialDict cdMaterialDict : lstCdMaterialDicts) {
				Double amount = materialCurrentStockDAO.findStockAmountByID(
						unitsCode, storageCode, cdMaterialDict.getMaterialId());
				Double virtualAmount=materialCurrentStockDAO.findVirtalAmountByID(
						unitsCode, storageCode, cdMaterialDict.getMaterialId());
				cdMaterialDict.setAmount(amount);
				cdMaterialDict.setVirtualAmount(virtualAmount);
			}
		}
		
		ro.setData(lstCdMaterialDicts);
		ro.setCount(count, itemsPerPage);
		return ro;
	}

	@Override
	public ReObject findByInputCodeDF(ParameterObject fparameter) {
		ReObject ro = new ReObject();
		ro.setAction("查询物资字典列表");

		String unitsCode = SessionUtil.getSysUser().getUnitsCode();
		
		Map<String, Object> map = fparameter.getConditions();
		Object fiveInputCode = map.get("fiveInputCode") == null ? "" : map
				.get("fiveInputCode");
		Object phoInputCode = map.get("phoInputCode") == null ? "" : map
				.get("phoInputCode");
		
//		// 仓库编码
		String storageCode = (String) map.get("storageCode");
//		//单位编码来自仓库所在单位
//		if(storageCode!=null && !storageCode.equals("")){
//			List<CdStorageDict> storageList = cdStorageDictDAO.findByProperty("storageCode", storageCode);
//			if(storageList.size() !=1){
//				throw new RuntimeException("请检查仓库字典，编码为:"+storageCode+"不存在或有重复！");
//			}
//			unitsCode = storageList.get(0).getUnitsCode();
//		}byzcl
		
//		// 固定资产标志
//		String fixedSign = (String) map.get("fixedSign");
//		// 供应室物资标志
//		String cssdSign = (String) map.get("cssdSign");byzcl

		// 通用拼音简码
		String generalPhoInputCode = "";
		// 通用五笔简码
		String generalFiveInputCode = "";
		
		int start = fparameter.getStart();
		int itemsPerPage = fparameter.getItemsPerPage();

//		StringBuilder condition = new StringBuilder();
//byzcl		condition.append(" where materialClass like '"+MATERIAL_CLASS+"%' and freezeSign = '0' and unitsCode = '" + unitsCode + "'"); //将冻结的过滤掉，根据要求
		List<Map<String,Object>> list = null;
		if (!phoInputCode.equals("")
				&& phoInputCode.toString().trim().length() > 0) {
			phoInputCode = phoInputCode.toString().toLowerCase();
			generalPhoInputCode = (String) phoInputCode;
			if(storageCode != null && !storageCode.equals("")){
				list = materialCurrentStockDAO.findMaterialCurrentAmount(phoInputCode.toString(),storageCode);
			}else{
				list = materialCurrentStockDAO.findMaterialCurrentAmount(phoInputCode.toString());
			}
//			condition.append(" and (phoInputCode like '%" + phoInputCode + "%'");
//			condition.append(" or generalPhoInputCode like '%"
//byzcl					+ generalPhoInputCode + "%')");
		}else{
			if(storageCode != null && !storageCode.equals("")){
				list = materialCurrentStockDAO.findMaterialCurrentAmount1(storageCode);
			}else{
				list = materialCurrentStockDAO.findMaterialCurrentAmount();
			}
		}
		if (!fiveInputCode.equals("")
				&& fiveInputCode.toString().trim().length() > 0) {
			fiveInputCode = fiveInputCode.toString().toLowerCase();
			generalFiveInputCode = (String) fiveInputCode;
//			condition.append(" and (fiveInputCode like '%" + fiveInputCode
//					+ "%'");
//			condition.append(" or generalFiveInputCode like '%"
//byzcl					+ generalFiveInputCode + "%')");
		}

//		if (fixedSign != null && fixedSign.length() > 0) {
//			condition.append(" and fixedSign = '" + fixedSign + "'");
//		}
//		if (cssdSign != null && cssdSign.length() > 0) {
//			condition.append(" and cssdSign = '" + cssdSign + "'");
//		}
//		if (storageCode != null && storageCode.length() > 0) {
//			//二级库
//			String subStorageCode = storageCode.substring(0, storageCode.length());
//			condition.append(" and storageDefault = '" + subStorageCode + "'");
//		}byzcl
//		List<CdMaterialDict> lstCdMaterialDicts = cdMaterialDictDAO
//				.findByCondition(condition.toString(), start, itemsPerPage);
//byzcl		int count = cdMaterialDictDAO.countByCondition(condition.toString());
//byzcl		List<Map<String,Object>> list = materialCurrentStockDAO.findMaterialCurrentAmount(phoInputCode.toString());
		// 如果仓库编码不为空查询当前库存量
//		if(storageCode != null && storageCode.length() > 0){
//			for (CdMaterialDict cdMaterialDict : lstCdMaterialDicts) {
//				Double amount = materialCurrentStockDAO.findStockAmountByID(
//						unitsCode, storageCode, cdMaterialDict.getMaterialId());
//				Double virtualAmount=materialCurrentStockDAO.findVirtalAmountByID(
//						unitsCode, storageCode, cdMaterialDict.getMaterialId());
//				cdMaterialDict.setAmount(amount);
//				cdMaterialDict.setVirtualAmount(virtualAmount);
//			}
//byzcl		}
//		String yearMonth = getPreviousMonth();
		String yearMonth = materialCurrentStockDAO.findMaxMonth();
		for(Map<String,Object> cdmap:list){
			String materialId = cdmap.get("materialId").toString();
			String batch = cdmap.get("batch").toString();
			double tradePrice = cdmap.get("tradePrice") == null?0d:Double.valueOf(cdmap.get("tradePrice").toString());
			Double deliveryAmount = materialCurrentStockDAO.findDeliveryAmount(materialId, batch, yearMonth,storageCode);
			cdmap.put("deliveryAmount", deliveryAmount);
			cdmap.put("tradeMoney", tradePrice * deliveryAmount);
		}
		ro.setData(list);
		//ro.setCount(count, itemsPerPage);
		return ro;
	}
	
	public static String getPreviousMonth() {
		TimeZone tzCN = TimeZone.getTimeZone(TIMEZONE);
		Calendar calendar = Calendar.getInstance(tzCN);
		calendar.add(Calendar.MONTH, -1);
		int beforeyear = calendar.get(Calendar.YEAR);
		int beforemonth = calendar.get(Calendar.MONTH) + 1;
		int beforeday = calendar.get(Calendar.DAY_OF_MONTH);
		return beforeyear + "-" + lpad(beforemonth);
	}
	public static String lpad(int a) {
		return a < 10 ? "0" + a : String.valueOf(a);
	}

	@Override
	public ReObject findMaterialClass() {
		ReObject ro = new ReObject();
		ro.setAction("查询物资字典分类列表");
		List<CdMaterialClass> lstMaterialClass=cdMaterialDictDAO.findAllMeaterialClass();
		ro.setData(lstMaterialClass);
		return ro;
	}

	@Override
	public ReObject findMaterialDictByClass(ParameterObject fparameter) {
		Map<String, Object> map = fparameter.getConditions();
		String fstrMaterialClass =(String) map.get("materialClass");
		// 仓库编码
		String storageCode = (String) map.get("storageCode");
		// 固定资产标志
		String fixedSign = (String) map.get("fixedSign");
		String storageMaterialSign = (String) map.get("storageMaterialSign");
		// 供应室物资标志
		String cssdSign = (String) map.get("cssdSign");
		String unitsCode = SessionUtil.getUnitsCode();
		StringBuilder condition = new StringBuilder();
		condition.append(" where unitsCode = '" + unitsCode + "'");
		condition.append(" and materialClass = '" + fstrMaterialClass + "'");
		if (fixedSign != null && fixedSign.length() > 0) {
			condition.append(" and fixedSign = '" + fixedSign + "'");
		}
		if (cssdSign != null && cssdSign.length() > 0) {
			condition.append(" and cssdSign = '" + cssdSign + "'");
		}
		if (cssdSign != null && cssdSign.length() > 0) {
			condition.append(" and cssdSign = '" + cssdSign + "'");
		}
		if (storageCode != null && storageCode.length() > 0) {
			condition.append(" and storageDefault = '" + storageCode + "'");
		}
		if (storageMaterialSign != null && storageMaterialSign.length() > 0) {
			condition.append(" and storageMaterialSign = '"+storageMaterialSign+"'");
		}
		ReObject ro = new ReObject();
		ro.setAction("根据物资分类查询物资字典");
		List<CdMaterialDict> lstMaterialDict=cdMaterialDictDAO.findByCondition(condition.toString());
		for (CdMaterialDict cdMaterialDict : lstMaterialDict) {
			Double amount = materialCurrentStockDAO.findStockAmountByID(
					unitsCode, storageCode, cdMaterialDict.getMaterialId());
			cdMaterialDict.setAmount(amount);
		}
		ro.setData(lstMaterialDict);
		return ro;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findAdvanceDict() {
		// TODO Auto-generated method stub
		ReObject obj = new ReObject();
		obj.setAction("获取高级字典列表");
		Map<String, List<?>> lmapAllDict = new HashMap<String, List<?>>();

		// 人员档案字典 CD_PERSON_DICT
		List<Map<String, Object>> lstCdPersonDict = materialCurrentStockDAO
				.findPersonDict();
		lmapAllDict.put("personId", lstCdPersonDict);
		
		// 科室档案 CD_DEPT
		List<Map<String, Object>> lstCdDept = materialCurrentStockDAO.findDeptDict();
		lmapAllDict.put("dept", lstCdDept);
		
		// 往来单位档案 CD_PROVIDER
		List<Map<String, Object>> lstCdProvider = materialCurrentStockDAO.findProviderDict();
		lmapAllDict.put("provider", lstCdProvider);
		
		List<Map<String, Object>> lstFactory = materialCurrentStockDAO.findFactoryDict();
		lmapAllDict.put("factory", lstFactory);
		// 仓库字典 CD_STORAGE_DICT
		List<Map<String, Object>> lstCdStorageDict = materialCurrentStockDAO.findStorageDict();
		lmapAllDict.put("storage", lstCdStorageDict);
		List lstAllDict = new ArrayList();
		lstAllDict.add(lmapAllDict);
		obj.setData(lstAllDict);
		return obj;
	}
	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}

	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}

	public CdStorageDictDAO getCdStorageDictDAO() {
		return cdStorageDictDAO;
	}

	public void setCdStorageDictDAO(CdStorageDictDAO cdStorageDictDAO) {
		this.cdStorageDictDAO = cdStorageDictDAO;
	}
}
