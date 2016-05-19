package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialInPatsVisit;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialInPatsVisit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialInPatsVisit
 * @author MyEclipse Persistence Tools
 */

public class MaterialInPatsVisitDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialInPatsVisitDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String PATIENT_ID = "patientId";
	public static final String INP_NO = "inpNo";
	public static final String PERSON_NAME = "personName";
	public static final String SEX = "sex";
	public static final String AGE = "age";
	public static final String AGE_UNITS = "ageUnits";
	public static final String ID_NO = "idNo";
	public static final String BLOOD_NAME = "bloodName";
	public static final String RH_TYPE = "rhType";
	public static final String OPERATOR = "operator";
	public static final String MODIFY_PERSON = "modifyPerson";

	public void save(MaterialInPatsVisit transientInstance) {
		log.debug("saving MaterialInPatsVisit instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialInPatsVisit persistentInstance) {
		log.debug("deleting MaterialInPatsVisit instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialInPatsVisit findById(java.lang.String id) {
		log.debug("getting MaterialInPatsVisit instance with id: " + id);
		try {
			MaterialInPatsVisit instance = (MaterialInPatsVisit) getSession()
					.get("cn.superion.material.entity.MaterialInPatsVisit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialInPatsVisit> findByExample(MaterialInPatsVisit instance) {
		log.debug("finding MaterialInPatsVisit instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialInPatsVisit").add(
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
	public List<MaterialInPatsVisit> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialInPatsVisit instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialInPatsVisit as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialInPatsVisit> findAll() {
		log.debug("finding all MaterialInPatsVisit instances");
		try {
			String queryString = "from MaterialInPatsVisit";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialInPatsVisit merge(MaterialInPatsVisit detachedInstance) {
		log.debug("merging MaterialInPatsVisit instance");
		try {
			MaterialInPatsVisit result = (MaterialInPatsVisit) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialInPatsVisit instance) {
		log.debug("attaching dirty MaterialInPatsVisit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialInPatsVisit instance) {
		log.debug("attaching clean MaterialInPatsVisit instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}