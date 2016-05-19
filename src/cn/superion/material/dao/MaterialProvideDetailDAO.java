package cn.superion.material.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialProvideDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialProvideDetail entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialProvideDetail
  * @author MyEclipse Persistence Tools 
 */

public class MaterialProvideDetailDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialProvideDetailDAO.class);
		//property constants
	public static final String MAIN_AUTO_ID = "mainAutoId";
	public static final String SERIAL_NO = "serialNo";
	public static final String MATERIAL_CLASS = "materialClass";
	public static final String MATERIAL_ID = "materialId";
	public static final String MATERIAL_CODE = "materialCode";
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_SPEC = "materialSpec";
	public static final String FACTORY_CODE = "factoryCode";
	public static final String MATERIAL_UNITS = "materialUnits";
	public static final String AMOUNT = "amount";
	public static final String CHECK_AMOUNT = "checkAmount";
	public static final String TRADE_PRICE = "tradePrice";
	public static final String TRADE_MONEY = "tradeMoney";
	public static final String RETAIL_PRICE = "retailPrice";
	public static final String RETAIL_MONEY = "retailMoney";
	public static final String DETAIL_REMARK = "detailRemark";



    
    public void save(MaterialProvideDetail transientInstance) {
        log.debug("saving MaterialProvideDetail instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialProvideDetail persistentInstance) {
        log.debug("deleting MaterialProvideDetail instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialProvideDetail findById( java.lang.String id) {
        log.debug("getting MaterialProvideDetail instance with id: " + id);
        try {
            MaterialProvideDetail instance = (MaterialProvideDetail) getSession()
                    .get("cn.superion.material.entity.MaterialProvideDetail", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<MaterialProvideDetail> findByMainAutoIds(String[] fstrMainAutoIds,String storageMaterialSign) {
		StringBuilder hql = new StringBuilder();
		hql.append("from MaterialProvideDetail as model where model.mainAutoId in (:fstrMainAutoIds)");
		
		if(storageMaterialSign!=null && !"".equals(storageMaterialSign))
		{
			hql.append(" and model.storageMaterialSign ="+storageMaterialSign);
		}
		return getSession().createQuery(hql.toString()).setParameterList("fstrMainAutoIds", fstrMainAutoIds).list();
	}
    
    @SuppressWarnings("unchecked")
	public List<MaterialProvideDetail> findSendDetailByAutoIdAndOtherCons(String fstrMainAutoId,String storageMaterialSign) {
		StringBuilder hql = new StringBuilder();
		hql.append("from MaterialProvideDetail as model where model.mainAutoId =:fstrMainAutoId and model.storageMaterialSign = :storageMaterialSign");
		if(storageMaterialSign!=null && !"".equals(storageMaterialSign))
		{
			hql.append(" and model.storageMaterialSign = "+storageMaterialSign);
		}
		return getSession().createQuery(hql.toString()).setParameter("fstrMainAutoId", fstrMainAutoId).setString("storageMaterialSign", storageMaterialSign).list();
	}
    
    @SuppressWarnings("unchecked")
	public List<MaterialProvideDetail> findByExample(MaterialProvideDetail instance) {
        log.debug("finding MaterialProvideDetail instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialProvideDetail")
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
	public List<MaterialProvideDetail> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialProvideDetail instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialProvideDetail as model where model." 
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
	public List<MaterialProvideDetail> findAll() {
		log.debug("finding all MaterialProvideDetail instances");
		try {
			String queryString = "from MaterialProvideDetail";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialProvideDetail merge(MaterialProvideDetail detachedInstance) {
        log.debug("merging MaterialProvideDetail instance");
        try {
            MaterialProvideDetail result = (MaterialProvideDetail) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialProvideDetail instance) {
        log.debug("attaching dirty MaterialProvideDetail instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialProvideDetail instance) {
        log.debug("attaching clean MaterialProvideDetail instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /**
     * 领用出库时更新已出库数量
     * @param autoId
     * @param checkAmount
     */
    public void updateCheckAmount(String autoId,Double checkAmount){
    	String hql = "update MaterialProvideDetail set checkAmount=:checkAmount where autoId=:autoId";
    	getSession().createQuery(hql).setString("autoId", autoId).setDouble("checkAmount", checkAmount).executeUpdate();
    }

    /**
     * 还原领用申请单明细记录的已发数量为0
     * @param mainAutoId
     */
	public void reduceCheckAmount(String mainAutoId) {
		String hql = "update MaterialProvideDetail set checkAmount=0 where mainAutoId=:mainAutoId";
		getSession().createQuery(hql).setString("mainAutoId", mainAutoId).executeUpdate();
	}

	public List<MaterialProvideDetail> findByMainAutoId(String fstrMainAutoId) {
		return this.findByProperty("mainAutoId", fstrMainAutoId);
	}

	/**
	 * 根据业务号查询领用申请明细记录列表
	 * @param unitsCode
	 * @param billNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialProvideDetail> findByBillNo(String unitsCode,String storageCode,String billNo) {
		String hql = "select d from MaterialProvideDetail d,MaterialProvideMaster m where d.mainAutoId=m.autoId and m.unitsCode=:unitsCode and m.storageCode=:storageCode and m.billNo=:billNo";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).list();
	}

	/**
	 * 根据业务号和物资ID，更新物资的实发数量
	 * @param unitsCode
	 * @param operationNo
	 * @param materialId
	 * @param checkAmount
	 */
	public void updateCheckAmount(String unitsCode,String billNo, String materialId,
			Double checkAmount) {
		String hql = "update MaterialProvideDetail d set checkAmount=:checkAmount where exists(from MaterialProvideMaster m where m.autoId=d.mainAutoId and m.unitsCode=:unitsCode and m.billNo=:billNo) and materialId=:materialId";
		getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("billNo", billNo).setString("materialId", materialId).setDouble("checkAmount", checkAmount).executeUpdate();
	}

	public void delByMainAutoId(String mainAutoId) {
		getSession().createQuery(
		"delete from MaterialProvideDetail where mainAutoId=:mainAutoId")
		.setString("mainAutoId", mainAutoId).executeUpdate();
	}
}