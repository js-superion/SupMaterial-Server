package cn.superion.cssd.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdEquipmentDict;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdEquipmentDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdEquipmentDict
 * @author MyEclipse Persistence Tools
 */

public class CssdEquipmentDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdEquipmentDictDAO.class);

	public void save(CssdEquipmentDict transientInstance) {
		log.debug("saving CssdEquipmentDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdEquipmentDict persistentInstance) {
		log.debug("deleting CssdEquipmentDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public CssdEquipmentDict findById(String unitsCode, String equipmentCode) {
		log.debug("getting CssdEquipmentDict instance with equipmentCode: "
				+ equipmentCode);
		try {
			CssdEquipmentDict instance = (CssdEquipmentDict) getSession()
					.createQuery(
							"from CssdEquipmentDict where unitsCode=:unitsCode and equipmentCode=:equipmentCode")
					.setString("unitsCode", unitsCode).setString(
							"equipmentCode", equipmentCode).uniqueResult();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdEquipmentDict instance) {
		log.debug("finding CssdEquipmentDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdEquipmentDict").add(
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
	public List<CssdEquipmentDict> findByProperty(String propertyName, Object value) {
		log.debug("finding CssdEquipmentDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdEquipmentDict as model where model."
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
	public List<CssdEquipmentDict> findAll() {
		log.debug("finding all CssdEquipmentDict instances");
		try {
			String queryString = "from CssdEquipmentDict order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdEquipmentDict> findByCondtion(String condition) {
		log.debug("finding all CssdEquipmentDict instances");
		try {
			String queryString = "from CssdEquipmentDict "+condition;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public CssdEquipmentDict merge(CssdEquipmentDict detachedInstance) {
		log.debug("merging CssdEquipmentDict instance");
		try {
			CssdEquipmentDict result = (CssdEquipmentDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdEquipmentDict instance) {
		log.debug("attaching dirty CssdEquipmentDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdEquipmentDict instance) {
		log.debug("attaching clean CssdEquipmentDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public List<CssdEquipmentDict> findByPhoInputCode(String phoInputCode) {
		return this.findByProperty("phoInputCode", phoInputCode);
	}

	public List<CssdEquipmentDict> findByFiveInputCode(String fiveInputCode) {
		return this.findByProperty("fiveInputCode", fiveInputCode);
	}
}