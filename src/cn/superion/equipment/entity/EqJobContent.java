package cn.superion.equipment.entity;

import java.util.Date;

/**
 * EqJobContent entity. @author MyEclipse Persistence Tools
 */

public class EqJobContent implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8785864196210086467L;
	private String autoId;
	private String unitsCode;
	private String jobCode;
	private String jobName;
	private String jobType;
	private String objectType;
	private String objectCode;
	private String objectName;
	private String equipmentType;
	private Date startDate;
	private String calendar;
	private String timeUnit;
	private Long intervalTime;
	private String exeDays;
	private String remark;
	private String allowPlaySign;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqJobContent() {
	}

	/** minimal constructor */
	public EqJobContent(String autoId, String unitsCode, String jobCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.jobCode = jobCode;
	}

	/** full constructor */
	public EqJobContent(String autoId, String unitsCode, String jobCode,
			String jobName, String jobType, String objectType,
			String objectCode, String objectName, Date startDate,
			String calendar, String timeUnit, Long intervalTime,
			String exeDays, String remark, String allowPlaySign, String maker,
			Date makeDate, String verifier, Date verifyDate,
			String currentStatus) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobType = jobType;
		this.objectType = objectType;
		this.objectCode = objectCode;
		this.objectName = objectName;
		this.startDate = startDate;
		this.calendar = calendar;
		this.timeUnit = timeUnit;
		this.intervalTime = intervalTime;
		this.exeDays = exeDays;
		this.remark = remark;
		this.allowPlaySign = allowPlaySign;
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

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getCalendar() {
		return this.calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public String getTimeUnit() {
		return this.timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public Long getIntervalTime() {
		return this.intervalTime;
	}

	public void setIntervalTime(Long intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getExeDays() {
		return this.exeDays;
	}

	public void setExeDays(String exeDays) {
		this.exeDays = exeDays;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAllowPlaySign() {
		return this.allowPlaySign;
	}

	public void setAllowPlaySign(String allowPlaySign) {
		this.allowPlaySign = allowPlaySign;
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

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	public EqJobPlan buildJobPlan(){
		EqJobPlan plan = new EqJobPlan();
		plan.setUnitsCode(unitsCode);
		plan.setJobCode(jobCode);
		plan.setJobName(jobName);
		plan.setJobType(jobType);
		plan.setObjectType(objectType);
		plan.setObjectCode(objectCode);
		plan.setObjectName(objectName);
		plan.setPlanStatus("1");
		return plan;
	}

	public EqJobBill buildJobBill() {
		EqJobBill bill = new EqJobBill();
		bill.setUnitsCode(unitsCode);
		bill.setJobCode(jobCode);
		bill.setJobName(jobName);
		bill.setJobType(jobType);
		bill.setObjectType(objectType);
		bill.setObjectCode(objectCode);
		bill.setObjectName(objectName);
		return bill;
	}

}