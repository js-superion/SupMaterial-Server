package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdChemistryMaterialDict;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdChemistryMaterialDict entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see cn.superion.cssd.entity.CssdChemistryMaterialDict
 * @author MyEclipse Persistence Tools
 */

public class CssdChemistryMaterialDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdChemistryMaterialDictDAO.class);

	public void save(CssdChemistryMaterialDict transientInstance) {
		log.debug("saving CssdChemistryMaterialDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdChemistryMaterialDict persistentInstance) {
		log.debug("deleting CssdChemistryMaterialDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdChemistryMaterialDict findById(java.lang.String id) {
		log.debug("getting CssdChemistryMaterialDict instance with id: " + id);
		try {
			CssdChemistryMaterialDict instance = (CssdChemistryMaterialDict) getSession()
					.get("cn.superion.cssd.entity.CssdChemistryMaterialDict",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdChemistryMaterialDict instance) {
		log.debug("finding CssdChemistryMaterialDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdChemistryMaterialDict").add(
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
	public List<CssdChemistryMaterialDict> findByProperty(String propertyName, Object value) {
		log.debug("finding CssdChemistryMaterialDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdChemistryMaterialDict as model where model."
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
	public List<CssdChemistryMaterialDict> findAll() {
		log.debug("finding all CssdChemistryMaterialDict instances");
		try {
			String queryString = "from CssdChemistryMaterialDict order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdChemistryMaterialDict merge(
			CssdChemistryMaterialDict detachedInstance) {
		log.debug("merging CssdChemistryMaterialDict instance");
		try {
			CssdChemistryMaterialDict result = (CssdChemistryMaterialDict) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdChemistryMaterialDict instance) {
		log.debug("attaching dirty CssdChemistryMaterialDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdChemistryMaterialDict instance) {
		log.debug("attaching clean CssdChemistryMaterialDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<CssdChemistryMaterialDict> findByPhoInputCode(String phoInputCode) {
		return this.findByProperty("phoInputCode", phoInputCode);
	}

	public List<CssdChemistryMaterialDict> findByFiveInputCode(String fiveInputCode) {
		return this.findByProperty("fiveInputCode", fiveInputCode);
	}
}