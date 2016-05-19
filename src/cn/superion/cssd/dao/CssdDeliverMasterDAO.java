package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdDeliverMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdDeliverMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdDeliverMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdDeliverMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdDeliverMasterDAO.class);

	public void save(CssdDeliverMaster transientInstance) {
		log.debug("saving CssdDeliverMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdDeliverMaster persistentInstance) {
		log.debug("deleting CssdDeliverMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdDeliverMaster findById(java.lang.String id) {
		log.debug("getting CssdDeliverMaster instance with id: " + id);
		try {
			CssdDeliverMaster instance = (CssdDeliverMaster) getSession().get(
					"cn.superion.cssd.entity.CssdDeliverMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdDeliverMaster instance) {
		log.debug("finding CssdDeliverMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdDeliverMaster").add(
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
		log.debug("finding CssdDeliverMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdDeliverMaster as model where model."
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
		log.debug("finding all CssdDeliverMaster instances");
		try {
			String queryString = "from CssdDeliverMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdDeliverMaster merge(CssdDeliverMaster detachedInstance) {
		log.debug("merging CssdDeliverMaster instance");
		try {
			CssdDeliverMaster result = (CssdDeliverMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdDeliverMaster instance) {
		log.debug("attaching dirty CssdDeliverMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdDeliverMaster instance) {
		log.debug("attaching clean CssdDeliverMaster instance");
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
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含
	 *            <ul>
	 *            <li>deptCode 回收科室</li>
	 *            以下为从表条件
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>overdueNum 过期天数</li>
	 *            <li>anearNum 临近天数</li>
	 *            <li>usedSign 当前状态（使用标志）</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		boolean flag = false;
		String deptCode = (String)conditions.get("deptCode");
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		Integer overdueNum = (Integer) conditions.get("overdueNum");
		Integer anearNum = (Integer) conditions.get("anearNum");
		String usedSign = (String)conditions.get("usedSign");
		StringBuilder shql = new StringBuilder("from CssdDeliverMaster p where unitsCode='").append(unitsCode).append("'");
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		// 从表条件CURRENT_STATUS
		StringBuilder shql2 = new StringBuilder("from CssdStockMaster s where s.unitsCode=p.unitsCode and s.deliverAutoId=p.autoId and s.currentStatus='2'");
		if (overdueNum != null) {
			flag = true;
			shql2
					.append(" and s.availDate>=trunc(sysdate)-").append(overdueNum).append(" and s.availDate<trunc(sysdate)+1-").append(overdueNum);
		} else if (anearNum != null) {
			flag = true;
			shql2
					.append(" and s.availDate>=trunc(sysdate)+").append(anearNum).append(" and s.availDate<trunc(sysdate)+1+").append(anearNum);
		} else {
			if (beginAvailDate != null) {
				flag = true;
				shql2.append(" and s.availDate>=:beginAvailDate");
			}
			if (endAvailDate != null) {
				flag = true;
				shql2.append(" and s.availDate<=:endAvailDate");
			}
		}
		if(usedSign != null && !"".equals(usedSign)){
			flag = true;
			shql2.append(" and s.usedSign=:usedSign");
		}

			shql.append(" and exists(").append(shql2).append(")");
		
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<CssdDeliverMaster> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含
	 *            <ul>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>deptCode 申请科室</li>
	 *            <li>personId 申请人</li>
	 *            <li>deliverPerson 送货人</li>
	 *            以下为物品包属性
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
		String deptCode = (String)conditions.get("deptCode");
		String personId = (String)conditions.get("personId");
		String deliverPerson = (String)conditions.get("deliverPerson");
		String packageId = (String)conditions.get("packageId");
		// 主表条件
		StringBuilder shql = new StringBuilder("select autoId from CssdDeliverMaster p where unitsCode='").append(unitsCode).append("'");
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
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		if(deliverPerson != null && !"".equals(deliverPerson)){
			shql.append(" and deliverPerson=:deliverPerson");
		}
		// 从表条件
		StringBuilder shql2 = new StringBuilder("from CssdStockMaster s where s.unitsCode=p.unitsCode and s.deliverAutoId=p.autoId");
		if (packageId != null && !"".equals(packageId)) {
			flag = true;
			shql2.append(" and s.packageId=:packageId");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from CssdDeliverMaster where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}
}