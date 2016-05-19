package cn.superion.cssd.entity;

import java.util.Date;

/**
 * VCssdWash entity. @author MyEclipse Persistence Tools
 */

public class VCssdWash implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6766145571942435914L;
	private String autoId;
	private String unitsCode;
	private String billNo;
	private Date billDate;
	private String equipmentCode;
	private String equipmentName;
	private String antisepsis;
	private String enzymeChroma;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private String detailAutoId;
	private Short serialNo;
	private String personId;
	private Date washDate;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private Double tradePrice;
	private Double amount;
	private String detailRemark;
	private Double packagedAmount;
	private String detailCurrentStatus;

	// Constructors

	/** default constructor */
	public VCssdWash() {
	}

	/** minimal constructor */
	public VCssdWash(String autoId, String unitsCode, String detailAutoId,
			Short serialNo, String materialClass, String materialId,
			String materialCode, String materialName) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.detailAutoId = detailAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public VCssdWash(String autoId, String unitsCode, String billNo,
			Date billDate, String equipmentCode, String equipmentName,
			String antisepsis, String enzymeChroma, String remark,
			String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus, String detailAutoId,
			Short serialNo, String personId, Date washDate,
			String materialClass, String materialId, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			String factoryCode, Double tradePrice, Double amount,
			String detailRemark, Double packagedAmount,
			String detailCurrentStatus) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.equipmentCode = equipmentCode;
		this.equipmentName = equipmentName;
		this.antisepsis = antisepsis;
		this.enzymeChroma = enzymeChroma;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
		this.detailAutoId = detailAutoId;
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
		this.detailRemark = detailRemark;
		this.packagedAmount = packagedAmount;
		this.detailCurrentStatus = detailCurrentStatus;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
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

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getEquipmentCode() {
		return this.equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getAntisepsis() {
		return this.antisepsis;
	}

	public void setAntisepsis(String antisepsis) {
		this.antisepsis = antisepsis;
	}

	public String getEnzymeChroma() {
		return this.enzymeChroma;
	}

	public void setEnzymeChroma(String enzymeChroma) {
		this.enzymeChroma = enzymeChroma;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Date getMakeDate() {
		return this.makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getVerifier() {
		return this.verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public Date getVerifyDate() {
		return this.verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getDetailAutoId() {
		return this.detailAutoId;
	}

	public void setDetailAutoId(String detailAutoId) {
		this.detailAutoId = detailAutoId;
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

	public String getDetailCurrentStatus() {
		return this.detailCurrentStatus;
	}

	public void setDetailCurrentStatus(String detailCurrentStatus) {
		this.detailCurrentStatus = detailCurrentStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VCssdWash))
			return false;
		VCssdWash castOther = (VCssdWash) other;

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
				&& ((this.getBillDate() == castOther.getBillDate()) || (this
						.getBillDate() != null
						&& castOther.getBillDate() != null && this
						.getBillDate().equals(castOther.getBillDate())))
				&& ((this.getEquipmentCode() == castOther.getEquipmentCode()) || (this
						.getEquipmentCode() != null
						&& castOther.getEquipmentCode() != null && this
						.getEquipmentCode()
						.equals(castOther.getEquipmentCode())))
				&& ((this.getEquipmentName() == castOther.getEquipmentName()) || (this
						.getEquipmentName() != null
						&& castOther.getEquipmentName() != null && this
						.getEquipmentName()
						.equals(castOther.getEquipmentName())))
				&& ((this.getAntisepsis() == castOther.getAntisepsis()) || (this
						.getAntisepsis() != null
						&& castOther.getAntisepsis() != null && this
						.getAntisepsis().equals(castOther.getAntisepsis())))
				&& ((this.getEnzymeChroma() == castOther.getEnzymeChroma()) || (this
						.getEnzymeChroma() != null
						&& castOther.getEnzymeChroma() != null && this
						.getEnzymeChroma().equals(castOther.getEnzymeChroma())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())))
				&& ((this.getMaker() == castOther.getMaker()) || (this
						.getMaker() != null
						&& castOther.getMaker() != null && this.getMaker()
						.equals(castOther.getMaker())))
				&& ((this.getMakeDate() == castOther.getMakeDate()) || (this
						.getMakeDate() != null
						&& castOther.getMakeDate() != null && this
						.getMakeDate().equals(castOther.getMakeDate())))
				&& ((this.getVerifier() == castOther.getVerifier()) || (this
						.getVerifier() != null
						&& castOther.getVerifier() != null && this
						.getVerifier().equals(castOther.getVerifier())))
				&& ((this.getVerifyDate() == castOther.getVerifyDate()) || (this
						.getVerifyDate() != null
						&& castOther.getVerifyDate() != null && this
						.getVerifyDate().equals(castOther.getVerifyDate())))
				&& ((this.getCurrentStatus() == castOther.getCurrentStatus()) || (this
						.getCurrentStatus() != null
						&& castOther.getCurrentStatus() != null && this
						.getCurrentStatus()
						.equals(castOther.getCurrentStatus())))
				&& ((this.getDetailAutoId() == castOther.getDetailAutoId()) || (this
						.getDetailAutoId() != null
						&& castOther.getDetailAutoId() != null && this
						.getDetailAutoId().equals(castOther.getDetailAutoId())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getPersonId() == castOther.getPersonId()) || (this
						.getPersonId() != null
						&& castOther.getPersonId() != null && this
						.getPersonId().equals(castOther.getPersonId())))
				&& ((this.getWashDate() == castOther.getWashDate()) || (this
						.getWashDate() != null
						&& castOther.getWashDate() != null && this
						.getWashDate().equals(castOther.getWashDate())))
				&& ((this.getMaterialClass() == castOther.getMaterialClass()) || (this
						.getMaterialClass() != null
						&& castOther.getMaterialClass() != null && this
						.getMaterialClass()
						.equals(castOther.getMaterialClass())))
				&& ((this.getMaterialId() == castOther.getMaterialId()) || (this
						.getMaterialId() != null
						&& castOther.getMaterialId() != null && this
						.getMaterialId().equals(castOther.getMaterialId())))
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
				&& ((this.getFactoryCode() == castOther.getFactoryCode()) || (this
						.getFactoryCode() != null
						&& castOther.getFactoryCode() != null && this
						.getFactoryCode().equals(castOther.getFactoryCode())))
				&& ((this.getTradePrice() == castOther.getTradePrice()) || (this
						.getTradePrice() != null
						&& castOther.getTradePrice() != null && this
						.getTradePrice().equals(castOther.getTradePrice())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null
						&& castOther.getAmount() != null && this.getAmount()
						.equals(castOther.getAmount())))
				&& ((this.getDetailRemark() == castOther.getDetailRemark()) || (this
						.getDetailRemark() != null
						&& castOther.getDetailRemark() != null && this
						.getDetailRemark().equals(castOther.getDetailRemark())))
				&& ((this.getPackagedAmount() == castOther.getPackagedAmount()) || (this
						.getPackagedAmount() != null
						&& castOther.getPackagedAmount() != null && this
						.getPackagedAmount().equals(
								castOther.getPackagedAmount())))
				&& ((this.getDetailCurrentStatus() == castOther
						.getDetailCurrentStatus()) || (this
						.getDetailCurrentStatus() != null
						&& castOther.getDetailCurrentStatus() != null && this
						.getDetailCurrentStatus().equals(
								castOther.getDetailCurrentStatus())));
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
				+ (getBillDate() == null ? 0 : this.getBillDate().hashCode());
		result = 37
				* result
				+ (getEquipmentCode() == null ? 0 : this.getEquipmentCode()
						.hashCode());
		result = 37
				* result
				+ (getEquipmentName() == null ? 0 : this.getEquipmentName()
						.hashCode());
		result = 37
				* result
				+ (getAntisepsis() == null ? 0 : this.getAntisepsis()
						.hashCode());
		result = 37
				* result
				+ (getEnzymeChroma() == null ? 0 : this.getEnzymeChroma()
						.hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getMaker() == null ? 0 : this.getMaker().hashCode());
		result = 37 * result
				+ (getMakeDate() == null ? 0 : this.getMakeDate().hashCode());
		result = 37 * result
				+ (getVerifier() == null ? 0 : this.getVerifier().hashCode());
		result = 37
				* result
				+ (getVerifyDate() == null ? 0 : this.getVerifyDate()
						.hashCode());
		result = 37
				* result
				+ (getCurrentStatus() == null ? 0 : this.getCurrentStatus()
						.hashCode());
		result = 37
				* result
				+ (getDetailAutoId() == null ? 0 : this.getDetailAutoId()
						.hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37 * result
				+ (getPersonId() == null ? 0 : this.getPersonId().hashCode());
		result = 37 * result
				+ (getWashDate() == null ? 0 : this.getWashDate().hashCode());
		result = 37
				* result
				+ (getMaterialClass() == null ? 0 : this.getMaterialClass()
						.hashCode());
		result = 37
				* result
				+ (getMaterialId() == null ? 0 : this.getMaterialId()
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
				+ (getFactoryCode() == null ? 0 : this.getFactoryCode()
						.hashCode());
		result = 37
				* result
				+ (getTradePrice() == null ? 0 : this.getTradePrice()
						.hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37
				* result
				+ (getDetailRemark() == null ? 0 : this.getDetailRemark()
						.hashCode());
		result = 37
				* result
				+ (getPackagedAmount() == null ? 0 : this.getPackagedAmount()
						.hashCode());
		result = 37
				* result
				+ (getDetailCurrentStatus() == null ? 0 : this
						.getDetailCurrentStatus().hashCode());
		return result;
	}

}