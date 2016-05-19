package cn.superion.cssd.entity;

/**
 * CssdSpecimenDict entity. @author MyEclipse Persistence Tools
 */

public class CssdSpecimenDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5965249170234410156L;
	private String specimenCode;
	private String specimenName;
	private Byte serialNo;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public CssdSpecimenDict() {
	}

	/** minimal constructor */
	public CssdSpecimenDict(String specimenCode) {
		this.specimenCode = specimenCode;
	}

	/** full constructor */
	public CssdSpecimenDict(String specimenCode, String specimenName,
			Byte serialNo, String phoInputCode, String fiveInputCode) {
		this.specimenCode = specimenCode;
		this.specimenName = specimenName;
		this.serialNo = serialNo;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getSpecimenCode() {
		return this.specimenCode;
	}

	public void setSpecimenCode(String specimenCode) {
		this.specimenCode = specimenCode;
	}

	public String getSpecimenName() {
		return this.specimenName;
	}

	public void setSpecimenName(String specimenName) {
		this.specimenName = specimenName;
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