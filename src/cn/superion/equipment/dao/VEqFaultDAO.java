package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.VEqFault;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VEqFault entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.VEqFault
 * @author MyEclipse Persistence Tools
 */

public class VEqFaultDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VEqFaultDAO.class);

	@SuppressWarnings("unchecked")
	public List<VEqFault> findByExample(VEqFault instance) {
		log.debug("finding VEqFault instance by example");
		try {
			List<VEqFault> results = getSession().createCriteria(
					"cn.superion.equipment.entity.VEqFault").add(
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
	public List<VEqFault> findByProperty(String propertyName, Object value) {
		log.debug("finding VEqFault instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from VEqFault as model where model."
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
		StringBuilder shql = new StringBuilder(
				"from VEqFault where unitsCode='").append(unitsCode)
				.append("'");
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
		if (beginEquipmentCode != null && !"".equals(beginEquipmentCode)) {
			shql.append(" and equipmentCode>=:beginEquipmentCode");
		}
		if (endEquipmentCode != null && !"".equals(endEquipmentCode)) {
			shql.append(" and equipmentCode<=:endEquipmentCode");
		}
		if (beginEquipmentType != null && !"".equals(beginEquipmentType)) {
			shql.append(" and equipmentType>=:beginEquipmentType");
		}
		if (endEquipmentType != null && !"".equals(endEquipmentType)) {
			shql.append(" and equipmentType<=:endEquipmentType");
		}
		if (beginEquipmentClass != null && !"".equals(beginEquipmentClass)) {
			shql.append(" and equipmentClass>=:beginEquipmentClass");
		}
		if (endEquipmentClass != null && !"".equals(endEquipmentClass)) {
			shql.append(" and equipmentClass<=:endEquipmentClass");
		}
		if (usedDept != null && !"".equals(usedDept)) {
			shql.append(" and usedDept=:usedDept");
		}
		if (positionCode != null && !"".equals(positionCode)) {
			shql.append(" and positionCode=:positionCode");
		}
		if (workingShift != null && !"".equals(workingShift)) {
			shql.append(" and workingShift=:workingShift");
		}
		if (beginStartDate != null) {
			shql.append(" and startDate>=:beginStartDate");
		}
		if (endStartDate != null) {
			shql.append(" and startDate<=:endStartDate");
		}
		if (beginEndDate != null) {
			shql.append(" and endDate>=:beginEndDate");
		}
		if (endEndDate != null) {
			shql.append(" and endDate<=:endEndDate");
		}
		if (faultType != null && !"".equals(faultType)) {
			shql.append(" and faultType=:faultType");
		}
		if (faultReason != null && !"".equals(faultReason)) {
			shql.append(" and faultReason=:faultReason");
		}
		if (jobStatus != null && !"".equals(jobStatus)) {
			shql.append(" and jobStatus=:jobStatus");
		}
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<VEqFault> list = query.setProperties(conditions).setFirstResult(
				start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 备件需求统计
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>beginRequireFinishDate 起始需求日期</li>
	 *            <li>endRequireFinishDate 结束需求日期</li>
	 *            配件清单明细记录条件
	 *            <li>beginSparePartCode 起始备件编码</li>
	 *            <li>endSparePartCode 结束备件编码</li>
	 *            </ul>
	 * @return  配件编码、配件名称、规格、制造商、数量、金额
	 */
	@SuppressWarnings("unchecked")
	public List<Object> addUpByCondition(String unitsCode,
			Map<String, Object> conditions) {
		Date beginRequireFinishDate = (Date) conditions
				.get("beginRequireFinishDate");
		Date endRequireFinishDate = (Date) conditions
				.get("endRequireFinishDate");
		StringBuilder shql = new StringBuilder(
				"select s.sparePartCode,s.sparePartName,s.partSpec,s.manufacture,sum(s.amount),sum(s.charge) from  EqSparePartsDetail s,VEqFault f where s.equipmentTypeCode=f.equipmentType and f.unitsCode='").append(unitsCode).append(
				"'");
		if (beginRequireFinishDate != null) {
			shql.append(" and f.requireFinishDate>=:beginRequireFinishDate");
		}
		if (endRequireFinishDate != null) {
			shql.append(" and f.requireFinishDate<=:endRequireFinishDate");
		}
		String beginSparePartCode = (String) conditions
				.get("beginSparePartCode");
		String endSparePartCode = (String) conditions.get("endSparePartCode");
		if (beginSparePartCode != null || "".equals(beginSparePartCode)) {
			shql.append(" and s.sparePartCode>=:beginSparePartCode");
		}
		if (endSparePartCode != null || "".equals(endSparePartCode)) {
			shql.append(" and s.sparePartCode<=:endSparePartCode");
		}
		shql.append(" group by s.sparePartCode,s.sparePartName,s.partSpec,s.manufacture");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	
	/**
	 * 备件需求列表
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>beginRequireFinishDate 起始需求日期</li>
	 *            <li>endRequireFinishDate 结束需求日期</li>
	 *            配件清单明细记录条件
	 *            <li>beginSparePartCode 起始备件编码</li>
	 *            <li>endSparePartCode 结束备件编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VEqFault> findByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		Date beginRequireFinishDate = (Date) conditions
				.get("beginRequireFinishDate");
		Date endRequireFinishDate = (Date) conditions
				.get("endRequireFinishDate");
		StringBuilder shql = new StringBuilder(
				"from VEqFault f where unitsCode='").append(unitsCode).append(
				"'");
		if (beginRequireFinishDate != null) {
			shql.append(" and requireFinishDate>=:beginRequireFinishDate");
		}
		if (endRequireFinishDate != null) {
			shql.append(" and requireFinishDate<=:endRequireFinishDate");
		}
		String beginSparePartCode = (String) conditions
				.get("beginSparePartCode");
		String endSparePartCode = (String) conditions.get("endSparePartCode");
		StringBuilder shql2 = new StringBuilder(
				"from EqSparePartsDetail s where s.equipmentTypeCode=f.equipmentType");
		if (beginSparePartCode != null || "".equals(beginSparePartCode)) {
			flag = true;
			shql2.append(" and s.sparePartCode>=:beginSparePartCode");
		}
		if (endSparePartCode != null || "".equals(endSparePartCode)) {
			flag = true;
			shql2.append(" and s.sparePartCode<=:endSparePartCode");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
}