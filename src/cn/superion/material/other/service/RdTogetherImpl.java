package cn.superion.material.other.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.provider.dao.CdProviderMaterialDAO;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

public class RdTogetherImpl implements IRdTogether {
	private CdSysParamDAO cdSysParamDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private CdProviderMaterialDAO cdProviderMaterialDAO;
	private ICommMaterialService commMaterialServiceImpl;
	
	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public CdMaterialDictDAO getCdMaterialDictDAO() {
		return cdMaterialDictDAO;
	}

	public void setCdMaterialDictDAO(CdMaterialDictDAO cdMaterialDictDAO) {
		this.cdMaterialDictDAO = cdMaterialDictDAO;
	}

	public MaterialRdsMasterDAO getMaterialRdsMasterDAO() {
		return materialRdsMasterDAO;
	}

	public void setMaterialRdsMasterDAO(MaterialRdsMasterDAO materialRdsMasterDAO) {
		this.materialRdsMasterDAO = materialRdsMasterDAO;
	}

	public MaterialRdsDetailDAO getMaterialRdsDetailDAO() {
		return materialRdsDetailDAO;
	}

	public void setMaterialRdsDetailDAO(MaterialRdsDetailDAO materialRdsDetailDAO) {
		this.materialRdsDetailDAO = materialRdsDetailDAO;
	}

	public CdProviderMaterialDAO getCdProviderMaterialDAO() {
		return cdProviderMaterialDAO;
	}

	public void setCdProviderMaterialDAO(CdProviderMaterialDAO cdProviderMaterialDAO) {
		this.cdProviderMaterialDAO = cdProviderMaterialDAO;
	}

	public ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	@Override
	public ReObject saveRdTogether(MaterialRdsMaster receiveMaster,
			MaterialRdsMaster deliverMaster, List<MaterialRdsDetail> fdetails) {
		ReObject ro = new ReObject("保存整进整出");
		String operationType = receiveMaster.getOperationType();
		if (operationType == null) {
			throw new RuntimeException("业务类型不能为空！");
		}
		if (!operationType.equals(RdConstant.R_PURCHASE)
				&& !operationType.equals(RdConstant.R_AGENCY)
				&& !operationType.equals(RdConstant.R_DIRECT)
				&& !operationType.equals(RdConstant.R_SPECIAL)
				&& !operationType.equals(RdConstant.R_TOGETHER)) {
			throw new RuntimeException("业务类型必须为101或102或103或106或110！");
		}
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(unitsCode,
				appCode, RdConstant.SYS_PARA_CODE_R_PURCHASE_CHK, "0"));
		// 校验供应商授权物资，并初始采购入库业务的明细记录的已开票数和已开票标志
		String salerCode = receiveMaster.getSalerCode();
		// 是否来自订单
		boolean isFromOrder = receiveMaster.getOrderNo() != null
				&& !"".equals(receiveMaster.getOrderNo());
		// 是否来自特殊入库申请单
		boolean isFromSpecial = RdConstant.R_SPECIAL.equals(receiveMaster
				.getOperationType());
		// 是否来自到货单
		boolean isFromArrival = receiveMaster.getArrivalNo() != null
				&& !"".equals(receiveMaster.getArrivalNo());
		// 是否来自手工填单
		boolean isFromManual = !isFromOrder && !isFromSpecial && !isFromArrival;
		// 是否校验供应商授权物资
		boolean isNeedAuthorized = "1".equals(cdSysParamDAO.findByParaCode(
				unitsCode, appCode,
				RdConstant.SYS_PARA_CODE_MATERIAL_AUTHORIZATION, "0"));
		
		//出库明细
		List<MaterialRdsDetail> deliverDetails=new ArrayList<MaterialRdsDetail>();
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
			
			MaterialRdsDetail dDeliver=new MaterialRdsDetail();
			try {
				dDeliver=(MaterialRdsDetail) BeanUtils.cloneBean(detail);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			deliverDetails.add(dDeliver);
		}
		receiveMaster.setCurrentStatus(isVerified ? "1" : "0");
		receiveMaster.setRdFlag(RdConstant.R);
		//保存入库记录
		save(receiveMaster, fdetails);
	
		String receiveMainAutoId = receiveMaster.getAutoId();
		for(MaterialRdsDetail dDetail:deliverDetails){
			dDetail.setSourceInputAutoId(receiveMainAutoId);
		}
		//保存出库记录
		save(deliverMaster, deliverDetails);
		
		String deliverMainAutoId=deliverMaster.getAutoId();
		for(MaterialRdsDetail rDetail:fdetails){
			rDetail.setSourceInputAutoId(deliverMainAutoId);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(receiveMaster);
		data.add(deliverMaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	private Object[] save(MaterialRdsMaster fmaster,
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
		Date curDate = new Date();
		// 判断新增或修改状态
		boolean isAdd = true;
		String mainAutoId = fmaster.getAutoId();
		isAdd = mainAutoId == null || "".equals(mainAutoId);
		boolean isVerified = "1".equals(fmaster.getCurrentStatus());
		if (isVerified) {
			fmaster.setVerifier(personId);
			fmaster.setVerifyDate(curDate);
		}
		if (isAdd) {
			// 新增
			// 设置流水号和流水日期
			String billNo = fmaster.getBillNo();
			String billMonthNo = fmaster.getBillMonthNo();//byzcl
			if(billMonthNo == null || "".equals(billMonthNo)){
				fmaster.setBillMonthNo(commMaterialServiceImpl.getNextBillMonthNo(fmaster.getRdFlag(),fmaster.getRdType(), fmaster
						.getStorageCode()));
			}
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(commMaterialServiceImpl.getNextBillNo(fmaster.getRdFlag(),fmaster.getRdType(), fmaster
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
			fmaster.setUnitsCode(unitsCode);
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
			detail.setMainAutoId(mainAutoId);
			detail.setCurrentStockAmount(0d);
			materialRdsDetailDAO.save(detail);
			
		}
		objs[0] = fmaster;
		objs[1] = fdetails;
		return objs;
	}

	@Override
	public ReObject findRdTogetherByAutoId(String fstrMainAutoId) {
		ReObject ro = new ReObject("查询整进整出单据的详细信息");
		Object[] objs = commMaterialServiceImpl.findById(fstrMainAutoId);
		Object[] objs1 = new Object[2];//byzcl
		
		//入库单据
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		data.add(objs[1]);

		//出库单据
		if(objs[1]!=null){
			List details=(List) objs[1];
			MaterialRdsDetail dDetail=(MaterialRdsDetail) details.get(0);
			String dAutoId=dDetail.getSourceInputAutoId();
			if(dAutoId!=null && !dAutoId.equals("")){
				objs1 = commMaterialServiceImpl.findById(dAutoId);
				data.add(objs1[0]);
			}
		}
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject deleteRds(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的整进整出单据");

		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		MaterialRdsMaster master = (MaterialRdsMaster) objs[0];
		
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的入库主记录！");
		}
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已审核，不能删除！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已记帐，不能删除！");
		}
		objs = commMaterialServiceImpl.findById(fstrAutoId);
		//审核出库单据
		if(objs[1]!=null){
			List<MaterialRdsDetail> details=(List<MaterialRdsDetail>) objs[1];
			String dAutoId=details.get(0).getSourceInputAutoId();
			commMaterialServiceImpl.deleteByAutoId(dAutoId);
		}
		commMaterialServiceImpl.deleteByAutoId(fstrAutoId);
		
		return ro;
	}
	
	@Override
	public ReObject verifyRds(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的整进整出信息");
		
		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		//审核出库单据
		if(objs[1]!=null){
			List<MaterialRdsDetail> details=(List<MaterialRdsDetail>) objs[1];
			String dAutoId=details.get(0).getSourceInputAutoId();
			verify(dAutoId);
		}

		objs = verify(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(objs[0]);
		ro.setData(data);
		return ro;
	}
	
	private Object[] verify(String fstrAutoId) {
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
		String personId = user.getPersonId();
		master.setCurrentStatus("1");
		master.setVerifier(personId);
		master.setVerifyDate(new Date());
		List<MaterialRdsDetail> details = materialRdsDetailDAO
				.findByMainAutoId(master.getAutoId());
		for (MaterialRdsDetail detail : details) {
			detail.setCurrentStatus("1");
		}
		objs[0] = master;
		objs[1] = details;
		return objs;
	}
	
	@Override
	public ReObject cancelVerifyRds(String fstrAutoId){
		ReObject ro = new ReObject("弃审整进整出单据");
		
		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		//弃审出库单据
		if(objs[1]!=null){
			List<MaterialRdsDetail> details=(List<MaterialRdsDetail>) objs[1];
			String dAutoId=details.get(0).getSourceInputAutoId();
			cancelVerify(dAutoId);
		}
		
		//弃审入库单据
		cancelVerify(fstrAutoId);
		return ro;
	}

	private void cancelVerify(String fstrAutoId){
		MaterialRdsMaster master = materialRdsMasterDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("收发存主记录不存在，不能弃审！");
		}
		if ("0".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入出库未审核，不能弃审！");
		}
		List<MaterialRdsDetail> details = materialRdsDetailDAO
		.findByMainAutoId(master.getAutoId());
		for (MaterialRdsDetail detail : details) {
			String trimedAcctBillNo = StringUtils.trim(detail.getAcctBillNo());
			if(!"".equals(trimedAcctBillNo)&&!"0".equals(trimedAcctBillNo)){
				throw new RuntimeException("物资"+detail.getMaterialName()+"["+detail.getMaterialCode()+"已记账，不能弃审！");
			}
			detail.setCurrentStatus("0");
		}
		master.setCurrentStatus("0");
		master.setVerifier(null);
		master.setVerifyDate(null);
	}
	
	@Override
	public ReObject findRdsMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条件查询整进整出单据列表");
		Map<String, Object> condition=fparameter.getConditions();
		condition.put("remark", "整进整出");
		List<Object> data = materialRdsMasterDAO
				.findAutoIdsByPurchaseConditionToger(SessionUtil.getUnitsCode(),
						condition);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject updateRdsPrintSign(String fstrAutoId) {
		ReObject ro = new ReObject("更新打印状态！");
		commMaterialServiceImpl.updatePrintSign(fstrAutoId);
		return ro;
	}
}
