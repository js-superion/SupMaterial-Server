package cn.superion.cssd.entity;

import java.util.Date;

/**
 * CssdCheckMaster entity. @author MyEclipse Persistence Tools
 */

public class CssdCheckMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -679524857786900871L;
	private String autoId;
	private String unitsCode;
	private String checkMotif;
	private String checkContent;
	private String checkResult;
	private String deptCode;
	private Date checkDate;
	private String createPerson;
	private Date createDate;
	private String verifier;
	private Date verifyDate;
	private String currentStuats;

	// Constructors

	/** default constructor */
	public CssdCheckMaster() {
	}

	/** minimal constructor */
	public CssdCheckMaster(String autoId, String unitsCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
	}

	/** full constructor */
	public CssdCheckMaster(String autoId, String unitsCode, String checkMotif,
			String checkContent, String checkResult, String deptCode,
			Date checkDate, String createPerson, Date createDate,
			String verifier, Date verifyDate, String currentStuats) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.checkMotif = checkMotif;
		this.checkContent = checkContent;
		this.checkResult = checkResult;
		this.deptCode = deptCode;
		this.checkDate = checkDate;
		this.createPerson = createPerson;
		this.createDate = createDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStuats = currentStuats;
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

	public String getCheckMotif() {
		return this.checkMotif;
	}

	public void setCheckMotif(String checkMotif) {
		this.checkMotif = checkMotif;
	}

	public String getCheckContent() {
		return this.checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {		
		this.checkDate = checkDate;
	}

	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		if(this.createDate==null){
		   this.createDate = createDate;
		}
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

	public String getCurrentStuats() {
		return this.currentStuats;
	}

	public void setCurrentStuats(String currentStuats) {
		this.currentStuats = currentStuats;
	}

}