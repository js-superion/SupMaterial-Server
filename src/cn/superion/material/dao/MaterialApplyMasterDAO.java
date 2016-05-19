package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialApplyMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialApplyMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialApplyMaster
  * @author MyEclipse Persistence Tools 
 */

public class MaterialApplyMasterDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialApplyMasterDAO.class);
		//property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String BILL_NO = "billNo";
	public static final String DEPT_CODE = "deptCode";
	public static final String PERSON_ID = "personId";
	public static final String SALER_CODE = "salerCode";
	public static final String SALER_NAME = "salerName";
	public static final String REMARK = "remark";
	public static final String MAKER = "maker";
	public static final String VERIFIER = "verifier";
	public static final String CURRENT_STATUS = "currentStatus";



    
    public void save(MaterialApplyMaster transientInstance) {
        log.debug("saving MaterialApplyMaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialApplyMaster persistentInstance) {
        log.debug("deleting MaterialApplyMaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialApplyMaster findById( java.lang.String id) {
        log.debug("getting MaterialApplyMaster instance with id: " + id);
        try {
            MaterialApplyMaster instance = (MaterialApplyMaster) getSession()
                    .get("cn.superion.material.entity.MaterialApplyMaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialApplyMaster> findByExample(MaterialApplyMaster instance) {
        log.debug("finding MaterialApplyMaster instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialApplyMaster")
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
	public List<MaterialApplyMaster> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialApplyMaster instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialApplyMaster as model where model." 
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
	public List<MaterialApplyMaster> findAll() {
		log.debug("finding all MaterialApplyMaster instances");
		try {
			String queryString = "from MaterialApplyMaster";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialApplyMaster merge(MaterialApplyMaster detachedInstance) {
        log.debug("merging MaterialApplyMaster instance");
        try {
            MaterialApplyMaster result = (MaterialApplyMaster) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialApplyMaster instance) {
        log.debug("attaching dirty MaterialApplyMaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialApplyMaster instance) {
        log.debug("attaching clean MaterialApplyMaster instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public MaterialApplyMaster findByBillNo(String unitsCode, String billNo) {
		String hql = "from MaterialApplyMaster where unitsCode=:unitsCode and billNo=:billNo";
		return (MaterialApplyMaster) getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("billNo", billNo).uniqueResult();
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginBillNo 起始申请单号</li>
	 *            <li>endBillNo 结束申请单号</li>
	 *            <li>beginBillDate 起始申请日期</li>
	 *            <li>endBillDate 结束申请日期</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String storageCode = (String)conditions.get("storageCode");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String salerCode = (String)conditions.get("salerCode");
		String deptCode = (String)conditions.get("deptCode");
		String personId = (String)conditions.get("personId");
		StringBuilder shql = new StringBuilder("select autoId from MaterialApplyMaster where unitsCode='").append(unitsCode).append("'");
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
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and salerCode=:salerCode");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String storageCode,
			String billNo) {
		String hql = "select count(*) from MaterialApplyMaster where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>salerCode 供应商编码</li>
	 *            <li>beginBillDate 起始申请日期</li>
	 *            <li>endBillDate 结束申请日期</li>
	 *            <li>billNo 申请单号</li>
	 *            以下为明细记录条件
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findCheckedByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		boolean flag = false;
		String materialClass = (String)conditions.get("materialClass");
		String materialCode = (String)conditions.get("materialCode");
		String salerCode = (String)conditions.get("salerCode");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String billNo = (String)conditions.get("billNo");
		StringBuilder shql = new StringBuilder("from MaterialApplyMaster m where currentStatus='1' and unitsCode='").append(unitsCode).append("'");
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and salerCode=:salerCode");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(billNo != null && !"".equals(billNo)){
			shql.append(" and billNo=:billNo");
		}
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialApplyDetail d where d.mainAutoId=m.autoId");
		if(materialClass != null && !"".equals(materialClass)){
			flag = true;
			shql2.append(" and d.materialClass=:materialClass");
		}
		if(materialCode != null && !"".equals(materialCode)){
			flag = true;
			shql2.append(" and d.materialCode=:materialCode");
		}
		if(flag)
			shql.append(" and exists(").append(shql2).append(")");
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<MaterialApplyMaster> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
}