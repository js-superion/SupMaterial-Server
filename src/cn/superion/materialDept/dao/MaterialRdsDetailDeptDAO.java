package cn.superion.materialDept.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.materialDept.entity.InpPatsBillDetail;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.VMaterialRdsDept;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialRdsDetail entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialRdsDetail
  * @author MyEclipse Persistence Tools 
 */

public class MaterialRdsDetailDeptDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialRdsDetailDeptDAO.class);

	public List<MaterialRdsDetailDept> findByMainAutoId(String fstrMainAutoId) {
		return this.findByProperty("mainAutoId", fstrMainAutoId);
	}
	
	public void deleteByMainAutoId(String fstrMainAutoId) {
		getSession().createQuery(
				"delete from MaterialRdsDetailDept where mainAutoId=:mainAutoId")
				.setString("mainAutoId", fstrMainAutoId).executeUpdate();
	}
    
    public void save(MaterialRdsDetailDept transientInstance) {
        log.debug("saving MaterialRdsDetailDept instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialRdsDetailDept persistentInstance) {
        log.debug("deleting MaterialRdsDetailDept instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialRdsDetailDept findById( java.lang.String id) {
        log.debug("getting MaterialRdsDetailDept instance with id: " + id);
        try {
            MaterialRdsDetailDept instance = (MaterialRdsDetailDept) getSession()
                    .get("cn.superion.materialDept.entity.MaterialRdsDetailDept", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialRdsDetailDept> findByExample(MaterialRdsDetailDept instance) {
        log.debug("finding MaterialRdsDetailDept instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.materialDept.entity.MaterialRdsDetailDept")
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
	public List<MaterialRdsDetailDept> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialRdsDetailDept instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialRdsDetailDept as model where model." 
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
	public List<MaterialRdsDetailDept> findAll() {
		log.debug("finding all MaterialRdsDetailDept instances");
		try {
			String queryString = "from MaterialRdsDetailDept";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialRdsDetailDept merge(MaterialRdsDetailDept detachedInstance) {
        log.debug("merging MaterialRdsDetailDept instance");
        try {
            MaterialRdsDetailDept result = (MaterialRdsDetailDept) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialRdsDetailDept instance) {
        log.debug("attaching dirty MaterialRdsDetailDept instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialRdsDetailDept instance) {
        log.debug("attaching clean MaterialRdsDetailDept instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /**
     * 根据入出库单主记录ID和入出库单明细记录serialNo查找入出库单明细记录
     * @param mainAutoId
     * @param serialNo
     * @return
     */
	public MaterialRdsDetailDept findByMainAutoIdAndSerialNo(
			String mainAutoId, Short serialNo) {
		String hql = "from MaterialRdsDetailDept where mainAutoId=:mainAutoId and serialNo=:serialNo";
		return (MaterialRdsDetailDept) getSession().createQuery(hql).setString("mainAutoId", mainAutoId).setShort("serialNo", serialNo).uniqueResult();
	}


	/**
	 * 生成yyyyMMdd+位序列，序列不足最大位数，以零补
	 * @param sequenceName
	 * @param maxLength 最大位数
	 * @return
	 */
	public String nextTimeValueFromDB(String sequenceName,int maxLength){
		String sql = "select concat(to_char(sysdate,'yyyymmdd'),lpad("+sequenceName+".nextval,"+maxLength+",'0')) from dual";
		return getSession().createSQLQuery(sql).uniqueResult().toString();
	}

	/**
	 * 根据条形码查询高值耗材信息
	 * @param fstrBarCode
	 * @return
	 * 
	 * @author 芮玉红
	 * @date 2012.9.27
	 */
	public VMaterialRdsDept findByBarCode(String fstrBarCode,boolean isAmount) {
		String hql = "from VMaterialRdsDept where  barCode in (select barCode from MaterialCurrentStockDept where barCode='"+fstrBarCode+"'";
		if(isAmount){
			hql+=" and amount>0";
		}
		hql+=" ) and rdFlag='1' and currentStatus='1')";
		
		return (VMaterialRdsDept) getSession().createQuery(hql).uniqueResult();
	}

	public CdMaterialDict findByMaterialId(String materialId) {
		String hql = "from CdMaterialDict where materialId='"+materialId+"' ";
		return (CdMaterialDict) getSession().createQuery(hql).uniqueResult();
	}

	public MaterialRdsDetailDept findByRByBarCode(String barCode) {
		String hql = "from MaterialRdsDetailDept where barCode='"+barCode+"' and mainAutoId in (select autoId from MaterialRdsMasterDept where rdFlag='1' and currentStatus='1')";
		
		return (MaterialRdsDetailDept) getSession().createQuery(hql).uniqueResult();
	}
}