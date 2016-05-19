package cn.superion.cssd.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdFileClassDict;
import cn.superion.cssd.entity.CssdWorkRuleMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdFileClassDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdFileClassDict
 * @author MyEclipse Persistence Tools
 */

public class CssdFileClassDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdFileClassDictDAO.class);

	public void save(CssdFileClassDict transientInstance) {
		log.debug("saving CssdFileClassDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdFileClassDict persistentInstance) {
		log.debug("deleting CssdFileClassDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdFileClassDict findById(java.lang.String id) {
		log.debug("getting CssdFileClassDict instance with id: " + id);
		try {
			CssdFileClassDict instance = (CssdFileClassDict) getSession().get(
					"cn.superion.cssd.entity.CssdFileClassDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdFileClassDict instance) {
		log.debug("finding CssdFileClassDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdFileClassDict").add(
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
	public List<CssdFileClassDict> findByProperty(String propertyName, Object value) {
		log.debug("finding CssdFileClassDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdFileClassDict as model where model."
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
	public List<CssdWorkRuleMaster> findByPropertyMaster(String propertyName, Object value )
	{
		
		log.debug("finding CssdWorkRuleMaster instance with classCode: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdWorkRuleMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by classCode name failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CssdFileClassDict> findAll() {
		log.debug("finding all CssdFileClassDict instances");
		try {
			String queryString = "from CssdFileClassDict";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdFileClassDict merge(CssdFileClassDict detachedInstance) {
		log.debug("merging CssdFileClassDict instance");
		try {
			CssdFileClassDict result = (CssdFileClassDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdFileClassDict instance) {
		log.debug("attaching dirty CssdFileClassDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdFileClassDict instance) {
		log.debug("attaching clean CssdFileClassDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public void updateFileType(CssdFileClassDict cssdFileClassDict) {
		try {
			String queryString = "update CssdFileClassDict set className=?,fiveInputCode=?,phoInputCode=? where classCode=? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, cssdFileClassDict.getClassName());
			queryObject.setParameter(1, cssdFileClassDict.getFiveInputCode());
			queryObject.setParameter(2, cssdFileClassDict.getPhoInputCode());
			queryObject.setParameter(3, cssdFileClassDict.getClassCode());
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findAllFiles() {
		log.debug("finding CssdFileClassDict ");
		try {
			String queryString = "select new map(classCode as fileClass,className  as fileClassName) from CssdFileClassDict order by classCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public String findMaxClassCodeById(String id) {
		log.debug("getting CSSD_FILE_CLASS_DICT instance with id: " + id);
		try {
			String sql = " select max(t.class_code) from CSSD_FILE_CLASS_DICT t where t.parent_code='"
					+ id + "' ";
			Query queryObj = getSession().createSQLQuery(sql);
			Object obj = queryObj.uniqueResult();
			if (obj == null) {
				return id;
			}
			return queryObj.uniqueResult().toString();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CssdFileClassDict> findByPhoInputCode(String phoInputCode) {
		return this.findByProperty("phoInputCode", phoInputCode);
	}

	public List<CssdFileClassDict> findByFiveInputCode(String fiveInputCode) {
		return this.findByProperty("fiveInputCode", fiveInputCode);
	}
	
}