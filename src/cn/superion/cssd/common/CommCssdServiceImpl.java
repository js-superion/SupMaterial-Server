package cn.superion.cssd.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.cssd.dao.CssdAntisepsisDictDAO;
import cn.superion.cssd.dao.CssdChemistryMaterialDictDAO;
import cn.superion.cssd.dao.CssdChemistryTypeDictDAO;
import cn.superion.cssd.dao.CssdDeptVsPackageClassDAO;
import cn.superion.cssd.dao.CssdEquipmentDictDAO;
import cn.superion.cssd.dao.CssdFileClassDictDAO;
import cn.superion.cssd.dao.CssdPackageClassDictDAO;
import cn.superion.cssd.dao.CssdPackageDictDAO;
import cn.superion.cssd.dao.CssdPackageDictDetailDAO;
import cn.superion.cssd.dao.CssdPackageModeDictDAO;
import cn.superion.cssd.dao.CssdSpecimenDictDAO;
import cn.superion.cssd.dao.CssdSterilizeTypeDictDAO;
import cn.superion.cssd.dao.CssdStockMasterDAO;
import cn.superion.cssd.dao.CssdTestItemDictDAO;
import cn.superion.cssd.dao.VCssdPackageDictDetailDAO;
import cn.superion.cssd.entity.CssdAntisepsisDict;
import cn.superion.cssd.entity.CssdChemistryMaterialDict;
import cn.superion.cssd.entity.CssdChemistryTypeDict;
import cn.superion.cssd.entity.CssdEquipmentDict;
import cn.superion.cssd.entity.CssdFileClassDict;
import cn.superion.cssd.entity.CssdPackageClassDict;
import cn.superion.cssd.entity.CssdPackageDict;
import cn.superion.cssd.entity.CssdPackageDictDetail;
import cn.superion.cssd.entity.CssdPackageModeDict;
import cn.superion.cssd.entity.CssdSpecimenDict;
import cn.superion.cssd.entity.CssdSterilizeTypeDict;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.cssd.entity.CssdTestItemDict;
import cn.superion.cssd.entity.VCssdPackageDictDetail;
import cn.superion.util.SessionUtil;

/**
 * 物资公共服务实现
 * 
 * @author 曹国魁
 * 
 */
public class CommCssdServiceImpl implements ICommCssdService {
	private CssdFileClassDictDAO cssdFileClassDictDAO;
	private CssdPackageClassDictDAO cssdPackageClassDictDAO;
	private CssdPackageModeDictDAO cssdPackageModeDictDAO;
	private CssdChemistryMaterialDictDAO cssdChemistryMaterialDictDAO;
	private CssdAntisepsisDictDAO cssdAntisepsisDictDAO;
	private CssdEquipmentDictDAO cssdEquipmentDictDAO;
	private CssdSterilizeTypeDictDAO cssdSterilizeTypeDictDAO;
	private VCssdPackageDictDetailDAO vCssdPackageDictDetailDAO;
	private CssdPackageDictDAO cssdPackageDictDAO;
	private CssdPackageDictDetailDAO cssdPackageDictDetailDAO;
	private CssdSpecimenDictDAO cssdSpecimenDictDAO;
	private CssdTestItemDictDAO cssdTestItemDictDAO;
	private CssdChemistryTypeDictDAO cssdChemistryTypeDictDAO;
	private CssdStockMasterDAO cssdStockMasterDAO;
	public CssdSpecimenDictDAO getCssdSpecimenDictDAO() {
		return cssdSpecimenDictDAO;
	}

	public void setCssdSpecimenDictDAO(CssdSpecimenDictDAO cssdSpecimenDictDAO) {
		this.cssdSpecimenDictDAO = cssdSpecimenDictDAO;
	}

	public CssdTestItemDictDAO getCssdTestItemDictDAO() {
		return cssdTestItemDictDAO;
	}

	public void setCssdTestItemDictDAO(CssdTestItemDictDAO cssdTestItemDictDAO) {
		this.cssdTestItemDictDAO = cssdTestItemDictDAO;
	}

	public CssdChemistryTypeDictDAO getCssdChemistryTypeDictDAO() {
		return cssdChemistryTypeDictDAO;
	}

	public void setCssdChemistryTypeDictDAO(
			CssdChemistryTypeDictDAO cssdChemistryTypeDictDAO) {
		this.cssdChemistryTypeDictDAO = cssdChemistryTypeDictDAO;
	}

	public CssdStockMasterDAO getCssdStockMasterDAO() {
		return cssdStockMasterDAO;
	}

	public void setCssdStockMasterDAO(CssdStockMasterDAO cssdStockMasterDAO) {
		this.cssdStockMasterDAO = cssdStockMasterDAO;
	}

	public VCssdPackageDictDetailDAO getvCssdPackageDictDetailDAO() {
		return vCssdPackageDictDetailDAO;
	}

	public void setvCssdPackageDictDetailDAO(
			VCssdPackageDictDetailDAO vCssdPackageDictDetailDAO) {
		this.vCssdPackageDictDetailDAO = vCssdPackageDictDetailDAO;
	}

	public CssdPackageClassDictDAO getCssdPackageClassDictDAO() {
		return cssdPackageClassDictDAO;
	}

	public void setCssdPackageClassDictDAO(
			CssdPackageClassDictDAO cssdPackageClassDictDAO) {
		this.cssdPackageClassDictDAO = cssdPackageClassDictDAO;
	}

	public CssdPackageModeDictDAO getCssdPackageModeDictDAO() {
		return cssdPackageModeDictDAO;
	}

	public void setCssdPackageModeDictDAO(
			CssdPackageModeDictDAO cssdPackageModeDictDAO) {
		this.cssdPackageModeDictDAO = cssdPackageModeDictDAO;
	}

	public CssdChemistryMaterialDictDAO getCssdChemistryMaterialDictDAO() {
		return cssdChemistryMaterialDictDAO;
	}

	public void setCssdChemistryMaterialDictDAO(
			CssdChemistryMaterialDictDAO cssdChemistryMaterialDictDAO) {
		this.cssdChemistryMaterialDictDAO = cssdChemistryMaterialDictDAO;
	}

	public CssdAntisepsisDictDAO getCssdAntisepsisDictDAO() {
		return cssdAntisepsisDictDAO;
	}

	public void setCssdAntisepsisDictDAO(CssdAntisepsisDictDAO cssdAntisepsisDictDAO) {
		this.cssdAntisepsisDictDAO = cssdAntisepsisDictDAO;
	}

	public CssdEquipmentDictDAO getCssdEquipmentDictDAO() {
		return cssdEquipmentDictDAO;
	}

	public void setCssdEquipmentDictDAO(CssdEquipmentDictDAO cssdEquipmentDictDAO) {
		this.cssdEquipmentDictDAO = cssdEquipmentDictDAO;
	}

	public CssdSterilizeTypeDictDAO getCssdSterilizeTypeDictDAO() {
		return cssdSterilizeTypeDictDAO;
	}

	public void setCssdSterilizeTypeDictDAO(
			CssdSterilizeTypeDictDAO cssdSterilizeTypeDictDAO) {
		this.cssdSterilizeTypeDictDAO = cssdSterilizeTypeDictDAO;
	}

	public void setCssdFileClassDictDAO(
			CssdFileClassDictDAO cssdFileClassDictDAO) {
		this.cssdFileClassDictDAO = cssdFileClassDictDAO;
	}

	public CssdFileClassDictDAO getCssdFileClassDictDAO() {
		return cssdFileClassDictDAO;
	}

	public void setCssdPackageDictDAO(CssdPackageDictDAO cssdPackageDictDAO) {
		this.cssdPackageDictDAO = cssdPackageDictDAO;
	}

	public CssdPackageDictDAO getCssdPackageDictDAO() {
		return cssdPackageDictDAO;
	}

	public void setCssdPackageDictDetailDAO(
			CssdPackageDictDetailDAO cssdPackageDictDetailDAO) {
		this.cssdPackageDictDetailDAO = cssdPackageDictDetailDAO;
	}

	public CssdPackageDictDetailDAO getCssdPackageDictDetailDAO() {
		return cssdPackageDictDetailDAO;
	}

	@Override
	public ReObject findCssdFileClassByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询文件分类字典列表");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdFileClassDict> flieClassList = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			flieClassList = cssdFileClassDictDAO
					.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			flieClassList = cssdFileClassDictDAO
					.findByFiveInputCode(fiveInputCode);
		} else {
			flieClassList = cssdFileClassDictDAO.findAll();
		}
		ro.setData(flieClassList);
		return ro;
	}

	@Override
	public ReObject findAllCssdFileClass() {
		ReObject ro = new ReObject("查询文件类型列表");
		List<Map<String, Object>> llstFiles = cssdFileClassDictDAO
				.findAllFiles();
		ro.setData(llstFiles);
		return ro;
	}

	@Override
	public ReObject downloadFile(String fstrAutoId, String fstrSysFileName,
			String fstrModual) {
		byte[] fileData = getTrainFileDataBySysFileName(fstrAutoId,
				fstrSysFileName, fstrModual);
		List<Object> list = new ArrayList<Object>();
		list.add(fileData);
		ReObject ro = new ReObject("下载附件");
		ro.setData(list);
		return ro;
	}

	@Override
	public ReObject findCssdPackageByIntputCode(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入简码查询有效物品包字典");
		Map<String, Object> map = fparameter.getConditions();
		String lstrInputType = map.get("phoInputCode") == null ? "2" : "1";
		int limit = fparameter.getItemsPerPage();
		String lstrInputCode = (String) (map.get("phoInputCode") == null ? map
				.get("fiveInputCode") : map.get("phoInputCode"));
		String lstrUnitsCode = SessionUtil.getUnitsCode();
		List<CssdPackageDict> lstPackageDict = cssdPackageDictDAO
				.findValidByInputCode(lstrUnitsCode, lstrInputCode, lstrInputType,
						limit);
		ro.setData(lstPackageDict);
		return ro;
	}
	
	public ReObject findCssdPackageByIntputCode1(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入简码查询有效物品包字典");
		Map<String, Object> map = fparameter.getConditions();
		int limit = fparameter.getItemsPerPage();
		//拼音或五笔编码
		String lstrInputCode = (String) (map.get("phoInputCode") == null ? map
				.get("fiveInputCode") : map.get("phoInputCode"));
		//所在部门
		String deptCode=(String) (map.get("deptCode") == null ? map
				.get("deptCode") : map.get("deptCode"));
		String lstrUnitsCode = SessionUtil.getUnitsCode();
		List<Object> lstPackageDict = cssdPackageDictDAO
				.findValidByInDeptCode(lstrUnitsCode, lstrInputCode, deptCode,
						limit);
		ro.setData(lstPackageDict);
		return ro;
	}
	public ReObject findCssdPackageByIntputCode2(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入简码查询有效物品包字典");
		Map<String, Object> map = fparameter.getConditions();
		int limit = fparameter.getItemsPerPage();
		//拼音或五笔编码
		String lstrInputCode = (String) (map.get("phoInputCode") == null ? map
				.get("fiveInputCode") : map.get("phoInputCode"));
		//所在部门
		String inputType = map.get("phoInputCode") == null ? "2" :"1";
		String deptCode=SessionUtil.getSysUser().getDeptCode();
		String lstrUnitsCode = SessionUtil.getUnitsCode();
		List<Object> lstPackageDict = cssdPackageDictDAO
				.findValidByInDeptCode(lstrUnitsCode,deptCode, lstrInputCode, inputType,
						limit);
		ro.setData(lstPackageDict);
		return ro;
	}
	public ReObject findAllPackage() {
		ReObject ro = new ReObject("查所有的包");
		List<CssdPackageDict> lstPackageDict = cssdPackageDictDAO
				.findAll();
		ro.setData(lstPackageDict);
		return ro;
	}
	public byte[] getTrainFileDataBySysFileName(String fstrPersonId,
			String fstrSysFileName, String fstrModual) {
		byte[] fileData = new byte[0];
		try {
			String filePath = CommCssdServiceImpl.createFilePath(SessionUtil
					.getUnitsCode(), fstrPersonId, fstrModual);
			String fullFileName = filePath + fstrSysFileName;
			File file = new File(fullFileName);
			FileInputStream fis;
			fis = new FileInputStream(file);
			fileData = new byte[(int) file.length()];
			fis.read(fileData);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return fileData;
	}

	public static String createFileName(String fileName) {
		String[] faryName = fileName.split("\\.");
		String fstrExtName = faryName.length > 1 ? "." + faryName[1] : "";
		return System.currentTimeMillis() + fstrExtName;
	}

	public static String createFilePath(String unitsCode, String personId,
			String modual) {
		String lstrTrainBasePath = ReadPropertiesFile.getValue("CSSD_FILE_DIR");
		String lstrFile = lstrTrainBasePath + "//" + modual + "//" + unitsCode
				+ "//" + personId + "//";
		return lstrFile;
	}

	@Override
	public ReObject findCssdPackageClassDictByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询物品包分类字典");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdPackageClassDict> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdPackageClassDictDAO.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdPackageClassDictDAO.findByFiveInputCode(fiveInputCode);
		} else {
			data = cssdPackageClassDictDAO.findAll();
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCssdPackageClassDictByCode(String fstrCode) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询物品包分类字典");
		List<CssdPackageClassDict> data = null;
		data = cssdPackageClassDictDAO.findByProperty("classCode", fstrCode);
		ro.setData(data);
		return ro;
	}
	@Override
	public ReObject findCssdPackageModeDictByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询物品包装方式字典");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdPackageModeDict> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdPackageModeDictDAO.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdPackageModeDictDAO.findByFiveInputCode(fiveInputCode);
		} else {
			data = cssdPackageModeDictDAO.findAll();
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCssdChemistryMaterialDictByCondition(
			ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询化学指示物字典");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdChemistryMaterialDict> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdChemistryMaterialDictDAO
					.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdChemistryMaterialDictDAO
					.findByFiveInputCode(fiveInputCode);
		} else {
			data = cssdChemistryMaterialDictDAO.findAll();
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCssdAntisepsisDictByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询消毒液字典");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdAntisepsisDict> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdAntisepsisDictDAO.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdAntisepsisDictDAO.findByFiveInputCode(fiveInputCode);
		} else {
			data = cssdAntisepsisDictDAO.findAll();
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCssdEquipmentDictByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询设备字典");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdEquipmentDict> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdEquipmentDictDAO.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdEquipmentDictDAO.findByFiveInputCode(fiveInputCode);
		} else {
			data = cssdEquipmentDictDAO.findAll();
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCssdSterilizeTypeDictByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询灭菌类型字典");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdSterilizeTypeDict> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdSterilizeTypeDictDAO.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdSterilizeTypeDictDAO.findByFiveInputCode(fiveInputCode);
		} else {
			data = cssdSterilizeTypeDictDAO.findAll();
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findVCssdPackageDictDetailByPackageId(String fstrPackageId) {
		ReObject ro = new ReObject("根据物品包编码查询物品包明细视图");
		List<VCssdPackageDictDetail> data = vCssdPackageDictDetailDAO.findByPackageId(SessionUtil.getUnitsCode(),fstrPackageId);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findStockMasterByPackageNo(String fstrPackageNo) {
		ReObject ro = new ReObject("根据物品包编号查询物品包信息");
		CssdStockMaster stockMaster = cssdStockMasterDAO.findById(fstrPackageNo);
		List<Object> data = new ArrayList<Object>();
		data.add(stockMaster);
		ro.setData(data);
		return ro;
	}


	@Override
	public ReObject findVCssdPackageDictDetailByPackageId(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据物品包编码查询物品包明细视图");
		Map<String,Object> map=fparameter.getConditions();
		String lstrPackageId=(String)map.get("packageId");
		List<VCssdPackageDictDetail> data = vCssdPackageDictDetailDAO.findByPackageId(SessionUtil.getUnitsCode(),lstrPackageId);
		ro.setData(data);
		return ro;
	}	
	@Override
	public ReObject findCssdSpecimenDictByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询送检标本字典");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdSpecimenDict> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdSpecimenDictDAO.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdSpecimenDictDAO.findByFiveInputCode(fiveInputCode);
		} else {
			data = cssdSpecimenDictDAO.findAll();
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCssdTestItemDictBySpecimenCode(String fstrSpecimenCode) {
		ReObject ro = new ReObject("根据标本编码查询送检项目字典");
		List<CssdTestItemDict> data = cssdTestItemDictDAO.findBySpecimenCode(fstrSpecimenCode);
		ro.setData(data);
		return ro;
	}
	@Override
	public ReObject findCssdPackageDetailByMainId(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据包id查询包明细");
		Map<String,Object> map=fparameter.getConditions();
		String lstrPackageId=(String)map.get("packageId");
		List<CssdPackageDictDetail> llstPackageDetails= cssdPackageDictDetailDAO.findByPackageId(SessionUtil.getUnitsCode(),lstrPackageId);
		ro.setData(llstPackageDetails);
		return ro;
	}
	@Override
	public ReObject findCssdEquipmentByInputCode(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询设备字典");
		Map<String,Object> map=fparameter.getConditions();
		StringBuilder condition = new StringBuilder();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		String unitsCode = SessionUtil.getUnitsCode();
		condition.append(" where unitsCode = '" + unitsCode + "'");
		if (phoInputCode != null && phoInputCode.toString().trim().length() > 0) {
			condition.append(" and phoInputCode like '" + phoInputCode + "%'");
		} else {
			condition
					.append(" and fiveInputCode like '" + fiveInputCode + "%'");
		}
		List<CssdEquipmentDict> llstEquipments= cssdEquipmentDictDAO.findByCondtion( condition.toString());
		ro.setData(llstEquipments);
		return ro;
	}	

	@Override
	public ReObject findAllCssdEquipment() {
		ReObject ro = new ReObject("查询设备字典");
		List<CssdEquipmentDict> llstEquipments= cssdEquipmentDictDAO.findAll();
		ro.setData(llstEquipments);
		return ro;
	}

	@Override
	public ReObject findCssdChemistryTypeDictByCondition(
			ParameterObject fparameter) {
		ReObject ro = new ReObject("根据输入的拼音码或五笔码查询化学检测类型字典");
		Map<String, Object> map = fparameter.getConditions();
		String phoInputCode = (String) map.get("phoInputCode");
		String fiveInputCode = (String) map.get("fiveInputCode");
		List<CssdChemistryTypeDict> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdChemistryTypeDictDAO.findByPhoInputCode(phoInputCode);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdChemistryTypeDictDAO.findByFiveInputCode(fiveInputCode);
		} else {
			data = cssdChemistryTypeDictDAO.findAll();
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject uploadFileToTmp(String fstrFileName, byte[] faryFileData) {
		String lstrBaseTmpPath=ReadPropertiesFile.getValue("UPLOAD_FILE_TEMP_BASE_DIR");
		File dir=new File(lstrBaseTmpPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		try {
		    FileOutputStream fos=new FileOutputStream(lstrBaseTmpPath+fstrFileName);		
			fos.write(faryFileData);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return new ReObject("上传并保存为临时文件");
	}

	
}
