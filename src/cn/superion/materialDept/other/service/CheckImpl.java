package cn.superion.materialDept.other.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.dao.MaterialCheckDetailDeptDAO;
import cn.superion.materialDept.dao.MaterialCheckMasterDeptDAO;
import cn.superion.materialDept.dao.MaterialCurrentStockDeptDAO;
import cn.superion.materialDept.entity.MaterialCheckDetailDept;
import cn.superion.materialDept.entity.MaterialCheckMasterDept;
import cn.superion.materialDept.entity.MaterialCurrentStockDept;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 盘点服务实现
 * 
 * @author 曹国魁
 * 
 */
public class CheckImpl implements ICheck {
	private MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO;
	private MaterialCheckMasterDeptDAO materialCheckMasterDeptDAO;
	private MaterialCheckDetailDeptDAO materialCheckDetailDeptDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;
	
	public MaterialCurrentStockDeptDAO getMaterialCurrentStockDeptDAO() {
		return materialCurrentStockDeptDAO;
	}

	public void setMaterialCurrentStockDeptDAO(
			MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO) {
		this.materialCurrentStockDeptDAO = materialCurrentStockDeptDAO;
	}

	public MaterialCheckMasterDeptDAO getMaterialCheckMasterDeptDAO() {
		return materialCheckMasterDeptDAO;
	}

	public void setMaterialCheckMasterDeptDAO(
			MaterialCheckMasterDeptDAO materialCheckMasterDeptDAO) {
		this.materialCheckMasterDeptDAO = materialCheckMasterDeptDAO;
	}

	public MaterialCheckDetailDeptDAO getMaterialCheckDetailDeptDAO() {
		return materialCheckDetailDeptDAO;
	}

	public void setMaterialCheckDetailDeptDAO(
			MaterialCheckDetailDeptDAO materialCheckDetailDeptDAO) {
		this.materialCheckDetailDeptDAO = materialCheckDetailDeptDAO;
	}

	public ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}


	@Override
	public ReObject delCheck(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的盘点单");
		MaterialCheckMasterDept master = materialCheckMasterDeptDAO
				.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("盘点单不存在！");
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("盘点记录已审核，不能删除！");
		}
		materialCheckDetailDeptDAO.delByMainAutoId(fstrAutoId);
		materialCheckMasterDeptDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findCheckDetailList(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前盘点单的详细信息记录");
		MaterialCheckMasterDept master = materialCheckMasterDeptDAO
				.findById(fstrAutoId);
		List<MaterialCheckDetailDept> details = materialCheckDetailDeptDAO
				.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCheckMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的盘点单据列表");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<Object> data = materialCheckMasterDeptDAO.findAutoIdsByCondition(
				user.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCheckMaterial(ParameterObject fparameter) {
		ReObject ro = new ReObject("查找当前需要盘点的物资相关信息");
		Map<String,Object> conditions = fparameter.getConditions();
		String checkZeroSign = (String) conditions.get("checkZeroSign");
		boolean isCheckZero = "1".equals(checkZeroSign);
		SysUser user = SessionUtil.getSysUser();
		conditions.put("storageCode", user.getDeptCode());
		String unitsCode = user.getUnitsCode();
		List<MaterialCheckDetailDept> data = new ArrayList<MaterialCheckDetailDept>(); 
		List<MaterialCurrentStockDept> stockList = materialCurrentStockDeptDAO.findByCheckCondition(isCheckZero,unitsCode, conditions);
		for(MaterialCurrentStockDept stock : stockList){
			data.add(buildMaterialCheckDetail(stock));
		}
		if(isCheckZero){
			List<CdMaterialDict> dictList = materialCurrentStockDeptDAO.findMaterialDictByCheckCondition(unitsCode,conditions);
			for(CdMaterialDict dict : dictList){
				data.add(buildMaterialCheckDetail(dict));
			}
		}
		ro.setData(data);
		return ro;
	}
	
	/**
	 * 根据库存记录构造盘点明细记录
	 * @param stock
	 * @return
	 */
	private MaterialCheckDetailDept buildMaterialCheckDetail(MaterialCurrentStockDept stock){
		MaterialCheckDetailDept detail = new MaterialCheckDetailDept();
		detail.setMaterialClass(stock.getMaterialClass());
		detail.setBarCode(stock.getBarCode());
		detail.setMaterialId(stock.getMaterialId());
		detail.setMaterialCode(stock.getMaterialCode());
		detail.setMaterialName(stock.getMaterialName());
		detail.setMaterialSpec(stock.getMaterialSpec());
		detail.setMaterialUnits(stock.getMaterialUnits());
		detail.setAmount(stock.getAmount());
		if(stock.getTradePrice() != null){
			detail.setTradePrice(stock.getTradePrice());
			detail.setTradeMoney(stock.getTradePrice()*stock.getAmount());
		}
		detail.setFactoryCode(stock.getFactoryCode());
		detail.setMadeDate(stock.getMadeDate());
		detail.setBatch(stock.getBatch());
		detail.setAvailDate(stock.getAvailDate());
		detail.setPosition(stock.getPosition());
		return detail;
	}

	/**
	 * 构造零库存盘点明细记录
	 * @param dict
	 * @return
	 */
	private MaterialCheckDetailDept buildMaterialCheckDetail(CdMaterialDict dict){
		MaterialCheckDetailDept detail = new MaterialCheckDetailDept();
		detail.setMaterialClass(dict.getMaterialClass());
		detail.setMaterialId(dict.getMaterialId());
		detail.setMaterialCode(dict.getMaterialCode());
		detail.setMaterialName(dict.getMaterialName());
		detail.setMaterialSpec(dict.getMaterialSpec());
		detail.setMaterialUnits(dict.getMaterialUnits());
		detail.setAmount(0d);
		detail.setTradePrice(dict.getTradePrice());
		detail.setTradeMoney(0d);
		detail.setFactoryCode(dict.getFactoryCode());
		return detail;
	}
	
	@Override
	public ReObject saveCheck(MaterialCheckMasterDept fmaster,
			List<MaterialCheckDetailDept> fdetails) {
		ReObject ro = new ReObject("保存当前盘点单信息");
		if (fmaster == null)
			throw new RuntimeException("物资盘点主记录不能为空！");
		if (fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("物资盘点明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		Date curDate = new Date();
		String storageCode = user.getDeptCode();
		if (storageCode == null || "".equals(storageCode)) {
			storageCode = "0";
		}
		fmaster.setStorageCode(storageCode);
		String autoId = fmaster.getAutoId();
		if (autoId == null || "".equals(autoId)) {
			// 新增
			String billNo = fmaster.getBillNo();
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(deptCommMaterialServiceImpl.getNextBillNo(
						RdConstant.OTHERS));
			} else {
				// 新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if (!materialCheckMasterDeptDAO.checkBillNoUnique(unitsCode,
						storageCode, billNo)) {
					throw new RuntimeException("手工输入的单据编号[" + billNo + "]在单位["
							+ unitsCode + "],仓库[" + storageCode + "]下有重复");
				}
			}
			if (fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			if (fmaster.getPersonId() == null
					|| "".equals(fmaster.getPersonId()))
				fmaster.setPersonId(personId);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			fmaster.setCurrentStatus("0");
			materialCheckMasterDeptDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		} else {
			// 修改
			MaterialCheckMasterDept original = materialCheckMasterDeptDAO
					.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + autoId + "的物资盘点单主记录！");
			}
			if ("1".equals(original.getCurrentStatus())) {
				throw new RuntimeException("物资盘点单已审核，不能修改！");
			}
			fmaster.setCurrentStatus("0");
			materialCheckMasterDeptDAO.merge(fmaster);
			materialCheckDetailDeptDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for (MaterialCheckDetailDept detail : fdetails) {
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			materialCheckDetailDeptDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verifyCheck(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的物资盘点单");
		MaterialCheckMasterDept original = materialCheckMasterDeptDAO
				.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物资盘点单！");
		}
		if ("1".equals(original.getCurrentStatus())) {
			throw new RuntimeException("物资盘点单已审核，不能审核！");
		}
		original.setCurrentStatus("1");
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		String appCode = SessionUtil.getAppCode();
		// 写出库单
		// 是否要更新库存
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(original
				.getUnitsCode(), appCode,
				RdConstant.SYS_PARA_CODE_CHECK_LAUNCH, "0"));
		List<MaterialCheckDetailDept> checkDetails = materialCheckDetailDeptDAO
				.findByMainAutoId(fstrAutoId);
		// 分解盘盈入库单和盘亏出库单
		List<MaterialRdsDetailDept> checkProfitRdsDetails = new ArrayList<MaterialRdsDetailDept>();
		List<MaterialRdsDetailDept> checkLossRdsDetails = new ArrayList<MaterialRdsDetailDept>();
		for (MaterialCheckDetailDept checkDetail : checkDetails) {
			Double amount = checkDetail.getAmount();
			Double factAmount = checkDetail.getCheckAmount()
					+ (checkDetail.getInAmount() == null ? 0d : checkDetail
							.getInAmount())
					- (checkDetail.getOutAmount() == null ? 0d : checkDetail
							.getOutAmount());
			Double diffAmount = amount - factAmount;
			if (diffAmount < 0) {
				// 盘盈
				MaterialRdsDetailDept rdsDetail = checkDetail
						.buildMaterialRdsDetail(-diffAmount);
				checkProfitRdsDetails.add(rdsDetail);
			} else if (diffAmount > 0) {
				// 盘亏
				MaterialRdsDetailDept rdsDetail = checkDetail
						.buildMaterialRdsDetail(diffAmount);
				checkLossRdsDetails.add(rdsDetail);
			}
		}
		if (!checkProfitRdsDetails.isEmpty()) {
			MaterialRdsMasterDept checkProfitRdsMaster = new MaterialRdsMasterDept();
			checkProfitRdsMaster.setUnitsCode(original.getUnitsCode());
			checkProfitRdsMaster.setStorageCode(original.getStorageCode());
			// 单据类型
			checkProfitRdsMaster.setInvoiceType("1");
			// 收发标志
			checkProfitRdsMaster.setRdFlag(RdConstant.R);
			// 收发类别
			String rdType = original.getInRdType();
			checkProfitRdsMaster.setRdType(rdType == null || "".equals(rdType) ? "102" : rdType);
			// 业务类型
			checkProfitRdsMaster.setOperationType(RdConstant.R_CHECK_PROFIT);
			// 业务号
			checkProfitRdsMaster.setOperationNo(original.getBillNo());
			checkProfitRdsMaster.setCurrentStatus(isVerified ? "1" : "0");
			deptCommMaterialServiceImpl.save(checkProfitRdsMaster,
					checkProfitRdsDetails);
		}
		if (!checkLossRdsDetails.isEmpty()) {
			MaterialRdsMasterDept checkLossRdsMaster = new MaterialRdsMasterDept();
			checkLossRdsMaster.setUnitsCode(original.getUnitsCode());
			checkLossRdsMaster.setStorageCode(original.getStorageCode());
			// 单据类型
			checkLossRdsMaster.setInvoiceType("1");
			// 收发标志
			checkLossRdsMaster.setRdFlag(RdConstant.D);
			// 收发类别
			String rdType = original.getOutRdType();
			checkLossRdsMaster.setRdType(rdType == null || "".equals(rdType) ? "202" : rdType);
			// 业务类型
			checkLossRdsMaster.setOperationType(RdConstant.D_CHECK_LOSS);
			// 业务号
			checkLossRdsMaster.setOperationNo(original.getBillNo());
			checkLossRdsMaster.setCurrentStatus(isVerified ? "1" : "0");
			deptCommMaterialServiceImpl.save(checkLossRdsMaster,
					checkLossRdsDetails);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

}
