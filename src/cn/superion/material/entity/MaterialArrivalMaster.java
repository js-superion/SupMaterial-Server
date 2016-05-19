package cn.superion.material.entity;

import java.util.Date;



/**
 * MaterialArrivalMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialArrivalMaster  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 6607269962437579316L;
	private String autoId;
     private String unitsCode;
     private String storageCode;
     private String billNo;
     private Date billDate;
     private String operationType;
     private String stockType;
     private String salerCode;
     private String salerName;
     private String transport;
     private String deptCode;
     private String personId;
     private Double totalCosts;
     private String remark;
     private String maker;
     private Date makeDate;
     private String verifier;
     private Date verifyDate;
     private String currentStatus;


    // Constructors

    /** default constructor */
    public MaterialArrivalMaster() {
    }

	/** minimal constructor */
    public MaterialArrivalMaster(String autoId, String unitsCode, String billNo) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.billNo = billNo;
    }
    
    /** full constructor */
    public MaterialArrivalMaster(String autoId, String unitsCode, String storageCode, String billNo, Date billDate, String operationType, String stockType, String salerCode, String salerName, String transport, String deptCode, String personId, Double totalCosts, String remark, String maker, Date makeDate, String verifier, Date verifyDate, String currentStatus) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
        this.billDate = billDate;
        this.operationType = operationType;
        this.stockType = stockType;
        this.salerCode = salerCode;
        this.salerName = salerName;
        this.transport = transport;
        this.deptCode = deptCode;
        this.personId = personId;
        this.totalCosts = totalCosts;
        this.remark = remark;
        this.maker = maker;
        this.makeDate = makeDate;
        this.verifier = verifier;
        this.verifyDate = verifyDate;
        this.currentStatus = currentStatus;
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

    public String getTransport() {
        return this.transport;
    }
    
    public void setTransport(String transport) {
        this.transport = transport;
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

    public String getCurrentStatus() {
        return this.currentStatus;
    }
    
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
   








}