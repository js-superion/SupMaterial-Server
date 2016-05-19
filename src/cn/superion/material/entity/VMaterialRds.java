package cn.superion.material.entity;

import java.util.Date;

/**
 * VMaterialRds entity. @author MyEclipse Persistence Tools
 */

public class VMaterialRds implements java.io.Serializable {

	// Fields

	/**
	 * storageCode,salerCode,salerName,materialClass,materialCode,materialName,materialSpec,materialUnits,tradePrice,amount,tradeMoney,invoiceDate,invoiceNo,billDate,billNo,rdBillNo,accountDate
	 */
	private static final long serialVersionUID = 2171969908906591194L;
	private String autoId;
	private String unitsCode;
	private String storageCode;
	private String deptUnitsCode;
	private String billNo;
	private String billMonthNo;
	private Date billDate;
	private String invoiceType;
	private String rdFlag;
	private String rdType;
	private String operationType;
	private String operationNo;
	private String orderNo;
	private String arrivalNo;
	private String cardCode;
	private String deptCode;
	private String personId;
	private String salerCode;
	private String salerName;
	private String remark;
	private String maker;
	private Date makeDate;
	private String verifier;
	private Date verifyDate;
	private String accounter;
	private Date accountDate;
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
	private Double amount;
	//


	//
	private Double tradePrice;
	private Double tradeMoney;
	private Double retailPrice;
	private Double retailMoney;
	private Double wholeSalePrice;
	private Double wholeSaleMoney;
	private Double invitePrice;
	private Double inviteMoney;
	private String factoryCode;
	private Date madeDate;
	private String batch;
	private Date availDate;
	private Double outAmount;
	private String outSign;
	private Double currentStockAmount;
	private Double invoiceAmount;
	private String invoiceSign;
	private String invoiceNo;
	private Date invoiceDate;
	private String rdBillNo;
	private String materialBarCode;
	private String isGive;
	private String countClass;
	private String registerNo;
	
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

	public String getRdBillNo() {
		return rdBillNo;
	}

	public void setRdBillNo(String rdBillNo) {
		this.rdBillNo = rdBillNo;
	}

	private String highValueSign;
	private String agentSign;
	private String sourceInputAutoId;
	private String sourceAutoId;
	private String detailRemark;

	// Constructors

	public String getDeptUnitsCode() {
		return deptUnitsCode;
	}

	public void setDeptUnitsCode(String deptUnitsCode) {
		this.deptUnitsCode = deptUnitsCode;
	}

	/** default constructor */
	public VMaterialRds() {
	}

	/** minimal constructor */
	public VMaterialRds(String unitsCode, String storageCode, String billNo,
			Date billDate, String detailAutoId, Short serialNo,
			String materialClass, String materialId, String materialCode,
			String materialName) {
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.detailAutoId = detailAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public VMaterialRds(String autoId, String unitsCode, String storageCode,String deptUnitsCode,
			String billNo, Date billDate, String invoiceType, String rdFlag,
			String rdType, String operationType, String operationNo,
			String orderNo, String arrivalNo, String cardCode, String deptCode,
			String personId, String salerCode, String salerName, String remark,
			String maker, Date makeDate, String verifier, Date verifyDate,
			String accounter, Date accountDate, String currentStatus,
			String detailAutoId, Short serialNo, String materialClass,
			String barCode, String materialId, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			Double amount, Double tradePrice, Double tradeMoney,String billMonthNo,
			Double retailPrice, Double retailMoney, String factoryCode,
			Date madeDate, String batch, Date availDate, Double outAmount,
			String outSign, Double currentStockAmount, Double invoiceAmount,
			String invoiceSign, String invoiceNo,Date invoiceDate, String rdBillNo,String highValueSign, String agentSign,
			String sourceInputAutoId, String sourceAutoId, String detailRemark) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.deptUnitsCode = deptUnitsCode;
		this.billNo = billNo;
		this.billDate = billDate;
		this.invoiceType = invoiceType;
		this.rdFlag = rdFlag;
		this.rdType = rdType;
		this.operationType = operationType;
		this.billMonthNo = billMonthNo;
		this.operationNo = operationNo;
		this.orderNo = orderNo;
		this.arrivalNo = arrivalNo;
		this.cardCode = cardCode;
		this.deptCode = deptCode;
		this.personId = personId;
		this.salerCode = salerCode;
		this.salerName = salerName;
		this.remark = remark;
		this.maker = maker;
		this.makeDate = makeDate;
		this.verifier = verifier;
		this.verifyDate = verifyDate;
		this.accounter = accounter;
		this.accountDate = accountDate;
		this.currentStatus = currentStatus;
		this.detailAutoId = detailAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.barCode = barCode;
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
		this.madeDate = madeDate;
		this.batch = batch;
		this.availDate = availDate;
		this.outAmount = outAmount;
		this.outSign = outSign;
		this.currentStockAmount = currentStockAmount;
		this.invoiceAmount = invoiceAmount;
		this.invoiceSign = invoiceSign;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.rdBillNo = rdBillNo;
		this.highValueSign = highValueSign;
		this.agentSign = agentSign;
		this.sourceInputAutoId = sourceInputAutoId;
		this.sourceAutoId = sourceAutoId;
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

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getRdFlag() {
		return this.rdFlag;
	}

	public void setRdFlag(String rdFlag) {
		this.rdFlag = rdFlag;
	}

	public String getRdType() {
		return this.rdType;
	}

	public String getBillMonthNo() {
		return billMonthNo;
	}

	public void setBillMonthNo(String billMonthNo) {
		this.billMonthNo = billMonthNo;
	}

	public void setRdType(String rdType) {
		this.rdType = rdType;
	}

	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationNo() {
		return this.operationNo;
	}

	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getArrivalNo() {
		return this.arrivalNo;
	}

	public void setArrivalNo(String arrivalNo) {
		this.arrivalNo = arrivalNo;
	}

	public String getCardCode() {
		return this.cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
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

	public Double getOutAmount() {
		return this.outAmount;
	}

	public void setOutAmount(Double outAmount) {
		this.outAmount = outAmount;
	}

	public String getOutSign() {
		return this.outSign;
	}

	public void setOutSign(String outSign) {
		this.outSign = outSign;
	}

	public Double getCurrentStockAmount() {
		return this.currentStockAmount;
	}

	public void setCurrentStockAmount(Double currentStockAmount) {
		this.currentStockAmount = currentStockAmount;
	}

	public Double getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceSign() {
		return this.invoiceSign;
	}

	public void setInvoiceSign(String invoiceSign) {
		this.invoiceSign = invoiceSign;
	}

	public String getHighValueSign() {
		return this.highValueSign;
	}

	public void setHighValueSign(String highValueSign) {
		this.highValueSign = highValueSign;
	}

	public String getAgentSign() {
		return this.agentSign;
	}

	public void setAgentSign(String agentSign) {
		this.agentSign = agentSign;
	}

	public String getSourceInputAutoId() {
		return this.sourceInputAutoId;
	}

	public void setSourceInputAutoId(String sourceInputAutoId) {
		this.sourceInputAutoId = sourceInputAutoId;
	}

	public String getSourceAutoId() {
		return this.sourceAutoId;
	}

	public void setSourceAutoId(String sourceAutoId) {
		this.sourceAutoId = sourceAutoId;
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
		result = prime * result
				+ ((accountDate == null) ? 0 : accountDate.hashCode());
		result = prime * result
				+ ((accounter == null) ? 0 : accounter.hashCode());
		result = prime * result
				+ ((agentSign == null) ? 0 : agentSign.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((arrivalNo == null) ? 0 : arrivalNo.hashCode());
		result = prime * result + ((autoId == null) ? 0 : autoId.hashCode());
		result = prime * result
				+ ((availDate == null) ? 0 : availDate.hashCode());
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result
				+ ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + ((billNo == null) ? 0 : billNo.hashCode());
		result = prime * result
				+ ((cardCode == null) ? 0 : cardCode.hashCode());
		result = prime * result
				+ ((currentStatus == null) ? 0 : currentStatus.hashCode());
		result = prime
				* result
				+ ((currentStockAmount == null) ? 0 : currentStockAmount
						.hashCode());
		result = prime * result
				+ ((deptCode == null) ? 0 : deptCode.hashCode());
		result = prime * result
				+ ((detailAutoId == null) ? 0 : detailAutoId.hashCode());
		result = prime * result
				+ ((detailRemark == null) ? 0 : detailRemark.hashCode());
		result = prime * result
				+ ((factoryCode == null) ? 0 : factoryCode.hashCode());
		result = prime * result
				+ ((highValueSign == null) ? 0 : highValueSign.hashCode());
		result = prime * result
				+ ((invoiceAmount == null) ? 0 : invoiceAmount.hashCode());
		result = prime * result
				+ ((invoiceSign == null) ? 0 : invoiceSign.hashCode());
		result = prime * result
				+ ((invoiceType == null) ? 0 : invoiceType.hashCode());
		result = prime * result
				+ ((madeDate == null) ? 0 : madeDate.hashCode());
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
				+ ((operationNo == null) ? 0 : operationNo.hashCode());
		result = prime * result
				+ ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result + ((orderNo == null) ? 0 : orderNo.hashCode());
		result = prime * result
				+ ((outAmount == null) ? 0 : outAmount.hashCode());
		result = prime * result + ((outSign == null) ? 0 : outSign.hashCode());
		result = prime * result
				+ ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((rdFlag == null) ? 0 : rdFlag.hashCode());
		result = prime * result + ((rdType == null) ? 0 : rdType.hashCode());
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
				+ ((sourceAutoId == null) ? 0 : sourceAutoId.hashCode());
		result = prime
				* result
				+ ((sourceInputAutoId == null) ? 0 : sourceInputAutoId
						.hashCode());
		result = prime * result
				+ ((storageCode == null) ? 0 : storageCode.hashCode());
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
		VMaterialRds other = (VMaterialRds) obj;
		if (accountDate == null) {
			if (other.accountDate != null)
				return false;
		} else if (!accountDate.equals(other.accountDate))
			return false;
		if (accounter == null) {
			if (other.accounter != null)
				return false;
		} else if (!accounter.equals(other.accounter))
			return false;
		if (agentSign == null) {
			if (other.agentSign != null)
				return false;
		} else if (!agentSign.equals(other.agentSign))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (arrivalNo == null) {
			if (other.arrivalNo != null)
				return false;
		} else if (!arrivalNo.equals(other.arrivalNo))
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
		if (barCode == null) {
			if (other.barCode != null)
				return false;
		} else if (!barCode.equals(other.barCode))
			return false;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
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
		if (cardCode == null) {
			if (other.cardCode != null)
				return false;
		} else if (!cardCode.equals(other.cardCode))
			return false;
		if (currentStatus == null) {
			if (other.currentStatus != null)
				return false;
		} else if (!currentStatus.equals(other.currentStatus))
			return false;
		if (currentStockAmount == null) {
			if (other.currentStockAmount != null)
				return false;
		} else if (!currentStockAmount.equals(other.currentStockAmount))
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
		if (highValueSign == null) {
			if (other.highValueSign != null)
				return false;
		} else if (!highValueSign.equals(other.highValueSign))
			return false;
		if (invoiceAmount == null) {
			if (other.invoiceAmount != null)
				return false;
		} else if (!invoiceAmount.equals(other.invoiceAmount))
			return false;
		if (invoiceSign == null) {
			if (other.invoiceSign != null)
				return false;
		} else if (!invoiceSign.equals(other.invoiceSign))
			return false;
		if (invoiceType == null) {
			if (other.invoiceType != null)
				return false;
		} else if (!invoiceType.equals(other.invoiceType))
			return false;
		if (madeDate == null) {
			if (other.madeDate != null)
				return false;
		} else if (!madeDate.equals(other.madeDate))
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
		if (operationNo == null) {
			if (other.operationNo != null)
				return false;
		} else if (!operationNo.equals(other.operationNo))
			return false;
		if (operationType == null) {
			if (other.operationType != null)
				return false;
		} else if (!operationType.equals(other.operationType))
			return false;
		if (orderNo == null) {
			if (other.orderNo != null)
				return false;
		} else if (!orderNo.equals(other.orderNo))
			return false;
		if (outAmount == null) {
			if (other.outAmount != null)
				return false;
		} else if (!outAmount.equals(other.outAmount))
			return false;
		if (outSign == null) {
			if (other.outSign != null)
				return false;
		} else if (!outSign.equals(other.outSign))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (rdFlag == null) {
			if (other.rdFlag != null)
				return false;
		} else if (!rdFlag.equals(other.rdFlag))
			return false;
		if (rdType == null) {
			if (other.rdType != null)
				return false;
		} else if (!rdType.equals(other.rdType))
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
		if (sourceAutoId == null) {
			if (other.sourceAutoId != null)
				return false;
		} else if (!sourceAutoId.equals(other.sourceAutoId))
			return false;
		if (sourceInputAutoId == null) {
			if (other.sourceInputAutoId != null)
				return false;
		} else if (!sourceInputAutoId.equals(other.sourceInputAutoId))
			return false;
		if (storageCode == null) {
			if (other.storageCode != null)
				return false;
		} else if (!storageCode.equals(other.storageCode))
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

	public String getMaterialBarCode() {
		return materialBarCode;
	}

	public void setMaterialBarCode(String materialBarCode) {
		this.materialBarCode = materialBarCode;
	}

	public String getIsGive() {
		return isGive;
	}

	public void setIsGive(String isGive) {
		this.isGive = isGive;
	}

	public Double getWholeSalePrice() {
		return wholeSalePrice;
	}

	public void setWholeSalePrice(Double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}

	public Double getWholeSaleMoney() {
		return wholeSaleMoney;
	}

	public void setWholeSaleMoney(Double wholeSaleMoney) {
		this.wholeSaleMoney = wholeSaleMoney;
	}

	public Double getInvitePrice() {
		return invitePrice;
	}

	public void setInvitePrice(Double invitePrice) {
		this.invitePrice = invitePrice;
	}

	public Double getInviteMoney() {
		return inviteMoney;
	}

	public void setInviteMoney(Double inviteMoney) {
		this.inviteMoney = inviteMoney;
	}

	public String getCountClass() {
		return countClass;
	}

	public void setCountClass(String countClass) {
		this.countClass = countClass;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
}