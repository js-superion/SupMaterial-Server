package cn.superion.materialDept.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 病人信息
 * @author 曹国魁
 *
 */
public class PatsVisit implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 604035035421375588L;
	private String unitsCode;
	private String patientId;
	private Byte visitId;
	private String inpNo;
	//医疗付款方式
	private String insuranceMode;
	//医疗保险类型
	private String insuranceType;
	//医疗保险号
	private String insuranceNo;
	//医疗卡号
	private String insuranceCardNo;
	//首诊定点医院
	private String firstBookHospital;
	//所属地区
	private String areaCode;
	//统筹地区
	private String areaCodeFound;
	private String patientName;
	private String sex;
	private Date dateOfBirth;
	private String age;
	//未成年人标志
	private String notAgeStatus;
	//婚姻状况
	private String maritalState;
	//职业
	private String occupation;
	//出生地
	private String birthPlace;
	private String nation;
	//国籍
	private String citizenship;
	private String idNo;
	//费别
	private String chargeType;
	//身份
	private String personStatus;
	//特约单位
	private String unitInContract;
	//在职标志
	private String workingStatus;
	//工作单位
	private String serviceAgency;
	//住址（通信地址）
	private String mailingAddress;
	private String zipCode;
	private String homePhone;
	private String mobilePhone;
	private String EMail;
	//联系人信息：联系人姓名
	private String nextOfKinName;
	//联系人与病人关系
	private String relationship;
	//联系电话
	private String nextOfKinPhone;
	//联系地址
	private String nextOfKinAddress;
	//入院方式
	private String admMode;
	//住院目的
	private String admCause;
	//入院日期及时间
	private Date admDateTime;
	//入院主要诊断
	private String admDiagnose;
	//入院主要诊断分类
	private String admDiagnoseClass;
	//入院病情状态
	private String admPatientCondition;
	//护理等级
	private String nursingClass;
	//接诊医生
	private String consultingDoctor;
	//经治医生
	private String doctorInCharge;
	//床号
	private Short bedNoTemp;
	//床号
	private Short bedNo;
	//手术日期
	private Date operatingDate;
	//入院组别
	private String admGroup;
	//入院科别
	private String admDept;
	//入院病区
	private String admWard;
	//出院日期及时间
	private Date dischargeDateTime;
	//出院组别
	private String dischargeGroup;
	//出院科别
	private String dischargeDept;
	//出院病区
	private String dischargeWard;
	//出院主要诊断
	private String dischargeDiagnose;
	//出院诊断分类
	private String dischargeDiagnoseClass;
	//出院状态
	private String dischargePatientCondition;
	//出院方式
	private String dischargeMode;
	//预交金额
	private Double prepayments;
	//预交金余额
	private Double prepaymentsLeft;
	//医生
	private String doctor;
	//当前诊疗组
	private String groupCode;
	//当前科室
	private String deptCode;
	//当前病区
	private String wardCode;
	//诊断名称
	private String diagnoseName;
	//诊断编码
	private String diagnoseCode;
	//诊断分类
	private String diagnoseClass;
	public String getUnitsCode() {
		return unitsCode;
	}
	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public Byte getVisitId() {
		return visitId;
	}
	public void setVisitId(Byte visitId) {
		this.visitId = visitId;
	}
	public String getInpNo() {
		return inpNo;
	}
	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}
	public String getInsuranceMode() {
		return insuranceMode;
	}
	public void setInsuranceMode(String insuranceMode) {
		this.insuranceMode = insuranceMode;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getInsuranceNo() {
		return insuranceNo;
	}
	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	public String getInsuranceCardNo() {
		return insuranceCardNo;
	}
	public void setInsuranceCardNo(String insuranceCardNo) {
		this.insuranceCardNo = insuranceCardNo;
	}
	public String getFirstBookHospital() {
		return firstBookHospital;
	}
	public void setFirstBookHospital(String firstBookHospital) {
		this.firstBookHospital = firstBookHospital;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaCodeFound() {
		return areaCodeFound;
	}
	public void setAreaCodeFound(String areaCodeFound) {
		this.areaCodeFound = areaCodeFound;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNotAgeStatus() {
		return notAgeStatus;
	}
	public void setNotAgeStatus(String notAgeStatus) {
		this.notAgeStatus = notAgeStatus;
	}
	public String getMaritalState() {
		return maritalState;
	}
	public void setMaritalState(String maritalState) {
		this.maritalState = maritalState;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getPersonStatus() {
		return personStatus;
	}
	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}
	public String getUnitInContract() {
		return unitInContract;
	}
	public void setUnitInContract(String unitInContract) {
		this.unitInContract = unitInContract;
	}
	public String getWorkingStatus() {
		return workingStatus;
	}
	public void setWorkingStatus(String workingStatus) {
		this.workingStatus = workingStatus;
	}
	public String getServiceAgency() {
		return serviceAgency;
	}
	public void setServiceAgency(String serviceAgency) {
		this.serviceAgency = serviceAgency;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEMail() {
		return EMail;
	}
	public void setEMail(String eMail) {
		EMail = eMail;
	}
	public String getNextOfKinName() {
		return nextOfKinName;
	}
	public void setNextOfKinName(String nextOfKinName) {
		this.nextOfKinName = nextOfKinName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getNextOfKinPhone() {
		return nextOfKinPhone;
	}
	public void setNextOfKinPhone(String nextOfKinPhone) {
		this.nextOfKinPhone = nextOfKinPhone;
	}
	public String getNextOfKinAddress() {
		return nextOfKinAddress;
	}
	public void setNextOfKinAddress(String nextOfKinAddress) {
		this.nextOfKinAddress = nextOfKinAddress;
	}
	public String getAdmMode() {
		return admMode;
	}
	public void setAdmMode(String admMode) {
		this.admMode = admMode;
	}
	public String getAdmCause() {
		return admCause;
	}
	public void setAdmCause(String admCause) {
		this.admCause = admCause;
	}
	public Date getAdmDateTime() {
		return admDateTime;
	}
	public void setAdmDateTime(Date admDateTime) {
		this.admDateTime = admDateTime;
	}
	public String getAdmDiagnose() {
		return admDiagnose;
	}
	public void setAdmDiagnose(String admDiagnose) {
		this.admDiagnose = admDiagnose;
	}
	public String getAdmDiagnoseClass() {
		return admDiagnoseClass;
	}
	public void setAdmDiagnoseClass(String admDiagnoseClass) {
		this.admDiagnoseClass = admDiagnoseClass;
	}
	public String getAdmPatientCondition() {
		return admPatientCondition;
	}
	public void setAdmPatientCondition(String admPatientCondition) {
		this.admPatientCondition = admPatientCondition;
	}
	public String getNursingClass() {
		return nursingClass;
	}
	public void setNursingClass(String nursingClass) {
		this.nursingClass = nursingClass;
	}
	public String getConsultingDoctor() {
		return consultingDoctor;
	}
	public void setConsultingDoctor(String consultingDoctor) {
		this.consultingDoctor = consultingDoctor;
	}
	public String getDoctorInCharge() {
		return doctorInCharge;
	}
	public void setDoctorInCharge(String doctorInCharge) {
		this.doctorInCharge = doctorInCharge;
	}
	public Short getBedNoTemp() {
		return bedNoTemp;
	}
	public void setBedNoTemp(Short bedNoTemp) {
		this.bedNoTemp = bedNoTemp;
	}
	public Short getBedNo() {
		return bedNo;
	}
	public void setBedNo(Short bedNo) {
		this.bedNo = bedNo;
	}
	public Date getOperatingDate() {
		return operatingDate;
	}
	public void setOperatingDate(Date operatingDate) {
		this.operatingDate = operatingDate;
	}
	public String getAdmGroup() {
		return admGroup;
	}
	public void setAdmGroup(String admGroup) {
		this.admGroup = admGroup;
	}
	public String getAdmDept() {
		return admDept;
	}
	public void setAdmDept(String admDept) {
		this.admDept = admDept;
	}
	public String getAdmWard() {
		return admWard;
	}
	public void setAdmWard(String admWard) {
		this.admWard = admWard;
	}
	public Date getDischargeDateTime() {
		return dischargeDateTime;
	}
	public void setDischargeDateTime(Date dischargeDateTime) {
		this.dischargeDateTime = dischargeDateTime;
	}
	public String getDischargeGroup() {
		return dischargeGroup;
	}
	public void setDischargeGroup(String dischargeGroup) {
		this.dischargeGroup = dischargeGroup;
	}
	public String getDischargeDept() {
		return dischargeDept;
	}
	public void setDischargeDept(String dischargeDept) {
		this.dischargeDept = dischargeDept;
	}
	public String getDischargeWard() {
		return dischargeWard;
	}
	public void setDischargeWard(String dischargeWard) {
		this.dischargeWard = dischargeWard;
	}
	public String getDischargeDiagnose() {
		return dischargeDiagnose;
	}
	public void setDischargeDiagnose(String dischargeDiagnose) {
		this.dischargeDiagnose = dischargeDiagnose;
	}
	public String getDischargeDiagnoseClass() {
		return dischargeDiagnoseClass;
	}
	public void setDischargeDiagnoseClass(String dischargeDiagnoseClass) {
		this.dischargeDiagnoseClass = dischargeDiagnoseClass;
	}
	public String getDischargePatientCondition() {
		return dischargePatientCondition;
	}
	public void setDischargePatientCondition(String dischargePatientCondition) {
		this.dischargePatientCondition = dischargePatientCondition;
	}
	public String getDischargeMode() {
		return dischargeMode;
	}
	public void setDischargeMode(String dischargeMode) {
		this.dischargeMode = dischargeMode;
	}
	public Double getPrepayments() {
		return prepayments;
	}
	public void setPrepayments(Double prepayments) {
		this.prepayments = prepayments;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getDiagnoseName() {
		return diagnoseName;
	}
	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}
	public String getDiagnoseCode() {
		return diagnoseCode;
	}
	public void setDiagnoseCode(String diagnoseCode) {
		this.diagnoseCode = diagnoseCode;
	}
	public String getDiagnoseClass() {
		return diagnoseClass;
	}
	public void setDiagnoseClass(String diagnoseClass) {
		this.diagnoseClass = diagnoseClass;
	}
	public Double getPrepaymentsLeft() {
		return prepaymentsLeft;
	}
	public void setPrepaymentsLeft(Double prepaymentsLeft) {
		this.prepaymentsLeft = prepaymentsLeft;
	}
}
