package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**查询疾病的相关文献
 * @author zhm
 * 
 */
public class QueryResultHtmlRelatedDoc extends QueryResultHtmlFactory {
	@Override
	public String generateQueryResult() {

		// get the common header of the query result page
		String htmlStr = this.getPageHeader();
		ArrayList<String> relatedDocsList = generateRelatedDocs();

		htmlStr += "<span><img src=\"./public/images/menu_create.gif\"></img>"
				+ "<b>" + this.subjectTitle + "</b>相关<b>文献</b>列表<span>";

		htmlStr += "<table width=\"100%\" border=0 >"
				+ "<tr >"
				+ "<td>"
				+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
		htmlStr += "<tr class=\"tablecaption\"> "
			+ "<td width=\"40\">序号</td>"
				+ "<td width=\"100\">中文题名</td>" 
				+ "<td width=\"100\">个人著者</td>" 
				+ "<td width=\"60\">年份</td>" 
				+ "<td width=\"60\">刊名</td>" 
				+ "<td width=\"80\">主题词</td>" 
				+ "<td width=\"100\">查看全文</td>"
				+ "</tr>";
		
		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	private ArrayList<String> generateRelatedDocs() {
		ArrayList<String> docList = new ArrayList<String>();
		docList.add("1");
		docList.add("2");
		docList.add("3");
		return null;
	}
}
