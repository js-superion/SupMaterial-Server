package cn.superion.cssd.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdRetrieveDetail;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdRetrieveDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdRetrieveDetail
 * @author MyEclipse Persistence Tools
 */

public class CssdRetrieveDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdRetrieveDetailDAO.class);

	public void save(CssdRetrieveDetail transientInstance) {
		log.debug("saving CssdRetrieveDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdRetrieveDetail persistentInstance) {
		log.debug("deleting CssdRetrieveDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdRetrieveDetail findById(java.lang.String id) {
		log.debug("getting CssdRetrieveDetail instance with id: " + id);
		try {
			CssdRetrieveDetail instance = (CssdRetrieveDetail) getSession()
					.get("cn.superion.cssd.entity.CssdRetrieveDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdRetrieveDetail instance) {
		log.debug("finding CssdRetrieveDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdRetrieveDetail").add(
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
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding CssdRetrieveDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdRetrieveDetail as model where model."
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
	public List findAll() {
		log.debug("finding all CssdRetrieveDetail instances");
		try {
			String queryString = "from CssdRetrieveDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdRetrieveDetail merge(CssdRetrieveDetail detachedInstance) {
		log.debug("merging CssdRetrieveDetail instance");
		try {
			CssdRetrieveDetail result = (CssdRetrieveDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdRetrieveDetail instance) {
		log.debug("attaching dirty CssdRetrieveDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdRetrieveDetail instance) {
		log.debug("attaching clean CssdRetrieveDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delByMainAutoId(String mainAutoId) {
		getSession().createQuery(
		"delete from CssdRetrieveDetail where mainAutoId=:mainAutoId")
		.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<CssdRetrieveDetail> findByMainAutoId(String mainAutoId) {
		return findByProperty("mainAutoId", mainAutoId);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findPackgageNo(String mainAutoId){
		String hql = "select distinct packageNo from CssdRetrieveDetail where mainAutoId=:mainAutoId";
		return getSession().createQuery(hql).setString("mainAutoId", mainAutoId).list();
	}
	/**
	 * 根据回收日期（精确到秒）范围、物资Id查询
	 * @param beginRetrieveDate 回收主记录ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CssdRetrieveDetail> findRetrieveListByMaterialId(Date beginRetrieveDate,Date endRetrieveDate,String materialId){
		String hql = "from CssdRetrieveDetail s where exists(from CssdRetrieveMaster r where r.verifyDate>=:beginRetrieveDate and r.verifyDate>=:beginRetrieveDate" +
				" and s.materialId=:materialId )";
		return getSession().createQuery(hql).setString("materialId", materialId).list();
	}
	/**
	 * 查询与回收记录相对应的物品包信息
	 * @param mainAutoId 回收主记录ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CssdStockMaster> findPackageList(String mainAutoId){
		String hql = "from CssdStockMaster s where exists(from CssdRetrieveDetail r where r.mainAutoId=:mainAutoId and r.packageNo=s.packageNo)";
		return getSession().createQuery(hql).setString("mainAutoId", mainAutoId).list();
	}

	public void cleanWashSignByWashMasterAutoId(String washedAutoId) {
		String hql = "update CssdRetrieveDetail set washedAutoId=null,currentStatus='0' where washedAutoId=:washedAutoId";
		getSession().createQuery(hql).setString("washedAutoId", washedAutoId).executeUpdate();
	}

	public void updateWashedAutoId(String washedAutoId,String unitsCode,Date beginRetrieveDate, Date endRetrieveDate) {
		String hql = "update CssdRetrieveDetail d set washedAutoId=:washedAutoId,currentStatus='1' where exists(from CssdRetrieveMaster m where m.autoId=d.mainAutoId and m.unitsCode=:unitsCode and m.currentStatus='1' and m.verifyDate between :beginRetrieveDate and :endRetrieveDate)";
		getSession().createQuery(hql).setString("washedAutoId", washedAutoId).setString("unitsCode", unitsCode).setTime("beginRetrieveDate", beginRetrieveDate).setTime("endRetrieveDate", endRetrieveDate).executeUpdate();
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions 包含：
	 *            <ul>
	 *            <li>beginRetrieveDate 起始回收日期，精确到秒</li>
	 *            <li>endRetrieveDate 终止回收日期，精确到秒</li>
	 *            </ul>
	 * @return {@code List<Map<String,Object>>},包含：
	 *         <ul>
	 *         <li>packageName 物品包名称</li>
	 *         <li>materialClass 物资分类</li>
	 *         <li>materialCode 物资编码</li>
	 *         <li>materialName 物资名称</li>
	 *         <li>materialSpec 规格型号</li>
	 *         <li>materialUnits 单位</li>
	 *         <li>amount 实回数量</li>
	 *         </ul>
	 */
	@SuppressWarnings("unchecked")
	public List<Object> addUpByCondition(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder("select new map(d.packageNo as packageNo,d.tradePrice as tradePrice,d.packageId as packageId,d.packageName as packageName,d.materialClass as materialClass,d.materialCode as materialCode,d.materialId as materialId,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,sum(d.amount) as amount,d.packageAmount as packageAmount) from CssdPackageDict p,CssdStockMaster s,CssdRetrieveDetail d  where p.unitsCode=s.unitsCode and p.packageId=s.packageId and d.packageNo=s.packageNo and p.unitsCode=:unitsCode");
		shql.append(" and d.currentStatus<>'1' and exists(from CssdRetrieveMaster m where m.autoId=d.mainAutoId and m.unitsCode=:unitsCode and m.currentStatus='1' and m.verifyDate between :beginRetrieveDate and :endRetrieveDate)");
		shql.append(" group by d.packageNo,d.tradePrice,d.materialClass,d.packageId,d.packageName,d.materialCode,d.materialId,d.materialName,d.materialSpec,d.materialUnits,d.packageAmount order by d.packageNo");
		return getSession().createQuery(shql.toString()).setString("unitsCode", unitsCode).setProperties(conditions).list();
	}
}