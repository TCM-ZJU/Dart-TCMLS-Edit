package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;
import cn.edu.zju.ccnt.dartSS.db.dao.LczzDAO;
import cn.edu.zju.ccnt.dartSS.object.Lczz;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/** 疾病的症状
 * @author zhm
 * 
 */
public class QueryResultHtmlZhengzhuang extends QueryResultHtmlFactory {
	@Override
	public String generateQueryResult() {

		// get the common header of the query result page
		String htmlStr = this.getPageHeader();
		htmlStr += ShowZZInfo();
		return htmlStr;
	}

	private String ShowZZInfo() {
		String htmlStr = "";
		ArrayList<Lczz> lczzList = generateLczzList();

		htmlStr += "";
		this.setItemsInpage(20);
		this.setTotalRecordNum(lczzList.size());
		htmlStr += this.SetPageCounterHtml();
		htmlStr += "<table width=\"100%\" border=0 >"
			+ "<tr >"
			+ "<td>"
			+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
	htmlStr += "<tr class=\"tablecaption\"> " + "<td width=\"40\">序号</td>"
				+ "<td width=\"60\">症状</td>" + "<td width=\"80\">使用次数</td>"
				+ "<td width=\"80\">相关疾病</td>"
				+ "<td width=\"100\">相关临床研究</td>"
				+ "<td width=\"100\">相关证候</td>" + "</tr>";
		htmlStr += ShowResultWithPageNo(lczzList, this.getCurrentPage());

		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	public String ShowResultWithPageNo(ArrayList<Lczz> lczzList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (lczzList.size() < (pageNum + 1) * itemsInpage) ? lczzList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String zzmc = lczzList.get(counter).getZZMC().toString();
			   String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>" + "<td>" + zzmc + "</td>" + "<td>"
					+ lczzList.get(counter).getRefNum().toString() + "</td>"
					+ "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"症状相关疾病\"," + "\""
					+ zzmc + "\",\"0\")>" + "查看" + "</span></td>"
					+ "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"症状相关临床研究\"," + "\""
					+ zzmc + "\",\"0\")>" + "查看" + "</span></td>"
					+ "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"症状相关证候\"," + "\""
					+ zzmc + 
					"\",\"0\")>" + "查看" + "</span></td>" + "</tr>";
		}
		return htmlStr;
	}

	private ArrayList<Lczz> generateLczzList() {
		this.setTablePrefix();
		LczzDAO lczzDAO = new LczzDAO();
		lczzDAO.setTablePrefix(this.tablePrefix);
		lczzDAO.setJBMC(this.subjectTitle);
		ArrayList<Lczz> lczzList = lczzDAO.GetLczzByJbmc();
		return lczzList;
	}

}
