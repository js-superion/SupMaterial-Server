package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdDeptVsPackageClass;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdDeptVsPackageClass entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.dao.CssdDeptVsPackageClass
 * @author MyEclipse Persistence Tools
 */

public class CssdDeptVsPackageClassDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdDeptVsPackageClassDAO.class);

	// property constants

	public void save(CssdDeptVsPackageClass transientInstance) {
		log.debug("saving CssdDeptVsPackageClass instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delById(String unitsCode, String fstrDeptCode,String fstrClassCode) {
		log.debug("deleting CssdDeptVsPackageClass instance");
		try {
			String queryString = "delete from CssdDeptVsPackageClass  where unitsCode= '"+unitsCode
			+"' and deptCode = '"+fstrDeptCode+"' and userCode = '"+fstrClassCode+"'";
			Query queryObject = getSession().createQuery(queryString);
		    queryObject.executeUpdate();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdDeptVsPackageClass findById(
			CssdDeptVsPackageClass id) {
		log.debug("getting CssdDeptVsPackageClass instance with id: " + id);
		try {
			CssdDeptVsPackageClass instance = (CssdDeptVsPackageClass) getSession()
					.get("CssdDeptVsPackageClass", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CssdDeptVsPackageClass instance) {
		log.debug("finding CssdDeptVsPackageClass instance by example");
		try {
			List results = getSession().createCriteria(
					"CssdDeptVsPackageClass").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CssdDeptVsPackageClass instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdDeptVsPackageClass as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all CssdDeptVsPackageClass instances");
		try {
			String queryString = "from CssdDeptVsPackageClass";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List<CssdDeptVsPackageClass> findByDeptCode(String fstrUnitsCode,String deptCode) {
		log.debug("finding all CssdDeptVsPackageClass instances");
		try {
			String queryString = "from CssdDeptVsPackageClass where unitsCode = '"+fstrUnitsCode+"' and  deptCode = '"+deptCode+"'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List<CssdDeptVsPackageClass> findByDeptCode(String fstrUnitsCode,String fstrDeptCode,String fstrClassCode) {
		log.debug("finding all CssdDeptVsPackageClass instances");
		try {
			String queryString = "from CssdDeptVsPackageClass where unitsCode = '"+fstrUnitsCode+"' and deptCode = '"+fstrDeptCode+"' and classCode = '"+fstrClassCode+"'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public CssdDeptVsPackageClass merge(CssdDeptVsPackageClass detachedInstance) {
		log.debug("merging CssdDeptVsPackageClass instance");
		try {
			CssdDeptVsPackageClass result = (CssdDeptVsPackageClass) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdDeptVsPackageClass instance) {
		log.debug("attaching dirty CssdDeptVsPackageClass instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdDeptVsPackageClass instance) {
		log.debug("attaching clean CssdDeptVsPackageClass instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}