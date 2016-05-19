package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialPlanMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialPlanMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialPlanMaster
  * @author MyEclipse Persistence Tools 
 */

public class MaterialPlanMasterDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialPlanMasterDAO.class);
		//property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String BILL_NO = "billNo";
	public static final String OPERATION_TYPE = "operationType";
	public static final String STOCK_TYPE = "stockType";
	public static final String DEPT_CODE = "deptCode";
	public static final String PERSON_ID = "personId";
	public static final String TOTAL_COSTS = "totalCosts";
	public static final String REMARK = "remark";
	public static final String MAKER = "maker";
	public static final String VERIFIER = "verifier";
	public static final String DATA_SOURCE = "dataSource";



    
    public void save(MaterialPlanMaster transientInstance) {
        log.debug("saving MaterialPlanMaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialPlanMaster persistentInstance) {
        log.debug("deleting MaterialPlanMaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialPlanMaster findById( java.lang.String id) {
        log.debug("getting MaterialPlanMaster instance with id: " + id);
        try {
            MaterialPlanMaster instance = (MaterialPlanMaster) getSession()
                    .get("cn.superion.material.entity.MaterialPlanMaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialPlanMaster> findByExample(MaterialPlanMaster instance) {
        log.debug("finding MaterialPlanMaster instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialPlanMaster")
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
	public List<MaterialPlanMaster> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialPlanMaster instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialPlanMaster as model where model." 
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
	public List<MaterialPlanMaster> findAll() {
		log.debug("finding all MaterialPlanMaster instances");
		try {
			String queryString = "from MaterialPlanMaster";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialPlanMaster merge(MaterialPlanMaster detachedInstance) {
        log.debug("merging MaterialPlanMaster instance");
        try {
            MaterialPlanMaster result = (MaterialPlanMaster) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialPlanMaster instance) {
        log.debug("attaching dirty MaterialPlanMaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialPlanMaster instance) {
        log.debug("attaching clean MaterialPlanMaster instance");
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
     * @param conditions 条件：
	 *            <ul>
	 *            <li>dataSource 标示计划或请购</li>
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo起始单据编号</li>
	 *            <li>endBillNo结束单据编号</li>
	 *            <li>beginBillDate起始单据日期</li>
	 *            <li>endBillDate结束单据日期</li>
	 *            以下为明细记录条件
	 *            <li>beginAdviceBookDate起始订货日期</li>
	 *            <li>endAdviceBookDate结束订货日期</li>
	 *            <li>beginMaterialCode起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>salerCode供应商</li>
	 *            <li>currentStatus当前状态</li>
	 *            </ul>
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String dataSource = (String)conditions.get("dataSource");
		String operationType = (String)conditions.get("operationType");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		Date beginAdviceBookDate = (Date)conditions.get("beginAdviceBookDate");
		Date endAdviceBookDate = (Date)conditions.get("endAdviceBookDate");
		String beginMaterialCode = (String)conditions.get("beginMaterialCode");
		String endMaterialCode = (String)conditions.get("endMaterialCode");
		String salerCode = (String)conditions.get("salerCode");
		String currentStatus = (String)conditions.get("currentStatus");
		//主表条件
		StringBuilder shql = new StringBuilder("select autoId from MaterialPlanMaster m where unitsCode='").append(unitsCode).append("'");
		if(dataSource != null && !"".equals(dataSource)){
			shql.append(" and dataSource=:dataSource");
		}
		if(operationType != null && !"".equals(operationType)){
			shql.append(" and operationType=:operationType");
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
		StringBuilder shql2 = new StringBuilder("from MaterialPlanDetail d where d.mainAutoId=m.autoId");
		if(beginAdviceBookDate != null){
			flag = true;
			shql2.append(" and d.adviceBookDate>=:beginAdviceBookDate");
		}
		if(endAdviceBookDate != null){
			flag = true;
			shql2.append(" and d.adviceBookDate<=:endAdviceBookDate");
		}
		if(beginMaterialCode != null && !"".equals(beginMaterialCode)){
			flag = true;
			shql2.append(" and d.materialCode>=:beginMaterialCode");
		}
		if(endMaterialCode != null && !"".equals(endMaterialCode)){
			flag = true;
			shql2.append(" and d.materialCode<=:endMaterialCode");
		}
		if(salerCode != null && !"".equals(salerCode)){
			flag = true;
			shql2.append(" and d.salerCode=:salerCode");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			flag = true;
			shql2.append(" and d.currentStatus=:currentStatus");
		}
		if(flag){
			shql.append(" and exists(").append(shql2).append(")");
		}
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String storageCode,
			String billNo) {
		String hql = "select count(*) from MaterialPlanMaster where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}
}