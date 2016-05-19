package cn.superion.equipment.entity;

/**
 * EqSparePartsDetail entity. @author MyEclipse Persistence Tools
 */

public class EqSparePartsDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2666471521260843740L;
	private String equipmentTypeCode;
	private Short serialNo;
	private String sparePartCode;
	private String sparePartName;
	private String partSpec;
	private String partUnit;
	private String manufacture;
	private Double amount;
	private Double unitPrice;
	private Double charge;

	// Constructors

	/** default constructor */
	public EqSparePartsDetail() {
	}

	/** minimal constructor */
	public EqSparePartsDetail(String equipmentTypeCode, Short serialNo) {
		this.equipmentTypeCode = equipmentTypeCode;
		this.serialNo = serialNo;
	}

	/** full constructor */
	public EqSparePartsDetail(String equipmentTypeCode, Short serialNo,
			String sparePartCode, String sparePartName, String partSpec,
			String partUnit, String manufacture, Double amount,
			Double unitPrice, Double charge) {
		this.equipmentTypeCode = equipmentTypeCode;
		this.serialNo = serialNo;
		this.sparePartCode = sparePartCode;
		this.sparePartName = sparePartName;
		this.partSpec = partSpec;
		this.partUnit = partUnit;
		this.manufacture = manufacture;
		this.amount = amount;
		this.unitPrice = unitPrice;
		this.charge = charge;
	}

	// Property accessors

	public String getEquipmentTypeCode() {
		return this.equipmentTypeCode;
	}

	public void setEquipmentTypeCode(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public String getSparePartCode() {
		return this.sparePartCode;
	}

	public void setSparePartCode(String sparePartCode) {
		this.sparePartCode = sparePartCode;
	}

	public String getSparePartName() {
		return this.sparePartName;
	}

	public void setSparePartName(String sparePartName) {
		this.sparePartName = sparePartName;
	}

	public String getPartSpec() {
		return this.partSpec;
	}

	public void setPartSpec(String partSpec) {
		this.partSpec = partSpec;
	}

	public String getPartUnit() {
		return this.partUnit;
	}

	public void setPartUnit(String partUnit) {
		this.partUnit = partUnit;
	}

	public String getManufacture() {
		return this.manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((charge == null) ? 0 : charge.hashCode());
		result = prime
				* result
				+ ((equipmentTypeCode == null) ? 0 : equipmentTypeCode
						.hashCode());
		result = prime * result
				+ ((manufacture == null) ? 0 : manufacture.hashCode());
		result = prime * result
				+ ((partSpec == null) ? 0 : partSpec.hashCode());
		result = prime * result
				+ ((partUnit == null) ? 0 : partUnit.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		result = prime * result
				+ ((sparePartCode == null) ? 0 : sparePartCode.hashCode());
		result = prime * result
				+ ((sparePartName == null) ? 0 : sparePartName.hashCode());
		result = prime * result
				+ ((unitPrice == null) ? 0 : unitPrice.hashCode());
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
		EqSparePartsDetail other = (EqSparePartsDetail) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (charge == null) {
			if (other.charge != null)
				return false;
		} else if (!charge.equals(other.charge))
			return false;
		if (equipmentTypeCode == null) {
			if (other.equipmentTypeCode != null)
				return false;
		} else if (!equipmentTypeCode.equals(other.equipmentTypeCode))
			return false;
		if (manufacture == null) {
			if (other.manufacture != null)
				return false;
		} else if (!manufacture.equals(other.manufacture))
			return false;
		if (partSpec == null) {
			if (other.partSpec != null)
				return false;
		} else if (!partSpec.equals(other.partSpec))
			return false;
		if (partUnit == null) {
			if (other.partUnit != null)
				return false;
		} else if (!partUnit.equals(other.partUnit))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		if (sparePartCode == null) {
			if (other.sparePartCode != null)
				return false;
		} else if (!sparePartCode.equals(other.sparePartCode))
			return false;
		if (sparePartName == null) {
			if (other.sparePartName != null)
				return false;
		} else if (!sparePartName.equals(other.sparePartName))
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
			return false;
		return true;
	}

}