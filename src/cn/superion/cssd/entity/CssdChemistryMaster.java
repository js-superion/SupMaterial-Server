package cn.superion.cssd.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * CssdChemistryMaster entity. @author MyEclipse Persistence Tools
 */

public class CssdChemistryMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5380404595644711900L;
	private String autoId;
	private String unitsCode;
	private String billNo;
	private Date billDate;
	private String chemistryType;
	private String chemistryCode;
	private String chemistryName;
	private Double press;
	private Double temperature;
	private Double time;
	private String checkResult;
	private String remark;
	private String operator;
	private Date operateDate;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	@SuppressWarnings("unused")
	private List<Map<String, Object>> pics;

	// Constructors

	/** default constructor */
	public CssdChemistryMaster() {
	}

	/** minimal constructor */
	public CssdChemistryMaster(String autoId, String unitsCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
	}

	/** full constructor */
	public CssdChemistryMaster(String autoId, String unitsCode, String billNo,
			Date billDate, String chemistryType,String chemistryCode, String chemistryName,
			Double press, Double temperature, Double time, String checkResult,
			String remark, String operator, Date operateDate,
			String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.chemistryType=chemistryType;
		this.chemistryCode = chemistryCode;
		this.chemistryName = chemistryName;
		this.press = press;
		this.temperature = temperature;
		this.time = time;
		this.checkResult = checkResult;
		this.remark = remark;
		this.operator = operator;
		this.operateDate = operateDate;
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
	public String getChemistryType() {
		return this.chemistryType;
	}

	public void setChemistryType(String chemistryType) {
		this.chemistryType = chemistryType;
	}
	public String getChemistryCode() {
		return this.chemistryCode;
	}

	public void setChemistryCode(String chemistryCode) {
		this.chemistryCode = chemistryCode;
	}

	public String getChemistryName() {
		return this.chemistryName;
	}

	public void setChemistryName(String chemistryName) {
		this.chemistryName = chemistryName;
	}

	public Double getPress() {
		return this.press;
	}

	public void setPress(Double press) {
		this.press = press;
	}

	public Double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getTime() {
		return this.time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public String getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
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

	public List<Map<String, Object>> getPics() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPics(List<Map<String, Object>> pics) {
		this.pics = pics;
	}

}