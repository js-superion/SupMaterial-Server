package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.VMaterialPlan;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialPlan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.VMaterialPlan
 * @author MyEclipse Persistence Tools
 */

public class VMaterialPlanDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialPlanDAO.class);

	// property constants

	public void save(VMaterialPlan transientInstance) {
		log.debug("saving VMaterialPlan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VMaterialPlan persistentInstance) {
		log.debug("deleting VMaterialPlan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VMaterialPlan> findByExample(VMaterialPlan instance) {
		log.debug("finding VMaterialPlan instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.VMaterialPlan").add(
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
	public List<VMaterialPlan> findByProperty(String propertyName, Object value) {
		log.debug("finding VMaterialPlan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialPlan as model where model."
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
	public List<VMaterialPlan> findAll() {
		log.debug("finding all VMaterialPlan instances");
		try {
			String queryString = "from VMaterialPlan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VMaterialPlan merge(VMaterialPlan detachedInstance) {
		log.debug("merging VMaterialPlan instance");
		try {
			VMaterialPlan result = (VMaterialPlan) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VMaterialPlan instance) {
		log.debug("attaching dirty VMaterialPlan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VMaterialPlan instance) {
		log.debug("attaching clean VMaterialPlan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 查询审核或执行的采购计划
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <ul>dataSource 数据来源</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>salerCode 供应商</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginRequireDate 起始需求日期</li>
	 *            <li>endRequireDate 结束需求日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VMaterialPlan> findByCheckCondition(
			String unitsCode, Map<String, Object> conditions) {
		String dataSource = (String)conditions.get("dataSource");
		String materialClass = (String)conditions.get("materialClass");
		String beginMaterialCode = (String)conditions.get("beginMaterialCode");
		String endMaterialCode = (String)conditions.get("endMaterialCode");
		String salerCode = (String)conditions.get("salerCode");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		Date beginRequireDate = (Date)conditions.get("beginRequireDate");
		Date endRequireDate = (Date)conditions.get("endRequireDate");
		String deptCode = (String)conditions.get("deptCode");
		String personId = (String)conditions.get("personId");
		StringBuilder shql = new StringBuilder("from VMaterialPlan where currentStatus in ('1','2') and unitsCode='").append(unitsCode).append("'");
		if(dataSource != null && !"".equals(dataSource)){
			shql.append(" and dataSource=:dataSource");
		}
		if(materialClass != null && !"".equals(materialClass)){
			shql.append(" and materialClass=:materialClass");
		}
		if(beginMaterialCode != null && !"".equals(beginMaterialCode)){
			shql.append(" and materialCode>=:beginMaterialCode");
		}
		if(endMaterialCode != null && !"".equals(endMaterialCode)){
			shql.append(" and materialCode<=:endMaterialCode");
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
		if(beginRequireDate != null){
			shql.append(" and requireDate>=:beginRequireDate");
		}
		if(endRequireDate != null){
			shql.append(" and requireDate<=:endRequireDate");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	/**
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>dataSource 数据来源</li>
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
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start,
			int limit,String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String dataSource = (String)conditions.get("dataSource");
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
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("from VMaterialPlan where unitsCode='").append(unitsCode).append("'");
		if(dataSource != null && !"".equals(dataSource)){
			shql.append(" and dataSource=:dataSource");
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
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<VMaterialPlan> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
}