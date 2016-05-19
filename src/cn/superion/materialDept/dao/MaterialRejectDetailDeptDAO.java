package cn.superion.materialDept.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.MaterialRejectDetailDept;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialRejectDetail entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.materialDept.entity.MaterialRejectDetailDept
  * @author MyEclipse Persistence Tools 
 */

public class MaterialRejectDetailDeptDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialRejectDetailDeptDAO.class);
		//property constants
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
	public static final String DISPOSE_PLACE = "disposePlace";
	public static final String DISPOSE_MODE = "disposeMode";
	public static final String DETAIL_REMARK = "detailRemark";



    
    public void save(MaterialRejectDetailDept transientInstance) {
        log.debug("saving MaterialRejectDetailDept instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialRejectDetailDept persistentInstance) {
        log.debug("deleting MaterialRejectDetailDept instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialRejectDetailDept findById( java.lang.String id) {
        log.debug("getting MaterialRejectDetail instance with id: " + id);
        try {
            MaterialRejectDetailDept instance = (MaterialRejectDetailDept) getSession()
                    .get("cn.superion.materialDept.entity.MaterialRejectDetailDept", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialRejectDetailDept> findByExample(MaterialRejectDetailDept instance) {
        log.debug("finding MaterialRejectDetailDept instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.materialDept.entity.MaterialRejectDetailDept")
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
	public List<MaterialRejectDetailDept> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialRejectDetailDept instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialRejectDetailDept as model where model." 
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
	public List<MaterialRejectDetailDept> findAll() {
		log.debug("finding all MaterialRejectDetailDept instances");
		try {
			String queryString = "from MaterialRejectDetailDept";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialRejectDetailDept merge(MaterialRejectDetailDept detachedInstance) {
        log.debug("merging MaterialRejectDetailDept instance");
        try {
            MaterialRejectDetailDept result = (MaterialRejectDetailDept) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialRejectDetailDept instance) {
        log.debug("attaching dirty MaterialRejectDetailDept instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialRejectDetailDept instance) {
        log.debug("attaching clean MaterialRejectDetailDept instance");
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
		"delete from MaterialRejectDetailDept where mainAutoId=:mainAutoId")
		.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	public List<MaterialRejectDetailDept> findByMainAutoId(String mainAutoId) {
		return findByProperty("mainAutoId", mainAutoId);
	}
}