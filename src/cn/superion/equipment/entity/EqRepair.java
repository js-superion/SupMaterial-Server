package cn.superion.equipment.entity;

import java.sql.Timestamp;

/**
 * EqRepair entity. @author MyEclipse Persistence Tools
 */

public class EqRepair implements java.io.Serializable {

	// Fields

	private String autoId;
	private String unitsCode;
	private String billNo;
	private String equipmentCode;
	private String equipmentName;
	private String equipmentSpec;
	private String usedDeptName;
	private String chargePerson;
	private String describe;
	private String repProcess;
	private Timestamp finishDate;
	private Timestamp makeDate;
	private Double charges;
	private String res;
	private String maker;
	private String verifier;
	private Timestamp verifyDate;
	private String remark;
	private String status;
	private String sourceAutoId;

	private String applyDept;

	// Constructors

	/** default constructor */
	public EqRepair() {
	}

	/** minimal constructor */
	public EqRepair(String autoId, String unitsCode, String billNo) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
	}

	/** full constructor */
	public EqRepair(String autoId, String unitsCode, String billNo,
			String equipmentCode, String equipmentName, String equipmentSpec,
			String usedDeptName, String chargePerson, String describe,
			String repProcess, Timestamp finishDate, Double charges,String applyDept,
			String res, String maker, String verifier, Timestamp verifyDate,Timestamp makeDate,
			String remark, String status, String sourceAutoId) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.equipmentCode = equipmentCode;
		this.equipmentName = equipmentName;
		this.applyDept = applyDept;
		this.equipmentSpec = equipmentSpec;
		this.usedDeptName = usedDeptName;
		this.makeDate = makeDate;
		this.chargePerson = chargePerson;
		this.describe = describe;
		this.repProcess = repProcess;
		this.finishDate = finishDate;
		this.charges = charges;
		this.res = res;
		this.maker = maker;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.remark = remark;
		this.status = status;
		this.sourceAutoId = sourceAutoId;
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

	public String getApplyDept() {
		return applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
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

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getRepProcess() {
		return this.repProcess;
	}

	public void setRepProcess(String repProcess) {
		this.repProcess = repProcess;
	}

	public Timestamp getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Timestamp finishDate) {
		this.finishDate = finishDate;
	}

	public Double getCharges() {
		return this.charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public String getRes() {
		return this.res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Timestamp getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Timestamp makeDate) {
		this.makeDate = makeDate;
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

	public String getSourceAutoId() {
		return this.sourceAutoId;
	}

	public void setSourceAutoId(String sourceAutoId) {
		this.sourceAutoId = sourceAutoId;
	}

}