package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;
import cn.edu.zju.ccnt.dartSS.db.dao.LcyjDAO;

/**查询疾病的临床研究详细信息
 * @author zhm
 * 
 */
public class QueryResultHtmlClinicResearch extends QueryResultHtmlFactory {

	@Override
	public String generateQueryResult() {

		// get the common header of the query result page
		String htmlStr = this.getPageHeader();
		ArrayList<String> lcyjmcList = generateLcyjmcList();

		htmlStr += "";
		this.setItemsInpage(20);
		this.setTotalRecordNum(lcyjmcList.size());
		htmlStr += this.SetPageCounterHtml();
		htmlStr += "<table width=\"100%\" border=0 >"
			+ "<tr >"
			+ "<td>"
			+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
	htmlStr += "<tr class=\"tablecaption\"> "
		        + "<td width=\"40\">序号</td>"
				+ "<td width=\"100\">疾病名称</td>"
				+ "<td width=\"200\">相关的临床研究</td>" + "</tr>";

		htmlStr += ShowResultWithPageNo(lcyjmcList, this.getCurrentPage());

		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	/**
	 * @param lcyjmcList
	 * @param pageNum
	 *            当前页数
	 * @return
	 */
	public String ShowResultWithPageNo(ArrayList<String> lcyjmcList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (lcyjmcList.size() < (pageNum + 1) * itemsInpage) ? lcyjmcList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String lcyjmc = lcyjmcList.get(counter).toString();
			  String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>" + "<td>" + this.subjectTitle + "<td>"
					+ "<span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"临床研究详细信息\"," + "\""
					+ lcyjmc + "\",\"0\")>" + lcyjmc + "</span></td>" + "</tr>";
		}
		return htmlStr;
	}

	private ArrayList<String> generateLcyjmcList() {
		this.setTablePrefix();
		LcyjDAO lcyjDAO = new LcyjDAO();
		lcyjDAO.setTablePrefix(this.tablePrefix);
		lcyjDAO.setJBMC(this.subjectTitle);
		ArrayList<String> lcyjmcList = lcyjDAO.GetLcyjmcByJbmc();
		return lcyjmcList;
	}

}
