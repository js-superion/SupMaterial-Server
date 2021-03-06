package cn.superion.cssd.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdChemistryDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdChemistryDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdChemistryDetail
 * @author MyEclipse Persistence Tools
 */

public class CssdChemistryDetailDAO extends BaseHibernateDAO
 {
	private static final Logger log = LoggerFactory
			.getLogger(CssdChemistryDetailDAO.class);

	public void save(CssdChemistryDetail transientInstance) {
		log.debug("saving CssdChemistryDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdChemistryDetail persistentInstance) {
		log.debug("deleting CssdChemistryDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	

	@SuppressWarnings("unchecked")
	public List findByExample(CssdChemistryDetail instance) {
		log.debug("finding CssdChemistryDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdChemistryDetail").add(
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
		log.debug("finding CssdChemistryDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdChemistryDetail as model where model."
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
		log.debug("finding all CssdChemistryDetail instances");
		try {
			String queryString = "from CssdChemistryDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdChemistryDetail merge(CssdChemistryDetail detachedInstance) {
		log.debug("merging CssdChemistryDetail instance");
		try {
			CssdChemistryDetail result = (CssdChemistryDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdChemistryDetail instance) {
		log.debug("attaching dirty CssdChemistryDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdChemistryDetail instance) {
		log.debug("attaching clean CssdChemistryDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByMainAutoId(String fstrMainAutoId) {
		log.debug("finding CssdChemistryDetail instance");
		try {
			String queryString = "from CssdChemistryDetail where mainAutoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrMainAutoId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("finding CssdChemistryDetail", re);
			throw re;
		}
	}

	public void deleteByMainAutoId(String fstrMainAutoId) {
		try {
			String queryString = "delete from CssdChemistryDetail where mainAutoId= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrMainAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void saveDetails(String fstrMainAutoId,
			List<CssdChemistryDetail> fchemistryDetails) {
		deleteByMainAutoId(fstrMainAutoId);
		short serialNo=1;
		for(CssdChemistryDetail ldetail:fchemistryDetails){
			ldetail.setMainAutoId(fstrMainAutoId);
			ldetail.setSerialNo(serialNo);
			serialNo++;
			getSession().save(ldetail);
		}
	}
}