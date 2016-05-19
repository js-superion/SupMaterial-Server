package cn.superion.materialDept.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.InpPatsBillDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * InpPatsBillDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.materialDept.entity.InpPatsBillDetail
 * @author MyEclipse Persistence Tools
 */

public class InpPatsBillDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InpPatsBillDetailDAO.class);

	public void save(InpPatsBillDetail transientInstance) {
		log.debug("saving InpPatsBillDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InpPatsBillDetail persistentInstance) {
		log.debug("deleting InpPatsBillDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InpPatsBillDetail findById(
			cn.superion.materialDept.entity.InpPatsBillDetailId id) {
		log.debug("getting InpPatsBillDetail instance with id: " + id);
		try {
			InpPatsBillDetail instance = (InpPatsBillDetail) getSession().get(
					"cn.superion.materialDept.entity.InpPatsBillDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InpPatsBillDetail> findByExample(InpPatsBillDetail instance) {
		log.debug("finding InpPatsBillDetail instance by example");
		try {
			List<InpPatsBillDetail> results = getSession().createCriteria(
					"cn.superion.materialDept.entity.InpPatsBillDetail").add(
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
	public List<InpPatsBillDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding InpPatsBillDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InpPatsBillDetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public InpPatsBillDetail merge(InpPatsBillDetail detachedInstance) {
		log.debug("merging InpPatsBillDetail instance");
		try {
			InpPatsBillDetail result = (InpPatsBillDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InpPatsBillDetail instance) {
		log.debug("attaching dirty InpPatsBillDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InpPatsBillDetail instance) {
		log.debug("attaching clean InpPatsBillDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void lockPatBill(String unitsCode, String patientId) {
		String hql = "from InpPatsBillDetail b where id.unitsCode=:unitsCode and id.inpNo=:patientId";
		getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("patientId", patientId).setLockMode("b", LockMode.UPGRADE);
	}

	/**
	 * 获取病人当前就诊的最大费用项目序号
	 * @param unitsCode
	 * @param patientId
	 * @return
	 */
	public int getCurItemNo(String unitsCode, String patientId) {
		String hql = "select max(id.itemNo) from InpPatsBillDetail b where id.unitsCode=:unitsCode and id.inpNo=:patientId ";
		Object result = getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("patientId", patientId).uniqueResult();
		return result == null ? 0 : Integer.parseInt(result.toString());
	}
	
	public void updateRefundSign(String unitsCode, String patientId,Integer itemNo) {
		String hql = "update Inp_Pats_Bill_Detail b set b.refund_sign='1' where b.units_Code=:unitsCode and b.inp_No=:patientId and b.item_No=:itemNo";
		getSession().createSQLQuery(hql).setString("unitsCode", unitsCode).setString("patientId", patientId).setInteger("itemNo", itemNo).executeUpdate();
	}
	
	public Map<Object, String> findItemById(String unitsCode, String materialId) {
		String hql = "select item_class as itemClass,item_id as itemId,item_spec as itemSpec,item_units as units,class_On_Inp_Rcpt as classOnInpRcpt,class_On_Reckoning as classOnReckoning,class_On_Account as classOnAccount from clinic_item_dict t where t.item_id = ?";
		Query query=getSession().createSQLQuery(hql).addScalar("itemClass").addScalar("itemId").addScalar("itemSpec").addScalar("units").addScalar("classOnInpRcpt").addScalar("classOnReckoning").addScalar("classOnAccount").setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<Object, String>) query.setString(0, materialId).uniqueResult();
	}
	

	public Map<Object, String> findItemByCode(String unitsCode, String materialCode) {
		String hql = "select item_class as itemClass,item_id as itemId,item_spec as itemSpec,item_units as units,class_On_Inp_Rcpt as classOnInpRcpt,class_On_Reckoning as classOnReckoning,class_On_Account as classOnAccount from clinic_item_dict t where t.units_code = ? and t.item_code = ?";
		Query query=getSession().createSQLQuery(hql).addScalar("itemClass").addScalar("itemId").addScalar("itemSpec").addScalar("units").addScalar("classOnInpRcpt").addScalar("classOnReckoning").addScalar("classOnAccount").setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<Object, String>) query.setString(0, unitsCode).setString(1, materialCode).uniqueResult();
	}
}