package cn.superion.equipment.entity;

/**
 * EqRunStatusDict entity. @author MyEclipse Persistence Tools
 */

public class EqRunStatusDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3370677321932841113L;
	private String statusCode;
	private String statusName;
	private String statusAttribute;
	private String remark;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqRunStatusDict() {
	}

	/** minimal constructor */
	public EqRunStatusDict(String statusCode) {
		this.statusCode = statusCode;
	}

	/** full constructor */
	public EqRunStatusDict(String statusCode, String statusName,
			String statusAttribute, String remark, String phoInputCode,
			String fiveInputCode) {
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.statusAttribute = statusAttribute;
		this.remark = remark;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusAttribute() {
		return this.statusAttribute;
	}

	public void setStatusAttribute(String statusAttribute) {
		this.statusAttribute = statusAttribute;
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