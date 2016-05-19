package cn.superion.material.entity;

import java.util.Date;

/**
 * MaterialRdsAcctMaster entity. @author MyEclipse Persistence Tools
 */

public class MaterialRdsAcctMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 224366421230012438L;
	private String autoId;
	private String unitsCode;
	private String storageCode;
	private String rdFlag;
	private String deptUnitsCode;
	private String deptCode;
	private String deptName;
	private String salerCode;
	private String salerName;
	private Date billDate1;
	private Date billDate2;
	private String minRdBillNo;
	private String maxRdBillNo;
	private Double tradeSum;
	private Double factTradeSum;
	private Double wholeSaleSum;
	private Double inviteSum;
	private Double retailSum;
	private String accounter;
	private Date accountDate;

	// Constructors

	/** default constructor */
	public MaterialRdsAcctMaster() {
	}

	/** minimal constructor */
	public MaterialRdsAcctMaster(String autoId, String unitsCode,
			String storageCode, String rdFlag) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.rdFlag = rdFlag;
	}

	/** full constructor */
	public MaterialRdsAcctMaster(String autoId, String unitsCode,
			String storageCode, String rdFlag, String deptUnitsCode,String deptCode,
			String deptName, String salerCode, String salerName,
			Date billDate1, Date billDate2, String minRdBillNo,
			String maxRdBillNo, Double tradeSum, Double factTradeSum,
			Double wholeSaleSum, Double inviteSum, Double retailSum,
			String accounter, Date accountDate) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.rdFlag = rdFlag;
		this.deptUnitsCode = deptUnitsCode;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.salerCode = salerCode;
		this.salerName = salerName;
		this.billDate1 = billDate1;
		this.billDate2 = billDate2;
		this.minRdBillNo = minRdBillNo;
		this.maxRdBillNo = maxRdBillNo;
		this.tradeSum = tradeSum;
		this.factTradeSum = factTradeSum;
		this.wholeSaleSum = wholeSaleSum;
		this.inviteSum = inviteSum;
		this.retailSum = retailSum;
		this.accounter = accounter;
		this.accountDate = accountDate;
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

	public String getRdFlag() {
		return this.rdFlag;
	}

	public void setRdFlag(String rdFlag) {
		this.rdFlag = rdFlag;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return this.deptName;
	}
	public String getDeptUnitsCode() {
		return deptUnitsCode;
	}

	public void setDeptUnitsCode(String deptUnitsCode) {
		this.deptUnitsCode = deptUnitsCode;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public Date getBillDate1() {
		return this.billDate1;
	}

	public void setBillDate1(Date billDate1) {
		this.billDate1 = billDate1;
	}

	public Date getBillDate2() {
		return this.billDate2;
	}

	public void setBillDate2(Date billDate2) {
		this.billDate2 = billDate2;
	}

	public String getMinRdBillNo() {
		return this.minRdBillNo;
	}

	public void setMinRdBillNo(String minRdBillNo) {
		this.minRdBillNo = minRdBillNo;
	}

	public String getMaxRdBillNo() {
		return this.maxRdBillNo;
	}

	public void setMaxRdBillNo(String maxRdBillNo) {
		this.maxRdBillNo = maxRdBillNo;
	}

	public Double getTradeSum() {
		return this.tradeSum;
	}

	public void setTradeSum(Double tradeSum) {
		this.tradeSum = tradeSum;
	}

	public Double getFactTradeSum() {
		return this.factTradeSum;
	}

	public void setFactTradeSum(Double factTradeSum) {
		this.factTradeSum = factTradeSum;
	}

	public Double getWholeSaleSum() {
		return this.wholeSaleSum;
	}

	public void setWholeSaleSum(Double wholeSaleSum) {
		this.wholeSaleSum = wholeSaleSum;
	}

	public Double getInviteSum() {
		return this.inviteSum;
	}

	public void setInviteSum(Double inviteSum) {
		this.inviteSum = inviteSum;
	}

	public Double getRetailSum() {
		return this.retailSum;
	}

	public void setRetailSum(Double retailSum) {
		this.retailSum = retailSum;
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

}