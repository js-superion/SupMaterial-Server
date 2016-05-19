package cn.superion.materialDept.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.CommSysParameter;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for CommSysParameter entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.materialDept.dao.CommSysParameter
  * @author MyEclipse Persistence Tools 
 */

public class CommSysParameterDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CommSysParameterDAO.class);
		//property constants
	public static final String PARA_NAME = "paraName";
	public static final String PARA_VALUE = "paraValue";
	public static final String PARA_DEFAULT = "paraDefault";
	public static final String PARA_DESCRIPTION = "paraDescription";



    
    public void save(CommSysParameter transientInstance) {
        log.debug("saving CommSysParameter instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(CommSysParameter persistentInstance) {
        log.debug("deleting CommSysParameter instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public String findById(String unitsCode,String menuNo,String paraCode) {
        log.debug("getting CommSysParameter instance with id");
        try {
            String sql="select t.paraValue from CommSysParameter t where t.unitsCode='"+unitsCode+"' and t.menuNo='"+menuNo+"' and t.paraCode = '"+paraCode+"'";
            Object result=getSession().createQuery(sql).uniqueResult();
            if(result==null){
            	return "";
            }
            return  result.toString();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(CommSysParameter instance) {
        log.debug("finding CommSysParameter instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.materialDept.dao.CommSysParameter")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding CommSysParameter instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from CommSysParameter as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByParaName(Object paraName
	) {
		return findByProperty(PARA_NAME, paraName
		);
	}
	
	public List findByParaValue(Object paraValue
	) {
		return findByProperty(PARA_VALUE, paraValue
		);
	}
	
	public List findByParaDefault(Object paraDefault
	) {
		return findByProperty(PARA_DEFAULT, paraDefault
		);
	}
	
	public List findByParaDescription(Object paraDescription
	) {
		return findByProperty(PARA_DESCRIPTION, paraDescription
		);
	}
	

	public List findAll() {
		log.debug("finding all CommSysParameter instances");
		try {
			String queryString = "from CommSysParameter";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public CommSysParameter merge(CommSysParameter detachedInstance) {
        log.debug("merging CommSysParameter instance");
        try {
            CommSysParameter result = (CommSysParameter) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(CommSysParameter instance) {
        log.debug("attaching dirty CommSysParameter instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(CommSysParameter instance) {
        log.debug("attaching clean CommSysParameter instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}