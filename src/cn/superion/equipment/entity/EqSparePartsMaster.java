package cn.superion.equipment.entity;

import java.util.Date;

/**
 * EqSparePartsMaster entity. @author MyEclipse Persistence Tools
 */

public class EqSparePartsMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7820902233375001603L;
	private String equipmentTypeCode;
	private String equipmentTypeName;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;

	// Constructors

	/** default constructor */
	public EqSparePartsMaster() {
	}

	/** minimal constructor */
	public EqSparePartsMaster(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}

	/** full constructor */
	public EqSparePartsMaster(String equipmentTypeCode,
			String equipmentTypeName, String remark, String maker,
			Date makeDate, String verifier, Date verifyDate,
			String currentStatus) {
		this.equipmentTypeCode = equipmentTypeCode;
		this.equipmentTypeName = equipmentTypeName;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
	}

	// Property accessors

	public String getEquipmentTypeCode() {
		return this.equipmentTypeCode;
	}

	public void setEquipmentTypeCode(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}

	public String getEquipmentTypeName() {
		return this.equipmentTypeName;
	}

	public void setEquipmentTypeName(String equipmentTypeName) {
		this.equipmentTypeName = equipmentTypeName;
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