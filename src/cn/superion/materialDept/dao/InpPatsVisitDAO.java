package cn.superion.materialDept.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.InpPatsVisit;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * InpPatsVisit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.materialDept.entity.InpPatsVisit
 * @author MyEclipse Persistence Tools
 */

public class InpPatsVisitDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InpPatsVisitDAO.class);

	public void save(InpPatsVisit transientInstance) {
		log.debug("saving InpPatsVisit instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InpPatsVisit persistentInstance) {
		log.debug("deleting InpPatsVisit instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<InpPatsVisit> findByExample(InpPatsVisit instance) {
		log.debug("finding InpPatsVisit instance by example");
		try {
			List<InpPatsVisit> results = getSession().createCriteria(
					"cn.superion.materialDept.entity.InpPatsVisit").add(
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
	public List<InpPatsVisit> findByProperty(String propertyName, Object value) {
		log.debug("finding InpPatsVisit instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InpPatsVisit as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public InpPatsVisit merge(InpPatsVisit detachedInstance) {
		log.debug("merging InpPatsVisit instance");
		try {
			InpPatsVisit result = (InpPatsVisit) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InpPatsVisit instance) {
		log.debug("attaching dirty InpPatsVisit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InpPatsVisit instance) {
		log.debug("attaching clean InpPatsVisit instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 查询在院病人信息
	 * @param unitsCode
	 * @param patientId
	 * @return
	 * @author ryh
	 * @date 2012.10.25
	 */
	public InpPatsVisit findPatsVisitById(String unitsCode,String patientId) {
		log.debug("finding InpPatsVisit by unitsCode and patientId");
		try {
			String queryString = "from InpPatsVisit where id.unitsCode= ? and id.inpNo=? and currentStatus<>'1'";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, patientId);
			return (InpPatsVisit) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public InpPatsVisit findById(String unitsCode, String fstrPatientId) {
		log.debug("finding InpPatsVisit by unitsCode and inpNo");
		try {
			String queryString = "from InpPatsVisit where unitsCode= ? and inpNo=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, fstrPatientId);
			return (InpPatsVisit) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}