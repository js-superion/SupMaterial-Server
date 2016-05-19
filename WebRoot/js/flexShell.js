var win
var _param

function setTitle(title) {
	document.title = title
}
function showWebDoc(param) {
	if (!param.FileType) {
		param.FileType = '.doc'
	}
	if (!param.EditType) {
		param.EditType = '1'
	}
	if (!param.ExtParam) {
		param.ExtParam = ''
	}
	if (!param.isPrint) {
		param.isPrint = '0'
	}
	_param = param
	// window.location.href=
	var urlparam = encodeParamToUrl(param)
	var url = "jsp/WebOfficeLoader.jsp?" + urlparam
	document.getElementById('iframe_iframe').contentWindow.location.href = url
}
function printWordReport(param) {
	if (!param.FileType) {
		param.FileType = '.doc'
	}
	if (!param.EditType) {
		param.EditType = '1'
	}
	if (!param.ExtParam) {
		param.ExtParam = ''
	}
	if (!param.isPrint) {
		param.isPrint = '0'
	}
	_param = param
	var urlparam = encodeParamToUrl(param)
	var url = "jsp/WebOfficeLoader.jsp?" + urlparam

	win = window
			.open(
					url,
					null,
					"height=2, width=10, toolbar= no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=0,left=0")

	// window.setTimeout('win.preview()',3000)

	// document.getElementById('webPrintIframe').contentWindow.webprint()
}
function printReport(param) {
	if (!param.FileType) {
		param.FileType = '.xls'
	}
	if (!param.EditType) {
		param.EditType = '1'
	}
	if (!param.ExtParam) {
		param.ExtParam = ''
	}
	if (!param.isPrint) {
		param.isPrint = '0'
	}
	_param = param
	var urlparam = encodeParamToUrl(param)
	var url = "jsp/WebOfficeLoader.jsp?" + urlparam
	// alert(url)
	win = window
			.open(
					url,
					null,
					"height=2, width=10, toolbar= no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=0,left=0")

	// window.setTimeout('win.preview()',3000)

	// document.getElementById('webPrintIframe').contentWindow.webprint()
}
function encodeParamToUrl(obj) {
	var res = ''
	for ( var field in obj) {
		res += field + '=' + obj[field] + '&'
	}
	return res
}
function fullScreen() {
	window.moveTo(0, 0)
	window.resizeTo(window.screen.width, window.screen.height)
}
function save() {
	alert(document.getElementById('iframe_iframe').contentWindow.save)
	document.getElementById('iframe_iframe').contentWindow.save()
}
function saveFile(param) {
	document.getElementById('iframe_iframe').contentWindow.saveFile(param)
}
function saveAsPersonTemplate(topic, template_code) {
	document.getElementById('iframe_iframe').contentWindow
			.saveAsPersonTemplate(topic, template_code)
}
function webprint() {
	document.getElementById('iframe_iframe').contentWindow.webprint()
}
function fullSize() {
	document.getElementById('iframe_iframe').contentWindow.fullSize()
}
function showRevision(mValue) {
	document.getElementById('iframe_iframe').contentWindow.showRevision(mValue)
}
function clearRevisions() {
	document.getElementById('iframe_iframe').contentWindow.clearRevisions()
}
function closeIE() {
	window.close()
}
function setFlexFocus() {
	var flashs;
	if (document.all && document.uniqueID)// IE Browser
		flashs = document.getElementsByTagName("object");
	else
		// Other e.g FF
		flashs = document.getElementsByTagName("embed");
	//
	flashs = flashs || []
	if (flashs[0]) {
		flashs[0].focus()
	}
}