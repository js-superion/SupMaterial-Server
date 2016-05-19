package cn.superion.material.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialRdsAcctMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialRdsAcctMaster entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialRdsAcctMaster
 * @author MyEclipse Persistence Tools
 */

public class MaterialRdsAcctMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialRdsAcctMasterDAO.class);

	public void save(MaterialRdsAcctMaster transientInstance) {
		log.debug("saving MaterialRdsAcctMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialRdsAcctMaster persistentInstance) {
		log.debug("deleting MaterialRdsAcctMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialRdsAcctMaster findById(java.lang.String id) {
		log.debug("getting MaterialRdsAcctMaster instance with id: " + id);
		try {
			MaterialRdsAcctMaster instance = (MaterialRdsAcctMaster) getSession()
					.get("cn.superion.material.entity.MaterialRdsAcctMaster",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialRdsAcctMaster> findByExample(
			MaterialRdsAcctMaster instance) {
		log.debug("finding MaterialRdsAcctMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialRdsAcctMaster").add(
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
	public List<MaterialRdsAcctMaster> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialRdsAcctMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialRdsAcctMaster as model where model."
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
	public List<MaterialRdsAcctMaster> findAll() {
		log.debug("finding all MaterialRdsAcctMaster instances");
		try {
			String queryString = "from MaterialRdsAcctMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialRdsAcctMaster merge(MaterialRdsAcctMaster detachedInstance) {
		log.debug("merging MaterialRdsAcctMaster instance");
		try {
			MaterialRdsAcctMaster result = (MaterialRdsAcctMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialRdsAcctMaster instance) {
		log.debug("attaching dirty MaterialRdsAcctMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialRdsAcctMaster instance) {
		log.debug("attaching clean MaterialRdsAcctMaster instance");
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
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>rdFlag 出入库标志</li>
	 *            <li>storageCode 仓库</li>
	 *            <li>deptCode 部门</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>beginBillDate1 单据起始日期1</li>
	 *            <li>endBillDate1 单据起始日期2</li>
	 *            <li>beginBillDate2 单据终止日期1</li>
	 *            <li>endBillDate2 单据终止日期2</li>
	 *            <li>accounter 记账人</li>
	 *            <li>beginAccountDate 起始记账日期</li>
	 *            <li>endAccountDate 结束记账日期</li>
	 *            明细记录条件
	 *            <li>beginInvoiceDate 起始发票日期</li>
	 *            <li>endInvoiceDate 结束发票日期</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String rdFlag = (String) conditions.get("rdFlag");
		String storageCode = (String) conditions.get("storageCode");
		// 根据核算的出入库号查询
		String beginRdBillNo = (String) conditions.get("beginRdBillNo");
		String endRdBillNo = (String) conditions.get("endRdBillNo");
		String deptCode = (String) conditions.get("deptCode");
		String deptUnitsCode = (String) conditions.get("deptUnitsCode");
		String salerCode = (String) conditions.get("salerCode");
		Date beginBillDate1 = (Date) conditions.get("beginBillDate1");
		Date endBillDate1 = (Date) conditions.get("endBillDate1");
		Date beginBillDate2 = (Date) conditions.get("beginBillDate2");
		Date endBillDate2 = (Date) conditions.get("endBillDate2");
		String accounter = (String) conditions.get("accounter");
		Date beginAccountDate = (Date) conditions.get("beginAccountDate");
		Date endAccountDate = (Date) conditions.get("endAccountDate");
		String isGive=(String) conditions.get("isGive");
		
		StringBuilder shql = new StringBuilder(
				"from MaterialRdsAcctMaster a where unitsCode='").append(
				unitsCode).append("'");
		if (rdFlag != null && !"".equals(rdFlag)) {
			shql.append(" and rdFlag=:rdFlag");
		}
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginRdBillNo != null && !"".equals(beginRdBillNo)) {

			shql
					.append(" and substring(m.maxRdBillNo,length(m.maxRdBillNo)-7,8)>=:beginRdBillNo");
		}
		if (endRdBillNo != null && !"".equals(endRdBillNo)) {
			shql
					.append(" and substring(m.minRdBillNo,length(m.minRdBillNo)-7,8)<=:endRdBillNo");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (deptUnitsCode != null && !"".equals(deptUnitsCode)) {
			shql.append(" and deptUnitsCode=:deptUnitsCode");
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and salerCode=:salerCode");
		}
		if (beginBillDate1 != null) {
			shql.append(" and billDate1>=:beginBillDate1");
		}
		if (endBillDate1 != null) {
			shql.append(" and billDate1<=:endBillDate1");
		}
		if (beginBillDate2 != null) {
			shql.append(" and billDate2>=:beginBillDate2");
		}
		if (endBillDate2 != null) {
			shql.append(" and billDate2<=:endBillDate2");
		}
		if (accounter != null && !"".equals(accounter)) {
			shql.append(" and accounter=:accounter");
		}
		if (beginAccountDate != null) {
			shql.append(" and accountDate>=:beginAccountDate");
		}
		if (endAccountDate != null) {
			shql.append(" and accountDate<=:endAccountDate");
		}
		boolean flag = false;
		Date beginInvoiceDate = (Date) conditions.get("beginInvoiceDate");
		Date endInvoiceDate = (Date) conditions.get("endInvoiceDate");
		StringBuilder shql2 = new StringBuilder(
				"from MaterialRdsDetail d where d.acctBillNo=a.autoId");
		if (beginInvoiceDate != null) {
			flag = true;
			shql2.append(" and d.invoiceDate>=:beginInvoiceDate");
		}
		if (endInvoiceDate != null) {
			flag = true;
			shql2.append(" and d.invoiceDate<=:endInvoiceDate");
		}
		if(isGive!=null && !isGive.equals("")){
			flag=true;
			shql2.append(" and d.isGive=:isGive");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<MaterialRdsAcctMaster> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 核算出库物资汇总
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>rdFlag 收发标志</li>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始出库单号</li>
	 *            <li>endBillNo 结束出库单号</li>
	 *            <li>beginBillDate 起始日期</li>
	 *            <li>endBillDate 结束日期</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>beginMaterialClass 起始物资分类</li>
	 *            <li>endMaterialClass 结束物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>accounter 记账人</li>
	 *            <li>beginAccountDate 起始记账日期</li>
	 *            <li>endAccountDate 结束记账日期</li>
	 *            <li>statFlag 汇总方式
	 *            <ul>
	 *            <li>1 按领用部门</li>
	 *            <li>2 按物资编码</li>
	 *            <li>3 按领用部门，物资编码</li>
	 *            </ul>
	 *            </li>
	 *            </ul>
	 * @return 包含：
	 *         <ul>
	 *         <li>按领用部门汇总方式返回：
	 *         <ul>
	 *         <li>deptCode 领用部门</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         </ul>
	 *         </li>
	 *         <li>
	 *         按物资编码汇总方式返回：
	 *         <ul>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         </ul>
	 *         </li>
	 *         <li>
	 *         按领用部门,物资编码汇总方式返回：
	 *         <ul>
	 *         <li>unitsCode 所属单位</li>
	 *         <li>deptCode 领用部门</li>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         </ul>
	 *         </li>
	 */
	@SuppressWarnings("unchecked")
	public List<Object> addUpDeliverByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String statFlag = (String) conditions.get("statFlag");
		String selectClause = null;
		String groupByClause = null;
		if ("1".equals(statFlag)) {
			selectClause = "dept.unitsCode as deptUnitsCode ,m.deptCode as deptCode,sum(d.tradePrice*nvl(d.acctAmount,0)) as tradeMoney,sum(d.wholeSalePrice*nvl(d.acctAmount,0)) as saleMoney,sum(d.retailPrice*nvl(d.acctAmount,0)) as retailMoney,sum(case when d.chargeSign = '1' then (d.tradePrice * d.acctAmount) else 0 end) as tradeMoney1,sum(case when d.chargeSign = '0' then (d.tradePrice * d.acctAmount) else 0 end) as tradeMoney0,  sum(case when d.chargeSign = '1' then (d.wholeSalePrice * d.acctAmount) else 0 end) as saleMoney1,sum(case when d.chargeSign = '0' then (d.wholeSalePrice * d.acctAmount) else 0 end) as saleMoney0,sum(case when d.chargeSign = '1' then (d.retailPrice * d.acctAmount) else 0 end) as retailMoney1,sum(case when d.chargeSign = '0' then (d.retailPrice * d.acctAmount) else 0 end) as retailMoney0";
			groupByClause = "dept.unitsCode,m.deptCode";
		} else if ("2".equals(statFlag)) {
			selectClause = "dept.unitsCode as deptUnitsCode,d.materialCode as materialCode,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,d.tradePrice as tradePrice ,d.wholeSalePrice as wholeSalePrice,d.retailPrice as retailPrice,"
					+ "sum(nvl(d.acctAmount,0)) as acctAmount,sum(d.tradePrice*nvl(d.acctAmount,0)) as tradeMoney,sum(d.wholeSalePrice*nvl(d.acctAmount,0)) as saleMoney,sum(d.retailPrice*nvl(d.acctAmount,0)) as retailMoney";
			groupByClause = "dept.unitsCode,d.materialCode,d.materialName,d.materialSpec,d.materialUnits,d.tradePrice,d.wholeSalePrice,d.retailPrice";
		} else if ("3".equals(statFlag)) {
			selectClause = "dept.unitsCode as deptUnitsCode,c.billNo as billNo,c.billDate as billDate,m.deptCode as deptCode,d.materialCode as materialCode,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,d.tradePrice as tradePrice,d.wholeSalePrice as wholeSalePrice,d.retailPrice as retailPrice,d.rdBillNo as rdBillNo,"
					+ "sum(nvl(d.acctAmount,0)) as acctAmount,sum(d.tradePrice*nvl(d.acctAmount,0)) as tradeMoney,sum(d.wholeSalePrice*nvl(d.acctAmount,0)) as saleMoney,sum(d.retailPrice*nvl(d.acctAmount,0)) as retailMoney";
			groupByClause = "dept.unitsCode,c.billNo,c.billDate,m.deptCode,d.materialCode,d.materialName,d.materialSpec,d.materialUnits,d.tradePrice,d.wholeSalePrice,d.retailPrice,d.rdBillNo";
		} else if ("4".equals(statFlag)) {
			selectClause = "dept.unitsCode as deptUnitsCode,c.billNo as billNo,c.billDate as billDate,m.deptCode as deptCode,d.materialCode as materialCode,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,d.tradePrice as tradePrice,d.wholeSalePrice as wholeSalePrice,d.retailPrice as retailPrice,"
					+ "sum(nvl(d.acctAmount,0)) as acctAmount,sum(d.tradePrice*nvl(d.acctAmount,0)) as tradeMoney,sum(d.wholeSalePrice*nvl(d.acctAmount,0)) as saleMoney,sum(d.retailPrice*nvl(d.acctAmount,0)) as retailMoney";
			groupByClause = "dept.unitsCode,c.billNo,c.billDate,m.deptCode,d.materialCode,d.materialName,d.materialSpec,d.materialUnits,d.tradePrice,d.wholeSalePrice,d.retailPrice";
		}
		String storageCode = (String) conditions.get("storageCode");
		String deptCode = (String) conditions.get("deptCode");
		String deptUnitsCode = (String) conditions.get("deptUnitsCode"); // 这里的unitsCode从前台条件中获取，而不是从session中
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		//
		String beginRdBillNo = (String) conditions.get("beginRdBillNo");
		String endRdBillNo = (String) conditions.get("endRdBillNo");

		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String beginMaterialClass = (String) conditions
				.get("beginMaterialClass");
		String endMaterialClass = (String) conditions.get("endMaterialClass");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		String materialCode = (String) conditions.get("materialCode");
		String accounter = (String) conditions.get("accounter");
		Date beginAccountDate = (Date) conditions.get("beginAccountDate");
		Date endAccountDate = (Date) conditions.get("endAccountDate");
		String specialSign = (String)conditions.get("specialSign");
		String commanSign = (String)conditions.get("commanSign");
		StringBuilder shql = new StringBuilder("select new map(")
				.append(selectClause)
				.append(
						") from MaterialRdsAcctMaster m,MaterialRdsDetail d,MaterialRdsMaster c,CdDeptDict dept where m.unitsCode = '"
								+ unitsCode
								+ "' and m.deptCode = dept.deptCode and m.autoId=d.acctBillNo and c.autoId = d.mainAutoId and m.deptCode = dept.deptCode ");
		shql.append(" and m.rdFlag='2'"); // 这里不需要加d.currentStatus=2，核算主记录的autoId已经与明细中的acct_bill_no关联了
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and m.storageCode=:storageCode");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and m.deptCode=:deptCode");
		}
		// 关联科室字典中的单位编码，针对领用的部门统计
		if (deptUnitsCode != null && !"".equals(deptUnitsCode)) {
			shql.append(" and dept.unitsCode=:deptUnitsCode");
		}
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and m.minRdBillNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and m.maxRdBillNo<=:endBillNo");
		}
		if (beginBillDate != null) {
			shql.append(" and m.billDate1>=:beginBillDate");
		}
		if (beginRdBillNo != null && !"".equals(beginRdBillNo)) {

			shql.append(" and substring(m.maxRdBillNo,length(m.maxRdBillNo)-7,8)>=:beginRdBillNo");
		}
		if (endRdBillNo != null && !"".equals(endRdBillNo)) {
			shql.append(" and substring(m.minRdBillNo,length(m.minRdBillNo)-7,8)<=:endRdBillNo");
		}
		if (endBillDate != null) {
			shql.append(" and m.billDate2<=:endBillDate");
		}
		if (beginMaterialClass != null && !"".equals(beginMaterialClass)) {
			shql.append(" and d.materialClass>=:beginMaterialClass");
		}
		if (endMaterialClass != null && !"".equals(endMaterialClass)) {
			shql.append(" and d.materialClass<=:endMaterialClass");
		}
		if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
			shql.append(" and d.materialCode>=:beginMaterialCode");
		}
		if (endMaterialCode != null && !"".equals(endMaterialCode)) {
			shql.append(" and d.materialCode<=:endMaterialCode");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and d.materialCode=:materialCode");
			conditions.put("materialCode", materialCode);
		}
		if(specialSign != null && !"".equals(specialSign) && "1".equals(specialSign)){
			shql.append(" and d.highValueSign = '1' and d.agentSign = '1'");
		}
		if(commanSign != null && !"".equals(commanSign) && "1".equals(commanSign)){
			shql.append(" and d.highValueSign = '1' and d.agentSign = '0'");
		}
		if (accounter != null && !"".equals(accounter)) {
			shql.append(" and m.accounter=:accounter");
		}
		
		// 调价日期
		if (beginAccountDate != null) {
			shql.append(" and m.accountDate >=:beginAccountDate");
		}
		if (endAccountDate != null) {
			shql.append(" and m.accountDate <=:endAccountDate");
		}
		
		shql.append(" group by ").append(groupByClause);// .append(" order by c.billDate asc");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	/**
	 * 核算入库物资汇总
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            参数包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始入库单号</li>
	 *            <li>endBillNo 结束入库单号</li>
	 *            <li>beginInvoiceDate 起始发票日期</li>
	 *            <li>endInvoiceDate 结束发票日期</li>
	 *            <li>salerCode 供应商</li>
	 *            <li>beginMaterialClass 起始物资分类</li>
	 *            <li>endMaterialClass 结束物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>accounter 记账人</li>
	 *            <li>beginAccountDate 起始记账日期</li>
	 *            <li>endAccountDate 结束记账日期</li>
	 *            <li>statFlag 汇总方式
	 *            <ul>
	 *            <li>1 按供应商</li>
	 *            <li>2 按物资编码</li>
	 *            <li>3 按供应商，物资编码</li>
	 *            </ul>
	 *            </li>
	 *            </ul>
	 * @return {@code List<Map<String,Object>>}，包含：
	 *         <ul>
	 *         <li>按供应商汇总方式返回：
	 *         <ul>
	 *         <li>salerCode 供应商</li>
	 *         <li>tradeMoney 进价金额</li>
	 *         <li>factTradeMoney 实价金额</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         <li>invoiceNo 发票号</li>
	 *         <li>invoiceDate 发票日期</li>
	 *         <li>accountDate 记账日期</li>
	 *         </ul>
	 *         </li>
	 *         <li>
	 *         按物资编码汇总方式返回：
	 *         <ul>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>tradeMoney 进价金额</li>
	 *         <li>factTradeMoney 实价金额</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         <li>invoiceNo 发票号</li>
	 *         <li>invoiceDate 发票日期</li>
	 *         <li>accountDate 记账日期</li>
	 *         </ul>
	 *         </li>
	 *         <li>
	 *         按供应商,物资编码汇总方式返回：
	 *         <ul>
	 *         <li>salerCode 供应商</li>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>tradeMoney 进价金额</li>
	 *         <li>factTradeMoney 实价金额</li>
	 *         <li>saleMoney 批发金额</li>
	 *         <li>retailMoney 零售金额</li>
	 *         <li>invoiceNo 发票号</li>
	 *         <li>invoiceDate 发票日期</li>
	 *         <li>accountDate 记账日期</li>
	 *         </ul>
	 *         </li>
	 *         </ul>
	 */
	@SuppressWarnings("unchecked")
	public List<Object> miMaxBillNo(String unitsCode, Map<String, Object> conditions) {

		String selectClause = null;
		{// d.rdBillNo as rdBillNo,d.invoiceDate as invoiceDate,
			selectClause = "max(d.rdBillNo) as iMax,min(d.rdBillNo) as iMin,max(c.billDate) as maxDate,min(c.billDate) as minBillDate";


			String storageCode = (String) conditions.get("storageCode");
			String salerCode = (String) conditions.get("salerCode");
			String beginBillNo = (String) conditions.get("beginBillNo");
			String endBillNo = (String) conditions.get("endBillNo");
			Date beginBillDate = (Date) conditions.get("beginBillDate");
			Date endBillDate = (Date) conditions.get("endBillDate");
			String beginRdBillNo = (String) conditions.get("beginRdBillNo");
			String endRdBillNo = (String) conditions.get("endRdBillNo");
			Date beginInvoiceDate = (Date) conditions.get("beginInvoiceDate");
			Date endInvoiceDate = (Date) conditions.get("endInvoiceDate");
			String beginMaterialClass = (String) conditions
					.get("beginMaterialClass");
			String endMaterialClass = (String) conditions
					.get("endMaterialClass");
			String beginMaterialCode = (String) conditions
					.get("beginMaterialCode");
			String endMaterialCode = (String) conditions.get("endMaterialCode");
			String materialCode = (String) conditions.get("materialCode");
			String accounter = (String) conditions.get("accounter");
			Date beginAccountDate = (Date) conditions.get("beginAccountDate");
			Date endAccountDate = (Date) conditions.get("endAccountDate");
			StringBuilder shql = new StringBuilder("select ")
					.append(selectClause)
					.append(
							" from MaterialRdsAcctMaster m,MaterialRdsDetail d,MaterialRdsMaster c where m.autoId=d.acctBillNo and c.autoId = d.mainAutoId and m.unitsCode='")
					.append(unitsCode).append("'");
			shql.append(" and m.rdFlag='1'");
			if (storageCode != null && !"".equals(storageCode)) {
				shql.append(" and m.storageCode=:storageCode");
			}
			if (salerCode != null && !"".equals(salerCode)) {
				shql.append(" and m.salerCode=:salerCode");
			}
			if (beginBillNo != null && !"".equals(beginBillNo)) {
				shql.append(" and m.minRdBillNo>=:beginBillNo");
			}
			if (endBillNo != null && !"".equals(endBillNo)) {
				shql.append(" and m.maxRdBillNo<=:endBillNo");
			}
			if (endBillDate != null) {
				shql.append(" and m.billDate2<=:endBillDate");
			}
			if (beginRdBillNo != null && !"".equals(beginRdBillNo)) {

				shql
						.append(" and substring(m.maxRdBillNo,length(m.maxRdBillNo)-7,8)>=:beginRdBillNo");
			}
			if (beginBillDate != null) {
				shql.append(" and m.billDate1>=:beginBillDate");
			}
			if (endRdBillNo != null && !"".equals(endRdBillNo)) {
				shql
						.append(" and substring(m.minRdBillNo,length(m.minRdBillNo)-7,8)<=:endRdBillNo");
			}
			if (beginInvoiceDate != null) {
				shql.append(" and d.invoiceDate>=:beginInvoiceDate");
			}
			if (endInvoiceDate != null) {
				shql.append(" and d.invoiceDate<=:endInvoiceDate");
			}
			if (beginMaterialClass != null && !"".equals(beginMaterialClass)) {
				shql.append(" and d.materialClass>=:beginMaterialClass");
			}
			if (endMaterialClass != null && !"".equals(endMaterialClass)) {
				shql.append(" and d.materialClass<=:endMaterialClass");
			}
			if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
				shql.append(" and d.materialCode>=:beginMaterialCode");
			}
			if (endMaterialCode != null && !"".equals(endMaterialCode)) {
				shql.append(" and d.materialCode<=:endMaterialCode");
			}
			if (materialCode != null && !"".equals(materialCode)) {
				shql.append(" and d.materialCode=:materialCode");
				conditions.put("materialCode", materialCode);
			}
			if (accounter != null && !"".equals(accounter)) {
				shql.append(" and m.accounter=:accounter");
			}
			// 调价日期
			if (beginAccountDate != null) {
				shql.append(" and m.accountDate >=:beginAccountDate");
			}
			if (endAccountDate != null) {
				shql.append(" and m.accountDate <=:endAccountDate");
			}
			return getSession().createQuery(shql.toString()).setProperties(
					conditions).list();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Object> addUpReceiveByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String statFlag = (String) conditions.get("statFlag");
		String selectClause = null;
		String groupByClause = null;
		if ("1".equals(statFlag)) {// d.rdBillNo as rdBillNo,d.invoiceDate as
			// invoiceDate,
			selectClause = "m.salerCode as salerCode,m.salerName as salerName, sum(d.tradePrice*nvl(d.acctAmount,0)) as tradeMoney,sum(d.factTradePrice*nvl(d.acctAmount,0)) as factTradeMoney,sum(d.wholeSalePrice*nvl(d.acctAmount,0)) as saleMoney,sum(d.retailPrice*nvl(d.acctAmount,0)) as retailMoney,sum(case when d.chargeSign = '1' then (d.wholeSalePrice * d.acctAmount) else 0 end) as saleMoney1,sum(case when d.chargeSign = '0' then (d.wholeSalePrice * d.acctAmount) else 0 end) as saleMoney0,sum(case when d.chargeSign = '1' then (d.retailPrice * d.acctAmount) else 0 end) as retailMoney1,sum(case when d.chargeSign = '0' then (d.retailPrice * d.acctAmount) else 0 end) as retailMoney0";
			groupByClause = "m.salerCode, m.salerName";
		} else if ("2".equals(statFlag)) { // d.invoiceNo as
			// invoiceNo,d.invoiceDate as
			// invoiceDate,m.accountDate as
			// accountDate,
			// d.rdBillNo as rdBillNo,d.invoiceDate as invoiceDate,
			selectClause = "d.materialCode as materialCode,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,d.tradePrice as tradePrice,d.wholeSalePrice as wholeSalePrice,d.retailPrice as retailPrice,"
					+ "sum(nvl(d.acctAmount,0)) as acctAmount,sum(d.tradePrice*nvl(d.acctAmount,0)) as tradeMoney,sum(d.factTradePrice*nvl(d.acctAmount,0)) as factTradeMoney,sum(d.wholeSalePrice*nvl(d.acctAmount,0)) as saleMoney,sum(d.retailPrice*nvl(d.acctAmount,0)) as retailMoney";
			groupByClause = "d.materialCode,d.materialName,d.materialSpec,d.materialUnits,d.tradePrice,d.wholeSalePrice,d.retailPrice";
		} else if ("3".equals(statFlag)) {
			selectClause = "d.rdBillNo as rdBillNo,m.accountDate as accountDate,m.salerCode as salerCode,m.salerName as salerName, d.materialCode as materialCode,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,d.tradePrice as tradePrice,d.wholeSalePrice as wholeSalePrice,d.retailPrice as retailPrice,"
					+ "sum(nvl(d.acctAmount,0)) as acctAmount,sum(d.tradePrice*nvl(d.acctAmount,0)) as tradeMoney,sum(d.factTradePrice*nvl(d.acctAmount,0)) as factTradeMoney,sum(d.wholeSalePrice*nvl(d.acctAmount,0)) as saleMoney,sum(d.retailPrice*nvl(d.acctAmount,0)) as retailMoney,d.invoiceNo as invoiceNo,d.invoiceDate as invoiceDate,m.accountDate as accountDate";
			groupByClause = "d.rdBillNo,m.accountDate,m.salerCode,m.salerName, d.materialCode,d.materialName,d.materialSpec,d.materialUnits,d.tradePrice,d.wholeSalePrice,d.retailPrice,d.invoiceNo,d.invoiceDate,m.accountDate";
		} else if ("4".equals(statFlag)) {
			selectClause = "d.rdBillNo as rdBillNo,m.accountDate as accountDate,d.materialCode as materialCode,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,d.tradePrice as tradePrice,d.wholeSalePrice as wholeSalePrice,d.retailPrice as retailPrice,"
					+ "sum(nvl(d.acctAmount,0)) as acctAmount,sum(d.tradePrice*nvl(d.acctAmount,0)) as tradeMoney,sum(d.factTradePrice*nvl(d.acctAmount,0)) as factTradeMoney,sum(d.wholeSalePrice*nvl(d.acctAmount,0)) as saleMoney,sum(d.retailPrice*nvl(d.acctAmount,0)) as retailMoney,d.invoiceNo as invoiceNo,d.invoiceDate as invoiceDate,m.accountDate as accountDate";
			groupByClause = "d.rdBillNo,m.accountDate, d.materialCode,d.materialName,d.materialSpec,d.materialUnits,d.tradePrice,d.wholeSalePrice,d.retailPrice,d.invoiceNo,d.invoiceDate,m.accountDate";
		}
		String storageCode = (String) conditions.get("storageCode");
		
		String salerCode = (String) conditions.get("salerCode");
		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");
		//
		String beginRdBillNo = (String) conditions.get("beginRdBillNo");
		String endRdBillNo = (String) conditions.get("endRdBillNo");
		Date beginInvoiceDate = (Date) conditions.get("beginInvoiceDate");
		Date endInvoiceDate = (Date) conditions.get("endInvoiceDate");
		String beginMaterialClass = (String) conditions
				.get("beginMaterialClass");
		String endMaterialClass = (String) conditions.get("endMaterialClass");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		String materialCode = (String) conditions.get("materialCode");
		String accounter = (String) conditions.get("accounter");
		Date beginAccountDate = (Date) conditions.get("beginAccountDate");
		Date endAccountDate = (Date) conditions.get("endAccountDate");
		String isGive=(String) conditions.get("isGive");
		StringBuilder shql = new StringBuilder("select new map(")
				.append(selectClause)
				.append(
						") from MaterialRdsAcctMaster m,MaterialRdsDetail d,MaterialRdsMaster c where m.autoId=d.acctBillNo and c.autoId = d.mainAutoId and m.unitsCode='")
				.append(unitsCode).append("'");
		shql.append(" and m.rdFlag='1'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and m.storageCode=:storageCode");
		}
		if (salerCode != null && !"".equals(salerCode)) {
			shql.append(" and m.salerCode=:salerCode");
		}
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and m.minRdBillNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and m.maxRdBillNo<=:endBillNo");
		}
		if (beginRdBillNo != null && !"".equals(beginRdBillNo)) {

			shql
					.append(" and substring(m.maxRdBillNo,length(m.maxRdBillNo)-7,8)>=:beginRdBillNo");
		}
		if (endRdBillNo != null && !"".equals(endRdBillNo)) {
			shql
					.append(" and substring(m.minRdBillNo,length(m.minRdBillNo)-7,8)<=:endRdBillNo");
		}
		if (beginInvoiceDate != null) {
			shql.append(" and d.invoiceDate>=:beginInvoiceDate");
		}
		if (endInvoiceDate != null) {
			shql.append(" and d.invoiceDate<=:endInvoiceDate");
		}
		if (beginMaterialClass != null && !"".equals(beginMaterialClass)) {
			shql.append(" and d.materialClass>=:beginMaterialClass");
		}
		if (endMaterialClass != null && !"".equals(endMaterialClass)) {
			shql.append(" and d.materialClass<=:endMaterialClass");
		}
		if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
			shql.append(" and d.materialCode>=:beginMaterialCode");
		}
		if (endMaterialCode != null && !"".equals(endMaterialCode)) {
			shql.append(" and d.materialCode<=:endMaterialCode");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and d.materialCode=:materialCode");
			conditions.put("materialCode", materialCode);
		}
		if (accounter != null && !"".equals(accounter)) {
			shql.append(" and m.accounter=:accounter");
		}
		if (beginAccountDate != null) {
			shql.append(" and m.accountDate>=:beginAccountDate");
		}
		if (endAccountDate != null) {
			shql.append(" and m.accountDate<=:endAccountDate");
		}
		if(isGive!=null && !isGive.equals("")){
			shql.append(" and d.isGive=:isGive");
		}
		shql.append(" group by ").append(groupByClause);// .append(" order by d.rdBillNo");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	public List<Object[]> findDistinctByFlag(String flag,String parentClass) {
		log.debug("select * from CD_ACCOUNT_CLASS_DICT p");
		try {
			String queryString = "select * from CD_ACCOUNT_CLASS_DICT p order by class_code" ;
			Query queryObject = getSession().createSQLQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Map addUpStorageReceiveByAccount(String unitsCode,
			Map<String, Object> conditions) {
		String rdFlag = (String) conditions.get("rdFlag");
		String storageCode = (String) conditions.get("storageCode");
		String salerCode = (String) conditions.get("salerCode");
		String beginBillDate = (String) conditions.get("beginBillDate");
		String endBillDate = (String) conditions.get("endBillDate");
		String deptUnitsCode = (String) conditions.get("deptUnitsCode"); //这里的unitsCode为前台传入
		String deptCode = (String) conditions.get("deptCode");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String parentClass = (String) conditions.get("parentClass");
//		int length = Integer.parseInt( conditions.get("length").toString()) ;
		String groupField = (String) conditions.get("groupField");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String beginAccDate = (String) conditions.get("beginAccountDate");
		String endAccDate = (String) conditions.get("endAccountDate");
		//查找会计分类字典中所有的数据
		List<Object[]> lstMaterialClass = findDistinctByFlag(rdFlag,parentClass);
		StringBuilder sb2 = new StringBuilder("");
		for (Object[] strClass : lstMaterialClass) {
			sb2.append(",sum(case when d.class_on_account = '"+strClass[0]+"' then( case when d.charge_sign = '1' then d.trade_money else 0 end) end) as "+'"'+strClass[1]+"0"+'"')
			.append(",sum(case when d.class_on_account = '"+strClass[0]+"' then( case when d.charge_sign = '0' then d.trade_money else 0 end) end) as "+'"'+strClass[1]+"1"+'"');
		}
		StringBuilder shql = new StringBuilder(
						"select material_class,d.material_code,d.material_name "+sb2+" from Material_Rds_Acct_Master m,Material_Rds_Detail d,Material_Rds_Master c where m.auto_Id=d.acct_Bill_No and c.auto_Id = d.main_Auto_Id");
			shql.append(" and m.rd_flag= '"+rdFlag+"' ");
		if (beginBillDate != null) {
			shql.append(" and c.bill_date >= to_date('" + beginBillDate
					+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endBillDate != null) {
			shql.append(" and c.bill_date <= to_date('" + endBillDate
					+ " 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (beginAccDate != null) {
			shql.append(" and c.account_date >= to_date('" + beginAccDate
					+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endAccDate != null) {
			shql.append(" and c.account_date <= to_date('" + endAccDate
					+ " 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')");
		}
//		if (deptUnitsCode != null && !"".equals(deptUnitsCode)) {
//			shql.append(" and m.dept_units_code=:deptUnitsCode");
//		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and m.dept_code=:deptCode");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and d.material_class=:materialClass");
		}
		shql.append(" group by d.material_class,d.material_code,d.material_name order by d.material_class,d.material_code");
		
		Map map = new HashMap<String,Object[]>();
		map.put("result",getSession().createSQLQuery(shql.toString()).setProperties(
				conditions).list());
		map.put("lstMaterialClass",lstMaterialClass);
		return map;
	}
	public Map addUpDeptRdsByAccount(String unitsCode,
			Map<String, Object> conditions) {
		String rdFlag = (String) conditions.get("rdFlag");
		String storageCode = (String) conditions.get("storageCode");
		String beginBillDate = (String) conditions.get("beginBillDate");
		String endBillDate = (String) conditions.get("endBillDate");
		String deptUnitsCode = (String) conditions.get("deptUnitsCode"); //这里的unitsCode为前台传入
		String deptCode = (String) conditions.get("deptCode");
		String operationType = (String) conditions.get("operationType");
		String rdType = (String) conditions.get("rdType");
		String parentClass = (String) conditions.get("parentClass");
//		int length = Integer.parseInt( conditions.get("length").toString()) ;
		String groupField = (String) conditions.get("groupField");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String beginAccDate = (String) conditions.get("beginAccountDate");
		String endAccDate = (String) conditions.get("endAccountDate");
		//查找会计分类字典中所有的数据
		List<Object[]> lstMaterialClass = findDistinctByFlag(rdFlag,parentClass);
		StringBuilder sb2 = new StringBuilder("");
		for (Object[] strClass : lstMaterialClass) {
			sb2.append(",sum(case when d.class_on_account = '"+strClass[0]+"' then( case when d.charge_sign = '1' then d.trade_money else 0 end) end) as "+'"'+strClass[1]+"0"+'"')
			.append(",sum(case when d.class_on_account = '"+strClass[0]+"' then( case when d.charge_sign = '0' then d.trade_money else 0 end) end) as "+'"'+strClass[1]+"1"+'"');
		}
		StringBuilder shql = new StringBuilder(
						"select m.dept_units_code,m.dept_code "+sb2+" from Material_Rds_Acct_Master m,Material_Rds_Detail d,Material_Rds_Master c where m.auto_Id=d.acct_Bill_No and c.auto_Id = d.main_Auto_Id ");
			shql.append(" and m.rd_flag= '2' ");
		if (beginBillDate != null) {
			shql.append(" and c.bill_date >= to_date('" + beginBillDate
					+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endBillDate != null) {
			shql.append(" and c.bill_date <= to_date('" + endBillDate
					+ " 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (beginAccDate != null) {
			shql.append(" and c.account_date >= to_date('" + beginAccDate
					+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endAccDate != null) {
			shql.append(" and c.account_date <= to_date('" + endAccDate
					+ " 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (deptUnitsCode != null && !"".equals(deptUnitsCode)) {
			shql.append(" and m.dept_units_code=:deptUnitsCode");
		}
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and m.dept_code=:deptCode");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and d.material_class=:materialClass");
		}
		shql.append(" group by m.dept_units_code,m.dept_code order by dept_code");
		
		Map map = new HashMap<String,Object[]>();
		map.put("result",getSession().createSQLQuery(shql.toString()).setProperties(
				conditions).list());
		map.put("lstMaterialClass",lstMaterialClass);
		return map;
	}
}