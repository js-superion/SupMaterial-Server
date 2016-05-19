package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialProvideMaster;
import cn.superion.util.BaseHibernateDAO;
import cn.superion.util.SessionUtil;

/**
 	* A data access object (DAO) providing persistence and search support for MaterialProvideMaster entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.material.entity.MaterialProvideMaster
  * @author MyEclipse Persistence Tools 
 */

public class MaterialProvideMasterDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MaterialProvideMasterDAO.class);
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



    
    public void save(MaterialProvideMaster transientInstance) {
        log.debug("saving MaterialProvideMaster instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Object> findSendMaterialEntityListByCondition(String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		String flag1 = (String)conditions.get("flag");
		String materialCode = (String)conditions.get("materialCode");
		String mainProvider = (String)conditions.get("mainProvider");
		String sendStatus = (String)conditions.get("sendStatus");
		String manualSign = (String)conditions.get("manualSign"); 
		String beginBillDate = (String)conditions.get("fromDate");
    	String endBillDate = (String)conditions.get("toDate");
		String currentStatus = (String)conditions.get("currentStatus");
		String storageCode = (String)conditions.get("storageCode");
		String checkAmountSign = (String)conditions.get("checkAmountSign");
		String checkSupplySign = (String)conditions.get("checkSupplySign");
		StringBuilder shql = new StringBuilder("from MaterialProvideMaster m where unitsCode='").append(unitsCode).append("'");
		String deptCode = null;
		if(flag1!=null && flag1.equals("1")){
			deptCode = (String)conditions.get("deptCode");
			if(null !=deptCode){
				shql.append(" and deptCode=:deptCode");
			}
		}else
		{
			deptCode = SessionUtil.getSysUser().getDeptCode();
			shql.append(" and deptCode='"+deptCode+"'");
		}
		if(sendStatus !=null && !"".equals(sendStatus))
		{
			if(sendStatus.equals("1,2")){
				
				shql.append(" and sendStatus in('1','2')");
			}
			else if(sendStatus.equals("2,3")){
				
				shql.append(" and sendStatus in('2','3')");
			}else if(sendStatus.equals("3,4")){
				
				shql.append(" and sendStatus in('3','4')");
			}
			else if(sendStatus.equals("0,1")){
				
				shql.append(" and sendStatus in('0','1')");
			}
			else{
				shql.append(" and sendStatus in('"+sendStatus+"')");
			}
			
		}
		if(storageCode !=null && !"".equals(storageCode))
		{
				
				shql.append(" and m.storageCode in('"+storageCode+"')");
			
		}
		
		if(manualSign !=null && !"".equals(manualSign))
		{
				
				shql.append(" and manualSign in('"+manualSign+"')");
			
		}
		
		if(currentStatus !=null && !"".equals(currentStatus))
		{
			if(currentStatus.equals("0,1")){
				
				shql.append(" and currentStatus in('0','1')");
			} else if(currentStatus.equals("1,4")){
				
				shql.append(" and currentStatus in('1','4')");
			}
			else if(currentStatus.equals("1,2")){
				
				shql.append(" and currentStatus in('1','2')");
			}
			else if(currentStatus.equals("2,3")){
	
				shql.append(" and currentStatus in('2','3')");
			}
			else if(currentStatus.equals("3,4")){
				
				shql.append(" and currentStatus in('3','4')");
			}
			else if(currentStatus.equals("1,4,5")){
				
				shql.append(" and currentStatus in('1','4','5')");
			}
			
			else if(currentStatus.equals("4,5")){
				
				shql.append(" and currentStatus in('4','5')");
			}
			else{
				shql.append(" and currentStatus in('"+currentStatus+"')");
			}
			
		}
		
		 if(checkAmountSign !=null && !"".equals(checkAmountSign))
		{
			if(checkAmountSign.equals("0,1")){
				
				shql.append(" and checkAmountSign in('0','1')");
			}
			else if(checkAmountSign.equals("8,9")){
				shql.append(" and checkAmountSign in('8','9')");
			}
			else{
				shql.append(" and checkAmountSign in('"+checkAmountSign+"')");
			}
			
		}
		 
		 if(checkSupplySign !=null && !"".equals(checkSupplySign))
		{
			if(checkSupplySign.equals("0,1")){
				
				shql.append(" and checkSupplySign in('0','1')");
			}
			else{
				shql.append(" and checkSupplySign in('"+checkSupplySign+"')");
			}
			
		}
		 
		if(beginBillDate != null&& !"".equals(endBillDate)){
			
			 shql.append(" and billDate>=to_date('"+beginBillDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
		if(endBillDate != null&& !"".equals(endBillDate)){
			shql.append(" and billDate<=to_date('"+endBillDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
			
		
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialProvideDetail d where d.mainAutoId=m.autoId");
		if(materialCode != null && !"".equals(materialCode)){
			flag = true;
			shql2.append(" and d.materialCode=:materialCode");
		}
		if(mainProvider != null && !"".equals(mainProvider)){
			flag = true;
			shql2.append(" and mainProvider=:mainProvider");
		}
		if(flag)
			shql.append(" and exists(").append(shql2).append(")");
		shql.append(" order by m.billDate desc,m.billNo desc");
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}
    
    public List findTotal(List<String> autoIds,String unitsCode,String storageMaterialSign) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new map(b.materialClass as materialClass,b.materialCode as materialCode,b.mainProvider as mainProvider,b.materialName as materialName,b.materialSpec as materialSpec,b.materialUnits as materialUnits,b.tradePrice as tradePrice,sum (b.sendAmount) as sendAmount,sum (b.amount) as amount,sum(b.sendAmount * b.tradePrice) as tradeMoney )" +
				" from MaterialProvideMaster a,MaterialProvideDetail b  " +
				" where a.unitsCode=:unitsCode and a.autoId = b.mainAutoId");
		if(storageMaterialSign!=null && !"".equals(storageMaterialSign))
		{
			hql.append(" and b.storageMaterialSign = '"+storageMaterialSign+"'");
		}
		hql.append(" and a.autoId in(:autoIds)" +" group by b.materialClass, b.materialCode,b.mainProvider,b.materialName,b.materialSpec,b.materialUnits,b.tradePrice ");		
		return  getSession().createQuery(hql.toString()).setString("unitsCode", unitsCode).setParameterList("autoIds", autoIds).list();
	}
    
    
    public List findGroupByDept(String unitsCode,String supplyNo) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select new map(a.deptCode as deptCode,b.materialClass as materialClass,b.materialCode as materialCode,b.materialId as materialId,b.materialName as materialName,b.materialSpec as materialSpec,b.materialUnits as materialUnits,b.tradePrice as tradePrice,sum (b.sendAmount) as sendAmount,sum (b.amount) as amount,sum(b.sendAmount * b.tradePrice) as tradeMoney )" +
				" from MaterialProvideMaster a,MaterialProvideDetail b  " +
				" where a.unitsCode='"+unitsCode+"' and a.autoId = b.mainAutoId and b.mainProvider is not null and b.supplyNo ='"+supplyNo+"'");
		hql.append("  group by a.deptCode,b.materialClass, b.materialCode,b.materialId,b.materialName,b.materialSpec,b.materialUnits,b.tradePrice order by a.deptCode ");		
		return  getSession().createQuery(hql.toString()).list();
	}
    
    
    public List findTotal2(String unitsCode,Map<String,Object> param) {
    	String storageMaterialSign = (String) param.get("storageMaterialSign");
    	String mainProvider = (String) param.get("mainProvider");
    	String storageCode = (String) param.get("storageCode");
    	String currentStatus = (String) param.get("currentStatus");
    	String beginBillDate = (String)param.get("fromDate");
    	String endBillDate = (String)param.get("toDate");
    	String manualSign = (String)param.get("manualSign");
		StringBuilder hql = new StringBuilder();
		hql.append(" select new map(b.mainProvider as mainProvider,b.materialClass as materialClass,b.materialId as materialId,b.materialCode as materialCode,b.materialName as materialName,b.materialSpec as materialSpec,b.materialUnits as materialUnits,b.tradePrice as tradePrice,sum (b.sendAmount) as sendAmount,sum (b.amount) as amount,sum(b.sendAmount * b.tradePrice) as tradeMoney )" +
				" from MaterialProvideMaster a,MaterialProvideDetail b  " +
				" where a.unitsCode=:unitsCode and a.autoId = b.mainAutoId and b.mainProvider is not null and b.supplyNo is null");
		if(storageMaterialSign!=null && !"".equals(storageMaterialSign))
		{
			hql.append(" and b.storageMaterialSign = '"+storageMaterialSign+"'");
		}
		if(mainProvider!=null && !"".equals(mainProvider))
		{
			hql.append(" and b.mainProvider = '"+mainProvider+"'");
		}
		if(manualSign!=null && !"".equals(manualSign))
		{
			hql.append(" and a.manualSign = '"+manualSign+"'");
		}
		
		if(currentStatus!=null && !"".equals(currentStatus))
		{
			hql.append(" and a.currentStatus = '"+currentStatus+"'");
		}
		
		if(beginBillDate != null&& !"".equals(endBillDate)){
			hql.append(" and a.billDate>=to_date('"+beginBillDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
		if(endBillDate != null&& !"".equals(endBillDate)){
			hql.append(" and a.billDate<=to_date('"+endBillDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		if(storageCode != null){
			hql.append(" and a.storageCode=:storageCode");
		}
		hql.append("  group by b.mainProvider,b.materialClass, b.materialCode,b.materialId,b.materialName,b.materialSpec,b.materialUnits,b.tradePrice order by b.mainProvider ");		
		return  getSession().createQuery(hql.toString()).setString("unitsCode", unitsCode).setProperties(param).list();
	}
    
    
    public int updateProviderDetail(String unitsCode,String storageCode,String beginBillDate,
    		String endBillDate,String mainProvider,String materialCode,String supplyNo) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" update MaterialProvideDetail c set c.supplyNo = '"+supplyNo+"'" +
				" where c.autoId in ( select b.autoId " +
				" from MaterialProvideMaster a,MaterialProvideDetail b  " +
				" where a.currentStatus ='4' and a.autoId = b.mainAutoId and b.supplyNo is null ");
		
		if(unitsCode!=null && !"".equals(unitsCode)){
			hql.append(" and a.unitsCode='"+unitsCode+"'");
		}
		if(storageCode != null&& !"".equals(storageCode)){
			hql.append(" and a.storageCode='"+storageCode+"'");
		}
		if(mainProvider!=null && !"".equals(mainProvider))
		{
			hql.append(" and b.mainProvider='"+mainProvider+"'");
		}
		
		if(beginBillDate != null&& !"".equals(beginBillDate)){
			hql.append(" and a.billDate>=to_date('"+beginBillDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
		if(endBillDate != null&& !"".equals(endBillDate)){
			hql.append(" and a.billDate<=to_date('"+endBillDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		
		if(materialCode != null&& !"".equals(materialCode)){
			hql.append(" and b.materialCode = '"+materialCode+"'");
		}
		hql.append(")");
		return  getSession().createQuery(hql.toString()).executeUpdate();
	}
    
	public void delete(MaterialProvideMaster persistentInstance) {
        log.debug("deleting MaterialProvideMaster instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MaterialProvideMaster findById( java.lang.String id) {
        log.debug("getting MaterialProvideMaster instance with id: " + id);
        try {
            MaterialProvideMaster instance = (MaterialProvideMaster) getSession()
                    .get("cn.superion.material.entity.MaterialProvideMaster", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<MaterialProvideMaster> findByExample(MaterialProvideMaster instance) {
        log.debug("finding MaterialProvideMaster instance by example");
        try {
            List results = getSession()
                    .createCriteria("cn.superion.material.entity.MaterialProvideMaster")
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
	public List<MaterialProvideMaster> findByProperty(String propertyName, Object value) {
      log.debug("finding MaterialProvideMaster instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MaterialProvideMaster as model where model." 
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
	public List<MaterialProvideMaster> findAll() {
		log.debug("finding all MaterialProvideMaster instances");
		try {
			String queryString = "from MaterialProvideMaster";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Object> findDeptByStorageCode(String storageCode) {
		try {
			String queryString = "select p.deptCode as deptCode,p.deptName as deptName from CdStorageDict t,CdDeptDict p where t.unitsCode = p.unitsCode and t.deptCode = p.deptCode and t.storageCode = ? ";
	         Query queryObject = getSession().createQuery(queryString);
//	         queryObject.setString(0, unitsCode);
	         queryObject.setString(0, storageCode);
			 return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
    public MaterialProvideMaster merge(MaterialProvideMaster detachedInstance) {
        log.debug("merging MaterialProvideMaster instance");
        try {
            MaterialProvideMaster result = (MaterialProvideMaster) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MaterialProvideMaster instance) {
        log.debug("attaching dirty MaterialProvideMaster instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MaterialProvideMaster instance) {
        log.debug("attaching clean MaterialProvideMaster instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /**
     * 根据单据号查询物资领用申请单主记录
     * @param billNo
     * @return
     */
	public MaterialProvideMaster findByBillNo(String unitsCode,String billNo) {
		String hql = "from MaterialProvideMaster where unitsCode=:unitsCode and billNo=:billNo";
		return (MaterialProvideMaster) getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("billNo", billNo).uniqueResult();
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 当前仓库</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据编号</li>
	 *            <li>deptCode 领用部门</li>
	 *            <li>billNo 领用单号</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String storageCode = (String)conditions.get("storageCode");
		String billNo = (String)conditions.get("billNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String deptCode = (String)conditions.get("deptCode");
		StringBuilder shql = new StringBuilder("from MaterialProvideMaster where currentStatus='1' ");
		if(unitsCode != null && !"".equals(unitsCode)){
			shql.append(" and unitsCode='"+unitsCode+"'");
		}
		if(storageCode != null && !"".equals(storageCode)){
			shql.append(" and storageCode=:storageCode");
		}
		if(billNo != null && !"".equals(billNo)){
			shql.append(" and billNo=:billNo");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		shql.append(" order by deptCode,billDate desc");
		int count = Integer.parseInt(getSession().createQuery("select count(*) "+shql.toString()).setProperties(conditions).uniqueResult().toString());
		List<MaterialProvideMaster> list = getSession().createQuery(shql.toString()).setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>deptCode 申请科室编码</li>
	 *            <li>beginBillNo 起始单据编号</li>
	 *            <li>endBillNo 结束单据编号</li>
	 *            <li>beginBillDate 起始申请日期</li>
	 *            <li>endBillDate 结束申请日期</li>
	 *            <li>currentStatus 当前状态</li>
	 *            以下为从表条件
	 *            <li>materialClass 物资类别</li>
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
		String deptCode = (String)conditions.get("deptCode");
		//String deptCode = SessionUtil.getSysUser().getDeptCode();
		String beginBillNo = (String)conditions.get("beginBillNo");
		String endBillNo = (String)conditions.get("endBillNo");
		String billNo = (String) conditions.get("billNo");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String currentStatus = (String)conditions.get("currentStatus");
		String materialClass = (String)conditions.get("materialClass");
		String manualSign = (String)conditions.get("manualSign");
		String beginMaterialCode = (String)conditions.get("beginMaterialCode");
//		String endMaterialCode = (String)conditions.get("endMaterialCode");
		StringBuilder shql = new StringBuilder("select autoId from MaterialProvideMaster m where unitsCode='").append(unitsCode).append("'");
		if(storageCode != null && !"".equals(storageCode)){
			shql.append(" and storageCode=:storageCode");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
		
		if(manualSign != null && !"".equals(manualSign)){
			shql.append(" and manualSign=:manualSign");
		}
		
		if (billNo != null && !"".equals(billNo)) {
			shql.append(" and billNo=:billNo");
		}else{
			if(beginBillNo != null && !"".equals(beginBillNo)){
				shql.append(" and billNo>=:beginBillNo");
			}
			if(endBillNo != null && !"".equals(endBillNo)){
				shql.append(" and billNo<=:endBillNo");
			}
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(currentStatus != null && !"".equals(currentStatus)){
			if(currentStatus.equals("3,4")){
				
				shql.append(" and currentStatus in ('3','4')");	
			}else if(currentStatus.equals("2,3,4")){
				
				shql.append(" and currentStatus in ('2','3','4')");
			}else{
				
				shql.append(" and currentStatus=:currentStatus");
			}
		}
		//从表条件
		StringBuilder shql2 = new StringBuilder("from MaterialProvideDetail d where d.mainAutoId=m.autoId");
		if(materialClass != null && !"".equals(materialClass)){
			flag = true;
			shql2.append(" and d.materialClass=:materialClass");
		}
		if(beginMaterialCode != null && !"".equals(beginMaterialCode)){
			flag = true;
			shql2.append(" and d.materialCode=:beginMaterialCode");
		}
//		if(endMaterialCode != null && !"".equals(endMaterialCode)){
//			flag = true;
//			shql2.append(" and d.materialCode<=:endMaterialCode");
//		}
		if(flag)
			shql.append(" and exists(").append(shql2).append(")");
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}

	public boolean checkBillNoUnique(String unitsCode, String storageCode,
			String billNo) {
		String hql = "select count(*) from MaterialProvideMaster where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult().toString());
		return cnt == 0;
	}

	public MaterialProvideMaster findByUnitsCodeBillNo(String unitsCode, String storageCode,
			String billNo) {
		String hql = "from MaterialProvideMaster where unitsCode=:unitsCode and storageCode=:storageCode and billNo=:billNo";
		return (MaterialProvideMaster) getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult();
	}
	
	public MaterialProvideMaster findByStorageBillNo(String storageCode,String billNo) {
		String hql = "from MaterialProvideMaster where storageCode=:storageCode and billNo=:billNo";
		return (MaterialProvideMaster) getSession().createQuery(hql).setString("storageCode", storageCode).setString("billNo", billNo).uniqueResult();
	}
}