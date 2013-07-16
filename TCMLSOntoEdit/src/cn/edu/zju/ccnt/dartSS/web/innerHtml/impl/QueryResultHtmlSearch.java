package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.object.Disease;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;
import cn.edu.zju.ccnt.dartSS.db.dao.DiseaseDAO;
import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;

/**疾病关键词搜索
 * @author zhm
 *
 */
public class QueryResultHtmlSearch extends QueryResultHtmlFactory {

	@Override
	public String generateQueryResult() {

		// get the common header of the query result page
		String htmlStr = "";
		ArrayList<Disease> diseaseList = generateDiseaseList();

		htmlStr += "<span><img src=\"./public/images/menu_create.gif\"></img>与疾病名称<b>"
				+ this.subjectTitle + "</b>相关的搜索结果<span></br>";
		this.setItemsInpage(20);
		if(diseaseList!=null)
		this.setTotalRecordNum(diseaseList.size());
		else
			this.setTotalRecordNum(0);
		htmlStr += this.SetPageCounterHtml();

		htmlStr += "<table width=\"100%\" border=0 >"
				+ "<tr >"
				+ "<td>"
				+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
		htmlStr += "<tr class=\"tablecaption\"> "
			+ "<td width=\"40\">序号</td>"
				+ "<td width=\"80\">所属专题</td>" 
				+ "<td width=\"120\">疾病名称</td>"
				+ "<td width=\"100\">查看疾病信息</td>" + "</tr>";

		htmlStr += ShowResultWithPageNo(diseaseList, this.getCurrentPage());

		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	/**
	 * @param pageNum
	 *            当前页数
	 * @return
	 */
	public String ShowResultWithPageNo(ArrayList<Disease> diseaseList,
			int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (diseaseList.size() < (pageNum + 1) * itemsInpage) ? diseaseList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String subjectName=diseaseList.get(counter).getSubject();
			String jbmc=diseaseList.get(counter).getJBMC();
			
			   String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>" 
					+ "<td>" + subjectName + "</td>"
					+ "<td>" + jbmc + "</td><td>"
					+ "<span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + subjectName
					+ "\",\""+jbmc+"\",\"疾病基本信息\"," + "\""
					+ jbmc + "\",\"0\")>查看</span></td>" + "</tr>";
		}
		return htmlStr;
	}

	private ArrayList<Disease> generateDiseaseList() {
		DiseaseDAO diseaseDAO=new DiseaseDAO();
		if(this.subjectName!=null)
			this.tablePrefix=(new Dss_subjectDAO()).GetTagByName(this.subjectName);
		ArrayList<Disease> diseaseList = diseaseDAO.DiseaseSearch(this.subjectTitle,this.subjectName,this.tablePrefix);
		return diseaseList;
	}

}
