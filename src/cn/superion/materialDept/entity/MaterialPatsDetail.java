package cn.superion.materialDept.entity;

import java.util.Date;

/**
 * MaterialPatsDetail entity. @author MyEclipse Persistence Tools
 */

public class MaterialPatsDetail implements java.io.Serializable,Cloneable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3679651063157202475L;
	private String autoId;
	private String mainAutoId;
	private Short serialNo;
	private String materialClass;
	private String barCode;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private Double applyAmount;
	private Double amount;
	private Double retailPrice;
	private Double retailMoney;
	private String factoryCode;
	private Date madeDate;
	private String batch;
	private Date availDate;
	private String hisBillNo;
	private String hisClass;
	private String hisCode;
	private String hisSpec;
	private String hisUnits;
	private String accounter;
	private Date accountDate;
	private String refundSign;
	private String refundOperator;
	private Date refundDate;
	private String factInSign;
	

	private Double tradePrice;
	private Double tradeMoney;
	private Double wholeSalePrice;
	private Double wholeSaleMoney;
	private String chargeSign;
	private String classOnAccount;
	private String materialBarCode;
	private String isGive;
	private String supplyDeptCode;
	private String currentReceiveSign;//来自本院的入库物资

	//扩展字段
	private String invoiceNo;
	private Date invoiceDate;

	// Constructors

	/** default constructor */
	public MaterialPatsDetail() {
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getMainAutoId() {
		return this.mainAutoId;
	}

	public void setMainAutoId(String mainAutoId) {
		this.mainAutoId = mainAutoId;
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

	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	public String getMaterialUnits() {
		return this.materialUnits;
	}

	public void setMaterialUnits(String materialUnits) {
		this.materialUnits = materialUnits;
	}

	public Double getApplyAmount() {
		return this.applyAmount;
	}

	public void setApplyAmount(Double applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public String getFactoryCode() {
		return this.factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public Date getMadeDate() {
		return this.madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Date getAvailDate() {
		return this.availDate;
	}

	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}

	public String getHisBillNo() {
		return this.hisBillNo;
	}

	public void setHisBillNo(String hisBillNo) {
		this.hisBillNo = hisBillNo;
	}

	public String getHisClass() {
		return this.hisClass;
	}

	public void setHisClass(String hisClass) {
		this.hisClass = hisClass;
	}

	public String getHisCode() {
		return this.hisCode;
	}

	public void setHisCode(String hisCode) {
		this.hisCode = hisCode;
	}

	public String getHisSpec() {
		return this.hisSpec;
	}

	public void setHisSpec(String hisSpec) {
		this.hisSpec = hisSpec;
	}

	public String getHisUnits() {
		return this.hisUnits;
	}

	public void setHisUnits(String hisUnits) {
		this.hisUnits = hisUnits;
	}

	public String getAccounter() {
		return this.accounter;
	}

	public void setAccounter(String accounter) {
		this.accounter = accounter;
	}

	public Date getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}
	
	public String getRefundSign() {
		return refundSign;
	}

	public void setRefundSign(String refundSign) {
		this.refundSign = refundSign;
	}

	public String getRefundOperator() {
		return refundOperator;
	}

	public void setRefundOperator(String refundOperator) {
		this.refundOperator = refundOperator;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public MaterialPatsDetail clone(){
		MaterialPatsDetail mpd = null;
		try {
			mpd = (MaterialPatsDetail) super.clone();
			mpd.setAutoId(null);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return mpd;
	}
	
	/**
	 * 写红字
	 * @return
	 */
	public MaterialPatsDetail writeRedPatsDetail(){
		MaterialPatsDetail mpd = this.clone();
		mpd.setAmount(-mpd.getAmount());
		if(mpd.getRetailMoney() != null)
			mpd.setRetailMoney(-mpd.getRetailMoney());
		return mpd;
	}
	
	/**
	 * 更新售价，并计算售价金额
	 * @param retailPrice
	 */
	public void updateRetailPrice(Double retailPrice){
		this.retailPrice = retailPrice == null ? 0d : retailPrice;
		this.retailMoney = this.retailPrice * this.amount;
	}

	public String getFactInSign() {
		return factInSign;
	}

	public void setFactInSign(String factInSign) {
		this.factInSign = factInSign;
	}

	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public Double getWholeSalePrice() {
		return wholeSalePrice;
	}

	public void setWholeSalePrice(Double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}

	public Double getWholeSaleMoney() {
		return wholeSaleMoney;
	}

	public void setWholeSaleMoney(Double wholeSaleMoney) {
		this.wholeSaleMoney = wholeSaleMoney;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getChargeSign() {
		return chargeSign;
	}

	public void setChargeSign(String chargeSign) {
		this.chargeSign = chargeSign;
	}

	public String getClassOnAccount() {
		return classOnAccount;
	}

	public void setClassOnAccount(String classOnAccount) {
		this.classOnAccount = classOnAccount;
	}

	public String getMaterialBarCode() {
		return materialBarCode;
	}

	public void setMaterialBarCode(String materialBarCode) {
		this.materialBarCode = materialBarCode;
	}

	public String getIsGive() {
		return isGive;
	}

	public void setIsGive(String isGive) {
		this.isGive = isGive;
	}

	public String getSupplyDeptCode() {
		return supplyDeptCode;
	}

	public void setSupplyDeptCode(String supplyDeptCode) {
		this.supplyDeptCode = supplyDeptCode;
	}

	public String getCurrentReceiveSign() {
		return currentReceiveSign;
	}

	public void setCurrentReceiveSign(String currentReceiveSign) {
		this.currentReceiveSign = currentReceiveSign;
	}

}