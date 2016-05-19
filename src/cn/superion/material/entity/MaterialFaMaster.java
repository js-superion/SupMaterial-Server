package cn.superion.material.entity;

import java.util.Date;


/**
 * MaterialFaMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialFaMaster  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -4629719466828732397L;
	private String autoId;
     private String unitsCode;
     private String billNo;
     private Date billDate;
     private String salerCode;
     private String salerName;
     private Double totalCost;
     private String remark;
     private String maker;
     private Date makeDate;
     private String verifier;
     private Date verifyDate;
     private String currentStatus;


    // Constructors

    /** default constructor */
    public MaterialFaMaster() {
    }

	/** minimal constructor */
    public MaterialFaMaster(String autoId, String unitsCode, String billNo) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.billNo = billNo;
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

    public Double getTotalCost() {
        return this.totalCost;
    }
    
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
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

    public String getCurrentStatus() {
        return this.currentStatus;
    }
    
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}