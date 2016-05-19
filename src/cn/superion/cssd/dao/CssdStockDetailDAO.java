package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdStockDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdStockDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdStockDetail
 * @author MyEclipse Persistence Tools
 */

public class CssdStockDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdStockDetailDAO.class);
	
	public void save(CssdStockDetail transientInstance) {
		log.debug("saving CssdStockDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdStockDetail persistentInstance) {
		log.debug("deleting CssdStockDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdStockDetail findById(java.lang.String id) {
		log.debug("getting CssdStockDetail instance with id: " + id);
		try {
			CssdStockDetail instance = (CssdStockDetail) getSession().get(
					"cn.superion.cssd.entity.CssdStockDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdStockDetail instance) {
		log.debug("finding CssdStockDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdStockDetail").add(
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
		log.debug("finding CssdStockDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdStockDetail as model where model."
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
	public List findAll() {
		log.debug("finding all CssdStockDetail instances");
		try {
			String queryString = "from CssdStockDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdStockDetail merge(CssdStockDetail detachedInstance) {
		log.debug("merging CssdStockDetail instance");
		try {
			CssdStockDetail result = (CssdStockDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdStockDetail instance) {
		log.debug("attaching dirty CssdStockDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdStockDetail instance) {
		log.debug("attaching clean CssdStockDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByPackageAutoId(String unitsCode, String packageAutoId) {
		String hql = "delete from CssdStockDetail d where exists(from CssdStockMaster m where m.packageNo=d.packageNo and m.unitsCode=:unitsCode and m.packageAutoId=:packageAutoId)";
		getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("packageAutoId", packageAutoId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<CssdStockDetail> findByPackageNo(String packageNo) {
		return findByProperty("packageNo",packageNo);
	}
}