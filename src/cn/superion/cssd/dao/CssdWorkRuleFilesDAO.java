package cn.superion.cssd.dao;

import java.io.File;
import java.util.List;

import org.aspectj.util.FileUtil;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.superion.base.ReadPropertiesFile;
import cn.superion.cssd.common.CommCssdServiceImpl;
import cn.superion.cssd.entity.CssdWorkRuleFiles;
import cn.superion.cssd.entity.CssdWorkRuleMaster;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdWorkRuleFiles entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdWorkRuleFiles
 * @author MyEclipse Persistence Tools
 */

public class CssdWorkRuleFilesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdWorkRuleFilesDAO.class);
	private String modual = "workRule";

	public void save(CssdWorkRuleFiles transientInstance) {
		log.debug("saving CssdWorkRuleFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CssdWorkRuleFiles persistentInstance) {
		log.debug("deleting CssdWorkRuleFiles instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdWorkRuleFiles findById(java.lang.String id) {
		log.debug("getting CssdWorkRuleFiles instance with id: " + id);
		try {
			CssdWorkRuleFiles instance = (CssdWorkRuleFiles) getSession().get(
					"cn.superion.cssd.entity.CssdWorkRuleFiles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdWorkRuleFiles instance) {
		log.debug("finding CssdWorkRuleFiles instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdWorkRuleFiles").add(
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
		log.debug("finding CssdWorkRuleFiles instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdWorkRuleFiles as model where model."
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
		log.debug("finding all CssdWorkRuleFiles instances");
		try {
			String queryString = "from CssdWorkRuleFiles";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdWorkRuleFiles merge(CssdWorkRuleFiles detachedInstance) {
		log.debug("merging CssdWorkRuleFiles instance");
		try {
			CssdWorkRuleFiles result = (CssdWorkRuleFiles) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdWorkRuleFiles instance) {
		log.debug("attaching dirty CssdWorkRuleFiles instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdWorkRuleFiles instance) {
		log.debug("attaching clean CssdWorkRuleFiles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void saveWorkRuleFiles(CssdWorkRuleMaster fworkRuleMaster,
			List<CssdWorkRuleFiles> fworkRuleFiles) {
		deleteFilesByAutoId(fworkRuleMaster.getAutoId());
		try {
			int i = 0;
			for (CssdWorkRuleFiles file : fworkRuleFiles) {
				if (file.getFileName() == null) {
					continue;
				}
				String fileSysName = file.getSysFile();
				if (fileSysName == null) {
					fileSysName = CommCssdServiceImpl.createFileName(file
							.getFileName());
				}
				file.setSysFile(fileSysName);
				file.setSerialNo(i + "");
				file.setMainAutoId(fworkRuleMaster.getAutoId());
				i++;
				saveFileRec(file);
				saveFileToDisk(fworkRuleMaster, file);
			}
		} catch (Exception re) {
			log.error("save failed", re);
			throw new RuntimeException(re);
		}
	}

	public void saveFileRec(CssdWorkRuleFiles transientInstance) {
		log.debug("saving CssdWorkRuleFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	private void saveFileToDisk(CssdWorkRuleMaster ruleMaster,
			CssdWorkRuleFiles file) throws Exception {
		if (file.getTempfileName() == null) {
			return;
		}
		String filePath = CommCssdServiceImpl.createFilePath(ruleMaster
				.getUnitsCode(), ruleMaster.getAutoId(), modual);
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String lstrDesFile = filePath + "//" + file.getSysFile();
		;
		String lstrTempFile = ReadPropertiesFile
				.getValue("UPLOAD_FILE_TEMP_BASE_DIR")
				+ file.getTempfileName();
		FileUtil.copyFile(new File(lstrTempFile), new File(lstrDesFile));
	}

	private void deleteFilesByAutoId(String autoId) {
		try {
			String queryString = "delete CssdWorkRuleFiles  where mainAutoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, autoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdWorkRuleFiles  failed", re);
			throw re;
		}
	}

	public void deleteByMainAutoId(String fstrAutoId) {
		try {
			String queryString = "delete from CssdWorkRuleFiles where mainAutoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, fstrAutoId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}