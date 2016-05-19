package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqJobContent;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqJobContent entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobContent
 * @author MyEclipse Persistence Tools
 */

public class EqJobContentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobContentDAO.class);

	public void save(EqJobContent transientInstance) {
		log.debug("saving EqJobContent instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobContent persistentInstance) {
		log.debug("deleting EqJobContent instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqJobContent findById(java.lang.String id) {
		log.debug("getting EqJobContent instance with id: " + id);
		try {
			EqJobContent instance = (EqJobContent) getSession().get(
					"cn.superion.equipment.entity.EqJobContent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobContent> findByExample(EqJobContent instance) {
		log.debug("finding EqJobContent instance by example");
		try {
			List<EqJobContent> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobContent").add(
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
	public List<EqJobContent> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobContent instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqJobContent as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqJobContent merge(EqJobContent detachedInstance) {
		log.debug("merging EqJobContent instance");
		try {
			EqJobContent result = (EqJobContent) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobContent instance) {
		log.debug("attaching dirty EqJobContent instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobContent instance) {
		log.debug("attaching clean EqJobContent instance");
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
	 *            <li>beginJobCode 起始作业编码</li>
	 *            <li>endJobCode 结束作业编码</li>
	 *            <li>jobName 作业名称</li>
	 *            <li>beginJobType 起始作业类型编码</li>
	 *            <li>endJobType 结束作业类型编码</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始对象编码</li>
	 *            <li>endObjectCode 结束对象编码</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginStartDate 起始开始日期</li>
	 *            <li>endStartDate 结束开始日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String beginJobCode = (String)conditions.get("beginJobCode");
		String endJobCode = (String)conditions.get("endJobCode");
		String jobName = (String)conditions.get("jobName");
		String beginJobType = (String)conditions.get("beginJobType");
		String endJobType = (String)conditions.get("endJobType");
		String objectType = (String)conditions.get("objectType");
		String beginObjectCode = (String)conditions.get("beginObjectCode");
		String endObjectCode = (String)conditions.get("endObjectCode");
		Date beginStartDate = (Date)conditions.get("beginStartDate");
		Date endStartDate = (Date)conditions.get("endStartDate");
		String maker = (String) conditions.get("maker");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("select autoId from EqJobContent where unitsCode='").append(unitsCode).append("'");
		if(beginJobCode != null && !"".equals(beginJobCode)){
			shql.append(" and jobCode>=:beginJobCode");
		}
		if(endJobCode != null && !"".equals(endJobCode)){
			shql.append(" and jobCode<=:endJobCode");
		}
		if(jobName != null && !"".equals(jobName)){
			shql.append(" and jobName like :jobName");
			conditions.put("jobName", "%"+jobName+"%");
		}
		if(beginJobType != null && !"".equals(beginJobType)){
			shql.append(" and jobType>=:beginJobType");
		}
		if(endJobType != null && !"".equals(endJobType)){
			shql.append(" and jobType<=:endJobType");
		}
		if(objectType != null && !"".equals(objectType)){
			shql.append(" and objectType=:objectType");
		}
		if(beginObjectCode != null && !"".equals(beginObjectCode)){
			shql.append(" and objectCode>=:beginObjectCode");
		}
		if(endObjectCode != null && !"".equals(endObjectCode)){
			shql.append(" and objectCode<=:endObjectCode");
		}
		if(beginStartDate != null){
			shql.append(" and startDate>=:beginStartDate");
		}
		if(endStartDate != null){
			shql.append(" and startDate<=:endStartDate");
		}
		if(maker !=null && !"".equals(maker)){
			shql.append(" and maker=:maker");
		}
		if(beginMakeDate != null){
			shql.append(" and makeDate>=:beginMakeDate");
		}
		if(endMakeDate != null){
			shql.append(" and makeDate<=:endMakeDate");
		}
		if(verifier !=null && !"".equals(verifier)){
			shql.append(" and verifier=:verifier");
		}
		if(beginVerifyDate != null){
			shql.append(" and verifyDate>=:beginVerifyDate");
		}
		if(endVerifyDate != null){
			shql.append(" and verifyDate<=:endVerifyDate");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkJobCodeUnique(String unitsCode, String jobCode) {
		String hql = "select count(*) from EqJobContent where unitsCode=:unitsCode and jobCode=:jobCode";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("jobCode", jobCode).uniqueResult().toString());
		return cnt == 0;
	}

	public EqJobContent findByJobCode(String unitsCode, String jobCode) {
		String hql = "from EqJobContent where unitsCode=:unitsCode and jobCode=:jobCode";
		return (EqJobContent) getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("jobCode", jobCode).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<EqJobContent> findByEquipmentCode(String unitsCode,
			String equipmentCode) {
		String hql = "from EqJobContent where unitsCode=:unitsCode and objectType='1' and objectCode=:equipmentCode";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("equipmentCode", equipmentCode).list();
	}
}