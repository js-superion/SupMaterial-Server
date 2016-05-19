package cn.superion.cssd.entity;

import java.util.Date;

/**
 * VCssdPersonWorkloadId entity. @author MyEclipse Persistence Tools
 */

public class VCssdPersonWorkload implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7201860360945352240L;
	private String billName;
	private String unitsCode;
	private Date billDate;
	private String personId;
	private String packageNo;
	
	private String packageClass;
	private String packageId;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	private Double tradePrice;
	private Double amount;
	private String detailRemark;

	// Constructors

	/** default constructor */
	public VCssdPersonWorkload() {
	}
	
	

	public VCssdPersonWorkload(String billName, String unitsCode,
			Date billDate, String personId, String packageNo) {
		super();
		this.billName = billName;
		this.unitsCode = unitsCode;
		this.billDate = billDate;
		this.personId = personId;
		this.packageNo = packageNo;
	}



	/** full constructor */
	public VCssdPersonWorkload(String billName, String unitsCode,
			Date billDate, String personId, String packageClass,
			String packageId, String packageNo, String packageName,
			String packageMode, String packageUnits, Double tradePrice,
			Double amount, String detailRemark) {
		this.billName = billName;
		this.unitsCode = unitsCode;
		this.billDate = billDate;
		this.personId = personId;
		this.packageClass = packageClass;
		this.packageId = packageId;
		this.packageNo = packageNo;
		this.packageName = packageName;
		this.packageMode = packageMode;
		this.packageUnits = packageUnits;
		this.tradePrice = tradePrice;
		this.amount = amount;
		this.detailRemark = detailRemark;
	}

	// Property accessors

	public String getBillName() {
		return this.billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPackageClass() {
		return this.packageClass;
	}

	public void setPackageClass(String packageClass) {
		this.packageClass = packageClass;
	}

	public String getPackageId() {
		return this.packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageNo() {
		return this.packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageMode() {
		return this.packageMode;
	}

	public void setPackageMode(String packageMode) {
		this.packageMode = packageMode;
	}

	public String getPackageUnits() {
		return this.packageUnits;
	}

	public void setPackageUnits(String packageUnits) {
		this.packageUnits = packageUnits;
	}

	public Double getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result
				+ ((billName == null) ? 0 : billName.hashCode());
		result = prime * result
				+ ((packageNo == null) ? 0 : packageNo.hashCode());
		result = prime * result
				+ ((personId == null) ? 0 : personId.hashCode());
		result = prime * result
				+ ((unitsCode == null) ? 0 : unitsCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VCssdPersonWorkload other = (VCssdPersonWorkload) obj;
		if (billDate == null) {
			if (other.billDate != null)
				return false;
		} else if (!billDate.equals(other.billDate))
			return false;
		if (billName == null) {
			if (other.billName != null)
				return false;
		} else if (!billName.equals(other.billName))
			return false;
		if (packageNo == null) {
			if (other.packageNo != null)
				return false;
		} else if (!packageNo.equals(other.packageNo))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (unitsCode == null) {
			if (other.unitsCode != null)
				return false;
		} else if (!unitsCode.equals(other.unitsCode))
			return false;
		return true;
	}

}