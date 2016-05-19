package cn.superion.material.dao;

import cn.superion.util.BaseHibernateDAO;
import cn.superion.material.entity.MaterialChangePriceDetail;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialChangePriceDetail entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see cn.superion.material.entity.MaterialChangePriceDetail
 * @author MyEclipse Persistence Tools
 */

public class MaterialChangePriceDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialChangePriceDetailDAO.class);

	public void save(MaterialChangePriceDetail transientInstance) {
		log.debug("saving MaterialChangePriceDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialChangePriceDetail persistentInstance) {
		log.debug("deleting MaterialChangePriceDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialChangePriceDetail findById(java.lang.String id) {
		log.debug("getting MaterialChangePriceDetail instance with id: " + id);
		try {
			MaterialChangePriceDetail instance = (MaterialChangePriceDetail) getSession()
					.get(
							"cn.superion.material.entity.MaterialChangePriceDetail",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialChangePriceDetail> findByExample(
			MaterialChangePriceDetail instance) {
		log.debug("finding MaterialChangePriceDetail instance by example");
		try {
			List<MaterialChangePriceDetail> results = getSession()
					.createCriteria(
							"cn.superion.material.entity.MaterialChangePriceDetail")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialChangePriceDetail> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialChangePriceDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialChangePriceDetail as model where model."
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
	public List<MaterialChangePriceDetail> findAll() {
		log.debug("finding all MaterialChangePriceDetail instances");
		try {
			String queryString = "from MaterialChangePriceDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialChangePriceDetail merge(
			MaterialChangePriceDetail detachedInstance) {
		log.debug("merging MaterialChangePriceDetail instance");
		try {
			MaterialChangePriceDetail result = (MaterialChangePriceDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialChangePriceDetail instance) {
		log.debug("attaching dirty MaterialChangePriceDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialChangePriceDetail instance) {
		log.debug("attaching clean MaterialChangePriceDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 根据系统标识号删除费用明细记录
	 * 
	 * @param autoId
	 * @author 周作建
	 * @date 2011.06.21
	 */
	public void delByMainAutoId(String mainAutoId) {
		log.debug("delete MaterialChangePriceDetail by mainAutoId");
		try {
			String queryString = "delete from MaterialChangePriceDetail where mainAutoId=? ";
			Query queryObject = getSession().createQuery(queryString);

			queryObject.setParameter(0, mainAutoId);

			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete MaterialChangePriceDetail by mainAutoId failed",
					re);
			throw re;
		}
	}

	/**
	 * 根据条件查询
	 * 
	 * @date 2011-06-14
	 * @param conditions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialChangePriceDetail> findByCondition(String conditions) {
		log.debug("finding MaterialChangePriceDetail by condition");
		try {
			String queryString = " from MaterialChangePriceDetail" + conditions;
			Query query = getSession().createQuery(queryString);
			return query.list();
		} catch (RuntimeException re) {
			log.error("finding MaterialChangePriceDetail by condition failed",
					re);
			throw re;
		}
	}

}