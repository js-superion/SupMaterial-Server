package cn.superion.equipment.dao;

import cn.superion.equipment.entity.EqClassAbcDict;
import cn.superion.util.BaseHibernateDAO;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqClassAbcDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqClassAbcDict
 * @author MyEclipse Persistence Tools
 */

public class EqClassAbcDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqClassAbcDictDAO.class);

	public void save(EqClassAbcDict transientInstance) {
		log.debug("saving EqClassAbcDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqClassAbcDict persistentInstance) {
		log.debug("deleting EqClassAbcDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqClassAbcDict findById(java.lang.String id) {
		log.debug("getting EqClassAbcDict instance with id: " + id);
		try {
			EqClassAbcDict instance = (EqClassAbcDict) getSession().get(
					"cn.superion.equipment.entity.EqClassAbcDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqClassAbcDict> findByExample(EqClassAbcDict instance) {
		log.debug("finding EqClassAbcDict instance by example");
		try {
			List<EqClassAbcDict> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqClassAbcDict").add(
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
	public List<EqClassAbcDict> findByProperty(String propertyName, Object value) {
		log.debug("finding EqClassAbcDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqClassAbcDict as model where model."
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
	public List<EqClassAbcDict> findAll() {
		log.debug("finding all EqClassAbcDict instances");
		try {
			String queryString = "from EqClassAbcDict";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqClassAbcDict merge(EqClassAbcDict detachedInstance) {
		log.debug("merging EqClassAbcDict instance");
		try {
			EqClassAbcDict result = (EqClassAbcDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqClassAbcDict instance) {
		log.debug("attaching dirty EqClassAbcDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqClassAbcDict instance) {
		log.debug("attaching clean EqClassAbcDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(EqClassAbcDict eqClassAbcDict) {
		getSession().update(eqClassAbcDict);
	}

	/**
	 * 通过Sql语句查询基础数据字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqClassAbcDict BaseData");
		try {
			String queryString = "select new map(classCode as eqClassAbc,className as eqClassAbcName) from EqClassAbcDict order by classCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
}