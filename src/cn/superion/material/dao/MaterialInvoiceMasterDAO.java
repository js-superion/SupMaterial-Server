package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialInvoiceMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialInvoiceMaster entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialInvoiceMaster
 * @author MyEclipse Persistence Tools
 */

public class MaterialInvoiceMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialInvoiceMasterDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String BILL_NO = "billNo";
	public static final String OPERATION_TYPE = "operationType";
	public static final String STOCK_TYPE = "stockType";
	public static final String INVOICE_TYPE = "invoiceType";
	public static final String INVOICE_NO = "invoiceNo";
	public static final String SALER_CODE = "salerCode";
	public static final String SALER_NAME = "salerName";
	public static final String DEPT_CODE = "deptCode";
	public static final String PERSON_ID = "personId";
	public static final String PAY_CONDITION = "payCondition";
	public static final String TOTAL_COSTS = "totalCosts";
	public static final String REMARK = "remark";
	public static final String MAKER = "maker";
	public static final String VERIFIER = "verifier";
	public static final String ACCOUNTER = "accounter";
	public static final String CURRENT_STATUS = "currentStatus";

	public void save(MaterialInvoiceMaster transientInstance) {
		log.debug("saving MaterialInvoiceMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialInvoiceMaster persistentInstance) {
		log.debug("deleting MaterialInvoiceMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialInvoiceMaster findById(java.lang.String id) {
		log.debug("getting MaterialInvoiceMaster instance with id: " + id);
		try {
			MaterialInvoiceMaster instance = (MaterialInvoiceMaster) getSession()
					.get("cn.superion.material.entity.MaterialInvoiceMaster",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialInvoiceMaster> findByExample(
			MaterialInvoiceMaster instance) {
		log.debug("finding MaterialInvoiceMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialInvoiceMaster").add(
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
	public List<MaterialInvoiceMaster> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialInvoiceMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialInvoiceMaster as model where model."
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
	public List<MaterialInvoiceMaster> findAll() {
		log.debug("finding all MaterialInvoiceMaster instances");
		try {
			String queryString = "from MaterialInvoiceMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialInvoiceMaster merge(MaterialInvoiceMaster detachedInstance) {
		log.debug("merging MaterialInvoiceMaster instance");
		try {
			MaterialInvoiceMaster result = (MaterialInvoiceMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialInvoiceMaster instance) {
		log.debug("attaching dirty MaterialInvoiceMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialInvoiceMaster instance) {
		log.debug("attaching clean MaterialInvoiceMaster instance");
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
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>billType 单据类型(蓝字，红字)</li>
	 *            <li>invoiceType 发票类型</li>
	 *            <li>beginInvoiceNo 起始发票号</li>
	 *            <li>endInvoiceNo 结束发票号</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>beginInvoiceDate 起始发票日期</li>
	 *            <li>endInvoiceDate 结束发票日期</li>
	 *            <li>deptCode 部门</li>
	 *            <li>currentStatus 当前状态</li>
	 *            以下为从表条件
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginTradePrice 最小进价</li>
	 *            <li>endTradePrice 最大进价</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String operationType = (String)conditions.get("operationType");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		String billType = (String)conditions.get("billType");
		String invoiceType = (String)conditions.get("invoiceType");
		String beginInvoiceNo = (String)conditions.get("beginInvoiceNo");
		String endInvoiceNo = (String)conditions.get("endInvoiceNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		Date beginInvoiceDate = (Date)conditions.get("beginInvoiceDate");
		Date endInvoiceDate = (Date)conditions.get("endInvoiceDate");
		String deptCode = (String)conditions.get("deptCode");
		String materialCode = (String)conditions.get("materialCode");
		Double beginTradePrice= conditions.get("beginTradePrice")==null?null:Double.valueOf(conditions.get("beginTradePrice").toString());
		Double endTradePrice= conditions.get("endTradePrice")==null?null:Double.valueOf(conditions.get("endTradePrice").toString());
		String currentStatus = (String)conditions.get("currentStatus");
		//主表条件
		StringBuilder shql = new StringBuilder("select autoId from MaterialInvoiceMaster m where unitsCode='").append(unitsCode).append("'");
		if(operationType != null && !"".equals(operationType)){
			shql.append(" and operationType=:operationType");
		}
		if(beginBillNo != null && !"".equals(beginBillNo)){
			shql.append(" and billNo>=:beginBillNo");
		}
		if(endBillNo != null && !"".equals(endBillNo)){
			shql.append(" and billNo<=:endBillNo");
		}
		if(billType != null && !"".equals(billType)){
			shql.append(" and billType=:billType");
		}
		if(invoiceType != null && !"".equals(invoiceType)){
			shql.append(" and invoiceType=:invoiceType");
		}
		if(beginInvoiceNo != null && !"".equals(beginInvoiceNo)){
			shql.append(" and invoiceNo>=:beginInvoiceNo");
		}
		if(endInvoiceNo != null && !"".equals(endInvoiceNo)){
			shql.append(" and invoiceNo<=:endInvoiceNo");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(beginInvoiceDate != null){
			shql.append(" and invoiceDate>=:beginInvoiceDate");
		}
		if(endInvoiceDate != null){
			shql.append(" and invoiceDate<=:endInvoiceDate");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialInvoiceDetail d where d.mainAutoId=m.autoId");
		if(materialCode != null && !"".equals(materialCode)){
			flag = true;
			shql2.append(" and d.materialCode=:materialCode");
		}
		if(beginTradePrice != null){
			flag = true;
			shql2.append(" and d.tradePrice>=:beginTradePrice");
			conditions.put("beginTradePrice", beginTradePrice);
		}
		if(endTradePrice != null){
			flag = true;
			shql2.append(" and d.tradePrice<=:endTradePrice");
			conditions.put("endTradePrice", endTradePrice);
		}
		if(flag){
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String storageCode,
			String billNo) {
		String hql = "select count(*) from MaterialInvoiceMaster where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}

	public void cleanSettleInfo(String unitsCode,String settleAutoId) {
		String hql = "update MaterialInvoiceMaster i set currentStatus='1',accounter=null,accountDate=null where i.unitsCode=:unitsCode and exists(from MaterialFaInvoice f where f.mainAutoId=:settleAutoId and i.autoId=f.invoiceAutoId)";
		getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("settleAutoId", settleAutoId).executeUpdate();
	}
	
	public void cleanSettleInfoByInvoiceId(String unitsCode,String invoiceId){
		String hql = "update MaterialInvoiceMaster i set currentStatus='1',accounter=null,accountDate=null where i.unitsCode=:unitsCode and i.autoId=:autoId";
		getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("autoId", invoiceId).executeUpdate();
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>beginInvoiceDate 起始发票日期</li>
	 *            <li>endInvoiceDate 结束发票日期</li>
	 *            <li>beginInvoiceNo 起始发票号</li>
	 *            <li>endInvoiceNo 结束发票号</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialInvoiceMaster> findByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String salerCode = (String)conditions.get("salerCode");
		String beginInvoiceNo = (String)conditions.get("beginInvoiceNo");
		String endInvoiceNo = (String)conditions.get("endInvoiceNo");
		Date beginInvoiceDate = (Date)conditions.get("beginInvoiceDate");
		Date endInvoiceDate = (Date)conditions.get("endInvoiceDate");
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("from MaterialInvoiceMaster where unitsCode='").append(unitsCode).append("'");
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and salerCode=:salerCode");
		}
		if(beginInvoiceNo != null && !"".equals(beginInvoiceNo)){
			shql.append(" and invoiceNo>=:beginInvoiceNo");
		}
		if(endInvoiceNo != null && !"".equals(endInvoiceNo)){
			shql.append(" and invoiceNo<=:endInvoiceNo");
		}
		if(beginInvoiceDate != null){
			shql.append(" and invoiceDate>=:beginInvoiceDate");
		}
		if(endInvoiceDate != null){
			shql.append(" and invoiceDate<=:endInvoiceDate");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}
}