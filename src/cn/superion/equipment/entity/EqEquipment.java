package cn.superion.equipment.entity;

import java.util.Date;

/**
 * EqEquipment entity. @author MyEclipse Persistence Tools
 */

public class EqEquipment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1460444427131360998L;
	private String autoId;
	private String unitsCode;
	private String equipmentCode;
	private String equipmentName;
	private String equipmentSpec;
	private String equipmentClass;
	private String eqClassName;
	
	private String nationClass;
	private String equipmentType;
	private String classAbc;
	private String fatherCode;
	private String equipmentStatus;
	private String eqStatusName;
	private String positionCode;
	private String positionCodeName;
	private String serialNumber;
	private String figureNumber;
	private String usedDept;
	private String usedDeptName;
	private String jobDept;
	private String jobDeptName;
	private String powerUnit;
	private Double motorPower;
	private Integer motorNum;
	private Double systemTime;
	private String supplier;
	private String supplierName;
	private String manufacturer;
	private String manufacturerName;
	private Date dateOfProduction;
	private Date dateOfPurchase;
	private Date dateOfUsed;
	private Date dateOfSetup;
	private Integer serviceLife;
	private Date guaranteeCutOffDate;
	private Double originalValue;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private String phoInputCode;
	private String fiveInputCode;
	
	private String testCertifyNo;
	

	
	private String brandName;
	private String measureSign;
	
	private String chargePerson;
	private String chargePersonName;
	private String testUnit;
	
	private Date testDate;
	private Date testValDate;
	private String testRes;
	
	
	
	public String dateOfProductionName;
	public String dateOfPurchaseName;
	public String dateOfUsedName;
	public String dateOfSetupName;
	public String guaranteeCutOffDateName;
	public String makeDateName;
	public String verifyDateName;
	public String testDateName;
	public String testValDateName;
	
	// Constructors

	/** default constructor */
	public EqEquipment() {
	}

	/** minimal constructor */
	public EqEquipment(String autoId, String unitsCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
	}

	/** full constructor */
	public EqEquipment(String autoId, String unitsCode, String equipmentCode,
			String equipmentName, String equipmentSpec, String equipmentClass,
			String nationClass, String equipmentType, String classAbc,
			String fatherCode, String equipmentStatus, String positionCode,
			String serialNumber, String figureNumber, String usedDept,
			String jobDept, String powerUnit, Double motorPower,
			Integer motorNum, Double systemTime, String supplier,
			String brandName,String measureSign,String chargePerson,String testUnit,
			String testRes,Date testDate,Date testValDate,String testCertifyNo,
			String manufacturer, Date dateOfProduction, Date dateOfPurchase,
			Date dateOfUsed, Date dateOfSetup, Integer serviceLife,
			Date guaranteeCutOffDate, Double originalValue, String remark,
			String maker, Date makeDate, String verifier, Date verifyDate,
			String currentStatus, String phoInputCode, String fiveInputCode) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.equipmentCode = equipmentCode;
		this.equipmentName = equipmentName;
		this.equipmentSpec = equipmentSpec;
		this.equipmentClass = equipmentClass;
		this.nationClass = nationClass;
		this.equipmentType = equipmentType;
		this.classAbc = classAbc;
		this.fatherCode = fatherCode;
		this.equipmentStatus = equipmentStatus;
		this.positionCode = positionCode;
		this.serialNumber = serialNumber;
		this.figureNumber = figureNumber;
		this.usedDept = usedDept;
		this.jobDept = jobDept;
		this.testCertifyNo = testCertifyNo;
		this.powerUnit = powerUnit;
		this.motorPower = motorPower;
		this.motorNum = motorNum;
		this.systemTime = systemTime;
		this.supplier = supplier;
		this.manufacturer = manufacturer;
		this.dateOfProduction = dateOfProduction;
		this.dateOfPurchase = dateOfPurchase;
		this.dateOfUsed = dateOfUsed;
		this.dateOfSetup = dateOfSetup;
		this.serviceLife = serviceLife;
		this.guaranteeCutOffDate = guaranteeCutOffDate;
		this.originalValue = originalValue;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.currentStatus = currentStatus;
		this.phoInputCode = phoInputCode;
		this.fiveInputCode = fiveInputCode;
		
		this.testDate = testDate;
		this.testValDate = testValDate;
		this.testRes = testRes;
		this.chargePerson = chargePerson;
		this.measureSign = measureSign;
		this.brandName = brandName;
		this.testUnit = testUnit;
		
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getEquipmentCode() {
		return this.equipmentCode;
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

	public String getEquipmentSpec() {
		return this.equipmentSpec;
	}

	public void setEquipmentSpec(String equipmentSpec) {
		this.equipmentSpec = equipmentSpec;
	}

	public String getEquipmentClass() {
		return this.equipmentClass;
	}

	public void setEquipmentClass(String equipmentClass) {
		this.equipmentClass = equipmentClass;
	}

	public String getNationClass() {
		return nationClass;
	}

	public void setNationClass(String nationClass) {
		this.nationClass = nationClass;
	}

	public String getEquipmentType() {
		return this.equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getClassAbc() {
		return this.classAbc;
	}

	public void setClassAbc(String classAbc) {
		this.classAbc = classAbc;
	}

	public String getFatherCode() {
		return this.fatherCode;
	}

	public void setFatherCode(String fatherCode) {
		this.fatherCode = fatherCode;
	}

	public String getEquipmentStatus() {
		return this.equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public String getPositionCode() {
		return this.positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFigureNumber() {
		return this.figureNumber;
	}

	public void setFigureNumber(String figureNumber) {
		this.figureNumber = figureNumber;
	}

	public String getUsedDept() {
		return this.usedDept;
	}

	public void setUsedDept(String usedDept) {
		this.usedDept = usedDept;
	}

	public String getJobDept() {
		return this.jobDept;
	}

	public void setJobDept(String jobDept) {
		this.jobDept = jobDept;
	}

	public String getPowerUnit() {
		return this.powerUnit;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getMeasureSign() {
		return measureSign;
	}

	public void setMeasureSign(String measureSign) {
		this.measureSign = measureSign;
	}

	public String getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getTestUnit() {
		return testUnit;
	}

	public void setTestUnit(String testUnit) {
		this.testUnit = testUnit;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Date getTestValDate() {
		return testValDate;
	}

	public void setTestValDate(Date testValDate) {
		this.testValDate = testValDate;
	}

	public String getTestRes() {
		return testRes;
	}

	public void setTestRes(String testRes) {
		this.testRes = testRes;
	}

	public void setPowerUnit(String powerUnit) {
		this.powerUnit = powerUnit;
	}

	public Double getMotorPower() {
		return this.motorPower;
	}

	public void setMotorPower(Double motorPower) {
		this.motorPower = motorPower;
	}

	public Integer getMotorNum() {
		return this.motorNum;
	}

	public void setMotorNum(Integer motorNum) {
		this.motorNum = motorNum;
	}

	public Double getSystemTime() {
		return this.systemTime;
	}

	public void setSystemTime(Double systemTime) {
		this.systemTime = systemTime;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getEqClassName() {
		return eqClassName;
	}

	public void setEqClassName(String eqClassName) {
		this.eqClassName = eqClassName;
	}

	public String getEqStatusName() {
		return eqStatusName;
	}

	public void setEqStatusName(String eqStatusName) {
		this.eqStatusName = eqStatusName;
	}

	public String getPositionCodeName() {
		return positionCodeName;
	}

	public void setPositionCodeName(String positionCodeName) {
		this.positionCodeName = positionCodeName;
	}

	public String getUsedDeptName() {
		return usedDeptName;
	}

	public void setUsedDeptName(String usedDeptName) {
		this.usedDeptName = usedDeptName;
	}

	public String getJobDeptName() {
		return jobDeptName;
	}

	public void setJobDeptName(String jobDeptName) {
		this.jobDeptName = jobDeptName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getChargePersonName() {
		return chargePersonName;
	}

	public void setChargePersonName(String chargePersonName) {
		this.chargePersonName = chargePersonName;
	}

	public Date getDateOfProduction() {
		return this.dateOfProduction;
	}

	public void setDateOfProduction(Date dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}

	public Date getDateOfPurchase() {
		return this.dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Date getDateOfUsed() {
		return this.dateOfUsed;
	}

	public void setDateOfUsed(Date dateOfUsed) {
		this.dateOfUsed = dateOfUsed;
	}

	public Date getDateOfSetup() {
		return this.dateOfSetup;
	}

	public String getDateOfProductionName() {
		return dateOfProductionName;
	}

	public void setDateOfProductionName(String dateOfProductionName) {
		this.dateOfProductionName = dateOfProductionName;
	}

	public String getDateOfPurchaseName() {
		return dateOfPurchaseName;
	}

	public void setDateOfPurchaseName(String dateOfPurchaseName) {
		this.dateOfPurchaseName = dateOfPurchaseName;
	}

	public String getDateOfUsedName() {
		return dateOfUsedName;
	}

	public void setDateOfUsedName(String dateOfUsedName) {
		this.dateOfUsedName = dateOfUsedName;
	}

	public String getDateOfSetupName() {
		return dateOfSetupName;
	}

	public void setDateOfSetupName(String dateOfSetupName) {
		this.dateOfSetupName = dateOfSetupName;
	}

	public String getGuaranteeCutOffDateName() {
		return guaranteeCutOffDateName;
	}

	public void setGuaranteeCutOffDateName(String guaranteeCutOffDateName) {
		this.guaranteeCutOffDateName = guaranteeCutOffDateName;
	}

	public String getMakeDateName() {
		return makeDateName;
	}

	public void setMakeDateName(String makeDateName) {
		this.makeDateName = makeDateName;
	}

	public String getVerifyDateName() {
		return verifyDateName;
	}

	public void setVerifyDateName(String verifyDateName) {
		this.verifyDateName = verifyDateName;
	}

	public String getTestDateName() {
		return testDateName;
	}

	public void setTestDateName(String testDateName) {
		this.testDateName = testDateName;
	}

	public String getTestValDateName() {
		return testValDateName;
	}

	public void setTestValDateName(String testValDateName) {
		this.testValDateName = testValDateName;
	}

	public void setDateOfSetup(Date dateOfSetup) {
		this.dateOfSetup = dateOfSetup;
	}

	public Integer getServiceLife() {
		return this.serviceLife;
	}

	public void setServiceLife(Integer serviceLife) {
		this.serviceLife = serviceLife;
	}

	public Date getGuaranteeCutOffDate() {
		return this.guaranteeCutOffDate;
	}

	public void setGuaranteeCutOffDate(Date guaranteeCutOffDate) {
		this.guaranteeCutOffDate = guaranteeCutOffDate;
	}

	public Double getOriginalValue() {
		return this.originalValue;
	}

	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}

	public String getRemark() {
		return this.remark;
	}

	public String getTestCertifyNo() {
		return testCertifyNo;
	}

	public void setTestCertifyNo(String testCertifyNo) {
		this.testCertifyNo = testCertifyNo;
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

	public String getPhoInputCode() {
		return phoInputCode;
	}

	public void setPhoInputCode(String phoInputCode) {
		this.phoInputCode = phoInputCode;
	}

	public String getFiveInputCode() {
		return fiveInputCode;
	}

	public void setFiveInputCode(String fiveInputCode) {
		this.fiveInputCode = fiveInputCode;
	}

}