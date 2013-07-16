package cn.edu.zju.ccnt.dartSS.web.innerHtml;

import java.util.ArrayList;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**
 * 从其他的关键字关联导航查询到相关的临床研究的具体工厂类
 * 
 * @author zhm
 * 
 */
public class NavigateQueryHtmlGetLcyj extends QueryResultHtmlFactory {

	public String titleString;

	public String CurrentNavigateType;

	/** 
	 * 重载generateQueryResult，产生临床研究查询结果
	 */
	@Override
	public String generateQueryResult() {

		String htmlStr = this.getPageHeader();
		ArrayList<String> lcyjmcList = generateLcyjmcList();

		htmlStr += this.getTitleString();

		this.setItemsInpage(20);
		this.setTotalRecordNum(lcyjmcList.size());
		htmlStr += this.SetPageCounterHtmlWithKeyword();

		htmlStr += "<table width=\"100%\" border=0 bordercolor=\"#ffffff\">"
				+ "<tr >"
				+ "<td>"
				+ "<table width=\"100%\" bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">";

		htmlStr += "<tr bgcolor=\"#C5D1F3\"> " + "<td width=\"30\">序号</td>"
				+ "<td width=\"120\">" + CurrentNavigateType + "</td> "
				+ "<td width=\"220\">临床研究文献名称</td> " + "</tr>";

		htmlStr += DrawCurrentPage(lcyjmcList, this.getCurrentPage());
		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	/**画当前页
	 * @param lcyjmcList 临床研究列表
	 * @param pageNum 页号
	 * @return
	 */
	private String DrawCurrentPage(ArrayList<String> lcyjmcList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (lcyjmcList.size() < (pageNum + 1) * itemsInpage) ? lcyjmcList
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {
			String lcyjmc = lcyjmcList.get(counter).toString();
			htmlStr += "<tr bgcolor=\"#ffffff\" >" + "<td>" + (counter + 1)
					+ "</td>" + "<td>" + this.getKeyWord() + "</td>"
					+ "<td><span class=\"navigateLink\" "
					+ "onclick=GetNavigateQuery(\"" + this.subjectName
					+ "\",\"" + this.subjectTitle + "\",\"临床研究详细信息\"," + "\""
					+ lcyjmc + "\",\"0\")>" + lcyjmc + "</span></td>" + "</tr>";
		}
		return htmlStr;

	}

	public ArrayList<String> generateLcyjmcList() {
		return null;
	}

	public String getTitleString() {
		return titleString;
	}

	/**设置标题提示信息
	 * @param hintForPage 提示信息
	 */
	public void setTitleString(String hintForPage) {
		this.titleString = "<span><img src=\"./public/images/menu_create.gif\"></img>"
				+ hintForPage + "<span>";
	}

	public String getCurrentNavigateType() {
		return CurrentNavigateType;
	}

	public void setCurrentNavigateType(String currentNavigateType) {
		CurrentNavigateType = currentNavigateType;
	}

	@Override
	public void InintialTitleHints() {
		SetTitleHints();
	}

	public void SetTitleHints() {

	}
}
