package cn.superion.equipment.entity;

import java.sql.Timestamp;

/**
 * EqRepairApply entity. @author MyEclipse Persistence Tools
 */

public class EqRepairApply implements java.io.Serializable {

	// Fields

	private String autoId;
	private String unitsCode;
	private String deptName;
	private String billNo;
	private String equipmentCode;
	private String equipmentName;
	private String equipmentSpec;
	private String usedDeptName;
	private String chargePerson;
	private String describe;
	private String maker;
	private String verifier;
	private Timestamp verifyDate;
	private Timestamp makeDate;
	private String remark;
	private String status;

	// Constructors

	/** default constructor */
	public EqRepairApply() {
	}

	/** minimal constructor */
	public EqRepairApply(String autoId, String unitsCode, String billNo) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
	}

	/** full constructor */
	public EqRepairApply(String autoId, String unitsCode, String billNo,
			String equipmentCode, String equipmentName, String equipmentSpec,String deptName,
			String usedDeptName, String chargePerson, String describe,Timestamp makeDate,
			String maker, String verifier, Timestamp verifyDate, String remark,
			String status) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.equipmentCode = equipmentCode;
		this.equipmentName = equipmentName;
		this.equipmentSpec = equipmentSpec;
		this.usedDeptName = usedDeptName;
		this.deptName = deptName;
		this.chargePerson = chargePerson;
		this.describe = describe;
		this.maker = maker;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.makeDate = makeDate;
		this.remark = remark;
		this.status = status;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getEquipmentSpec() {
		return this.equipmentSpec;
	}

	public void setEquipmentSpec(String equipmentSpec) {
		this.equipmentSpec = equipmentSpec;
	}

	public String getUsedDeptName() {
		return this.usedDeptName;
	}

	public void setUsedDeptName(String usedDeptName) {
		this.usedDeptName = usedDeptName;
	}

	public Timestamp getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Timestamp makeDate) {
		this.makeDate = makeDate;
	}

	public String getChargePerson() {
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getVerifier() {
		return this.verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public Timestamp getVerifyDate() {
		return this.verifyDate;
	}

	public void setVerifyDate(Timestamp verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}