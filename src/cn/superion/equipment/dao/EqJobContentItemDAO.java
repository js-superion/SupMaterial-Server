package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqJobContentItem;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqJobContentItem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobContentItem
 * @author MyEclipse Persistence Tools
 */

public class EqJobContentItemDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobContentItemDAO.class);

	public void save(EqJobContentItem transientInstance) {
		log.debug("saving EqJobContentItem instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobContentItem persistentInstance) {
		log.debug("deleting EqJobContentItem instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobContentItem> findByExample(EqJobContentItem instance) {
		log.debug("finding EqJobContentItem instance by example");
		try {
			List<EqJobContentItem> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobContentItem").add(
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
	public List<EqJobContentItem> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobContentItem instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqJobContentItem as model where model."
					+ propertyName + "= ? order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqJobContentItem merge(EqJobContentItem detachedInstance) {
		log.debug("merging EqJobContentItem instance");
		try {
			EqJobContentItem result = (EqJobContentItem) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobContentItem instance) {
		log.debug("attaching dirty EqJobContentItem instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobContentItem instance) {
		log.debug("attaching clean EqJobContentItem instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqJobContentItem where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}

	public List<EqJobContentItem> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}

	@SuppressWarnings("unchecked")
	public List<EqJobContentItem> findByEquipmentCode(String unitsCode,String equipmentCode) {
		String hql = "from EqJobContentItem a,EqJobContent b where a.autoId=b.autoId and b.unitsCode=:unitsCode and b.objectType='1' and b.objectCode=:equipmentCode";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("equipmentCode", equipmentCode).list();
	}
}