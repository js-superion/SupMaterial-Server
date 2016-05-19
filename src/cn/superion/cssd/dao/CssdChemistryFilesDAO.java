package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdChemistryFiles;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdChemistryFiles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdChemistryFiles
 * @author MyEclipse Persistence Tools
 */

public class CssdChemistryFilesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdChemistryFilesDAO.class);

	public void save(CssdChemistryFiles transientInstance) {
		log.debug("saving CssdChemistryFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdChemistryFiles persistentInstance) {
		log.debug("deleting CssdChemistryFiles instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdChemistryFiles findById(java.lang.String id) {
		log.debug("getting CssdChemistryFiles instance with id: " + id);
		try {
			CssdChemistryFiles instance = (CssdChemistryFiles) getSession()
					.get("cn.superion.cssd.entity.CssdChemistryFiles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdChemistryFiles instance) {
		log.debug("finding CssdChemistryFiles instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdChemistryFiles").add(
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
		log.debug("finding CssdChemistryFiles instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdChemistryFiles as model where model."
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
		log.debug("finding all CssdChemistryFiles instances");
		try {
			String queryString = "from CssdChemistryFiles";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdChemistryFiles merge(CssdChemistryFiles detachedInstance) {
		log.debug("merging CssdChemistryFiles instance");
		try {
			CssdChemistryFiles result = (CssdChemistryFiles) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdChemistryFiles instance) {
		log.debug("attaching dirty CssdChemistryFiles instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdChemistryFiles instance) {
		log.debug("attaching clean CssdChemistryFiles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}