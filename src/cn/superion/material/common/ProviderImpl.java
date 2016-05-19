package cn.superion.material.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.center.provider.dao.CdProviderDAO;
import cn.superion.center.provider.dao.CdProviderFilesDAO;
import cn.superion.center.provider.dao.CdProviderMaterialDAO;
import cn.superion.center.provider.entity.CdProvider;
import cn.superion.center.provider.entity.CdProviderFiles;
import cn.superion.center.provider.entity.CdProviderMaterial;
import cn.superion.system.dao.SysUnitInforDAO;
import cn.superion.system.entity.SysUnitInfor;
import cn.superion.system.entity.SysUser;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;

/**
 * @author yuan cheng
 * @author cao guokui
 * @author zhou zuojian
 * 
 */
public class ProviderImpl implements IProvider {
	// 存放供应商附件的目录
	private static String PROVIDER_FILE_ROOT_DIR;
	// 存放供应商授权物资的附件目录
	private static String PROVIDER_MATERIAL_ROOT_DIR;
	static {
		PROVIDER_FILE_ROOT_DIR = ReadPropertiesFile
				.getValue("PROVIDER_FILE_ROOT_DIR");
		File file = new File(PROVIDER_FILE_ROOT_DIR);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = null;
		PROVIDER_MATERIAL_ROOT_DIR = ReadPropertiesFile
				.getValue("PROVIDER_MATERIAL_ROOT_DIR");
		file = new File(PROVIDER_MATERIAL_ROOT_DIR);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = null;
	}

	private static final Log log = LogFactory.getLog(ProviderImpl.class);
	private CdProviderDAO cdProviderDAO;
	private CdProviderFilesDAO cdProviderFilesDAO;
	private CdProviderMaterialDAO cdProviderMaterialDAO;
	private CdSysParamDAO cdSysParamDAO;
	private SysUnitInforDAO sysUnitInforDAO;
	
	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public SysUnitInforDAO getSysUnitInforDAO() {
		return sysUnitInforDAO;
	}

	public void setSysUnitInforDAO(SysUnitInforDAO sysUnitInforDAO) {
		this.sysUnitInforDAO = sysUnitInforDAO;
	}

	public CdProviderDAO getCdProviderDAO() {
		return cdProviderDAO;
	}

	public void setCdProviderDAO(CdProviderDAO cdProviderDAO) {
		this.cdProviderDAO = cdProviderDAO;
	}

	public CdProviderFilesDAO getCdProviderFilesDAO() {
		return cdProviderFilesDAO;
	}

	public void setCdProviderFilesDAO(CdProviderFilesDAO cdProviderFilesDAO) {
		this.cdProviderFilesDAO = cdProviderFilesDAO;
	}

	public CdProviderMaterialDAO getCdProviderMaterialDAO() {
		return cdProviderMaterialDAO;
	}

	public void setCdProviderMaterialDAO(
			CdProviderMaterialDAO cdProviderMaterialDAO) {
		this.cdProviderMaterialDAO = cdProviderMaterialDAO;
	}

	@Override
	public ReObject findProviderListByCondtion(ParameterObject fparameter) {
		String unitsCode = SessionUtil.getUnitsCode();
		String providerClass = (String) fparameter.getConditions().get(
				"providerClass");
		String providerName = (String) fparameter.getConditions().get(
				"providerName");
		int start = fparameter.getStart();
		int itemsPerPage = fparameter.getItemsPerPage();
		ReObject obj = new ReObject();
		obj.setAction("查询供应商档案列表");
		boolean flag = true;
		try {
			List<CdProvider> list = cdProviderDAO.findByCondtion(unitsCode,
					providerClass, providerName, start, itemsPerPage);
			int count = cdProviderDAO.countByCondtion(unitsCode, providerClass,
					providerName);
			obj
					.setPageCount(count / itemsPerPage + count % itemsPerPage > 0 ? 1
							: 0);
			obj.setData(list);

		} catch (RuntimeException re) {
			log.error("查询供应商档案列表", re);
			obj.setError("查询供应商档案列表失败");
			flag = false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;
	}

	@Override
	public ReObject findProviderById(String fstrProviderId) {
		ReObject obj = new ReObject();
		obj.setAction("查看供应商档案信息");
		boolean flag = true;
		try {
			List<CdProvider> list = cdProviderDAO.findByProperty("providerId",
					fstrProviderId);
			obj.setData(list);
		} catch (RuntimeException re) {
			log.error("查看供应商档案信息失败", re);
			obj.setError("查看供应商档案信息失败");
			flag = false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;
	}

	@Override
	public ReObject findProviderByProviderCode(String fstrProviderCode) {
		ReObject obj = new ReObject();
		obj.setAction("查看供应商档案信息");
		boolean flag = true;
		try {
			String unitsCode = SessionUtil.getUnitsCode();
			List<CdProvider> list = cdProviderDAO.findByCondition(unitsCode,
					fstrProviderCode);
			obj.setData(list);
		} catch (RuntimeException re) {
			log.error("查看供应商档案信息失败", re);
			obj.setError("查看供应商档案信息失败" + re);
			flag = false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;
	}

	@Override
	public ReObject findProviderByProvideClass(String fstrProviderClass) {
		return findProviderByProvideClass(fstrProviderClass, null);
	}

	@Override
	public ReObject findProviderByProvideClass(String fstrProviderClass,
			String fstrUnitsCode) {
		ReObject obj = new ReObject();
		obj.setAction("查看供应商档案信息列表");
		boolean flag = true;
		try {
			String unitsCode = fstrUnitsCode;
			if (fstrUnitsCode == null) {
				unitsCode = SessionUtil.getUnitsCode();
			}
			List<CdProvider> list = cdProviderDAO.findByProviderClass(
					unitsCode, fstrProviderClass);
			obj.setData(list);
		} catch (RuntimeException re) {
			log.error("查看供应商档案信息列表失败", re);
			obj.setError("查看供应商档案信息列表失败" + re);
			flag = false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;
	}

	@Override
	public ReObject saveProvider(CdProvider cdProvider, String fstrType) {
		ReObject obj = new ReObject();
		obj.setAction("保存供应商档案信息");
		boolean flag = true;
		try {
			String unitsCode = SessionUtil.getUnitsCode();
			List<CdProvider> proList = cdProviderDAO.findByCondition(unitsCode,
					cdProvider.getProviderCode());
			if (null != fstrType && fstrType.equals("add")) {
				if (proList == null || proList.size() == 0) {
					cdProvider.setUnitsCode(unitsCode);

					cdProviderDAO.save(cdProvider);
				} else {
					flag = false;
					obj.setError("此供应商编码已存在，请重新输入！");
				}
			} else {
				obj.setAction("修改供应商档案信息");
				cdProviderDAO.merge(cdProvider);
			}
		} catch (RuntimeException re) {
			log.error("失败", re);
			obj.setError("失败");
			flag = false;
			throw re;
		}
		obj.setSuccess(flag);
		List<CdProvider> list = new ArrayList<CdProvider>();
		list.add(cdProvider);
		obj.setData(list);
		return obj;
	}

	@Override
	public ReObject deleteProviderById(String fstrProviderId) {
		ReObject obj = new ReObject();
		obj.setAction("删除供应商档案信息");
		boolean flag = true;
		try {
			// 删除供应商附件
			delProviderFiles(fstrProviderId);
			cdProviderFilesDAO.delByProviderId(fstrProviderId);
			// 删除供应商物资授权
			delProviderMaterialFiles(fstrProviderId);
			cdProviderMaterialDAO.delByProviderId(fstrProviderId);
			cdProviderDAO.delById(fstrProviderId);
		} catch (RuntimeException re) {
			log.error("删除供应商信息档案信息失败", re);
			obj.setError("删除供应商档案信息失败" + re);
			flag = false;
			throw re;
		}
		obj.setSuccess(flag);
		return obj;
	}

	/**
	 * 删除供应商附件物理文件
	 * 
	 * @param fstrProviderId
	 */
	private void delProviderFiles(String fstrProviderId) {
		List<CdProviderFiles> pfiles = cdProviderFilesDAO
				.findByProviderId(fstrProviderId);
		for (CdProviderFiles pfile : pfiles) {
			String fileName = PROVIDER_FILE_ROOT_DIR + File.separator
					+ pfile.getFilePath();
			delFile(fileName);
		}
	}

	/**
	 * 删除供应商授权物资物理文件
	 * 
	 * @param fstrProviderId
	 */
	private void delProviderMaterialFiles(String fstrProviderId) {
		List<CdProviderMaterial> mfiles = cdProviderMaterialDAO
				.findByProviderId(fstrProviderId);
		for (CdProviderMaterial mfile : mfiles) {
			delProviderMaterialFiles(mfile);
		}
	}

	private void delProviderMaterialFiles(CdProviderMaterial mfile) {
		String filePath = mfile.getAccreditFilePath1();
		if (filePath != null && !"".equals(filePath)) {
			delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator + filePath);
		}
		filePath = mfile.getAccreditFilePath2();
		if (filePath != null && !"".equals(filePath)) {
			delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator + filePath);
		}
		filePath = mfile.getAccreditFilePath3();
		if (filePath != null && !"".equals(filePath)) {
			delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator + filePath);
		}
	}

	private void delFile(String fileName) {
		File file = new File(fileName);
		file.delete();
	}

	@Override
	public ReObject findProviderFilesById(String fstrProviderId) {
		ReObject reObject = new ReObject();
		reObject.setAction("供应商附件信息列表");
		List<CdProviderFiles> lstCdProviderFiles = cdProviderFilesDAO
				.findByProviderId(fstrProviderId);
		reObject.setData(lstCdProviderFiles);
		return reObject;
	}

	@Override
	public ReObject findProviderMaterialById(String fstrProviderId) {
		ReObject reObject = new ReObject();
		reObject.setAction("供应商产品信息列表");
		List<CdProviderMaterial> lstCdProviderMaterials = cdProviderMaterialDAO
				.findByProviderId(SessionUtil.getUnitsCode(), fstrProviderId);
		cdProviderMaterialDAO.fillMaterialNameToList(lstCdProviderMaterials);
		reObject.setData(lstCdProviderMaterials);
		return reObject;
	}

	@Override
	public ReObject save(CdProvider fCdProvider,
			List<CdProviderFiles> flstCdProviderFiles,
			List<CdProviderMaterial> flstCdProviderMaterial) {
		ReObject reObject = new ReObject();
		reObject.setAction("保存供应商信息");
		Date curDate = new Date();
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String application=user.getAppCode();

		String providerId = fCdProvider.getProviderId();
		String providerCode = fCdProvider.getProviderCode();

		boolean isAdd=false;
		if (fCdProvider.getProviderClass() == null
				|| "".equals(fCdProvider.getProviderClass())) {
			throw new RuntimeException("供应商分类不能为空");
		}

		if (providerCode == null || "".equals(providerCode)) {
			providerCode = cdProviderDAO.getMaxMaterialCode(unitsCode);
			fCdProvider.setProviderCode(providerCode);
		} else {
			// 验证该编号是否存在
			if (!cdProviderDAO.checkProviderCodeUnique(unitsCode, providerCode,
					providerId)) {
				throw new RuntimeException("供应商编码" + providerCode + "已经存在");
			}
		}
		if (providerId == null || "".equals(providerId)) {
			// 新增
			isAdd=true;
			fCdProvider.setUnitsCode(unitsCode);
			fCdProvider.setCreateDate(curDate);
			fCdProvider.setCreatePerson(personId);
			cdProviderDAO.save(fCdProvider);
			providerId = fCdProvider.getProviderId();
			saveProviderFilesDataAndFiles(providerId, flstCdProviderFiles);
			saveProviderMaterialDataAndFiles(unitsCode, providerId,
					flstCdProviderMaterial);
		} else {
			// 修改
			isAdd=false;
			fCdProvider.setModifyDate(curDate);
			fCdProvider.setModifyPerson(personId);
			cdProviderDAO.merge(fCdProvider);
			// 清空供应商附件
			if ("1".equals(fCdProvider.getClearFileSign())) {
				delProviderFiles(providerId);
				cdProviderFilesDAO.delByProviderId(providerId);
			} else {
				if (flstCdProviderFiles != null
						&& !flstCdProviderFiles.isEmpty()) {
					updateProviderFilesDataAndFiles(providerId,
							flstCdProviderFiles);
				}
			}
			// 清空供应商授权物资
			if ("1".equals(fCdProvider.getClearMaterialSign())) {
				delProviderMaterialFiles(providerId);
				cdProviderMaterialDAO.delByProviderId(providerId);
			} else {
				if (flstCdProviderMaterial != null
						&& !flstCdProviderMaterial.isEmpty()) {
					updateProviderMaterialDataAndFiles(unitsCode, providerId,
							flstCdProviderMaterial);
				}
			}
		}
		//同步其他单位字典数据
		String paraGroup=cdSysParamDAO.findByParaCode(unitsCode, application, "0801");
		boolean isGroupUsed = paraGroup!=null && "1".equals(paraGroup) ? true : false;
		if(isGroupUsed){
			synchOtherUnitDict(isAdd,fCdProvider,flstCdProviderFiles,flstCdProviderMaterial);
		}
		
		List<Object> data = new ArrayList<Object>();
		data.add(fCdProvider);
		reObject.setData(data);
		return reObject;
	}
	
	/**
	 * 同步其他单位字典数据
	 * @param providerDict 字典模板
	 */
	private void synchOtherUnitDict(boolean isAdd,CdProvider providerDict,
			List<CdProviderFiles> flstCdProviderFiles,
			List<CdProviderMaterial> flstCdProviderMaterial){

		String unitsCode = providerDict.getUnitsCode();
		List<SysUnitInfor> units = sysUnitInforDAO.findAll();
		for(SysUnitInfor unit : units){
			String curUnitsCode=unit.getUnitsCode();
			if(!curUnitsCode.equals(unitsCode) && "1".equals(unit.getEndSign())){
				String providerId=providerDict.getProviderId();
				CdProvider newProvider = new CdProvider();
				BeanUtils.copyProperties(providerDict, newProvider);
				newProvider.setUnitsCode(curUnitsCode);
				//分管部门，南院是0开头，北院是1开头
				String chargeDept=newProvider.getChargeDept();
				if(chargeDept!=null && chargeDept.length()>1){
					chargeDept=chargeDept.substring(1,chargeDept.length());
				}
				String firstCode=curUnitsCode.substring(curUnitsCode.length()-1, curUnitsCode.length());
				chargeDept=String.valueOf(Integer.valueOf(firstCode)-1)+chargeDept;
				newProvider.setChargeDept(chargeDept);
				
				if (isAdd) {
					// 新增
					newProvider.setProviderId("");
					cdProviderDAO.save(newProvider);
					providerId = newProvider.getProviderId();
					saveProviderFilesDataAndFiles(providerId, flstCdProviderFiles);
					saveProviderMaterialDataAndFiles(unitsCode, providerId,
							flstCdProviderMaterial);
				} else {
					// 修改
					List<CdProvider> otherProviderList=cdProviderDAO.findByCondition(curUnitsCode, providerDict.getProviderCode());
					if(otherProviderList!=null && otherProviderList.size()>0){
						CdProvider otherProvider=otherProviderList.get(0);
						String oldProviderId=otherProvider.getProviderId();
						newProvider.setProviderId(oldProviderId);
						cdProviderDAO.merge(newProvider);
						// 清空供应商附件
						if ("1".equals(newProvider.getClearFileSign())) {
							delProviderFiles(oldProviderId);
							cdProviderFilesDAO.delByProviderId(oldProviderId);
						} else {
							if (flstCdProviderFiles != null
									&& !flstCdProviderFiles.isEmpty()) {
								updateProviderFilesDataAndFiles(oldProviderId,
										flstCdProviderFiles);
							}
						}
						// 清空供应商授权物资
						if ("1".equals(newProvider.getClearMaterialSign())) {
							delProviderMaterialFiles(providerId);
							cdProviderMaterialDAO.delByProviderId(oldProviderId);
						} else {
							if (flstCdProviderMaterial != null
									&& !flstCdProviderMaterial.isEmpty()) {
								updateProviderMaterialDataAndFiles(curUnitsCode, oldProviderId,
										flstCdProviderMaterial);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 修改供应商附件
	 * 
	 * @param providerId
	 * @param flstCdProviderFiles
	 */
	private void updateProviderFilesDataAndFiles(String providerId,
			List<CdProviderFiles> flstCdProviderFiles) {
		// 过滤要新增的和要删除的
		List<CdProviderFiles> originalPFiles = cdProviderFilesDAO
				.findByProviderId(providerId);
		for (CdProviderFiles pfile : flstCdProviderFiles) {
			if (pfile.getData() != null && pfile.getData().length > 0) {
				// 要上传附件的，都做新增处理
				pfile.setAutoId(null);
			}
		}
		// 过滤要删除的数据删除之，并删除相应的物理文件
		for (CdProviderFiles originalPFile : originalPFiles) {
			boolean existFlag = false;
			for (CdProviderFiles pfile : flstCdProviderFiles) {
				if (originalPFile.getAutoId().equals(pfile.getAutoId())) {
					existFlag = true;
					break;
				}
			}
			// 原数据不存在autoId的，需要删除
			if (!existFlag) {
				String fileName = PROVIDER_FILE_ROOT_DIR + File.separator
						+ originalPFile.getFilePath();
				delFile(fileName);
				cdProviderFilesDAO.delete(originalPFile);
			}
		}
		// 有附件的新增
		saveProviderFilesDataAndFiles(providerId, flstCdProviderFiles);
	}

	/**
	 * 修改供应商授权物资
	 * 
	 * @param providerId
	 * @param flstCdProviderMaterial
	 */
	private void updateProviderMaterialDataAndFiles(String unitsCode,
			String providerId, List<CdProviderMaterial> flstCdProviderMaterial) {
		List<CdProviderMaterial> originalMFiles = cdProviderMaterialDAO
				.findByProviderId(unitsCode, providerId);
		// 初始化unitsCode和providerId
		for (CdProviderMaterial mfile : flstCdProviderMaterial) {
			mfile.setUnitsCode(unitsCode);
			mfile.setProviderId(providerId);
		}
		// 过滤要删除的数据
		for (CdProviderMaterial originalMFile : originalMFiles) {
			int ind = flstCdProviderMaterial.indexOf(originalMFile);
			if (-1 == ind) {
				delProviderMaterialFiles(originalMFile);
				cdProviderMaterialDAO.delete(originalMFile);
			} else {
				flstCdProviderMaterial.get(ind).setAutoId(
						originalMFile.getAutoId());
			}
		}
		int orderNo = 0;
		// 过滤要新增和修改的数据
		for (CdProviderMaterial mfile : flstCdProviderMaterial) {
			int ind = originalMFiles.indexOf(mfile);
			if (ind > -1) {
				CdProviderMaterial originalMFile = originalMFiles.get(ind);
				// 修改数据，并更新附件，
				// 如果参数记录附件accredit_file_name为空，则删除原附件；
				if (mfile.getAccreditFileName1() == null
						|| "".equals(mfile.getAccreditFileName1())) {
					String filePath = originalMFile.getAccreditFilePath1();
					if (filePath != null && !"".equals(filePath)) {
						delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator
								+ filePath);
					}
				}
				if (mfile.getAccreditFileName2() == null
						|| "".equals(mfile.getAccreditFileName2())) {
					String filePath = originalMFile.getAccreditFilePath2();
					if (filePath != null && !"".equals(filePath)) {
						delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator
								+ filePath);
					}
				}
				if (mfile.getAccreditFileName3() == null
						|| "".equals(mfile.getAccreditFileName3())) {
					String filePath = originalMFile.getAccreditFilePath3();
					if (filePath != null && !"".equals(filePath)) {
						delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator
								+ filePath);
					}
				}
				// 如果参数记录附件byte[]不为空，则先删除原附件，
				if (mfile.getData1() != null && mfile.getData1().length > 0) {
					String filePath = originalMFile.getAccreditFilePath1();
					if (filePath != null && !"".equals(filePath)) {
						delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator
								+ filePath);
					}
				}
				if (mfile.getData2() != null && mfile.getData2().length > 0) {
					String filePath = originalMFile.getAccreditFilePath2();
					if (filePath != null && !"".equals(filePath)) {
						delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator
								+ filePath);
					}
				}
				if (mfile.getData3() != null && mfile.getData3().length > 0) {
					String filePath = originalMFile.getAccreditFilePath3();
					if (filePath != null && !"".equals(filePath)) {
						delFile(PROVIDER_MATERIAL_ROOT_DIR + File.separator
								+ filePath);
					}
				}
				// 后增加新附件
				saveProviderMaterialFiles(providerId, mfile, ++orderNo);
				cdProviderMaterialDAO.merge(mfile);
			} else {
				// 新增
				saveProviderMaterialDataAndFiles(unitsCode, providerId, mfile,
						++orderNo);
			}
		}
	}

	/**
	 * 保存上传附件，并写供应商附件表,有附件的才新增, 附件在服务端文件命名规则：yyyyMMddHHmmssSSS-供应商ID-序号
	 * 
	 * @param providerId
	 * @param flstCdProviderFiles
	 */
	private void saveProviderFilesDataAndFiles(String providerId,
			List<CdProviderFiles> flstCdProviderFiles) {
		int orderNo = 0;
		for (CdProviderFiles pfile : flstCdProviderFiles) {
			if (pfile.getData() != null && pfile.getData().length > 0) {
				String fileName = pfile.getFileName();
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String filePath = providerId + File.separator
						+ DateUtil.getCurrentTimeVersion() + "-" + providerId
						+ "-" + (++orderNo) + ext;
				try {
					saveFile(pfile.getData(), PROVIDER_FILE_ROOT_DIR
							+ File.separator + filePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				pfile.setFilePath(filePath);
				pfile.setProviderId(providerId);
				cdProviderFilesDAO.save(pfile);
			}
		}
	}

	private void saveProviderMaterialDataAndFiles(String unitsCode,
			String providerId, List<CdProviderMaterial> flstCdProviderMaterial) {
		int orderNo = 0;
		for (CdProviderMaterial mfile : flstCdProviderMaterial) {
			saveProviderMaterialDataAndFiles(unitsCode, providerId, mfile,
					++orderNo);
		}
	}

	private void saveProviderMaterialDataAndFiles(String unitsCode,
			String providerId, CdProviderMaterial mfile, int orderNo) {
		saveProviderMaterialFiles(providerId, mfile, orderNo);
		mfile.setUnitsCode(unitsCode);
		mfile.setProviderId(providerId);
		cdProviderMaterialDAO.save(mfile);
	}

	private void saveProviderMaterialFiles(String providerId,
			CdProviderMaterial mfile, int orderNo) {
		if (mfile.getData1() != null && mfile.getData1().length > 0) {
			String filePath1 = parseFilePath(mfile.getAccreditFileName1(),
					providerId, orderNo, 1);
			try {
				saveFile(mfile.getData1(), PROVIDER_MATERIAL_ROOT_DIR
						+ File.separator + filePath1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mfile.setAccreditFilePath1(filePath1);
		}
		if (mfile.getData2() != null && mfile.getData2().length > 0) {
			String filePath2 = parseFilePath(mfile.getAccreditFileName2(),
					providerId, orderNo, 2);
			try {
				saveFile(mfile.getData2(), PROVIDER_MATERIAL_ROOT_DIR
						+ File.separator + filePath2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mfile.setAccreditFilePath2(filePath2);
		}
		if (mfile.getData3() != null && mfile.getData3().length > 0) {
			String filePath3 = parseFilePath(mfile.getAccreditFileName3(),
					providerId, orderNo, 3);
			try {
				saveFile(mfile.getData3(), PROVIDER_MATERIAL_ROOT_DIR
						+ File.separator + filePath3);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mfile.setAccreditFilePath3(filePath3);
		}
	}

	/**
	 * 附件在服务端文件命名规则：yyyyMMddHHmmssSSS-供应商ID-序号-子序号
	 * 
	 * @param fileName
	 * @param providerId
	 * @param orderNo
	 * @param subOrderNo
	 * @return
	 */
	private String parseFilePath(String fileName, String providerId,
			int orderNo, int subOrderNo) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return providerId + File.separator + DateUtil.getCurrentTimeVersion()
				+ "-" + providerId + "-" + (orderNo) + "-" + subOrderNo + ext;
	}

	private void saveFile(byte[] data, String fullFileName) throws IOException {
		System.out.println(fullFileName);
		String lstrFilePath = fullFileName.substring(0, fullFileName
				.lastIndexOf(File.separator));
		File dir = new File(lstrFilePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (data != null && data.length > 0) {
			OutputStream os = null;
			try {
				os = new FileOutputStream(fullFileName);
				os.write(data);
			} finally {
				if (os != null)
					os.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject downLoadFile(String fstrFlag, String fstrFilePath) {
		ReObject ro = new ReObject("根据附件路径，下载指定附件");
		FileInputStream fis = null;
		byte[] bits = null;
		String fileName = ("1".equals(fstrFlag) ? PROVIDER_FILE_ROOT_DIR
				: PROVIDER_MATERIAL_ROOT_DIR)
				+ File.separator + fstrFilePath;
		try {
			fis = new FileInputStream(fileName);
			bits = new byte[fis.available()];
			fis.read(bits);
			fis.close();
		} catch (IOException e) {
			ro.setError("下载文件失败");
			return ro;
		}
		List lstData = new ArrayList();
		lstData.add(bits);
		ro.setData(lstData);
		return ro;
	}

	/**
	 * 根据输入码、分管部门查询供应商 修改者：周作建 2011.06.18
	 */
	@Override
	public ReObject findProviderDictListByInputCode(ParameterObject fparameter) {
		ReObject reObject = new ReObject("根据输入码查询供应商字典");

		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		String chargeDept = (String) map.get("chargeDept");

		StringBuilder conditions = new StringBuilder();

		String unitsCode = SessionUtil.getUnitsCode();
		conditions.append(" where unitsCode='" + unitsCode + "'");
		if (phoInputCode != null && phoInputCode.length() > 0) {
			conditions.append(" and phoInputCode like'" + phoInputCode + "%'");
		} else {
			conditions
					.append(" and fiveInputCode like'" + fiveInputCode + "%'");
		}
		if (chargeDept != null && chargeDept.length() > 0) {
			conditions.append(" and chargeDept='" + chargeDept + "'");
		}
		List<CdProvider> lstProviderDict = cdProviderDAO
				.findByCondition(conditions.toString());

		reObject.setData(lstProviderDict);
		return reObject;
	}

	@Override
	public ReObject freezeProviderById(String fstrProviderId) {
		ReObject reObject = new ReObject("冻结供应商");
		cdProviderDAO.freezeProviderById(fstrProviderId);
		return reObject;
	}

	@Override
	public ReObject unFreezeProviderById(String fstrProviderId) {
		ReObject reObject = new ReObject("解冻供应商");
		cdProviderDAO.unFreezeProviderById(fstrProviderId);
		return reObject;
	}
}
