package cn.superion.material.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialRdsStock;
import cn.superion.material.stat.entity.RdsStatistic;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialRdsStock entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialRdsStock
 * @author MyEclipse Persistence Tools
 */

public class MaterialRdsStockDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialRdsStockDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String YEAR_MONTH = "yearMonth";
	public static final String MATERIAL_CLASS = "materialClass";
	public static final String MATERIAL_ID = "materialId";
	public static final String MATERIAL_CODE = "materialCode";
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_SPEC = "materialSpec";
	public static final String MATERIAL_UNITS = "materialUnits";
	public static final String TRADE_PRICE = "tradePrice";
	public static final String TRADE_MONEY = "tradeMoney";
	public static final String RETAIL_PRICE = "retailPrice";
	public static final String RETAIL_MONEY = "retailMoney";
	public static final String INIT_AMOUNT = "initAmount";
	public static final String RECEIVE_AMOUNT = "receiveAmount";
	public static final String OTHER_RECEIVE_AMOUNT = "otherReceiveAmount";
	public static final String DELIVERY_AMOUNT = "deliveryAmount";
	public static final String DELIVERY_OTHER_AMOUNT = "deliveryOtherAmount";
	public static final String CURRENT_STOCK_AMOUNT = "currentStockAmount";

	public void save(MaterialRdsStock transientInstance) {
		log.debug("saving MaterialRdsStock instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialRdsStock persistentInstance) {
		log.debug("deleting MaterialRdsStock instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialRdsStock findById(java.lang.String id) {
		log.debug("getting MaterialRdsStock instance with id: " + id);
		try {
			MaterialRdsStock instance = (MaterialRdsStock) getSession().get(
					"cn.superion.material.entity.MaterialRdsStock", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialRdsStock> findByExample(MaterialRdsStock instance) {
		log.debug("finding MaterialRdsStock instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialRdsStock").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialRdsStock> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialRdsStock instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialRdsStock as model where model."
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
	public List<MaterialRdsStock> findAll() {
		log.debug("finding all MaterialRdsStock instances");
		try {
			String queryString = "from MaterialRdsStock";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialRdsStock merge(MaterialRdsStock detachedInstance) {
		log.debug("merging MaterialRdsStock instance");
		try {
			MaterialRdsStock result = (MaterialRdsStock) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialRdsStock instance) {
		log.debug("attaching dirty MaterialRdsStock instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialRdsStock instance) {
		log.debug("attaching clean MaterialRdsStock instance");
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
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>beginYearMonth 起始月份yyyy-MM</li>
	 *            <li>endYearMonth 结束月份yyyy-MM</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginCurrentStockAmount 起始结存数量</li>
	 *            <li>endCurrentStockAmount 结束结存数量</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RdsStatistic> addUpByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String storageCode = (String) conditions.get("storageCode");
		String beginYearMonth = (String) conditions.get("beginYearMonth");
		String endYearMonth = (String) conditions.get("endYearMonth");
		String materialClass = (String) conditions.get("materialClass");
		Double beginCurrentStockAmount = conditions
				.get("beginCurrentStockAmount") == null ? null : Double
				.valueOf(conditions.get("beginCurrentStockAmount").toString());
		Double endCurrentStockAmount = conditions.get("endCurrentStockAmount") == null ? null
				: Double.valueOf(conditions.get("endCurrentStockAmount")
						.toString());
		StringBuilder shql = new StringBuilder(
				"select new cn.superion.material.stat.entity.RdsStatistic(storageCode,materialCode,materialName,materialSpec,materialUnits,sum(initAmount),sum(receiveAmount),sum(otherReceiveAmount),sum(deliveryAmount),sum(deliveryOtherAmount),sum(currentStockAmount)) from MaterialRdsStock where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginYearMonth != null && !"".equals(beginYearMonth)) {
			shql.append(" and yearMonth>=:beginYearMonth");
		}
		if (endYearMonth != null && !"".equals(endYearMonth)) {
			shql.append(" and yearMonth<=:endYearMonth");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (beginCurrentStockAmount != null) {
			shql.append(" and currentStockAmount>=:beginCurrentStockAmount");
			conditions.put("beginCurrentStockAmount", beginCurrentStockAmount);
		}
		if (endCurrentStockAmount != null) {
			shql.append(" and currentStockAmount<=:endCurrentStockAmount");
			conditions.put("endCurrentStockAmount", endCurrentStockAmount);
		}
		shql
				.append(" group by storageCode,materialCode,materialName,materialSpec,materialUnits");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	/**
	 * 东方医院结账查询
	 */
	@SuppressWarnings("unchecked")
	public List<RdsStatistic> addUpByConditionFee(String unitsCode,
			Map<String, Object> conditions) {
		String storageCode = (String) conditions.get("storageCode");
		String beginYearMonth = (String) conditions.get("beginYearMonth");
		String endYearMonth = (String) conditions.get("endYearMonth");
		//String materialClass = (String) conditions.get("materialClass");byzcl
		String materialName = (String) conditions.get("materialName");
//		Double beginCurrentStockAmount = conditions
//				.get("beginCurrentStockAmount") == null ? null : Double
//				.valueOf(conditions.get("beginCurrentStockAmount").toString());
//		Double endCurrentStockAmount = conditions.get("endCurrentStockAmount") == null ? null
//				: Double.valueOf(conditions.get("endCurrentStockAmount")
//						.toString());
		StringBuilder shql = new StringBuilder(
				"select new cn.superion.material.stat.entity.RdsStatistic(storageCode,materialCode,materialName,initAmount,receiveAmount,deliveryAmount,currentStockAmount,batch,initMoney,currentStockMoney,tradeMoney,retailMoney,countClass) from MaterialRdsStock where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginYearMonth != null && !"".equals(beginYearMonth)) {
			shql.append(" and yearMonth>=:beginYearMonth");
		}
		if (endYearMonth != null && !"".equals(endYearMonth)) {
			shql.append(" and yearMonth<=:endYearMonth");
		}
//		if (materialClass != null && !"".equals(materialClass)) {
//			shql.append(" and materialClass=:materialClass");
//		}byzcl
		if (materialName != null && !"".equals(materialName)) {
			shql.append(" and materialName=:materialName");
		}
//		if (beginCurrentStockAmount != null) {
//			shql.append(" and currentStockAmount>=:beginCurrentStockAmount");
//			conditions.put("beginCurrentStockAmount", beginCurrentStockAmount);
//		}
//		if (endCurrentStockAmount != null) {
//			shql.append(" and currentStockAmount<=:endCurrentStockAmount");
//			conditions.put("endCurrentStockAmount", endCurrentStockAmount);
//		}
//		shql
//				.append(" group by storageCode,materialCode,materialName,materialSpec,materialUnits,batch,initMoney,currentStockMoney");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	
	/**
	 * 东方医院库存物资月查询
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> addUpByConditionFeeMonth(String unitsCode,
			Map<String, Object> conditions) {
		String storageCode = (String) conditions.get("storageCode");
		String beginYearMonth = (String) conditions.get("beginYearMonth");
		String endYearMonth = (String) conditions.get("endYearMonth");
		//String materialClass = (String) conditions.get("materialClass");byzcl
		String materialName = (String) conditions.get("materialName");
		String countCode = (String) conditions.get("countClass");
		StringBuilder shql = new StringBuilder(
				"select new Map(countClass as countClass,sum(initMoney) as initMoney,sum(tradeMoney) as tradeMoney," +
				"sum(retailMoney) as retailMoney,sum(currentStockMoney) as currentStockMoney) from MaterialRdsStock where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginYearMonth != null && !"".equals(beginYearMonth)) {
			shql.append(" and yearMonth>=:beginYearMonth");
		}
		if (endYearMonth != null && !"".equals(endYearMonth)) {
			shql.append(" and yearMonth<=:endYearMonth");
		}
//		if (materialClass != null && !"".equals(materialClass)) {
//			shql.append(" and materialClass=:materialClass");
//		}byzcl
		if (materialName != null && !"".equals(materialName)) {
			shql.append(" and materialName=:materialName");
		}
		shql.append(" group by countClass");
		List<Map<String,Object>> list = getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		String str1[] = {"血费","气体费","固定资产","（单）放射_G","（单）化验_G","（单）植入_G","（单）介入_G","（单）其他_G",
				"（非单）放射_G","（非单）化验_G","（非单）植入_G","（非单）介入_G","（非单）其他_G","（单）放射_D","（单）化验_D",
				"（单）植入_D","（单）介入_D","（单）其他_D","（非单）放射_D","（非单）化验_D","（非单）植入_D",
				"（非单）介入_D","（非单）其他_D"};

//		if(countCode.equals("1")){
//			String str1[] = {"血费","气体费","（单独计价）放射材料","（单独计价）检验材料","（单独计价）植入材料","（单独计价）介入材料","（单独计价）其他卫生材料"};
//			str = str1;
//		}else{
//			String str1[] = {"（非单独计价）放射材料","（非单独计价）检验材料","（非单独计价）植入材料","（非单独计价）介入材料","（非单独计价）其他卫生材料"};
//			str = str1;
//		}
		
		boolean sign = true;
		for(int j=0;j<str1.length;j++){
			for(int i=0;i<list.size();i++){
				//if(countCode.equals("1")){
					if(list.get(i).get("countClass") != null && list.get(i).get("countClass").toString().equals(str1[j])){
						list1.add(list.get(i));
						sign = false;
					}
		//		}
//				if(countCode.equals("2")){
//					if(list.get(i).get("countClass") != null && list.get(i).get("countClass").toString().equals(str[j])){
//						list1.add(list.get(i));
//						sign = false;
//					}
//				}
			}
			if(sign){
				Map map = new HashMap<String,Object>();
				map.put("countClass",str1[j]);
				list1.add(map);
			}
		}
		
		return list1;
	}

	/**
	 * 查询指定物资的所有出库量
	 * 
	 * @param unitsCode
	 * @param materialId
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库编号</li>
	 *            <li>beginYearMonth 起始月份yyyy-MM</li>
	 *            <li>endYearMonth 结束月份yyyy-MM</li>
	 *            </ul>
	 * @return
	 */
	public Double findAllDeliverAmount(String unitsCode, String materialId,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				"select sum(deliveryAmount+deliveryOtherAmount) from MaterialRdsStock where unitsCode='")
				.append(unitsCode).append("' and materialId=:materialId");
		String storageCode = (String) conditions.get("storageCode");
		String beginYearMonth = (String) conditions.get("beginYearMonth");
		String endYearMonth = (String) conditions.get("endYearMonth");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (beginYearMonth != null && !"".equals(beginYearMonth)) {
			shql.append(" and yearMonth>=:beginYearMonth");
		}
		if (endYearMonth != null && !"".equals(endYearMonth)) {
			shql.append(" and yearMonth<=:endYearMonth");
		}
		Object obj = getSession().createQuery(shql.toString()).setString(
				"materialId", materialId).setProperties(conditions)
				.uniqueResult();
		return obj == null ? 0d : Double.valueOf(obj.toString());
	}

	/**
	 * 根据唯一索引查找收发存汇总记录
	 * 
	 * @param unitsCode
	 * @param storageCode
	 * @param yearMonth
	 * @param materialId
	 * @return
	 */
	public MaterialRdsStock findByUniqueIndex(String unitsCode,
			String storageCode, String yearMonth, String materialId) {
		String hql = "from MaterialRdsStock where unitsCode=:unitsCode and storageCode=:storageCode and yearMonth=:yearMonth and materialId=:materialId";
		return (MaterialRdsStock) getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode)
				.setString("yearMonth", yearMonth).setString("materialId",
						materialId).uniqueResult();
	}
	/**
	 * 根据批次查找记录
	 * @param unitsCode
	 * @param storageCode
	 * @param yearMonth
	 * @param materialId
	 * @param batch
	 * @return
	 */
	public MaterialRdsStock findByUniqueIndex(String unitsCode,
			String storageCode, String yearMonth, String materialId,String batch) {
		String hql = "from MaterialRdsStock where unitsCode=:unitsCode and storageCode=:storageCode and yearMonth=:yearMonth and materialId=:materialId and batch=:batch";
		return (MaterialRdsStock) getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode)
				.setString("yearMonth", yearMonth).setString("materialId",
						materialId).setString("batch", batch).uniqueResult();
	}

	/**
	 * 取消月末结账时，删除收发存汇总记录
	 * @param unitsCode
	 * @param storageCode
	 * @param yearMonth
	 */
	public void deleteByStorageAndMonth(String unitsCode, String storageCode,
			String yearMonth) {
		String hql = "delete from MaterialRdsStock where unitsCode=:unitsCode and storageCode=:storageCode and yearMonth=:yearMonth";
		getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("storageCode", storageCode).setString("yearMonth",
						yearMonth).executeUpdate();
	}
}