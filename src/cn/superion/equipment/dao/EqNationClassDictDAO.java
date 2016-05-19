package cn.superion.equipment.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.superion.equipment.entity.EqNationClassDict;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqNationClassDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqNationClassDict
 * @author MyEclipse Persistence Tools
 */

public class EqNationClassDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqNationClassDictDAO.class);

	public void save(EqNationClassDict transientInstance) {
		log.debug("saving EqNationClassDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqNationClassDict persistentInstance) {
		log.debug("deleting EqNationClassDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqNationClassDict findById(java.lang.String id) {
		log.debug("getting EqNationClassDict instance with id: " + id);
		try {
			EqNationClassDict instance = (EqNationClassDict) getSession().get(
					"cn.superion.equipment.entity.EqNationClassDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqNationClassDict> findByExample(EqNationClassDict instance) {
		log.debug("finding EqNationClassDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqNationClassDict").add(
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
	public List<EqNationClassDict> findByProperty(String propertyName,
			Object value) {
		log.debug("finding EqNationClassDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqNationClassDict as model where model."
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
	public List<EqNationClassDict> findAll() {
		log.debug("finding all EqNationClassDict instances");
		try {
			String queryString = "from EqNationClassDict";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqNationClassDict BaseData");
		try {
			String queryString = "select new map(classCode as eqNationClass,className as eqNationClassName) from EqNationClassDict order by classCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
	public EqNationClassDict merge(EqNationClassDict detachedInstance) {
		log.debug("merging EqNationClassDict instance");
		try {
			EqNationClassDict result = (EqNationClassDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqNationClassDict instance) {
		log.debug("attaching dirty EqNationClassDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqNationClassDict instance) {
		log.debug("attaching clean EqNationClassDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public void update(EqNationClassDict eqNationClassDict) {
		getSession().update(eqNationClassDict);
	}
}