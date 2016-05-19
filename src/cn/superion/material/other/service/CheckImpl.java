package cn.superion.material.other.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialCheckDetailDAO;
import cn.superion.material.dao.MaterialCheckMasterDAO;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.entity.MaterialCheckDetail;
import cn.superion.material.entity.MaterialCheckMaster;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 盘点服务实现
 * 
 * @author 曹国魁
 * 
 */
public class CheckImpl implements ICheck {
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private MaterialCheckMasterDAO materialCheckMasterDAO;
	private MaterialCheckDetailDAO materialCheckDetailDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService commMaterialServiceImpl;
	
	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}
	
	public MaterialCheckMasterDAO getMaterialCheckMasterDAO() {
		return materialCheckMasterDAO;
	}

	public void setMaterialCheckMasterDAO(
			MaterialCheckMasterDAO materialCheckMasterDAO) {
		this.materialCheckMasterDAO = materialCheckMasterDAO;
	}

	public MaterialCheckDetailDAO getMaterialCheckDetailDAO() {
		return materialCheckDetailDAO;
	}

	public void setMaterialCheckDetailDAO(
			MaterialCheckDetailDAO materialCheckDetailDAO) {
		this.materialCheckDetailDAO = materialCheckDetailDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	@Override
	public ReObject delCheck(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的盘点单");
		MaterialCheckMaster master = materialCheckMasterDAO
				.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("盘点单不存在！");
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("盘点记录已审核，不能删除！");
		}
		materialCheckDetailDAO.delByMainAutoId(fstrAutoId);
		materialCheckMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findCheckDetailList(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前盘点单的详细信息记录");
		MaterialCheckMaster master = materialCheckMasterDAO
				.findById(fstrAutoId);
		List<MaterialCheckDetail> details = materialCheckDetailDAO
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
		List<Object> data = materialCheckMasterDAO.findAutoIdsByCondition(
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCheckMaterial(ParameterObject fparameter) {
		ReObject ro = new ReObject("查找当前需要盘点的物资相关信息");
		Map<String,Object> conditions = fparameter.getConditions();
		String checkZeroSign = (String) conditions.get("checkZeroSign");
		boolean isCheckZero = "1".equals(checkZeroSign);
		String unitsCode = SessionUtil.getUnitsCode();
		List<MaterialCheckDetail> data = new ArrayList<MaterialCheckDetail>(); 
		List<MaterialCurrentStock> stockList = materialCurrentStockDAO.findByCheckCondition(isCheckZero,unitsCode, conditions);
		for(MaterialCurrentStock stock : stockList){
			data.add(buildMaterialCheckDetail(stock));
		}
		if(isCheckZero){
			List<CdMaterialDict> dictList = materialCurrentStockDAO.findMaterialDictByCheckCondition(unitsCode,conditions);
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
	private MaterialCheckDetail buildMaterialCheckDetail(MaterialCurrentStock stock){
		MaterialCheckDetail detail = new MaterialCheckDetail();
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
	private MaterialCheckDetail buildMaterialCheckDetail(CdMaterialDict dict){
		MaterialCheckDetail detail = new MaterialCheckDetail();
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
	public ReObject saveCheck(MaterialCheckMaster fmaster,
			List<MaterialCheckDetail> fdetails) {
		ReObject ro = new ReObject("保存当前盘点单信息");
		if (fmaster == null)
			throw new RuntimeException("物资盘点主记录不能为空！");
		if (fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("物资盘点明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		Date curDate = new Date();
		String storageCode = fmaster.getStorageCode();
		if (storageCode == null || "".equals(storageCode)) {
			storageCode = "0";
			fmaster.setStorageCode(storageCode);
		}
		String autoId = fmaster.getAutoId();
		if (autoId == null || "".equals(autoId)) {
			// 新增
			String billNo = fmaster.getBillNo();
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(commMaterialServiceImpl.getNextBillNo(
						RdConstant.OTHERS, storageCode));
			} else {
				// 新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if (!materialCheckMasterDAO.checkBillNoUnique(unitsCode,
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
			materialCheckMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		} else {
			// 修改
			MaterialCheckMaster original = materialCheckMasterDAO
					.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + autoId + "的物资盘点单主记录！");
			}
			if ("1".equals(original.getCurrentStatus())) {
				throw new RuntimeException("物资盘点单已审核，不能修改！");
			}
			fmaster.setCurrentStatus("0");
			materialCheckMasterDAO.merge(fmaster);
			materialCheckDetailDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for (MaterialCheckDetail detail : fdetails) {
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			materialCheckDetailDAO.save(detail);
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
		MaterialCheckMaster original = materialCheckMasterDAO
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
		List<MaterialCheckDetail> checkDetails = materialCheckDetailDAO
				.findByMainAutoId(fstrAutoId);
		// 分解盘盈入库单和盘亏出库单
		List<MaterialRdsDetail> checkProfitRdsDetails = new ArrayList<MaterialRdsDetail>();
		List<MaterialRdsDetail> checkLossRdsDetails = new ArrayList<MaterialRdsDetail>();
		for (MaterialCheckDetail checkDetail : checkDetails) {
			Double amount = checkDetail.getAmount();
			Double factAmount = checkDetail.getCheckAmount()
					+ (checkDetail.getInAmount() == null ? 0d : checkDetail
							.getInAmount())
					- (checkDetail.getOutAmount() == null ? 0d : checkDetail
							.getOutAmount());
			Double diffAmount = amount - factAmount;
			if (diffAmount < 0) {
				// 盘盈
				MaterialRdsDetail rdsDetail = checkDetail
						.buildMaterialRdsDetail(-diffAmount);
				checkProfitRdsDetails.add(rdsDetail);
			} else if (diffAmount > 0) {
				// 盘亏
				MaterialRdsDetail rdsDetail = checkDetail
						.buildMaterialRdsDetail(diffAmount);
				checkLossRdsDetails.add(rdsDetail);
			}
		}
		if (!checkProfitRdsDetails.isEmpty()) {
			MaterialRdsMaster checkProfitRdsMaster = new MaterialRdsMaster();
			checkProfitRdsMaster.setUnitsCode(original.getUnitsCode());
			checkProfitRdsMaster.setStorageCode(original.getStorageCode());
			// 单据类型
			checkProfitRdsMaster.setInvoiceType("1");
			// 收发标志
			checkProfitRdsMaster.setRdFlag(RdConstant.R);
			// 收发类别
			checkProfitRdsMaster.setRdType(original.getInRdType());
			// 业务类型
			checkProfitRdsMaster.setOperationType(RdConstant.R_CHECK_PROFIT);
			// 业务号
			checkProfitRdsMaster.setOperationNo(original.getBillNo());
			checkProfitRdsMaster.setCurrentStatus(isVerified ? "1" : "0");
			commMaterialServiceImpl.save(checkProfitRdsMaster,
					checkProfitRdsDetails);
		}
		if (!checkLossRdsDetails.isEmpty()) {
			MaterialRdsMaster checkLossRdsMaster = new MaterialRdsMaster();
			checkLossRdsMaster.setUnitsCode(original.getUnitsCode());
			checkLossRdsMaster.setStorageCode(original.getStorageCode());
			// 单据类型
			checkLossRdsMaster.setInvoiceType("1");
			// 收发标志
			checkLossRdsMaster.setRdFlag(RdConstant.D);
			// 收发类别
			checkLossRdsMaster.setRdType(original.getOutRdType());
			// 业务类型
			checkLossRdsMaster.setOperationType(RdConstant.D_CHECK_LOSS);
			// 业务号
			checkLossRdsMaster.setOperationNo(original.getBillNo());
			checkLossRdsMaster.setCurrentStatus(isVerified ? "1" : "0");
			commMaterialServiceImpl.save(checkLossRdsMaster,
					checkLossRdsDetails);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

}
