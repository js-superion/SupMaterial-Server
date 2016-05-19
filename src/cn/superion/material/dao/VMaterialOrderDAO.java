package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.VMaterialOrder;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialOrder entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.VMaterialOrder
 * @author MyEclipse Persistence Tools
 */

public class VMaterialOrderDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialOrderDAO.class);

	// property constants

	public void save(VMaterialOrder transientInstance) {
		log.debug("saving VMaterialOrder instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VMaterialOrder persistentInstance) {
		log.debug("deleting VMaterialOrder instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VMaterialOrder> findByExample(VMaterialOrder instance) {
		log.debug("finding VMaterialOrder instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.VMaterialOrder").add(
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
	public List<VMaterialOrder> findByProperty(String propertyName, Object value) {
		log.debug("finding VMaterialOrder instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialOrder as model where model."
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
	public List<VMaterialOrder> findAll() {
		log.debug("finding all VMaterialOrder instances");
		try {
			String queryString = "from VMaterialOrder";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VMaterialOrder merge(VMaterialOrder detachedInstance) {
		log.debug("merging VMaterialOrder instance");
		try {
			VMaterialOrder result = (VMaterialOrder) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VMaterialOrder instance) {
		log.debug("attaching dirty VMaterialOrder instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VMaterialOrder instance) {
		log.debug("attaching clean VMaterialOrder instance");
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
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>beginBillDate 起始订单日期</li>
	 *            <li>endBillDate 结束订单日期</li>
	 *            <li>billNo 订单号</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VMaterialOrder> findCheckedByCondition(
			String unitsCode, Map<String, Object> conditions) {
		String materialClass = (String)conditions.get("materialClass");
		String materialCode = (String)conditions.get("materialCode");
		String salerCode = (String)conditions.get("salerCode");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String billNo = (String)conditions.get("billNo");
		StringBuilder shql = new StringBuilder("from VMaterialOrder where currentStatus in ('1','2') and unitsCode='").append(unitsCode).append("'");
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
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginBillNo 开始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>deptCode 部门</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>beginTradePrice 起始进价范围</li>
	 *            <li>endTradePrice 结束进价范围</li>
	 *            <li>salerCode 供应商</li>
	 *            <li>beginPlanArriveDate 起始计划到货日期</li>
	 *            <li>endPlanArriveDate 结束计划到货日期</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>currentStatus 当前状态</li>
	 * 			  </ul>	
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
		String deptCode = (String)conditions.get("deptCode");
		String materialClass = (String)conditions.get("materialClass");
		String materialCode = (String)conditions.get("materialCode");
		String materialName = (String)conditions.get("materialName");
		Double beginTradePrice= conditions.get("beginTradePrice")==null?null:Double.valueOf(conditions.get("beginTradePrice").toString());
		Double endTradePrice= conditions.get("endTradePrice")==null?null:Double.valueOf(conditions.get("endTradePrice").toString());
		String salerCode = (String)conditions.get("salerCode");
		Date beginPlanArriveDate = (Date)conditions.get("beginPlanArriveDate");
		Date endPlanArriveDate = (Date)conditions.get("endPlanArriveDate");
		String factoryCode = (String)conditions.get("factoryCode");
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("from VMaterialOrder where unitsCode='").append(unitsCode).append("'");
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
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(materialClass != null && !"".equals(materialClass)){
			shql.append(" and materialClass=:materialClass");
		}
		if(materialCode != null && !"".equals(materialCode)){
			shql.append(" and materialCode=:materialCode");
		}
		if(materialName != null && !"".equals(materialName)){
			shql.append(" and materialName like :materialName");
			conditions.put("materialName", "%"+materialName+"%");
		}
		if(beginTradePrice != null){
			shql.append(" and tradePrice>=:beginTradePrice");
			conditions.put("beginTradePrice", beginTradePrice);
		}
		if(endTradePrice != null){
			shql.append(" and tradePrice<=:endTradePrice");
			conditions.put("endTradePrice", endTradePrice);
		}
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and salerCode=:salerCode");
		}
		if(beginPlanArriveDate != null){
			shql.append(" and planArriveDate>=:beginPlanArriveDate");
		}
		if(endPlanArriveDate != null){
			shql.append(" and planArriveDate<=:endPlanArriveDate");
		}
		if(factoryCode != null && !"".equals(factoryCode)){
			shql.append(" and factoryCode=:factoryCode");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<VMaterialOrder> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
}