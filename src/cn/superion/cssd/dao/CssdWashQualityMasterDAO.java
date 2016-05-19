package cn.superion.cssd.dao;

import cn.superion.cssd.entity.CssdWashQualityMaster;
import cn.superion.util.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdWashQualityMaster entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdWashQualityMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdWashQualityMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdWashQualityMasterDAO.class);

	public void save(CssdWashQualityMaster transientInstance) {
		log.debug("saving CssdWashQualityMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdWashQualityMaster persistentInstance) {
		log.debug("deleting CssdWashQualityMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdWashQualityMaster findById(java.lang.String id) {
		log.debug("getting CssdWashQualityMaster instance with id: " + id);
		try {
			CssdWashQualityMaster instance = (CssdWashQualityMaster) getSession()
					.get("cn.superion.cssd.entity.CssdWashQualityMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdWashQualityMaster instance) {
		log.debug("finding CssdWashQualityMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdWashQualityMaster").add(
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
		log.debug("finding CssdWashQualityMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdWashQualityMaster as model where model."
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
		log.debug("finding all CssdWashQualityMaster instances");
		try {
			String queryString = "from CssdWashQualityMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdWashQualityMaster merge(CssdWashQualityMaster detachedInstance) {
		log.debug("merging CssdWashQualityMaster instance");
		try {
			CssdWashQualityMaster result = (CssdWashQualityMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdWashQualityMaster instance) {
		log.debug("attaching dirty CssdWashQualityMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdWashQualityMaster instance) {
		log.debug("attaching clean CssdWashQualityMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public void update(CssdWashQualityMaster cssdMaster) {
		getSession().update(cssdMaster);
	}

	public void deleteByAutoId(String fstrAutoId) {
		try {
			String queryString = "delete from CssdWashQualityMaster where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdWashQualityMaster failed", re);
			throw re;
		}
	}	
}