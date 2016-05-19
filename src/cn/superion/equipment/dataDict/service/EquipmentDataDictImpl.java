package cn.superion.equipment.dataDict.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.equipment.dao.EqChangeTypeDictDAO;
import cn.superion.equipment.dao.EqClassAbcDictDAO;
import cn.superion.equipment.dao.EqClassDictDAO;
import cn.superion.equipment.dao.EqNationClassDictDAO;
import cn.superion.equipment.dao.EqEquipmentDAO;
import cn.superion.equipment.dao.EqEquipmentTypeDAO;
import cn.superion.equipment.dao.EqFaultReasonDictDAO;
import cn.superion.equipment.dao.EqFaultTypeDictDAO;
import cn.superion.equipment.dao.EqJobContentDAO;
import cn.superion.equipment.dao.EqJobGroupDictDAO;
import cn.superion.equipment.dao.EqJobTypeDictDAO;
import cn.superion.equipment.dao.EqPositionDictDAO;
import cn.superion.equipment.dao.EqRunStatusDictDAO;
import cn.superion.equipment.dao.EqStatusDictDAO;
import cn.superion.equipment.entity.EqEquipment;
import cn.superion.equipment.entity.EqJobContent;
import cn.superion.util.BaseToolDAO;
import cn.superion.util.SessionUtil;

public class EquipmentDataDictImpl implements IEquipmentDataDict {
	private EqChangeTypeDictDAO eqChangeTypeDictDAO;
	private EqClassDictDAO eqClassDictDAO;
	private EqNationClassDictDAO eqNationClassDictDAO;
	private EqClassAbcDictDAO eqClassAbcDictDAO;
	private EqFaultReasonDictDAO eqFaultReasonDictDAO;
	private EqFaultTypeDictDAO eqFaultTypeDictDAO;
	private EqPositionDictDAO eqPositionDictDAO;
	private EqJobGroupDictDAO eqJobGroupDictDAO;
	private EqJobTypeDictDAO eqJobTypeDictDAO;
	private EqRunStatusDictDAO eqRunStatusDictDAO;
	private EqStatusDictDAO eqStatusDictDAO;
	private EqEquipmentTypeDAO eqEquipmentTypeDAO;
	private EqEquipmentDAO eqEquipmentDAO;
	private EqJobContentDAO eqJobContentDAO; 
	private BaseToolDAO baseToolDAO;

	public EqJobContentDAO getEqJobContentDAO() {
		return eqJobContentDAO;
	}

	public void setEqJobContentDAO(EqJobContentDAO eqJobContentDAO) {
		this.eqJobContentDAO = eqJobContentDAO;
	}
	
	public EqEquipmentDAO getEqEquipmentDAO() {
		return eqEquipmentDAO;
	}

	public void setEqEquipmentDAO(EqEquipmentDAO eqEquipmentDAO) {
		this.eqEquipmentDAO = eqEquipmentDAO;
	}

	public EqEquipmentTypeDAO getEqEquipmentTypeDAO() {
		return eqEquipmentTypeDAO;
	}

	public void setEqEquipmentTypeDAO(EqEquipmentTypeDAO eqEquipmentTypeDAO) {
		this.eqEquipmentTypeDAO = eqEquipmentTypeDAO;
	}
	public EqNationClassDictDAO getEqNationClassDictDAO() {
		return eqNationClassDictDAO;
	}

	public void setEqNationClassDictDAO(EqNationClassDictDAO eqNationClassDictDAO) {
		this.eqNationClassDictDAO = eqNationClassDictDAO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findEquipmentDataDict() {
		// TODO Auto-generated method stub
		ReObject obj = new ReObject();
		obj.setAction("获取基础字典列表");
		Map<String, List<?>> lmapAllDict = new HashMap<String, List<?>>();
		// 1.1 设备类别字典 EQ_CLASS_DICT
		List<Map<String, Object>> lstEqClassDict = eqClassDictDAO
				.findBaseData();
		lmapAllDict.put("eqClass", lstEqClassDict);
		// 1.2 设备状态字典 EQ_STATUS_DICT
		List<Map<String, Object>> lstEqStatusDict = eqStatusDictDAO
				.findBaseData();
		lmapAllDict.put("eqStatus", lstEqStatusDict);
		// 1.3 设备ABC分类字典 EQ_CLASS_ABC_DICT
		List<Map<String, Object>> lstEqClassAbcDict = eqClassAbcDictDAO
				.findBaseData();
		lmapAllDict.put("eqClassAbc", lstEqClassAbcDict);
		// 1.4 变更类型字典 EQ_CHANGE_TYPE_DICT
		List<Map<String, Object>> lstEqChangeTypeDict = eqChangeTypeDictDAO
				.findBaseData();
		lmapAllDict.put("eqChangeType", lstEqChangeTypeDict);
		// 1.5 作业类型字典 EQ_JOB_TYPE_DICT
		List<Map<String, Object>> lstEqJobTypeDict = eqJobTypeDictDAO
				.findBaseData();
		lmapAllDict.put("eqJobType", lstEqJobTypeDict);
		// 1.6 作业小组字典 EQ_JOB_GROUP_DICT
		List<Map<String, Object>> lstEqJobGroupDict = eqJobGroupDictDAO
				.findBaseData();
		lmapAllDict.put("eqJobGroup", lstEqJobGroupDict);
		// 1.7 运行状态字典 EQ_RUN_STATUS_DICT
		List<Map<String, Object>> lstEqRunStatusDict = eqRunStatusDictDAO
				.findBaseData();
		lmapAllDict.put("eqRunStatus", lstEqRunStatusDict);
		// 1.8 故障类型字典 EQ_FAULT_TYPE_DICT
		List<Map<String, Object>> lstEqFaultTypeDict = eqFaultTypeDictDAO
				.findBaseData();
		lmapAllDict.put("eqFaultType", lstEqFaultTypeDict);
		// 1.9 故障原因字典 EQ_FAULT_REASON_DICT
		List<Map<String, Object>> lstEqFaultReasonDict = eqFaultReasonDictDAO
				.findBaseData();
		lmapAllDict.put("eqFaultReason", lstEqFaultReasonDict);
		// 1.10 位置字典 EQ_POSITION_DICT
		List<Map<String, Object>> lstEqPositionDict = eqPositionDictDAO
				.findBaseData();
		lmapAllDict.put("eqPosition", lstEqPositionDict);
		// 1.13设备国家类别字典 EQ_NATION_CLASS_DICT
		List<Map<String, Object>> lstEqNationClassDict = eqNationClassDictDAO
				.findBaseData();
		lmapAllDict.put("eqNationClass", lstEqNationClassDict);
		// 2.1 设备类型台帐 EQ_EQUIPMENT_TYPE
		List<Map<String, Object>> lstEqEquipmentType = eqEquipmentTypeDAO
				.findBaseData();
		lmapAllDict.put("eqEquipmentType", lstEqEquipmentType);
		List lstAllDict = new ArrayList();
		lstAllDict.add(lmapAllDict);
		obj.setData(lstAllDict);
		return obj;
	}

	@Override
	public ReObject findByInputCode(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject();
		ro.setAction("查询字典列表");

		Map<String, Object> fmap = fparameter.getConditions();
		if (fmap == null) {
			return ro;
		}

		StringBuilder condition = new StringBuilder();
		int start = fparameter.getStart();
		int limits = fparameter.getItemsPerPage();

		// 排序字段
		String orderField = (String) fmap.get("serverOrderField");
		// 类全名
		String classFullName = (String) fmap.get("entityClassName");

		// 五笔/拼音码等查询条件只能选一个
		String phoInputCode = (String) fmap.get("phoInputCode");
		String fiveInputCode = (String) fmap.get("fiveInputCode");
		if (null != phoInputCode) {
			phoInputCode = phoInputCode.toLowerCase();
			condition
					.append(" where phoInputCode like '" + phoInputCode + "%'");
		} else {
			if (null != fiveInputCode) {
				fiveInputCode = fiveInputCode.toLowerCase();
			}
			condition.append(" where fiveInputCode like '" + fiveInputCode
					+ "%'");
		}

		if (orderField != null && orderField.toString().trim().length() > 0)
			condition.append(" order by " + orderField);

		try {
			// 类
			Class<?> className = Class.forName(classFullName.toString());
			List<?> result = baseToolDAO.findByCondition(className, condition
					.toString(), start, limits);

			ro.setData(result);
			ro.setCount(baseToolDAO.countByCondition(className, condition
					.toString()), limits);

		} catch (ClassNotFoundException re) {
			re.printStackTrace();
			ro.setError("查询字典信息失败！");
			return ro;
		}

		return ro;
	}

	public EqChangeTypeDictDAO getEqChangeTypeDictDAO() {
		return eqChangeTypeDictDAO;
	}

	public void setEqChangeTypeDictDAO(EqChangeTypeDictDAO eqChangeTypeDictDAO) {
		this.eqChangeTypeDictDAO = eqChangeTypeDictDAO;
	}

	public EqClassDictDAO getEqClassDictDAO() {
		return eqClassDictDAO;
	}

	public void setEqClassDictDAO(EqClassDictDAO eqClassDictDAO) {
		this.eqClassDictDAO = eqClassDictDAO;
	}

	public EqClassAbcDictDAO getEqClassAbcDictDAO() {
		return eqClassAbcDictDAO;
	}

	public void setEqClassAbcDictDAO(EqClassAbcDictDAO eqClassAbcDictDAO) {
		this.eqClassAbcDictDAO = eqClassAbcDictDAO;
	}

	public EqFaultReasonDictDAO getEqFaultReasonDictDAO() {
		return eqFaultReasonDictDAO;
	}

	public void setEqFaultReasonDictDAO(
			EqFaultReasonDictDAO eqFaultReasonDictDAO) {
		this.eqFaultReasonDictDAO = eqFaultReasonDictDAO;
	}

	public EqFaultTypeDictDAO getEqFaultTypeDictDAO() {
		return eqFaultTypeDictDAO;
	}

	public void setEqFaultTypeDictDAO(EqFaultTypeDictDAO eqFaultTypeDictDAO) {
		this.eqFaultTypeDictDAO = eqFaultTypeDictDAO;
	}

	public EqPositionDictDAO getEqPositionDictDAO() {
		return eqPositionDictDAO;
	}

	public void setEqPositionDictDAO(EqPositionDictDAO eqPositionDictDAO) {
		this.eqPositionDictDAO = eqPositionDictDAO;
	}

	public EqJobGroupDictDAO getEqJobGroupDictDAO() {
		return eqJobGroupDictDAO;
	}

	public void setEqJobGroupDictDAO(EqJobGroupDictDAO eqJobGroupDictDAO) {
		this.eqJobGroupDictDAO = eqJobGroupDictDAO;
	}

	public EqJobTypeDictDAO getEqJobTypeDictDAO() {
		return eqJobTypeDictDAO;
	}

	public void setEqJobTypeDictDAO(EqJobTypeDictDAO eqJobTypeDictDAO) {
		this.eqJobTypeDictDAO = eqJobTypeDictDAO;
	}

	public EqRunStatusDictDAO getEqRunStatusDictDAO() {
		return eqRunStatusDictDAO;
	}

	public void setEqRunStatusDictDAO(EqRunStatusDictDAO eqRunStatusDictDAO) {
		this.eqRunStatusDictDAO = eqRunStatusDictDAO;
	}

	public EqStatusDictDAO getEqStatusDictDAO() {
		return eqStatusDictDAO;
	}

	public void setEqStatusDictDAO(EqStatusDictDAO eqStatusDictDAO) {
		this.eqStatusDictDAO = eqStatusDictDAO;
	}

	public BaseToolDAO getBaseToolDAO() {
		return baseToolDAO;
	}

	public void setBaseToolDAO(BaseToolDAO baseToolDAO) {
		this.baseToolDAO = baseToolDAO;
	}

	@Override
	public ReObject findEquipmentByEquipmentCode(String fstrEquipmentCode) {
		ReObject ro = new ReObject("根据设备编号查询设备台账信息");
		EqEquipment eq = eqEquipmentDAO.findByCode(SessionUtil.getUnitsCode(),fstrEquipmentCode);
		List<Object> data = new ArrayList<Object>();
		data.add(eq);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findJobContentByEquipmentCode(String fstrEquipmentCode) {
		ReObject ro = new ReObject("根据设备编号查询设备的作业内容列表");
		List<EqJobContent> data = eqJobContentDAO.findByEquipmentCode(SessionUtil.getUnitsCode(),fstrEquipmentCode);
		ro.setData(data);
		return ro;
	}

}
