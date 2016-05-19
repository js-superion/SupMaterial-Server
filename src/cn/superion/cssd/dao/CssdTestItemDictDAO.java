package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdTestItemDict;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdTestItemDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdTestItemDict
 * @author MyEclipse Persistence Tools
 */

public class CssdTestItemDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdTestItemDictDAO.class);

	public void save(CssdTestItemDict transientInstance) {
		log.debug("saving CssdTestItemDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdTestItemDict persistentInstance) {
		log.debug("deleting CssdTestItemDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdTestItemDict findById(String specimenCode,String 

			itemCode) {
					log.debug("getting CssdTestItemDict instance with itemCode: " + itemCode);
					try {
						CssdTestItemDict instance = (CssdTestItemDict) 

			getSession().createQuery(
						"from CssdTestItemDict where specimenCode=:specimenCode and itemCodee=:itemCode")
						.setString("specimenCode", specimenCode).setString(
								"itemCode", itemCode).uniqueResult();
						return instance;
					} catch (RuntimeException re) {
						log.error("get failed", re);
						throw re;
					}
				}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdTestItemDict instance) {
		log.debug("finding CssdTestItemDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdTestItemDict").add(
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
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CssdTestItemDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdTestItemDict as model where model."
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
	public List findAll() {
		log.debug("finding all CssdTestItemDict instances");
		try {
			String queryString = "from CssdTestItemDict";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdTestItemDict merge(CssdTestItemDict detachedInstance) {
		log.debug("merging CssdTestItemDict instance");
		try {
			CssdTestItemDict result = (CssdTestItemDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdTestItemDict instance) {
		log.debug("attaching dirty CssdTestItemDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdTestItemDict instance) {
		log.debug("attaching clean CssdTestItemDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdTestItemDict> findBySpecimenCode(String specimenCode) {
		String hql = "from CssdTestItemDict where specimenCode=:specimenCode order by serialNo";
		return getSession().createQuery(hql).setString("specimenCode", specimenCode).list();
	}

	
}