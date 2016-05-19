package cn.superion.equipment.dao;

import cn.superion.equipment.entity.EqFaultTypeDict;
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
 * EqFaultTypeDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqFaultTypeDict
 * @author MyEclipse Persistence Tools
 */

public class EqFaultTypeDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqFaultTypeDictDAO.class);

	public void save(EqFaultTypeDict transientInstance) {
		log.debug("saving EqFaultTypeDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqFaultTypeDict persistentInstance) {
		log.debug("deleting EqFaultTypeDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqFaultTypeDict findById(java.lang.String id) {
		log.debug("getting EqFaultTypeDict instance with id: " + id);
		try {
			EqFaultTypeDict instance = (EqFaultTypeDict) getSession().get(
					"cn.superion.equipment.entity.EqFaultTypeDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqFaultTypeDict> findByExample(EqFaultTypeDict instance) {
		log.debug("finding EqFaultTypeDict instance by example");
		try {
			List<EqFaultTypeDict> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqFaultTypeDict").add(
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
	public List<EqFaultTypeDict> findByProperty(String propertyName,
			Object value) {
		log.debug("finding EqFaultTypeDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqFaultTypeDict as model where model."
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
	public List<EqFaultTypeDict> findAll() {
		log.debug("finding all EqFaultTypeDict instances");
		try {
			String queryString = "from EqFaultTypeDict order by typeCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqFaultTypeDict merge(EqFaultTypeDict detachedInstance) {
		log.debug("merging EqFaultTypeDict instance");
		try {
			EqFaultTypeDict result = (EqFaultTypeDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqFaultTypeDict instance) {
		log.debug("attaching dirty EqFaultTypeDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqFaultTypeDict instance) {
		log.debug("attaching clean EqFaultTypeDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(EqFaultTypeDict eqFaultTypeDict) {
		getSession().update(eqFaultTypeDict);
	}

	/**
	 * 通过Sql语句查询基础数据字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqFaultTypeDict BaseData");
		try {
			String queryString = "select new map(typeCode as eqFaultType,typeName as eqFaultTypeName) from EqFaultTypeDict order by typeCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
}