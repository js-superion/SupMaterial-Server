package cn.superion.cssd.entity;

/**
 * CssdFileClassDict entity. @author MyEclipse Persistence Tools
 */

public class CssdFileClassDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3046775278397620682L;
	private String classCode;
	private String className;
	private String codeLevel;
	private String parentCode;
	private String endSign;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public CssdFileClassDict() {
	}

	/** minimal constructor */
	public CssdFileClassDict(String classCode) {
		this.classCode = classCode;
	}

	/** full constructor */
	public CssdFileClassDict(String classCode, String className,
			String codeLevel, String parentCode, String endSign,
			String phoInputCode, String fiveInputCode) {
		this.classCode = classCode;
		this.className = className;
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