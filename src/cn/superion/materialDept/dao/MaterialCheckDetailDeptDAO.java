package cn.superion.materialDept.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.MaterialCheckDetailDept;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialCheckDetail entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.materialDept.entity.MaterialCheckDetailDept
  * @author MyEclipse Persistence Tools 
 */

public class MaterialCheckDetailDeptDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialCheckDetailDeptDAO.class);

    public void save(MaterialCheckDetailDept transientInstance) {
        log.debug("saving MaterialCheckDetailDept instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialCheckDetailDept persistentInstance) {
        log.debug("deleting MaterialCheckDetailDept instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialCheckDetailDept findById( java.lang.String id) {
        log.debug("getting MaterialCheckDetailDept instance with id: " + id);
        try {
            MaterialCheckDetailDept instance = (MaterialCheckDetailDept) getSession()
                    .get("cn.superion.materialDept.entity.MaterialCheckDetailDept", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialCheckDetailDept> findByExample(MaterialCheckDetailDept instance) {
        log.debug("finding MaterialCheckDetailDept instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.materialDept.entity.MaterialCheckDetailDept")
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
	public List<MaterialCheckDetailDept> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialCheckDetailDept instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialCheckDetailDept as model where model." 
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
	public List<MaterialCheckDetailDept> findAll() {
		log.debug("finding all MaterialCheckDetailDept instances");
		try {
			String queryString = "from MaterialCheckDetailDept";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialCheckDetailDept merge(MaterialCheckDetailDept detachedInstance) {
        log.debug("merging MaterialCheckDetailDept instance");
        try {
            MaterialCheckDetailDept result = (MaterialCheckDetailDept) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialCheckDetailDept instance) {
        log.debug("attaching dirty MaterialCheckDetailDept instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialCheckDetailDept instance) {
        log.debug("attaching clean MaterialCheckDetailDept instance");
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
		"delete from MaterialCheckDetailDept where mainAutoId=:mainAutoId")
		.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	public List<MaterialCheckDetailDept> findByMainAutoId(String mainAutoId) {
		return findByProperty("mainAutoId", mainAutoId);
	}
}