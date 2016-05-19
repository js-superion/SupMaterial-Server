package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdPackageMaster;
import cn.superion.cssd.entity.CssdWashDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdPackageMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdPackageMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdPackageMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdPackageMasterDAO.class);

	public void save(CssdPackageMaster transientInstance) {
		log.debug("saving CssdPackageMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdPackageMaster persistentInstance) {
		log.debug("deleting CssdPackageMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdPackageMaster findById(java.lang.String id) {
		log.debug("getting CssdPackageMaster instance with id: " + id);
		try {
			CssdPackageMaster instance = (CssdPackageMaster) getSession().get(
					"cn.superion.cssd.entity.CssdPackageMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdPackageMaster instance) {
		log.debug("finding CssdPackageMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdPackageMaster").add(
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
		log.debug("finding CssdPackageMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdPackageMaster as model where model."
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
		log.debug("finding all CssdPackageMaster instances");
		try {
			String queryString = "from CssdPackageMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdPackageMaster merge(CssdPackageMaster detachedInstance) {
		log.debug("merging CssdPackageMaster instance");
		try {
			CssdPackageMaster result = (CssdPackageMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdPackageMaster instance) {
		log.debug("attaching dirty CssdPackageMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdPackageMaster instance) {
		log.debug("attaching clean CssdPackageMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            以下为物品包记录属性
	 *            <li>packager 包装人</li>
	 *            <li>sterilizeNo 灭菌锅号</li>
	 *            <li>beginSterilizeDate 起始灭菌日期</li>
	 *            <li>endSterilizeDate 结束灭菌日期</li>
	 *            <li>packageNo 物品包号</li>
	 *            <li>sterilizeType 灭菌类型</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		String currentStatus = (String) conditions.get("currentStatus");
		String packager = (String) conditions.get("packager");
		String sterilizeNo = (String) conditions.get("sterilizeNo");
		Date beginSterilizeDate = (Date) conditions.get("beginSterilizeDate");
		Date endSterilizeDate = (Date) conditions.get("endSterilizeDate");
		String packageNo = (String) conditions.get("packageNo");
		String sterilizeType = (String) conditions.get("sterilizeType");
		// 主表条件
		StringBuilder shql = new StringBuilder(
				"select autoId from CssdPackageMaster p where unitsCode='")
				.append(unitsCode).append("'");
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
		if (currentStatus != null && !"".equals(currentStatus)) {
			shql.append(" and currentStatus=:currentStatus");
		}
		// 从表条件
		StringBuilder shql2 = new StringBuilder(
				"from CssdStockMaster s where s.unitsCode=p.unitsCode and s.packageAutoId=p.autoId");
		if (packager != null && !"".equals(packager)) {
			flag = true;
			shql2.append(" and s.packager=:packager");
		}
		if (sterilizeNo != null && !"".equals(sterilizeNo)) {
			flag = true;
			shql2.append(" and s.sterilizeNo=:sterilizeNo");
		}
		if (beginSterilizeDate != null) {
			flag = true;
			shql2.append(" and s.sterilizeDate>=:beginSterilizeDate");
		}
		if (endSterilizeDate != null) {
			flag = true;
			shql2.append(" and s.sterilizeDate<=:endSterilizeDate");
		}
		if (packageNo != null && !"".equals(packageNo)) {
			flag = true;
			shql2.append(" and s.packageNo=:packageNo");
		}
		if (sterilizeType != null && !"".equals(sterilizeType)) {
			flag = true;
			shql2.append(" and s.sterilizeType=:sterilizeType");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from CssdPackageMaster where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("billNo", billNo)
				.uniqueResult().toString());
		return cnt == 0;
	}

}