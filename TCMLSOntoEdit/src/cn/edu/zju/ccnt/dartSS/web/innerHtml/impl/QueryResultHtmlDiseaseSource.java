package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.LcyjDAO;
import cn.edu.zju.ccnt.dartSS.object.By;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**查询疾病的病因
 * @author zhm
 * 
 */
public class QueryResultHtmlDiseaseSource extends QueryResultHtmlFactory {
	@Override
	public String generateQueryResult() {

		// get the common header of the query result page
		String htmlStr = this.getPageHeader();
		ArrayList<By> byList = generateByList();

		htmlStr += "<span><img src=\"./public/images/menu_create.gif\"></img><b>"
				+ subjectTitle + "</b>相关的<b>病因</b>列表<span>";
		this.setItemsInpage(20);
		this.setTotalRecordNum(byList.size());
		htmlStr += this.SetPageCounterHtml();


		htmlStr += "<table width=\"100%\" border=0 >"
				+ "<tr >"
				+ "<td>"
				+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
		htmlStr += "<tr class=\"tablecaption\"> " + "<td width=\"40\">序号</td>"
				+ "<td width=\"60\">病因</td>" + "<td width=\"80\">年龄组</td>"
				+ "<td width=\"120\">相关临床研究</td>" + "<td width=\"100\">结果</td>"
				+ "</tr>";
		htmlStr += ShowResultWithPageNo(byList, this.getCurrentPage());

		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	public String ShowResultWithPageNo(ArrayList<By> byList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (byList.size() < (pageNum + 1) * itemsInpage) ? byList
				.size() : (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String by = byList.get(counter).getJ_BY();
			String lcyj = byList.get(counter).getLCYJMC();
			String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
			htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>" 
					+ "<td>" 
					+"<span class=\"navigateLink\" onclick=GetQueryResult(\""
					+ subjectName + "\",\"" + by
					+ "\",\"基本信息\",\"0\")>" + by + "</span>" 
					+"</td>" 
					+ "<td>"
					+ byList.get(counter).getNLZ() 
					+ "</td>" 
					+ "<td>"
					+ "<span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"临床研究详细信息\"," + "\""
					+ lcyj + "\",\"0\")>" + lcyj + "</span>" 
					+"</td>" 
					+ "<td>"
					+ byList.get(counter).getJG() 
					+ "</td>" 
					+ "</tr>";
		}
		return htmlStr;
	}

	private ArrayList<By> generateByList() {
		this.setTablePrefix();
		LcyjDAO lcyjDAO = new LcyjDAO();
		lcyjDAO.setTablePrefix(this.tablePrefix);
		lcyjDAO.setJBMC(this.subjectTitle);
		ArrayList<By> byList = lcyjDAO.GetByByJbmc();
		return byList;
	}
}
