package cn.superion.cssd.dao;

import cn.superion.cssd.entity.VCssdPackageDictDetail;
import cn.superion.util.BaseHibernateDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * VCssdPackageDictDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.VCssdPackageDictDetail
 * @author MyEclipse Persistence Tools
 */

public class VCssdPackageDictDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VCssdPackageDictDetailDAO.class);

	@SuppressWarnings("unchecked")
	public List<VCssdPackageDictDetail> findByExample(VCssdPackageDictDetail instance) {
		log.debug("finding VCssdPackageDictDetail instance by example");
		try {
			List<VCssdPackageDictDetail> results = getSession().createCriteria(
					"cn.superion.cssd.entity.VCssdPackageDictDetail").add(
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
	public List<VCssdPackageDictDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding VCssdPackageDictDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VCssdPackageDictDetail as model where model."
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
	public List<VCssdPackageDictDetail> findAll() {
		log.debug("finding all VCssdPackageDictDetail instances");
		try {
			String queryString = "from VCssdPackageDictDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 查询物品包字典明细视图
	 * @param unitsCode
	 * @param packageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VCssdPackageDictDetail> findByPackageId(String unitsCode,
			String packageId) {
		String hql = "from VCssdPackageDictDetail where unitsCode=:unitsCode and packageId=:packageId order by serialNo";
		return getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("packageId", packageId).list();
	}

	
}