package cn.superion.cssd.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.cssd.dao.CssdDeliverMasterDAO;
import cn.superion.cssd.dao.CssdRetrieveDetailDAO;
import cn.superion.cssd.dao.CssdRetrieveMasterDAO;
import cn.superion.cssd.dao.CssdStockMasterDAO;
import cn.superion.cssd.dao.VCssdDeliverStockDetailDAO;
import cn.superion.cssd.dao.VCssdStockDAO;
import cn.superion.cssd.entity.CssdRetrieveDetail;
import cn.superion.cssd.entity.CssdRetrieveMaster;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.cssd.entity.VCssdDeliverStockDetail;
import cn.superion.cssd.entity.VCssdStock;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.materialDept.entity.MaterialCurrentStockDept;
import cn.superion.materialDept.entity.MaterialRejectDetailDept;
import cn.superion.materialDept.entity.MaterialRejectMasterDept;
import cn.superion.materialDept.other.service.IReject;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
import cn.superion.cssd.entity.DeptPackageMaterial;

/**
 * 物品回收处理服务实现
 * 
 * @author 曹国魁
 * 
 */
public class RetrieveImpl implements IRetrieve {
	private Log log = LogFactory.getLog(RetrieveImpl.class);
	private static final String PARA_CODE_AUTO_CHK = "0201";
	private CssdRetrieveMasterDAO cssdRetrieveMasterDAO;
	private CssdRetrieveDetailDAO cssdRetrieveDetailDAO;
	private CssdDeliverMasterDAO cssdDeliverMasterDAO;
	private CssdStockMasterDAO cssdStockMasterDAO;
	private VCssdStockDAO vCssdStockDAO;
	private VCssdDeliverStockDetailDAO vCssdDeliverStockDetailDAO;
	private IReject deptRejectImpl;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public VCssdDeliverStockDetailDAO getvCssdDeliverStockDetailDAO() {
		return vCssdDeliverStockDetailDAO;
	}

	public void setvCssdDeliverStockDetailDAO(
			VCssdDeliverStockDetailDAO vCssdDeliverStockDetailDAO) {
		this.vCssdDeliverStockDetailDAO = vCssdDeliverStockDetailDAO;
	}

	public CssdRetrieveMasterDAO getCssdRetrieveMasterDAO() {
		return cssdRetrieveMasterDAO;
	}

	public void setCssdRetrieveMasterDAO(
			CssdRetrieveMasterDAO cssdRetrieveMasterDAO) {
		this.cssdRetrieveMasterDAO = cssdRetrieveMasterDAO;
	}

	public CssdRetrieveDetailDAO getCssdRetrieveDetailDAO() {
		return cssdRetrieveDetailDAO;
	}

	public void setCssdRetrieveDetailDAO(
			CssdRetrieveDetailDAO cssdRetrieveDetailDAO) {
		this.cssdRetrieveDetailDAO = cssdRetrieveDetailDAO;
	}

	public CssdDeliverMasterDAO getCssdDeliverMasterDAO() {
		return cssdDeliverMasterDAO;
	}

	public void setCssdDeliverMasterDAO(
			CssdDeliverMasterDAO cssdDeliverMasterDAO) {
		this.cssdDeliverMasterDAO = cssdDeliverMasterDAO;
	}

	public CssdStockMasterDAO getCssdStockMasterDAO() {
		return cssdStockMasterDAO;
	}

	public void setCssdStockMasterDAO(CssdStockMasterDAO cssdStockMasterDAO) {
		this.cssdStockMasterDAO = cssdStockMasterDAO;
	}

	public VCssdStockDAO getvCssdStockDAO() {
		return vCssdStockDAO;
	}

	public void setvCssdStockDAO(VCssdStockDAO vCssdStockDAO) {
		this.vCssdStockDAO = vCssdStockDAO;
	}

	public IReject getDeptRejectImpl() {
		return deptRejectImpl;
	}

	public void setDeptRejectImpl(IReject deptRejectImpl) {
		this.deptRejectImpl = deptRejectImpl;
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
		ReObject ro = new ReObject("删除当前未审核的物品回收单据");
		CssdRetrieveMaster master = cssdRetrieveMasterDAO.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("物品回收单不存在！");
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("物品回收记录已审核，不能删除！");
		}
		List<CssdStockMaster> pgkList = cssdRetrieveDetailDAO
				.findPackageList(fstrAutoId);
		for (CssdStockMaster pkg : pgkList) {
			// 还原物品包回收状态为发放状态
			if ("3".equals(pkg.getCurrentStatus())) {
				pkg.setCurrentStatus("2");
			}
		}
		cssdRetrieveDetailDAO.delByMainAutoId(fstrAutoId);
		cssdRetrieveMasterDAO.delete(master);
		// 如果有关联的物资报损单，需写报损红单
		String rejectAutoId = master.getRejectAutoId();
		if (rejectAutoId != null && !"".equals(rejectAutoId)) {
			deptRejectImpl.writeRejectDeliverRed(rejectAutoId);
		}
		return ro;
	}

	@Override
	public ReObject findDeliverDetail(String fstrAutoId) {
		ReObject ro = new ReObject("根据物品发放单主记录ID查询物品包视图列表");
		List<VCssdStock> data = vCssdStockDAO.findDeliverDetail(fstrAutoId);
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findDeliverListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤已审的物品发放单");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = cssdDeliverMasterDAO.findByCondition(start, limit,
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前物品回收处理的详细信息记录");
		CssdRetrieveMaster master = cssdRetrieveMasterDAO.findById(fstrAutoId);
		List<CssdRetrieveDetail> details = null;
		if (master != null) {
			details = cssdRetrieveDetailDAO.findByMainAutoId(fstrAutoId);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findMasterIdListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物品回收列表");
		List<Object> data = cssdRetrieveMasterDAO.findAutoIdsByCondition(
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的物品回收信息");
		CssdRetrieveMaster original = cssdRetrieveMasterDAO
				.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物品回收单！");
		}
		if ("1".equals(original.getCurrentStatus())) {
			throw new RuntimeException("物品回收单已审核，不能审核！");
		}
		original.setCurrentStatus("1");
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		String unitsCode = original.getUnitsCode();
		String storageCode = SessionUtil.getSysUser().getDeptCode();
		//是否负库存
		boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode,
				RdConstant.APP_CODE_DEPT_MATERIAL,
				RdConstant.SYS_PARA_CODE_ZERO_INVENTORY, "0").equals("1");
		List<CssdRetrieveDetail> details = cssdRetrieveDetailDAO
				.findByMainAutoId(fstrAutoId);
		for(CssdRetrieveDetail detail : details){
			updateDeptMaterialStock(isZeroDeliver, unitsCode, storageCode, detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

	/**
	 * 增加物资库存数量
	 * 
	 * @param isZeroDeliver
	 * @param unitsCode
	 * @param storageCode
	 * @param detail
	 */
	private void updateDeptMaterialStock(boolean isZeroDeliver,
			String unitsCode, String storageCode, CssdRetrieveDetail detail) {
		if (detail.getAmount() > 0) {
			MaterialCurrentStockDept stock = buildCurrentStock(unitsCode,
					storageCode, detail);
			deptCommMaterialServiceImpl.saveCurrentStock(isZeroDeliver, stock);
		}
	}

	private MaterialCurrentStockDept buildCurrentStock(String unitsCode,
			String storageCode, CssdRetrieveDetail detail) {
		MaterialCurrentStockDept stock = new MaterialCurrentStockDept();
		stock.setUnitsCode(unitsCode);
		stock.setStorageCode(storageCode);
		stock.setMaterialClass(detail.getMaterialClass());
		stock.setBarCode("0");
		stock.setMaterialId(detail.getMaterialId());
		stock.setMaterialCode(detail.getMaterialCode());
		stock.setMaterialName(detail.getMaterialName());
		stock.setMaterialSpec(detail.getMaterialSpec());
		stock.setMaterialUnits(detail.getMaterialUnits());
		stock.setTradePrice(detail.getTradePrice());
		stock.setAmount(detail.getAmount());
		stock.setFactoryCode(detail.getFactoryCode());
		// stock.setMadeDate();
		// stock.setBatch();
		// stock.setAvailDate();
		// stock.setPosition();
		// stock.setHighValueSign();
		// stock.setAgentSign();
		return stock;
	}

	@Override
	public ReObject save(CssdRetrieveMaster fmaster,
			List<CssdRetrieveDetail> flstDetail) {
		ReObject ro = new ReObject("保存当前物品回收信息");
		if(fmaster == null)
			throw new RuntimeException("物品回收主记录不能为空！");
		if(flstDetail == null || flstDetail.isEmpty())
			throw new RuntimeException("物品回收明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String appCode = user.getAppCode();
		String storageCode = user.getDeptCode();
		Date curDate = new Date();
		String autoId = fmaster.getAutoId(); 
		//实际的物资回收明细记录列表
		List<CssdRetrieveDetail> factRetrieveDetailList = flstDetail;
		//是否自动审核
		boolean isAutoChk = cdSysParamDAO.findByParaCode(unitsCode, appCode,PARA_CODE_AUTO_CHK ,"0").equals("1");
		boolean isAdd = autoId == null || "".equals(autoId);
		if(isAdd){
			//新增
			String billNo = fmaster.getBillNo();
			if(billNo == null || "".equals(billNo)){
				fmaster.setBillNo(deptCommMaterialServiceImpl.getNextBillNo(RdConstant.OTHERS));
			}else{
				//新增时，校验手工输入的流水号在一个单位中唯一性
				if(!cssdRetrieveMasterDAO.checkBillNoUnique(unitsCode,billNo)){
					throw new RuntimeException("手工输入的单据编号["+billNo+"]在单位["+unitsCode+"]下有重复");
				}
			}
			if(fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			if(fmaster.getPersonId() == null || "".equals(fmaster.getPersonId()))
				fmaster.setPersonId(personId);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			if(isAutoChk){
				fmaster.setCurrentStatus("1");
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			}else{
				fmaster.setCurrentStatus("0");
			}
			cssdRetrieveMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
			//需要按有效期至顺序来分配回收科室之前已审核发放的的物品包物资，作为实际回收的物资
			boolean isDistribute = flstDetail.get(0).getPackageNo() == null || "".equals(flstDetail.get(0).getPackageNo());
			if(isDistribute){
				factRetrieveDetailList = new ArrayList<CssdRetrieveDetail>();
				String deptCode = fmaster.getDeptCode();
				for(CssdRetrieveDetail detail : flstDetail){
					factRetrieveDetailList.addAll(autoDirtributePackageMaterial(unitsCode,deptCode,detail));
				}
			}
		}else{
			//修改
			CssdRetrieveMaster original = cssdRetrieveMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的物品回收主记录！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("物品回收单已审核，不能修改！");
			}
			String rejectAutoId = original.getRejectAutoId();
			//清空原报损单据或写报损出库红单
			if(rejectAutoId != null && !"".equals(rejectAutoId)){
				deptRejectImpl.writeRejectDeliverRed(rejectAutoId);
			}
			if(isAutoChk){
				fmaster.setCurrentStatus("1");
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			}else{
				fmaster.setCurrentStatus("0");
			}
			cssdRetrieveMasterDAO.merge(fmaster);
			cssdRetrieveDetailDAO.delByMainAutoId(autoId);
		}
		//物品包编号集合
		Set<String> packageNoSet = new TreeSet<String>();
		//报损物资集合
		Map<String,MaterialRejectDetailDept> rjDetailMap = new TreeMap<String,MaterialRejectDetailDept>();
		//报损数量
		Double rejectAmount = 0d;
		boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, RdConstant.APP_CODE_DEPT_MATERIAL, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0").equals("1");
		short i = 0;
		for(CssdRetrieveDetail detail : factRetrieveDetailList){
			//校验物品包编号非空
			String packageNo = detail.getPackageNo();
			if(packageNo == null || packageNo.equals("")){
				throw new RuntimeException("回收的物品包编号不能为空！");
			}
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			cssdRetrieveDetailDAO.save(detail);
			packageNoSet.add(packageNo);
			rejectAmount = detail.getDeliverAmount() - detail.getAmount(); 
			if(rejectAmount > 0 && "1".equals(detail.getRejectSign())){
				String key = detail.getMaterialId();
				MaterialRejectDetailDept rjDetail = rjDetailMap.get(key);
				if(rjDetail == null){
					rjDetailMap.put(key, buildRejectDetail(detail,rejectAmount));
				}else{
					rjDetail.addAmount(rejectAmount);
					rjDetailMap.put(key, rjDetail);
				}
			}
			//自动审核，增加物资库存数量
			if(isAutoChk){
				updateDeptMaterialStock(isZeroDeliver, unitsCode, storageCode, detail);
			}
		}
		//更新物品包当前状态
		CssdStockMaster pkg = null;
		String pkgCurrentStatus = null;
		for(String packageNo : packageNoSet){
			pkg = cssdStockMasterDAO.findById(packageNo);
			if(pkg == null){
				//物品打包作为日常工作流程的第一个环节，系统上线时，可以不初始化物品包记录
				//throw new RuntimeException("回收的物品包[编号："+packageNo+"]在系统中不存在!");
				log.warn("回收的物品包[编号："+packageNo+"]在系统中不存在!");
				continue;
			}
			pkgCurrentStatus = pkg.getCurrentStatus();
			if ("2".equals(pkgCurrentStatus)) {
				pkg.setCurrentStatus("3");
			}else if("0".equals(pkgCurrentStatus)){
				throw new RuntimeException("物品包处于打包状态，还不能回收，必须出库使用后才能回收！");
			}else if("1".equals(pkgCurrentStatus)){
				throw new RuntimeException("物品包处于灭菌状态，还不能回收，必须出库使用后才能回收！");
			}else if(isAdd && "3".equals(pkgCurrentStatus)){
				throw new RuntimeException("物品包处于回收状态，不能重复回收！");
			}
		}
		//写报损单
		if(!rjDetailMap.isEmpty()){
			//构造报损主记录
			MaterialRejectMasterDept rjmaster = new MaterialRejectMasterDept();
			rjmaster.setOutDeptCode(fmaster.getDeptCode());
			deptRejectImpl.saveReject(rjmaster, new ArrayList<MaterialRejectDetailDept>(rjDetailMap.values()));
			fmaster.setRejectAutoId(rjmaster.getAutoId());
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(factRetrieveDetailList);
		ro.setData(data);
		return ro;
	}

	/**
	 * 对科室领用的物品包的物资按照有效期至顺序，在指定的实回数量范围内自动分配物品包物资明细列表
	 * 
	 * @param unitsCode
	 * @param deptCode
	 * @param addUpedRetrieveDetail
	 * @return
	 */
	private List<CssdRetrieveDetail> autoDirtributePackageMaterial(
			String unitsCode, String deptCode,
			CssdRetrieveDetail addUpedRetrieveDetail) {
		List<CssdRetrieveDetail> result = null;
		String packageId = addUpedRetrieveDetail.getPackageId();
		String materialCode = addUpedRetrieveDetail.getMaterialCode();
		List<VCssdDeliverStockDetail> materialList = vCssdDeliverStockDetailDAO
				.findDeliveredPackageMaterial(unitsCode, deptCode, packageId,
						materialCode);
		if (materialList.isEmpty()) {
			throw new RuntimeException("不存在科室[编码:" + deptCode + "]领用的物品包[编码:"
					+ packageId + "]内物资[编码:" + materialCode + "]记录，或该记录还未审核发放！");
		}
		result = new ArrayList<CssdRetrieveDetail>();
		// 汇总的实回数量
		Double totalAmount = addUpedRetrieveDetail.getAmount();
		for (VCssdDeliverStockDetail material : materialList) {
			CssdRetrieveDetail retrieveDetail = new CssdRetrieveDetail();
			retrieveDetail.setPackageNo(material.getPackageNo());
			retrieveDetail.setMaterialClass(material.getMaterialClass());
			retrieveDetail.setMaterialId(material.getMaterialId());
			retrieveDetail.setMaterialCode(material.getMaterialCode());
			retrieveDetail.setMaterialName(material.getMaterialName());
			retrieveDetail.setMaterialSpec(material.getMaterialSpec());
			retrieveDetail.setMaterialUnits(material.getMaterialUnits());
			retrieveDetail.setFactoryCode(material.getFactoryCode());
			retrieveDetail.setTradePrice(material.getTradePrice());
			Double _amount = material.getAmount();
			if (totalAmount >= _amount) {
				retrieveDetail.setAmount(_amount);
				totalAmount -= _amount;
			} else {
				retrieveDetail.setAmount(totalAmount);
			}
			retrieveDetail.setDeliverAmount(_amount);
			retrieveDetail.setRejectSign(addUpedRetrieveDetail.getRejectSign());
			retrieveDetail.setDetailRemark(addUpedRetrieveDetail
					.getDetailRemark());
			result.add(retrieveDetail);
		}
		return result;
	}

	/**
	 * 构造报损明细记录
	 * 
	 * @param detail
	 *            回收明细记录
	 * @param rejectAmount
	 *            报损数量=应回数量-实回数量
	 * @return
	 */
	private MaterialRejectDetailDept buildRejectDetail(
			CssdRetrieveDetail detail, Double rejectAmount) {
		MaterialRejectDetailDept rjDetail = new MaterialRejectDetailDept();
		rjDetail.setAmount(rejectAmount);
		rjDetail.setMaterialClass(detail.getMaterialClass());
		// rjDetail.setBarCode()
		rjDetail.setMaterialId(detail.getMaterialId());
		rjDetail.setMaterialCode(detail.getMaterialCode());
		rjDetail.setMaterialName(detail.getMaterialName());
		rjDetail.setMaterialSpec(detail.getMaterialSpec());
		rjDetail.setMaterialUnits(detail.getMaterialUnits());
		rjDetail.setTradePrice(detail.getTradePrice());
		if (detail.getTradePrice() != null) {
			rjDetail.setTradeMoney(detail.getTradePrice() * rejectAmount);
		}
		rjDetail.setFactoryCode(detail.getFactoryCode());
		// rjDetail.setBatch(detail.getb)
		// rjDetail.setAvailDate()
		return rjDetail;
	}

	@Override
	public ReObject findRetrieveVStockByPackageNo(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据条码或拼音码，五笔码查询可回收物品包视图");
		String packageNo = (String) fparameter.getConditions().get("packageNo");
		String phoInputCode = (String) fparameter.getConditions().get(
				"phoInputCode");
		String fiveInputCode = (String) fparameter.getConditions().get(
				"fiveInputCode");
		List<VCssdStock> data = null;
		if (packageNo != null && !"".equals(packageNo)) {
			data = vCssdStockDAO.findRetrieveVStockByPackageNo(SessionUtil
					.getUnitsCode(), packageNo);
		} else if (phoInputCode != null && !"".equals(phoInputCode)) {
			data = vCssdStockDAO.findRetrieveVStockByInputCode(SessionUtil
					.getUnitsCode(), phoInputCode, true);
		} else if (fiveInputCode != null && !"".equals(fiveInputCode)) {
			data = vCssdStockDAO.findRetrieveVStockByInputCode(SessionUtil
					.getUnitsCode(), fiveInputCode, false);
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findPackageMaterial(String fstrDeptCode,
			String fstrPackageId) {
		ReObject ro = new ReObject("根据物品包ID查询回收科室领用的物品包明细汇总");
		List<DeptPackageMaterial> data = vCssdDeliverStockDetailDAO
				.addUpRetrieveableDeliveredPackageMaterial(SessionUtil
						.getUnitsCode(), fstrDeptCode, fstrPackageId);
		ro.setData(data);
		return ro;
	}

}
