package cn.superion.material.entity;

import java.util.Date;

/**
 * MaterialChangePriceMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialChangePriceMaster implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private String autoId;
	private String unitsCode;
	private String salerCode;
	private String salerName;
	public String getSalerName() {
		return salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}

	public String getSalerCode() {
		return salerCode;
	}

	public void setSalerCode(String salerCode) {
		this.salerCode = salerCode;
	}

	private String billNo; 
	private Date billDate;
	private String changeReason;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;

	// Constructors

	/** default constructor */
	public MaterialChangePriceMaster() {
	}

	/** minimal constructor */
	public MaterialChangePriceMaster(String autoId, String unitsCode,
			String billNo) {
		
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
	}

	/** full constructor */
	public MaterialChangePriceMaster(String autoId, String unitsCode,String salerCode,String salerName,
			String billNo, Date billDate, String changeReason,
			String remark, String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.salerCode = salerCode;
		this.salerName = salerName;
		this.billNo = billNo;
		this.billDate = billDate;
		this.changeReason = changeReason;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
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

	public String getChangeReason() {
		return this.changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
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

}