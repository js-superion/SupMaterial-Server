package cn.superion.cssd.entity;

/**
 * CssdDeptVsPackageClassId entity. @author MyEclipse Persistence Tools
 */

public class CssdDeptVsPackageClass implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String unitsCode;
	private String deptCode;
	private String classCode;
	private String className;

	// Constructors

	/** default constructor */
	public CssdDeptVsPackageClass() {
	}

	/** full constructor */
	public CssdDeptVsPackageClass(String unitsCode, String deptCode,
			String classCode,String className) {
		this.unitsCode = unitsCode;
		this.deptCode = deptCode;
		this.classCode = classCode;
		this.className = className;
	}

	// Property accessors

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CssdDeptVsPackageClass))
			return false;
		CssdDeptVsPackageClass castOther = (CssdDeptVsPackageClass) other;

		return ((this.getUnitsCode() == castOther.getUnitsCode()) || (this
				.getUnitsCode() != null
				&& castOther.getUnitsCode() != null && this.getUnitsCode()
				.equals(castOther.getUnitsCode())))
				&& ((this.getDeptCode() == castOther.getDeptCode()) || (this
						.getDeptCode() != null
						&& castOther.getDeptCode() != null && this
						.getDeptCode().equals(castOther.getDeptCode())))
				&& ((this.getClassCode() == castOther.getClassCode()) || (this
						.getClassCode() != null
						&& castOther.getClassCode() != null && this
						.getClassCode().equals(castOther.getClassCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUnitsCode() == null ? 0 : this.getUnitsCode().hashCode());
		result = 37 * result
				+ (getDeptCode() == null ? 0 : this.getDeptCode().hashCode());
		result = 37 * result
				+ (getClassCode() == null ? 0 : this.getClassCode().hashCode());
		return result;
	}

}