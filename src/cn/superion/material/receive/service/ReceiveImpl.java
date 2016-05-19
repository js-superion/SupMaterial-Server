package cn.superion.material.receive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.center.provider.dao.CdProviderMaterialDAO;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialApplyDetailDAO;
import cn.superion.material.dao.MaterialApplyMasterDAO;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialOrderDetailDAO;
import cn.superion.material.dao.MaterialOrderMasterDAO;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialApplyDetail;
import cn.superion.material.entity.MaterialApplyMaster;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialOrderDetail;
import cn.superion.material.entity.MaterialOrderMaster;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 采购入库和其他入库服务实现
 * 
 * @author 曹国魁
 * 
 */
public class ReceiveImpl implements IReceive {
	private Log log = LogFactory.getLog(ReceiveImpl.class);
	private CdProviderMaterialDAO cdProviderMaterialDAO;
	private MaterialOrderMasterDAO materialOrderMasterDAO;
	private MaterialOrderDetailDAO materialOrderDetailDAO;
	private MaterialApplyMasterDAO materialApplyMasterDAO;
	private MaterialApplyDetailDAO materialApplyDetailDAO;
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}

	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private CdSysParamDAO cdSysParamDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;
	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}

	private ICommMaterialService commMaterialServiceImpl;

	public CdProviderMaterialDAO getCdProviderMaterialDAO() {
		return cdProviderMaterialDAO;
	}

	public void setCdProviderMaterialDAO(
			CdProviderMaterialDAO cdProviderMaterialDAO) {
		this.cdProviderMaterialDAO = cdProviderMaterialDAO;
	}

	public MaterialOrderDetailDAO getMaterialOrderDetailDAO() {
		return materialOrderDetailDAO;
	}

	public void setMaterialOrderDetailDAO(
			MaterialOrderDetailDAO materialOrderDetailDAO) {
		this.materialOrderDetailDAO = materialOrderDetailDAO;
	}

	public MaterialOrderMasterDAO getMaterialOrderMasterDAO() {
		return materialOrderMasterDAO;
	}

	public void setMaterialOrderMasterDAO(
			MaterialOrderMasterDAO materialOrderMasterDAO) {
		this.materialOrderMasterDAO = materialOrderMasterDAO;
	}

	public MaterialApplyDetailDAO getMaterialApplyDetailDAO() {
		return materialApplyDetailDAO;
	}

	public void setMaterialApplyDetailDAO(
			MaterialApplyDetailDAO materialApplyDetailDAO) {
		this.materialApplyDetailDAO = materialApplyDetailDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
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

	public MaterialApplyMasterDAO getMaterialApplyMasterDAO() {
		return materialApplyMasterDAO;
	}

	public void setMaterialApplyMasterDAO(
			MaterialApplyMasterDAO materialApplyMasterDAO) {
		this.materialApplyMasterDAO = materialApplyMasterDAO;
	}

	public ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject deleteRds(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的入库单据");
		// 先还原来源单据所对应的记录
		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		MaterialRdsMaster master = (MaterialRdsMaster) objs[0];
		List<MaterialRdsDetail> details = (List<MaterialRdsDetail>) objs[1];
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的入库主记录！");
		}
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已审核，不能删除！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已记帐，不能删除！");
		}
		String operationType = master.getOperationType();
		boolean isPurchase = operationType.equals(RdConstant.R_PURCHASE)
				|| operationType.equals(RdConstant.R_AGENCY)
				|| operationType.equals(RdConstant.R_DIRECT)
				|| operationType.equals(RdConstant.R_SPECIAL);
		// 采购入库时，还原采购订单已入库数和当前状态或特殊入库状态
		if (isPurchase) {
			if (master.getOrderNo() != null && !"".equals(master.getOrderNo())) {
				// 还原订单
				for (MaterialRdsDetail originalDetail : details) {
					updateInputAmount(false, true, originalDetail);
				}
			} else if (RdConstant.R_SPECIAL.equals(master.getOperationType())) {
				// 还原特殊入库
				updateApplyStatus(false, master.getUnitsCode(), master
						.getOperationNo());
			} else if (master.getArrivalNo() != null
					&& !"".equals(master.getArrivalNo())) {
				// 还原到货单
				// do nothing
			}
		}
		commMaterialServiceImpl.deleteByAutoId(fstrAutoId);
		return ro;
	}
	
	@Override
	public ReObject updateRdsPrintSign(String fstrAutoId) {
		ReObject ro = new ReObject("更新打印状态！");
		commMaterialServiceImpl.updatePrintSign(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject findCheckSaleProduct(ParameterObject fparameter) {
		ReObject ro = new ReObject("校验供应商物资授权");
		String materialId = (String) fparameter.getConditions().get(
				"materialId");
		String salerCode = (String) fparameter.getConditions().get("salerCode");
		boolean isValid = cdProviderMaterialDAO.checkSaleProductPrivilege(
				SessionUtil.getUnitsCode(), salerCode, materialId);
		List<Boolean> data = new ArrayList<Boolean>();
		data.add(isValid);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findOrderMasterList(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤已审核或已执行的采购订单");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = materialOrderMasterDAO.findCheckedByCondition(start,
				limit, SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List<MaterialOrderMaster>) objs[1]);
		return ro;
	}

	@Override
	public ReObject findOrderDetailList(String fstrAutoId) {
		ReObject ro = new ReObject("根据订单主记录ID查询订单明细列表");
		List<MaterialOrderDetail> data = materialOrderDetailDAO
				.findByMainAutoId2(fstrAutoId);
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		//
		for (MaterialOrderDetail materialOrderDetail : data) {
			String materialId = materialOrderDetail.getMaterialId();
			//
			CdMaterialDict material = cdMaterialDictDAO.findById(unitsCode, materialId);
			if(material!=null){
				materialOrderDetail.setPackageSpec(material.getPackageSpec());
				materialOrderDetail.setPackageUnits(material.getPackageUnits());
				materialOrderDetail.setAmountPerPackage(material.getAmountPerPackage());
				//判断包装系数、若为null或0，默认赋1
				Double pkgAmount = .0;
				if(materialOrderDetail.getAmountPerPackage() == null || 0==materialOrderDetail.getAmountPerPackage()){
					materialOrderDetail.setAmountPerPackage(1.00);//默认赋1;
				}
				pkgAmount =  materialOrderDetail.getAmount() / materialOrderDetail.getAmountPerPackage();
				materialOrderDetail.setPackageAmount(pkgAmount);
			}
		}
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findApplyMasterList(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤已审核的特殊入库申请单");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = materialApplyMasterDAO.findCheckedByCondition(start,
				limit, SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List<MaterialApplyMaster>) objs[1]);
		return ro;
	}

	@Override
	public ReObject findApplyDetailList(String fstrAutoId) {
		ReObject ro = new ReObject("根据特殊入库申请主记录ID查询申请明细列表");
		List<MaterialApplyDetail> data = materialApplyDetailDAO
				.findByMainAutoId(fstrAutoId);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前采购入库单的详细信息记录");
		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		data.add(objs[1]);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsDetailByMainAutoId(String fstrAutoId) {
		ReObject ro = new ReObject("根据主记录ID查询收发存明细");
		List<MaterialRdsDetail> data = materialRdsDetailDAO
				.findByMainAutoId(fstrAutoId);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的采购入库单据列表");
		List<Object> data = materialRdsMasterDAO
				.findAutoIdsByPurchaseCondition(SessionUtil.getUnitsCode(),
						fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findRdsMasterOtherByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询盘盈入库(104)的生单");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = materialRdsMasterDAO
				.findUnverifiedMasterByCheckReceiveCondition(start, limit,
						SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findRdsMasterOtherList(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的其他入库单据列表");
		List<Object> data = materialRdsMasterDAO
				.findAutoIdsByOtherReceiveCondition(SessionUtil.getUnitsCode(),
						fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveRds(MaterialRdsMaster fmaster,
			List<MaterialRdsDetail> fdetails) {
		ReObject ro = new ReObject("保存当前采购入库信息");
		String operationType = fmaster.getOperationType();
		if (operationType == null) {
			throw new RuntimeException("业务类型不能为空！");
		}
		if (!operationType.equals(RdConstant.R_PURCHASE)
				&& !operationType.equals(RdConstant.R_AGENCY)
				&& !operationType.equals(RdConstant.R_DIRECT)
				&& !operationType.equals(RdConstant.R_SPECIAL)) {
			throw new RuntimeException("业务类型必须为101或102或103或106！");
		}
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(unitsCode,
				appCode, RdConstant.SYS_PARA_CODE_R_PURCHASE_CHK, "0"));
		// 校验供应商授权物资，并初始采购入库业务的明细记录的已开票数和已开票标志
		String salerCode = fmaster.getSalerCode();
		// 是否来自订单
		boolean isFromOrder = fmaster.getOrderNo() != null
				&& !"".equals(fmaster.getOrderNo());
		// 是否来自特殊入库申请单
		boolean isFromSpecial = RdConstant.R_SPECIAL.equals(fmaster
				.getOperationType());
		// 是否来自到货单
		boolean isFromArrival = fmaster.getArrivalNo() != null
				&& !"".equals(fmaster.getArrivalNo());
		// 是否来自手工填单
		boolean isFromManual = !isFromOrder && !isFromSpecial && !isFromArrival;
		// 是否校验供应商授权物资
		boolean isNeedAuthorized = "1".equals(cdSysParamDAO.findByParaCode(
				unitsCode, appCode,
				RdConstant.SYS_PARA_CODE_MATERIAL_AUTHORIZATION, "0"));
		for (MaterialRdsDetail detail : fdetails) {
			if (isNeedAuthorized && salerCode != null && !"".equals(salerCode)
					&& isFromManual) {
				String materialId = detail.getMaterialId();
				if (!cdProviderMaterialDAO.checkSaleProductPrivilege(
						SessionUtil.getUnitsCode(), salerCode, materialId)) {
					throw new RuntimeException("供应商[" + salerCode + "]没有物资["
							+ materialId + "]的经销授权或授权过期，不能入库！");
				}
			}
			//票面数量
			if (detail.getAcctAmount() == null
					|| detail.getAcctAmount().isNaN()) {
				detail.setAcctAmount(0d);
			}
			
			if (detail.getInvoiceAmount() == null
					|| detail.getInvoiceAmount().isNaN()) {
				detail.setInvoiceAmount(0d);
			}
			if (detail.getInvoiceAmount() < detail.getAmount()) {
				detail.setInvoiceSign("0");
			} else {
				detail.setInvoiceSign("1");
			}
			detail.setAcctBillNo("0");
			detail.setRdBillNo("0");
			detail.setCurrentStatus("0");
		}
		fmaster.setCurrentStatus(isVerified ? "1" : "0");
		fmaster.setRdFlag(RdConstant.R);
		String mainAutoId = fmaster.getAutoId();
		if (mainAutoId != null && !"".equals(mainAutoId)) {
			// 修改，先还原来源单据所对应的记录
			if (isFromOrder) {
				// 还原订单
				List<MaterialRdsDetail> originalDetails = materialRdsDetailDAO
						.findByMainAutoId(mainAutoId);
				for (MaterialRdsDetail originalDetail : originalDetails) {
					updateInputAmount(false, false, originalDetail);
				}
			} else if (isFromSpecial) {
				// 还原特殊入库
				// do nothing
			} else if (isFromArrival) {
				// 还原到货单
				// do nothing
			}
		}
		commMaterialServiceImpl.save(fmaster, fdetails);
		// 采购入库时，更新来源单据所对应的记录
		if (isFromOrder) {
			// 更新订单
			for (MaterialRdsDetail detail : fdetails) {
				updateInputAmount(true, true, detail);
			}
		} else if (isFromSpecial) {
			// 更新特殊入库
			updateApplyStatus(true, unitsCode, fmaster.getOperationNo());
		} else if (isFromArrival) {
			// 更新到货单
			// do nothing
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	/**
	 * 更新特殊入库当前状态
	 * 
	 * @param isPlus
	 *            是更新还是还原，保存时是更新；删除时是还原
	 * @param unitsCode
	 * @param billNo
	 */
	private void updateApplyStatus(boolean isPlus, String unitsCode,
			String billNo) {
		MaterialApplyMaster applyMaster = materialApplyMasterDAO.findByBillNo(
				unitsCode, billNo);
		if (applyMaster == null) {
			log.warn("不存在特殊入库申请单[单据号:" + billNo + "],无法更新入库状态！");
		} else {
			applyMaster.setCurrentStatus(isPlus ? "2" : "1");
		}
	}

	/**
	 * 更新采购订单已入库数和状态
	 * 
	 * @param isPlus
	 *            是更新还是还原，保存时是更新；删除时是还原
	 * @param isUpdateCurrentStatus
	 *            是否更新状态
	 * @param detail
	 *            入库明细
	 */
	private void updateInputAmount(boolean isPlus,
			boolean isUpdateCurrentStatus, MaterialRdsDetail detail) {
		String orderDetailAutoId = detail.getSourceAutoId();
		if (orderDetailAutoId != null && !"".equals(orderDetailAutoId)) {
			MaterialOrderDetail orderDetail = materialOrderDetailDAO
					.findById(orderDetailAutoId);
			if (orderDetail == null) {
				log.warn("不存在采购订单明细记录[ID:" + orderDetailAutoId
						+ "]，无法更新已入库数和执行状态！");
			} else {
				Double inputAmount = orderDetail.getInputAmount();
				if (inputAmount == null)
					inputAmount = 0d;
				if (isPlus)
					inputAmount += detail.getAmount();
				else
					inputAmount -= detail.getAmount();
				orderDetail.setInputAmount(inputAmount);
				if (isUpdateCurrentStatus) {
					if (isPlus) {
						if (inputAmount.equals(orderDetail.getAmount())) {
							// 当数量=已开票数时,已开票标志为1
							orderDetail.setCurrentStatus("3");
						} else {
							orderDetail.setCurrentStatus("2");
						}
					} else {
						if (inputAmount <= 0) {
							orderDetail.setCurrentStatus("1");
						} else {
							orderDetail.setCurrentStatus("2");
						}
					}
				}
			}
		} else {
			log.warn("入库单来源于订单，但入库单明细记录的来源系统标识号sourceAutoId为空，无法更新已入库数和执行状态！");
		}
	}

	@Override
	public ReObject verifyRds(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的入库单信息");
		Object[] objs = commMaterialServiceImpl.verify(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject cancelVerifyRds(String fstrAutoId){
		ReObject ro = new ReObject("弃审");
		commMaterialServiceImpl.cancelVerify(fstrAutoId);
		return ro;
	}

	@Override
	public ReObject saveInitialRds(MaterialRdsMaster fmaster,
			List<MaterialRdsDetail> fdetails) {
		ReObject ro = new ReObject("保存当前期初入库信息");
		fmaster.setOperationType(RdConstant.R_INITIAL);
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		if(materialRdsDetailDAO.hasOtherRD(unitsCode, fmaster.getStorageCode())){
			throw new RuntimeException("系统已开始业务，不能再进行期初录入！");
		}
//		for(MaterialRdsDetail detail : fdetails){
//			if(materialRdsDetailDAO.hasOtherRD(unitsCode,fmaster.getStorageCode(),detail.getMaterialId())){
//				throw new RuntimeException("物资"+detail.getMaterialName()+"["+detail.getMaterialCode()+"]已有入出库记录，不能期初入库，请做其他入库处理！");
//			}
//		}
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(unitsCode,
				appCode, RdConstant.SYS_PARA_CODE_INITIAL_CHK, "0"));
		fmaster.setCurrentStatus(isVerified ? "1" : "0");
		fmaster.setRdFlag(RdConstant.R);
		commMaterialServiceImpl.save(fmaster, fdetails);
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}
	@Override
	public ReObject verifyInitialRds(MaterialRdsMaster fmaster,
			List<MaterialRdsDetail> details) {
			ReObject ro = new ReObject("审核期初入库信息");
			Object[] objs = new Object[2];
			SysUser user = SessionUtil.getSysUser();
			String unitsCode = user.getUnitsCode();
			String storageCode =  fmaster.getStorageCode();
			String materialId = "";
			String batch = "0";
			//其次明细，更新明细中的核算数量,同时将状态直接改为2，已核算
			for (MaterialRdsDetail materialRdsDetail : details) {
				materialId = materialRdsDetail.getMaterialId();
				batch = materialRdsDetail.getBatch();
				materialRdsDetail.setAcctAmount(materialRdsDetail.getAmount());
				materialRdsDetail.setCurrentStatus("2");
				// 更新库存中的核算数量
				MaterialCurrentStock materialCurrentStock = materialCurrentStockDAO
						.findByUnique(unitsCode, storageCode, materialId, batch);
				if (null == materialCurrentStock) {
					// 因弹出提示，并撤消事务
					throw new RuntimeException("当前库存中无" + materialRdsDetail.getMaterialName()
							+ " ，保存失败！");
				}
				materialCurrentStock.setAcctAmount(materialCurrentStock
						.getAcctAmount()
						+ materialRdsDetail.getAmount());
				materialCurrentStockDAO.attachDirty(materialCurrentStock);

			}
			List<Object> data = new ArrayList<Object>();
			objs[0] = fmaster;
			objs[1] = details;
			ro.setData(data);
			return ro;
	}
	@Override
	public ReObject saveOtherRds(MaterialRdsMaster fmaster,
			List<MaterialRdsDetail> fdetails) {
		ReObject ro = new ReObject("保存当前其他入库信息");
		String operationType = fmaster.getOperationType();
		if (!operationType.equals(RdConstant.R_CHECK_PROFIT)
				&& !operationType.equals(RdConstant.R_OTHERS)) {
			throw new RuntimeException("业务类型必须为104或109！");
		}
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(unitsCode,
				appCode, RdConstant.SYS_PARA_CODE_R_OTHERS_CHK, "0"));
		fmaster.setCurrentStatus(isVerified ? "1" : "0");
		fmaster.setRdFlag(RdConstant.R);
		commMaterialServiceImpl.save(fmaster, fdetails);
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsMasterInitialList(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的期初入库单据列表");
		List<Object> data = materialRdsMasterDAO.findAutoIdsByInitialCondition(
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findMaxBatch(String fstrMaterialId){
		ReObject ro = new ReObject("查找最大的批号");
		Object result = materialCurrentStockDAO.findMaxBatchById(fstrMaterialId);
		
		Integer maxBatch=1;
		if(result!=null){
			maxBatch=Integer.valueOf(result.toString())+1;
		}
		List dataList=new ArrayList();
		dataList.add(maxBatch);
		ro.setData(dataList);
		return ro;
	}
}
