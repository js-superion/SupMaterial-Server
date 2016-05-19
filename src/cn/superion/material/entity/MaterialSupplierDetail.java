package cn.superion.material.entity;

/**
 * MaterialSupplierDetail entity. @author MyEclipse Persistence Tools
 */

public class MaterialSupplierDetail implements java.io.Serializable {

	// Fields

	private String autoId;
	private String unitsCode;
	private String billNo;
	private String serialNo;
	private String materialClass;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private Double sendAmount;
	private Double currentStockAmount;
	private Double tradePrice;
	private String materialId;
	private Double tradeMoney;

	// Constructors

	/** default constructor */
	public MaterialSupplierDetail() {
	}

	/** minimal constructor */
	public MaterialSupplierDetail(String autoId) {
		this.autoId = autoId;
	}

	/** full constructor */
	public MaterialSupplierDetail(String autoId, String unitsCode,
			String billNo, String serialNo, String materialClass,String materialId,
			String materialCode, String materialName, String materialSpec,
			String materialUnits, Double sendAmount, Double currentStockAmount,
			Double tradePrice, Double tradeMoney) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.materialId = materialId;
		this.billNo = billNo;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.sendAmount = sendAmount;
		this.currentStockAmount = currentStockAmount;
		this.tradePrice = tradePrice;
		this.tradeMoney = tradeMoney;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMaterialClass() {
		return this.materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
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

	public Double getSendAmount() {
		return this.sendAmount;
	}

	public void setSendAmount(Double sendAmount) {
		this.sendAmount = sendAmount;
	}

	public Double getCurrentStockAmount() {
		return this.currentStockAmount;
	}

	public void setCurrentStockAmount(Double currentStockAmount) {
		this.currentStockAmount = currentStockAmount;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaterialSupplierDetail))
			return false;
		MaterialSupplierDetail castOther = (MaterialSupplierDetail) other;

		return ((this.getAutoId() == castOther.getAutoId()) || (this
				.getAutoId() != null
				&& castOther.getAutoId() != null && this.getAutoId().equals(
				castOther.getAutoId())))
				&& ((this.getUnitsCode() == castOther.getUnitsCode()) || (this
						.getUnitsCode() != null
						&& castOther.getUnitsCode() != null && this
						.getUnitsCode().equals(castOther.getUnitsCode())))
				&& ((this.getBillNo() == castOther.getBillNo()) || (this
						.getBillNo() != null
						&& castOther.getBillNo() != null && this.getBillNo()
						.equals(castOther.getBillNo())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getMaterialClass() == castOther.getMaterialClass()) || (this
						.getMaterialClass() != null
						&& castOther.getMaterialClass() != null && this
						.getMaterialClass()
						.equals(castOther.getMaterialClass())))
				&& ((this.getMaterialCode() == castOther.getMaterialCode()) || (this
						.getMaterialCode() != null
						&& castOther.getMaterialCode() != null && this
						.getMaterialCode().equals(castOther.getMaterialCode())))
				&& ((this.getMaterialName() == castOther.getMaterialName()) || (this
						.getMaterialName() != null
						&& castOther.getMaterialName() != null && this
						.getMaterialName().equals(castOther.getMaterialName())))
				&& ((this.getMaterialSpec() == castOther.getMaterialSpec()) || (this
						.getMaterialSpec() != null
						&& castOther.getMaterialSpec() != null && this
						.getMaterialSpec().equals(castOther.getMaterialSpec())))
				&& ((this.getMaterialUnits() == castOther.getMaterialUnits()) || (this
						.getMaterialUnits() != null
						&& castOther.getMaterialUnits() != null && this
						.getMaterialUnits()
						.equals(castOther.getMaterialUnits())))
				&& ((this.getSendAmount() == castOther.getSendAmount()) || (this
						.getSendAmount() != null
						&& castOther.getSendAmount() != null && this
						.getSendAmount().equals(castOther.getSendAmount())))
				&& ((this.getCurrentStockAmount() == castOther
						.getCurrentStockAmount()) || (this
						.getCurrentStockAmount() != null
						&& castOther.getCurrentStockAmount() != null && this
						.getCurrentStockAmount().equals(
								castOther.getCurrentStockAmount())))
				&& ((this.getTradePrice() == castOther.getTradePrice()) || (this
						.getTradePrice() != null
						&& castOther.getTradePrice() != null && this
						.getTradePrice().equals(castOther.getTradePrice())))
				&& ((this.getTradeMoney() == castOther.getTradeMoney()) || (this
						.getTradeMoney() != null
						&& castOther.getTradeMoney() != null && this
						.getTradeMoney().equals(castOther.getTradeMoney())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAutoId() == null ? 0 : this.getAutoId().hashCode());
		result = 37 * result
				+ (getUnitsCode() == null ? 0 : this.getUnitsCode().hashCode());
		result = 37 * result
				+ (getBillNo() == null ? 0 : this.getBillNo().hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37
				* result
				+ (getMaterialClass() == null ? 0 : this.getMaterialClass()
						.hashCode());
		result = 37
				* result
				+ (getMaterialCode() == null ? 0 : this.getMaterialCode()
						.hashCode());
		result = 37
				* result
				+ (getMaterialName() == null ? 0 : this.getMaterialName()
						.hashCode());
		result = 37
				* result
				+ (getMaterialSpec() == null ? 0 : this.getMaterialSpec()
						.hashCode());
		result = 37
				* result
				+ (getMaterialUnits() == null ? 0 : this.getMaterialUnits()
						.hashCode());
		result = 37
				* result
				+ (getSendAmount() == null ? 0 : this.getSendAmount()
						.hashCode());
		result = 37
				* result
				+ (getCurrentStockAmount() == null ? 0 : this
						.getCurrentStockAmount().hashCode());
		result = 37
				* result
				+ (getTradePrice() == null ? 0 : this.getTradePrice()
						.hashCode());
		result = 37
				* result
				+ (getTradeMoney() == null ? 0 : this.getTradeMoney()
						.hashCode());
		return result;
	}

}