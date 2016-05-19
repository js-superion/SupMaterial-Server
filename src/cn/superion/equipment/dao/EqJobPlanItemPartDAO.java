package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqJobPlanItemPart;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqJobPlanItemPart entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobPlanItemPart
 * @author MyEclipse Persistence Tools
 */

public class EqJobPlanItemPartDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobPlanItemPartDAO.class);

	public void save(EqJobPlanItemPart transientInstance) {
		log.debug("saving EqJobPlanItemPart instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobPlanItemPart persistentInstance) {
		log.debug("deleting EqJobPlanItemPart instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobPlanItemPart> findByExample(EqJobPlanItemPart instance) {
		log.debug("finding EqJobPlanItemPart instance by example");
		try {
			List<EqJobPlanItemPart> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobPlanItemPart").add(
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
	public List<EqJobPlanItemPart> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobPlanItemPart instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqJobPlanItemPart as model where model."
					+ propertyName + "= ? order by serialNo,sparePartCode";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqJobPlanItemPart merge(EqJobPlanItemPart detachedInstance) {
		log.debug("merging EqJobPlanItemPart instance");
		try {
			EqJobPlanItemPart result = (EqJobPlanItemPart) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobPlanItemPart instance) {
		log.debug("attaching dirty EqJobPlanItemPart instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobPlanItemPart instance) {
		log.debug("attaching clean EqJobPlanItemPart instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqJobPlanItemPart where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}

	public List<EqJobPlanItemPart> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}
}