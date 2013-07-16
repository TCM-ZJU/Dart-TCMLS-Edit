package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.object.Lcyj;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;
import cn.edu.zju.ccnt.dartSS.db.dao.LcyjDAO;

/**查询疾病的临床研究详细信息
 * 根据研究单位信息
 * @author zhm
 * 
 */
public class QueryResultHtmlClinicOrderByQKRQ extends QueryResultHtmlFactory {

	@Override
	public String generateQueryResult() {

		// get the common header of the query result page
		String htmlStr = this.getPageHeader();
		ArrayList<Lcyj> lcyjList = generateLcyjmcList();

		htmlStr += "按出版日期检索临床研究期刊信息";
		this.setItemsInpage(20);
		this.setTotalRecordNum(lcyjList.size());
		htmlStr += this.SetPageCounterHtml();
		htmlStr += "<table width=\"100%\" border=0 >"
			+ "<tr >"
			+ "<td>"
			+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
	htmlStr += "<tr class=\"tablecaption\"> "
		 + "<td width=\"40\">序号</td>"
		        + "<td width=\"120\">期号</td>"
				+ "<td width=\"120\">刊物名称</td>"
				+ "<td width=\"300\">临床研究名称</td>" 
				+ "</tr>";

		htmlStr += ShowResultWithPageNo(lcyjList, this.getCurrentPage());

		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	/**
	 * @param lcyjmcList
	 * @param pageNum
	 *            当前页数
	 * @return
	 */
	public String ShowResultWithPageNo(ArrayList<Lcyj> lcyjDwList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (lcyjDwList.size() < (pageNum + 1) * itemsInpage) ? lcyjDwList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String lcyjmc = lcyjDwList.get(counter).getLCYJMC().toString();
			String QS=lcyjDwList.get(counter).getQS().toString();
			String QKMC=lcyjDwList.get(counter).getQKMC().toString();
			  String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>"
					+"<td>"+QS+"</td>"
					+"<td>《"+QKMC+"》</td>"
					+"<td>"
					+ "<span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"临床研究详细信息\"," + "\""
					+ lcyjmc + "\",\"0\")>" + lcyjmc + "</span></td></tr>";
		}
		return htmlStr;
	}

	private ArrayList<Lcyj> generateLcyjmcList() {
		this.setTablePrefix();
		LcyjDAO lcyjDAO = new LcyjDAO();
		lcyjDAO.setTablePrefix(this.tablePrefix);
		lcyjDAO.setJBMC(this.subjectTitle);
		ArrayList<Lcyj> lcyjmcList = lcyjDAO.GetQSByJbmc();
		return lcyjmcList;
	}

}
