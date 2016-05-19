package cn.superion.cssd.entity;

import java.io.Serializable;

/**
 * 科室领用的物品包物资VO
 * @author 曹国魁
 *
 */
public class DeptPackageMaterial implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9138687451819412153L;
	//物品包信息
	private String packageId;
	private String packageClass;
	private String packageName;
	private String packageMode;
	private String packageUnits;
	//包内的物资信息
	private String materialClass;
	private String materialId;	
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private String factoryCode;
	private Double tradePrice;
	//使用数量，即应回数量
	private Double deliverAmount;
	
	public DeptPackageMaterial(){}
	
	public DeptPackageMaterial(String packageId,String packageName,String materialCode,String materialName,String materialSpec,String materialUnits,Double tradePrice,Double amount){
		this.packageId = packageId;
		this.packageName = packageName;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec= materialSpec;
		this.materialUnits = materialUnits;
		this.tradePrice = tradePrice;
		this.deliverAmount = amount;
	}
	
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPackageClass() {
		return packageClass;
	}
	public void setPackageClass(String packageClass) {
		this.packageClass = packageClass;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageMode() {
		return packageMode;
	}
	public void setPackageMode(String packageMode) {
		this.packageMode = packageMode;
	}
	public String getPackageUnits() {
		return packageUnits;
	}
	public void setPackageUnits(String packageUnits) {
		this.packageUnits = packageUnits;
	}
	public String getMaterialClass() {
		return materialClass;
	}
	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
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
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public Double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getDeliverAmount() {
		return deliverAmount;
	}

	public void setDeliverAmount(Double deliverAmount) {
		this.deliverAmount = deliverAmount;
	}

	
	
}
