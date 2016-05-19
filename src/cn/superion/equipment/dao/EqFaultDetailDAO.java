package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqFaultDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqFaultDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqFaultDetail
 * @author MyEclipse Persistence Tools
 */

public class EqFaultDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqFaultDetailDAO.class);

	public void save(EqFaultDetail transientInstance) {
		log.debug("saving EqFaultDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqFaultDetail persistentInstance) {
		log.debug("deleting EqFaultDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqFaultDetail> findByExample(EqFaultDetail instance) {
		log.debug("finding EqFaultDetail instance by example");
		try {
			List<EqFaultDetail> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqFaultDetail").add(
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
	public List<EqFaultDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding EqFaultDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EqFaultDetail as model where model."
					+ propertyName + "= ? order by autoId,serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqFaultDetail merge(EqFaultDetail detachedInstance) {
		log.debug("merging EqFaultDetail instance");
		try {
			EqFaultDetail result = (EqFaultDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqFaultDetail instance) {
		log.debug("attaching dirty EqFaultDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqFaultDetail instance) {
		log.debug("attaching clean EqFaultDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<EqFaultDetail> findByJobPlanAutoId(String jobPlanAutoId) {
		return findByProperty("jobPlanAutoId", jobPlanAutoId);
	}
	
	public List<EqFaultDetail> findByJobBillAutoId(String jobBillAutoId) {
		return findByProperty("jobBillAutoId", jobBillAutoId);
	}

	public EqFaultDetail findById(String autoId, Short serialNo) {
		String hql = "from EqFaultDetail where autoId=:autoId and serialNo=:serialNo";
		return (EqFaultDetail) getSession().createQuery(hql).setString(
				"autoId", autoId).setShort("serialNo", serialNo).uniqueResult();
	}

	public void cleanByJobPlanAutoId(String jobPlanAutoId) {
		String hql = "update EqFaultDetail set createJobPlanSign='0',jobCode=null,jobName=null,jobPlanAutoId=null where jobPlanAutoId=:jobPlanAutoId";
		//String hql = "update EqFaultDetail set jobPlanAutoId=null where jobPlanAutoId=:jobPlanAutoId";
		getSession().createQuery(hql).setString("jobPlanAutoId", jobPlanAutoId)
				.executeUpdate();
	}
	
	public void cleanByJobBillAutoId(String jobBillAutoId) {
		String hql = "update EqFaultDetail set createJobBillSign='0',jobCode=null,jobName=null,jobBillAutoId=null where jobBillAutoId=:jobBillAutoId";
		//String hql = "update EqFaultDetail set jobBillAutoId=null where jobBillAutoId=:jobBillAutoId";
		getSession().createQuery(hql).setString("jobBillAutoId", jobBillAutoId)
				.executeUpdate();
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 故障明细记录条件
	 *            包含：
	 *            <ul>
	 *            <li>equipmentCode 设备编码</li>
	 *            <li>beginStartDate 起始故障开始日期</li>
	 *            <li>endStartDate 结束故障开始日期</li>
	 *            <li>usedDept 使用部门</li>
	 *            <li>createJobPlanSign 生成作业计划标志</li>
	 *            <li>createJobBillSign 生成作业单标志</li>
	 *            <li>jobStatus 作业完成情况</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EqFaultDetail> findVerifiedByCondition(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder("select d from EqFaultDetail d,EqFaultMaster m where d.autoId=m.autoId and m.currentStatus='1' and m.unitsCode='").append(unitsCode).append("'");
		String equipmentCode = (String)conditions.get("equipmentCode");
		Date beginStartDate = (Date)conditions.get("beginStartDate");
		Date endStartDate = (Date)conditions.get("endStartDate");
		String usedDept = (String)conditions.get("usedDept");
		String createJobPlanSign = (String)conditions.get("createJobPlanSign");
		String createJobBillSign = (String)conditions.get("createJobBillSign");
		String jobStatus = (String)conditions.get("jobStatus");
		if(equipmentCode !=null && !"".equals(equipmentCode)){
			shql.append(" and d.equipmentCode=:equipmentCode");
		}
		if(beginStartDate != null){
			shql.append(" and d.startDate>=:beginStartDate");
		}
		if(endStartDate != null){
			shql.append(" and d.startDate<=:endStartDate");
		}
		if(usedDept !=null && !"".equals(usedDept)){
			shql.append(" and d.usedDept=:usedDept");
		}
		if(createJobPlanSign !=null && !"".equals(createJobPlanSign)){
			shql.append(" and d.createJobPlanSign=:createJobPlanSign");
		}
		if(createJobBillSign !=null && !"".equals(createJobBillSign)){
			shql.append(" and d.createJobBillSign=:createJobBillSign");
		}
		if(jobStatus !=null && !"".equals(jobStatus)){
			shql.append(" and d.jobStatus=:jobStatus");
		}
		shql.append(" order by d.autoId,d.serialNo");
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqFaultDetail where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}

	public List<EqFaultDetail> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}

	@SuppressWarnings("unchecked")
	public List<EqFaultDetail> findToJobPlanFaultList(String autoId) {
		String hql = "from EqFaultDetail where autoId=:autoId and createJobPlanSign='1'";
		return getSession().createQuery(hql).setString("autoId", autoId).list();
	}

	@SuppressWarnings("unchecked")
	public List<EqFaultDetail> findToJobBillFaultList(String autoId) {
		String hql = "from EqFaultDetail where autoId=:autoId and createJobPlanSign='2'";
		return getSession().createQuery(hql).setString("autoId", autoId).list();
	}

	@SuppressWarnings("unchecked")
	public List<EqFaultDetail> findFaultDetailsByDateUDeptEqType(Date fDateStart,
			Date fDateEnd, String fstrUsedDept, String fstrEquipmentType) {
		String hql = "from EqFaultDetail where createJobPlanSign='0' and usedDept=:usedDept" +
				" and equipmentType=:equipmentType and startDate>=:beginStartDate" +
				" and startDate>=:endStartDate";
		Query query= getSession().createQuery(hql);
		query.setString("usedDept", fstrUsedDept);
		query.setString("equipmentType", fstrEquipmentType);
		query.setDate("beginStartDate", fDateStart);
		query.setDate("endStartDate", fDateEnd);
		return query.list();
	}

	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		String hql = "select count(*) from EqFaultDetail where unitsCode=:unitsCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}

	
}