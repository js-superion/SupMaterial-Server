package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.VEqMeasure;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VEqMeasure entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.VEqMeasure
 * @author MyEclipse Persistence Tools
 */

public class VEqMeasureDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VEqMeasureDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<VEqMeasure> findByExample(VEqMeasure instance) {
		log.debug("finding VEqMeasure instance by example");
		try {
			List<VEqMeasure> results = getSession().createCriteria(
					"cn.superion.equipment.entity.VEqMeasure").add(
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
	public List<VEqMeasure> findByProperty(String propertyName, Object value) {
		log.debug("finding VEqMeasure instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from VEqMeasure as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
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
	 *            <li>beginBillNo 起始测量点记录单号</li>
	 *            <li>endBillNo 结束测量点记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
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
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		String maker = (String) conditions.get("maker");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
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
		StringBuilder shql = new StringBuilder("from VEqMeasure where unitsCode='").append(unitsCode).append("'");
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
		if(beginPointCode != null && !"".equals(beginPointCode)){
			shql.append(" and pointCode>=:beginPointCode");
		}
		if(endPointCode != null && !"".equals(endPointCode)){
			shql.append(" and pointCode<=:endPointCode");
		}
		if(objectType !=null && !"".equals(objectType)){
			shql.append(" and objectType=:objectType");
		}
		if(beginObjectCode !=null && !"".equals(beginObjectCode)){
			shql.append(" and objectCode>=:beginObjectCode");
		}
		if(endObjectCode !=null && !"".equals(endObjectCode)){
			shql.append(" and objectCode<=:endObjectCode");
		}
		if("1".equals(objectType)){
			//设备
			if(beginEquipmentType !=null && !"".equals(beginEquipmentType)){
				shql.append(" and equipmentType>=:beginEquipmentType");
			}
			if(endEquipmentType !=null && !"".equals(endEquipmentType)){	
				shql.append(" and equipmentType<=:endEquipmentType");
			}
			if(beginEquipmentClass !=null && !"".equals(beginEquipmentClass)){
				shql.append(" and equipmentClass>=:beginEquipmentClass");
			}
			if(endEquipmentClass !=null && !"".equals(endEquipmentClass)){
				shql.append(" and equipmentClass<=:endEquipmentClass");
			}	
		}
		if(beginStandard != null){
			shql.append(" and standard>=:beginStandard");
			conditions.put("beginStandard", Double.valueOf(beginStandard.toString()));
		}
		if(endStandard != null){
			shql.append(" and standard<=:endStandard");
			conditions.put("endStandard", Double.valueOf(endStandard.toString()));
		}
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<VEqMeasure> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
}