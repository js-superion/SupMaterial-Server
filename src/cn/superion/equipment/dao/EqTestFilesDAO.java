package cn.superion.equipment.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.equipment.entity.EqEquipmentFiles;
import cn.superion.equipment.entity.EqTestFiles;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * EqTestFiles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.equipment.entity.EqTestFiles
 * @author MyEclipse Persistence Tools
 */

public class EqTestFilesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EqTestFilesDAO.class);
	// property constants
	public static final String SERIAL_NO = "serialNo";
	public static final String FILE_NO = "fileNo";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_REMARK = "fileRemark";
	public static final String FILE_PATH = "filePath";

	public void save(EqTestFiles transientInstance) {
		log.debug("saving EqTestFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EqTestFiles persistentInstance) {
		log.debug("deleting EqTestFiles instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<EqTestFiles> findByAutoId(String autoId) {
		return findByProperty("autoId",autoId);
	}

	public void delByAutoId(String autoId) {
		String hql = "delete from EqTestFiles where autoId=:autoId";
		getSession().createQuery(hql).setString("autoId", autoId).executeUpdate();
	}
	public EqTestFiles findById(java.lang.String id) {
		log.debug("getting EqTestFiles instance with id: " + id);
		try {
			EqTestFiles instance = (EqTestFiles) getSession().get(
					"cn.superion.equipment.entity.EqTestFiles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EqTestFiles instance) {
		log.debug("finding EqTestFiles instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.equipment.entity.EqTestFiles").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EqTestFiles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EqTestFiles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySerialNo(Object serialNo) {
		return findByProperty(SERIAL_NO, serialNo);
	}

	public List findByFileNo(Object fileNo) {
		return findByProperty(FILE_NO, fileNo);
	}

	public List findByFileName(Object fileName) {
		return findByProperty(FILE_NAME, fileName);
	}

	public List findByFileRemark(Object fileRemark) {
		return findByProperty(FILE_REMARK, fileRemark);
	}

	public List findByFilePath(Object filePath) {
		return findByProperty(FILE_PATH, filePath);
	}

	public List findAll() {
		log.debug("finding all EqTestFiles instances");
		try {
			String queryString = "from EqTestFiles";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EqTestFiles merge(EqTestFiles detachedInstance) {
		log.debug("merging EqTestFiles instance");
		try {
			EqTestFiles result = (EqTestFiles) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EqTestFiles instance) {
		log.debug("attaching dirty EqTestFiles instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EqTestFiles instance) {
		log.debug("attaching clean EqTestFiles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}