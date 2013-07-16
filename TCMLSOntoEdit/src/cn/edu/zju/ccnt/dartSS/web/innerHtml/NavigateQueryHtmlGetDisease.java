package cn.edu.zju.ccnt.dartSS.web.innerHtml;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.object.Disease;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**
 * 从某个关键词导航查询相关的疾病列表
 * 
 * @author zhm
 * 
 */
public class NavigateQueryHtmlGetDisease extends QueryResultHtmlFactory {

	public String titleString = "";

	public String NavigeteTypeName = "";

	/**
	 * 重载generateQueryResult，产生疾病查询结果
	 * 
	 */
	@Override
	public String generateQueryResult() {

		String htmlStr = this.getPageHeader();
		ArrayList<Disease> diseaseList = generateDiseaseList();

		htmlStr += getTitleString();

		this.setItemsInpage(20);
		this.setTotalRecordNum(diseaseList.size());
		htmlStr += this.SetPageCounterHtmlWithKeyword();

		htmlStr += "<table width=\"100%\" border=0 bordercolor=\"#ffffff\">"
				+ "<tr >"
				+ "<td>"
				+ "<table width=\"100%\" bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">";

		htmlStr += "<tr bgcolor=\"#C5D1F3\"> " + "<td width=\"30\">序号</td>"
				+ "<td width=\"80\">" + NavigeteTypeName + "</td> "
				+ "<td width=\"120\">相关的疾病名称</td> "
				+ "<td width=\"60\">关联次数</td> "
				+ "<td width=\"80\">查看疾病信息</td>" + "</tr>";

		htmlStr += DrawCurrentPage(diseaseList, this.getCurrentPage());
		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	/**
	 * 画当前页
	 * 
	 * @param diseaseList
	 *            疾病列表
	 * @param pageNum
	 *            当前页
	 * @return
	 */
	private String DrawCurrentPage(ArrayList<Disease> diseaseList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (diseaseList.size() < (pageNum + 1) * itemsInpage) ? diseaseList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {

			String jbmc = diseaseList.get(counter).getJBMC();
			htmlStr += "<tr bgcolor=\"#ffffff\" >" + "<td>" + (counter + 1)
					+ "</td>" + "<td>" + this.KeyWord + "</td>" + "<td>" + jbmc
					+ "</td>" + "<td>" + diseaseList.get(counter).getRefNum()
					+ "</td>" + "<td>" + "<span class=\"navigateLink\" "
					+ "onclick=GetQueryResult(\"" + this.subjectName + "\",\""
					+ jbmc + "\",\"基本信息\",\"0\")>" + "查看" + "</span></td>"
					+ "</tr>";
		}
		return htmlStr;

	}

	public ArrayList<Disease> generateDiseaseList() {
		return null;
	}

	public String getTitleString() {
		return titleString;
	}

	/**
	 * 设置提示信息
	 * 
	 * @param hintForPage
	 */
	public void setTitleString(String hintForPage) {
		this.titleString = "<span><img src=\"./public/images/menu_create.gif\"></img>"
				+ hintForPage + "<span>";
	}

	public String getNavigeteTypeName() {
		return NavigeteTypeName;
	}

	@Override
	public void InintialTitleHints() {
		SetTitleHints();
	}

	public void setNavigeteTypeName(String navigeteTypeName) {
		NavigeteTypeName = navigeteTypeName;
	}

	public void SetTitleHints() {

	}
}
