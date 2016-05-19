package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqJobPlanItem;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqJobPlanItem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobPlanItem
 * @author MyEclipse Persistence Tools
 */

public class EqJobPlanItemDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobPlanItemDAO.class);

	public void save(EqJobPlanItem transientInstance) {
		log.debug("saving EqJobPlanItem instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobPlanItem persistentInstance) {
		log.debug("deleting EqJobPlanItem instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobPlanItem> findByExample(EqJobPlanItem instance) {
		log.debug("finding EqJobPlanItem instance by example");
		try {
			List<EqJobPlanItem> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobPlanItem").add(
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
	public List<EqJobPlanItem> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobPlanItem instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqJobPlanItem as model where model."
					+ propertyName + "= ? order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public EqJobPlanItem merge(EqJobPlanItem detachedInstance) {
		log.debug("merging EqJobPlanItem instance");
		try {
			EqJobPlanItem result = (EqJobPlanItem) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobPlanItem instance) {
		log.debug("attaching dirty EqJobPlanItem instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobPlanItem instance) {
		log.debug("attaching clean EqJobPlanItem instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqJobPlanItem where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}

	public List<EqJobPlanItem> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}
}