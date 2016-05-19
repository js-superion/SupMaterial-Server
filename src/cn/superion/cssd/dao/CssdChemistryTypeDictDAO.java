package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdChemistryTypeDict;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdChemistryTypeDict entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdChemistryTypeDict
 * @author MyEclipse Persistence Tools
 */

public class CssdChemistryTypeDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdChemistryTypeDictDAO.class);

	public void save(CssdChemistryTypeDict transientInstance) {
		log.debug("saving CssdChemistryTypeDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdChemistryTypeDict persistentInstance) {
		log.debug("deleting CssdChemistryTypeDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdChemistryTypeDict findById(java.lang.String id) {
		log.debug("getting CssdChemistryTypeDict instance with id: " + id);
		try {
			CssdChemistryTypeDict instance = (CssdChemistryTypeDict) getSession()
					.get("cn.superion.cssd.entity.CssdChemistryTypeDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdChemistryTypeDict instance) {
		log.debug("finding CssdChemistryTypeDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdChemistryTypeDict").add(
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
	public List<CssdChemistryTypeDict> findByProperty(String propertyName, Object value) {
		log.debug("finding CssdChemistryTypeDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdChemistryTypeDict as model where model."
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
	public List<CssdChemistryTypeDict> findAll() {
		log.debug("finding all CssdChemistryTypeDict instances");
		try {
			String queryString = "from CssdChemistryTypeDict order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdChemistryTypeDict merge(CssdChemistryTypeDict detachedInstance) {
		log.debug("merging CssdChemistryTypeDict instance");
		try {
			CssdChemistryTypeDict result = (CssdChemistryTypeDict) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdChemistryTypeDict instance) {
		log.debug("attaching dirty CssdChemistryTypeDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdChemistryTypeDict instance) {
		log.debug("attaching clean CssdChemistryTypeDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<CssdChemistryTypeDict> findByPhoInputCode(String phoInputCode) {
		return this.findByProperty("phoInputCode", phoInputCode);
	}

	public List<CssdChemistryTypeDict> findByFiveInputCode(String fiveInputCode) {
		return this.findByProperty("fiveInputCode", fiveInputCode);
	}
}