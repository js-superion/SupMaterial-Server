package cn.superion.material.entity;

import java.util.Date;


/**
 * MaterialPlanDetail entity. @author MyEclipse Persistence Tools
 */

public class MaterialPlanDetail  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 7834955646461408743L;
	private String autoId;
     private String mainAutoId;
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
     private Double currentStockAmount;
     private Double deliveryAmount;
	 private Double totalCurrentStockAmount;
     private Date requireDate;
     private Date adviceBookDate;
     private String salerCode;
     private String salerName;
     private Double orderAmount;
     private String currentStatus;
     private String detailRemark;
     private String chargeSign;
 	 private String classOnAccount;
 	 private String batch;

    // Constructors

   
	/** default constructor */
    public MaterialPlanDetail() {
    }

	/** minimal constructor */
    public MaterialPlanDetail(String autoId, String mainAutoId, Short serialNo, String materialClass, String materialId, String materialCode, String materialName) {
        this.autoId = autoId;
        this.mainAutoId = mainAutoId;
        this.serialNo = serialNo;
        this.materialClass = materialClass;
        this.materialId = materialId;
        this.materialCode = materialCode;
        this.materialName = materialName;
    }
    
    /** full constructor */
    public MaterialPlanDetail(String autoId, String chargeSign,String classOnAccount,String mainAutoId, Short serialNo, String materialClass, String materialId, String materialCode, String materialName, String materialSpec, String materialUnits, Double amount, Double tradePrice, Double tradeMoney, Double retailPrice, Double retailMoney, Double currentStockAmount, Double totalCurrentStockAmount, Date requireDate, Date adviceBookDate, String salerCode, String salerName, Double orderAmount, String currentStatus, String detailRemark,String batch,Double deliveryAmount) {
        this.autoId = autoId;
        this.mainAutoId = mainAutoId;
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
        this.currentStockAmount = currentStockAmount;
        this.totalCurrentStockAmount = totalCurrentStockAmount;
        this.requireDate = requireDate;
        this.adviceBookDate = adviceBookDate;
        this.salerCode = salerCode;
        this.salerName = salerName;
        this.orderAmount = orderAmount;
        this.currentStatus = currentStatus;
        this.detailRemark = detailRemark;
        this.chargeSign = chargeSign;
		this.classOnAccount = classOnAccount;
		this.batch = batch;
		this.deliveryAmount = deliveryAmount;
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
    
    public String getChargeSign() {
		return chargeSign;
	}

	public void setChargeSign(String chargeSign) {
		this.chargeSign = chargeSign;
	}

	public String getClassOnAccount() {
		return classOnAccount;
	}

	public void setClassOnAccount(String classOnAccount) {
		this.classOnAccount = classOnAccount;
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

    public Double getCurrentStockAmount() {
        return this.currentStockAmount;
    }
    
    public void setCurrentStockAmount(Double currentStockAmount) {
        this.currentStockAmount = currentStockAmount;
    }

    public Double getTotalCurrentStockAmount() {
        return this.totalCurrentStockAmount;
    }
    
    public void setTotalCurrentStockAmount(Double totalCurrentStockAmount) {
        this.totalCurrentStockAmount = totalCurrentStockAmount;
    }

    public Date getRequireDate() {
        return this.requireDate;
    }
    
    public void setRequireDate(Date requireDate) {
        this.requireDate = requireDate;
    }

    public Date getAdviceBookDate() {
        return this.adviceBookDate;
    }
    
    public void setAdviceBookDate(Date adviceBookDate) {
        this.adviceBookDate = adviceBookDate;
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

    public Double getOrderAmount() {
        return this.orderAmount;
    }
    
    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
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
    
    public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Double getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(Double deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}







}