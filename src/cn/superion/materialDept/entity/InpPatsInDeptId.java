package cn.superion.materialDept.entity;

/**
 * InpPatsInDeptId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class InpPatsInDeptId implements java.io.Serializable {

	// Fields

	private String unitsCode;
	private String inpNo;
	private Byte serialNo;

	// Constructors

	/** default constructor */
	public InpPatsInDeptId() {
	}

	/** full constructor */
	public InpPatsInDeptId(String unitsCode, String inpNo, Byte serialNo) {
		this.unitsCode = unitsCode;
		this.inpNo = inpNo;
		this.serialNo = serialNo;
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

	public Byte getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Byte serialNo) {
		this.serialNo = serialNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof InpPatsInDeptId))
			return false;
		InpPatsInDeptId castOther = (InpPatsInDeptId) other;

		return ((this.getUnitsCode() == castOther.getUnitsCode()) || (this
				.getUnitsCode() != null
				&& castOther.getUnitsCode() != null && this.getUnitsCode()
				.equals(castOther.getUnitsCode())))
				&& ((this.getInpNo() == castOther.getInpNo()) || (this
						.getInpNo() != null
						&& castOther.getInpNo() != null && this.getInpNo()
						.equals(castOther.getInpNo())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUnitsCode() == null ? 0 : this.getUnitsCode().hashCode());
		result = 37 * result
				+ (getInpNo() == null ? 0 : this.getInpNo().hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		return result;
	}

}