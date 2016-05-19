package cn.superion.material.stat.entity;

import java.util.Date;

/**
 * 库存统计结果类,用于入库汇总和出库汇总统计
 * @author 曹国魁
 *
 */
public class StockStatistic {
	private String storageCode;
	//供应商或领用部门
	private String salerCode;
	private String salerName;
	private String materialClass;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private Double tradePrice;
	private Double amount;
	private Double tradeMoney;
	private Double initMoney;
	private Double currentStockMoney;
	private Double retailMoney;
	private Date accountDate;
	private Date invoiceDate;
	private Date billDate;
	private String invoiceNo;
	private String remark;
	private String countClass;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getRdBillNo() {
		return rdBillNo;
	}
	public void setRdBillNo(String rdBillNo) {
		this.rdBillNo = rdBillNo;
	}

	private String billNo;
	private String rdBillNo;
	public String getStorageCode() {
		return storageCode;
	}
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}
	public String getSalerCode() {
		return salerCode;
	}
	public void setSalerCode(String salerCode) {
		this.salerCode = salerCode;
	}
	public String getSalerName() {
		return salerName;
	}
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	public String getMaterialClass() {
		return materialClass;
	}
	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialSpec() {
		return materialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}
	public String getMaterialUnits() {
		return materialUnits;
	}
	public void setMaterialUnits(String materialUnits) {
		this.materialUnits = materialUnits;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public Double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	/** full constructor */
	public StockStatistic(String storageCode, String salerCode,
			String salerName, String materialClass, String materialCode,
			String materialName, String materialSpec, String materialUnits,Double tradePrice, 
			Double amount, Double tradeMoney,Double initMoney,Double currentStockMoney,Double retailMoney,String countClass,
			Date invoiceDate,String invoiceNo,Date billDate,String billNo,String rdBillNo,Date accountDate,String remark) {
		super();
		this.storageCode = storageCode;
		this.salerCode = salerCode;
		this.salerName = salerName;
		this.materialClass = materialClass;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.tradePrice = tradePrice;
		this.amount = amount;
		this.tradeMoney = tradeMoney;
		this.invoiceDate = invoiceDate;
		this.invoiceNo = invoiceNo;
		this.billDate = billDate;
		this.billNo = billNo;
		this.rdBillNo = rdBillNo;
		this.accountDate = accountDate;
		this.remark=remark;
		this.initMoney = initMoney;
		this.currentStockMoney = currentStockMoney;
		this.retailMoney = retailMoney;
		this.countClass = countClass;
	}
	public Double getInitMoney() {
		return initMoney;
	}
	public void setInitMoney(Double initMoney) {
		this.initMoney = initMoney;
	}
	public Double getCurrentStockMoney() {
		return currentStockMoney;
	}
	public void setCurrentStockMoney(Double currentStockMoney) {
		this.currentStockMoney = currentStockMoney;
	}
	public Double getRetailMoney() {
		return retailMoney;
	}
	public void setRetailMoney(Double retailMoney) {
		this.retailMoney = retailMoney;
	}
	public String getCountClass() {
		return countClass;
	}
	public void setCountClass(String countClass) {
		this.countClass = countClass;
	}
	
}
