package cn.superion.cssd.entity;

import java.util.Date;

/**
 * CssdPackageMaster entity. @author MyEclipse Persistence Tools
 */

public class CssdPackageMaster implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6369676727780790577L;
	// Fields

	private String autoId;
	private String unitsCode;
	private String billNo;
	private Date billDate;
	
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	//扩展
	//过滤回收物品包物资的起始日期，终止日期
	private Date beginWashDate;
	private Date endWashDate;
	// Constructors

	public Date getEndWashDate() {
		return endWashDate;
	}

	public Date getBeginWashDate() {
		return beginWashDate;
	}

	public void setBeginWashDate(Date beginWashDate) {
		this.beginWashDate = beginWashDate;
	}

	public void setEndWashDate(Date endWashDate) {
		this.endWashDate = endWashDate;
	}

	/** default constructor */
	public CssdPackageMaster() {
	}

	/** minimal constructor */
	public CssdPackageMaster(String autoId, String unitsCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
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
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

}