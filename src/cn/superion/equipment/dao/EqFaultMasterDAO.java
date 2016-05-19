package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqFaultMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqFaultMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqFaultMaster
 * @author MyEclipse Persistence Tools
 */

public class EqFaultMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqFaultMasterDAO.class);

	public void save(EqFaultMaster transientInstance) {
		log.debug("saving EqFaultMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqFaultMaster persistentInstance) {
		log.debug("deleting EqFaultMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqFaultMaster findById(java.lang.String id) {
		log.debug("getting EqFaultMaster instance with id: " + id);
		try {
			EqFaultMaster instance = (EqFaultMaster) getSession().get(
					"cn.superion.equipment.entity.EqFaultMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqFaultMaster> findByExample(EqFaultMaster instance) {
		log.debug("finding EqFaultMaster instance by example");
		try {
			List<EqFaultMaster> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqFaultMaster").add(
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
	public List<EqFaultMaster> findByProperty(String propertyName, Object value) {
		log.debug("finding EqFaultMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqFaultMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqFaultMaster merge(EqFaultMaster detachedInstance) {
		log.debug("merging EqFaultMaster instance");
		try {
			EqFaultMaster result = (EqFaultMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqFaultMaster instance) {
		log.debug("attaching dirty EqFaultMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqFaultMaster instance) {
		log.debug("attaching clean EqFaultMaster instance");
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
	 *            <li>beginBillNo 起始故障记录单号</li>
	 *            <li>endBillNo 结束故障记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            明细记录条件
	 *            <li>beginEquipmentCode 起始设备编码</li>
	 *            <li>endEquipmentCode 结束设备编码</li>
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>positionCode 位置</li>
	 *            <li>workingShift 班次</li>
	 *            <li>beginStartDate 起始故障开始日期</li>
	 *            <li>endStartDate 结束故障开始日期</li>
	 *            <li>beginEndDate 起始故障结束日期</li>
	 *            <li>endEndDate 结束故障结束日期</li>
	 *            <li>faultType 故障类型</li>
	 *            <li>faultReason 故障原因</li>
	 *            <li>jobStatus 作业完成情况</li>
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
		StringBuilder shql = new StringBuilder(
				"select autoId from EqFaultMaster m where unitsCode='").append(
				unitsCode).append("'");
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and billNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and billNo<=:endBillNo");
		}
		if (maker != null && !"".equals(maker)) {
			shql.append(" and maker=:maker");
		}
		if (beginMakeDate != null) {
			shql.append(" and makeDate>=:beginMakeDate");
		}
		if (endMakeDate != null) {
			shql.append(" and makeDate<=:endMakeDate");
		}
		if (verifier != null && !"".equals(verifier)) {
			shql.append(" and verifier=:verifier");
		}
		if (beginVerifyDate != null) {
			shql.append(" and verifyDate>=:beginVerifyDate");
		}
		if (endVerifyDate != null) {
			shql.append(" and verifyDate<=:endVerifyDate");
		}
		String beginEquipmentCode = (String) conditions
				.get("beginEquipmentCode");
		String endEquipmentCode = (String) conditions.get("endEquipmentCode");
		String beginEquipmentType = (String) conditions
				.get("beginEquipmentType");
		String endEquipmentType = (String) conditions.get("endEquipmentType");
		String beginEquipmentClass = (String) conditions
				.get("beginEquipmentClass");
		String endEquipmentClass = (String) conditions.get("endEquipmentClass");
		String usedDept = (String) conditions.get("usedDept");
		String positionCode = (String) conditions.get("positionCode");
		String workingShift = (String) conditions.get("workingShift");
		Date beginStartDate = (Date) conditions.get("beginStartDate");
		Date endStartDate = (Date) conditions.get("endStartDate");
		Date beginEndDate = (Date) conditions.get("beginEndDate");
		Date endEndDate = (Date) conditions.get("endEndDate");
		String faultType = (String) conditions.get("faultType");
		String faultReason = (String) conditions.get("faultReason");
		String jobStatus = (String) conditions.get("jobStatus");
		StringBuilder shql2 = new StringBuilder(
				"from EqMeasureDetail d where d.autoId=m.autoId");

		if (beginEquipmentCode != null && !"".equals(beginEquipmentCode)) {
			flag = true;
			shql2.append(" and equipmentCode>=:beginEquipmentCode");
		}
		if (endEquipmentCode != null && !"".equals(endEquipmentCode)) {
			flag = true;
			shql2.append(" and equipmentCode<=:endEquipmentCode");
		}
		if (beginEquipmentType != null && !"".equals(beginEquipmentType)) {
			flag = true;
			shql2.append(" and equipmentType>=:beginEquipmentType");
		}
		if (endEquipmentType != null && !"".equals(endEquipmentType)) {
			flag = true;
			shql2.append(" and equipmentType<=:endEquipmentType");
		}
		if (beginEquipmentClass != null && !"".equals(beginEquipmentClass)) {
			flag = true;
			shql2.append(" and equipmentClass>=:beginEquipmentClass");
		}
		if (endEquipmentClass != null && !"".equals(endEquipmentClass)) {
			flag = true;
			shql2.append(" and equipmentClass<=:endEquipmentClass");
		}
		if (usedDept != null && !"".equals(usedDept)) {
			flag = true;
			shql2.append(" and usedDept=:usedDept");
		}
		if (positionCode != null && !"".equals(positionCode)) {
			flag = true;
			shql2.append(" and positionCode=:positionCode");
		}
		if (workingShift != null && !"".equals(workingShift)) {
			flag = true;
			shql2.append(" and workingShift=:workingShift");
		}
		if(beginStartDate != null){
			flag = true;
			shql2.append(" and startDate>=:beginStartDate");
		}
		if(endStartDate != null){
			flag = true;
			shql2.append(" and startDate<=:endStartDate");
		}
		if(beginEndDate != null){
			flag = true;
			shql2.append(" and endDate>=:beginEndDate");
		}
		if(endEndDate != null){
			flag = true;
			shql2.append(" and endDate<=:endEndDate");
		}
		if (faultType != null && !"".equals(faultType)) {
			flag = true;
			shql2.append(" and faultType=:faultType");
		}
		if (faultReason != null && !"".equals(faultReason)) {
			flag = true;
			shql2.append(" and faultReason=:faultReason");
		}
		if (jobStatus != null && !"".equals(jobStatus)) {
			flag = true;
			shql2.append(" and jobStatus=:jobStatus");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from EqFaultMaster where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("billNo", billNo)
				.uniqueResult().toString());
		return cnt == 0;
	}
}