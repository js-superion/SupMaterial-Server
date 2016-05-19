package cn.superion.materialDept.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.MaterialRejectMasterDept;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialRejectMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.materialDept.entity.MaterialRejectMasterDept
  * @author MyEclipse Persistence Tools 
 */

public class MaterialRejectMasterDeptDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialRejectMasterDeptDAO.class);

    
    public void save(MaterialRejectMasterDept transientInstance) {
        log.debug("saving MaterialRejectMasterDept instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialRejectMasterDept persistentInstance) {
        log.debug("deleting MaterialRejectMasterDept instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialRejectMasterDept findById( java.lang.String id) {
        log.debug("getting MaterialRejectMasterDept instance with id: " + id);
        try {
            MaterialRejectMasterDept instance = (MaterialRejectMasterDept) getSession()
                    .get("cn.superion.materialDept.entity.MaterialRejectMasterDept", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialRejectMasterDept> findByExample(MaterialRejectMasterDept instance) {
        log.debug("finding MaterialRejectMasterDept instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.materialDept.entity.MaterialRejectMasterDept")
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
	public List<MaterialRejectMasterDept> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialRejectMasterDept instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialRejectMasterDept as model where model." 
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
	public List<MaterialRejectMasterDept> findAll() {
		log.debug("finding all MaterialRejectMaster instances");
		try {
			String queryString = "from MaterialRejectMaster";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialRejectMasterDept merge(MaterialRejectMasterDept detachedInstance) {
        log.debug("merging MaterialRejectMasterDept instance");
        try {
            MaterialRejectMasterDept result = (MaterialRejectMasterDept) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialRejectMasterDept instance) {
        log.debug("attaching dirty MaterialRejectMasterDept instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialRejectMasterDept instance) {
        log.debug("attaching clean MaterialRejectMasterDept instance");
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
	 *            <li>beginBillDate 起始报损日期</li>
	 *            <li>endBillDate 结束报损日期</li>
	 *            <li>beginBillNo 起始报损单号</li>
	 *            <li>endBillNo 结束报损单号</li>
	 *            <li>outDeptCode 报损部门</li>
	 *            <li>personId 经手人</li>
	 *            <li>currentStatus 当前状态</li>
	 *            以下为从表条件
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
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
		String outDeptCode = (String)conditions.get("outDeptCode");
		String personId = (String)conditions.get("personId");
		String currentStatus = (String)conditions.get("currentStatus");
		String materialClass = (String)conditions.get("materialClass");
		String beginMaterialCode = (String)conditions.get("beginMaterialCode");
		String endMaterialCode = (String)conditions.get("endMaterialCode");
		//主表条件
		StringBuilder shql = new StringBuilder("select autoId from MaterialRejectMasterDept m where unitsCode='").append(unitsCode).append("'");
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
		if(outDeptCode != null && !"".equals(outDeptCode)){
			shql.append(" and outDeptCode=:outDeptCode");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			shql.append(" and currentStatus=:currentStatus");
		}
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialRejectDetailDept d where d.mainAutoId=m.autoId");
		if(materialClass != null && !"".equals(materialClass)){
			flag = true;
			shql2.append(" and materialClass=:materialClass");
		}
		if(beginMaterialCode != null && !"".equals(beginMaterialCode)){
			flag = true;
			shql2.append(" and d.materialCode>=:beginMaterialCode");
		}
		if(endMaterialCode != null && !"".equals(endMaterialCode)){
			flag = true;
			shql2.append(" and d.materialCode<=:endMaterialCode");
		}
		if(flag){
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String storageCode,
			String billNo) {
		String hql = "select count(*) from MaterialRejectMasterDept where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}
}