package cn.superion.cssd.entity;

import java.util.Date;

/**
 * CssdPackageDict entity. @author MyEclipse Persistence Tools
 */

public class CssdPackageDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8208212131936742838L;
	private String unitsCode;
	private String packageId;
	private String packageClass;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	private Double tradePrice;
	private Double materialFee;
	private Double sterilizeFee;
	private Double qualityDay;
	private String remark;
	private Date startDate;
	private Date stopDate;
	private String createPerson;
	private Date createDate;
	private String modifyPerson;
	private Date modifyDate;
	private String phoInputCode;
	private String fiveInputCode;
	private String trackSign;

	// Constructors

	/** default constructor */
	public CssdPackageDict() {
	}

	
	public String getUnitsCode() {
		return unitsCode;
	}


	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}


	public String getPackageId() {
		return packageId;
	}


	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}


	public String getPackageClass() {
		return this.packageClass;
	}

	public void setPackageClass(String packageClass) {
		this.packageClass = packageClass;
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

	public String getTrackSign() {
		return trackSign;
	}


	public void setTrackSign(String trackSign) {
		this.trackSign = trackSign;
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

	public Double getMaterialFee() {
		return this.materialFee;
	}

	public void setMaterialFee(Double materialFee) {
		this.materialFee = materialFee;
	}

	public Double getSterilizeFee() {
		return this.sterilizeFee;
	}

	public void setSterilizeFee(Double sterilizeFee) {
		this.sterilizeFee = sterilizeFee;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return this.stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
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
		this.createDate = createDate;
	}

	public String getModifyPerson() {
		return this.modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPhoInputCode() {
		return this.phoInputCode;
	}

	public void setPhoInputCode(String phoInputCode) {
		this.phoInputCode = phoInputCode;
	}

	public String getFiveInputCode() {
		return this.fiveInputCode;
	}

	public void setFiveInputCode(String fiveInputCode) {
		this.fiveInputCode = fiveInputCode;
	}


	public Double getQualityDay() {
		return qualityDay;
	}


	public void setQualityDay(Double qualityDay) {
		this.qualityDay = qualityDay;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createPerson == null) ? 0 : createPerson.hashCode());
		result = prime * result
				+ ((fiveInputCode == null) ? 0 : fiveInputCode.hashCode());
		result = prime * result
				+ ((materialFee == null) ? 0 : materialFee.hashCode());
		result = prime * result
				+ ((modifyDate == null) ? 0 : modifyDate.hashCode());
		result = prime * result
				+ ((modifyPerson == null) ? 0 : modifyPerson.hashCode());
		result = prime * result
				+ ((packageClass == null) ? 0 : packageClass.hashCode());
		result = prime * result
				+ ((packageId == null) ? 0 : packageId.hashCode());
		result = prime * result
				+ ((packageMode == null) ? 0 : packageMode.hashCode());
		result = prime * result
				+ ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result
				+ ((packageUnits == null) ? 0 : packageUnits.hashCode());
		result = prime * result
				+ ((phoInputCode == null) ? 0 : phoInputCode.hashCode());
		result = prime * result
				+ ((qualityDay == null) ? 0 : qualityDay.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((sterilizeFee == null) ? 0 : sterilizeFee.hashCode());
		result = prime * result
				+ ((stopDate == null) ? 0 : stopDate.hashCode());
		result = prime * result
				+ ((tradePrice == null) ? 0 : tradePrice.hashCode());
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
		CssdPackageDict other = (CssdPackageDict) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createPerson == null) {
			if (other.createPerson != null)
				return false;
		} else if (!createPerson.equals(other.createPerson))
			return false;
		if (fiveInputCode == null) {
			if (other.fiveInputCode != null)
				return false;
		} else if (!fiveInputCode.equals(other.fiveInputCode))
			return false;
		if (materialFee == null) {
			if (other.materialFee != null)
				return false;
		} else if (!materialFee.equals(other.materialFee))
			return false;
		if (modifyDate == null) {
			if (other.modifyDate != null)
				return false;
		} else if (!modifyDate.equals(other.modifyDate))
			return false;
		if (modifyPerson == null) {
			if (other.modifyPerson != null)
				return false;
		} else if (!modifyPerson.equals(other.modifyPerson))
			return false;
		if (packageClass == null) {
			if (other.packageClass != null)
				return false;
		} else if (!packageClass.equals(other.packageClass))
			return false;
		if (packageId == null) {
			if (other.packageId != null)
				return false;
		} else if (!packageId.equals(other.packageId))
			return false;
		if (packageMode == null) {
			if (other.packageMode != null)
				return false;
		} else if (!packageMode.equals(other.packageMode))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (packageUnits == null) {
			if (other.packageUnits != null)
				return false;
		} else if (!packageUnits.equals(other.packageUnits))
			return false;
		if (phoInputCode == null) {
			if (other.phoInputCode != null)
				return false;
		} else if (!phoInputCode.equals(other.phoInputCode))
			return false;
		if (qualityDay == null) {
			if (other.qualityDay != null)
				return false;
		} else if (!qualityDay.equals(other.qualityDay))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (sterilizeFee == null) {
			if (other.sterilizeFee != null)
				return false;
		} else if (!sterilizeFee.equals(other.sterilizeFee))
			return false;
		if (stopDate == null) {
			if (other.stopDate != null)
				return false;
		} else if (!stopDate.equals(other.stopDate))
			return false;
		if (tradePrice == null) {
			if (other.tradePrice != null)
				return false;
		} else if (!tradePrice.equals(other.tradePrice))
			return false;
		if (unitsCode == null) {
			if (other.unitsCode != null)
				return false;
		} else if (!unitsCode.equals(other.unitsCode))
			return false;
		return true;
	}

}