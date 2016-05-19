package cn.superion.equipment.entity;

/**
 * EqUnitDict entity. @author MyEclipse Persistence Tools
 */

public class EqUnitDict implements java.io.Serializable {

	// Fields

	private String unitcode;
	private String unitname;
	private String fiveinputcode;
	private String phoinputcode;

	// Constructors

	/** default constructor */
	public EqUnitDict() {
	}

	/** minimal constructor */
	public EqUnitDict(String unitcode, String unitname) {
		this.unitcode = unitcode;
		this.unitname = unitname;
	}

	/** full constructor */
	public EqUnitDict(String unitcode, String unitname, String fiveinputcode,
			String phoinputcode) {
		this.unitcode = unitcode;
		this.unitname = unitname;
		this.fiveinputcode = fiveinputcode;
		this.phoinputcode = phoinputcode;
	}

	// Property accessors

	public String getUnitcode() {
		return this.unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getUnitname() {
		return this.unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getFiveinputcode() {
		return this.fiveinputcode;
	}

	public void setFiveinputcode(String fiveinputcode) {
		this.fiveinputcode = fiveinputcode;
	}

	public String getPhoinputcode() {
		return this.phoinputcode;
	}

	public void setPhoinputcode(String phoinputcode) {
		this.phoinputcode = phoinputcode;
	}

}