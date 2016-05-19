package cn.superion.material.entity;

import java.util.Date;

/**
 * VMaterialProvideId entity. @author MyEclipse Persistence Tools
 */

public class VMaterialProvide implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7058040969140254952L;
	private String autoId;
	private String unitsCode;
	private String storageCode;
	private String billNo;
	private Date billDate;
	private String invoiceType;
    private String supplyDeptCode;
    private String cardCode;
    private Double accountRemain;
	private String deptCode;
	private String personId;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private String detailAutoId;
	private Short serialNo;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String factoryCode;
	private String materialUnits;
	private Double amount;
	private Double checkAmount;
	private Double planAmount;
	
	private Double tradePrice;
	private Double tradeMoney;
	private Double retailPrice;
	private Double retailMoney;
	private String detailRemark;
	//科室退货入库，即写红单时的属性
    private String barCode;
    private Date madeDate;
    private String batch;
    private Date availDate;
	// Constructors

	/** default constructor */
	public VMaterialProvide() {
	}

	/** minimal constructor */
	public VMaterialProvide(String autoId, String unitsCode,
			String storageCode, String billNo, String detailAutoId,
			Short serialNo, String materialClass, String materialId,
			String materialCode, String materialName) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.billNo = billNo;
		this.detailAutoId = detailAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public VMaterialProvide(String autoId, String unitsCode,
			String storageCode, String billNo, Date billDate, String deptCode,
			String personId, String remark, String maker, Date makeDate,
			String verifier, Date verifyDate, String currentStatus,
			String detailAutoId, Short serialNo, String materialClass,Double planAmount,
			String materialId, String materialCode, String materialName,
			String materialSpec, String factoryCode, String materialUnits,
			Double amount, Double checkAmount, Double tradePrice,
			Double tradeMoney, Double retailPrice, Double retailMoney,
			String detailRemark) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.deptCode = deptCode;
		this.personId = personId;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
		this.detailAutoId = detailAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.factoryCode = factoryCode;
		this.materialUnits = materialUnits;
		this.amount = amount;
		this.checkAmount = checkAmount;
		this.planAmount = planAmount;
		this.tradePrice = tradePrice;
		this.tradeMoney = tradeMoney;
		this.retailPrice = retailPrice;
		this.retailMoney = retailMoney;
		this.detailRemark = detailRemark;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getStorageCode() {
		return this.storageCode;
	}

	public Double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Date getMakeDate() {
		return this.makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getVerifier() {
		return this.verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public Date getVerifyDate() {
		return this.verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getDetailAutoId() {
		return this.detailAutoId;
	}

	public void setDetailAutoId(String detailAutoId) {
		this.detailAutoId = detailAutoId;
	}

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public String getMaterialClass() {
		return this.materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}

	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialCode() {
		return this.materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialSpec() {
		return this.materialSpec;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	public String getFactoryCode() {
		return this.factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getMaterialUnits() {
		return this.materialUnits;
	}

	public void setMaterialUnits(String materialUnits) {
		this.materialUnits = materialUnits;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getCheckAmount() {
		return this.checkAmount;
	}

	public void setCheckAmount(Double checkAmount) {
		this.checkAmount = checkAmount;
	}

	public Double getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getTradeMoney() {
		return this.tradeMoney;
	}

	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public Double getRetailPrice() {
		return this.retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getRetailMoney() {
		return this.retailMoney;
	}

	public void setRetailMoney(Double retailMoney) {
		this.retailMoney = retailMoney;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getSupplyDeptCode() {
		return supplyDeptCode;
	}

	public void setSupplyDeptCode(String supplyDeptCode) {
		this.supplyDeptCode = supplyDeptCode;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public Double getAccountRemain() {
		return accountRemain;
	}

	public void setAccountRemain(Double accountRemain) {
		this.accountRemain = accountRemain;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Date getAvailDate() {
		return availDate;
	}

	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}

}