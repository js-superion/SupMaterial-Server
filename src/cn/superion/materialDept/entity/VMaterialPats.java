package cn.superion.materialDept.entity;

import java.util.Date;

/**
 * VMaterialPatsId entity. @author MyEclipse Persistence Tools
 */

public class VMaterialPats implements java.io.Serializable {

	// Fields

	private String autoId;
	private String unitsCode;
	private String billNo;
	private String billType;
	private String storageCode;
	private String operationNo;
	private String patientType;
	private String patientId;
	private String inpNo;
	private Byte visitId;
	private String personName;
	private String sex;
	private Date dateOfBirth;
	private Short age;
	private String ageUnits;
	private String idNo;
	private String bloodName;
	private String rhType;
	private Short bedNo;
	private String chargeType;
	private String deptCode;
	private String wardCode;
	private String clinicDiag;
	private String clinicDiagName;
	private String applyDoctor;
	private Date applyDate;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String currentStatus;
	private String detailAutoId;
	private Short serialNo;
	private String materialClass;
	private String barCode;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private Double applyAmount;
	private Double amount;
	private Double retailPrice;
	private Double retailMoney;
	private String factoryCode;
	private Date madeDate;
	private String batch;
	private Date availDate;
	private String hisBillNo;
	private String hisClass;
	private String hisCode;
	private String hisSpec;
	private String hisUnits;
	private String accounter;
	private Date accountDate;
	private String refundSign;
	private String refundOperator;
	private Date refundDate;
	private Double tradePrice;
	private Double tradeMoney;
	private Double wholeSalePrice;
	private Double wholeSaleMoney;
	private String factInSign;
	private String chargeSign;
	private String classOnAccount;
	private String materialBarCode;
	private String isGive;
	private String supplyDeptCode;
	private String currentReceiveSign;//来自本院的入库物资


	//扩展字段
	private String invoiceNo;
	private Date invoiceDate;
	
	
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

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getStorageCode() {
		return this.storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}

	public String getOperationNo() {
		return this.operationNo;
	}

	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}

	public String getPatientType() {
		return this.patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getInpNo() {
		return this.inpNo;
	}

	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}

	public Byte getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Byte visitId) {
		this.visitId = visitId;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Short getAge() {
		return this.age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public String getAgeUnits() {
		return this.ageUnits;
	}

	public void setAgeUnits(String ageUnits) {
		this.ageUnits = ageUnits;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getBloodName() {
		return this.bloodName;
	}

	public void setBloodName(String bloodName) {
		this.bloodName = bloodName;
	}

	public String getRhType() {
		return this.rhType;
	}

	public void setRhType(String rhType) {
		this.rhType = rhType;
	}

	public Short getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(Short bedNo) {
		this.bedNo = bedNo;
	}

	public String getChargeType() {
		return this.chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getWardCode() {
		return this.wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getClinicDiag() {
		return this.clinicDiag;
	}

	public void setClinicDiag(String clinicDiag) {
		this.clinicDiag = clinicDiag;
	}

	public String getClinicDiagName() {
		return this.clinicDiagName;
	}

	public void setClinicDiagName(String clinicDiagName) {
		this.clinicDiagName = clinicDiagName;
	}

	public String getApplyDoctor() {
		return this.applyDoctor;
	}

	public void setApplyDoctor(String applyDoctor) {
		this.applyDoctor = applyDoctor;
	}

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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

	public String getDetailAutoId() {
		return this.detailAutoId;
	}

	public void setDetailAutoId(String detailAutoId) {
		this.detailAutoId = detailAutoId;
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

	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	public Double getApplyAmount() {
		return this.applyAmount;
	}

	public void setApplyAmount(Double applyAmount) {
		this.applyAmount = applyAmount;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRetailPrice() {
		return this.retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getRetailMoney() {
		return this.retailMoney;
	}

	public void setRetailMoney(Double retailMoney) {
		this.retailMoney = retailMoney;
	}

	public String getFactoryCode() {
		return this.factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public Date getMadeDate() {
		return this.madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Date getAvailDate() {
		return this.availDate;
	}

	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}

	public String getHisBillNo() {
		return this.hisBillNo;
	}

	public void setHisBillNo(String hisBillNo) {
		this.hisBillNo = hisBillNo;
	}

	public String getHisClass() {
		return this.hisClass;
	}

	public void setHisClass(String hisClass) {
		this.hisClass = hisClass;
	}

	public String getHisCode() {
		return this.hisCode;
	}

	public void setHisCode(String hisCode) {
		this.hisCode = hisCode;
	}

	public String getHisSpec() {
		return this.hisSpec;
	}

	public void setHisSpec(String hisSpec) {
		this.hisSpec = hisSpec;
	}

	public String getHisUnits() {
		return this.hisUnits;
	}

	public void setHisUnits(String hisUnits) {
		this.hisUnits = hisUnits;
	}

	public String getAccounter() {
		return this.accounter;
	}

	public void setAccounter(String accounter) {
		this.accounter = accounter;
	}

	public Date getAccountDate() {
		return this.accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	public String getRefundSign() {
		return this.refundSign;
	}

	public void setRefundSign(String refundSign) {
		this.refundSign = refundSign;
	}

	public String getRefundOperator() {
		return this.refundOperator;
	}

	public void setRefundOperator(String refundOperator) {
		this.refundOperator = refundOperator;
	}

	public Date getRefundDate() {
		return this.refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public Double getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getTradeMoney() {
		return this.tradeMoney;
	}

	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public Double getWholeSalePrice() {
		return this.wholeSalePrice;
	}

	public void setWholeSalePrice(Double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}

	public Double getWholeSaleMoney() {
		return this.wholeSaleMoney;
	}

	public void setWholeSaleMoney(Double wholeSaleMoney) {
		this.wholeSaleMoney = wholeSaleMoney;
	}

	public String getFactInSign() {
		return this.factInSign;
	}

	public void setFactInSign(String factInSign) {
		this.factInSign = factInSign;
	}

	public String getChargeSign() {
		return this.chargeSign;
	}

	public void setChargeSign(String chargeSign) {
		this.chargeSign = chargeSign;
	}

	public String getClassOnAccount() {
		return this.classOnAccount;
	}

	public void setClassOnAccount(String classOnAccount) {
		this.classOnAccount = classOnAccount;
	}

	public String getMaterialBarCode() {
		return this.materialBarCode;
	}

	public void setMaterialBarCode(String materialBarCode) {
		this.materialBarCode = materialBarCode;
	}

	public String getIsGive() {
		return this.isGive;
	}

	public void setIsGive(String isGive) {
		this.isGive = isGive;
	}

	public String getSupplyDeptCode() {
		return this.supplyDeptCode;
	}

	public void setSupplyDeptCode(String supplyDeptCode) {
		this.supplyDeptCode = supplyDeptCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VMaterialPats))
			return false;
		VMaterialPats castOther = (VMaterialPats) other;

		return ((this.getAutoId() == castOther.getAutoId()) || (this
				.getAutoId() != null
				&& castOther.getAutoId() != null && this.getAutoId().equals(
				castOther.getAutoId())))
				&& ((this.getUnitsCode() == castOther.getUnitsCode()) || (this
						.getUnitsCode() != null
						&& castOther.getUnitsCode() != null && this
						.getUnitsCode().equals(castOther.getUnitsCode())))
				&& ((this.getBillNo() == castOther.getBillNo()) || (this
						.getBillNo() != null
						&& castOther.getBillNo() != null && this.getBillNo()
						.equals(castOther.getBillNo())))
				&& ((this.getBillType() == castOther.getBillType()) || (this
						.getBillType() != null
						&& castOther.getBillType() != null && this
						.getBillType().equals(castOther.getBillType())))
				&& ((this.getStorageCode() == castOther.getStorageCode()) || (this
						.getStorageCode() != null
						&& castOther.getStorageCode() != null && this
						.getStorageCode().equals(castOther.getStorageCode())))
				&& ((this.getOperationNo() == castOther.getOperationNo()) || (this
						.getOperationNo() != null
						&& castOther.getOperationNo() != null && this
						.getOperationNo().equals(castOther.getOperationNo())))
				&& ((this.getPatientType() == castOther.getPatientType()) || (this
						.getPatientType() != null
						&& castOther.getPatientType() != null && this
						.getPatientType().equals(castOther.getPatientType())))
				&& ((this.getPatientId() == castOther.getPatientId()) || (this
						.getPatientId() != null
						&& castOther.getPatientId() != null && this
						.getPatientId().equals(castOther.getPatientId())))
				&& ((this.getInpNo() == castOther.getInpNo()) || (this
						.getInpNo() != null
						&& castOther.getInpNo() != null && this.getInpNo()
						.equals(castOther.getInpNo())))
				&& ((this.getVisitId() == castOther.getVisitId()) || (this
						.getVisitId() != null
						&& castOther.getVisitId() != null && this.getVisitId()
						.equals(castOther.getVisitId())))
				&& ((this.getPersonName() == castOther.getPersonName()) || (this
						.getPersonName() != null
						&& castOther.getPersonName() != null && this
						.getPersonName().equals(castOther.getPersonName())))
				&& ((this.getSex() == castOther.getSex()) || (this.getSex() != null
						&& castOther.getSex() != null && this.getSex().equals(
						castOther.getSex())))
				&& ((this.getDateOfBirth() == castOther.getDateOfBirth()) || (this
						.getDateOfBirth() != null
						&& castOther.getDateOfBirth() != null && this
						.getDateOfBirth().equals(castOther.getDateOfBirth())))
				&& ((this.getAge() == castOther.getAge()) || (this.getAge() != null
						&& castOther.getAge() != null && this.getAge().equals(
						castOther.getAge())))
				&& ((this.getAgeUnits() == castOther.getAgeUnits()) || (this
						.getAgeUnits() != null
						&& castOther.getAgeUnits() != null && this
						.getAgeUnits().equals(castOther.getAgeUnits())))
				&& ((this.getIdNo() == castOther.getIdNo()) || (this.getIdNo() != null
						&& castOther.getIdNo() != null && this.getIdNo()
						.equals(castOther.getIdNo())))
				&& ((this.getBloodName() == castOther.getBloodName()) || (this
						.getBloodName() != null
						&& castOther.getBloodName() != null && this
						.getBloodName().equals(castOther.getBloodName())))
				&& ((this.getRhType() == castOther.getRhType()) || (this
						.getRhType() != null
						&& castOther.getRhType() != null && this.getRhType()
						.equals(castOther.getRhType())))
				&& ((this.getBedNo() == castOther.getBedNo()) || (this
						.getBedNo() != null
						&& castOther.getBedNo() != null && this.getBedNo()
						.equals(castOther.getBedNo())))
				&& ((this.getChargeType() == castOther.getChargeType()) || (this
						.getChargeType() != null
						&& castOther.getChargeType() != null && this
						.getChargeType().equals(castOther.getChargeType())))
				&& ((this.getDeptCode() == castOther.getDeptCode()) || (this
						.getDeptCode() != null
						&& castOther.getDeptCode() != null && this
						.getDeptCode().equals(castOther.getDeptCode())))
				&& ((this.getWardCode() == castOther.getWardCode()) || (this
						.getWardCode() != null
						&& castOther.getWardCode() != null && this
						.getWardCode().equals(castOther.getWardCode())))
				&& ((this.getClinicDiag() == castOther.getClinicDiag()) || (this
						.getClinicDiag() != null
						&& castOther.getClinicDiag() != null && this
						.getClinicDiag().equals(castOther.getClinicDiag())))
				&& ((this.getClinicDiagName() == castOther.getClinicDiagName()) || (this
						.getClinicDiagName() != null
						&& castOther.getClinicDiagName() != null && this
						.getClinicDiagName().equals(
								castOther.getClinicDiagName())))
				&& ((this.getApplyDoctor() == castOther.getApplyDoctor()) || (this
						.getApplyDoctor() != null
						&& castOther.getApplyDoctor() != null && this
						.getApplyDoctor().equals(castOther.getApplyDoctor())))
				&& ((this.getApplyDate() == castOther.getApplyDate()) || (this
						.getApplyDate() != null
						&& castOther.getApplyDate() != null && this
						.getApplyDate().equals(castOther.getApplyDate())))
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
						.equals(castOther.getCurrentStatus())))
				&& ((this.getDetailAutoId() == castOther.getDetailAutoId()) || (this
						.getDetailAutoId() != null
						&& castOther.getDetailAutoId() != null && this
						.getDetailAutoId().equals(castOther.getDetailAutoId())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getMaterialClass() == castOther.getMaterialClass()) || (this
						.getMaterialClass() != null
						&& castOther.getMaterialClass() != null && this
						.getMaterialClass()
						.equals(castOther.getMaterialClass())))
				&& ((this.getBarCode() == castOther.getBarCode()) || (this
						.getBarCode() != null
						&& castOther.getBarCode() != null && this.getBarCode()
						.equals(castOther.getBarCode())))
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
				&& ((this.getApplyAmount() == castOther.getApplyAmount()) || (this
						.getApplyAmount() != null
						&& castOther.getApplyAmount() != null && this
						.getApplyAmount().equals(castOther.getApplyAmount())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null
						&& castOther.getAmount() != null && this.getAmount()
						.equals(castOther.getAmount())))
				&& ((this.getRetailPrice() == castOther.getRetailPrice()) || (this
						.getRetailPrice() != null
						&& castOther.getRetailPrice() != null && this
						.getRetailPrice().equals(castOther.getRetailPrice())))
				&& ((this.getRetailMoney() == castOther.getRetailMoney()) || (this
						.getRetailMoney() != null
						&& castOther.getRetailMoney() != null && this
						.getRetailMoney().equals(castOther.getRetailMoney())))
				&& ((this.getFactoryCode() == castOther.getFactoryCode()) || (this
						.getFactoryCode() != null
						&& castOther.getFactoryCode() != null && this
						.getFactoryCode().equals(castOther.getFactoryCode())))
				&& ((this.getMadeDate() == castOther.getMadeDate()) || (this
						.getMadeDate() != null
						&& castOther.getMadeDate() != null && this
						.getMadeDate().equals(castOther.getMadeDate())))
				&& ((this.getBatch() == castOther.getBatch()) || (this
						.getBatch() != null
						&& castOther.getBatch() != null && this.getBatch()
						.equals(castOther.getBatch())))
				&& ((this.getAvailDate() == castOther.getAvailDate()) || (this
						.getAvailDate() != null
						&& castOther.getAvailDate() != null && this
						.getAvailDate().equals(castOther.getAvailDate())))
				&& ((this.getHisBillNo() == castOther.getHisBillNo()) || (this
						.getHisBillNo() != null
						&& castOther.getHisBillNo() != null && this
						.getHisBillNo().equals(castOther.getHisBillNo())))
				&& ((this.getHisClass() == castOther.getHisClass()) || (this
						.getHisClass() != null
						&& castOther.getHisClass() != null && this
						.getHisClass().equals(castOther.getHisClass())))
				&& ((this.getHisCode() == castOther.getHisCode()) || (this
						.getHisCode() != null
						&& castOther.getHisCode() != null && this.getHisCode()
						.equals(castOther.getHisCode())))
				&& ((this.getHisSpec() == castOther.getHisSpec()) || (this
						.getHisSpec() != null
						&& castOther.getHisSpec() != null && this.getHisSpec()
						.equals(castOther.getHisSpec())))
				&& ((this.getHisUnits() == castOther.getHisUnits()) || (this
						.getHisUnits() != null
						&& castOther.getHisUnits() != null && this
						.getHisUnits().equals(castOther.getHisUnits())))
				&& ((this.getAccounter() == castOther.getAccounter()) || (this
						.getAccounter() != null
						&& castOther.getAccounter() != null && this
						.getAccounter().equals(castOther.getAccounter())))
				&& ((this.getAccountDate() == castOther.getAccountDate()) || (this
						.getAccountDate() != null
						&& castOther.getAccountDate() != null && this
						.getAccountDate().equals(castOther.getAccountDate())))
				&& ((this.getRefundSign() == castOther.getRefundSign()) || (this
						.getRefundSign() != null
						&& castOther.getRefundSign() != null && this
						.getRefundSign().equals(castOther.getRefundSign())))
				&& ((this.getRefundOperator() == castOther.getRefundOperator()) || (this
						.getRefundOperator() != null
						&& castOther.getRefundOperator() != null && this
						.getRefundOperator().equals(
								castOther.getRefundOperator())))
				&& ((this.getRefundDate() == castOther.getRefundDate()) || (this
						.getRefundDate() != null
						&& castOther.getRefundDate() != null && this
						.getRefundDate().equals(castOther.getRefundDate())))
				&& ((this.getTradePrice() == castOther.getTradePrice()) || (this
						.getTradePrice() != null
						&& castOther.getTradePrice() != null && this
						.getTradePrice().equals(castOther.getTradePrice())))
				&& ((this.getTradeMoney() == castOther.getTradeMoney()) || (this
						.getTradeMoney() != null
						&& castOther.getTradeMoney() != null && this
						.getTradeMoney().equals(castOther.getTradeMoney())))
				&& ((this.getWholeSalePrice() == castOther.getWholeSalePrice()) || (this
						.getWholeSalePrice() != null
						&& castOther.getWholeSalePrice() != null && this
						.getWholeSalePrice().equals(
								castOther.getWholeSalePrice())))
				&& ((this.getWholeSaleMoney() == castOther.getWholeSaleMoney()) || (this
						.getWholeSaleMoney() != null
						&& castOther.getWholeSaleMoney() != null && this
						.getWholeSaleMoney().equals(
								castOther.getWholeSaleMoney())))
				&& ((this.getFactInSign() == castOther.getFactInSign()) || (this
						.getFactInSign() != null
						&& castOther.getFactInSign() != null && this
						.getFactInSign().equals(castOther.getFactInSign())))
				&& ((this.getChargeSign() == castOther.getChargeSign()) || (this
						.getChargeSign() != null
						&& castOther.getChargeSign() != null && this
						.getChargeSign().equals(castOther.getChargeSign())))
				&& ((this.getClassOnAccount() == castOther.getClassOnAccount()) || (this
						.getClassOnAccount() != null
						&& castOther.getClassOnAccount() != null && this
						.getClassOnAccount().equals(
								castOther.getClassOnAccount())))
				&& ((this.getMaterialBarCode() == castOther
						.getMaterialBarCode()) || (this.getMaterialBarCode() != null
						&& castOther.getMaterialBarCode() != null && this
						.getMaterialBarCode().equals(
								castOther.getMaterialBarCode())))
				&& ((this.getIsGive() == castOther.getIsGive()) || (this
						.getIsGive() != null
						&& castOther.getIsGive() != null && this.getIsGive()
						.equals(castOther.getIsGive())))
				&& ((this.getSupplyDeptCode() == castOther.getSupplyDeptCode()) || (this
						.getSupplyDeptCode() != null
						&& castOther.getSupplyDeptCode() != null && this
						.getSupplyDeptCode().equals(
								castOther.getSupplyDeptCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAutoId() == null ? 0 : this.getAutoId().hashCode());
		result = 37 * result
				+ (getUnitsCode() == null ? 0 : this.getUnitsCode().hashCode());
		result = 37 * result
				+ (getBillNo() == null ? 0 : this.getBillNo().hashCode());
		result = 37 * result
				+ (getBillType() == null ? 0 : this.getBillType().hashCode());
		result = 37
				* result
				+ (getStorageCode() == null ? 0 : this.getStorageCode()
						.hashCode());
		result = 37
				* result
				+ (getOperationNo() == null ? 0 : this.getOperationNo()
						.hashCode());
		result = 37
				* result
				+ (getPatientType() == null ? 0 : this.getPatientType()
						.hashCode());
		result = 37 * result
				+ (getPatientId() == null ? 0 : this.getPatientId().hashCode());
		result = 37 * result
				+ (getInpNo() == null ? 0 : this.getInpNo().hashCode());
		result = 37 * result
				+ (getVisitId() == null ? 0 : this.getVisitId().hashCode());
		result = 37
				* result
				+ (getPersonName() == null ? 0 : this.getPersonName()
						.hashCode());
		result = 37 * result
				+ (getSex() == null ? 0 : this.getSex().hashCode());
		result = 37
				* result
				+ (getDateOfBirth() == null ? 0 : this.getDateOfBirth()
						.hashCode());
		result = 37 * result
				+ (getAge() == null ? 0 : this.getAge().hashCode());
		result = 37 * result
				+ (getAgeUnits() == null ? 0 : this.getAgeUnits().hashCode());
		result = 37 * result
				+ (getIdNo() == null ? 0 : this.getIdNo().hashCode());
		result = 37 * result
				+ (getBloodName() == null ? 0 : this.getBloodName().hashCode());
		result = 37 * result
				+ (getRhType() == null ? 0 : this.getRhType().hashCode());
		result = 37 * result
				+ (getBedNo() == null ? 0 : this.getBedNo().hashCode());
		result = 37
				* result
				+ (getChargeType() == null ? 0 : this.getChargeType()
						.hashCode());
		result = 37 * result
				+ (getDeptCode() == null ? 0 : this.getDeptCode().hashCode());
		result = 37 * result
				+ (getWardCode() == null ? 0 : this.getWardCode().hashCode());
		result = 37
				* result
				+ (getClinicDiag() == null ? 0 : this.getClinicDiag()
						.hashCode());
		result = 37
				* result
				+ (getClinicDiagName() == null ? 0 : this.getClinicDiagName()
						.hashCode());
		result = 37
				* result
				+ (getApplyDoctor() == null ? 0 : this.getApplyDoctor()
						.hashCode());
		result = 37 * result
				+ (getApplyDate() == null ? 0 : this.getApplyDate().hashCode());
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
		result = 37
				* result
				+ (getDetailAutoId() == null ? 0 : this.getDetailAutoId()
						.hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37
				* result
				+ (getMaterialClass() == null ? 0 : this.getMaterialClass()
						.hashCode());
		result = 37 * result
				+ (getBarCode() == null ? 0 : this.getBarCode().hashCode());
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
				+ (getApplyAmount() == null ? 0 : this.getApplyAmount()
						.hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37
				* result
				+ (getRetailPrice() == null ? 0 : this.getRetailPrice()
						.hashCode());
		result = 37
				* result
				+ (getRetailMoney() == null ? 0 : this.getRetailMoney()
						.hashCode());
		result = 37
				* result
				+ (getFactoryCode() == null ? 0 : this.getFactoryCode()
						.hashCode());
		result = 37 * result
				+ (getMadeDate() == null ? 0 : this.getMadeDate().hashCode());
		result = 37 * result
				+ (getBatch() == null ? 0 : this.getBatch().hashCode());
		result = 37 * result
				+ (getAvailDate() == null ? 0 : this.getAvailDate().hashCode());
		result = 37 * result
				+ (getHisBillNo() == null ? 0 : this.getHisBillNo().hashCode());
		result = 37 * result
				+ (getHisClass() == null ? 0 : this.getHisClass().hashCode());
		result = 37 * result
				+ (getHisCode() == null ? 0 : this.getHisCode().hashCode());
		result = 37 * result
				+ (getHisSpec() == null ? 0 : this.getHisSpec().hashCode());
		result = 37 * result
				+ (getHisUnits() == null ? 0 : this.getHisUnits().hashCode());
		result = 37 * result
				+ (getAccounter() == null ? 0 : this.getAccounter().hashCode());
		result = 37
				* result
				+ (getAccountDate() == null ? 0 : this.getAccountDate()
						.hashCode());
		result = 37
				* result
				+ (getRefundSign() == null ? 0 : this.getRefundSign()
						.hashCode());
		result = 37
				* result
				+ (getRefundOperator() == null ? 0 : this.getRefundOperator()
						.hashCode());
		result = 37
				* result
				+ (getRefundDate() == null ? 0 : this.getRefundDate()
						.hashCode());
		result = 37
				* result
				+ (getTradePrice() == null ? 0 : this.getTradePrice()
						.hashCode());
		result = 37
				* result
				+ (getTradeMoney() == null ? 0 : this.getTradeMoney()
						.hashCode());
		result = 37
				* result
				+ (getWholeSalePrice() == null ? 0 : this.getWholeSalePrice()
						.hashCode());
		result = 37
				* result
				+ (getWholeSaleMoney() == null ? 0 : this.getWholeSaleMoney()
						.hashCode());
		result = 37
				* result
				+ (getFactInSign() == null ? 0 : this.getFactInSign()
						.hashCode());
		result = 37
				* result
				+ (getChargeSign() == null ? 0 : this.getChargeSign()
						.hashCode());
		result = 37
				* result
				+ (getClassOnAccount() == null ? 0 : this.getClassOnAccount()
						.hashCode());
		result = 37
				* result
				+ (getMaterialBarCode() == null ? 0 : this.getMaterialBarCode()
						.hashCode());
		result = 37 * result
				+ (getIsGive() == null ? 0 : this.getIsGive().hashCode());
		result = 37
				* result
				+ (getSupplyDeptCode() == null ? 0 : this.getSupplyDeptCode()
						.hashCode());
		return result;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getCurrentReceiveSign() {
		return currentReceiveSign;
	}

	public void setCurrentReceiveSign(String currentReceiveSign) {
		this.currentReceiveSign = currentReceiveSign;
	}

}