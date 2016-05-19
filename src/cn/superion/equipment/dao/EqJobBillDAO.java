package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqJobBill;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqJobBill entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqJobBill
 * @author MyEclipse Persistence Tools
 */

public class EqJobBillDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqJobBillDAO.class);

	public void save(EqJobBill transientInstance) {
		log.debug("saving EqJobBill instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqJobBill persistentInstance) {
		log.debug("deleting EqJobBill instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EqJobBill findById(java.lang.String id) {
		log.debug("getting EqJobBill instance with id: " + id);
		try {
			EqJobBill instance = (EqJobBill) getSession().get(
					"cn.superion.equipment.entity.EqJobBill", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqJobBill> findByExample(EqJobBill instance) {
		log.debug("finding EqJobBill instance by example");
		try {
			List<EqJobBill> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqJobBill").add(
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
	public List<EqJobBill> findByProperty(String propertyName, Object value) {
		log.debug("finding EqJobBill instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EqJobBill as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqJobBill merge(EqJobBill detachedInstance) {
		log.debug("merging EqJobBill instance");
		try {
			EqJobBill result = (EqJobBill) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqJobBill instance) {
		log.debug("attaching dirty EqJobBill instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqJobBill instance) {
		log.debug("attaching clean EqJobBill instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public boolean checkJobBillNoUnique(String unitsCode, String jobBillNo) {
		String hql = "select count(*) from EqJobBill where unitsCode=:unitsCode and jobBillNo=:jobBillNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("jobBillNo", jobBillNo)
				.uniqueResult().toString());
		return cnt == 0;
	}

	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String hql = "select autoId " + parseCondition(unitsCode,conditions);
		return getSession().createQuery(hql).setProperties(
				conditions).list();
	}

	@SuppressWarnings("unchecked")
	public Object[] findListByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String hql = parseCondition(unitsCode,conditions);
		Query countQuery = getSession().createQuery("select count(*) "+hql);
		Query query = getSession().createQuery(hql);
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<EqJobBill> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
	
	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>beginJobBillNo 起始作业单号</li>
	 *            <li>endJobBillNo 结束作业单号</li>
	 *            <li>beginJobPlanNo 起始作业计划单号</li>
	 *            <li>endJobPlanNo 结束作业计划单号</li>
	 *            <li>beginJobCode 起始作业编码</li>
	 *            <li>endJobCode 结束作业编码</li>
	 *            <li>beginJobType 起始作业类型编码</li>
	 *            <li>endJobType 结束作业类型编码</li>
	 *            <li>objectType 对象类型</li>
	 *            <li>beginObjectCode 起始位置/设备编码</li>
	 *            <li>endObjectCode 结束位置/设备编码</li>
	 *            <li>beginPlanStartDate 起始计划开始日期</li>
	 *            <li>endPlanStartDate 结束计划开始日期</li>
	 *            <li>beginPlanEndDate 起始计划终止日期</li>
	 *            <li>endPlanEndDate 结束计划终止日期</li>
	 *            <li>beginFactStartDate 起始实际开始日期</li>
	 *            <li>endFactStartDate 结束实际开始日期</li>
	 *            <li>beginFactEndDate 起始实际终止日期</li>
	 *            <li>endFactEndDate 结束实际终止日期</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>jobDept 作业部门</li>
	 *            <li>maker 制单人</li>
	 *            <li>jobSign 作业标识</li>
	 *            <li>beginFactFee 开始计划费用</li>
	 *            <li>endFactFee 开始计划费用</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            <li>verifier 审核人</li>
	 *            <li>beginVerifyDate 起始审核日期</li>
	 *            <li>endVerifyDate 结束审核日期</li>
	 *            设备台帐条件
	 *            <li>beginEquipmentType 起始设备类型编码</li>
	 *            <li>endEquipmentType 结束设备类型编码</li>
	 *            <li>beginEquipmentClass 起始设备类别编码</li>
	 *            <li>endEquipmentClass 结束设备类别编码</li>
	 *            作业项目条件
	 *            <li>beginJobGroup 起始作业小组编码</li>
	 *            <li>endJobGroup 结束作业小组编码</li>
	 *            作业项目备件条件
	 *            <li>beginSparePartCode 起始备件编码</li>
	 *            <li>endSparePartCode 结束备件编码</li>
	 *            作业解决故障条件
	 *            <li>beginFaultBillNo 起始故障记录单号</li>
	 *            <li>endFaultBillNo 结束故障记录单号</li>
	 *            </ul>
	 * @return
	 */
	private String parseCondition(String unitsCode,
			Map<String, Object> conditions){
		boolean eqCondFlag = false,itemCondFlag = false,partCondFlag = false,faultCondFlag = false;
		String beginJobBillNo = (String) conditions.get("beginJobBillNo");
		String endJobBillNo = (String) conditions.get("endJobBillNo");
		
		String beginJobPlanNo = (String) conditions.get("beginJobPlanNo");
		String endJobPlanNo = (String) conditions.get("endJobPlanNo");
		String beginJobCode = (String) conditions.get("beginJobCode");
		String endJobCode = (String) conditions.get("endJobCode");
		String beginJobType = (String) conditions.get("beginJobType");
		String endJobType = (String) conditions.get("endJobType");
		String objectType = (String) conditions.get("objectType");
		String beginObjectCode = (String) conditions.get("beginObjectCode");
		String endObjectCode = (String) conditions.get("endObjectCode");
		String beginEquipmentType = (String) conditions
				.get("beginEquipmentType");
		String endEquipmentType = (String) conditions.get("endEquipmentType");
		String beginEquipmentClass = (String) conditions
				.get("beginEquipmentClass");
		String endEquipmentClass = (String) conditions.get("endEquipmentClass");
		Date beginPlanStartDate = (Date) conditions.get("beginPlanStartDate");
		Date endPlanStartDate = (Date) conditions.get("endPlanStartDate");
		Date beginPlanEndDate = (Date) conditions.get("beginPlanEndDate");
		Date endPlanEndDate = (Date) conditions.get("endPlanEndDate");
		Date beginFactStartDate = (Date) conditions.get("beginFactStartDate");
		Date endFactStartDate = (Date) conditions.get("endFactStartDate");
		Date beginFactEndDate = (Date) conditions.get("beginFactEndDate");
		Date endFactEndDate = (Date) conditions.get("endFactEndDate");
		String usedDept = (String)conditions.get("usedDept");
		String jobDept = (String)conditions.get("jobDept");
		String maker = (String) conditions.get("maker");
		Date beginMakeDate = (Date) conditions.get("beginMakeDate");
		Date endMakeDate = (Date) conditions.get("endMakeDate");
		String verifier = (String) conditions.get("verifier");
		Date beginVerifyDate = (Date) conditions.get("beginVerifyDate");
		Date endVerifyDate = (Date) conditions.get("endVerifyDate");
		String jobSign = (String) conditions.get("jobSign");
		Double beginFactFee = conditions.get("beginFactFee")==null?null:Double.valueOf(conditions.get("beginFactFee").toString());
		Double endFactFee = conditions.get("endFactFee")==null?null:Double.valueOf(conditions.get("endFactFee").toString());
		StringBuilder shql = new StringBuilder(
				"from EqJobBill p where unitsCode='").append(
				unitsCode).append("'");
		if(beginJobBillNo !=null && !"".equals(beginJobBillNo)){
			shql.append(" and jobBillNo>=:beginJobBillNo");
		}
		if(endJobBillNo !=null && !"".equals(endJobBillNo)){
			shql.append(" and jobBillNo<=:endJobBillNo");
		}
		if(beginJobPlanNo !=null && !"".equals(beginJobPlanNo)){
			shql.append(" and jobPlanNo>=:beginJobPlanNo");
		}
		if(endJobPlanNo !=null && !"".equals(endJobPlanNo)){
			shql.append(" and jobPlanNo<=:endJobPlanNo");
		}
		if(beginJobCode !=null && !"".equals(beginJobCode)){
			shql.append(" and jobCode>=:beginJobCode");
		}
		if(endJobCode !=null && !"".equals(endJobCode)){
			shql.append(" and jobCode<=:beginJobCode");
		}
		if(beginJobType !=null && !"".equals(beginJobType)){
			shql.append(" and jobType>=:beginJobType");
		}
		if(endJobType !=null && !"".equals(endJobType)){
			shql.append(" and jobType<=:endJobType");
		}
		if(objectType !=null && !"".equals(objectType)){
			shql.append(" and objectType=:objectType");
		}
		if(beginObjectCode !=null && !"".equals(beginObjectCode)){
			shql.append(" and objectCode>=:beginObjectCode");
		}
		if(endObjectCode !=null && !"".equals(endObjectCode)){
			shql.append(" and objectCode<=:endObjectCode");
		}
		if(beginPlanStartDate != null){
			shql.append(" and planStartDate>=:beginPlanStartDate");
		}
		if(endPlanStartDate != null){
			shql.append(" and planStartDate<=:endPlanStartDate");
		}
		if(beginPlanEndDate != null){
			shql.append(" and planEndDate>=:beginPlanEndDate");
		}
		if(endPlanEndDate != null){
			shql.append(" and planEndDate<=:endPlanEndDate");
		}
		if(beginFactStartDate != null){
			shql.append(" and factStartDate>=:beginFactStartDate");
		}
		if(endFactStartDate != null){
			shql.append(" and factStartDate<=:endFactStartDate");
		}
		if(beginFactEndDate != null){
			shql.append(" and factEndDate>=:beginFactEndDate");
		}
		if(endFactEndDate != null){
			shql.append(" and factEndDate<=:endFactEndDate");
		}
		if(usedDept !=null && !"".equals(usedDept)){
			shql.append(" and usedDept=:usedDept");
		}
		if(jobDept !=null && !"".equals(jobDept)){
			shql.append(" and jobDept=:jobDept");
		}
		if(maker !=null && !"".equals(maker)){
			shql.append(" and maker=:maker");
		}
		if(beginMakeDate != null){
			shql.append(" and makeDate>=:beginMakeDate");
		}
		if(endMakeDate != null){
			shql.append(" and makeDate<=:endMakeDate");
		}
		if(verifier !=null && !"".equals(verifier)){
			shql.append(" and verifier=:verifier");
		}
		if(beginVerifyDate != null){
			shql.append(" and verifyDate>=:beginVerifyDate");
		}
		if(endVerifyDate != null){
			shql.append(" and verifyDate<=:endVerifyDate");
		}
		if(jobSign != null){
			shql.append(" and jobSign=:jobSign");
		}
		if(beginFactFee != null){
			conditions.put("beginFactFee", beginFactFee);
			shql.append(" and factFee<=:beginFactFee");
		}
		if(endFactFee != null){
			conditions.put("endFactFee", endFactFee);
			shql.append(" and factFee<=:endFactFee");
		}
		//设备台帐条件
		if("1".equals(objectType)){
			//设备
			StringBuilder shqlEq = new StringBuilder("from EqEquipment e where e.unitsCode=p.unitsCode and e.equipmentCode=p.objectCode");
			if(beginEquipmentType !=null && !"".equals(beginEquipmentType)){
				eqCondFlag = true;
				shqlEq.append(" and e.equipmentType>=:beginEquipmentType");
			}
			if(endEquipmentType !=null && !"".equals(endEquipmentType)){
				eqCondFlag = true;
				shqlEq.append(" and e.equipmentType<=:endEquipmentType");
			}
			if(beginEquipmentClass !=null && !"".equals(beginEquipmentClass)){
				eqCondFlag = true;
				shqlEq.append(" and e.equipmentClass>=:beginEquipmentClass");
			}
			if(endEquipmentClass !=null && !"".equals(endEquipmentClass)){
				eqCondFlag = true;
				shqlEq.append(" and e.equipmentClass<=:endEquipmentClass");
			}
			if(eqCondFlag){
				shql.append(" and exists(").append(shqlEq).append(")");
			}
		}
		//作业计划项目条件
		String beginJobGroup = (String) conditions.get("beginJobGroup");
		String endJobGroup = (String) conditions.get("endJobGroup");
		StringBuilder shqlItem = new StringBuilder("from EqJobPlanItem i where i.autoId=p.autoId");
		if(beginJobGroup !=null && !"".equals(beginJobGroup)){
			itemCondFlag = true;
			shqlItem.append(" and i.jobGroup>=:beginJobGroup");
		}
		if(endJobGroup !=null && !"".equals(endJobGroup)){
			itemCondFlag = true;
			shqlItem.append(" and i.jobGroup<=:endJobGroup");
		}
		if(itemCondFlag){
			shql.append(" and exists(").append(shqlItem).append(")");
		}
		//作业计划备件条件
		String beginSparePartCode = (String) conditions.get("beginSparePartCode");
		String endSparePartCode = (String) conditions.get("endSparePartCode");
		StringBuilder shqlPart = new StringBuilder("from EqJobPlanItemPart s where s.autoId=p.autoId");
		if(beginSparePartCode !=null && !"".equals(beginSparePartCode)){
			partCondFlag = true;
			shqlPart.append(" and s.sparePartCode>=:beginSparePartCode");
		}
		if(endSparePartCode !=null && !"".equals(endSparePartCode)){
			partCondFlag = true;
			shqlPart.append(" and s.sparePartCode<=:endSparePartCode");
		}
		if(partCondFlag){
			shql.append(" and exists(").append(shqlPart).append(")");
		}
		//作业计划故障记录条件
		String beginFaultBillNo = (String) conditions.get("beginFaultBillNo");
		String endFaultBillNo = (String) conditions.get("endFaultBillNo");
		StringBuilder shqlFault = new StringBuilder("from VEqFault f where f.jobPlanAutoId=p.autoId");
		if(beginFaultBillNo !=null && !"".equals(beginFaultBillNo)){
			faultCondFlag = true;
			shqlFault.append(" and f.billNo>=:beginFaultBillNo");
		}
		if(endFaultBillNo !=null && !"".equals(endFaultBillNo)){
			faultCondFlag = true;
			shqlFault.append(" and f.billNo<=:endFaultBillNo");
		}
		if(faultCondFlag){
			shql.append(" and exists(").append(shqlFault).append(")");
		}
		return shql.toString();
	}

	
}