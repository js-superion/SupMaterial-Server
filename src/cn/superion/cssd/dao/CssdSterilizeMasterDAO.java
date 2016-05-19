package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdSterilizeMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdSterilizeMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdSterilizeMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdSterilizeMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdSterilizeMasterDAO.class);

	public void save(CssdSterilizeMaster transientInstance) {
		log.debug("saving CssdSterilizeMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdSterilizeMaster persistentInstance) {
		log.debug("deleting CssdSterilizeMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdSterilizeMaster findById(java.lang.String id) {
		log.debug("getting CssdSterilizeMaster instance with id: " + id);
		try {
			CssdSterilizeMaster instance = (CssdSterilizeMaster) getSession()
					.get("cn.superion.cssd.entity.CssdSterilizeMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdSterilizeMaster instance) {
		log.debug("finding CssdSterilizeMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdSterilizeMaster").add(
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
		log.debug("finding CssdSterilizeMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdSterilizeMaster as model where model."
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
		log.debug("finding all CssdSterilizeMaster instances");
		try {
			String queryString = "from CssdSterilizeMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdSterilizeMaster merge(CssdSterilizeMaster detachedInstance) {
		log.debug("merging CssdSterilizeMaster instance");
		try {
			CssdSterilizeMaster result = (CssdSterilizeMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdSterilizeMaster instance) {
		log.debug("attaching dirty CssdSterilizeMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdSterilizeMaster instance) {
		log.debug("attaching clean CssdSterilizeMaster instance");
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
	 * @param conditions  包含
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>equipmentCode 灭菌设备</li>
	 *            <li>personId 灭菌人员</li>
	 *            以下为物品包属性
	 *            <li>beginPackageId 起始物品包编码</li>
	 *            <li>endPackageId 结束物品包编码</li>
	 *            <li>packageId 物品包编码</li>
	 *            </ul>

	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		String currentStatus = (String)conditions.get("currentStatus");
		String equipmentCode = (String)conditions.get("equipmentCode");
		String personId = (String)conditions.get("personId");
		String beginPackageId = (String)conditions.get("beginPackageId");
		String endPackageId = (String)conditions.get("endPackageId");
		String packageId = (String)conditions.get("packageId");
		// 主表条件
		StringBuilder shql = new StringBuilder("select autoId from CssdSterilizeMaster p where unitsCode='").append(unitsCode).append("'");
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(beginBillNo != null && !"".equals(beginBillNo)){
			shql.append(" and billNo>=:beginBillNo");
		}
		if(endBillNo != null && !"".equals(endBillNo)){
			shql.append(" and billNo<=:endBillNo");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		if(equipmentCode != null && !"".equals(equipmentCode)){
			shql.append(" and equipmentCode=:equipmentCode");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		// 从表条件
		StringBuilder shql2 = new StringBuilder("from CssdStockMaster s where s.unitsCode=p.unitsCode and s.sterilizeAutoId=p.autoId");
		if (packageId != null && !"".equals(packageId)) {
			flag = true;
			shql2.append(" and s.packageId=:packageId");
		}else{
			if(beginPackageId != null && !"".equals(beginPackageId)){
				flag = true;
				shql2.append(" and s.packageId>=:beginPackageId");
			}
			if(endPackageId != null && !"".equals(endPackageId)){
				flag = true;
				shql2.append(" and s.packageId<=:endPackageId");
			}
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from CssdSterilizeMaster where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}
}