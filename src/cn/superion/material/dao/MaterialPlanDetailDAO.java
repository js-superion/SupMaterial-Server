package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialPlanDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialPlanDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialPlanDetail
 * @author MyEclipse Persistence Tools
 */

public class MaterialPlanDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialPlanDetailDAO.class);
	// property constants
	public static final String MAIN_AUTO_ID = "mainAutoId";
	public static final String SERIAL_NO = "serialNo";
	public static final String MATERIAL_CLASS = "materialClass";
	public static final String MATERIAL_ID = "materialId";
	public static final String MATERIAL_CODE = "materialCode";
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_SPEC = "materialSpec";
	public static final String MATERIAL_UNITS = "materialUnits";
	public static final String AMOUNT = "amount";
	public static final String TRADE_PRICE = "tradePrice";
	public static final String TRADE_MONEY = "tradeMoney";
	public static final String RETAIL_PRICE = "retailPrice";
	public static final String RETAIL_MONEY = "retailMoney";
	public static final String CURRENT_STOCK_AMOUNT = "currentStockAmount";
	public static final String TOTAL_CURRENT_STOCK_AMOUNT = "totalCurrentStockAmount";
	public static final String SALER_CODE = "salerCode";
	public static final String SALER_NAME = "salerName";
	public static final String ORDER_AMOUNT = "orderAmount";
	public static final String CURRENT_STATUS = "currentStatus";
	public static final String DETAIL_REMARK = "detailRemark";

	/**
	 * 审核明细记录
	 * 
	 * @param mainAutoId
	 */
	public void verify(String mainAutoId) {
		getSession()
				.createQuery(
						"update MaterialPlanDetail set currentStatus='1' where mainAutoId=:mainAutoId")
				.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	public List<MaterialPlanDetail> findByMainAutoId(String mainAutoId) {
		return findByProperty("mainAutoId", mainAutoId);
	}

	/**
	 * 删除明细记录
	 * 
	 * @param mainAutoId
	 */
	public void delByMainAutoId(String mainAutoId) {
		getSession().createQuery(
				"delete from MaterialPlanDetail where mainAutoId=:mainAutoId")
				.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	/**
	 * 校验采购计划明细是否已使用（审核，执行，关闭），有已使用明细记录的单据不能删除
	 * 
	 * @param mainAutoId
	 * @return
	 */
	public boolean checkUsedStatus(String mainAutoId) {
		String hql = "select count(*) from MaterialPlanDetail where mainAutoId=:mainAutoId and currentStatus<>'0'";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"mainAutoId", mainAutoId).uniqueResult().toString());
		return cnt > 0;
	}
	/**
	 * 查询明细-----汇总记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findDetailsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String dataSource = (String)conditions.get("dataSource");
		String operationType = (String)conditions.get("operationType");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		Date beginAdviceBookDate = (Date)conditions.get("beginAdviceBookDate");
		Date endAdviceBookDate = (Date)conditions.get("endAdviceBookDate");
		String beginMaterialCode = (String)conditions.get("beginMaterialCode");
		String endMaterialCode = (String)conditions.get("endMaterialCode");
		String salerCode = (String)conditions.get("salerCode");
		String currentStatus = (String)conditions.get("currentStatus");
		//主表条件
//		StringBuilder shql = new StringBuilder("select autoId from MaterialPlanMaster m where unitsCode='").append(unitsCode).append("'");
//		if(dataSource != null && !"".equals(dataSource)){
//			shql.append(" and dataSource=:dataSource");
//		}
//		if(operationType != null && !"".equals(operationType)){
//			shql.append(" and operationType=:operationType");
//		}
//		if(beginBillNo != null && !"".equals(beginBillNo)){
//			shql.append(" and billNo>=:beginBillNo");
//		}
//		if(endBillNo != null && !"".equals(endBillNo)){
//			shql.append(" and billNo<=:endBillNo");
//		}
//		if(beginBillDate != null){
//			shql.append(" and billDate>=:beginBillDate");
//		}
//		if(endBillDate != null){
//			shql.append(" and billDate<=:endBillDate");
//		}
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialPlanDetail d where d.mainAutoId in " +
				"(select t.autoId from MaterialPlanMaster t where unitsCode='").append(unitsCode).append("'");
		if(beginBillDate != null){
			shql2.append(" and t.makeDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql2.append(" and t.makeDate<=:endBillDate");
		}
		if(beginAdviceBookDate != null){
			flag = true;
			shql2.append(" and t.verifyDate>=:beginAdviceBookDate");
		}
		if(endAdviceBookDate != null){
			flag = true;
			shql2.append(" and t.verifyDate<=:endAdviceBookDate");
		}
//		if(beginMaterialCode != null && !"".equals(beginMaterialCode)){
//			flag = true;
//			shql2.append(" and d.materialCode>=:beginMaterialCode");
//		}
//		if(endMaterialCode != null && !"".equals(endMaterialCode)){
//			flag = true;
//			shql2.append(" and d.materialCode<=:endMaterialCode");
//		}
//		if(salerCode != null && !"".equals(salerCode)){
//			flag = true;
//			shql2.append(" and d.salerCode=:salerCode");
//		}
		shql2.append(")");
		if(currentStatus != null && !"".equals(currentStatus)){
			shql2.append(" and d.currentStatus=:currentStatus");
		}

		return getSession().createQuery(shql2.toString()).setProperties(conditions).list();
	}

	public void save(MaterialPlanDetail transientInstance) {
		log.debug("saving MaterialPlanDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialPlanDetail persistentInstance) {
		log.debug("deleting MaterialPlanDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialPlanDetail findById(java.lang.String id) {
		log.debug("getting MaterialPlanDetail instance with id: " + id);
		try {
			MaterialPlanDetail instance = (MaterialPlanDetail) getSession()
					.get("cn.superion.material.entity.MaterialPlanDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialPlanDetail> findByExample(MaterialPlanDetail instance) {
		log.debug("finding MaterialPlanDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialPlanDetail").add(
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
	public List<MaterialPlanDetail> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialPlanDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialPlanDetail as model where model."
					+ propertyName + "= ? order by model.salerCode ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialPlanDetail> findAll() {
		log.debug("finding all MaterialPlanDetail instances");
		try {
			String queryString = "from MaterialPlanDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialPlanDetail merge(MaterialPlanDetail detachedInstance) {
		log.debug("merging MaterialPlanDetail instance");
		try {
			MaterialPlanDetail result = (MaterialPlanDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialPlanDetail instance) {
		log.debug("attaching dirty MaterialPlanDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialPlanDetail instance) {
		log.debug("attaching clean MaterialPlanDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public MaterialPlanDetail findByMainAutoIdAndSerialNo(
			String mainAutoId, Short serialNo) {
		String hql = "from MaterialPlanDetail where mainAutoId=:mainAutoId and serialNo=:serialNo";
		return (MaterialPlanDetail) getSession().createQuery(hql).setString("mainAutoId", mainAutoId).setShort("serialNo", serialNo).uniqueResult();
	}
}