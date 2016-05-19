package cn.superion.material.deliver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.config.dao.CdStorageDictDAO;
import cn.superion.center.config.entity.CdStorageDict;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialProvideDetailDAO;
import cn.superion.material.dao.MaterialProvideMasterDAO;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.dao.VMaterialProvideDAO;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialPlanDetail;
import cn.superion.material.entity.MaterialProvideDetail;
import cn.superion.material.entity.MaterialProvideMaster;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.material.entity.VMaterialProvide;
import cn.superion.materialDept.dao.MaterialRdsDetailDeptDAO;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 物资领用出库服务实现
 * 
 * @author 曹国魁
 * 
 */
public class DeliverImpl implements IDeliver {
	private Log log = LogFactory.getLog(DeliverImpl.class);
	private MaterialProvideMasterDAO materialProvideMasterDAO;
	private MaterialProvideDetailDAO materialProvideDetailDAO;
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService commMaterialServiceImpl;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO;
	private VMaterialProvideDAO vMaterialProvideDAO; 
	private CdStorageDictDAO cdStorageDictDAO;
	public CdStorageDictDAO getCdStorageDictDAO() {
		return cdStorageDictDAO;
	}

	public void setCdStorageDictDAO(CdStorageDictDAO cdStorageDictDAO) {
		this.cdStorageDictDAO = cdStorageDictDAO;
	}

	public MaterialRdsDetailDeptDAO getMaterialRdsDetailDeptDAO() {
		return materialRdsDetailDeptDAO;
	}

	public void setMaterialRdsDetailDeptDAO(
			MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO) {
		this.materialRdsDetailDeptDAO = materialRdsDetailDeptDAO;
	}

	public MaterialRdsDetailDAO getMaterialRdsDetailDAO() {
		return materialRdsDetailDAO;
	}

	public void setMaterialRdsDetailDAO(MaterialRdsDetailDAO materialRdsDetailDAO) {
		this.materialRdsDetailDAO = materialRdsDetailDAO;
	}

	public cn.superion.materialDept.common.service.ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	private cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl;
	

	public MaterialProvideMasterDAO getMaterialProvideMasterDAO() {
		return materialProvideMasterDAO;
	}

	public void setMaterialProvideMasterDAO(
			MaterialProvideMasterDAO materialProvideMasterDAO) {
		this.materialProvideMasterDAO = materialProvideMasterDAO;
	}

	public MaterialProvideDetailDAO getMaterialProvideDetailDAO() {
		return materialProvideDetailDAO;
	}

	public void setMaterialProvideDetailDAO(
			MaterialProvideDetailDAO materialProvideDetailDAO) {
		this.materialProvideDetailDAO = materialProvideDetailDAO;
	}

	public MaterialRdsMasterDAO getMaterialRdsMasterDAO() {
		return materialRdsMasterDAO;
	}

	public VMaterialProvideDAO getvMaterialProvideDAO() {
		return vMaterialProvideDAO;
	}

	public void setvMaterialProvideDAO(VMaterialProvideDAO vMaterialProvideDAO) {
		this.vMaterialProvideDAO = vMaterialProvideDAO;
	}

	public void setMaterialRdsMasterDAO(
			MaterialRdsMasterDAO materialRdsMasterDAO) {
		this.materialRdsMasterDAO = materialRdsMasterDAO;
	}

	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
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
	public ReObject deleteRds(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的出库单据");
		MaterialRdsMaster master = commMaterialServiceImpl
				.deleteByAutoId(fstrAutoId);
		if (master != null) {
			String operationNo = master.getOperationNo();
			if (operationNo != null && !"".equals(operationNo)) {
				// 自动发放
				MaterialProvideMaster provideMaster = materialProvideMasterDAO
						.findByBillNo(master.getUnitsCode(), operationNo);
				if (provideMaster == null) {
					log.warn("不存在单据号为" + operationNo
							+ "的科室领用申请单记录，无法还原科室领用单状态！");
				} else {
					provideMaster.setCurrentStatus("1");
					materialProvideDetailDAO.reduceCheckAmount(provideMaster
							.getAutoId());
				}
			}
		}
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findProvide(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤科室领用申请单据");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		//北院科室可以向南院的耗材仓库申领物资 ryh 2012.12.11
		Object[] objs = materialProvideMasterDAO.findByCondition(start, limit,
				"", fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findProvideDetailByMainAutoId(String fstrMainAutoId) {
		ReObject ro = new ReObject("查找当前科室领用申请明细单");
		List<MaterialProvideDetail> data = materialProvideDetailDAO
				.findByMainAutoId(fstrMainAutoId);
		ro.setData(data);
		return ro;
	}

	public ReObject findProvideByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤科室申请单视图");
		String unitsCode = SessionUtil.getUnitsCode();
		List<VMaterialProvide> result = vMaterialProvideDAO.findByCondition(unitsCode,fparameter.getConditions());
		ro.setData(result);
		return ro;
	}

	
	@Override
	public ReObject findRdsDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前出库单的详细信息记录");
		// 先获取出库主记录和明细记录
		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		List<MaterialProvideDetail> provideDetails = null;
		String operationNo = ((MaterialRdsMaster) objs[0]).getOperationNo();
		String unitsCode = ((MaterialRdsMaster) objs[0]).getUnitsCode();
		String storageCode = ((MaterialRdsMaster) objs[0]).getStorageCode();
		if (operationNo != null && !"".equals(operationNo)) { 
			provideDetails = materialProvideDetailDAO.findByBillNo(unitsCode,storageCode,
					operationNo);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		data.add(objs[1]);
		data.add(provideDetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的出库单据列表");
		List<Object> data = materialRdsMasterDAO.findAutoIdsByDeliverCondition(
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveRds(MaterialRdsMaster fmaster,
			List<MaterialRdsDetail> fdetails) {
		ReObject ro = new ReObject("保存领用出库信息");
		String storageCode = fmaster.getStorageCode();
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		//String unitsCode = fmaster.getUnitsCode();
		String applyUnitsCode=fmaster.getUnitsCode();
		if(applyUnitsCode==null || applyUnitsCode.equals("")){
			applyUnitsCode=unitsCode;
			fmaster.setUnitsCode(applyUnitsCode);//byzcl
		}
		String appCode = user.getAppCode();
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(unitsCode,
				appCode, RdConstant.SYS_PARA_CODE_D_DELIVER_CHK, "0"));
		fmaster.setCurrentStatus(isVerified ? "1" : "0");
		fmaster.setRdFlag(RdConstant.D);
		String operationNo = fmaster.getOperationNo();
		String autoId = fmaster.getAutoId();
		// 是否过滤科室领用申请单,新增且业务号不为空的是过滤科室领用申请单的
		boolean isFilterDeptApply = (autoId == null || "".equals(autoId))
				&& operationNo != null && !"".equals(operationNo);
		// 科室领用申请单的主记录ID
		String provideMasterAutoId = fmaster.getSourceAutoId();
		if (isFilterDeptApply) {
			if (provideMasterAutoId == null || "".equals(provideMasterAutoId)) {
				throw new RuntimeException(
						"过滤科室领用申请单时，参数收发存明细记录引用的科室领用申请单主记录autoId不能为空！");
			}
			for (MaterialRdsDetail detail : fdetails) {
				if (detail.getSourceAutoId() == null
						|| "".equals(detail.getSourceAutoId())) {
					throw new RuntimeException(
							"过滤科室领用申请单时，参数收发存明细记录引用的科室领用申请单明细记录autoId不能为空！");
				}
			}
		}
		// 蓝字单据
		boolean isBlue = "1".equals(fmaster.getInvoiceType());
		// 蓝字单据自动发放
		boolean isAutoDelive = isBlue && isFilterDeptApply;
		//保存后再修改
		boolean isMify = cdSysParamDAO.findByParaCode(unitsCode, appCode, "0607", "0").equals("1");
		List<MaterialRdsDetail> factDetails = null;
		
		if(isMify){
			factDetails = new ArrayList<MaterialRdsDetail>();
		}else{
			factDetails = isAutoDelive ? new ArrayList<MaterialRdsDetail>()
					: fdetails;//byzcl
		}
		
		
		// 更新科室领用单状态为执行
		if (isFilterDeptApply && provideMasterAutoId != null
				&& !"".equals(provideMasterAutoId)) {
			MaterialProvideMaster provideMaster = materialProvideMasterDAO
					.findById(provideMasterAutoId);
			if (provideMaster == null) {
				log.warn("不存在ID为" + provideMasterAutoId + "的科室领用单,无法更新执行状态！");
			} else {
				// 系统只支持一个申请单据仅一次发放处理
				provideMaster.setCurrentStatus("2");
			}
		}
		String deliveType = cdSysParamDAO.findByParaCode(unitsCode, appCode,
				RdConstant.SYS_PARA_CODE_DELIVE_TYPE, "0");
		// 存放materialId为key,amount为value的汇总数据
		Map<String, Double> tmp = null;
		// 加载系统参数：是否允许零出库
		boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode,
				appCode, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY, "0").equals(
				"1");
		for (MaterialRdsDetail detail : fdetails) {
			// 实发数量
			Double checkAmount = 0d;
			if(isMify){
				if (true) {//isAutoDelive byzcl
					// 客户端传参-实发数量为checkAmount,和MaterialRdsDetail的amount对应
					detail.setAmount(detail.getCheckAmount());
					checkAmount = detail.getAmount();
					autoDistributeDeliveAmount(unitsCode, storageCode, detail
							.getMaterialId(), deliveType, factDetails, checkAmount,
							isZeroDeliver, detail);
				}
			}else{
				if (isAutoDelive) {//isAutoDelive byzcl
					// 客户端传参-实发数量为checkAmount,和MaterialRdsDetail的amount对应
					detail.setAmount(detail.getCheckAmount());
					checkAmount = detail.getAmount();
					autoDistributeDeliveAmount(unitsCode, storageCode, detail
							.getMaterialId(), deliveType, factDetails, checkAmount,
							isZeroDeliver, detail);
				}
			}
			
			String sourceAutoId = detail.getSourceAutoId();
			checkAmount = detail.getAmount();
			detail.setAcctBillNo("0");
			detail.setRdBillNo("0");
			detail.setCurrentStatus("0");
			if (isFilterDeptApply && sourceAutoId != null
					&& !"".equals(sourceAutoId)) {
				// 同时更新红单或蓝单的申请物资的已发数量
				//如果是一般高值，则重新计算实发数量ryh 2012.10.22
				if(detail.getHighValueSign()!=null && detail.getHighValueSign().equals("1"))
				{
					checkAmount=0d;
					for (MaterialRdsDetail factDetail : fdetails){
						if(factDetail.getMaterialId().equals(detail.getMaterialId())){
							checkAmount+=factDetail.getAmount();
						}
					}
				}
				materialProvideDetailDAO.updateCheckAmount(sourceAutoId,
						checkAmount);
			}
			if (!isFilterDeptApply) {
				String materialId = detail.getMaterialId();
				Double amount = detail.getCheckAmount();
				// 非过滤领用申请单的蓝字单据，需汇总修改实发数量
				if (isBlue) {
					if (tmp == null) {
						tmp = new HashMap<String, Double>();
					}
					if (tmp.containsKey(materialId)) {
						amount += tmp.get(materialId);
					}
					tmp.put(materialId, amount);
				} else {
					// 红单，物资退回仓库，无需汇总处理
					materialProvideDetailDAO.updateCheckAmount(sourceAutoId,
							checkAmount);
				}
			}
		}
		if (tmp != null) {
			for (String materialId : tmp.keySet()) {
				materialProvideDetailDAO.updateCheckAmount(applyUnitsCode,
						operationNo, materialId, tmp.get(materialId));
			}
		}
		commMaterialServiceImpl.save(fmaster, factDetails);
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(factDetails);
		ro.setData(data);
		return ro;
	}

	/**
	 * 为指定物资自动分配出库数量
	 * 
	 * @param unitsCode
	 * @param storageCode
	 * @param materialId
	 * @param deliveType
	 *            出库方式：0-近效期先出；1-批号先进先出
	 * @param factDetails
	 *            分配的出库明细记录集合
	 * @param checkAmount
	 *            应出库的总量
	 * @param isZeroDeliver
	 *            系统参数：是否零出库
	 * @param detail
	 *            申请的物资记录,依赖参数isZeroDeliver,如果isZeroDeliver为true,则detail非空
	 */
	private void autoDistributeDeliveAmount(String unitsCode,
			String storageCode, String materialId, String deliveType,
			List<MaterialRdsDetail> factDetails, Double checkAmount,
			boolean isZeroDeliver, MaterialRdsDetail detail) {
		// 自动发放，将申请的物资分解到具体批号物资上
		List<MaterialCurrentStock> stockList = new ArrayList<MaterialCurrentStock>();
		//是否高值耗材
		String highValueSign=detail.getHighValueSign();
		String barCode=detail.getBarCode();
		
		SysUser user = SessionUtil.getSysUser();
		String appCode = user.getAppCode();
		
		double i = 0.0;
		boolean sign = false;
		
		if(highValueSign!=null && highValueSign.equals("1")){
			stockList = materialCurrentStockDAO
			.findValueByMaterialId(unitsCode, storageCode, materialId,barCode,
					deliveType);
		}
		else
		{
			stockList = materialCurrentStockDAO
			.findByMaterialId(unitsCode, storageCode, materialId,
					deliveType);
		}
		// 是否有库存
		boolean hasInventory = false;
		for (MaterialCurrentStock stock : stockList) {
			// 当前一个批号下的库存量
			Double curBatchStockAmount = stock.getAmount();
			if (curBatchStockAmount > 0) {
				if (checkAmount <= curBatchStockAmount) {
					MaterialRdsDetail details = buildMaterialRdsDetail(stock, checkAmount);
					details.setRetailPrice(stock.getRetailPrice());//byzcl
					details.setRetailMoney(stock.getRetailPrice() * checkAmount);
					details.setWholeSalePrice(stock.getWholeSalePrice());
					details.setWholeSaleMoney(stock.getWholeSalePrice() * checkAmount);
					details.setDetailRemark(detail.getDetailRemark());
					details.setFactoryCode(detail.getFactoryCode());
					//ryh 2012.10.21
					details.setIsGive(detail.getIsGive());
					details.setChargeSign(detail.getChargeSign());
					details.setClassOnAccount(detail.getClassOnAccount());
					details.setMaterialBarCode(detail.getMaterialBarCode());
					details.setPackageSpec(detail.getPackageSpec());
					details.setPackageUnits(detail.getPackageUnits());
					details.setCountClass(detail.getCountClass());
					details.setRegisterNo(detail.getRegisterNo());
					details.setAmountPerPackage(detail.getAmountPerPackage());
					details.setPackageAmount(detail.getPackageAmount());
					factDetails.add(details);
					hasInventory = true;
					sign = true;
					break;
				} else {
					MaterialRdsDetail details = buildMaterialRdsDetail(stock,curBatchStockAmount);
					details.setRetailPrice(stock.getRetailPrice());
					details.setRetailMoney(stock.getRetailPrice() * curBatchStockAmount);
					details.setWholeSalePrice(stock.getWholeSalePrice());
					details.setWholeSaleMoney(stock.getWholeSalePrice() * curBatchStockAmount);
					details.setDetailRemark(detail.getDetailRemark());
					details.setFactoryCode(detail.getFactoryCode());
					//ryh 2012.10.21
					details.setIsGive(detail.getIsGive());
					details.setChargeSign(detail.getChargeSign());
					details.setClassOnAccount(detail.getClassOnAccount());
					details.setMaterialBarCode(detail.getMaterialBarCode());
					details.setPackageSpec(detail.getPackageSpec());
					details.setPackageUnits(detail.getPackageUnits());
					details.setAmountPerPackage(detail.getAmountPerPackage());
					details.setPackageAmount(detail.getPackageAmount());
					details.setCountClass(detail.getCountClass());
					details.setRegisterNo(detail.getRegisterNo());
					
					factDetails.add(details);
					hasInventory = true;
					checkAmount -= curBatchStockAmount;
					i = checkAmount;
				}
			}
		}
		CdStorageDict cdStorageDict = cdStorageDictDAO.findById(unitsCode, storageCode);
		boolean isZero = cdSysParamDAO.findByParaCode(unitsCode, appCode, "0603", "0").equals("1");
		//判断库存不足
		if(isZero){
			if(i > 0 && !sign){
				throw new RuntimeException("物资库[" + cdStorageDict.getStorageName() + "]中物资["
						+ detail.getMaterialName() + "]库存不足，不允许出库！");
			}
			if(!hasInventory){
				MaterialRdsDetail details = buildMaterialRdsDetailZero(detail, checkAmount);
				details.setRetailPrice(detail.getRetailPrice());
				details.setRetailMoney(detail.getRetailPrice() * checkAmount);
				details.setWholeSaleMoney(detail.getWholeSalePrice() * checkAmount);
				details.setWholeSalePrice(detail.getWholeSalePrice());
				details.setDetailRemark(detail.getDetailRemark());
				details.setFactoryCode(detail.getFactoryCode());
				//ryh 2012.10.21
				details.setIsGive(detail.getIsGive());
				details.setChargeSign(detail.getChargeSign());
				details.setClassOnAccount(detail.getClassOnAccount());
				details.setMaterialBarCode(detail.getMaterialBarCode());
				details.setPackageSpec(detail.getPackageSpec());
				details.setPackageUnits(detail.getPackageUnits());
				details.setAmountPerPackage(detail.getAmountPerPackage());
				details.setPackageAmount(detail.getPackageAmount());
				details.setCountClass(detail.getCountClass());
				details.setRegisterNo(detail.getRegisterNo());
				factDetails.add(details);
			}
			if(stockList.isEmpty() || !hasInventory){
				if(checkAmount > 0){
					throw new RuntimeException("物资库[" + cdStorageDict.getStorageName() + "]中物资["
							+ detail.getMaterialName() + "]库存不足，不允许出库！");
				}
			}
		}else{
			// 如果零库存，则根据系统参数判断是否给予零库存出库
			if (stockList.isEmpty() || !hasInventory) {
				log.warn("物资库[" +  cdStorageDict.getStorageName() + "]中物资[" + detail.getMaterialName() + "]零库存！");
				if (isZeroDeliver) {
					factDetails.add(detail);
				} else {
					throw new RuntimeException("物资库[" + cdStorageDict.getStorageName() + "]中物资["
							+ detail.getMaterialName() + "]零库存,系统参数不允许零库存出库！");
				}
			}
		}
	}
	
	private MaterialRdsDetail buildMaterialRdsDetailZero(
			MaterialRdsDetail detailz, Double checkAmount) {
		MaterialRdsDetail detail = new MaterialRdsDetail();
		detail.setMaterialClass(detailz.getMaterialClass());
		detail.setBarCode(detailz.getBarCode());
		detail.setMaterialId(detailz.getMaterialId());
		detail.setMaterialCode(detailz.getMaterialCode());
		detail.setMaterialName(detailz.getMaterialName());
		detail.setMaterialSpec(detailz.getMaterialSpec());
		detail.setMaterialUnits(detailz.getMaterialUnits());
		detail.setTradePrice(detailz.getTradePrice());
		detail.setAmount(checkAmount);
		detail.setTradeMoney(detailz.getTradePrice() * checkAmount);
		// 因无零售价，故不好计算零售金额
		// if(applyDetail != null && applyDetail.getRetailPrice() != null){
		// detail.setRetailPrice(applyDetail.getRetailPrice());
		// detail.setRetailMoney(applyDetail.getRetailPrice()*checkAmount);
		// }
		
		detail.setFactoryCode(detailz.getFactoryCode());
		detail.setMadeDate(detailz.getMadeDate());
		detail.setBatch(detailz.getBatch());
		detail.setAvailDate(detailz.getAvailDate());
		detail.setPosition(detailz.getPosition());
		detail.setHighValueSign(detailz.getHighValueSign());
		detail.setAgentSign(detailz.getAgentSign());
		return detail;
	}

	/**
	 * 构造出库明细记录
	 * 
	 * @param stock
	 *            当前库存记录
	 * @param checkAmount
	 *            指定的一个批号下的应出库数量
	 * @return
	 */
	private MaterialRdsDetail buildMaterialRdsDetail(
			MaterialCurrentStock stock, Double checkAmount) {
		MaterialRdsDetail detail = new MaterialRdsDetail();
		detail.setMaterialClass(stock.getMaterialClass());
		detail.setBarCode(stock.getBarCode());
		detail.setMaterialId(stock.getMaterialId());
		detail.setMaterialCode(stock.getMaterialCode());
		detail.setMaterialName(stock.getMaterialName());
		detail.setMaterialSpec(stock.getMaterialSpec());
		detail.setMaterialUnits(stock.getMaterialUnits());
		detail.setTradePrice(stock.getTradePrice());
		detail.setAmount(checkAmount);
		detail.setTradeMoney(stock.getTradePrice() * checkAmount);
		// 因无零售价，故不好计算零售金额
		// if(applyDetail != null && applyDetail.getRetailPrice() != null){
		// detail.setRetailPrice(applyDetail.getRetailPrice());
		// detail.setRetailMoney(applyDetail.getRetailPrice()*checkAmount);
		// }
		
		detail.setFactoryCode(stock.getFactoryCode());
		detail.setMadeDate(stock.getMadeDate());
		detail.setBatch(stock.getBatch());
		detail.setAvailDate(stock.getAvailDate());
		detail.setPosition(stock.getPosition());
		detail.setHighValueSign(stock.getHighValueSign());
		detail.setAgentSign(stock.getAgentSign());
		return detail;
	}

	@Override
	public ReObject verifyRds(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的出库单信息");
		Object[] objs = commMaterialServiceImpl.verify(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject cancelVerifyRds(String fstrAutoId){
		ReObject ro = new ReObject("弃审");
		//这里的弃审应该考虑到2种情况，1、若入库确认，不能弃审；2、记账后，不能弃审；
		//查找是否有入科
		List<MaterialRdsDetail> rdsDetails = materialRdsDetailDAO.findByMainAutoId(fstrAutoId);
		for (MaterialRdsDetail materialRdsDetail : rdsDetails) {
			//根据收发存明细查找对应的科室收发存明细
			List<MaterialRdsDetailDept> deptDetails = materialRdsDetailDeptDAO.findByProperty("sourceAutoId", materialRdsDetail.getAutoId());
			if(deptDetails.size() > 0 ){
				throw new RuntimeException("该张单据已入科确认，不能弃审！");
			}
		}
		//查找是否已记账，cancelVerify方法中已验证
		commMaterialServiceImpl.cancelVerify(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject findCurrentStockByIdStorageAmount(String fstrMaterialId,
			String fstrStorageCode, Double amount) {
		ReObject ro = new ReObject("自动分配当前出库数量的数据列表");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		String deliveType = cdSysParamDAO.findByParaCode(unitsCode, appCode,
				RdConstant.SYS_PARA_CODE_DELIVE_TYPE, "0");
		List<MaterialRdsDetail> factDetails = new ArrayList<MaterialRdsDetail>();
		autoDistributeDeliveAmount(unitsCode, fstrStorageCode, fstrMaterialId,
				deliveType, factDetails, amount, false, null);
		ro.setData(factDetails);
		return ro;
	}
	
	@Override
	public ReObject findMaterialDetailByBarCode(String fstrBarCode){
		ReObject ro = new ReObject("根据物资条形码查询物资明细");
		
		MaterialRdsDetail detail=materialRdsDetailDAO.findByBarCode(fstrBarCode,false);
		if(detail==null){
			throw new RuntimeException("没有查询到条形码["+fstrBarCode+"]对应的物资信息！");
		}
		detail=materialRdsDetailDAO.findByBarCode(fstrBarCode,true);
		if(detail==null){
			throw new RuntimeException("条形码["+fstrBarCode+"]对应的物资已被领用！");
		}
		List<MaterialRdsDetail> data=new ArrayList<MaterialRdsDetail>();
		data.add(detail);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findMaterialStockByBarCode(String fstrBarCode){
		ReObject ro = new ReObject("根据物资条形码查询物资明细");
		
		MaterialRdsDetail detail=materialRdsDetailDAO.findStockByBarCode(fstrBarCode);
		if(detail==null){
			throw new RuntimeException("没有查询到条形码["+fstrBarCode+"]对应的物资信息！");
		}
		List<MaterialRdsDetail> data=new ArrayList<MaterialRdsDetail>();
		data.add(detail);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject updateRdsPrintSign(String fstrAutoId) {
		ReObject ro = new ReObject("更新打印状态！");
		commMaterialServiceImpl.updatePrintSign(fstrAutoId);
		return ro;
	}
	
	public ReObject updateProviderDetail(ParameterObject fparameter) {
		ReObject ro = new ReObject("更新打印状态！");
		Map<String,Object> con = fparameter.getConditions();
		String autoId = con.get("autoId").toString();
		String planSign = con.get("planSign").toString();
		Double planAmount =Double.valueOf(con.get("planAmount").toString()) ;
		MaterialProvideDetail detail = materialProvideDetailDAO.findById(autoId);
		detail.setPlanAmount(planAmount);
		detail.setPlanSign(planSign);
		materialProvideDetailDAO.merge(detail);
		return ro;
	}
}
