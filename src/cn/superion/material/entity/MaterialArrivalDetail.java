package cn.superion.material.entity;

import java.util.Date;



/**
 * MaterialArrivalDetail entity. @author MyEclipse Persistence Tools
 */

public class MaterialArrivalDetail  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 6818504315273115886L;
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
     private String factoryCode;
     private Date madeDate;
     private String batch;
     private Date availDate;
     private Double inputAmount;
     private String sourceBillNo;
     private Short sourceSerialNo;
     private String currentStatus;
     private String detailRemark; 
     private String chargeSign;
 	 private String classOnAccount;

    // Constructors

    /** default constructor */
    public MaterialArrivalDetail() {
    }

	/** minimal constructor */
    public MaterialArrivalDetail(String autoId, String mainAutoId, Short serialNo, String materialClass, String materialId, String materialCode, String materialName) {
        this.autoId = autoId;
        this.mainAutoId = mainAutoId;
        this.serialNo = serialNo;
        this.materialClass = materialClass;
        this.materialId = materialId;
        this.materialCode = materialCode;
        this.materialName = materialName;
    }
    
    /** full constructor */
    public MaterialArrivalDetail(String autoId, String chargeSign,String classOnAccount,String mainAutoId, Short serialNo, String materialClass, String materialId, String materialCode, String materialName, String materialSpec, String materialUnits, Double amount, Double tradePrice, Double tradeMoney, Double retailPrice, Double retailMoney, String factoryCode, Date madeDate, String batch, Date availDate, Double inputAmount, String sourceBillNo, Short sourceSerialNo, String currentStatus, String detailRemark) {
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
        this.factoryCode = factoryCode;
        this.madeDate = madeDate;
        this.batch = batch;
        this.availDate = availDate;
        this.inputAmount = inputAmount;
        this.sourceBillNo = sourceBillNo;
        this.sourceSerialNo = sourceSerialNo;
        this.currentStatus = currentStatus;
        this.detailRemark = detailRemark;
        this.chargeSign = chargeSign;
		this.classOnAccount = classOnAccount;
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

    public Double getInputAmount() {
        return this.inputAmount;
    }
    
    public void setInputAmount(Double inputAmount) {
        this.inputAmount = inputAmount;
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
   








}