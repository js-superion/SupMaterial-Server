package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.VMaterialApply;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialApply entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.VMaterialApply
 * @author MyEclipse Persistence Tools
 */

public class VMaterialApplyDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialApplyDAO.class);

	@SuppressWarnings("unchecked")
	public List<VMaterialApply> findByExample(VMaterialApply instance) {
		log.debug("finding VMaterialApply instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.VMaterialApply").add(
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
	public List<VMaterialApply> findByProperty(String propertyName, Object value) {
		log.debug("finding VMaterialApply instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialApply as model where model."
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
	public List<VMaterialApply> findAll() {
		log.debug("finding all VMaterialApply instances");
		try {
			String queryString = "from VMaterialApply";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>beginBillDate 起始申请日期</li>
	 *            <li>endBillDate 结束申请日期</li>
	 *            <li>billNo 申请单号</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VMaterialApply> findByCondition(
			String unitsCode, Map<String, Object> conditions) {
		String materialClass = (String)conditions.get("materialClass");
		String materialCode = (String)conditions.get("materialCode");
		String salerCode = (String)conditions.get("salerCode");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String billNo = (String)conditions.get("billNo");
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("from VMaterialApply where unitsCode='").append(unitsCode).append("'");
		if(materialClass != null && !"".equals(materialClass)){
			shql.append(" and materialClass=:materialClass");
		}
		if(materialCode != null && !"".equals(materialCode)){
			shql.append(" and materialCode=:materialCode");
		}
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and salerCode=:salerCode");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(billNo != null && !"".equals(billNo)){
			shql.append(" and billNo=:billNo");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}
}