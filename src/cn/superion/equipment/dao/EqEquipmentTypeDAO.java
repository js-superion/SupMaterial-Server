package cn.superion.equipment.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqEquipmentType;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqEquipmentType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqEquipmentType
 * @author MyEclipse Persistence Tools
 */

public class EqEquipmentTypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqEquipmentTypeDAO.class);

	public void save(EqEquipmentType transientInstance) {
		log.debug("saving EqEquipmentType instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqEquipmentType persistentInstance) {
		log.debug("deleting EqEquipmentType instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqEquipmentType findById(java.lang.String id) {
		log.debug("getting EqEquipmentType instance with id: " + id);
		try {
			EqEquipmentType instance = (EqEquipmentType) getSession().get(
					"cn.superion.equipment.entity.EqEquipmentType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqEquipmentType> findByExample(EqEquipmentType instance) {
		log.debug("finding EqEquipmentType instance by example");
		try {
			List<EqEquipmentType> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqEquipmentType").add(
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
	public List<EqEquipmentType> findByProperty(String propertyName, Object value) {
		log.debug("finding EqEquipmentType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqEquipmentType as model where model."
					+ propertyName + "= ? order by equipmentTypeCode";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqEquipmentType merge(EqEquipmentType detachedInstance) {
		log.debug("merging EqEquipmentType instance");
		try {
			EqEquipmentType result = (EqEquipmentType) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqEquipmentType instance) {
		log.debug("attaching dirty EqEquipmentType instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqEquipmentType instance) {
		log.debug("attaching clean EqEquipmentType instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	/**
	 * 通过Sql语句查询基础数据字典
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findBaseData() {
		log.debug("finding EqEquipmentType ");
		try {
			String queryString = "select new map(equipmentTypeCode as eqEquipmentTypeCode,equipmentTypeName as eqEquipmentTypeName) from EqEquipmentType order by equipmentTypeCode";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}

	public List<EqEquipmentType> findByClassCode(String classCode) {
		return findByProperty("equipmentClass",classCode);
	}

	public boolean checkTypeCodeUnique(String typeCode) {
		return checkTypeCodeUnique(typeCode,null);
	}
	
	public boolean checkTypeCodeUnique(String typeCode,String excludedAutoId) {
		String hql = "select count(*) from EqEquipmentType where equipmentTypeCode=:typeCode";
		if(excludedAutoId != null && !"".equals(excludedAutoId)){
			hql += " and autoId<>'"+excludedAutoId+"'";
		}
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"typeCode", typeCode).uniqueResult().toString());
		return cnt == 0;
	}
}