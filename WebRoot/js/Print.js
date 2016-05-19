var LODOP; // 声明为全局变量a
var contextPath = "SupMaterial";
var hst = window.location.host;
function PreviewOrExp(map) {
	window.setTimeout(function() {
		PreviewOrExpInit(map);
	}, 1000);
}

function PrintLabelInit(selectedItems) {
	window.setTimeout(function() {
		PrintLabel(selectedItems);
	}, 1000);
}


function PrintDeptGroupInit(map,printFlag) {
	window.setTimeout(function() {
		PrintDeptGroup(map,printFlag);
	}, 1000);
}
function PrintDeptGroup(map,printFlag){
	LODOP = getLodop(document.getElementById('LODOP_OB'), document
			.getElementById('LODOP_EM'));
			LODOP.PRINT_INITA(0,0,794,525,"打印科室汇总");
		LODOP.SET_PRINT_PAGESIZE(1,2100,1390,"");
		
	var totalPage = map.length;
	for (i = 0; i < map.length; i++) {
	
		
		LODOP.NewPage();
		//表头信息
		var providerName = map[i].providerName;//供货单位
		var deptName = map[i].deptName;//申领部门
		var billNo = map[i].billNo; //单据号
		var currentPage = i +1;
		var storageName = map[i].storageName;//仓库
		
		//标题
		LODOP.ADD_PRINT_TEXT(4,136,275,30,"后勤物资发放明细（科室）");
		LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		//表头
		LODOP.ADD_PRINT_TEXT(40,9,288,20,"供货单位："+providerName);
		LODOP.ADD_PRINT_TEXT(40,300,130,20,"领用部门："+deptName);
		LODOP.ADD_PRINT_TEXT(40,600,140,20,"单号："+billNo);//杨涛改过，往右边移了，要不部门显示不全
		LODOP.ADD_PRINT_TEXT(59,10,150,20,"仓　　库："+storageName);
		LODOP.ADD_PRINT_TEXT(59,300,71,20,"记 账 人：");
		LODOP.ADD_PRINT_TEXT(59,600,160,20,"页码："+ "第"+currentPage+"页 / 共"+totalPage+"页");//杨涛改过，往右边移了

		//列头
		LODOP.ADD_PRINT_TEXT(86,18,34,20,"序号");
		LODOP.ADD_PRINT_TEXT(86,60,74,20,"物资编码");
		LODOP.ADD_PRINT_TEXT(86,153,120,20,"物资名称");
		LODOP.ADD_PRINT_TEXT(86,366,85,20,"规格型号");
		LODOP.ADD_PRINT_TEXT(86,472,50,20,"数量");
		LODOP.ADD_PRINT_TEXT(86,537,46,20,"单价");
		LODOP.ADD_PRINT_TEXT(86,613,53,20,"金额");
		LODOP.ADD_PRINT_TEXT(86,697,56,20,"备注");
		//
		LODOP.ADD_PRINT_LINE(81,14,80,774,0,1);
		LODOP.ADD_PRINT_LINE(81,602,109,603,0,1);
		LODOP.ADD_PRINT_LINE(81,534,109,535,0,1);
		
		LODOP.ADD_PRINT_LINE(109,14,80,15,0,1);
		LODOP.ADD_PRINT_LINE(109,351,81,352,0,1);
		LODOP.ADD_PRINT_LINE(109,140,81,141,0,1);
		LODOP.ADD_PRINT_LINE(109,466,81,467,0,1);
		LODOP.ADD_PRINT_LINE(109,51,80,52,0,1);
		LODOP.ADD_PRINT_LINE(109,691,81,692,0,1);
		LODOP.ADD_PRINT_LINE(109,775,80,776,0,1);

		LODOP.ADD_PRINT_LINE(110,14,109,776,0,1);
//		
		//--行内容
		var details = map[i].detail;

		var detailRows = details.length;

		var detailHeight = 116;
		for (j = 0; j < detailRows; j++) { //表格数据
		
			LODOP.ADD_PRINT_TEXT(116+30*j,18,27,20,j+1);
			LODOP.ADD_PRINT_TEXT(116+30*j,56,81,20,details[j].materialCode);//物资编码
			LODOP.ADD_PRINT_TEXT(116+30*j,143,202,20,details[j].materialName);//品名
			LODOP.ADD_PRINT_TEXT(116+30*j,355,101,20,details[j].materialSpec);//规格
			LODOP.ADD_PRINT_TEXT(116+30*j,474,59,20,details[j].sendAmount);//数量
			LODOP.ADD_PRINT_TEXT(116+30*j,536,64,20,details[j].tradePrice); //单价
			LODOP.ADD_PRINT_TEXT(116+30*j,605,84,20,details[j].tradeMoney);//金额
			LODOP.ADD_PRINT_TEXT(116+30*j,693,70,20,details[j].remark);//备注
			
		
			
			//--格子画线	
			LODOP.ADD_PRINT_LINE(139+30*j,14,109+30*j,15,0,1);
			LODOP.ADD_PRINT_LINE(139+30*j,51,109+30*j,52,0,1);
			LODOP.ADD_PRINT_LINE(139+30*j,351,109+30*j,352,0,1);
			LODOP.ADD_PRINT_LINE(139+30*j,534,109+30*j,535,0,1);
			LODOP.ADD_PRINT_LINE(140+30*j,14,139+30*j,776,0,1);
			LODOP.ADD_PRINT_LINE(139+30*j,691,109+30*j,692,0,1);
			LODOP.ADD_PRINT_LINE(139+30*j,602,109+30*j,603,0,1);
			LODOP.ADD_PRINT_LINE(139+30*j,466,109+30*j,467,0,1);
			LODOP.ADD_PRINT_LINE(139+30*j,140,109+30*j,141,0,1);			
			
			detailHeight += 30;
			
		}	 
		
		LODOP.ADD_PRINT_TEXT(detailHeight,30,100,30,"发货人：");
		LODOP.ADD_PRINT_TEXT(detailHeight,300,80,30,"收货人：");//杨涛改过，往右边移了
		LODOP.ADD_PRINT_TEXT(detailHeight,600,80,30,"制单人：");//杨涛改过，往右边移了
	
	}
	if(printFlag == '2'){
		LODOP.PRINTA();
	}
//	if(printFlag == '3'){
//		LODOP.SAVE_TO_FILE("d:\\2.xls"); 
//	}
		
	
}

function PreviewOrExpInit(map) {
	LODOP = getLodop(document.getElementById('LODOP_OB'), document
			.getElementById('LODOP_EM'));
	var printFlag = map.printFlag;
	var jspName = map.jspName;
	//设置纸张大小
	var intOrient = map.intOrient;
	var pageWidth = map.pageWidth;
	var pageHeight = map.pageHeight;
	var pageName = map.pageName;
	
	LODOP.PRINT_INIT(map.title);
	LODOP.SET_PRINT_PAGESIZE(intOrient,pageWidth,pageHeight,pageName);
	
	//设置打印项的大小位置
	var width = map.width;
	var height = map.height;
	var top = map.top;
	var left = map.left;
//	alert(intOrient + pageWidth + pageHeight + pageName);
//	alert(top + left + width + height);
	LODOP.ADD_PRINT_URL(top, left,width, height,
			"report/"+jspName+"")
	// LODOP.PRINT_DESIGN();
	if (printFlag == '1') {
		LODOP.PREVIEW();
	}
	if (printFlag == '2') {
		LODOP.PRINTA();
	}
	if (printFlag == '3') {
		
		LODOP.ADD_PRINT_TABLE(top, left,width, height,"URL:http://"+hst+"/"+contextPath+"/report/"+jspName+"");
		LODOP.SAVE_TO_FILE(""+map.title+".xls"); 
	}
	if (printFlag == '4') {
		alert(window.location.host);
		loadRemoteHtml("http://"+hst+"/"+contextPath+"/report/"+jspName+"",map.title);
	}

}
/**
 * 打印标签
 * @param selectedItems，表格中选中的记录
 * @return
 */
function PrintLabel(selectedItems){
	LODOP = getLodop(document.getElementById('LODOP_OB'), document
			.getElementById('LODOP_EM'));
	LODOP.PRINT_INIT("打印标签");
	LODOP.SET_PRINT_PAGESIZE(0,100+'mm',60+'mm',"CreateCustomPage");	
	for (i = 0; i < selectedItems.length; i++) {
//		alert('1'+i);
//		alert('1'+selectedItems[0]);
//		alert('1'+selectedItems[i])
////		alert(selectedItems[i].equipmentCode);
//		alert(selectedItems[i].equipmentName);
//		alert(selectedItems[i].equipmentSpec);
//		alert(selectedItems[i].serialNumber);
//		alert(selectedItems[i].originalValue);
//		alert(selectedItems[i].manufacturerName);
//		alert(selectedItems[i].dateOfProductionName);
//		alert(selectedItems[i].verifyDateName);
//		alert(selectedItems[i].serviceLife);
//		alert(selectedItems[i].chargePersonName);
//		alert(selectedItems[i].positionCodeName);
//		alert(selectedItems[i].usedDeptName);
//		
	
		LODOP.NewPage();
		LODOP.ADD_PRINT_TEXT("4mm","5mm","20mm","4mm","设备编码：");
		LODOP.ADD_PRINT_BARCODE("4mm","28mm","55mm","11mm","128A",selectedItems[i].equipmentCode);
		LODOP.ADD_PRINT_TEXT("17mm","05mm","80mm","6mm","设备名称："+selectedItems[i].equipmentName);
		LODOP.SET_PRINT_STYLEA(0,"Bold",1);
		LODOP.ADD_PRINT_TEXT("23mm","05mm","70mm","6mm","型号："+selectedItems[i].equipmentSpec);
		LODOP.ADD_PRINT_TEXT("29mm","05mm","70mm","6mm","序列号："+selectedItems[i].serialNumber);
		LODOP.ADD_PRINT_TEXT("35mm","05mm","45mm","6mm","设备价值："+selectedItems[i].originalValue);
		LODOP.ADD_PRINT_TEXT("35mm","50mm","45mm","6mm","生产商："+selectedItems[i].manufacturerName);
		//
		LODOP.ADD_PRINT_TEXT("41mm","05mm","45mm","6mm","出厂日期："+selectedItems[i].dateOfProductionName);
		LODOP.ADD_PRINT_TEXT("41mm","50mm","45mm","6mm","购买日期："+selectedItems[i].dateOfPurchaseName);
		
		LODOP.ADD_PRINT_TEXT("47mm","05mm","45mm","6mm","使用年限："+selectedItems[i].serviceLife);
		LODOP.ADD_PRINT_TEXT("47mm","50mm","45mm","6mm","负责人："+selectedItems[i].chargePersonName);
		
		LODOP.ADD_PRINT_TEXT("53mm","05mm","45mm","6mm","存放位置："+selectedItems[i].positionCodeName);
		LODOP.ADD_PRINT_TEXT("53mm","50mm","45mm","6mm","使用科室："+selectedItems[i].usedDeptName);
	}
	LODOP.PREVIEW();
}
function loadRemoteHtml(fragment_url,reportName) {

	var xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	xmlhttp.open("POST", fragment_url);
	xmlhttp.onreadystatechange = function() { 
	
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			LODOP = getLodop();
			var filePath = LODOP.GET_DIALOG_VALUE("LocalFileFullName",
					""+reportName+".xls");
			var htmlResult = xmlhttp.responseText;
			
			var res = LODOP.WRITE_FILE_TEXT('UTF-8', filePath, htmlResult);
			if (res == "ok") {
				alert("导出成功！")
			} else {
				alert("失败！")
			}
		}
	}
	xmlhttp.send(null);
}

function getEditorObject() {
	// 根据id获取flash实例，在这里id是CallAsFromJs，可以从Embed
	var flash = document.getElementById("His");
	if (flash == null || flash == 'undefined') {
		flash = document.getElementById("His");
	}
	// 调用ActionScript注册的回调方法
	return flash;
}
