/**
 * 
 */
package cn.superion.materialAcct.daily.service;

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
import cn.superion.center.deptPerson.dao.CdDeptDictDAO;
import cn.superion.center.deptPerson.entity.CdDeptDict;
import cn.superion.material.dao.MaterialAcctCurrentRcptNoDAO;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialRdsAcctMasterDAO;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialAcctCurrentRcptNo;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialRdsAcctMaster;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.materialAcct.daily.entity.DeliverReportAcct;
import cn.superion.materialDept.dao.MaterialPatsMasterDAO;
import cn.superion.materialDept.entity.MaterialPatsMaster;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;
import cn.superion.util.StringUtil;

public class DeliverImpl implements IDeliver {
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private MaterialRdsAcctMasterDAO materialRdsAcctMasterDAO;
	private MaterialAcctCurrentRcptNoDAO materialAcctCurrentRcptNoDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private CdDeptDictDAO cdDeptDictDAO;
	private CdSysParamDAO cdSysParamDAO;
	private MaterialPatsMasterDAO materialPatsMasterDAO;

	@Override
	public ReObject findReportByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("查询出库报告单");

		Map<String, Object> map = fparameter.getConditions();
		String storageCode = (String) map.get("storageCode");
		String salerCode = (String) map.get("salerCode");
		String deptCode = (String) map.get("deptCode");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		String billNo = (String) map.get("billNo"); //出库单号
		String specialSign = (String) map.get("specialSign");//特殊高值耗材
		
		StringBuilder conditions = new StringBuilder();

		String unitsCode = SessionUtil.getUnitsCode();
		
		conditions
				.append("where mainAutoId in (select autoId from MaterialRdsMaster");
		conditions.append(" where unitsCode='" + unitsCode + "'");
		// 仓库编码
		if (storageCode != null && storageCode.length() > 0) {
			conditions.append(" and storageCode='" + storageCode + "'");
		}
		// 请领部门
		if (deptCode != null && deptCode.length() > 0) {
			conditions.append(" and deptCode='" + deptCode + "'");
		}
		// 供应商
		if (salerCode != null && salerCode.length() > 0) {
			conditions.append(" and salerCode='" + salerCode + "'");
		}
		// 出库单据号
		if (billNo != null && billNo.length() > 0) {
			conditions.append(" and billNo ='" + billNo + "'");
		}
		// 日期范围
		conditions.append(" and billDate >= to_date('" + startDate
				+ " 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')");
		conditions.append(" and billDate <= to_date('" + endDate + " 23:59:59"
				+ "','yyyy-mm-dd hh24:mi:ss')");
		// 附加条件--主记录
		conditions.append(" and rdFlag='2'");
		conditions.append(" and currentStatus='1'");

		List<MaterialRdsDetail> lstMaterialRdsDetails = new ArrayList<MaterialRdsDetail>();
			
		if (specialSign != null && specialSign.equals("1")) {
			conditions.append(") and acctBillNo='0' and barCode<>'0' and highValueSign='1' and agentSign='1' order by mainAutoId,serialNo");
			lstMaterialRdsDetails = materialRdsDetailDAO.findByCondition(conditions.toString());
			
			for(MaterialRdsDetail detail:lstMaterialRdsDetails){
				String barCode=detail.getBarCode()==null ? "0" : detail.getBarCode();
				//通过科室入库的特殊高值耗材需要查询病人信息 ryh 2012.12.11
				if(!detail.getBarCode().equals("0")){
					MaterialPatsMaster patsMaster=materialPatsMasterDAO.findDetailByBarCode(barCode);
					if(patsMaster!=null){
						detail.setPatientId(patsMaster.getPatientId());
						detail.setPersonName(patsMaster.getPersonName());
						detail.setBedNo(patsMaster.getBedNo());
						detail.setWardCode(patsMaster.getWardCode());
					}
				}
			}
		}
		else{
			conditions.append(") and acctBillNo='0' and (nvl(barCode,'0')='0' or (barCode<>'0' and highValueSign='1' and agentSign='0')) order by mainAutoId,serialNo");
			lstMaterialRdsDetails = materialRdsDetailDAO.findByCondition(conditions.toString());
		}
		
		reObject.setData(lstMaterialRdsDetails);
		return reObject;
	}
	public ReObject findReportByCondition1(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("查询出库报告单");

		Map<String, Object> map = fparameter.getConditions();
		String storageCode = (String) map.get("storageCode");
		String salerCode = (String) map.get("salerCode");
		String deptCode = (String) map.get("deptCode");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		String specialSign = (String) map.get("specialSign");//特殊高值耗材
		
		StringBuilder conditions = new StringBuilder();

		String unitsCode = SessionUtil.getUnitsCode();
		conditions.append(" where unitsCode='" + unitsCode + "'");
		// 仓库编码
		if (storageCode != null && storageCode.length() > 0) {
			conditions.append(" and storageCode='" + storageCode + "'");
		}
		// 请领部门
		if (deptCode != null && deptCode.length() > 0) {
			conditions.append(" and deptCode='" + deptCode + "'");
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
		conditions.append(" and rdFlag='2'");
		conditions.append(" and currentStatus='1'");
	      
	    if (specialSign != null && specialSign.equals("1")) {
	      conditions.append(" and autoId in (select d.mainAutoId from MaterialRdsDetail d where d.barCode<>'0' and d.highValueSign='1' and d.agentSign='1' and d.acctBillNo='0')");
	    }
	    else{
	      conditions.append(" and autoId in (select d.mainAutoId from MaterialRdsDetail d where (nvl(d.barCode,'0')='0' or (d.barCode<>'0' and d.highValueSign='1' and d.agentSign='0')) and d.acctBillNo='0')");  
	    }
	    
	    conditions.append(" order by autoId");
	    String result=conditions.toString();
		List<Object[]> lstMaterialRdsDetails = materialRdsDetailDAO
				.findByCondition1(result);
		reObject.setData(lstMaterialRdsDetails);

		return reObject;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject saveReport(ParameterObject fparameter,
			List<MaterialRdsDetail> details) {
		// TODO Auto-generated method stub
		ReObject reObject = new ReObject();
		reObject.setAction("保存出库报告单");

		Map<String, Object> map = fparameter.getConditions();
		String storageCode = (String) map.get("storageCode");
		String deptCode = (String) map.get("deptCode");
		String deptName = (String) map.get("deptName");
//		String salerSign = (String) map.get("salerSign");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		String specialSign = (String) map.get("specialSign");//特殊高值耗材

		String unitsCode = SessionUtil.getUnitsCode();
		List<CdDeptDict> deptList = cdDeptDictDAO.findByProperty("deptCode", deptCode);
		if(deptList.size() !=1 ){
			throw new RuntimeException("请检查科室编码为："+deptCode+"在字典中的记录");
		}
		String deptUnitsCode = deptList.get(0).getUnitsCode();
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
				"0102","N")+"C";
		// 每页打印行数
		int pageRow = Integer.parseInt(cdSysParamDAO.findByParaCode(unitsCode,
				"892", "0103"));
		if (pageRow < 1) {
			pageRow = 10;
		}
		// 读取当前最大单据号
		String rdBillNo = "0000000000";
//		MaterialAcctCurrentRcptNo maCurrentRcptNo = rcptType.equals("4")?materialAcctCurrentRcptNoDAO
//				.findByUniqueIndex2(unitsCode, "2", rcptType,storageCode):materialAcctCurrentRcptNoDAO
//				.findByUniqueIndex(unitsCode, "2", rcptType, typeDate);
		List<MaterialAcctCurrentRcptNo> nos = materialAcctCurrentRcptNoDAO
		.findByUniqueIndex3(unitsCode, "2");
		MaterialAcctCurrentRcptNo maCurrentRcptNo  = null;
		if (nos.size() == 0) {
			maCurrentRcptNo = new MaterialAcctCurrentRcptNo();
			String currentRcptNoAutoId = materialAcctCurrentRcptNoDAO
					.nextValue("SEQ_MATERAIL_ACCT_CURRENT_RCPT").toString();
			maCurrentRcptNo.setAutoId(currentRcptNoAutoId);
			maCurrentRcptNo.setUnitsCode(unitsCode);
			maCurrentRcptNo.setRcptType(rcptType);
			maCurrentRcptNo.setRdFlag("2");
			if (rcptType.equals("4")) {
				maCurrentRcptNo.setTypeDate("0");
				maCurrentRcptNo.setStorageCode(storageCode);
			}else{
				maCurrentRcptNo.setTypeDate(typeDate);
			}
			maCurrentRcptNo.setCurrentNo(rdBillNo);
		} else {
			maCurrentRcptNo = nos.get(0);
			rdBillNo = maCurrentRcptNo.getCurrentNo();
		}
		String currentRdBillNo = null,minRdBillNo=null,maxRdBillNo=null;
		int i = 1;
		String mainAutoId = "";
		String materialId;
		String batch = "0";
		//double类型运算会造成精度丢失，改用BigDecimal
		BigDecimal tradeSum = new BigDecimal("0");
		BigDecimal factTradeSum = new BigDecimal("0");
		BigDecimal wholeSaleSum = new BigDecimal("0");
		BigDecimal inviteSum = new BigDecimal("0");
		BigDecimal retailSum = new BigDecimal("0");
		String barCode="";//医院条码
		for (MaterialRdsDetail detail : details) {
			materialId = detail.getMaterialId();
			barCode=detail.getBarCode()==null ? "0" : detail.getBarCode();
			batch = detail.getBatch();
			if (i % pageRow == 1) {
				// 新的一行，出库号进行改变
				rdBillNo = String.valueOf(Long.parseLong(rdBillNo) + 1);
				rdBillNo = StringUtil.lpad(rdBillNo, 10);
				currentRdBillNo = currentPrefix + rdBillNo;
				minRdBillNo = maxRdBillNo = currentRdBillNo;
			}
			i++;
			// 核算单号、出库号
			detail.setAcctBillNo(autoId);
			detail.setRdBillNo(currentRdBillNo);
			materialRdsDetailDAO.merge(detail);
			// 更新库存中的核算数量
			MaterialCurrentStock materialCurrentStock=null;
			
			//是否属于高值耗材 ryh 2012.10.18
			if(detail.getHighValueSign() !=null && detail.getHighValueSign().equals("1") && !barCode.equals("0")){
				materialCurrentStock = materialCurrentStockDAO
				.findBarCodeByUnique(unitsCode, storageCode, materialId, batch,barCode);
			}
			else 
			{
				materialCurrentStock = materialCurrentStockDAO
				.findByUnique(unitsCode, storageCode, materialId, batch);
			}
			//非特殊高值耗材  ryh 2012.10.18
			if(detail.getHighValueSign()==null || detail.getHighValueSign().equals("0") || barCode.equals("0")){
				if (null == materialCurrentStock) {
					// 因弹出提示，并撤消事务
					throw new RuntimeException(detail.getMaterialName()
							+ " 无当前库存，保存失败！");
				}
				materialCurrentStock.setAcctAmount(materialCurrentStock
						.getAcctAmount()
						- detail.getAcctAmount());
				materialCurrentStockDAO.attachDirty(materialCurrentStock);
			}
		
			// 更新主记录中当前状态
			if (!detail.getMainAutoId().equals(mainAutoId)) {
				mainAutoId = detail.getMainAutoId();
				updateRdsMaster(mainAutoId);
			}
			// 计算当前总价
			tradeSum = tradeSum .add( new BigDecimal(detail.getTradeMoney().doubleValue()));
			factTradeSum = factTradeSum.add(new BigDecimal(detail.getFactTradeMoney().doubleValue())) ;
			wholeSaleSum = wholeSaleSum.add(new BigDecimal(detail.getWholeSaleMoney().doubleValue())) ;
			inviteSum = inviteSum.add(new BigDecimal(detail.getInviteMoney().doubleValue())) ;
			retailSum = retailSum.add(new BigDecimal(detail.getRetailMoney().doubleValue())) ;
			
			//特殊高值耗材需要查询病人信息 ryh 2012.12.11
			if(specialSign!=null && specialSign.equals("1") && !barCode.equals("0")){
				MaterialPatsMaster patsMaster=materialPatsMasterDAO.findDetailByBarCode(barCode);
				if(patsMaster==null){
					throw new RuntimeException("没有查询到物资id["+detail.getMaterialId()+"]的病人使用信息");
				}
				detail.setPatientId(patsMaster.getPatientId());
				detail.setPersonName(patsMaster.getPersonName());
				detail.setBedNo(patsMaster.getBedNo());
				detail.setWardCode(patsMaster.getWardCode());
			}
		}
		// 更新当前最大入库流水号
		maCurrentRcptNo.setCurrentNo(rdBillNo);
		materialAcctCurrentRcptNoDAO.attachDirty(maCurrentRcptNo);
		// 写核算主记录
		MaterialRdsAcctMaster materialRdsAcctMaster = new MaterialRdsAcctMaster();
		materialRdsAcctMaster.setAutoId(autoId);
		materialRdsAcctMaster.setUnitsCode(unitsCode);
		materialRdsAcctMaster.setStorageCode(storageCode);
		materialRdsAcctMaster.setRdFlag("2");
		materialRdsAcctMaster.setDeptUnitsCode(deptUnitsCode);//8-30增加
//		if (salerSign.equals("1")) {
//			materialRdsAcctMaster.setSalerCode(deptCode);
//			materialRdsAcctMaster.setSalerName(deptName);
//		} else {
			materialRdsAcctMaster.setDeptCode(deptCode);
			materialRdsAcctMaster.setDeptName(deptName);
//		}
		materialRdsAcctMaster.setBillDate1(DateUtil
				.getDateComeString(startDate));
		materialRdsAcctMaster.setBillDate2(DateUtil.getDateComeString(endDate));
		if(details.size() == 0  ){
			minRdBillNo = maxRdBillNo = details.get(0).getRdBillNo();
			materialRdsAcctMaster.setMinRdBillNo(minRdBillNo);
			materialRdsAcctMaster.setMaxRdBillNo(maxRdBillNo);
		}else{
			minRdBillNo = details.get(0).getRdBillNo();
		    maxRdBillNo = details.get(details.size()-1).getRdBillNo();
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

	@Override
	public ReObject saveReportValue(ParameterObject fparameter,
			List<MaterialRdsDetail> details){
		ReObject reObject = new ReObject();
		reObject.setAction("保存特殊高值耗材出库报告单");
		
		String tempPatientId="";
		List<DeliverReportAcct> reportList=new ArrayList<DeliverReportAcct>();
		for(MaterialRdsDetail detail:details){
			boolean flag=true;
			tempPatientId=detail.getPatientId();
			if(reportList.size()>0){
				for(DeliverReportAcct reportAcct:reportList){
					if(reportAcct.getPatientId().equals(tempPatientId)){
						List<MaterialRdsDetail> rdsDetails=reportAcct.getRdsDetails();
						rdsDetails.add(detail);
						flag=false;
					}
				}
			}
			if(flag){
				DeliverReportAcct acct=new DeliverReportAcct();
				acct.setPatientId(tempPatientId);
				List<MaterialRdsDetail> rdsList=new ArrayList<MaterialRdsDetail>();
				rdsList.add(detail);
				acct.setRdsDetails(rdsList);
				reportList.add(acct);
			}
		}
		List<DeliverReportAcct> dataList=new ArrayList<DeliverReportAcct>();

		for(DeliverReportAcct reportAcct:reportList){
			reportAcct.setMaster(saveAcctReport(fparameter,reportAcct.getRdsDetails()));
			dataList.add(reportAcct);
		}
		reObject.setData(dataList);
		return reObject;
	}
	
	private MaterialRdsAcctMaster saveAcctReport(ParameterObject fparameter,
			List<MaterialRdsDetail> details) {
		
		Map<String, Object> map = fparameter.getConditions();
		String storageCode = (String) map.get("storageCode");
		String deptCode = (String) map.get("deptCode");
		String deptName = (String) map.get("deptName");
//		String salerSign = (String) map.get("salerSign");
		String startDate = (String) map.get("startDate");
		String endDate = (String) map.get("endDate");
		String specialSign = (String) map.get("specialSign");//特殊高值耗材

		String unitsCode = SessionUtil.getUnitsCode();
		List<CdDeptDict> deptList = cdDeptDictDAO.findByProperty("deptCode", deptCode);
		if(deptList.size() !=1 ){
			throw new RuntimeException("请检查科室编码为："+deptCode+"在字典中的记录");
		}
		String deptUnitsCode = deptList.get(0).getUnitsCode();
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
				"0102","N")+"C";
		// 每页打印行数
		int pageRow = Integer.parseInt(cdSysParamDAO.findByParaCode(unitsCode,
				"892", "0103"));
		if (pageRow < 1) {
			pageRow = 10;
		}
		// 读取当前最大单据号
		String rdBillNo = "0000000000";
//		MaterialAcctCurrentRcptNo maCurrentRcptNo = rcptType.equals("4")?materialAcctCurrentRcptNoDAO
//				.findByUniqueIndex2(unitsCode, "2", rcptType,storageCode):materialAcctCurrentRcptNoDAO
//				.findByUniqueIndex(unitsCode, "2", rcptType, typeDate);
		List<MaterialAcctCurrentRcptNo> nos = materialAcctCurrentRcptNoDAO
		.findByUniqueIndex3(unitsCode, "2");
		MaterialAcctCurrentRcptNo maCurrentRcptNo  = null;
		if (nos.size() == 0) {
			maCurrentRcptNo = new MaterialAcctCurrentRcptNo();
			String currentRcptNoAutoId = materialAcctCurrentRcptNoDAO
					.nextValue("SEQ_MATERAIL_ACCT_CURRENT_RCPT").toString();
			maCurrentRcptNo.setAutoId(currentRcptNoAutoId);
			maCurrentRcptNo.setUnitsCode(unitsCode);
			maCurrentRcptNo.setRcptType(rcptType);
			maCurrentRcptNo.setRdFlag("2");
			if (rcptType.equals("4")) {
				maCurrentRcptNo.setTypeDate("0");
				maCurrentRcptNo.setStorageCode(storageCode);
			}else{
				maCurrentRcptNo.setTypeDate(typeDate);
			}
			maCurrentRcptNo.setCurrentNo(rdBillNo);
		} else {
			maCurrentRcptNo = nos.get(0);
			rdBillNo = maCurrentRcptNo.getCurrentNo();
		}
		String currentRdBillNo = null,minRdBillNo=null,maxRdBillNo=null;
		int i = 1;
		String mainAutoId = "";
		String materialId;
		String batch = "0";
		//double类型运算会造成精度丢失，改用BigDecimal
		BigDecimal tradeSum = new BigDecimal("0");
		BigDecimal factTradeSum = new BigDecimal("0");
		BigDecimal wholeSaleSum = new BigDecimal("0");
		BigDecimal inviteSum = new BigDecimal("0");
		BigDecimal retailSum = new BigDecimal("0");
		String barCode="";//医院条码
		for (MaterialRdsDetail detail : details) {
			materialId = detail.getMaterialId();
			batch = detail.getBatch();
			barCode=detail.getBarCode()==null ? "0" : detail.getBarCode();
			if (i % pageRow == 1) {
				// 新的一行，出库号进行改变
				rdBillNo = String.valueOf(Long.parseLong(rdBillNo) + 1);
				rdBillNo = StringUtil.lpad(rdBillNo, 10);
				currentRdBillNo = currentPrefix + rdBillNo;
				minRdBillNo = maxRdBillNo = currentRdBillNo;
			}
			i++;
			// 核算单号、出库号
			detail.setAcctBillNo(autoId);
			detail.setRdBillNo(currentRdBillNo);
			materialRdsDetailDAO.merge(detail);
			// 更新库存中的核算数量
			MaterialCurrentStock materialCurrentStock=null;
			
			//是否属于高值耗材 ryh 2012.10.18
			if(detail.getHighValueSign() !=null && detail.getHighValueSign().equals("1") && !barCode.equals("0")){
				materialCurrentStock = materialCurrentStockDAO
				.findBarCodeByUnique(unitsCode, storageCode, materialId, batch,detail.getBarCode());
			}
			else 
			{
				materialCurrentStock = materialCurrentStockDAO
				.findByUnique(unitsCode, storageCode, materialId, batch);
			}
			//非特殊高值耗材  ryh 2012.10.18
			if(detail.getHighValueSign()==null || detail.getHighValueSign().equals("0") || barCode.equals("0")){
				if (null == materialCurrentStock) {
					// 因弹出提示，并撤消事务
					throw new RuntimeException(detail.getMaterialName()
							+ " 无当前库存，保存失败！");
				}
				materialCurrentStock.setAcctAmount(materialCurrentStock
						.getAcctAmount()
						- detail.getAcctAmount());
				materialCurrentStockDAO.attachDirty(materialCurrentStock);
			}
		
			// 更新主记录中当前状态
			if (!detail.getMainAutoId().equals(mainAutoId)) {
				mainAutoId = detail.getMainAutoId();
				updateRdsMaster(mainAutoId);
			}
			// 计算当前总价
			tradeSum = tradeSum .add( new BigDecimal(detail.getTradeMoney().doubleValue()));
			factTradeSum = factTradeSum.add(new BigDecimal(detail.getFactTradeMoney().doubleValue())) ;
			wholeSaleSum = wholeSaleSum.add(new BigDecimal(detail.getWholeSaleMoney().doubleValue())) ;
			inviteSum = inviteSum.add(new BigDecimal(detail.getInviteMoney().doubleValue())) ;
			retailSum = retailSum.add(new BigDecimal(detail.getRetailMoney().doubleValue())) ;
			
			//特殊高值耗材需要查询病人信息 ryh 2012.12.11
			if(specialSign!=null && specialSign.equals("1") && !barCode.equals("0")){
				MaterialPatsMaster patsMaster=materialPatsMasterDAO.findDetailByBarCode(barCode);
				if(patsMaster==null){
					throw new RuntimeException("没有查询到物资id["+detail.getMaterialId()+"]的病人使用信息");
				}
				detail.setPatientId(patsMaster.getPatientId());
				detail.setPersonName(patsMaster.getPersonName());
				detail.setBedNo(patsMaster.getBedNo());
				detail.setWardCode(patsMaster.getWardCode());
			}
		}
		// 更新当前最大入库流水号
		maCurrentRcptNo.setCurrentNo(rdBillNo);
		materialAcctCurrentRcptNoDAO.attachDirty(maCurrentRcptNo);
		// 写核算主记录
		MaterialRdsAcctMaster materialRdsAcctMaster = new MaterialRdsAcctMaster();
		materialRdsAcctMaster.setAutoId(autoId);
		materialRdsAcctMaster.setUnitsCode(unitsCode);
		materialRdsAcctMaster.setStorageCode(storageCode);
		materialRdsAcctMaster.setRdFlag("2");
		materialRdsAcctMaster.setDeptUnitsCode(deptUnitsCode);//8-30增加
//		if (salerSign.equals("1")) {
//			materialRdsAcctMaster.setSalerCode(deptCode);
//			materialRdsAcctMaster.setSalerName(deptName);
//		} else {
			materialRdsAcctMaster.setDeptCode(deptCode);
			materialRdsAcctMaster.setDeptName(deptName);
//		}
		materialRdsAcctMaster.setBillDate1(DateUtil
				.getDateComeString(startDate));
		materialRdsAcctMaster.setBillDate2(DateUtil.getDateComeString(endDate));
		if(details.size() == 0  ){
			minRdBillNo = maxRdBillNo = details.get(0).getRdBillNo();
			materialRdsAcctMaster.setMinRdBillNo(minRdBillNo);
			materialRdsAcctMaster.setMaxRdBillNo(maxRdBillNo);
		}else{
			minRdBillNo = details.get(0).getRdBillNo();
		    maxRdBillNo = details.get(details.size()-1).getRdBillNo();
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
		
		return materialRdsAcctMaster;
	}

	
	private void updateRdsMaster(String mainAutoId) {
		Date today = new Date();
		String personId = SessionUtil.getPersonId();

		materialRdsMasterDAO.updateAcctByAutoId(mainAutoId, personId, today,
				"2");
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
	
	//
	public CdDeptDictDAO getCdDeptDictDAO() {
		return cdDeptDictDAO;
	}
	public void setCdDeptDictDAO(CdDeptDictDAO cdDeptDictDAO) {
		this.cdDeptDictDAO = cdDeptDictDAO;
	}
	public MaterialPatsMasterDAO getMaterialPatsMasterDAO() {
		return materialPatsMasterDAO;
	}
	public void setMaterialPatsMasterDAO(MaterialPatsMasterDAO materialPatsMasterDAO) {
		this.materialPatsMasterDAO = materialPatsMasterDAO;
	}


}
