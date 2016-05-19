package cn.superion.materialDept.dao;

import cn.superion.base.ParameterObject;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.materialDept.entity.MaterialPatsMaster;
import cn.superion.util.BaseHibernateDAO;
import cn.superion.util.SessionUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialPatsMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.materialDept.entity.MaterialPatsMaster
 * @author MyEclipse Persistence Tools
 */

public class MaterialPatsMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialPatsMasterDAO.class);

	public void save(MaterialPatsMaster transientInstance) {
		log.debug("saving MaterialPatsMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialPatsMaster persistentInstance) {
		log.debug("deleting MaterialPatsMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialPatsMaster findById(java.lang.String id) {
		log.debug("getting MaterialPatsMaster instance with id: " + id);
		try {
			MaterialPatsMaster instance = (MaterialPatsMaster) getSession()
					.get("cn.superion.materialDept.entity.MaterialPatsMaster",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialPatsMaster> findByExample(MaterialPatsMaster instance) {
		log.debug("finding MaterialPatsMaster instance by example");
		try {
			List<MaterialPatsMaster> results = getSession().createCriteria(
					"cn.superion.materialDept.entity.MaterialPatsMaster").add(
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
	public List<MaterialPatsMaster> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialPatsMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialPatsMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public MaterialPatsMaster merge(MaterialPatsMaster detachedInstance) {
		log.debug("merging MaterialPatsMaster instance");
		try {
			MaterialPatsMaster result = (MaterialPatsMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialPatsMaster instance) {
		log.debug("attaching dirty MaterialPatsMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialPatsMaster instance) {
		log.debug("attaching clean MaterialPatsMaster instance");
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
	 * @param billType
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 科室编码</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginApplyDate 起始申请日期</li>
	 *            <li>endApplyDate 结束申请日期</li>
	 *            <li>beginMakeDate 起始单据日期</li>
	 *            <li>endMakeDate 结束单据日期</li>
	 *            <li>patientId 病人标识号</li>
	 *            <li>personName 病人姓名</li>
	 *            <li>deptCode 所在科室</li>
	 *            <li>wardCode 所在病区</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,String billType,
			Map<String, Object> conditions) {
		boolean flag = false;
		String storageCode = (String) conditions.get("storageCode");
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		Date beginApplyDate = (Date) conditions.get("beginApplyDate");
		Date endApplyDate = (Date) conditions.get("endApplyDate");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String patientId = (String) conditions.get("patientId");
		String inpNo = (String) conditions.get("inpNo");
		String personName = (String) conditions.get("personName");
		String deptCode = (String) conditions.get("deptCode");
		String wardCode = (String) conditions.get("wardCode");
		String currentStatus = (String) conditions.get("currentStatus");
		String refundSign = (String) conditions.get("refundSign");
		String materialCode = (String) conditions.get("materialCode");
		String materialName = (String) conditions.get("materialName");
		// 主表条件
		StringBuilder shql = new StringBuilder(
				"select autoId from MaterialPatsMaster m where unitsCode='")
				.append(unitsCode).append("' and billType='").append(billType).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and billNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and billNo<=:endBillNo");
		}
		if (beginApplyDate != null) {
			shql.append(" and applyDate>=:beginApplyDate");
		}
		if (endApplyDate != null) {
			shql.append(" and applyDate<=:endApplyDate");
		}
		if (beginMakeDate != null) {
			shql.append(" and makeDate>=:beginMakeDate");
		}
		if (endMakeDate != null) {
			shql.append(" and makeDate<=:endMakeDate");
		}
		if (patientId != null && !"".equals(patientId)) {
			shql.append(" and patientId=:patientId");
		}
		if (inpNo != null && !"".equals(inpNo)) {
			shql.append(" and inpNo=:inpNo");
		}
		if (personName != null && !"".equals(personName)) {
			shql.append(" and personName like :personName");
			conditions.put("personName", "%" + personName + "%");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (wardCode != null && !"".equals(wardCode)) {
			shql.append(" and wardCode=:wardCode");
		}
		if (currentStatus != null && !"".equals(currentStatus)) {
			shql.append(" and currentStatus=:currentStatus");
		}
		// 从表条件
		StringBuilder shql2 = new StringBuilder(
				"from MaterialPatsDetail d where d.mainAutoId=m.autoId");
		if (materialCode != null && !"".equals(materialCode)) {
			flag = true;
			shql2.append(" and d.materialCode=:materialCode");
		}
		if (materialName != null && !"".equals(materialName)) {
			flag = true;
			shql2.append(" and materialName like :materialName");
			conditions.put("materialName", "%" + materialName + "%");
		}
		if ("0".equals(refundSign)) {
			flag = true;
			shql2.append(" and d.refundSign =:refundSign");
		}
		else{
			flag = true;
			shql2.append(" and (d.refundSign ='1' or d.refundSign is null)");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		Query query = getSession().createQuery(shql.toString());
		return query.setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String storageCode,
			String billNo) {
		String hql = "select count(*) from MaterialPatsMaster where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>wardCode 所在病区</li>
	 *            <li>inpNo 住院号</li>
	 *            <li>deptCode 所在科室</li>
	 *            <li>beginVerifyDate 起始确认日期</li>
	 *            <li>endVerifyDate 结束确认日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialPatsMaster> findAgentListByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String storageCode = (String) conditions.get("storageCode");
		String salerCode = (String) conditions.get("salerCode");
		String wardCode = (String) conditions.get("wardCode");
		String inpNo = (String) conditions.get("inpNo");
		String deptCode = (String) conditions.get("deptCode");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		String currentStatus = (String) conditions.get("currentStatus");
		String materialCode = (String) conditions.get("materialCode");
		StringBuilder shql = new StringBuilder("from MaterialPatsMaster m where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and salerCode=:salerCode");
		}
		if (wardCode != null && !"".equals(wardCode)) {
			shql.append(" and wardCode=:wardCode");
		}
		if (inpNo != null && !"".equals(inpNo)) {
			shql.append(" and inpNo=:inpNo");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (beginVerifyDate != null) {
			shql.append(" and verifyDate>=:beginVerifyDate");
		}
		if (endVerifyDate != null) {
			shql.append(" and verifyDate<=:endVerifyDate");
		}
		if (currentStatus != null && !"".equals(currentStatus)) {
			shql.append(" and currentStatus=:currentStatus");
		}
		// 从表条件
		StringBuilder shql2 = new StringBuilder(
				"from MaterialPatsDetail d where d.mainAutoId=m.autoId");
		if (materialCode != null && !"".equals(materialCode)) {
			flag = true;
			shql2.append(" and d.materialCode=:materialCode");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	/**
	 * @author 芮玉红
	 * @date 2012.9.26
	 * 
	 * @param conditions 包含
	 *            <ul>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>beginAccountDate 起始使用日期</li>
	 *            <li>endAccountDate 结束使用日期</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findValueMasterByCondition(int start, int limit,
			Map<String, Object> conditions) {

		Object[] objs=new Object[2];
		String unitsCode = SessionUtil.getUnitsCode();
		String supplyDeptCode = (String) conditions.get("supplyDeptCode");
		Date beginAccountDate = (Date) conditions.get("beginAccountDate");
		Date endAccountDate = (Date) conditions.get("endAccountDate");
		StringBuilder shql = new StringBuilder("from MaterialRdsMasterDept m where unitsCode='")
				.append(unitsCode).append("'");
		
		// 从表条件
		StringBuilder shql2 = new StringBuilder(
				" from MaterialRdsDetailDept d where d.barCode in (select v.barCode from VMaterialPats v where v.billType='2' and v.refundSign='0' and v.factInSign='0'");
		if (beginAccountDate != null) {
			shql2.append(" and v.accountDate>=:beginAccountDate");
		}
		if (endAccountDate != null) {
			shql2.append(" and v.accountDate<=:endAccountDate");
		}
		shql2.append(" )");
		if (supplyDeptCode != null && !"".equals(supplyDeptCode)) {
			shql.append(" and m.supplyDeptCode=:supplyDeptCode");
		}
		shql.append(" and exists (").append(shql2).append(") and m.currentStatus='1' order by m.billNo,m.billDate");
		
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<MaterialCurrentStock> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	public MaterialPatsMaster findDetailByBarCode(String barCode) {
		log.debug("finding MaterialPatsMaster by barCode");
		try {
			String queryString = "from MaterialPatsMaster where autoId in (select mainAutoId from MaterialPatsDetail where barCode=?)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, barCode);
			return (MaterialPatsMaster) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by unique Index failed", re);
			throw re;
		}
	}
}