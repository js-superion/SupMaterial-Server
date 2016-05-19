package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqMeasureMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqMeasureMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqMeasureMaster
 * @author MyEclipse Persistence Tools
 */

public class EqMeasureMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqMeasureMasterDAO.class);

	public void save(EqMeasureMaster transientInstance) {
		log.debug("saving EqMeasureMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqMeasureMaster persistentInstance) {
		log.debug("deleting EqMeasureMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqMeasureMaster findById(java.lang.String id) {
		log.debug("getting EqMeasureMaster instance with id: " + id);
		try {
			EqMeasureMaster instance = (EqMeasureMaster) getSession().get(
					"cn.superion.equipment.entity.EqMeasureMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqMeasureMaster> findByExample(EqMeasureMaster instance) {
		log.debug("finding EqMeasureMaster instance by example");
		try {
			List<EqMeasureMaster> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqMeasureMaster").add(
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
	public List<EqMeasureMaster> findByProperty(String propertyName,
			Object value) {
		log.debug("finding EqMeasureMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqMeasureMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqMeasureMaster merge(EqMeasureMaster detachedInstance) {
		log.debug("merging EqMeasureMaster instance");
		try {
			EqMeasureMaster result = (EqMeasureMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqMeasureMaster instance) {
		log.debug("attaching dirty EqMeasureMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqMeasureMaster instance) {
		log.debug("attaching clean EqMeasureMaster instance");
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
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始测量点记录单号</li>
	 *            <li>endBillNo 结束测量点记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            明细记录条件
	 *            <li>beginPointCode 起始测量点编码</li>
	 *            <li>endPointCode 结束测量点编码</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始位置/设备编码</li>
	 *            <li>endObjectCode 结束位置/设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>beginStandard 起始标准值</li>
	 *            <li>endStandard 结束标准值</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		String maker = (String) conditions.get("maker");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		StringBuilder shql = new StringBuilder("select autoId from EqMeasureMaster m where unitsCode='").append(unitsCode).append("'");
		if(beginBillNo != null && !"".equals(beginBillNo)){
			shql.append(" and billNo>=:beginBillNo");
		}
		if(endBillNo != null && !"".equals(endBillNo)){
			shql.append(" and billNo<=:endBillNo");
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
		String beginPointCode = (String) conditions.get("beginPointCode");
		String endPointCode = (String) conditions.get("endPointCode");
		String objectType = (String) conditions.get("objectType");
		String beginObjectCode = (String) conditions.get("beginObjectCode");
		String endObjectCode = (String) conditions.get("endObjectCode");
		String beginEquipmentType = (String) conditions
				.get("beginEquipmentType");
		String endEquipmentType = (String) conditions.get("endEquipmentType");
		String beginEquipmentClass = (String) conditions
				.get("beginEquipmentClass");
		String endEquipmentClass = (String) conditions.get("endEquipmentClass");
		Object beginStandard = conditions.get("beginStandard");
		Object endStandard = conditions.get("endStandard");
		StringBuilder shql2 = new StringBuilder("from EqMeasureDetail d where d.autoId=m.autoId");
		if(beginPointCode != null && !"".equals(beginPointCode)){
			flag = true;
			shql2.append(" and pointCode>=:beginPointCode");
		}
		if(endPointCode != null && !"".equals(endPointCode)){
			flag = true;
			shql2.append(" and pointCode<=:endPointCode");
		}
		if(objectType !=null && !"".equals(objectType)){
			flag = true;
			shql2.append(" and objectType=:objectType");
		}
		if(beginObjectCode !=null && !"".equals(beginObjectCode)){
			flag = true;
			shql2.append(" and objectCode>=:beginObjectCode");
		}
		if(endObjectCode !=null && !"".equals(endObjectCode)){
			flag = true;
			shql2.append(" and objectCode<=:endObjectCode");
		}
		if("1".equals(objectType)){
			//设备
			if(beginEquipmentType !=null && !"".equals(beginEquipmentType)){
				flag = true;
				shql2.append(" and equipmentType>=:beginEquipmentType");
			}
			if(endEquipmentType !=null && !"".equals(endEquipmentType)){
				flag = true;
				shql2.append(" and equipmentType<=:endEquipmentType");
			}
			if(beginEquipmentClass !=null && !"".equals(beginEquipmentClass)){
				flag = true;
				shql2.append(" and equipmentClass>=:beginEquipmentClass");
			}
			if(endEquipmentClass !=null && !"".equals(endEquipmentClass)){
				flag = true;
				shql2.append(" and equipmentClass<=:endEquipmentClass");
			}	
		}
		if(beginStandard != null){
			flag = true;
			shql2.append(" and standard>=:beginStandard");
			conditions.put("beginStandard", Double.valueOf(beginStandard.toString()));
		}
		if(endStandard != null){
			flag = true;
			shql2.append(" and standard<=:endStandard");
			conditions.put("endStandard", Double.valueOf(endStandard.toString()));
		}
		if(flag){
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from EqMeasureMaster where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("billNo", billNo)
				.uniqueResult().toString());
		return cnt == 0;
	}
}