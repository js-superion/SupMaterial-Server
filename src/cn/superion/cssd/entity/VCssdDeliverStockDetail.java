package cn.superion.cssd.entity;

import java.util.Date;

/**
 * VCssdDeliverStockDetailId entity. @author MyEclipse Persistence Tools
 */


public class VCssdDeliverStockDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5882695808306131179L;
	private String autoId;
	private String unitsCode;
	private String deptUnitsCode;
	private String billNo;
	private Date billDate;
	private String deptCode;
	private String personId;
	private String deliverPerson;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String deliverCurrentStatus;
	private String packageNo;
	private String packageClass;
	private String packageId;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	private Double packageTradePrice;
	private Double packageAmount;
	private Double materialFee;
	private Double sterilizeFee;
	private Date availDate;
	private String packager;
	private String sterilizeNo;
	private String sterilizeOrder;
	private String sterilizeType;
	private Date sterilizeDate;
	private String sterilizeStatus;
	private String detailRemark;
	private String phoInputCode;
	private String fiveInputCode;
	private String usedSign;
	private String currentStatus;
	private String packageAutoId;
	private Short packageSerialNo;
	private String sterilizeAutoId;
	private Short sterilizeSerialNo;
	private String deliverAutoId;
	private Short deliverSerialNo;
	private Short serialNo;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private Double tradePrice;
	private Double amount;
	private Double tradeMoney;
	private String materialSign;
	private String retrieveSign;
	private String sourceAutoId;

	// Constructors

	/** default constructor */
	public VCssdDeliverStockDetail() {
	}

	/** minimal constructor */
	public VCssdDeliverStockDetail(String autoId, String unitsCode,
			String packageNo, String packageClass, String packageId,
			Short serialNo, String materialClass, String materialId,
			String materialCode, String materialName) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.packageNo = packageNo;
		this.packageClass = packageClass;
		this.packageId = packageId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public VCssdDeliverStockDetail(String autoId, String unitsCode,String deptUnitsCode,
			String billNo, Date billDate, String deptCode, String personId,
			String deliverPerson, String remark, String maker, Date makeDate,
			String verifier, Date verifyDate, String deliverCurrentStatus,
			String packageNo, String packageClass, String packageId,
			String packageName, String packageMode, String packageUnits,
			Double packageTradePrice, Double packageAmount, Double materialFee,
			Double sterilizeFee, Date availDate, String packager,
			String sterilizeNo, String sterilizeOrder, String sterilizeType,
			Date sterilizeDate, String sterilizeStatus, String detailRemark,
			String usedSign, String currentStatus, String packageAutoId,
			Short packageSerialNo, String sterilizeAutoId,
			Short sterilizeSerialNo, String deliverAutoId,
			Short deliverSerialNo, Short serialNo, String materialClass,
			String materialId, String materialCode, String materialName,
			String materialSpec, String materialUnits, String factoryCode,
			Double tradePrice, Double amount, Double tradeMoney,
			String materialSign, String retrieveSign, String sourceAutoId) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.deptUnitsCode = deptUnitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.deptCode = deptCode;
		this.personId = personId;
		this.deliverPerson = deliverPerson;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.deliverCurrentStatus = deliverCurrentStatus;
		this.packageNo = packageNo;
		this.packageClass = packageClass;
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageMode = packageMode;
		this.packageUnits = packageUnits;
		this.packageTradePrice = packageTradePrice;
		this.packageAmount = packageAmount;
		this.materialFee = materialFee;
		this.sterilizeFee = sterilizeFee;
		this.availDate = availDate;
		this.packager = packager;
		this.sterilizeNo = sterilizeNo;
		this.sterilizeOrder = sterilizeOrder;
		this.sterilizeType = sterilizeType;
		this.sterilizeDate = sterilizeDate;
		this.sterilizeStatus = sterilizeStatus;
		this.detailRemark = detailRemark;
		this.usedSign = usedSign;
		this.currentStatus = currentStatus;
		this.packageAutoId = packageAutoId;
		this.packageSerialNo = packageSerialNo;
		this.sterilizeAutoId = sterilizeAutoId;
		this.sterilizeSerialNo = sterilizeSerialNo;
		this.deliverAutoId = deliverAutoId;
		this.deliverSerialNo = deliverSerialNo;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.factoryCode = factoryCode;
		this.tradePrice = tradePrice;
		this.amount = amount;
		this.tradeMoney = tradeMoney;
		this.materialSign = materialSign;
		this.retrieveSign = retrieveSign;
		this.sourceAutoId = sourceAutoId;
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

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDeliverPerson() {
		return this.deliverPerson;
	}

	public void setDeliverPerson(String deliverPerson) {
		this.deliverPerson = deliverPerson;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeptUnitsCode() {
		return deptUnitsCode;
	}

	public void setDeptUnitsCode(String deptUnitsCode) {
		this.deptUnitsCode = deptUnitsCode;
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

	public String getDeliverCurrentStatus() {
		return this.deliverCurrentStatus;
	}

	public void setDeliverCurrentStatus(String deliverCurrentStatus) {
		this.deliverCurrentStatus = deliverCurrentStatus;
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

	public Double getPackageTradePrice() {
		return this.packageTradePrice;
	}

	public void setPackageTradePrice(Double packageTradePrice) {
		this.packageTradePrice = packageTradePrice;
	}

	public Double getPackageAmount() {
		return this.packageAmount;
	}

	public void setPackageAmount(Double packageAmount) {
		this.packageAmount = packageAmount;
	}

	public Double getMaterialFee() {
		return this.materialFee;
	}

	public void setMaterialFee(Double materialFee) {
		this.materialFee = materialFee;
	}

	public Double getSterilizeFee() {
		return this.sterilizeFee;
	}

	public void setSterilizeFee(Double sterilizeFee) {
		this.sterilizeFee = sterilizeFee;
	}

	public Date getAvailDate() {
		return this.availDate;
	}

	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}

	public String getPackager() {
		return this.packager;
	}

	public void setPackager(String packager) {
		this.packager = packager;
	}

	public String getSterilizeNo() {
		return this.sterilizeNo;
	}

	public void setSterilizeNo(String sterilizeNo) {
		this.sterilizeNo = sterilizeNo;
	}

	public String getSterilizeOrder() {
		return this.sterilizeOrder;
	}

	public void setSterilizeOrder(String sterilizeOrder) {
		this.sterilizeOrder = sterilizeOrder;
	}

	public String getSterilizeType() {
		return this.sterilizeType;
	}

	public void setSterilizeType(String sterilizeType) {
		this.sterilizeType = sterilizeType;
	}

	public Date getSterilizeDate() {
		return this.sterilizeDate;
	}

	public void setSterilizeDate(Date sterilizeDate) {
		this.sterilizeDate = sterilizeDate;
	}

	public String getSterilizeStatus() {
		return this.sterilizeStatus;
	}

	public void setSterilizeStatus(String sterilizeStatus) {
		this.sterilizeStatus = sterilizeStatus;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public String getUsedSign() {
		return this.usedSign;
	}

	public void setUsedSign(String usedSign) {
		this.usedSign = usedSign;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getPackageAutoId() {
		return this.packageAutoId;
	}

	public void setPackageAutoId(String packageAutoId) {
		this.packageAutoId = packageAutoId;
	}

	public Short getPackageSerialNo() {
		return this.packageSerialNo;
	}

	public void setPackageSerialNo(Short packageSerialNo) {
		this.packageSerialNo = packageSerialNo;
	}

	public String getSterilizeAutoId() {
		return this.sterilizeAutoId;
	}

	public void setSterilizeAutoId(String sterilizeAutoId) {
		this.sterilizeAutoId = sterilizeAutoId;
	}

	public Short getSterilizeSerialNo() {
		return this.sterilizeSerialNo;
	}

	public void setSterilizeSerialNo(Short sterilizeSerialNo) {
		this.sterilizeSerialNo = sterilizeSerialNo;
	}

	public String getDeliverAutoId() {
		return this.deliverAutoId;
	}

	public void setDeliverAutoId(String deliverAutoId) {
		this.deliverAutoId = deliverAutoId;
	}

	public Short getDeliverSerialNo() {
		return this.deliverSerialNo;
	}

	public void setDeliverSerialNo(Short deliverSerialNo) {
		this.deliverSerialNo = deliverSerialNo;
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

	public Double getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTradeMoney() {
		return this.tradeMoney;
	}

	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getMaterialSign() {
		return this.materialSign;
	}

	public void setMaterialSign(String materialSign) {
		this.materialSign = materialSign;
	}

	public String getRetrieveSign() {
		return this.retrieveSign;
	}

	public void setRetrieveSign(String retrieveSign) {
		this.retrieveSign = retrieveSign;
	}

	public String getSourceAutoId() {
		return this.sourceAutoId;
	}

	public void setSourceAutoId(String sourceAutoId) {
		this.sourceAutoId = sourceAutoId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result
				+ ((availDate == null) ? 0 : availDate.hashCode());
		result = prime * result
				+ ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime * result
				+ ((currentStatus == null) ? 0 : currentStatus.hashCode());
		result = prime * result
				+ ((deliverAutoId == null) ? 0 : deliverAutoId.hashCode());
		result = prime
				* result
				+ ((deliverCurrentStatus == null) ? 0 : deliverCurrentStatus
						.hashCode());
		result = prime * result
				+ ((deliverPerson == null) ? 0 : deliverPerson.hashCode());
		result = prime * result
				+ ((deliverSerialNo == null) ? 0 : deliverSerialNo.hashCode());
		result = prime * result
				+ ((deptCode == null) ? 0 : deptCode.hashCode());
		result = prime * result
				+ ((detailRemark == null) ? 0 : detailRemark.hashCode());
		result = prime * result
				+ ((factoryCode == null) ? 0 : factoryCode.hashCode());
		result = prime * result
				+ ((fiveInputCode == null) ? 0 : fiveInputCode.hashCode());
		result = prime * result
				+ ((makeDate == null) ? 0 : makeDate.hashCode());
		result = prime * result + ((maker == null) ? 0 : maker.hashCode());
		result = prime * result
				+ ((materialClass == null) ? 0 : materialClass.hashCode());
		result = prime * result
				+ ((materialCode == null) ? 0 : materialCode.hashCode());
		result = prime * result
				+ ((materialFee == null) ? 0 : materialFee.hashCode());
		result = prime * result
				+ ((materialId == null) ? 0 : materialId.hashCode());
		result = prime * result
				+ ((materialName == null) ? 0 : materialName.hashCode());
		result = prime * result
				+ ((materialSign == null) ? 0 : materialSign.hashCode());
		result = prime * result
				+ ((materialSpec == null) ? 0 : materialSpec.hashCode());
		result = prime * result
				+ ((materialUnits == null) ? 0 : materialUnits.hashCode());
		result = prime * result
				+ ((packageAmount == null) ? 0 : packageAmount.hashCode());
		result = prime * result
				+ ((packageAutoId == null) ? 0 : packageAutoId.hashCode());
		result = prime * result
				+ ((packageClass == null) ? 0 : packageClass.hashCode());
		result = prime * result
				+ ((packageId == null) ? 0 : packageId.hashCode());
		result = prime * result
				+ ((packageMode == null) ? 0 : packageMode.hashCode());
		result = prime * result
				+ ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result
				+ ((packageNo == null) ? 0 : packageNo.hashCode());
		result = prime * result
				+ ((packageSerialNo == null) ? 0 : packageSerialNo.hashCode());
		result = prime
				* result
				+ ((packageTradePrice == null) ? 0 : packageTradePrice
						.hashCode());
		result = prime * result
				+ ((packageUnits == null) ? 0 : packageUnits.hashCode());
		result = prime * result
				+ ((packager == null) ? 0 : packager.hashCode());
		result = prime * result
				+ ((personId == null) ? 0 : personId.hashCode());
		result = prime * result
				+ ((phoInputCode == null) ? 0 : phoInputCode.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((retrieveSign == null) ? 0 : retrieveSign.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		result = prime * result
				+ ((sourceAutoId == null) ? 0 : sourceAutoId.hashCode());
		result = prime * result
				+ ((sterilizeAutoId == null) ? 0 : sterilizeAutoId.hashCode());
		result = prime * result
				+ ((sterilizeDate == null) ? 0 : sterilizeDate.hashCode());
		result = prime * result
				+ ((sterilizeFee == null) ? 0 : sterilizeFee.hashCode());
		result = prime * result
				+ ((sterilizeNo == null) ? 0 : sterilizeNo.hashCode());
		result = prime * result
				+ ((sterilizeOrder == null) ? 0 : sterilizeOrder.hashCode());
		result = prime
				* result
				+ ((sterilizeSerialNo == null) ? 0 : sterilizeSerialNo
						.hashCode());
		result = prime * result
				+ ((sterilizeStatus == null) ? 0 : sterilizeStatus.hashCode());
		result = prime * result
				+ ((sterilizeType == null) ? 0 : sterilizeType.hashCode());
		result = prime * result
				+ ((tradeMoney == null) ? 0 : tradeMoney.hashCode());
		result = prime * result
				+ ((tradePrice == null) ? 0 : tradePrice.hashCode());
		result = prime * result
				+ ((unitsCode == null) ? 0 : unitsCode.hashCode());
		result = prime * result
				+ ((usedSign == null) ? 0 : usedSign.hashCode());
		result = prime * result
				+ ((verifier == null) ? 0 : verifier.hashCode());
		result = prime * result
				+ ((verifyDate == null) ? 0 : verifyDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VCssdDeliverStockDetail other = (VCssdDeliverStockDetail) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (autoId == null) {
			if (other.autoId != null)
				return false;
		} else if (!autoId.equals(other.autoId))
			return false;
		if (availDate == null) {
			if (other.availDate != null)
				return false;
		} else if (!availDate.equals(other.availDate))
			return false;
		if (billDate == null) {
			if (other.billDate != null)
				return false;
		} else if (!billDate.equals(other.billDate))
			return false;
		if (billNo == null) {
			if (other.billNo != null)
				return false;
		} else if (!billNo.equals(other.billNo))
			return false;
		if (currentStatus == null) {
			if (other.currentStatus != null)
				return false;
		} else if (!currentStatus.equals(other.currentStatus))
			return false;
		if (deliverAutoId == null) {
			if (other.deliverAutoId != null)
				return false;
		} else if (!deliverAutoId.equals(other.deliverAutoId))
			return false;
		if (deliverCurrentStatus == null) {
			if (other.deliverCurrentStatus != null)
				return false;
		} else if (!deliverCurrentStatus.equals(other.deliverCurrentStatus))
			return false;
		if (deliverPerson == null) {
			if (other.deliverPerson != null)
				return false;
		} else if (!deliverPerson.equals(other.deliverPerson))
			return false;
		if (deliverSerialNo == null) {
			if (other.deliverSerialNo != null)
				return false;
		} else if (!deliverSerialNo.equals(other.deliverSerialNo))
			return false;
		if (deptCode == null) {
			if (other.deptCode != null)
				return false;
		} else if (!deptCode.equals(other.deptCode))
			return false;
		if (detailRemark == null) {
			if (other.detailRemark != null)
				return false;
		} else if (!detailRemark.equals(other.detailRemark))
			return false;
		if (factoryCode == null) {
			if (other.factoryCode != null)
				return false;
		} else if (!factoryCode.equals(other.factoryCode))
			return false;
		if (fiveInputCode == null) {
			if (other.fiveInputCode != null)
				return false;
		} else if (!fiveInputCode.equals(other.fiveInputCode))
			return false;
		if (makeDate == null) {
			if (other.makeDate != null)
				return false;
		} else if (!makeDate.equals(other.makeDate))
			return false;
		if (maker == null) {
			if (other.maker != null)
				return false;
		} else if (!maker.equals(other.maker))
			return false;
		if (materialClass == null) {
			if (other.materialClass != null)
				return false;
		} else if (!materialClass.equals(other.materialClass))
			return false;
		if (materialCode == null) {
			if (other.materialCode != null)
				return false;
		} else if (!materialCode.equals(other.materialCode))
			return false;
		if (materialFee == null) {
			if (other.materialFee != null)
				return false;
		} else if (!materialFee.equals(other.materialFee))
			return false;
		if (materialId == null) {
			if (other.materialId != null)
				return false;
		} else if (!materialId.equals(other.materialId))
			return false;
		if (materialName == null) {
			if (other.materialName != null)
				return false;
		} else if (!materialName.equals(other.materialName))
			return false;
		if (materialSign == null) {
			if (other.materialSign != null)
				return false;
		} else if (!materialSign.equals(other.materialSign))
			return false;
		if (materialSpec == null) {
			if (other.materialSpec != null)
				return false;
		} else if (!materialSpec.equals(other.materialSpec))
			return false;
		if (materialUnits == null) {
			if (other.materialUnits != null)
				return false;
		} else if (!materialUnits.equals(other.materialUnits))
			return false;
		if (packageAmount == null) {
			if (other.packageAmount != null)
				return false;
		} else if (!packageAmount.equals(other.packageAmount))
			return false;
		if (packageAutoId == null) {
			if (other.packageAutoId != null)
				return false;
		} else if (!packageAutoId.equals(other.packageAutoId))
			return false;
		if (packageClass == null) {
			if (other.packageClass != null)
				return false;
		} else if (!packageClass.equals(other.packageClass))
			return false;
		if (packageId == null) {
			if (other.packageId != null)
				return false;
		} else if (!packageId.equals(other.packageId))
			return false;
		if (packageMode == null) {
			if (other.packageMode != null)
				return false;
		} else if (!packageMode.equals(other.packageMode))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (packageNo == null) {
			if (other.packageNo != null)
				return false;
		} else if (!packageNo.equals(other.packageNo))
			return false;
		if (packageSerialNo == null) {
			if (other.packageSerialNo != null)
				return false;
		} else if (!packageSerialNo.equals(other.packageSerialNo))
			return false;
		if (packageTradePrice == null) {
			if (other.packageTradePrice != null)
				return false;
		} else if (!packageTradePrice.equals(other.packageTradePrice))
			return false;
		if (packageUnits == null) {
			if (other.packageUnits != null)
				return false;
		} else if (!packageUnits.equals(other.packageUnits))
			return false;
		if (packager == null) {
			if (other.packager != null)
				return false;
		} else if (!packager.equals(other.packager))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (phoInputCode == null) {
			if (other.phoInputCode != null)
				return false;
		} else if (!phoInputCode.equals(other.phoInputCode))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (retrieveSign == null) {
			if (other.retrieveSign != null)
				return false;
		} else if (!retrieveSign.equals(other.retrieveSign))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		if (sourceAutoId == null) {
			if (other.sourceAutoId != null)
				return false;
		} else if (!sourceAutoId.equals(other.sourceAutoId))
			return false;
		if (sterilizeAutoId == null) {
			if (other.sterilizeAutoId != null)
				return false;
		} else if (!sterilizeAutoId.equals(other.sterilizeAutoId))
			return false;
		if (sterilizeDate == null) {
			if (other.sterilizeDate != null)
				return false;
		} else if (!sterilizeDate.equals(other.sterilizeDate))
			return false;
		if (sterilizeFee == null) {
			if (other.sterilizeFee != null)
				return false;
		} else if (!sterilizeFee.equals(other.sterilizeFee))
			return false;
		if (sterilizeNo == null) {
			if (other.sterilizeNo != null)
				return false;
		} else if (!sterilizeNo.equals(other.sterilizeNo))
			return false;
		if (sterilizeOrder == null) {
			if (other.sterilizeOrder != null)
				return false;
		} else if (!sterilizeOrder.equals(other.sterilizeOrder))
			return false;
		if (sterilizeSerialNo == null) {
			if (other.sterilizeSerialNo != null)
				return false;
		} else if (!sterilizeSerialNo.equals(other.sterilizeSerialNo))
			return false;
		if (sterilizeStatus == null) {
			if (other.sterilizeStatus != null)
				return false;
		} else if (!sterilizeStatus.equals(other.sterilizeStatus))
			return false;
		if (sterilizeType == null) {
			if (other.sterilizeType != null)
				return false;
		} else if (!sterilizeType.equals(other.sterilizeType))
			return false;
		if (tradeMoney == null) {
			if (other.tradeMoney != null)
				return false;
		} else if (!tradeMoney.equals(other.tradeMoney))
			return false;
		if (tradePrice == null) {
			if (other.tradePrice != null)
				return false;
		} else if (!tradePrice.equals(other.tradePrice))
			return false;
		if (unitsCode == null) {
			if (other.unitsCode != null)
				return false;
		} else if (!unitsCode.equals(other.unitsCode))
			return false;
		if (usedSign == null) {
			if (other.usedSign != null)
				return false;
		} else if (!usedSign.equals(other.usedSign))
			return false;
		if (verifier == null) {
			if (other.verifier != null)
				return false;
		} else if (!verifier.equals(other.verifier))
			return false;
		if (verifyDate == null) {
			if (other.verifyDate != null)
				return false;
		} else if (!verifyDate.equals(other.verifyDate))
			return false;
		return true;
	}
	
}