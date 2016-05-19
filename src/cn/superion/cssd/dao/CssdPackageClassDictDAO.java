package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdPackageClassDict;
import cn.superion.cssd.entity.CssdPackageDict;
import cn.superion.cssd.entity.CssdPackageDictDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdPackageClassDict entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdPackageClassDict
 * @author MyEclipse Persistence Tools
 */

public class CssdPackageClassDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdPackageClassDictDAO.class);

	public void save(CssdPackageClassDict transientInstance) {
		log.debug("saving CssdPackageClassDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdPackageClassDict persistentInstance) {
		log.debug("deleting CssdPackageClassDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdPackageClassDict findById(java.lang.String id) {
		log.debug("getting CssdPackageClassDict instance with id: " + id);
		try {
			CssdPackageClassDict instance = (CssdPackageClassDict) getSession()
					.get("cn.superion.cssd.entity.CssdPackageClassDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdPackageClassDict instance) {
		log.debug("finding CssdPackageClassDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdPackageClassDict").add(
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
	public List<CssdPackageClassDict> findByProperty(String propertyName, Object value) {
		log.debug("finding CssdPackageClassDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdPackageClassDict as model where model."
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
	public List<CssdPackageClassDict> findAll() {
		log.debug("finding all CssdPackageClassDict instances");
		try {
			String queryString = "from CssdPackageClassDict";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdPackageClassDict merge(CssdPackageClassDict detachedInstance) {
		log.debug("merging CssdPackageClassDict instance");
		try {
			CssdPackageClassDict result = (CssdPackageClassDict) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdPackageClassDict instance) {
		log.debug("attaching dirty CssdPackageClassDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdPackageClassDict instance) {
		log.debug("attaching clean CssdPackageClassDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public void updateFileType(CssdPackageClassDict cssdPackageClassDict) {
		try {
			String queryString = "update CssdPackageClassDict set className=?,fiveInputCode=?,phoInputCode=? where classCode=? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, cssdPackageClassDict.getClassName());
			queryObject.setParameter(1, cssdPackageClassDict.getFiveInputCode());
			queryObject.setParameter(2, cssdPackageClassDict.getPhoInputCode());
			queryObject.setParameter(3, cssdPackageClassDict.getClassCode());
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CssdPackageClassDict> findFileTypeAll()
	{
		log.debug("finding all CssdPackageClassDict instances");
		try {
			String queryString = "from CssdPackageClassDict order by classCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<CssdPackageDictDetail> findByPropertyMaster(String propertyName, Object value) {
		log.debug("finding CssdPackageDict instance with  packageClass: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CssdPackageDict as model where model."
					+ propertyName + "= ? order by packageClass";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	//主记录
	@SuppressWarnings("unchecked")
	public List<CssdPackageDict> findPackageIdM(String unitsCode,
			String packageId){
		String hql = "from CssdPackageDict where unitsCode=:unitsCode and packageId=:packageId order by packageId";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("packageId", packageId).list();
	}
	@SuppressWarnings("unchecked")
	//明细
	public List<CssdPackageDictDetail> findPackageIdD(String unitsCode,
			String packageId) {
		String hql = "from CssdPackageDictDetail where unitsCode=:unitsCode and packageId=:packageId order by packageId";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("packageId", packageId).list();
	
	}
	
	public String findMaxClassCodeById(String id) {
		log.debug("getting CSSD_PACKAGE_CLASS_DICT instance with id: " + id);
		try {
			String sql = " select max(t.class_code) from CSSD_PACKAGE_CLASS_DICT t where t.parent_code='"
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

	public List<CssdPackageClassDict> findByPhoInputCode(String phoInputCode) {
		return this.findByProperty("phoInputCode", phoInputCode);
	}

	public List<CssdPackageClassDict> findByFiveInputCode(String fiveInputCode) {
		return this.findByProperty("fiveInputCode", fiveInputCode);
	}
}