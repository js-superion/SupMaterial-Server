package cn.superion.cssd.entity;

/**
 * CssdBiologyDetial entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CssdBiologyDetial implements java.io.Serializable {

	// Fields

	private String autoId;
	private String mainAutoId;
	private Short serialNo;
	private String itemCode;
	private String itemName;
	private String units;
	private Double amount;
	private Double unitPrice;
	private Double charges;
	private Long resultValue;
	private String referenceValue;
	private String normalSign;
	private String column13;

	// Constructors

	/** default constructor */
	public CssdBiologyDetial() {
	}

	/** minimal constructor */
	public CssdBiologyDetial(String autoId, String mainAutoId, Short serialNo) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
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

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCharges() {
		return this.charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public Long getResultValue() {
		return this.resultValue;
	}

	public void setResultValue(Long resultValue) {
		this.resultValue = resultValue;
	}

	public String getReferenceValue() {
		return this.referenceValue;
	}

	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

	public String getNormalSign() {
		return this.normalSign;
	}

	public void setNormalSign(String normalSign) {
		this.normalSign = normalSign;
	}

	public String getColumn13() {
		return this.column13;
	}

	public void setColumn13(String column13) {
		this.column13 = column13;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

}