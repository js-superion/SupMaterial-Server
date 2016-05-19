package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialOrderMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialOrderMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialOrderMaster
  * @author MyEclipse Persistence Tools 
 */

public class MaterialOrderMasterDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialOrderMasterDAO.class);
		//property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String BILL_NO = "billNo";
	public static final String OPERATION_TYPE = "operationType";
	public static final String STOCK_TYPE = "stockType";
	public static final String SALER_CODE = "salerCode";
	public static final String SALER_NAME = "salerName";
	public static final String DEPT_CODE = "deptCode";
	public static final String PERSON_ID = "personId";
	public static final String PAY_CONDITION = "payCondition";
	public static final String TOTAL_COSTS = "totalCosts";
	public static final String REMARK = "remark";
	public static final String MAKER = "maker";
	public static final String VERIFIER = "verifier";



    
    public void save(MaterialOrderMaster transientInstance) {
        log.debug("saving MaterialOrderMaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MaterialOrderMaster persistentInstance) {
        log.debug("deleting MaterialOrderMaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialOrderMaster findById( java.lang.String id) {
        log.debug("getting MaterialOrderMaster instance with id: " + id);
        try {
            MaterialOrderMaster instance = (MaterialOrderMaster) getSession()
                    .get("cn.superion.material.entity.MaterialOrderMaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialOrderMaster> findByExample(MaterialOrderMaster instance) {
        log.debug("finding MaterialOrderMaster instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialOrderMaster")
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
	public List<MaterialOrderMaster> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialOrderMaster instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialOrderMaster as model where model." 
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
	public List<MaterialOrderMaster> findAll() {
		log.debug("finding all MaterialOrderMaster instances");
		try {
			String queryString = "from MaterialOrderMaster";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MaterialOrderMaster merge(MaterialOrderMaster detachedInstance) {
        log.debug("merging MaterialOrderMaster instance");
        try {
            MaterialOrderMaster result = (MaterialOrderMaster) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialOrderMaster instance) {
        log.debug("attaching dirty MaterialOrderMaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialOrderMaster instance) {
        log.debug("attaching clean MaterialOrderMaster instance");
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
	 *            <li>operationType 业务类型</li>
	 *            <li>beginBillNo 起始订单编号</li>
	 *            <li>endBillNo 结束订单编号</li>
	 *            <li>beginBillDate 起始订单日期</li>
	 *            <li>endBillDate 结束订单日期</li>
	 *            <li>salerCode 供应单位</li>
	 *            <li>deptCode 部门</li>
	 *            <li>personId 业务员</li>
	 *            以下为明细记录条件
	 *            <li>currentStatus 当前状态</li>
	 *            </ul>
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<Object> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String operationType = (String)conditions.get("operationType");
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String salerCode = (String)conditions.get("salerCode");
		String deptCode = (String)conditions.get("deptCode");
		String personId = (String)conditions.get("personId");
		String currentStatus = (String)conditions.get("currentStatus");
		//主表条件
		StringBuilder shql = new StringBuilder("select autoId from MaterialOrderMaster m where unitsCode='").append(unitsCode).append("'");
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
		if(salerCode != null && !"".equals(salerCode)){
			shql.append(" and salerCode=:salerCode");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialOrderDetail d where d.mainAutoId=m.autoId");
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
		String hql = "select count(*) from MaterialOrderMaster where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
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
	 *            <li>beginBillDate 起始订单日期</li>
	 *            <li>endBillDate 结束订单日期</li>
	 *            <li>billNo 订单号</li>
	 *            以下为明细记录条件
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findCheckedByCondition(int start, int limit,
			String unitsCode, Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String materialClass = (String)conditions.get("materialClass");
		String materialCode = (String)conditions.get("materialCode");
		String salerCode = (String)conditions.get("salerCode");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String billNo = (String)conditions.get("billNo");
		StringBuilder shql = new StringBuilder("from MaterialOrderMaster m where unitsCode='").append(unitsCode).append("'");
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
		StringBuilder shql2 = new StringBuilder("from MaterialOrderDetail d where d.mainAutoId=m.autoId and d.currentStatus in ('1','2')");
		if(materialClass != null && !"".equals(materialClass)){
			shql2.append(" and d.materialClass=:materialClass");
		}
		if(materialCode != null && !"".equals(materialCode)){
			shql2.append(" and d.materialCode=:materialCode");
		}
		shql.append(" and exists(").append(shql2).append(")");
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<MaterialOrderMaster> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 根据业务号获取采购订单主记录ID
	 * @param unitsCode
	 * @param billNo
	 * @return
	 */
	public String findAutoIdByBillNo(String unitsCode, String billNo) {
		String hql = "select autoId from MaterialOrderMaster where unitsCode=:unitsCode and billNo=:billNo";
		return (String)getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("billNo", billNo).uniqueResult();
	}
}