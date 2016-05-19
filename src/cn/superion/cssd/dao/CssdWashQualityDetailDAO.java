package cn.superion.cssd.dao;


import cn.superion.cssd.entity.CssdWashQualityDetail;
import cn.superion.util.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdWashQualityDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdWashQualityDetail
 * @author MyEclipse Persistence Tools
 */

public class CssdWashQualityDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdWashQualityDetailDAO.class);

	public void save(CssdWashQualityDetail transientInstance) {
		log.debug("saving CssdWashQualityDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdWashQualityDetail persistentInstance) {
		log.debug("deleting CssdWashQualityDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdWashQualityDetail findById(java.lang.String id) {
		log.debug("getting CssdWashQualityDetail instance with id: " + id);
		try {
			CssdWashQualityDetail instance = (CssdWashQualityDetail) getSession()
					.get("cn.superion.cssd.entity.CssdWashQualityDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdWashQualityDetail> findByExample(CssdWashQualityDetail instance) {
		log.debug("finding CssdWashQualityDetail instance by example");
		try {
			List<CssdWashQualityDetail> results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdWashQualityDetail").add(
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
	public List<CssdWashQualityDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding CssdWashQualityDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdWashQualityDetail as model where model."
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
	public List<CssdWashQualityDetail> findAll() {
		log.debug("finding all CssdWashQualityDetail instances");
		try {
			String queryString = "from CssdWashQualityDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdWashQualityDetail merge(CssdWashQualityDetail detachedInstance) {
		log.debug("merging CssdWashQualityDetail instance");
		try {
			CssdWashQualityDetail result = (CssdWashQualityDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdWashQualityDetail instance) {
		log.debug("attaching dirty CssdWashQualityDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdWashQualityDetail instance) {
		log.debug("attaching clean CssdWashQualityDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteByMainAutoId(String fstrMainAutoId) {
		try {
			String queryString = "delete from CssdWashQualityDetail where mainAutoId= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrMainAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public void saveDetails(String fstrMainAutoId, List<CssdWashQualityDetail> fDetails) {
		deleteByMainAutoId(fstrMainAutoId);
		short serialNo=1;
		for(CssdWashQualityDetail ldetail:fDetails){
			ldetail.setMainAutoId(fstrMainAutoId);
			ldetail.setSerialNo(serialNo);
			serialNo++;
			getSession().save(ldetail);
		}
	}

	public void deleteById(String fstrAutoId) {
		try {
			String queryString = "delete from CssdWashQualityDetail where autoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdWashQualityDetail failed", re);
			throw re;
		}		
	}
}