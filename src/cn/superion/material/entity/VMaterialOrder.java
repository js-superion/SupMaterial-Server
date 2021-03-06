package cn.superion.material.entity;

import java.util.Date;

/**
 * VMaterialOrder entity. @author MyEclipse Persistence Tools
 */

public class VMaterialOrder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3792462534126767121L;
	private String autoId;
	private String unitsCode;
	private String storageCode;
	private String billNo;
	private Date billDate;
	private String operationType;
	private String stockType;
	private String salerCode;
	private String salerName;
	private String deptCode;
	private String personId;
	private String payCondition;
	private Double totalCosts;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String detailAutoId;
	private Short serialNo;
	private String materialClass;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private Double amount;
	private Double tradePrice;
	private Double tradeMoney;
	private Double retailPrice;
	private Double retailMoney;
	private String factoryCode;
	private Date planArriveDate;
	private Double inputAmount;
	private Double arrivalAmount;
	private String sourceBillNo;
	private Short sourceSerialNo;
	private String currentStatus;
	private String detailRemark;

	// Constructors

	/** default constructor */
	public VMaterialOrder() {
	}

	/** minimal constructor */
	public VMaterialOrder(String autoId, String unitsCode, String billNo,
			String detailAutoId, Short serialNo, String materialClass,
			String materialId, String materialCode, String materialName) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.billNo = billNo;
		this.detailAutoId = detailAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public VMaterialOrder(String autoId, String unitsCode,
			String storageCode, String billNo, Date billDate,
			String operationType, String stockType, String salerCode,
			String salerName, String deptCode, String personId,
			String payCondition, Double totalCosts, String remark,
			String maker, Date makeDate, String verifier,
			Date verifyDate, String detailAutoId, Short serialNo,
			String materialClass, String materialId, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			Double amount, Double tradePrice, Double tradeMoney,
			Double retailPrice, Double retailMoney, String factoryCode,
			Date planArriveDate, Double inputAmount, Double arrivalAmount,
			String sourceBillNo, Short sourceSerialNo, String currentStatus,
			String detailRemark) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.operationType = operationType;
		this.stockType = stockType;
		this.salerCode = salerCode;
		this.salerName = salerName;
		this.deptCode = deptCode;
		this.personId = personId;
		this.payCondition = payCondition;
		this.totalCosts = totalCosts;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.detailAutoId = detailAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.amount = amount;
		this.tradePrice = tradePrice;
		this.tradeMoney = tradeMoney;
		this.retailPrice = retailPrice;
		this.retailMoney = retailMoney;
		this.factoryCode = factoryCode;
		this.planArriveDate = planArriveDate;
		this.inputAmount = inputAmount;
		this.arrivalAmount = arrivalAmount;
		this.sourceBillNo = sourceBillNo;
		this.sourceSerialNo = sourceSerialNo;
		this.currentStatus = currentStatus;
		this.detailRemark = detailRemark;
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

	public String getStorageCode() {
		return this.storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
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

	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getSalerCode() {
		return this.salerCode;
	}

	public void setSalerCode(String salerCode) {
		this.salerCode = salerCode;
	}

	public String getSalerName() {
		return this.salerName;
	}

	public void setSalerName(String salerName) {
		this.salerName = salerName;
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

	public String getPayCondition() {
		return this.payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	public Double getTotalCosts() {
		return this.totalCosts;
	}

	public void setTotalCosts(Double totalCosts) {
		this.totalCosts = totalCosts;
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

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Date getPlanArriveDate() {
		return this.planArriveDate;
	}

	public void setPlanArriveDate(Date planArriveDate) {
		this.planArriveDate = planArriveDate;
	}

	public Double getInputAmount() {
		return this.inputAmount;
	}

	public void setInputAmount(Double inputAmount) {
		this.inputAmount = inputAmount;
	}

	public Double getArrivalAmount() {
		return this.arrivalAmount;
	}

	public void setArrivalAmount(Double arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public String getSourceBillNo() {
		return this.sourceBillNo;
	}

	public void setSourceBillNo(String sourceBillNo) {
		this.sourceBillNo = sourceBillNo;
	}

	public Short getSourceSerialNo() {
		return this.sourceSerialNo;
	}

	public void setSourceSerialNo(Short sourceSerialNo) {
		this.sourceSerialNo = sourceSerialNo;
	}

	public String getCurrentStatus() {
		return this.currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getDetailRemark() {
		return this.detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((arrivalAmount == null) ? 0 : arrivalAmount.hashCode());
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result
				+ ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime * result
				+ ((currentStatus == null) ? 0 : currentStatus.hashCode());
		result = prime * result
				+ ((deptCode == null) ? 0 : deptCode.hashCode());
		result = prime * result
				+ ((detailAutoId == null) ? 0 : detailAutoId.hashCode());
		result = prime * result
				+ ((detailRemark == null) ? 0 : detailRemark.hashCode());
		result = prime * result
				+ ((factoryCode == null) ? 0 : factoryCode.hashCode());
		result = prime * result
				+ ((inputAmount == null) ? 0 : inputAmount.hashCode());
		result = prime * result
				+ ((makeDate == null) ? 0 : makeDate.hashCode());
		result = prime * result + ((maker == null) ? 0 : maker.hashCode());
		result = prime * result
				+ ((materialClass == null) ? 0 : materialClass.hashCode());
		result = prime * result
				+ ((materialCode == null) ? 0 : materialCode.hashCode());
		result = prime * result
				+ ((materialId == null) ? 0 : materialId.hashCode());
		result = prime * result
				+ ((materialName == null) ? 0 : materialName.hashCode());
		result = prime * result
				+ ((materialSpec == null) ? 0 : materialSpec.hashCode());
		result = prime * result
				+ ((materialUnits == null) ? 0 : materialUnits.hashCode());
		result = prime * result
				+ ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result
				+ ((payCondition == null) ? 0 : payCondition.hashCode());
		result = prime * result
				+ ((personId == null) ? 0 : personId.hashCode());
		result = prime * result
				+ ((planArriveDate == null) ? 0 : planArriveDate.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((retailMoney == null) ? 0 : retailMoney.hashCode());
		result = prime * result
				+ ((retailPrice == null) ? 0 : retailPrice.hashCode());
		result = prime * result
				+ ((salerCode == null) ? 0 : salerCode.hashCode());
		result = prime * result
				+ ((salerName == null) ? 0 : salerName.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		result = prime * result
				+ ((sourceBillNo == null) ? 0 : sourceBillNo.hashCode());
		result = prime * result
				+ ((sourceSerialNo == null) ? 0 : sourceSerialNo.hashCode());
		result = prime * result
				+ ((stockType == null) ? 0 : stockType.hashCode());
		result = prime * result
				+ ((storageCode == null) ? 0 : storageCode.hashCode());
		result = prime * result
				+ ((totalCosts == null) ? 0 : totalCosts.hashCode());
		result = prime * result
				+ ((tradeMoney == null) ? 0 : tradeMoney.hashCode());
		result = prime * result
				+ ((tradePrice == null) ? 0 : tradePrice.hashCode());
		result = prime * result
				+ ((unitsCode == null) ? 0 : unitsCode.hashCode());
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
		VMaterialOrder other = (VMaterialOrder) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (arrivalAmount == null) {
			if (other.arrivalAmount != null)
				return false;
		} else if (!arrivalAmount.equals(other.arrivalAmount))
			return false;
		if (autoId == null) {
			if (other.autoId != null)
				return false;
		} else if (!autoId.equals(other.autoId))
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
		if (deptCode == null) {
			if (other.deptCode != null)
				return false;
		} else if (!deptCode.equals(other.deptCode))
			return false;
		if (detailAutoId == null) {
			if (other.detailAutoId != null)
				return false;
		} else if (!detailAutoId.equals(other.detailAutoId))
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
		if (inputAmount == null) {
			if (other.inputAmount != null)
				return false;
		} else if (!inputAmount.equals(other.inputAmount))
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
		if (operationType == null) {
			if (other.operationType != null)
				return false;
		} else if (!operationType.equals(other.operationType))
			return false;
		if (payCondition == null) {
			if (other.payCondition != null)
				return false;
		} else if (!payCondition.equals(other.payCondition))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (planArriveDate == null) {
			if (other.planArriveDate != null)
				return false;
		} else if (!planArriveDate.equals(other.planArriveDate))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (retailMoney == null) {
			if (other.retailMoney != null)
				return false;
		} else if (!retailMoney.equals(other.retailMoney))
			return false;
		if (retailPrice == null) {
			if (other.retailPrice != null)
				return false;
		} else if (!retailPrice.equals(other.retailPrice))
			return false;
		if (salerCode == null) {
			if (other.salerCode != null)
				return false;
		} else if (!salerCode.equals(other.salerCode))
			return false;
		if (salerName == null) {
			if (other.salerName != null)
				return false;
		} else if (!salerName.equals(other.salerName))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		if (sourceBillNo == null) {
			if (other.sourceBillNo != null)
				return false;
		} else if (!sourceBillNo.equals(other.sourceBillNo))
			return false;
		if (sourceSerialNo == null) {
			if (other.sourceSerialNo != null)
				return false;
		} else if (!sourceSerialNo.equals(other.sourceSerialNo))
			return false;
		if (stockType == null) {
			if (other.stockType != null)
				return false;
		} else if (!stockType.equals(other.stockType))
			return false;
		if (storageCode == null) {
			if (other.storageCode != null)
				return false;
		} else if (!storageCode.equals(other.storageCode))
			return false;
		if (totalCosts == null) {
			if (other.totalCosts != null)
				return false;
		} else if (!totalCosts.equals(other.totalCosts))
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