package cn.superion.equipment.dao;

import cn.superion.equipment.entity.EqSparePartsDetail;
import cn.superion.util.BaseHibernateDAO;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqSparePartsDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqSparePartsDetail
 * @author MyEclipse Persistence Tools
 */

public class EqSparePartsDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqSparePartsDetailDAO.class);

	public void save(EqSparePartsDetail transientInstance) {
		log.debug("saving EqSparePartsDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqSparePartsDetail persistentInstance) {
		log.debug("deleting EqSparePartsDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqSparePartsDetail> findByExample(EqSparePartsDetail instance) {
		log.debug("finding EqSparePartsDetail instance by example");
		try {
			List<EqSparePartsDetail> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqSparePartsDetail").add(
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
	public List<EqSparePartsDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding EqSparePartsDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqSparePartsDetail as model where model."
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
	public List<EqSparePartsDetail> findAll() {
		log.debug("finding all EqSparePartsDetail instances");
		try {
			String queryString = "from EqSparePartsDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqSparePartsDetail merge(EqSparePartsDetail detachedInstance) {
		log.debug("merging EqSparePartsDetail instance");
		try {
			EqSparePartsDetail result = (EqSparePartsDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqSparePartsDetail instance) {
		log.debug("attaching dirty EqSparePartsDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqSparePartsDetail instance) {
		log.debug("attaching clean EqSparePartsDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByEquipmentTypeCode(String equipmentTypeCode) {
		String hql = "delete from EqSparePartsDetail where equipmentTypeCode=:equipmentTypeCode";
		getSession().createQuery(hql).setString("equipmentTypeCode", equipmentTypeCode).executeUpdate();
	}
	public List<EqSparePartsDetail> findByEquipmentTypeCode(
			String equipmentTypeCode) {
	return findByProperty("equipmentTypeCode",equipmentTypeCode);
	}


}