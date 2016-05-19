package cn.superion.material.entity;

import java.sql.Timestamp;

/**
 * MaterialSupplierSummaryId entity. @author MyEclipse Persistence Tools
 */

public class MaterialSupplierSummary implements java.io.Serializable {

	// Fields

	private String autoId;
	private String unitsCode;
	private String billNo;
	private String storageCode;
	private String checkSign;
	private Timestamp billDate;
	private String providerCode;
	private String providerName;
	private String contactPerson;
	private String deptCode; //扩展属性，供货单确认模块：作为最后生成出入库时，接受前台生成的科室编码
	
	private Double totalCharge;
	private String personName;
	private Timestamp operatorDate;

	// Constructors

	/** default constructor */
	public MaterialSupplierSummary() {
	}

	/** minimal constructor */
	public MaterialSupplierSummary(String autoId) {
		this.autoId = autoId;
	}

	/** full constructor */
	public MaterialSupplierSummary(String autoId, String unitsCode,String checkSign,
			String billNo, Timestamp billDate, String providerCode,String storageCode,
			String providerName, String contactPerson, Double totalCharge,
			String personName, Timestamp operatorDate) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.storageCode = storageCode;
		this.billDate = billDate;
		this.providerCode = providerCode;
		this.checkSign = checkSign;
		this.providerName = providerName;
		this.contactPerson = contactPerson;
		this.totalCharge = totalCharge;
		this.personName = personName;
		this.operatorDate = operatorDate;
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
	public String getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
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

	public String getCheckSign() {
		return checkSign;
	}

	public void setCheckSign(String checkSign) {
		this.checkSign = checkSign;
	}

	public Timestamp getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Timestamp billDate) {
		this.billDate = billDate;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getProviderCode() {
		return this.providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderName() {
		return this.providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Double getTotalCharge() {
		return this.totalCharge;
	}

	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Timestamp getOperatorDate() {
		return this.operatorDate;
	}

	public void setOperatorDate(Timestamp operatorDate) {
		this.operatorDate = operatorDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MaterialSupplierSummary))
			return false;
		MaterialSupplierSummary castOther = (MaterialSupplierSummary) other;

		return ((this.getAutoId() == castOther.getAutoId()) || (this
				.getAutoId() != null
				&& castOther.getAutoId() != null && this.getAutoId().equals(
				castOther.getAutoId())))
				&& ((this.getUnitsCode() == castOther.getUnitsCode()) || (this
						.getUnitsCode() != null
						&& castOther.getUnitsCode() != null && this
						.getUnitsCode().equals(castOther.getUnitsCode())))
				&& ((this.getBillNo() == castOther.getBillNo()) || (this
						.getBillNo() != null
						&& castOther.getBillNo() != null && this.getBillNo()
						.equals(castOther.getBillNo())))
				&& ((this.getBillDate() == castOther.getBillDate()) || (this
						.getBillDate() != null
						&& castOther.getBillDate() != null && this
						.getBillDate().equals(castOther.getBillDate())))
				&& ((this.getProviderCode() == castOther.getProviderCode()) || (this
						.getProviderCode() != null
						&& castOther.getProviderCode() != null && this
						.getProviderCode().equals(castOther.getProviderCode())))
				&& ((this.getProviderName() == castOther.getProviderName()) || (this
						.getProviderName() != null
						&& castOther.getProviderName() != null && this
						.getProviderName().equals(castOther.getProviderName())))
				&& ((this.getContactPerson() == castOther.getContactPerson()) || (this
						.getContactPerson() != null
						&& castOther.getContactPerson() != null && this
						.getContactPerson()
						.equals(castOther.getContactPerson())))
				&& ((this.getTotalCharge() == castOther.getTotalCharge()) || (this
						.getTotalCharge() != null
						&& castOther.getTotalCharge() != null && this
						.getTotalCharge().equals(castOther.getTotalCharge())))
				&& ((this.getPersonName() == castOther.getPersonName()) || (this
						.getPersonName() != null
						&& castOther.getPersonName() != null && this
						.getPersonName().equals(castOther.getPersonName())))
				&& ((this.getOperatorDate() == castOther.getOperatorDate()) || (this
						.getOperatorDate() != null
						&& castOther.getOperatorDate() != null && this
						.getOperatorDate().equals(castOther.getOperatorDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAutoId() == null ? 0 : this.getAutoId().hashCode());
		result = 37 * result
				+ (getUnitsCode() == null ? 0 : this.getUnitsCode().hashCode());
		result = 37 * result
				+ (getBillNo() == null ? 0 : this.getBillNo().hashCode());
		result = 37 * result
				+ (getBillDate() == null ? 0 : this.getBillDate().hashCode());
		result = 37
				* result
				+ (getProviderCode() == null ? 0 : this.getProviderCode()
						.hashCode());
		result = 37
				* result
				+ (getProviderName() == null ? 0 : this.getProviderName()
						.hashCode());
		result = 37
				* result
				+ (getContactPerson() == null ? 0 : this.getContactPerson()
						.hashCode());
		result = 37
				* result
				+ (getTotalCharge() == null ? 0 : this.getTotalCharge()
						.hashCode());
		result = 37
				* result
				+ (getPersonName() == null ? 0 : this.getPersonName()
						.hashCode());
		result = 37
				* result
				+ (getOperatorDate() == null ? 0 : this.getOperatorDate()
						.hashCode());
		return result;
	}

}