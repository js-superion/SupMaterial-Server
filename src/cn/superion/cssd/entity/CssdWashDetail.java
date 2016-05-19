package cn.superion.cssd.entity;

import java.util.Date;

/**
 * CssdWashDetail entity. @author MyEclipse Persistence Tools
 */

public class CssdWashDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8892814885735133356L;
	private String autoId;
	private String mainAutoId;
	private Short serialNo;
	private String packageClass;
	private String packageId;
	private String packageName;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private Double tradePrice;
	private Double amount;
	private Double washPackageAmount;
	private String detailRemark;
	private String personId;
	private Date washDate;
	private Double packagedAmount;
	private String currentStatus;

	// Constructors

	/** default constructor */
	public CssdWashDetail() {
	}

	/** minimal constructor */
	public CssdWashDetail(String autoId, String mainAutoId, Short serialNo,
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

	/** full constructor */
	public CssdWashDetail(String autoId, String mainAutoId, Short serialNo,
			String packageClass, String packageId, String packageName,
			String personId, Date washDate, String materialClass,
			String materialId, String materialCode, String materialName,
			String materialSpec, String materialUnits, String factoryCode,
			Double tradePrice, Double amount, Double washPackageAmount,
			String detailRemark, Double packagedAmount, String currentStatus) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.personId = personId;
		this.washDate = washDate;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.factoryCode = factoryCode;
		this.tradePrice = tradePrice;
		this.amount = amount;
		this.washPackageAmount = washPackageAmount;
		this.detailRemark = detailRemark;
		this.packagedAmount = packagedAmount;
		this.currentStatus = currentStatus;
		this.packageClass = packageClass;
		this.packageId = packageId;
		this.packageName = packageName;
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

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Date getWashDate() {
		return this.washDate;
	}

	public void setWashDate(Date washDate) {
		this.washDate = washDate;
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

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public Double getPackagedAmount() {
		return this.packagedAmount;
	}

	public void setPackagedAmount(Double packagedAmount) {
		this.packagedAmount = packagedAmount;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Double getWashPackageAmount() {
		return washPackageAmount;
	}

	public void setWashPackageAmount(Double washPackageAmount) {
		this.washPackageAmount = washPackageAmount;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getPackageClass() {
		return packageClass;
	}

	public void setPackageClass(String packageClass) {
		this.packageClass = packageClass;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}