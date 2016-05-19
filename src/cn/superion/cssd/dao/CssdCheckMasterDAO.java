package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdCheckMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdCheckMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdCheckMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdCheckMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdCheckMasterDAO.class);

	public void save(CssdCheckMaster transientInstance) {
		log.debug("saving CssdCheckMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdCheckMaster persistentInstance) {
		log.debug("deleting CssdCheckMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdCheckMaster findById(java.lang.String id) {
		log.debug("getting CssdCheckMaster instance with id: " + id);
		try {
			CssdCheckMaster instance = (CssdCheckMaster) getSession().get(
					"cn.superion.cssd.entity.CssdCheckMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdCheckMaster instance) {
		log.debug("finding CssdCheckMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdCheckMaster").add(
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
		log.debug("finding CssdCheckMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdCheckMaster as model where model."
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
		log.debug("finding all CssdCheckMaster instances");
		try {
			String queryString = "from CssdCheckMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdCheckMaster merge(CssdCheckMaster detachedInstance) {
		log.debug("merging CssdCheckMaster instance");
		try {
			CssdCheckMaster result = (CssdCheckMaster) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdCheckMaster instance) {
		log.debug("attaching dirty CssdCheckMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdCheckMaster instance) {
		log.debug("attaching clean CssdCheckMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdCheckMaster> findByCheckDate(String fstrUnitsCode,Date checkDateFrom,
			Date checkDateTo, int start, int itemsPerPage, ReObject ro) {
		String hql="select count(*)   from CssdCheckMaster where unitsCode='"+fstrUnitsCode+"' ";
		String whereClause="";
		if(checkDateFrom!=null){
			whereClause+=" and checkDate>=:checkDateFrom and checkDate<=:checkDateTo";
		}
		hql=hql+whereClause;
		Query query=createDateQuery(checkDateFrom,checkDateTo,hql);		
		int count=((Long)query.uniqueResult()).intValue();
		ro.setCount(count, itemsPerPage);
		hql=hql.substring(16);
		query=createDateQuery(checkDateFrom,checkDateTo,hql);
		query.setFirstResult(start);
		query.setMaxResults(itemsPerPage);		
		return query.list();
	}
	private Query createDateQuery(Date checkDateFrom,
			Date checkDateTo,String hql){
		Query query=getSession().createQuery(hql);
		if(checkDateFrom!=null){
			query.setParameter("checkDateFrom", checkDateFrom);
			query.setParameter("checkDateTo", checkDateTo);
		}
		return query;
	}

	public void delById(String fstrAutoId) {
		try{
		String queryString = "delete from CssdCheckMaster where autoId=?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, fstrAutoId);
		queryObject.executeUpdate();
	} catch (RuntimeException re) {
		log.error("find all failed", re);
		throw re;
	}
	}

	public void updateCurrentStateById(String fstrCurrentState, String fstrAutoId) {
		try {
			String queryString = "update CssdCheckMaster set currentStuats=? where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrCurrentState);
			queryObject.setParameter(1, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}	
	}

	public void update(CssdCheckMaster cssdCheckMaster) {
		getSession().update(cssdCheckMaster);
	}	
}