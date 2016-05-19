package cn.superion.equipment.entity;

/**
 * EqPositionDict entity. @author MyEclipse Persistence Tools
 */

public class EqPositionDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1063336332471899443L;
	private String positionCode;
	private String positionName;
	private String jobDept;
	private Double systemTime;
	private String remark;
	private String codeLevel;
	private String parentCode;
	private String endSign;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqPositionDict() {
	}

	/** minimal constructor */
	public EqPositionDict(String positionCode) {
		this.positionCode = positionCode;
	}

	/** full constructor */
	public EqPositionDict(String positionCode, String positionName,
			String jobDept, Double systemTime, String remark, String codeLevel,
			String parentCode, String endSign, String phoInputCode,
			String fiveInputCode) {
		this.positionCode = positionCode;
		this.positionName = positionName;
		this.jobDept = jobDept;
		this.systemTime = systemTime;
		this.remark = remark;
		this.codeLevel = codeLevel;
		this.parentCode = parentCode;
		this.endSign = endSign;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getPositionCode() {
		return this.positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getJobDept() {
		return this.jobDept;
	}

	public void setJobDept(String jobDept) {
		this.jobDept = jobDept;
	}

	public Double getSystemTime() {
		return this.systemTime;
	}

	public void setSystemTime(Double systemTime) {
		this.systemTime = systemTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCodeLevel() {
		return this.codeLevel;
	}

	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getEndSign() {
		return this.endSign;
	}

	public void setEndSign(String endSign) {
		this.endSign = endSign;
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