package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqEquipment;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqEquipment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqEquipment
 * @author MyEclipse Persistence Tools
 */

public class EqEquipmentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqEquipmentDAO.class);

	public void save(EqEquipment transientInstance) {
		log.debug("saving EqEquipment instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqEquipment persistentInstance) {
		log.debug("deleting EqEquipment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqEquipment findById(java.lang.String id) {
		log.debug("getting EqEquipment instance with id: " + id);
		try {
			EqEquipment instance = (EqEquipment) getSession().get(
					"cn.superion.equipment.entity.EqEquipment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqEquipment> findByExample(EqEquipment instance) {
		log.debug("finding EqEquipment instance by example");
		try {
			List<EqEquipment> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqEquipment").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqEquipment> findByProperty(String propertyName, Object value) {
		log.debug("finding EqEquipment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EqEquipment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqEquipment merge(EqEquipment detachedInstance) {
		log.debug("merging EqEquipment instance");
		try {
			EqEquipment result = (EqEquipment) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqEquipment instance) {
		log.debug("attaching dirty EqEquipment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqEquipment instance) {
		log.debug("attaching clean EqEquipment instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String hql = "select autoId " + parseCondition(unitsCode,conditions);
		return getSession().createQuery(hql).setProperties(
				conditions).list();
	}
	
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String hql = parseCondition(unitsCode,conditions);
		Query countQuery = getSession().createQuery("select count(*) "+hql);
		Query query = getSession().createQuery(hql);
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<EqEquipment> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>beginEquipmentCode 起始设备编码</li>
	 *            <li>endEquipmentCode 结束设备编码</li>
	 *            <li>equipmentName 设备名称</li>
	 *            <li>beginClassAbc 起始设备ABC分类编码</li>
	 *            <li>endClassAbc 结束设备ABC分类编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginFatherCode 起始父设备编码</li>
	 *            <li>endFatherCode 结束父设备编码</li>
	 *            <li>equipmentStatus 设备状态</li>
	 *            <li>positionCode 位置</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>supplier 供应商</li>
	 *            <li>manufacturer 制造商</li>
	 *            <li>beginDateOfProduction 起始出厂日期</li>
	 *            <li>endDateOfProduction 结束出厂日期</li>
	 *            <li>beginDateOfUsed 起始使用日期</li>
	 *            <li>endDateOfUsed 结束使用日期</li>
	 *            <li>jobDept 作业部门</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            </ul>
	 * @return
	 */
	private String parseCondition(String unitsCode,
			Map<String, Object> conditions) {
		String beginEquipmentCode = (String) conditions
				.get("beginEquipmentCode");
		String endEquipmentCode = (String) conditions.get("endEquipmentCode");
		String equipmentName = (String) conditions.get("equipmentName");
		String beginClassAbc = (String) conditions.get("beginClassAbc");
		String endClassAbc = (String) conditions.get("endClassAbc");
		String beginEquipmentType = (String) conditions
				.get("beginEquipmentType");
		String endEquipmentType = (String) conditions.get("endEquipmentType");
		String beginEquipmentClass = (String) conditions
				.get("beginEquipmentClass");
		String endEquipmentClass = (String) conditions.get("endEquipmentClass");
		String beginFatherCode = (String) conditions.get("beginFatherCode");
		String endFatherCode = (String) conditions.get("endFatherCode");
		String equipmentStatus = (String) conditions.get("equipmentStatus");
		String positionCode = (String) conditions.get("positionCode");
		String beginNationClass=(String)conditions.get("beginNationClass");
		String endNationClass=(String)conditions.get("endNationClass");
		String usedDept = (String) conditions.get("usedDept");
		String supplier = (String) conditions.get("supplier");
		String manufacturer = (String) conditions.get("manufacturer");
		Date beginDateOfProduction = (Date) conditions
				.get("beginDateOfProduction");
		Date endDateOfProduction = (Date) conditions.get("endDateOfProduction");
		Date beginDateOfUsed = (Date) conditions.get("beginDateOfUsed");
		Date endDateOfUsed = (Date) conditions.get("endDateOfUsed");
		String jobDept = (String) conditions.get("jobDept");
		String maker = (String) conditions.get("maker");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		Double beginOriginalValue = conditions.get("beginOriginalValue")==null?null:Double.valueOf(conditions.get("beginOriginalValue").toString());
		Double endOriginalValue = conditions.get("endOriginalValue")==null?null:Double.valueOf(conditions.get("endOriginalValue").toString());
		StringBuilder shql = new StringBuilder(
				"from EqEquipment where unitsCode='").append(unitsCode).append(
				"'");
		if (beginEquipmentCode != null && !"".equals(beginEquipmentCode)) {
			shql.append(" and equipmentCode>=:beginEquipmentCode");
		}
		if (endEquipmentCode != null && !"".equals(endEquipmentCode)) {
			shql.append(" and equipmentCode<=:endEquipmentCode");
		}
		if(endNationClass !=null && !"".equals(endNationClass)){
			shql.append(" and nationClass<=:endNationClass");
		}
		if(beginNationClass !=null && !"".equals(beginNationClass)){
			shql.append(" and nationClass>=:beginNationClass");
		}	
		if (equipmentName != null && !"".equals(equipmentName.trim())) {
			shql.append(" and equipmentName like :equipmentName");
			conditions.put("equipmentName", "%" + equipmentName + "%");
		}
		if (beginClassAbc != null && !"".equals(beginClassAbc)) {
			shql.append(" and classAbc>=:beginClassAbc");
		}
		if (endClassAbc != null && !"".equals(endClassAbc)) {
			shql.append(" and classAbc<=:endClassAbc");
		}
		if (beginEquipmentType != null && !"".equals(beginEquipmentType)) {
			shql.append(" and equipmentType>=:beginEquipmentType");
		}
		if (endEquipmentType != null && !"".equals(endEquipmentType)) {
			shql.append(" and equipmentType<=:endEquipmentType");
		}
		if (beginEquipmentClass != null && !"".equals(beginEquipmentClass)) {
			shql.append(" and equipmentClass>=:beginEquipmentClass");
		}
		if (endEquipmentClass != null && !"".equals(endEquipmentClass)) {
			shql.append(" and equipmentClass<=:endEquipmentClass");
		}
		if (beginFatherCode != null && !"".equals(beginFatherCode)) {
			shql.append(" and fatherCode>=:beginFatherCode");
		}
		if (endFatherCode != null && !"".equals(endFatherCode)) {
			shql.append(" and fatherCode<=:endFatherCode");
		}
		if (equipmentStatus != null && !"".equals(equipmentStatus)) {
			shql.append(" and equipmentStatus=:equipmentStatus");
		}
		if (positionCode != null && !"".equals(positionCode)) {
			shql.append(" and positionCode=:positionCode");
		}
		if (usedDept != null && !"".equals(usedDept)) {
			shql.append(" and usedDept=:usedDept");
		}
		if (supplier != null && !"".equals(supplier)) {
			shql.append(" and supplier=:supplier");
		}
		if (manufacturer != null && !"".equals(manufacturer)) {
			shql.append(" and manufacturer=:manufacturer");
		}
		if (beginDateOfProduction != null) {
			shql.append(" and dateOfProduction>=:beginDateOfProduction");
		}
		if (endDateOfProduction != null) {
			shql.append(" and dateOfProduction<=:endDateOfProduction");
		}
		if (beginDateOfUsed != null) {
			shql.append(" and dateOfUsed>=:beginDateOfUsed");
		}
		if (endDateOfUsed != null) {
			shql.append(" and dateOfUsed<=:endDateOfUsed");
		}
		if (jobDept != null && !"".equals(jobDept)) {
			shql.append(" and jobDept=:jobDept");
		}
		if (maker != null && !"".equals(maker)) {
			shql.append(" and maker=:maker");
		}
		if (beginMakeDate != null) {
			shql.append(" and makeDate>=:beginMakeDate");
		}
		if (endMakeDate != null) {
			shql.append(" and makeDate<=:endMakeDate");
		}
		if (verifier != null && !"".equals(verifier)) {
			shql.append(" and verifier=:verifier");
		}
		if (beginVerifyDate != null) {
			shql.append(" and verifyDate>=:beginVerifyDate");
		}
		if (endVerifyDate != null) {
			shql.append(" and verifyDate<=:endVerifyDate");
		}
		if (beginOriginalValue != null) {
			conditions.put("beginOriginalValue", beginOriginalValue);
			shql.append(" and originalValue>=:beginOriginalValue");
		}
		if (endOriginalValue != null) {
			conditions.put("endOriginalValue", endOriginalValue);
			shql.append(" and originalValue<=:endOriginalValue");
		}
		return shql.toString();
	}
	
	public boolean checkCodeUnique(String unitsCode, String equipmentCode) {
		return checkCodeUnique(unitsCode,equipmentCode,null);
	}

	public boolean checkCodeUnique(String unitsCode, String equipmentCode,String excludedAutoId) {
		String hql = "select count(*) from EqEquipment where unitsCode=:unitsCode and equipmentCode=:equipmentCode";
		if(excludedAutoId != null && !"".equals(excludedAutoId)){
			hql += " and autoId<>'"+excludedAutoId+"'";
		}
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString(
				"equipmentCode", equipmentCode).uniqueResult().toString());
		return cnt == 0;
	}

	@SuppressWarnings("unchecked")
	public EqEquipment findByCode(String unitsCode, String equipmentCode) {
		String hql = "from EqEquipment where unitsCode=:unitsCode and equipmentCode=:equipmentCode";
		List<EqEquipment> eqs = getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("equipmentCode", equipmentCode).list();
		return eqs == null || eqs.isEmpty() ? null : eqs.get(0);
	}

}