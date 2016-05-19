package cn.superion.equipment.dao;

import cn.superion.util.BaseHibernateDAO;
import cn.superion.equipment.entity.EqSparePartsMaster;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqSparePartsMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqSparePartsMaster
 * @author MyEclipse Persistence Tools
 */

public class EqSparePartsMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqSparePartsMasterDAO.class);

	public void save(EqSparePartsMaster transientInstance) {
		log.debug("saving EqSparePartsMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqSparePartsMaster persistentInstance) {
		log.debug("deleting EqSparePartsMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqSparePartsMaster findById(java.lang.String id) {
		log.debug("getting EqSparePartsMaster instance with id: " + id);
		try {
			EqSparePartsMaster instance = (EqSparePartsMaster) getSession()
					.get("cn.superion.equipment.entity.EqSparePartsMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqSparePartsMaster> findByExample(EqSparePartsMaster instance) {
		log.debug("finding EqSparePartsMaster instance by example");
		try {
			List<EqSparePartsMaster> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqSparePartsMaster").add(
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
	public List<EqSparePartsMaster> findByProperty(String propertyName, Object value) {
		log.debug("finding EqSparePartsMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqSparePartsMaster as model where model."
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
	public List<EqSparePartsMaster> findAll() {
		log.debug("finding all EqSparePartsMaster instances");
		try {
			String queryString = "from EqSparePartsMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqSparePartsMaster merge(EqSparePartsMaster detachedInstance) {
		log.debug("merging EqSparePartsMaster instance");
		try {
			EqSparePartsMaster result = (EqSparePartsMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqSparePartsMaster instance) {
		log.debug("attaching dirty EqSparePartsMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqSparePartsMaster instance) {
		log.debug("attaching clean EqSparePartsMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>equipmentTypeCode 设备编码</li>
	 *            以下为明细记录条件
	 *            <li>sparePartsCode 备件编码</li>
	 *             <li>currentStatus 当前状态，新建、审核</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object>  findAutoIdsByCondition( Map<String, Object> conditions) {
		String equipmentTypeCode = (String)conditions.get("equipmentTypeCode");
		String sparePartCode = (String)conditions.get("sparePartCode");
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("select equipmentTypeCode from EqSparePartsMaster m where 1=1");
		boolean flag = false;
		if(equipmentTypeCode != null && !"".equals(equipmentTypeCode)){
			shql.append(" and equipmentTypeCode=:equipmentTypeCode");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		// 从表条件
		StringBuilder shql2 = new StringBuilder(
				"from EqSparePartsDetail d where d.equipmentTypeCode=m.equipmentTypeCode");
		if (sparePartCode != null && !"".equals(equipmentTypeCode)) {
			flag = true;
			shql2.append(" and sparePartCode=:sparePartCode");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
		}
	public void delByEquipmentTypeCode(String equipmentTypeCode) {
		String hql = "delete from EqSparePartsMaster where equipmentTypeCode=:equipmentTypeCode";
		getSession().createQuery(hql).setString("equipmentTypeCode", equipmentTypeCode).executeUpdate();
	}
	public void update(EqSparePartsMaster eqSparePartsMaster){
		getSession().update(eqSparePartsMaster);
	}

	}

	
