package cn.superion.cssd.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdPackageDict;
import cn.superion.cssd.entity.CssdStockMaster;

import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdPackageDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdPackageDict
 * @author MyEclipse Persistence Tools
 */

public class CssdPackageDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdPackageDictDAO.class);

	public void save(CssdPackageDict transientInstance) {
		log.debug("saving CssdPackageDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdPackageDict persistentInstance) {
		log.debug("deleting CssdPackageDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdPackageDict findById(String unitsCode, String packageId) {
		log.debug("getting CssdPackageDict instance with id: " + packageId);
		try {
			CssdPackageDict instance = (CssdPackageDict) getSession()
					.createQuery(
							"from CssdPackageDict where unitsCode=:unitsCode and packageId=:packageId")
					.setString("unitsCode", unitsCode).setString("packageId",
							packageId).uniqueResult();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdPackageDict instance) {
		log.debug("finding CssdPackageDict instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdPackageDict").add(
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
	public List<CssdPackageDict> findByProperty(String propertyName,
			Object value) {
		log.debug("finding CssdPackageDict instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdPackageDict as model where model."
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
	public List<CssdPackageDict> findAll() {
		log.debug("finding all CssdPackageDict instances");
		try {
			String queryString = "from CssdPackageDict";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdPackageDict merge(CssdPackageDict detachedInstance) {
		log.debug("merging CssdPackageDict instance");
		try {
			CssdPackageDict result = (CssdPackageDict) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdPackageDict instance) {
		log.debug("attaching dirty CssdPackageDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdPackageDict instance) {
		log.debug("attaching clean CssdPackageDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 根据输入法查询当前物资档案
	 * 
	 * @param fstrUnitsCode
	 * @param fstrInputCode
	 * @param fstrInputType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CssdPackageDict> findByInputCode(String fstrUnitsCode,
			String fstrInputCode, String fstrInputType, int limit) {
		log.debug("finding CssdPackageDict instance with inputCode ");
		try {
			String queryString = null;
			if (fstrInputType.equals("1")) {
				queryString = "from CssdPackageDict where unitsCode =? and phoInputCode like ?";
			} else {
				queryString = "from CssdPackageDict where unitsCode =? and fiveInputCode like ?";
			}
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrUnitsCode);
			queryObject.setParameter(1, fstrInputCode + "%");
			return queryObject.setFirstResult(0).setMaxResults(limit).list();
		} catch (RuntimeException re) {
			log.error("find by inputCode failed", re);
			throw re;
		}
	}

	/**
	 * 查询有效的物品包字典
	 * 
	 * @param fstrUnitsCode
	 * @param fstrInputCode
	 * @param fstrInputType
	 * @param limit
	 * @return
	 * @author 曹国魁
	 */
	@SuppressWarnings("unchecked")
	public List<CssdPackageDict> findValidByInputCode(String fstrUnitsCode,
			String fstrInputCode, String fstrInputType, int limit) {
		log.debug("finding CssdPackageDict instance with inputCode ");
		try {
			String queryString = null;
			queryString = "from CssdPackageDict where "
					+ (fstrInputType.equals("1") ? "phoInputCode"
							: "fiveInputCode")
					+ " like ? and startDate<=sysDate and (stopDate>sysdate or stopDate is null)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrInputCode + "%");
			return queryObject.setFirstResult(0).setMaxResults(limit).list();
		} catch (RuntimeException re) {
			log.error("find by inputCode failed", re);
			throw re;
		}
	}
	/**
	 * 查询有效的物品包字典
	 * 
	 * @param fstrUnitsCode
	 * @param fstrInputCode
	 * @param fstrInputType
	 * @param limit
	 * @return
	 * @author 曹国魁
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findValidByInDeptCode(String fstrUnitsCode,
			String fstrInputCode, String deptCode, int limit) {
//		String sql="select csm.* from  cssd_Stock_Master csm,cssd_Provide_Master crm where csm.units_Code="+fstrUnitsCode+"and crm.dept_Code="+deptCode+"and crm.current_Status='3' and csm.provide_Auto_Id=crm.auto_Id and csm.current_Status='2'";
//		
		StringBuilder shql = new StringBuilder(
				"select new map(csm.packageNo as packageNo) from  CssdStockMaster csm,CssdProvideMaster crm  where csm.unitsCode='"
						+ fstrUnitsCode
						+ "' and crm.deptCode='"
						+ deptCode
						+ "' and crm.currentStatus='3' and csm.provideAutoId=crm.autoId and csm.currentStatus='2'");

//		shql.append(" group by deptCode,materialCode,materialName,factoryCode,materialSpec,materialUnits");
		return getSession().createQuery(shql.toString()).list();
	}
	
	public List<Object> findValidByInDeptCode(String fstrUnitsCode,String fstrDeptCode,
			String fstrInputCode, String fstrInputType, int limit) {
		log.debug("finding CssdPackageDict instance with inputCode ");
		try {
			String queryString = null;
			queryString = "select new map(t.packageName as packageName, t.packageMode as packageMode,t.packageUnits as packageUnits,t.tradePrice as tradePrice,t.packageClass as packageClass,t.packageId as packageId,t.packageUnits as packageUnits)from CssdPackageDict t, CssdDeptVsPackageClass p where p.classCode = t.packageClass and p.deptCode = '"+fstrDeptCode+"' and "
					+ (fstrInputType.equals("1") ? "t.phoInputCode"
							: "t.fiveInputCode")
					+ " like ? and t.startDate<=sysDate and (t.stopDate>sysdate or t.stopDate is null)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrInputCode + "%");
			return queryObject.setFirstResult(0).setMaxResults(limit).list();
		} catch (RuntimeException re) {
			log.error("find by inputCode failed", re);
			throw re;
		}
	}
	
	public void update(CssdPackageDict transientInstance) {
		try {
			getSession().update(transientInstance);
		} catch (RuntimeException re) {
			log.error("find baseData failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findByPropertyMaster(String packageNo,
			Object value) {

		log.debug("finding CssdStockMaster instance with packageNo: "
				+ packageNo + ", value: " + value);
		try {
			String queryString = "from CssdStockMaster as model where model."
					+ packageNo + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by classCode name failed", re);
			throw re;
		}
	}

	/**
	 * 功能说明：根据条件，查询物品包字典列表（分页）
	 * @param condition
	 * @param start
	 * @param limits
	 * @return
	 * @author 
	 */
	@SuppressWarnings("unchecked")
	public List<CssdPackageDict> findByCondition(String condition,int start,int limits) {
		log.debug("finding CssdPackageDict by condition");
		try {
			String queryString = "from CssdPackageDict "+condition+" order by packageId";
			Query query = getSession().createQuery(queryString);
//			query.setFirstResult(start);
//			query.setMaxResults(limits);
			return query.list();
		} catch (RuntimeException re) {
			log.error("finding CssdPackageDict by condition failed", re);
			throw re;
		}
	}
	
	/**
	 * 功能说明：根据条件，统计物品包字典列表数量
	 * @param condition
	 * @return
	 * @author 
	 */
	public int countByCondition(String condition) {
		log.debug("finding CssdPackageDict by condition");
		try {
			String queryString = "select count(*) from CssdPackageDict "+condition;
			Query query = getSession().createQuery(queryString);
			Object result=query.uniqueResult();
			return result==null?0:Integer.parseInt(result.toString());
		} catch (RuntimeException re) {
			log.error("finding CssdPackageDict by condition failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Object[] findAutoIdsByCondition(int start, int limit,
			String unitsCode, Map<String, Object> conditions) {

		Object[] objs = new Object[2];

		String packageClass = (String) conditions.get("packageClass");
		String phoInputCode = (String) conditions.get("phoInputCode");
		String fiveInputCode = (String) conditions.get("fiveInputCode");
		String packageId = (String) conditions.get("packageId");
		StringBuilder shql = new StringBuilder(
				"from CssdPackageDict  where unitsCode='").append(
				unitsCode).append("'");
		if (packageClass != null && !"".equals(packageClass)) {
			shql.append(" and packageClass=:packageClass");
		}
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			shql.append(" and phoInputCode like '"+phoInputCode+"%'");
		}
		if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			shql.append(" and fiveInputCode like '"+fiveInputCode+"%'");
		}
		if (packageId != null && !"".equals(packageId)) {
			shql.append(" and packageId=:packageId");
		}
		Query countQuery = getSession().createQuery(
				"select count(*)" + shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions)
				.uniqueResult().toString());
		List<CssdPackageDict> list = query.setProperties(conditions)
				.setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}

}