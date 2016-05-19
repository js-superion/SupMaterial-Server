var _docObject=null;
function loadInitDoc(){
	var of = document.getElementById('WebOffice')
	of.LoadOriginalFile('/plugin/setPrint.bin', 'doc');
	_docObject = of.GetDocumentObject();	
}
function setPrintPageSize(fwidth,fheight){
	_docObject.setCustomerPageSize(fwidth,fheight)
}
function setPrintPageToDefault(){
	_docObject.setDefaultPageSize("")
}