package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialOrderDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialOrderDetail entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialOrderDetail
  * @author MyEclipse Persistence Tools 
 */

public class MaterialOrderDetailDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialOrderDetailDAO.class);
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
	public static final String INPUT_AMOUNT = "inputAmount";
	public static final String ARRIVAL_AMOUNT = "arrivalAmount";
	public static final String SOURCE_BILL_NO = "sourceBillNo";
	public static final String SOURCE_SERIAL_NO = "sourceSerialNo";
	public static final String CURRENT_STATUS = "currentStatus";
	public static final String DETAIL_REMARK = "detailRemark";

	/**
	 * 审核明细记录
	 * 
	 * @param mainAutoId
	 */
	public void verify(String mainAutoId) {
		getSession()
				.createQuery(
						"update MaterialOrderDetail set currentStatus='1' where mainAutoId=:mainAutoId")
				.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	public List<MaterialOrderDetail> findByMainAutoId(String mainAutoId) {
		return findByProperty("mainAutoId", mainAutoId);
	}
	public List<MaterialOrderDetail> findByMainAutoId2(String mainAutoId) {
	      try {
	         String queryString = "from MaterialOrderDetail as model where model.currentStatus in ('1','2') and model.mainAutoId = ?" ;
	         Query queryObject = getSession().createQuery(queryString).setString(0, mainAutoId);
			 return queryObject.list();
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
	}
	/**
	 * 删除明细记录
	 * 
	 * @param mainAutoId
	 */
	public void delByMainAutoId(String mainAutoId) {
		getSession().createQuery(
				"delete from MaterialOrderDetail where mainAutoId=:mainAutoId")
				.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	/**
	 * 校验采购订单明细是否已使用（审核，执行，关闭），有已使用明细记录的单据不能删除
	 * 
	 * @param mainAutoId
	 * @return
	 */
	public boolean checkUsedStatus(String mainAutoId) {
		String hql = "select count(*) from MaterialOrderDetail where mainAutoId=:mainAutoId and currentStatus<>'0'";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString(
				"mainAutoId", mainAutoId).uniqueResult().toString());
		return cnt > 0;
	}
    
    public void save(MaterialOrderDetail transientInstance) {
        log.debug("saving MaterialOrderDetail instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialOrderDetail persistentInstance) {
        log.debug("deleting MaterialOrderDetail instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialOrderDetail findById( java.lang.String id) {
        log.debug("getting MaterialOrderDetail instance with id: " + id);
        try {
            MaterialOrderDetail instance = (MaterialOrderDetail) getSession()
                    .get("cn.superion.material.entity.MaterialOrderDetail", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialOrderDetail> findByExample(MaterialOrderDetail instance) {
        log.debug("finding MaterialOrderDetail instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialOrderDetail")
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
	public List<MaterialOrderDetail> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialOrderDetail instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialOrderDetail as model where model." 
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
	public List<MaterialOrderDetail> findAll() {
		log.debug("finding all MaterialOrderDetail instances");
		try {
			String queryString = "from MaterialOrderDetail";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialOrderDetail merge(MaterialOrderDetail detachedInstance) {
        log.debug("merging MaterialOrderDetail instance");
        try {
            MaterialOrderDetail result = (MaterialOrderDetail) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialOrderDetail instance) {
        log.debug("attaching dirty MaterialOrderDetail instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialOrderDetail instance) {
        log.debug("attaching clean MaterialOrderDetail instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}