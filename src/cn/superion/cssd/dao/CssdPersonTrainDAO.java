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
import cn.superion.cssd.entity.CssdPersonTrain;
import cn.superion.cssd.entity.CssdPersonTrainFiles;
import cn.superion.util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * CssdAntisepsisDict entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.superion.cssd.entity.CssdAntisepsisDict
 * @author MyEclipse Persistence Tools
 */

public class CssdPersonTrainDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CssdPersonTrainDAO.class);

	public void save(CssdPersonTrain train) {
		log.debug("saving CssdPersonTrain instance");
		try {
			getSession().save(train);
			log.debug("save successful");
		} catch (Exception re) {
			log.error("save failed", re);
			throw new RuntimeException(re);
		}
	}


	private void saveFileToDisk(CssdPersonTrain train, CssdPersonTrainFiles file)
			throws Exception {
		if(file.getTempfileName()==null){
			return; 
		}
		String filePath =CommCssdServiceImpl.createFilePath(train.getUnitsCode(), train
				.getPersonId(),"train");
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}				
		String lstrDesFile=filePath + "//"		+ file.getSysFile();;
		String lstrTempFile=ReadPropertiesFile.getValue("UPLOAD_FILE_TEMP_BASE_DIR") +file.getTempfileName();
		FileUtil.copyFile(new File(lstrTempFile),new File(lstrDesFile));
	}

	public void saveFileRec(CssdPersonTrainFiles transientInstance) {
		log.debug("saving CssdPersonTrainFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void deleteFilesByTrainAutoId(String trainId) {
		try {

			String queryString = "delete CssdPersonTrainFiles  where mainAutoId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, trainId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete CssdPersonTrain  failed", re);
			throw re;
		}
	}

	public void delete(CssdPersonTrain persistentInstance) {
		log.debug("deleting CssdPersonTrain instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CssdPersonTrain findById(java.lang.String id) {
		log.debug("getting CssdPersonTrain instance with id: " + id);
		try {
			CssdPersonTrain instance = (CssdPersonTrain) getSession().get(
					"cn.superion.cssd.entity.CssdPersonTrain", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(CssdPersonTrain instance) {
		log.debug("finding CssdPersonTrain instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.superion.cssd.entity.CssdPersonTrain").add(
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
		log.debug("finding CssdPersonTrain instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CssdPersonTrain as model where model."
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
		log.debug("finding all CssdPersonTrain instances");
		try {
			String queryString = "from CssdPersonTrain";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CssdPersonTrain merge(CssdPersonTrain detachedInstance) {
		log.debug("merging CssdPersonTrain instance");
		try {
			CssdPersonTrain result = (CssdPersonTrain) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CssdPersonTrain instance) {
		log.debug("attaching dirty CssdPersonTrain instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CssdPersonTrain instance) {
		log.debug("attaching clean CssdPersonTrain instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CssdPersonTrain> findByUnitsCodePersonId(String unitscode,
			String personId) {
		try {
			String queryString = "from CssdPersonTrain where unitsCode=? and personId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitscode);
			queryObject.setParameter(1, personId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by unitsCode  personId failed", re);
			throw re;
		}
	}

	public void deleteByPersonId(String unitscode, String personId) {
		try {
			String queryString = "delete CssdPersonTrain  where unitsCode=? and personId=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitscode);
			queryObject.setParameter(1, personId);
			queryObject.executeUpdate();
			// 删除相关文件
		} catch (RuntimeException re) {
			log.error("delete CssdPersonTrain  failed", re);
			throw re;
		}
	}

	public void saveOrUpdateTrainList(String unitscode, String personId,
			List<CssdPersonTrain> flstTrains) {
		deleteByNotInAutoIds(unitscode,personId,getTrainAutoIdString(flstTrains));
		for (CssdPersonTrain train : flstTrains) {
			train.setPersonId(personId);
			train.setUnitsCode(unitscode);
			saveTrainAndFiles(train);
		}
	}
	private void saveTrainAndFiles(CssdPersonTrain train) {
		log.debug("saving CssdPersonTrain instance");
		try {
			getSession().saveOrUpdate(train);
			if ("1".equals(train.getIsLoaded())) {
				deleteFilesByTrainAutoId(train.getAutoId());
				int i = 1;
				for (CssdPersonTrainFiles file : train.getFiles()) {
					if(file.getFileName()==null){
						continue;
					}
					String fileSysName =file.getSysFile();
					if(fileSysName==null){
						fileSysName=CommCssdServiceImpl.createFileName(file.getFileName());
					}
					file.setSysFile(fileSysName);
					file.setSerialNo(i + "");
					file.setMainAutoId(train.getAutoId());
					i++;
					saveFileRec(file);
					saveFileToDisk(train, file);
				}
			}
			log.debug("save successful");
		} catch (Exception re) {
			log.error("save failed", re);
			throw new RuntimeException(re);
		}
	}


	private void deleteByNotInAutoIds(String unitscode, String personId,String trainAutoIdString) {
		try {
			String queryString = "delete CssdPersonTrain  where  unitsCode=? and personId=? and  autoId not in ("+trainAutoIdString+")";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, unitscode);
			queryObject.setParameter(1, personId);			
			queryObject.executeUpdate();
			// 删除相关文件
		} catch (RuntimeException re) {
			log.error("delete CssdPersonTrain  failed", re);
			throw re;
		}
	}


	private String getTrainAutoIdString(List<CssdPersonTrain> flstTrains){
		String lstrAutoIdStrings="";
		for(CssdPersonTrain train :flstTrains){
			if(train.getAutoId()!=null){
			  lstrAutoIdStrings+="'"+train.getAutoId()+"',";
			}
		}
		lstrAutoIdStrings+="'-1'";
		return lstrAutoIdStrings;
	}
}