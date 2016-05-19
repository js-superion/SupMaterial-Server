package cn.superion.cssd.dao;

import cn.superion.cssd.entity.VCssdDeliverStock;
import cn.superion.util.BaseHibernateDAO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flex.messaging.io.ArrayList;

/**
 	* A data access object (DAO) providing persistence and search support for VCssdDeliverDetail entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.superion.cssd.entity.VCssdDeliverStock
  * @author MyEclipse Persistence Tools 
 */

public class VCssdDeliverStockDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(VCssdDeliverStockDAO.class);

    
    @SuppressWarnings("unchecked")
	public List<VCssdDeliverStock> findByExample(VCssdDeliverStock instance) {
        log.debug("finding VCssdDeliverDetail instance by example");
        try {
            List<VCssdDeliverStock> results = getSession()
                    .createCriteria("cn.superion.cssd.entity.VCssdDeliverDetail")
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
	public List<VCssdDeliverStock> findByProperty(String propertyName, Object value) {
      log.debug("finding VCssdDeliverDetail instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from VCssdDeliverDetail as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    /**
     * 查询科室领用物品包记录列表
     * @param unitsCode
     * @param conditions 包含
	 *            <ul>
	 *            <li>deptCode 领用科室</li>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginPackageNo 其实物品包编号</li>
	 *            <li>endPackageNo 结束物品包编号</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>overdueNum 过期天数</li>
	 *            <li>anearNum 临近天数</li>
	 *            </ul>
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findByCondition(String unitsCode,
			Map<String, Object> conditions) {
		
		
		String deptUnitsCode = (String)conditions.get("deptUnitsCode");
		String groupByDept = (String)conditions.get("groupByDept"); //页面勾选了科室   12-7增加
		String groupByClass = (String)conditions.get("groupByClass");//页面勾选了包分类 12-7增加
		String groupById = (String)conditions.get("groupById");//页面勾选了包名称 12-7增加
		Object[] billNos = (Object[])conditions.get("billNos"); //用于 发放模块，审核后，根据审核的单据汇总，billNos作为条件，
		
		String deptCode = (String)conditions.get("deptCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String packageClass = (String)conditions.get("packageClass");
		String packageId = (String)conditions.get("packageId");
		String beginPackageNo = (String)conditions.get("beginPackageNo");
		String endPackageNo = (String)conditions.get("endPackageNo");
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		Integer overdueNum = (Integer) conditions.get("overdueNum");
		Integer anearNum = (Integer) conditions.get("anearNum");
		StringBuilder strQueryField = new StringBuilder("t.packageUnits as packageUnits,t.tradePrice as tradePrice,");
		StringBuilder strGroupBy = new StringBuilder("group by ");
		StringBuilder strOrderBy = new StringBuilder(" ");
		//
		if(deptUnitsCode != null && !"".equals(deptUnitsCode)){
			strQueryField.append("t.deptUnitsCode as deptUnitsCode,");
			strGroupBy.append("t.deptUnitsCode,");
		}
		if(groupByDept != null && !"".equals(groupByDept)){
			strQueryField.append("t.deptCode as deptCode,");
			strGroupBy.append("t.deptCode,");
			strOrderBy.append("order by t.deptCode ");
			
		}
		if(groupByClass != null && !"".equals(groupByClass)){
			strQueryField.append("t.packageClass as packageClassName,");
			strGroupBy.append("t.packageClass,");
		}
		if(groupById != null && !"".equals(groupById)){
			strQueryField.append("t.packageId as packageId,t.packageName as packageName,");
			strGroupBy.append("t.packageId,t.packageName, ");
		}
		StringBuilder shql = new StringBuilder("select new map(").append(strQueryField).append("count(*) as amount,sum(t.materialFee) as materialFee,sum(t.sterilizeFee) as sterilizeFee)from VCssdDeliverStock t ");
		StringBuilder whereClause = new StringBuilder("where deptUnitsCode='").append(deptUnitsCode).append("'");
//		shql.append(whereClause);
		if (deptCode != null && !"".equals(deptCode)) {
			whereClause.append(" and deptCode=:deptCode");
		}
		
		if (beginBillDate != null) {
			whereClause.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			whereClause.append(" and billDate<=:endBillDate");
		}
		if(packageClass != null && !"".equals(packageClass)){
			whereClause.append(" and packageClass=:packageClass");
		}
		if(packageId != null && !"".equals(packageId)){
			whereClause.append(" and packageId=:packageId");
		}
		if(beginPackageNo != null && !"".equals(beginPackageNo)){
			whereClause.append(" and packageNo>=:beginPackageNo");
		}
		if(endPackageNo != null && !"".equals(endPackageNo)){
			whereClause.append(" and packageNo<=:endPackageNo");
		}
		if (overdueNum != null) {
			whereClause
					.append(" and availDate>=trunc(sysdate)-").append(overdueNum).append(" and availDate<trunc(sysdate)+1-").append(overdueNum);
		} else if (anearNum != null) {
			whereClause
					.append(" and availDate>=trunc(sysdate)+").append(anearNum).append(" and availDate<trunc(sysdate)+1+").append(anearNum);
		} else {
			if (beginAvailDate != null) {
				whereClause.append(" and availDate>=:beginAvailDate");
			}
			if (endAvailDate != null) {
				whereClause.append(" and availDate<=:endAvailDate");
			}
		}
		if (billNos != null && billNos.length>0) {
			StringBuilder shql2 = new StringBuilder("select new map(t.packageClass as packageClassName,t.packageId as packageId,t.packageName as packageName,t.packageUnits as packageUnits,t.tradePrice as tradePrice,count(*)as amount,sum(t.materialFee) as materialFee,sum(t.sterilizeFee) as sterilizeFee)from VCssdDeliverStock t ");
			StringBuilder sb = new StringBuilder("and billNo in(");
			for (Object billNo : billNos) {
				sb.append("'"+billNo.toString()+"',");
			}
			shql2.append(whereClause.append(sb.substring(0, sb.length()-1)).append(")")).append("group by t.packageClass,t.packageId,t.packageName,t.packageUnits,t.tradePrice ").append(strOrderBy);
			return getSession().createQuery(shql2.toString()).setProperties(conditions).list();
		}
			shql.append(whereClause + " ").append(strGroupBy.append(" t.packageUnits,t.tradePrice ")).append(strOrderBy);;
		return getSession().createQuery(shql.toString()).setProperties(conditions).list();
	}



}