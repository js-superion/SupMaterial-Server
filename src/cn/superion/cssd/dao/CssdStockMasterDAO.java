package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdStockMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdStockMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdStockMasterDAO extends BaseHibernateDAO {
	private static final String PACKAGE_NO_RULE = "SEQ_CSSD_STOCK_MASTER";
	private static final Logger log = LoggerFactory
			.getLogger(CssdStockMasterDAO.class);

	/**
	 * 若物品包编号为空，则分配一个物品包编号
	 * @param instance
	 */
	private void assignPackageNo(CssdStockMaster instance){
		if(instance.getPackageNo() == null || "".equals(instance.getPackageNo())){
			instance.setPackageNo(this.nextValue(PACKAGE_NO_RULE).toString());
		}
	}
	
	public void save(CssdStockMaster transientInstance) {
		log.debug("saving CssdStockMaster instance");
		try {
			assignPackageNo(transientInstance);
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdStockMaster persistentInstance) {
		log.debug("deleting CssdStockMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdStockMaster findById(java.lang.String id) {
		log.debug("getting CssdStockMaster instance with id: " + id);
		try {
			CssdStockMaster instance = (CssdStockMaster) getSession().get(
					"cn.superion.cssd.entity.CssdStockMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdStockMaster instance) {
		log.debug("finding CssdStockMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdStockMaster").add(
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
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CssdStockMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdStockMaster as model where model."
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
	public List findAll() {
		log.debug("finding all CssdStockMaster instances");
		try {
			String queryString = "from CssdStockMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdStockMaster merge(CssdStockMaster detachedInstance) {
		log.debug("merging CssdStockMaster instance");
		try {
			CssdStockMaster result = (CssdStockMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdStockMaster instance) {
		log.debug("attaching dirty CssdStockMaster instance");
		try {
			assignPackageNo(instance);
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdStockMaster instance) {
		log.debug("attaching clean CssdStockMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findByPackageAutoId(
			String unitsCode, String packageAutoId) {
		String hql = "from CssdStockMaster where unitsCode=:unitsCode and packageAutoId=:packageAutoId order by packageSerialNo";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("packageAutoId", packageAutoId).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findDetachedByPackageAutoId(
			String unitsCode, String packageAutoId) {
		String hql = "from CssdStockMaster where unitsCode=:unitsCode and packageAutoId=:packageAutoId order by packageSerialNo";
		List<CssdStockMaster> list = getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("packageAutoId", packageAutoId).list();
		getSession().clear();
		return list;
	}

	public void delByPackageAutoId(String unitsCode, String packageAutoId) {
		String hql = "delete from CssdStockMaster where unitsCode=:unitsCode and packageAutoId=:packageAutoId";
		getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("packageAutoId", packageAutoId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findBySterilizeAutoId(String unitsCode,
			String sterilizeAutoId) {
		String hql = "from CssdStockMaster where unitsCode=:unitsCode and sterilizeAutoId=:sterilizeAutoId order by sterilizeSerialNo";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("sterilizeAutoId", sterilizeAutoId).list();
	}

	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findByDeliverAutoId(String unitsCode,
			String deliverAutoId) {
		String hql = "from CssdStockMaster where unitsCode=:unitsCode and deliverAutoId=:deliverAutoId order by deliverSerialNo";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("deliverAutoId", deliverAutoId).list();
	}
	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findByDeliverAutoIds(String unitsCode,
			String deliverAutoId) {
		String hqls="select distinct(c.package_Id), c.package_Name,c.amount from Cssd_Stock_Master c where units_Code='"+unitsCode+"'" +" and deliver_Auto_Id='"+ deliverAutoId+"'";
		return getSession().createSQLQuery(hqls).list();
	}

	public void deleteDeliverAutoId(String unitsCode,
			String deliverAutoId) {
		String hql = "delete CssdStockMaster where unitsCode=:unitsCode and deliverAutoId=:deliverAutoId ";
		getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("deliverAutoId", deliverAutoId).executeUpdate();
	}
	/**
	 * 
	 * @param start`
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含
	 *            <ul>
	 *            <li>packageClass 物品包分类</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginPackageId 起始物品包编码</li>
	 *            <li>endPackageId 结束物品包编码</li>
	 *            <li>beginAvailDate 起始有效期至</li>
	 *            <li>endAvailDate 结束有效期至</li>
	 *            <li>sterilizeStatus 灭菌状态</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findCurrentByCondition(int start,int limit,String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String packageClass = (String)conditions.get("packageClass");
		String packageId = (String)conditions.get("packageId");
		String beginPackageId = (String)conditions.get("beginPackageId");
		String endPackageId = (String)conditions.get("endPackageId");
		Date beginAvailDate = (Date)conditions.get("beginAvailDate");
		Date endAvailDate = (Date)conditions.get("endAvailDate");
		String sterilizeStatus = (String)conditions.get("sterilizeStatus");
		StringBuilder shql = new StringBuilder("  from CssdStockMaster t where current_Status='1' and units_Code='").append(unitsCode).append("'");
		String selSql="select   t.packageClass,t.packageId,t.packageName,t.packageMode,t.packageUnits,count(*)as amount,t.tradePrice,t.materialFee,t.sterilizeFee  ";
		if(packageClass != null && !"".equals(packageClass)){
			shql.append(" and packageClass=:packageClass");
		}
		if(packageId != null && !"".equals(packageId)){
			shql.append(" and packageId=:packageId");
		}else{
			if(beginPackageId != null && !"".equals(beginPackageId)){
				shql.append(" and packageId>=:beginPackageId");
			}
			if(endPackageId != null && !"".equals(endPackageId)){
				shql.append(" and packageId<=:endPackageId");
			}
		}
		if(beginAvailDate != null){
			shql.append(" and availDate>=:beginAvailDate");
		}
		if(endAvailDate != null){
			shql.append(" and availDate<=:endAvailDate");
		}
		if(sterilizeStatus != null && !"".equals(sterilizeStatus)){
			shql.append(" and sterilizeStatus=:sterilizeStatus");
		}
		Query countQuery = getSession().createQuery(selSql+shql.toString()+"  group by   t.packageClass,t.packageId, t.packageName,t.packageMode,t.packageUnits,t.tradePrice,t.materialFee,t.sterilizeFee");
//		Query query = getSession().createQuery(shql.toString());
//		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<Object> list = countQuery.setProperties(conditions).list();
//		objs[0] = count;
		objs[1] = list;
		return objs;
	}
	/**
	 * 
	 * @param packaeId
	 * @param deliverAoutId
	 * @return
	 */
	public int numberAmount(String packaeId,String deliverAoutId)
	{
		StringBuilder shql = new StringBuilder("from CssdStockMaster where packageId='").append(packaeId).append("'").append(" and deliverAutoId='").append(deliverAoutId).append("'");
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		int count = Integer.parseInt(countQuery.uniqueResult().toString());
		return count;
	}
	/**
	 * 查询在库的无菌包
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含
	 *            <ul>
	 *            <li>packageClass 物品包分类</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>minAmount 指定物品包编码的现存量下限</li>
	 *            <li>maxAmount 指定物品包编码的现存量上限</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findCurrentByAmountCondition(int start,int limit,String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		conditions.put("unitsCode", unitsCode);
		String packageClass = (String)conditions.get("packageClass");
		String packageId = (String)conditions.get("packageId");
		Double minAmount = conditions.get("minAmount")==null?null:Double.valueOf(conditions.get("minAmount").toString());
		Double maxAmount = conditions.get("maxAmount")==null?null:Double.valueOf(conditions.get("maxAmount").toString());
		StringBuilder sCondt = new StringBuilder(" where currentStatus='1' and unitsCode=:unitsCode");
		if(packageClass != null && !"".equals(packageClass)){
			sCondt.append(" and packageClass=:packageClass");
		}
		if(packageId != null && !"".equals(packageId)){
			sCondt.append(" and packageId=:packageId");
		}
		StringBuilder shql = new StringBuilder("from CssdStockMaster").append(sCondt);
		if(minAmount != null){
			shql.append(" and (select sum(amount) from CssdStockMaster").append(sCondt).append(")>=:minAmount");
			conditions.put("minAmount", minAmount);
		}
		if(maxAmount != null){
			shql.append(" and (select sum(amount) from CssdStockMaster").append(sCondt).append(")<=:maxAmount");
			conditions.put("maxAmount", maxAmount);
		}
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<CssdStockMaster> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginPackageId 起始物品包编码</li>
	 *            <li>endPackageId 结束物品包编码</li>
	 *            <li>beginPackageNo 起始物品包编号</li>
	 *            <li>endPackageNo 结束物品包编号</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>overdueNum 过期天数</li>
	 *            <li>anearNum 临近天数</li>
	 *            <li>sterilizeStatus 灭菌状态</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
	 *            条件过期天数，临近天数和失效日期范围是互斥条件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String packageClass = (String)conditions.get("packageClass");
		String packageId = (String)conditions.get("packageId");
		String beginPackageId = (String)conditions.get("beginPackageId");
		String endPackageId = (String)conditions.get("endPackageId");
		String beginPackageNo = (String)conditions.get("beginPackageNo");
		String endPackageNo = (String)conditions.get("endPackageNo");
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		Integer overdueNum = (Integer) conditions.get("overdueNum");
		Integer anearNum = (Integer) conditions.get("anearNum");
		String sterilizeStatus = (String)conditions.get("sterilizeStatus");
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("from CssdStockMaster where unitsCode='").append(unitsCode).append("'");
		if(packageClass != null && !"".equals(packageClass)){
			shql.append(" and packageClass=:packageClass");
		}
		if(packageId != null && !"".equals(packageId)){
			shql.append(" and packageId=:packageId");
		}else{
			if(beginPackageId != null && !"".equals(beginPackageId)){
				shql.append(" and packageId>=:beginPackageId");
			}
			if(endPackageId != null && !"".equals(endPackageId)){
				shql.append(" and packageId<=:endPackageId");
			}
		}
		if(beginPackageNo != null && !"".equals(beginPackageNo)){
			shql.append(" and packageNo>=:beginPackageNo");
		}
		if(endPackageNo != null && !"".equals(endPackageNo)){
			shql.append(" and packageNo<=:endPackageNo");
		}
		if (overdueNum != null) {
			shql
					.append(" and availDate>=trunc(sysdate)-").append(overdueNum).append(" and availDate<trunc(sysdate)+1-").append(overdueNum);
		} else if (anearNum != null) {
			shql
					.append(" and availDate>=trunc(sysdate)+").append(anearNum).append(" and availDate<trunc(sysdate)+1+").append(anearNum);
		} else {
			if (beginAvailDate != null) {
				shql.append(" and availDate>=:beginAvailDate");
			}
			if (endAvailDate != null) {
				shql.append(" and availDate<=:endAvailDate");
			}
		}
		if(sterilizeStatus != null && !"".equals(sterilizeStatus)){
			shql.append(" and sterilizeStatus=:sterilizeStatus");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<CssdStockMaster> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
	
	/**
	 * 过滤已打包审核的物品包列表
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>billNo 单据编号</li>
	 *            <li>beginBillDate 起始打包日期</li>
	 *            <li>endBillDate 结束打包日期</li>
	 *            <li>sterilizeNo 灭菌锅号</li>
	 *            <li>sterilizeOrder 灭菌锅次</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findPackedByCondition(int start, int limit,
			String unitsCode, Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String sterilizeNo = (String)conditions.get("sterilizeNo");
		String sterilizeOrder = (String)conditions.get("sterilizeOrder");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String billNo = (String)conditions.get("billNo");
		StringBuilder shql = new StringBuilder("from CssdStockMaster s where currentStatus='0' and unitsCode='").append(unitsCode).append("'");
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		if(sterilizeNo != null && !"".equals(sterilizeNo)){
			shql.append(" and sterilizeNo=:sterilizeNo");
		}
		if(sterilizeOrder != null && !"".equals(sterilizeOrder)){
			shql.append(" and sterilizeOrder=:sterilizeOrder");
		}
		StringBuilder shql2 = new StringBuilder("from CssdPackageMaster p where p.unitsCode=s.unitsCode and p.autoId=s.packageAutoId and p.currentStatus='1'");
		if(beginBillDate != null){
			shql2.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql2.append(" and billDate<=:endBillDate");
		}
		if(billNo != null && !"".equals(billNo)){
			shql2.append(" and billNo=:billNo");
		}
		shql.append(" and exists(").append(shql2).append(")");
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<CssdStockMaster> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 根据物品包编号查询已打包并审核的物品包信息，物品包灭菌时调用
	 * @param unitsCode
	 * @param packageNo
	 * @return
	 */
	public CssdStockMaster findPackedById(String unitsCode,String packageNo) {
		String hql = "from CssdStockMaster s where unitsCode=:unitsCode and packageNo=:packageNo and currentStatus='0' and exists(from CssdPackageMaster p where p.unitsCode=s.unitsCode and s.packageAutoId=p.autoId and p.currentStatus='1')";
		return (CssdStockMaster) getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("packageNo", packageNo).uniqueResult();
	}
	
	
	/**
	 * 根据物品包拼音码或五笔码查询已打包并审核的物品包信息，物品包灭菌时调用
	 * @param unitsCode
	 * @param inputCode 输入码
	 * @param isPho 是否拼音码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findPackedByInputCode(String unitsCode,
			String inputCode, boolean isPho) {
		String hql = "from CssdStockMaster s where unitsCode=:unitsCode and currentStatus='0' and exists(from CssdPackageMaster p where p.unitsCode=s.unitsCode and p.autoId=s.packageAutoId and p.currentStatus='1') and "
			+ (isPho ? "phoInputCode" : "fiveInputCode")
			+ " like :inputCode";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("inputCode", inputCode+"%").list();
	}
	
	/**
	 * 根据物品包编号查询已灭菌并审核的物品包信息，物品包发放时调用
	 * @param unitsCode
	 * @param packageNo
	 * @return
	 */
	public CssdStockMaster findSterilizedById(String unitsCode,String packageNo) {
		String hql = "from CssdStockMaster s where unitsCode=:unitsCode and packageNo=:packageNo and currentStatus='1' and exists(from CssdSterilizeMaster p where p.unitsCode=s.unitsCode and s.sterilizeAutoId=p.autoId and p.currentStatus='1')";
		return (CssdStockMaster) getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("packageNo", packageNo).uniqueResult();
	}
	
	/**
	 * 根据物品包拼音码或五笔码查询已灭菌并审核的物品包信息，物品包发放时调用
	 * @param unitsCode
	 * @param inputCode 输入码
	 * @param isPho 是否拼音码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findSterilizedByInputCode(String unitsCode,
			String inputCode, boolean isPho) {
		String hql = "from CssdStockMaster s where unitsCode=:unitsCode and currentStatus='1' and exists(from CssdSterilizeMaster p where p.unitsCode=s.unitsCode and s.sterilizeAutoId=p.autoId and p.currentStatus='1') and "
			+ (isPho ? "phoInputCode" : "fiveInputCode")
			+ " like :inputCode";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("inputCode", inputCode+"%").list();
	}
	
	/**
	 * 根据物品包编码（ID）查询已灭菌并审核的物品包信息，物品包发放时调用
	 * @param unitsCode
	 * @param packageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findSterilizedByPackageId(String unitsCode,
			String packageId) {
		String hql = "from CssdStockMaster s where unitsCode=:unitsCode and currentStatus='1' and addSign is null and packageId=:packageId and exists(from CssdSterilizeMaster p where p.unitsCode=s.unitsCode and s.sterilizeAutoId=p.autoId and p.currentStatus='1') order by availDate";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("packageId", packageId).list();
	}
	
	public CssdStockMaster findById(String packageNo,String currentStatus){
		String hql = "from CssdStockMaster where packageNo=:packageNo and currentStatus=:currentStatus";
		return (CssdStockMaster) getSession().createQuery(hql).setString("packageNo", packageNo).setString("currentStatus", currentStatus).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Object> findSterilizedByPackageId(String unitsCode,String packageId,String deptCode)
	{
		StringBuilder shql = new StringBuilder(
				"select new map(csm.packageNo as packageNo,csm.unitsCode as unitsCode,csm.deptUnitsCode as deptUnitsCode,csm.deptUnitsName as deptUnitsName,csm.packageClass as packageClass,csm.packageId as packageId,csm.packageName as packageName,csm.packageMode as packageMode,csm.packageUnits as packageUnits,csm.tradePrice as tradePrice,csm.amount as amount,csm.materialFee as materialFee,csm.sterilizeFee as sterilizeFee,csm.availDate as availDate,csm.packager as packager,csm.sterilizeNo as sterilizeNo,csm.sterilizeOrder as sterilizeOrder,csm.sterilizeType as sterilizeType,csm.sterilizeDate as sterilizeDate,csm.sterilizeStatus as sterilizeStatus,csm.detailRemark as detailRemark,csm.phoInputCode as phoInputCode,csm.fiveInputCode as fiveInputCode,csm.usedSign as usedSign,csm.currentStatus as currentStatus,csm.packageAutoId as packageAutoId,csm.packageSerialNo as packageSerialNo,csm.sterilizeAutoId as sterilizeAutoId,csm.sterilizeSerialNo as sterilizeSerialNo,csm.deliverAutoId as deliverAutoId,csm.deliverSerialNo as deliverSerialNo,csm.provideAutoId as provideAutoId,csm.packageMode as packageMode, csm.packageUnits as packageUnits) from CssdStockMaster csm, CssdProvideMaster cr where csm.unitsCode='"
						+ unitsCode
						+ "' and csm.registerStatus='0' and csm.currentStatus='2' and csm.registerStatus='0' and cr.deptCode='"
						+ deptCode
						+ "' and csm.provideAutoId=cr.autoId and csm.packageId='"
						+ packageId + "'");
		return getSession().createQuery(shql.toString()).list();
	}
	
	/**
	 * 
	 *  科室可用物品包
	 */
	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findSterilizedByProvideAutoId(String deptCode,String unitsCode)
	{
		String sql="select csm.* from  CSSD_STOCK_MASTER csm,CSSD_PROVIDE_MASTER crm where csm.units_Code="+unitsCode+"and crm.DEPT_CODE="+deptCode+"and crm.current_status='3' and csm.provide_auto_id=crm.auto_id and csm.current_status='2'";
		return getSession().createSQLQuery(sql).list();
	}


	
	@SuppressWarnings("unchecked")
	public List<String> findStockMasterStatu(String unitsCode,
			Map<String, Object> conditions) {
		String inpNo = (String)conditions.get("inpNo");
		Date beginRegistere = (Date)conditions.get("beginRegistere");
		Date endRegistere = (Date)conditions.get("endRegistere");
		String registerStatus = (String)conditions.get("registerStatus");
		
		StringBuilder shql = new StringBuilder(" select distinct(t.inpNo) as inpNo from CssdStockMaster t where current_Status='2' and units_Code='").append(unitsCode).append("'");
		if(inpNo != null && !"".equals(inpNo)){
			shql.append(" and inpNo=:inpNo");
		}
		if(beginRegistere != null){
			shql.append(" and registeDate>=:beginRegistere");
		}
		if(endRegistere != null){
			shql.append(" and registeDate<=:endRegistere");
		}
		if(registerStatus != null && !"".equals(registerStatus)){
			shql.append(" and registerStatus=:registerStatus");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findStockMasterInpNo(String unitsCode,
			Map<String, Object> conditions) {
		String inpNo = (String)conditions.get("inpNo");
		Date beginRegistere = (Date)conditions.get("beginRegistere");
		Date endRegistere = (Date)conditions.get("endRegistere");
		String registerStatus = (String)conditions.get("registerStatus");
		
		StringBuilder shql = new StringBuilder("from CssdStockMaster t where current_Status='2' and units_Code='"+ unitsCode +"'");
		if(inpNo != null && !"".equals(inpNo)){
			shql.append(" and inpNo=:inpNo");
		}
		if(beginRegistere != null){
			shql.append(" and registeDate>=:beginRegistere");
		}
		if(endRegistere != null){
			shql.append(" and registeDate<=:endRegistere");
		}
		if(registerStatus != null && !"".equals(registerStatus)){
			shql.append(" and registerStatus=:registerStatus");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}
	
	@SuppressWarnings("unchecked")
	public CssdStockMaster findStockMasterPackageNo(String packageNo) 
	{
		String sql="from CssdStockMaster where currentStatus='1' and packageNo='"+ packageNo +"'";
		Query q = getSession().createQuery(sql.toString());
		List lst = q.list();
		CssdStockMaster mas = null;
		if(lst.size() > 0){
			mas = (CssdStockMaster) lst.get(0);
			mas.setAmount(1.00);
			return mas;
		}
		return null;  
	}

}