package cn.superion.materialDept.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.material.common.BillNoGenerator;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialCurrentRcptNoDAO;
import cn.superion.material.entity.MaterialCurrentRcptNo;
import cn.superion.material.entity.MaterialCurrentStock;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.MaterialRdsMaster;
import cn.superion.materialDept.dao.MaterialCurrentStockDeptDAO;
import cn.superion.materialDept.dao.MaterialRdsDetailDeptDAO;
import cn.superion.materialDept.dao.MaterialRdsMasterDeptDAO;
import cn.superion.materialDept.entity.MaterialCurrentStockDept;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.materialDept.his.service.IHisServiceFacade;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 科室物资公共服务实现
 * @author 曹国魁
 *
 */
public class CommMaterialServiceImpl implements ICommMaterialService {
	private CdSysParamDAO cdSysParamDAO;
	private MaterialCurrentRcptNoDAO materialCurrentRcptNoDAO;
	private MaterialRdsMasterDeptDAO materialRdsMasterDeptDAO;
	private MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO;
	private MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO;
	private IHisServiceFacade hisServiceFacade;
	public static final String ERROR_MSG_ZERO_DELIVER = "物资库[物资编码:%S]存量不足，不允许负库存，请重新操作或修改系统参数允许负库存！";
	

	public IHisServiceFacade getHisServiceFacade() {
		return hisServiceFacade;
	}

	public void setHisServiceFacade(IHisServiceFacade hisServiceFacade) {
		this.hisServiceFacade = hisServiceFacade;
	}

	public MaterialRdsMasterDeptDAO getMaterialRdsMasterDeptDAO() {
		return materialRdsMasterDeptDAO;
	}

	public void setMaterialRdsMasterDeptDAO(
			MaterialRdsMasterDeptDAO materialRdsMasterDeptDAO) {
		this.materialRdsMasterDeptDAO = materialRdsMasterDeptDAO;
	}

	public MaterialRdsDetailDeptDAO getMaterialRdsDetailDeptDAO() {
		return materialRdsDetailDeptDAO;
	}

	public void setMaterialRdsDetailDeptDAO(
			MaterialRdsDetailDeptDAO materialRdsDetailDeptDAO) {
		this.materialRdsDetailDeptDAO = materialRdsDetailDeptDAO;
	}

	public MaterialCurrentStockDeptDAO getMaterialCurrentStockDeptDAO() {
		return materialCurrentStockDeptDAO;
	}

	public void setMaterialCurrentStockDeptDAO(
			MaterialCurrentStockDeptDAO materialCurrentStockDeptDAO) {
		this.materialCurrentStockDeptDAO = materialCurrentStockDeptDAO;
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
	public MaterialRdsMasterDept deleteByAutoId(String fstrAutoId) {
		MaterialRdsMasterDept master = materialRdsMasterDeptDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的收发存主记录！");
		}
		if ("1".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入出库已审核，不能删除！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入出库已记帐，不能删除！");
		}
		materialRdsDetailDeptDAO.deleteByMainAutoId(fstrAutoId);
		materialRdsMasterDeptDAO.delete(master);
		return master;
	}

	@Override
	public ReObject findCurrentStockByFactoryBatch(ParameterObject fparameter) {
		ReObject ro = new ReObject("查找当前现存量的数据列表");
		String storageCode = SessionUtil.getSysUser().getDeptCode();
		String materialId = (String)fparameter.getConditions().get("materialId");
		if(storageCode == null || "".equals(storageCode.trim())){
			throw new RuntimeException("仓库编码不能为空！");
		}
		if(materialId == null || "".equals(materialId.trim())){
			throw new RuntimeException("物资ID不能为空！");
		}
		fparameter.getConditions().put("storageCode",storageCode);
		List<MaterialCurrentStockDept> data = materialCurrentStockDeptDAO.findByCondition(SessionUtil.getUnitsCode(), fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findCurrentStockById(String fstrMaterialId) {
		ReObject ro = new ReObject("查询物资当前现存量");
		Map<String,Double> map = findCurStockById(fstrMaterialId,null);
		List<Map<String,Double>> data = new ArrayList<Map<String,Double>>();
		data.add(map);
		ro.setData(data);
		return ro;	}
	
	@Override
	public ReObject findCurrentStockById(String fstrMaterialId,
			String fstrStorageCode) {
		ReObject ro = new ReObject("查询物资当前现存量");
		Map<String,Double> map = findCurStockById(fstrMaterialId,fstrStorageCode);
		List<Map<String,Double>> data = new ArrayList<Map<String,Double>>();
		data.add(map);
		ro.setData(data);
		return ro;
	}
	
	private Map<String,Double> findCurStockById(String fstrMaterialId,
			String fstrStorageCode){
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String storageCode = fstrStorageCode == null || "".equals(fstrStorageCode) ? user.getDeptCode() : fstrStorageCode;
		Double currentAmount = null;
		boolean isAll = storageCode == null || "".equals(storageCode) || "0".equals(storageCode);
		if(!isAll){
			currentAmount = materialCurrentStockDeptDAO.findAmount(unitsCode, storageCode, fstrMaterialId);
		}
		Double totalCurrentAmount = materialCurrentStockDeptDAO.findAmount(unitsCode, fstrMaterialId);
		Map<String,Double> map = new HashMap<String,Double>();
		if(!isAll){
			map.put("currentStockAmount", currentAmount);
		}
		map.put("totalCurrentStockAmount", totalCurrentAmount);
		return map;
	}

	@Override
	public String getNextBillNo(String rdFlag) {
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String storageCode = "0";
		/*if(storageCode == null || "".equals(storageCode))
			storageCode = "0";*/
		//应用程序编号 889
		String appNo = user.getAppCode();
		String rcptType = cdSysParamDAO.findByParaCode(unitsCode, appNo, RdConstant.SYS_PARA_CODE_BILL_NO,"1");
		String nowTypeDate = BillNoGenerator.getNowTypeDate(rcptType);
		//科室物资业务号生成都是一个仓库编码(0)下的
		MaterialCurrentRcptNo rcptNo = materialCurrentRcptNoDAO.findByStorageCode(unitsCode, rdFlag, rcptType,storageCode,nowTypeDate); 
		String curTypeDate = null;
		String curNo = null;
		if(rcptNo != null){
			curTypeDate = rcptNo.getTypeDate();
			curNo = rcptNo.getCurrentNo();
		}
		String nextNo = null;
		try{
			nextNo = BillNoGenerator.getNextNo(rcptType,curTypeDate,curNo,nowTypeDate);
		}catch(Exception e){
			throw new RuntimeException("生成下一个流水号错误",e);
		}
		//流水号数据库中不存在，则新增
		if(rcptNo == null){
			rcptNo = new MaterialCurrentRcptNo(unitsCode,rdFlag,rcptType,storageCode,nowTypeDate,nextNo);
			materialCurrentRcptNoDAO.save(rcptNo);
		}else{
			rcptNo.setTypeDate(nowTypeDate);
			rcptNo.setCurrentNo(nextNo);
			materialCurrentRcptNoDAO.flush();
		}
		return nowTypeDate+nextNo;
	}

	@Override
	public Object[] save(MaterialRdsMasterDept fmaster,
			List<MaterialRdsDetailDept> fdetails) {
		if(fmaster == null){
			throw new RuntimeException("收发存主记录不能为空！");
		}
		if(fdetails == null || fdetails.isEmpty()){
			throw new RuntimeException("收发存明细记录不能为空！");
		}
		if(fmaster.getInvoiceType() == null || "".equals(fmaster.getInvoiceType())){
			throw new RuntimeException("单据类型不能为空！");
		}
		if(fmaster.getRdFlag() == null || "".equals(fmaster.getRdFlag())){
			throw new RuntimeException("收发标志不能为空！");
		}
		/*if(fmaster.getRdType() == null || "".equals(fmaster.getRdType())){
			throw new RuntimeException("收发类别不能为空！");
		}*/
		if(fmaster.getOperationType() == null || "".equals(fmaster.getOperationType())){
			throw new RuntimeException("业务类型不能为空！");
		}
		Object[] objs = new Object[2];
		SysUser user = SessionUtil.getSysUser();
		String personId = user.getPersonId();
		String unitsCode = user.getUnitsCode();
		String appCode = user.getAppCode();
		String deptCode=user.getDeptCode();
		
		Date curDate = new Date();
		String storageCode = fmaster.getStorageCode();
		if(storageCode == null || "".equals(storageCode)){
			storageCode = user.getDeptCode();
			fmaster.setStorageCode(storageCode);
		}
		if(fmaster.getDeptCode() == null || "".equals(fmaster.getDeptCode())){
			fmaster.setDeptCode(deptCode);
		}
		// 判断新增或修改状态
		boolean isAdd = true;
		//String operationNo = fmaster.getOperationNo();
		String mainAutoId = fmaster.getAutoId();
		isAdd = mainAutoId == null || "".equals(mainAutoId);
		boolean isVerified = "1".equals(fmaster.getCurrentStatus()); 
		boolean isZeroDeliver = false;
		if (isVerified) {
			//加载系统参数：是否允许零出库
			isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0").equals("1");
			fmaster.setVerifier(personId);
			fmaster.setVerifyDate(curDate);
		}
		if (isAdd) {
			//新增
			// 设置流水号和流水日期
			String billNo = fmaster.getBillNo();
			if (billNo == null || "".equals(billNo)) {
				fmaster.setBillNo(getNextBillNo(fmaster.getRdFlag()));
			}else{
				//新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if(!materialRdsMasterDeptDAO.checkBillNoUnique(unitsCode,storageCode,fmaster.getRdFlag(),billNo)){
					throw new RuntimeException("手工输入的单据编号["+billNo+"]在单位["+unitsCode+"],仓库["+storageCode+"],业务类型["+fmaster.getOperationType()+"]下有重复");
				}
			}
			if (fmaster.getBillDate() == null) {
				fmaster.setBillDate(curDate);
			}
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			// 保存主记录
			materialRdsMasterDeptDAO.save(fmaster);
			mainAutoId = fmaster.getAutoId();
		} else {
			//修改
			mainAutoId = fmaster.getAutoId();
			MaterialRdsMasterDept original = materialRdsMasterDeptDAO.findById(mainAutoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+mainAutoId+"的收发存主记录！");
			}
			if (!"0".equals(original.getCurrentStatus())) {
				String msg = "本次入出库已审核，不能修改保存！";
				if (isVerified) {
					msg = "本次入出库已审核，不能再次审核保存！";
				}
				throw new RuntimeException(msg);
			}
			materialRdsMasterDeptDAO.merge(fmaster);
			materialRdsDetailDeptDAO.deleteByMainAutoId(mainAutoId);
		}
		// 保存明细记录
		Short i = 0;
		for (MaterialRdsDetailDept detail : fdetails) {
			detail.setSerialNo(++i);
			String batch = detail.getBatch();
			if(batch == null || "".equals(batch)){
				batch = "0";
				detail.setBatch(batch);
			}
			String barCode = detail.getBarCode();
			if(barCode == null || "".equals(barCode)){
				barCode = "0";
				detail.setBarCode(barCode);
			}
			if(isVerified){
				// 若已审核，则更新库存数量
				MaterialCurrentStockDept stock = buildCurrentStock(RdConstant.D.equals(fmaster.getRdFlag()),unitsCode,storageCode,detail);
				MaterialCurrentStockDept mStock = saveCurrentStock(isZeroDeliver,stock);
				detail.setCurrentStockAmount(mStock.getAmount());
			}else{
				//因是未审核，收发存明细记录的现存量只是一个参考
				MaterialCurrentStockDept _stock = materialCurrentStockDeptDAO.findByUniqueIndex(unitsCode, storageCode, detail.getMaterialId(), batch,barCode);
				detail.setCurrentStockAmount(_stock == null?0d:_stock.getAmount());
			}
			detail.setMainAutoId(mainAutoId);
			materialRdsDetailDeptDAO.save(detail);
		}
		objs[0] = fmaster;
		objs[1] = fdetails;
		return objs;
	}
	
	/**
	 * 构造现存量汇总记录对象
	 * @param isDeliver 是否出库
	 * @param unitsCode
	 * @param storageCode
	 * @param detail
	 * @return
	 */
	private MaterialCurrentStockDept buildCurrentStock(boolean isDeliver,String unitsCode,String storageCode,MaterialRdsDetailDept detail){
    	MaterialCurrentStockDept stock = new MaterialCurrentStockDept();
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
    	stock.setAmount(isDeliver?-detail.getAmount():detail.getAmount());
    	if(detail.getRetailPrice() !=null){
    		stock.setRetailPrice(detail.getRetailPrice());
    	}
    	if(detail.getWholeSalePrice() != null){
    		stock.setWholeSalePrice(detail.getWholeSalePrice());
    	}
    	
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
	 * @param isZeroDeliver 系统是否允许零出库
	 * @param fstock 要累积的现存量记录
	 * @return
	 */
	@Override
	public MaterialCurrentStockDept saveCurrentStock(boolean isZeroDeliver,MaterialCurrentStockDept fstock) {
		String batch = fstock.getBatch();
		if(batch == null || "".equals(batch)){
			batch = "0";
		}
		String barCode = fstock.getBarCode();
		if(barCode == null || "".equals(barCode)){
			barCode = "0";
		}
		String unitsCode = fstock.getUnitsCode();
		SysUser user = SessionUtil.getSysUser();
		if(unitsCode==null){
			unitsCode = user.getUnitsCode();
		}
		String storageCode = fstock.getStorageCode();
		String materialId = fstock.getMaterialId();
		String materialCode = fstock.getMaterialCode();
		String materialName = fstock.getMaterialName();
		MaterialCurrentStockDept _stock = new MaterialCurrentStockDept();
		String highValueSign=fstock.getHighValueSign();
		if(highValueSign!=null && highValueSign.equals("1") && !barCode.equals("0")){
			_stock = materialCurrentStockDeptDAO.findByBarCode(barCode);
		}
		else
		{
			_stock = materialCurrentStockDeptDAO.findByUniqueIndex(unitsCode, storageCode, materialId, batch,barCode);
		}
		if(_stock == null){
			//新增
			if(fstock.getAmount()<0){
				if(!isZeroDeliver)
					throw new RuntimeException(String.format(ERROR_MSG_ZERO_DELIVER, materialCode+materialName));
			}
			materialCurrentStockDeptDAO.save(fstock);
			return fstock;
		}else{
			//修改
			Double currentAmount = _stock.getAmount()+fstock.getAmount(); 
			if(currentAmount<0){
				if(!isZeroDeliver)
					throw new RuntimeException(String.format(ERROR_MSG_ZERO_DELIVER, materialCode+materialName));
			}
			//最近值
			//进价
			if(fstock.getTradePrice()!=null){
				_stock.setTradePrice(fstock.getTradePrice());
			}
			//售价
			if(fstock.getRetailPrice()!=null){
				_stock.setRetailPrice(fstock.getRetailPrice());
			}
			//批发价
			if(fstock.getWholeSalePrice()!=null){
				_stock.setWholeSalePrice(fstock.getWholeSalePrice());
			}
			//累计值
			//结存数量
			_stock.setAmount(currentAmount);
			return _stock;
		}
	}

	@Override
	public Object[] verify(String fstrAutoId) {
		Object[] objs = new Object[2];
		MaterialRdsMasterDept master = materialRdsMasterDeptDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的收发存主记录！");
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
		boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, appNo, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0").equals("1");
		master.setCurrentStatus("1");
		master.setVerifier(personId);
		master.setVerifyDate(new Date());
		List<MaterialRdsDetailDept> details = materialRdsDetailDeptDAO.findByMainAutoId(master.getAutoId());
		for (MaterialRdsDetailDept detail : details) {
			// 更新库存数量
			MaterialCurrentStockDept stock = buildCurrentStock(RdConstant.D.equals(master.getRdFlag()),unitsCode,master.getStorageCode(),detail);
			MaterialCurrentStockDept mStock = saveCurrentStock(isZeroDeliver,stock);
			detail.setCurrentStockAmount(mStock.getAmount());
		}
		objs[0] = master;
		objs[1] = details;
		return objs;
	}

	@Override
	public void cancelVerify(String fstrAutoId){
		MaterialRdsMasterDept master = materialRdsMasterDeptDAO.findById(fstrAutoId);
		if (master == null) {
			throw new RuntimeException("收发存主记录不存在，不能弃审！");
		}
		if ("0".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库未审核，不能弃审！");
		}
		if ("2".equals(master.getCurrentStatus())) {
			throw new RuntimeException("本次入库已记账，不能弃审！");
		}
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = master.getUnitsCode();
		String appNo = user.getAppCode();
		boolean isZeroDeliver = cdSysParamDAO.findByParaCode(unitsCode, appNo, RdConstant.SYS_PARA_CODE_ZERO_INVENTORY,"0").equals("1");
		List<MaterialRdsDetailDept> details = materialRdsDetailDeptDAO
		.findByMainAutoId(master.getAutoId());
		for (MaterialRdsDetailDept detail : details) {
			MaterialCurrentStockDept stock = buildCurrentStock(RdConstant.R
					.equals(master.getRdFlag()), unitsCode, master
					.getStorageCode(), detail);
			MaterialCurrentStockDept mStock = saveCurrentStock(isZeroDeliver,stock);
			detail.setCurrentStockAmount(mStock.getAmount());
		}
		master.setCurrentStatus("0");
		master.setVerifier(null);
		master.setVerifyDate(null);
	}
	
	@Override
	public Object[] findById(String fstrAutoId) {
		Object[] objs = new Object[2];
		MaterialRdsMasterDept master = materialRdsMasterDeptDAO.findById(fstrAutoId);
		if(master == null){
			return objs;
		}
		List<MaterialRdsDetailDept> details = materialRdsDetailDeptDAO.findByMainAutoId(master.getAutoId());
		objs[0] = master;
		objs[1] = details;
		return objs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReObject findPrice(String fstrClass, String fstrCode,
			String fstrSpec, String fstrUnits) {
		ReObject ro = new ReObject("根据项目类别，编码，规格和单位查询HIS项目价格");
		Double price = hisServiceFacade.getHisBaseDictService().findPrice(fstrClass, fstrCode, fstrSpec, fstrUnits);
		List data = new ArrayList();
		data.add(price);
		ro.setData(data);
		return ro;
	}

	@Override
	public Object[] findByOperationNo(String unitsCode, String storageCode,
			String operationType, String operationNo) {
		Object[] objs = new Object[2];
		MaterialRdsMasterDept master = materialRdsMasterDeptDAO.findByOperationNo(unitsCode, storageCode, operationNo, operationType);
		if(master == null){
			return objs;
		}
		List<MaterialRdsDetailDept> details = materialRdsDetailDeptDAO.findByMainAutoId(master.getAutoId());
		objs[0] = master;
		objs[1] = details;
		return objs;
	}
}
