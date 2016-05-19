var mServerUrl='../servlet/WebOfficeServer.do'
function createEditor()
{
	var webOffice=document.getElementById('WebOffice')
	if(webOffice!=null)return;
    var str = '';
    str += '<OBJECT id="WebOffice" width="100%" height="100%" classid="clsid:8B23EA28-2009-402F-92C4-59BE0E063499" codebase="iWebOffice2009.cab#version=10,0,0,8" >';
    str += '</object>';
	var editor=document.getElementById('wordEditor');
	editor.innerHTML=str;
	Load()
}
function fillBookMarks()
{
	if(isDocExit)return
	var webOffice=document.getElementById('WebOffice')
	webOffice.WebLoadBookmarks()
}
function webprint(){
  try{
  	var webOffice=document.getElementById('WebOffice')
    webOffice.WebOpenPrint();
  }catch(e){alert(e.description);}
}
function Load(){
  try{
  	var webOffice=document.getElementById('WebOffice')
    //以下属性必须设置，实始化iWebOffice
    webOffice.WebUrl=mServerUrl+"?bean="+bean;		//WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件 
    webOffice.Template=template_code;	//RecordID:本文档记录编号
    webOffice.ExtParam=units_code;	//Template:模板编号
    webOffice.UserName=inp_no;	//FileName:文档名称
    webOffice.FileType=".doc";	//FileType:文档类型  .doc  .xls  .wps
    webOffice.EditType=EditType;	//EditType:编辑类型  方式一、方式二  <参考技术文档>
//    webOffice.UserName="Administrator";	//UserName:操作用户名，痕迹保留需要
    webOffice.WebOpen();
    //if(inp_no!='') 
    fillBookMarks()
    //打开该文档    交互OfficeServer  调出文档OPTION="LOADFILE"    调出模板OPTION="LOADTEMPLATE"     <参考技术文档>
//    StatusMsg(webOffice.Status);			//状态信息
  }catch(e){
    alert(e.description);							//显示出错误信息
  }
}
function save()
{
  var editor=document.getElementById('wordEditor');
  var webOffice=document.getElementById('WebOffice');
  webOffice.ExtParam= units_code;
  webOffice.Template=template_code;
  webOffice.WebSave()   //FullSize
  alert('保存成功')
}
function fullSize()
{
  var webOffice=document.getElementById('WebOffice')
  var b=webOffice.FullSize()   //FullSize

}
function showRevision(mValue){
  var webOffice=document.getElementById('WebOffice')	
  if (mValue){
     webOffice.WebShow(true);
  }else{
     webOffice.WebShow(false);
  }
  refrashIframeMask()
}
function clearRevisions()
{
	var webOffice=document.getElementById('WebOffice')
	webOffice.WebObject.Application.ActiveDocument.AcceptAllRevisions();
	refrashIframeMask()
}