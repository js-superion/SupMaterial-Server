package cn.superion.equipment.entity;

import java.util.Date;

/**
 * EqJobPlan entity. @author MyEclipse Persistence Tools
 */

public class EqJobPlan implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2891713521562690825L;
	private String autoId;
	private String unitsCode;
	private String jobPlanNo;
	private String jobCode;
	private String jobName;
	private String jobType;
	private String objectType;
	private String objectCode;
	private String objectName;
	private String planStatus;
	private Date planStartDate;
	private Date planEndDate;
	private String usedDept;
	private String jobDept;
	private String remark;
	private String sourceData;
	private String planPerson;
	private String jobSign;
	private Double planFee;	
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;

	// Constructors

	/** default constructor */
	public EqJobPlan() {
	}

	/** minimal constructor */
	public EqJobPlan(String autoId, String unitsCode, String jobCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.jobCode = jobCode;
	}

	/** full constructor */
	public EqJobPlan(String autoId, String unitsCode, String jobPlanNo,
			String jobCode, String jobName, String jobType, String objectType,
			String objectCode, String objectName, String planStatus,
			Date planStartDate, Date planEndDate, String usedDept,
			String jobDept, String remark, String sourceData,
			String planPerson, String jobSign, Double planFee, String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.jobPlanNo = jobPlanNo;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobType = jobType;
		this.objectType = objectType;
		this.objectCode = objectCode;
		this.objectName = objectName;
		this.planStatus = planStatus;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.usedDept = usedDept;
		this.jobDept = jobDept;
		this.remark = remark;
		this.sourceData = sourceData;
		this.planPerson = planPerson;
		this.jobSign = jobSign;
		this.planFee = planFee;
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

	public String getJobPlanNo() {
		return this.jobPlanNo;
	}

	public void setJobPlanNo(String jobPlanNo) {
		this.jobPlanNo = jobPlanNo;
	}

	public String getJobCode() {
		return this.jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobType() {
		return this.jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobSign() {
		return jobSign;
	}

	public void setJobSign(String jobSign) {
		this.jobSign = jobSign;
	}

	public Double getPlanFee() {
		return planFee;
	}

	public void setPlanFee(Double planFee) {
		this.planFee = planFee;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectCode() {
		return this.objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getPlanStatus() {
		return this.planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public Date getPlanStartDate() {
		return this.planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return this.planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getUsedDept() {
		return this.usedDept;
	}

	public void setUsedDept(String usedDept) {
		this.usedDept = usedDept;
	}

	public String getJobDept() {
		return this.jobDept;
	}

	public void setJobDept(String jobDept) {
		this.jobDept = jobDept;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSourceData() {
		return this.sourceData;
	}

	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}

	public String getPlanPerson() {
		return this.planPerson;
	}

	public void setPlanPerson(String planPerson) {
		this.planPerson = planPerson;
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

}