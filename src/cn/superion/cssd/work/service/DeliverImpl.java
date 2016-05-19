package cn.superion.cssd.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.cssd.dao.CssdDeliverMasterDAO;
import cn.superion.cssd.dao.CssdProvideDetailDAO;
import cn.superion.cssd.dao.CssdProvideMasterDAO;
import cn.superion.cssd.dao.CssdStockMasterDAO;
import cn.superion.cssd.dao.VCssdStockDAO;
import cn.superion.cssd.entity.CssdDeliverMaster;
import cn.superion.cssd.entity.CssdProvideDetail;
import cn.superion.cssd.entity.CssdProvideMaster;
import cn.superion.cssd.entity.CssdStockMaster;
import cn.superion.cssd.entity.VCssdStock;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.materialDept.entity.MaterialCurrentStockDept;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 物品发放处理服务实现
 * @author 曹国魁
 *
 */
public class DeliverImpl implements IDeliver {
	private static final String PARA_CODE_AUTO_CHK = "0205";
	private static final String STATUS_APPLY_VERIFY = "1";
	private static final String STATUS_DELIVER_VERIFY = "2";
	//private Log log = LogFactory.getLog(DeliverImpl.class);
	private CssdDeliverMasterDAO cssdDeliverMasterDAO;
	private CssdStockMasterDAO cssdStockMasterDAO;
	private CssdProvideMasterDAO cssdProvideMasterDAO;
	private CssdProvideDetailDAO cssdProvideDetailDAO;
	public CssdProvideDetailDAO getCssdProvideDetailDAO() {
		return cssdProvideDetailDAO;
	}

	public void setCssdProvideDetailDAO(CssdProvideDetailDAO cssdProvideDetailDAO) {
		this.cssdProvideDetailDAO = cssdProvideDetailDAO;
	}

	public CssdProvideMasterDAO getCssdProvideMasterDAO() {
		return cssdProvideMasterDAO;
	}

	public void setCssdProvideMasterDAO(CssdProvideMasterDAO cssdProvideMasterDAO) {
		this.cssdProvideMasterDAO = cssdProvideMasterDAO;
	}

	private VCssdStockDAO vCssdStockDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;
	
	public VCssdStockDAO getvCssdStockDAO() {
		return vCssdStockDAO;
	}

	public void setvCssdStockDAO(VCssdStockDAO vCssdStockDAO) {
		this.vCssdStockDAO = vCssdStockDAO;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	public CssdDeliverMasterDAO getCssdDeliverMasterDAO() {
		return cssdDeliverMasterDAO;
	}

	public void setCssdDeliverMasterDAO(CssdDeliverMasterDAO cssdDeliverMasterDAO) {
		this.cssdDeliverMasterDAO = cssdDeliverMasterDAO;
	}

	public CssdStockMasterDAO getCssdStockMasterDAO() {
		return cssdStockMasterDAO;
	}

	public void setCssdStockMasterDAO(CssdStockMasterDAO cssdStockMasterDAO) {
		this.cssdStockMasterDAO = cssdStockMasterDAO;
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
		ReObject ro = new ReObject("删除当前未审核的物品发放单据");
		CssdDeliverMaster master = cssdDeliverMasterDAO
				.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("物品发放单不存在！");
		if (STATUS_DELIVER_VERIFY.equals(master.getCurrentStatus())) {
			throw new RuntimeException("物品发放记录已审核，不能删除！");
		}
		String unitsCode = master.getUnitsCode();
		List<CssdStockMaster> pgkList = cssdStockMasterDAO
				.findBySterilizeAutoId(unitsCode, fstrAutoId);
		for (CssdStockMaster pkg : pgkList) {
			String packageNo = pkg.getPackageNo();
			//检查物品包使用状态
			if("1".equals(pkg.getUsedSign())){
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]已被使用，不能删除发放记录！");
			}
			// 检查物品包状态为发放状态
			if ("0".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]还是打包入库，不能删除发放记录！");
			}
			if ("1".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]还是灭菌入库，不能删除发放记录！");
			}
			if ("3".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo + "]已回收，不能删除发放记录！");
			}
			if (!"2".equals(pkg.getCurrentStatus())) {
				throw new RuntimeException("物品包[编号：" + packageNo
						+ "]不是发放状态，不能删除发放记录！");
			}
			pkg.setCurrentStatus("1");
			pkg.setDeliverAutoId(null);
			pkg.setDeliverSerialNo(null);
			//还原物品包实发数量
			//updateDeliverAmount(false,pkg,fstrAutoId);
		}
		cssdDeliverMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findProvideDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看物资领用申请单据详细信息记录");
		CssdProvideMaster master = cssdProvideMasterDAO
				.findById(fstrAutoId);
		List<CssdProvideDetail> details = cssdProvideDetailDAO
				.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setSuccess(true);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前物品发放处理的详细信息记录");
//		String unitsCode = SessionUtil.getUnitsCode();
		CssdDeliverMaster master = cssdDeliverMasterDAO
				.findById(fstrAutoId);
		List<CssdStockMaster> details = null;
		List<CssdStockMaster> details1 = null;
		List<CssdProvideDetail> lstProvideDetail = new ArrayList<CssdProvideDetail>();
		if (master != null) {
			details = cssdStockMasterDAO.findByDeliverAutoId(master
					.getUnitsCode(), fstrAutoId);
			details1=cssdStockMasterDAO.findByDeliverAutoIds(master
					.getUnitsCode(), fstrAutoId);
		}
		//根据业务号查申请明细
		String operationNo = master.getOperationNo();
		if(operationNo!=null && !"".equals(operationNo)){
			CssdProvideMaster provideMaster = cssdProvideMasterDAO.findByProperty("billNo", operationNo,master.getDeptUnitsCode()).get(0);
			if(provideMaster!=null){
				lstProvideDetail = cssdProvideDetailDAO.findByMainAutoId(provideMaster.getAutoId());
			}
		}
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details); //发放明细
		data.add(lstProvideDetail);//申请明细
		data.add(details1);//报表数据
		ro.setData(data);
		return ro;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ReObject findProvideListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤已审核的申请单据");
		int start = fparameter.getStart();
		int limit = fparameter.getItemsPerPage();
		Object[] objs = cssdProvideMasterDAO.findByCondition(start, limit,
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		int count = Integer.parseInt(objs[0].toString());
		ro.setCount(count, limit);
		ro.setData((List) objs[1]);
		return ro;
	}

	@Override
	public ReObject findMasterIdListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物品灭菌列表");
		List<Object> data = cssdDeliverMasterDAO.findAutoIdsByCondition(
				SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject save(CssdDeliverMaster fmaster,
			List<CssdStockMaster> flstStockMaster) {
		ReObject ro = new ReObject("保存当前物品发放信息");
		if (fmaster == null)
			throw new RuntimeException("物品发放主记录不能为空！");
		if (flstStockMaster == null || flstStockMaster.isEmpty())
			throw new RuntimeException("物品包记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String appCode = user.getAppCode();
		String storageCode = user.getDeptCode();
		Date curDate = new Date();
		//是否自动审核
		boolean isAutoChk = cdSysParamDAO.findByParaCode(unitsCode, appCode,PARA_CODE_AUTO_CHK ,"0").equals("1");
		String autoId = fmaster.getAutoId();
		//新增--即无申请单情况下的物品包发放；修改--可能有申请单，可能无
		boolean isAdd = autoId == null || "".equals(autoId); 
		if (isAdd) {
			// 新增
			String billNo = fmaster.getBillNo();
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(deptCommMaterialServiceImpl
						.getNextBillNo(RdConstant.OTHERS));
			} else {
				// 新增时，校验手工输入的流水号在一个单位中唯一性
				if (!cssdDeliverMasterDAO
						.checkBillNoUnique(unitsCode, billNo)) {
					throw new RuntimeException("手工输入的单据编号[" + billNo + "]在单位["
							+ unitsCode + "]下有重复");
				}
			}
			if (fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			fmaster.setVerifier(personId);
			fmaster.setVerifyDate(curDate);
			//无申请单时，状态初始化为1
			if(isAutoChk){
				fmaster.setCurrentStatus(STATUS_DELIVER_VERIFY);
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
//				verify(fstrAutoId)
			}else{
				fmaster.setCurrentStatus(STATUS_APPLY_VERIFY);
			}
			cssdDeliverMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
			
			
			
		} else {
			// 修改
			CssdDeliverMaster original = cssdDeliverMasterDAO.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + autoId + "的物品发放主记录！");
			}
			if (STATUS_DELIVER_VERIFY.equals(original.getCurrentStatus())) {
				throw new RuntimeException("物品发放单已审核，不能修改！");
			}
			if(isAutoChk){
				fmaster.setCurrentStatus(STATUS_DELIVER_VERIFY);
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(SessionUtil.getPersonId());
			}else{
				fmaster.setCurrentStatus(STATUS_APPLY_VERIFY);
			}
			cssdDeliverMasterDAO.merge(fmaster);
			List<CssdStockMaster> originalDetails = cssdStockMasterDAO.findByDeliverAutoId(unitsCode, autoId);
			for(CssdStockMaster pkg : originalDetails){
				String packageNo = pkg.getPackageNo();
				if("1".equals(pkg.getUsedSign())){
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已被使用，不能修改发放记录！");
				}
				/*if(!"2".equals(pkg.getCurrentStatus())){
					log.warn("要更新的物品发放记录的物品包[编号："+packageNo+"]状态["+pkg.getCurrentStatus()+"]不对，应为2");
				}*/
				if(flstStockMaster.contains(pkg)){
					//对要删除的发放包，清除发放主记录ID和序号，以及还原状态
					pkg.setDeliverAutoId(null);
					pkg.setDeliverSerialNo(null);
					pkg.setCurrentStatus("1");
					//还原物品包实发数量
					//updateDeliverAmount(false,pkg,autoId);
				}
			}
		}
		short i = 0;
		for (CssdStockMaster stockMaster : flstStockMaster) {
			String packageNo = stockMaster.getPackageNo();
			CssdStockMaster originalStockMaster = cssdStockMasterDAO.findById(packageNo);//查出物品包主记录所对应的物品包
			String curStatus = originalStockMaster.getCurrentStatus();//物品包状态
			String deliAutoId = originalStockMaster.getDeliverAutoId();//发放主记录Id
			if(originalStockMaster == null){
				throw new RuntimeException("要发放的物品包在系统中不存在");
			}
			originalStockMaster.setDeliverAutoId(autoId);//与主记录Id对应
			originalStockMaster.setDeliverSerialNo(++i);//序号
			originalStockMaster.setCurrentStatus("2");//修改状态
			originalStockMaster.setAmount(stockMaster.getAmount());
			originalStockMaster.setDeptUnitsCode(stockMaster.getDeptUnitsCode());
			originalStockMaster.setProvideAutoId(stockMaster.getProvideAutoId());
			originalStockMaster.setDeptUnitsName(stockMaster.getDeptUnitsName());
			originalStockMaster.setDetailRemark(stockMaster.getDetailRemark());//备注
			originalStockMaster.setRegisterStatus("0");
			if(isAdd || (!isAdd && (deliAutoId == null || "".equals(deliAutoId)))){
				//校验发放新增操作时的物品包原状态应为1
				//校验修改操作时的物品包原状态:发放包是否是灭菌入库包，原状态应为1
				if ("0".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]还未灭菌入库，不能新增发放记录！");
				}
				if ("2".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]已发放出库，不能新增发放记录！");
				}
				if ("3".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo + "]已回收，不能新增发放记录！");
				}
				if (!"1".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]不是灭菌状态，不能新增发放记录！");
				}
			}else{
				// 检查物品包状态为发放状态2
				if ("0".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]还是打包入库，不能修改发放记录！");
				}
				if ("1".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]还是灭菌入库，不能修改发放记录！");
				}
				if ("3".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo + "]已回收，不能修改发放记录！");
				}
				if (!"2".equals(curStatus)) {
					throw new RuntimeException("物品包[编号：" + packageNo
							+ "]不是发放状态，不能修改发放记录！");
				}
			}
			
			if(!isAdd){
				//更新物品包实发数量
				//updateDeliverAmount(true,stockMaster,autoId);
			}
		}
		//自动审核，扣减物资库存数量
		if(isAutoChk){
			//是否负库存
			boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, RdConstant.APP_CODE_DEPT_MATERIAL, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0").equals("1");
			List<VCssdStock> details = vCssdStockDAO.findDeliverDetail(autoId);
			for(VCssdStock detail : details){
				updateDeptMaterialStock(isZeroDeliver,unitsCode,storageCode,detail);
			}
		}
		//根据发放主记录查找申请明细
		String operationNo = fmaster.getOperationNo(); //
		String deptUnitsCode = fmaster.getDeptUnitsCode(); //
		List<CssdProvideMaster> lstProvide = cssdProvideMasterDAO.findByProperty("billNo", operationNo,deptUnitsCode);
		System.out.println("根据operationNo = "+operationNo+" ：billNo查申请主记录select * from cssd_provide_master t where t.bill_no = '"+operationNo+"' and t.units_code = '"+deptUnitsCode+"'");
		System.out.println("长度："+lstProvide.size());
		CssdProvideMaster mas  = null;
		List<CssdProvideDetail> lstProvideDetail = null;
		if(lstProvide.size() > 0){
			mas = lstProvide.get(0);
			mas.setCurrentStatus("2"); //保存了，为执行状态
			lstProvideDetail = cssdProvideDetailDAO.findByMainAutoId(mas.getAutoId());
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(flstStockMaster);
		data.add(lstProvideDetail);
		ro.setData(data);
		ro.setSuccess(true);
		return ro;
	}

	/**
	 * @param operationNo:发放主记录对应的业务号
	 * @param status:要改变的状态值
	 */
	private void changeProvideMasterStatus(String operationNo,String status,String deptUnitsCode){
		CssdProvideMaster master =(CssdProvideMaster) cssdProvideMasterDAO.findByProperty("billNo", operationNo,deptUnitsCode).get(0);
		master.setCurrentStatus(status);
		cssdProvideMasterDAO.merge(master);
	}
	/**
	 * 根据发放主记录的业务号查询对应申请主记录的autoId
	 * 再对物品包集合循环处理，找出对应的申请明细，再更新对应的数量
	 * @param autoId:发放主记录的autoId
	 * @param operationNo:发放主记录中的申请单据号
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void changeProvideDetailsAmount(String autoId,String operationNo){
		String unitsCode = SessionUtil.getUnitsCode();
		List<CssdStockMaster> lstStockMaster = cssdStockMasterDAO.findByProperty("deliverAutoId", autoId);
		CssdProvideMaster cssdProvider = (CssdProvideMaster) cssdProvideMasterDAO.findByProperty("billNo", operationNo,unitsCode).get(0);
		String mainAutoId = cssdProvider.getAutoId();
		for (CssdStockMaster cssdStockMaster : lstStockMaster) {
			short deliverSerialNo = cssdStockMaster.getDeliverSerialNo();
			//找申请明细
			CssdProvideDetail detail = cssdProvideDetailDAO.findByMainAutoIdAndSerialNo(mainAutoId, deliverSerialNo);
			if(detail == null){
				throw new RuntimeException("审核失败！请检查 在申请明细表[mainAutoId="+mainAutoId+",serialNo="+deliverSerialNo+"]的记录");
			}else{
				//开始更新实发数量
				detail.setCheckAmount(cssdStockMaster.getAmount());
				cssdProvideDetailDAO.attachClean(detail);
			}
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private void changeProvideDetailsAmount1(String autoId,String operationNo,String deptUnitsCode){
		//发放对应的物品包
		//发放给科室的包
		List<CssdStockMaster> lstStockMaster = cssdStockMasterDAO.findByProperty("deliverAutoId", autoId);
		//科室领用主记录
		CssdProvideMaster cssdProvider = (CssdProvideMaster) cssdProvideMasterDAO.findByProperty("billNo", operationNo,deptUnitsCode).get(0);
		//科室领用主记录ID
		String mainAutoId = cssdProvider.getAutoId();
		for (CssdStockMaster cssdStockMaster : lstStockMaster) {
			//科室申请明细
			List<CssdProvideDetail> detail = cssdProvideDetailDAO.findByMainAutoIdAndSerialNo1(mainAutoId,cssdStockMaster.getPackageId());
			int amount=cssdStockMasterDAO.numberAmount(cssdStockMaster.getPackageId(), cssdStockMaster.getDeliverAutoId());
			if(detail == null){
				throw new RuntimeException("审核失败！请检查 在申请明细表[mainAutoId="+mainAutoId+"]的记录");
			}else{
				//开始更新实发数量
				detail.get(0).setCheckAmount(Double.parseDouble(Integer.toString(amount)));
				cssdProvideDetailDAO.attachClean(detail.get(0));
			}
		}
		cssdProvider.setCurrentStatus("3");
		cssdProvideMasterDAO.merge(cssdProvider);
	}
	
	@Override
	public ReObject verify(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的物品发放信息");
		CssdDeliverMaster original = cssdDeliverMasterDAO
				.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物品发放单！");
		}
//		if (STATUS_DELIVER_VERIFY.equals(original.getCurrentStatus())) {
//			throw new RuntimeException("物品发放单已审核，不能重复审核！");
//		}
		original.setCurrentStatus(STATUS_DELIVER_VERIFY);
		//保存后根据业务号改写供应室4.15当前状态为3；直接入库确认
		if(!"".equals(original.getOperationNo()) &&original.getOperationNo()!=null ){
			changeProvideMasterStatus(original.getOperationNo(), "3",original.getDeptUnitsCode());
			//找主记录对应的物品包主记录
			changeProvideDetailsAmount1(original.getAutoId(),original.getOperationNo(),original.getDeptUnitsCode());
		}
		String unitsCode = original.getUnitsCode();
		String storageCode = SessionUtil.getSysUser().getDeptCode();
		//是否负库存
		boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, RdConstant.APP_CODE_DEPT_MATERIAL, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0").equals("1");
		List<VCssdStock> details = vCssdStockDAO.findDeliverDetail(fstrAutoId);
		for(VCssdStock detail : details){
			updateDeptMaterialStock(isZeroDeliver,unitsCode,storageCode,detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}
	
	/**
	 * 扣减物资库存数量.
	 * @param isZeroDeliver
	 * @param unitsCode
	 * @param storageCode
	 * @param detail
	 */
	private void updateDeptMaterialStock(boolean isZeroDeliver,
			String unitsCode, String storageCode, VCssdStock detail){
		if(detail.getDetailAmount() > 0){
			MaterialCurrentStockDept stock = buildCurrentStock(unitsCode,storageCode,detail);
			deptCommMaterialServiceImpl.saveCurrentStock(isZeroDeliver, stock);
		}
	}
	
	private MaterialCurrentStockDept buildCurrentStock(String unitsCode,String storageCode,VCssdStock detail){
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
    	stock.setTradePrice(detail.getDetailTradePrice());
    	stock.setAmount(-detail.getDetailAmount());
    	stock.setFactoryCode(detail.getFactoryCode());
    	//stock.setMadeDate();
    	//stock.setBatch();
    	//stock.setAvailDate();
    	//stock.setPosition();
    	//stock.setHighValueSign();
    	//stock.setAgentSign();
    	return stock;
	}

	/**
	 * 
	 * @param isPlus 是更新还是还原，保存时是更新；删除时是还原
	 * @param pkg 物品包记录
	 */
	/*private void updateDeliverAmount(boolean isPlus,CssdStockMaster pkg,String deliverAutoId){
		String packageId = pkg.getPackageId();
		if(deliverAutoId != null && !"".equals(deliverAutoId) && packageId != null && !"".equals(packageId)){
			//from CssdDeliverApplyDetail where mainAutoId=:mainAutoId and packageId=:packageId
			CssdDeliverApplyDetail applyDetail = cssdDeliverApplyDetailDAO.findByPackageId(deliverAutoId,packageId);
			if(applyDetail == null){
				log.warn("不存在物品包发放主记录ID["+deliverAutoId+"]和物品包编码["+packageId+"]的物品包申请明细记录，无法更新实发数量！");
			}else{
				//实发数量
				Double deliverAmount = applyDetail.getAmount();
				if(deliverAmount == null)
					deliverAmount = 0d;
				if(isPlus)
					deliverAmount += pkg.getAmount();
				else
					deliverAmount -= pkg.getAmount();
				applyDetail.setAmount(deliverAmount);
				
			}
		}
	}*/

	@Override
	public ReObject findStockMasterByPackageNo(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据物品包编号或拼音码，五笔码查询已灭菌的物品包信息");
		String packageNo = (String)fparameter.getConditions().get("packageNo");
		String phoInputCode = (String)fparameter.getConditions().get("phoInputCode");
		String fiveInputCode = (String)fparameter.getConditions().get("fiveInputCode");
		List<CssdStockMaster> data = null;
		if(packageNo != null && !"".equals(packageNo)){
			CssdStockMaster stockMaster = cssdStockMasterDAO.findSterilizedById(SessionUtil.getUnitsCode(),packageNo);
			if(stockMaster != null){
				data = new ArrayList<CssdStockMaster>();
				data.add(stockMaster);
			}
		}
		else if(phoInputCode != null && !"".equals(phoInputCode)){
			data = cssdStockMasterDAO.findSterilizedByInputCode(SessionUtil.getUnitsCode(),phoInputCode,true);
		}else if(fiveInputCode != null && !"".equals(fiveInputCode)){
			data = cssdStockMasterDAO.findSterilizedByInputCode(SessionUtil.getUnitsCode(),fiveInputCode,false);
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findSterilizedByPackageId(String fstrPackageId,
			Double famount) {
		ReObject ro = new ReObject("根据物品包ID和发放数量，按有效期止顺序自动发放已灭菌的物品包");
		List<CssdStockMaster> data = new ArrayList<CssdStockMaster>();
		List<CssdStockMaster> stockMasterList = cssdStockMasterDAO.findSterilizedByPackageId(SessionUtil.getUnitsCode(),fstrPackageId);
		Double tmpAmount = 0.00;
		for(CssdStockMaster stockMaster : stockMasterList){
			tmpAmount ++;
			if(tmpAmount > famount){
				break;
			}
			data.add(stockMaster);
		}
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findBatchSterilizedByPackageId(
			List<CssdStockMaster> cssdStockMasterList) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("根据物品包ID和发放数量，按有效期止顺序自动发放已灭菌的物品包");
		List<CssdStockMaster> data = new ArrayList<CssdStockMaster>();
		
		//针对表格
		for(int i=0;i<cssdStockMasterList.size();i++)
		{
			if(i>0)
			{
				if(cssdStockMasterList.get(i).getProvideAutoId()
						==cssdStockMasterList.get(i-1).getProvideAutoId()
						&&cssdStockMasterList.get(i).getPackageId()
						==cssdStockMasterList.get(i-1).getPackageId())
				{
					continue;
				}
			}
			Double amouString=cssdStockMasterList.get(i).getAmount();
			Double amouString2=cssdStockMasterList.get(i).getAmount();
			String istrPackageId=cssdStockMasterList.get(i).getPackageId();
			String deptUnitsCode=cssdStockMasterList.get(i).getDeptUnitsCode();
			String deptUnitsName=cssdStockMasterList.get(i).getDeptUnitsName();
			String providerAutoId=cssdStockMasterList.get(i).getProvideAutoId();
			if(amouString!=0)
			{
				List<CssdStockMaster> stockMasterList = cssdStockMasterDAO.findSterilizedByPackageId(SessionUtil.getUnitsCode(),istrPackageId);
				Double tmpAmount = 0.00;
				for(CssdStockMaster stockMaster : stockMasterList){
					if(stockMaster.getAddSign()==null){
						tmpAmount ++;
						if(tmpAmount > amouString){
							break;
						}
						stockMaster.setProvideAutoId(providerAutoId);
						stockMaster.setDeptUnitsCode(deptUnitsCode);
						stockMaster.setDeptUnitsName(deptUnitsName);
						stockMaster.setAmount(amouString);
						data.add(stockMaster);
						stockMaster.setAddSign("1");
//						cssdStockMasterDAO.merge(stockMaster);
					}
				}
				
			}
		}
		ro.setData(data);
		return ro;
	}

	/**
	 * 
	 * 过滤已发放的物品包
	 */
	@Override
	public ReObject findProvideListByPackageId(String packageId,String deptCode) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("过滤已发放的物品包");
		String unitsCode = SessionUtil.getUnitsCode();
		List<Object> cssdList=cssdStockMasterDAO.findSterilizedByPackageId(unitsCode,packageId,deptCode);
		ro.setData(cssdList);
		return ro;
	}

	@Override
	public ReObject saveRegister(List<CssdStockMaster> cssdStockMasters) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("修改物品包状态");
		List<CssdStockMaster> cssdStockMasterMerge =new ArrayList<CssdStockMaster>();
		for (CssdStockMaster cssdStockMaster : cssdStockMasters) {
			cssdStockMasterMerge.add(cssdStockMasterDAO.merge(cssdStockMaster));
		}
		ro.setData(cssdStockMasterMerge);
		return ro;
	}

	@Override
	public ReObject findStockMasterStatu(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("过滤包信息");
		String unitsCode = SessionUtil.getUnitsCode();
		List<String> cssdStockMasterList=new ArrayList<String>();
		cssdStockMasterList=cssdStockMasterDAO.findStockMasterStatu(unitsCode, fparameter.getConditions());
		ro.setData(cssdStockMasterList);
		return ro;
	}

	@Override
	public ReObject findStockMasterInpNo(ParameterObject fparameter) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("物品包详细信息");
		String unitsCode = SessionUtil.getUnitsCode();
		List<CssdStockMaster> cssdStockMasterList=new ArrayList<CssdStockMaster>();
		cssdStockMasterList=cssdStockMasterDAO.findStockMasterInpNo(unitsCode, fparameter.getConditions());
		ro.setData(cssdStockMasterList);
		return ro;
	}

	/***
	 * 审核
	 */
	@Override
	public ReObject verifyInpNo(List<CssdStockMaster> cssdStockMasterList,String stat) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("审核病人用品登记");
		
		List<CssdStockMaster> cssdStockMasterLists=new ArrayList<CssdStockMaster>();
		for (CssdStockMaster cssdStockMaster : cssdStockMasterList) {
			if(stat=="2")
			{
				cssdStockMaster.setVerifier(SessionUtil.getUserCode());
				cssdStockMaster.setVerifierDate(new Date());
			}
			else
			{
				cssdStockMaster.setVerifier("");
				cssdStockMaster.setVerifierDate(null);
				cssdStockMaster.setRegister("");
				cssdStockMaster.setRegisteDate(null);
			}
			cssdStockMaster.setRegisterStatus(stat);
			cssdStockMasterLists.add(cssdStockMasterDAO.merge(cssdStockMaster));
		}
		ro.setData(cssdStockMasterLists);
		return ro;
	}

	@Override
	public ReObject findStockMasterPackageNo(String PackageNo) {
		// TODO Auto-generated method stub
		ReObject ro = new ReObject("按照包号、过滤已灭菌的包");
		List<CssdStockMaster> listArray=new ArrayList<CssdStockMaster>();
		listArray.add(cssdStockMasterDAO.findStockMasterPackageNo(PackageNo));
		ro.setData(listArray);
		return ro;
	}
	
	
}
