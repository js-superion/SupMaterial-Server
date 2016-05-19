package cn.superion.material.entity;

import java.util.Date;


/**
 * MaterialRejectMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialRejectMaster  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1778969757106210046L;
	private String autoId;
     private String unitsCode;
     private String storageCode;
     private String billNo;
     private Date billDate;
     private String outDeptCode;
     private String personId;
     private String rejectReason;
     private String remark;
     private String maker;
     private Date makeDate;
     private String verifier;
     private Date verifyDate;
     private String currentStatus;
     //报损出库类别，关联收发存主记录的rdType
     private String rdType;


    // Constructors

    /** default constructor */
    public MaterialRejectMaster() {
    }

	/** minimal constructor */
    public MaterialRejectMaster(String autoId, String unitsCode, String billNo) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.billNo = billNo;
    }
    
    /** full constructor */
    public MaterialRejectMaster(String autoId, String unitsCode, String storageCode, String billNo, Date billDate, String outDeptCode, String personId, String rejectReason, String remark, String maker, Date makeDate, String verifier, Date verifyDate, String currentStatus) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.billNo = billNo;
        this.billDate = billDate;
        this.outDeptCode = outDeptCode;
        this.personId = personId;
        this.rejectReason = rejectReason;
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

    public String getOutDeptCode() {
        return this.outDeptCode;
    }
    
    public void setOutDeptCode(String outDeptCode) {
        this.outDeptCode = outDeptCode;
    }

    public String getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRejectReason() {
        return this.rejectReason;
    }
    
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
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

	public String getRdType() {
		return rdType;
	}

	public void setRdType(String rdType) {
		this.rdType = rdType;
	}

}