package cn.superion.equipment.entity;

/**
 * EqJobPlanFault entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class EqJobPlanFault implements java.io.Serializable {

	// Fields
	//计划的autoId
	private String autoId;
	//对应故障明细记录的autoId
	private String faultNo;
	//对应故障明细记录的serialNo
	private Short faultSerialNo;
	

	// Constructors

	/** default constructor */
	public EqJobPlanFault() {
	}


	// Property accessors

	public String getFaultNo() {
		return this.faultNo;
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


	public void setFaultNo(String faultNo) {
		this.faultNo = faultNo;
	}

}