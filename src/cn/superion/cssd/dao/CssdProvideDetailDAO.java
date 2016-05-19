package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdProvideDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdProvideDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdProvideDetail
 * @author MyEclipse Persistence Tools
 */

public class CssdProvideDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdProvideDetailDAO.class);
	// property constants
	public void save(CssdProvideDetail transientInstance) {
		log.debug("saving CssdProvideDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdProvideDetail persistentInstance) {
		log.debug("deleting CssdProvideDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdProvideDetail findById(java.lang.String id) {
		log.debug("getting CssdProvideDetail instance with id: " + id);
		try {
			CssdProvideDetail instance = (CssdProvideDetail) getSession().get(
					"cn.superion.cssd.entity.CssdProvideDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdProvideDetail> findByExample(CssdProvideDetail instance) {
		log.debug("finding CssdProvideDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdProvideDetail").add(
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
	public List<CssdProvideDetail>  findByProperty(String propertyName, Object value) {
		log.debug("finding CssdProvideDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdProvideDetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List<CssdProvideDetail> findByMainAutoId(String fstrMainAutoId) {
		return this.findByProperty("mainAutoId", fstrMainAutoId);
	}
	public CssdProvideDetail findByMainAutoIdAndSerialNo(String fstrMainAutoId,short serialNo) {
		try {
			String queryString = "from CssdProvideDetail as model where model.mainAutoId = ?"+
					" and model.serialNo = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrMainAutoId);
			queryObject.setParameter(1, serialNo);
			if(queryObject.list().size() > 0){
				return (CssdProvideDetail) queryObject.list().get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CssdProvideDetail> findByMainAutoIdAndSerialNo1(String fstrMainAutoId,String packageId) {
		try {
			String queryString = "from CssdProvideDetail as model where model.mainAutoId = ? and model.packageId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrMainAutoId);
			queryObject.setParameter(1, packageId);
			if(queryObject.list().size() > 0){
				return queryObject.list();
			}
			return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List findAll() {
		log.debug("finding all CssdProvideDetail instances");
		try {
			String queryString = "from CssdProvideDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public void deleteByMainAutoId(String fstrMainAutoId) {
		getSession().createQuery(
				"delete from CssdProvideDetail where mainAutoId=:mainAutoId")
				.setString("mainAutoId", fstrMainAutoId).executeUpdate();
	}
	public CssdProvideDetail merge(CssdProvideDetail detachedInstance) {
		log.debug("merging CssdProvideDetail instance");
		try {
			CssdProvideDetail result = (CssdProvideDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdProvideDetail instance) {
		log.debug("attaching dirty CssdProvideDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdProvideDetail instance) {
		log.debug("attaching clean CssdProvideDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}