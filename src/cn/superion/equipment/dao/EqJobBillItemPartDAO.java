package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqJobBillItemPart;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqJobBillItemPart entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobBillItemPart
 * @author MyEclipse Persistence Tools
 */

public class EqJobBillItemPartDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobBillItemPartDAO.class);

	public void save(EqJobBillItemPart transientInstance) {
		log.debug("saving EqJobBillItemPart instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobBillItemPart persistentInstance) {
		log.debug("deleting EqJobBillItemPart instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobBillItemPart> findByExample(EqJobBillItemPart instance) {
		log.debug("finding EqJobBillItemPart instance by example");
		try {
			List<EqJobBillItemPart> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobBillItemPart").add(
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
	public List<EqJobBillItemPart> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobBillItemPart instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqJobBillItemPart as model where model."
					+ propertyName + "= ? order by serialNo,sparePartCode";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqJobBillItemPart merge(EqJobBillItemPart detachedInstance) {
		log.debug("merging EqJobBillItemPart instance");
		try {
			EqJobBillItemPart result = (EqJobBillItemPart) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobBillItemPart instance) {
		log.debug("attaching dirty EqJobBillItemPart instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobBillItemPart instance) {
		log.debug("attaching clean EqJobBillItemPart instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqJobBillItemPart where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}

	public List<EqJobBillItemPart> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}
}