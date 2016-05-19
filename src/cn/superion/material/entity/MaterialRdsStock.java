package cn.superion.material.entity;

/**
 * MaterialRdsStock entity. @author MyEclipse Persistence Tools
 */

public class MaterialRdsStock implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5163817422936735057L;
	private String autoId;
	private String unitsCode;
	private String storageCode;
	private String yearMonth;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private String batch;
	private Double tradePrice;
	private Double tradeMoney;
	private Double retailPrice;
	private Double retailMoney;
	private Double initAmount;
	private Double initMoney;
	private Double receiveAmount;
	private Double otherReceiveAmount;
	private Double deliveryAmount;
	private Double deliveryOtherAmount;
	private Double currentStockAmount;
	private Double currentStockMoney;
	private String countClass;

	// Constructors

	/** default constructor */
	public MaterialRdsStock() {
	}

	/** minimal constructor */
	public MaterialRdsStock(String autoId, String unitsCode,
			String storageCode, String yearMonth, String materialClass,
			String materialId, String materialCode, String materialName) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.yearMonth = yearMonth;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public MaterialRdsStock(String autoId, String unitsCode,
			String storageCode, String yearMonth, String materialClass,
			String materialId, String materialCode, String materialName,
			String materialSpec, String materialUnits, String factoryCode,String batch,
			Double tradePrice, Double tradeMoney, Double retailPrice,
			Double retailMoney, Double initAmount, Double receiveAmount,
			Double otherReceiveAmount, Double deliveryAmount,
			Double deliveryOtherAmount, Double currentStockAmount) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.yearMonth = yearMonth;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.factoryCode = factoryCode;
		this.batch = batch;
		this.tradePrice = tradePrice;
		this.tradeMoney = tradeMoney;
		this.retailPrice = retailPrice;
		this.retailMoney = retailMoney;
		this.initAmount = initAmount;
		this.receiveAmount = receiveAmount;
		this.otherReceiveAmount = otherReceiveAmount;
		this.deliveryAmount = deliveryAmount;
		this.deliveryOtherAmount = deliveryOtherAmount;
		this.currentStockAmount = currentStockAmount;
	}

	// Property accessors
	
	public String getAutoId() {
		return this.autoId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
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

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}

	public String getYearMonth() {
		return this.yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
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

	public String getMaterialUnits() {
		return this.materialUnits;
	}

	public void setMaterialUnits(String materialUnits) {
		this.materialUnits = materialUnits;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
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

	public Double getInitAmount() {
		return this.initAmount;
	}

	public void setInitAmount(Double initAmount) {
		this.initAmount = initAmount;
	}

	public Double getReceiveAmount() {
		return this.receiveAmount;
	}

	public void setReceiveAmount(Double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public Double getOtherReceiveAmount() {
		return this.otherReceiveAmount;
	}

	public void setOtherReceiveAmount(Double otherReceiveAmount) {
		this.otherReceiveAmount = otherReceiveAmount;
	}

	public Double getDeliveryAmount() {
		return this.deliveryAmount;
	}

	public void setDeliveryAmount(Double deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public Double getDeliveryOtherAmount() {
		return this.deliveryOtherAmount;
	}

	public void setDeliveryOtherAmount(Double deliveryOtherAmount) {
		this.deliveryOtherAmount = deliveryOtherAmount;
	}

	public Double getCurrentStockAmount() {
		return this.currentStockAmount;
	}

	public void setCurrentStockAmount(Double currentStockAmount) {
		this.currentStockAmount = currentStockAmount;
	}

	public Double getInitMoney() {
		return initMoney;
	}

	public void setInitMoney(Double initMoney) {
		this.initMoney = initMoney;
	}

	public Double getCurrentStockMoney() {
		return currentStockMoney;
	}

	public void setCurrentStockMoney(Double currentStockMoney) {
		this.currentStockMoney = currentStockMoney;
	}

	public String getCountClass() {
		return countClass;
	}

	public void setCountClass(String countClass) {
		this.countClass = countClass;
	}
}