package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.VCssdRetrieve;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VCssdRetrieve entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.VCssdRetrieve
 * @author MyEclipse Persistence Tools
 */

public class VCssdRetrieveDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VCssdRetrieveDAO.class);

	public void save(VCssdRetrieve transientInstance) {
		log.debug("saving VCssdRetrieve instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VCssdRetrieve persistentInstance) {
		log.debug("deleting VCssdRetrieve instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(VCssdRetrieve instance) {
		log.debug("finding VCssdRetrieve instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.VCssdRetrieve").add(
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
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding VCssdRetrieve instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VCssdRetrieve as model where model."
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
	public List findAll() {
		log.debug("finding all VCssdRetrieve instances");
		try {
			String queryString = "from VCssdRetrieve";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VCssdRetrieve merge(VCssdRetrieve detachedInstance) {
		log.debug("merging VCssdRetrieve instance");
		try {
			VCssdRetrieve result = (VCssdRetrieve) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VCssdRetrieve instance) {
		log.debug("attaching dirty VCssdRetrieve instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VCssdRetrieve instance) {
		log.debug("attaching clean VCssdRetrieve instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 统计报损物资数量
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含
	 *            <ul>
	 *            <li>deptCode 回收科室</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            </ul>
	 * @return {@code List<Map<String, Object>>},map里包含
	 *         <ul>
	 *         <li>deptCode 回收科室</li>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 物资规格</li>
	 *         <li>materialUnits 物资单位</li>
	 *         <li>tradeMoney 金额</li>
	 *         <li>amount 报损数量</li>
	 *         <li>factoryCode 生产厂家</li>
	 *         </ul>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> addUpByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String deptCode = (String) conditions.get("deptCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String packageClass = (String) conditions.get("packageClass");
		String packageId = (String) conditions.get("packageId");
		StringBuilder shql = new StringBuilder(
				"select new map(r.deptCode as deptCode,r.materialCode as materialCode,r.materialName as materialName,r.materialSpec as materialSpec,r.materialUnits as materialUnits,sum(r.tradePrice*(r.deliverAmount-r.amount)) as tradeMoney,sum(r.deliverAmount-r.amount) as amount,r.factoryCode as factoryCode) from VCssdRetrieve r,CssdStockMaster s where  r.unitsCode=s.unitsCode and r.packageNo=s.packageNo and r.rejectSign='1' and r.unitsCode='")
				.append(unitsCode).append("'");
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and r.deptCode=:deptCode");
		}
		if (beginBillDate != null) {
			shql.append(" and r.billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and r.billDate<=:endBillDate");
		}
		if (packageClass != null && !"".equals(packageClass)) {
			shql.append(" and s.packageClass=:packageClass");
		}
		if (packageId != null && !"".equals(packageId)) {
			shql.append(" and s.packageId=:packageId");
		}
		shql
				.append(" group by r.deptCode,r.materialCode,r.materialName,r.materialSpec,r.materialUnits,r.factoryCode");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
}