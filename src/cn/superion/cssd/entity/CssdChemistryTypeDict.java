package cn.superion.cssd.entity;

/**
 * CssdChemistryTypeDict entity. @author MyEclipse Persistence Tools
 */

public class CssdChemistryTypeDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5608091969494779973L;
	private String typeCode;
	private String typeName;
	private Byte serialNo;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public CssdChemistryTypeDict() {
	}

	/** minimal constructor */
	public CssdChemistryTypeDict(String typeCode) {
		this.typeCode = typeCode;
	}

	/** full constructor */
	public CssdChemistryTypeDict(String typeCode, String typeName,
			Byte serialNo, String phoInputCode, String fiveInputCode) {
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.serialNo = serialNo;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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