package cn.superion.materialDept.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.entity.MaterialPatsDetail;
import cn.superion.materialDept.entity.VMaterialPats;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialPatsDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.materialDept.entity.MaterialPatsDetail
 * @author MyEclipse Persistence Tools
 */

public class MaterialPatsDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialPatsDetailDAO.class);

	public void save(MaterialPatsDetail transientInstance) {
		log.debug("saving MaterialPatsDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialPatsDetail persistentInstance) {
		log.debug("deleting MaterialPatsDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialPatsDetail findById(java.lang.String id) {
		log.debug("getting MaterialPatsDetail instance with id: " + id);
		try {
			MaterialPatsDetail instance = (MaterialPatsDetail) getSession()
					.get("cn.superion.materialDept.entity.MaterialPatsDetail",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialPatsDetail> findByExample(MaterialPatsDetail instance) {
		log.debug("finding MaterialPatsDetail instance by example");
		try {
			List<MaterialPatsDetail> results = getSession().createCriteria(
					"cn.superion.materialDept.entity.MaterialPatsDetail").add(
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
	public List<MaterialPatsDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding MaterialPatsDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialPatsDetail as model where model."
					+ propertyName + "= ? order by model.barCode";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public MaterialPatsDetail merge(MaterialPatsDetail detachedInstance) {
		log.debug("merging MaterialPatsDetail instance");
		try {
			MaterialPatsDetail result = (MaterialPatsDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialPatsDetail instance) {
		log.debug("attaching dirty MaterialPatsDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialPatsDetail instance) {
		log.debug("attaching clean MaterialPatsDetail instance");
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
		"delete from MaterialPatsDetail where mainAutoId=:mainAutoId")
		.setString("mainAutoId", mainAutoId).executeUpdate();
	}

	public List<MaterialPatsDetail> findByMainAutoId(String fstrMainAutoId) {
		return this.findByProperty("mainAutoId", fstrMainAutoId);
	}
	
	@SuppressWarnings("unchecked")
	public List<MaterialPatsDetail> findUsedByMainAutoId(String fstrMainAutoId) {
		String hql = "from MaterialPatsDetail where mainAutoId=:mainAutoId and amount>0";
		return getSession().createQuery(hql).setString("mainAutoId", fstrMainAutoId).list();
	}
	
	public Short findMaxSerialNo(String fstrMainAutoId){
		String hql = "select max(serialNo) from MaterialPatsDetail where mainAutoId=:mainAutoId ";
		Short maxSerialNo = (Short)getSession().createQuery(hql).setString("mainAutoId", fstrMainAutoId).uniqueResult();
		return maxSerialNo == null ? 0 : maxSerialNo;
	}

	/**
	 * 查找需要退费的材料明细
	 * @param fstrAutoIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialPatsDetail> findByAutoIds(String[] fstrAutoIds) {
		String hql = "from MaterialPatsDetail where autoId in (:autoIds)";
		return getSession().createQuery(hql).setParameterList("autoIds", fstrAutoIds).list();
	}

	/**
	 * 查找不需要退费的材料明细
	 * @param fstrAutoIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialPatsDetail> findNoByAutoIds(String[] fstrAutoIds,String fstrMainAutoId) {
		String hql = "from MaterialPatsDetail where autoId not in (:autoIds) and mainAutoId=:mainAutoId";
		return getSession().createQuery(hql).setParameterList("autoIds", fstrAutoIds).setString("mainAutoId", fstrMainAutoId).list();
	}
	
	/**
	 * @author 芮玉红
	 * @date 2012.9.26
	 * 
	 * @param conditions 包含
	 *            <ul>
	 *            <li>mainAutoId 主记录标识号</li>
	 *            <li>factoryCode 生产厂家</li>
	 *            <li>beginAccountDate 起始使用日期</li>
	 *            <li>endAccountDate 结束使用日期</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VMaterialPats> findValueDetailByCondition(
			Map<String, Object> conditions) {
		String isGive = (String) conditions.get("isGive");
		String supplyDeptCode = (String) conditions.get("supplyDeptCode");
		String materialCode = (String) conditions.get("materialCode");
		Date beginAccountDate = (Date) conditions.get("beginAccountDate");
		Date endAccountDate = (Date) conditions.get("endAccountDate");
		String operationType = RdConstant.R_AGENCY;
		
		StringBuilder shql2 = new StringBuilder(
				"from VMaterialPats p where p.barCode in (select d.barCode from VMaterialRdsDept d where d.supplyDeptCode='"+supplyDeptCode+"' and d.operationType='"+operationType+"') and p.refundSign='0' and p.factInSign='0'");
		if (materialCode != null && !materialCode.equals("")) {
			shql2.append(" and p.materialCode=:materialCode");
		}
		if (beginAccountDate != null) {
			shql2.append(" and p.accountDate>=:beginAccountDate");
		}
		if (endAccountDate != null) {
			shql2.append(" and p.accountDate<=:endAccountDate");
		}
		if (isGive != null && isGive.equals("1")) {
			shql2.append(" and p.isGive='1'");
		}
		else
		{
			shql2.append(" and (p.isGive='0' or p.isGive is null)");
		}
		shql2.append(" order by p.patientId,p.materialCode,p.autoId");
				
		return getSession().createQuery(shql2.toString()).setProperties(conditions).list();
	}

	public void updateRefundSignById(String autoId,String refundSign) {
		String hql = "update MaterialPatsDetail set refundSign='"+refundSign+"' where autoId = '"+autoId+"'";
		getSession().createQuery(hql).executeUpdate();
	}
	
	public void updateFactInSignById(String autoId,String factInSign) {
		String hql = "update MaterialPatsDetail set factInSign='"+factInSign+"' where autoId = '"+autoId+"'";
		getSession().createQuery(hql).executeUpdate();
	}
	
	public List<MaterialPatsDetail> findByIdAndSign(String fstrMainAutoId) {
		log.debug("finding MaterialPatsDetail by property");
		try {
			String queryString = "from MaterialPatsDetail where mainAutoId= '"+fstrMainAutoId+"'";
			queryString+=" order by barCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public void delByAutoId(String[] fstrAutoIds) {
		String hql = "delete from MaterialPatsDetail where autoId in (:autoIds)";
		getSession().createQuery(hql).setParameterList("autoIds", fstrAutoIds).executeUpdate();
	}
}