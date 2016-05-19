package cn.superion.cssd.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdPackageDict;
import cn.superion.cssd.entity.CssdPackageDictDetail;
import cn.superion.cssd.entity.VCssdPackageDictDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdPackageDictDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdPackageDictDetail
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unused")
public class CssdPackageDictDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdPackageDictDetailDAO.class);

	public void save(CssdPackageDictDetail transientInstance) {
		log.debug("saving CssdPackageDictDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(String unitsCode, String packageId) {
		log.debug("finding CssdPackageDictDetail list by condition");
		try {
			String queryString = "delete from CssdPackageDictDetail where unitsCode = ? and packageId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, packageId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("finding CssdPackageDictDetail list by condition failed", re);
			throw re;
		}
	}



	@SuppressWarnings("unchecked")
	public List findByExample(CssdPackageDictDetail instance) {
		log.debug("finding CssdPackageDictDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdPackageDictDetail").add(
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
		log.debug("finding CssdPackageDictDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdPackageDictDetail as model where model."
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
		log.debug("finding all CssdPackageDictDetail instances");
		try {
			String queryString = "from CssdPackageDictDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdPackageDictDetail merge(CssdPackageDictDetail detachedInstance) {
		log.debug("merging CssdPackageDictDetail instance");
		try {
			CssdPackageDictDetail result = (CssdPackageDictDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdPackageDictDetail instance) {
		log.debug("attaching dirty CssdPackageDictDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdPackageDictDetail instance) {
		log.debug("attaching clean CssdPackageDictDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void filnPackageId(String unitsCode,
			String packageId) {
		String hql = "from CssdPackageDictDetail where unitsCode=:unitsCode and packageId=:packageId order by packageId";
	}
	
	
	
	
	public void update(CssdPackageDictDetail cssdPackageDictDetail) {
		try {
			String queryString = "update CssdPackageDictDetail set materialClass=?,materialId=?,amount?,materialSign?,remark?,serialNo?," +
					"where packageId=?";
			
			Query queryObject = getSession().createQuery(queryString);

			queryObject.setParameter(0,cssdPackageDictDetail.getMaterialClass());
			queryObject.setParameter(1,cssdPackageDictDetail.getMaterialId());
			queryObject.setParameter(2,cssdPackageDictDetail.getAmount());
			queryObject.setParameter(3,cssdPackageDictDetail.getMaterialSign());
			queryObject.setParameter(4,cssdPackageDictDetail.getRemark());
			queryObject.setParameter(5,cssdPackageDictDetail.getSerialNo());
			queryObject.setParameter(6,cssdPackageDictDetail.getSerialNo());
			
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdPackageDictDetail> findByPackageId(String unitsCode,
			String packageId) {
		String hql = "from CssdPackageDictDetail where unitsCode=:unitsCode and packageId=:packageId order by serialNo";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("packageId", packageId).list();
	}
}