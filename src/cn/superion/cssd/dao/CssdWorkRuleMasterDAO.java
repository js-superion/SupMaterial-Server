package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.base.ReObject;
import cn.superion.cssd.entity.CssdWorkRuleMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdWorkRuleMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdWorkRuleMaster
 * @author MyEclipse Persistence Tools
 */

public class CssdWorkRuleMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdWorkRuleMasterDAO.class);

	public void save(CssdWorkRuleMaster transientInstance) {
		log.debug("saving CssdWorkRuleMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdWorkRuleMaster persistentInstance) {
		log.debug("deleting CssdWorkRuleMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdWorkRuleMaster findById(java.lang.String id) {
		log.debug("getting CssdWorkRuleMaster instance with id: " + id);
		try {
			CssdWorkRuleMaster instance = (CssdWorkRuleMaster) getSession()
					.get("cn.superion.cssd.entity.CssdWorkRuleMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdWorkRuleMaster instance) {
		log.debug("finding CssdWorkRuleMaster instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdWorkRuleMaster").add(
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
		log.debug("finding CssdWorkRuleMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdWorkRuleMaster as model where model."
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
		log.debug("finding all CssdWorkRuleMaster instances");
		try {
			String queryString = "from CssdWorkRuleMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdWorkRuleMaster merge(CssdWorkRuleMaster detachedInstance) {
		log.debug("merging CssdWorkRuleMaster instance");
		try {
			CssdWorkRuleMaster result = (CssdWorkRuleMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdWorkRuleMaster instance) {
		log.debug("attaching dirty CssdWorkRuleMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdWorkRuleMaster instance) {
		log.debug("attaching clean CssdWorkRuleMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CssdWorkRuleMaster> findByClassCodeFileDate(String classCode,
			Date fileDateFrom, Date fileDateTo,int start ,int limit,ReObject ro) {
		String hql="select count(*)   from CssdWorkRuleMaster ";
		String whereClause=" where 1=1 ";
		if(!"".equals(classCode) && classCode!=null){
			whereClause+=" and classCode='"+classCode+"' ";
		}
		if(fileDateFrom!=null){
			whereClause+=" and fileDate>=:fileDateFrom and fileDate<=:fileDateTo";
		}
		hql=hql+whereClause;
		Query query=createFileDateQuery(fileDateFrom,fileDateTo,hql);		
		int count=((Long)query.uniqueResult()).intValue();
		ro.setCount(count, limit);
		hql=hql.substring(16);
		query=createFileDateQuery(fileDateFrom,fileDateTo,hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);		
		return query.list();
	}
	private Query createFileDateQuery(Date fileDateFrom, Date fileDateTo,String hql){
		Query query=getSession().createQuery(hql);
		if(fileDateFrom!=null){
			query.setParameter("fileDateFrom", fileDateFrom);
			query.setParameter("fileDateTo", fileDateTo);
		}
		return query;
	}

	public void delById(String fstrAutoId) {
		try {
			String queryString = "delete from CssdWorkRuleMaster where autoId=?";
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
			String queryString = "update CssdWorkRuleMaster set currentStuats=? where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrCurrentState);
			queryObject.setParameter(1, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}

	public void update(CssdWorkRuleMaster cssdWorkRuleMaster) {
		getSession().update(cssdWorkRuleMaster);
	}

}