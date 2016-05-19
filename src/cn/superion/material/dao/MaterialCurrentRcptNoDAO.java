package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.superion.material.entity.MaterialCurrentRcptNo;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialCurrentRcptNo entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialCurrentRcptNo
 * @author MyEclipse Persistence Tools
 */

public class MaterialCurrentRcptNoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialCurrentRcptNoDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String RD_FLAG = "rdFlag";
	public static final String RCPT_TYPE = "rcptType";
	public static final String STORAGE_CODE = "storageCode";
	public static final String TYPE_DATE = "typeDate";
	public static final String CURRENT_NO = "currentNo";

	public MaterialCurrentRcptNo findByStorageCode(String unitsCode, String rdFlag,
			String rcptType,String storageCode,String typeDate){
		String queryString = "from MaterialCurrentRcptNo where unitsCode=? and rdFlag=? and rcptType=? and storageCode=? and typeDate=?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, unitsCode);
		queryObject.setParameter(1, rdFlag);
		queryObject.setParameter(2, rcptType);
		queryObject.setParameter(3, storageCode);
		queryObject.setParameter(4, typeDate);
		return (MaterialCurrentRcptNo) queryObject.uniqueResult();
	}
	
	public MaterialCurrentRcptNo findByStorageCodeMonth(String unitsCode, String rdFlag,
			String rcptType,String storageCode,String typeDate,String rdType){
		String queryString = "from MaterialCurrentRcptNo where unitsCode=? and rdFlag=? and rcptType=? and storageCode=? and typeDate=? and rdType=?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, unitsCode);
		queryObject.setParameter(1, rdFlag);
		queryObject.setParameter(2, rcptType);
		queryObject.setParameter(3, storageCode);
		queryObject.setParameter(4, typeDate);
		queryObject.setParameter(5, rdType);
		return (MaterialCurrentRcptNo) queryObject.uniqueResult();
	}
	
	public void flush(){
		getSession().flush();
	}
	
	public void save(MaterialCurrentRcptNo transientInstance) {
		log.debug("saving MaterialCurrentRcptNo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialCurrentRcptNo persistentInstance) {
		log.debug("deleting MaterialCurrentRcptNo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialCurrentRcptNo findById(java.lang.String id) {
		log.debug("getting MaterialCurrentRcptNo instance with id: " + id);
		try {
			MaterialCurrentRcptNo instance = (MaterialCurrentRcptNo) getSession()
					.get("cn.superion.material.entity.MaterialCurrentRcptNo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialCurrentRcptNo> findByExample(MaterialCurrentRcptNo instance) {
		log.debug("finding MaterialCurrentRcptNo instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialCurrentRcptNo").add(
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
	public List<MaterialCurrentRcptNo> findByProperty(String propertyName, Object value) {
		log.debug("finding MaterialCurrentRcptNo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialCurrentRcptNo as model where model."
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
	public List<MaterialCurrentRcptNo> findAll() {
		log.debug("finding all MaterialCurrentRcptNo instances");
		try {
			String queryString = "from MaterialCurrentRcptNo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialCurrentRcptNo merge(MaterialCurrentRcptNo detachedInstance) {
		log.debug("merging MaterialCurrentRcptNo instance");
		try {
			MaterialCurrentRcptNo result = (MaterialCurrentRcptNo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialCurrentRcptNo instance) {
		log.debug("attaching dirty MaterialCurrentRcptNo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialCurrentRcptNo instance) {
		log.debug("attaching clean MaterialCurrentRcptNo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}