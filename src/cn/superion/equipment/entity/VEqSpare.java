package cn.superion.equipment.entity;

import java.util.Date;

/**
 * VEqSpareId entity. @author MyEclipse Persistence Tools
 */

public class VEqSpare implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1867982446070304878L;
	private String equipmentTypeCode;
	private String equipmentTypeName;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private Short serialNo;
	private String sparePartCode;
	private String sparePartName;
	private String partSpec;
	private String partUnit;
	private String manufacture;
	private Double amount;
	private Double unitPrice;

	// Constructors

	/** default constructor */
	public VEqSpare() {
	}

	/** minimal constructor */
	public VEqSpare(String equipmentTypeCode, Short serialNo) {
		this.equipmentTypeCode = equipmentTypeCode;
		this.serialNo = serialNo;
	}

	/** full constructor */
	public VEqSpare(String equipmentTypeCode, String equipmentTypeName,
			String remark, String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus, Short serialNo,
			String sparePartCode, String sparePartName, String partSpec,
			String partUnit, String manufacture, Double amount, Double unitPrice) {
		this.equipmentTypeCode = equipmentTypeCode;
		this.equipmentTypeName = equipmentTypeName;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
		this.serialNo = serialNo;
		this.sparePartCode = sparePartCode;
		this.sparePartName = sparePartName;
		this.partSpec = partSpec;
		this.partUnit = partUnit;
		this.manufacture = manufacture;
		this.amount = amount;
		this.unitPrice = unitPrice;
	}

	// Property accessors

	public String getEquipmentTypeCode() {
		return this.equipmentTypeCode;
	}

	public void setEquipmentTypeCode(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}

	public String getEquipmentTypeName() {
		return this.equipmentTypeName;
	}

	public void setEquipmentTypeName(String equipmentTypeName) {
		this.equipmentTypeName = equipmentTypeName;
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

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public String getSparePartCode() {
		return this.sparePartCode;
	}

	public void setSparePartCode(String sparePartCode) {
		this.sparePartCode = sparePartCode;
	}

	public String getSparePartName() {
		return this.sparePartName;
	}

	public void setSparePartName(String sparePartName) {
		this.sparePartName = sparePartName;
	}

	public String getPartSpec() {
		return this.partSpec;
	}

	public void setPartSpec(String partSpec) {
		this.partSpec = partSpec;
	}

	public String getPartUnit() {
		return this.partUnit;
	}

	public void setPartUnit(String partUnit) {
		this.partUnit = partUnit;
	}

	public String getManufacture() {
		return this.manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VEqSpare))
			return false;
		VEqSpare castOther = (VEqSpare) other;

		return ((this.getEquipmentTypeCode() == castOther
				.getEquipmentTypeCode()) || (this.getEquipmentTypeCode() != null
				&& castOther.getEquipmentTypeCode() != null && this
				.getEquipmentTypeCode()
				.equals(castOther.getEquipmentTypeCode())))
				&& ((this.getEquipmentTypeName() == castOther
						.getEquipmentTypeName()) || (this
						.getEquipmentTypeName() != null
						&& castOther.getEquipmentTypeName() != null && this
						.getEquipmentTypeName().equals(
								castOther.getEquipmentTypeName())))
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
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getSparePartCode() == castOther.getSparePartCode()) || (this
						.getSparePartCode() != null
						&& castOther.getSparePartCode() != null && this
						.getSparePartCode()
						.equals(castOther.getSparePartCode())))
				&& ((this.getSparePartName() == castOther.getSparePartName()) || (this
						.getSparePartName() != null
						&& castOther.getSparePartName() != null && this
						.getSparePartName()
						.equals(castOther.getSparePartName())))
				&& ((this.getPartSpec() == castOther.getPartSpec()) || (this
						.getPartSpec() != null
						&& castOther.getPartSpec() != null && this
						.getPartSpec().equals(castOther.getPartSpec())))
				&& ((this.getPartUnit() == castOther.getPartUnit()) || (this
						.getPartUnit() != null
						&& castOther.getPartUnit() != null && this
						.getPartUnit().equals(castOther.getPartUnit())))
				&& ((this.getManufacture() == castOther.getManufacture()) || (this
						.getManufacture() != null
						&& castOther.getManufacture() != null && this
						.getManufacture().equals(castOther.getManufacture())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null
						&& castOther.getAmount() != null && this.getAmount()
						.equals(castOther.getAmount())))
				&& ((this.getUnitPrice() == castOther.getUnitPrice()) || (this
						.getUnitPrice() != null
						&& castOther.getUnitPrice() != null && this
						.getUnitPrice().equals(castOther.getUnitPrice())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEquipmentTypeCode() == null ? 0 : this
						.getEquipmentTypeCode().hashCode());
		result = 37
				* result
				+ (getEquipmentTypeName() == null ? 0 : this
						.getEquipmentTypeName().hashCode());
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
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37
				* result
				+ (getSparePartCode() == null ? 0 : this.getSparePartCode()
						.hashCode());
		result = 37
				* result
				+ (getSparePartName() == null ? 0 : this.getSparePartName()
						.hashCode());
		result = 37 * result
				+ (getPartSpec() == null ? 0 : this.getPartSpec().hashCode());
		result = 37 * result
				+ (getPartUnit() == null ? 0 : this.getPartUnit().hashCode());
		result = 37
				* result
				+ (getManufacture() == null ? 0 : this.getManufacture()
						.hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37 * result
				+ (getUnitPrice() == null ? 0 : this.getUnitPrice().hashCode());
		return result;
	}

}