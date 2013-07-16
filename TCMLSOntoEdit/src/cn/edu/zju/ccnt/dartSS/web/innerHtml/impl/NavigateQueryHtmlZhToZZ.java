package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;
import cn.edu.zju.ccnt.dartSS.db.dao.LczzDAO;
import cn.edu.zju.ccnt.dartSS.object.Lczz;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**
 * 从证候查询相关症状
 * 
 * @author zhm
 * 
 */
public class NavigateQueryHtmlZhToZZ extends QueryResultHtmlFactory {

	@Override
	public String generateQueryResult() {

		String htmlStr = this.getPageHeader();
		ArrayList<Lczz> relatedZZList = generateZZList();

		htmlStr += "<span>与证候<b>"
				+ this.getKeyWord() + "</b>相关的<b>症状</b>列表<span>";

		this.setItemsInpage(20);
		this.setTotalRecordNum(relatedZZList.size());
		htmlStr += this.SetPageCounterHtmlWithKeyword();

		htmlStr += "<table width=\"100%\" border=0 >"
			+ "<tr >"
			+ "<td>"
			+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
	htmlStr += "<tr class=\"tablecaption\"> "
			+ "<td width=\"40\">序号</td>"
				+ "<td width=\"160\">相关症状名称</td> "
				+ "<td width=\"100\">关联次数</td> " + "<td width=\"100\">相关证候</td>"
				+ "</tr>";

		htmlStr += DrawCurrentPage(relatedZZList, this.getCurrentPage());
		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	private String DrawCurrentPage(ArrayList<Lczz> relatedZZList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (relatedZZList.size() < (pageNum + 1) * itemsInpage) ? relatedZZList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String zzmc = relatedZZList.get(counter).getZZMC();
			 String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>" + "<td>" + relatedZZList.get(counter).getZZMC()
					+ "</td>" + "<td>" + relatedZZList.get(counter).getRefNum()
					+ "</td>" + "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"症状相关证候\"," + "\""
					+ zzmc 
					+ "\",\"0\")>" + "查看" + "</span></td>" + "</tr>";
		}
		return htmlStr;

	}

	private ArrayList<Lczz> generateZZList() {
		LczzDAO lczzDAO = new LczzDAO();
		lczzDAO.setTablePrefix(this.getTablePrefix());
		ArrayList<Lczz> lczzList = lczzDAO.getZzmcByZhMC(this.getKeyWord());
		return lczzList;
	}

}
