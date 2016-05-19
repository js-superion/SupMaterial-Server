package cn.superion.cssd.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.cssd.dao.CssdSterilizeMasterDAO;
import cn.superion.cssd.dao.CssdStockMasterDAO;
import cn.superion.cssd.entity.CssdSterilizeMaster;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 物品灭菌登记服务实现
 * 
 * @author 曹国魁
 * 
 */
public class SterilizeImpl implements ISterilize {
	private static final String PARA_CODE_AUTO_CHK = "0204";
	// private Log log = LogFactory.getLog(SterilizeImpl.class);
	private CssdSterilizeMasterDAO cssdSterilizeMasterDAO;
	private CssdStockMasterDAO cssdStockMasterDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;
	private CdSysParamDAO cdSysParamDAO;

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public CssdSterilizeMasterDAO getCssdSterilizeMasterDAO() {
		return cssdSterilizeMasterDAO;
	}

	public void setCssdSterilizeMasterDAO(
			CssdSterilizeMasterDAO cssdSterilizeMasterDAO) {
		this.cssdSterilizeMasterDAO = cssdSterilizeMasterDAO;
	}

	public CssdStockMasterDAO getCssdStockMasterDAO() {
		return cssdStockMasterDAO;
	}

	public void setCssdStockMasterDAO(CssdStockMasterDAO cssdStockMasterDAO) {
		this.cssdStockMasterDAO = cssdStockMasterDAO;
	}

	public ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	@Override
	public ReObject deleteById(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的物品灭菌单据");
		CssdSterilizeMaster master = cssdSterilizeMasterDAO
				.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("物品灭菌单不存在！");
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("物品灭菌记录已审核，不能删除！");
		}
		String unitsCode = master.getUnitsCode();
		List<CssdStockMaster> pgkList = cssdStockMasterDAO
				.findBySterilizeAutoId(unitsCode, fstrAutoId);
		for (CssdStockMaster pkg : pgkList) {
			String packageNo = pkg.getPackageNo();
			// 检查物品包状态为灭菌状态
			// 检查原物品包合格的，且状态不为灭菌的
			if ("1".equals(pkg.getSterilizeStatus())
					&& "0".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]还未灭菌入库，不能删除灭菌记录！");
			}
			if ("2".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]已发放出库，不能删除灭菌记录！");
			}
			if ("3".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]已回收，不能删除灭菌记录！");
			}
			if ("1".equals(pkg.getSterilizeStatus())
					&& !"1".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]不是灭菌状态，不能删除灭菌记录！");
			}
			pkg.setCurrentStatus("0");
			pkg.setSterilizeAutoId(null);
			pkg.setSterilizeSerialNo(null);
			pkg.setSterilizeStatus("0");
		}
		cssdSterilizeMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前物品灭菌登记的详细信息记录");
		CssdSterilizeMaster master = cssdSterilizeMasterDAO
				.findById(fstrAutoId);
		List<CssdStockMaster> details = null;
		if (master != null) {
			details = cssdStockMasterDAO.findBySterilizeAutoId(master
					.getUnitsCode(), fstrAutoId);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findMasterIdListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物品灭菌列表");
		List<Object> data = cssdSterilizeMasterDAO.findAutoIdsByCondition(
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(CssdSterilizeMaster fmaster,
			List<CssdStockMaster> flstStockMaster) {
		ReObject ro = new ReObject("保存当前物品灭菌信息");
		if (fmaster == null)
			throw new RuntimeException("物品灭菌主记录不能为空！");
		if (flstStockMaster == null || flstStockMaster.isEmpty())
			throw new RuntimeException("物品包记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String appCode = user.getAppCode();
		Date curDate = new Date();
		// 是否自动审核
		boolean isAutoChk = cdSysParamDAO.findByParaCode(unitsCode, appCode,
				PARA_CODE_AUTO_CHK, "0").equals("1");
		String autoId = fmaster.getAutoId();
		boolean isAdd = autoId == null || "".equals(autoId);
		if (isAdd) {
			// 新增
			String billNo = fmaster.getBillNo();
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(deptCommMaterialServiceImpl
						.getNextBillNo(RdConstant.OTHERS));
			} else {
				// 新增时，校验手工输入的流水号在一个单位中唯一性
				if (!cssdSterilizeMasterDAO
						.checkBillNoUnique(unitsCode, billNo)) {
					throw new RuntimeException("手工输入的单据编号[" + billNo + "]在单位["
							+ unitsCode + "]下有重复");
				}
			}
			if (fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			if (isAutoChk) {
				fmaster.setCurrentStatus("1");
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			} else {
				fmaster.setCurrentStatus("0");
			}
			cssdSterilizeMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		} else {
			// 修改
			CssdSterilizeMaster original = cssdSterilizeMasterDAO
					.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + autoId + "的物品灭菌主记录！");
			}
			if ("1".equals(original.getCurrentStatus())) {
				throw new RuntimeException("物品灭菌单已审核，不能修改！");
			}
			if (isAutoChk) {
				fmaster.setCurrentStatus("1");
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			} else {
				fmaster.setCurrentStatus("0");
			}
			cssdSterilizeMasterDAO.merge(fmaster);

			List<CssdStockMaster> originalDetails = cssdStockMasterDAO
					.findBySterilizeAutoId(unitsCode, autoId);
			for (CssdStockMaster pkg : originalDetails) {
				// String packageNo = pkg.getPackageNo();
				// 检查原物品包合格的，且状态不为灭菌的
				/*
				 * if("1".equals(pkg.getSterilizeStatus()) &&
				 * !"1".equals(pkg.getCurrentStatus())){
				 * log.warn("要更新的物品灭菌记录的物品包[编号："
				 * +packageNo+"]状态["+pkg.getCurrentStatus()+"]不对，应为1"); }
				 */
				if (!flstStockMaster.contains(pkg)) {
					// 对要删除的灭菌包清除灭菌主记录ID和序号，以及还原状态
					pkg.setSterilizeAutoId(null);
					pkg.setSterilizeSerialNo(null);
					pkg.setSterilizeStatus("0");
					pkg.setCurrentStatus("0");
				}
			}
		}
		short i = 0;
		for (CssdStockMaster stockMaster : flstStockMaster) {
			String packageNo = stockMaster.getPackageNo();
			CssdStockMaster originalStockMaster = cssdStockMasterDAO
					.findById(packageNo);
			String curStatus = originalStockMaster.getCurrentStatus();
			String sterAutoId = originalStockMaster.getSterilizeAutoId();

			if (originalStockMaster == null) {
				throw new RuntimeException("要灭菌的物品包在系统中不存在");
			}
			if (isAdd
					|| (!isAdd && (sterAutoId == null || "".equals(sterAutoId)))) {
				// 校验灭菌新增操作时的物品包原状态应为0
				// 校验修改操作时的物品包原状态:灭菌包是否是打包入库包，原状态应为0
				if ("1".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已灭菌入库，不能新增灭菌记录！");
				}
				if ("2".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已发放出库，不能新增灭菌记录！");
				}
				if ("3".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已回收，不能新增灭菌记录！");
				}
				if (!"0".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]不是打包状态，不能新增灭菌记录！");
				}
			} else {
				String sterStatus = originalStockMaster.getSterilizeStatus();
				// 灭菌包是否是灭菌入库包，不合格的包原状态应为0，合格的包原状态应为1
				if ("1".equals(sterStatus) && "0".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]还未灭菌入库，不能修改灭菌记录！");
				}
				if ("2".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已发放出库，不能修改灭菌记录！");
				}
				if ("3".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已回收，不能修改灭菌记录！");
				}
				if ("1".equals(sterStatus) && !"1".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]不是灭菌状态，不能修改灭菌记录！");
				}
			}
			originalStockMaster.setSterilizeAutoId(autoId);
			originalStockMaster.setSterilizeSerialNo(++i);
			originalStockMaster.setSterilizeStatus(stockMaster
					.getSterilizeStatus());
			originalStockMaster.setSterilizeNo(stockMaster.getSterilizeNo()); //将灭菌锅次、锅号、放到灭菌这里更新
			originalStockMaster.setSterilizeOrder(stockMaster.getSterilizeOrder());
			// 灭菌不合格的当前状态仍为0- 打包入库状态
			originalStockMaster.setCurrentStatus("1".equals(stockMaster
					.getSterilizeStatus()) ? "1" : "0");
			originalStockMaster.setDetailRemark(stockMaster.getDetailRemark());
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(flstStockMaster);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的物品灭菌信息");
		CssdSterilizeMaster original = cssdSterilizeMasterDAO
				.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物品灭菌单！");
		}
		if ("1".equals(original.getCurrentStatus())) {
			throw new RuntimeException("物品灭菌单已审核，不能重复审核！");
		}
		original.setCurrentStatus("1");
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findStockMasterByPackageNo(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据物品包编号或拼音码，五笔码查询已打包的物品包信息");
		String packageNo = (String) fparameter.getConditions().get("packageNo");
		String phoInputCode = (String) fparameter.getConditions().get(
				"phoInputCode");
		String fiveInputCode = (String) fparameter.getConditions().get(
				"fiveInputCode");
		List<CssdStockMaster> data = null;
		if (packageNo != null && !"".equals(packageNo)) {
			CssdStockMaster stockMaster = cssdStockMasterDAO.findPackedById(
					SessionUtil.getUnitsCode(), packageNo);
			if (stockMaster != null) {
				data = new ArrayList<CssdStockMaster>();
				data.add(stockMaster);
			}
		} else if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdStockMasterDAO.findPackedByInputCode(SessionUtil
					.getUnitsCode(), phoInputCode, true);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdStockMasterDAO.findPackedByInputCode(SessionUtil
					.getUnitsCode(), fiveInputCode, false);
		}
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findPackedByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件，过滤已打包审核的物品包列表");
		int start = 0;
		int limit = 20000000;
		Object[] objs = cssdStockMasterDAO.findPackedByCondition(start, limit,
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findPackedByInputCode(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据物品包编号或拼音码，五笔码查询已打包的物品包信息");
		String phoInputCode = (String) fparameter.getConditions().get(
				"phoInputCode");
		String fiveInputCode = (String) fparameter.getConditions().get(
				"fiveInputCode");
		List<CssdStockMaster> data = null;
		if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = cssdStockMasterDAO.findPackedByInputCode(SessionUtil
					.getUnitsCode(), phoInputCode, true);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = cssdStockMasterDAO.findPackedByInputCode(SessionUtil
					.getUnitsCode(), fiveInputCode, false);
		}
		ro.setData(data);
		return ro;
	}

}
