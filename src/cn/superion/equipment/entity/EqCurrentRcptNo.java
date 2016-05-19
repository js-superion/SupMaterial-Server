package cn.superion.equipment.entity;

/**
 * EqCurrentRcptNo entity. @author MyEclipse Persistence Tools
 */

public class EqCurrentRcptNo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4504284878633115213L;
	// Fields

	private String autoId;
	private String unitsCode;
	private String rcptFlag;
	private String rcptType;
	private String typeDate;
	private String currentNo;

	// Constructors

	/** default constructor */
	public EqCurrentRcptNo() {
	}
	
	public EqCurrentRcptNo(String unitsCode, String rcptFlag,
			String rcptType, String typeDate, String currentNo) {
		this.unitsCode = unitsCode;
		this.rcptFlag = rcptFlag;
		this.rcptType = rcptType;
		this.typeDate = typeDate;
		this.currentNo = currentNo;
	}

	/** full constructor */
	public EqCurrentRcptNo(String autoId, String unitsCode, String rcptFlag,
			String rcptType, String typeDate, String currentNo) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.rcptFlag = rcptFlag;
		this.rcptType = rcptType;
		this.typeDate = typeDate;
		this.currentNo = currentNo;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getRcptFlag() {
		return this.rcptFlag;
	}

	public void setRcptFlag(String rcptFlag) {
		this.rcptFlag = rcptFlag;
	}

	public String getRcptType() {
		return this.rcptType;
	}

	public void setRcptType(String rcptType) {
		this.rcptType = rcptType;
	}

	public String getTypeDate() {
		return this.typeDate;
	}

	public void setTypeDate(String typeDate) {
		this.typeDate = typeDate;
	}

	public String getCurrentNo() {
		return this.currentNo;
	}

	public void setCurrentNo(String currentNo) {
		this.currentNo = currentNo;
	}

}