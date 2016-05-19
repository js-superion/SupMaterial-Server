package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialSupplierDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialSupplierDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialSupplierDetail
 * @author MyEclipse Persistence Tools
 */

public class MaterialSupplierDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialSupplierDetailDAO.class);

	// property constants

	public void save(MaterialSupplierDetail transientInstance) {
		log.debug("saving MaterialSupplierDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialSupplierDetail persistentInstance) {
		log.debug("deleting MaterialSupplierDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void deleteByIds(List<String> ids) {
		log.debug("deleting MaterialSupplierDetail instance");
		try {
			String queryString = "delete MaterialSupplierDetail t where t.autoId in(:ids)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameterList("ids", ids).executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialSupplierDetail findById(String id) {
		try {
			MaterialSupplierDetail instance = (MaterialSupplierDetail) getSession()
					.get("cn.superion.material.entity.MaterialSupplierDetail",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MaterialSupplierDetail instance) {
		log.debug("finding MaterialSupplierDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialSupplierDetail").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MaterialSupplierDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialSupplierDetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all MaterialSupplierDetail instances");
		try {
			String queryString = "from MaterialSupplierDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialSupplierDetail merge(MaterialSupplierDetail detachedInstance) {
		log.debug("merging MaterialSupplierDetail instance");
		try {
			MaterialSupplierDetail result = (MaterialSupplierDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialSupplierDetail instance) {
		log.debug("attaching dirty MaterialSupplierDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialSupplierDetail instance) {
		log.debug("attaching clean MaterialSupplierDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}