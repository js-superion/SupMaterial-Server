package cn.superion.material.dao;

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
import cn.superion.material.entity.MaterialCurrentStock;
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

public class MaterialCurrentStockDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialCurrentStockDAO.class);
	// property constants
	public static final String UNITS_CODE = "unitsCode";
	public static final String STORAGE_CODE = "storageCode";
	public static final String MATERIAL_CLASS = "materialClass";
	public static final String MATERIAL_ID = "materialId";
	public static final String MATERIAL_CODE = "materialCode";
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_SPEC = "materialSpec";
	public static final String MATERIAL_UNITS = "materialUnits";
	public static final String TRADE_PRICE = "tradePrice";
	public static final String AMOUNT = "amount";
	public static final String FACTORY_CODE = "factoryCode";
	public static final String BATCH = "batch";
	public static final String POSITION = "position";
	public static final String HIGH_VALUE_SIGN = "highValueSign";
	public static final String AGENT_SIGN = "agentSign";
	public static final String REMARK = "remark";

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
				"select sum(amount) from MaterialCurrentStock where unitsCode='")
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
	 *            <li>barCode 条码</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>minStockAmount 现存量下限</li>
	 *            <li>maxStockAmount 现存量上限</li>
	 *            <li>beginAvailDate 起始有效期限</li>
	 *            <li>beginAvailDate 结束有效期限</li>
	 *            <li>stockAmountFlag 汇总数量标志
	 *            <ul>
	 *            <li>1 >0</li>
	 *            <li>2 =0</li>
	 *            <li>3 <0</li>
	 *            <li>4 >=0</li>
	 *            <li>5 <=0</li>
	 *            <li>6 >0 or <0,即不等于0</li>
	 *            </ul>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialCurrentStock> findByCondition(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				"from MaterialCurrentStock where unitsCode='")
				.append(unitsCode).append("'");
		String storageCode = (String) conditions.get("storageCode");
		String materialId = (String) conditions.get("materialId");
		String fatctoryCode = (String) conditions.get("fatctoryCode");
		String batch = (String) conditions.get("batch");
		String barCode = (String) conditions.get("barCode");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		String materialName = (String) conditions.get("materialName");
		Double minStockAmount = conditions.get("minStockAmount") == null ? null
				: Double.valueOf(conditions.get("minStockAmount").toString());
		Double maxStockAmount = conditions.get("maxStockAmount") == null ? null
				: Double.valueOf(conditions.get("maxStockAmount").toString());
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		String stockAmountFlag = (String) conditions.get("stockAmountFlag");
		

		
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
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
		if (barCode != null && !"".equals(barCode)) {
			shql.append(" and barCode=:barCode");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass  like :materialClass");
			conditions.put("materialClass",  materialClass +"%");
		}
		if (materialCode != null && !"".equals(materialCode)) {
			shql.append(" and materialCode=:materialCode");
		}
		if (materialName != null && !"".equals(materialName)) {
			shql.append(" and materialName like :materialName");
			conditions.put("materialName", "%" + materialName + "%");
		}
		if(stockAmountFlag!=null && !"".equals(stockAmountFlag)){
			if ("1".equals(stockAmountFlag)) {
				shql.append(" and amount>0");
			} else if ("2".equals(stockAmountFlag)) {
				shql.append(" and amount=0");
			} else if ("3".equals(stockAmountFlag)) {
				shql.append(" and amount<0");
			} else if ("4".equals(stockAmountFlag)) {
				shql.append(" and amount>=0");
			} else if ("5".equals(stockAmountFlag)) {
				shql.append(" and amount<=0");
			} else if ("6".equals(stockAmountFlag)) {
				shql.append(" and amount<>0");
			}
		}
		if (minStockAmount != null) {
			if(minStockAmount<0)
			{
				shql.append(" and amount<=:minStockAmount");
				conditions.put("minStockAmount", minStockAmount);
			}
			else
			{
				shql.append(" and amount>=:minStockAmount");
				conditions.put("minStockAmount", minStockAmount);
			}
		}
		if (maxStockAmount != null) {
			if(maxStockAmount<0)
			{
				shql.append(" and amount>=:maxStockAmount");
				conditions.put("maxStockAmount", maxStockAmount);
			}
			else
			{
				shql.append(" and amount<=:maxStockAmount");
				conditions.put("maxStockAmount", maxStockAmount);
			}
		}
		if (beginAvailDate != null) {
			shql.append(" and availDate>=:beginAvailDate");
		}
		if (endAvailDate != null) {
			shql.append(" and availDate<=:endAvailDate");
		}
		shql.append(" order by materialCode");
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
				"from MaterialCurrentStock where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
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
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<MaterialCurrentStock> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

	/**
	 * 根据单位编码,仓库编码,物资ID,批号以及条码查询当前库存
	 * 
	 * @param unitsCode
	 *            单位编码
	 * @param storageCode
	 *            仓库编码
	 * @param materialId
	 *            物资ID
	 * @param batch
	 *            批号
	 * @param barCode
	 *            条码
	 * @return
	 */
	public MaterialCurrentStock findByUniqueIndex(String unitsCode,
			String storageCode, String materialId, String batch, String barCode) {
		try {
			String hql = "from MaterialCurrentStock where unitsCode=:unitsCode and storageCode=:storageCode and materialId=:materialId and batch=:batch and barCode=:barCode";
			return (MaterialCurrentStock) getSession().createQuery(hql).setString(
					"unitsCode", unitsCode).setString("storageCode", storageCode)
					.setString("materialId", materialId).setString("batch", batch)
					.setString("barCode", barCode).uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(unitsCode+"ss" +storageCode + "materialId"+materialId + "batch:" +batch+
					"barCode"+barCode);
			return null;
		}
		
	}

	public void save(MaterialCurrentStock transientInstance) {
		log.debug("saving MaterialCurrentStock instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialCurrentStock persistentInstance) {
		log.debug("deleting MaterialCurrentStock instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialCurrentStock findById(java.lang.String id) {
		log.debug("getting MaterialCurrentStock instance with id: " + id);
		try {
			MaterialCurrentStock instance = (MaterialCurrentStock) getSession()
					.get("cn.superion.material.entity.MaterialCurrentStock", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialCurrentStock> findByExample(
			MaterialCurrentStock instance) {
		log.debug("finding MaterialCurrentStock instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialCurrentStock").add(
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
	public List<MaterialCurrentStock> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialCurrentStock instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialCurrentStock as model where model."
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
	public List<MaterialCurrentStock> findAll() {
		log.debug("finding all MaterialCurrentStock instances");
		try {
			String queryString = "from MaterialCurrentStock";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialCurrentStock merge(MaterialCurrentStock detachedInstance) {
		log.debug("merging MaterialCurrentStock instance");
		try {
			MaterialCurrentStock result = (MaterialCurrentStock) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialCurrentStock instance) {
		log.debug("attaching dirty MaterialCurrentStock instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialCurrentStock instance) {
		log.debug("attaching clean MaterialCurrentStock instance");
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
	public List<MaterialCurrentStock> findByMaterialId(String unitsCode,
			String storageCode, String materialId, String deliveType) {
		String hql = "from MaterialCurrentStock where unitsCode=:unitsCode and storageCode=:storageCode and materialId=:materialId";
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
	 * 高值耗材
	 * @param unitsCode
	 * @param storageCode
	 * @param materialId
	 * @param deliveType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialCurrentStock> findValueByMaterialId(String unitsCode,
			String storageCode, String materialId,String barCode, String deliveType) {
		String hql = "from MaterialCurrentStock where unitsCode=:unitsCode and storageCode=:storageCode and materialId=:materialId and barCode=:barCode";
		if ("0".equals(deliveType)) {
			hql += " order by availDate";
		} else if ("1".equals(deliveType)) {
			hql += " order by batch";
		}
		return getSession().createQuery(hql).setString("unitsCode", unitsCode)
				.setString("storageCode", storageCode).setString("materialId",
						materialId).setString("barCode", barCode).list();
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
				"select s.units_code,s.storage_code,s.material_id,sum(s.amount) as amount from material_current_stock s where s.units_code='")
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
			stock.setAmount(oamount == null ? 0d : Double.valueOf(oamount
					.toString()));
			Object otradePrice = objs[i++];
			stock.setTradePrice(otradePrice == null ? 0d : Double
					.valueOf(otradePrice.toString()));
			Object ossamount = objs[i++];
			stock.setSafeStockAmount(ossamount == null ? 0d : Double
					.valueOf(ossamount.toString()));
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
	public List<MaterialCurrentStock> findByCheckCondition(boolean isCheckZero,
			String unitsCode, Map<String, Object> conditions) {
		String storageCode = (String) conditions.get("storageCode");
		String materialClass = (String) conditions.get("materialClass");
		String materialCode = (String) conditions.get("materialCode");
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		Integer anearNum = (Integer) conditions.get("anearNum");
		String agentSign = (String) conditions.get("agentSign");
		String lessZero = (String) conditions.get("lessZero");
		String bigZero = (String) conditions.get("bigZero");
		StringBuilder shql = new StringBuilder(
				"from MaterialCurrentStock where unitsCode='")
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
		if (agentSign != null && !"".equals(agentSign)) {
			shql.append(" and agentSign=:agentSign");
		}
		if (bigZero != null && "1".equals(bigZero)) {
			shql.append(" and amount > 0");
			return getSession().createQuery(shql.toString()).setProperties(
					conditions).list();
		}
		else if (lessZero != null && "1".equals(lessZero)) {
			shql.append(" and amount < 0");
			return getSession().createQuery(shql.toString()).setProperties(
					conditions).list();
		}else{
			if (!isCheckZero) {
				shql.append(" and amount <> 0");
			}
			return getSession().createQuery(shql.toString()).setProperties(
					conditions).list();
		}
		
		
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
//		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
//		Date endAvailDate = (Date) conditions.get("endAvailDate");
		String agentSign = (String) conditions.get("agentSign");
		StringBuilder shql = new StringBuilder(
				"from CdMaterialDict d where unitsCode='").append(unitsCode)
				.append("' and storageDefault = '").append(storageCode).append("'");
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
				.append(" and not exists(from MaterialCurrentStock s where s.unitsCode=d.unitsCode and s.materialId=d.materialId");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and s.storageCode=:storageCode");
		}
		shql.append(")");
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>storageCode 仓库</li>
	 *            <li>materialClass 物资分类</li>
	 *            <li>beginMaterialCode 起始物资编码</li>
	 *            <li>endMaterialCode 结束物资编码</li>
	 *            <li>materialName 物资名称</li>
	 *            <li>beginAvailDate 起始失效日期</li>
	 *            <li>endAvailDate 结束失效日期</li>
	 *            <li>anearNum 临近天数</li>
	 *            <li>stockAmountFlag 汇总数量标志
	 *            <ul>
	 *            <li>1 >0</li>
	 *            <li>2 =0</li>
	 *            <li>3 <0</li>
	 *            <li>4 >=0</li>
	 *            <li>5 <=0</li>
	 *            <li>6 >0 or <0,即不等于0</li>
	 *            </ul>
	 *            </li>
	 *            </ul>
	 * @return <ul>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>acctAmount 核算数量</li>
	 *         <li>amount 库存数量</li>
	 *         <li>saleMoney 批发价金额</li>
	 *         <li>retailMoney 零售价金额</li>
	 *         </ul>
	 */
	@SuppressWarnings("unchecked")
	public List<Object> addUpByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String storageCode = (String) conditions.get("storageCode");
		String materialClass = (String) conditions.get("materialClass");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");
		String materialName = (String) conditions.get("materialName");
		Date beginAvailDate = (Date) conditions.get("beginAvailDate");
		Date endAvailDate = (Date) conditions.get("endAvailDate");
		Integer anearNum = (Integer) conditions.get("anearNum");
		String stockAmountFlag = (String) conditions.get("stockAmountFlag");
		StringBuilder shql = new StringBuilder(
				"select new map(materialCode as materialCode,materialName as materialName,materialSpec as materialSpec,materialUnits as materialUnits,sum(nvl(acctAmount,0)) as acctAmount,sum(nvl(amount,0)) as amount,sum(wholeSalePrice*nvl(acctAmount,0)) as saleMoney,sum(retailPrice*nvl(acctAmount,0)) as retailMoney) from MaterialCurrentStock where unitsCode='")
				.append(unitsCode).append("'");
		if (storageCode != null && !"".equals(storageCode)) {
			shql.append(" and storageCode=:storageCode");
		}
		if (materialClass != null && !"".equals(materialClass)) {
			shql.append(" and materialClass=:materialClass");
		}
		if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
			shql.append(" and materialCode>=:beginMaterialCode");
		}
		if (endMaterialCode != null && !"".equals(endMaterialCode)) {
			shql.append(" and materialCode<=:endMaterialCode");
		}
		if (materialName != null && !"".equals(materialName)) {
			shql.append(" and materialName like :materialName");
			conditions.put("materialName", "%" + materialName + "%");
		}
		if (anearNum != null) {
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
		shql
				.append(" group by materialCode,materialName,materialSpec,materialUnits");
		if ("1".equals(stockAmountFlag)) {
			shql.append(" having sum(nvl(amount,0))>0");
		} else if ("2".equals(stockAmountFlag)) {
			shql.append(" having sum(nvl(amount,0))=0");
		} else if ("3".equals(stockAmountFlag)) {
			shql.append(" having sum(nvl(amount,0))<0");
		} else if ("4".equals(stockAmountFlag)) {
			shql.append(" having sum(nvl(amount,0))>=0");
		} else if ("5".equals(stockAmountFlag)) {
			shql.append(" having sum(nvl(amount,0))<=0");
		} else if ("6".equals(stockAmountFlag)) {
			shql.append(" having sum(nvl(amount,0))<>0");
		}
		return getSession().createQuery(shql.toString()).setProperties(
				conditions).list();
	}

	/**
	 * 查询当前库存量
	 * 
	 * @param unitsCode
	 * @param storageCode
	 * @param materialId
	 * @return 作者：周作建 2011.06.07
	 */
	public Double findStockAmountByID(String unitsCode, String storageCode,
			String materialId) {
		log.debug("finding MaterialCurrentStock Stock amount");
		try {
			String queryString = "select sum(nvl(amount,0)) from MaterialCurrentStock where unitsCode= ? and storageCode=? and materialId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, storageCode);
			queryObject.setParameter(2, materialId);
			Object obj = queryObject.uniqueResult();
			return obj == null ? 0d : Double.valueOf(obj.toString());
		} catch (RuntimeException re) {
			log.error("find MaterialCurrentStock failed", re);
			throw re;
		}
	}

	/**
	 * 根据条件查询
	 * 
	 * @date 2011-06-08
	 * @param conditions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialCurrentStock> findByCondition(String conditions) {
		log.debug("finding MaterialCurrentStock by condition");
		try {
			String queryString = "from MaterialCurrentStock " + conditions;
			Query query = getSession().createQuery(queryString);
			return query.list();
		} catch (RuntimeException re) {
			log.error("finding MaterialCurrentStock by condition failed", re);
			throw re;
		}
	}
	public Object[] findByUnitsCodeAndMaterialId(String unitsCode,String materialId) {
		log.debug("finding MaterialCurrentStock Stock amount");
		try {
			String queryString = "select unitsCode,storageCode,materialId, sum(nvl(amount,0)) from MaterialCurrentStock where unitsCode= ?  and materialId=? group by unitsCode,storageCode,materialId ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, materialId);
			Object obj = queryObject.uniqueResult();
			return (Object[]) obj ;
		} catch (RuntimeException re) {
			log.error("find MaterialCurrentStock failed", re);
			throw re;
		}
	}
	/**
	 * 通过唯一主键查询当前现存量记录
	 * 
	 * @param unitsCode
	 * @param storageCode
	 * @param materialId
	 * @param batch
	 * @return
	 * 
	 *         作者：周作建 2011.06.11
	 */
	public MaterialCurrentStock findByUnique(String unitsCode,
			String storageCode, String materialId, String batch) {
		log.debug("finding MaterialCurrentStock by unique Index ");
		try {
			String queryString = "from MaterialCurrentStock where unitsCode=? and storageCode=? and materialId=? and batch=? and (barCode is null or barCode='0')";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, storageCode);
			queryObject.setParameter(2, materialId);
			queryObject.setParameter(3, batch);
			return (MaterialCurrentStock) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by unique Index failed", re);
			throw re;
		}
	}
	
	public MaterialCurrentStock findBarCodeByUnique(String unitsCode,
			String storageCode, String materialId, String batch,String barCode) {
		log.debug("finding MaterialCurrentStock by unique Index ");
		try {
			String queryString = "from MaterialCurrentStock where unitsCode=? and storageCode=? and materialId=? and batch=? and barCode=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, storageCode);
			queryObject.setParameter(2, materialId);
			queryObject.setParameter(3, batch);
			queryObject.setParameter(4, barCode);
			return (MaterialCurrentStock) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by unique Index failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CdMaterialDict> findCurrentMaterialDict(String unitsCode,String storageCode){
		String hql = "from CdMaterialDict d where d.unitsCode=:unitsCode and exists(from MaterialCurrentStock c where c.unitsCode=:unitsCode and c.storageCode=:storageCode and c.materialId=d.materialId)";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CdMaterialDict> findCurrentMaterialDict1(String unitsCode,String storageCode){
		String hql = "from CdMaterialDict d where d.unitsCode=:unitsCode and storageDefault = :storageCode";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).list();
	}
	
	public List<String> findCurrentMaterialDictBatch(String unitsCode,String storageCode,String materialId){
		String hql = "select t.batch from MaterialCurrentStock t where t.unitsCode=:unitsCode and t.storageCode=:storageCode and t.materialId=:materialId";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("materialId", materialId).list();
	}

	/**
	 * 查询所有供应单位基础数据
	 * ryh 2012.12.12
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findProviderDict() {
		log.debug("finding CdProvider BaseData");
		try {
			String queryString = "select new map(providerId as provider,providerName as providerName) from CdProvider where unitsCode in (select unitsCode from SysUnitInfor) order by providerCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}

	/**
	 * 查询所有生产厂家基础数据
	 * ryh 2012.12.20
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findFactoryDict() {
		log.debug("finding CdProvider BaseData");
		try {
			String queryString = "select new map(providerId as factory,providerName as factoryName) from CdProvider where unitsCode in (select unitsCode from SysUnitInfor) order by providerCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
	
	/**
	 * 查询仓库基础数据
	 * ryh 2012.12.14
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findStorageDict() {
		log.debug("finding CdStorageDict BaseData");
		try {
			String queryString = "select new map(storageCode as storage,storageName as storageName) from CdStorageDict where unitsCode in (select unitsCode from SysUnitInfor) order by storageCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}

	public Object findMaxBatchById(String fstrMaterialId) {
		log.debug("finding MaterialCurrentStock");
		try {
			String queryString = "select max(batch) from MaterialCurrentStock where materialId = '"+fstrMaterialId+"'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find MaterialCurrentStock failed", re);
			throw re;
		}
	}
	
	/**
	 * 查询所有科室基础数据
	 * ryh 2013.01.30
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findDeptDict() {
		log.debug("finding CdDeptDict BaseData");
		try {
			String queryString = "select new map(deptCode as dept,deptName as deptName) from CdDeptDict where unitsCode in (select unitsCode from SysUnitInfor) order by deptCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findPersonDict() {
		log.debug("finding CdPersonDict BaseData");
		try {
			String queryString = "select new map(personId as personId,name as personIdName) from CdPersonDict order by personCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}
	
	public List<Map<String,Object>> findMaterialCurrentAmount(String inputCode){
		log.debug("finding MaterialCurrentStock Plan");
		try{
			String queryString = "select new map(r.materialId as materialId,r.batch as batch,r.storageCode as storageCode,r.materialCode as materialCode,r.materialName as materialName," +
					"r.wholeSalePrice as wholeSalePrice,r.amount as amount,r.wholeSalePrice * r.amount as saleMoney,"+
					"r.materialUnits as materialUnits,r.amount as currentAmount) from MaterialCurrentStock r where r.batch != '0' and r.materialId in"+ 
					"(select t.materialId from CdMaterialDict t where t.phoInputCode like :inputCode)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString("inputCode", inputCode+"%");
			return queryObject.list();
		}catch(RuntimeException re){
			log.error("find MaterialCurrentStock Plan failed",re);
			throw re;
		}
	}
	
	public List<Map<String,Object>> findMaterialCurrentAmount(String inputCode,String storageCode){
		log.debug("finding MaterialCurrentStock Plan");
		try{
			String queryString = "select new map(r.materialId as materialId,r.batch as batch,r.storageCode as storageCode,r.materialCode as materialCode,r.materialName as materialName," +
					"r.wholeSalePrice as wholeSalePrice,r.amount as amount,r.wholeSalePrice * r.amount as saleMoney,"+
					"r.materialUnits as materialUnits,r.amount as currentAmount) from MaterialCurrentStock r where r.storageCode =:storageCode and r.batch != '0' and r.materialId in"+ 
					"(select t.materialId from CdMaterialDict t where t.phoInputCode like :inputCode)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString("storageCode",storageCode);
			queryObject.setString("inputCode", inputCode+"%");
			return queryObject.list();
		}catch(RuntimeException re){
			log.error("find MaterialCurrentStock Plan failed",re);
			throw re;
		}
	}
	
	public List<Map<String,Object>> findMaterialCurrentAmount(){
		log.debug("finding MaterialCurrentStock Plan");
		try{
			String queryString = "select new map(r.materialId as materialId,r.batch as batch,r.storageCode as storageCode,r.materialCode as materialCode,r.materialName as materialName," +
					"r.tradePrice as tradePrice,r.amount as amount,"+
					"r.materialUnits as materialUnits,r.amount as currentStockAmount,r.materialClass as materialClass) from MaterialCurrentStock r where r.batch != '0' and r.materialId in"+ 
					"(select t.materialId from CdMaterialDict t)";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		}catch(RuntimeException re){
			log.error("find MaterialCurrentStock Plan failed",re);
			throw re;
		}
	}
	
	public List<Map<String,Object>> findMaterialCurrentAmount1(String storageCode){
		log.debug("finding MaterialCurrentStock Plan");
		try{
			String queryString = "select new map(r.materialId as materialId,r.batch as batch,r.storageCode as storageCode,r.materialCode as materialCode,r.materialName as materialName," +
					"r.tradePrice as tradePrice,r.amount as amount,"+
					"r.materialUnits as materialUnits,r.amount as currentStockAmount,r.materialClass as materialClass,r.materialSpec as materialSpec,t.mainProvider as mainProvider) from MaterialCurrentStock r,CdMaterialDict t where " +
					" r.materialId = t.materialId and r.storageCode =:storageCode ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString("storageCode",storageCode);
			return queryObject.list();
		}catch(RuntimeException re){
			log.error("find MaterialCurrentStock Plan failed",re);
			throw re;
		}
	}
	
	
	public Double findDeliveryAmount(String materialId,String batch,String yearMonth){
		log.debug("find deliver amount");
		try{
			String queryString = "select t.deliveryAmount from MaterialRdsStock t " +
					" where t.materialId = :materialId and t.batch = :batch and t.yearMonth = :yearMonth";
			Query queryObejct = getSession().createQuery(queryString);
			queryObejct.setString("materialId", materialId);
			queryObejct.setString("batch", batch);
			queryObejct.setString("yearMonth", yearMonth);
			Object obj= queryObejct.uniqueResult();
			return obj == null?0d:Double.valueOf(obj.toString());
		}catch(RuntimeException re){
			log.error("find deliver amount failed");
			throw re;
		}
	}
	
	public Double findDeliveryAmount(String materialId,String batch,String yearMonth,String storageCode){
		log.debug("find deliver amount");
		try{
			String queryString = "select t.deliveryAmount from MaterialRdsStock t " +
					" where t.materialId = :materialId and t.batch = :batch and t.yearMonth = :yearMonth and t.storageCode =:storageCode";
			Query queryObejct = getSession().createQuery(queryString);
			queryObejct.setString("materialId", materialId);
			queryObejct.setString("batch", batch);
			queryObejct.setString("yearMonth", yearMonth);
			queryObejct.setString("storageCode",storageCode);
			Object obj= queryObejct.uniqueResult();
			return obj == null?0d:Double.valueOf(obj.toString());
		}catch(RuntimeException re){
			log.error("find deliver amount failed");
			throw re;
		}
	}
	
	public Double findDeliveryAmountSy(String materialId,String yearMonth,String storageCode){
		log.debug("find deliver amount");
		try{
			String queryString = "select sum(nvl(t.deliveryAmount,0)) from MaterialRdsStock t " +
					" where t.materialId = :materialId and t.yearMonth = :yearMonth and t.storageCode =:storageCode";
			Query queryObejct = getSession().createQuery(queryString);
			queryObejct.setString("materialId", materialId);
			queryObejct.setString("yearMonth", yearMonth);
			queryObejct.setString("storageCode",storageCode);
			Object obj= queryObejct.uniqueResult();
			return obj == null?0d:Double.valueOf(obj.toString());
		}catch(RuntimeException re){
			log.error("find deliver amount failed");
			throw re;
		}
	}
	
	public String findMaxMonth(){
		log.debug("find max month");
		try{
			String sql = "select max(yearMonth) from MaterialRdsStock t";
			Query queryObejct = getSession().createQuery(sql);
			return (String)queryObejct.uniqueResult();
		}catch(RuntimeException re){
			log.error("find max month failed");
			throw re;
		}
	}
	public MaterialCurrentStock findVirtualAmountByUniqueIndex(
			String storageCode, String materialId, String batch, String barCode) {
		try {
			String hql = "from MaterialCurrentStock where storageCode=:storageCode and materialId=:materialId and batch=:batch and barCode=:barCode";
			return (MaterialCurrentStock) getSession().createQuery(hql).setString("storageCode", storageCode)
					.setString("materialId", materialId).setString("batch", batch)
					.setString("barCode", barCode).uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(storageCode + "materialId"+materialId + "batch:" +batch+
					"barCode"+barCode);
			return null;
		}
		
	}
	
	/**
	 * 查询虚拟库存
	 * @param unitsCode
	 * @param storageCode
	 * @param materialId
	 * @return
	 */
	public Double findVirtalAmountByID(String unitsCode, String storageCode,
			String materialId) {
		log.debug("finding MaterialCurrentStock Stock amount");
		try {
			String queryString = "select sum(nvl(virtualAmount,0)) from MaterialCurrentStock where unitsCode= ? and storageCode=? and materialId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, storageCode);
			queryObject.setParameter(2, materialId);
			Object obj = queryObject.uniqueResult();
			return obj == null ? 0d : Double.valueOf(obj.toString());
		} catch (RuntimeException re) {
			log.error("find MaterialCurrentStock failed", re);
			throw re;
		}
	}
}