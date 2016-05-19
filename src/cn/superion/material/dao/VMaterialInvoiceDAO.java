package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.VMaterialInvoice;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialInvoice entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.VMaterialInvoice
 * @author MyEclipse Persistence Tools
 */

public class VMaterialInvoiceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialInvoiceDAO.class);

	// property constants

	public void save(VMaterialInvoice transientInstance) {
		log.debug("saving VMaterialInvoice instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VMaterialInvoice persistentInstance) {
		log.debug("deleting VMaterialInvoice instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VMaterialInvoice> findByExample(VMaterialInvoice instance) {
		log.debug("finding VMaterialInvoice instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.VMaterialInvoice").add(
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
	public List<VMaterialInvoice> findByProperty(String propertyName, Object value) {
		log.debug("finding VMaterialInvoice instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialInvoice as model where model."
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
	public List<VMaterialInvoice> findAll() {
		log.debug("finding all VMaterialInvoice instances");
		try {
			String queryString = "from VMaterialInvoice";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VMaterialInvoice merge(VMaterialInvoice detachedInstance) {
		log.debug("merging VMaterialInvoice instance");
		try {
			VMaterialInvoice result = (VMaterialInvoice) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VMaterialInvoice instance) {
		log.debug("attaching dirty VMaterialInvoice instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VMaterialInvoice instance) {
		log.debug("attaching clean VMaterialInvoice instance");
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
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>beginInvoiceNo 起始发票号</li>
	 *            <li>endInvoiceNo 结束发票号</li>
	 *            <li>beginInvoiceDate 起始发票日期</li>
	 *            <li>endInvoiceDate 结束发票日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginTradePrice 起始进价范围</li>
	 *            <li>endTradePrice 结束进价范围</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String operationType = (String)conditions.get("operationType");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		String salerCode = (String)conditions.get("salerCode");
		Date beginInvoiceDate = (Date)conditions.get("beginInvoiceDate");
		Date endInvoiceDate = (Date)conditions.get("endInvoiceDate");
		String beginInvoiceNo = (String)conditions.get("beginInvoiceNo");
		String endInvoiceNo = (String)conditions.get("endInvoiceNo");
		String deptCode = (String)conditions.get("deptCode");
		String personId = (String)conditions.get("personId");
		String materialCode = (String)conditions.get("materialCode");
		Double beginTradePrice= conditions.get("beginTradePrice")==null?null:Double.valueOf(conditions.get("beginTradePrice").toString());
		Double endTradePrice= conditions.get("endTradePrice")==null?null:Double.valueOf(conditions.get("endTradePrice").toString());
		String factoryCode = (String)conditions.get("factoryCode");
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("from VMaterialInvoice where unitsCode='").append(unitsCode).append("'");
		if(operationType != null && !"".equals(operationType)){
			shql.append(" and operationType=:operationType");
		}
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
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and salerCode=:salerCode");
		}
		if(beginInvoiceDate != null){
			shql.append(" and invoiceDate>=:beginInvoiceDate");
		}
		if(endInvoiceDate != null){
			shql.append(" and invoiceDate<=:endInvoiceDate");
		}
		if(beginInvoiceNo != null && !"".equals(beginInvoiceNo)){
			shql.append(" and invoiceNo>=:beginInvoiceNo");
		}
		if(endInvoiceNo != null && !"".equals(endInvoiceNo)){
			shql.append(" and invoiceNo<=:endInvoiceNo");
		}
		
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		if(materialCode != null && !"".equals(materialCode)){
			shql.append(" and materialCode=:materialCode");
		}
		if(beginTradePrice != null){
			shql.append(" and tradePrice>=:beginTradePrice");
			conditions.put("beginTradePrice", beginTradePrice);
		}
		if(endTradePrice != null){
			shql.append(" and tradePrice<=:endTradePrice");
			conditions.put("endTradePrice", endTradePrice);
		}
		if(factoryCode != null && !"".equals(factoryCode)){
			shql.append(" and factoryCode=:factoryCode");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count=((Long)countQuery.setProperties(conditions).uniqueResult()).intValue();
		
		List<VMaterialInvoice> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;

	}
}