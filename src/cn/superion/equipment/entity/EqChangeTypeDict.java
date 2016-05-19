package cn.superion.equipment.entity;

/**
 * EqChangeTypeDict entity. @author MyEclipse Persistence Tools
 */

public class EqChangeTypeDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7095200359156887479L;
	private String typeCode;
	private String typeName;
	private String content;
	private String remark;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqChangeTypeDict() {
	}

	/** minimal constructor */
	public EqChangeTypeDict(String typeCode) {
		this.typeCode = typeCode;
	}

	/** full constructor */
	public EqChangeTypeDict(String typeCode, String typeName, String content,
			String remark, String phoInputCode, String fiveInputCode) {
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.content = content;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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