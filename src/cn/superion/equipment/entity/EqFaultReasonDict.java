package cn.superion.equipment.entity;

/**
 * EqFaultReasonDictId entity. @author MyEclipse Persistence Tools
 */

public class EqFaultReasonDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2089519272217425790L;
	private String reasonCode;
	private String reasonName;
	private String remark;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public EqFaultReasonDict() {
	}

	/** full constructor */
	public EqFaultReasonDict(String reasonCode, String reasonName,
			String remark, String phoInputCode, String fiveInputCode) {
		this.reasonCode = reasonCode;
		this.reasonName = reasonName;
		this.remark = remark;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonName() {
		return this.reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
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