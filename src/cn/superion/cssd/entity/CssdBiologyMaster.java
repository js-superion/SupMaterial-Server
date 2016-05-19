package cn.superion.cssd.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * CssdBiologyMaster entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CssdBiologyMaster implements java.io.Serializable {

	// Fields

	private String autoId;
	private String unitsCode;
	private String billNo;
	private Date billDate;
	private Date applyDate;
	private String specimen;
	private String testCause;
	private String reportPerson;
	private Date reportDate;
	private String confirmPerson;
	private Date confirmDate;
	private String currentStatus;
	private String remark;
	private List<Map<String, Object>> pics;
	// Constructors

	/** default constructor */
	public CssdBiologyMaster() {
	}

	/** minimal constructor */
	public CssdBiologyMaster(String autoId, String unitsCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
	}

	/** full constructor */
	public CssdBiologyMaster(String autoId, String unitsCode, String billNo,
			Date billDate, Date applyDate, String specimen,
			String testCause, String reportPerson, Date reportDate,
			String confirmPerson, Date confirmDate, String currentStatus,
			String remark) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.applyDate = applyDate;
		this.specimen = specimen;
		this.testCause = testCause;
		this.reportPerson = reportPerson;
		this.reportDate = reportDate;
		this.confirmPerson = confirmPerson;
		this.confirmDate = confirmDate;
		this.currentStatus = currentStatus;
		this.remark = remark;
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

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getSpecimen() {
		return this.specimen;
	}

	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}

	public String getTestCause() {
		return this.testCause;
	}

	public void setTestCause(String testCause) {
		this.testCause = testCause;
	}

	public String getReportPerson() {
		return this.reportPerson;
	}

	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getConfirmPerson() {
		return this.confirmPerson;
	}

	public void setConfirmPerson(String confirmPerson) {
		this.confirmPerson = confirmPerson;
	}

	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setPics(List<Map<String, Object>> pics) {
		this.pics = pics;
	}

	public List<Map<String, Object>> getPics() {
		return pics;
	}
}