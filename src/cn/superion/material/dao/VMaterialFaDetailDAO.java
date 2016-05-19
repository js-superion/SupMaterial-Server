package cn.superion.material.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.material.entity.VMaterialFaDetail;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * VMaterialFaDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.material.entity.VMaterialFaDetail
 * @author MyEclipse Persistence Tools
 */

public class VMaterialFaDetailDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VMaterialFaDetailDAO.class);

	@SuppressWarnings("unchecked")
	public List<VMaterialFaDetail> findByExample(VMaterialFaDetail instance) {
		log.debug("finding VMaterialFaDetail instance by example");
		try {
			List<VMaterialFaDetail> results = getSession().createCriteria(
					"cn.superion.material.entity.VMaterialFaDetail").add(
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
	public List<VMaterialFaDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding VMaterialFaDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VMaterialFaDetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<VMaterialFaDetail> findBySettleAutoId(String settleAutoId) {
		return findByProperty("mainAutoId",settleAutoId);
	}
}