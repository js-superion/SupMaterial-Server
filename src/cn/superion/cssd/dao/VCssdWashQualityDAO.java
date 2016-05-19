package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.base.ReObject;
import cn.superion.cssd.entity.VCssdWashQuality;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VCssdWashQuality entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.VCssdWashQuality
 * @author MyEclipse Persistence Tools
 */

public class VCssdWashQualityDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VCssdWashQualityDAO.class);

	public void save(VCssdWashQuality transientInstance) {
		log.debug("saving VCssdWashQuality instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VCssdWashQuality persistentInstance) {
		log.debug("deleting VCssdWashQuality instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}



	@SuppressWarnings("unchecked")
	public List findByExample(VCssdWashQuality instance) {
		log.debug("finding VCssdWashQuality instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.VCssdWashQuality").add(
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
		log.debug("finding VCssdWashQuality instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VCssdWashQuality as model where model."
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
		log.debug("finding all VCssdWashQuality instances");
		try {
			String queryString = "from VCssdWashQuality";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VCssdWashQuality merge(VCssdWashQuality detachedInstance) {
		log.debug("merging VCssdWashQuality instance");
		try {
			VCssdWashQuality result = (VCssdWashQuality) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VCssdWashQuality instance) {
		log.debug("attaching dirty VCssdWashQuality instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VCssdWashQuality instance) {
		log.debug("attaching clean VCssdWashQuality instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}



	@SuppressWarnings("unchecked")
	public List<VCssdWashQuality> findVWashMasterByResultInRange(
			String fstrUnitsCode, String resultValue, Date fdateFrom, Date fdateTo,
			ReObject ro, int start, int itemsPerPage) {	
		resultValue=resultValue==null?"":resultValue;
		String hql="select count(*)   from VCssdWashQuality where unitsCode='"+fstrUnitsCode+"' and resultValue='"+resultValue+"' ";
		String whereClause="";
		if(fdateFrom!=null){
			whereClause+=" and billDate>=:dateFrom and billDate<=:dateTo ";
		}
		hql=hql+whereClause;
		hql+=" order by billDate ";
		Query query=createDateQuery(fdateFrom,fdateTo,hql);		
		int count=((Long)query.uniqueResult()).intValue();
		ro.setCount(count, itemsPerPage);
		hql=hql.substring(16);
		query=createDateQuery(fdateFrom,fdateTo,hql);
		query.setFirstResult(start);
		query.setMaxResults(itemsPerPage);		
		return query.list();		
	}
	private Query createDateQuery(Date checkDateFrom,
			Date checkDateTo,String hql){
		Query query=getSession().createQuery(hql);
		if(checkDateFrom!=null){
			query.setParameter("dateFrom", checkDateFrom);
			query.setParameter("dateTo", checkDateTo);
		}
		return query;
	}		
}