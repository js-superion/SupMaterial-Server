package cn.superion.material.entity;

import java.util.Date;

/**
 * MaterialChangePriceDetail entity. @author MyEclipse Persistence Tools
 */

public class MaterialChangePriceDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String autoId;
	private String mainAutoId;
	private String storageCode;
	private Short serialNo;
	private String materialClass;
	private String barCode;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private Double amount;
	private Double oldTradePrice;
	private Double newTradePrice;
	private Double oldWholeSalePrice;
	private Double newWholeSalePrice;
	private Double oldInvitePrice;
	private Double newInvitePrice;
	private Double oldRetailPrice;
	private Double newRetailPrice;
	private String factoryCode;
	private Date availDate;
	private Date startDate;
	private String detailRemark;

	// Constructors

	/** default constructor */
	public MaterialChangePriceDetail() {
	}

	/** minimal constructor */
	public MaterialChangePriceDetail(String autoId, String mainAutoId,
			String storageCode, Short serialNo, String materialClass,
			String materialId, String materialCode, String materialName) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.storageCode = storageCode;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public MaterialChangePriceDetail(String autoId, String mainAutoId,
			String storageCode, Short serialNo, String materialClass,
			String barCode, String materialId, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			Double amount, Double oldTradePrice, Double newTradePrice,
			Double oldWholeSalePrice, Double newWholeSalePrice,
			Double oldInvitePrice, Double newInvitePrice,
			Double oldRetailPrice, Double newRetailPrice, String factoryCode,
			Date availDate, Date startDate, String detailRemark) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.storageCode = storageCode;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.barCode = barCode;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.amount = amount;
		this.oldTradePrice = oldTradePrice;
		this.newTradePrice = newTradePrice;
		this.oldWholeSalePrice = oldWholeSalePrice;
		this.newWholeSalePrice = newWholeSalePrice;
		this.oldInvitePrice = oldInvitePrice;
		this.newInvitePrice = newInvitePrice;
		this.oldRetailPrice = oldRetailPrice;
		this.newRetailPrice = newRetailPrice;
		this.factoryCode = factoryCode;
		this.availDate = availDate;
		this.startDate = startDate;
		this.detailRemark = detailRemark;
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

	public String getStorageCode() {
		return this.storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
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

	public Double getOldTradePrice() {
		return this.oldTradePrice;
	}

	public void setOldTradePrice(Double oldTradePrice) {
		this.oldTradePrice = oldTradePrice;
	}

	public Double getNewTradePrice() {
		return this.newTradePrice;
	}

	public void setNewTradePrice(Double newTradePrice) {
		this.newTradePrice = newTradePrice;
	}

	public Double getOldWholeSalePrice() {
		return this.oldWholeSalePrice;
	}

	public void setOldWholeSalePrice(Double oldWholeSalePrice) {
		this.oldWholeSalePrice = oldWholeSalePrice;
	}

	public Double getNewWholeSalePrice() {
		return this.newWholeSalePrice;
	}

	public void setNewWholeSalePrice(Double newWholeSalePrice) {
		this.newWholeSalePrice = newWholeSalePrice;
	}

	public Double getOldInvitePrice() {
		return this.oldInvitePrice;
	}

	public void setOldInvitePrice(Double oldInvitePrice) {
		this.oldInvitePrice = oldInvitePrice;
	}

	public Double getNewInvitePrice() {
		return this.newInvitePrice;
	}

	public void setNewInvitePrice(Double newInvitePrice) {
		this.newInvitePrice = newInvitePrice;
	}

	public Double getOldRetailPrice() {
		return this.oldRetailPrice;
	}

	public void setOldRetailPrice(Double oldRetailPrice) {
		this.oldRetailPrice = oldRetailPrice;
	}

	public Double getNewRetailPrice() {
		return this.newRetailPrice;
	}

	public void setNewRetailPrice(Double newRetailPrice) {
		this.newRetailPrice = newRetailPrice;
	}

	public String getFactoryCode() {
		return this.factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public Date getAvailDate() {
		return this.availDate;
	}

	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

}