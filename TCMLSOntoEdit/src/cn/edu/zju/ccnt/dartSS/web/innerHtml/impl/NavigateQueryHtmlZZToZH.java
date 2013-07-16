package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.ZhDAO;
import cn.edu.zju.ccnt.dartSS.object.Zh;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**
 * 从症状查询相关证候
 * 
 * @author zhm
 * 
 */
public class NavigateQueryHtmlZZToZH extends QueryResultHtmlFactory {

	@Override
	public String generateQueryResult() {

		String htmlStr = this.getPageHeader();
		ArrayList<Zh> relatedZhList = generateZhList();

		htmlStr += "<span>与症状<b>"
				+ this.getKeyWord() + "</b>相关的<b>证候</b>列表<span>";

		this.setItemsInpage(20);
		this.setTotalRecordNum(relatedZhList.size());
		htmlStr += this.SetPageCounterHtmlWithKeyword();

		htmlStr += "<table width=\"100%\" border=0 >"
			+ "<tr >"
			+ "<td>"
			+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
	htmlStr += "<tr class=\"tablecaption\"> "
		+ "<td width=\"40\">序号</td>"
				+ "<td width=\"120\">相关证候名称</td> "
				+ "<td width=\"100\">关联次数</td> " + "<td width=\"100\">相关症状</td>"
				+ "</tr>";

		htmlStr += DrawCurrentPage(relatedZhList, this.getCurrentPage());
		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	private String DrawCurrentPage(ArrayList<Zh> relatedZhList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (relatedZhList.size() < (pageNum + 1) * itemsInpage) ? relatedZhList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {

			String zhmc = relatedZhList.get(counter).getZHMC();
			 String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>" + "<td>" + zhmc + "</td>" + "<td>"
					+ relatedZhList.get(counter).getRefNum() + "</td>"
					+ "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"证候相关症状\"," + "\""
					+ zhmc 
					+ "\",\"0\")>" + "查看" + "</span></td>" + "</tr>";
		}
		return htmlStr;

	}

	private ArrayList<Zh> generateZhList() {
		ZhDAO zhDAO = new ZhDAO();
		zhDAO.setTablePrefix(this.getTablePrefix());
		ArrayList<Zh> zhList = zhDAO.getZzmcGetZhMC(this.getKeyWord());
		return zhList;
	}
}
