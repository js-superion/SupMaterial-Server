package cn.superion.materialDept.entity;

import java.util.Date;


/**
 * MaterialCurrentStock entity. @author MyEclipse Persistence Tools
 */

public class MaterialCurrentStockDept  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4767342175060938697L;
	// Fields    
	private String autoId;
     private String unitsCode;
     private String storageCode;
     private String materialClass;
     private String barCode;
     private String materialId;
     private String materialCode;
     private String materialName;
     private String materialSpec;
     private String materialUnits;
     private Double tradePrice;
     private Double amount;
     private String factoryCode;
     private Date madeDate;
     private String batch;
     private Date availDate;
     private String position;
     private String highValueSign;
     private String agentSign;
     private String remark;

     private Double wholeSalePrice;
 	 private Double retailPrice;

    // Constructors

    /** default constructor */
    public MaterialCurrentStockDept() {
    }

	/** minimal constructor */
    public MaterialCurrentStockDept(String autoId, String unitsCode, String storageCode, String materialClass, String materialId, String materialCode, String materialName) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.materialClass = materialClass;
        this.materialId = materialId;
        this.materialCode = materialCode;
        this.materialName = materialName;
    }
    
    /** full constructor */
    public MaterialCurrentStockDept(String autoId, String unitsCode, String storageCode, String materialClass, String materialId, String materialCode, String materialName, String materialSpec, String materialUnits, Double tradePrice, Double amount, String factoryCode, Date madeDate, String batch, Date availDate, String position, String highValueSign, String agentSign, String remark) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.materialClass = materialClass;
        this.materialId = materialId;
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialSpec = materialSpec;
        this.materialUnits = materialUnits;
        this.tradePrice = tradePrice;
        this.amount = amount;
        this.factoryCode = factoryCode;
        this.madeDate = madeDate;
        this.batch = batch;
        this.availDate = availDate;
        this.position = position;
        this.highValueSign = highValueSign;
        this.agentSign = agentSign;
        this.remark = remark;
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

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
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

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Double getWholeSalePrice() {
		return wholeSalePrice;
	}

	public void setWholeSalePrice(Double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
   








}