package cn.superion.cssd.entity;

/**
 * CssdSterilizeTypeDict entity. @author MyEclipse Persistence Tools
 */

public class CssdSterilizeTypeDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6959565972989328279L;
	private String sterilizeTypeCod;
	private String sterilizeTypeName;
	private Byte serialNo;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public CssdSterilizeTypeDict() {
	}

	/** minimal constructor */
	public CssdSterilizeTypeDict(String sterilizeTypeCod) {
		this.sterilizeTypeCod = sterilizeTypeCod;
	}

	/** full constructor */
	public CssdSterilizeTypeDict(String sterilizeTypeCod,
			String sterilizeTypeName, Byte serialNo, String phoInputCode,
			String fiveInputCode) {
		this.sterilizeTypeCod = sterilizeTypeCod;
		this.sterilizeTypeName = sterilizeTypeName;
		this.serialNo = serialNo;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getSterilizeTypeCod() {
		return this.sterilizeTypeCod;
	}

	public void setSterilizeTypeCod(String sterilizeTypeCod) {
		this.sterilizeTypeCod = sterilizeTypeCod;
	}

	public String getSterilizeTypeName() {
		return this.sterilizeTypeName;
	}

	public void setSterilizeTypeName(String sterilizeTypeName) {
		this.sterilizeTypeName = sterilizeTypeName;
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