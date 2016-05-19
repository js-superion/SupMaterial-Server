package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqCurrentRcptNo;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqCurrentRcptNo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqCurrentRcptNo
 * @author MyEclipse Persistence Tools
 */

public class EqCurrentRcptNoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqCurrentRcptNoDAO.class);

	public void save(EqCurrentRcptNo transientInstance) {
		log.debug("saving EqCurrentRcptNo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqCurrentRcptNo persistentInstance) {
		log.debug("deleting EqCurrentRcptNo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqCurrentRcptNo findById(java.lang.String id) {
		log.debug("getting EqCurrentRcptNo instance with id: " + id);
		try {
			EqCurrentRcptNo instance = (EqCurrentRcptNo) getSession().get(
					"cn.superion.equipment.entity.EqCurrentRcptNo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqCurrentRcptNo> findByExample(EqCurrentRcptNo instance) {
		log.debug("finding EqCurrentRcptNo instance by example");
		try {
			List<EqCurrentRcptNo> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqCurrentRcptNo").add(
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
	public List<EqCurrentRcptNo> findByProperty(String propertyName, Object value) {
		log.debug("finding EqCurrentRcptNo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqCurrentRcptNo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqCurrentRcptNo merge(EqCurrentRcptNo detachedInstance) {
		log.debug("merging EqCurrentRcptNo instance");
		try {
			EqCurrentRcptNo result = (EqCurrentRcptNo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqCurrentRcptNo instance) {
		log.debug("attaching dirty EqCurrentRcptNo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqCurrentRcptNo instance) {
		log.debug("attaching clean EqCurrentRcptNo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void flush() {
		getSession().flush();
	}

	public EqCurrentRcptNo findByStorageCode(String unitsCode, String rcptFlag,
			String rcptType,String typeDate) {
		String queryString = "from EqCurrentRcptNo where unitsCode=? and rcptFlag=? and rcptType=? and typeDate=?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, unitsCode);
		queryObject.setParameter(1, rcptFlag);
		queryObject.setParameter(2, rcptType);
		queryObject.setParameter(3, typeDate);
		return (EqCurrentRcptNo) queryObject.uniqueResult();
	}
}