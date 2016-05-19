package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialCheckMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialCheckMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialCheckMaster
  * @author MyEclipse Persistence Tools 
 */

public class MaterialCheckMasterDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialCheckMasterDAO.class);
		//property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String BILL_NO = "billNo";
	public static final String DEPT_CODE = "deptCode";
	public static final String PERSON_ID = "personId";
	public static final String REMARK = "remark";
	public static final String MAKER = "maker";
	public static final String VERIFIER = "verifier";
	public static final String CURRENT_STATUS = "currentStatus";



    
    public void save(MaterialCheckMaster transientInstance) {
        log.debug("saving MaterialCheckMaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialCheckMaster persistentInstance) {
        log.debug("deleting MaterialCheckMaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialCheckMaster findById( java.lang.String id) {
        log.debug("getting MaterialCheckMaster instance with id: " + id);
        try {
            MaterialCheckMaster instance = (MaterialCheckMaster) getSession()
                    .get("cn.superion.material.entity.MaterialCheckMaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialCheckMaster> findByExample(MaterialCheckMaster instance) {
        log.debug("finding MaterialCheckMaster instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialCheckMaster")
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
	public List<MaterialCheckMaster> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialCheckMaster instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialCheckMaster as model where model." 
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
	public List<MaterialCheckMaster> findAll() {
		log.debug("finding all MaterialCheckMaster instances");
		try {
			String queryString = "from MaterialCheckMaster";
	         Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialCheckMaster merge(MaterialCheckMaster detachedInstance) {
        log.debug("merging MaterialCheckMaster instance");
        try {
            MaterialCheckMaster result = (MaterialCheckMaster) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialCheckMaster instance) {
        log.debug("attaching dirty MaterialCheckMaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialCheckMaster instance) {
        log.debug("attaching clean MaterialCheckMaster instance");
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
		StringBuilder shql = new StringBuilder("select autoId from MaterialCheckMaster m where unitsCode='").append(unitsCode).append("'");
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
		StringBuilder shql2 = new StringBuilder("from MaterialCheckDetail d where d.mainAutoId=m.autoId");
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
		String hql = "select count(*) from MaterialCheckMaster where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}
}