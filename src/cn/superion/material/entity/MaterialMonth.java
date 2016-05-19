package cn.superion.material.entity;

import java.util.Date;


/**
 * MaterialMonth entity. @author MyEclipse Persistence Tools
 */

public class MaterialMonth  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 856576801176184532L;
	private String autoId;
     private String unitsCode;
     private String storageCode;
     private String yearMonth;
     private Date startDate;
     private Date endDate;
     private String accountSign;
     private String createPerson;
     private Date createDate;


    // Constructors

    /** default constructor */
    public MaterialMonth() {
    }

	/** minimal constructor */
    public MaterialMonth(String autoId, String unitsCode, String storageCode) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
    }
    
    /** full constructor */
    public MaterialMonth(String autoId, String unitsCode, String storageCode, String yearMonth, Date startDate, Date endDate, String accountSign, String createPerson, Date createDate) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.storageCode = storageCode;
        this.yearMonth = yearMonth;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accountSign = accountSign;
        this.createPerson = createPerson;
        this.createDate = createDate;
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

    public String getYearMonth() {
        return this.yearMonth;
    }
    
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAccountSign() {
        return this.accountSign;
    }
    
    public void setAccountSign(String accountSign) {
        this.accountSign = accountSign;
    }

    public String getCreatePerson() {
        return this.createPerson;
    }
    
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
   








}