package cn.superion.equipment.entity;

/**
 * EqJobTypeDict entity. @author MyEclipse Persistence Tools
 */

public class EqJobTypeDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -566605900848727657L;
	private String typeCode;
	private String typeName;
	private String remark;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqJobTypeDict() {
	}

	/** minimal constructor */
	public EqJobTypeDict(String typeCode) {
		this.typeCode = typeCode;
	}

	/** full constructor */
	public EqJobTypeDict(String typeCode, String typeName, String remark,
			String phoInputCode, String fiveInputCode) {
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.remark = remark;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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