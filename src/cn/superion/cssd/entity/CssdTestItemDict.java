package cn.superion.cssd.entity;

/**
 * CssdTestItemDictId entity. @author MyEclipse Persistence Tools
 */

public class CssdTestItemDict implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -3183551245267715504L;
	private String specimenCode;
	private Short serialNo;
	private String itemCode;
	private String itemName;
	private String referenceMinValue;
	private String referenceMaxValue;
	private String units;

	// Constructors

	/** default constructor */
	public CssdTestItemDict() {
	}



	// Property accessors

	public String getSpecimenCode() {
		return this.specimenCode;
	}

	public void setSpecimenCode(String specimenCode) {
		this.specimenCode = specimenCode;
	}

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getReferenceMinValue() {
		return this.referenceMinValue;
	}

	public void setReferenceMinValue(String referenceMinValue) {
		this.referenceMinValue = referenceMinValue;
	}

	public String getReferenceMaxValue() {
		return this.referenceMaxValue;
	}

	public void setReferenceMaxValue(String referenceMaxValue) {
		this.referenceMaxValue = referenceMaxValue;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((itemCode == null) ? 0 : itemCode.hashCode());
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		result = prime
				* result
				+ ((referenceMaxValue == null) ? 0 : referenceMaxValue
						.hashCode());
		result = prime
				* result
				+ ((referenceMinValue == null) ? 0 : referenceMinValue
						.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		result = prime * result
				+ ((specimenCode == null) ? 0 : specimenCode.hashCode());
		result = prime * result + ((units == null) ? 0 : units.hashCode());
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
		CssdTestItemDict other = (CssdTestItemDict) obj;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (referenceMaxValue == null) {
			if (other.referenceMaxValue != null)
				return false;
		} else if (!referenceMaxValue.equals(other.referenceMaxValue))
			return false;
		if (referenceMinValue == null) {
			if (other.referenceMinValue != null)
				return false;
		} else if (!referenceMinValue.equals(other.referenceMinValue))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		if (specimenCode == null) {
			if (other.specimenCode != null)
				return false;
		} else if (!specimenCode.equals(other.specimenCode))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}
}