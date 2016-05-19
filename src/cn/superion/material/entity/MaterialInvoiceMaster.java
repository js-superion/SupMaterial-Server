package cn.superion.material.entity;

import java.util.Date;


/**
 * MaterialInvoiceMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialInvoiceMaster  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1273668828263699238L;
	private String autoId;
     private String unitsCode;
     private String storageCode;
     private String billNo;
     private Date billDate;
     private String operationType;
     private String stockType;
     private String invoiceType;
     private String invoiceNo;
     private Date invoiceDate;
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
     private String accounter;
     private Date accountDate;
     private String currentStatus;


    // Constructors

    /** default constructor */
    public MaterialInvoiceMaster() {
    }

	/** minimal constructor */
    public MaterialInvoiceMaster(String autoId, String unitsCode, String storageCode, String billNo) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
    }
    
    /** full constructor */
    public MaterialInvoiceMaster(String autoId, String unitsCode, String storageCode, String billNo, Date billDate, String operationType, String stockType, String invoiceType, String invoiceNo, Date invoiceDate, String salerCode, String salerName, String deptCode, String personId, String payCondition, Double totalCosts, String remark, String maker, Date makeDate, String verifier, Date verifyDate, String accounter, Date accountDate, String currentStatus) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
        this.billDate = billDate;
        this.operationType = operationType;
        this.stockType = stockType;
        this.invoiceType = invoiceType;
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
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
        this.accounter = accounter;
        this.accountDate = accountDate;
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

    public String getInvoiceType() {
        return this.invoiceType;
    }
    
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }
    
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getInvoiceDate() {
        return this.invoiceDate;
    }
    
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
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
   








}