package cn.superion.equipment.dao;

import cn.superion.equipment.entity.EqStatusDict;
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
 * EqStatusDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqStatusDict
 * @author MyEclipse Persistence Tools
 */

public class EqStatusDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqStatusDictDAO.class);

	public void save(EqStatusDict transientInstance) {
		log.debug("saving EqStatusDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqStatusDict persistentInstance) {
		log.debug("deleting EqStatusDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqStatusDict findById(java.lang.String id) {
		log.debug("getting EqStatusDict instance with id: " + id);
		try {
			EqStatusDict instance = (EqStatusDict) getSession().get(
					"cn.superion.equipment.entity.EqStatusDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqStatusDict> findByExample(EqStatusDict instance) {
		log.debug("finding EqStatusDict instance by example");
		try {
			List<EqStatusDict> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqStatusDict").add(
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
	public List<EqStatusDict> findByProperty(String propertyName, Object value) {
		log.debug("finding EqStatusDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqStatusDict as model where model."
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
	public List<EqStatusDict> findAll() {
		log.debug("finding all EqStatusDict instances");
		try {
			String queryString = "from EqStatusDict order by statusCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqStatusDict merge(EqStatusDict detachedInstance) {
		log.debug("merging EqStatusDict instance");
		try {
			EqStatusDict result = (EqStatusDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqStatusDict instance) {
		log.debug("attaching dirty EqStatusDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqStatusDict instance) {
		log.debug("attaching clean EqStatusDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(EqStatusDict eqStatusDict) {
		getSession().update(eqStatusDict);
	}

	/**
	 * 通过Sql语句查询基础数据字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqStatusDict BaseData");
		try {
			String queryString = "select new map(statusCode as eqStatus,statusName as eqStatusName) from EqStatusDict order by statusCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
}