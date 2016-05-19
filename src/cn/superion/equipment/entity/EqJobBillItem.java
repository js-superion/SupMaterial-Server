package cn.superion.equipment.entity;

/**
 * EqJobBillItem entity. @author MyEclipse Persistence Tools
 */

public class EqJobBillItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1508918418546888816L;
	private String autoId;
	private Short serialNo;
	private String itemName;
	private String part;
	private String jobGroup;
	private String director;

	// Constructors

	/** default constructor */
	public EqJobBillItem() {
	}

	// Property accessors

	public String getItemName() {
		return this.itemName;
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

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPart() {
		return this.part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getJobGroup() {
		return this.jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result
				+ ((director == null) ? 0 : director.hashCode());
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result
				+ ((jobGroup == null) ? 0 : jobGroup.hashCode());
		result = prime * result + ((part == null) ? 0 : part.hashCode());
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
		EqJobBillItem other = (EqJobBillItem) obj;
		if (autoId == null) {
			if (other.autoId != null)
				return false;
		} else if (!autoId.equals(other.autoId))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (jobGroup == null) {
			if (other.jobGroup != null)
				return false;
		} else if (!jobGroup.equals(other.jobGroup))
			return false;
		if (part == null) {
			if (other.part != null)
				return false;
		} else if (!part.equals(other.part))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		return true;
	}

}