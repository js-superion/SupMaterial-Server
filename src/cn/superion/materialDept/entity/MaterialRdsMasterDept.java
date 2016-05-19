package cn.superion.materialDept.entity;

import java.util.Date;


/**
 * MaterialRdsMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialRdsMasterDept  implements java.io.Serializable,Cloneable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1179182006864677833L;
	
	private String unitsCode;
     private String autoId;
     private String storageCode;
     private String billNo;
     private Date billDate;
     private String invoiceType;
     private String rdFlag;
     private String rdType;
     private String operationType;
     private String operationNo;
     private String supplyDeptCode;
     private String cardCode;
     private String deptCode;
     private String personId;
     private String remark;
     private String maker;
     private Date makeDate;
     private String verifier;
     private Date verifyDate;
     private String accounter;
     private Date accountDate;
     private String currentStatus;
     //扩展，来源单据主记录ID
     private String sourceAutoId;


    // Constructors

    /** default constructor */
    public MaterialRdsMasterDept() {
    }

	/** minimal constructor */
    public MaterialRdsMasterDept(String unitsCode, String storageCode, String billNo, Date billDate) {
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
        this.billDate = billDate;
    }
   
    // Property accessors

    public String getUnitsCode() {
        return this.unitsCode;
    }
    
    public void setUnitsCode(String unitsCode) {
        this.unitsCode = unitsCode;
    }

    public String getAutoId() {
        return this.autoId;
    }
    
    public void setAutoId(String autoId) {
        this.autoId = autoId;
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

	public String getSourceAutoId() {
		return sourceAutoId;
	}

	public void setSourceAutoId(String sourceAutoId) {
		this.sourceAutoId = sourceAutoId;
	}

	public String getSupplyDeptCode() {
		return supplyDeptCode;
	}

	public void setSupplyDeptCode(String supplyDeptCode) {
		this.supplyDeptCode = supplyDeptCode;
	}
	
	public MaterialRdsMasterDept clone(){
		MaterialRdsMasterDept copy = null;
		try {
			copy = (MaterialRdsMasterDept)super.clone();
			copy.setAutoId(null);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
}