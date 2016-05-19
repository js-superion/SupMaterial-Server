package cn.superion.equipment.dao;

import cn.superion.equipment.entity.EqJobTypeDict;
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
 * EqJobTypeDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobTypeDict
 * @author MyEclipse Persistence Tools
 */

public class EqJobTypeDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobTypeDictDAO.class);

	public void save(EqJobTypeDict transientInstance) {
		log.debug("saving EqJobTypeDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobTypeDict persistentInstance) {
		log.debug("deleting EqJobTypeDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqJobTypeDict findById(java.lang.String id) {
		log.debug("getting EqJobTypeDict instance with id: " + id);
		try {
			EqJobTypeDict instance = (EqJobTypeDict) getSession().get(
					"cn.superion.equipment.entity.EqJobTypeDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobTypeDict> findByExample(EqJobTypeDict instance) {
		log.debug("finding EqJobTypeDict instance by example");
		try {
			List<EqJobTypeDict> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobTypeDict").add(
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
	public List<EqJobTypeDict> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobTypeDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqJobTypeDict as model where model."
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
	public List<EqJobTypeDict> findAll() {
		log.debug("finding all EqJobTypeDict instances");
		try {
			String queryString = "from EqJobTypeDict order by typeCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqJobTypeDict merge(EqJobTypeDict detachedInstance) {
		log.debug("merging EqJobTypeDict instance");
		try {
			EqJobTypeDict result = (EqJobTypeDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobTypeDict instance) {
		log.debug("attaching dirty EqJobTypeDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobTypeDict instance) {
		log.debug("attaching clean EqJobTypeDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(EqJobTypeDict eqJobTypeDict) {
		getSession().update(eqJobTypeDict);
	}

	/**
	 * 通过Sql语句查询基础数据字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqJobTypeDict BaseData");
		try {
			String queryString = "select new map(typeCode as eqJobType,typeName as eqJobTypeName) from EqJobTypeDict order by typeCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
}