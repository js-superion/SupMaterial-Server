package cn.superion.equipment.entity;

import java.util.Date;

/**
 * EqRunDetail entity. @author MyEclipse Persistence Tools
 */

public class EqRunDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8003732958283636941L;
	private String autoId;
	private Short serialNo;
	private String objectType;
	private String objectCode;
	private String objectName;
	private String usedDept;
	private Date startDate;
	private Date endDate;
	private Double runTime;
	private Double systemTime;
	private String runStatus;

	// Constructors

	/** default constructor */
	public EqRunDetail() {
	}

	// Property accessors

	public String getObjectType() {
		return this.objectType;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public Short getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
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

	public String getUsedDept() {
		return this.usedDept;
	}

	public void setUsedDept(String usedDept) {
		this.usedDept = usedDept;
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

	public Double getRunTime() {
		return this.runTime;
	}

	public void setRunTime(Double runTime) {
		this.runTime = runTime;
	}

	public String getRunStatus() {
		return this.runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public Double getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Double systemTime) {
		this.systemTime = systemTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((objectCode == null) ? 0 : objectCode.hashCode());
		result = prime * result
				+ ((objectName == null) ? 0 : objectName.hashCode());
		result = prime * result
				+ ((objectType == null) ? 0 : objectType.hashCode());
		result = prime * result
				+ ((runStatus == null) ? 0 : runStatus.hashCode());
		result = prime * result + ((runTime == null) ? 0 : runTime.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((systemTime == null) ? 0 : systemTime.hashCode());
		result = prime * result
				+ ((usedDept == null) ? 0 : usedDept.hashCode());
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
		EqRunDetail other = (EqRunDetail) obj;
		if (autoId == null) {
			if (other.autoId != null)
				return false;
		} else if (!autoId.equals(other.autoId))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (objectCode == null) {
			if (other.objectCode != null)
				return false;
		} else if (!objectCode.equals(other.objectCode))
			return false;
		if (objectName == null) {
			if (other.objectName != null)
				return false;
		} else if (!objectName.equals(other.objectName))
			return false;
		if (objectType == null) {
			if (other.objectType != null)
				return false;
		} else if (!objectType.equals(other.objectType))
			return false;
		if (runStatus == null) {
			if (other.runStatus != null)
				return false;
		} else if (!runStatus.equals(other.runStatus))
			return false;
		if (runTime == null) {
			if (other.runTime != null)
				return false;
		} else if (!runTime.equals(other.runTime))
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
		if (systemTime == null) {
			if (other.systemTime != null)
				return false;
		} else if (!systemTime.equals(other.systemTime))
			return false;
		if (usedDept == null) {
			if (other.usedDept != null)
				return false;
		} else if (!usedDept.equals(other.usedDept))
			return false;
		return true;
	}

}