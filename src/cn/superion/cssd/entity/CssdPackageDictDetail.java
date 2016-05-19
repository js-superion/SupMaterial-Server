package cn.superion.cssd.entity;

/**
 * CssdPackageDictDetail entity. @author MyEclipse Persistence Tools
 */
 
public class CssdPackageDictDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2169053205144424774L;
	private String unitsCode;
	private String packageId;
	private Short serialNo;
	private String materialClass;
	private String materialId;	
	private Double amount;
	private String materialSign;
	private String remark;

	// Constructors

	/** default constructor */
	public CssdPackageDictDetail() {
	}

	/** minimal constructor */
	public CssdPackageDictDetail(Short serialNo,
			String materialClass) {
		this.serialNo = serialNo;
		this.materialClass = materialClass;
	}

	/** full constructor */
	public CssdPackageDictDetail( String unitsCode,String packageId,Short serialNo,
			String materialClass, String materialId,  Double amount,  String materialSign, 
			String remark) {
		this.unitsCode = unitsCode;
		this.packageId = packageId;
		this.serialNo = serialNo;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.amount = amount;
		this.materialSign = materialSign;
		this.remark = remark;
	}
	
	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getUnitsCode() {
		return unitsCode;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public Short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public String getMaterialClass() {
		return this.materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}

	

	

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	



	public String getMaterialSign() {
		return this.materialSign;
	}

	public void setMaterialSign(String materialSign) {
		this.materialSign = materialSign;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((materialClass == null) ? 0 : materialClass.hashCode());
		result = prime * result
				+ ((materialId == null) ? 0 : materialId.hashCode());
		result = prime * result
				+ ((materialSign == null) ? 0 : materialSign.hashCode());
		result = prime * result
				+ ((packageId == null) ? 0 : packageId.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
		result = prime * result
				+ ((unitsCode == null) ? 0 : unitsCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CssdPackageDictDetail other = (CssdPackageDictDetail) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (materialClass == null) {
			if (other.materialClass != null)
				return false;
		} else if (!materialClass.equals(other.materialClass))
			return false;
		if (materialId == null) {
			if (other.materialId != null)
				return false;
		} else if (!materialId.equals(other.materialId))
			return false;
		if (materialSign == null) {
			if (other.materialSign != null)
				return false;
		} else if (!materialSign.equals(other.materialSign))
			return false;
		if (packageId == null) {
			if (other.packageId != null)
				return false;
		} else if (!packageId.equals(other.packageId))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		if (unitsCode == null) {
			if (other.unitsCode != null)
				return false;
		} else if (!unitsCode.equals(other.unitsCode))
			return false;
		return true;
	}



}