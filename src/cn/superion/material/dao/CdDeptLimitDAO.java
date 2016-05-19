package cn.superion.material.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.CdDeptLimit;
import cn.superion.material.entity.MaterialSupplierSummary;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CdDeptLimit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.CdDeptLimit
 * @author MyEclipse Persistence Tools
 */

public class CdDeptLimitDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CdDeptLimitDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String DEPT_CODE = "deptCode";
	public static final String DEPT_NAME = "deptName";
	public static final String YEARS = "years";
	public static final String SEASON = "season";
	public static final String LIMITS = "limits";
	public static final String ADD_UP = "addUp";
	public static final String CREATE_PERSON = "createPerson";
	public static final String MODIFY_PERSON = "modifyPerson";

	public void save(CdDeptLimit transientInstance) {
		log.debug("saving CdDeptLimit instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CdDeptLimit persistentInstance) {
		log.debug("deleting CdDeptLimit instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	
	public List<CdDeptLimit> findByCondition2(String unitsCode,String deptCode,String years,String season){
		StringBuilder shql = new StringBuilder("from CdDeptLimit m where unitsCode='"+unitsCode+"' and deptCode = '"+deptCode+"' and years = '"+years+"' and season = '"+season+"'");
		return getSession().createQuery(shql.toString()).list();
	}
	
	
	public List<CdDeptLimit> findByCondition(String unitsCode,
			Map<String, Object> conditions){
		String deptCode = (String)conditions.get("deptCode");//可是
		String years = (String)conditions.get("years");//年度
		String season = (String)conditions.get("season");//季度
		StringBuilder shql = new StringBuilder("from CdDeptLimit m where unitsCode='").append(unitsCode).append("'");
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(years != null && !"".equals(years)){
			shql.append(" and years=:years");
		}
		if(season != null && !"".equals(season)){
			shql.append(" and season=:season");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}
	

	public CdDeptLimit findById(java.lang.String id) {
		log.debug("getting CdDeptLimit instance with id: " + id);
		try {
			CdDeptLimit instance = (CdDeptLimit) getSession().get(
					"cn.superion.material.entity.CdDeptLimit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CdDeptLimit instance) {
		log.debug("finding CdDeptLimit instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.CdDeptLimit").add(
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
		log.debug("finding CdDeptLimit instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CdDeptLimit as model where model."
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

	public List findByDeptCode(Object deptCode) {
		return findByProperty(DEPT_CODE, deptCode);
	}

	public List findByDeptName(Object deptName) {
		return findByProperty(DEPT_NAME, deptName);
	}

	public List findByYears(Object years) {
		return findByProperty(YEARS, years);
	}

	public List findBySeason(Object season) {
		return findByProperty(SEASON, season);
	}

	public List findByLimits(Object limits) {
		return findByProperty(LIMITS, limits);
	}

	public List findByAddUp(Object addUp) {
		return findByProperty(ADD_UP, addUp);
	}

	public List findByCreatePerson(Object createPerson) {
		return findByProperty(CREATE_PERSON, createPerson);
	}

	public List findByModifyPerson(Object modifyPerson) {
		return findByProperty(MODIFY_PERSON, modifyPerson);
	}

	public List findAll() {
		log.debug("finding all CdDeptLimit instances");
		try {
			String queryString = "from CdDeptLimit";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CdDeptLimit merge(CdDeptLimit detachedInstance) {
		log.debug("merging CdDeptLimit instance");
		try {
			CdDeptLimit result = (CdDeptLimit) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CdDeptLimit instance) {
		log.debug("attaching dirty CdDeptLimit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CdDeptLimit instance) {
		log.debug("attaching clean CdDeptLimit instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}