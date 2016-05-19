package cn.superion.material.entity;

import java.sql.Timestamp;

/**
 * CdDeptLimit entity. @author MyEclipse Persistence Tools
 */

public class CdDeptLimit implements java.io.Serializable {

	// Fields

	private String autoId;
	private String unitsCode;
	private String deptCode;
	private String deptName;
	private String years;
	private String season;
	private Double limits;
	private Double addUp;
	private String createPerson;
	private Timestamp createDate;
	private String modifyPerson;
	private Timestamp modifyDate;

	// Constructors

	/** default constructor */
	public CdDeptLimit() {
	}

	/** minimal constructor */
	public CdDeptLimit(String autoId) {
		this.autoId = autoId;
	}

	/** full constructor */
	public CdDeptLimit(String autoId, String unitsCode, String deptCode,
			String deptName, String years, String season, Double limits,
			Double addUp, String createPerson, Timestamp createDate,
			String modifyPerson, Timestamp modifyDate) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.years = years;
		this.season = season;
		this.limits = limits;
		this.addUp = addUp;
		this.createPerson = createPerson;
		this.createDate = createDate;
		this.modifyPerson = modifyPerson;
		this.modifyDate = modifyDate;
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

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getYears() {
		return this.years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getSeason() {
		return this.season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Double getLimits() {
		return this.limits;
	}

	public void setLimits(Double limits) {
		this.limits = limits;
	}

	public Double getAddUp() {
		return this.addUp;
	}

	public void setAddUp(Double addUp) {
		this.addUp = addUp;
	}

	public String getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

}