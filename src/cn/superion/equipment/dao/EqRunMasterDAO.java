package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqRunMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqRunMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqRunMaster
 * @author MyEclipse Persistence Tools
 */

public class EqRunMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqRunMasterDAO.class);

	public void save(EqRunMaster transientInstance) {
		log.debug("saving EqRunMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqRunMaster persistentInstance) {
		log.debug("deleting EqRunMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqRunMaster findById(java.lang.String id) {
		log.debug("getting EqRunMaster instance with id: " + id);
		try {
			EqRunMaster instance = (EqRunMaster) getSession().get(
					"cn.superion.equipment.entity.EqRunMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqRunMaster> findByExample(EqRunMaster instance) {
		log.debug("finding EqRunMaster instance by example");
		try {
			List<EqRunMaster> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqRunMaster").add(
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
	public List<EqRunMaster> findByProperty(String propertyName, Object value) {
		log.debug("finding EqRunMaster instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EqRunMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqRunMaster merge(EqRunMaster detachedInstance) {
		log.debug("merging EqRunMaster instance");
		try {
			EqRunMaster result = (EqRunMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqRunMaster instance) {
		log.debug("attaching dirty EqRunMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqRunMaster instance) {
		log.debug("attaching clean EqRunMaster instance");
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
	 *            <li>beginBillNo 起始运行记录单号</li>
	 *            <li>endBillNo 结束运行记录单号</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            明细记录条件
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始位置/设备编码</li>
	 *            <li>endObjectCode 结束位置/设备编码</li>
	 *            <li>beginStartDate 起始开始日期</li>
	 *            <li>endStartDate 结束开始日期</li>
	 *            <li>beginEndDate 起始终止日期</li>
	 *            <li>endEndDate 结束终止日期</li>
	 *            <li>runStatus 运行状态</li>
	 *            设备条件
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean detailCondFlag = false, eqCondFlag = false;
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		String maker = (String) conditions.get("maker");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		StringBuilder shql = new StringBuilder(
				"select autoId from EqRunMaster m where unitsCode='").append(
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
		// 明细记录条件
		String objectType = (String) conditions.get("objectType");
		String beginObjectCode = (String) conditions.get("beginObjectCode");
		String endObjectCode = (String) conditions.get("endObjectCode");
		Date beginStartDate = (Date) conditions.get("beginStartDate");
		Date endStartDate = (Date) conditions.get("endStartDate");
		Date beginEndDate = (Date) conditions.get("beginEndDate");
		Date endEndDate = (Date) conditions.get("endEndDate");
		String runStatus = (String) conditions.get("runStatus");
		StringBuilder shqlDetail = new StringBuilder(
				"from EqRunDetail d where d.autoId=m.autoId");
		if (objectType != null && !"".equals(objectType)) {
			detailCondFlag = true;
			shqlDetail.append(" and objectType=:objectType");
		}
		if (beginObjectCode != null && !"".equals(beginObjectCode)) {
			detailCondFlag = true;
			shqlDetail.append(" and objectCode>=:beginObjectCode");
		}
		if (endObjectCode != null && !"".equals(endObjectCode)) {
			detailCondFlag = true;
			shqlDetail.append(" and objectCode<=:endObjectCode");
		}
		if (beginStartDate != null) {
			detailCondFlag = true;
			shqlDetail.append(" and startDate>=:beginStartDate");
		}
		if (endStartDate != null) {
			detailCondFlag = true;
			shqlDetail.append(" and startDate<=:endStartDate");
		}
		if (beginEndDate != null) {
			detailCondFlag = true;
			shqlDetail.append(" and endDate>=:beginEndDate");
		}
		if (endEndDate != null) {
			detailCondFlag = true;
			shqlDetail.append(" and endDate<=:endEndDate");
		}
		if (runStatus != null && !"".equals(runStatus)) {
			detailCondFlag = true;
			shqlDetail.append(" and runStatus=:runStatus");
		}
		// 设备条件
		String beginEquipmentType = (String) conditions
				.get("beginEquipmentType");
		String endEquipmentType = (String) conditions.get("endEquipmentType");
		String beginEquipmentClass = (String) conditions
				.get("beginEquipmentClass");
		String endEquipmentClass = (String) conditions.get("endEquipmentClass");
		StringBuilder shqlEq = new StringBuilder("from EqEquipment e where e.unitsCode='").append(unitsCode).append("' and e.equipmentCode=d.objectCode");
		if("1".equals(objectType)){
			//设备
			if(beginEquipmentType !=null && !"".equals(beginEquipmentType)){
				eqCondFlag = true;
				shqlEq.append(" and equipmentType>=:beginEquipmentType");
			}
			if(endEquipmentType !=null && !"".equals(endEquipmentType)){
				eqCondFlag = true;
				shqlEq.append(" and equipmentType<=:endEquipmentType");
			}
			if(beginEquipmentClass !=null && !"".equals(beginEquipmentClass)){
				eqCondFlag = true;
				shqlEq.append(" and equipmentClass>=:beginEquipmentClass");
			}
			if(endEquipmentClass !=null && !"".equals(endEquipmentClass)){
				eqCondFlag = true;
				shqlEq.append(" and equipmentClass<=:endEquipmentClass");
			}
			if(eqCondFlag){
				shqlDetail.append(" and exists(").append(shqlEq).append(")");
			}
		}
		if(detailCondFlag){
			shql.append(" and exists(").append(shqlDetail).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from EqRunMaster where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("billNo", billNo)
				.uniqueResult().toString());
		return cnt == 0;
	}
}