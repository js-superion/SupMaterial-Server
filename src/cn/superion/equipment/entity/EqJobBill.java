package cn.superion.equipment.entity;

import java.util.Date;

/**
 * EqJobBill entity. @author MyEclipse Persistence Tools
 */


public class EqJobBill implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4344404429757494374L;
	// Fields

	private String autoId;
	private String unitsCode;
	private String jobBillNo;
	private String jobPlanNo;
	private String jobCode;
	private String jobName;
	private String jobType;
	private String objectType;
	private String objectCode;
	private String objectName;
	private String equipmentSpec;
	private String workingShift;
	private Date planStartDate;
	private Date planEndDate;
	private Date factStartDate;
	private Date factEndDate;
	private String usedDept;
	private String jobDept;
	private String remark;
	private String sourceData;
	private String jobSign;
	private Double factFee;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;

	// Constructors

	/** default constructor */
	public EqJobBill() {
	}

	/** minimal constructor */
	public EqJobBill(String autoId, String unitsCode, String jobCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.jobCode = jobCode;
	}

	/** full constructor */
	public EqJobBill(String autoId, String unitsCode, String jobBillNo,
			String jobPlanNo, String jobCode, String jobName, String jobType,
			String objectType, String objectCode, String objectName,
			String equipmentSpec, String workingShift, Date planStartDate,
			Date planEndDate, Date factStartDate, Date factEndDate,
			String usedDept, String jobDept, String remark, String sourceData,
			String jobSign, Double factFee,
			String maker, Date makeDate, String verifier, Date verifyDate,
			String currentStatus) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.jobBillNo = jobBillNo;
		this.jobPlanNo = jobPlanNo;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobType = jobType;
		this.objectType = objectType;
		this.objectCode = objectCode;
		this.objectName = objectName;
		this.equipmentSpec = equipmentSpec;
		this.workingShift = workingShift;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.factStartDate = factStartDate;
		this.factEndDate = factEndDate;
		this.usedDept = usedDept;
		this.jobDept = jobDept;
		this.remark = remark;
		this.sourceData = sourceData;
		this.jobSign = jobSign;
		this.factFee = factFee;
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

	public String getJobBillNo() {
		return this.jobBillNo;
	}

	public void setJobBillNo(String jobBillNo) {
		this.jobBillNo = jobBillNo;
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

	public String getJobSign() {
		return jobSign;
	}

	public void setJobSign(String jobSign) {
		this.jobSign = jobSign;
	}

	public Double getFactFee() {
		return factFee;
	}

	public void setFactFee(Double factFee) {
		this.factFee = factFee;
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

	public String getEquipmentSpec() {
		return this.equipmentSpec;
	}

	public void setEquipmentSpec(String equipmentSpec) {
		this.equipmentSpec = equipmentSpec;
	}

	public String getWorkingShift() {
		return this.workingShift;
	}

	public void setWorkingShift(String workingShift) {
		this.workingShift = workingShift;
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

	public Date getFactStartDate() {
		return this.factStartDate;
	}

	public void setFactStartDate(Date factStartDate) {
		this.factStartDate = factStartDate;
	}

	public Date getFactEndDate() {
		return this.factEndDate;
	}

	public void setFactEndDate(Date factEndDate) {
		this.factEndDate = factEndDate;
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