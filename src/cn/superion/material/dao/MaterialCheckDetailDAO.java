package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialCheckDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialCheckDetail entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialCheckDetail
  * @author MyEclipse Persistence Tools 
 */

public class MaterialCheckDetailDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialCheckDetailDAO.class);
		//property constants
	public static final String MAIN_AUTO_ID = "mainAutoId";
	public static final String SERIAL_NO = "serialNo";
	public static final String MATERIAL_CLASS = "materialClass";
	public static final String BAR_CODE = "barCode";
	public static final String MATERIAL_ID = "materialId";
	public static final String MATERIAL_CODE = "materialCode";
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_SPEC = "materialSpec";
	public static final String MATERIAL_UNITS = "materialUnits";
	public static final String AMOUNT = "amount";
	public static final String CHECK_AMOUNT = "checkAmount";
	public static final String IN_AMOUNT = "inAmount";
	public static final String OUT_AMOUNT = "outAmount";
	public static final String TRADE_PRICE = "tradePrice";
	public static final String TRADE_MONEY = "tradeMoney";
	public static final String RETAIL_PRICE = "retailPrice";
	public static final String RETAIL_MONEY = "retailMoney";
	public static final String FACTORY_CODE = "factoryCode";
	public static final String BATCH = "batch";
	public static final String POSITION = "position";
	public static final String DETAIL_REMARK = "detailRemark";



    
    public void save(MaterialCheckDetail transientInstance) {
        log.debug("saving MaterialCheckDetail instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialCheckDetail persistentInstance) {
        log.debug("deleting MaterialCheckDetail instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialCheckDetail findById( java.lang.String id) {
        log.debug("getting MaterialCheckDetail instance with id: " + id);
        try {
            MaterialCheckDetail instance = (MaterialCheckDetail) getSession()
                    .get("cn.superion.material.entity.MaterialCheckDetail", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialCheckDetail> findByExample(MaterialCheckDetail instance) {
        log.debug("finding MaterialCheckDetail instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialCheckDetail")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
	public List<MaterialCheckDetail> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialCheckDetail instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialCheckDetail as model where model." 
         						+ propertyName + "= ? order by model.serialNo";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	@SuppressWarnings("unchecked")
	public List<MaterialCheckDetail> findAll() {
		log.debug("finding all MaterialCheckDetail instances");
		try {
			String queryString = "from MaterialCheckDetail";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialCheckDetail merge(MaterialCheckDetail detachedInstance) {
        log.debug("merging MaterialCheckDetail instance");
        try {
            MaterialCheckDetail result = (MaterialCheckDetail) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialCheckDetail instance) {
        log.debug("attaching dirty MaterialCheckDetail instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialCheckDetail instance) {
        log.debug("attaching clean MaterialCheckDetail instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public void delByMainAutoId(String mainAutoId) {
		getSession().createQuery(
		"delete from MaterialCheckDetail where mainAutoId=:mainAutoId")
		.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	public List<MaterialCheckDetail> findByMainAutoId(String mainAutoId) {
		return findByProperty("mainAutoId", mainAutoId);
	}
}