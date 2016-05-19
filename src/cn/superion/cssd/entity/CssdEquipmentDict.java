package cn.superion.cssd.entity;

/**
 * CssdEquipmentDict entity. @author MyEclipse Persistence Tools
 */

public class CssdEquipmentDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3183551245267715504L;
	private String unitsCode;
	private String equipmentCode;
	private String equipmentName;
	private Byte serialNo;
	private String materialId;
	private String phoInputCode;
	private String fiveInputCode;

	// Constructors

	/** default constructor */
	public CssdEquipmentDict() {
	}


	/** full constructor */
	public CssdEquipmentDict(String equipmentName,
			Byte serialNo, String materialId, String phoInputCode,
			String fiveInputCode) {
		this.equipmentName = equipmentName;
		this.serialNo = serialNo;
		this.materialId = materialId;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
	}

	// Property accessors

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Byte getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Byte serialNo) {
		this.serialNo = serialNo;
	}

	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
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

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getUnitsCode() {
		return unitsCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((equipmentCode == null) ? 0 : equipmentCode.hashCode());
		result = prime * result
				+ ((equipmentName == null) ? 0 : equipmentName.hashCode());
		result = prime * result
				+ ((fiveInputCode == null) ? 0 : fiveInputCode.hashCode());
		result = prime * result
				+ ((materialId == null) ? 0 : materialId.hashCode());
		result = prime * result
				+ ((phoInputCode == null) ? 0 : phoInputCode.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
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
		CssdEquipmentDict other = (CssdEquipmentDict) obj;
		if (equipmentCode == null) {
			if (other.equipmentCode != null)
				return false;
		} else if (!equipmentCode.equals(other.equipmentCode))
			return false;
		if (equipmentName == null) {
			if (other.equipmentName != null)
				return false;
		} else if (!equipmentName.equals(other.equipmentName))
			return false;
		if (fiveInputCode == null) {
			if (other.fiveInputCode != null)
				return false;
		} else if (!fiveInputCode.equals(other.fiveInputCode))
			return false;
		if (materialId == null) {
			if (other.materialId != null)
				return false;
		} else if (!materialId.equals(other.materialId))
			return false;
		if (phoInputCode == null) {
			if (other.phoInputCode != null)
				return false;
		} else if (!phoInputCode.equals(other.phoInputCode))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		if (unitsCode == null) {
			if (other.unitsCode != null)
				return false;
		} else if (!unitsCode.equals(other.unitsCode))
			return false;
		return true;
	}

}