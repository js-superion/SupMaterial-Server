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
/**
td {
	text-align: center;
	line-height: 150%;
	word-break: break-all;
	word-wrap: break-word;
}
**/

.td_column_header {
	font-weight: bold;
	font-size: 13px
}
</style>
	</head>
 <body>
 <center><label class="td_column_header">${printData.title}</label></center>
<table border="1px" bordercolor="#000000" style="font-size: 12px;border-collapse: collapse;">
  <tr>
    <td width="50" class="td_column_header">设备编码</td>
    <td class="td_column_header">设备名称</td>
    <td class="td_column_header">设备类别（会计科目）</td>
    <td class="td_column_header">品牌</td>
    <td class="td_column_header">型号</td>
    <td class="td_column_header">设备价值</td>
    <td class="td_column_header">是否为计量设备</td>
    <td class="td_column_header">设备状态</td>
    <td class="td_column_header">使用科室</td>
    <td class="td_column_header">存放位置</td>
    <td class="td_column_header">负责人</td>
    <td class="td_column_header">序列号</td>
    <td class="td_column_header">功率</td>
    <td class="td_column_header">供货商</td>
    <td class="td_column_header">生产商</td>
    <td class="td_column_header">出厂日期</td>
    <td class="td_column_header">购买日期</td>
    <td class="td_column_header">验收日期</td>
    <td class="td_column_header">使用年限</td>
    <td class="td_column_header">保修截至日期</td>
    <td class="td_column_header">检测单位</td>
    <td class="td_column_header">检测日期</td>
    <td class="td_column_header">检测有效期</td>
    <td class="td_column_header">检测证书编号</td>
    <td class="td_column_header">检测结果</td>
    <td class="td_column_header">备注</td>
  </tr>
  <c:forEach items="${printData.dataProvider}" var="item">
  <tr>
    <td>${item.equipmentCode}</td>
    <td>${item.equipmentName}</td>
    <td>${item.eqClassName}</td>
    <td>${item.brandName}</td>
    <td>${item.equipmentSpec}</td>
    <td>${item.originalValue}</td>
    <td>${item.measureSign}</td>
     <td>${item.eqStatusName}</td>
     <td>${item.usedDeptName}</td>
    <td>${item.positionCodeName}</td>
    <td>${item.chargePersonName}</td>
    <td>${item.serialNumber}</td>
    <td>${item.motorPower}</td>
    <td>${item.supplierName}</td>
    <td>${item.manufacturerName}</td>
    <td>${item.dateOfProductionName}</td>
     <td>${item.dateOfPurchaseName}</td>
    <td>${item.verifyDateName}</td>
    <td>${item.serviceLife}</td>
    <td>${item.guaranteeCutOffDateName}</td>
    <td>${item.testUnit}</td>
    <td>${item.testDateName}</td>
    <td>${item.testValDateName}</td>
    <td>${item.testCertifyNo}</td>
     <td>${item.testRes}</td>
    <td>${item.remark}</td>
  </tr>
  </c:forEach>
</table>
</body>
</html>
