package cn.superion.equipment.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqRepairApply;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqRepairApply entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqRepairApply
 * @author MyEclipse Persistence Tools
 */

public class EqRepairApplyDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqRepairApplyDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String BILL_NO = "billNo";
	public static final String EQUIPMENT_CODE = "equipmentCode";
	public static final String EQUIPMENT_NAME = "equipmentName";
	public static final String EQUIPMENT_SPEC = "equipmentSpec";
	public static final String USED_DEPT_NAME = "usedDeptName";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String DESCRIBE = "describe";
	public static final String MAKER = "maker";
	public static final String VERIFIER = "verifier";
	public static final String REMARK = "remark";
	public static final String STATUS = "status";

	public void save(EqRepairApply transientInstance) {
		log.debug("saving EqRepairApply instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqRepairApply persistentInstance) {
		log.debug("deleting EqRepairApply instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqRepairApply findById(java.lang.String id) {
		log.debug("getting EqRepairApply instance with id: " + id);
		try {
			EqRepairApply instance = (EqRepairApply) getSession().get(
					"cn.superion.equipment.entity.EqRepairApply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EqRepairApply instance) {
		log.debug("finding EqRepairApply instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqRepairApply").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EqRepairApply instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqRepairApply as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from EqRepairApply where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("billNo", billNo)
				.uniqueResult().toString());
		return cnt == 0;
	}
	
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		String maker = (String) conditions.get("maker");
		String status = (String) conditions.get("status");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		
		StringBuilder shql = new StringBuilder(
				"select autoId from EqRepairApply m where unitsCode='").append(
				unitsCode).append("'");
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and billNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and billNo<=:endBillNo");
		}
		if (maker != null && !"".equals(maker)) {
			shql.append(" and maker=:maker");
		}
		if (status != null && !"".equals(status)) {
			shql.append(" and status=:status");
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
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	
	public List<EqRepairApply> findListByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		String maker = (String) conditions.get("maker");
		String status = (String) conditions.get("status");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		
		StringBuilder shql = new StringBuilder(
				"from EqRepairApply m where unitsCode='").append(
				unitsCode).append("'");
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and billNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and billNo<=:endBillNo");
		}
		if (maker != null && !"".equals(maker)) {
			shql.append(" and maker=:maker");
		}
		if (status != null && !"".equals(status)) {
			shql.append(" and status=:status");
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
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	

	
	public List findByUnitsCode(Object unitsCode) {
		return findByProperty(UNITS_CODE, unitsCode);
	}

	public List findByBillNo(Object billNo) {
		return findByProperty(BILL_NO, billNo);
	}

	public List findByEquipmentCode(Object equipmentCode) {
		return findByProperty(EQUIPMENT_CODE, equipmentCode);
	}

	public List findByEquipmentName(Object equipmentName) {
		return findByProperty(EQUIPMENT_NAME, equipmentName);
	}

	public List findByEquipmentSpec(Object equipmentSpec) {
		return findByProperty(EQUIPMENT_SPEC, equipmentSpec);
	}

	public List findByUsedDeptName(Object usedDeptName) {
		return findByProperty(USED_DEPT_NAME, usedDeptName);
	}

	public List findByChargePerson(Object chargePerson) {
		return findByProperty(CHARGE_PERSON, chargePerson);
	}

	public List findByDescribe(Object describe) {
		return findByProperty(DESCRIBE, describe);
	}

	public List findByMaker(Object maker) {
		return findByProperty(MAKER, maker);
	}

	public List findByVerifier(Object verifier) {
		return findByProperty(VERIFIER, verifier);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all EqRepairApply instances");
		try {
			String queryString = "from EqRepairApply";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqRepairApply merge(EqRepairApply detachedInstance) {
		log.debug("merging EqRepairApply instance");
		try {
			EqRepairApply result = (EqRepairApply) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqRepairApply instance) {
		log.debug("attaching dirty EqRepairApply instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqRepairApply instance) {
		log.debug("attaching clean EqRepairApply instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}