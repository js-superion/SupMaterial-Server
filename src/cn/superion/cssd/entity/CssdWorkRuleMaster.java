package cn.superion.cssd.entity;

import java.util.Date;


/**
 * CssdWorkRuleMaster entity. @author MyEclipse Persistence Tools
 */

public class CssdWorkRuleMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5956047816697332449L;
	private String autoId;
	private String unitsCode;
	private String classCode;
	private String fileName;
	private String fileContent;
	private String deptCode;
	private Date fileDate;
	private String createPerson;
	private Date createDate;
	private String verifier;
	private Date verifyDate;
	private String currentStuats;

	// Constructors

	/** default constructor */
	public CssdWorkRuleMaster() {
	}

	/** minimal constructor */
	public CssdWorkRuleMaster(String autoId, String unitsCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
	}

	/** full constructor */
	public CssdWorkRuleMaster(String autoId, String unitsCode,
			String classCode, String fileName, String fileContent,
			String deptCode, Date fileDate, String createPerson,
			Date createDate, String verifier, Date verifyDate,
			String currentStuats) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.classCode = classCode;
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.deptCode = deptCode;
		this.fileDate = fileDate;
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

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Date getFileDate() {
		return this.fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
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