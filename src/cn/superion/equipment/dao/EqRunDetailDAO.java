package cn.superion.equipment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqRunDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqRunDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqRunDetail
 * @author MyEclipse Persistence Tools
 */

public class EqRunDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqRunDetailDAO.class);

	public void save(EqRunDetail transientInstance) {
		log.debug("saving EqRunDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqRunDetail persistentInstance) {
		log.debug("deleting EqRunDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EqRunDetail> findByExample(EqRunDetail instance) {
		log.debug("finding EqRunDetail instance by example");
		try {
			List<EqRunDetail> results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqRunDetail").add(
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
	public List<EqRunDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding EqRunDetail instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EqRunDetail as model where model."
					+ propertyName + "= ? order by serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public EqRunDetail merge(EqRunDetail detachedInstance) {
		log.debug("merging EqRunDetail instance");
		try {
			EqRunDetail result = (EqRunDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqRunDetail instance) {
		log.debug("attaching dirty EqRunDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqRunDetail instance) {
		log.debug("attaching clean EqRunDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqRunDetail where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId)
				.executeUpdate();
	}

	public List<EqRunDetail> findByAutoId(String autoId) {
		return findByProperty("autoId", autoId);
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含
	 *            <ul>
	 *            <li>startDate 开始日期</li>
	 *            <li>endDate 终止日期</li>
	 *            <li>usedDept 使用部门</li>
	 *            </ul>
	 * @return 设备编码，设备名称，利用率，故障率，计划停机时间，故障停机时间，运行时间，制度时间
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findEquipmentStat(String unitsCode,
			Map<String, Object> conditions) {
		Date startDate = (Date) conditions.get("startDate");
		Date endDate = (Date) conditions.get("endDate");
		String usedDept = (String) conditions.get("usedDept");
		StringBuilder ssql = new StringBuilder(
			"select a.object_code,a.object_name,decode(system_time-plan_stop_time,0,0,run_time*100/(system_time-plan_stop_time)) as used_rate,decode(fault_stop_time,0,0,fault_stop_time*100/(fault_stop_time+run_time)) as fault_rate,plan_stop_time,fault_stop_time,run_time,system_time")
				.append(" from (select r.object_code,r.object_name,")
				.append(
						"sum(case when d.status_attribute='1' then r.run_time else 0 end) as run_time,")
				.append(
						"sum(case when d.status_attribute='2' then r.run_time else 0 end) as plan_stop_time,")
				.append(
						"sum(case when d.status_attribute='3' then r.run_time else 0 end) as fault_stop_time,")
				.append(
						"sum(r.system_time) as system_time from eq_run_detail r,eq_run_master m,eq_run_status_dict d where r.run_status=d.status_code and r.auto_id=m.auto_id and m.units_code='")
				.append(unitsCode).append("' and r.object_type='1'");
		if(startDate != null){
			ssql.append(" and r.start_date>=:startDate");
		}
		if(endDate != null){
			ssql.append(" and r.end_date<=:endDate");
		}
		if(usedDept != null && !"".equals(usedDept)){
			ssql.append(" and r.used_dept=:usedDept");
		}
		ssql.append(" group by r.object_code,r.object_name) a");
		return getSession().createSQLQuery(ssql.toString()).setProperties(conditions).list();
	}
}