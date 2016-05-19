package cn.superion.materialDept.receive.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.base.ReadPropertiesFile;
import cn.superion.center.config.dao.CdStorageDictDAO;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.center.config.entity.CdStorageDict;
import cn.superion.center.material.dao.CdMaterialCardDAO;
import cn.superion.center.material.dao.CdMaterialDictDAO;
import cn.superion.center.material.entity.CdMaterialCard;
import cn.superion.center.material.entity.CdMaterialDict;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialCurrentStockDAO;
import cn.superion.material.dao.MaterialProvideDetailDAO;
import cn.superion.material.dao.MaterialProvideMasterDAO;
import cn.superion.material.dao.MaterialRdsMasterDAO;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialProvideDetail;
import cn.superion.material.entity.MaterialProvideMaster;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;

/**
 * 物资领用服务实现
 * 
 * @author 曹国魁
 * 
 */
public class ApplyImpl implements IApply {
	private Log log = LogFactory.getLog(ApplyImpl.class);
	private CdMaterialDictDAO cdMaterialDictDAO;
	private CdMaterialCardDAO cdMaterialCardDAO;
	private MaterialProvideMasterDAO materialProvideMasterDAO;
	private MaterialProvideDetailDAO materialProvideDetailDAO;
	private MaterialRdsMasterDAO materialRdsMasterDAO;
	private cn.superion.material.common.ICommMaterialService commMaterialServiceImpl;
	private cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl;
	private CdSysParamDAO cdSysParamDAO;
	private MaterialCurrentStockDAO materialCurrentStockDAO;
	private CdStorageDictDAO cdStorageDictDAO;

	public CdStorageDictDAO getCdStorageDictDAO() {
		return cdStorageDictDAO;
	}

	public void setCdStorageDictDAO(CdStorageDictDAO cdStorageDictDAO) {
		this.cdStorageDictDAO = cdStorageDictDAO;
	}

	public MaterialCurrentStockDAO getMaterialCurrentStockDAO() {
		return materialCurrentStockDAO;
	}

	public void setMaterialCurrentStockDAO(
			MaterialCurrentStockDAO materialCurrentStockDAO) {
		this.materialCurrentStockDAO = materialCurrentStockDAO;
	}

	//科室领用入库确认之前是否在核算系统中打印出库单 1：不打印，2：打印，泰州是2，东方医院是1
	public static final String DEPT_RECEIVE_STATUS = ReadPropertiesFile
	.getValue("DEPT_RECEIVE_STATUS");
	
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

	public CdMaterialCardDAO getCdMaterialCardDAO() {
		return cdMaterialCardDAO;
	}

	public void setCdMaterialCardDAO(CdMaterialCardDAO cdMaterialCardDAO) {
		this.cdMaterialCardDAO = cdMaterialCardDAO;
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

	public MaterialRdsMasterDAO getMaterialRdsMasterDAO() {
		return materialRdsMasterDAO;
	}

	public void setMaterialRdsMasterDAO(
			MaterialRdsMasterDAO materialRdsMasterDAO) {
		this.materialRdsMasterDAO = materialRdsMasterDAO;
	}

	public cn.superion.material.common.ICommMaterialService getCommMaterialServiceImpl() {
		return commMaterialServiceImpl;
	}

	public void setCommMaterialServiceImpl(
			cn.superion.material.common.ICommMaterialService commMaterialServiceImpl) {
		this.commMaterialServiceImpl = commMaterialServiceImpl;
	}

	public cn.superion.materialDept.common.service.ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			cn.superion.materialDept.common.service.ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	@Override
	public ReObject deleteApply(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的物资领用申请");
		MaterialProvideMaster master = materialProvideMasterDAO
				.findById(fstrAutoId);
		if (master == null)
			throw new RuntimeException("物资领用申请不存在！");
		if (!"0".equals(master.getCurrentStatus())) {
			throw new RuntimeException("物资领用申请已审核或领用出库，不能删除！");
		}
		materialProvideDetailDAO.delByMainAutoId(fstrAutoId);
		materialProvideMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findApplyDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看物资领用申请单据详细信息记录");
		MaterialProvideMaster master = materialProvideMasterDAO
				.findById(fstrAutoId);
		List<MaterialProvideDetail> details = materialProvideDetailDAO
				.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findApplyMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询物资领用申请单据信息");
		SysUser user = SessionUtil.getSysUser();
//		fparameter.getConditions().put("deptCode", user.getDeptCode());
		List<Object> data = materialProvideMasterDAO.findAutoIdsByCondition(
				user.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findDeliverDetail(String fstrAutoId) {
		ReObject ro = new ReObject("根据领用出库单主记录ID查询领用物资明细列表和物资领用申请明细列表");
		// 查询领用出库单据
		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		if (objs[0] == null) {
			return ro;
		}
		MaterialRdsMaster master = (MaterialRdsMaster) objs[0];
		String billNo = master.getOperationNo();
		// 查询领用申请单据
		MaterialProvideMaster pmaster = materialProvideMasterDAO.findByStorageBillNo(master.getStorageCode(), billNo);
		if (pmaster == null) {
			ro.setError("物资领用申请[单据号：" + billNo + "]不存在！");
			return ro;
		}
		List<MaterialProvideDetail> pdetails = materialProvideDetailDAO
				.findByMainAutoId(pmaster.getAutoId());
		List data = new ArrayList();
		data.add(objs[0]);
		data.add(objs[1]);
		data.add(pmaster);
		data.add(pdetails);
		ro.setData(data);
		return ro;
	}
	
	@Override
	public ReObject findDeptByStorageCode(String storageCode) {
		ReObject ro = new ReObject("根据领用出库单主记录ID查询领用物资明细列表和物资领用申请明细列表");
		// 查询领用出库单据
//		String unitsCode = SessionUtil.getUnitsCode();
		List<Object> pdetails = materialProvideMasterDAO
				.findDeptByStorageCode(storageCode);
		ro.setData(pdetails);
		return ro;
	}
	
	@Override
	public ReObject findDeliverListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("过滤已审的领用出库单");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("deptCode", user.getDeptCode());
		//科室领用入库确认之前是否在核算系统中打印出库单 1：不打印，2：打印，泰州是2，东方医院是1
		String currentStatus=DEPT_RECEIVE_STATUS == null ? "1" : DEPT_RECEIVE_STATUS;
		fparameter.getConditions().put("currentStatus", currentStatus);
		//整进整出的领用单不需要入库确认
		fparameter.getConditions().put("notRdTogether", true);
		List<Object> data = materialRdsMasterDAO.findAutoIdsByDeliverCondition(
				"", fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveApply(MaterialProvideMaster fmaster,
			List<MaterialProvideDetail> fdetails) {
		ReObject ro = new ReObject("保存物资领用申请信息");
		if (fmaster == null)
			throw new RuntimeException("物资领用申请主记录不能为空！");
		if (fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("物资领用申请明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		//String deptCode = user.getDeptCode();//byzcl 东方用
		String deptCode = fmaster.getDeptCode();
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
						RdConstant.R,"666", storageCode));
			} else {
				// 新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if (!materialProvideMasterDAO.checkBillNoUnique(unitsCode,
						storageCode, billNo)) {
					throw new RuntimeException("物资领用申请单据编号[" + billNo + "]在单位["
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
			fmaster.setDeptCode(deptCode);
			fmaster.setCheckAmountSign("0");
			fmaster.setCheckSupplySign("0");//
			fmaster.setSendStatus("0"); //初始化状态值
			fmaster.setCurrentStatus("0");//科室单据未审状态
			materialProvideMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		} else {
			// 修改
			MaterialProvideMaster original = materialProvideMasterDAO
					.findById(autoId);
			if (original == null) {
				throw new RuntimeException("不存在系统标识号为" + autoId + "的物资领用申请主记录！");
			}
			if (!"0".equals(original.getCurrentStatus())) {
				throw new RuntimeException("物资领用申请已审核或领用出库，不能修改！");
			}
			fmaster.setCurrentStatus("0");
			materialProvideMasterDAO.merge(fmaster);
			materialProvideDetailDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		boolean hasSendMaterial  = false;
		for (MaterialProvideDetail detail : fdetails) {
			detail.setMainAutoId(autoId);
			if(detail.getStorageMaterialSign()!=null && detail.getStorageMaterialSign().equals("0")){
				hasSendMaterial =true;
			}
			detail.setSerialNo(++i);
			detail.setSendAmount(detail.getAmount());
			materialProvideDetailDAO.save(detail);
		}
		if(hasSendMaterial){
			fmaster.setSendStatus("0");
			materialProvideMasterDAO.merge(fmaster);
		}
		
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verifyApply(String fstrAutoId,String status) {
		ReObject ro = new ReObject("审核物资领用申请");
		MaterialProvideMaster original = materialProvideMasterDAO
				.findById(fstrAutoId);
		if (original == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物资领用申请主记录！");
		}
		if (!"0".equals(original.getCurrentStatus())) {
			throw new RuntimeException("物资领用申请已审核或领用出库，不能审核！");
		}
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		original.setCurrentStatus(status);
		if(original.getSendStatus() !=null  && original.getSendStatus().equals("0")){
			original.setSendStatus("1");//配送状态改为 科室审核
		}
		String unitsCode=SessionUtil.getUnitsCode();
		String application=SessionUtil.getAppCode();
		String isZeroDeliver=cdSysParamDAO.findByParaCode(unitsCode, application, "0601");
		String storageCode=original.getStorageCode();
//		String invoiceType=original.getInvoiceType();
		List<MaterialProvideDetail> detailList=materialProvideDetailDAO.findByMainAutoId(fstrAutoId);
		for(MaterialProvideDetail detail:detailList){
			String materialId=detail.getMaterialId();
			String batch=detail.getBatch();
			String barCode=detail.getBarCode();
			if (barCode == null || barCode.trim().equals("")) {
				barCode = "0";
			}
			MaterialCurrentStock _stock = materialCurrentStockDAO
			.findVirtualAmountByUniqueIndex(storageCode, materialId, batch,
					barCode);
			if(_stock!=null){
				Double virtualAmount=_stock.getVirtualAmount().isNaN() ? _stock.getAmount() : _stock.getVirtualAmount();
				_stock.setVirtualAmount(virtualAmount - detail.getAmount());
			}
		}
		
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}
	
	
	@Override
	public ReObject cancelApply(String fstrAutoId){
		ReObject ro = new ReObject("弃审物资领用申请");
		MaterialProvideMaster master = materialProvideMasterDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为" + fstrAutoId + "的物资领用申请主记录！");
		}
		if ("0".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库未审核，不能弃审！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已记账，不能弃审！");
		}
		master.setCurrentStatus("0");
		master.setVerifier(null);
		master.setVerifyDate(null);
		
		String storageCode=master.getStorageCode();
		String invoiceType=master.getInvoiceType();
		List<MaterialProvideDetail> detailList=materialProvideDetailDAO.findByMainAutoId(fstrAutoId);
		for(MaterialProvideDetail detail:detailList){
			String materialId=detail.getMaterialId();
			String batch=detail.getBatch();
			String barCode=detail.getBarCode();
			if (barCode == null || barCode.trim().equals("")) {
				barCode = "0";
			}
			MaterialCurrentStock _stock = materialCurrentStockDAO
			.findVirtualAmountByUniqueIndex(storageCode, materialId, batch,
					barCode);
			if(_stock!=null && _stock.getVirtualAmount()!=null){
				Double virtualAmount=_stock.getVirtualAmount();
				if(invoiceType.equals("1")){
					_stock.setVirtualAmount( detail.getAmount() + virtualAmount);
				}
				else
				{
					_stock.setVirtualAmount( virtualAmount + detail.getAmount());
				}
				
			}
		}
		return ro;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public ReObject verifyDeptReceive(String fstrAutoId) {
		ReObject ro = new ReObject("根据领用出库单主记录ID确认当前的物资领用入科信息");
		Object[] objs = commMaterialServiceImpl.findById(fstrAutoId);
		MaterialRdsMaster master = (MaterialRdsMaster) objs[0];
		List<MaterialRdsDetail> details = (List<MaterialRdsDetail>) objs[1];
		if (master == null) {
			throw new RuntimeException("领用出库单[系统标识号：" + fstrAutoId + "]不存在！");
		}
		if ("0".equals(master.getCurrentStatus())) {
			throw new RuntimeException("领用出库单[系统标识号：" + fstrAutoId
					+ "]仓库还未审核，不能审核！");
		}
		String unitsCode = master.getUnitsCode();
		String cardCode = master.getCardCode();
		MaterialRdsMasterDept mrmd = null;
		MaterialProvideMaster pmaster = null;
		List<MaterialRdsDetailDept> mrdds = null;
		boolean isBlue = "1".equals(master.getInvoiceType());
		if (true) {//isBluebyzcl
			//蓝字单据时，要生成科室领用物资入库单
			String billNo = master.getOperationNo();
			pmaster = materialProvideMasterDAO
					.findByStorageBillNo(master.getStorageCode(), billNo);
			if (pmaster == null) {
				throw new RuntimeException("科室领用申请单[单据号：" + billNo
						+ "]不存在,科室不能入库确认!");
			}
			if (pmaster.getCurrentStatus().equals("3")) {
				throw new RuntimeException("科室领用申请单[单据号：" + billNo
						+ "]已入库确认!");
			}
			pmaster.setCurrentStatus("3");
			cardCode = pmaster.getCardCode();
			MaterialRdsMasterDept dmaster = buildMaterialRdsMasterDept(master,
					pmaster);
			List<MaterialRdsDetailDept> ddetails = new ArrayList<MaterialRdsDetailDept>();
			for (MaterialRdsDetail detail : details) {
				ddetails.add(buildMaterialRdsDetailDept(detail));
			}
			Object[] dobjs = deptCommMaterialServiceImpl
					.save(dmaster, ddetails);
			mrmd = (MaterialRdsMasterDept) dobjs[0];
			mrdds = (List<MaterialRdsDetailDept>) dobjs[1];
		}
		Double payout = 0d;
		for (MaterialRdsDetail detail : details) {
			Double retailMoney = 0d;
			if(isBlue){
				CdMaterialDict madt = cdMaterialDictDAO.findById(unitsCode, detail.getMaterialId());
				retailMoney = madt.getRetailPrice()*detail.getAmount();
			}else{
				retailMoney = detail.getRetailMoney();
			}
			if(retailMoney == null){
				log.warn(detail.getMaterialName()+"["+detail.getMaterialId()+"]售价为空，不能有效合计科室领用物资总费用！");
			}else{
				payout += retailMoney;
			}
		}
		//更新物资领用卡帐户余额
		Double accountRemain = 0d;
		String appCode = SessionUtil.getAppCode();
		boolean isUseCard = "1".equals(cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0"));
		if(isUseCard){
			if(cardCode != null && !cardCode.equals("")){
				CdMaterialCard cmc = cdMaterialCardDAO.updateCredit(unitsCode, cardCode,payout);
				if(cmc == null){
					log.warn("物资领用卡[卡号:"+cardCode+"]不存在或已停用，不能更新帐户余额(支出:"+payout+")！");
				}else{
					accountRemain = cmc.getAccountRemain();
				}
			}else{
				log.warn("物资领用卡号为空，不能更新帐户余额！");
			}
		}
		List data = new ArrayList();
		data.add(accountRemain);
		data.add(mrmd);
		data.add(mrdds);
		data.add(pmaster);
		ro.setData(data);
		return ro;
	}

	/**
	 * 构造科室物资入库单主记录
	 * 
	 * @param master
	 *            领用出库单主记录
	 * @param pmaster
	 *            物资领用申请单主记录
	 * @return
	 */
	private MaterialRdsMasterDept buildMaterialRdsMasterDept(
			MaterialRdsMaster master, MaterialProvideMaster pmaster) {
		MaterialRdsMasterDept dmaster = new MaterialRdsMasterDept();
		dmaster.setUnitsCode(SessionUtil.getUnitsCode());
		dmaster.setInvoiceType(master.getInvoiceType());
		dmaster.setAutoId(null);
		dmaster.setBillNo(null);
		dmaster.setBillDate(null);
		dmaster.setRdFlag(RdConstant.R);
		dmaster.setRdType("101");
		dmaster.setOperationType(RdConstant.R_APPLY);
		dmaster.setOperationNo(master.getBillNo());
		// 供应部门
		dmaster.setSupplyDeptCode(pmaster.getSupplyDeptCode());
		// 领用卡号
		dmaster.setCardCode(pmaster.getCardCode());
		// 清空仓库编码，准备写科室编码
		dmaster.setStorageCode(null);
		dmaster.setCurrentStatus("1");
		return dmaster;
	}

	private MaterialRdsDetailDept buildMaterialRdsDetailDept(
			MaterialRdsDetail detail) {
		MaterialRdsDetailDept ddetail = new MaterialRdsDetailDept();
		ddetail.setAutoId(null);
		ddetail.setMainAutoId(null);
		ddetail.setSourceAutoId(detail.getAutoId());//科室确认时，写收发存明细对应的autoId
		ddetail.setSourceInputAutoId(null);
		ddetail.setCurrentStockAmount(0d);
		ddetail.setOutSign(null);
		ddetail.setOutAmount(0d);
		ddetail.setMaterialClass(detail.getMaterialClass());
		ddetail.setBarCode(detail.getBarCode());
		ddetail.setMaterialId(detail.getMaterialId());
		ddetail.setMaterialCode(detail.getMaterialCode());
		ddetail.setMaterialName(detail.getMaterialName());
		ddetail.setMaterialSpec(detail.getMaterialSpec());
		ddetail.setMaterialUnits(detail.getMaterialUnits());
		ddetail.setAmount(detail.getAmount());
		ddetail.setTradePrice(detail.getTradePrice());
		ddetail.setTradeMoney(detail.getTradeMoney());
		ddetail.setRetailPrice(detail.getRetailPrice());
		ddetail.setRetailMoney(detail.getRetailMoney());
		ddetail.setFactoryCode(detail.getFactoryCode());
		ddetail.setBatch(detail.getBatch());
		ddetail.setMadeDate(detail.getMadeDate());
		ddetail.setAvailDate(detail.getAvailDate());
		ddetail.setHighValueSign(detail.getHighValueSign());
		ddetail.setAgentSign(detail.getAgentSign());
		ddetail.setMaterialBarCode(detail.getMaterialBarCode());
		return ddetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findAccountRemain(String fstrCardCode) {
		ReObject ro = new ReObject("根据卡号返回物资领用卡帐户余额");
		if(fstrCardCode == null || "".equals(fstrCardCode)){
			ro.setError("领用卡号不能为空！");
			return ro;
		}
		CdMaterialCard cmc = cdMaterialCardDAO.findValidCardByCardCode(SessionUtil.getUnitsCode(), fstrCardCode);
		if(cmc == null){
			ro.setError("物资领用卡[卡号:"+fstrCardCode+"]不存在或已停用");
			return ro;
		}
		List data = new ArrayList();
		data.add(cmc.getAccountRemain());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCurrentMaterialCard() {
		ReObject ro = new ReObject("查询当前用户所属科室的物资领用卡列表");
		SysUser user = SessionUtil.getSysUser();
		List<CdMaterialCard> data = cdMaterialCardDAO.findValidCardByDeptCode(user.getUnitsCode(), user.getDeptCode());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findVirtualAmountByMaterialId(String storageCode,
			String materialId) {
		ReObject ro = new ReObject("查询物资虚拟库存");
		
		String unitsCode=SessionUtil.getUnitsCode();
		
		if(storageCode!=null && !storageCode.equals("")){
			List<CdStorageDict> storageList = cdStorageDictDAO.findByProperty("storageCode", storageCode);
			if(storageList.size() !=1){
				throw new RuntimeException("请检查仓库字典，编码为:"+storageCode+"不存在或有重复！");
			}
			unitsCode = storageList.get(0).getUnitsCode();
		}
		Double virtualAmount=materialCurrentStockDAO.findVirtalAmountByID(
				unitsCode, storageCode, materialId);
		List<Object> dataList=new ArrayList<Object>();
		dataList.add(virtualAmount);
		ro.setData(dataList);
		return ro;
	}

	
}
