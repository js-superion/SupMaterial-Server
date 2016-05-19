package cn.superion.material.entity;



import java.util.Date;
/**
 * MaterialProvideMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialProvideMaster  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 4462263418435820813L;
	private String autoId;
     private String unitsCode;
     private String storageCode;
     private String billNo;
     private Date billDate;
     private String invoiceType;
     private String supplyDeptCode;
     private String cardCode;
     private Double accountRemain;
     private String deptCode;
     private String personId;
     private String remark;
     private String remark1;
     
     private String maker;
     private Date makeDate;
     private String verifier;
     private Date verifyDate;
     private String currentStatus;
     
     private String sendStatus;
     private String sendNo;
     private String execPerson; //执行人
     private Date execDate;//执行时间
     private String checkPerson;//审核人
     private Date checkDate;//审核日期
     private String checkSign;
     
     private String checkAmountSign;//审核人
     private Date checkAmountDate;//审核日期
     private String checkAmountPerson;
     
     private String checkSupplyPerson;//审核人
     private Date checkSupplyDate;//审核日期
     private String checkSupplySign;
     private String printPerson;
     private Date printDate;//打印日期
     
     private String importPerson;//入库人
     private Date importDate;//入库日期
     private String manualSign;//入库人
     private String remark2;
     private String remark3;
     private String check2;
     private String check3;

    // Constructors

    /** default constructor */
    public MaterialProvideMaster() {
    }

	/** minimal constructor */
    public MaterialProvideMaster(String autoId, String unitsCode, String storageCode, String billNo) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
    }
    
    public String getManualSign() {
		return manualSign;
	}

	public void setManualSign(String manualSign) {
		this.manualSign = manualSign;
	}

	/** full constructor */
    public MaterialProvideMaster(String autoId, String unitsCode,String remark1,String remark2,
    		String storageCode, String billNo, Date billDate, String deptCode, String personId, 
    		String remark, String maker, Date makeDate, String verifier,String manualSign,
    		Date verifyDate, String currentStatus,String sendStatus,String sendNo,String execPerson,Date execDate,String checkPerson
    		,Date checkDate,String importPerson,Date importDate) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
        this.manualSign = manualSign;
        this.billDate = billDate;
        this.deptCode = deptCode;
        this.personId = personId;
        this.remark = remark;
        this.maker = maker;
        this.makeDate = makeDate;
        this.verifier = verifier;
        this.verifyDate = verifyDate;
        this.currentStatus = currentStatus;
        this.sendStatus = sendStatus;
        
        this.sendNo = sendNo;
        this.execPerson = execPerson;
        this.checkPerson = checkPerson;
        this.importPerson = importPerson;
        this.execDate = execDate;
        this.checkDate = checkDate;
        this.importDate = importDate;
    }

   
    // Property accessors

    public String getCheckSign() {
		return checkSign;
	}

	public void setCheckSign(String checkSign) {
		this.checkSign = checkSign;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getCheck2() {
		return check2;
	}

	public void setCheck2(String check2) {
		this.check2 = check2;
	}

	public String getCheck3() {
		return check3;
	}

	public void setCheck3(String check3) {
		this.check3 = check3;
	}

	public String getCheckAmountSign() {
		return checkAmountSign;
	}

	public void setCheckAmountSign(String checkAmountSign) {
		this.checkAmountSign = checkAmountSign;
	}

	public Date getCheckAmountDate() {
		return checkAmountDate;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public void setCheckAmountDate(Date checkAmountDate) {
		this.checkAmountDate = checkAmountDate;
	}

	public String getCheckAmountPerson() {
		return checkAmountPerson;
	}

	public void setCheckAmountPerson(String checkAmountPerson) {
		this.checkAmountPerson = checkAmountPerson;
	}

	public String getCheckSupplyPerson() {
		return checkSupplyPerson;
	}

	public void setCheckSupplyPerson(String checkSupplyPerson) {
		this.checkSupplyPerson = checkSupplyPerson;
	}

	public Date getCheckSupplyDate() {
		return checkSupplyDate;
	}

	public void setCheckSupplyDate(Date checkSupplyDate) {
		this.checkSupplyDate = checkSupplyDate;
	}

	public String getCheckSupplySign() {
		return checkSupplySign;
	}

	public void setCheckSupplySign(String checkSupplySign) {
		this.checkSupplySign = checkSupplySign;
	}

	public String getPrintPerson() {
		return printPerson;
	}

	public void setPrintPerson(String printPerson) {
		this.printPerson = printPerson;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

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

    public String getCurrentStatus() {
        return this.currentStatus;
    }
    
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

	public String getInvoiceType() {
		return invoiceType;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSendNo() {
		return sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}

	public String getExecPerson() {
		return execPerson;
	}

	public void setExecPerson(String execPerson) {
		this.execPerson = execPerson;
	}

	public Date getExecDate() {
		return execDate;
	}

	public void setExecDate(Date execDate) {
		this.execDate = execDate;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getImportPerson() {
		return importPerson;
	}

	public void setImportPerson(String importPerson) {
		this.importPerson = importPerson;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getSupplyDeptCode() {
		return supplyDeptCode;
	}

	public void setSupplyDeptCode(String supplyDeptCode) {
		this.supplyDeptCode = supplyDeptCode;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public Double getAccountRemain() {
		return accountRemain;
	}

	public void setAccountRemain(Double accountRemain) {
		this.accountRemain = accountRemain;
	}
   
}