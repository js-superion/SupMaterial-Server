package cn.superion.material.entity;

import java.util.Date;



/**
 * MaterialProvideDetail entity. @author MyEclipse Persistence Tools
 */

public class MaterialProvideDetail  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 7478190813683078945L;
	private String autoId;
     private String mainAutoId;
     private Short serialNo;
     private String materialClass;
     private String materialId;
     private String materialCode;
     private String materialName;
     private String materialSpec;
     private String factoryCode;
     private String materialUnits;
     private Double amount;
     private Double checkAmount;
     //
     private Double wholeSalePrice;
     private Double wholeSaleMoney;
     private Double tradePrice;
     private Double tradeMoney;
     private Double retailPrice;
     private Double retailMoney;
     private String detailRemark;
     //科室退货入库，即写红单时的属性
     private String barCode;
     private Date madeDate;
     private String batch;
     private Date availDate;
     private String chargeSign;
 	 private String classOnAccount;
 	 private String agentSign;
 	 private String highValueSign;
 	 private String registerNo;
 	 private String countClass;
 	private String planSign;
 	private Double planAmount;
 	
 	private Double sendAmount;
 	
 	private String mainProvider;
	private String supplyNo;
 	private String providerName;
 	public String getMainProvider() {
		return mainProvider;
	}

	public void setMainProvider(String mainProvider) {
		this.mainProvider = mainProvider;
	}

	public Double getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(Double sendAmount) {
		this.sendAmount = sendAmount;
	}

	public String getStorageMaterialSign() {
		return storageMaterialSign;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public void setStorageMaterialSign(String storageMaterialSign) {
		this.storageMaterialSign = storageMaterialSign;
	}

	private String storageMaterialSign;
    // Constructors

    /** default constructor */
    public MaterialProvideDetail() {
    }

	public String getPlanSign() {
		return planSign;
	}

	public void setPlanSign(String planSign) {
		this.planSign = planSign;
	}

	public Double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}

	/** minimal constructor */
    public MaterialProvideDetail(String autoId, String mainAutoId, Short serialNo, String materialClass, String materialId, String materialCode, String materialName) {
        this.autoId = autoId;
        this.mainAutoId = mainAutoId;
        this.serialNo = serialNo;
        this.materialClass = materialClass;
        this.materialId = materialId;
        this.materialCode = materialCode;
        this.materialName = materialName;
    }
    
    /** full constructor */
    public MaterialProvideDetail(String autoId,String supplyNo,String planSign,Double planAmount, String chargeSign,String classOnAccount,String mainAutoId, Short serialNo, String materialClass, String materialId, String materialCode, String materialName, String materialSpec, String factoryCode, String materialUnits, Double amount, Double checkAmount, Double wholeSalePrice, Double wholeSaleMoney,Double tradePrice, Double tradeMoney, Double retailPrice, Double retailMoney, String detailRemark) {
        this.autoId = autoId;
        this.mainAutoId = mainAutoId;
        this.serialNo = serialNo;
        this.materialClass = materialClass;
        this.materialId = materialId;
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialSpec = materialSpec;
        this.supplyNo = supplyNo;
        this.factoryCode = factoryCode;
        this.planSign = planSign;
        this.planAmount = planAmount;
        this.materialUnits = materialUnits;
        this.amount = amount;
        this.checkAmount = checkAmount;
        //
        this.wholeSalePrice = wholeSalePrice;
        this.wholeSaleMoney = wholeSaleMoney;
        
        this.tradePrice = tradePrice;
        this.tradeMoney = tradeMoney;
        this.retailPrice = retailPrice;
        this.retailMoney = retailMoney;
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

    public String getSupplyNo() {
		return supplyNo;
	}

	public void setSupplyNo(String supplyNo) {
		this.supplyNo = supplyNo;
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
    
    public Double getWholeSalePrice() {
		return wholeSalePrice;
	}

	public void setWholeSalePrice(Double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}

	public Double getWholeSaleMoney() {
		return wholeSaleMoney;
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

	public void setWholeSaleMoney(Double wholeSaleMoney) {
		this.wholeSaleMoney = wholeSaleMoney;
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

    public String getFactoryCode() {
        return this.factoryCode;
    }
    
    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
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

    public Double getCheckAmount() {
        return this.checkAmount;
    }
    
    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
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

    public String getDetailRemark() {
        return this.detailRemark;
    }
    
    public void setDetailRemark(String detailRemark) {
        this.detailRemark = detailRemark;
    }

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Date getAvailDate() {
		return availDate;
	}

	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}

	public String getAgentSign() {
		return agentSign;
	}

	public void setAgentSign(String agentSign) {
		this.agentSign = agentSign;
	}

	public String getHighValueSign() {
		return highValueSign;
	}

	public void setHighValueSign(String highValueSign) {
		this.highValueSign = highValueSign;
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

	
}