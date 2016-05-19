package cn.superion.equipment.entity;

import java.util.Date;

/**
 * EqEquipmentType entity. @author MyEclipse Persistence Tools
 */


public class EqEquipmentType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -337583380019056780L;
	// Fields

	private String autoId;
	private String equipmentTypeCode;
	private String equipmentTypeName;
	private String equipmentSpec;
	private String equipmentClass;
	private Double machineComplexRatio;
	private Double electricalComplexRatio;
	private String startChangeSign;
	private String preciseSign;
	private String rareSign;
	private String largeSign;
	private String specialTypeSign;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqEquipmentType() {
	}

	/** minimal constructor */
	public EqEquipmentType(String autoId) {
		this.autoId = autoId;
	}

	/** full constructor */
	public EqEquipmentType(String autoId, String equipmentTypeCode,
			String equipmentTypeName, String equipmentSpec,
			String equipmentClass, Double machineComplexRatio,
			Double electricalComplexRatio, String startChangeSign,
			String preciseSign, String rareSign, String largeSign,
			String specialTypeSign, String remark, String maker, Date makeDate,
			String verifier, Date verifyDate, String currentStatus) {
		this.autoId = autoId;
		this.equipmentTypeCode = equipmentTypeCode;
		this.equipmentTypeName = equipmentTypeName;
		this.equipmentSpec = equipmentSpec;
		this.equipmentClass = equipmentClass;
		this.machineComplexRatio = machineComplexRatio;
		this.electricalComplexRatio = electricalComplexRatio;
		this.startChangeSign = startChangeSign;
		this.preciseSign = preciseSign;
		this.rareSign = rareSign;
		this.largeSign = largeSign;
		this.specialTypeSign = specialTypeSign;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

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

	public String getEquipmentSpec() {
		return this.equipmentSpec;
	}

	public void setEquipmentSpec(String equipmentSpec) {
		this.equipmentSpec = equipmentSpec;
	}

	public String getEquipmentClass() {
		return this.equipmentClass;
	}

	public void setEquipmentClass(String equipmentClass) {
		this.equipmentClass = equipmentClass;
	}

	public Double getMachineComplexRatio() {
		return this.machineComplexRatio;
	}

	public void setMachineComplexRatio(Double machineComplexRatio) {
		this.machineComplexRatio = machineComplexRatio;
	}

	public Double getElectricalComplexRatio() {
		return this.electricalComplexRatio;
	}

	public void setElectricalComplexRatio(Double electricalComplexRatio) {
		this.electricalComplexRatio = electricalComplexRatio;
	}

	public String getStartChangeSign() {
		return this.startChangeSign;
	}

	public void setStartChangeSign(String startChangeSign) {
		this.startChangeSign = startChangeSign;
	}

	public String getPreciseSign() {
		return this.preciseSign;
	}

	public void setPreciseSign(String preciseSign) {
		this.preciseSign = preciseSign;
	}

	public String getRareSign() {
		return this.rareSign;
	}

	public void setRareSign(String rareSign) {
		this.rareSign = rareSign;
	}

	public String getLargeSign() {
		return this.largeSign;
	}

	public void setLargeSign(String largeSign) {
		this.largeSign = largeSign;
	}

	public String getSpecialTypeSign() {
		return this.specialTypeSign;
	}

	public void setSpecialTypeSign(String specialTypeSign) {
		this.specialTypeSign = specialTypeSign;
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