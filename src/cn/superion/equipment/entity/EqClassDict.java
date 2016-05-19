package cn.superion.equipment.entity;

/**
 * EqClassDict entity. @author MyEclipse Persistence Tools
 */

public class EqClassDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2878442067376508508L;
	private String classCode;
	private String className;
	private String remark;
	private String codeLevel;
	private String parentCode;
	private String endSign;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqClassDict() {
	}

	/** minimal constructor */
	public EqClassDict(String classCode) {
		this.classCode = classCode;
	}

	/** full constructor */
	public EqClassDict(String classCode, String className, String remark,
			String codeLevel, String parentCode, String endSign,
			String phoInputCode, String fiveInputCode) {
		this.classCode = classCode;
		this.className = className;
		this.remark = remark;
		this.codeLevel = codeLevel;
		this.parentCode = parentCode;
		this.endSign = endSign;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
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