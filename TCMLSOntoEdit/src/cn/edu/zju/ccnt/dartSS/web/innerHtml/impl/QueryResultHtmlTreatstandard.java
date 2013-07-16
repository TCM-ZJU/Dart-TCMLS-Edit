package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.LczlDAO;
import cn.edu.zju.ccnt.dartSS.object.Lczl;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**疾病的诊疗标准
 * @author zhm
 * 
 */
public class QueryResultHtmlTreatstandard extends QueryResultHtmlFactory{
	@Override
	public String generateQueryResult() {

		String htmlStr = this.getPageHeader();
		ArrayList<Lczl> lxbzList =GenerateLxbzList();

		htmlStr+="<span><img src=\"./public/images/menu_create.gif\"><b>"+this.subjectTitle+"</b>相关的</img><b>诊疗标准</b>信息列表<span>";
		this.setItemsInpage(20);
		this.setTotalRecordNum(lxbzList.size());
		htmlStr += this.SetPageCounterHtml();
		htmlStr += "<table width=\"100%\" border=0 >"
			+ "<tr >"
			+ "<td>"
			+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
	htmlStr += "<tr class=\"tablecaption\"> " + "<td width=\"40\">序号</td>"
				+ "<td width=\"100\">指标项目</td>"
				+ "<td width=\"120\">诊断指标分类</td>"
				+ "<td width=\"120\">诊断标准来源</td>"
				+ "<td width=\"100\">疗效标准</td>"
				+ "<td width=\"120\">疗效标准来源</td>"
				+ "<td width=\"100\">总有效率</td>"
				+ "</tr>";
		
		htmlStr += ShowResultWithPageNo(lxbzList, this.getCurrentPage());


		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}
	
	public String ShowResultWithPageNo(ArrayList<Lczl> lxbzList, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum*this.itemsInpage;
		int endPoint=(lxbzList.size()<(pageNum+1)*itemsInpage)?lxbzList.size():(pageNum+1)*itemsInpage;
		for (int counter=BeginPoint; counter <endPoint; counter++) {

			 String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + "<td>" + (counter + 1)
				+ "</td>"
			+ "<td>" +lxbzList.get(counter).getZBXM()+ "</td>" 
			+"<td>" + lxbzList.get(counter).getZDZBFL()+ "</td>" 
			+ "<td>" +lxbzList.get(counter).getZDBZLY()+ "</td>" 
			+ "<td>" +lxbzList.get(counter).getLXBZ()+ "</td>" 
			+ "<td>" +lxbzList.get(counter).getLXBZLY()+ "</td>" 
			+ "<td>" +lxbzList.get(counter).getZYXL()+ "</td>" 
			+ "</tr>";
		}
		return htmlStr;
	}
	
	private ArrayList<Lczl> GenerateLxbzList(){
		this.setTablePrefix();		
		LczlDAO lczlDAO = new LczlDAO();
		lczlDAO.setTablePrefix(this.tablePrefix);
		lczlDAO.setJBMC(this.subjectTitle);
		ArrayList<Lczl> lczlList = lczlDAO.GetLxbzByJbmc();
		return lczlList;
	}
}
