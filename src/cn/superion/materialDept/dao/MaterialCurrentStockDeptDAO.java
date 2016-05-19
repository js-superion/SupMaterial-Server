package cn.superion.materialDept.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.materialDept.entity.MaterialCurrentStockDept;
import cn.superion.material.stat.entity.SafeStockStatistic;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialCurrentStock entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialCurrentStock
 * @author MyEclipse Persistence Tools
 */

public class MaterialCurrentStockDeptDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialCurrentStockDeptDAO.class);
	
	/**
	 * 查找物资的当前库存量
	 * 
	 * @param unitsCode
	 * @param materialId
	 * @return
	 */
	public Double findAmount(String unitsCode, String materialId) {
		return findAmount(unitsCode, null, materialId);
	}

	public Double findAmount(String unitsCode, String storageCode,
			String materialId) {
		StringBuilder shql = new StringBuilder(
				"select sum(amount) from MaterialCurrentStockDept where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode='").append(storageCode).append("'");
		}
		shql.append(" and materialId=:materialId");
		Object obj = getSession().createQuery(shql.toString()).setString(
				"materialId", materialId).uniqueResult();
		return obj == null ? 0d : Double.valueOf(obj.toString());
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含条件：
	 *            <ul>
	 *            <li>storageCode 仓库编码</li>
	 *            <li>materialId 物资ID</li>
	 *            <li>fatctoryCode 生产厂家</li>
	 *            <li>batch 批号</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>minStockAmount 现存量下限</li>
	 *            <li>maxStockAmount 现存量上限</li>
	 *            <li>beginAvailDate 起始有效期限</li>
	 *            <li>endAvailDate 结束有效期限</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialCurrentStockDept> findByCondition(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				" from MaterialCurrentStockDept  where unitsCode='")
				.append(unitsCode).append("'");
		String storageCode = (String) conditions.get("storageCode");
		String materialId = (String) conditions.get("materialId");
		String fatctoryCode = (String) conditions.get("fatctoryCode");
		String batch = (String) conditions.get("batch");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String materialName = (String) conditions.get("materialName");
		Double minStockAmount = conditions.get("minStockAmount")==null?null:Double.valueOf(conditions.get("minStockAmount").toString());
		Double maxStockAmount = conditions.get("maxStockAmount")==null?null:Double.valueOf(conditions.get("maxStockAmount").toString());
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode =:storageCode");
		}
		if (materialId != null && !"".equals(materialId)) {
			shql.append(" and materialId=:materialId");
		}
		if (fatctoryCode != null && !"".equals(fatctoryCode)) {
			shql.append(" and fatctoryCode=:fatctoryCode");
		}
		if (batch != null && !"".equals(batch)) {
			shql.append(" and batch=:batch");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (materialName != null && !"".equals(materialName)) {
			shql.append(" and materialName like :materialName");
			conditions.put("materialName", "%" + materialName + "%");
		}
		if (minStockAmount != null) {
			shql.append(" and amount>=:minStockAmount");
			conditions.put("minStockAmount", minStockAmount);
		}
		if (maxStockAmount != null) {
			shql.append(" and amount<=:maxStockAmount");
			conditions.put("maxStockAmount", maxStockAmount);
		}
		if (beginAvailDate != null) {
			shql.append(" and availDate>=:beginAvailDate");
		}
		if (endAvailDate != null) {
			shql.append(" and availDate<=:endAvailDate");
		}
		
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	
	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>materialClass 物资类型</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>overdueNum 过期天数</li>
	 *            <li>anearNum 临近天数</li>
	 *            </ul>
	 *            条件过期天数，临近天数和失效日期范围是互斥条件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String storageCode = (String) conditions.get("storageCode");
		String materialClass = (String) conditions.get("materialClass");
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		Integer overdueNum = (Integer) conditions.get("overdueNum");
		Integer anearNum = (Integer) conditions.get("anearNum");
		StringBuilder shql = new StringBuilder(
				"from MaterialCurrentStockDept where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (overdueNum != null) {
			shql
					.append(" and availDate>=trunc(sysdate)-").append(overdueNum).append(" and availDate<trunc(sysdate)+1-").append(overdueNum);
		} else if (anearNum != null) {
			shql
					.append(" and availDate>=trunc(sysdate)+").append(anearNum).append(" and availDate<trunc(sysdate)+1+").append(anearNum);
		} else {
			if (beginAvailDate != null) {
				shql.append(" and availDate>=:beginAvailDate");
			}
			if (endAvailDate != null) {
				shql.append(" and availDate<=:endAvailDate");
			}
		}
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<MaterialCurrentStockDept> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 根据单位编码,仓库编码,物资ID,批号以及条码查询当前库存
	 * @param unitsCode 单位编码
	 * @param storageCode 仓库编码
	 * @param materialId 物资ID
	 * @param batch 批号
	 * @param barCode 条码
	 * @return
	 */
	public MaterialCurrentStockDept findByUniqueIndex(String unitsCode,
			String storageCode, String materialId, String batch,String barCode) {
		String hql = "from MaterialCurrentStockDept where unitsCode=:unitsCode and storageCode=:storageCode and materialId=:materialId and batch=:batch and barCode=:barCode";
		return (MaterialCurrentStockDept) getSession().createQuery(hql).setString(
				"unitsCode", unitsCode).setString("storageCode", storageCode)
				.setString("materialId", materialId).setString("batch", batch).setString("barCode", barCode)
				.uniqueResult();
	}

	/**
	 * 高值耗材查询库存量
	 * @param barCode
	 * @return
	 */
	public MaterialCurrentStockDept findByBarCode(String barCode) {
		String hql = "from MaterialCurrentStockDept where  barCode=:barCode";
		return (MaterialCurrentStockDept) getSession().createQuery(hql).setString("barCode", barCode)
				.uniqueResult();
	}
	
	public void save(MaterialCurrentStockDept transientInstance) {
		log.debug("saving MaterialCurrentStockDept instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialCurrentStockDept persistentInstance) {
		log.debug("deleting MaterialCurrentStockDept instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialCurrentStockDept findById(java.lang.String id) {
		log.debug("getting MaterialCurrentStockDept instance with id: " + id);
		try {
			MaterialCurrentStockDept instance = (MaterialCurrentStockDept) getSession()
					.get("cn.superion.materialDept.entity.MaterialCurrentStockDept", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialCurrentStockDept> findByExample(
			MaterialCurrentStockDept instance) {
		log.debug("finding MaterialCurrentStockDept instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.materialDept.entity.MaterialCurrentStockDept").add(
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
	public List<MaterialCurrentStockDept> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialCurrentStock instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialCurrentStockDept as model where model."
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
	public List<MaterialCurrentStockDept> findAll() {
		log.debug("finding all MaterialCurrentStockDept instances");
		try {
			String queryString = "from MaterialCurrentStockDept";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialCurrentStockDept merge(MaterialCurrentStockDept detachedInstance) {
		log.debug("merging MaterialCurrentStockDept instance");
		try {
			MaterialCurrentStockDept result = (MaterialCurrentStockDept) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialCurrentStockDept instance) {
		log.debug("attaching dirty MaterialCurrentStockDept instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialCurrentStockDept instance) {
		log.debug("attaching clean MaterialCurrentStockDept instance");
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
	 * @param storageCode
	 * @param materialId
	 * @param deliveType
	 *            出库方式：0-近效期先出；1-批号先进先出，决定了排序字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialCurrentStockDept> findByMaterialId(String unitsCode,
			String storageCode, String materialId, String deliveType) {
		String hql = "from MaterialCurrentStockDept where unitsCode=:unitsCode and storageCode=:storageCode and materialId=:materialId";
		if ("0".equals(deliveType)) {
			hql += " order by availDate";
		} else if ("1".equals(deliveType)) {
			hql += " order by batch";
		}
		return getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("storageCode", storageCode).setString("materialId",
						materialId).list();
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>maerialClass 物资分类</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findUnsafeStockByCondition(int start, int limit,
			String unitsCode, Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		String storageCode = (String) conditions.get("storageCode");
		String materialClass = (String) conditions.get("materialClass");
		StringBuilder shql = new StringBuilder(
				"select s.units_code,s.storage_code,s.material_id,sum(s.amount) as amount from material_current_stock_dept s where s.units_code='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storage_code=:storageCode");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and material_class=:materialClass");
		}
		shql.append(" group by s.units_code,s.storage_code,s.material_id");
		String sql = "from cd_material_dict d left join ("
				+ shql.toString()
				+ ") x on d.units_code=x.units_code and d.material_id=x.material_id and d.safe_stock_amount>nvl(x.amount,0)";
		SQLQuery countQuery = getSession().createSQLQuery(
				"select count(*) " + sql);
		SQLQuery query = getSession()
				.createSQLQuery(
						"select x.storage_code,d.material_code,d.material_name,d.material_spec,d.material_units,nvl(x.amount,0),d.trade_price,d.safe_stock_amount "
								+ sql);
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<Object[]> list = query.setProperties(conditions).setFirstResult(
				start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list.isEmpty() ? null : convert(list);
		return objs;
	}

	// 将数字转成SafeStockStatistic对象
	private List<SafeStockStatistic> convert(List<Object[]> list) {
		// x.storage_code,d.material_code,d.material_name,d.material_spec,d.material_units,x.amount,d.trade_price,d.safe_stock_amount
		List<SafeStockStatistic> stockList = new ArrayList<SafeStockStatistic>();
		for (Object[] objs : list) {
			int i = 0;
			SafeStockStatistic stock = new SafeStockStatistic();
			stock.setStorageCode((String) objs[i++]);
			stock.setMaterialCode((String) objs[i++]);
			stock.setMaterialName((String) objs[i++]);
			stock.setMaterialSpec((String) objs[i++]);
			stock.setMaterialUnits((String) objs[i++]);
			Object oamount = objs[i++];
			stock.setAmount(oamount==null?0d:Double.valueOf(oamount.toString()) );
			Object otradePrice = objs[i++];
			stock.setTradePrice(otradePrice==null?0d:Double.valueOf(otradePrice.toString()));
			Object ossamount = objs[i++];
			stock.setSafeStockAmount(ossamount==null?0d:Double.valueOf(ossamount.toString()));
			stockList.add(stock);
		}
		return stockList;
	}

	/**
	 * 在物资库存中查询要盘点的物资
	 * 
	 * @param isCheckZero
	 *            账面为零是否盘点
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>beginAvailDate 起始有效期限</li>
	 *            <li>endAvailDate 结束有效期限</li>
	 *            <li>anearNum 临近天数,与有效日期范围互斥</li>
	 *            <li>agentSign 是否代销</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialCurrentStockDept> findByCheckCondition(boolean isCheckZero,
			String unitsCode, Map<String, Object> conditions) {
		String storageCode = (String) conditions.get("storageCode");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		Integer anearNum = (Integer) conditions.get("anearNum");
		String agentSign = (String) conditions.get("agentSign");
		StringBuilder shql = new StringBuilder(
				"from MaterialCurrentStockDept where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (anearNum != null) {
			shql
					.append(" and availDate>=trunc(sysdate)+").append(anearNum).append(" and availDate<trunc(sysdate)+1+").append(anearNum);
		} else {
			if (beginAvailDate != null) {
				shql.append(" and availDate>=:beginAvailDate");
			}
			if (endAvailDate != null) {
				shql.append(" and availDate<=:endAvailDate");
			}
		}
		if (agentSign != null && !"".equals(agentSign)) {
			shql.append(" and agentSign=:agentSign");
		}
		if (!isCheckZero) {
			shql.append(" and amount>0");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	/**
	 * 查询仓库库存中不存在，而物资档案字典存在的物资（可以认为是零库存的一种），作为盘点物资
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>agentSign 是否代销</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CdMaterialDict> findMaterialDictByCheckCondition(
			String unitsCode, Map<String, Object> conditions) {
		String storageCode = (String) conditions.get("storageCode");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String agentSign = (String) conditions.get("agentSign");
		StringBuilder shql = new StringBuilder(
				"from CdMaterialDict d where unitsCode='").append(unitsCode)
				.append("'");
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (agentSign != null && !"".equals(agentSign)) {
			shql.append(" and agentSign=:agentSign");
		}
		shql
				.append(" and not exists(from MaterialCurrentStockDept s where s.unitsCode=d.unitsCode and s.materialId=d.materialId");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and s.storageCode=:storageCode");
		}
		shql.append(")");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	
	/**
	 * ryh 2012.11.01
	 * @param unitsCode
	 * @param conditions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findMapByCondition(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				"select new map(c.materialCode as materialCode,c.materialName as materialName,c.materialSpec as materialSpec,c.materialUnits as materialUnits,sum(c.amount) as amount,c.tradePrice as tradePrice,c.factoryCode as factoryCode,c.batch as batch,c.availDate as availDate) from MaterialCurrentStockDept c where unitsCode='")
				.append(unitsCode).append("'");
		String storageCode = (String) conditions.get("storageCode");
		String materialId = (String) conditions.get("materialId");
		String fatctoryCode = (String) conditions.get("fatctoryCode");
		String batch = (String) conditions.get("batch");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String materialName = (String) conditions.get("materialName");
		Double minStockAmount = conditions.get("minStockAmount")==null?null:Double.valueOf(conditions.get("minStockAmount").toString());
		Double maxStockAmount = conditions.get("maxStockAmount")==null?null:Double.valueOf(conditions.get("maxStockAmount").toString());
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		
		if (storageCode != null && !"".equals(storageCode)) {
			String vStorageCode="V"+storageCode;
			shql.append(" and (storageCode ='"+storageCode+"'");
			shql.append("  or storageCode ='"+vStorageCode+"')");
		}
		if (materialId != null && !"".equals(materialId)) {
			shql.append(" and materialId=:materialId");
		}
		if (fatctoryCode != null && !"".equals(fatctoryCode)) {
			shql.append(" and fatctoryCode=:fatctoryCode");
		}
		if (batch != null && !"".equals(batch)) {
			shql.append(" and batch=:batch");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (materialName != null && !"".equals(materialName)) {
			shql.append(" and materialName like :materialName");
			conditions.put("materialName", "%" + materialName + "%");
		}
		if (minStockAmount != null) {
			shql.append(" and amount>=:minStockAmount");
			conditions.put("minStockAmount", minStockAmount);
		}
		if (maxStockAmount != null) {
			shql.append(" and amount<=:maxStockAmount");
			conditions.put("maxStockAmount", maxStockAmount);
		}
		if (beginAvailDate != null) {
			shql.append(" and availDate>=:beginAvailDate");
		}
		if (endAvailDate != null) {
			shql.append(" and availDate<=:endAvailDate");
		}
		shql.append(" and amount != 0");
		shql.append(" group by c.materialCode,c.materialName,c.materialSpec,c.materialUnits,c.tradePrice,c.factoryCode,c.batch,c.availDate");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
	
	
	/**
	 * 高值耗材现存量查询
	 * 
	 * ryh 2012.11.29
	 * @param unitsCode
	 * @param conditions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findValueMapByCondition(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				"select new map(c.materialCode as materialCode,c.materialName as materialName,c.materialSpec as materialSpec,c.materialUnits as materialUnits,sum(c.amount) as amount,c.tradePrice as tradePrice,c.factoryCode as factoryCode,c.batch as batch,c.availDate as availDate) from MaterialCurrentStockDept c where highValueSign='1'");
		String storageCode = (String) conditions.get("storageCode");
		String materialId = (String) conditions.get("materialId");
		String fatctoryCode = (String) conditions.get("fatctoryCode");
		String batch = (String) conditions.get("batch");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String materialName = (String) conditions.get("materialName");
		Double minStockAmount = conditions.get("minStockAmount")==null?null:Double.valueOf(conditions.get("minStockAmount").toString());
		Double maxStockAmount = conditions.get("maxStockAmount")==null?null:Double.valueOf(conditions.get("maxStockAmount").toString());
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		
		if (storageCode != null && !"".equals(storageCode)) {
			String vStorageCode="V"+storageCode;
			shql.append(" and (storageCode ='"+storageCode+"'");
			shql.append("  or storageCode ='"+vStorageCode+"')");
		}
		if (materialId != null && !"".equals(materialId)) {
			shql.append(" and materialId=:materialId");
		}
		if (fatctoryCode != null && !"".equals(fatctoryCode)) {
			shql.append(" and fatctoryCode=:fatctoryCode");
		}
		if (batch != null && !"".equals(batch)) {
			shql.append(" and batch=:batch");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (materialName != null && !"".equals(materialName)) {
			shql.append(" and materialName like :materialName");
			conditions.put("materialName", "%" + materialName + "%");
		}
		if (minStockAmount != null) {
			shql.append(" and amount>=:minStockAmount");
			conditions.put("minStockAmount", minStockAmount);
		}
		if (maxStockAmount != null) {
			shql.append(" and amount<=:maxStockAmount");
			conditions.put("maxStockAmount", maxStockAmount);
		}
		if (beginAvailDate != null) {
			shql.append(" and availDate>=:beginAvailDate");
		}
		if (endAvailDate != null) {
			shql.append(" and availDate<=:endAvailDate");
		}
		shql.append(" group by c.materialCode,c.materialName,c.materialSpec,c.materialUnits,c.tradePrice,c.factoryCode,c.batch,c.availDate");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}
}