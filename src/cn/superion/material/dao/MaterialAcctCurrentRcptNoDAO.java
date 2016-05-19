package cn.superion.material.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialAcctCurrentRcptNo;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialAcctCurrentRcptNo entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see cn.superion.material.entity.MaterialAcctCurrentRcptNo
 * @author MyEclipse Persistence Tools
 */

public class MaterialAcctCurrentRcptNoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialAcctCurrentRcptNoDAO.class);

	public void save(MaterialAcctCurrentRcptNo transientInstance) {
		log.debug("saving MaterialAcctCurrentRcptNo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialAcctCurrentRcptNo persistentInstance) {
		log.debug("deleting MaterialAcctCurrentRcptNo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialAcctCurrentRcptNo findById(java.lang.String id) {
		log.debug("getting MaterialAcctCurrentRcptNo instance with id: " + id);
		try {
			MaterialAcctCurrentRcptNo instance = (MaterialAcctCurrentRcptNo) getSession()
					.get(
							"cn.superion.material.entity.MaterialAcctCurrentRcptNo",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialAcctCurrentRcptNo> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialAcctCurrentRcptNo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialAcctCurrentRcptNo as model where model."
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
	public List<MaterialAcctCurrentRcptNo> findAll() {
		log.debug("finding all MaterialAcctCurrentRcptNo instances");
		try {
			String queryString = "from MaterialAcctCurrentRcptNo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialAcctCurrentRcptNo merge(
			MaterialAcctCurrentRcptNo detachedInstance) {
		log.debug("merging MaterialAcctCurrentRcptNo instance");
		try {
			MaterialAcctCurrentRcptNo result = (MaterialAcctCurrentRcptNo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialAcctCurrentRcptNo instance) {
		log.debug("attaching dirty MaterialAcctCurrentRcptNo instance");
		try {
			getSession().saveOrUpdate(instance);
			getSession().flush();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialAcctCurrentRcptNo instance) {
		log.debug("attaching clean MaterialAcctCurrentRcptNo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 获取当前最大号 作者：周作建 2011.06.11
	 * 
	 * @param unitsCode
	 * @param rdFlg
	 * @param rcptType
	 * @param typeDate
	 * @return
	 */
	public String getMaxNo(String unitsCode, String rdFlg, String rcptType) {
		log.debug("finding MaterialAcctCurrentRcptNo max currentNo");
		try {
			String typeDate = "";
			if (rcptType.equals("1")) {
				typeDate = getTypeDate("yyyyMMdd");
			} else if (rcptType.equals("2")) {
				typeDate = getTypeDate("yyyyMM");
			} else {
				typeDate = getTypeDate("yyyy");
			}
			String queryString = "select max(currentNo) from MaterialAcctCurrentRcptNo where unitsCode=? and rdFlag=? and rcptType=? and typeDate=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, rdFlg);
			queryObject.setParameter(2, rcptType);
			queryObject.setParameter(3, typeDate);

			String currentNo = (String) queryObject.uniqueResult();
			if (null == currentNo) {
				currentNo = typeDate + "0000";
			}
			return currentNo;
		} catch (RuntimeException re) {
			log.error("find by property max currentNo failed", re);
			throw re;
		}
	}

	/**
	 * 获取日期格式
	 * 
	 * @param fstrDateFormat
	 * @return
	 */
	private String getTypeDate(String fstrDateFormat) {
		java.util.Date dt = Calendar.getInstance().getTime();
		SimpleDateFormat df = new SimpleDateFormat(fstrDateFormat);
		return df.format(dt);
	}

	/**
	 * 获取当前最大号 作者：周作建 2011.06.11
	 * 
	 * @param unitsCode
	 * @param rdFlg
	 * @param rcptType
	 * @param typeDate
	 * @return
	 */
	public MaterialAcctCurrentRcptNo findByUniqueIndex(String unitsCode,
			String rdFlg, String rcptType, String typeDate) {
		log.debug("finding MaterialAcctCurrentRcptNo by unique index");
		try {

			String queryString = "from MaterialAcctCurrentRcptNo where unitsCode=? and rdFlag=? and rcptType=? and typeDate=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, rdFlg);
			queryObject.setParameter(2, rcptType);
			queryObject.setParameter(3, typeDate);

			return (MaterialAcctCurrentRcptNo) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by unique index failed", re);
			throw re;
		}
	}
	
	public MaterialAcctCurrentRcptNo findByUniqueIndex2(String unitsCode,
			String rdFlg, String rcptType,String storageCode) {
		log.debug("finding MaterialAcctCurrentRcptNo by unique index");
		try {

			String queryString = "from MaterialAcctCurrentRcptNo where unitsCode=? and rdFlag=? and rcptType=? and storageCode = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, rdFlg);
			queryObject.setParameter(2, rcptType);
			queryObject.setParameter(3, storageCode);

			return (MaterialAcctCurrentRcptNo) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by unique index failed", re);
			throw re;
		}
	}
	public List<MaterialAcctCurrentRcptNo> findByUniqueIndex3(String unitsCode,
			String rdFlg) {
		log.debug("finding MaterialAcctCurrentRcptNo by unique index");
		try {

			String queryString = "from MaterialAcctCurrentRcptNo where unitsCode=? and rdFlag=? order by currentNo desc ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, rdFlg);
			return  queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by unique index failed", re);
			throw re;
		}
	}
}