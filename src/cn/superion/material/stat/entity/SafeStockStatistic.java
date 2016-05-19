package cn.superion.material.stat.entity;

/**
 * 安全库存预警统计结果
 * @author 曹国魁
 *
 */
public class SafeStockStatistic {
	private String storageCode;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private Double amount;
	private Double tradePrice;
	private Double safeStockAmount;
	public String getStorageCode() {
		return storageCode;
	}
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
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
	public Double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}
	public Double getSafeStockAmount() {
		return safeStockAmount;
	}
	public void setSafeStockAmount(Double safeStockAmount) {
		this.safeStockAmount = safeStockAmount;
	}
	
	/** full constructor */
	public SafeStockStatistic(String storageCode, String materialCode,
			String materialName, String materialSpec, String materialUnits,
			Double amount, Double tradePrice, Double safeStockAmount) {
		super();
		this.storageCode = storageCode;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.amount = amount;
		this.tradePrice = tradePrice;
		this.safeStockAmount = safeStockAmount;
	}
	public SafeStockStatistic() {
	}
	
}
