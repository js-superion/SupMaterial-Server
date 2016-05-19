package cn.superion.cssd.entity;

/**
 * CssdChemistryDetail entity. @author MyEclipse Persistence Tools
 */

public class CssdChemistryDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3630523454191948348L;
	private String autoId;
	private String mainAutoId;
	private Short serialNo;
	private String packageClass;
	private String packageId;
	private String packageNo;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	private String detailRemark;
	

	// Constructors

	/** default constructor */
	public CssdChemistryDetail() {
	}

	/** minimal constructor */
	public CssdChemistryDetail(String autoId, String mainAutoId,
			Short serialNo, String packageClass, String packageId) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.packageClass = packageClass;
		this.packageId = packageId;
	}

	/** full constructor */
	public CssdChemistryDetail(String autoId, String mainAutoId,
			Short serialNo, String packageClass, String packageId,
			String packageNo, String packageName, String packageMode,
			String packageUnits, String detailRemark, String sourceAutoId) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.packageNo = packageNo;
		this.packageClass = packageClass;
		this.packageId = packageId;
		
		this.packageName = packageName;
		this.packageMode = packageMode;
		this.packageUnits = packageUnits;
		this.detailRemark = detailRemark;
		
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getMainAutoId() {
		return this.mainAutoId;
	}

	public void setMainAutoId(String mainAutoId) {
		this.mainAutoId = mainAutoId;
	}

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}
	public String getPackageNo() {
		return this.packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
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

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}


	

}