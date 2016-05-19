package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqChangeDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqChangeDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqChangeDetail
 * @author MyEclipse Persistence Tools
 */

public class EqChangeDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqChangeDetailDAO.class);

	public void save(EqChangeDetail transientInstance) {
		log.debug("saving EqChangeDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqChangeDetail persistentInstance) {
		log.debug("deleting EqChangeDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqChangeDetail> findByExample(EqChangeDetail instance) {
		log.debug("finding EqChangeDetail instance by example");
		try {
			List<EqChangeDetail> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqChangeDetail").add(
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
	public List<EqChangeDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding EqChangeDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqChangeDetail as model where model."
					+ propertyName + "= ? order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqChangeDetail merge(EqChangeDetail detachedInstance) {
		log.debug("merging EqChangeDetail instance");
		try {
			EqChangeDetail result = (EqChangeDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqChangeDetail instance) {
		log.debug("attaching dirty EqChangeDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqChangeDetail instance) {
		log.debug("attaching clean EqChangeDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqChangeDetail where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}

	public List<EqChangeDetail> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}
}