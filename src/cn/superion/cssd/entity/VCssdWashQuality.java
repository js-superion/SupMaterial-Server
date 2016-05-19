package cn.superion.cssd.entity;

import java.util.Date;

/**
 * VCssdWashQuality entity. @author MyEclipse Persistence Tools
 */

public class VCssdWashQuality implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6811065664718213086L;
	// Fields

	private String autoId;
	private String mainAutoId;
	private String unitsCode;
	private String billNo;
	private Date billDate;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private String eyeballingValue;
	private String magnifierValue;
	private String hgbValue;
	private String functionValue;
	private String detailRemark;
	private String resultValue;
	private String checker;
	private Date checkDate;
	private String sourceAutoId;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;	

	// Constructors

	/** default constructor */
	public VCssdWashQuality() {
	}

	/** minimal constructor */
	public VCssdWashQuality(String autoId, String mainAutoId,
			String unitsCode, String materialClass, String materialId,
			String materialCode, String materialName) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.unitsCode = unitsCode;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public VCssdWashQuality(String autoId, String mainAutoId,
			String unitsCode, String billNo, Date billDate,
			String materialClass, String materialId, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			String factoryCode, String eyeballingValue, String magnifierValue,
			String hgbValue, String functionValue, String detailRemark,
			String resultValue, String checker, Date checkDate,
			String sourceAutoId, String remark, String maker,
			Date makeDate,String verifier,Date verifyDate,String currentStatus) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.factoryCode = factoryCode;
		this.eyeballingValue = eyeballingValue;
		this.magnifierValue = magnifierValue;
		this.hgbValue = hgbValue;
		this.functionValue = functionValue;
		this.detailRemark = detailRemark;
		this.resultValue = resultValue;
		this.checker = checker;
		this.checkDate = checkDate;
		this.sourceAutoId = sourceAutoId;
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

	public String getMaterialClass() {
		return this.materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}

	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialCode() {
		return this.materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialSpec() {
		return this.materialSpec;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	public String getMaterialUnits() {
		return this.materialUnits;
	}

	public void setMaterialUnits(String materialUnits) {
		this.materialUnits = materialUnits;
	}

	public String getFactoryCode() {
		return this.factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getEyeballingValue() {
		return this.eyeballingValue;
	}

	public void setEyeballingValue(String eyeballingValue) {
		this.eyeballingValue = eyeballingValue;
	}

	public String getMagnifierValue() {
		return this.magnifierValue;
	}

	public void setMagnifierValue(String magnifierValue) {
		this.magnifierValue = magnifierValue;
	}

	public String getHgbValue() {
		return this.hgbValue;
	}

	public void setHgbValue(String hgbValue) {
		this.hgbValue = hgbValue;
	}

	public String getFunctionValue() {
		return this.functionValue;
	}

	public void setFunctionValue(String functionValue) {
		this.functionValue = functionValue;
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

	public String getSourceAutoId() {
		return this.sourceAutoId;
	}

	public void setSourceAutoId(String sourceAutoId) {
		this.sourceAutoId = sourceAutoId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VCssdWashQuality))
			return false;
		VCssdWashQuality castOther = (VCssdWashQuality) other;

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
				&& ((this.getMaterialClass() == castOther.getMaterialClass()) || (this
						.getMaterialClass() != null
						&& castOther.getMaterialClass() != null && this
						.getMaterialClass()
						.equals(castOther.getMaterialClass())))
				&& ((this.getMaterialId() == castOther.getMaterialId()) || (this
						.getMaterialId() != null
						&& castOther.getMaterialId() != null && this
						.getMaterialId().equals(castOther.getMaterialId())))
				&& ((this.getMaterialCode() == castOther.getMaterialCode()) || (this
						.getMaterialCode() != null
						&& castOther.getMaterialCode() != null && this
						.getMaterialCode().equals(castOther.getMaterialCode())))
				&& ((this.getMaterialName() == castOther.getMaterialName()) || (this
						.getMaterialName() != null
						&& castOther.getMaterialName() != null && this
						.getMaterialName().equals(castOther.getMaterialName())))
				&& ((this.getMaterialSpec() == castOther.getMaterialSpec()) || (this
						.getMaterialSpec() != null
						&& castOther.getMaterialSpec() != null && this
						.getMaterialSpec().equals(castOther.getMaterialSpec())))
				&& ((this.getMaterialUnits() == castOther.getMaterialUnits()) || (this
						.getMaterialUnits() != null
						&& castOther.getMaterialUnits() != null && this
						.getMaterialUnits()
						.equals(castOther.getMaterialUnits())))
				&& ((this.getFactoryCode() == castOther.getFactoryCode()) || (this
						.getFactoryCode() != null
						&& castOther.getFactoryCode() != null && this
						.getFactoryCode().equals(castOther.getFactoryCode())))
				&& ((this.getEyeballingValue() == castOther
						.getEyeballingValue()) || (this.getEyeballingValue() != null
						&& castOther.getEyeballingValue() != null && this
						.getEyeballingValue().equals(
								castOther.getEyeballingValue())))
				&& ((this.getMagnifierValue() == castOther.getMagnifierValue()) || (this
						.getMagnifierValue() != null
						&& castOther.getMagnifierValue() != null && this
						.getMagnifierValue().equals(
								castOther.getMagnifierValue())))
				&& ((this.getHgbValue() == castOther.getHgbValue()) || (this
						.getHgbValue() != null
						&& castOther.getHgbValue() != null && this
						.getHgbValue().equals(castOther.getHgbValue())))
				&& ((this.getFunctionValue() == castOther.getFunctionValue()) || (this
						.getFunctionValue() != null
						&& castOther.getFunctionValue() != null && this
						.getFunctionValue()
						.equals(castOther.getFunctionValue())))
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
				&& ((this.getSourceAutoId() == castOther.getSourceAutoId()) || (this
						.getSourceAutoId() != null
						&& castOther.getSourceAutoId() != null && this
						.getSourceAutoId().equals(castOther.getSourceAutoId())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())))
				&& ((this.getMaker() == castOther.getMaker()) || (this
						.getMaker() != null
						&& castOther.getMaker() != null && this.getMaker()
						.equals(castOther.getMaker())));
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
				+ (getMaterialClass() == null ? 0 : this.getMaterialClass()
						.hashCode());
		result = 37
				* result
				+ (getMaterialId() == null ? 0 : this.getMaterialId()
						.hashCode());
		result = 37
				* result
				+ (getMaterialCode() == null ? 0 : this.getMaterialCode()
						.hashCode());
		result = 37
				* result
				+ (getMaterialName() == null ? 0 : this.getMaterialName()
						.hashCode());
		result = 37
				* result
				+ (getMaterialSpec() == null ? 0 : this.getMaterialSpec()
						.hashCode());
		result = 37
				* result
				+ (getMaterialUnits() == null ? 0 : this.getMaterialUnits()
						.hashCode());
		result = 37
				* result
				+ (getFactoryCode() == null ? 0 : this.getFactoryCode()
						.hashCode());
		result = 37
				* result
				+ (getEyeballingValue() == null ? 0 : this.getEyeballingValue()
						.hashCode());
		result = 37
				* result
				+ (getMagnifierValue() == null ? 0 : this.getMagnifierValue()
						.hashCode());
		result = 37 * result
				+ (getHgbValue() == null ? 0 : this.getHgbValue().hashCode());
		result = 37
				* result
				+ (getFunctionValue() == null ? 0 : this.getFunctionValue()
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
		result = 37
				* result
				+ (getSourceAutoId() == null ? 0 : this.getSourceAutoId()
						.hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getMaker() == null ? 0 : this.getMaker().hashCode());
		return result;
	}

	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

}