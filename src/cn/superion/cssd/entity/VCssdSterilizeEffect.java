package cn.superion.cssd.entity;

import java.util.Date;

/**
 * VCssdSterilizeEffect entity. @author MyEclipse Persistence Tools
 */

public class VCssdSterilizeEffect implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5876108290722916484L;
	// Fields

	private String autoId;
	private String mainAutoId;
	private String unitsCode;
	private String billNo;
	private Date billDate;
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
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;

	// Constructors

	/** default constructor */
	public VCssdSterilizeEffect() {
	}

	/** minimal constructor */
	public VCssdSterilizeEffect(String autoId, String mainAutoId,
			String unitsCode, Date billDate, String packageClass,
			String packageId) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.unitsCode = unitsCode;
		this.billDate = billDate;
		this.packageClass = packageClass;
		this.packageId = packageId;
	}

	/** full constructor */
	public VCssdSterilizeEffect(String autoId, String mainAutoId,
			String unitsCode, String billNo, Date billDate,
			String packageClass, String packageId, String packageNo,
			String packageName, String packageMode, String packageUnits,
			String resultDescr, String detailRemark, String resultValue,
			String checker, Date checkDate, String remark, String maker,
			Date makeDate, String verifier, Date verifyDate,
			String currentStatus) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
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
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
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

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMaker() {
		return this.maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Date getMakeDate() {
		return this.makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getVerifier() {
		return this.verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public Date getVerifyDate() {
		return this.verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VCssdSterilizeEffect))
			return false;
		VCssdSterilizeEffect castOther = (VCssdSterilizeEffect) other;

		return ((this.getAutoId() == castOther.getAutoId()) || (this
				.getAutoId() != null
				&& castOther.getAutoId() != null && this.getAutoId().equals(
				castOther.getAutoId())))
				&& ((this.getMainAutoId() == castOther.getMainAutoId()) || (this
						.getMainAutoId() != null
						&& castOther.getMainAutoId() != null && this
						.getMainAutoId().equals(castOther.getMainAutoId())))
				&& ((this.getUnitsCode() == castOther.getUnitsCode()) || (this
						.getUnitsCode() != null
						&& castOther.getUnitsCode() != null && this
						.getUnitsCode().equals(castOther.getUnitsCode())))
				&& ((this.getBillNo() == castOther.getBillNo()) || (this
						.getBillNo() != null
						&& castOther.getBillNo() != null && this.getBillNo()
						.equals(castOther.getBillNo())))
				&& ((this.getBillDate() == castOther.getBillDate()) || (this
						.getBillDate() != null
						&& castOther.getBillDate() != null && this
						.getBillDate().equals(castOther.getBillDate())))
				&& ((this.getPackageClass() == castOther.getPackageClass()) || (this
						.getPackageClass() != null
						&& castOther.getPackageClass() != null && this
						.getPackageClass().equals(castOther.getPackageClass())))
				&& ((this.getPackageId() == castOther.getPackageId()) || (this
						.getPackageId() != null
						&& castOther.getPackageId() != null && this
						.getPackageId().equals(castOther.getPackageId())))
				&& ((this.getPackageNo() == castOther.getPackageNo()) || (this
						.getPackageNo() != null
						&& castOther.getPackageNo() != null && this
						.getPackageNo().equals(castOther.getPackageNo())))
				&& ((this.getPackageName() == castOther.getPackageName()) || (this
						.getPackageName() != null
						&& castOther.getPackageName() != null && this
						.getPackageName().equals(castOther.getPackageName())))
				&& ((this.getPackageMode() == castOther.getPackageMode()) || (this
						.getPackageMode() != null
						&& castOther.getPackageMode() != null && this
						.getPackageMode().equals(castOther.getPackageMode())))
				&& ((this.getPackageUnits() == castOther.getPackageUnits()) || (this
						.getPackageUnits() != null
						&& castOther.getPackageUnits() != null && this
						.getPackageUnits().equals(castOther.getPackageUnits())))
				&& ((this.getResultDescr() == castOther.getResultDescr()) || (this
						.getResultDescr() != null
						&& castOther.getResultDescr() != null && this
						.getResultDescr().equals(castOther.getResultDescr())))
				&& ((this.getDetailRemark() == castOther.getDetailRemark()) || (this
						.getDetailRemark() != null
						&& castOther.getDetailRemark() != null && this
						.getDetailRemark().equals(castOther.getDetailRemark())))
				&& ((this.getResultValue() == castOther.getResultValue()) || (this
						.getResultValue() != null
						&& castOther.getResultValue() != null && this
						.getResultValue().equals(castOther.getResultValue())))
				&& ((this.getChecker() == castOther.getChecker()) || (this
						.getChecker() != null
						&& castOther.getChecker() != null && this.getChecker()
						.equals(castOther.getChecker())))
				&& ((this.getCheckDate() == castOther.getCheckDate()) || (this
						.getCheckDate() != null
						&& castOther.getCheckDate() != null && this
						.getCheckDate().equals(castOther.getCheckDate())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())))
				&& ((this.getMaker() == castOther.getMaker()) || (this
						.getMaker() != null
						&& castOther.getMaker() != null && this.getMaker()
						.equals(castOther.getMaker())))
				&& ((this.getMakeDate() == castOther.getMakeDate()) || (this
						.getMakeDate() != null
						&& castOther.getMakeDate() != null && this
						.getMakeDate().equals(castOther.getMakeDate())))
				&& ((this.getVerifier() == castOther.getVerifier()) || (this
						.getVerifier() != null
						&& castOther.getVerifier() != null && this
						.getVerifier().equals(castOther.getVerifier())))
				&& ((this.getVerifyDate() == castOther.getVerifyDate()) || (this
						.getVerifyDate() != null
						&& castOther.getVerifyDate() != null && this
						.getVerifyDate().equals(castOther.getVerifyDate())))
				&& ((this.getCurrentStatus() == castOther.getCurrentStatus()) || (this
						.getCurrentStatus() != null
						&& castOther.getCurrentStatus() != null && this
						.getCurrentStatus()
						.equals(castOther.getCurrentStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAutoId() == null ? 0 : this.getAutoId().hashCode());
		result = 37
				* result
				+ (getMainAutoId() == null ? 0 : this.getMainAutoId()
						.hashCode());
		result = 37 * result
				+ (getUnitsCode() == null ? 0 : this.getUnitsCode().hashCode());
		result = 37 * result
				+ (getBillNo() == null ? 0 : this.getBillNo().hashCode());
		result = 37 * result
				+ (getBillDate() == null ? 0 : this.getBillDate().hashCode());
		result = 37
				* result
				+ (getPackageClass() == null ? 0 : this.getPackageClass()
						.hashCode());
		result = 37 * result
				+ (getPackageId() == null ? 0 : this.getPackageId().hashCode());
		result = 37 * result
				+ (getPackageNo() == null ? 0 : this.getPackageNo().hashCode());
		result = 37
				* result
				+ (getPackageName() == null ? 0 : this.getPackageName()
						.hashCode());
		result = 37
				* result
				+ (getPackageMode() == null ? 0 : this.getPackageMode()
						.hashCode());
		result = 37
				* result
				+ (getPackageUnits() == null ? 0 : this.getPackageUnits()
						.hashCode());
		result = 37
				* result
				+ (getResultDescr() == null ? 0 : this.getResultDescr()
						.hashCode());
		result = 37
				* result
				+ (getDetailRemark() == null ? 0 : this.getDetailRemark()
						.hashCode());
		result = 37
				* result
				+ (getResultValue() == null ? 0 : this.getResultValue()
						.hashCode());
		result = 37 * result
				+ (getChecker() == null ? 0 : this.getChecker().hashCode());
		result = 37 * result
				+ (getCheckDate() == null ? 0 : this.getCheckDate().hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getMaker() == null ? 0 : this.getMaker().hashCode());
		result = 37 * result
				+ (getMakeDate() == null ? 0 : this.getMakeDate().hashCode());
		result = 37 * result
				+ (getVerifier() == null ? 0 : this.getVerifier().hashCode());
		result = 37
				* result
				+ (getVerifyDate() == null ? 0 : this.getVerifyDate()
						.hashCode());
		result = 37
				* result
				+ (getCurrentStatus() == null ? 0 : this.getCurrentStatus()
						.hashCode());
		return result;
	}

}