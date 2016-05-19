package cn.superion.cssd.entity;

/**
 * CssdRetrieveDetail entity. @author MyEclipse Persistence Tools
 */

public class CssdRetrieveDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6616168185361307599L;
	private String autoId;
	private String mainAutoId;
	private Short serialNo;
	private String packageNo;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private Double tradePrice;
	private Double deliverAmount;
	private Double amount;
	private Double washedAmount;
	private String washedAutoId;
	private Double packageAmount;
	public Double getPackageAmount() {
		return packageAmount;
	}

	public void setPackageAmount(Double packageAmount) {
		this.packageAmount = packageAmount;
	}

	public String getWashedAutoId() {
		return washedAutoId;
	}

	public void setWashedAutoId(String washedAutoId) {
		this.washedAutoId = washedAutoId;
	}

	private String detailRemark;
	private String currentStatus;
	private String rejectSign;
	// 扩展
	private String packageId;
	private String packageName;

	// Constructors

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/** default constructor */
	public CssdRetrieveDetail() {
	}

	/** minimal constructor */
	public CssdRetrieveDetail(String autoId, String mainAutoId, Short serialNo,
			String packageNo) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.packageNo = packageNo;
	}

	/** full constructor */
	public CssdRetrieveDetail(String autoId, String mainAutoId, Short serialNo,
			String packageNo, String materialClass, String materialId,
			String materialCode, String materialName, String materialSpec,
			String materialUnits, String factoryCode, Double tradePrice,
			Double deliverAmount, Double amount, Double washedAmount,
			String detailRemark, String currentStatus, String rejectSign,String packageId,String packageName,Double packageAmount) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.packageNo = packageNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.factoryCode = factoryCode;
		this.tradePrice = tradePrice;
		this.deliverAmount = deliverAmount;
		this.amount = amount;
		this.washedAmount = washedAmount;
		this.detailRemark = detailRemark;
		this.currentStatus = currentStatus;
		this.rejectSign = rejectSign;
		this.packageId=packageId;
		this.packageName = packageName;
		this.packageAmount= packageAmount;
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

	public String getPackageNo() {
		return this.packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
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

	public Double getDeliverAmount() {
		return this.deliverAmount;
	}

	public void setDeliverAmount(Double deliverAmount) {
		this.deliverAmount = deliverAmount;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getWashedAmount() {
		return this.washedAmount;
	}

	public void setWashedAmount(Double washedAmount) {
		this.washedAmount = washedAmount;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getRejectSign() {
		return this.rejectSign;
	}

	public void setRejectSign(String rejectSign) {
		this.rejectSign = rejectSign;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

}