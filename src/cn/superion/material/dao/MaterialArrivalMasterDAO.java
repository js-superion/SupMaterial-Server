package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialArrivalMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialArrivalMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialArrivalMaster
  * @author MyEclipse Persistence Tools 
 */

public class MaterialArrivalMasterDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialArrivalMasterDAO.class);
		//property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String BILL_NO = "billNo";
	public static final String OPERATION_TYPE = "operationType";
	public static final String STOCK_TYPE = "stockType";
	public static final String SALER_CODE = "salerCode";
	public static final String SALER_NAME = "salerName";
	public static final String TRANSPORT = "transport";
	public static final String DEPT_CODE = "deptCode";
	public static final String PERSON_ID = "personId";
	public static final String TOTAL_COSTS = "totalCosts";
	public static final String REMARK = "remark";
	public static final String MAKER = "maker";
	public static final String VERIFIER = "verifier";
	public static final String CURRENT_STATUS = "currentStatus";



    
    public void save(MaterialArrivalMaster transientInstance) {
        log.debug("saving MaterialArrivalMaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialArrivalMaster persistentInstance) {
        log.debug("deleting MaterialArrivalMaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialArrivalMaster findById( java.lang.String id) {
        log.debug("getting MaterialArrivalMaster instance with id: " + id);
        try {
            MaterialArrivalMaster instance = (MaterialArrivalMaster) getSession()
                    .get("cn.superion.material.entity.MaterialArrivalMaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialArrivalMaster> findByExample(MaterialArrivalMaster instance) {
        log.debug("finding MaterialArrivalMaster instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialArrivalMaster")
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
	public List<MaterialArrivalMaster> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialArrivalMaster instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialArrivalMaster as model where model." 
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
	public List<MaterialArrivalMaster> findAll() {
		log.debug("finding all MaterialArrivalMaster instances");
		try {
			String queryString = "from MaterialArrivalMaster";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialArrivalMaster merge(MaterialArrivalMaster detachedInstance) {
        log.debug("merging MaterialArrivalMaster instance");
        try {
            MaterialArrivalMaster result = (MaterialArrivalMaster) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialArrivalMaster instance) {
        log.debug("attaching dirty MaterialArrivalMaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialArrivalMaster instance) {
        log.debug("attaching clean MaterialArrivalMaster instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}