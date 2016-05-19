package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqJobContentItemPart;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqJobContentItemPart entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobContentItemPart
 * @author MyEclipse Persistence Tools
 */

public class EqJobContentItemPartDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobContentItemPartDAO.class);

	public void save(EqJobContentItemPart transientInstance) {
		log.debug("saving EqJobContentItemPart instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobContentItemPart persistentInstance) {
		log.debug("deleting EqJobContentItemPart instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobContentItemPart> findByExample(EqJobContentItemPart instance) {
		log.debug("finding EqJobContentItemPart instance by example");
		try {
			List<EqJobContentItemPart> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobContentItemPart").add(
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
	public List<EqJobContentItemPart> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobContentItemPart instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqJobContentItemPart as model where model."
					+ propertyName + "= ? order by serialNo,sparePartCode";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqJobContentItemPart merge(EqJobContentItemPart detachedInstance) {
		log.debug("merging EqJobContentItemPart instance");
		try {
			EqJobContentItemPart result = (EqJobContentItemPart) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobContentItemPart instance) {
		log.debug("attaching dirty EqJobContentItemPart instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobContentItemPart instance) {
		log.debug("attaching clean EqJobContentItemPart instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqJobContentItemPart where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}

	public List<EqJobContentItemPart> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}

	@SuppressWarnings("unchecked")
	public List<EqJobContentItemPart> findByEquipmentCode(String unitsCode,
			String equipmentCode) {
		String hql = "from EqJobContentItemPart a,EqJobContent b where a.autoId=b.autoId and b.unitsCode=:unitsCode and b.objectType='1' and b.objectCode=:equipmentCode";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("equipmentCode", equipmentCode).list();
	}
}