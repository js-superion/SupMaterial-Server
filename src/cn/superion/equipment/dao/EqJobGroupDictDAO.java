package cn.superion.equipment.dao;

import cn.superion.equipment.entity.EqJobGroupDict;
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
 * EqJobGroupDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobGroupDict
 * @author MyEclipse Persistence Tools
 */

public class EqJobGroupDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobGroupDictDAO.class);

	public void save(EqJobGroupDict transientInstance) {
		log.debug("saving EqJobGroupDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobGroupDict persistentInstance) {
		log.debug("deleting EqJobGroupDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqJobGroupDict findById(java.lang.String id) {
		log.debug("getting EqJobGroupDict instance with id: " + id);
		try {
			EqJobGroupDict instance = (EqJobGroupDict) getSession().get(
					"cn.superion.equipment.entity.EqJobGroupDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobGroupDict> findByExample(EqJobGroupDict instance) {
		log.debug("finding EqJobGroupDict instance by example");
		try {
			List<EqJobGroupDict> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobGroupDict").add(
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
	public List<EqJobGroupDict> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobGroupDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqJobGroupDict as model where model."
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
	public List<EqJobGroupDict> findAll() {
		log.debug("finding all EqJobGroupDict instances");
		try {
			String queryString = "from EqJobGroupDict order by jobCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqJobGroupDict merge(EqJobGroupDict detachedInstance) {
		log.debug("merging EqJobGroupDict instance");
		try {
			EqJobGroupDict result = (EqJobGroupDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobGroupDict instance) {
		log.debug("attaching dirty EqJobGroupDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobGroupDict instance) {
		log.debug("attaching clean EqJobGroupDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(EqJobGroupDict eqJobGroupDict) {
		getSession().update(eqJobGroupDict);
	}

	/**
	 * 通过Sql语句查询基础数据字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqJobGroupDict BaseData");
		try {
			String queryString = "select new map(jobCode as eqJobGroup,jobName as eqJobGroupName) from EqJobGroupDict order by jobCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
}