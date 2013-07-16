package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.DiseaseDAO;
import cn.edu.zju.ccnt.dartSS.object.Disease;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**
 * 查询疾病的基本信息
 * 
 * @author zhm
 * 
 */
public class QueryResultHtmlDiseaesInfo extends QueryResultHtmlFactory {

	@Override
	public String generateQueryResult() {

		// get the common header of the query result page
		String htmlStr = this.getPageHeader();
		ArrayList<Disease> diseaseArray = generateDiseaseList();

		htmlStr += "";

		this.setItemsInpage(15);
		this.setTotalRecordNum(diseaseArray.size());
		htmlStr += this.SetPageCounterHtml();

		htmlStr += "<table width=\"100%\" border=0 >"
				+ "<tr >"
				+ "<td>"
				+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
		htmlStr += "<tr class=\"tablecaption\"> "
				+ "<td width=\"40\" class=\"xuhao\">序号</td>"
				+ "<td width=\"100\">疾病名称</td>" + "<td width=\"120\">常见原发病</td> "
				+ "<td width=\"120\">常见并发症</td> " + "<td width=\"100\">常见证候</td>"
				+ "<td width=\"100\">常见症状</td>" + "</tr>";

		htmlStr += DrawDiseaseInfo(diseaseArray, this.getCurrentPage());
		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	private String DrawDiseaseInfo(ArrayList<Disease> diseaseArray, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (diseaseArray.size() < (pageNum + 1) * itemsInpage) ? diseaseArray
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
            String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
			htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
					+ "</td>" + "<td>" + diseaseArray.get(counter).getJBMC()
					+ "</td>" + "<td>" + diseaseArray.get(counter).getYFB()
					+ "</td>" + "<td>" + diseaseArray.get(counter).getBFZ()
					+ "</td>" + "<td>" + diseaseArray.get(counter).getZH()
					+ "</td>" + "<td>" + diseaseArray.get(counter).getZZ()
					+ "</td>" + "</tr>";
		}
		return htmlStr;

	}

	private ArrayList<Disease> generateDiseaseList() {
		this.setTablePrefix();
		DiseaseDAO getDiseaseBasicInfoDao = new DiseaseDAO();
		getDiseaseBasicInfoDao.setDiseaseName(subjectTitle);
		getDiseaseBasicInfoDao.setTablePrefix(this.getTablePrefix());
		ArrayList<Disease> diseaseArray;
		if (this.isWithRkc())
			diseaseArray = getDiseaseBasicInfoDao.GetDiseaseBasicInfoWithRkc();
		else
			diseaseArray = getDiseaseBasicInfoDao.GetDiseaseBasicInfo();
		return diseaseArray;
	}

}
