package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialFaMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialFaMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialFaMaster
  * @author MyEclipse Persistence Tools 
 */

public class MaterialFaMasterDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialFaMasterDAO.class);
	

    
    public void save(MaterialFaMaster transientInstance) {
        log.debug("saving MaterialFaMaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialFaMaster persistentInstance) {
        log.debug("deleting MaterialFaMaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialFaMaster findById( java.lang.String id) {
        log.debug("getting MaterialFaMaster instance with id: " + id);
        try {
            MaterialFaMaster instance = (MaterialFaMaster) getSession()
                    .get("cn.superion.material.entity.MaterialFaMaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialFaMaster> findByExample(MaterialFaMaster instance) {
        log.debug("finding MaterialFaMaster instance by example");
        try {
            List<MaterialFaMaster> results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialFaMaster")
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
	public List<MaterialFaMaster> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialFaMaster instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialFaMaster as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}
	
    public MaterialFaMaster merge(MaterialFaMaster detachedInstance) {
        log.debug("merging MaterialFaMaster instance");
        try {
            MaterialFaMaster result = (MaterialFaMaster) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialFaMaster instance) {
        log.debug("attaching dirty MaterialFaMaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialFaMaster instance) {
        log.debug("attaching clean MaterialFaMaster instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /**
     * 
     * @param unitsCode
     * @param conditions 包含：
	 *            <ul>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>beginBillDate 起始结算日期</li>
	 *            <li>endBillDate 结束结算日期</li>
	 *            <li>beginBillNo 起始结算单号</li>
	 *            <li>endBillNo 结束结算单号</li>
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String salerCode  = (String)conditions.get("salerCode");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		String currentStatus = (String)conditions.get("currentStatus");
		StringBuilder shql = new StringBuilder("select autoId from MaterialFaMaster where unitsCode='").append(unitsCode).append("'");
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and salerCode=:salerCode");
		}
		if(beginBillNo != null && !"".equals(beginBillNo)){
			shql.append(" and billNo>=:beginBillNo");
		}
		if(endBillNo != null && !"".equals(endBillNo)){
			shql.append(" and billNo<=:endBillNo");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}
}