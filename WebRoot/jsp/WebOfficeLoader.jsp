<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript">
	var param = opener._param
	var isPrint = param.isPrint
	var bean = param.bean
	var method = param.method
	var extParam = param.extParam
	var EditType = param.EditType
	var FileType = param.FileType
	
</script>
<SCRIPT LANGUAGE=javascript FOR=WebOffice EVENT=NotifyCtrlReady>
	WebOffice_NotifyCtrlReady()
	function WebOffice_NotifyCtrlReady() {
		document.all.WebOffice.SetWindowText("授权${user.unitsName}", 0);
		loadFile()
	}
	//-->
</SCRIPT>
		<script type="text/javascript" src='../js/djWebOffice.js'></script>
	</head>

	<body onload="init()">
		<form name="webform" method="post">
			<!--保存iWebOffice后提交表单信息-->
			<SCRIPT src="../js/LoadWebOffice.js"></SCRIPT>
		</form>
	</body>
</html>
