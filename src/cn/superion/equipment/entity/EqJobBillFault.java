package cn.superion.equipment.entity;

/**
 * EqJobBillFault entity. @author MyEclipse Persistence Tools
 */

public class EqJobBillFault implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1336875137141583748L;
	private String autoId;
	private String faultNo;
	private Short faultSerialNo;
	

	// Constructors

	/** default constructor */
	public EqJobBillFault() {
	}
	
	

	// Property accessors

	public EqJobBillFault(String autoId, String faultNo, Short faultSerialNo) {
		super();
		this.autoId = autoId;
		this.faultNo = faultNo;
		this.faultSerialNo = faultSerialNo;
	}



	public String getAutoId() {
		return autoId;
	}



	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}



	public Short getFaultSerialNo() {
		return faultSerialNo;
	}



	public void setFaultSerialNo(Short faultSerialNo) {
		this.faultSerialNo = faultSerialNo;
	}



	public String getFaultNo() {
		return this.faultNo;
	}

	public void setFaultNo(String faultNo) {
		this.faultNo = faultNo;
	}

}