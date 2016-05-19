package cn.superion.materialDept.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.materialDept.entity.InpPatsInDept;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * InpPatsInDept entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.materialDept.entity.InpPatsInDept
 * @author MyEclipse Persistence Tools
 */

public class InpPatsInDeptDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InpPatsInDeptDAO.class);

	public void save(InpPatsInDept transientInstance) {
		log.debug("saving InpPatsInDept instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InpPatsInDept persistentInstance) {
		log.debug("deleting InpPatsInDept instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InpPatsInDept findById(
			cn.superion.materialDept.entity.InpPatsInDeptId id) {
		log.debug("getting InpPatsInDept instance with id: " + id);
		try {
			InpPatsInDept instance = (InpPatsInDept) getSession().get(
					"cn.superion.materialDept.entity.InpPatsInDept", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InpPatsInDept> findByExample(InpPatsInDept instance) {
		log.debug("finding InpPatsInDept instance by example");
		try {
			List<InpPatsInDept> results = getSession().createCriteria(
					"cn.superion.materialDept.entity.InpPatsInDept").add(
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
	public List<InpPatsInDept> findByProperty(String propertyName, Object value) {
		log.debug("finding InpPatsInDept instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InpPatsInDept as model where model."
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
	public List<InpPatsInDept> findAll() {
		log.debug("finding all InpPatsInDept instances");
		try {
			String queryString = "from InpPatsInDept";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InpPatsInDept merge(InpPatsInDept detachedInstance) {
		log.debug("merging InpPatsInDept instance");
		try {
			InpPatsInDept result = (InpPatsInDept) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InpPatsInDept instance) {
		log.debug("attaching dirty InpPatsInDept instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InpPatsInDept instance) {
		log.debug("attaching clean InpPatsInDept instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public InpPatsInDept findCurrentInDept(
			String unitsCode, String patientId) {
		String hql = "from InpPatsInDept where id.unitsCode=:unitsCode and id.inpNo=:patientId and currentStatus='0'";
		Iterator it = getSession().createQuery(hql).setString("unitsCode", unitsCode).setString("patientId", patientId).iterate();
		if(it.hasNext()){
			return (InpPatsInDept) it.next();
		}
		return null;
	}
}