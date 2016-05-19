package cn.superion.equipment.run.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.equipment.common.IEqBillNoService;
import cn.superion.equipment.dao.EqEquipmentDAO;
import cn.superion.equipment.dao.EqTestDAO;
import cn.superion.equipment.dao.EqTestFilesDAO;
import cn.superion.equipment.entity.EqEquipment;
import cn.superion.equipment.entity.EqEquipmentFiles;
import cn.superion.equipment.entity.EqTest;
import cn.superion.equipment.entity.EqTestFiles;
import cn.superion.system.entity.SysUser;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;

public class EqTestImpl implements IEqTest {

	private EqTestDAO eqTestDAO;
	private EqTestFilesDAO eqTestFilesDAO;
	private EqEquipmentDAO  eqEquipmentDAO;
	private IEqBillNoService eqBillNoService;
	private static String EQ_FILES_TEST;
	static {
		EQ_FILES_TEST = ReadPropertiesFile
				.getValue("EQ_FILES_TEST");
		File file = new File(EQ_FILES_TEST);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = null;
	}

	@Override
	public ReObject del(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的记录");
		EqTest master = eqTestDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("不存在此记录[系统标识号："+fstrAutoId+"],不能删除！");
		}
		if("1".equals(master.getStatus())){
			throw new RuntimeException("记录已审核[系统标识号："+fstrAutoId+"]，不能删除！");
		}
		eqTestDAO.delete(master);
		// 删除物理文件
		deleteFiles(fstrAutoId);
		eqTestFilesDAO.delByAutoId(fstrAutoId);
		return ro;
	}

	private void deleteFiles(String autoId) {
		List<EqTestFiles> files = eqTestFilesDAO.findByAutoId(autoId);
		for (EqTestFiles file : files) {
			String fileName = EQ_FILES_TEST + File.separator
					+ file.getFilePath();
			delFile(fileName);
		}
	}
	private void delFile(String fileName) {
		File file = new File(fileName);
		file.delete();
	}
	@Override
	public ReObject downLoadFile(String fstrFilePath) {
		ReObject ro = new ReObject("根据附件路径，下载指定附件");
		FileInputStream fis = null;
		byte[] bits = null;
		String fileName = EQ_FILES_TEST + File.separator + fstrFilePath;
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
	
	@Override
	public ReObject findAutoIdsByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据条件，查询记录ID列表");
		List<Object> data = eqTestDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findByAutoId(String fstrAutoId) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据记录ID，查询记录");
		EqTest master = eqTestDAO.findById(fstrAutoId);
		List<EqTestFiles> eqFiles = eqTestFilesDAO
		.findByAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(eqFiles);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findDetailListByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据条件，查询记录ID列表");
		List<EqTest> data = eqTestDAO.findListByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(EqTest master,
			List<EqTestFiles> equipmentFiles) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("保存维修记录");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String autoId = master.getAutoId();
		if(autoId == null || "".equals(autoId) ){
			//新增
			String billNo = master.getBillNo();
			if(billNo == null || "".equals(billNo)){
				master.setBillNo(eqBillNoService.getNextBillNo("8"));
			}else{
				//校验运行单号唯一
				if(!eqTestDAO.checkBillNoUnique(unitsCode,billNo)){
					throw new RuntimeException("手工输入的运行单号[" + billNo + "]在单位["
							+ unitsCode + "]]下有重复");
				}
			}
			master.setUnitsCode(unitsCode);
			master.setStatus("0");
			master.setMaker(user.getPersonId());
			master.setMakeDate(new Timestamp(new Date().getTime()));
			eqTestDAO.save(master);
			autoId = master.getAutoId();
			if(equipmentFiles!=null){
				saveEquipmentFilesDataAndFile(master.getAutoId(), equipmentFiles);
				
			}
		}else{
			//修改
			EqTest original = eqTestDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在记录[系统标识号："+autoId+"],不能修改！");
			}
			if("1".equals(original.getStatus())){
				throw new RuntimeException("运行记录已审核[系统标识号："+autoId+"]，不能修改！");
			}
			master.setBillNo(original.getBillNo());
			eqTestDAO.merge(master);
			updateEquipmentFilesDataAndFiles(autoId, equipmentFiles);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}
	
	public ReObject uploadFileToTmp(String fstrFileName, byte[] faryFileData) {
		String lstrBaseTmpPath=EQ_FILES_TEST;//ReadPropertiesFile.getValue("UPLOAD_FILE_TEMP_BASE_DIR");
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

	@Override
	public ReObject verify(String fstrAutoId) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("审核未审核的检测单");
		String unitsCode = SessionUtil.getUnitsCode();
		EqTest master = eqTestDAO.findById(fstrAutoId);
		if(master == null){
			throw new RuntimeException("该检测单的[系统标识号："+fstrAutoId+"],不能审核！");
		}
		if("1".equals(master.getStatus())){
			throw new RuntimeException("该检测单已审核[系统标识号："+fstrAutoId+"]，不能重复审核！");
		}
		master.setStatus("1");
		master.setVerifier(SessionUtil.getPersonId());
		master.setVerifyDate(new Timestamp(new Date().getTime()));
		
		//更新对应的设备台账
		EqEquipment ep = eqEquipmentDAO.findByCode(unitsCode,master.getEquipmentCode());
		ep.setTestCertifyNo(master.getTestCertifyNo());
		ep.setTestDate(master.getTestDate());
		String testDateName = DateUtil.getStringFromDate(master.getTestDate());
		ep.setTestDateName(testDateName);
		ep.setTestRes(master.getRes());
		ep.setTestUnit(master.getTestUnit());
		ep.setTestValDate(master.getTestValDate());
		String testValDateName = DateUtil.getStringFromDate(master.getTestValDate());
		ep.setTestValDateName(testValDateName);
		eqEquipmentDAO.merge(ep);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		ro.setData(data);
		return ro;
	}
	
	private void saveEquipmentFilesDataAndFile(String autoId,
			List<EqTestFiles> equipmentFiles) {
		//序号
		short ser = 0;
		for (EqTestFiles efile : equipmentFiles) {
			efile.setAutoId(autoId);
			efile.setSerialNo(++ser);
			if (efile.getData() != null && efile.getData().length > 0) {
				String fileName = efile.getFileName();
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String filePath = DateUtil.getCurrentTimeVersion() + "-"
						+ autoId + "-" + (efile.getFileNo()) + ext;
				try {
					saveFile(efile.getData(), EQ_FILES_TEST
							+ File.separator + filePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//保存完文件后，释放
				efile.setData(null);
				efile.setFilePath(filePath);
			}
			if(efile.isToUpdated()){
				eqTestFilesDAO.merge(efile);
				efile.setToUpdated(false);
			}
			else
				eqTestFilesDAO.save(efile);
		}
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

	
	private void updateEquipmentFilesDataAndFiles(String autoId,
			List<EqTestFiles> equipmentFiles) {
		// 过滤要新增的和要删除的
		List<EqTestFiles> originalFiles = eqTestFilesDAO
				.findByAutoId(autoId);
		// 过滤要删除的数据删除之，并删除相应的物理文件
		for (EqTestFiles originalFile : originalFiles) {
			boolean existFlag = false;
			if (equipmentFiles != null) {
				for (EqTestFiles efile : equipmentFiles) {
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
				String fileName = EQ_FILES_TEST + File.separator
						+ originalFile.getFilePath();
				delFile(fileName);
				eqTestFilesDAO.delete(originalFile);
			}
		}
		// 有附件的新增
		saveEquipmentFilesDataAndFile(autoId, equipmentFiles);
	}

	public EqTestDAO getEqTestDAO() {
		return eqTestDAO;
	}

	public void setEqTestDAO(EqTestDAO eqTestDAO) {
		this.eqTestDAO = eqTestDAO;
	}

	public IEqBillNoService getEqBillNoService() {
		return eqBillNoService;
	}

	public void setEqBillNoService(IEqBillNoService eqBillNoService) {
		this.eqBillNoService = eqBillNoService;
	}


	public EqTestFilesDAO getEqTestFilesDAO() {
		return eqTestFilesDAO;
	}


	public void setEqTestFilesDAO(EqTestFilesDAO eqTestFilesDAO) {
		this.eqTestFilesDAO = eqTestFilesDAO;
	}


	public EqEquipmentDAO getEqEquipmentDAO() {
		return eqEquipmentDAO;
	}


	public void setEqEquipmentDAO(EqEquipmentDAO eqEquipmentDAO) {
		this.eqEquipmentDAO = eqEquipmentDAO;
	}

}
