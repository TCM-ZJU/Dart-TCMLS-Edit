// clear all childnodes of a parentNode identified by nodeID
function clearChildNodes(nodeid) {
	var mainpartRootNode = document.getElementById(nodeid);
	for (var i = 0; i < mainpartRootNode.childNodes.length; i++) {
		mainpartRootNode.removeChild(mainpartRootNode.childNodes[i]);
	}
}

// append an childnode to a parentnode
function appendNodes(parentNode, childNode) {
	var pn = document.getElementById(parentNode);
	pn.appendChild(childNode);
	return;
}

// refresh the mainpart with new content
function refillMainPart(newContent) {
	clearChildNodes("mainpart");
	appendNodes("mainpart", newContent);
	return;
}

// ������ʾר�������Ϣshow the introduction page of a subject
function subjectIntroduction(subjectName) {
	SubjectIntroduction.getSubjectIntro(subjectName, SetSubjectIntroInnerHTML);
	return;
}
// �ص���
function SetSubjectIntroInnerHTML(data) {
	var newContent = document.createElement("SubjectIntroduction");
	newContent.innerHTML = data;
	refillMainPart(newContent);
}
/*
 * show the queryNavigation page of a subject subjectName: name of a subject
 * subjectTitle: a defined title of the subject ���ڲ�ѯ����
 */
function queryNavigation(subjectName, subjectTitle) {
	var newContent = document.createElement("processBar");
	newContent.innerHTML = "<div style=\"margin-top:200px; margin-left:200px\">\u6b63\u5728\u8bfb\u53d6\u75be\u75c5\u57fa\u672c\u4fe1\u606f<br/><img src=\"./public/images/progress.gif\"></img></div>";
	refillMainPart(newContent);
	QueryResult.getQueryResult(subjectName, subjectTitle, "\u57fa\u672c\u4fe1\u606f", "0", SetNavigatePageInnerHTML);
	return;
}
// �ص���
function SetNavigatePageInnerHTML(data) {
	var newContent = document.createElement("queryNavigation");
	newContent.innerHTML = data;
	refillMainPart(newContent);
}
// Զ�̵��ã����ڹ�j����
function GetNavigateQuery(subjectName, subjectTitle, queryType, KeyWord, PageNo) {
	var newContent = document.createElement("processBar");
	if(subjectName!="\u6025\u95e8\u8bca"){
		newContent.innerHTML = "<div style=\"margin-top:200px; margin-left:200px\">\u6b63\u5728\u8bfb\u53d6\u6570\u636e<br/><img src=\"./public/images/progress.gif\"></img></div>";
	}
	else
	{
		newContent.innerHTML ="";
	}refillMainPart(newContent);
	QueryResult.NavigateQuery(subjectName, subjectTitle, queryType, KeyWord, PageNo, SetQueryResultInnerHTML);
	return;
}
// Զ�̵��ã���ѯ��Լ����ĸ����ѯ
function GetQueryResult(subjectName, subjectTitle, queryType, currenPage) {
    var newContent = document.createElement("processBar");
	if(subjectName!="\u6025\u95e8\u8bca"){
		newContent.innerHTML = "<div style=\"margin-top:200px; margin-left:200px\">\u6b63\u5728\u8bfb\u53d6\u6570\u636e<br/><img src=\"./public/images/progress.gif\"></img></div>";
	}
	else
	{
		newContent.innerHTML ="";
	}
	refillMainPart(newContent);
	QueryResult.getQueryResult(subjectName, subjectTitle, queryType, currenPage, SetQueryResultInnerHTML);
	return;
}
// �ص���
function SetQueryResultInnerHTML(data) {
	var newContent = document.createElement("queryResult");
	newContent.innerHTML = data;
	refillMainPart(newContent);
}


// ר�����ĵ���
function subjectManagement(manType) {
	if (manType == "\u7528\u6237\u767b\u5f55") {
		SubjectManagement.GetPageHtmlByType(manType, setSubManMainPage);
	} else {
		SubjectManagement.GetPageHtmlByType(manType, setSubPageDownPart);
	}
	return;
}
function setSubManMainPage(data) {
	var newContent = document.createElement("DssSubjectManagement");
	newContent.innerHTML = data;
	refillMainPart(newContent);
}
function setSubPageDownPart(data) {
	var mytextBox = document.getElementById("hiddeProcessBar");
	if (mytextBox !== null) {
		mytextBox.innerHTML = "";
	}
	mytextBox = document.getElementById("subjectManBody");
	mytextBox.innerHTML = data;
}

// Զ�̵��ã��û���¼
function userLoggon(userName, passWord) {
	if (userName == "" || passWord == "") {
		alert("\"\u7528\u6237\u540d\"\uff0c\"\u5bc6\u7801\" \u90fd\u4e0d\u80fd\u4e3a\u7a7a\u3002");
		return;
	}
	SubjectManagement.userLoggon(userName, passWord, setSubManMainPage);
}
// Զ�̵��ã���������
function subManAddZtc(zhuangtiName, zhuticiName) {
	if (zhuangtiName == "" || zhuticiName == "") {
		alert("\"\u4e13\u9898\u540d\u79f0\"\uff0c\"\u4e3b\u9898\u8bcd\" \u90fd\u4e0d\u80fd\u4e3a\u7a7a\u3002");
		return;
	}
	SubjectManagement.AddZhutici(zhuangtiName, zhuticiName, setSubPageDownPart);
}

// Զ�̵��ã�����һ���µ�ר��
function subManAdd(subjectName, subjectTag, subjectCondition, subjectIntroPic, subjectNotes, subjectAddedZTC) {
	if (subjectName == "" || subjectTag == "" || subjectCondition == "") {
		alert("\"\u4e13\u9898\u540d\u79f0\"\uff0c\"\u4e13\u9898\u7f16\u7801\"\uff0c\"\u4e3b\u9898\u8bcd\u4ea7\u751f\u6761\u4ef6\"\u90fd\u4e0d\u80fd\u4e3a\u7a7a\u3002");
		return;
	} else {
		var mytextBox = document.getElementById("hiddeProcessBar");
		mytextBox.innerHTML = "<div style=\"margin-top:20px; margin-left:0px\">\u6b63\u5728\u521b\u5efa\u4e13\u9898,\u8fd9\u4e2a\u8fc7\u7a0b\u53ef\u80fd\u9700\u8981\u51e0\u5206\u949f\uff0c\u8bf7\u7a0d\u7b49<br/><img src=\"./public/images/progress.gif\"></img></div>";
		SubjectManagement.AddNewSuject(subjectName, subjectTag, subjectCondition, subjectIntroPic, subjectNotes, subjectAddedZTC, IsSubjectAdded);
	}
	return;
}
function IsSubjectAdded(data) {
	var mytextBox = document.getElementById("hiddeProcessBar");
	mytextBox.innerHTML = "";
	alert(data);
	return;
}
// Զ�̵��� ɾ��һ��ר��
function subManDelete(subjectName) {
	if (subjectName == "") {
		alert("\"\u4e13\u9898\u540d\u79f0\"\u4e0d\u80fd\u4e3a\u7a7a\u3002");
		return;
	}
	var mytextBox = document.getElementById("hiddeProcessBar");
	mytextBox.innerHTML = "<div style=\"margin-top:20px; margin-left:0px\">\u6b63\u5728\u5220\u9664\u4e13\u9898,\u8bf7\u7a0d\u7b49<br/><img src=\"./public/images/progress.gif\"></img></div>";
	SubjectManagement.DeleteSubject(subjectName, setSubPageDownPart);
}
// Զ�̵��� �ؽ�һ��ר��
function subManRecreate(subjectName) {
	if (subjectName == "") {
		alert("\"\u4e13\u9898\u540d\u79f0\"\u4e0d\u80fd\u4e3a\u7a7a\u3002");
		return;
	}
	var mytextBox = document.getElementById("hiddeProcessBar");
	mytextBox.innerHTML = "<div style=\"margin-top:20px; margin-left:0px\">\u6b63\u5728\u91cd\u5efa\u4e13\u9898,\u8bf7\u7a0d\u7b49<br/><img src=\"./public/images/progress.gif\"></img></div>";
	SubjectManagement.RecreateSubject(subjectName, setSubPageDownPart);
}

