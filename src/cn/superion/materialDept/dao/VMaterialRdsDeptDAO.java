package cn.superion.materialDept.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import cn.superion.materialDept.entity.VMaterialRdsDept;
import cn.superion.materialDept.stat.entity.StockStatistic;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialRds entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.materialDept.entity.VMaterialRdsDept
 * @author MyEclipse Persistence Tools
 */

public class VMaterialRdsDeptDAO extends BaseHibernateDAO {

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
	 *            <li>deptCode 部门/领用部门</li>
	 *            <li>supplyDeptCode 供应部门</li>
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
		String operationType = (String) conditions.get("operationType");
		String billNo = (String) conditions.get("billNo");
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String deptCode = (String) conditions.get("deptCode");
		String supplyDeptCode = (String) conditions.get("supplyDeptCode");
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
				"from VMaterialRdsDept where unitsCode='").append(unitsCode)
				.append("'");
		if (rdFlag != null && !"".equals(rdFlag)) {
			shql.append(" and rdFlag=:rdFlag");
		}
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
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
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (supplyDeptCode != null && !"".equals(supplyDeptCode)) {
			shql.append(" and supplyDeptCode=:supplyDeptCode");
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
		List<VMaterialRdsDept> list = query.setProperties(conditions)
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
	 *            <li>supplyDeptCode 供应单位</li>
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
	public List<VMaterialRdsDept> findByCondition(String unitsCode,
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
		String supplyDeptCode = (String) conditions.get("supplyDeptCode");
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
				"from VMaterialRdsDept where unitsCode='").append(unitsCode)
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
		if (supplyDeptCode != null && !"".equals(supplyDeptCode)) {
			shql.append(" and supplyDeptCode=:supplyDeptCode");
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
				"select distinct materialId from VMaterialRdsDept where currentStatus='1' and unitsCode='")
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
	 * 按供应部门，物资类别汇总入出库数量
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillDate 起始日期</li>
	 *            <li>endBillDate 结束日期</li>
	 *            <li>supplyDeptCode 供应部门</li>
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
		String supplyDeptCode = (String) conditions.get("supplyDeptCode");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		StringBuilder shql = new StringBuilder(
				"select new cn.superion.materialDept.stat.entity.StockStatistic(storageCode,supplyDeptCode,(select deptName from CdDeptDict dp where dp.unitsCode=m.unitsCode and dp.deptCode=m.supplyDeptCode),materialClass,materialCode,materialName,materialSpec,materialUnits,sum(amount),sum(tradeMoney),batch) from VMaterialRdsDept m where unitsCode='")
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
		if (supplyDeptCode != null && !"".equals(supplyDeptCode)) {
			shql.append(" and supplyDeptCode=:supplyDeptCode");
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
		shql
				.append(" group by unitsCode,storageCode,supplyDeptCode,materialClass,materialCode,materialName,materialSpec,materialUnits,batch");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	
	/**
	 * 按物资类别汇总入出库数量
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillDate 起始日期</li>
	 *            <li>endBillDate 结束日期</li>
	 *            <li>supplyDeptCode 供应部门</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>rdType 入库类别</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StockStatistic> addUpRdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String rdFlag = (String) conditions.get("rdFlag");
		String storageCode = (String) conditions.get("storageCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		StringBuilder shql = new StringBuilder(
				"select new cn.superion.materialDept.stat.entity.StockStatistic(storageCode,materialClass,materialCode,materialName,materialSpec,materialUnits,sum(amount),sum(tradeMoney)) from VMaterialRdsDept m where unitsCode='")
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
		shql
				.append(" group by storageCode,materialClass,materialCode,materialName,materialSpec,materialUnits");
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
	 * @return 返回领用入库数量，其他入库数量，领用出库数量，其他出库数量，进价金额，售价金额
	 */
	public Object[] addUpRdsAmountMoney(String unitsCode, String storageCode,
			Date startDate, Date endDate, String materialId) {
		StringBuilder sql = new StringBuilder("select sum(case when operation_type ='107' then amount  else 0 end) as receiveAmount")
		.append(",sum(case when operation_type in ('104','105','109') then amount  else 0 end) as otherReceiveAmount")
		.append(",sum(case when operation_type ='201' then amount  else 0 end) as deliveryAmount")
		.append(",sum(case when operation_type in ('203','204','205','209') then amount  else 0 end) as deliveryOtherAmount")
		.append(",sum(case when rd_flag='1' then trade_money else 0 end) as tradeMoney")
		.append(",sum(case when rd_flag='2' then retail_money else 0 end) as retailMoney")
		.append(" from v_material_rds_dept where units_code=:unitsCode and storage_code=:storageCode and bill_date>=:startDate and bill_date<1+:endDate and material_id=:materialId and current_status <> '0'");
		return (Object[]) getSession().createSQLQuery(sql.toString()).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode)
				.setDate("startDate", startDate).setDate("endDate", endDate)
				.setString("materialId", materialId).uniqueResult();
	}

}