package cn.superion.material.stat.entity;
/**
 * 收发存汇总结果
 * @author 曹国魁
 *
 */
public class RdsStatistic {
	private String storageCode;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String batch;
	private Double initAmount;
	private Double initMoney;
	private Double receiveAmount;
	private Double otherReceiveAmount;
	private Double deliveryAmount;
	private Double deliveryOtherAmount;
	private Double currentStockAmount;
	private Double currentStockMoney;
	private Double tradeMoney;
	private Double retailMoney;
	private String countClass;

	public String getStorageCode() {
		return storageCode;
	}
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialSpec() {
		return materialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}
	public String getMaterialUnits() {
		return materialUnits;
	}
	public void setMaterialUnits(String materialUnits) {
		this.materialUnits = materialUnits;
	}
	public Double getInitAmount() {
		return initAmount;
	}
	public void setInitAmount(Double initAmount) {
		this.initAmount = initAmount;
	}
	public Double getReceiveAmount() {
		return receiveAmount;
	}
	public void setReceiveAmount(Double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	public Double getOtherReceiveAmount() {
		return otherReceiveAmount;
	}
	public void setOtherReceiveAmount(Double otherReceiveAmount) {
		this.otherReceiveAmount = otherReceiveAmount;
	}
	public Double getDeliveryAmount() {
		return deliveryAmount;
	}
	public void setDeliveryAmount(Double deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}
	public Double getDeliveryOtherAmount() {
		return deliveryOtherAmount;
	}
	public void setDeliveryOtherAmount(Double deliveryOtherAmount) {
		this.deliveryOtherAmount = deliveryOtherAmount;
	}
	public Double getCurrentStockAmount() {
		return currentStockAmount;
	}
	public void setCurrentStockAmount(Double currentStockAmount) {
		this.currentStockAmount = currentStockAmount;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
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
	
	public Double getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public Double getRetailMoney() {
		return retailMoney;
	}
	public void setRetailMoney(Double retailMoney) {
		this.retailMoney = retailMoney;
	}
	
	public String getCountClass() {
		return countClass;
	}
	public void setCountClass(String countClass) {
		this.countClass = countClass;
	}
	/** full constructor */
	public RdsStatistic(String storageCode, String materialCode,
			String materialName, 
			Double initAmount, Double receiveAmount,
			Double deliveryAmount,
			Double currentStockAmount,String batch,Double initMoney,Double currentStockMoney,Double tradeMoney,Double retailMoney,String countClass) {
		super();
		this.storageCode = storageCode;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.initAmount = initAmount;
		this.receiveAmount = receiveAmount;
		this.deliveryAmount = deliveryAmount;
		this.currentStockAmount = currentStockAmount;
		this.batch = batch;
		this.initMoney = initMoney;
		this.currentStockMoney = currentStockMoney;
		this.tradeMoney = tradeMoney;
		this.retailMoney = retailMoney;
		this.countClass = countClass;
	}
	
	public RdsStatistic(String storageCode, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			Double initAmount, Double receiveAmount, Double otherReceiveAmount,
			Double deliveryAmount, Double deliveryOtherAmount,
			Double currentStockAmount) {
		super();
		this.storageCode = storageCode;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.initAmount = initAmount;
		this.receiveAmount = receiveAmount;
		this.otherReceiveAmount = otherReceiveAmount;
		this.deliveryAmount = deliveryAmount;
		this.deliveryOtherAmount = deliveryOtherAmount;
		this.currentStockAmount = currentStockAmount;

	}
}
