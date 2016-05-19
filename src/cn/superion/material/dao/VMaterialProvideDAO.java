package cn.superion.material.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.MaterialProvideMaster;
import cn.superion.material.entity.VMaterialProvide;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialProvide entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.VMaterialProvide
 * @author MyEclipse Persistence Tools
 */

public class VMaterialProvideDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialProvideDAO.class);

	@SuppressWarnings("unchecked")
	public List<VMaterialProvide> findByExample(VMaterialProvide instance) {
		log.debug("finding VMaterialProvide instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.material.entity.VMaterialProvide").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<VMaterialProvide> findByCondition(String unitsCode,
			Map<String, Object> conditions) {
		String storageCode = (String)conditions.get("storageCode");
		String billNo = (String)conditions.get("billNo");
		String materialName = (String)conditions.get("materialName");
		Date beginBillDate = (Date)conditions.get("beginBillDate");
		Date endBillDate = (Date)conditions.get("endBillDate");
		String deptCode = (String)conditions.get("deptCode");
		StringBuilder shql = new StringBuilder("from VMaterialProvide where planAmount>0 ");
		if(unitsCode != null && !"".equals(unitsCode)){
			shql.append(" and unitsCode='"+unitsCode+"'");
		}
		if(storageCode != null && !"".equals(storageCode)){
			shql.append(" and storageCode=:storageCode");
		}
		
		if(materialName != null && !"".equals(materialName)){
			shql.append(" and materialName=:materialName");
		}
		
		if(billNo != null && !"".equals(billNo)){
			shql.append(" and billNo=:billNo");
		}
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(deptCode != null && !"".equals(deptCode)){
			shql.append(" and deptCode=:deptCode");
		}
//		shql.append(" order by deptCode,billDate desc");
		List<VMaterialProvide> list = getSession().createQuery(shql.toString()).setProperties(conditions).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<VMaterialProvide> findByProperty(String propertyName, Object value) {
		log.debug("finding VMaterialProvide instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialProvide as model where model."
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
	public List<VMaterialProvide> findAll() {
		log.debug("finding all VMaterialProvide instances");
		try {
			String queryString = "from VMaterialProvide";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}