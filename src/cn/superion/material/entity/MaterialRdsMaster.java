package cn.superion.material.entity;

import java.util.Date;


/**
 * MaterialRdsMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialRdsMaster  implements java.io.Serializable,Cloneable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -6766998597548531866L;
	private String unitsCode;
     private String autoId;
     private String storageCode;
     private String billNo;
     private Date billDate;
     private String invoiceType;
     private Date invoiceDate;
     private String rdFlag;
     private String rdType;
     private String operationType;
     private String operationNo;
     private String orderNo;
     private String arrivalNo;
     private String cardCode;
     private String deptCode;
     private String personId;
     private String salerCode;
     private String salerName;
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
     private String cargoNo;
     private String billMonthNo;
     private String printSign;
     private String invoiceNo;
    // Constructors


	/** default constructor */
    public MaterialRdsMaster() {
    }

	/** minimal constructor */
    public MaterialRdsMaster(String unitsCode, String storageCode, String billNo, Date billDate) {
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
        this.billDate = billDate;
    }
    
    /** full constructor */
    public MaterialRdsMaster(String unitsCode, String autoId, String storageCode, String billNo, Date billDate, String invoiceType, String rdFlag, String rdType, String operationType, String operationNo, String orderNo, String arrivalNo, String cardCode, String deptCode, String personId, String salerCode, String salerName, String remark, String maker, Date makeDate, String verifier, Date verifyDate, String accounter, Date accountDate, String currentStatus) {
        this.unitsCode = unitsCode;
        this.autoId = autoId;
        this.storageCode = storageCode;
        this.billNo = billNo;
        this.billDate = billDate;
        this.invoiceType = invoiceType;
        this.rdFlag = rdFlag;
        this.rdType = rdType;
        this.operationType = operationType;
        this.operationNo = operationNo;
        this.orderNo = orderNo;
        this.arrivalNo = arrivalNo;
        this.cardCode = cardCode;
        this.deptCode = deptCode;
        this.personId = personId;
        this.salerCode = salerCode;
        this.salerName = salerName;
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
    
    
    public String getUnitsCode() {
        return this.unitsCode;
    }
    
    public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPrintSign() {
		return printSign;
	}

	public void setPrintSign(String printSign) {
		this.printSign = printSign;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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

    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getArrivalNo() {
        return this.arrivalNo;
    }
    
    public void setArrivalNo(String arrivalNo) {
        this.arrivalNo = arrivalNo;
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
	
	public String getCargoNo() {
		return cargoNo;
	}

	public void setCargoNo(String cargoNo) {
		this.cargoNo = cargoNo;
	}
	
	public String getBillMonthNo() {
		return billMonthNo;
	}

	public void setBillMonthNo(String billMonthNo) {
		this.billMonthNo = billMonthNo;
	}
	
	public MaterialRdsMaster clone(){
		MaterialRdsMaster mrm = null;
		try {
			mrm = (MaterialRdsMaster) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return mrm;
	}
	
	public MaterialRdsMaster writeRed(){
		MaterialRdsMaster red = this.clone();
		red.setAutoId(null);
		red.setInvoiceType("2");
		red.setMakeDate(null);
		red.setMaker(null);
		red.setVerifyDate(null);
		red.setVerifier(null);
		return red;
	}
}