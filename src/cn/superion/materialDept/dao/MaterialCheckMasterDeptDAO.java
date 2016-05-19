package cn.superion.materialDept.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.MaterialCheckMasterDept;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialCheckMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.materialDept.entity.MaterialCheckMasterDept
  * @author MyEclipse Persistence Tools 
 */

public class MaterialCheckMasterDeptDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialCheckMasterDeptDAO.class);

    public void save(MaterialCheckMasterDept transientInstance) {
        log.debug("saving MaterialCheckMasterDept instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialCheckMasterDept persistentInstance) {
        log.debug("deleting MaterialCheckMasterDept instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialCheckMasterDept findById( java.lang.String id) {
        log.debug("getting MaterialCheckMasterDept instance with id: " + id);
        try {
            MaterialCheckMasterDept instance = (MaterialCheckMasterDept) getSession()
                    .get("cn.superion.materialDept.entity.MaterialCheckMasterDept", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialCheckMasterDept> findByExample(MaterialCheckMasterDept instance) {
        log.debug("finding MaterialCheckMasterDept instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.materialDept.entity.MaterialCheckMasterDept")
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
	public List<MaterialCheckMasterDept> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialCheckMasterDept instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialCheckMasterDept as model where model." 
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
	public List<MaterialCheckMasterDept> findAll() {
		log.debug("finding all MaterialCheckMasterDept instances");
		try {
			String queryString = "from MaterialCheckMasterDept";
	         Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialCheckMasterDept merge(MaterialCheckMasterDept detachedInstance) {
        log.debug("merging MaterialCheckMasterDept instance");
        try {
            MaterialCheckMasterDept result = (MaterialCheckMasterDept) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialCheckMasterDept instance) {
        log.debug("attaching dirty MaterialCheckMasterDept instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialCheckMasterDept instance) {
        log.debug("attaching clean MaterialCheckMasterDept instance");
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
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始盘点单号</li>
	 *            <li>endBillNo 结束盘点单号</li>
	 *            <li>beginBillDate 起始盘点日期</li>
	 *            <li>endBillDate 结束盘点日期</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            </ul>
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String storageCode = (String)conditions.get("storageCode");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String materialClass = (String)conditions.get("materialClass");
		String materialCode = (String)conditions.get("materialCode");
		String materialName = (String)conditions.get("materialName");
		//主表条件
		StringBuilder shql = new StringBuilder("select autoId from MaterialCheckMasterDept m where unitsCode='").append(unitsCode).append("'");
		if(storageCode != null && !"".equals(storageCode)){
			shql.append(" and storageCode=:storageCode");
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
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialCheckDetailDept d where d.mainAutoId=m.autoId");
		if(materialClass != null && !"".equals(materialClass)){
			flag = true;
			shql2.append(" and materialClass=:materialClass");
		}
		if(materialCode != null && !"".equals(materialCode)){
			flag = true;
			shql2.append(" and materialCode=:materialCode");
		}
		if(materialName != null && !"".equals(materialName)){
			flag = true;
			shql2.append(" and materialName like :materialName");
			conditions.put("materialName", "%"+materialName+"%");
		}
		if(flag){
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String storageCode,
			String billNo) {
		String hql = "select count(*) from MaterialCheckMasterDept where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}
}