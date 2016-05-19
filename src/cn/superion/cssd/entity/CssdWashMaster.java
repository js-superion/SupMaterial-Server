package cn.superion.cssd.entity;

import java.util.Date;

/**
 * CssdWashMaster entity. @author MyEclipse Persistence Tools
 */

public class CssdWashMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645772239041560813L;
	private String autoId;
	private String unitsCode;
	private String billNo;
	private Date billDate;
	private String equipmentCode;
	private String equipmentName;
	private String antisepsis;
	private String enzymeChroma;
	private String washNo;
	private String washOrder;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	//扩展
	//过滤回收物品包物资的起始日期，终止日期
	private Date beginRetrieveDate;
	private Date endRetrieveDate;

	// Constructors

	/** default constructor */
	public CssdWashMaster() {
	}

	/** minimal constructor */
	public CssdWashMaster(String autoId, String unitsCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
	}

	/** full constructor */
	public CssdWashMaster(String autoId, String unitsCode, String billNo,
			Date billDate, String equipmentCode, String equipmentName,
			String antisepsis, String enzymeChroma, String washNo,String washOrder, String remark,
			String maker, Date makeDate, String verifier,
			Date verifyDate, String currentStatus) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.equipmentCode = equipmentCode;
		this.equipmentName = equipmentName;
		this.antisepsis = antisepsis;
		this.enzymeChroma = enzymeChroma;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
		this.washNo = washNo;
		this.washOrder = washOrder;
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

	public String getEquipmentCode() {
		return this.equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public String getWashNo() {
		return washNo;
	}

	public void setWashNo(String washNo) {
		this.washNo = washNo;
	}

	public String getWashOrder() {
		return washOrder;
	}

	public void setWashOrder(String washOrder) {
		this.washOrder = washOrder;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getAntisepsis() {
		return this.antisepsis;
	}

	public void setAntisepsis(String antisepsis) {
		this.antisepsis = antisepsis;
	}

	public String getEnzymeChroma() {
		return this.enzymeChroma;
	}

	public void setEnzymeChroma(String enzymeChroma) {
		this.enzymeChroma = enzymeChroma;
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

	public Date getBeginRetrieveDate() {
		return beginRetrieveDate;
	}

	public void setBeginRetrieveDate(Date beginRetrieveDate) {
		this.beginRetrieveDate = beginRetrieveDate;
	}

	public Date getEndRetrieveDate() {
		return endRetrieveDate;
	}

	public void setEndRetrieveDate(Date endRetrieveDate) {
		this.endRetrieveDate = endRetrieveDate;
	}

}