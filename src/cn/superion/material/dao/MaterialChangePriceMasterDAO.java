package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialChangePriceMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialChangePriceMaster entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see cn.superion.material.entity.MaterialChangePriceMaster
 * @author MyEclipse Persistence Tools
 */

public class MaterialChangePriceMasterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialChangePriceMasterDAO.class);

	public void save(MaterialChangePriceMaster transientInstance) {
		log.debug("saving MaterialChangePriceMaster instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialChangePriceMaster persistentInstance) {
		log.debug("deleting MaterialChangePriceMaster instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialChangePriceMaster findById(java.lang.String id) {
		log.debug("getting MaterialChangePriceMaster instance with id: " + id);
		try {
			MaterialChangePriceMaster instance = (MaterialChangePriceMaster) getSession()
					.get("cn.superion.material.entity.MaterialChangePriceMaster",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialChangePriceMaster> findByExample(
			MaterialChangePriceMaster instance) {
		log.debug("finding MaterialChangePriceMaster instance by example");
		try {
			List<MaterialChangePriceMaster> results = getSession()
					.createCriteria(
							"cn.superion.material.entity.MaterialChangePriceMaster")
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
	public List<MaterialChangePriceMaster> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialChangePriceMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialChangePriceMaster as model where model."
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
	public List<MaterialChangePriceMaster> findAll() {
		log.debug("finding all MaterialChangePriceMaster instances");
		try {
			String queryString = "from MaterialChangePriceMaster";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialChangePriceMaster merge(
			MaterialChangePriceMaster detachedInstance) {
		log.debug("merging MaterialChangePriceMaster instance");
		try {
			MaterialChangePriceMaster result = (MaterialChangePriceMaster) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialChangePriceMaster instance) {
		log.debug("attaching dirty MaterialChangePriceMaster instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialChangePriceMaster instance) {
		log.debug("attaching clean MaterialChangePriceMaster instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 检验单据编号唯一性
	 * 
	 * @param unitsCode
	 * @param billNo
	 * @return true:是唯一的；false:不是唯一的
	 */
	public boolean checkBillNoUnique(String unitsCode, String billNo) {
		log.debug("check billNo unique MaterialChangePriceMaster with billNo");
		try {
			String queryString = "select count(*) from MaterialChangePriceMaster where unitsCode =? and billNo=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitsCode);
			queryObject.setParameter(1, billNo);
			int cnt = Integer.parseInt(queryObject.uniqueResult().toString());
			return cnt == 0;
		} catch (RuntimeException re) {
			log.error("check billNo unique  failed", re);
			throw re;
		}
	}

	/**
	 * 根据条件查询
	 * 
	 * @date 2011-06-14
	 * @param conditions
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Object> findByCondition(String conditions) {
		log.debug("finding MaterialChangePriceMaster by condition");
		try {
			String queryString = " from MaterialChangePriceMaster" + conditions;
			Query query = getSession().createQuery(queryString);
			return query.list();
		} catch (RuntimeException re) {
			log.error("finding MaterialChangePriceDetail by condition failed",
					re);
			throw re;
		}
	}

	/**
	 * 查询当前主记录ID列表
	 * 
	 * @param unitsCode
	 * @param map
	 * @return 作者：周作建 2011.06.21
	 */
	@SuppressWarnings("unchecked")
	public List<String> findAutoIdsByCondition(String unitsCode,
			Map<String, Object> map) {
		log.debug("finding MaterialChangePriceMaster by map");
		try {
			boolean flag = false;
			String beginBillNo = (String) map.get("beginBillNo");
			String endBillNo = (String) map.get("endBillNo");

			String beginBillDate = (String) map.get("beginBillDate");
			String endBillDate = (String) map.get("endBillDate");

			String maker = (String) map.get("maker");

			String storageCode = (String) map.get("storageCode");

			String beginMaterialClass = (String) map.get("beginMaterialClass");
			String endMaterialClass = (String) map.get("endMaterialClass");
			String beginMaterialCode = (String) map.get("beginMaterialCode");
			String endMaterialCode = (String) map.get("endMaterialCode");
			String materialName = (String) map.get("materialName");

			StringBuilder conditions = new StringBuilder();
			conditions
					.append("select distinct m.autoId from MaterialChangePriceMaster m,MaterialChangePriceDetail d where m.autoId= d.mainAutoId");
			conditions.append(" and m.unitsCode= '" + unitsCode + "'");
			// 单据编码范围
			if (beginBillNo != null && beginBillNo.length() > 0) {
				conditions.append(" and m.billNo>='" + beginBillNo + "'");
			}
			if (endBillNo != null && endBillNo.length() > 0) {
				conditions.append(" and m.billNo<='" + endBillNo + "'");
			}
			// 单据日期范围
			if (beginBillDate != null && beginBillDate.length() > 0) {
				conditions.append(" and m.billDate >= to_date('"
						+ beginBillDate + " 00:00:00"
						+ "','yyyy-mm-dd hh24:mi:ss')");
			}
			if (endBillDate != null && endBillDate.length() > 0) {
				conditions.append(" and m.billDate <= to_date('" + endBillDate
						+ " 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')");
			}
			// 制单人
			if (maker != null && maker.length() > 0) {
				conditions.append(" and m.maker= '" + maker + "'");
			}
			// 明细表中的条件
			// 仓库
			if (storageCode != null && storageCode.length() > 0 && flag) {
				conditions.append(" and d.storageCode= '" + storageCode + "'");
			}
			// 物资分类
			if (beginMaterialClass != null && beginMaterialClass.length() > 0) {
				conditions.append(" and d.materialClass>='"
						+ beginMaterialClass + "'");
			}
			if (endMaterialClass != null && endMaterialClass.length() > 0) {
				conditions.append(" and d.materialClass>='" + endMaterialClass
						+ "'");
			}
			// 物资编码
			if (beginMaterialCode != null && beginMaterialCode.length() > 0) {
				conditions.append(" and d.materialCode>='" + beginMaterialCode
						+ "'");
			}
			if (endMaterialCode != null && endMaterialCode.length() > 0) {
				conditions.append(" and d.materialCode<='" + endMaterialCode
						+ "'");
			}
			// 物资名称
			if (materialName != null && materialName.length() > 0) {
				conditions.append(" and d.materialName like '" + materialName
						+ "%'");
			}
			conditions.append(" order by m.autoId");

			String queryString = conditions.toString();
			Query query = getSession().createQuery(queryString);
			return query.list();
		} catch (RuntimeException re) {
			log.error("finding MaterialChangePriceDetail by condition failed",
					re);
			throw re;
		}
	}

	/**
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>beginBillNo 起始调价单号</li>
	 *            <li>endBillNo 结束调价单号</li>
	 *            <li>beginBillDate 调价起始日期</li>
	 *            <li>endBillDate 调价起始日期</li>
	 *            <li>maker 制单人</li>
	 *            <li>beginMakeDate 起始制单日期</li>
	 *            <li>endMakeDate 结束制单日期</li>
	 *            调价明细表
	 *            <li>materialClass 物资分类</li>
	 *            <li>materialCode 物资编码</li>
	 *            </ul>
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public Object[] findByCondition(int start, int limit, String unitsCode,
			Map<String, Object> conditions) {
		boolean flag = false;
		Object[] objs = new Object[2];

		String beginBillNo = (String) conditions.get("beginBillNo");
		String endBillNo = (String) conditions.get("endBillNo");

		String beginMakeDate = (String) conditions.get("beginMakeDate");
		String endMakeDate = (String) conditions.get("endMakeDate");
		String beginBillDate = (String) conditions.get("beginBillDate");
		String endBillDate = (String) conditions.get("endBillDate");

		String maker = (String) conditions.get("maker");

		String beginMaterialClass = (String) conditions
				.get("beginMaterialClass");
		String endMaterialClass = (String) conditions.get("endMaterialClass");
		String beginMaterialCode = (String) conditions.get("beginMaterialCode");
		String endMaterialCode = (String) conditions.get("endMaterialCode");

		StringBuilder shql = new StringBuilder(
				"from MaterialChangePriceMaster m where unitsCode='").append(
				unitsCode).append("'");
		// 单据编码范围
		if (beginBillNo != null && !"".equals(beginBillNo)) {
			shql.append(" and m.billNo>=:beginBillNo");
		}
		if (endBillNo != null && !"".equals(endBillNo)) {
			shql.append(" and m.billNo<=:endBillNo");
		}
		// 制单日期范围
		if (beginMakeDate != null) {
			shql.append(" and m.makeDate >= to_date('" + beginMakeDate
						+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endMakeDate != null) {
			shql.append(" and m.makeDate <= to_date('" + endMakeDate
						+ " 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		// 调价日期
		if (beginBillDate != null) {
			shql.append(" and m.billDate >= to_date('" + beginBillDate
						+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endBillDate != null) {
			shql.append(" and m.billDate <= to_date('" + endBillDate
						+ " 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')");
		}
		// 制单人
		if (maker != null && !"".equals(maker)) {
			shql.append(" and m.maker=:maker");
		}
		// 明细表中的条件
		StringBuilder shql2 = new StringBuilder(
				"from MaterialChangePriceDetail d where d.mainAutoId=m.autoId");
		// 物资分类
		if (beginMaterialClass != null && !"".equals(beginMaterialClass)) {
			flag = true;
			shql2.append(" and d.materialClass>=:beginMaterialClass");
		}
		if (endMaterialClass != null && !"".equals(endMaterialClass)) {
			flag = true;
			shql2.append(" and d.materialClass>=: endMaterialClass");
		}
		// 物资编码
		if (beginMaterialCode != null && !"".equals(beginMaterialCode)) {
			flag = true;
			shql2.append(" and d.materialCode>=:beginMaterialCode");
		}
		if (endMaterialCode != null && !"".equals(endMaterialCode)) {
			flag = true;
			shql2.append(" and d.materialCode<=:endMaterialCode	");
		}
		if (flag) {
			shql.append(" and exists(").append(shql2).append(")");
		}
		Query countQuery = getSession().createQuery(
				"select count(*) " + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<MaterialChangePriceMaster> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
	@SuppressWarnings("unchecked")
	public List<Object> findByCondition(String unitsCode,Map<String,Object> map){
		try {
			String salerCode = (String) map.get("salerCode");
			Date beginBillDate = (Date) map.get("beginBillDate");
			Date endBillDate = (Date) map.get("endBillDate");
			String storageCode= (String) map.get("storageCode");
			StringBuilder shql = new StringBuilder();
			shql
					.append("select m.salerName as salerName," +
							"m.salerCode as salerCode," +
							"d.storageCode as storageCode," +
							"d.materialClass as materialClass," +
							"d.materialId as materialId," +
							"d.materialCode as materialCode," +
							"d.materialName as materialName," +
							"d.materialSpec as materialSpec," +
							"d.materialUnits as materialUnits," +
							"d.amount as amount," +
							"d.oldTradePrice as oldTradePrice," +
							"d.newTradePrice as newTradePrice," +
							"d.oldWholeSalePrice as oldWholeSalePrice," +
							"d.newWholeSalePrice as newWholeSalePrice," +
							"d.oldInvitePrice as oldInvitePrice," +
							"d.newInvitePrice as newInvitePrice, " +
							"d.oldRetailPrice as oldRetailPrice, " +
							"d.newRetailPrice as newRetailPrice ,"+
							"d.startDate as startDate from MaterialChangePriceMaster m,MaterialChangePriceDetail d where m.autoId= d.mainAutoId");
			shql.append(" and m.unitsCode= '" + unitsCode + "'");
			// 供应商
			if (salerCode != null && !"".equals(salerCode)) {
				shql.append(" and m.salerCode ='" + salerCode + "'");
			}
			// 单据日期范围
			if (beginBillDate != null) {
				shql.append(" and m.billDate>=:beginBillDate");
			}
			if (endBillDate != null) {
				shql.append(" and m.billDate<=:endBillDate");
			}
			// 明细表中的条件
			// 仓库
			if (storageCode != null && !"".equals(storageCode)) {
				shql.append(" and d.storageCode= '" + storageCode + "'");
			}
			shql.append(" order by m.autoId");

			String queryString = shql.toString();
			Query query = getSession().createQuery(queryString);
			return query.setProperties(map).list();
		} catch (RuntimeException re) {
			log.error("finding MaterialChangePriceDetail by condition failed",
					re);
			throw re;
		}
	}
	
	/**
	 * @author hening
	 * @param fstrUnitsCode
	 * @param fstrBillNo
	 * @return
	 */
	public MaterialChangePriceMaster findByBillNo(String fstrUnitsCode,String fstrBillNo) {
		log.debug("finding MaterialChangePriceMaster by unitsCode and billno ");
		try {
			String queryString = "from MaterialChangePriceMaster where unitsCode= ? and billNo =? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString(0, fstrUnitsCode);
			queryObject.setString(1, fstrBillNo);
			return (MaterialChangePriceMaster) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find MaterialChangePriceMaster by unitsCode and billNo failed", re);
			throw re;
		}
	}
	
}