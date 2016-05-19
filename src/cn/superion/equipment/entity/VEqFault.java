package cn.superion.equipment.entity;

import java.util.Date;

/**
 * VEqFaultId entity. @author MyEclipse Persistence Tools
 */

public class VEqFault implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4823108358083681306L;
	private String autoId;
	private String unitsCode;
	private String billNo;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private Short serialNo;
	private String equipmentCode;
	private String equipmentName;
	private String equipmentSpec;
	private String equipmentClass;
	private String equipmentType;
	private String usedDept;
	private String positionCode;
	private String workingShift;
	private Date startDate;
	private Date endDate;
	private String faultType;
	private String faultReason;
	private Date requireFinishDate;
	private String jobCondition;
	private String jobStatus;
	private String createJobPlanSign;
	private String jobCode;
	private String jobName;
	private String jobPlanAutoId;
	private String jobBillAutoId;
	private String operateResult;
	private String operateInfo;

	// Constructors

	/** default constructor */
	public VEqFault() {
	}

	/** minimal constructor */
	public VEqFault(String autoId, String unitsCode, Short serialNo) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.serialNo = serialNo;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
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

	public String getEquipmentClass() {
		return this.equipmentClass;
	}

	public void setEquipmentClass(String equipmentClass) {
		this.equipmentClass = equipmentClass;
	}

	public String getEquipmentType() {
		return this.equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getUsedDept() {
		return this.usedDept;
	}

	public void setUsedDept(String usedDept) {
		this.usedDept = usedDept;
	}

	public String getPositionCode() {
		return this.positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getWorkingShift() {
		return this.workingShift;
	}

	public void setWorkingShift(String workingShift) {
		this.workingShift = workingShift;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFaultType() {
		return this.faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getFaultReason() {
		return this.faultReason;
	}

	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}

	public Date getRequireFinishDate() {
		return this.requireFinishDate;
	}

	public void setRequireFinishDate(Date requireFinishDate) {
		this.requireFinishDate = requireFinishDate;
	}

	public String getJobCondition() {
		return this.jobCondition;
	}

	public void setJobCondition(String jobCondition) {
		this.jobCondition = jobCondition;
	}

	public String getJobStatus() {
		return this.jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCreateJobPlanSign() {
		return this.createJobPlanSign;
	}

	public void setCreateJobPlanSign(String createJobPlanSign) {
		this.createJobPlanSign = createJobPlanSign;
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

	public String getJobPlanAutoId() {
		return jobPlanAutoId;
	}

	public void setJobPlanAutoId(String jobPlanAutoId) {
		this.jobPlanAutoId = jobPlanAutoId;
	}

	public String getJobBillAutoId() {
		return jobBillAutoId;
	}

	public void setJobBillAutoId(String jobBillAutoId) {
		this.jobBillAutoId = jobBillAutoId;
	}

	public String getOperateResult() {
		return this.operateResult;
	}

	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}

	public String getOperateInfo() {
		return this.operateInfo;
	}

	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime
				* result
				+ ((createJobPlanSign == null) ? 0 : createJobPlanSign
						.hashCode());
		result = prime * result
				+ ((currentStatus == null) ? 0 : currentStatus.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((equipmentClass == null) ? 0 : equipmentClass.hashCode());
		result = prime * result
				+ ((equipmentCode == null) ? 0 : equipmentCode.hashCode());
		result = prime * result
				+ ((equipmentName == null) ? 0 : equipmentName.hashCode());
		result = prime * result
				+ ((equipmentSpec == null) ? 0 : equipmentSpec.hashCode());
		result = prime * result
				+ ((equipmentType == null) ? 0 : equipmentType.hashCode());
		result = prime * result
				+ ((faultReason == null) ? 0 : faultReason.hashCode());
		result = prime * result
				+ ((faultType == null) ? 0 : faultType.hashCode());
		result = prime * result
				+ ((jobBillAutoId == null) ? 0 : jobBillAutoId.hashCode());
		result = prime * result + ((jobCode == null) ? 0 : jobCode.hashCode());
		result = prime * result
				+ ((jobCondition == null) ? 0 : jobCondition.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		result = prime * result
				+ ((jobPlanAutoId == null) ? 0 : jobPlanAutoId.hashCode());
		result = prime * result
				+ ((jobStatus == null) ? 0 : jobStatus.hashCode());
		result = prime * result
				+ ((makeDate == null) ? 0 : makeDate.hashCode());
		result = prime * result + ((maker == null) ? 0 : maker.hashCode());
		result = prime * result
				+ ((operateInfo == null) ? 0 : operateInfo.hashCode());
		result = prime * result
				+ ((operateResult == null) ? 0 : operateResult.hashCode());
		result = prime * result
				+ ((positionCode == null) ? 0 : positionCode.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime
				* result
				+ ((requireFinishDate == null) ? 0 : requireFinishDate
						.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((unitsCode == null) ? 0 : unitsCode.hashCode());
		result = prime * result
				+ ((usedDept == null) ? 0 : usedDept.hashCode());
		result = prime * result
				+ ((verifier == null) ? 0 : verifier.hashCode());
		result = prime * result
				+ ((verifyDate == null) ? 0 : verifyDate.hashCode());
		result = prime * result
				+ ((workingShift == null) ? 0 : workingShift.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VEqFault other = (VEqFault) obj;
		if (autoId == null) {
			if (other.autoId != null)
				return false;
		} else if (!autoId.equals(other.autoId))
			return false;
		if (billNo == null) {
			if (other.billNo != null)
				return false;
		} else if (!billNo.equals(other.billNo))
			return false;
		if (createJobPlanSign == null) {
			if (other.createJobPlanSign != null)
				return false;
		} else if (!createJobPlanSign.equals(other.createJobPlanSign))
			return false;
		if (currentStatus == null) {
			if (other.currentStatus != null)
				return false;
		} else if (!currentStatus.equals(other.currentStatus))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (equipmentClass == null) {
			if (other.equipmentClass != null)
				return false;
		} else if (!equipmentClass.equals(other.equipmentClass))
			return false;
		if (equipmentCode == null) {
			if (other.equipmentCode != null)
				return false;
		} else if (!equipmentCode.equals(other.equipmentCode))
			return false;
		if (equipmentName == null) {
			if (other.equipmentName != null)
				return false;
		} else if (!equipmentName.equals(other.equipmentName))
			return false;
		if (equipmentSpec == null) {
			if (other.equipmentSpec != null)
				return false;
		} else if (!equipmentSpec.equals(other.equipmentSpec))
			return false;
		if (equipmentType == null) {
			if (other.equipmentType != null)
				return false;
		} else if (!equipmentType.equals(other.equipmentType))
			return false;
		if (faultReason == null) {
			if (other.faultReason != null)
				return false;
		} else if (!faultReason.equals(other.faultReason))
			return false;
		if (faultType == null) {
			if (other.faultType != null)
				return false;
		} else if (!faultType.equals(other.faultType))
			return false;
		if (jobBillAutoId == null) {
			if (other.jobBillAutoId != null)
				return false;
		} else if (!jobBillAutoId.equals(other.jobBillAutoId))
			return false;
		if (jobCode == null) {
			if (other.jobCode != null)
				return false;
		} else if (!jobCode.equals(other.jobCode))
			return false;
		if (jobCondition == null) {
			if (other.jobCondition != null)
				return false;
		} else if (!jobCondition.equals(other.jobCondition))
			return false;
		if (jobName == null) {
			if (other.jobName != null)
				return false;
		} else if (!jobName.equals(other.jobName))
			return false;
		if (jobPlanAutoId == null) {
			if (other.jobPlanAutoId != null)
				return false;
		} else if (!jobPlanAutoId.equals(other.jobPlanAutoId))
			return false;
		if (jobStatus == null) {
			if (other.jobStatus != null)
				return false;
		} else if (!jobStatus.equals(other.jobStatus))
			return false;
		if (makeDate == null) {
			if (other.makeDate != null)
				return false;
		} else if (!makeDate.equals(other.makeDate))
			return false;
		if (maker == null) {
			if (other.maker != null)
				return false;
		} else if (!maker.equals(other.maker))
			return false;
		if (operateInfo == null) {
			if (other.operateInfo != null)
				return false;
		} else if (!operateInfo.equals(other.operateInfo))
			return false;
		if (operateResult == null) {
			if (other.operateResult != null)
				return false;
		} else if (!operateResult.equals(other.operateResult))
			return false;
		if (positionCode == null) {
			if (other.positionCode != null)
				return false;
		} else if (!positionCode.equals(other.positionCode))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (requireFinishDate == null) {
			if (other.requireFinishDate != null)
				return false;
		} else if (!requireFinishDate.equals(other.requireFinishDate))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (unitsCode == null) {
			if (other.unitsCode != null)
				return false;
		} else if (!unitsCode.equals(other.unitsCode))
			return false;
		if (usedDept == null) {
			if (other.usedDept != null)
				return false;
		} else if (!usedDept.equals(other.usedDept))
			return false;
		if (verifier == null) {
			if (other.verifier != null)
				return false;
		} else if (!verifier.equals(other.verifier))
			return false;
		if (verifyDate == null) {
			if (other.verifyDate != null)
				return false;
		} else if (!verifyDate.equals(other.verifyDate))
			return false;
		if (workingShift == null) {
			if (other.workingShift != null)
				return false;
		} else if (!workingShift.equals(other.workingShift))
			return false;
		return true;
	}
}