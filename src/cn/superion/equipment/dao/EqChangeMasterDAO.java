package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqChangeMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqChangeMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqChangeMaster
 * @author MyEclipse Persistence Tools
 */

public class EqChangeMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqChangeMasterDAO.class);

	public void save(EqChangeMaster transientInstance) {
		log.debug("saving EqChangeMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqChangeMaster persistentInstance) {
		log.debug("deleting EqChangeMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqChangeMaster findById(java.lang.String id) {
		log.debug("getting EqChangeMaster instance with id: " + id);
		try {
			EqChangeMaster instance = (EqChangeMaster) getSession().get(
					"cn.superion.equipment.entity.EqChangeMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqChangeMaster> findByExample(EqChangeMaster instance) {
		log.debug("finding EqChangeMaster instance by example");
		try {
			List<EqChangeMaster> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqChangeMaster").add(
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
	public List<EqChangeMaster> findByProperty(String propertyName, Object value) {
		log.debug("finding EqChangeMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqChangeMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqChangeMaster merge(EqChangeMaster detachedInstance) {
		log.debug("merging EqChangeMaster instance");
		try {
			EqChangeMaster result = (EqChangeMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqChangeMaster instance) {
		log.debug("attaching dirty EqChangeMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqChangeMaster instance) {
		log.debug("attaching clean EqChangeMaster instance");
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
	 *            <li>beginBillNo 起始变更单号</li>
	 *            <li>endBillNo 结束变更单号</li>
	 *            <li>beginBillDate 起始变更日期</li>
	 *            <li>endBillDate 结束变更日期</li>
	 *            <li>changeType 变更类型</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            明细记录条件
	 *            <li>beginEquipmentCode 起始设备编码</li>
	 *            <li>endEquipmentCode 结束设备编码</li>
	 *            <li>changeContent 变更内容</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String changeType = (String) conditions.get("changeType");
		String maker = (String) conditions.get("maker");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		StringBuilder shql = new StringBuilder("select autoId from EqChangeMaster m where unitsCode='").append(unitsCode).append("'");
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and billNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and billNo<=:endBillNo");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if (changeType != null && !"".equals(changeType)) {
			shql.append(" and changeType=:changeType");
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
		String beginEquipmentCode = (String) conditions.get("beginEquipmentCode");
		String endEquipmentCode = (String) conditions.get("endEquipmentCode");
		String changeContent = (String) conditions.get("changeContent");
		StringBuilder shql2 = new StringBuilder("from EqChangeDetail d where d.autoId=m.autoId");
		if (beginEquipmentCode != null && !"".equals(beginEquipmentCode)) {
			flag = true;
			shql2.append(" and equipmentCode>=:beginEquipmentCode");
		}
		if (endEquipmentCode != null && !"".equals(endEquipmentCode)) {
			flag = true;
			shql2.append(" and equipmentCode<=:endEquipmentCode");
		}
		if (changeContent != null && !"".equals(changeContent)) {
			flag = true;
			shql2.append(" and changeContent=:changeContent");
		}
		if(flag){
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from EqChangeMaster where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("billNo", billNo)
				.uniqueResult().toString());
		return cnt == 0;
	}
}