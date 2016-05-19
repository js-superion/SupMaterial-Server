package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdBiologyDetial;
import cn.superion.util.BaseHibernateDAO;


/**
 * A data access object (DAO) providing persistence and search support for
 * CssdBiologyDetial entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdBiologyDetial
 * @author MyEclipse Persistence Tools
 */

public class CssdBiologyDetialDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdBiologyDetialDAO.class);

	public void save(CssdBiologyDetial transientInstance) {
		log.debug("saving CssdBiologyDetial instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdBiologyDetial persistentInstance) {
		log.debug("deleting CssdBiologyDetial instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdBiologyDetial findById(java.lang.String id) {
		log.debug("getting CssdBiologyDetial instance with id: " + id);
		try {
			CssdBiologyDetial instance = (CssdBiologyDetial) getSession().get(
					"cn.superion.cssd.entity.CssdBiologyDetial", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdBiologyDetial instance) {
		log.debug("finding CssdBiologyDetial instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdBiologyDetial").add(
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
		log.debug("finding CssdBiologyDetial instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdBiologyDetial as model where model."
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
		log.debug("finding all CssdBiologyDetial instances");
		try {
			String queryString = "from CssdBiologyDetial";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdBiologyDetial merge(CssdBiologyDetial detachedInstance) {
		log.debug("merging CssdBiologyDetial instance");
		try {
			CssdBiologyDetial result = (CssdBiologyDetial) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdBiologyDetial instance) {
		log.debug("attaching dirty CssdBiologyDetial instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdBiologyDetial instance) {
		log.debug("attaching clean CssdBiologyDetial instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByMainAutoId(String fstrMainAutoId) {
		log.debug("finding CssdBiologyDetial instance");
		try {
			String queryString = "from CssdBiologyDetial where mainAutoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrMainAutoId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("finding CssdBiologyDetial", re);
			throw re;
		}
	}

	public void deleteByMainAutoId(String fstrMainAutoId) {
		try {
			String queryString = "delete from CssdBiologyDetial where mainAutoId= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrMainAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
    
	public void saveDetails(String fstrMainAutoId,List<CssdBiologyDetial> fbiologyDetails) {
		deleteByMainAutoId(fstrMainAutoId);
		short serialNo=1;
		for(CssdBiologyDetial ldetail:fbiologyDetails){
			ldetail.setMainAutoId(fstrMainAutoId);
			ldetail.setSerialNo(serialNo);
			serialNo++;
			getSession().save(ldetail);
		}
	}
}