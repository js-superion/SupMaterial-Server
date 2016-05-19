package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdSterilizeEffectDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdSterilizeEffectDetail entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see cn.superion.cssd.entity.CssdSterilizeEffectDetail
 * @author MyEclipse Persistence Tools
 */

public class CssdSterilizeEffectDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdSterilizeEffectDetailDAO.class);

	public void save(CssdSterilizeEffectDetail transientInstance) {
		log.debug("saving CssdSterilizeEffectDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdSterilizeEffectDetail persistentInstance) {
		log.debug("deleting CssdSterilizeEffectDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdSterilizeEffectDetail findById(java.lang.String id) {
		log.debug("getting CssdSterilizeEffectDetail instance with id: " + id);
		try {
			CssdSterilizeEffectDetail instance = (CssdSterilizeEffectDetail) getSession()
					.get("cn.superion.cssd.entity.CssdSterilizeEffectDetail",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdSterilizeEffectDetail instance) {
		log.debug("finding CssdSterilizeEffectDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdSterilizeEffectDetail").add(
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
		log.debug("finding CssdSterilizeEffectDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdSterilizeEffectDetail as model where model."
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
		log.debug("finding all CssdSterilizeEffectDetail instances");
		try {
			String queryString = "from CssdSterilizeEffectDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdSterilizeEffectDetail merge(
			CssdSterilizeEffectDetail detachedInstance) {
		log.debug("merging CssdSterilizeEffectDetail instance");
		try {
			CssdSterilizeEffectDetail result = (CssdSterilizeEffectDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdSterilizeEffectDetail instance) {
		log.debug("attaching dirty CssdSterilizeEffectDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdSterilizeEffectDetail instance) {
		log.debug("attaching clean CssdSterilizeEffectDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void saveDetails(String fstrMainAutoId,
			List<CssdSterilizeEffectDetail> fDetails) {
		deleteByMainAutoId(fstrMainAutoId);
		short serialNo=1;
		for(CssdSterilizeEffectDetail ldetail:fDetails){
			ldetail.setMainAutoId(fstrMainAutoId);
			ldetail.setSerialNo(serialNo);
			serialNo++;
			getSession().save(ldetail);
		}
	}

	public void deleteByMainAutoId(String fstrMainAutoId) {
		try {
			String queryString = "delete from CssdSterilizeEffectDetail where mainAutoId= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrMainAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void deleteByAutoId(String fstrAutoId) {
		try {
			String queryString = "delete from CssdSterilizeEffectDetail where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdSterilizeEffectDetail failed", re);
			throw re;
		}
	}
}