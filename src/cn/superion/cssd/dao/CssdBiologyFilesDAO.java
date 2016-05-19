package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdBiologyFiles;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdBiologyFiles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdBiologyFiles
 * @author MyEclipse Persistence Tools
 */

public class CssdBiologyFilesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdBiologyFilesDAO.class);

	public void save(CssdBiologyFiles transientInstance) {
		log.debug("saving CssdBiologyFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdBiologyFiles persistentInstance) {
		log.debug("deleting CssdBiologyFiles instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdBiologyFiles findById(java.lang.String id) {
		log.debug("getting CssdBiologyFiles instance with id: " + id);
		try {
			CssdBiologyFiles instance = (CssdBiologyFiles) getSession().get(
					"cn.superion.cssd.entity.CssdBiologyFiles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdBiologyFiles instance) {
		log.debug("finding CssdBiologyFiles instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdBiologyFiles").add(
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
		log.debug("finding CssdBiologyFiles instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdBiologyFiles as model where model."
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
		log.debug("finding all CssdBiologyFiles instances");
		try {
			String queryString = "from CssdBiologyFiles";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdBiologyFiles merge(CssdBiologyFiles detachedInstance) {
		log.debug("merging CssdBiologyFiles instance");
		try {
			CssdBiologyFiles result = (CssdBiologyFiles) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdBiologyFiles instance) {
		log.debug("attaching dirty CssdBiologyFiles instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdBiologyFiles instance) {
		log.debug("attaching clean CssdBiologyFiles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}