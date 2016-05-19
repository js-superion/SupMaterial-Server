package cn.superion.cssd.entity;

import java.util.Date;

/**
 * CssdRetrieveMaster entity. @author MyEclipse Persistence Tools
 */

public class CssdRetrieveMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8891848638533137681L;
	private String autoId;
	private String unitsCode;
	private String billNo;
	private Date billDate;
	private String deptCode;
	private String personId;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private String rejectAutoId;

	// Constructors

	/** default constructor */
	public CssdRetrieveMaster() {
	}

	/** minimal constructor */
	public CssdRetrieveMaster(String autoId, String unitsCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
	}

	/** full constructor */
	public CssdRetrieveMaster(String autoId, String unitsCode, String billNo,
			Date billDate, String deptCode, String personId,
			String remark, String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus, String rejectAutoId) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.deptCode = deptCode;
		this.personId = personId;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
		this.rejectAutoId = rejectAutoId;
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

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Date getMakeDate() {
		return this.makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getVerifier() {
		return this.verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public Date getVerifyDate() {
		return this.verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getRejectAutoId() {
		return this.rejectAutoId;
	}

	public void setRejectAutoId(String rejectAutoId) {
		this.rejectAutoId = rejectAutoId;
	}

}