package cn.superion.equipment.ledger.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.center.deptPerson.dao.CdDeptDictDAO;
import cn.superion.center.deptPerson.dao.CdPersonDictDAO;
import cn.superion.center.deptPerson.entity.CdDeptDict;
import cn.superion.center.deptPerson.entity.CdPersonDict;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqClassDictDAO;
import cn.superion.equipment.dao.EqEquipmentDAO;
import cn.superion.equipment.dao.EqEquipmentFilesDAO;
import cn.superion.equipment.dao.EqStatusDictDAO;
import cn.superion.equipment.entity.EqClassDict;
import cn.superion.equipment.entity.EqEquipment;
import cn.superion.equipment.entity.EqEquipmentFiles;
import cn.superion.equipment.entity.EqStatusDict;
import cn.superion.exception.AppException;
import cn.superion.system.entity.SysUser;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;
import cn.superion.util.jxl.JExcel;
import cn.superion.util.jxl.XlsData;
import flex.messaging.FlexContext;

/**
 * 设备台账服务实现
 * 
 * @author 曹国魁
 * 
 */
public class EquipmentImpl implements IEquipment {
	private static String EQUIPMENT_FILE_ROOT_DIR;
	static {
		EQUIPMENT_FILE_ROOT_DIR = ReadPropertiesFile
				.getValue("EQUIPMENT_FILE_ROOT_DIR");
		File file = new File(EQUIPMENT_FILE_ROOT_DIR);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = null;
	}

	private EqEquipmentDAO eqEquipmentDAO;
	private EqEquipmentFilesDAO eqEquipmentFilesDAO;
	private IEqBillNoService eqBillNoService;
	private CdPersonDictDAO  cdPersonDictDAO;
	private CdDeptDictDAO  cdDeptDictDAO;
	private EqStatusDictDAO  eqStatusDictDAO;
	private EqClassDictDAO  eqClassDictDAO;
	

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}

	public EqEquipmentDAO getEqEquipmentDAO() {
		return eqEquipmentDAO;
	}

	public void setEqEquipmentDAO(EqEquipmentDAO eqEquipmentDAO) {
		this.eqEquipmentDAO = eqEquipmentDAO;
	}

	public CdPersonDictDAO getCdPersonDictDAO() {
		return cdPersonDictDAO;
	}

	public void setCdPersonDictDAO(CdPersonDictDAO cdPersonDictDAO) {
		this.cdPersonDictDAO = cdPersonDictDAO;
	}

	public CdDeptDictDAO getCdDeptDictDAO() {
		return cdDeptDictDAO;
	}

	public void setCdDeptDictDAO(CdDeptDictDAO cdDeptDictDAO) {
		this.cdDeptDictDAO = cdDeptDictDAO;
	}

	public EqEquipmentFilesDAO getEqEquipmentFilesDAO() {
		return eqEquipmentFilesDAO;
	}

	public void setEqEquipmentFilesDAO(EqEquipmentFilesDAO eqEquipmentFilesDAO) {
		this.eqEquipmentFilesDAO = eqEquipmentFilesDAO;
	}
 
	public EqStatusDictDAO getEqStatusDictDAO() {
		return eqStatusDictDAO;
	}

	public void setEqStatusDictDAO(EqStatusDictDAO eqStatusDictDAO) {
		this.eqStatusDictDAO = eqStatusDictDAO;
	}

	public EqClassDictDAO getEqClassDictDAO() {
		return eqClassDictDAO;
	}

	public void setEqClassDictDAO(EqClassDictDAO eqClassDictDAO) {
		this.eqClassDictDAO = eqClassDictDAO;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的设备台账");
		EqEquipment eq = eqEquipmentDAO.findById(fstrAutoId);
		if (eq == null) {
			throw new RuntimeException("不存在此设备台账[系统标识号：" + fstrAutoId
					+ "],不能删除！");
		}
		if ("1".equals(eq.getCurrentStatus())) {
			throw new RuntimeException("设备台账已审核[系统标识号：" + fstrAutoId
					+ "]，不能删除！");
		}
		// 删除物理文件
		deleteFiles(fstrAutoId);
		eqEquipmentFilesDAO.delByAutoId(fstrAutoId);
		eqEquipmentDAO.delete(eq);
		return ro;
	}

	private void deleteFiles(String autoId) {
		List<EqEquipmentFiles> files = eqEquipmentFilesDAO.findByAutoId(autoId);
		for (EqEquipmentFiles file : files) {
			String fileName = EQUIPMENT_FILE_ROOT_DIR + File.separator
					+ file.getFilePath();
			delFile(fileName);
		}
	}

	private void delFile(String fileName) {
		File file = new File(fileName);
		file.delete();
	}

	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，查询设备台账主记录ID列表");
		List<Object> data = eqEquipmentDAO.findAutoIdsByCondition(SessionUtil
				.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据设备台账主记录ID查询设备台账卡片");
		EqEquipment eq = eqEquipmentDAO.findById(fstrAutoId);
		List<EqEquipmentFiles> eqFiles = eqEquipmentFilesDAO
				.findByAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(eq);
		data.add(eqFiles);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = eqEquipmentDAO.findByCondition(start, limit,
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject save(EqEquipment equipment,
			List<EqEquipmentFiles> equipmentFiles) {
		ReObject ro = new ReObject("保存设备台账信息");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String equipmentCode = equipment.getEquipmentCode();
		/*if (equipmentCode == null || "".equals(equipmentCode.trim())) {
			throw new RuntimeException("设备编码不能为空！");
		}*/
		String autoId = equipment.getAutoId();
		if (autoId == null || "".equals(autoId)) {
			// 新增
			// 校验设备编码唯一
			/*if (!eqEquipmentDAO.checkCodeUnique(unitsCode, equipmentCode)) {
				throw new RuntimeException("设备编码[" + equipmentCode
						+ "]已存在,不能保存！");
			}*/
			if(equipmentCode == null || "".equals(equipmentCode)){
				equipmentCode = eqBillNoService.getNextBillNo("9");
				equipment.setEquipmentCode(equipmentCode);
			}
			equipment.setUnitsCode(unitsCode);
			equipment.setCurrentStatus("0");
			equipment.setMaker(user.getPersonId());
			equipment.setMakeDate(new Date());
			eqEquipmentDAO.save(equipment);
			saveEquipmentFilesDataAndFile(equipment.getAutoId(), equipmentFiles);
		} else {
			// 修改
			// 校验审核状态
			EqEquipment original = eqEquipmentDAO.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在此设备台账[系统标识号：" + autoId
						+ "],不能修改！");
			}
			if ("1".equals(original.getCurrentStatus())) {
				throw new RuntimeException("设备台账已审核[系统标识号：" + autoId
						+ "]，不能修改！");
			}
			// 校验设备编码唯一
			/*if (!eqEquipmentDAO.checkCodeUnique(unitsCode, equipmentCode,
					autoId)) {
				throw new RuntimeException("设备编码[" + equipmentCode
						+ "]已存在,不能保存！");
			}*/
			eqEquipmentDAO.merge(equipment);
			updateEquipmentFilesDataAndFiles(autoId, equipmentFiles);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(equipment);
		data.add(equipmentFiles);
		ro.setData(data);
		return ro;
	}

	private void saveEquipmentFilesDataAndFile(String autoId,
			List<EqEquipmentFiles> equipmentFiles) {
		//序号
		short ser = 0;
		for (EqEquipmentFiles efile : equipmentFiles) {
			efile.setAutoId(autoId);
			efile.setSerialNo(++ser);
			if (efile.getData() != null && efile.getData().length > 0) {
				String fileName = efile.getFileName();
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String filePath = DateUtil.getCurrentTimeVersion() + "-"
						+ autoId + "-" + (efile.getFileNo()) + ext;
				try {
					saveFile(efile.getData(), EQUIPMENT_FILE_ROOT_DIR
							+ File.separator + filePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//保存完文件后，释放
				efile.setData(null);
				efile.setFilePath(filePath);
			}
			if(efile.isToUpdated()){
				eqEquipmentFilesDAO.merge(efile);
				efile.setToUpdated(false);
			}
			else
				eqEquipmentFilesDAO.save(efile);
		}
	}

	private void updateEquipmentFilesDataAndFiles(String autoId,
			List<EqEquipmentFiles> equipmentFiles) {
		// 过滤要新增的和要删除的
		List<EqEquipmentFiles> originalFiles = eqEquipmentFilesDAO
				.findByAutoId(autoId);
		// 过滤要删除的数据删除之，并删除相应的物理文件
		for (EqEquipmentFiles originalFile : originalFiles) {
			boolean existFlag = false;
			if (equipmentFiles != null) {
				for (EqEquipmentFiles efile : equipmentFiles) {
					if (originalFile.getFileNo().equals(efile.getFileNo())) {
						existFlag = true;
						//标记要修改
						efile.setToUpdated(true);
						break;
					}
				}
			}
			// 原数据不存在的，需要删除
			if (!existFlag) {
				String fileName = EQUIPMENT_FILE_ROOT_DIR + File.separator
						+ originalFile.getFilePath();
				delFile(fileName);
				eqEquipmentFilesDAO.delete(originalFile);
			}
		}
		// 有附件的新增
		saveEquipmentFilesDataAndFile(autoId, equipmentFiles);
	}

	private void saveFile(byte[] data, String fullFileName) throws IOException {
		String filePath = fullFileName.substring(0, fullFileName
				.lastIndexOf(File.separator));
		File dir = new File(filePath);
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

	
	public ReObject saveImportData(Map fmap) {
		String unitsCode = SessionUtil.getUnitsCode();
		ReObject ro = new ReObject("导入");
		XlsData xlsData = null;
		try {
			JExcel je = new JExcel();
			byte[] data = (byte[]) fmap.get("data");
			xlsData = je.readXls(new ByteArrayInputStream(data));
		} catch (AppException e) {
			ro.setError("解析表格出错" + e.getMessage());
			ro.setSuccess(false);
			return ro;
		}
		if (xlsData != null) {
			List<EqEquipment> details = new ArrayList<EqEquipment>();
			String autoId = "";
			List<Map> tbData = xlsData.getTableDatas();
			for (Map map : tbData) {
				EqEquipment staffSalary = fillEntityByExcelItem(map,unitsCode);
				eqEquipmentDAO.save(staffSalary);
				details.add(staffSalary);
			}
			ro.setData(details);
		}
		return ro;
	}
	
	private EqEquipment fillEntityByExcelItem(Map map,String unitsCode)
	{
		String equipmentCode = ((String) map.get("equipmentCode")).trim();
		String equipmentName = ((String) map.get("equipmentName")).trim();
		String equipmentClassName = ((String) map.get("equipmentClass")).trim();
		String brandName = ((String) map.get("brandName")).trim();
		String equipmentSpec = ((String) map.get("equipmentSpec")).trim();
		Double originalValue = Double.parseDouble( map.get("originalValue").toString());
		String measureSign = ((String) map.get("measureSign")).trim();
		String equipmentStatusName = ((String) map.get("equipmentStatus")).trim();
		String usedDeptName = ((String) map.get("usedDeptName")).trim();
		String positionCodeName = ((String) map.get("positionCodeName")).trim();
		String chargePersonName = ((String) map.get("chargePersonName")).trim();
		String serialNumber = ((String) map.get("serialNumber")).trim();
		Double motorPower = Double.parseDouble( map.get("motorPower").toString());
		
		String supplier = ((String) map.get("supplier")).trim();
		String manufacturer = ((String) map.get("manufacturer")).trim();
		Date dateOfProduction = (Date) map.get("dateOfProduction");
		Date dateOfPurchase = ((Date) map.get("dateOfPurchase"));
		Date verifyDate = ((Date) map.get("verifyDate"));
		Integer serviceLife = Integer.parseInt( map.get("serviceLife").toString());
		Date guaranteeCutOffDate = ((Date) map.get("guaranteeCutOffDate"));
		String testUnit = ((String) map.get("testUnit")).trim();
		Date testDate = ((Date) map.get("testDate"));
		Date testValDate = ((Date) map.get("testValDate"));
		String testCertifyNo = ((String) map.get("testCertifyNo")).trim();
		String testRes = ((String) map.get("testRes")).trim();
		String remark = ((String) map.get("remark")).trim();
		
		EqEquipment _eqEquipment = new EqEquipment();
		_eqEquipment.setUnitsCode(unitsCode);
		_eqEquipment.setEquipmentCode(equipmentCode);
		_eqEquipment.setEquipmentName(equipmentName);
		//序列号
		_eqEquipment.setSerialNumber(serialNumber);
		//电机功率
		_eqEquipment.setMotorPower(motorPower);
		_eqEquipment.setEquipmentSpec(equipmentSpec);
		//电机数量
//		_eqEquipment.setMotorNum(Number(motorNum);
		//制度时间
//		_eqEquipment.setSystemTime(Number(systemTime);
		//出厂日期
		_eqEquipment.setDateOfProduction(dateOfProduction);
		_eqEquipment.setManufacturer(manufacturer);
		_eqEquipment.setSupplier(supplier);
		_eqEquipment.setVerifyDate(verifyDate);
		//转换人员名转换成代码
		List<CdPersonDict> persons = cdPersonDictDAO.findByProperty("userName", chargePersonName);
		if(persons!=null && persons.size() > 0){
			_eqEquipment.setChargePerson(persons.get(0).getPersonId());//要转换
		}
		//根据部门名称转换为代码
		List<CdDeptDict> depts = cdDeptDictDAO.findByProperty("deptName", usedDeptName);
		if(depts!=null && depts.size() > 0){
			_eqEquipment.setUsedDept(depts.get(0).getDeptCode());//要转换
		}
		//根据位置名称转换为代码
		List<CdDeptDict> positions = cdDeptDictDAO.findByProperty("deptName", positionCodeName);
		if(positions!=null && positions.size() > 0){
			_eqEquipment.setPositionCode(positions.get(0).getDeptCode());//要转换
		}
		
		//根据状态名称转换为代码
		List<EqStatusDict> eqStatus = eqStatusDictDAO.findByProperty("statusName", equipmentStatusName);
		if(eqStatus!=null && eqStatus.size() > 0){
			_eqEquipment.setEquipmentStatus(eqStatus.get(0).getStatusCode());//要转换
		}
		
		//根据类别名称转换为代码
		List<EqClassDict> eqClasses = eqClassDictDAO.findByProperty("className", equipmentClassName);
		if(eqClasses!=null && eqClasses.size() > 0){
			_eqEquipment.setEquipmentClass(eqClasses.get(0).getClassCode());//要转换
		}
		
		//购买日期
		_eqEquipment.setDateOfPurchase(dateOfPurchase);
		//使用日期
//		_eqEquipment.setDateOfUsed(dateOfUsed);
		//安装日期
//		_eqEquipment.setDateOfSetup(dateOfSetUp);
		//使用年限
		_eqEquipment.setServiceLife(serviceLife);
		//保修截止日期
		_eqEquipment.setGuaranteeCutOffDate(guaranteeCutOffDate);
		//设备原值
		_eqEquipment.setOriginalValue(originalValue);
		//备注
		_eqEquipment.setRemark(remark);
		//品牌
		_eqEquipment.setBrandName (brandName);
		_eqEquipment.setTestRes (testRes);
		_eqEquipment.setTestDate (testDate);
		_eqEquipment.setTestCertifyNo(testCertifyNo);
		_eqEquipment.setTestUnit (testUnit);
		_eqEquipment.setTestValDate (testValDate);
		_eqEquipment.setMeasureSign (measureSign);
		_eqEquipment.setRemark(remark);
		return _eqEquipment;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核未审核的设备台账");
		EqEquipment eq = eqEquipmentDAO.findById(fstrAutoId);
		if (eq == null) {
			throw new RuntimeException("不存在此设备台账[系统标识号：" + fstrAutoId
					+ "],不能审核！");
		}
		if ("1".equals(eq.getCurrentStatus())) {
			throw new RuntimeException("设备台账已审核[系统标识号：" + fstrAutoId
					+ "]，不能重复审核！");
		}
		eq.setCurrentStatus("1");
		eq.setVerifier(SessionUtil.getPersonId());
		eq.setVerifyDate(new Date());
		List<Object> data = new ArrayList<Object>();
		data.add(eq);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject downLoadFile(String fstrFilePath) {
		ReObject ro = new ReObject("根据附件路径，下载指定附件");
		FileInputStream fis = null;
		byte[] bits = null;
		String fileName = EQUIPMENT_FILE_ROOT_DIR + File.separator + fstrFilePath;
		try {
			fis = new FileInputStream(fileName);
			bits = new byte[fis.available()];
			fis.read(bits);
			fis.close();
		} catch (IOException e) {
			ro.setError("下载文件失败");
			return ro;
		}
		List<Object> lstData = new ArrayList<Object>();
		lstData.add(bits);
		ro.setData(lstData);
		return ro;
	}
	
	public ReObject fillPrintData(Map<String,Object> data) {
		ReObject ro=new ReObject();
		FlexContext.getFlexSession().setAttribute("printData", data);
		return ro;
	}

}
