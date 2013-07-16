package cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl;

import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.SubjectManHtmlFactory;

/**专题管理的工具栏
 * @author zhm
 * 
 */
public class SubjectManHtmlHomePage extends SubjectManHtmlFactory{

	@Override
	public String setInnerHtml() {
		String htmlStr="<br/><Span><b>专题管理</b></span>" +
				"<hr/>" +
				generateNavigeteHtml("添加专题")+
				generateNavigeteHtml("上传专题图片")+								
				generateNavigeteHtml("删除专题")+
				generateNavigeteHtml("重新生成专题")+
				logout("退出")+
				"<hr/>";
		return htmlStr;
			  
		
	}

	public String generateNavigeteHtml(String navigateInfo) {

		String htmlStr = "<span class=\"navigateHint\" onclick=subjectManagement(\""
				+ navigateInfo
				+ "\")>"
				+ "<img src=\"./public/images/loading.gif\"></img>"
				+ navigateInfo+"</span>  ";
		return htmlStr;
	}
	
	public String logout(String navigateInfo) {

		String htmlStr = "<span class=\"navigateHint\" >"
				+ "<img src=\"./public/images/loading.gif\"></img><a href=index.htm>"
				+ navigateInfo+"</a></span>  ";
		return htmlStr;
	}
	
}
