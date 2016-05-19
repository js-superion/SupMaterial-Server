package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdPhysicsFiles;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdPhysicsFiles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdPhysicsFiles
 * @author MyEclipse Persistence Tools
 */

public class CssdPhysicsFilesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdPhysicsFilesDAO.class);

	public void save(CssdPhysicsFiles transientInstance) {
		log.debug("saving CssdPhysicsFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdPhysicsFiles persistentInstance) {
		log.debug("deleting CssdPhysicsFiles instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdPhysicsFiles findById(java.lang.String id) {
		log.debug("getting CssdPhysicsFiles instance with id: " + id);
		try {
			CssdPhysicsFiles instance = (CssdPhysicsFiles) getSession().get(
					"cn.superion.cssd.entity.CssdPhysicsFiles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdPhysicsFiles instance) {
		log.debug("finding CssdPhysicsFiles instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdPhysicsFiles").add(
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
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CssdPhysicsFiles instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdPhysicsFiles as model where model."
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
	public List findAll() {
		log.debug("finding all CssdPhysicsFiles instances");
		try {
			String queryString = "from CssdPhysicsFiles";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdPhysicsFiles merge(CssdPhysicsFiles detachedInstance) {
		log.debug("merging CssdPhysicsFiles instance");
		try {
			CssdPhysicsFiles result = (CssdPhysicsFiles) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdPhysicsFiles instance) {
		log.debug("attaching dirty CssdPhysicsFiles instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdPhysicsFiles instance) {
		log.debug("attaching clean CssdPhysicsFiles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}