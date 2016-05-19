package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.superion.material.entity.VMaterialReject;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialReject entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.VMaterialReject
 * @author MyEclipse Persistence Tools
 */

public class VMaterialRejectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialRejectDAO.class);

	@SuppressWarnings("unchecked")
	public List<VMaterialReject> findByExample(VMaterialReject instance) {
		log.debug("finding VMaterialReject instance by example");
		try {
			List<VMaterialReject> results = getSession().createCriteria(
					"cn.superion.material.entity.VMaterialReject").add(
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
	public List<VMaterialReject> findByProperty(String propertyName,
			Object value) {
		log.debug("finding VMaterialReject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialReject as model where model."
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
	public List<VMaterialReject> findAll() {
		log.debug("finding all VMaterialReject instances");
		try {
			String queryString = "from VMaterialReject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
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
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始报损单号</li>
	 *            <li>endBillNo 结束报损单号</li>
	 *            <li>beginBillDate 起始报损日期</li>
	 *            <li>endBillDate 结束报损日期</li>
	 *            <li>deptCode 报损部门</li>
	 *            <li>personId 经手人</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String storageCode = (String) conditions.get("storageCode");
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String deptCode = (String) conditions.get("deptCode");
		String personId = (String) conditions.get("personId");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		String factoryCode = (String) conditions.get("factoryCode");
		String currentStatus = (String) conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder(
				"from VMaterialReject where unitsCode='").append(unitsCode)
				.append("'");

		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and billNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and billNo<=:endBillNo");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and outDeptCode=:deptCode");
		}
		if (personId != null && !"".equals(personId)) {
			shql.append(" and personId=:personId");
		}
		if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
			shql.append(" and materialCode>=:beginMaterialCode");
		}
		if (endMaterialCode != null && !"".equals(endMaterialCode)) {
			shql.append(" and materialCode<=:endMaterialCode");
		}
		if (factoryCode != null && !"".equals(factoryCode)) {
			shql.append(" and factoryCode=:factoryCode");
		}
		if (currentStatus != null && !"".equals(currentStatus)) {
			shql.append(" and currentStatus=:currentStatus");
		}
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<VMaterialReject> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
}