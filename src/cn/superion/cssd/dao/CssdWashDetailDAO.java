package cn.superion.cssd.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.cssd.entity.CssdWashDetail;
import cn.superion.cssd.entity.VCssdWash;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdWashDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdWashDetail
 * @author MyEclipse Persistence Tools
 */

public class CssdWashDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdWashDetailDAO.class);

	public void save(CssdWashDetail transientInstance) {
		log.debug("saving CssdWashDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdWashDetail persistentInstance) {
		log.debug("deleting CssdWashDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdWashDetail findById(java.lang.String id) {
		log.debug("getting CssdWashDetail instance with id: " + id);
		try {
			CssdWashDetail instance = (CssdWashDetail) getSession().get(
					"cn.superion.cssd.entity.CssdWashDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdWashDetail instance) {
		log.debug("finding CssdWashDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdWashDetail").add(
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
		log.debug("finding CssdWashDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdWashDetail as model where model."
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
		log.debug("finding all CssdWashDetail instances");
		try {
			String queryString = "from CssdWashDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdWashDetail merge(CssdWashDetail detachedInstance) {
		log.debug("merging CssdWashDetail instance");
		try {
			CssdWashDetail result = (CssdWashDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdWashDetail instance) {
		log.debug("attaching dirty CssdWashDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdWashDetail instance) {
		log.debug("attaching clean CssdWashDetail instance");
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
				"delete from CssdWashDetail where mainAutoId=:mainAutoId")
				.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<CssdWashDetail> findByMainAutoId(String mainAutoId) {
		return findByProperty("mainAutoId", mainAutoId);
	}

	/**
	 * 
	 * @param unitsCode
	 * @param conditions
	 *            包含：
	 *            <ul>
	 *            <li>beginWashDate 起始清洗日期，精确到秒</li>
	 *            <li>endWashDate 终止清洗日期，精确到秒</li>
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
	//国魁版本
	public List<Object> addUpByCondition(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				"select new map(d.tradePrice as tradPrice,d.packageId as packageId,d.packageName as packageName,d.materialClass as materialClass,d.materialCode as materialCode,d.materialId as materialId,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,d.amount as amount)  from CssdPackageDict p,CssdStockMaster s,CssdWashDetail d  where p.unitsCode=s.unitsCode and p.packageId=s.packageId and p.unitsCode=:unitsCode");
		shql
				.append(" and d.currentStatus<>'1' and exists(from CssdWashMaster m where m.autoId=d.mainAutoId and m.unitsCode=:unitsCode and m.currentStatus='1' and m.verifyDate between :beginWashDate and :endWashDate)");
		shql
				.append(" group by d.tradePrice,d.packageId,d.packageName,d.amount,d.materialClass,d.materialCode,d.materialId,d.materialName,d.materialSpec,d.materialUnits order by d.packageId");
		return getSession().createQuery(shql.toString()).setString("unitsCode",
				unitsCode).setProperties(conditions).list();
	}
	@SuppressWarnings("unchecked")
	//朱玉峰版本
	public List<Object> addUpByConditionzyf(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				"select new map(d.tradePrice as tradPrice,d.packageId as packageId,d.packageName as packageName,d.materialClass as materialClass,d.materialCode as materialCode,d.materialId as materialId,d.materialName as materialName,d.materialSpec as materialSpec,d.materialUnits as materialUnits,d.amount as amount)  from CssdPackageDict p,CssdWashDetail d  where p.unitsCode=:unitsCode");
		shql
				.append(" and d.currentStatus<>'1' and d.washDate between :beginWashDate and :endWashDate  and exists(from CssdWashMaster m where m.autoId=d.mainAutoId and m.unitsCode=:unitsCode and m.currentStatus='1')");
		shql
				.append(" group by d.tradePrice,d.packageId,d.packageName,d.amount,d.materialClass,d.materialCode,d.materialId,d.materialName,d.materialSpec,d.materialUnits order by d.packageId");
		return getSession().createQuery(shql.toString()).setString("unitsCode",
				unitsCode).setProperties(conditions).list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CssdWashDetail> addUpByCondition2(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				"select distinct(d.auto_Id) as autoId,d.main_Auto_Id as mainAutoId,d.serial_No as serialNo,d.package_Class as packageClass,d.package_Id as packageId,d.package_Name as packageName,d.material_Id as materialId,d.material_Code as materialCode,d.material_Spec as materialSpec,d.material_Units as materialUnits,d.factory_Code as factoryCode,d.amount as amount,d.WASH_PACKAGE_AMOUNT as washPackageAmout,d.detail_Remark as detailRemark,d.person_Id as personId,d.wash_Date as washDate,d.packaged_Amount as packagedAmount,d.current_Status as currentStatus ,d.MATERIAL_CLASS as materialClass,d.MATERIAL_NAME as materialName,d.trade_Price as tradePrice from Cssd_Wash_Detail cd ,Cssd_Wash_Detail d ,Cssd_Wash_Master cw where cd.main_Auto_Id=cw.auto_Id and cw.units_Code=:unitsCode and cd.wash_Date between :beginWashDate and :endWashDate and cd.current_Status<>'1' and cw.current_Status='1'");
		return getSession().createSQLQuery(shql.toString()).setString(
				"unitsCode", unitsCode).setProperties(conditions).list();
	}

	@SuppressWarnings("unchecked")
	public List<String> addUpByCondition1(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
				"select distinct cd.packageId from CssdWashDetail cd ,CssdWashMaster cw where cd.mainAutoId=cw.autoId and cw.unitsCode=:unitsCode  and cd.washDate >=:beginWashDate and cd.washDate<=:endWashDate   and cd.currentStatus<>'1' and cw.currentStatus='1'");
		return getSession().createQuery(shql.toString()).setString(
				"unitsCode", unitsCode).setProperties(conditions).list();
	}
	@SuppressWarnings("unchecked")
	public List<Object> amountList(String unitsCode,
			Map<String, Object> conditions) {
		StringBuilder shql = new StringBuilder(
		"select distinct(cd.mainAutoId),cd.packageId,cd.washPackageAmount,cw.autoId from CssdWashDetail cd ,CssdWashMaster cw where cd.mainAutoId=cw.autoId and cw.unitsCode=:unitsCode  and cd.washDate >=:beginWashDate and cd.washDate<=:endWashDate   and cd.currentStatus<>'1' and cw.currentStatus='1'");
		return getSession().createQuery(shql.toString()).setString(
				"unitsCode", unitsCode).setProperties(conditions).list();
	}

	@SuppressWarnings("unchecked")
	public List<CssdWashDetail> addUpByCondition2(String packageId) {
		StringBuilder shql = new StringBuilder(
				"from CssdWashDetail csd where csd.packageId=:packageId");
		return getSession().createQuery(shql.toString()).setString("packageId",
				packageId).list();
	}
}