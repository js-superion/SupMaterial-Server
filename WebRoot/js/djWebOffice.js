var mServerUrl = '../servlet/WebOfficeServer.do'
function init(){
	reSizeWindow()
}
//上传文档
function SaveDoc() {
	var returnValue; // 保存页面的返回值
	document.all.WebOffice.HttpInit(); // 初始化Http 引擎
	// 添加相应的Post 元素
	//测试代码
	document.all.WebOffice.HttpAddPostString("username", "xxxxx");
	document.all.WebOffice.HttpAddPostCurrFile("AipFile ", "");
	returnValue = document.all.WebOffice.HttpPost(mServerUrl + "?flag=save");
	if ("success" == returnValue) {
		alert("文件上传成功");
	} else {
		alert("文件上传失败")
	}
}
//全屏
function fullScreen() {
	document.all.WebOffice.FullScreen = true;
}

/* 加载文档
 * isPrint=0  输出  isPrint=1 直接打印  isPrint=2 预览打印
 */
function loadFile() {
	var urlparam = encodeParamToUrl(param)
	document.all.WebOffice.LoadOriginalFile(mServerUrl + "?flag=load&"
					+ urlparam, param.FileType);	
	if (isPrint == '1') {
		window.moveTo(-1000, 0)
		window.resizeTo(0, 0)
		directPrint()
		return
	} 
	if (isPrint == '2') {
		window.moveTo(0, 0)
		window.resizeTo(screen.availWidth, screen.availHeight)
		setTimeout("printPreview()",100)
		return
	} 	
	if (isPrint == '0') {
		hideAll(false)
		document.all.WebOffice.ProtectDoc(1, 2, "suphis");
		return
	}
}
function reSizeWindow(){
	if (isPrint == '1') {
		window.moveTo(-1000, 0)
		window.resizeTo(0, 0)
		return
	} 
	if (isPrint == '2') {
		window.moveTo(0, 0)
		window.resizeTo(screen.availWidth, screen.availHeight)
		return
	} 	
	if (isPrint == '0') {
		window.resizeTo(screen.availWidth, screen.availHeight)
		window.moveTo(0, 0)
		return
	}	
}
/*
 * 隐藏全部菜单和工具栏
 * 参数：hideToolBar true 隐藏自带工具栏  
 */
function hideAll(hideToolBar){
	try{
		var webObj=document.getElementById("WebOffice");
		webObj.HideMenuArea("hideall", "","", "");
		if(hideToolBar){
		   webObj.ShowToolBar = 0;
		}
	}catch(e){
		alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
	}
}
/**
 * 显示打印对话框
 */
function showPrintDialog() {
	try {
		var webObj = document.getElementById("WebOffice");
		webObj.PrintDoc(1);
	} catch (e) {
		alert("\r\nError:" + e + "\r\nError Code:" + e.number
				+ "\r\nError Des:" + e.description);
	}
}
/**
 * 直接打印
 */
function directPrint() {
	try {
		var webObj = document.getElementById("WebOffice");
		webObj.PrintDoc(0);
		close()
	} catch (e) {
		alert("\r\nError:" + e + "\r\nError Code:" + e.number
				+ "\r\nError Des:" + e.description);
	}
}
/**
 * 打印预览
 */
function printPreview() {
	try {
		var webObj = document.getElementById("WebOffice");		
		var vObj = webObj.GetDocumentObject();
		webObj.ProtectDoc(1, 2, "suphis");
		webObj.SetSecurity(2+4+8); 
		if (!vObj) {
			alert("获取对象失败，请核实您已经打开文档");
			return false;
		}
		vObj.Application.ActiveWindow.SelectedSheets.PrintPreview
		hideAll(true);
	} catch (e) {
		alert("异常\r\nError:" + e + "\r\nError Code:" + e.number
				+ "\r\nError Des:" + e.description);
	}
}
/**
 * 将对象转换为url
 */
function encodeParamToUrl(obj) {
	var res = ''
	for (var field in obj) {
		res += field + '=' + obj[field] + '&'
	}
	return res
}