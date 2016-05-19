package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdPersonArrange;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdPersonArrange entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdPersonArrange
 * @author MyEclipse Persistence Tools
 */

public class CssdPersonArrangeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdPersonArrangeDAO.class);

	public void save(CssdPersonArrange transientInstance) {
		log.debug("saving CssdPersonArrange instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
	public void delete(CssdPersonArrange persistentInstance) {
		log.debug("deleting CssdPersonArrange instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdPersonArrange findById(java.lang.String id) {
		log.debug("getting CssdPersonArrange instance with id: " + id);
		try {
			CssdPersonArrange instance = (CssdPersonArrange) getSession().get(
					"cn.superion.cssd.entity.CssdPersonArrange", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdPersonArrange instance) {
		log.debug("finding CssdPersonArrange instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdPersonArrange").add(
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
		log.debug("finding CssdPersonArrange instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdPersonArrange as model where model."
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
	public List findByPersonId(String fstrPersonId,String fstrUnitsCode) {
		log.debug("finding CssdPersonArrange instance with fstrPersonId: "
				+ fstrPersonId + ", fstrUnitsCode: " + fstrUnitsCode);
		try {
			String queryString = "from CssdPersonArrange where unitsCode=? and  personId= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrUnitsCode);
			queryObject.setParameter(1, fstrPersonId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	@SuppressWarnings("unchecked")
	public List findAll() {
		log.debug("finding all CssdPersonArrange instances");
		try {
			String queryString = "from CssdPersonArrange";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdPersonArrange merge(CssdPersonArrange detachedInstance) {
		log.debug("merging CssdPersonArrange instance");
		try {
			CssdPersonArrange result = (CssdPersonArrange) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdPersonArrange instance) {
		log.debug("attaching dirty CssdPersonArrange instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdPersonArrange instance) {
		log.debug("attaching clean CssdPersonArrange instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdPersonArrange> findArrangeListByCondition(
			Map<String, Object> lmapCondition,String fstrUnitsCode) {
		String hql="from CssdPersonArrange where unitsCode='"+fstrUnitsCode+"' %s and personId in (select personId from CdPersonDict where unitsCode='"+fstrUnitsCode+"' ";
		String conDataRangeClause="";
		Date ldateFrom= (Date) lmapCondition.get("dateFrom");
		Date ldateTo= (Date) lmapCondition.get("dateTo");
		if(ldateFrom!=null){
			conDataRangeClause=" and workDate>=:dateFrom and workDate<=:dateTo ";
		}
		hql=String.format(hql, conDataRangeClause);
		String conPersonClause="";
		String lstrPersonName=(String) lmapCondition.get("personName");
		String lstrPersonCode=(String) lmapCondition.get("personCode");
		if(lstrPersonName==null||lstrPersonName.trim().equals("")){
			conPersonClause=" and personCode like '"+lstrPersonCode+"%')";
		}else{
			conPersonClause=" and name like '"+lstrPersonName+"%')";
		}
		hql+=conPersonClause;
		Query queryObject = getSession().createQuery(hql);
		if(hql.indexOf(":dateFrom")>-1){
			queryObject.setParameter("dateFrom", ldateFrom);
			queryObject.setParameter("dateTo", ldateTo);
		}
		return queryObject.list();
	}

	public void deleteByPersonId(String fstrPersonId,String fstrUnitsCode) {
		String hql="delete from CssdPersonArrange where unitsCode=? and personId =?";
		Query queryObject = getSession().createQuery(hql);
		queryObject.setParameter(0,fstrUnitsCode);
		queryObject.setParameter(1,fstrPersonId);
		queryObject.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<CssdPersonArrange> findByPersonIdInRange(String fstrPersonId,
			String fstrUnitsCode, Date fdateFrom, Date fdateTo) {
		String hql="from CssdPersonArrange where unitsCode='"+fstrUnitsCode+"' and personId='"+fstrPersonId+"'";
		if(fdateFrom!=null){
			hql+=" and workDate>=:dateFrom and workDate<=:dateTo ";
		}
		hql+=" order by workDate ";
		
		Query queryObject = getSession().createQuery(hql);
		if(fdateFrom!=null){
			queryObject.setParameter("dateFrom", fdateFrom);
			queryObject.setParameter("dateTo", fdateTo);
		}
		return queryObject.list();
	}
}