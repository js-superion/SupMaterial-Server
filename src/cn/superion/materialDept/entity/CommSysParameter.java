package cn.superion.materialDept.entity;

/**
 * AbstractCommSysParameter entity provides the base persistence definition of
 * the CommSysParameter entity. @author MyEclipse Persistence Tools
 */

public abstract class CommSysParameter implements java.io.Serializable {

	// Fields

	private String unitsCode;
	private String menuNo;
	private String paraCode;
	private String paraName;
	private String paraValue;
	private String paraDefault;
	private String paraDescription;

	// Constructors


	/** minimal constructor */
	public CommSysParameter(String unitsCode, String menuNo,
			String paraCode) {
		this.unitsCode = unitsCode;
		this.menuNo = menuNo;
		this.paraCode = paraCode;
	}

	/** full constructor */
	public CommSysParameter(String unitsCode, String menuNo,
			String paraCode, String paraName,
			String paraValue, String paraDefault, String paraDescription) {
		this.unitsCode = unitsCode;
		this.menuNo = menuNo;
		this.paraCode = paraCode;
		this.paraName = paraName;
		this.paraValue = paraValue;
		this.paraDefault = paraDefault;
		this.paraDescription = paraDescription;
	}

	// Property accessors

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getMenuNo() {
		return this.menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getParaCode() {
		return this.paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}

	public String getParaName() {
		return this.paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getParaValue() {
		return this.paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

	public String getParaDefault() {
		return this.paraDefault;
	}

	public void setParaDefault(String paraDefault) {
		this.paraDefault = paraDefault;
	}

	public String getParaDescription() {
		return this.paraDescription;
	}

	public void setParaDescription(String paraDescription) {
		this.paraDescription = paraDescription;
	}

}