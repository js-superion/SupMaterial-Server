package cn.superion.material.entity;

import java.util.Date;

/**
 * MaterialRdsDetail entity. @author MyEclipse Persistence Tools
 */

public class MaterialRdsDetail implements java.io.Serializable, Cloneable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1457308658563289217L;
	private String autoId;
	private String mainAutoId;
	private Short serialNo;
	private String materialClass;
	private String barCode;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String packageSpec;
	private String packageUnits;
	private Double amountPerPackage;
	private Double packageAmount;
	private Double amount;
	private Double acctAmount;
	private Double tradePrice;
	private Double tradeMoney;
	private Double factTradePrice;
	private Double factTradeMoney;
	private Double rebateRate;
	private Double wholeSalePrice;
	private Double wholeSaleMoney;
	private Double invitePrice;
	private Double inviteMoney;
	private Double retailPrice;
	private Double retailMoney;
	private String factoryCode;
	private Date madeDate;
	private String batch;
	private Date availDate;
	private String position;
	private Double outAmount;
	private String outSign;
	private Double currentStockAmount;
	private Double invoiceAmount;
	private String invoiceSign;
	private String invoiceNo;
	private Date invoiceDate;
	private String highValueSign;
	private String agentSign;
	private String sourceInputAutoId;
	private String sourceAutoId;
	private String detailRemark;
	private String acctBillNo;
	private String rdBillNo;
	private String currentStatus;
	private String chargeSign;
	private String classOnAccount;
	// 扩展
	private Double checkAmount;
	
	private String materialBarCode;
	private String isGive;

	private String patientId;
	private String personName;
	private Short bedNo;
	private String wardCode;
	
	private String registerNo;
	private String inviteNo;
	private String countClass;
	private String mateClass;
	private String yldetailRemark;
	private String ypdetailRemark;
	
	// Constructors

	/** default constructor */
	public MaterialRdsDetail() {
	}

	/** minimal constructor */
	public MaterialRdsDetail(String autoId, String mainAutoId, Short serialNo,
			String materialClass, String materialId, String materialCode,
			String materialName) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */

	public MaterialRdsDetail(String autoId, String mainAutoId, Short serialNo,
			String materialClass, String barCode, String materialId,
			String materialCode, String materialName, String materialSpec,
			String materialUnits, String packageSpec, String packageUnits,
			Double amountPerPackage, Double packageAmount, Double amount,
			Double acctAmount, Double tradePrice, Double tradeMoney,
			Double factTradePrice, Double factTradeMoney, Double rebateRate,
			Double wholeSalePrice, Double wholeSaleMoney, Double invitePrice,
			Double inviteMoney, Double retailPrice, Double retailMoney,
			String factoryCode, Date madeDate, String batch, Date availDate,
			String position, Double outAmount, String outSign,
			Double currentStockAmount, Double invoiceAmount,
			String invoiceSign, String invoiceNo, Date invoiceDate,
			String highValueSign, String agentSign, String sourceInputAutoId,
			String sourceAutoId, String detailRemark, String acctBillNo,String chargeSign,String classOnAccount,
			String rdBillNo, String currentStatus, Double checkAmount,String registerNo,String countClass,String mateClass) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.barCode = barCode;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.packageSpec = packageSpec;
		this.chargeSign = chargeSign;
		this.packageUnits = packageUnits;
		this.amountPerPackage = amountPerPackage;
		this.packageAmount = packageAmount;
		this.amount = amount;
		this.acctAmount = acctAmount;
		this.tradePrice = tradePrice;
		this.tradeMoney = tradeMoney;
		this.factTradePrice = factTradePrice;
		this.factTradeMoney = factTradeMoney;
		this.rebateRate = rebateRate;
		this.wholeSalePrice = wholeSalePrice;
		this.wholeSaleMoney = wholeSaleMoney;
		this.invitePrice = invitePrice;
		this.inviteMoney = inviteMoney;
		this.retailPrice = retailPrice;
		this.retailMoney = retailMoney;
		this.factoryCode = factoryCode;
		this.madeDate = madeDate;
		this.batch = batch;
		this.availDate = availDate;
		this.position = position;
		this.outAmount = outAmount;
		this.outSign = outSign;
		this.currentStockAmount = currentStockAmount;
		this.invoiceAmount = invoiceAmount;
		this.invoiceSign = invoiceSign;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.highValueSign = highValueSign;
		this.agentSign = agentSign;
		this.sourceInputAutoId = sourceInputAutoId;
		this.sourceAutoId = sourceAutoId;
		this.detailRemark = detailRemark;
		this.acctBillNo = acctBillNo;
		this.rdBillNo = rdBillNo;
		this.currentStatus = currentStatus;
		this.checkAmount = checkAmount;
		this.classOnAccount = classOnAccount;
		this.registerNo = registerNo;
		this.countClass = countClass;
		this.mateClass = mateClass;
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

	public String getClassOnAccount() {
		return classOnAccount;
	}

	public void setClassOnAccount(String classOnAccount) {
		this.classOnAccount = classOnAccount;
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

	public String getChargeSign() {
		return chargeSign;
	}

	public void setChargeSign(String chargeSign) {
		this.chargeSign = chargeSign;
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

	public Double getAcctAmount() {
		return acctAmount;
	}

	public void setAcctAmount(Double acctAmount) {
		this.acctAmount = acctAmount;
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

	public Double getFactTradePrice() {
		return factTradePrice;
	}

	public void setFactTradePrice(Double factTradePrice) {
		this.factTradePrice = factTradePrice;
	}

	public Double getFactTradeMoney() {
		return factTradeMoney;
	}

	public void setFactTradeMoney(Double factTradeMoney) {
		this.factTradeMoney = factTradeMoney;
	}

	public Double getRebateRate() {
		return rebateRate;
	}

	public void setRebateRate(Double rebateRate) {
		this.rebateRate = rebateRate;
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
		if (this.batch == null) {
			return "0";
		}
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getAcctBillNo() {
		return acctBillNo;
	}

	public void setAcctBillNo(String acctBillNo) {
		this.acctBillNo = acctBillNo;
	}

	public String getRdBillNo() {
		return rdBillNo;
	}

	public void setRdBillNo(String rdBillNo) {
		this.rdBillNo = rdBillNo;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public Double getCheckAmount() {
		return checkAmount;
	}

	public void setCheckAmount(Double checkAmount) {
		this.checkAmount = checkAmount;
	}

	public void setPackageSpec(String packageSpec) {
		this.packageSpec = packageSpec;
	}

	public String getPackageSpec() {
		return packageSpec;
	}

	public void setPackageUnits(String packageUnits) {
		this.packageUnits = packageUnits;
	}

	public String getPackageUnits() {
		return packageUnits;
	}

	public void setAmountPerPackage(Double amountPerPackage) {
		this.amountPerPackage = amountPerPackage;
	}

	public Double getAmountPerPackage() {
		return amountPerPackage;
	}

	public void setPackageAmount(Double packageAmount) {
		this.packageAmount = packageAmount;
	}

	public Double getPackageAmount() {
		return packageAmount;
	}

	public MaterialRdsDetail clone() {
		MaterialRdsDetail copy = null;
		try {
			copy = (MaterialRdsDetail) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}

	public MaterialRdsDetail writeRed() {
		MaterialRdsDetail red = this.clone();
		red.setAutoId(null);
		red.setMainAutoId(null);
		red.setAmount(-red.getAmount());
		if (red.getRetailMoney() != null)
			red.setRetailMoney(-red.getRetailMoney());
		if (red.getTradeMoney() != null)
			red.setTradeMoney(-red.getTradeMoney());
		return red;
	}

	public String getIsGive() {
		return isGive;
	}

	public void setIsGive(String isGive) {
		this.isGive = isGive;
	}

	public String getMaterialBarCode() {
		return materialBarCode;
	}

	public void setMaterialBarCode(String materialBarCode) {
		this.materialBarCode = materialBarCode;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Short getBedNo() {
		return bedNo;
	}

	public void setBedNo(Short bedNo) {
		this.bedNo = bedNo;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public String getCountClass() {
		return countClass;
	}

	public void setCountClass(String countClass) {
		this.countClass = countClass;
	}

	public String getMateClass() {
		return mateClass;
	}

	public void setMateClass(String mateClass) {
		this.mateClass = mateClass;
	}

	public String getYldetailRemark() {
		return yldetailRemark;
	}

	public void setYldetailRemark(String yldetailRemark) {
		this.yldetailRemark = yldetailRemark;
	}

	public String getYpdetailRemark() {
		return ypdetailRemark;
	}

	public void setYpdetailRemark(String ypdetailRemark) {
		this.ypdetailRemark = ypdetailRemark;
	}

	public String getInviteNo() {
		return inviteNo;
	}

	public void setInviteNo(String inviteNo) {
		this.inviteNo = inviteNo;
	}
	
}