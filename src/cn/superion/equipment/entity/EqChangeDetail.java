package cn.superion.equipment.entity;

/**
 * EqChangeDetail entity. @author MyEclipse Persistence Tools
 */

public class EqChangeDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1723849511868717333L;
	private String autoId;
	private Short serialNo;
	private String equipmentCode;
	private String equipmentName;
	private String changeContent;
	private String beforeContent;
	private String afterContent;

	// Constructors

	/** default constructor */
	public EqChangeDetail() {
	}

	/** minimal constructor */
	public EqChangeDetail(String autoId, Short serialNo) {
		super();
		this.autoId = autoId;
		this.serialNo = serialNo;
	}

	// Property accessors

	public String getEquipmentCode() {
		return this.equipmentCode;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public Short getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getChangeContent() {
		return this.changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	public String getBeforeContent() {
		return this.beforeContent;
	}

	public void setBeforeContent(String beforeContent) {
		this.beforeContent = beforeContent;
	}

	public String getAfterContent() {
		return afterContent;
	}

	public void setAfterContent(String afterContent) {
		this.afterContent = afterContent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((afterContent == null) ? 0 : afterContent.hashCode());
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result
				+ ((beforeContent == null) ? 0 : beforeContent.hashCode());
		result = prime * result
				+ ((changeContent == null) ? 0 : changeContent.hashCode());
		result = prime * result
				+ ((equipmentCode == null) ? 0 : equipmentCode.hashCode());
		result = prime * result
				+ ((equipmentName == null) ? 0 : equipmentName.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
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
		EqChangeDetail other = (EqChangeDetail) obj;
		if (afterContent == null) {
			if (other.afterContent != null)
				return false;
		} else if (!afterContent.equals(other.afterContent))
			return false;
		if (autoId == null) {
			if (other.autoId != null)
				return false;
		} else if (!autoId.equals(other.autoId))
			return false;
		if (beforeContent == null) {
			if (other.beforeContent != null)
				return false;
		} else if (!beforeContent.equals(other.beforeContent))
			return false;
		if (changeContent == null) {
			if (other.changeContent != null)
				return false;
		} else if (!changeContent.equals(other.changeContent))
			return false;
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
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		return true;
	}

}