<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	Map<String,Object> map = (Map<String,Object>)session.getAttribute("printData");
	System.out.println(map);
	Double totalCharges = Double.valueOf(map.get("totalCharges")==null?"0":map.get("totalCharges").toString()) ;
	
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'FundApply.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		 <script language="javascript" type="text/javascript" src="js/LodopFuncs.js"></script> 
		<script language="javascript">  
		 var LODOP;
		   function onLoad(){
		       //LODOP=getLodop();  
		       //var upper_charges = LODOP.FORMAT("UpperMoney",'<%=totalCharges%>');
		       //document.getElementById('td_total').innerText ="".concat(upper_charges); 
		       
		   }
		</script>
	<style type="text/css"><!--
* {
	margin: 0 auto;
	padding: 0;
}

body {
	width: 190mm;
	font-family: 宋体;
	font-size: 11px;
}
/**
table {
	table-layout: fixed;
}
**/

td {
	text-align: left;
	line-height: 150%;
	word-break: break-all;
	word-wrap: break-word;
	empty-cells:show;
}


.td_column_header {
	font-weight: bold;
	font-size: 13px
}
.td_right {
	font-weight: bold;
	font-size: 13px;
	text-align: right;
}
.td_left {
	font-weight: bold;
	font-size: 13px;
	text-align: left;
}
.td_middle {
	font-weight: bold;
	font-size: 13px;
	text-align: center;
}
</style>
	</head>
 <body onload="onLoad()">
 <center><label class="td_column_header">后勤供应物资汇总表</label> </center>
<table border="1px" bordercolor="#000000" style="font-size: 12px;border-collapse: collapse;">
 <tr height="30">
    <td colspan="5"  class="td_column_header">供货单位:${printData.providerName}</td>
    <td colspan="4"  class="td_column_header">单据编号:${printData.billNo}</td>
  </tr>
  <tr height="30">
    <td colspan="5"  class="td_column_header">仓库:${printData.storageName}</td>
    <td colspan="4"  class="td_left">记账人：</td>
  </tr>
  <tr height="30">
    <td width="70" class="td_column_header">序号</td>
     <td width="70" class="td_column_header">部门</td>
    <td width="70" class="td_column_header">物资编码</td>
    <td width="220" class="td_column_header">物资名称</td>
    <td width="80" class="td_column_header">规格型号</td>
    <td width="40" class="td_column_header">数量</td>
    <td width="50" class="td_column_header">价格</td>
    <td width="50" class="td_column_header">金额</td>
    <td width="50" class="td_column_header">备注</td>
  </tr>
  <c:forEach items="${printData.dataProvider1}" var="item" varStatus="status">
  <tr>
    <td>${ status.index + 1}</td>
    <td>${item.deptName}</td>
    <td>${item.materialCode}</td>
    <td>${item.materialName}</td>
    <td>${item.materialSpec}</td>
    <td>${item.sendAmount}</td>
    <td>${item.tradePrice}</td>
    <td>${item.tradeMoney}</td>
    <td>${item.detailRemark}</td>
  </tr>
  
  </c:forEach>
  
  <tr height="30">
    <td colspan="9" class="td_column_header">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;采购:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会计:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制单:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table>
</body>
</html>
