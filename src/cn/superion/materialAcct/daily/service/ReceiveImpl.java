package cn.superion.materialAcct.daily.service;

/**
 * 采购入库单记录服务实现
 * 
 * @author 李攀
 * 修改者：周作建 2011.06.12
 * 
 */
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.dao.MaterialAcctCurrentRcptNoDAO;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialRdsAcctMasterDAO;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialAcctCurrentRcptNo;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialRdsAcctMaster;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.materialDept.dao.VMaterialPatsDAO;
import cn.superion.materialDept.entity.VMaterialPats;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;
import cn.superion.util.StringUtil;

public class ReceiveImpl implements IReceive {
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO;
	private MaterialAcctCurrentRcptNoDAO materialAcctCurrentRcptNoDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private CdSysParamDAO cdSysParamDAO;
	private CdMaterialDictDAO cdMaterialDictDAO;
	private VMaterialPatsDAO vMaterialPatsDAO;

	public VMaterialPatsDAO getvMaterialPatsDAO() {
		return vMaterialPatsDAO;
	}

	public void setvMaterialPatsDAO(VMaterialPatsDAO vMaterialPatsDAO) {
		this.vMaterialPatsDAO = vMaterialPatsDAO;
	}

	@Override
	public ReObject findByCondition(ParameterObject fparameter) {
		ReObject reObject = new ReObject();
		reObject.setAction("查询采购入库单");

		Map<String, Object> map = fparameter.getConditions();
		String storageCode = (String) map.get("storageCode");
		String salerCode = (String) map.get("salerCode");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		String invoiceNo = (String) map.get("invoiceNo");
		String isGive = (String) map.get("isGive");

		StringBuilder conditions = new StringBuilder();

//		String unitsCode = SessionUtil.getUnitsCode();

		conditions.append("where mainAutoId in (select autoId from MaterialRdsMaster");
		//特殊高值耗材在北院虚拟入库后，在南院对其进行实际出入库 ryh 2012.12.11
//		conditions.append(" where unitsCode='" + unitsCode + "'");
		// 仓库编码
		if (storageCode != null && storageCode.length() > 0) {
			conditions.append(" where storageCode='" + storageCode + "'");
		}
		// 供应商
		if (salerCode != null && salerCode.length() > 0) {
			conditions.append(" and salerCode='" + salerCode + "'");
		}
		// 日期范围
		conditions.append(" and billDate >= to_date('" + startDate
				+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		conditions.append(" and billDate <= to_date('" + endDate + " 23:59:59"
				+ "','yyyy-mm-dd hh24:mi:ss')");
		// 附加条件--主记录
		conditions.append(" and rdFlag='1'");
		conditions.append(" and currentStatus='1')");
		// 附加条件--明细记录
		// 发票号
		if (invoiceNo != null && invoiceNo.length() > 0) {
			conditions.append(" and invoiceNo='" + invoiceNo + "'");
		}

		//是否赠送的耗材 ryh 2012.10.21
		if (isGive != null && isGive.equals("1")) {
			conditions.append(" and isGive='1'");
		}
		else{
			conditions.append(" and (isGive is null or isGive='0')");
		}
		conditions.append(" and acctBillNo='0' and currentStatus = '1'");

//		conditions.append(" order by mainAutoId,serialNo");7-18按照日期升序排
		conditions.append(" order by invoiceDate ,autoId ");

		List<MaterialRdsDetail> lstMaterialRdsDetails = materialRdsDetailDAO
				.findByCondition(conditions.toString());
		
		//特殊高值耗材需要查询病人住院号、姓名 2012.12.24 ryh
		for(MaterialRdsDetail detail:lstMaterialRdsDetails){
			String highSign=detail.getHighValueSign()==null ? "" : detail.getHighValueSign();
			String agentSign=detail.getAgentSign()==null ? "" : detail.getAgentSign();
			String barCode=detail.getBarCode()==null ? "0" : detail.getBarCode();
			if(highSign.equals("1") && agentSign.equals("1") && !barCode.equals("0")){
				List<VMaterialPats> pats=vMaterialPatsDAO.findByBarCode(barCode);
				if(pats!=null && pats.size()>0){
					detail.setPatientId(pats.get(0).getPatientId());
					detail.setPersonName(pats.get(0).getPersonName());
				}
			}
		}
		reObject.setData(lstMaterialRdsDetails);

		return reObject;
	}

	public ReObject save(List<MaterialRdsDetail> details) {
		ReObject reObject = new ReObject();
		reObject.setAction("保存采购入库单");

		Double tradeMoney = 0d;
		for (MaterialRdsDetail detail : details) {
			// 已经核算过的入库单号
			if (null != detail.getAcctBillNo()
					&& !detail.getAcctBillNo().equals("0")) {
				continue;
			}
			// 如为空，置入库数量
			if (detail.getAcctAmount() == null ) {
				detail.setAcctAmount(detail.getAmount());
			}
			// 进价金额
			if(detail.getTradePrice() !=null){
				tradeMoney = detail.getAcctAmount() * detail.getTradePrice();
			}
			
			//实价
			detail.setTradeMoney(tradeMoney);
			// 更新当前状态
			if (null != detail.getTradePrice()) {
				detail.setCurrentStatus("2");
			}
			// 核算单号
			detail.setAcctBillNo("0");
			// 出入库号
			detail.setRdBillNo("0");
			// 更新
			materialRdsDetailDAO.attachDirty(detail);
		}
		//
		return reObject;
	}

	@Override
	public ReObject findReportByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("查询采购入库单");

		Map<String, Object> map = fparameter.getConditions();
		String storageCode = (String) map.get("storageCode");
		String salerCode = (String) map.get("salerCode");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		String isGive = (String) map.get("isGive");
		String invoiceNo = (String)map.get("invoiceNo");

		StringBuilder conditions = new StringBuilder();

//		String unitsCode = SessionUtil.getUnitsCode();

		conditions.append("where mainAutoId in (select autoId from MaterialRdsMaster");
//		conditions.append(" where unitsCode='" + unitsCode + "'");
		// 仓库编码
		if (storageCode != null && storageCode.length() > 0) {
			conditions.append(" where storageCode='" + storageCode + "'");
		}
		// 供应商
		if (salerCode != null && salerCode.length() > 0) {
			conditions.append(" and salerCode='" + salerCode + "'");
		}
		// 日期范围
		conditions.append(" and billDate >= to_date('" + startDate
				+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		conditions.append(" and billDate <= to_date('" + endDate + " 23:59:59"
				+ "','yyyy-mm-dd hh24:mi:ss')");
		// 附加条件--主记录
		conditions.append(" and rdFlag='1'");
		conditions.append(" and currentStatus='1')");
		// 附加条件--明细记录
		//发票号查询
		if(invoiceNo != null && !"".equals(invoiceNo)){
			conditions.append(" and invoiceNo = '"+invoiceNo+"'");
		}
		//是否赠送的耗材 ryh 2012.10.21
		if (isGive != null && isGive.equals("1")) {
			conditions.append(" and isGive='1'");
		}
		else{
			conditions.append(" and (isGive is null or isGive='0')");
		}
		conditions.append(" and acctBillNo='0' and currentStatus = '2'");

		conditions.append(" order by mainAutoId,serialNo");

		List<MaterialRdsDetail> lstMaterialRdsDetails = materialRdsDetailDAO
				.findByCondition(conditions.toString());
		
		//特殊高值耗材需要查询病人住院号、姓名 2012.12.24 ryh
		for(MaterialRdsDetail detail:lstMaterialRdsDetails){
			String highSign=detail.getHighValueSign()==null ? "" : detail.getHighValueSign();
			String agentSign=detail.getAgentSign()==null ? "" : detail.getAgentSign();
			String barCode=detail.getBarCode()==null ? "0" : detail.getBarCode();
			if(highSign.equals("1") && agentSign.equals("1") && !barCode.equals("0")){
				List<VMaterialPats> pats=vMaterialPatsDAO.findByBarCode(barCode);
				if(pats!=null && pats.size()>0){
					detail.setPatientId(pats.get(0).getPatientId());
					detail.setPersonName(pats.get(0).getPersonName());
				}
			}
		}
		
		reObject.setData(lstMaterialRdsDetails);

		return reObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject saveReport(ParameterObject fparameter,
			List<MaterialRdsDetail> details) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("保存采购入库单");

		Map<String, Object> map = fparameter.getConditions();
		String storageCode = (String) map.get("storageCode");
		String salerCode = (String) map.get("salerCode");
		String salerName = (String) map.get("salerName");
		String salerSign = (String) map.get("salerSign");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");

		String unitsCode = SessionUtil.getUnitsCode();

		// 获取当前核算系统标识号
		String autoId = materialRdsAcctMasterDAO.nextValue(
				"SEQ_MATERIAL_RDS_ACCT_MASTER").toString();
		// 入出库号生成规则
		String rcptType = cdSysParamDAO
				.findByParaCode(unitsCode, "892", "0101","1");
		String typeDate = "";
		if (rcptType.equals("1")) {
			typeDate = getTypeDate("yyyyMMdd");
		} else if (rcptType.equals("2")) {
			typeDate = getTypeDate("yyyyMM");
		} else {
			typeDate = getTypeDate("yyyy");
		}
		// 单据号前缀
		String currentPrefix = cdSysParamDAO.findByParaCode(unitsCode, "892",
				"0102","N") + "R";
		// 每页打印行数
		int pageRow = Integer.parseInt(cdSysParamDAO.findByParaCode(unitsCode,
				"892", "0103"));
		if (pageRow < 1) {
			pageRow = 10;
		}
		// 读取当前最大单据号
		String rdBillNo = "0000000000";
//		MaterialAcctCurrentRcptNo maCurrentRcptNo = rcptType.equals("4")?materialAcctCurrentRcptNoDAO
//				.findByUniqueIndex2(unitsCode, "1", rcptType,storageCode):materialAcctCurrentRcptNoDAO
//				.findByUniqueIndex(unitsCode, "1", rcptType, typeDate);
		List<MaterialAcctCurrentRcptNo> nos = materialAcctCurrentRcptNoDAO
		.findByUniqueIndex3(unitsCode, "1");
		MaterialAcctCurrentRcptNo maCurrentRcptNo = null;
		if(nos.size()== 0){
			maCurrentRcptNo = new MaterialAcctCurrentRcptNo();
			String currentRcptNoAutoId = materialAcctCurrentRcptNoDAO
					.nextValue("SEQ_MATERAIL_ACCT_CURRENT_RCPT").toString();
			maCurrentRcptNo.setAutoId(currentRcptNoAutoId);
			maCurrentRcptNo.setUnitsCode(unitsCode);
			maCurrentRcptNo.setRcptType(rcptType);
			maCurrentRcptNo.setRdFlag("1");
			if (rcptType.equals("4")) {
				maCurrentRcptNo.setTypeDate("0");
				maCurrentRcptNo.setStorageCode(storageCode);
			}else{
				maCurrentRcptNo.setTypeDate(typeDate);
			}
			maCurrentRcptNo.setCurrentNo(rdBillNo);
		}
//		if (maCurrentRcptNo == null) {
//			
//		}
		else {
			maCurrentRcptNo = nos.get(0);
			rdBillNo = maCurrentRcptNo.getCurrentNo();
		}
		String currentRdBillNo = null,minRdBillNo=null,maxRdBillNo=null;
		int i = 1;
		String mainAutoId = "";
		String materialId;
		String batch = "0";
		BigDecimal tradeSum = new BigDecimal("0");
		BigDecimal factTradeSum = new BigDecimal("0");
		BigDecimal wholeSaleSum = new BigDecimal("0");
		BigDecimal inviteSum = new BigDecimal("0");
		BigDecimal retailSum = new BigDecimal("0");
		for (MaterialRdsDetail detail : details) {
			materialId = detail.getMaterialId();
			batch = detail.getBatch();
			if (i % pageRow == 1) {
				// 新的一行，入库号进行改变
				rdBillNo = String.valueOf(Long.parseLong(rdBillNo) + 1);
				rdBillNo = StringUtil.lpad(rdBillNo, 10);
				currentRdBillNo = currentPrefix + rdBillNo;
				minRdBillNo = maxRdBillNo = currentRdBillNo;
			}
			i++;
			// 查找物资字典中的最新扣率
			List<CdMaterialDict> materialDictList = cdMaterialDictDAO.findByProperty("materialId", materialId);
			if (materialDictList == null || materialDictList.size()==0 || materialDictList.get(0)==null) {
				// 因弹出提示，并撤消事务
				throw new RuntimeException("物资字典中无" + detail.getMaterialName()
						+ " ，保存失败！");
			}
			CdMaterialDict cdMaterialDict=materialDictList.get(0);
			// 更新当前实价、扣率、核算单号、入库号
			detail.setFactTradePrice(detail.getTradePrice()
					* cdMaterialDict.getRebateRate());
			detail.setFactTradeMoney(detail.getFactTradePrice()
					* detail.getAcctAmount());
			detail.setRebateRate(cdMaterialDict.getRebateRate());
			detail.setAcctBillNo(autoId);
			detail.setRdBillNo(currentRdBillNo);

			materialRdsDetailDAO.attachDirty(detail);
			// 更新物资字典中的最新进价
			cdMaterialDict.setTradePrice(detail.getTradePrice());
			cdMaterialDictDAO.attachDirty(cdMaterialDict);
			//去掉库存量操作  byzcl
//			// 更新库存中的最新进价、核算数量
//			MaterialCurrentStock materialCurrentStock = null;
//			//高值耗材 ryh 2012.10.18
//			if(detail.getHighValueSign() != null && detail.getHighValueSign().equals("1")){
//				if(detail.getBarCode()!=null && !detail.getBarCode().equals("0")){
//					materialCurrentStock = materialCurrentStockDAO
//					.findBarCodeByUnique(unitsCode, storageCode, materialId, batch,detail.getBarCode());
//				}
//			}
//			else
//			{
//				materialCurrentStock = materialCurrentStockDAO
//				.findByUnique(unitsCode, storageCode, materialId, batch);
//			}
//			//非特殊高值耗材 ryh 2012.10.18
//			if(detail.getHighValueSign()==null || detail.getHighValueSign().equals("0")){
//				if (null == materialCurrentStock) {
//					// 因弹出提示，并撤消事务
//					throw new RuntimeException("当前库存中无" + detail.getMaterialName()
//							+ " ，保存失败！");
//				}
//				materialCurrentStock.setTradePrice(detail.getTradePrice());
//				materialCurrentStock.setAcctAmount(materialCurrentStock
//						.getAcctAmount()
//						+ detail.getAcctAmount());
//				materialCurrentStockDAO.attachDirty(materialCurrentStock);
//			}
			

			// 更新出库单中的进价
			updateRdsDetail(unitsCode, storageCode, salerCode, detail);
			// 更新主记录中当前状态
			if (!detail.getMainAutoId().equals(mainAutoId)) {
				mainAutoId = detail.getMainAutoId();
				//该单据对应的明细全部核算过，则改写主记录 的currentStatus为2已记账；
				//
				List<MaterialRdsDetail> detail2 = materialRdsDetailDAO.findByMainAutoIdAndCurrentStatus(mainAutoId, "1");
				if(detail2.size() == 0 ){
					updateRdsMaster(mainAutoId);
				}
			}
			// 计算当前总价
			tradeSum = tradeSum .add( new BigDecimal(detail.getTradeMoney()));
			factTradeSum = factTradeSum.add(new BigDecimal(detail.getFactTradeMoney())) ;
			wholeSaleSum = wholeSaleSum.add(new BigDecimal(detail.getWholeSaleMoney())) ;
			inviteSum = inviteSum.add(new BigDecimal(detail.getInviteMoney())) ;
			retailSum = retailSum.add(new BigDecimal(detail.getRetailMoney())) ;
		}
		// 更新当前最大入库流水号
		maCurrentRcptNo.setCurrentNo(rdBillNo);
		materialAcctCurrentRcptNoDAO.attachDirty(maCurrentRcptNo);
		// 写核算主记录
		MaterialRdsAcctMaster materialRdsAcctMaster = new MaterialRdsAcctMaster();
		materialRdsAcctMaster.setAutoId(autoId);
		materialRdsAcctMaster.setUnitsCode(unitsCode);
		materialRdsAcctMaster.setStorageCode(storageCode);
		materialRdsAcctMaster.setRdFlag("1");
		if (salerSign.equals("1")) {
			materialRdsAcctMaster.setSalerCode(salerCode);
			materialRdsAcctMaster.setSalerName(salerName);
		} else {
			materialRdsAcctMaster.setDeptCode(salerCode);
			materialRdsAcctMaster.setDeptName(salerName);
		}
		materialRdsAcctMaster.setBillDate1(DateUtil
				.getDateComeString(startDate));
		materialRdsAcctMaster.setBillDate2(DateUtil.getDateComeString(endDate));
		if (details.size() == 0) {
			minRdBillNo = maxRdBillNo = details.get(0).getRdBillNo();
			materialRdsAcctMaster.setMinRdBillNo(minRdBillNo);
			materialRdsAcctMaster.setMaxRdBillNo(maxRdBillNo);
		} else {
			minRdBillNo = details.get(0).getRdBillNo();
			maxRdBillNo = details.get(details.size() - 1).getRdBillNo();
			materialRdsAcctMaster.setMinRdBillNo(minRdBillNo);
			materialRdsAcctMaster.setMaxRdBillNo(maxRdBillNo);
		}
		materialRdsAcctMaster.setTradeSum(tradeSum.doubleValue());
		materialRdsAcctMaster.setFactTradeSum(factTradeSum.doubleValue());
		materialRdsAcctMaster.setWholeSaleSum(wholeSaleSum.doubleValue());
		materialRdsAcctMaster.setInviteSum(inviteSum.doubleValue());
		materialRdsAcctMaster.setRetailSum(retailSum.doubleValue());
		materialRdsAcctMaster.setAccounter(SessionUtil.getPersonId());
		materialRdsAcctMaster.setAccountDate(new Date());

		materialRdsAcctMasterDAO.save(materialRdsAcctMaster);
		// 返回核算主记录、入库单明细记录；
		Map<String, Object> mapReturn = new HashMap<String, Object>();
		mapReturn.put("master", materialRdsAcctMaster);
		mapReturn.put("detail", details);
		List data = new ArrayList();
		data.add(mapReturn);
		reObject.setData(data);
		return reObject;
	}

	/**
	 * 获取日期格式
	 * 
	 * @param fstrDateFormat
	 * @return
	 */
	private String getTypeDate(String fstrDateFormat) {
		java.util.Date dt = Calendar.getInstance().getTime();
		SimpleDateFormat df = new SimpleDateFormat(fstrDateFormat);
		return df.format(dt);
	}

	/**
	 * 更新出库单中的进价
	 * 
	 * @param unitsCode
	 * @param storageCode
	 * @param salerCode
	 * @param detail
	 */
	private void updateRdsDetail(String unitsCode, String storageCode,
			String salerCode, MaterialRdsDetail detail) {
		StringBuilder conditions = new StringBuilder();

		conditions.append(" set tradePrice='" + detail.getTradePrice() + "',");
		conditions.append(" tradeMoney='" + detail.getTradePrice()
				* detail.getAcctAmount() + "',");
		conditions.append(" factTradePrice='" + detail.getTradePrice()
				* detail.getRebateRate() + "',");
		conditions.append(" factTradeMoney='" + detail.getTradePrice()
				* detail.getRebateRate() * detail.getAcctAmount() + "',");
		conditions.append(" rebateRate='" + detail.getRebateRate() + "'");

		conditions
				.append(" where mainAutoId in (select autoId from MaterialRdsMaster");
		conditions.append(" where unitsCode='" + unitsCode + "'");
		// 仓库编码
		if (storageCode != null && storageCode.length() > 0) {
			conditions.append(" and storageCode='" + storageCode + "'");
		}
		// 供应商
		if (salerCode != null && salerCode.length() > 0) {
			conditions.append(" and salerCode='" + salerCode + "'");
		}
		// 附加条件--主记录
		conditions.append(" and rdFlag='1'");
		conditions.append(" and currentStatus='2')");
		// 附加条件--明细记录
		conditions.append(" and materialId='" + detail.getMaterialId() + "'");
		conditions.append(" and factoryCode='" + detail.getFactoryCode() + "'");
		conditions.append(" and batch='" + detail.getBatch() + "'");
		conditions.append(" and acctBillNo='0'");
		//
		materialRdsDetailDAO.updateByCause(conditions.toString());
	}

	private void updateRdsMaster(String mainAutoId) {
		Date today = new Date();
		String personId = SessionUtil.getPersonId();

		materialRdsMasterDAO.updateAcctByAutoId(mainAutoId, personId, today,
				"2");
	}

	public MaterialRdsDetailDAO getMaterialRdsDetailDAO() {
		return materialRdsDetailDAO;
	}

	public void setMaterialRdsDetailDAO(
			MaterialRdsDetailDAO materialRdsDetailDAO) {
		this.materialRdsDetailDAO = materialRdsDetailDAO;
	}

	public MaterialRdsAcctMasterDAO getMaterialRdsAcctMasterDAO() {
		return materialRdsAcctMasterDAO;
	}

	public void setMaterialRdsAcctMasterDAO(
			MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO) {
		this.materialRdsAcctMasterDAO = materialRdsAcctMasterDAO;
	}

	public MaterialAcctCurrentRcptNoDAO getMaterialAcctCurrentRcptNoDAO() {
		return materialAcctCurrentRcptNoDAO;
	}

	public void setMaterialAcctCurrentRcptNoDAO(
			MaterialAcctCurrentRcptNoDAO materialAcctCurrentRcptNoDAO) {
		this.materialAcctCurrentRcptNoDAO = materialAcctCurrentRcptNoDAO;
	}

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

}
