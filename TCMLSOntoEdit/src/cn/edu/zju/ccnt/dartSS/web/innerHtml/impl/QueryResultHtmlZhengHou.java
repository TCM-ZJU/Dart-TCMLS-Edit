package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;
import cn.edu.zju.ccnt.dartSS.db.dao.ZhDAO;
import cn.edu.zju.ccnt.dartSS.object.Zh;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**
 * 疾病的证候
 * 
 * @author zhm
 * 
 */
public class QueryResultHtmlZhengHou extends QueryResultHtmlFactory {
	@Override
	public String generateQueryResult() {

		// get the common header of the query result page
		String htmlStr = this.getPageHeader();
		htmlStr += ShowZhInfo();
		return htmlStr;
	}

	private String ShowZhInfo() {
		String htmlStr = "";
		ArrayList<Zh> zhList = generateZhList();

		htmlStr += "<span><img src=\"./public/images/menu_create.gif\"><b>"
				+ this.subjectTitle + "</b></img>相关<b>证候</b>列表<span>";
		this.setItemsInpage(20);
		this.setTotalRecordNum(zhList.size());
		htmlStr += this.SetPageCounterHtml();
		htmlStr += "<table width=\"100%\" border=0 >"
				+ "<tr >"
				+ "<td>"
				+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
		htmlStr += "<tr class=\"tablecaption\"> " + "<td width=\"40\">序号</td>"
				+ "<td width=\"60\">证候</td>" + "<td width=\"80\">使用次数</td>"
				+ "<td width=\"100\">证候相关疾病</td>"
				+ "<td width=\"100\">相关临床研究</td>"
				+ "<td width=\"100\">相关症状</td>" + "</tr>";

		htmlStr += ShowResultWithPageNo(zhList, this.getCurrentPage());

		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	public String ShowResultWithPageNo(ArrayList<Zh> zhList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (zhList.size() < (pageNum + 1) * itemsInpage) ? zhList
				.size() : (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String zhmc = zhList.get(counter).getZHMC().toString();
			String tableClass = (counter % 2 == 1) ? "tablerow1" : "tablerow2";
			htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>"
					+ (counter + 1) + "</td>" + "<td>" + zhmc + "</td>"
					+ "<td>" + zhList.get(counter).getRefNum().toString()
					+ "</td>" + "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"证候相关疾病\"," + "\""
					+ zhmc + "\",\"0\")>" + "查看" + "</span></td>"
					+ "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"证候相关临床研究\"," + "\""
					+ zhmc + "\",\"0\")>" + "查看" + "</span></td>"
					+ "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"证候相关症状\"," + "\""
					+ zhmc +
					"\",\"0\")>" + "查看" + "</span></td>" + "</tr>";
		}
		return htmlStr;
	}

	private ArrayList<Zh> generateZhList() {
		this.setTablePrefix();
		ZhDAO zhDAO = new ZhDAO();
		zhDAO.setTablePrefix(this.tablePrefix);
		zhDAO.setJBMC(this.subjectTitle);
		ArrayList<Zh> zhList = zhDAO.GetZhByJbmc();
		return zhList;
	}
}
