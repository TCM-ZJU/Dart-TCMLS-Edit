package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.LczlDAO;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;
import cn.edu.zju.ccnt.dartSS.object.Lczl;

/**查询疾病相关的临床诊疗
 * @author zhm 
 * 
 */
public class QueryResultHtmlClinicCure extends QueryResultHtmlFactory {
	@Override
	public String generateQueryResult() {

		String htmlStr = this.getPageHeader();
		ArrayList<Lczl> ClinicCureList = generateClinicCureList();

		htmlStr += "";
		this.setItemsInpage(20);
		this.setTotalRecordNum(ClinicCureList.size());
		htmlStr += this.SetPageCounterHtml();

		htmlStr += "<table width=\"100%\" border=0 >"
			+ "<tr >"
			+ "<td>"
			+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
	htmlStr += "<tr class=\"tablecaption\"> "
		+ "<td width=\"40\">序号</td>"
				+ "<td width=\"120\">中医诊断方法</td>"
				+ "<td width=\"120\">中医诊断仪器</td>" + "<td width=\"100\">总有效率</td>"
				+ "<td width=\"160\">相关的临床研究</td>" + "</tr>";

		htmlStr += setCurrentPage(ClinicCureList, this.currentPage);

		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	private String setCurrentPage(ArrayList<Lczl> ClinicCureList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (ClinicCureList.size() < (pageNum + 1) * itemsInpage) ? ClinicCureList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String lcyjmc = ClinicCureList.get(counter).getLCYJMC();
			String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
			htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>" + "<td>"
					+ ClinicCureList.get(counter).getZYZDFF() + "</td>"
					+ "<td>" + ClinicCureList.get(counter).getZDYQ() + "</td>"
					+ "<td>" + ClinicCureList.get(counter).getZYXL() + "</td>"
					+ "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"临床研究详细信息\"," + "\""
					+ lcyjmc + "\",\"0\")>" + lcyjmc + "</span></td>" + "</tr>";
		}
		return htmlStr;
	}

	private ArrayList<Lczl> generateClinicCureList() {
		this.setTablePrefix();
		LczlDAO lczlDAO = new LczlDAO();
		lczlDAO.setTablePrefix(this.tablePrefix);
		lczlDAO.setJBMC(this.subjectTitle);
		ArrayList<Lczl> lczlList = lczlDAO.GetLczlByJbmc();
		return lczlList;
	}
}
