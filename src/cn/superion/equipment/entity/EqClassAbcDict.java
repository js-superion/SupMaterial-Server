package cn.superion.equipment.entity;

/**
 * EqClassAbcDict entity. @author MyEclipse Persistence Tools
 */

public class EqClassAbcDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2118225984156423971L;
	private String classCode;
	private String className;
	private String remark;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqClassAbcDict() {
	}

	/** minimal constructor */
	public EqClassAbcDict(String classCode) {
		this.classCode = classCode;
	}

	/** full constructor */
	public EqClassAbcDict(String classCode, String className, String remark,
			String phoInputCode, String fiveInputCode) {
		this.classCode = classCode;
		this.className = className;
		this.remark = remark;
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