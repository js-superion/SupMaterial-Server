package cn.superion.material.entity;

import java.util.Date;


/**
 * MaterialCheckMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialCheckMaster  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1424233227408858430L;
	private String autoId;
     private String unitsCode;
     private String storageCode;
     private String billNo;
     private Date billDate;
     private Date accountDate;
     private String deptCode;
     private String personId;
     private String remark;
     private String maker;
     private Date makeDate;
     private String verifier;
     private Date verifyDate;
     private String currentStatus;
     //盘盈入库类别，关联收发存主记录的rdType
     private String inRdType;
     //盘亏出库类别，关联收发存主记录的rdType
     private String outRdType;

    // Constructors

    /** default constructor */
    public MaterialCheckMaster() {
    }

	/** minimal constructor */
    public MaterialCheckMaster(String autoId, String unitsCode, String storageCode, String billNo) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
    }
    
    /** full constructor */
    public MaterialCheckMaster(String autoId, String unitsCode, String storageCode, String billNo, Date billDate, Date accountDate, String deptCode, String personId, String remark, String maker, Date makeDate, String verifier, Date verifyDate, String currentStatus) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
        this.billDate = billDate;
        this.accountDate = accountDate;
        this.deptCode = deptCode;
        this.personId = personId;
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

    public Date getAccountDate() {
        return this.accountDate;
    }
    
    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
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

	public String getInRdType() {
		return inRdType;
	}

	public void setInRdType(String inRdType) {
		this.inRdType = inRdType;
	}

	public String getOutRdType() {
		return outRdType;
	}

	public void setOutRdType(String outRdType) {
		this.outRdType = outRdType;
	}

	
}