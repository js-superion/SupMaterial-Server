package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdProvideMaster;
import cn.superion.material.entity.MaterialProvideMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdProvideMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdProvideMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdProvideMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdProvideMasterDAO.class);
	// property constants
	public void save(CssdProvideMaster transientInstance) {
		log.debug("saving CssdProvideMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdProvideMaster persistentInstance) {
		log.debug("deleting CssdProvideMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdProvideMaster findById(java.lang.String id) {
		log.debug("getting CssdProvideMaster instance with id: " + id);
		try {
			CssdProvideMaster instance = (CssdProvideMaster) getSession().get(
					"cn.superion.cssd.entity.CssdProvideMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdProvideMaster instance) {
		log.debug("finding CssdProvideMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdProvideMaster").add(
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
	public List<CssdProvideMaster> findByProperty(String propertyName, Object value,String unitsCode) {
		log.debug("finding CssdProvideMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdProvideMaster as model where model."
					+ propertyName + "= ? and model.unitsCode = '"+unitsCode+"'";
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
		log.debug("finding all CssdProvideMaster instances");
		try {
			String queryString = "from CssdProvideMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdProvideMaster merge(CssdProvideMaster detachedInstance) {
		log.debug("merging CssdProvideMaster instance");
		try {
			CssdProvideMaster result = (CssdProvideMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdProvideMaster instance) {
		log.debug("attaching dirty CssdProvideMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdProvideMaster instance) {
		log.debug("attaching clean CssdProvideMaster instance");
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
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>deptCode 申请科室编码</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始申请日期</li>
	 *            <li>endBillDate 结束申请日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            以下为从表条件
	 *            <li>packageClass 物品包类别</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String deptCode = (String)conditions.get("deptCode");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String currentStatus = (String)conditions.get("currentStatus");
		String packageClass = (String)conditions.get("packageClass");
		StringBuilder shql = new StringBuilder("select autoId from CssdProvideMaster m where unitsCode='").append(unitsCode).append("'");
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(beginBillNo != null && !"".equals(beginBillNo)){
			shql.append(" and billNo>=:beginBillNo");
		}
		if(endBillNo != null && !"".equals(endBillNo)){
			shql.append(" and billNo<=:endBillNo");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		//从表条件
		StringBuilder shql2 = new StringBuilder("from CssdProvideDetail d where d.mainAutoId=m.autoId");
		if(packageClass != null && !"".equals(packageClass)){
			flag = true;
			shql2.append(" and packageClass=:packageClass");
		}
		if(flag)
			shql.append(" and exists(").append(shql2).append(")");
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}
	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 当前仓库</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据编号</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>billNo 领用单号</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String deptCode = (String)conditions.get("deptCode");
		String deptUnitsCode = (String)conditions.get("unitsCode");
		StringBuilder shql = new StringBuilder("from CssdProvideMaster where currentStatus='1' and unitsCode='").append(deptUnitsCode).append("'");
		if(beginBillNo != null && !"".equals(beginBillNo)){
			shql.append(" and billNo>=:beginBillNo");
		}
		if(endBillNo != null && !"".equals(endBillNo)){
			shql.append(" and billNo<=:endBillNo");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		int count = Integer.parseInt(getSession().createQuery("select count(*) "+shql.toString()).setProperties(conditions).uniqueResult().toString());
		List<MaterialProvideMaster> list = getSession().createQuery(shql.toString()).setProperties(conditions).list();//.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
}