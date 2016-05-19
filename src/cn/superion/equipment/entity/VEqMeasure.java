package cn.superion.equipment.entity;

import java.util.Date;

/**
 * VEqMeasureId entity. @author MyEclipse Persistence Tools
 */

public class VEqMeasure implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1805365966612476857L;
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
	private String pointCode;
	private String pointName;
	private String objectType;
	private String objectCode;
	private String objectName;
	private String equipmentClass;
	private String equipmentType;
	private String units;
	private Double measureValue;
	private Double upperLimit;
	private Double lowerLimit;
	private Double standard;

	// Constructors

	/** default constructor */
	public VEqMeasure() {
	}

	/** minimal constructor */
	public VEqMeasure(String autoId, String unitsCode, Short serialNo) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.serialNo = serialNo;
	}

	/** full constructor */
	public VEqMeasure(String autoId, String unitsCode, String billNo,
			String remark, String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus, Short serialNo,
			String pointCode, String pointName, String objectType,
			String objectCode, String objectName, String equipmentClass,
			String equipmentType, String units, Double measureValue,
			Double upperLimit, Double lowerLimit, Double standard) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
		this.serialNo = serialNo;
		this.pointCode = pointCode;
		this.pointName = pointName;
		this.objectType = objectType;
		this.objectCode = objectCode;
		this.objectName = objectName;
		this.equipmentClass = equipmentClass;
		this.equipmentType = equipmentType;
		this.units = units;
		this.measureValue = measureValue;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.standard = standard;
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

	public String getPointCode() {
		return this.pointCode;
	}

	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}

	public String getPointName() {
		return this.pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
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

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Double getMeasureValue() {
		return this.measureValue;
	}

	public void setMeasureValue(Double measureValue) {
		this.measureValue = measureValue;
	}

	public Double getUpperLimit() {
		return this.upperLimit;
	}

	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Double getLowerLimit() {
		return this.lowerLimit;
	}

	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Double getStandard() {
		return this.standard;
	}

	public void setStandard(Double standard) {
		this.standard = standard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime * result
				+ ((currentStatus == null) ? 0 : currentStatus.hashCode());
		result = prime * result
				+ ((equipmentClass == null) ? 0 : equipmentClass.hashCode());
		result = prime * result
				+ ((equipmentType == null) ? 0 : equipmentType.hashCode());
		result = prime * result
				+ ((lowerLimit == null) ? 0 : lowerLimit.hashCode());
		result = prime * result
				+ ((makeDate == null) ? 0 : makeDate.hashCode());
		result = prime * result + ((maker == null) ? 0 : maker.hashCode());
		result = prime * result
				+ ((measureValue == null) ? 0 : measureValue.hashCode());
		result = prime * result
				+ ((objectCode == null) ? 0 : objectCode.hashCode());
		result = prime * result
				+ ((objectName == null) ? 0 : objectName.hashCode());
		result = prime * result
				+ ((objectType == null) ? 0 : objectType.hashCode());
		result = prime * result
				+ ((pointCode == null) ? 0 : pointCode.hashCode());
		result = prime * result
				+ ((pointName == null) ? 0 : pointName.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		result = prime * result
				+ ((standard == null) ? 0 : standard.hashCode());
		result = prime * result + ((units == null) ? 0 : units.hashCode());
		result = prime * result
				+ ((unitsCode == null) ? 0 : unitsCode.hashCode());
		result = prime * result
				+ ((upperLimit == null) ? 0 : upperLimit.hashCode());
		result = prime * result
				+ ((verifier == null) ? 0 : verifier.hashCode());
		result = prime * result
				+ ((verifyDate == null) ? 0 : verifyDate.hashCode());
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
		VEqMeasure other = (VEqMeasure) obj;
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
		if (currentStatus == null) {
			if (other.currentStatus != null)
				return false;
		} else if (!currentStatus.equals(other.currentStatus))
			return false;
		if (equipmentClass == null) {
			if (other.equipmentClass != null)
				return false;
		} else if (!equipmentClass.equals(other.equipmentClass))
			return false;
		if (equipmentType == null) {
			if (other.equipmentType != null)
				return false;
		} else if (!equipmentType.equals(other.equipmentType))
			return false;
		if (lowerLimit == null) {
			if (other.lowerLimit != null)
				return false;
		} else if (!lowerLimit.equals(other.lowerLimit))
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
		if (measureValue == null) {
			if (other.measureValue != null)
				return false;
		} else if (!measureValue.equals(other.measureValue))
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
		if (pointCode == null) {
			if (other.pointCode != null)
				return false;
		} else if (!pointCode.equals(other.pointCode))
			return false;
		if (pointName == null) {
			if (other.pointName != null)
				return false;
		} else if (!pointName.equals(other.pointName))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		if (standard == null) {
			if (other.standard != null)
				return false;
		} else if (!standard.equals(other.standard))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		if (unitsCode == null) {
			if (other.unitsCode != null)
				return false;
		} else if (!unitsCode.equals(other.unitsCode))
			return false;
		if (upperLimit == null) {
			if (other.upperLimit != null)
				return false;
		} else if (!upperLimit.equals(other.upperLimit))
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
		return true;
	}
}