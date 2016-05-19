package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqEquipmentFiles;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqEquipmentFiles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqEquipmentFiles
 * @author MyEclipse Persistence Tools
 */

public class EqEquipmentFilesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqEquipmentFilesDAO.class);

	public void save(EqEquipmentFiles transientInstance) {
		log.debug("saving EqEquipmentFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqEquipmentFiles persistentInstance) {
		log.debug("deleting EqEquipmentFiles instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqEquipmentFiles> findByExample(EqEquipmentFiles instance) {
		log.debug("finding EqEquipmentFiles instance by example");
		try {
			List<EqEquipmentFiles> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqEquipmentFiles").add(
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
	public List<EqEquipmentFiles> findByProperty(String propertyName, Object value) {
		log.debug("finding EqEquipmentFiles instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqEquipmentFiles as model where model."
					+ propertyName + "= ? order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqEquipmentFiles merge(EqEquipmentFiles detachedInstance) {
		log.debug("merging EqEquipmentFiles instance");
		try {
			EqEquipmentFiles result = (EqEquipmentFiles) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqEquipmentFiles instance) {
		log.debug("attaching dirty EqEquipmentFiles instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqEquipmentFiles instance) {
		log.debug("attaching clean EqEquipmentFiles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqEquipmentFiles where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}

	public List<EqEquipmentFiles> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}
}