package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdBiologyMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdBiologyMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdBiologyMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdBiologyMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdBiologyMasterDAO.class);

	public void save(CssdBiologyMaster transientInstance) {
		log.debug("saving CssdBiologyMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdBiologyMaster persistentInstance) {
		log.debug("deleting CssdBiologyMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdBiologyMaster findById(java.lang.String id) {
		log.debug("getting CssdBiologyMaster instance with id: " + id);
		try {
			CssdBiologyMaster instance = (CssdBiologyMaster) getSession().get(
					"cn.superion.cssd.entity.CssdBiologyMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdBiologyMaster instance) {
		log.debug("finding CssdBiologyMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdBiologyMaster").add(
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
		log.debug("finding CssdBiologyMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdBiologyMaster as model where model."
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
		log.debug("finding all CssdBiologyMaster instances");
		try {
			String queryString = "from CssdBiologyMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdBiologyMaster merge(CssdBiologyMaster detachedInstance) {
		log.debug("merging CssdBiologyMaster instance");
		try {
			CssdBiologyMaster result = (CssdBiologyMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdBiologyMaster instance) {
		log.debug("attaching dirty CssdBiologyMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdBiologyMaster instance) {
		log.debug("attaching clean CssdBiologyMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	
	

	@SuppressWarnings("unchecked")
	public List<CssdBiologyMaster> findByEquipNameInDateRange(String fstrUnitsCode,
			String specimenName, Date fdateFrom, Date fdateTo, ReObject ro,
			int start, int itemsPerPage) {	
		String hql="select count(*)   from CssdBiologyMaster where unitsCode='"+fstrUnitsCode+"' and specimen like '"+specimenName+"%' ";
		String whereClause="";
		if(fdateFrom!=null){
			whereClause+=" and applyDate>=:dateFrom and applyDate<=:dateTo ";
		}
		hql=hql+whereClause;
		hql+=" order by applyDate ";
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
	public void deleteByAutoId(String fstrAutoId) {
		try {
			String queryString = "delete from CssdBiologyMaster where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdBiologyMaster failed", re);
			throw re;
		}
	}

	public void updateCurrentStateByAutoId(String fstrAutoId, String fstrCurrentState) {
		try {
			String queryString = "update CssdBiologyMaster set currentStatus=? where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrCurrentState);
			queryObject.setParameter(1, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("update CssdBiologyMaster failed", re);
			throw re;
		}
	}

	public void update(CssdBiologyMaster cssdBiologyMaster) {
		getSession().update(cssdBiologyMaster);
		
	}
}