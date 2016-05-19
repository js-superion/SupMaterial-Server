package cn.superion.cssd.entity;

import java.util.Date;

/**
 * CssdWashQualityDetail entity. @author MyEclipse Persistence Tools
 */

public class CssdWashQualityDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1066378494832222422L;
	private String autoId;
	private String mainAutoId;
	private Short serialNo;
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

	// Constructors

	/** default constructor */
	public CssdWashQualityDetail() {
	}

	/** minimal constructor */
	public CssdWashQualityDetail(String autoId, String mainAutoId,
			Short serialNo, String materialClass, String materialId,
			String materialCode, String materialName) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public CssdWashQualityDetail(String autoId, String mainAutoId,
			Short serialNo, String materialClass, String materialId,
			String materialCode, String materialName, String materialSpec,
			String materialUnits, String factoryCode, String eyeballingValue,
			String magnifierValue, String hgbValue, String functionValue,
			String detailRemark, String resultValue, String checker,
			Date checkDate, String sourceAutoId) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
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

}