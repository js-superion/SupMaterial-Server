package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdChemistryMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdChemistryMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdChemistryMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdChemistryMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdChemistryMasterDAO.class);

	public void save(CssdChemistryMaster transientInstance) {
		log.debug("saving CssdChemistryMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdChemistryMaster persistentInstance) {
		log.debug("deleting CssdChemistryMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	

	@SuppressWarnings("unchecked")
	public List findByExample(CssdChemistryMaster instance) {
		log.debug("finding CssdChemistryMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdChemistryMaster").add(
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
		log.debug("finding CssdChemistryMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdChemistryMaster as model where model."
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
		log.debug("finding all CssdChemistryMaster instances");
		try {
			String queryString = "from CssdChemistryMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdChemistryMaster merge(CssdChemistryMaster detachedInstance) {
		log.debug("merging CssdChemistryMaster instance");
		try {
			CssdChemistryMaster result = (CssdChemistryMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdChemistryMaster instance) {
		log.debug("attaching dirty CssdChemistryMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdChemistryMaster instance) {
		log.debug("attaching clean CssdChemistryMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(CssdChemistryMaster cssdChemistryMaster) {
		getSession().update(cssdChemistryMaster);
	}

	public void deleteByAutoId(String fstrAutoId) {
		try {
			String queryString = "delete from CssdChemistryMaster where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdChemistryMaster failed", re);
			throw re;
		}
		
	}

	public CssdChemistryMaster findById(java.lang.String id) {
		log.debug("getting CssdChemistryMaster instance with id: " + id);
		try {
			CssdChemistryMaster instance = (CssdChemistryMaster) getSession().get(
					"cn.superion.cssd.entity.CssdChemistryMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdChemistryMaster> findBychemistryTypeInDateRange(String fstrUnitsCode, String chemistryType, Date fdateFrom,
			Date fdateTo, ReObject ro, int start, int itemsPerPage) {	
//		chemistryType=chemistryType==null?"":chemistryType;
		String hql="select count(*) from CssdChemistryMaster where unitsCode='"+fstrUnitsCode+"' and chemistryType like '"+chemistryType+"%' ";
		String whereClause="";
		if(fdateFrom!=null){
			whereClause+=" and operateDate>=:dateFrom and operateDate<=:dateTo ";
		}
		hql=hql+whereClause;
		hql+=" order by operateDate ";
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