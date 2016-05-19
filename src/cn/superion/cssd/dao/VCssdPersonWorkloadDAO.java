package cn.superion.cssd.dao;

import cn.superion.cssd.entity.VCssdPersonWorkload;
import cn.superion.util.BaseHibernateDAO;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * VCssdPersonWorkload entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.VCssdPersonWorkload
 * @author MyEclipse Persistence Tools
 */

public class VCssdPersonWorkloadDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VCssdPersonWorkloadDAO.class);

	
	@SuppressWarnings("unchecked")
	public List<VCssdPersonWorkload> findByExample(VCssdPersonWorkload instance) {
		log.debug("finding VCssdPersonWorkload instance by example");
		try {
			List<VCssdPersonWorkload> results = getSession().createCriteria(
					"cn.superion.cssd.entity.VCssdPersonWorkload").add(
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
	public List<VCssdPersonWorkload> findByProperty(String propertyName, Object value) {
		log.debug("finding VCssdPersonWorkload instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VCssdPersonWorkload as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param start
	 * @param limit
	 * @param unitsCode
	 * @param conditions 包含
	 *            <ul>
	 *            <li>beginBillDate 起始单据日期</li>
	 *            <li>endBillDate 结束单据日期</li>
	 *            <li>personId 工作人员</li>
	 *            <li>packageClass 物品包类别</li>
	 *            <li>packageId 物品包编码</li>
	 *            <li>beginPackageNo 起始物品包编号</li>
	 *            <li>endPackageNo 结束物品包编号</li>
	 *            </ul>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findWorkloadByCondition(int start, int limit,
			String unitsCode, Map<String, Object> conditions) {
		Object[] objs = new Object[2];
		Date beginBillDate = (Date) conditions.get("beginBillDate");
		Date endBillDate = (Date) conditions.get("endBillDate");
		String personId = (String)conditions.get("personId");
		String packageClass = (String)conditions.get("packageClass");
		String packageId = (String)conditions.get("packageId");
		String beginPackageNo = (String)conditions.get("beginPackageNo");
		String endPackageNo = (String)conditions.get("endPackageNo");
		StringBuilder shql = new StringBuilder("from VCssdPersonWorkload where unitsCode='").append(unitsCode).append("'");
		if(beginBillDate != null){
			shql.append(" and billDate>=:beginBillDate");
		}
		if(endBillDate != null){
			shql.append(" and billDate<=:endBillDate");
		}
		if(personId != null && !"".equals(personId)){
			shql.append(" and personId=:personId");
		}
		if(packageClass != null && !"".equals(packageClass)){
			shql.append(" and packageClass=:packageClass");
		}
		if(packageId != null && !"".equals(packageId)){
			shql.append(" and packageId=:packageId");
		}
		if(beginPackageNo != null && !"".equals(beginPackageNo)){
			shql.append(" and packageNo>=:beginPackageNo");
		}
		if(endPackageNo != null && !"".equals(endPackageNo)){
			shql.append(" and packageNo<=:endPackageNo");
		}
		shql.append(" order by billDate");
		Query countQuery = getSession().createQuery("select count(*) "+shql.toString());
		Query query = getSession().createQuery(shql.toString());
		int count = Integer.parseInt(countQuery.setProperties(conditions).uniqueResult().toString());
		List<VCssdPersonWorkload> list = query.setProperties(conditions).setFirstResult(start).setMaxResults(limit).list();
		objs[0] = count;
		objs[1] = list;
		return objs;
	}
}