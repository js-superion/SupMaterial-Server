package cn.superion.material.purchase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.material.common.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.material.dao.MaterialInvoiceDetailDAO;
import cn.superion.material.dao.MaterialInvoiceMasterDAO;
import cn.superion.material.dao.MaterialRdsDetailDAO;
import cn.superion.material.dao.VMaterialRdsDAO;
import cn.superion.material.entity.MaterialInvoiceDetail;
import cn.superion.material.entity.MaterialInvoiceMaster;
import cn.superion.material.entity.MaterialRdsDetail;
import cn.superion.material.entity.VMaterialRds;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 发票服务实现
 * @author 曹国魁
 *
 */
public class InvoiceImpl implements IInvoice {
	private static final Log log = LogFactory.getLog(InvoiceImpl.class);
	private MaterialInvoiceMasterDAO materialInvoiceMasterDAO;
	private MaterialInvoiceDetailDAO materialInvoiceDetailDAO;
	private MaterialRdsDetailDAO materialRdsDetailDAO;
	private VMaterialRdsDAO vMaterialRdsDAO; 
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService commMaterialServiceImpl;
	
	public MaterialRdsDetailDAO getMaterialRdsDetailDAO() {
		return materialRdsDetailDAO;
	}

	public void setMaterialRdsDetailDAO(MaterialRdsDetailDAO materialRdsDetailDAO) {
		this.materialRdsDetailDAO = materialRdsDetailDAO;
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

	public VMaterialRdsDAO getvMaterialRdsDAO() {
		return vMaterialRdsDAO;
	}

	public void setvMaterialRdsDAO(VMaterialRdsDAO vMaterialRdsDAO) {
		this.vMaterialRdsDAO = vMaterialRdsDAO;
	}

	public MaterialInvoiceMasterDAO getMaterialInvoiceMasterDAO() {
		return materialInvoiceMasterDAO;
	}

	public void setMaterialInvoiceMasterDAO(
			MaterialInvoiceMasterDAO materialInvoiceMasterDAO) {
		this.materialInvoiceMasterDAO = materialInvoiceMasterDAO;
	}

	public MaterialInvoiceDetailDAO getMaterialInvoiceDetailDAO() {
		return materialInvoiceDetailDAO;
	}

	public void setMaterialInvoiceDetailDAO(
			MaterialInvoiceDetailDAO materialInvoiceDetailDAO) {
		this.materialInvoiceDetailDAO = materialInvoiceDetailDAO;
	}

	@Override
	public ReObject delMaterialInvoice(String fstrAutoId) {
		ReObject ro = new ReObject("删除未审核的发票");
		MaterialInvoiceMaster master = materialInvoiceMasterDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("发票不存在！");
		if(!"0".equals(master.getCurrentStatus())){
			throw new RuntimeException("发票已审核或执行，不能删除！");
		}
		List<MaterialInvoiceDetail> originalDetails = materialInvoiceDetailDAO.findByMainAutoId(fstrAutoId);
		for(MaterialInvoiceDetail originalDetail : originalDetails){
			updateInvoiceAmount(false,true,originalDetail);
		}
		materialInvoiceDetailDAO.delByMainAutoId(fstrAutoId);
		materialInvoiceMasterDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findMaterialInvoiceByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询采购发票信息");
		List<Object> data = materialInvoiceMasterDAO.findAutoIdsByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findMaterialInvoiceDetail(String fstrAutoId) {
		ReObject ro = new ReObject("查询采购发票明细列表");
		MaterialInvoiceMaster master = materialInvoiceMasterDAO.findById(fstrAutoId);
		List<MaterialInvoiceDetail> details = materialInvoiceDetailDAO.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRdsInvoice(ParameterObject fparameter) {
		ReObject ro = new ReObject("查询未开票的入库明细");
		List<VMaterialRds> data = vMaterialRdsDAO.findUninvoicedByCondition(SessionUtil.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveMaterialInvoice(MaterialInvoiceMaster fmaster,
			List<MaterialInvoiceDetail> fdetails) {
		ReObject ro = new ReObject("保存发票信息");
		if(fmaster == null)
			throw new RuntimeException("发票主记录不能为空！");
		if(fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("发票明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		String appCode = user.getAppCode();
		Date curDate = new Date();
		String storageCode = fmaster.getStorageCode();
		if(storageCode == null || "".equals(storageCode)){
			storageCode = "0";
			fmaster.setStorageCode(storageCode);
		}
		String autoId = fmaster.getAutoId(); 
		boolean isVerified = false;
		//累计金额
		Double totalCosts = 0d;
		for(MaterialInvoiceDetail detail : fdetails){
			totalCosts += detail.getTradeMoney();
		}
		fmaster.setTotalCosts(totalCosts);
		if(autoId == null || "".equals(autoId)){
			//新增
			String billNo = fmaster.getBillNo();
			if(billNo == null || "".equals(billNo)){
				fmaster.setBillNo(commMaterialServiceImpl.getNextBillNo(RdConstant.OTHERS,storageCode));
			}else{
				//新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if(!materialInvoiceMasterDAO.checkBillNoUnique(unitsCode,storageCode,billNo)){
					throw new RuntimeException("手工输入的单据编号["+billNo+"]在单位["+unitsCode+"],仓库["+storageCode+"]下有重复");
				}
			}
			if(fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			if(fmaster.getPersonId() == null || "".equals(fmaster.getPersonId()))
				fmaster.setPersonId(personId);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			isVerified = "1".equals(loadCheckSysParam(unitsCode,appCode));
			if(isVerified){
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(personId);
			}
			fmaster.setCurrentStatus(isVerified?"1":"0");
			materialInvoiceMasterDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		}else{
			//修改
			MaterialInvoiceMaster original = materialInvoiceMasterDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的发票主记录！");
			}
			if(!"0".equals(original.getCurrentStatus())){
				throw new RuntimeException("发票已审核或执行，不能修改！");
			}
			isVerified = "1".equals(loadCheckSysParam(unitsCode,appCode));
			if(isVerified){
				fmaster.setVerifyDate(curDate);
				fmaster.setVerifier(personId);
			}
			fmaster.setCurrentStatus(isVerified?"1":"0");
			//先还原采购入库单的已开票数
			List<MaterialInvoiceDetail> originalDetails = materialInvoiceDetailDAO.findByMainAutoId(autoId);
			for(MaterialInvoiceDetail originalDetail : originalDetails){
				updateInvoiceAmount(false,false,originalDetail);
			}
			materialInvoiceMasterDAO.merge(fmaster);
			materialInvoiceDetailDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for(MaterialInvoiceDetail detail : fdetails){
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			materialInvoiceDetailDAO.save(detail);
			updateInvoiceAmount(true,true,detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}
	
	/**
	 * 加载系统参数
	 * @param unitsCode
	 * @param appCode
	 * @return
	 */
	private String loadCheckSysParam(String unitsCode,String appCode){
		return cdSysParamDAO.findByParaCode(unitsCode, appCode, RdConstant.SYS_PARA_CODE_PURCHASE_INVOICE_CHK,"0");
	}
	
	/**
	 * 更新入库单的开票数和开票标志
	 * @param isPlus 是更新还是还原，保存时是更新；删除时是还原
	 * @param isUpdateCurrentStatus 是否更新开票标识
	 * @param detail 发票明细
	 */
	private void updateInvoiceAmount(boolean isPlus,boolean isUpdateCurrentStatus,MaterialInvoiceDetail detail){
		String rdsMasterAutoId = detail.getSourceBillNo();
		Short rdsDetailSerialNo = detail.getSourceSerialNo();
		if(rdsMasterAutoId != null && !"".equals(rdsMasterAutoId) && rdsDetailSerialNo != null && rdsDetailSerialNo > 0){
			MaterialRdsDetail rdsDetail = materialRdsDetailDAO.findByMainAutoIdAndSerialNo(rdsMasterAutoId,rdsDetailSerialNo);
			if(rdsDetail == null){
				log.warn("不存在入库单主记录ID["+rdsMasterAutoId+"]和入库单明细记录serialNo["+rdsDetailSerialNo+"]的入库单明细记录，无法更新已开票数！");
			}else{
				Double invoiceAmount = rdsDetail.getInvoiceAmount();
				if(invoiceAmount == null)
					invoiceAmount = 0d;
				if(isPlus)
					invoiceAmount += detail.getAmount();
				else
					invoiceAmount -= detail.getAmount();
				rdsDetail.setInvoiceAmount(invoiceAmount);
				if(isUpdateCurrentStatus){
					if(isPlus){
						if(invoiceAmount.equals(rdsDetail.getAmount())){
							//当数量=已开票数时,已开票标志为1
							rdsDetail.setInvoiceSign("1");
						}else{
							rdsDetail.setInvoiceSign("0");
						}
					}else{
						rdsDetail.setInvoiceSign("0");
					}
				}
			}
		}
	}

	@Override
	public ReObject verifyMaterialInvoice(String fstrAutoId) {
		ReObject ro = new ReObject("审核发票");
		MaterialInvoiceMaster original = materialInvoiceMasterDAO.findById(fstrAutoId);
		if(original == null){
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的发票主记录！");
		}
		if(!"0".equals(original.getCurrentStatus())){
			throw new RuntimeException("发票已审核或执行，不能审核！");
		}
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		original.setCurrentStatus("1");
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}

}
