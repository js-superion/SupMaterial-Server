package cn.superion.equipment.entity;

/**
 * EqMeasurePoint entity. @author MyEclipse Persistence Tools
 */

public class EqMeasurePoint implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4102242670706186215L;
	private String pointCode;
	private String pointName;
	private String objectType;
	private String objectCode;
	private String objectName;
	private String units;
	private Double upperLimit;
	private Double lowerLimit;
	private Double standard;
	private String remark;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqMeasurePoint() {
	}

	/** minimal constructor */
	public EqMeasurePoint(String pointCode) {
		this.pointCode = pointCode;
	}

	/** full constructor */
	public EqMeasurePoint(String pointCode, String pointName,
			String objectType, String objectCode, String objectName,
			String units, Double upperLimit, Double lowerLimit,
			Double standard, String remark, String phoInputCode,
			String fiveInputCode) {
		this.pointCode = pointCode;
		this.pointName = pointName;
		this.objectType = objectType;
		this.objectCode = objectCode;
		this.objectName = objectName;
		this.units = units;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.standard = standard;
		this.remark = remark;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

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

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhoInputCode() {
		return this.phoInputCode;
	}

	public void setPhoInputCode(String phoInputCode) {
		this.phoInputCode = phoInputCode;
	}

	public String getFiveInputCode() {
		return this.fiveInputCode;
	}

	public void setFiveInputCode(String fiveInputCode) {
		this.fiveInputCode = fiveInputCode;
	}

}