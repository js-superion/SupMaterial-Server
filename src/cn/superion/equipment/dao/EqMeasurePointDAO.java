package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqMeasurePoint;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqMeasurePoint entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqMeasurePoint
 * @author MyEclipse Persistence Tools
 */

public class EqMeasurePointDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqMeasurePointDAO.class);

	public void save(EqMeasurePoint transientInstance) {
		log.debug("saving EqMeasurePoint instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqMeasurePoint persistentInstance) {
		log.debug("deleting EqMeasurePoint instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqMeasurePoint findById(java.lang.String id) {
		log.debug("getting EqMeasurePoint instance with id: " + id);
		try {
			EqMeasurePoint instance = (EqMeasurePoint) getSession().get(
					"cn.superion.equipment.entity.EqMeasurePoint", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqMeasurePoint> findByExample(EqMeasurePoint instance) {
		log.debug("finding EqMeasurePoint instance by example");
		try {
			List<EqMeasurePoint> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqMeasurePoint").add(
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
	public List<EqMeasurePoint> findByProperty(String propertyName, Object value) {
		log.debug("finding EqMeasurePoint instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqMeasurePoint as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqMeasurePoint merge(EqMeasurePoint detachedInstance) {
		log.debug("merging EqMeasurePoint instance");
		try {
			EqMeasurePoint result = (EqMeasurePoint) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqMeasurePoint instance) {
		log.debug("attaching dirty EqMeasurePoint instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqMeasurePoint instance) {
		log.debug("attaching clean EqMeasurePoint instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqMeasurePoint> findAll() {
		String hql = "from EqMeasurePoint order by pointCode";
		return getSession().createQuery(hql).list();
	}

	public void update(EqMeasurePoint measurePoint) {
		getSession().update(measurePoint);
	}
}