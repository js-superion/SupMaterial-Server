package cn.superion.cssd.entity;

import java.util.Date;


/**
 * VCssdDeliverDetailId entity. @author MyEclipse Persistence Tools
 */

public class VCssdDeliverStock  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1082210039856758513L;
	private String packageNo;
     private String unitsCode;
     private String deptUnitsCode;
     private String packageClass;
     private String packageId;
     private String packageName;
     private String packageMode;
     private String packageUnits;
     private Double tradePrice;
     private Double amount;
     private Double materialFee;
     private Double sterilizeFee;
     private Date availDate;
     private String packager;
     private String sterilizeNo;
     private String sterilizeOrder;
     private String sterilizeType;
     private Date sterilizeDate;
     private String sterilizeStatus;
     private String detailRemark;
     private String phoInputCode;
 	 private String fiveInputCode;
     private String usedSign;
     private String currentStatus;
     private String packageAutoId;
     private Short packageSerialNo;
     private String sterilizeAutoId;
     private Short sterilizeSerialNo;
     private String deliverAutoId;
     private Short deliverSerialNo;
     private String billNo;
     private Date billDate;
     private String deptCode;
     private String personId;
     private String deliverPerson;
     private String remark;
     private String maker;
     private Date makeDate;
     private String verifier;
     private Date verifyDate;


    // Constructors

    /** default constructor */
    public VCssdDeliverStock() {
    }

	/** minimal constructor */
    public VCssdDeliverStock(String packageNo, String unitsCode, String packageClass, String packageId) {
        this.packageNo = packageNo;
        this.unitsCode = unitsCode;
        this.packageClass = packageClass;
        this.packageId = packageId;
    }
    
    /** full constructor */
    public VCssdDeliverStock(String packageNo, String unitsCode,String deptUnitsCode, String packageClass, String packageId, String packageName, String packageMode, String packageUnits, Double tradePrice, Double amount, Double materialFee, Double sterilizeFee, Date availDate, String packager, String sterilizeNo, String sterilizeOrder, String sterilizeType, Date sterilizeDate, String sterilizeStatus, String detailRemark, String usedSign, String currentStatus, String packageAutoId, Short packageSerialNo, String sterilizeAutoId, Short sterilizeSerialNo, String deliverAutoId, Short deliverSerialNo, String billNo, Date billDate, String deptCode, String personId, String deliverPerson, String remark, String maker, Date makeDate, String verifier, Date verifyDate) {
        this.packageNo = packageNo;
        this.unitsCode = unitsCode;
        this.deptUnitsCode = deptUnitsCode;
        this.packageClass = packageClass;
        this.packageId = packageId;
        this.packageName = packageName;
        this.packageMode = packageMode;
        this.packageUnits = packageUnits;
        this.tradePrice = tradePrice;
        this.amount = amount;
        this.materialFee = materialFee;
        this.sterilizeFee = sterilizeFee;
        this.availDate = availDate;
        this.packager = packager;
        this.sterilizeNo = sterilizeNo;
        this.sterilizeOrder = sterilizeOrder;
        this.sterilizeType = sterilizeType;
        this.sterilizeDate = sterilizeDate;
        this.sterilizeStatus = sterilizeStatus;
        this.detailRemark = detailRemark;
        this.usedSign = usedSign;
        this.currentStatus = currentStatus;
        this.packageAutoId = packageAutoId;
        this.packageSerialNo = packageSerialNo;
        this.sterilizeAutoId = sterilizeAutoId;
        this.sterilizeSerialNo = sterilizeSerialNo;
        this.deliverAutoId = deliverAutoId;
        this.deliverSerialNo = deliverSerialNo;
        this.billNo = billNo;
        this.billDate = billDate;
        this.deptCode = deptCode;
        this.personId = personId;
        this.deliverPerson = deliverPerson;
        this.remark = remark;
        this.maker = maker;
        this.makeDate = makeDate;
        this.verifier = verifier;
        this.verifyDate = verifyDate;
    }

   
    // Property accessors

    public String getPackageNo() {
        return this.packageNo;
    }
    
    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getUnitsCode() {
        return this.unitsCode;
    }
    
    public void setUnitsCode(String unitsCode) {
        this.unitsCode = unitsCode;
    }

    public String getPackageClass() {
        return this.packageClass;
    }
    
    public void setPackageClass(String packageClass) {
        this.packageClass = packageClass;
    }

    public String getPackageId() {
        return this.packageId;
    }
    
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return this.packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageMode() {
        return this.packageMode;
    }
    
    public void setPackageMode(String packageMode) {
        this.packageMode = packageMode;
    }

    public String getPackageUnits() {
        return this.packageUnits;
    }
    
    public void setPackageUnits(String packageUnits) {
        this.packageUnits = packageUnits;
    }

    public Double getTradePrice() {
        return this.tradePrice;
    }
    
    public String getDeptUnitsCode() {
		return deptUnitsCode;
	}

	public void setDeptUnitsCode(String deptUnitsCode) {
		this.deptUnitsCode = deptUnitsCode;
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

    public Double getMaterialFee() {
        return this.materialFee;
    }
    
    public void setMaterialFee(Double materialFee) {
        this.materialFee = materialFee;
    }

    public Double getSterilizeFee() {
        return this.sterilizeFee;
    }
    
    public void setSterilizeFee(Double sterilizeFee) {
        this.sterilizeFee = sterilizeFee;
    }

    public Date getAvailDate() {
        return this.availDate;
    }
    
    public void setAvailDate(Date availDate) {
        this.availDate = availDate;
    }

    public String getPackager() {
        return this.packager;
    }
    
    public void setPackager(String packager) {
        this.packager = packager;
    }

    public String getSterilizeNo() {
        return this.sterilizeNo;
    }
    
    public void setSterilizeNo(String sterilizeNo) {
        this.sterilizeNo = sterilizeNo;
    }

    public String getSterilizeOrder() {
        return this.sterilizeOrder;
    }
    
    public void setSterilizeOrder(String sterilizeOrder) {
        this.sterilizeOrder = sterilizeOrder;
    }

    public String getSterilizeType() {
        return this.sterilizeType;
    }
    
    public void setSterilizeType(String sterilizeType) {
        this.sterilizeType = sterilizeType;
    }

    public Date getSterilizeDate() {
        return this.sterilizeDate;
    }
    
    public void setSterilizeDate(Date sterilizeDate) {
        this.sterilizeDate = sterilizeDate;
    }

    public String getSterilizeStatus() {
        return this.sterilizeStatus;
    }
    
    public void setSterilizeStatus(String sterilizeStatus) {
        this.sterilizeStatus = sterilizeStatus;
    }

    public String getDetailRemark() {
        return this.detailRemark;
    }
    
    public void setDetailRemark(String detailRemark) {
        this.detailRemark = detailRemark;
    }

    public String getUsedSign() {
        return this.usedSign;
    }
    
    public void setUsedSign(String usedSign) {
        this.usedSign = usedSign;
    }

    public String getCurrentStatus() {
        return this.currentStatus;
    }
    
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getPackageAutoId() {
        return this.packageAutoId;
    }
    
    public void setPackageAutoId(String packageAutoId) {
        this.packageAutoId = packageAutoId;
    }

    public Short getPackageSerialNo() {
        return this.packageSerialNo;
    }
    
    public void setPackageSerialNo(Short packageSerialNo) {
        this.packageSerialNo = packageSerialNo;
    }

    public String getSterilizeAutoId() {
        return this.sterilizeAutoId;
    }
    
    public void setSterilizeAutoId(String sterilizeAutoId) {
        this.sterilizeAutoId = sterilizeAutoId;
    }

    public Short getSterilizeSerialNo() {
        return this.sterilizeSerialNo;
    }
    
    public void setSterilizeSerialNo(Short sterilizeSerialNo) {
        this.sterilizeSerialNo = sterilizeSerialNo;
    }

    public String getDeliverAutoId() {
        return this.deliverAutoId;
    }
    
    public void setDeliverAutoId(String deliverAutoId) {
        this.deliverAutoId = deliverAutoId;
    }

    public Short getDeliverSerialNo() {
        return this.deliverSerialNo;
    }
    
    public void setDeliverSerialNo(Short deliverSerialNo) {
        this.deliverSerialNo = deliverSerialNo;
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

    public String getDeliverPerson() {
        return this.deliverPerson;
    }
    
    public void setDeliverPerson(String deliverPerson) {
        this.deliverPerson = deliverPerson;
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

	public String getPhoInputCode() {
		return phoInputCode;
	}

	public void setPhoInputCode(String phoInputCode) {
		this.phoInputCode = phoInputCode;
	}

	public String getFiveInputCode() {
		return fiveInputCode;
	}

	public void setFiveInputCode(String fiveInputCode) {
		this.fiveInputCode = fiveInputCode;
	}
   
}