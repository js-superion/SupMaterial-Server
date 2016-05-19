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
import cn.superion.equipment.entity.EqTest;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqTest entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqTest
 * @author MyEclipse Persistence Tools
 */

public class EqTestDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(EqTestDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String BILL_NO = "billNo";
	public static final String EQUIPMENT_CODE = "equipmentCode";
	public static final String EQUIPMENT_NAME = "equipmentName";
	public static final String EQUIPMENT_SPEC = "equipmentSpec";
	public static final String USED_DEPT_NAME = "usedDeptName";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String TEST_UNIT = "testUnit";
	public static final String RES = "res";
	public static final String TEST_CERTIFY_NO = "testCertifyNo";
	public static final String MAKER = "maker";
	public static final String VERIFIER = "verifier";
	public static final String REMARK = "remark";
	public static final String STATUS = "status";

	public void save(EqTest transientInstance) {
		log.debug("saving EqTest instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqTest persistentInstance) {
		log.debug("deleting EqTest instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from EqTest where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("billNo", billNo)
				.uniqueResult().toString());
		return cnt == 0;
	}
	public List<EqTest> findListByCondition(String unitsCode,
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
				"from EqTest m where unitsCode='").append(
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
				"select autoId from EqTest m where unitsCode='").append(
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
	
	
	public EqTest findById(java.lang.String id) {
		log.debug("getting EqTest instance with id: " + id);
		try {
			EqTest instance = (EqTest) getSession().get(
					"cn.superion.equipment.entity.EqTest", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EqTest instance) {
		log.debug("finding EqTest instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqTest").add(
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
		log.debug("finding EqTest instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EqTest as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
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

	public List findByTestUnit(Object testUnit) {
		return findByProperty(TEST_UNIT, testUnit);
	}

	public List findByRes(Object res) {
		return findByProperty(RES, res);
	}

	public List findByTestCertifyNo(Object testCertifyNo) {
		return findByProperty(TEST_CERTIFY_NO, testCertifyNo);
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
		log.debug("finding all EqTest instances");
		try {
			String queryString = "from EqTest";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqTest merge(EqTest detachedInstance) {
		log.debug("merging EqTest instance");
		try {
			EqTest result = (EqTest) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqTest instance) {
		log.debug("attaching dirty EqTest instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqTest instance) {
		log.debug("attaching clean EqTest instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}