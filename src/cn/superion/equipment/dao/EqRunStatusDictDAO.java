package cn.superion.equipment.dao;

import cn.superion.equipment.entity.EqRunStatusDict;
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
 * EqRunStatusDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqRunStatusDict
 * @author MyEclipse Persistence Tools
 */

public class EqRunStatusDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqRunStatusDictDAO.class);

	public void save(EqRunStatusDict transientInstance) {
		log.debug("saving EqRunStatusDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqRunStatusDict persistentInstance) {
		log.debug("deleting EqRunStatusDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqRunStatusDict findById(java.lang.String id) {
		log.debug("getting EqRunStatusDict instance with id: " + id);
		try {
			EqRunStatusDict instance = (EqRunStatusDict) getSession().get(
					"cn.superion.equipment.entity.EqRunStatusDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqRunStatusDict> findByExample(EqRunStatusDict instance) {
		log.debug("finding EqRunStatusDict instance by example");
		try {
			List<EqRunStatusDict> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqRunStatusDict").add(
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
	public List<EqRunStatusDict> findByProperty(String propertyName,
			Object value) {
		log.debug("finding EqRunStatusDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqRunStatusDict as model where model."
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
	public List<EqRunStatusDict> findAll() {
		log.debug("finding all EqRunStatusDict instances");
		try {
			String queryString = "from EqRunStatusDict order by statusCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqRunStatusDict merge(EqRunStatusDict detachedInstance) {
		log.debug("merging EqRunStatusDict instance");
		try {
			EqRunStatusDict result = (EqRunStatusDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqRunStatusDict instance) {
		log.debug("attaching dirty EqRunStatusDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqRunStatusDict instance) {
		log.debug("attaching clean EqRunStatusDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(EqRunStatusDict eqRunStatusDict) {
		getSession().update(eqRunStatusDict);
	}

	/**
	 * 通过Sql语句查询基础数据字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqRunStatusDict BaseData");
		try {
			String queryString = "select new map(statusCode as eqRunStatus,statusName as eqRunStatusName) from EqRunStatusDict order by statusCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
}