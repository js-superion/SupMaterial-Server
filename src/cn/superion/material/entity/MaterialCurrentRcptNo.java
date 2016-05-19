package cn.superion.material.entity;



/**
 * MaterialCurrentRcptNo entity. @author MyEclipse Persistence Tools
 */

public class MaterialCurrentRcptNo  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -5691742119051652113L;
	private String autoId;
     private String unitsCode;
     private String rdFlag;
     private String rcptType;
     private String storageCode;
     private String typeDate;
     private String currentNo;
     private String rdType;


	/** default constructor */
    public MaterialCurrentRcptNo() {
    }

    public MaterialCurrentRcptNo(String unitsCode, String rdFlag,
			String rcptType, String storageCode, String typeDate,
			String currentNo) {
		this.unitsCode = unitsCode;
		this.rdFlag = rdFlag;
		this.rcptType = rcptType;
		this.storageCode = storageCode;
		this.typeDate = typeDate;
		this.currentNo = currentNo;
	}
    
    public MaterialCurrentRcptNo(String unitsCode, String rdFlag,
			String rcptType, String storageCode, String typeDate,
			String currentNo,String rdType) {
		this.unitsCode = unitsCode;
		this.rdFlag = rdFlag;
		this.rcptType = rcptType;
		this.storageCode = storageCode;
		this.typeDate = typeDate;
		this.currentNo = currentNo;
		this.rdType = rdType;
	}
    
    
    /** full constructor */
    public MaterialCurrentRcptNo(String autoId, String unitsCode, String rdFlag, String rcptType, String storageCode, String typeDate, String currentNo,String rdType) {
        this.autoId = autoId;
        this.unitsCode = unitsCode;
        this.rdFlag = rdFlag;
        this.rcptType = rcptType;
        this.storageCode = storageCode;
        this.typeDate = typeDate;
        this.currentNo = currentNo;
        this.rdType = rdType;
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

    public String getRdFlag() {
        return this.rdFlag;
    }
    
    public void setRdFlag(String rdFlag) {
        this.rdFlag = rdFlag;
    }

    public String getRcptType() {
        return this.rcptType;
    }
    
    public void setRcptType(String rcptType) {
        this.rcptType = rcptType;
    }

    public String getStorageCode() {
        return this.storageCode;
    }
    
    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getTypeDate() {
        return this.typeDate;
    }
    
    public void setTypeDate(String typeDate) {
        this.typeDate = typeDate;
    }

    public String getCurrentNo() {
        return this.currentNo;
    }
    
    public void setCurrentNo(String currentNo) {
        this.currentNo = currentNo;
    }
   
    // Constructors

    public String getRdType() {
		return rdType;
	}

	public void setRdType(String rdType) {
		this.rdType = rdType;
	}

}