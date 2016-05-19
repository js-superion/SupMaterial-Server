package cn.superion.material.common;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.config.entity.CdStorageRd;
import cn.superion.material.dao.MaterialCurrentRcptNoDAO;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialCurrentRcptNo;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.BaseToolDAO;
import cn.superion.util.SessionUtil;
import cn.superion.util.StringUtil;

/**
 * 物资公共服务实现
 * 
 * @author 曹国魁
 * 
 */
public class CommMaterialServiceImpl implements ICommMaterialService {
	private CdSysParamDAO cdSysParamDAO;
	private MaterialCurrentRcptNoDAO materialCurrentRcptNoDAO;
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private BaseToolDAO toolDAO;

	public BaseToolDAO getToolDAO() {
		return toolDAO;
	}

	public void setToolDAO(BaseToolDAO toolDAO) {
		this.toolDAO = toolDAO;
	}

	public static final String ERROR_MSG_ZERO_DELIVER = "物资库[%S]存量不足，不允许负库存，请重新操作或修改系统参数允许负库存！";

	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}

	public MaterialRdsMasterDAO getMaterialRdsMasterDAO() {
		return materialRdsMasterDAO;
	}

	public void setMaterialRdsMasterDAO(
			MaterialRdsMasterDAO materialRdsMasterDAO) {
		this.materialRdsMasterDAO = materialRdsMasterDAO;
	}

	public MaterialRdsDetailDAO getMaterialRdsDetailDAO() {
		return materialRdsDetailDAO;
	}

	public void setMaterialRdsDetailDAO(
			MaterialRdsDetailDAO materialRdsDetailDAO) {
		this.materialRdsDetailDAO = materialRdsDetailDAO;
	}

	public MaterialCurrentRcptNoDAO getMaterialCurrentRcptNoDAO() {
		return materialCurrentRcptNoDAO;
	}

	public void setMaterialCurrentRcptNoDAO(
			MaterialCurrentRcptNoDAO materialCurrentRcptNoDAO) {
		this.materialCurrentRcptNoDAO = materialCurrentRcptNoDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	@Override
	public MaterialRdsMaster deleteByAutoId(String fstrAutoId) {
		MaterialRdsMaster master = materialRdsMasterDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的收发存主记录！");
		}
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入出库已审核，不能删除！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入出库已记帐，不能删除！");
		}
		materialRdsDetailDAO.deleteByMainAutoId(fstrAutoId);
		materialRdsMasterDAO.delete(master);
		return master;
	}
	
	@Override
	public void updatePrintSign(String fstrAutoId) {
		materialRdsMasterDAO.updatePrintSignByAutoId(fstrAutoId);
	}

	@Override
	public ReObject findCurrentStockByFactoryBatch(ParameterObject fparameter) {
		ReObject ro = new ReObject("查找当前现存量的数据列表");
		String storageCode = (String) fparameter.getConditions().get(
				"storageCode");
		if (storageCode == null || "".equals(storageCode.trim())) {
			throw new RuntimeException("仓库编码不能为空！");
		}
		List<MaterialCurrentStock> data = materialCurrentStockDAO
				.findByCondition(SessionUtil.getUnitsCode(), fparameter
						.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	@Deprecated
	public ReObject findCurrentStockByIdStorage(String fstrMaterialId,
			String fstrStorageCode) {
		ReObject ro = new ReObject("查询物资当前现存量");
		String unitsCode = SessionUtil.getUnitsCode();
		Double currentAmount = null;
		boolean isAll = fstrStorageCode == null || "".equals(fstrStorageCode)
				|| "0".equals(fstrStorageCode);
		if (!isAll) {
			currentAmount = materialCurrentStockDAO.findAmount(unitsCode,
					fstrStorageCode, fstrMaterialId);
		}
		Double totalCurrentAmount = materialCurrentStockDAO.findAmount(
				unitsCode, fstrMaterialId);
		Map<String, Double> map = new HashMap<String, Double>();
		if (!isAll) {
			map.put("currentStockAmount", currentAmount);
		}
		map.put("totalCurrentStockAmount", totalCurrentAmount);
		List<Map<String, Double>> data = new ArrayList<Map<String, Double>>();
		data.add(map);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCurrentStockById(String fstrMaterialId,
			String fstrStorageCode) {
		ReObject ro = new ReObject("查询物资当前现存量");
		String unitsCode = SessionUtil.getUnitsCode();
		Double currentAmount = null;
		boolean isAll = fstrStorageCode == null || "".equals(fstrStorageCode)
				|| "0".equals(fstrStorageCode);
		if (!isAll) {
			currentAmount = materialCurrentStockDAO.findAmount(unitsCode,
					fstrStorageCode, fstrMaterialId);
		}
		Double totalCurrentAmount = materialCurrentStockDAO.findAmount(
				unitsCode, fstrMaterialId);
		Map<String, Double> map = new HashMap<String, Double>();
		if (!isAll) {
			map.put("currentStockAmount", currentAmount);
		}
		map.put("totalCurrentStockAmount", totalCurrentAmount);
		List<Map<String, Double>> data = new ArrayList<Map<String, Double>>();
		data.add(map);
		ro.setData(data);
		return ro;
	}

	@Override
	public String getNextBillNo(String rdFlag, String storageCode) {
		if (storageCode == null || "".equals(storageCode))
			storageCode = "0";
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		// 应用程序编号 888
		String appNo = user.getAppCode();
		String rcptType = cdSysParamDAO.findByParaCode(unitsCode, appNo,
				RdConstant.SYS_PARA_CODE_BILL_NO, "1");
		String nowTypeDate = BillNoGenerator.getNowTypeDate(rcptType);
		MaterialCurrentRcptNo rcptNo = materialCurrentRcptNoDAO
				.findByStorageCodeMonth(unitsCode, rdFlag, rcptType, storageCode,
						nowTypeDate,"2");
		String curTypeDate = null;
		String curNo = null;
		if (rcptNo != null) {
			curTypeDate = rcptNo.getTypeDate();
			curNo = rcptNo.getCurrentNo();
		}
		String nextNo = null;
		try {
			nextNo = BillNoGenerator.getNextNo(rcptType, curTypeDate, curNo,
					nowTypeDate);
		} catch (Exception e) {
			throw new RuntimeException("生成下一个流水号错误", e);
		}
		// 流水号数据库中不存在，则新增
		if (rcptNo == null) {
			rcptNo = new MaterialCurrentRcptNo(unitsCode, rdFlag, rcptType,
					storageCode, nowTypeDate, nextNo,"2");
			materialCurrentRcptNoDAO.save(rcptNo);
		} else {
			rcptNo.setTypeDate(nowTypeDate);
			rcptNo.setCurrentNo(nextNo);
			materialCurrentRcptNoDAO.flush();
		}
//		if(rdType.equals("209")){
//			return "Q"+nowTypeDate + nextNo;
//		}else if(rdType.equals("201")){
//			return "L"+nowTypeDate + nextNo;
//		}else if(rdType.equals("210")){
//			return "C"+nowTypeDate + nextNo;
//		}else if(rdType.equals("110")){
//			return "Z"+nowTypeDate + nextNo;
//		}else if(rdType.equals("101")){
//			return "R"+nowTypeDate + nextNo;
//		}else{
			return nowTypeDate + nextNo;
//		}
	}
	@Override
	public String getNextBillNo(String rdFlag,String rdType, String storageCode) {
		if (storageCode == null || "".equals(storageCode))
			storageCode = "0";
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		// 应用程序编号 888
		String appNo = user.getAppCode();
		String rcptType = cdSysParamDAO.findByParaCode(unitsCode, appNo,
				RdConstant.SYS_PARA_CODE_BILL_NO, "1");
		String nowTypeDate = BillNoGenerator.getNowTypeDate(rcptType);
		MaterialCurrentRcptNo rcptNo = materialCurrentRcptNoDAO
				.findByStorageCodeMonth(unitsCode, rdFlag, rcptType, storageCode,
						nowTypeDate,rdType);
		String curTypeDate = null;
		String curNo = null;
		if (rcptNo != null) {
			curTypeDate = rcptNo.getTypeDate();
			curNo = rcptNo.getCurrentNo();
		}
		String nextNo = null;
		try {
			nextNo = BillNoGenerator.getNextNo(rcptType, curTypeDate, curNo,
					nowTypeDate);
		} catch (Exception e) {
			throw new RuntimeException("生成下一个流水号错误", e);
		}
		// 流水号数据库中不存在，则新增
		if (rcptNo == null) {
			rcptNo = new MaterialCurrentRcptNo(unitsCode, rdFlag, rcptType,
					storageCode, nowTypeDate, nextNo,rdType);
			materialCurrentRcptNoDAO.save(rcptNo);
		} else {
			rcptNo.setTypeDate(nowTypeDate);
			rcptNo.setCurrentNo(nextNo);
			materialCurrentRcptNoDAO.flush();
		}
		if(rdType.equals("209")){
			if(storageCode.equals("101")){
				return "Q"+nowTypeDate + nextNo;
			}else{
				return "P"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("201")){
			if(storageCode.equals("101")){
				return "L"+nowTypeDate + nextNo;
			}else{
				return "M"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("210")){
			if(storageCode.equals("101")){
				return "C"+nowTypeDate + nextNo;
			}else{
				return "D"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("110")){
			if(storageCode.equals("101")){
				return "Z"+nowTypeDate + nextNo;
			}else{
				return "Y"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("101")){
			if(storageCode.equals("101")){
				return "R"+nowTypeDate + nextNo;
			}else{
				return "S"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("109")){
			if(storageCode.equals("101")){
				return "T"+nowTypeDate + nextNo;
			}else{
				return "E"+nowTypeDate + nextNo;
			}
			
		}else{
			return nowTypeDate + nextNo;
		}
	}
	
	@Override
	public String getNextBillMonthNo(String rdFlag,String rdType, String storageCode) {
		if (storageCode == null || "".equals(storageCode))
			storageCode = "0";
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		// 应用程序编号 888
		String appNo = user.getAppCode();
		String rcptType = cdSysParamDAO.findByParaCode(unitsCode, appNo,
				"0102", "1");
		String nowTypeDate = BillNoGenerator.getNowTypeDate(rcptType);
		MaterialCurrentRcptNo rcptNo = materialCurrentRcptNoDAO
				.findByStorageCodeMonth(unitsCode, rdFlag, rcptType, storageCode,
						nowTypeDate,rdType);
		String curTypeDate = null;
		String curNo = null;
		if (rcptNo != null) {
			curTypeDate = rcptNo.getTypeDate();
			curNo = rcptNo.getCurrentNo();
		}
		String nextNo = null;
		try {
			
			nextNo = BillNoGenerator.getNextNo(rcptType, curTypeDate, curNo,
					nowTypeDate);
		} catch (Exception e) {
			throw new RuntimeException("生成下一个流水号错误", e);
		}
		// 流水号数据库中不存在，则新增
		if (rcptNo == null) {
			rcptNo = new MaterialCurrentRcptNo(unitsCode, rdFlag, rcptType,
					storageCode, nowTypeDate, nextNo,rdType);
			materialCurrentRcptNoDAO.save(rcptNo);
		} else {
			System.out.println("nowTypeDate:"+nowTypeDate+" nextNo:"+nextNo);
			rcptNo.setTypeDate(nowTypeDate);
			rcptNo.setCurrentNo(nextNo);
			materialCurrentRcptNoDAO.flush();
		}
//		if(rdType.equals("209")){
//			return "Q"+nowTypeDate + nextNo;
//		}else if(rdType.equals("201")){
//			return "L"+nowTypeDate + nextNo;
//		}else if(rdType.equals("210")){
//			return "C"+nowTypeDate + nextNo;
//		}else if(rdType.equals("110")){
//			return "Z"+nowTypeDate + nextNo;
//		}else if(rdType.equals("101")){
//			return "R"+nowTypeDate + nextNo;
//		}else if(rdType.equals("109")){
//			return "T"+nowTypeDate + nextNo;
//		}else{
//			return nowTypeDate + nextNo;
//		}
		if(rdType.equals("209")){
			if(storageCode.equals("101")){
				return "Q"+nowTypeDate + nextNo;
			}else{
				return "P"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("201")){
			if(storageCode.equals("101")){
				return "L"+nowTypeDate + nextNo;
			}else{
				return "M"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("210")){
			if(storageCode.equals("101")){
				return "C"+nowTypeDate + nextNo;
			}else{
				return "D"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("110")){
			if(storageCode.equals("101")){
				return "Z"+nowTypeDate + nextNo;
			}else{
				return "Y"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("101")){
			if(storageCode.equals("101")){
				return "R"+nowTypeDate + nextNo;
			}else{
				return "S"+nowTypeDate + nextNo;
			}
		}else if(rdType.equals("109")){
			if(storageCode.equals("101")){
				return "T"+nowTypeDate + nextNo;
			}else{
				return "E"+nowTypeDate + nextNo;
			}
			
		}else{
			return nowTypeDate + nextNo;
		}
	}

	@Override
	public Object[] save(MaterialRdsMaster fmaster,
			List<MaterialRdsDetail> fdetails) {
		if (fmaster == null) {
			throw new RuntimeException("收发存主记录不能为空！");
		}
		if (fdetails == null || fdetails.isEmpty()) {
			throw new RuntimeException("收发存明细记录不能为空！");
		}
		if (fmaster.getInvoiceType() == null
				|| "".equals(fmaster.getInvoiceType())) {
			throw new RuntimeException("单据类型不能为空！");
		}
		if (fmaster.getRdFlag() == null || "".equals(fmaster.getRdFlag())) {
			throw new RuntimeException("收发标志不能为空！");
		}
		/*
		 * if(fmaster.getRdType() == null || "".equals(fmaster.getRdType())){
		 * throw new RuntimeException("收发类别不能为空！"); }
		 */
		if (fmaster.getOperationType() == null
				|| "".equals(fmaster.getOperationType())) {
			throw new RuntimeException("业务类型不能为空！");
		}
		Object[] objs = new Object[2];
		SysUser user = SessionUtil.getSysUser();
		String personId = user.getPersonId();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		Date curDate = new Date();
		String storageCode = fmaster.getStorageCode();
		// 判断新增或修改状态
		boolean isAdd = true;
		// String operationNo = fmaster.getOperationNo();
		String mainAutoId = fmaster.getAutoId();
		isAdd = mainAutoId == null || "".equals(mainAutoId);
		/*
		 * if (operationNo == null || "".equals(operationNo)) { //
		 * 业务号为null,则根据收发存主记录ID判断 isAdd = mainAutoId == null ||
		 * "".equals(mainAutoId); } else { //
		 * 否则根据业务号和业务类型到收发存主记录表中查询判断（同一个业务类型下业务号可能有重复，如同一个采购订单有多个入库单）
		 * MaterialRdsMaster original =
		 * materialRdsMasterDAO.findByOperationNo(unitsCode
		 * ,storageCode,operationNo,fmaster.getOperationType()); isAdd =
		 * original == null; if (!isAdd) {
		 * fmaster.setAutoId(original.getAutoId());
		 * fmaster.setRdFlag(original.getRdFlag());
		 * fmaster.setRdType(original.getRdType()); } }
		 */
		boolean isVerified = "1".equals(fmaster.getCurrentStatus());
		boolean isZeroDeliver = false;
		
		if (isVerified) {
			// 加载系统参数：是否允许零出库
			isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, appCode,
					RdConstant.SYS_PARA_CODE_ZERO_INVENTORY, "0").equals("1");
			fmaster.setVerifier(personId);
			fmaster.setVerifyDate(curDate);
		}
		if (isAdd) {
			// 新增
			// 设置流水号和流水日期
			String billNo = fmaster.getBillNo();
			String billMonthNo = fmaster.getBillMonthNo();//byzcl
			if(billMonthNo == null || "".equals(billMonthNo)){
				fmaster.setBillMonthNo(getNextBillMonthNo(fmaster.getRdFlag(),fmaster.getRdType(), fmaster
						.getStorageCode()));
			}
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(getNextBillNo(fmaster.getRdFlag(),fmaster.getRdType(), fmaster
						.getStorageCode()));
			} else {
				// 新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if (!materialRdsMasterDAO.checkBillNoUnique(unitsCode, fmaster
						.getStorageCode(), fmaster.getRdFlag(), billNo)) {
					throw new RuntimeException("手工输入的单据编号[" + billNo + "]在单位["
							+ unitsCode + "],仓库[" + fmaster.getStorageCode()
							+ "],业务类型[" + fmaster.getOperationType() + "]下有重复");
				}
			}
			if (fmaster.getBillDate() == null) {
				fmaster.setBillDate(curDate);
			}
			if(fmaster.getUnitsCode() == null){
				fmaster.setUnitsCode(unitsCode);
			}
//			fmaster.setUnitsCode(unitsCode);//byzcl
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			// 保存主记录
			materialRdsMasterDAO.save(fmaster);
			mainAutoId = fmaster.getAutoId();
		} else {
			// 修改
			mainAutoId = fmaster.getAutoId();
			MaterialRdsMaster original = materialRdsMasterDAO
					.findById(mainAutoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + mainAutoId
						+ "的收发存主记录！");
			}
			if (!"0".equals(original.getCurrentStatus())) {
				String msg = "本次入出库已审核，不能修改保存！";
				if (isVerified) {
					msg = "本次入出库已审核，不能再次审核保存！";
				}
				throw new RuntimeException(msg);
			}
			materialRdsMasterDAO.merge(fmaster);
			materialRdsDetailDAO.deleteByMainAutoId(mainAutoId);
		}
		// 保存明细记录
		Short i = 0;
		for (MaterialRdsDetail detail : fdetails) {
			detail.setSerialNo(++i);
			String batch = detail.getBatch();
			if (batch == null || "".equals(StringUtils.trim(batch))) {
				batch = "0";
				detail.setBatch(batch);
			}
			String barCode = detail.getBarCode();
			if (barCode == null || "".equals(StringUtils.trim(barCode))) {
				barCode = "0";
				detail.setBarCode(barCode);
			}
			detail.setAcctBillNo("0");
			detail.setCurrentStatus("0");
			detail.setRdBillNo("0");
			detail.setRetailPrice(detail.getTradePrice());
			detail.setRetailMoney(detail.getTradeMoney());
			//
			detail.setWholeSaleMoney(detail.getTradePrice());
			detail.setWholeSalePrice(detail.getTradeMoney());
			
			if (isVerified) {
				// 若已审核，则更新库存数量
				MaterialCurrentStock stock = buildCurrentStock(RdConstant.D
						.equals(fmaster.getRdFlag()), unitsCode, storageCode,
						detail);
				MaterialCurrentStock mStock = saveCurrentStock(isZeroDeliver,fmaster.getRdType(),
						stock);
				detail.setCurrentStockAmount(mStock.getAmount());
			} else {
				// 因是未审核，收发存明细记录的现存量只是一个参考
				MaterialCurrentStock _stock = materialCurrentStockDAO
						.findByUniqueIndex(unitsCode, storageCode, detail
								.getMaterialId(), batch, barCode);
				detail.setCurrentStockAmount(_stock == null ? 0d : _stock
						.getAmount());
			}
			detail.setMainAutoId(mainAutoId);
			materialRdsDetailDAO.save(detail);
		}
		objs[0] = fmaster;
		objs[1] = fdetails;
		return objs;
	}

	/**
	 * 构造现存量汇总记录对象
	 * 
	 * @param isDeliver
	 *            是否出库
	 * @param unitsCode
	 * @param storageCode
	 * @param detail
	 * @return
	 */
	private MaterialCurrentStock buildCurrentStock(boolean isDeliver,
			String unitsCode, String storageCode, MaterialRdsDetail detail) {
		MaterialCurrentStock stock = new MaterialCurrentStock();
		stock.setUnitsCode(unitsCode);
		stock.setStorageCode(storageCode);
		stock.setMaterialClass(detail.getMaterialClass());
		stock.setBarCode(detail.getBarCode());
		stock.setMaterialId(detail.getMaterialId());
		stock.setMaterialCode(detail.getMaterialCode());
		stock.setMaterialName(detail.getMaterialName());
		stock.setMaterialSpec(detail.getMaterialSpec());
		stock.setMaterialUnits(detail.getMaterialUnits());
		stock.setTradePrice(detail.getTradePrice());
		stock.setFactTradePrice(detail.getFactTradePrice());
		stock.setWholeSalePrice(detail.getWholeSalePrice());
		stock.setInvitePrice(detail.getInvitePrice());
		stock.setRetailPrice(detail.getRetailPrice());
		stock.setAmount(isDeliver ? -detail.getAmount() : detail.getAmount());
		stock.setFactoryCode(detail.getFactoryCode());
		stock.setMadeDate(detail.getMadeDate());
		stock.setBatch(detail.getBatch());
		stock.setAvailDate(detail.getAvailDate());
		stock.setPosition(detail.getPosition());
		stock.setHighValueSign(detail.getHighValueSign());
		stock.setAgentSign(detail.getAgentSign());
		return stock;
	}

	/**
	 * 写现存量汇总，需判断是否零出库（蓝字出库，或红字入库）
	 * 
	 * @param isZeroDeliver
	 *            系统是否允许零出库
	 * @param rdType      
	 * 			     收发类型
	 * @param fstock
	 *            要累积的现存量记录
	 * @return
	 */
	private MaterialCurrentStock saveCurrentStock(boolean isZeroDeliver,String rdType,
			MaterialCurrentStock fstock) {
		String batch = fstock.getBatch();
		if (batch == null || "".equals(batch)) {
			batch = "0";
		}
		String barCode = fstock.getBarCode();
		if (barCode == null || "".equals(barCode)) {
			barCode = "0";
		}
		String unitsCode = fstock.getUnitsCode();
		SysUser user = SessionUtil.getSysUser();
		String appCode = user.getAppCode();
		if (unitsCode == null) {
			unitsCode = user.getUnitsCode();
		}
		String highValue=fstock.getHighValueSign();
		String storageCode = fstock.getStorageCode();
		String materialId = fstock.getMaterialId();
		String materialName = fstock.getMaterialName();
		
		//boolean isZero = cdSysParamDAO.findByParaCode(unitsCode, appCode, "0603", "0").equals("1");byzcl
		//提示库存不足
		boolean isZeroNo = cdSysParamDAO.findByParaCode(unitsCode, appCode, "0604", "0").equals("1");
		MaterialCurrentStock _stock = materialCurrentStockDAO
				.findByUniqueIndex(unitsCode, storageCode, materialId, batch,
						barCode);
		
		if (_stock == null) {
			// 新增
			//if(!isZero){byzcl判断库存不足，负库存出库的。
				if (fstock.getAmount() < 0) {
					if (isZeroNo)
						throw new RuntimeException(String.format(
								ERROR_MSG_ZERO_DELIVER, materialName));
				}
			//}
		
			if(rdType.equals("203") || rdType.equals("103")){ //期初入库103
				fstock.setAcctAmount(fstock.getAmount());
			}
			fstock.setVirtualAmount(fstock.getAmount());
			System.out.println("autoId:"+fstock.getAutoId()+" getMaterialId:"+fstock.getMaterialId()+" getFactoryCode:"+fstock.getFactoryCode()+" getStorageCode:"+fstock.getStorageCode()+" getBatch:"+fstock.getBatch()+"tradePrice"+fstock.getTradePrice());
			materialCurrentStockDAO.save(fstock);
			return fstock;
		} else {
			// 修改
			Double currentAmount = _stock.getAmount() + fstock.getAmount();
		//	if(!isZero){byzcl
				if (currentAmount < 0) {
					if (isZeroNo)
						throw new RuntimeException(String.format(
								ERROR_MSG_ZERO_DELIVER, materialName));
				}
		//	}
			
			// 最近值
			// 进价，入库时根据批次修改库存价格
			if(rdType.startsWith("1")){
				_stock.setTradePrice(fstock.getTradePrice());
				_stock.setFactTradePrice(fstock.getFactTradePrice());
				_stock.setWholeSalePrice(fstock.getWholeSalePrice());
				_stock.setInvitePrice(fstock.getInvitePrice());
				_stock.setRetailPrice(fstock.getRetailPrice());
			}
			
			// 累计值
			// 结存数量
			_stock.setAmount(currentAmount);
			Double virtualAmount=_stock.getVirtualAmount()==null || _stock.getVirtualAmount().isNaN() ? 0d : _stock.getVirtualAmount();
			
			//入库时更新虚拟库存
			if(rdType.startsWith("1")){
				_stock.setVirtualAmount(virtualAmount + fstock.getAmount());
			}
			else{
				if(highValue!=null && highValue.equals("1")){
					_stock.setVirtualAmount(virtualAmount - fstock.getAmount());
				}
			}
			if(rdType.equals("203") || rdType.equals("103")){ //期初入库103
				_stock.setAcctAmount(currentAmount);
			}
			return _stock;
		}
	}

	@Override
	public Object[] verify(String fstrAutoId) {
		Object[] objs = new Object[2];
		MaterialRdsMaster master = materialRdsMasterDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的收发存主记录！");
		}
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入出库已审核，不能再次审核！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入出库已记帐，不能审核！");
		}
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appNo = user.getAppCode();
		String personId = user.getPersonId();
		boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, appNo,
				RdConstant.SYS_PARA_CODE_ZERO_INVENTORY, "0").equals("1");
		master.setCurrentStatus("1");
		master.setVerifier(personId);
		master.setVerifyDate(new Date());
		
		List<MaterialRdsDetail> details = materialRdsDetailDAO
				.findByMainAutoId(master.getAutoId());
		for (MaterialRdsDetail detail : details) {
			// 更新库存数量
			MaterialCurrentStock stock = buildCurrentStock(RdConstant.D
					.equals(master.getRdFlag()), unitsCode, master
					.getStorageCode(), detail);
			MaterialCurrentStock mStock = saveCurrentStock(isZeroDeliver,master.getRdType(), stock);
			detail.setCurrentStockAmount(mStock.getAmount());
			detail.setCurrentStatus("1");//7-27，明细增加审核状态；
		}
		objs[0] = master;
		objs[1] = details;
		return objs;
	}
	
	@Override
	public void cancelVerify(String fstrAutoId){
		MaterialRdsMaster master = materialRdsMasterDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("收发存主记录不存在，不能弃审！");
		}
		if ("0".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入出库未审核，不能弃审！");
		}
//		String unitsCode = master.getUnitsCode();//byzcl
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		List<MaterialRdsDetail> details = materialRdsDetailDAO
		.findByMainAutoId(master.getAutoId());
		for (MaterialRdsDetail detail : details) {
			String trimedAcctBillNo = StringUtils.trim(detail.getAcctBillNo());
			if(!"".equals(trimedAcctBillNo)&&!"0".equals(trimedAcctBillNo)){
				throw new RuntimeException("物资"+detail.getMaterialName()+"["+detail.getMaterialCode()+"已记账，不能弃审！");
			}
			MaterialCurrentStock stock = buildCurrentStock(RdConstant.R
					.equals(master.getRdFlag()), unitsCode, master
					.getStorageCode(), detail);
			MaterialCurrentStock mStock = saveCurrentStock(true, master.getRdType(),stock);
			detail.setCurrentStockAmount(mStock.getAmount());
			detail.setCurrentStatus("0");
		}
		master.setCurrentStatus("0");
		master.setVerifier(null);
		master.setVerifyDate(null);
	}

	@Override
	public Object[] findById(String fstrAutoId) {
		Object[] objs = new Object[2];
		MaterialRdsMaster master = materialRdsMasterDAO.findById(fstrAutoId);
		if (master == null) {
			return objs;
		}
		List<MaterialRdsDetail> details = materialRdsDetailDAO
				.findByMainAutoId(master.getAutoId());
		objs[0] = master;
		objs[1] = details;
		return objs;
	}

	@Override
	public ReObject uploadFileToTmp(String fstrFileName, byte[] faryFileData) {
		String lstrBaseTmpPath = ReadPropertiesFile
				.getValue("UPLOAD_FILE_TEMP_BASE_DIR");
		File dir = new File(lstrBaseTmpPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			FileOutputStream fos = new FileOutputStream(lstrBaseTmpPath
					+ fstrFileName);
			fos.write(faryFileData);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ReObject("上传并保存为临时文件");
	}

	@Override
	public ReObject findOperationTypeByCondition(ParameterObject fParameters) {
		ReObject ro = new ReObject("物资收发类别字典列表");
//		int start = fParameters.getStart();
//		int limit = fParameters.getItemsPerPage();
		Map<String, Object> fmap = fParameters.getConditions();
		StringBuilder condition = new StringBuilder();
		String phoInputCode = (String) fmap.get("phoInputCode");
		String fiveInputCode = (String) fmap.get("fiveInputCode");
		String rdFlag = (String) fmap.get("rdFlag");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String deptCode = user.getDeptCode();
		//append("' and chargeCode='").append(deptCode). byzcl
		condition.append(" where unitsCode = '").append(unitsCode).append("' and rdFlag='").append(rdFlag).append("'");
		if (phoInputCode != null && phoInputCode.toString().trim().length() > 0) {
			phoInputCode = phoInputCode.toLowerCase();
			condition.append(" and phoInputCode like '" + phoInputCode + "%'");
		} else {
			if (null != fiveInputCode) {
				fiveInputCode = fiveInputCode.toLowerCase();
				condition
				.append(" and fiveInputCode like '" + fiveInputCode + "%'");
			}
		}
			Class<?> className = CdStorageRd.class;
			List<?> result = toolDAO.findByCondition(className, condition
					.toString());
			ro.setData(result);
		return ro;
	}

}
