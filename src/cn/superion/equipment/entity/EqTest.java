package cn.superion.equipment.entity;

import java.sql.Timestamp;

/**
 * EqTest entity. @author MyEclipse Persistence Tools
 */

public class EqTest implements java.io.Serializable {

	// Fields

	private String autoId;
	private String unitsCode;
	private String billNo;
	private String equipmentCode;
	private String equipmentName;
	private String equipmentSpec;
	private String usedDeptName;
	private String chargePerson;
	private String testUnit;
	private Timestamp testDate;
	private Timestamp testValDate;
	private String res;
	private String testCertifyNo;
	private String maker;
	private String verifier;
	private Timestamp verifyDate;
	private String remark;
	private String status;
	private Timestamp makeDate;

	// Constructors

	/** default constructor */
	public EqTest() {
	}

	/** minimal constructor */
	public EqTest(String autoId, String unitsCode, String billNo) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
	}

	/** full constructor */
	public EqTest(String autoId, String unitsCode, String billNo,
			String equipmentCode, String equipmentName, String equipmentSpec,
			String usedDeptName, String chargePerson, String testUnit,
			Timestamp testDate, Timestamp testValDate, String res,
			String testCertifyNo, String maker, String verifier,
			Timestamp verifyDate, String remark, String status,
			Timestamp makeDate) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.equipmentCode = equipmentCode;
		this.equipmentName = equipmentName;
		this.equipmentSpec = equipmentSpec;
		this.usedDeptName = usedDeptName;
		this.chargePerson = chargePerson;
		this.testUnit = testUnit;
		this.testDate = testDate;
		this.testValDate = testValDate;
		this.res = res;
		this.testCertifyNo = testCertifyNo;
		this.maker = maker;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.remark = remark;
		this.status = status;
		this.makeDate = makeDate;
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

	public String getChargePerson() {
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getTestUnit() {
		return this.testUnit;
	}

	public void setTestUnit(String testUnit) {
		this.testUnit = testUnit;
	}

	public Timestamp getTestDate() {
		return this.testDate;
	}

	public void setTestDate(Timestamp testDate) {
		this.testDate = testDate;
	}

	public Timestamp getTestValDate() {
		return this.testValDate;
	}

	public void setTestValDate(Timestamp testValDate) {
		this.testValDate = testValDate;
	}

	public String getRes() {
		return this.res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getTestCertifyNo() {
		return this.testCertifyNo;
	}

	public void setTestCertifyNo(String testCertifyNo) {
		this.testCertifyNo = testCertifyNo;
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

	public Timestamp getMakeDate() {
		return this.makeDate;
	}

	public void setMakeDate(Timestamp makeDate) {
		this.makeDate = makeDate;
	}

}