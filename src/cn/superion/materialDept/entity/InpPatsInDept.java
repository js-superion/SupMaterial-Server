package cn.superion.materialDept.entity;

import java.util.Date;

/**
 * InpPatsInDept entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class InpPatsInDept implements java.io.Serializable {

	// Fields

	private InpPatsInDeptId id;
	private String wardCode;
	private Short bedNo;
	private Date admDateTime;
	private Date dischargeDateTime;
	private String deptCode;
	private String deptCodeLend;
	private String groupCode;
	private String doctor;
	private String superior;
	private String director;
	private String nurse;
	private String nursingClass;
	private String patientCondition;
	private Double stature;
	private Double weight;
	private String diagnoseName;
	private String diagnoseCode;
	private String diagnoseClass;
	private Date operatingDate;
	private String bedLendIndicator;
	private String currentStatus;
	private String orderChangeStatus;

	// Constructors

	/** default constructor */
	public InpPatsInDept() {
	}

	/** minimal constructor */
	public InpPatsInDept(InpPatsInDeptId id, String wardCode, Short bedNo) {
		this.id = id;
		this.wardCode = wardCode;
		this.bedNo = bedNo;
	}

	/** full constructor */
	public InpPatsInDept(InpPatsInDeptId id, String wardCode, Short bedNo,
			Date admDateTime, Date dischargeDateTime, String deptCode,
			String deptCodeLend, String groupCode, String doctor,
			String superior, String director, String nurse,
			String nursingClass, String patientCondition, Double stature,
			Double weight, String diagnoseName, String diagnoseCode,
			String diagnoseClass, Date operatingDate, String bedLendIndicator,
			String currentStatus, String orderChangeStatus) {
		this.id = id;
		this.wardCode = wardCode;
		this.bedNo = bedNo;
		this.admDateTime = admDateTime;
		this.dischargeDateTime = dischargeDateTime;
		this.deptCode = deptCode;
		this.deptCodeLend = deptCodeLend;
		this.groupCode = groupCode;
		this.doctor = doctor;
		this.superior = superior;
		this.director = director;
		this.nurse = nurse;
		this.nursingClass = nursingClass;
		this.patientCondition = patientCondition;
		this.stature = stature;
		this.weight = weight;
		this.diagnoseName = diagnoseName;
		this.diagnoseCode = diagnoseCode;
		this.diagnoseClass = diagnoseClass;
		this.operatingDate = operatingDate;
		this.bedLendIndicator = bedLendIndicator;
		this.currentStatus = currentStatus;
		this.orderChangeStatus = orderChangeStatus;
	}

	// Property accessors

	public InpPatsInDeptId getId() {
		return this.id;
	}

	public void setId(InpPatsInDeptId id) {
		this.id = id;
	}

	public String getWardCode() {
		return this.wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public Short getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(Short bedNo) {
		this.bedNo = bedNo;
	}

	public Date getAdmDateTime() {
		return this.admDateTime;
	}

	public void setAdmDateTime(Date admDateTime) {
		this.admDateTime = admDateTime;
	}

	public Date getDischargeDateTime() {
		return this.dischargeDateTime;
	}

	public void setDischargeDateTime(Date dischargeDateTime) {
		this.dischargeDateTime = dischargeDateTime;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptCodeLend() {
		return this.deptCodeLend;
	}

	public void setDeptCodeLend(String deptCodeLend) {
		this.deptCodeLend = deptCodeLend;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getDoctor() {
		return this.doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getSuperior() {
		return this.superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getNurse() {
		return this.nurse;
	}

	public void setNurse(String nurse) {
		this.nurse = nurse;
	}

	public String getNursingClass() {
		return this.nursingClass;
	}

	public void setNursingClass(String nursingClass) {
		this.nursingClass = nursingClass;
	}

	public String getPatientCondition() {
		return this.patientCondition;
	}

	public void setPatientCondition(String patientCondition) {
		this.patientCondition = patientCondition;
	}

	public Double getStature() {
		return this.stature;
	}

	public void setStature(Double stature) {
		this.stature = stature;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getDiagnoseName() {
		return this.diagnoseName;
	}

	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}

	public String getDiagnoseCode() {
		return this.diagnoseCode;
	}

	public void setDiagnoseCode(String diagnoseCode) {
		this.diagnoseCode = diagnoseCode;
	}

	public String getDiagnoseClass() {
		return this.diagnoseClass;
	}

	public void setDiagnoseClass(String diagnoseClass) {
		this.diagnoseClass = diagnoseClass;
	}

	public Date getOperatingDate() {
		return this.operatingDate;
	}

	public void setOperatingDate(Date operatingDate) {
		this.operatingDate = operatingDate;
	}

	public String getBedLendIndicator() {
		return this.bedLendIndicator;
	}

	public void setBedLendIndicator(String bedLendIndicator) {
		this.bedLendIndicator = bedLendIndicator;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getOrderChangeStatus() {
		return this.orderChangeStatus;
	}

	public void setOrderChangeStatus(String orderChangeStatus) {
		this.orderChangeStatus = orderChangeStatus;
	}

}