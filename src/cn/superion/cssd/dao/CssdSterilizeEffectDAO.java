package cn.superion.cssd.dao;

import cn.superion.cssd.entity.CssdSterilizeEffect;
import cn.superion.util.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdSterilizeEffect entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdSterilizeEffect
 * @author MyEclipse Persistence Tools
 */

public class CssdSterilizeEffectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdSterilizeEffectDAO.class);

	public void save(CssdSterilizeEffect transientInstance) {
		log.debug("saving CssdSterilizeEffect instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdSterilizeEffect persistentInstance) {
		log.debug("deleting CssdSterilizeEffect instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdSterilizeEffect findById(java.lang.String id) {
		log.debug("getting CssdSterilizeEffect instance with id: " + id);
		try {
			CssdSterilizeEffect instance = (CssdSterilizeEffect) getSession()
					.get("cn.superion.cssd.entity.CssdSterilizeEffect", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdSterilizeEffect instance) {
		log.debug("finding CssdSterilizeEffect instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdSterilizeEffect").add(
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
		log.debug("finding CssdSterilizeEffect instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdSterilizeEffect as model where model."
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
		log.debug("finding all CssdSterilizeEffect instances");
		try {
			String queryString = "from CssdSterilizeEffect";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdSterilizeEffect merge(CssdSterilizeEffect detachedInstance) {
		log.debug("merging CssdSterilizeEffect instance");
		try {
			CssdSterilizeEffect result = (CssdSterilizeEffect) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdSterilizeEffect instance) {
		log.debug("attaching dirty CssdSterilizeEffect instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdSterilizeEffect instance) {
		log.debug("attaching clean CssdSterilizeEffect instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(CssdSterilizeEffect cssdMaster) {
		getSession().update(cssdMaster);
		
	}

	public void deleteByAutoId(String fstrAutoId) {
		try {
			String queryString = "delete from CssdSterilizeEffect where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdSterilizeEffect failed", re);
			throw re;
		}
	}	
}