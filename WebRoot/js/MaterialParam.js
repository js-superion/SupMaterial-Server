//ryh 13.01.21
this.salerClass="";//供应商分类，泰州=04，东方医院=""
this.factoryClass="";//生产厂商分类，泰州=03，东方医院=""
this.isEditorPrice=true;//是否允许修改进价和售价,泰州=false,东方医院=true
this.providerClassName="供货单位";//泰州=供应商分类,东方医院=供货单位
this.isAllUnitsDict=true;//是否加载所有单位下的基础数据，泰州=true，东方医院=false
this.isSelected=false;//核算系统中的采购入库单的勾选框,当发票号为空时是否允许勾选，泰州=false,东方=true
this.isDeptList=true;//科室领用申请时是否允许给所有授权科室申请，而非只能给当前用户所在科室，泰州=false，东方=true
this.isBacthRec = true;//采购入库处理时，需要提供物资的库存量批次记录,泰州的为false,东方的为true
this.isFee = true;//结账处理时，泰州的为false,东方的为true
this.isGray = true;//整进整出采购入库单号和出出库单号置为灰色。泰州的为false,东方的为true.
this.isTanchu = true;//领用出库保存后弹出增加界面，泰州false,东方的为true.
 
function getSalerClass(){
	return this.salerClass;
}

function getFactoryClass(){
	return this.factoryClass;
}

function getIsEditorPrice(){
	return this.isEditorPrice;
}

function getProviderClassName(){
	return this.providerClassName;
}

function getIsAllUnitsDict(){
	return this.isAllUnitsDict;
}

function getIsDeptList(){
	return this.isDeptList;
}

function getIsSelected(){
	return this.isSelected;
}

function getIsBacthRec(){
	return this.isBacthRec;
}

function getIsFee(){
	return this.isFee;
}

function getIsGray(){
	return this.isGray;
}

function getIsTanchu(){
	return this.isTanchu;
}

var LicDayBeforeExp= "7";//至授权日期7天前，开始提醒
function getLicDayBeforeExp(){
	return LicDayBeforeExp;
}

