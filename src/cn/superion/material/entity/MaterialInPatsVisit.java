package cn.superion.material.entity;

import java.util.Date;


/**
 * MaterialInPatsVisit entity. @author MyEclipse Persistence Tools
 */

public class MaterialInPatsVisit  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -4945716308308514889L;
	private String autoId;
     private String unitsCode;
     private String patientId;
     private String inpNo;
     private String personName;
     private String sex;
     private Date dateOfBirth;
     private Short age;
     private String ageUnits;
     private String idNo;
     private String bloodName;
     private String rhType;
     private String operator;
     private Date operateDate;
     private String modifyPerson;
     private Date modifyDate;


    // Constructors

    /** default constructor */
    public MaterialInPatsVisit() {
    }

	/** minimal constructor */
    public MaterialInPatsVisit(String autoId, String unitsCode, String patientId, String personName) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.patientId = patientId;
        this.personName = personName;
    }
    
    /** full constructor */
    public MaterialInPatsVisit(String autoId, String unitsCode, String patientId, String inpNo, String personName, String sex, Date dateOfBirth, Short age, String ageUnits, String idNo, String bloodName, String rhType, String operator, Date operateDate, String modifyPerson, Date modifyDate) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.patientId = patientId;
        this.inpNo = inpNo;
        this.personName = personName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.ageUnits = ageUnits;
        this.idNo = idNo;
        this.bloodName = bloodName;
        this.rhType = rhType;
        this.operator = operator;
        this.operateDate = operateDate;
        this.modifyPerson = modifyPerson;
        this.modifyDate = modifyDate;
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

    public String getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getInpNo() {
        return this.inpNo;
    }
    
    public void setInpNo(String inpNo) {
        this.inpNo = inpNo;
    }

    public String getPersonName() {
        return this.personName;
    }
    
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Short getAge() {
        return this.age;
    }
    
    public void setAge(Short age) {
        this.age = age;
    }

    public String getAgeUnits() {
        return this.ageUnits;
    }
    
    public void setAgeUnits(String ageUnits) {
        this.ageUnits = ageUnits;
    }

    public String getIdNo() {
        return this.idNo;
    }
    
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getBloodName() {
        return this.bloodName;
    }
    
    public void setBloodName(String bloodName) {
        this.bloodName = bloodName;
    }

    public String getRhType() {
        return this.rhType;
    }
    
    public void setRhType(String rhType) {
        this.rhType = rhType;
    }

    public String getOperator() {
        return this.operator;
    }
    
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateDate() {
        return this.operateDate;
    }
    
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getModifyPerson() {
        return this.modifyPerson;
    }
    
    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }
    
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
   








}