package cn.superion.materialDept.entity;

/**
 * InpPatsBillDetailId entity. @author MyEclipse Persistence Tools
 */

public class InpPatsBillDetailId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3861069037553624879L;
	private String unitsCode;
	private String inpNo;
	private Integer itemNo;

	// Constructors

	/** default constructor */
	public InpPatsBillDetailId() {
	}

	/** full constructor */
	public InpPatsBillDetailId(String unitsCode, String inpNo, Integer itemNo) {
		this.unitsCode = unitsCode;
		this.inpNo = inpNo;
		this.itemNo = itemNo;
	}

	// Property accessors

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getInpNo() {
		return this.inpNo;
	}

	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}

	public Integer getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof InpPatsBillDetailId))
			return false;
		InpPatsBillDetailId castOther = (InpPatsBillDetailId) other;

		return ((this.getUnitsCode() == castOther.getUnitsCode()) || (this
				.getUnitsCode() != null
				&& castOther.getUnitsCode() != null && this.getUnitsCode()
				.equals(castOther.getUnitsCode())))
				&& ((this.getInpNo() == castOther.getInpNo()) || (this
						.getInpNo() != null
						&& castOther.getInpNo() != null && this.getInpNo()
						.equals(castOther.getInpNo())))
				&& ((this.getItemNo() == castOther.getItemNo()) || (this
						.getItemNo() != null
						&& castOther.getItemNo() != null && this.getItemNo()
						.equals(castOther.getItemNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUnitsCode() == null ? 0 : this.getUnitsCode().hashCode());
		result = 37 * result
				+ (getInpNo() == null ? 0 : this.getInpNo().hashCode());
		result = 37 * result
				+ (getItemNo() == null ? 0 : this.getItemNo().hashCode());
		return result;
	}

}