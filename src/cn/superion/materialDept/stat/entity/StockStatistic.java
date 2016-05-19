package cn.superion.materialDept.stat.entity;

/**
 * 库存统计结果类,用于科室物资入库汇总和出库汇总统计
 * @author 曹国魁
 *
 */
public class StockStatistic {
	private String storageCode;
	//供应部门
	private String deptCode;
	private String deptName;
	private String materialClass;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String batch;
	private Double amount;
	private Double tradeMoney;
	public String getStorageCode() {
		return storageCode;
	}
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public StockStatistic(String storageCode,String materialClass, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			Double amount, Double tradeMoney) {
		super();
		this.storageCode = storageCode;
		this.materialClass = materialClass;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.amount = amount;
		this.tradeMoney = tradeMoney;
	}
	
	/** full constructor */
	public StockStatistic(String storageCode, String deptCode,
			String deptName, String materialClass, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			Double amount, Double tradeMoney,String batch) {
		super();
		this.storageCode = storageCode;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.materialClass = materialClass;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.amount = amount;
		this.tradeMoney = tradeMoney;
		this.batch = batch;
	}
	
}
