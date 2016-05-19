package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqUnitDict;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqUnitDict entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqUnitDict
 * @author MyEclipse Persistence Tools
 */

public class EqUnitDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqUnitDictDAO.class);
	// property constants
	public static final String UNITNAME = "unitname";
	public static final String FIVEINPUTCODE = "fiveinputcode";
	public static final String PHOINPUTCODE = "phoinputcode";

	public void save(EqUnitDict transientInstance) {
		log.debug("saving EqUnitDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqUnitDict persistentInstance) {
		log.debug("deleting EqUnitDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqUnitDict findById(java.lang.String id) {
		log.debug("getting EqUnitDict instance with id: " + id);
		try {
			EqUnitDict instance = (EqUnitDict) getSession().get(
					"cn.superion.equipment.entity.EqUnitDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EqUnitDict instance) {
		log.debug("finding EqUnitDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqUnitDict").add(
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
		log.debug("finding EqUnitDict instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EqUnitDict as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUnitname(Object unitname) {
		return findByProperty(UNITNAME, unitname);
	}

	public List findByFiveinputcode(Object fiveinputcode) {
		return findByProperty(FIVEINPUTCODE, fiveinputcode);
	}

	public List findByPhoinputcode(Object phoinputcode) {
		return findByProperty(PHOINPUTCODE, phoinputcode);
	}

	public List findAll() {
		log.debug("finding all EqUnitDict instances");
		try {
			String queryString = "from EqUnitDict";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqUnitDict merge(EqUnitDict detachedInstance) {
		log.debug("merging EqUnitDict instance");
		try {
			EqUnitDict result = (EqUnitDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqUnitDict instance) {
		log.debug("attaching dirty EqUnitDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqUnitDict instance) {
		log.debug("attaching clean EqUnitDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}