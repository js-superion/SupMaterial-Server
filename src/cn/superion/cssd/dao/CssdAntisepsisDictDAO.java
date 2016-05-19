package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdAntisepsisDict;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdAntisepsisDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdAntisepsisDict
 * @author MyEclipse Persistence Tools
 */

public class CssdAntisepsisDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdAntisepsisDictDAO.class);

	public void save(CssdAntisepsisDict transientInstance) {
		log.debug("saving CssdAntisepsisDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdAntisepsisDict persistentInstance) {
		log.debug("deleting CssdAntisepsisDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdAntisepsisDict findById(java.lang.String id) {
		log.debug("getting CssdAntisepsisDict instance with id: " + id);
		try {
			CssdAntisepsisDict instance = (CssdAntisepsisDict) getSession()
					.get("cn.superion.cssd.entity.CssdAntisepsisDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdAntisepsisDict instance) {
		log.debug("finding CssdAntisepsisDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdAntisepsisDict").add(
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
	public List<CssdAntisepsisDict> findByProperty(String propertyName, Object value) {
		log.debug("finding CssdAntisepsisDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdAntisepsisDict as model where model."
					+ propertyName + "= ? order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdAntisepsisDict> findAll() {
		log.debug("finding all CssdAntisepsisDict instances");
		try {
			String queryString = "from CssdAntisepsisDict order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdAntisepsisDict merge(CssdAntisepsisDict detachedInstance) {
		log.debug("merging CssdAntisepsisDict instance");
		try {
			CssdAntisepsisDict result = (CssdAntisepsisDict) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdAntisepsisDict instance) {
		log.debug("attaching dirty CssdAntisepsisDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdAntisepsisDict instance) {
		log.debug("attaching clean CssdAntisepsisDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<CssdAntisepsisDict> findByPhoInputCode(String phoInputCode) {
		return this.findByProperty("phoInputCode", phoInputCode);
	}

	public List<CssdAntisepsisDict> findByFiveInputCode(String fiveInputCode) {
		return this.findByProperty("fiveInputCode", fiveInputCode);
	}
}