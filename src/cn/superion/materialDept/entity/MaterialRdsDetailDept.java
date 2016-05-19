package cn.superion.materialDept.entity;

import java.util.Date;

/**
 * MaterialRdsDetail entity. @author MyEclipse Persistence Tools
 */

public class MaterialRdsDetailDept implements java.io.Serializable,Cloneable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1333787842695079293L;
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
	private Double amount;
	private Double tradePrice;
	private Double tradeMoney;
	private Double retailPrice;
	private Double retailMoney;
	private String factoryCode;
	private Date madeDate;
	private String batch;
	private Date availDate;
	private String position;
	private Double outAmount;
	private String outSign;
	private Double currentStockAmount;
	private String highValueSign;
	private String agentSign;
	private String sourceInputAutoId;
	private String sourceAutoId;
	private String detailRemark;

	private Double wholeSalePrice;
	private Double wholeSaleMoney;

	private String chargeSign;
	private String classOnAccount;
	private String materialBarCode;
	private String isGive;
	
	// Constructors

	/** default constructor */
	public MaterialRdsDetailDept() {
	}

	/** minimal constructor */
	public MaterialRdsDetailDept(String autoId, String mainAutoId, Short serialNo,
			String materialClass, String materialId, String materialCode,
			String materialName) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
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

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getOutAmount() {
		return this.outAmount;
	}

	public void setOutAmount(Double outAmount) {
		this.outAmount = outAmount;
	}

	public String getOutSign() {
		return this.outSign;
	}

	public void setOutSign(String outSign) {
		this.outSign = outSign;
	}

	public Double getCurrentStockAmount() {
		return this.currentStockAmount;
	}

	public void setCurrentStockAmount(Double currentStockAmount) {
		this.currentStockAmount = currentStockAmount;
	}

	public String getHighValueSign() {
		return this.highValueSign;
	}

	public void setHighValueSign(String highValueSign) {
		this.highValueSign = highValueSign;
	}

	public String getAgentSign() {
		return this.agentSign;
	}

	public void setAgentSign(String agentSign) {
		this.agentSign = agentSign;
	}

	public String getSourceInputAutoId() {
		return this.sourceInputAutoId;
	}

	public void setSourceInputAutoId(String sourceInputAutoId) {
		this.sourceInputAutoId = sourceInputAutoId;
	}

	public String getSourceAutoId() {
		return this.sourceAutoId;
	}

	public void setSourceAutoId(String sourceAutoId) {
		this.sourceAutoId = sourceAutoId;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}
	
	public MaterialRdsDetailDept clone(){
		MaterialRdsDetailDept copy = null;
		try {
			copy = (MaterialRdsDetailDept) super.clone();
			copy.setAutoId(null);
			copy.setMainAutoId(null);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
	
	public MaterialRdsDetailDept writeRed(){
		MaterialRdsDetailDept red = clone();
		red.setAmount(-amount);
		if(tradeMoney != null)
			red.setTradeMoney(-tradeMoney);
		if(retailMoney != null)
			red.setRetailMoney(-retailMoney);
		return red;
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

}