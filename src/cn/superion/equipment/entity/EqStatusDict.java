package cn.superion.equipment.entity;

/**
 * EqStatusDict entity. @author MyEclipse Persistence Tools
 */

public class EqStatusDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5968653755173563337L;
	private String statusCode;
	private String statusName;
	private String allowJobSign;
	private String remark;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqStatusDict() {
	}

	/** minimal constructor */
	public EqStatusDict(String statusCode) {
		this.statusCode = statusCode;
	}

	/** full constructor */
	public EqStatusDict(String statusCode, String statusName,
			String allowJobSign, String remark, String phoInputCode,
			String fiveInputCode) {
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.allowJobSign = allowJobSign;
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

	public String getAllowJobSign() {
		return this.allowJobSign;
	}

	public void setAllowJobSign(String allowJobSign) {
		this.allowJobSign = allowJobSign;
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