package cn.superion.cssd.entity;

import java.util.Date;

/**
 * VCssdStock entity. @author MyEclipse Persistence Tools
 */

public class VCssdStock implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2485429342798026472L;
	private String detailAutoId;
	private String packageNo;
	private String unitsCode;
	private String packageClass;
	private String packageId;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	private Double tradePrice;
	private Double amount;
	private Double sterilizeFee;
	private Date availDate;
	private String packager;
	private String sterilizeStatus;
	private String detailRemark;
	private String phoInputCode;
	private String fiveInputCode;
	private String usedSign;
	private String currentStatus;
	private String packageAutoId;
	private Short packageSerialNo;
	private String sterilizeAutoId;
	private Short sterilizeSerialNo;
	private String deliverAutoId;
	private Short deliverSerialNo;
	
	private Short serialNo;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private Double detailTradePrice;
	private Double detailAmount;
	private Double tradeMoney;
	private String materialSign;
	private String retrieveSign;
	
	private String sourceAutoId;
	private String sterilizeNo;
	private String sterilizeOrder;
	private String sterilizeType;
	private Date sterilizeDate;

	// Constructors

	/** default constructor */
	public VCssdStock() {
	}

	// Property accessors

	public String getPackageNo() {
		return this.packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getPackageClass() {
		return this.packageClass;
	}

	public void setPackageClass(String packageClass) {
		this.packageClass = packageClass;
	}

	public String getPackageId() {
		return this.packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageMode() {
		return this.packageMode;
	}

	public void setPackageMode(String packageMode) {
		this.packageMode = packageMode;
	}

	public String getPackageUnits() {
		return this.packageUnits;
	}

	public void setPackageUnits(String packageUnits) {
		this.packageUnits = packageUnits;
	}

	public Double getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getSterilizeFee() {
		return this.sterilizeFee;
	}

	public void setSterilizeFee(Double sterilizeFee) {
		this.sterilizeFee = sterilizeFee;
	}

	public Date getAvailDate() {
		return this.availDate;
	}

	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}

	public String getPackager() {
		return this.packager;
	}

	public void setPackager(String packager) {
		this.packager = packager;
	}

	public String getSterilizeStatus() {
		return this.sterilizeStatus;
	}

	public void setSterilizeStatus(String sterilizeStatus) {
		this.sterilizeStatus = sterilizeStatus;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public String getUsedSign() {
		return this.usedSign;
	}

	public void setUsedSign(String usedSign) {
		this.usedSign = usedSign;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getPackageAutoId() {
		return this.packageAutoId;
	}

	public void setPackageAutoId(String packageAutoId) {
		this.packageAutoId = packageAutoId;
	}

	public Short getPackageSerialNo() {
		return this.packageSerialNo;
	}

	public void setPackageSerialNo(Short packageSerialNo) {
		this.packageSerialNo = packageSerialNo;
	}

	public String getSterilizeAutoId() {
		return this.sterilizeAutoId;
	}

	public void setSterilizeAutoId(String sterilizeAutoId) {
		this.sterilizeAutoId = sterilizeAutoId;
	}

	public Short getSterilizeSerialNo() {
		return this.sterilizeSerialNo;
	}

	public void setSterilizeSerialNo(Short sterilizeSerialNo) {
		this.sterilizeSerialNo = sterilizeSerialNo;
	}

	public String getDeliverAutoId() {
		return this.deliverAutoId;
	}

	public void setDeliverAutoId(String deliverAutoId) {
		this.deliverAutoId = deliverAutoId;
	}

	public Short getDeliverSerialNo() {
		return this.deliverSerialNo;
	}

	public void setDeliverSerialNo(Short deliverSerialNo) {
		this.deliverSerialNo = deliverSerialNo;
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

	public String getMaterialUnits() {
		return this.materialUnits;
	}

	public void setMaterialUnits(String materialUnits) {
		this.materialUnits = materialUnits;
	}

	public String getFactoryCode() {
		return this.factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public Double getDetailTradePrice() {
		return this.detailTradePrice;
	}

	public void setDetailTradePrice(Double detailTradePrice) {
		this.detailTradePrice = detailTradePrice;
	}

	public Double getDetailAmount() {
		return this.detailAmount;
	}

	public void setDetailAmount(Double detailAmount) {
		this.detailAmount = detailAmount;
	}

	public Double getTradeMoney() {
		return this.tradeMoney;
	}

	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getMaterialSign() {
		return this.materialSign;
	}

	public void setMaterialSign(String materialSign) {
		this.materialSign = materialSign;
	}
    
	public String getRetrieveSign() {
		return this.retrieveSign;
	}

	public void setRetrieveSign(String retrieveSign) {
		this.retrieveSign = retrieveSign;
	}
	
	public String getSourceAutoId() {
		return this.sourceAutoId;
	}

	public void setSourceAutoId(String sourceAutoId) {
		this.sourceAutoId = sourceAutoId;
	}
	public String getSterilizeNo() {
		return this.sterilizeNo;
	}

	public void setSterilizeNo(String sterilizeNo) {
		this.sterilizeNo = sterilizeNo;
	}

	public String getSterilizeOrder() {
		return this.sterilizeOrder;
	}

	public void setSterilizeOrder(String sterilizeOrder) {
		this.sterilizeOrder = sterilizeOrder;
	}

	public String getSterilizeType() {
		return this.sterilizeType;
	}

	public void setSterilizeType(String sterilizeType) {
		this.sterilizeType = sterilizeType;
	}

	public Date getSterilizeDate() {
		return this.sterilizeDate;
	}

	public void setSterilizeDate(Date sterilizeDate) {
		this.sterilizeDate = sterilizeDate;
	}

	public String getDetailAutoId() {
		return detailAutoId;
	}

	public void setDetailAutoId(String detailAutoId) {
		this.detailAutoId = detailAutoId;
	}

	public String getPhoInputCode() {
		return phoInputCode;
	}

	public void setPhoInputCode(String phoInputCode) {
		this.phoInputCode = phoInputCode;
	}

	public String getFiveInputCode() {
		return fiveInputCode;
	}

	public void setFiveInputCode(String fiveInputCode) {
		this.fiveInputCode = fiveInputCode;
	}

}