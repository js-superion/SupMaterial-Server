package cn.superion.material.purchase.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.provider.dao.CdProviderDAO;
import cn.superion.center.provider.entity.CdProvider;
import cn.superion.dataDict.entity.CvDiseaseDict;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.CdDeptLimitDAO;
import cn.superion.material.dao.MaterialProvideDetailDAO;
import cn.superion.material.dao.MaterialProvideMasterDAO;
import cn.superion.material.dao.MaterialSupplierDetailDAO;
import cn.superion.material.dao.MaterialSupplierSummaryDAO;
import cn.superion.material.entity.CdDeptLimit;
import cn.superion.material.entity.MaterialProvideDetail;
import cn.superion.material.entity.MaterialProvideMaster;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.material.entity.MaterialSupplierDetail;
import cn.superion.material.entity.MaterialSupplierSummary;
import cn.superion.util.DateUtil;
import cn.superion.util.SessionUtil;
import flex.messaging.FlexContext;
/**
 * 配送物资服务实现
 *
 */
public class SendMaterialImpl implements ISendMaterial {
	private static final Log log = LogFactory.getLog(SendMaterialImpl.class);
	private MaterialProvideMasterDAO materialProvideMasterDAO;
	private MaterialProvideDetailDAO materialProvideDetailDAO;
	private ICommMaterialService commMaterialServiceImpl;
	private CdProviderDAO cdProviderDAO;
	private CdDeptLimitDAO cdDeptLimitDAO;
	private MaterialSupplierDetailDAO materialSupplierDetailDAO;
	private MaterialSupplierSummaryDAO materialSupplierSummaryDAO;
	
	public ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	public CdProviderDAO getCdProviderDAO() {
		return cdProviderDAO;
	}

	public MaterialSupplierDetailDAO getMaterialSupplierDetailDAO() {
		return materialSupplierDetailDAO;
	}

	public void setMaterialSupplierDetailDAO(
			MaterialSupplierDetailDAO materialSupplierDetailDAO) {
		this.materialSupplierDetailDAO = materialSupplierDetailDAO;
	}


	public CdDeptLimitDAO getCdDeptLimitDAO() {
		return cdDeptLimitDAO;
	}

	public void setCdDeptLimitDAO(CdDeptLimitDAO cdDeptLimitDAO) {
		this.cdDeptLimitDAO = cdDeptLimitDAO;
	}

	public MaterialSupplierSummaryDAO getMaterialSupplierSummaryDAO() {
		return materialSupplierSummaryDAO;
	}

	public void setMaterialSupplierSummaryDAO(
			MaterialSupplierSummaryDAO materialSupplierSummaryDAO) {
		this.materialSupplierSummaryDAO = materialSupplierSummaryDAO;
	}

	public void setCdProviderDAO(CdProviderDAO cdProviderDAO) {
		this.cdProviderDAO = cdProviderDAO;
	}

	@Override
	public ReObject findSendDetailByAutoId(String fstrAutoId) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据主记录id查询对应的配送明细列表");
		List<MaterialProvideDetail> details = materialProvideDetailDAO.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(details);
		ro.setData(data);
		return ro;
		
	}
	
	@Override
	public ReObject findSendDetailByAutoIdAndOtherCons(String fstrAutoId,String storageMaterialSign) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据主记录id查询对应的配送明细列表");
		List<MaterialProvideDetail> details = materialProvideDetailDAO.findSendDetailByAutoIdAndOtherCons(fstrAutoId,storageMaterialSign);
		List<Object> data = new ArrayList<Object>();
		data.add(details);
		ro.setData(data);
		return ro;
		
	}

	@Override
	public ReObject findDetailByMainAutoIds(String[] fstrAutoIds,String storageMaterialSign) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据主记录id查询对应的配送明细列表");
		List<MaterialProvideDetail> details = materialProvideDetailDAO.findByMainAutoIds(fstrAutoIds,storageMaterialSign);
		ro.setData(details);
		return ro;
		
	}
	
	@Override
	public ReObject findSendMaterialListByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("查询申请配送物资对应的主记录");
		List<Object> data = materialProvideMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findGroupByProvider(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("查询申请配送物资对应的主记录Entity");
		List<Object> data = materialProvideMasterDAO.findTotal2(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findSendMaterialEntityListByCondition(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("查询申请配送物资对应的主记录Entity");
		List<Object> data = materialProvideMasterDAO.findSendMaterialEntityListByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findSendMaterialTotal(List<String> autoIds,String storageMaterialSign) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据主记录表中的auto_id,按照供货商汇总对应的配送明细");
		String unitsCode = SessionUtil.getUnitsCode();
		List lst = materialProvideMasterDAO.findTotal(autoIds,unitsCode,storageMaterialSign);
		ro.setData(lst);
		return ro;
	}
	
	@Override
	public ReObject updateSendMaterial(MaterialProvideMaster master , List<MaterialProvideDetail> details) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("更新配送明细");
		Date sysDate = materialProvideDetailDAO.getSysDate();
		String personId = SessionUtil.getPersonId();
		for (MaterialProvideDetail materialProvideDetail : details) {
			materialProvideDetailDAO.merge(materialProvideDetail);
		}
		if(master!=null){
			if(master.getSendStatus().equals("2")) //如果是仓库执行状态，前台点击审核操作,生成配送单号
			{
				//生成配送单号
				String sendNo = commMaterialServiceImpl.getNextBillNo("1", master.getDeptCode());
				master.setSendNo("PS"+sendNo);
				master.setExecDate(sysDate);
				master.setExecPerson(personId);
			}
			if(master.getSendStatus().equals("3")) //科室验收
			{
				master.setCheckPerson(personId);
				master.setCheckDate(sysDate);
			}
			materialProvideMasterDAO.merge(master);
		}
		return ro;
	}
	
	
	@Override
	public ReObject updateProviderDetail(List<MaterialProvideDetail> details) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("更新配送明细");
		for (MaterialProvideDetail materialProvideDetail : details) {
			Double sendAmount = materialProvideDetail.getSendAmount();
			Double tradePrice = materialProvideDetail.getTradePrice();
			
			materialProvideDetail.setTradeMoney(tradePrice * sendAmount);
			materialProvideDetailDAO.merge(materialProvideDetail);
		}
	
		return ro;
	}
	
	
	
	@Override
	public ReObject updateSendMaterialMaster3(List<MaterialProvideMaster> masters,String status) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("弃审时调用");
		Date sysDate = materialProvideDetailDAO.getSysDate();
		String personId = SessionUtil.getPersonId();
		for(MaterialProvideMaster materialMaster:masters)
		{
			MaterialProvideMaster it = materialProvideMasterDAO.findById(materialMaster.getAutoId());
			if(status.equals("1")){//仓库弃审,校验单据是否被总务审核
				if(it.getCurrentStatus().equals("3")){
					throw new RuntimeException("单据："+it.getBillNo()+"，已被总务审核过，不能弃审！");
				}
				if(it.getCurrentStatus().equals("2")){//总务弃审，将状态还原为2
					materialMaster.setCurrentStatus("1");
				}
				
			}
			if(status.equals("2")){//总务弃审,校验单据是否被采购审核
				if(it.getCurrentStatus().equals("4")){
					throw new RuntimeException("单据："+it.getBillNo()+"，已被采购审核过，不能弃审！");
				}
				if(it.getCurrentStatus().equals("3")){//总务弃审，将状态还原为2
					materialMaster.setCurrentStatus("2");
				}
				
			}
			if(status.equals("3")){//采购弃审时,校验该单据是否生成供货单
//				if(it.getCurrentStatus().equals("1")){
//					throw new RuntimeException("单据："+it.getBillNo()+"，已被总务弃审，请重新查询数据！");
//				}else if(it.getCurrentStatus().equals("2")){
//					materialMaster.setCurrentStatus("3");
//				}
			}
			materialProvideMasterDAO.merge(materialMaster);
		}
		return ro;
	}
	
	@Override
	public ReObject updateSendMaterialMaster2(List<MaterialProvideMaster> masters,String status) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("更新配送明细");
		Date sysDate = materialProvideDetailDAO.getSysDate();
		String personId = SessionUtil.getPersonId();
		for(MaterialProvideMaster materialMaster:masters)
		{
			MaterialProvideMaster it = materialProvideMasterDAO.findById(materialMaster.getAutoId());
			if(status.equals("4")){//采购审核供货商的时候,校验单据是否被总务弃审
				if(it.getCurrentStatus().equals("2")){
					throw new RuntimeException("单据："+it.getBillNo()+"，已被总务弃审，请重新查询数据！");
				}else if(it.getCurrentStatus().equals("3")){
					materialMaster.setCurrentStatus("4");
				}else if(it.getCurrentStatus().equals("4")){
					throw new RuntimeException("单据："+it.getBillNo()+"，已审核过！");
				}
			}
			if(status.equals("3")){//总务审核数量时,校验该单据是否被仓库弃审
				if(it.getCurrentStatus().equals("1")){
					throw new RuntimeException("单据："+it.getBillNo()+"，已被仓库弃审，请重新查询数据！");
				}else if(it.getCurrentStatus().equals("2")){
					materialMaster.setCurrentStatus("3");
				}else if(it.getCurrentStatus().equals("3")){
					throw new RuntimeException("单据："+it.getBillNo()+"，已审核过！");
				}
			}
			materialProvideMasterDAO.merge(materialMaster);
		}
		return ro;
	}
	
	@Override
	public ReObject updateSendMaterialMaster(List<MaterialProvideMaster> masters) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("更新配送明细");
		Date sysDate = materialProvideDetailDAO.getSysDate();
		String personId = SessionUtil.getPersonId();
		for(MaterialProvideMaster materialMaster:masters)
		{
			//生成配送单号
			String sendNo = commMaterialServiceImpl.getNextBillNo("1", materialMaster.getDeptCode());
			materialMaster.setSendNo("PS"+sendNo);
			materialMaster.setExecDate(sysDate);
			materialMaster.setExecPerson(personId);
//			materialMaster.setSendStatus("2");
			materialProvideMasterDAO.merge(materialMaster);
		}
		return ro;
	}

	
	private void  initMaterialRds2(MaterialSupplierSummary master,String rdFlag,String operationType,String rdType,String rmk,
			List<MaterialSupplierDetail> details){
		List<MaterialRdsDetail> rdsDetails = new ArrayList<MaterialRdsDetail>();
		for (MaterialSupplierDetail materialProvideDetail : details) {
			//
			MaterialRdsDetail detail = new MaterialRdsDetail();
			detail.setAmount(materialProvideDetail.getSendAmount());
			detail.setCurrentStatus("0");
			detail.setMaterialCode(materialProvideDetail.getMaterialCode());
			detail.setMaterialClass(materialProvideDetail.getMaterialClass());
			detail.setMaterialName(materialProvideDetail.getMaterialName());
			detail.setMaterialSpec(materialProvideDetail.getMaterialSpec());
			detail.setMaterialUnits(materialProvideDetail.getMaterialUnits());
			detail.setTradePrice(materialProvideDetail.getTradePrice());
			detail.setTradeMoney(detail.getAmount() * detail.getTradePrice());
			detail.setDetailRemark("配送审核");
			detail.setMaterialId(materialProvideDetail.getMaterialId());
			rdsDetails.add(detail);
		}
		String mainProvider = master.getProviderCode();
		String providerName = master.getProviderName();
		String storageCode = master.getStorageCode();
		String deptCode = master.getDeptCode();
		String currentPerson = SessionUtil.getPersonId();
		Date sysDate = materialProvideMasterDAO.getSysDate();
		MaterialRdsMaster rdsMaster = new MaterialRdsMaster();
		rdsMaster.setRdType(rdType); //111
//		rdsMaster.setAutoId(autoId.toString());
		rdsMaster.setUnitsCode(SessionUtil.getUnitsCode());
		rdsMaster.setStorageCode(storageCode);
		rdsMaster.setDeptCode(deptCode);
		rdsMaster.setBillDate(materialProvideMasterDAO.getSysDate());
		rdsMaster.setInvoiceType("1");
		rdsMaster.setRdFlag(rdFlag);
		rdsMaster.setOperationType(operationType); //110
		rdsMaster.setOperationNo("");
		rdsMaster.setSalerCode(mainProvider);
		rdsMaster.setSalerName(providerName);
		rdsMaster.setMaker(currentPerson);
		rdsMaster.setMakeDate(sysDate);
		rdsMaster.setRemark(rmk);
		rdsMaster.setVerifier(currentPerson);
		rdsMaster.setVerifyDate(sysDate);
		rdsMaster.setCurrentStatus("1");
		
		commMaterialServiceImpl.save(rdsMaster, rdsDetails);
		//更新下cd_dept_limit记录
		if(rdFlag.equals("2")){
			updateCdDeptLimit(rdsMaster,rdsDetails);
		}
		
	}
	
	private void updateCdDeptLimit(MaterialRdsMaster mst,List<MaterialRdsDetail> details){
		//根据deptCode查找当前季度的额度
		String unitsCode = SessionUtil.getUnitsCode();
		String deptCode = mst.getDeptCode();
		String year = DateUtil.getCurrentYear();
		String month = DateUtil.getCurrentMonth();
		Integer int_mont = Integer.parseInt(month);
//		int_mont=int_mont-1;
		String season = (String) RdConstant.mp1.get(int_mont.toString());
		List<CdDeptLimit> limit = cdDeptLimitDAO.findByCondition2(unitsCode, deptCode, year, season);
		if(limit!=null && limit.size()>0){//存在数据且限额大于0，才更新，否则不 操作
			CdDeptLimit info = limit.get(0);
			if(info.getLimits()!=null && info.getLimits()>0){
				Double totalCharge = info.getAddUp();
				for (MaterialRdsDetail d : details) {
					totalCharge +=d.getTradeMoney();
				}
				info.setAddUp(totalCharge);
			}
			
		}
		
		
	}
	private void  initMaterialRds(MaterialProvideMaster master,String rdFlag,String operationType,String rdType,String rmk,
			List<MaterialProvideDetail> details){
		List<MaterialRdsDetail> rdsDetails = new ArrayList<MaterialRdsDetail>();
		Long autoId = materialProvideMasterDAO.nextValue("SEQ_MATERIAL_RDS_MASTER");
		for (MaterialProvideDetail materialProvideDetail : details) {
			//
			MaterialRdsDetail detail = new MaterialRdsDetail();
			detail.setAmount(materialProvideDetail.getAmount());
			detail.setCurrentStatus("0");
			detail.setMaterialCode(materialProvideDetail.getMaterialCode());
			detail.setMaterialClass(materialProvideDetail.getMaterialClass());
			detail.setMaterialId(materialProvideDetail.getMaterialId());
			detail.setMaterialName(materialProvideDetail.getMaterialName());
			detail.setMaterialSpec(materialProvideDetail.getMaterialSpec());
			detail.setMaterialUnits(materialProvideDetail.getMaterialUnits());
			detail.setMainAutoId(autoId.toString());
			detail.setTradePrice(materialProvideDetail.getTradePrice());
			detail.setTradeMoney(detail.getAmount() * detail.getTradePrice());
			detail.setFactoryCode(materialProvideDetail.getFactoryCode());
			detail.setDetailRemark("配送");
			rdsDetails.add(detail);
		}
		String mainProvider = details.get(0).getMainProvider();
		String deptCode = master.getDeptCode();
		String checkPerson = master.getCheckPerson();
		String currentPerson = SessionUtil.getPersonId();
		Date sysDate = materialProvideMasterDAO.getSysDate();
		MaterialRdsMaster rdsMaster = new MaterialRdsMaster();
		rdsMaster.setRdType(rdType); //111
//		rdsMaster.setAutoId(autoId.toString());
		rdsMaster.setUnitsCode(SessionUtil.getUnitsCode());
		rdsMaster.setStorageCode(deptCode);
		rdsMaster.setBillDate(materialProvideMasterDAO.getSysDate());
		rdsMaster.setInvoiceType("1");
		rdsMaster.setRdFlag(rdFlag);
		rdsMaster.setOperationType(operationType); //110
		rdsMaster.setOperationNo("");
		rdsMaster.setDeptCode(deptCode);
		rdsMaster.setPersonId(checkPerson);
		rdsMaster.setSalerCode(mainProvider);
		rdsMaster.setSalerName(null);
		rdsMaster.setMaker(currentPerson);
		rdsMaster.setMakeDate(sysDate);
		rdsMaster.setRemark(rmk);
		rdsMaster.setVerifier(currentPerson);
		rdsMaster.setVerifyDate(sysDate);
		rdsMaster.setCurrentStatus("1");
		
		commMaterialServiceImpl.save(rdsMaster, rdsDetails);
	}
	@Override
	public ReObject verifySendMaterial(List<MaterialProvideMaster> masters) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("验收审核操作，先更新，后写入出库明细");//
		List<MaterialProvideDetail> details = null;
		for (MaterialProvideMaster materialProvideMaster : masters) {
			materialProvideMaster.setSendStatus("4");//库房审核验收
			materialProvideMasterDAO.merge(materialProvideMaster);
			details = materialProvideDetailDAO.findByMainAutoId(materialProvideMaster.getAutoId());
			if(details == null || details.isEmpty())
			{
				continue;
			}
			initMaterialRds(materialProvideMaster,"1", "110", "111", "配送入库",details);
			initMaterialRds(materialProvideMaster,"2", "210", "211", "配送出库",details);
			for (MaterialProvideDetail materialProvideDetail : details) {
				materialProvideDetailDAO.merge(materialProvideDetail);
			}
		}
		return ro;
	}
	
	
	@Override
	public ReObject verifySupplyDetails(String autoId,List<Map<String,Object>> groupItems) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("验收审核1操作，先更新，后写入出库明细");//
		String unitsCode =SessionUtil.getUnitsCode();
		for (Map<String, Object> map : groupItems) {
			String mainProvider = (String) map.get("mainProvider");
			String providerName = (String) map.get("providerName");
			String storageCode =  (String) map.get("storageCode");
			String fromDate =  (String) map.get("fromDate");
			String deptCode = (String) map.get("deptCode");
			String toDate =  (String) map.get("toDate");
			MaterialSupplierSummary master = new MaterialSupplierSummary();
			
			//写主记录的
//			master.setAutoId(autoId.toString());
			master.setUnitsCode(unitsCode);
			master.setProviderCode(mainProvider);
			master.setProviderName(providerName);
			master.setStorageCode(storageCode);
			master.setUnitsCode(unitsCode);
			master.setDeptCode(deptCode);
			
			List<Map<String,Object>> details = (List<Map<String, Object>>) map.get("detail");
			List<MaterialSupplierDetail> rdsDetails = new ArrayList<MaterialSupplierDetail>();
			for (Map<String,Object> detail : details) {
				String materialName = (String) detail.get("materialName");
				String materialCode = (String) detail.get("materialCode");
				String materialId = (String) detail.get("materialId");
				String materialUnits = (String) detail.get("materialUnits");
				String materialSpec = (String) detail.get("materialSpec");
				String materialClass = (String) detail.get("materialClass");
				Double tradeMoney = Double.valueOf(detail.get("tradeMoney")==null?"0":detail.get("tradeMoney").toString()) ;
				Double tradePrice = Double.valueOf(detail.get("tradePrice")==null?"0":detail.get("tradePrice").toString()) ;
				Double sendAmount =Double.valueOf(detail.get("sendAmount")==null?"0":detail.get("sendAmount").toString()) ;
				MaterialSupplierDetail item = new MaterialSupplierDetail();
//				Long detailAutoId = materialSupplierDetailDAO.nextValue("seq_supplier_sumary");
//				item.setAutoId(detailAutoId.toString());
				item.setCurrentStockAmount(0.0);
				item.setMaterialClass(materialClass);
				item.setMaterialCode(materialCode);
				item.setMaterialId(materialId);
				item.setMaterialName(materialName);
				item.setMaterialSpec(materialSpec);
				item.setMaterialUnits(materialUnits);
				item.setSendAmount(sendAmount);
				item.setTradeMoney(tradeMoney);
				item.setTradePrice(tradePrice);
				rdsDetails.add(item);
			}
			if(providerName!=null && !"".equals(providerName)){
				if(providerName.equals("仓库直供")){
					
					initMaterialRds2(master,"2", "210", "211", "配送出库",rdsDetails);
				}else{
					
					initMaterialRds2(master,"1", "110", "111", "配送入库",rdsDetails);
					initMaterialRds2(master,"2", "210", "211", "配送出库",rdsDetails);
				}
			}	
			
			//
			
		}
		MaterialSupplierSummary s = materialSupplierSummaryDAO.findById(autoId);//更新下主记录的状态
		s.setCheckSign("1");
		return ro;
	}
	
	public ReObject findProvider(ParameterObject fParameters) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject();
		ro.setAction("查询疾病字典列表");

		int start = 0;
		int limit = 14000;

		Map<String, Object> fmap = fParameters.getConditions();
		if (fmap == null) {
			return ro;
		}

		StringBuilder condition = new StringBuilder();

		String phoInputCode = (String) fmap.get("phoInputCode");

		if (phoInputCode != null && phoInputCode.toString().trim().length() > 0) {
			phoInputCode = phoInputCode.toLowerCase();
			condition
					.append(" where phoInputCode like '" + phoInputCode + "%'");
		} 

		List<CdProvider> lstCvDiseaseDicts = cdProviderDAO
				.findByCondition(condition.toString());

		ro.setData(lstCvDiseaseDicts);
		return ro;
	}
	
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

	@Override
	public ReObject delSumary(List<String>mainIds,List<String> detailIds) {
		// TODO Auto-generated method stub
		//先删除明细
		ReObject ro = new ReObject("删除供货单");//
		materialSupplierDetailDAO.deleteByIds(detailIds);
		materialSupplierSummaryDAO.deleteByIds(mainIds);
		//删除主记录
		return ro;
	}

	@Override
	public ReObject saveSumary(List<Map<String,Object>> groupItems) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("生成供货单");//
		String unitsCode =SessionUtil.getUnitsCode();
		Date sysDt = materialSupplierSummaryDAO.getSysDate();
		for (Map<String, Object> map : groupItems) {
			String mainProvider = (String) map.get("mainProvider");
			String providerName = (String) map.get("providerName");
			String storageCode =  (String) map.get("storageCode");
			String fromDate =  (String) map.get("fromDate");
			String toDate =  (String) map.get("toDate");
//			Long autoId = materialSupplierSummaryDAO.nextValue("seq_supplier_sumary");
			String billNo =commMaterialServiceImpl.getNextBillNo("2", storageCode);
			System.out.println(billNo);
			
			MaterialSupplierSummary master = new MaterialSupplierSummary();
			//写主记录的
//			master.setAutoId(autoId.toString());
			master.setUnitsCode(unitsCode);
			master.setBillDate(new Timestamp(sysDt.getTime()));
			master.setBillNo(billNo);
			master.setCheckSign("0");
			master.setProviderCode(mainProvider);
			master.setProviderName(providerName);
			master.setStorageCode(storageCode);
			master.setUnitsCode(unitsCode);
			materialSupplierSummaryDAO.save(master);
			List<Map<String,Object>> details = (List<Map<String, Object>>) map.get("detail");
			//明细
			int index = 1;
			for (Map<String,Object> detail : details) {
				String materialName = (String) detail.get("materialName");
				String materialCode = (String) detail.get("materialCode");
				String materialId = (String) detail.get("materialId");
				String materialUnits = (String) detail.get("materialUnits");
				String materialSpec = (String) detail.get("materialSpec");
				String materialClass = (String) detail.get("materialClass");
				Integer amount = (Integer) detail.get("amount");
				
				Double tradeMoney = Double.valueOf(detail.get("tradeMoney")==null?"0":detail.get("tradeMoney").toString()) ;
				Double tradePrice = Double.valueOf(detail.get("tradePrice")==null?"0":detail.get("tradePrice").toString()) ;
				Double sendAmount =Double.valueOf(detail.get("sendAmount")==null?"0":detail.get("sendAmount").toString()) ;
				MaterialSupplierDetail item = new MaterialSupplierDetail();
//				Long detailAutoId = materialSupplierDetailDAO.nextValue("seq_supplier_sumary");
//				item.setAutoId(detailAutoId.toString());
				item.setBillNo(billNo);
				item.setCurrentStockAmount(0.0);
				item.setMaterialClass(materialClass);
				item.setMaterialCode(materialCode);
				item.setMaterialId(materialId);
				item.setMaterialName(materialName);
				item.setMaterialSpec(materialSpec);
				item.setMaterialUnits(materialUnits);
				item.setSendAmount(sendAmount);
				item.setSerialNo(Integer.valueOf(index).toString());
				item.setTradeMoney(tradeMoney);
				item.setTradePrice(tradePrice);
				item.setUnitsCode(unitsCode);
//				detail.setAutoId(detailAutoId.toString());
//				detail.setBillNo(billNo);
				materialSupplierDetailDAO.save(item);
				index ++;
				int cut = materialProvideMasterDAO.updateProviderDetail(unitsCode, storageCode, fromDate, toDate, mainProvider, materialCode, billNo);
				log.info("汇总明细autoId:"+item.getAutoId()+",对应更新申请明细数："+cut);
			}
			//
			
//			//根据供货单位名称，判断是否是走仓库库存
//			if(providerName!=null && !"".equals(providerName)){
//				if(providerName.equals("仓库直供")){
//					List<MaterialSupplierDetail> rdsDetails = new ArrayList<MaterialSupplierDetail>();
//					for (Map<String,Object> detail : details) {
//						String materialName = (String) detail.get("materialName");
//						String materialCode = (String) detail.get("materialCode");
//						String materialId = (String) detail.get("materialId");
//						String materialUnits = (String) detail.get("materialUnits");
//						String materialSpec = (String) detail.get("materialSpec");
//						String materialClass = (String) detail.get("materialClass");
//						Double tradeMoney = Double.valueOf(detail.get("tradeMoney")==null?"0":detail.get("tradeMoney").toString()) ;
//						Double tradePrice = Double.valueOf(detail.get("tradePrice")==null?"0":detail.get("tradePrice").toString()) ;
//						Double sendAmount =Double.valueOf(detail.get("sendAmount")==null?"0":detail.get("sendAmount").toString()) ;
//						MaterialSupplierDetail item = new MaterialSupplierDetail();
//						item.setCurrentStockAmount(0.0);
//						item.setMaterialClass(materialClass);
//						item.setMaterialCode(materialCode);
//						item.setMaterialName(materialName);
//						item.setMaterialSpec(materialSpec);
//						item.setMaterialId(materialId);
//						item.setMaterialUnits(materialUnits);
//						item.setSendAmount(sendAmount);
//						item.setTradeMoney(tradeMoney);
//						item.setTradePrice(tradePrice);
//						rdsDetails.add(item);
//					}
//					
//					initMaterialRds2(master,"2", "210", "211", "仓库直接出库",rdsDetails);
//				}
//			}
			
		}
		
		
		
		
		return ro;
		
	}

	@Override
	public ReObject findSumary(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("查询申请配送物资对应的主记录Entity");
		List<MaterialSupplierSummary> data = materialSupplierSummaryDAO.findSumaryByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	public ReObject findSupplierDetail(String billNo) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据主记录id查询对应的汇总明细");
		String unitsCode = SessionUtil.getUnitsCode();
		List<MaterialSupplierDetail> details = materialSupplierDetailDAO.findByProperty("billNo", billNo);
		List<Map<String,Object>> groupData = materialProvideMasterDAO.findGroupByDept(unitsCode,billNo);
		List data = new ArrayList();
		data.add(details);
		data.add(groupData);
		ro.setData(data);
		return ro;
		
	}
	public ReObject findPrintData(Map<String,Object> map) {
		ReObject ro = new ReObject("查询打印明细输血发放视图");
		FlexContext.getFlexSession().setAttribute("printData", map);
		return ro;
	}
	@Override
	public ReObject deleteDeptLimit(CdDeptLimit item) {
		// TODO Auto-generated method stub
		//先删除明细
		ReObject ro = new ReObject("删除CD_DEPT_LIMIT");//
		cdDeptLimitDAO.delete(item);
		return ro;
	}

	@Override
	public ReObject findDeptLimitByCon(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("查询CD_DEPT_LIMIT");
		List<CdDeptLimit> data = cdDeptLimitDAO.findByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveDeptLimit(CdDeptLimit item) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("保存CD_DEPT_LIMIT");
		String unitsCode =SessionUtil.getUnitsCode();
		String userName = SessionUtil.getUnitsName();
		Date sysDt = cdDeptLimitDAO.getSysDate();
		item.setUnitsCode(unitsCode);
		if(item.getAutoId()==null || "".equals(item.getAutoId())){
			List<CdDeptLimit> result = cdDeptLimitDAO.findByCondition2(unitsCode, item.getDeptCode(),item.getYears(),item.getSeason());
			if(result!=null && result.size()>0){
				throw new RuntimeException(item.getDeptName()+"："+item.getYears()+"年第"+item.getSeason()+"季度，"+"已设置了额度！");
			}
			item.setCreatePerson(userName);
			item.setCreateDate(new Timestamp(sysDt.getTime()));
			cdDeptLimitDAO.save(item);
		}else{
			item.setModifyPerson(userName);
			item.setModifyDate(new Timestamp(sysDt.getTime()));
			cdDeptLimitDAO.merge(item);
		}
		
		return ro;
	}
	
	
	@Override
	public ReObject findDeptLimitInfo(String year, String season,String deptCode) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("查询sCD_DEPT_LIMIT");
		String unitsCode =SessionUtil.getUnitsCode();
		String userName = SessionUtil.getUnitsName();
		List<CdDeptLimit> result = cdDeptLimitDAO.findByCondition2(unitsCode, deptCode,year,season);
		ro.setData(result);
		return ro;
		
	}
	
}
