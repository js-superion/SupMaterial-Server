package cn.superion.materialDept.entity;

import java.util.Date;

/**
 * InpPatsBillDetail entity. @author MyEclipse Persistence Tools
 */

public class InpPatsBillDetail implements java.io.Serializable,Cloneable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6398152207272631241L;
	private InpPatsBillDetailId id;
	private String itemClass;
	private String itemCode;
	private String itemName;
	private String generalName;
	private String itemSpec;
	private String units;
	private Double amount;
	private Integer quantity;
	private Double unitPrice;
	private Double costs;
	private Double charges;
	private String produceCorporation;
	private String producingAreaAttribute;
	private String classOnInpRcpt;
	private String classOnReckoning;
	private String classOnAccount;
	private String insuranceCode;
	private String insuranceClass;
	private String insuranceLevel;
	private String insuranceSpecialSign;
	private Double insurancePay;
	private Double selfExcMoney;
	private Double selfMoney;
	private String drugSign;
	private String storageManageSign;
	private String specialSign;
	private String orderedDept;
	private String orderedGroup;
	private String orderedDoctor;
	private String performedDept;
	private String performedDoctor;
	private String billingSource;
	private String billingRcptNo;
	private Date billingDateTime;
	private String operator;
	private Double oneLevelPrice;
	private Double twoLevelPrice;
	private Double threeLevelPrice;
	private Double maxLimitPrice;
	private String serviceSign;
	private String refundSign;
	private String transferSign;
	private Date transferDate;
	private String settledRcptNo;
	private String remark;
	private String recipeNo;
	private String selfSign;
	private String reduceSign;
	private Double reduceMoney;
	
	private String reckoningRcptNo;
	private String groupName;
	private Double totalRefundAmount;
	private String groupSign;
	private String groupCode;
	private String insuranceCodeUpdateSign;
	private String classOnMr;
	private String levelDrugSign;

	// Constructors

	/** default constructor */
	public InpPatsBillDetail() {
	}

	/** minimal constructor */
	public InpPatsBillDetail(InpPatsBillDetailId id, String itemClass,
			String itemCode) {
		this.id = id;
		this.itemClass = itemClass;
		this.itemCode = itemCode;
	}

	/** full constructor */
	public InpPatsBillDetail(InpPatsBillDetailId id, String itemClass,
			String itemCode, String itemName, String generalName,
			String itemSpec, String units, Double amount, Integer quantity,
			Double unitPrice, Double costs, Double charges,
			String produceCorporation, String producingAreaAttribute,
			String classOnInpRcpt, String classOnReckoning,
			String classOnAccount, String insuranceCode, String insuranceClass,
			String insuranceLevel, String insuranceSpecialSign,
			Double insurancePay, Double selfExcMoney, Double selfMoney,
			String drugSign, String storageManageSign, String specialSign,
			String orderedDept, String orderedGroup, String orderedDoctor,
			String performedDept, String performedDoctor, String billingSource,
			String billingRcptNo, Date billingDateTime, String operator,
			Double oneLevelPrice, Double twoLevelPrice, Double threeLevelPrice,
			Double maxLimitPrice, String serviceSign, String refundSign,
			String transferSign, Date transferDate, String settledRcptNo,
			String remark, String recipeNo, String selfSign, String reduceSign,
			Double reduceMoney) {
		this.id = id;
		this.itemClass = itemClass;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.generalName = generalName;
		this.itemSpec = itemSpec;
		this.units = units;
		this.amount = amount;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.costs = costs;
		this.charges = charges;
		this.produceCorporation = produceCorporation;
		this.producingAreaAttribute = producingAreaAttribute;
		this.classOnInpRcpt = classOnInpRcpt;
		this.classOnReckoning = classOnReckoning;
		this.classOnAccount = classOnAccount;
		this.insuranceCode = insuranceCode;
		this.insuranceClass = insuranceClass;
		this.insuranceLevel = insuranceLevel;
		this.insuranceSpecialSign = insuranceSpecialSign;
		this.insurancePay = insurancePay;
		this.selfExcMoney = selfExcMoney;
		this.selfMoney = selfMoney;
		this.drugSign = drugSign;
		this.storageManageSign = storageManageSign;
		this.specialSign = specialSign;
		this.orderedDept = orderedDept;
		this.orderedGroup = orderedGroup;
		this.orderedDoctor = orderedDoctor;
		this.performedDept = performedDept;
		this.performedDoctor = performedDoctor;
		this.billingSource = billingSource;
		this.billingRcptNo = billingRcptNo;
		this.billingDateTime = billingDateTime;
		this.operator = operator;
		this.oneLevelPrice = oneLevelPrice;
		this.twoLevelPrice = twoLevelPrice;
		this.threeLevelPrice = threeLevelPrice;
		this.maxLimitPrice = maxLimitPrice;
		this.serviceSign = serviceSign;
		this.refundSign = refundSign;
		this.transferSign = transferSign;
		this.transferDate = transferDate;
		this.settledRcptNo = settledRcptNo;
		this.remark = remark;
		this.recipeNo = recipeNo;
		this.selfSign = selfSign;
		this.reduceSign = reduceSign;
		this.reduceMoney = reduceMoney;
	}

	// Property accessors

	public InpPatsBillDetailId getId() {
		return this.id;
	}

	public void setId(InpPatsBillDetailId id) {
		this.id = id;
	}

	public String getItemClass() {
		return this.itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGeneralName() {
		return this.generalName;
	}

	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	public String getItemSpec() {
		return this.itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getCosts() {
		return this.costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}

	public Double getCharges() {
		return this.charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public String getProduceCorporation() {
		return this.produceCorporation;
	}

	public void setProduceCorporation(String produceCorporation) {
		this.produceCorporation = produceCorporation;
	}

	public String getProducingAreaAttribute() {
		return this.producingAreaAttribute;
	}

	public void setProducingAreaAttribute(String producingAreaAttribute) {
		this.producingAreaAttribute = producingAreaAttribute;
	}

	public String getClassOnInpRcpt() {
		return this.classOnInpRcpt;
	}

	public void setClassOnInpRcpt(String classOnInpRcpt) {
		this.classOnInpRcpt = classOnInpRcpt;
	}

	public String getClassOnReckoning() {
		return this.classOnReckoning;
	}

	public void setClassOnReckoning(String classOnReckoning) {
		this.classOnReckoning = classOnReckoning;
	}

	public String getClassOnAccount() {
		return this.classOnAccount;
	}

	public void setClassOnAccount(String classOnAccount) {
		this.classOnAccount = classOnAccount;
	}

	public String getInsuranceCode() {
		return this.insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getInsuranceClass() {
		return this.insuranceClass;
	}

	public void setInsuranceClass(String insuranceClass) {
		this.insuranceClass = insuranceClass;
	}

	public String getInsuranceLevel() {
		return this.insuranceLevel;
	}

	public void setInsuranceLevel(String insuranceLevel) {
		this.insuranceLevel = insuranceLevel;
	}

	public String getInsuranceSpecialSign() {
		return this.insuranceSpecialSign;
	}

	public void setInsuranceSpecialSign(String insuranceSpecialSign) {
		this.insuranceSpecialSign = insuranceSpecialSign;
	}

	public Double getInsurancePay() {
		return this.insurancePay;
	}

	public void setInsurancePay(Double insurancePay) {
		this.insurancePay = insurancePay;
	}

	public Double getSelfExcMoney() {
		return this.selfExcMoney;
	}

	public void setSelfExcMoney(Double selfExcMoney) {
		this.selfExcMoney = selfExcMoney;
	}

	public Double getSelfMoney() {
		return this.selfMoney;
	}

	public void setSelfMoney(Double selfMoney) {
		this.selfMoney = selfMoney;
	}

	public String getDrugSign() {
		return this.drugSign;
	}

	public void setDrugSign(String drugSign) {
		this.drugSign = drugSign;
	}

	public String getStorageManageSign() {
		return this.storageManageSign;
	}

	public void setStorageManageSign(String storageManageSign) {
		this.storageManageSign = storageManageSign;
	}

	public String getSpecialSign() {
		return this.specialSign;
	}

	public void setSpecialSign(String specialSign) {
		this.specialSign = specialSign;
	}

	public String getOrderedDept() {
		return this.orderedDept;
	}

	public void setOrderedDept(String orderedDept) {
		this.orderedDept = orderedDept;
	}

	public String getOrderedGroup() {
		return this.orderedGroup;
	}

	public void setOrderedGroup(String orderedGroup) {
		this.orderedGroup = orderedGroup;
	}

	public String getOrderedDoctor() {
		return this.orderedDoctor;
	}

	public void setOrderedDoctor(String orderedDoctor) {
		this.orderedDoctor = orderedDoctor;
	}

	public String getPerformedDept() {
		return this.performedDept;
	}

	public void setPerformedDept(String performedDept) {
		this.performedDept = performedDept;
	}

	public String getPerformedDoctor() {
		return this.performedDoctor;
	}

	public void setPerformedDoctor(String performedDoctor) {
		this.performedDoctor = performedDoctor;
	}

	public String getBillingSource() {
		return this.billingSource;
	}

	public void setBillingSource(String billingSource) {
		this.billingSource = billingSource;
	}

	public String getBillingRcptNo() {
		return this.billingRcptNo;
	}

	public void setBillingRcptNo(String billingRcptNo) {
		this.billingRcptNo = billingRcptNo;
	}

	public Date getBillingDateTime() {
		return this.billingDateTime;
	}

	public void setBillingDateTime(Date billingDateTime) {
		this.billingDateTime = billingDateTime;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Double getOneLevelPrice() {
		return this.oneLevelPrice;
	}

	public void setOneLevelPrice(Double oneLevelPrice) {
		this.oneLevelPrice = oneLevelPrice;
	}

	public Double getTwoLevelPrice() {
		return this.twoLevelPrice;
	}

	public void setTwoLevelPrice(Double twoLevelPrice) {
		this.twoLevelPrice = twoLevelPrice;
	}

	public Double getThreeLevelPrice() {
		return this.threeLevelPrice;
	}

	public void setThreeLevelPrice(Double threeLevelPrice) {
		this.threeLevelPrice = threeLevelPrice;
	}

	public Double getMaxLimitPrice() {
		return this.maxLimitPrice;
	}

	public void setMaxLimitPrice(Double maxLimitPrice) {
		this.maxLimitPrice = maxLimitPrice;
	}

	public String getServiceSign() {
		return this.serviceSign;
	}

	public void setServiceSign(String serviceSign) {
		this.serviceSign = serviceSign;
	}

	public String getRefundSign() {
		return this.refundSign;
	}

	public void setRefundSign(String refundSign) {
		this.refundSign = refundSign;
	}

	public String getTransferSign() {
		return this.transferSign;
	}

	public void setTransferSign(String transferSign) {
		this.transferSign = transferSign;
	}

	public Date getTransferDate() {
		return this.transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getSettledRcptNo() {
		return this.settledRcptNo;
	}

	public void setSettledRcptNo(String settledRcptNo) {
		this.settledRcptNo = settledRcptNo;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRecipeNo() {
		return this.recipeNo;
	}

	public void setRecipeNo(String recipeNo) {
		this.recipeNo = recipeNo;
	}

	public String getSelfSign() {
		return this.selfSign;
	}

	public void setSelfSign(String selfSign) {
		this.selfSign = selfSign;
	}

	public String getReduceSign() {
		return this.reduceSign;
	}

	public void setReduceSign(String reduceSign) {
		this.reduceSign = reduceSign;
	}

	public Double getReduceMoney() {
		return this.reduceMoney;
	}

	public void setReduceMoney(Double reduceMoney) {
		this.reduceMoney = reduceMoney;
	}
	
	public InpPatsBillDetail clone(){
		InpPatsBillDetail copy = null;
		try {
			copy = (InpPatsBillDetail) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
	
	public InpPatsBillDetail writeRed(){
		InpPatsBillDetail red = this.clone();
		red.setAmount(-red.getAmount());
		red.setCosts(-red.getCosts());
		red.setCharges(-red.getCharges());
		return red;
	}

	public String getReckoningRcptNo() {
		return reckoningRcptNo;
	}

	public void setReckoningRcptNo(String reckoningRcptNo) {
		this.reckoningRcptNo = reckoningRcptNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Double getTotalRefundAmount() {
		return totalRefundAmount;
	}

	public void setTotalRefundAmount(Double totalRefundAmount) {
		this.totalRefundAmount = totalRefundAmount;
	}

	public String getGroupSign() {
		return groupSign;
	}

	public void setGroupSign(String groupSign) {
		this.groupSign = groupSign;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getInsuranceCodeUpdateSign() {
		return insuranceCodeUpdateSign;
	}

	public void setInsuranceCodeUpdateSign(String insuranceCodeUpdateSign) {
		this.insuranceCodeUpdateSign = insuranceCodeUpdateSign;
	}

	public String getClassOnMr() {
		return classOnMr;
	}

	public void setClassOnMr(String classOnMr) {
		this.classOnMr = classOnMr;
	}

	public String getLevelDrugSign() {
		return levelDrugSign;
	}

	public void setLevelDrugSign(String levelDrugSign) {
		this.levelDrugSign = levelDrugSign;
	}

}