package cn.superion.cssd.entity;

/**
 * AbstractCssdProvideDetail entity provides the base persistence definition of
 * the CssdProvideDetail entity. @author MyEclipse Persistence Tools
 */

public  class CssdProvideDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String autoId;
	private String mainAutoId;
	private Short serialNo;
	private String packageClass;
	private String packageId;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	private Double tradePrice;
	private Double amount;
	private Double checkAmount;
	private String detailRemark;

	// Constructors

	/** default constructor */
	public CssdProvideDetail() {
	}

	/** minimal constructor */
	public CssdProvideDetail(String autoId) {
		this.autoId = autoId;
	}

	/** full constructor */
	public CssdProvideDetail(String autoId, String mainAutoId,
			Short serialNo, String packageClass, String packageId,
			String packageName, String packageMode, String packageUnits,
			Double tradePrice, Double amount, Double checkAmount,
			String detailRemark) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.packageClass = packageClass;
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageMode = packageMode;
		this.packageUnits = packageUnits;
		this.tradePrice = tradePrice;
		this.amount = amount;
		this.checkAmount = checkAmount;
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

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
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

	public Double getCheckAmount() {
		return this.checkAmount;
	}

	public void setCheckAmount(Double checkAmount) {
		this.checkAmount = checkAmount;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

}