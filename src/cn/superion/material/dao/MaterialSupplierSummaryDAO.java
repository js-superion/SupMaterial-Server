package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialSupplierSummary;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialSupplierSummary entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialSupplierSummary
 * @author MyEclipse Persistence Tools
 */

public class MaterialSupplierSummaryDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialSupplierSummaryDAO.class);

	// property constants

	public void save(MaterialSupplierSummary transientInstance) {
		log.debug("saving MaterialSupplierSummary instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialSupplierSummary persistentInstance) {
		log.debug("deleting MaterialSupplierSummary instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void deleteByIds(List<String> ids) {
		log.debug("deleting MaterialSupplierDetail instance");
		try {
			String queryString = "delete MaterialSupplierSummary t where t.autoId in(:ids)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameterList("ids", ids).executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<MaterialSupplierSummary> findSumaryByCondition(String unitsCode,
			Map<String, Object> conditions){
		String providerName = (String)conditions.get("providerName");
		
		String fromDate = (String)conditions.get("fromDate");
		String checkSign = (String)conditions.get("checkSign");
		String toDate = (String)conditions.get("toDate");
		String storageCode = (String)conditions.get("storageCode");
		StringBuilder shql = new StringBuilder("from MaterialSupplierSummary m where m.unitsCode='").append(unitsCode).append("'");
		if(providerName != null && !"".equals(providerName)){
			shql.append(" and m.providerName='"+providerName+"'");
		}
		
		if(storageCode != null && !"".equals(storageCode)){
			shql.append(" and m.storageCode='"+storageCode+"'");
		}
		if(checkSign != null && !"".equals(checkSign)){
			shql.append(" and m.checkSign='"+checkSign+"'");
		}
		
		if(fromDate != null&& !"".equals(fromDate)){
			shql.append(" and m.billDate>=to_date('"+fromDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
		if(toDate != null&& !"".equals(toDate)){
			shql.append(" and m.billDate<=to_date('"+toDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		return getSession().createQuery(shql.toString()).list();
	}

	public MaterialSupplierSummary findById(String id) {
		log.debug("getting MaterialSupplierSummary instance with id: " + id);
		try {
			MaterialSupplierSummary instance = (MaterialSupplierSummary) getSession()
					.get("cn.superion.material.entity.MaterialSupplierSummary",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MaterialSupplierSummary instance) {
		log.debug("finding MaterialSupplierSummary instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialSupplierSummary").add(
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
		log.debug("finding MaterialSupplierSummary instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialSupplierSummary as model where model."
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
		log.debug("finding all MaterialSupplierSummary instances");
		try {
			String queryString = "from MaterialSupplierSummary";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialSupplierSummary merge(
			MaterialSupplierSummary detachedInstance) {
		log.debug("merging MaterialSupplierSummary instance");
		try {
			MaterialSupplierSummary result = (MaterialSupplierSummary) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialSupplierSummary instance) {
		log.debug("attaching dirty MaterialSupplierSummary instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialSupplierSummary instance) {
		log.debug("attaching clean MaterialSupplierSummary instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}