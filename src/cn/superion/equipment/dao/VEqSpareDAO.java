package cn.superion.equipment.dao;

import cn.superion.equipment.entity.VEqSpare;
import cn.superion.util.BaseHibernateDAO;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * VEqSpare entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.VEqSpare
 * @author MyEclipse Persistence Tools
 */

public class VEqSpareDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VEqSpareDAO.class);

	@SuppressWarnings("unchecked")
	public List<VEqSpare> findByExample(VEqSpare instance) {
		log.debug("finding VEqSpare instance by example");
		try {
			List<VEqSpare> results = getSession().createCriteria(
					"cn.superion.equipment.entity.VEqSpare").add(
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
	public List<VEqSpare> findByProperty(String propertyName, Object value) {
		log.debug("finding VEqSpare instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from VEqSpare as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>equipmentTypeCode 设备编码</li>
	 *            <li>sparePartsCode 备件编码</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String equipmentTypeCode = (String) conditions.get("equipmentTypeCode");
		String sparePartCode = (String) conditions.get("sparePartCode");
		String currentStatus = (String) conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("from VEqSpare where 1=1");
		if (equipmentTypeCode != null || "".equals(equipmentTypeCode)) {
			shql.append(" and equipmentTypeCode=:equipmentTypeCode");
		}
		if (sparePartCode != null || "".equals(sparePartCode)) {
			shql.append(" and sparePartCode=:sparePartCode");
		}
		if (currentStatus != null && !"".equals(currentStatus)) {
			shql.append(" and currentStatus=:currentStatus");
		}
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<VEqSpare> list = query.setProperties(conditions).setFirstResult(
				start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
}