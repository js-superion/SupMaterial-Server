package cn.superion.material.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.jruby.compiler.ir.operands.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.base.ParameterObject;
import cn.superion.material.common.RdConstant;
import cn.superion.material.entity.VMaterialRds;
import cn.superion.material.stat.entity.StockStatistic;
import cn.superion.system.entity.SysUserDept;
import cn.superion.util.BaseHibernateDAO;
import cn.superion.util.SessionUtil;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialRds entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.VMaterialRds
 * @author MyEclipse Persistence Tools
 */

public class VMaterialRdsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialRdsDAO.class);

	// property constants

	public void save(VMaterialRds transientInstance) {
		log.debug("saving VMaterialRds instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VMaterialRds persistentInstance) {
		log.debug("deleting VMaterialRds instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VMaterialRds> findByExample(VMaterialRds instance) {
		log.debug("finding VMaterialRds instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.VMaterialRds").add(
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
	public List<VMaterialRds> findByProperty(String propertyName, Object value) {
		log.debug("finding VMaterialRds instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialRds as model where model."
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
	public List<VMaterialRds> findAll() {
		log.debug("finding all VMaterialRds instances");
		try {
			String queryString = "from VMaterialRds";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VMaterialRds merge(VMaterialRds detachedInstance) {
		log.debug("merging VMaterialRds instance");
		try {
			VMaterialRds result = (VMaterialRds) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VMaterialRds instance) {
		log.debug("attaching dirty VMaterialRds instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VMaterialRds instance) {
		log.debug("attaching clean VMaterialRds instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 查询未开票的入库单据
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>billType 单据类型(蓝字，红字)</li>
	 *            <li>billNo 入库单号</li>
	 *            <li>salerCode 供应商</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VMaterialRds> findUninvoicedByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String[] operationTypes = new String[] { RdConstant.R_PURCHASE,
				RdConstant.R_AGENCY, RdConstant.R_DIRECT, RdConstant.R_SPECIAL };
		String billType = (String) conditions.get("billType");
		String billNo = (String) conditions.get("billNo");
		String salerCode = (String) conditions.get("salerCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String materialClass = (String) conditions.get("materialClass");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		StringBuilder shql = new StringBuilder(
				"from VMaterialRds where unitsCode='")
				.append(unitsCode)
				.append(
						"' and operationType in (:operationTypes) and invoiceSign='0'");
		if (billType != null && !"".equals(billType)) {
			shql.append(" and invoiceType=:billType");
		}
		if (billNo != null && !"".equals(billNo)) {
			shql.append(" and billNo=:billNo");
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and salerCode=:salerCode");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
			shql.append(" and materialCode>=:beginMaterialCode");
		}
		if (endMaterialCode != null && !"".equals(endMaterialCode)) {
			shql.append(" and materialCode<=:endMaterialCode");
		}
		return getSession().createQuery(shql.toString()).setParameterList(
				"operationTypes", operationTypes).setProperties(conditions)
				.list();
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions
	 *            包括
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>billNo 入/出库单号</li>
	 *            <li>beginBillNo 开始入/出库单据编号</li>
	 *            <li>endBillNo 结束入/出库单据编号</li>
	 *            <li>beginBillDate 起始入/出库日期</li>
	 *            <li>endBillDate 结束入/出库日期</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>deptCode 部门/领用部门</li>
	 *            <li>personId 业务员/申请人</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>beginTradePrice 起始进价</li>
	 *            <li>endTradePrice 结束进价</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>beginAvailDate 起始有效日期</li>
	 *            <li>endAvailDate 结束有效日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String rdFlag = (String) conditions.get("rdFlag");
		String storageCode = (String) conditions.get("storageCode");
		String deptUnitsCode = (String) conditions.get("deptUnitsCode"); //这里的unitsCode为前台传入
		String operationType = (String) conditions.get("operationType");
		String billNo = (String) conditions.get("billNo");
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String salerCode = (String) conditions.get("salerCode");
		String deptCode = (String) conditions.get("deptCode");
		String personId = (String) conditions.get("personId");
		String materialCode = (String) conditions.get("materialCode");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		Double beginTradePrice = conditions.get("beginTradePrice") == null ? null
				: Double.valueOf(conditions.get("beginTradePrice").toString());
		Double endTradePrice = conditions.get("endTradePrice") == null ? null
				: Double.valueOf(conditions.get("endTradePrice").toString());
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		String factoryCode = (String) conditions.get("factoryCode");
		String currentStatus = (String) conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder(
				"from VMaterialRds where unitsCode='").append(unitsCode)
				.append("'");
		if (rdFlag != null && !"".equals(rdFlag)) {
			shql.append(" and rdFlag=:rdFlag");
		}
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (deptUnitsCode != null && !"".equals(deptUnitsCode)) {
			shql.append(" and deptUnitsCode=:deptUnitsCode");
		}
		if (operationType != null && !"".equals(operationType)) {
			shql.append(" and operationType=:operationType");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (billNo != null && !"".equals(billNo)) {
			shql.append(" and billNo=:billNo");
		} else {
			if (beginBillNo != null && !"".equals(beginBillNo)) {
				shql.append(" and billNo>=:beginBillNo");
			}
			if (endBillNo != null && !"".equals(endBillNo)) {
				shql.append(" and billNo<=:endBillNo");
			}
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and salerCode=:salerCode");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (personId != null && !"".equals(personId)) {
			shql.append(" and personId=:personId");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		} else {
			if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
				shql.append(" and materialCode>=:beginMaterialCode");
			}
			if (endMaterialCode != null && !"".equals(endMaterialCode)) {
				shql.append(" and materialCode<=:endMaterialCode");
			}
		}
		if (beginTradePrice != null) {
			shql.append(" and tradePrice>=:beginTradePrice");
			conditions.put("beginTradePrice", beginTradePrice);
		}
		if (endTradePrice != null) {
			shql.append(" and tradePrice<=:endTradePrice");
			conditions.put("endTradePrice", endTradePrice);
		}
		if (beginAvailDate != null) {
			shql.append(" and availDate>=:beginAvailDate");
		}
		if (endAvailDate != null) {
			shql.append(" and availDate<=:endAvailDate");
		}
		if (factoryCode != null && !"".equals(factoryCode)) {
			shql.append(" and factoryCode=:factoryCode");
		}
		if (currentStatus != null && !"".equals(currentStatus)) {
			shql.append(" and currentStatus=:currentStatus");
		}
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<VMaterialRds> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 流水帐或库存台帐查询调用
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 收发类别</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            <li>maker 制单人</li>
	 *            <li>verifier 审核人</li>
	 *            <li>materialId 物资ID,与物资分类，物资编码范围，生产厂家条件互斥</li>
	 *            <li>billNo 单据号,与单据号范围条件互斥</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @param orderBy 排序子句
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VMaterialRds> findByCondition(String unitsCode,
			Map<String, Object> conditions,String orderBy) {
		String storageCode = (String) conditions.get("storageCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		String materialClass = (String) conditions.get("materialClass");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		String factoryCode = (String) conditions.get("factoryCode");
		String salerCode = (String) conditions.get("salerCode");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String deptCode = (String) conditions.get("deptCode");
		String personId = (String) conditions.get("personId");
		String maker = (String) conditions.get("maker");
		String verifier = (String) conditions.get("verifier");
		String materialId = (String) conditions.get("materialId");
		String billNo = (String) conditions.get("billNo");
		String currentStatus = (String) conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder(
				"from VMaterialRds where unitsCode='").append(unitsCode)
				.append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (beginVerifyDate != null) {
			shql.append(" and verifyDate>=:beginVerifyDate");
		}
		if (endVerifyDate != null) {
			shql.append(" and verifyDate<=:endVerifyDate");
		}
		if (billNo != null && !"".equals(billNo)) {
			shql.append(" and billNo=:billNo");
		} else {
			if (beginBillNo != null && !"".equals(beginBillNo)) {
				shql.append(" and billNo>=:beginBillNo");
			}
			if (endBillNo != null && !"".equals(endBillNo)) {
				shql.append(" and billNo<=:endBillNo");
			}
		}
		if (materialId != null && !"".equals(materialId)) {
			shql.append(" and materialId=:materialId");
		} else {
			if (materialClass != null && !"".equals(materialClass)) {
				shql.append(" and materialClass=:materialClass");
			}
			if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
				shql.append(" and materialCode>=:beginMaterialCode");
			}
			if (endMaterialCode != null && !"".equals(endMaterialCode)) {
				shql.append(" and materialCode<=:endMaterialCode");
			}
			if (factoryCode != null && !"".equals(factoryCode)) {
				shql.append(" and factoryCode=:factoryCode");
			}
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and salerCode=:salerCode");
		}
		if (operationType != null && !"".equals(operationType)) {
			shql.append(" and operationType=:operationType");
		}
		if (rdType != null && !"".equals(rdType)) {
			shql.append(" and rdType=:rdType");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (personId != null && !"".equals(personId)) {
			shql.append(" and personId=:personId");
		}
		if (maker != null && !"".equals(maker)) {
			shql.append(" and maker=:maker");
		}
		if (verifier != null && !"".equals(verifier)) {
			shql.append(" and verifier=:verifier");
		}
		if (currentStatus != null && !"".equals(currentStatus)) {
			shql.append(" and currentStatus>:currentStatus");
		}
		shql.append(orderBy);
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	public List<VMaterialRds> findByCondition2(String unitsCode,
			Map<String, Object> conditions,String orderBy) {
		String storageCode = (String) conditions.get("storageCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		String materialClass = (String) conditions.get("materialClass");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		String factoryCode = (String) conditions.get("factoryCode");
		String salerCode = (String) conditions.get("salerCode");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String deptCode = (String) conditions.get("deptCode");
		String personId = (String) conditions.get("personId");
		String maker = (String) conditions.get("maker");
		String verifier = (String) conditions.get("verifier");
		String materialId = (String) conditions.get("materialId");
		String billNo = (String) conditions.get("billNo");
		String currentStatus = (String) conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder(
				"from VMaterialRds2 where unitsCode='").append(unitsCode)
				.append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (beginVerifyDate != null) {
			shql.append(" and verifyDate>=:beginVerifyDate");
		}
		if (endVerifyDate != null) {
			shql.append(" and verifyDate<=:endVerifyDate");
		}
		if (billNo != null && !"".equals(billNo)) {
			shql.append(" and billNo=:billNo");
		} else {
			if (beginBillNo != null && !"".equals(beginBillNo)) {
				shql.append(" and billNo>=:beginBillNo");
			}
			if (endBillNo != null && !"".equals(endBillNo)) {
				shql.append(" and billNo<=:endBillNo");
			}
		}
		if (materialId != null && !"".equals(materialId)) {
			shql.append(" and materialId=:materialId");
		} else {
			if (materialClass != null && !"".equals(materialClass)) {
				shql.append(" and materialClass=:materialClass");
			}
			if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
				shql.append(" and materialCode>=:beginMaterialCode");
			}
			if (endMaterialCode != null && !"".equals(endMaterialCode)) {
				shql.append(" and materialCode<=:endMaterialCode");
			}
			if (factoryCode != null && !"".equals(factoryCode)) {
				shql.append(" and factoryCode=:factoryCode");
			}
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and salerCode=:salerCode");
		}
		if (operationType != null && !"".equals(operationType)) {
			shql.append(" and operationType=:operationType");
		}
		if (rdType != null && !"".equals(rdType)) {
			shql.append(" and rdType=:rdType");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (personId != null && !"".equals(personId)) {
			shql.append(" and personId=:personId");
		}
		if (maker != null && !"".equals(maker)) {
			shql.append(" and maker=:maker");
		}
		if (verifier != null && !"".equals(verifier)) {
			shql.append(" and verifier=:verifier");
		}
		if (currentStatus != null && !"".equals(currentStatus)) {
			shql.append(" and currentStatus=:currentStatus");
		}
		shql.append(orderBy);
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	/**
	 * 库存台帐查询物资ID了列表
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>billNo 单据号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 经手人</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findMaterialIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String storageCode = (String) conditions.get("storageCode");
		String billNo = (String) conditions.get("billNo");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String deptCode = (String) conditions.get("deptCode");
		String personId = (String) conditions.get("personId");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		String factoryCode = (String) conditions.get("factoryCode");
		//String currentStatus = (String) conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder(
				"select distinct materialId from VMaterialRds where currentStatus>'0' and unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (billNo != null && !"".equals(billNo)) {
			shql.append(" and billNo=:billNo");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (personId != null && !"".equals(personId)) {
			shql.append(" and personId=:personId");
		}
		if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
			shql.append(" and materialCode>=:beginMaterialCode");
		}
		if (endMaterialCode != null && !"".equals(endMaterialCode)) {
			shql.append(" and materialCode<=:endMaterialCode");
		}
		if (factoryCode != null && !"".equals(factoryCode)) {
			shql.append(" and factoryCode=:factoryCode");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	/**
	 * 按供应商汇总入出库数量
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillDate 起始日期</li>
	 *            <li>endBillDate 结束日期</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StockStatistic> addUpSalerRdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String rdFlag = (String) conditions.get("rdFlag");
		String storageCode = (String) conditions.get("storageCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String salerCode = (String) conditions.get("salerCode");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String detailRemark = (String) conditions.get("detailRemark");
		String remark = (String) conditions.get("remark");
		String isGive = (String) conditions.get("isGive");
		String fixedSign = (String)conditions.get("fixedSign");
		StringBuilder shql = new StringBuilder(
				" from VMaterialRds where unitsCode='")
				.append(unitsCode).append("'");
		if (rdFlag != null && !"".equals(rdFlag)) {
			shql.append(" and rdFlag=:rdFlag");
		}
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and salerCode=:salerCode");
		}
		if (operationType != null && !"".equals(operationType)) {
			shql.append(" and operationType=:operationType");
		}
		if (rdType != null && !"".equals(rdType)) {
			shql.append(" and rdType=:rdType");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (isGive != null && !"".equals(isGive)) {
			shql.append(" and isGive=:isGive");
		}
		
		if(remark != null && !"".equals(remark)){
			shql.append(" and remark like :remark");
			conditions.put("remark","%"+remark+"%" );
		}
		
		if(detailRemark != null && !"".equals(detailRemark)){
			shql.append(" and detailRemark like :detailRemark");
			conditions.put("detailRemark","%"+detailRemark+"%" );
		}
		if(fixedSign != null && fixedSign.equals("1")){
			shql.append(" and materialId in (select r.materialId from CdMaterialDict r where r.fixedSign='1') ");
		}
//		shql
//				.append(" group by storageCode,salerCode,salerName,materialClass,materialCode,materialName,materialSpec,materialUnits,tradePrice");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<StockStatistic> addUpSalerRdsByConditionSum(String unitsCode,
			Map<String, Object> conditions) {
		String rdFlag = (String) conditions.get("rdFlag");
		String storageCode = (String) conditions.get("storageCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String salerCode = (String) conditions.get("salerCode");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String detailRemark = (String) conditions.get("detailRemark");
		String remark = (String) conditions.get("remark");
		String isGive = (String) conditions.get("isGive");
		StringBuilder shql = new StringBuilder(
				"select new Map(salerName as salerName,sum(wholeSaleMoney) as wholeSaleMoney,sum(inviteMoney) as inviteMoney) from VMaterialRds where unitsCode='")
				.append(unitsCode).append("'");
		if (rdFlag != null && !"".equals(rdFlag)) {
			shql.append(" and rdFlag=:rdFlag");
		}
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and salerCode=:salerCode");
		}
		if (operationType != null && !"".equals(operationType)) {
			shql.append(" and operationType=:operationType");
		}
		if (rdType != null && !"".equals(rdType)) {
			shql.append(" and rdType=:rdType");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (isGive != null && !"".equals(isGive)) {
			shql.append(" and isGive=:isGive");
		}
		
		if(remark != null && !"".equals(remark)){
			shql.append(" and remark like :remark");
			conditions.put("remark","%"+remark+"%" );
		}
		
		if(detailRemark != null && !"".equals(detailRemark)){
			shql.append(" and detailRemark like :detailRemark");
			conditions.put("detailRemark","%"+detailRemark+"%" );
		}
		shql
				.append(" group by salerName ");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	
	
	/**
	 * 按部门汇总入出库数量
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillDate 起始日期</li>
	 *            <li>endBillDate 结束日期</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StockStatistic> addUpDeptRdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String rdFlag = (String) conditions.get("rdFlag");
		String storageCode = (String) conditions.get("storageCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String deptUnitsCode = (String) conditions.get("deptUnitsCode"); //这里的unitsCode为前台传入
		String deptCode = (String) conditions.get("deptCode");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String detailRemark = (String) conditions.get("detailRemark");
		Boolean isDeptList = (Boolean) conditions.get("isDeptList");
		String remark = (String) conditions.get("remark");
		String fixedSign = (String)conditions.get("fixedSign");
		StringBuilder shql = new StringBuilder(
				" from VMaterialRds m where  currentStatus <> '0' and unitsCode='")
				.append(unitsCode).append("'");
		if (rdFlag != null && !"".equals(rdFlag)) {
			shql.append(" and rdFlag=:rdFlag");
		}
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
//		if (deptUnitsCode != null && !"".equals(deptUnitsCode)) {
//			shql.append(" and deptUnitsCode=:deptUnitsCode");
//		}
		if(isDeptList!=null && isDeptList){
				shql.append(" and deptCode in(:deptCodes)");
		}else{
			if (deptCode != null && !"".equals(deptCode)) {
				shql.append(" and deptCode=:deptCode");
			}
		}
		
		if (operationType != null && !"".equals(operationType)) {
			shql.append(" and operationType=:operationType");
		}
		if (rdType != null && !"".equals(rdType)) {
			shql.append(" and rdType=:rdType");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if(remark != null && !"".equals(remark)){
			shql.append(" and remark like :remark");
			conditions.put("remark","%"+remark+"%" );
		}
		if(detailRemark != null && !"".equals(detailRemark)){
			shql.append(" and detailRemark like :detailRemark");
			conditions.put("detailRemark","%"+detailRemark+"%" );
		}
		if(fixedSign != null && fixedSign.equals("1")){
			shql.append(" and materialId in (select r.materialId from CdMaterialDict r where r.fixedSign='1') ");
		}
//		shql
//				.append(" group by unitsCode,storageCode,deptCode,materialClass,materialCode,materialName,materialSpec,materialUnits,tradePrice");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	/**
	 * 统计入库和出库数量,金额
	 * 
	 * @param unitsCode
	 * @param storageCode
	 * @param startDate
	 * @param endDate
	 * @param materialId
	 * @return 返回采购入库数量，其他入库数量，领用出库数量，其他出库数量，进价金额，售价金额
	 */
	public Object[] addUpRdsAmountMoney(String unitsCode, String storageCode,
			Date startDate, Date endDate, String materialId) {
		StringBuilder sql = new StringBuilder("select sum(case when operation_type in ('101','102','103','106') then amount  else 0 end) as receiveAmount")
		.append(",sum(case when operation_type in ('104','109') then amount  else 0 end) as otherReceiveAmount")
		.append(",sum(case when operation_type ='201' then amount  else 0 end) as deliveryAmount")
		.append(",sum(case when operation_type in ('203','204','205','209') then amount  else 0 end) as deliveryOtherAmount")
		.append(",sum(case when operation_type in ('101','102','103','106','104','109') then trade_money else 0 end) as tradeMoney")
		.append(",sum(case when rd_flag='2' then retail_money else 0 end) as retailMoney")
		.append(" from v_material_rds where units_code=:unitsCode and storage_code=:storageCode and bill_date>=:startDate and bill_date<1+:endDate and material_id=:materialId and current_status <> '0'");
		return (Object[]) getSession().createSQLQuery(sql.toString()).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode)
				.setTimestamp("startDate", startDate).setTimestamp("endDate", endDate)
				.setString("materialId", materialId).uniqueResult();
	}
	/**
	 * 根据批号查找记录
	 * @param unitsCode
	 * @param storageCode
	 * @param startDate
	 * @param endDate
	 * @param materialId
	 * @param batch
	 * @return
	 */
	public Object[] addUpRdsAmountMoney(String unitsCode, String storageCode,
			Date startDate, Date endDate, String materialId,String batch) {
		StringBuilder sql = new StringBuilder("select sum(case when operation_type in ('101','102','103','106','104','109','110') then amount  else 0 end) as receiveAmount")
		//.append(",sum(case when operation_type in ('104','109') then amount  else 0 end) as otherReceiveAmount")
		//.append(",sum(case when operation_type ='201' then amount  else 0 end) as deliveryAmount")deliveryOtherAmount
		//.append(",sum(case when operation_type in ('201','203','204','205','209','210') then amount  else 0 end) as deliveryAmount")
		.append(",sum(case when rd_flag='2' then amount  else 0 end) as deliveryAmount")
		.append(",sum(case when operation_type in ('101','102','103','106','104','109','110') then whole_sale_money else 0 end) as tradeMoney")
		.append(",sum(case when rd_flag='2' then whole_sale_money else 0 end) as retailMoney")
		.append(" from v_material_rds where units_code=:unitsCode and storage_code=:storageCode and bill_date>=:startDate and bill_date<=:endDate and material_id=:materialId and batch =:batch and current_status <> '0'");
		return (Object[]) getSession().createSQLQuery(sql.toString()).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode)
				.setTimestamp("startDate", startDate).setTimestamp("endDate", endDate)
				.setString("materialId", materialId).setString("batch", batch).uniqueResult();
	}

	public Double addUpInitAmount(String unitsCode, String storageCode,
			String materialId) {
		String sql = "select sum(amount) from v_material_rds where units_code=:unitsCode and storage_code=:storageCode and material_id=:materialId and operation_type='105' and current_status <> '0'";
		Object result = getSession().createSQLQuery(sql.toString()).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode).setString("materialId", materialId).uniqueResult();
		return result==null?0d:Double.valueOf(result.toString());
	}
	/**
	 * 根据批号查找物资
	 * @param unitsCode
	 * @param storageCode
	 * @param materialId
	 * @param batch
	 * @return
	 */
	public Double addUpInitAmount(String unitsCode, String storageCode,
			String materialId,String batch) {
		String sql = "select sum(amount) from v_material_rds where units_code=:unitsCode and storage_code=:storageCode and material_id=:materialId and operation_type='105' and batch=:batch and current_status <> '0'";
		Object result = getSession().createSQLQuery(sql.toString()).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode).setString("materialId", materialId).setString("batch", batch).uniqueResult();
		return result==null?0d:Double.valueOf(result.toString());
	}

	public List findGroupBy(String fstrGroupFields) {
		String sql = "select new map(sum(amount) as amount" +toMapFields(fstrGroupFields)+
				") from VMaterialRds group by "+fstrGroupFields;
		Query query = getSession().createQuery(sql.toString());
		return query.list();
	}
	
	private String toMapFields(String fstrGroupFields){
		String[] laryGroupFields= fstrGroupFields.split(",");
		for(String field:laryGroupFields){
			fstrGroupFields=fstrGroupFields.replaceAll(field, field+" as "+field);
		}
		return ","+fstrGroupFields;
	}
	//查询顶级分类，01，02，03，04
	public List<String> findClassName(ParameterObject fparameter){
		String sql = "select t.class_name from CD_MATERIAL_CLASS t where t.class_code in "+
					 "(select substr(t.class_code,0,2) from CD_MATERIAL_CLASS t " +
					 " group by substr(t.class_code,0,2)) " +
					 " order by substr(t.class_code,0,2)";
		SQLQuery sl = getSession().createSQLQuery(sql);
		List<Object> list = sl.list();
		List<String> list1 = new ArrayList<String>();
		for(Object item:list){
			list1.add(item.toString());
		}
		return list1;
	}
	//查询计算机分类，只要是计算机仓库出库的。
	public List<Map<String,Object>> findComputerClassName(ParameterObject fparameter){
		String sql = "select t.material_class from v_material_rds t where t.storage_code='103' group by t.material_class "+
					 "order by t.material_class";
		SQLQuery sl = getSession().createSQLQuery(sql);
		List<Object> list = sl.list();
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(Object item:list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("materialClass", item.toString());
			listMap.add(map);
		}
		return listMap;
	}
	//查询医疗物资二级分类。
	public List<Map<String,Object>> findMedicalClassName(ParameterObject fparameter){
		String sql = "select substr(t.class_code,0,4) from CD_MATERIAL_CLASS t where t.class_code like '03%' group by substr(t.class_code,0,4)"+
					 " order by substr(t.class_code,0,4)";
		SQLQuery sl = getSession().createSQLQuery(sql);
		List<Object> list = sl.list();
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(Object item:list){
			if(item.toString().length()>2){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("materialClass", item.toString());
				listMap.add(map);
			}
			
		}
		return listMap;
	}
	//查询科室，在出库表中存在的。
	public List<String> findDept(ParameterObject fparameter){
		String sql = "select t.dept_code from v_material_rds t where t.dept_code is not null and t.storage_code=:storageCode "+
					 " group by t.dept_code order by t.dept_code";
		SQLQuery sl = getSession().createSQLQuery(sql);
		sl.setString("storageCode", (String)fparameter.getConditions().get("storageCode"));
		List<Object> list = sl.list();
		List<String> list1 = new ArrayList<String>();
		for(Object item:list){
			list1.add(item.toString());
		}
		return list1;
	}
	//查询科室备注
	public String findDeptRemark(String unitsCode,String deptCode){
		boolean flag = false;
		String sql = "select remark from cd_dept_dict where dept_code =? ";
		if(unitsCode !=null && !unitsCode.equals("")){
			sql += " and units_code = ?";
			flag = true;
		}
		SQLQuery sl = getSession().createSQLQuery(sql);
		sl.setString(0, deptCode);
		if(flag){
			sl.setString(1, unitsCode);
		}
		return (String)sl.uniqueResult();
	}
	//顶级分类下的物资类别和总费用，01，02，03，04
	public List<Map<String,Object>> findFee(ParameterObject fparameter){
		
		Date beginBillDate = (Date)fparameter.getConditions().get("beginBillDate");
		Date endBillDate = (Date)fparameter.getConditions().get("endBillDate");
		String unitsCode = (String)fparameter.getConditions().get("deptUnitsCode");
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		String sql = "select substr(t.materialClass,0,2),sum(t.wholeSaleMoney) "+
		"from VMaterialRds  t where t.deptCode = :deptCode and t.billDate >= :beginBillDate " +
		" and t.billDate <= :endBillDate and t.storageCode = :storageCode " +
		" and t.rdFlag='2' and t.currentStatus <>'0' group by substr(t.materialClass,0,2) order by "+
		" substr(t.materialClass,0,2)";
		Query sl = getSession().createQuery(sql);
		List<String> list = findDept(fparameter);
		for(int i=0;i<list.size();i++){
			sl.setString("deptCode", list.get(i));
			sl.setTimestamp("beginBillDate", beginBillDate);
			sl.setTimestamp("endBillDate", endBillDate);
			sl.setString("storageCode", (String)fparameter.getConditions().get("storageCode"));
			List<Object[]> list1 = sl.list();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deptCode", list.get(i));
			map.put("remark", findDeptRemark(unitsCode,(String)list.get(i)));
			Double dl = 0.0;
			for(int j = 0;j<list1.size();j++){
				if(list1.get(j)[0].toString().equals("01")){
					map.put("material1", (Double)list1.get(j)[1]);
				}else if(list1.get(j)[0].toString().equals("02")){
					map.put("material2", (Double)list1.get(j)[1]);
				}else if(list1.get(j)[0].toString().equals("03")){
					map.put("material3", (Double)list1.get(j)[1]);
				}else if(list1.get(j)[0].toString().equals("04")){
					map.put("material4", (Double)list1.get(j)[1]);
				}else{
					map.put(list1.get(j)[0].toString(), (Double)list1.get(j)[1]);
				}
				dl = dl + (Double)(list1.get(j)[1]);
			}
			map.put("sum", dl);
			listMap.add(map);
		}
		return listMap;
	}
	//计算机仓库下的出库的物资分类和总费用
	public List<Map<String,Object>> findComputerFee(ParameterObject fparameter){
		
		Date beginBillDate = (Date)fparameter.getConditions().get("beginBillDate");
		Date endBillDate = (Date)fparameter.getConditions().get("endBillDate");
		String unitsCode = (String)fparameter.getConditions().get("deptUnitsCode");
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		String sql = "select t.materialClass,sum(t.wholeSaleMoney) "+
		"from VMaterialRds  t where t.unitsCode='070102' and t.deptCode = :deptCode and t.billDate >= :beginBillDate " +
		" and t.billDate <= :endBillDate and t.storageCode = '103' " +
		" and t.rdFlag='2' and t.currentStatus <>'0' group by t.materialClass order by "+
		" t.materialClass";
		Query sl = getSession().createQuery(sql);
		List<String> list = findDept(fparameter);
		for(int i=0;i<list.size();i++){
			sl.setString("deptCode", list.get(i).toString());
			sl.setTimestamp("beginBillDate", beginBillDate);
			sl.setTimestamp("endBillDate", endBillDate);
//			sl.setString("storageCode", (String)fparameter.getConditions().get("storageCode"));
			List<Object[]> list1 = sl.list();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deptCode", list.get(i));
			map.put("remark", findDeptRemark(unitsCode,(String)list.get(i)));
			Double dl = 0.0;
			for(int j = 0;j<list1.size();j++){
				map.put(list1.get(j)[0].toString(), (Double)list1.get(j)[1]);
				dl = dl + (Double)(list1.get(j)[1]);
			}
			map.put("sum", dl);
			listMap.add(map);
		}
		return listMap;
	}
	
	//医疗仓库下的出库的物资分类和总费用
	public List<Map<String,Object>> findMedicalFee(ParameterObject fparameter){
		
		Date beginBillDate = (Date)fparameter.getConditions().get("beginBillDate");
		Date endBillDate = (Date)fparameter.getConditions().get("endBillDate");
		String unitsCode = (String)fparameter.getConditions().get("deptUnitsCode");
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		String sql = "select substr(t.materialClass,0,4),sum(t.wholeSaleMoney) "+
		"from VMaterialRds  t where t.unitsCode = :unitsCode and deptCode = :deptCode and t.billDate >= :beginBillDate " +
		" and t.billDate <= :endBillDate and t.storageCode = '102' and t.materialClass like '03%' " +
		" and t.rdFlag='2' and t.currentStatus <>'0' group by substr(t.materialClass,0,4) order by "+
		" substr(t.materialClass,0,4)";
		Query sl = getSession().createQuery(sql);
		sl.setString("unitsCode",unitsCode);
		List<String> list = findDept(fparameter);
		for(int i=0;i<list.size();i++){
			sl.setString("deptCode", list.get(i));
			sl.setTimestamp("beginBillDate", beginBillDate);
			sl.setTimestamp("endBillDate", endBillDate);
//			sl.setString("storageCode", (String)fparameter.getConditions().get("storageCode"));
			List<Object[]> list1 = sl.list();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deptCode", list.get(i));
			map.put("remark", findDeptRemark(unitsCode,(String)list.get(i)));
			Double dl = 0.0;
			for(int j = 0;j<list1.size();j++){
				map.put(list1.get(j)[0].toString(), (Double)list1.get(j)[1]);
				dl = dl + (Double)(list1.get(j)[1]);
			}
			map.put("sum", dl);
			listMap.add(map);
		}
		return listMap;
	}
	
	//医疗仓库下的出库的计价分类和总费用
	public List<Map<String,Object>> findMedicalFeeByCountClass(ParameterObject fparameter){
		
		Date beginBillDate = (Date)fparameter.getConditions().get("beginBillDate");
		Date endBillDate = (Date)fparameter.getConditions().get("endBillDate");
		String unitsCode = (String)fparameter.getConditions().get("deptUnitsCode");
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		String sql = "select t.countClass,sum(t.wholeSaleMoney) "+
		"from VMaterialRds  t where t.unitsCode = :unitsCode and deptCode = :deptCode and t.billDate >= :beginBillDate " +
		" and t.billDate <= :endBillDate and t.storageCode = '102' and t.countClass is not null " +
		" and t.rdFlag='2' and t.currentStatus <>'0' group by t.countClass ";
		Query sl = getSession().createQuery(sql);
		sl.setString("unitsCode",unitsCode);
		List<String> list = findDept(fparameter);
		for(int i=0;i<list.size();i++){
			sl.setString("deptCode", list.get(i));
			sl.setTimestamp("beginBillDate", beginBillDate);
			sl.setTimestamp("endBillDate", endBillDate);
//			sl.setString("storageCode", (String)fparameter.getConditions().get("storageCode"));
			List<Object[]> list1 = sl.list();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deptCode", list.get(i));
			map.put("remark", findDeptRemark(unitsCode,(String)list.get(i)));
			Double dl = 0.0;
			for(int j = 0;j<list1.size();j++){
				map.put(list1.get(j)[0].toString(), (Double)list1.get(j)[1]);
				dl = dl + (Double)(list1.get(j)[1]);
			}
			map.put("sum", dl);
			listMap.add(map);
		}
		return listMap;
	}
	
	//医疗仓库下的入库的计价分类和总费用
	public List<Map<String,Object>> findMedicalReceiveFeeByCountClass(ParameterObject fparameter){
		
		Date beginBillDate = (Date)fparameter.getConditions().get("beginBillDate");
		Date endBillDate = (Date)fparameter.getConditions().get("endBillDate");
		String unitsCode = (String)fparameter.getConditions().get("deptUnitsCode");
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		String sql = "select t.countClass,sum(t.wholeSaleMoney) "+
		"from VMaterialRds  t where t.unitsCode = :unitsCode and t.billDate >= :beginBillDate " +
		" and t.billDate <= :endBillDate and t.storageCode = '102' and t.countClass is not null " +
		" and t.rdFlag='1' and t.currentStatus <>'0' group by t.countClass ";
		Query sl = getSession().createQuery(sql);
		sl.setString("unitsCode",unitsCode);
		sl.setTimestamp("beginBillDate", beginBillDate);
		sl.setTimestamp("endBillDate", endBillDate);
		List<Object[]> list1 = sl.list();
		Double dl = 0.0;
		Map<String,Object> map = new HashMap<String,Object>();
		for(int j = 0;j<list1.size();j++){
			map.put(list1.get(j)[0].toString(), (Double)list1.get(j)[1]);
			dl = dl + (Double)(list1.get(j)[1]);
		}
		map.put("sum", dl);
		listMap.add(map);
		return listMap;
		
//		List<String> list = findDept(fparameter);
//		for(int i=0;i<list.size();i++){
//			sl.setString("deptCode", list.get(i));
//			sl.setDate("beginBillDate", beginBillDate);
//			sl.setDate("endBillDate", endBillDate);
////			sl.setString("storageCode", (String)fparameter.getConditions().get("storageCode"));
//			List<Object[]> list1 = sl.list();
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("deptCode", list.get(i));
//			map.put("remark", findDeptRemark(unitsCode,(String)list.get(i)));
//			Double dl = 0.0;
//			for(int j = 0;j<list1.size();j++){
//				map.put(list1.get(j)[0].toString(), (Double)list1.get(j)[1]);
//				dl = dl + (Double)(list1.get(j)[1]);
//			}
//			map.put("sum", dl);
//			listMap.add(map);
//		}
//		return listMap;
	}
}