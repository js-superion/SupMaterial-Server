package cn.superion.cssd.entity;

/**
 * CssdPackageModeDict entity. @author MyEclipse Persistence Tools
 */

public class CssdPackageModeDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 797014014702901407L;
	private String modeCode;
	private String modeName;
	private Byte serialNo;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public CssdPackageModeDict() {
	}

	/** minimal constructor */
	public CssdPackageModeDict(String modeCode) {
		this.modeCode = modeCode;
	}

	/** full constructor */
	public CssdPackageModeDict(String modeCode, String modeName,
			Byte serialNo, String phoInputCode, String fiveInputCode) {
		this.modeCode = modeCode;
		this.modeName = modeName;
		this.serialNo = serialNo;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getModeCode() {
		return this.modeCode;
	}

	public void setModeCode(String modeCode) {
		this.modeCode = modeCode;
	}

	public String getModeName() {
		return this.modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
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