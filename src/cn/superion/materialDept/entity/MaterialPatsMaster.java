package cn.superion.materialDept.entity;

import java.util.Date;

/**
 * MaterialPatsMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialPatsMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7704113382229701373L;
	private String autoId;
	private String unitsCode;
	private String billNo;
	private String billType;
	private String storageCode;
	private String operationNo;
	private String patientType;
	private String patientId;
	private String inpNo;
	private Byte visitId;
	private String personName;
	private String sex;
	private Date dateOfBirth;
	private Short age;
	private String ageUnits;
	private String idNo;
	private String bloodName;
	private String rhType;
	private Short bedNo;
	private String chargeType;
	private String deptCode;
	private String wardCode;
	private String clinicDiag;
	private String clinicDiagName;
	private String applyDoctor;
	private Date applyDate;
	
//	private String salerCode;
//	private String salerName;
	
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;

	// Constructors

	/** default constructor */
	public MaterialPatsMaster() {
	}

	/** minimal constructor */
	public MaterialPatsMaster(String autoId, String unitsCode, String billNo,
			String storageCode, String personName) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.storageCode = storageCode;
		this.personName = personName;
	}

	/** full constructor */
	public MaterialPatsMaster(String autoId, String unitsCode, String billNo,
			String billType, String storageCode, String operationNo,
			String patientType, String patientId, String inpNo, Byte visitId,
			String personName, String sex, Date dateOfBirth, Short age,
			String ageUnits, String idNo, String bloodName, String rhType,
			Short bedNo, String chargeType, String deptCode, String wardCode,
			String clinicDiag, String clinicDiagName, String applyDoctor,
			Date applyDate, String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.billType = billType;
		this.storageCode = storageCode;
		this.operationNo = operationNo;
		this.patientType = patientType;
		this.patientId = patientId;
		this.inpNo = inpNo;
		this.visitId = visitId;
		this.personName = personName;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.ageUnits = ageUnits;
		this.idNo = idNo;
		this.bloodName = bloodName;
		this.rhType = rhType;
		this.bedNo = bedNo;
		this.chargeType = chargeType;
		this.deptCode = deptCode;
		this.wardCode = wardCode;
		this.clinicDiag = clinicDiag;
		this.clinicDiagName = clinicDiagName;
		this.applyDoctor = applyDoctor;
		this.applyDate = applyDate;
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

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getStorageCode() {
		return this.storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}

	public String getOperationNo() {
		return this.operationNo;
	}

	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getInpNo() {
		return this.inpNo;
	}

	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}

	public Byte getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Byte visitId) {
		this.visitId = visitId;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public String getAgeUnits() {
		return this.ageUnits;
	}

	public void setAgeUnits(String ageUnits) {
		this.ageUnits = ageUnits;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getBloodName() {
		return this.bloodName;
	}

	public void setBloodName(String bloodName) {
		this.bloodName = bloodName;
	}

	public String getRhType() {
		return this.rhType;
	}

	public void setRhType(String rhType) {
		this.rhType = rhType;
	}

	public Short getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(Short bedNo) {
		this.bedNo = bedNo;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getWardCode() {
		return this.wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getClinicDiag() {
		return this.clinicDiag;
	}

	public void setClinicDiag(String clinicDiag) {
		this.clinicDiag = clinicDiag;
	}

	public String getClinicDiagName() {
		return this.clinicDiagName;
	}

	public void setClinicDiagName(String clinicDiagName) {
		this.clinicDiagName = clinicDiagName;
	}

	public String getApplyDoctor() {
		return this.applyDoctor;
	}

	public void setApplyDoctor(String applyDoctor) {
		this.applyDoctor = applyDoctor;
	}

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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
	
	/*public String getSalerCode() {
		return salerCode;
	}

	public void setSalerCode(String salerCode) {
		this.salerCode = salerCode;
	}

	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}*/

	public PatsBillMaster buildPatsBillMaster() {
		PatsBillMaster pbm = new PatsBillMaster();
		pbm.setUnitsCode(this.unitsCode);
		pbm.setPatientId(this.patientId);
		pbm.setVisitId(this.visitId);
		pbm.setInpNo(this.inpNo);
		pbm.setOrderedDept(this.deptCode);
		pbm.setPerformedDept(this.deptCode);
		pbm.setOrderedDoctor(this.applyDoctor);
		pbm.setPerformedDoctor(this.applyDoctor);
		return pbm;
	}

}