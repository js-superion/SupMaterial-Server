package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialMonth;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialMonth entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialMonth
 * @author MyEclipse Persistence Tools
 */

public class MaterialMonthDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialMonthDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String YEAR_MONTH = "yearMonth";
	public static final String ACCOUNT_SIGN = "accountSign";
	public static final String CREATE_PERSON = "createPerson";

	public void save(MaterialMonth transientInstance) {
		log.debug("saving MaterialMonth instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialMonth persistentInstance) {
		log.debug("deleting MaterialMonth instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialMonth findById(java.lang.String id) {
		log.debug("getting MaterialMonth instance with id: " + id);
		try {
			MaterialMonth instance = (MaterialMonth) getSession().get(
					"cn.superion.material.entity.MaterialMonth", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialMonth> findByExample(MaterialMonth instance) {
		log.debug("finding MaterialMonth instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialMonth").add(
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
	public List<MaterialMonth> findByProperty(String propertyName, Object value) {
		log.debug("finding MaterialMonth instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialMonth as model where model."
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
	public List<MaterialMonth> findAll() {
		log.debug("finding all MaterialMonth instances");
		try {
			String queryString = "from MaterialMonth";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialMonth merge(MaterialMonth detachedInstance) {
		log.debug("merging MaterialMonth instance");
		try {
			MaterialMonth result = (MaterialMonth) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialMonth instance) {
		log.debug("attaching dirty MaterialMonth instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialMonth instance) {
		log.debug("attaching clean MaterialMonth instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public MaterialMonth findByStorageAndMonth(String unitsCode,
			String storageCode, String yearMonth) {
		String hql = "from MaterialMonth where unitsCode=:unitsCode and storageCode=:storageCode and yearMonth=:yearMonth";
		return (MaterialMonth) getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode)
				.setString("yearMonth", yearMonth).uniqueResult();
	}
	

	/**
	 * 
	 * @param unitsCode
	 * @param storageCode
	 * @param year
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialMonth> findByStorageAndYear(String unitsCode,
			String storageCode, String year) {
		String hql = "from MaterialMonth where unitsCode=:unitsCode and storageCode=:storageCode and yearMonth like :year";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("storageCode", storageCode).setString("year",
						year + "%").list();
	}
}