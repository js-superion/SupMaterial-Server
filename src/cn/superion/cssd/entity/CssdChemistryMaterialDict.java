package cn.superion.cssd.entity;

/**
 * CssdChemistryMaterialDict entity. @author MyEclipse Persistence Tools
 */

public class CssdChemistryMaterialDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5385423082348932057L;
	private String chemistryCode;
	private String chemistryName;
	private Byte serialNo;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public CssdChemistryMaterialDict() {
	}

	/** minimal constructor */
	public CssdChemistryMaterialDict(String chemistryCode) {
		this.chemistryCode = chemistryCode;
	}

	/** full constructor */
	public CssdChemistryMaterialDict(String chemistryCode,
			String chemistryName, Byte serialNo, String phoInputCode,
			String fiveInputCode) {
		this.chemistryCode = chemistryCode;
		this.chemistryName = chemistryName;
		this.serialNo = serialNo;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getChemistryCode() {
		return this.chemistryCode;
	}

	public void setChemistryCode(String chemistryCode) {
		this.chemistryCode = chemistryCode;
	}

	public String getChemistryName() {
		return this.chemistryName;
	}

	public void setChemistryName(String chemistryName) {
		this.chemistryName = chemistryName;
	}

	public Byte getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Byte serialNo) {
		this.serialNo = serialNo;
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