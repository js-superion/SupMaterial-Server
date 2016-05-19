package cn.superion.cssd.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CssdStockMaster entity. @author MyEclipse Persistence Tools
 */

public class CssdStockMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 391425887914236449L;
	private String packageNo;
	private String unitsCode;
	private String deptUnitsCode;
	private String deptUnitsName;
	private String packageClass;
	private String packageId;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	private Double tradePrice;
	private Double amount;
	private Double applyAmount;
	public Double getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(Double applyAmount) {
		this.applyAmount = applyAmount;
	}

	private Double materialFee;
	private Double sterilizeFee;
	private Date availDate;
	private String packager;
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
	private String sterilizeNo;
	private String sterilizeOrder;
	private String sterilizeType;
	private Date sterilizeDate;
	private  String provideAutoId;
	private String registerStatus;
	private String inpNo;
	private Date registeDate;
	private String register;
	private String verifier;
	private Date verifierDate;
	private String addSign;
	
	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public Date getVerifierDate() {
		return verifierDate;
	}

	public void setVerifierDate(Date verifierDate) {
		this.verifierDate = verifierDate;
	}

	public String getRegisterStatus() {
		return registerStatus;
	}

	public String getAddSign() {
		return addSign;
	}

	public void setAddSign(String addSign) {
		this.addSign = addSign;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}

	public String getInpNo() {
		return inpNo;
	}

	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}

	public Date getRegisteDate() {
		return registeDate;
	}

	public void setRegisteDate(Date registeDate) {
		this.registeDate = registeDate;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getProvideAutoId() {
		return provideAutoId;
	}

	public void setProvideAutoId(String providerAutoId) {
		this.provideAutoId = providerAutoId;
	}

	private List<CssdStockDetail> stockDetailList = new ArrayList<CssdStockDetail>();
	// Constructors

	/** default constructor */
	public CssdStockMaster() {
	}

	/** minimal constructor */
	public CssdStockMaster(String packageNo, String unitsCode,
			String packageClass, String packageId) {
		this.packageNo = packageNo;
		this.unitsCode = unitsCode;
		this.packageClass = packageClass;
		this.packageId = packageId;
	}

	/** full constructor 	
	 * @param sterilizeNo */
	public CssdStockMaster(String packageNo, String unitsCode,String deptUnitsCode,String deptUnitsName,
			String packageClass, String packageId, String packageName,
			String packageMode, String packageUnits, Double tradePrice,
			Double amount, Double materialFee, Double sterilizeFee,
			Date availDate, String packager, String sterilizeStatus,
			String detailRemark, String usedSign, String currentStatus,
			String packageAutoId, Short packageSerialNo,
			String sterilizeAutoId, Short sterilizeSerialNo, String addSign,
			String deliverAutoId, Short deliverSerialNo,String sterilizeNo, String sterilizeOrder,
			String sterilizeType, Date sterilizeDate,String providerAutoId,String registerStatus,String inpNo,Date registeDate,String register,String verifier,Date verifierDate) {
		this.registerStatus=registerStatus;
		this.inpNo=inpNo;
		this.registeDate=registeDate;
		this.register=register;
		this.packageNo = packageNo;
		this.unitsCode = unitsCode;
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
		this.sterilizeStatus = sterilizeStatus;
		this.detailRemark = detailRemark;
		this.usedSign = usedSign;
		this.addSign = addSign;
		this.currentStatus = currentStatus;
		this.packageAutoId = packageAutoId;
		this.packageSerialNo = packageSerialNo;
		this.sterilizeAutoId = sterilizeAutoId;
		this.sterilizeSerialNo = sterilizeSerialNo;
		this.deliverAutoId = deliverAutoId;
		this.deliverSerialNo = deliverSerialNo;
		this.sterilizeNo = sterilizeNo;
		this.sterilizeOrder = sterilizeOrder;
		this.sterilizeType = sterilizeType;
		this.sterilizeDate = sterilizeDate;
		this.deptUnitsCode=deptUnitsCode;
		this.deptUnitsName=deptUnitsName;
		this.provideAutoId=providerAutoId;
		this.verifier=verifier;
		this.verifierDate=verifierDate;
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

	public String getDeptUnitsCode() {
		return deptUnitsCode;
	}

	public void setDeptUnitsCode(String deptUnitsCode) {
		this.deptUnitsCode = deptUnitsCode;
	}

	public String getDeptUnitsName() {
		return deptUnitsName;
	}

	public void setDeptUnitsName(String deptUnitsName) {
		this.deptUnitsName = deptUnitsName;
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

	public List<CssdStockDetail> getStockDetailList() {
		return stockDetailList;
	}

	public void setStockDetailList(List<CssdStockDetail> stockDetailList) {
		this.stockDetailList = stockDetailList;
	}
	
	public void addStockDetail(CssdStockDetail stockDetail){
		if(this.stockDetailList == null){
			this.stockDetailList = new ArrayList<CssdStockDetail>();
		}
		this.stockDetailList.add(stockDetail);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((packageNo == null) ? 0 : packageNo.hashCode());
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
		CssdStockMaster other = (CssdStockMaster) obj;
		if (packageNo == null) {
			if (other.packageNo != null)
				return false;
		} else if (!packageNo.equals(other.packageNo))
			return false;
		return true;
	}

}