package cn.superion.material.entity;

/**
 * MaterialAcctCurrentRcptNo entity. @author MyEclipse Persistence Tools
 */

public class MaterialAcctCurrentRcptNo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 496507082288163632L;
	private String autoId;
	private String unitsCode;
	private String rdFlag;
	private String rcptType;
	private String storageCode;
	private String typeDate;
	private String currentNo;

	// Constructors

	/** default constructor */
	public MaterialAcctCurrentRcptNo() {
	}

	/** full constructor */
	public MaterialAcctCurrentRcptNo(String autoId, String unitsCode,
			String rdFlag, String rcptType, String storageCode,String typeDate, String currentNo) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.rdFlag = rdFlag;
		this.rcptType = rcptType;
		this.storageCode = storageCode;
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

	public String getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getRdFlag() {
		return this.rdFlag;
	}

	public void setRdFlag(String rdFlag) {
		this.rdFlag = rdFlag;
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