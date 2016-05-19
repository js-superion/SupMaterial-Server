package cn.superion.cssd.entity;

/**
 * CssdAntisepsisDict entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CssdAntisepsisDict implements java.io.Serializable {

	// Fields 
	
	

	private String antisepsisCode;
	private String antisepsisName;
	private Byte serialNo;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public CssdAntisepsisDict() {
	}

	/** minimal constructor */
	public CssdAntisepsisDict(String antisepsisCode) {
		this.antisepsisCode = antisepsisCode;
	}

	/** full constructor */
	public CssdAntisepsisDict(String antisepsisCode, String antisepsisName,
			Byte serialNo, String phoInputCode, String fiveInputCode) {
		this.antisepsisCode = antisepsisCode;
		this.antisepsisName = antisepsisName;
		this.serialNo = serialNo;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getAntisepsisCode() {
		return this.antisepsisCode;
	}

	public void setAntisepsisCode(String antisepsisCode) {
		this.antisepsisCode = antisepsisCode;
	}

	public String getAntisepsisName() {
		return this.antisepsisName;
	}

	public void setAntisepsisName(String antisepsisName) {
		this.antisepsisName = antisepsisName;
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