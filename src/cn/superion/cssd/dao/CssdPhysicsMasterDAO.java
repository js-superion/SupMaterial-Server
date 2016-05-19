package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdPhysicsMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdPhysicsMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdPhysicsMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdPhysicsMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdPhysicsMasterDAO.class);

	public void save(CssdPhysicsMaster transientInstance) {
		log.debug("saving CssdPhysicsMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdPhysicsMaster persistentInstance) {
		log.debug("deleting CssdPhysicsMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdPhysicsMaster findById(java.lang.String id) {
		log.debug("getting CssdPhysicsMaster instance with id: " + id);
		try {
			CssdPhysicsMaster instance = (CssdPhysicsMaster) getSession().get(
					"cn.superion.cssd.entity.CssdPhysicsMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdPhysicsMaster instance) {
		log.debug("finding CssdPhysicsMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdPhysicsMaster").add(
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
		log.debug("finding CssdPhysicsMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdPhysicsMaster as model where model."
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
		log.debug("finding all CssdPhysicsMaster instances");
		try {
			String queryString = "from CssdPhysicsMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdPhysicsMaster merge(CssdPhysicsMaster detachedInstance) {
		log.debug("merging CssdPhysicsMaster instance");
		try {
			CssdPhysicsMaster result = (CssdPhysicsMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdPhysicsMaster instance) {
		log.debug("attaching dirty CssdPhysicsMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdPhysicsMaster instance) {
		log.debug("attaching clean CssdPhysicsMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdPhysicsMaster> findByEquipNameInDateRange(String fstrUnitsCode,
			String fstrEquipName, Date fdateFrom, Date fdateTo,ReObject ro,int start,int limit) {	
		String hql="select count(*)   from CssdPhysicsMaster where unitsCode='"+fstrUnitsCode+"' and equipmentName like '"+fstrEquipName+"%' ";
		String whereClause="";
		if(fdateFrom!=null){
			whereClause+=" and operateDate>=:dateFrom and operateDate<=:dateTo ";
		}
		hql=hql+whereClause;
		hql+=" order by operateDate ";
		Query query=createDateQuery(fdateFrom,fdateTo,hql);		
		int count=((Long)query.uniqueResult()).intValue();
		ro.setCount(count, limit);
		hql=hql.substring(16);
		query=createDateQuery(fdateFrom,fdateTo,hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);		
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
	public void updateCurrentStateByAutoId(String fstrAutoId,String fstrCurrentState) {
		try {
			String queryString = "update CssdPhysicsMaster set currentStatus=? where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrCurrentState);
			queryObject.setParameter(1, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("update CssdPhysicsMaster failed", re);
			throw re;
		}
	}

	public void deleteByAutoId(String fstrAutoId) {
		try {
			String queryString = "delete from CssdPhysicsMaster where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdPhysicsMaster failed", re);
			throw re;
		}
	}

	public void update(CssdPhysicsMaster cssdPhysicsMaster) {
		getSession().update(cssdPhysicsMaster);
	}

}