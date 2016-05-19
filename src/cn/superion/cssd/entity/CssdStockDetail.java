package cn.superion.cssd.entity;

/**
 * CssdStockDetail entity. @author MyEclipse Persistence Tools
 */

public class CssdStockDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3706369928694190568L;
	private String autoId;
	private String packageNo;
	private Short serialNo;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private Double tradePrice;
	private Double amount;
	private Double tradeMoney;
	private String materialSign;
	private String retrieveSign;
	private String sourceAutoId;

	// Constructors

	/** default constructor */
	public CssdStockDetail() {
	}

	/** minimal constructor */
	public CssdStockDetail(String autoId, String packageNo, Short serialNo,
			String materialClass, String materialId, String materialCode,
			String materialName) {
		this.autoId = autoId;
		this.packageNo = packageNo;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public CssdStockDetail(String autoId, String packageNo, Short serialNo,
			String materialClass, String materialId, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			String factoryCode, Double tradePrice, Double amount,
			Double tradeMoney, String materialSign, String retrieveSign,String sourceAutoId) {
		this.autoId = autoId;
		this.packageNo = packageNo;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.factoryCode = factoryCode;
		this.tradePrice = tradePrice;
		this.amount = amount;
		this.tradeMoney = tradeMoney;
		this.materialSign = materialSign;
		this.retrieveSign = retrieveSign;
		this.sourceAutoId = sourceAutoId;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getPackageNo() {
		return this.packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
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

}