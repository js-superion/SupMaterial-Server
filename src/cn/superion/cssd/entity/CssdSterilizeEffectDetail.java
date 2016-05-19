package cn.superion.cssd.entity;

import java.util.Date;

/**
 * CssdSterilizeEffectDetail entity. @author MyEclipse Persistence Tools
 */

public class CssdSterilizeEffectDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 774187892405329951L;
	private String autoId;
	private String mainAutoId;
	private Short serialNo;
	private String packageClass;
	private String packageId;
	private String packageNo;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	private String resultDescr;
	private String detailRemark;
	private String resultValue;
	private String checker;
	private Date checkDate;

	// Constructors

	/** default constructor */
	public CssdSterilizeEffectDetail() {
	}

	/** minimal constructor */
	public CssdSterilizeEffectDetail(String autoId, String mainAutoId,
			Short serialNo, String packageClass, String packageId) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.packageClass = packageClass;
		this.packageId = packageId;
	}

	/** full constructor */
	public CssdSterilizeEffectDetail(String autoId, String mainAutoId,
			Short serialNo, String packageClass, String packageId,
			String packageNo, String packageName, String packageMode,
			String packageUnits, String resultDescr, String detailRemark,
			String resultValue, String checker, Date checkDate) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.packageClass = packageClass;
		this.packageId = packageId;
		this.packageNo = packageNo;
		this.packageName = packageName;
		this.packageMode = packageMode;
		this.packageUnits = packageUnits;
		this.resultDescr = resultDescr;
		this.detailRemark = detailRemark;
		this.resultValue = resultValue;
		this.checker = checker;
		this.checkDate = checkDate;
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

	public String getPackageNo() {
		return this.packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
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

	public String getResultDescr() {
		return this.resultDescr;
	}

	public void setResultDescr(String resultDescr) {
		this.resultDescr = resultDescr;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public String getResultValue() {
		return this.resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public String getChecker() {
		return this.checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

}