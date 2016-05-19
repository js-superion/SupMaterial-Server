package cn.superion.cssd.dao;

import cn.superion.cssd.entity.DeptPackageMaterial;
import cn.superion.cssd.entity.VCssdDeliverStockDetail;
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
 * A data access object (DAO) providing persistence and search support for
 * VCssdDeliverStockDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.VCssdDeliverStockDetail
 * @author MyEclipse Persistence Tools
 */

public class VCssdDeliverStockDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VCssdDeliverStockDetailDAO.class);

	@SuppressWarnings("unchecked")
	public List<VCssdDeliverStockDetail> findByExample(
			VCssdDeliverStockDetail instance) {
		log.debug("finding VCssdDeliverStockDetail instance by example");
		try {
			List<VCssdDeliverStockDetail> results = getSession()
					.createCriteria(
							"cn.superion.cssd.entity.VCssdDeliverStockDetail")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VCssdDeliverStockDetail> findByProperty(String propertyName,
			Object value) {
		log.debug("finding VCssdDeliverStockDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VCssdDeliverStockDetail as model where model."
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
	 * 统计科室领用物品包物资
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含
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
	 * @return 返回{@code List<Map<String,Object>>},map里包含：
	 *         <ul>
	 *         <li>deptCode 领用科室</li>
	 *         <li>materialCode 物品编码</li>
	 *         <li>materialName 物品名称</li>
	 *         <li>factoryCode 生产厂家</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>amount 数量</li>
	 *         <li>tradeMoney 金额</li>
	 *         </ul>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> addUpByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String deptCode = (String) conditions.get("deptCode");
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String packageClass = (String) conditions.get("packageClass");
		String packageId = (String) conditions.get("packageId");
		String beginPackageNo = (String) conditions.get("beginPackageNo");
		String endPackageNo = (String) conditions.get("endPackageNo");
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		Integer overdueNum = (Integer) conditions.get("overdueNum");
		Integer anearNum = (Integer) conditions.get("anearNum");

		StringBuilder shql = new StringBuilder(
				"select new map(deptCode as deptCode,materialCode as materialCode,materialName as materialName,factoryCode as factoryCode,materialSpec as materialSpec,materialUnits as materialUnits,sum(amount) as amount,sum(tradeMoney) as tradeMoney) from VCssdDeliverStockDetail where unitsCode='")
				.append(unitsCode).append("'");
		if (deptCode != null && !"".equals(deptCode)) {
			shql.append(" and deptCode=:deptCode");
		}
		if (beginBillDate != null) {
			shql.append(" and billDate>=:beginBillDate");
		}
		if (endBillDate != null) {
			shql.append(" and billDate<=:endBillDate");
		}
		if (packageClass != null && !"".equals(packageClass)) {
			shql.append(" and packageClass=:packageClass");
		}
		if (packageId != null && !"".equals(packageId)) {
			shql.append(" and packageId=:packageId");
		}
		if (beginPackageNo != null && !"".equals(beginPackageNo)) {
			shql.append(" and packageNo>=:beginPackageNo");
		}
		if (endPackageNo != null && !"".equals(endPackageNo)) {
			shql.append(" and packageNo<=:endPackageNo");
		}
		if (overdueNum != null) {
			shql.append(" and availDate>=trunc(sysdate)-").append(overdueNum)
					.append(" and availDate<trunc(sysdate)+1-").append(
							overdueNum);
		} else if (anearNum != null) {
			shql.append(" and availDate>=trunc(sysdate)+").append(anearNum)
					.append(" and availDate<trunc(sysdate)+1+")
					.append(anearNum);
		} else {
			if (beginAvailDate != null) {
				shql.append(" and availDate>=:beginAvailDate");
			}
			if (endAvailDate != null) {
				shql.append(" and availDate<=:endAvailDate");
			}
		}
		shql.append(" group by deptCode,materialCode,materialName,factoryCode,materialSpec,materialUnits");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	/**
	 * 统计指定的物品包内物资(可回收物资)领用出库数量
	 * @param unitsCode
	 * @param deptCode 领用科室
	 * @param packageId 物品包ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DeptPackageMaterial> addUpRetrieveableDeliveredPackageMaterial(String unitsCode,
			String deptCode, String packageId) {
		String hql = "select new cn.superion.cssd.entity.DeptPackageMaterial(packageId,packageName,materialCode,materialName,materialSpec,materialUnits,tradePrice,sum(amount)) from VCssdDeliverStockDetail where unitsCode=:unitsCode and deptCode=:deptCode and packageId=:packageId and currentStatus='2' and deliverCurrentStatus='2' and retrieveSign='1' group by packageId,packageName,materialCode,materialName,materialSpec,materialUnits,tradePrice";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("deptCode", deptCode).setString("packageId", packageId).list();
	}
	
	/**
	 * 查询指定的已领用出库且已审核发放的物品包和物资的领用出库列表
	 * @param unitsCode
	 * @param deptCode 领用科室
	 * @param packageId 物品包ID
	 * @param materialCode 物资编码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VCssdDeliverStockDetail> findDeliveredPackageMaterial(String unitsCode,String deptCode,String packageId,String materialCode){
		String hql = "from VCssdDeliverStockDetail where unitsCode=:unitsCode and deptCode=:deptCode and packageId=:packageId and materialCode=:materialCode and currentStatus='2' and deliverCurrentStatus='2' order by availDate";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("deptCode", deptCode).setString("packageId", packageId).setString("materialCode", materialCode).list();
	}

}