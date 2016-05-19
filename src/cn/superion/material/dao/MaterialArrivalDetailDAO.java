package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialArrivalDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialArrivalDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialArrivalDetail
 * @author MyEclipse Persistence Tools
 */

public class MaterialArrivalDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialArrivalDetailDAO.class);
	// property constants
	public static final String MAIN_AUTO_ID = "mainAutoId";
	public static final String SERIAL_NO = "serialNo";
	public static final String MATERIAL_CLASS = "materialClass";
	public static final String MATERIAL_ID = "materialId";
	public static final String MATERIAL_CODE = "materialCode";
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_SPEC = "materialSpec";
	public static final String MATERIAL_UNITS = "materialUnits";
	public static final String AMOUNT = "amount";
	public static final String TRADE_PRICE = "tradePrice";
	public static final String TRADE_MONEY = "tradeMoney";
	public static final String RETAIL_PRICE = "retailPrice";
	public static final String RETAIL_MONEY = "retailMoney";
	public static final String FACTORY_CODE = "factoryCode";
	public static final String BATCH = "batch";
	public static final String INPUT_AMOUNT = "inputAmount";
	public static final String SOURCE_BILL_NO = "sourceBillNo";
	public static final String SOURCE_SERIAL_NO = "sourceSerialNo";
	public static final String CURRENT_STATUS = "currentStatus";
	public static final String DETAIL_REMARK = "detailRemark";

	public void save(MaterialArrivalDetail transientInstance) {
		log.debug("saving MaterialArrivalDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialArrivalDetail persistentInstance) {
		log.debug("deleting MaterialArrivalDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialArrivalDetail findById(java.lang.String id) {
		log.debug("getting MaterialArrivalDetail instance with id: " + id);
		try {
			MaterialArrivalDetail instance = (MaterialArrivalDetail) getSession()
					.get("cn.superion.material.entity.MaterialArrivalDetail",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialArrivalDetail> findByExample(MaterialArrivalDetail instance) {
		log.debug("finding MaterialArrivalDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialArrivalDetail").add(
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
	public List<MaterialArrivalDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding MaterialArrivalDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialArrivalDetail as model where model."
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
	public List<MaterialArrivalDetail> findAll() {
		log.debug("finding all MaterialArrivalDetail instances");
		try {
			String queryString = "from MaterialArrivalDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialArrivalDetail merge(MaterialArrivalDetail detachedInstance) {
		log.debug("merging MaterialArrivalDetail instance");
		try {
			MaterialArrivalDetail result = (MaterialArrivalDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialArrivalDetail instance) {
		log.debug("attaching dirty MaterialArrivalDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialArrivalDetail instance) {
		log.debug("attaching clean MaterialArrivalDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}