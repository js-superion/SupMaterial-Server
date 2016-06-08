<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	Map<String,Object> map = (Map<String,Object>)session.getAttribute("printData");
	System.out.println(map);
	
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
	text-align: center;
	line-height: 150%;
	word-break: break-all;
	word-wrap: break-word;
	empty-cells:show;
}

.td_column_header_title {
	font-weight: bold;
	font-size: 30px
}

.td_column_header {
	font-weight: bold;
	font-size: 13px
}

.td_column_menu {
text-align: left;
	font-size: 13px
}

</style>
	</head>
 <body>
 <center><label class="td_column_header_title">${printData.title}</label></center>
<table border="1px" bordercolor="#000000" style="font-size: 12px;border-collapse: collapse;">
 <tr height="30">
    <td colspan="4" width="200" class="td_column_header">申领部门：${printData.deptName}</td>
    <td colspan="2" width="240" class="td_column_header">打印日期：${printData.printDate}</td>
  </tr>
  <tr height="30">
    <td width="130" class="td_column_header">物资名称</td>
    <td width="100" class="td_column_header">规格型号</td>
    <td width="50" class="td_column_header">数量</td>
    <td width="130" class="td_column_header">产地品牌</td>
    <td width="70" class="td_column_header">预计金额</td>
    <td width="230" class="td_column_header">明细备注</td>
  </tr>
  <c:forEach items="${printData.dataProvider}" var="item">
  <tr>
    <td class="td_column_menu">${item.materialName}</td>
    <td class="td_column_menu">${item.materialSpec}</td>
    <td class="td_column_menu">${item.sendAmount}</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td class="td_column_menu">${item.detailRemark}</td>
    <td></td>
  </tr>
  </c:forEach>
  	<tr height="60">
    <td colspan="1" width="130" class="td_column_header">申请原因:</td>
    <td colspan="5" class="td_column_menu">${printData.remark}</td>
  </tr>    
  	<tr height="60">
    <td colspan="1" width="130" class="td_column_header">采购部门分管院长</td>
    <td colspan="5">&nbsp;</td>
  </tr>
  <tr height="60">
    <td colspan="1" width="130" class="td_column_header">职能处室审批意见:</td>
    <td colspan="5" class="td_column_menu">${printData.remark1}</td>
  </tr>
    <tr height="60">
    <td colspan="1" width="130" class="td_column_header">申请部门分管院长</td>
    <td colspan="5">&nbsp;</td>
  </tr>
  	<tr height="30">
    <td colspan="2" width="80" class="td_column_header"> </td>
    <td colspan="3" width="180" class="td_column_header">申请人：${printData.applyPerson}</td>
    <td colspan="1" width="80" class="td_column_header">采购员：</td>
  </tr>
  
  
</table>
</body>
</html>
