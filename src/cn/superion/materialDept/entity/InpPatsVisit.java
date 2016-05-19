package cn.superion.materialDept.entity;

import java.util.Date;

/**
 * AbstractInpPatsVisit entity provides the base persistence definition of the
 * InpPatsVisit entity. @author MyEclipse Persistence Tools
 */

public class InpPatsVisit implements java.io.Serializable {

	// Fields

	private String unitsCode;
	private String inpNo;
	private String patientId;
	private Byte visitId;
	private String insuranceMode;
	private String insuranceType;
	private String insuranceNo;
	private String insuranceCardNo;
	private String firstBookHospital;
	private String areaCode;
	private String areaCodeFound;
	private String patientName;
	private String sex;
	private Date dateOfBirth;
	private String age;
	private String notAgeStatus;
	private String maritalState;
	private String occupation;
	private String birthPlace;
	private String nation;
	private String citizenship;
	private String idNo;
	private String chargeType;
	private String personStatus;
	private String unitInContract;
	private String workingStatus;
	private String serviceAgency;
	private String mailingAddress;
	private String zipCode;
	private String homePhone;
	private String mobilePhone;
	private String EMail;
	private String nextOfKinName;
	private String relationship;
	private String nextOfKinPhone;
	private String nextOfKinAddress;
	private String admMode;
	private String admCause;
	private Date admDateTime;
	private String admDiagnose;
	private String admDiagnoseClass;
	private String admPatientCondition;
	private String nursingClass;
	private String consultingDoctor;
	private String doctorInCharge;
	private Short bedNoTemp;
	private Short bedNo;
	private Date operatingDate;
	private String admGroup;
	private String admDept;
	private String admWard;
	private Date dischargeDateTime;
	private String dischargeGroup;
	private String dischargeDept;
	private String dischargeWard;
	private String dischargeDiagnose;
	private String dischargeDiagnoseClass;
	private String dischargePatientCondition;
	private String dischargeMode;
	private Double prepayments;
	private Double totalCosts;
	private Double totalCharges;
	private String guarantor;
	private String guarantorOrg;
	private String guarantorPhoneNum;
	private Date billCheckedDateTime;
	private String singleIllnessSign;
	private Double singleIllnessSum;
	private Date settledDateTime;
	private String secondInSign;
	private Double payMinLine;
	private String currentStatus;
	private String transferSign;
	private Date transferDate;
	private String remark;
	private Short insuranceVisitId;
	private String insuranceInpNo;
	private String healthNo;
	private String healthCardNo;
	private String remoteVisitSign;
	private String otherInpNo;
	private String doubleSign;
	private String operator;
	private Date operateDate;
	private String admDiagnoseName;
	private String dischargeDiagnoseName;
	private String lockSign;
	private String settleType;
	private String verifyRemark;
	private Date verifyDate;
	private String verifier;
	private Double prepayments1;
	private Double prepayments2;
	private String transferPerson;
	private String insuranceTypeTransferSign;
	private Double totalFundPay;
	private String autoVisitId;
	private String insurancePayExceedSign;
	private String insuranceUpSign;
	private String areaCodeBirth;
	private String bedInsuranceType;
	private String newbornSign;
	private String seriousIllnessSign;
	private String deathCause;
	private Date deathDate;

	// Constructors

	/** default constructor */
	public InpPatsVisit() {
	}

	/** minimal constructor */
	public InpPatsVisit(String unitsCode, String inpNo, String patientId,
			String chargeType) {
		this.unitsCode = unitsCode;
		this.inpNo = inpNo;
		this.patientId = patientId;
		this.chargeType = chargeType;
	}

	/** full constructor */
	public InpPatsVisit(String unitsCode, String inpNo, String patientId,
			Byte visitId, String insuranceMode, String insuranceType,
			String insuranceNo, String insuranceCardNo,
			String firstBookHospital, String areaCode, String areaCodeFound,
			String patientName, String sex, Date dateOfBirth, String age,
			String notAgeStatus, String maritalState, String occupation,
			String birthPlace, String nation, String citizenship, String idNo,
			String chargeType, String personStatus, String unitInContract,
			String workingStatus, String serviceAgency, String mailingAddress,
			String zipCode, String homePhone, String mobilePhone, String EMail,
			String nextOfKinName, String relationship, String nextOfKinPhone,
			String nextOfKinAddress, String admMode, String admCause,
			Date admDateTime, String admDiagnose, String admDiagnoseClass,
			String admPatientCondition, String nursingClass,
			String consultingDoctor, String doctorInCharge, Short bedNoTemp,
			Short bedNo, Date operatingDate, String admGroup,
			String admDept, String admWard, Date dischargeDateTime,
			String dischargeGroup, String dischargeDept, String dischargeWard,
			String dischargeDiagnose, String dischargeDiagnoseClass,
			String dischargePatientCondition, String dischargeMode,
			Double prepayments, Double totalCosts, Double totalCharges,
			String guarantor, String guarantorOrg, String guarantorPhoneNum,
			Date billCheckedDateTime, String singleIllnessSign,
			Double singleIllnessSum, Date settledDateTime,
			String secondInSign, Double payMinLine, String currentStatus,
			String transferSign, Date transferDate, String remark,
			Short insuranceVisitId, String insuranceInpNo, String healthNo,
			String healthCardNo, String remoteVisitSign, String otherInpNo,
			String doubleSign, String operator, Date operateDate,
			String admDiagnoseName, String dischargeDiagnoseName,
			String lockSign, String settleType, String verifyRemark,
			Date verifyDate, String verifier, Double prepayments1,
			Double prepayments2, String transferPerson,
			String insuranceTypeTransferSign, Double totalFundPay,
			String autoVisitId, String insurancePayExceedSign,
			String insuranceUpSign, String areaCodeBirth,
			String bedInsuranceType, String newbornSign,
			String seriousIllnessSign, String deathCause, Date deathDate) {
		this.unitsCode = unitsCode;
		this.inpNo = inpNo;
		this.patientId = patientId;
		this.visitId = visitId;
		this.insuranceMode = insuranceMode;
		this.insuranceType = insuranceType;
		this.insuranceNo = insuranceNo;
		this.insuranceCardNo = insuranceCardNo;
		this.firstBookHospital = firstBookHospital;
		this.areaCode = areaCode;
		this.areaCodeFound = areaCodeFound;
		this.patientName = patientName;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.notAgeStatus = notAgeStatus;
		this.maritalState = maritalState;
		this.occupation = occupation;
		this.birthPlace = birthPlace;
		this.nation = nation;
		this.citizenship = citizenship;
		this.idNo = idNo;
		this.chargeType = chargeType;
		this.personStatus = personStatus;
		this.unitInContract = unitInContract;
		this.workingStatus = workingStatus;
		this.serviceAgency = serviceAgency;
		this.mailingAddress = mailingAddress;
		this.zipCode = zipCode;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.EMail = EMail;
		this.nextOfKinName = nextOfKinName;
		this.relationship = relationship;
		this.nextOfKinPhone = nextOfKinPhone;
		this.nextOfKinAddress = nextOfKinAddress;
		this.admMode = admMode;
		this.admCause = admCause;
		this.admDateTime = admDateTime;
		this.admDiagnose = admDiagnose;
		this.admDiagnoseClass = admDiagnoseClass;
		this.admPatientCondition = admPatientCondition;
		this.nursingClass = nursingClass;
		this.consultingDoctor = consultingDoctor;
		this.doctorInCharge = doctorInCharge;
		this.bedNoTemp = bedNoTemp;
		this.bedNo = bedNo;
		this.operatingDate = operatingDate;
		this.admGroup = admGroup;
		this.admDept = admDept;
		this.admWard = admWard;
		this.dischargeDateTime = dischargeDateTime;
		this.dischargeGroup = dischargeGroup;
		this.dischargeDept = dischargeDept;
		this.dischargeWard = dischargeWard;
		this.dischargeDiagnose = dischargeDiagnose;
		this.dischargeDiagnoseClass = dischargeDiagnoseClass;
		this.dischargePatientCondition = dischargePatientCondition;
		this.dischargeMode = dischargeMode;
		this.prepayments = prepayments;
		this.totalCosts = totalCosts;
		this.totalCharges = totalCharges;
		this.guarantor = guarantor;
		this.guarantorOrg = guarantorOrg;
		this.guarantorPhoneNum = guarantorPhoneNum;
		this.billCheckedDateTime = billCheckedDateTime;
		this.singleIllnessSign = singleIllnessSign;
		this.singleIllnessSum = singleIllnessSum;
		this.settledDateTime = settledDateTime;
		this.secondInSign = secondInSign;
		this.payMinLine = payMinLine;
		this.currentStatus = currentStatus;
		this.transferSign = transferSign;
		this.transferDate = transferDate;
		this.remark = remark;
		this.insuranceVisitId = insuranceVisitId;
		this.insuranceInpNo = insuranceInpNo;
		this.healthNo = healthNo;
		this.healthCardNo = healthCardNo;
		this.remoteVisitSign = remoteVisitSign;
		this.otherInpNo = otherInpNo;
		this.doubleSign = doubleSign;
		this.operator = operator;
		this.operateDate = operateDate;
		this.admDiagnoseName = admDiagnoseName;
		this.dischargeDiagnoseName = dischargeDiagnoseName;
		this.lockSign = lockSign;
		this.settleType = settleType;
		this.verifyRemark = verifyRemark;
		this.verifyDate = verifyDate;
		this.verifier = verifier;
		this.prepayments1 = prepayments1;
		this.prepayments2 = prepayments2;
		this.transferPerson = transferPerson;
		this.insuranceTypeTransferSign = insuranceTypeTransferSign;
		this.totalFundPay = totalFundPay;
		this.autoVisitId = autoVisitId;
		this.insurancePayExceedSign = insurancePayExceedSign;
		this.insuranceUpSign = insuranceUpSign;
		this.areaCodeBirth = areaCodeBirth;
		this.bedInsuranceType = bedInsuranceType;
		this.newbornSign = newbornSign;
		this.seriousIllnessSign = seriousIllnessSign;
		this.deathCause = deathCause;
		this.deathDate = deathDate;
	}

	// Property accessors

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getInpNo() {
		return this.inpNo;
	}

	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}


	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Byte getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Byte visitId) {
		this.visitId = visitId;
	}

	public String getInsuranceMode() {
		return this.insuranceMode;
	}

	public void setInsuranceMode(String insuranceMode) {
		this.insuranceMode = insuranceMode;
	}

	public String getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceNo() {
		return this.insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

	public String getInsuranceCardNo() {
		return this.insuranceCardNo;
	}

	public void setInsuranceCardNo(String insuranceCardNo) {
		this.insuranceCardNo = insuranceCardNo;
	}

	public String getFirstBookHospital() {
		return this.firstBookHospital;
	}

	public void setFirstBookHospital(String firstBookHospital) {
		this.firstBookHospital = firstBookHospital;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaCodeFound() {
		return this.areaCodeFound;
	}

	public void setAreaCodeFound(String areaCodeFound) {
		this.areaCodeFound = areaCodeFound;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNotAgeStatus() {
		return this.notAgeStatus;
	}

	public void setNotAgeStatus(String notAgeStatus) {
		this.notAgeStatus = notAgeStatus;
	}

	public String getMaritalState() {
		return this.maritalState;
	}

	public void setMaritalState(String maritalState) {
		this.maritalState = maritalState;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getCitizenship() {
		return this.citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getPersonStatus() {
		return this.personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	public String getUnitInContract() {
		return this.unitInContract;
	}

	public void setUnitInContract(String unitInContract) {
		this.unitInContract = unitInContract;
	}

	public String getWorkingStatus() {
		return this.workingStatus;
	}

	public void setWorkingStatus(String workingStatus) {
		this.workingStatus = workingStatus;
	}

	public String getServiceAgency() {
		return this.serviceAgency;
	}

	public void setServiceAgency(String serviceAgency) {
		this.serviceAgency = serviceAgency;
	}

	public String getMailingAddress() {
		return this.mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	public String getNextOfKinName() {
		return this.nextOfKinName;
	}

	public void setNextOfKinName(String nextOfKinName) {
		this.nextOfKinName = nextOfKinName;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getNextOfKinPhone() {
		return this.nextOfKinPhone;
	}

	public void setNextOfKinPhone(String nextOfKinPhone) {
		this.nextOfKinPhone = nextOfKinPhone;
	}

	public String getNextOfKinAddress() {
		return this.nextOfKinAddress;
	}

	public void setNextOfKinAddress(String nextOfKinAddress) {
		this.nextOfKinAddress = nextOfKinAddress;
	}

	public String getAdmMode() {
		return this.admMode;
	}

	public void setAdmMode(String admMode) {
		this.admMode = admMode;
	}

	public String getAdmCause() {
		return this.admCause;
	}

	public void setAdmCause(String admCause) {
		this.admCause = admCause;
	}

	public Date getAdmDateTime() {
		return this.admDateTime;
	}

	public void setAdmDateTime(Date admDateTime) {
		this.admDateTime = admDateTime;
	}

	public String getAdmDiagnose() {
		return this.admDiagnose;
	}

	public void setAdmDiagnose(String admDiagnose) {
		this.admDiagnose = admDiagnose;
	}

	public String getAdmDiagnoseClass() {
		return this.admDiagnoseClass;
	}

	public void setAdmDiagnoseClass(String admDiagnoseClass) {
		this.admDiagnoseClass = admDiagnoseClass;
	}

	public String getAdmPatientCondition() {
		return this.admPatientCondition;
	}

	public void setAdmPatientCondition(String admPatientCondition) {
		this.admPatientCondition = admPatientCondition;
	}

	public String getNursingClass() {
		return this.nursingClass;
	}

	public void setNursingClass(String nursingClass) {
		this.nursingClass = nursingClass;
	}

	public String getConsultingDoctor() {
		return this.consultingDoctor;
	}

	public void setConsultingDoctor(String consultingDoctor) {
		this.consultingDoctor = consultingDoctor;
	}

	public String getDoctorInCharge() {
		return this.doctorInCharge;
	}

	public void setDoctorInCharge(String doctorInCharge) {
		this.doctorInCharge = doctorInCharge;
	}

	public Short getBedNoTemp() {
		return this.bedNoTemp;
	}

	public void setBedNoTemp(Short bedNoTemp) {
		this.bedNoTemp = bedNoTemp;
	}

	public Short getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(Short bedNo) {
		this.bedNo = bedNo;
	}

	public Date getOperatingDate() {
		return this.operatingDate;
	}

	public void setOperatingDate(Date operatingDate) {
		this.operatingDate = operatingDate;
	}

	public String getAdmGroup() {
		return this.admGroup;
	}

	public void setAdmGroup(String admGroup) {
		this.admGroup = admGroup;
	}

	public String getAdmDept() {
		return this.admDept;
	}

	public void setAdmDept(String admDept) {
		this.admDept = admDept;
	}

	public String getAdmWard() {
		return this.admWard;
	}

	public void setAdmWard(String admWard) {
		this.admWard = admWard;
	}

	public Date getDischargeDateTime() {
		return this.dischargeDateTime;
	}

	public void setDischargeDateTime(Date dischargeDateTime) {
		this.dischargeDateTime = dischargeDateTime;
	}

	public String getDischargeGroup() {
		return this.dischargeGroup;
	}

	public void setDischargeGroup(String dischargeGroup) {
		this.dischargeGroup = dischargeGroup;
	}

	public String getDischargeDept() {
		return this.dischargeDept;
	}

	public void setDischargeDept(String dischargeDept) {
		this.dischargeDept = dischargeDept;
	}

	public String getDischargeWard() {
		return this.dischargeWard;
	}

	public void setDischargeWard(String dischargeWard) {
		this.dischargeWard = dischargeWard;
	}

	public String getDischargeDiagnose() {
		return this.dischargeDiagnose;
	}

	public void setDischargeDiagnose(String dischargeDiagnose) {
		this.dischargeDiagnose = dischargeDiagnose;
	}

	public String getDischargeDiagnoseClass() {
		return this.dischargeDiagnoseClass;
	}

	public void setDischargeDiagnoseClass(String dischargeDiagnoseClass) {
		this.dischargeDiagnoseClass = dischargeDiagnoseClass;
	}

	public String getDischargePatientCondition() {
		return this.dischargePatientCondition;
	}

	public void setDischargePatientCondition(String dischargePatientCondition) {
		this.dischargePatientCondition = dischargePatientCondition;
	}

	public String getDischargeMode() {
		return this.dischargeMode;
	}

	public void setDischargeMode(String dischargeMode) {
		this.dischargeMode = dischargeMode;
	}

	public Double getPrepayments() {
		return this.prepayments;
	}

	public void setPrepayments(Double prepayments) {
		this.prepayments = prepayments;
	}

	public Double getTotalCosts() {
		return this.totalCosts;
	}

	public void setTotalCosts(Double totalCosts) {
		this.totalCosts = totalCosts;
	}

	public Double getTotalCharges() {
		return this.totalCharges;
	}

	public void setTotalCharges(Double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public String getGuarantor() {
		return this.guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}

	public String getGuarantorOrg() {
		return this.guarantorOrg;
	}

	public void setGuarantorOrg(String guarantorOrg) {
		this.guarantorOrg = guarantorOrg;
	}

	public String getGuarantorPhoneNum() {
		return this.guarantorPhoneNum;
	}

	public void setGuarantorPhoneNum(String guarantorPhoneNum) {
		this.guarantorPhoneNum = guarantorPhoneNum;
	}

	public Date getBillCheckedDateTime() {
		return this.billCheckedDateTime;
	}

	public void setBillCheckedDateTime(Date billCheckedDateTime) {
		this.billCheckedDateTime = billCheckedDateTime;
	}

	public String getSingleIllnessSign() {
		return this.singleIllnessSign;
	}

	public void setSingleIllnessSign(String singleIllnessSign) {
		this.singleIllnessSign = singleIllnessSign;
	}

	public Double getSingleIllnessSum() {
		return this.singleIllnessSum;
	}

	public void setSingleIllnessSum(Double singleIllnessSum) {
		this.singleIllnessSum = singleIllnessSum;
	}

	public Date getSettledDateTime() {
		return this.settledDateTime;
	}

	public void setSettledDateTime(Date settledDateTime) {
		this.settledDateTime = settledDateTime;
	}

	public String getSecondInSign() {
		return this.secondInSign;
	}

	public void setSecondInSign(String secondInSign) {
		this.secondInSign = secondInSign;
	}

	public Double getPayMinLine() {
		return this.payMinLine;
	}

	public void setPayMinLine(Double payMinLine) {
		this.payMinLine = payMinLine;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getTransferSign() {
		return this.transferSign;
	}

	public void setTransferSign(String transferSign) {
		this.transferSign = transferSign;
	}

	public Date getTransferDate() {
		return this.transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Short getInsuranceVisitId() {
		return this.insuranceVisitId;
	}

	public void setInsuranceVisitId(Short insuranceVisitId) {
		this.insuranceVisitId = insuranceVisitId;
	}

	public String getInsuranceInpNo() {
		return this.insuranceInpNo;
	}

	public void setInsuranceInpNo(String insuranceInpNo) {
		this.insuranceInpNo = insuranceInpNo;
	}

	public String getHealthNo() {
		return this.healthNo;
	}

	public void setHealthNo(String healthNo) {
		this.healthNo = healthNo;
	}

	public String getHealthCardNo() {
		return this.healthCardNo;
	}

	public void setHealthCardNo(String healthCardNo) {
		this.healthCardNo = healthCardNo;
	}

	public String getRemoteVisitSign() {
		return this.remoteVisitSign;
	}

	public void setRemoteVisitSign(String remoteVisitSign) {
		this.remoteVisitSign = remoteVisitSign;
	}

	public String getOtherInpNo() {
		return this.otherInpNo;
	}

	public void setOtherInpNo(String otherInpNo) {
		this.otherInpNo = otherInpNo;
	}

	public String getDoubleSign() {
		return this.doubleSign;
	}

	public void setDoubleSign(String doubleSign) {
		this.doubleSign = doubleSign;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getAdmDiagnoseName() {
		return this.admDiagnoseName;
	}

	public void setAdmDiagnoseName(String admDiagnoseName) {
		this.admDiagnoseName = admDiagnoseName;
	}

	public String getDischargeDiagnoseName() {
		return this.dischargeDiagnoseName;
	}

	public void setDischargeDiagnoseName(String dischargeDiagnoseName) {
		this.dischargeDiagnoseName = dischargeDiagnoseName;
	}

	public String getLockSign() {
		return this.lockSign;
	}

	public void setLockSign(String lockSign) {
		this.lockSign = lockSign;
	}

	public String getSettleType() {
		return this.settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

	public String getVerifyRemark() {
		return this.verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public Date getVerifyDate() {
		return this.verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getVerifier() {
		return this.verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public Double getPrepayments1() {
		return this.prepayments1;
	}

	public void setPrepayments1(Double prepayments1) {
		this.prepayments1 = prepayments1;
	}

	public Double getPrepayments2() {
		return this.prepayments2;
	}

	public void setPrepayments2(Double prepayments2) {
		this.prepayments2 = prepayments2;
	}

	public String getTransferPerson() {
		return this.transferPerson;
	}

	public void setTransferPerson(String transferPerson) {
		this.transferPerson = transferPerson;
	}

	public String getInsuranceTypeTransferSign() {
		return this.insuranceTypeTransferSign;
	}

	public void setInsuranceTypeTransferSign(String insuranceTypeTransferSign) {
		this.insuranceTypeTransferSign = insuranceTypeTransferSign;
	}

	public Double getTotalFundPay() {
		return this.totalFundPay;
	}

	public void setTotalFundPay(Double totalFundPay) {
		this.totalFundPay = totalFundPay;
	}

	public String getAutoVisitId() {
		return this.autoVisitId;
	}

	public void setAutoVisitId(String autoVisitId) {
		this.autoVisitId = autoVisitId;
	}

	public String getInsurancePayExceedSign() {
		return this.insurancePayExceedSign;
	}

	public void setInsurancePayExceedSign(String insurancePayExceedSign) {
		this.insurancePayExceedSign = insurancePayExceedSign;
	}

	public String getInsuranceUpSign() {
		return this.insuranceUpSign;
	}

	public void setInsuranceUpSign(String insuranceUpSign) {
		this.insuranceUpSign = insuranceUpSign;
	}

	public String getAreaCodeBirth() {
		return this.areaCodeBirth;
	}

	public void setAreaCodeBirth(String areaCodeBirth) {
		this.areaCodeBirth = areaCodeBirth;
	}

	public String getBedInsuranceType() {
		return this.bedInsuranceType;
	}

	public void setBedInsuranceType(String bedInsuranceType) {
		this.bedInsuranceType = bedInsuranceType;
	}

	public String getNewbornSign() {
		return this.newbornSign;
	}

	public void setNewbornSign(String newbornSign) {
		this.newbornSign = newbornSign;
	}

	public String getSeriousIllnessSign() {
		return this.seriousIllnessSign;
	}

	public void setSeriousIllnessSign(String seriousIllnessSign) {
		this.seriousIllnessSign = seriousIllnessSign;
	}

	public String getDeathCause() {
		return this.deathCause;
	}

	public void setDeathCause(String deathCause) {
		this.deathCause = deathCause;
	}

	public Date getDeathDate() {
		return this.deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

}