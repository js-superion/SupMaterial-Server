<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
    <%
       String lstrBean=request.getParameter("bean");
       String lstrUnitCode=request.getParameter("unitsCode");
       lstrUnitCode=new String(lstrUnitCode.getBytes("ISO8859-1"),"utf-8");   
       String lstrInpno=request.getParameter("id");
       String lstrSubjectType=request.getParameter("templetCode");
       String lstrEditType=request.getParameter("EditType");
       
       String lstrUserCode=request.getParameter("userCode");
       String lstrDeptCode=request.getParameter("deptCode");
       //MedicalDocDao dao=new MedicalDocDao();
       boolean isExit=false;//dao.isExistFile(lstrTemplateCode+".doc",lstrUnitCode,lstrInpno);
       lstrEditType=isExit?lstrEditType:"1";
    %>
    var bean='<%=lstrBean%>'
    var isDocExit=<%=isExit%>
    var user_code='<%=lstrUserCode%>'
    var dept_code='<%=lstrDeptCode%>'
    var units_code='<%=lstrUnitCode%>'
	var inp_no='<%=lstrInpno%>'
	var template_code='<%=lstrSubjectType%>'
	var EditType='<%=lstrEditType%>'
    </script>
    <script type="text/javascript" src='../js/webOfficeDoc.js'></script>
  </head>
  
  <body onload='createEditor()'>
      <form name="webform" method="post" >  <!--保存iWebOffice后提交表单信息-->
      <!-- 
    <div style="position: absolute; width: 900; height: 59px; z-index: 1; left: 12px; top: 18px; background-color: #FFFFFF" id="layer1">
    <iframe id='maskIframe' height="20"  scrolling="no" border="0" frameborder="0"
      src="mask.jsp"></iframe>
　  </div>   -->    
    <div id='wordEditor'></div>
    </form>
  </body>
</html>
