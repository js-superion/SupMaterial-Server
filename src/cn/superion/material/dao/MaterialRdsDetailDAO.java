package cn.superion.material.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.materialDept.entity.VMaterialRdsDept;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialRdsDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.MaterialRdsDetail
 * @author MyEclipse Persistence Tools
 */

public class MaterialRdsDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaterialRdsDetailDAO.class);
	// property constants
	public static final String MAIN_AUTO_ID = "mainAutoId";
	public static final String SERIAL_NO = "serialNo";
	public static final String MATERIAL_CLASS = "materialClass";
	public static final String BAR_CODE = "barCode";
	public static final String MATERIAL_ID = "materialId";
	public static final String MATERIAL_CODE = "materialCode";
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_SPEC = "materialSpec";
	public static final String MATERIAL_UNITS = "materialUnits";
	public static final String AMOUNT = "amount";
	public static final String TRADE_PRICE = "tradePrice";
	public static final String TRADE_MONEY = "tradeMoney";
	public static final String RETAIL_PRICE = "retailPrice";
	public static final String RETAIL_MONEY = "retailMoney";
	public static final String FACTORY_CODE = "factoryCode";
	public static final String BATCH = "batch";
	public static final String OUT_AMOUNT = "outAmount";
	public static final String OUT_SIGN = "outSign";
	public static final String CURRENT_STOCK_AMOUNT = "currentStockAmount";
	public static final String INVOICE_AMOUNT = "invoiceAmount";
	public static final String INVOICE_SIGN = "invoiceSign";
	public static final String HIGH_VALUE_SIGN = "highValueSign";
	public static final String AGENT_SIGN = "agentSign";
	public static final String SOURCE_INPUT_AUTO_ID = "sourceInputAutoId";
	public static final String SOURCE_AUTO_ID = "sourceAutoId";
	public static final String DETAIL_REMARK = "detailRemark";

	public List<MaterialRdsDetail> findByMainAutoId(String fstrMainAutoId) {
		return this.findByProperty("mainAutoId", fstrMainAutoId);
	}

	public void deleteByMainAutoId(String fstrMainAutoId) {
		getSession().createQuery(
				"delete from MaterialRdsDetail where mainAutoId=:mainAutoId")
				.setString("mainAutoId", fstrMainAutoId).executeUpdate();
	}

	public void save(MaterialRdsDetail transientInstance) {
		log.debug("saving MaterialRdsDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialRdsDetail persistentInstance) {
		log.debug("deleting MaterialRdsDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialRdsDetail findById(java.lang.String id) {
		log.debug("getting MaterialRdsDetail instance with id: " + id);
		try {
			MaterialRdsDetail instance = (MaterialRdsDetail) getSession().get(
					"cn.superion.material.entity.MaterialRdsDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialRdsDetail> findByExample(MaterialRdsDetail instance) {
		log.debug("finding MaterialRdsDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.MaterialRdsDetail").add(
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
	public List<MaterialRdsDetail> findByProperty(String propertyName,
			Object value) {
		log.debug("finding MaterialRdsDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialRdsDetail as model where model."
					+ propertyName + "= ? order by model.serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MaterialRdsDetail> findAll() {
		log.debug("finding all MaterialRdsDetail instances");
		try {
			String queryString = "from MaterialRdsDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialRdsDetail merge(MaterialRdsDetail detachedInstance) {
		log.debug("merging MaterialRdsDetail instance");
		try {
			MaterialRdsDetail result = (MaterialRdsDetail) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialRdsDetail instance) {
		log.debug("attaching dirty MaterialRdsDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialRdsDetail instance) {
		log.debug("attaching clean MaterialRdsDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 根据入出库单主记录ID和入出库单明细记录serialNo查找入出库单明细记录
	 * 
	 * @param mainAutoId
	 * @param serialNo
	 * @return
	 */
	public MaterialRdsDetail findByMainAutoIdAndSerialNo(String mainAutoId,
			Short serialNo) {
		String hql = "from MaterialRdsDetail where mainAutoId=:mainAutoId and serialNo=:serialNo";
		return (MaterialRdsDetail) getSession().createQuery(hql).setString(
				"mainAutoId", mainAutoId).setShort("serialNo", serialNo)
				.uniqueResult();
	}
	/**
	 * 根据mainAutoId、currentStatus查询对应的记录
	 * 
	 * @param mainAutoId
	 * @param currentStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MaterialRdsDetail> findByMainAutoIdAndCurrentStatus(String mainAutoId,
			String currentStatus) {
		String hql = "from MaterialRdsDetail where mainAutoId=:mainAutoId and currentStatus=:currentStatus";
		return (List<MaterialRdsDetail>) getSession().createQuery(hql).setString(
				"mainAutoId", mainAutoId).setString("currentStatus", currentStatus).list();
	}
	public List<MaterialRdsDetail> findByAcctBillNo(String acctBillNo) {
		try {
			String queryString = "from MaterialRdsDetail as model where model.acctBillNo = ? "
					+ " order by model.rdBillNo , model.serialNo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, acctBillNo);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List<MaterialRdsDetail> findGiveByAcctBillNo(String acctBillNo,String fstrGive) {
		try {
			String queryString = "from MaterialRdsDetail as model where model.acctBillNo = '"+acctBillNo+"' ";
			if(fstrGive!=null && !fstrGive.equals("")){
				queryString+=" and nvl(model.isGive,'0')='"+fstrGive+"'";
			}
			queryString+=" order by model.rdBillNo , model.serialNo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
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
	public List<MaterialRdsDetail> findByCondition(String conditions) {
		log.debug("finding MaterialRdsDetail by condition");
		try {
			String queryString = "from MaterialRdsDetail " + conditions;
			Query query = getSession().createQuery(queryString);
			return query.list();
		} catch (RuntimeException re) {
			log.error("finding MaterialRdsDetail by condition failed", re);
			throw re;
		}
	}
	
	public List<Object[]> findByCondition1(String conditions) {
		log.debug("finding MaterialRdsDetail by condition");
		try {
			String queryString = "select billNo from MaterialRdsMaster " + conditions;
			Query query = getSession().createQuery(queryString);
			return query.list();
		} catch (RuntimeException re) {
			log.error("finding MaterialRdsMaster by condition failed", re);
			throw re;
		}
	}
	/*
	 * 功能说明：根据条件更新当前表中相关的值
	 * 
	 * @param causes 子句
	 * 
	 * @author 周作建
	 * 
	 * @date 2011.06.12
	 */
	public void updateByCause(String causes) {
		log.debug("update MaterialRdsDetail by condition");
		try {
			String queryString = "update MaterialRdsDetail " + causes;

			Query query = getSession().createQuery(queryString);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("update MaterialRdsDetail by condition failed", re);
			throw re;
		}
	}
	
	/**
	 * *判断业务表是否发生业务数据（不包含期初录入的数据，即：operationType<>'105'）
	 * @param unitsCode
	 * @param storageCode
	 * @return
	 */
	public boolean hasOtherRD(String unitsCode, String storageCode) {
		String hql = "select count(*) from MaterialRdsDetail d,MaterialRdsMaster m where d.mainAutoId=m.autoId and m.unitsCode=:unitsCode and m.storageCode=:storageCode and m.operationType<>'105'";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).uniqueResult().toString());
		return cnt>0;
	}
	public boolean hasOtherRD(String unitsCode, String storageCode,
			String materialId) {
		String hql = "select count(*) from MaterialRdsDetail d,MaterialRdsMaster m where d.mainAutoId=m.autoId and m.unitsCode=:unitsCode and m.storageCode=:storageCode and d.materialId=:materialId and m.operationType<>'105'";
		int cnt = Integer.parseInt(getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("storageCode", storageCode).setString("materialId", materialId).uniqueResult().toString());
		return cnt>0;
	}

	/**
	 * 生成yyyyMMdd+位序列，序列不足最大位数，以零补
	 * @param sequenceName
	 * @param maxLength 最大位数
	 * @return
	 */
	public String nextTimeValueFromDB(String sequenceName,int maxLength){
		String sql = "select concat(to_char(sysdate,'yyyymmdd'),lpad("+sequenceName+".nextval,"+maxLength+",'0')) from dual";
		return getSession().createSQLQuery(sql).uniqueResult().toString();
	}

	/**
	 * 根据条形码查询一般高值耗材信息
	 * @param fstrBarCode
	 * @return
	 * 
	 * @author 芮玉红
	 * @date 2012.9.27
	 */
	public MaterialRdsDetail findByBarCode(String fstrBarCode,boolean isAmount) {
		String hql = "from MaterialRdsDetail d where d.barCode in (select barCode from MaterialCurrentStock where barCode='"+fstrBarCode+"'";
		if(isAmount){
			hql+=" and amount>0";
		}
		hql+=" )and d.mainAutoId in (select autoId from MaterialRdsMaster where rdFlag='1' and currentStatus!='0')";
		return (MaterialRdsDetail) getSession().createQuery(hql).uniqueResult();
	}
	
	public MaterialRdsDetail findStockByBarCode(String fstrBarCode) {
		String hql = "from MaterialRdsDetail d where d.barCode in (select barCode from MaterialCurrentStock where barCode='"+fstrBarCode+"')"
		           +" and d.mainAutoId in (select autoId from MaterialRdsMaster where rdFlag='1' and currentStatus!='0')";
		return (MaterialRdsDetail) getSession().createQuery(hql).uniqueResult();
	}
}