package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.ZlfaDAO;
import cn.edu.zju.ccnt.dartSS.object.Xylf;
import cn.edu.zju.ccnt.dartSS.object.Zlfa;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

public class QueryResultHtml_xylf extends QueryResultHtmlFactory {
	@Override
	public String generateQueryResult() {
		String htmlStr = this.getPageHeader();
		ArrayList<Xylf> lxbzList = GenerateZlfaList();

		htmlStr += "<span>" +
		GenerateAnLink("中药药方疗法")+
		GenerateAnLink("西药疗法")+	
		GenerateAnLink("按摩疗法")+
		GenerateAnLink("针灸疗法")+
		GenerateAnLink("其他疗法")+		
		"</span>";
		htmlStr += "</br>";
		htmlStr += "<span><img src=\"./public/images/menu_create.gif\">"
				+ "<b>" + this.subjectTitle
				+ "</b>相关的</img><b>西药疗法:</b><span>";
		this.setItemsInpage(20);
		this.setTotalRecordNum(lxbzList.size());
		htmlStr += this.SetPageCounterHtml();


		htmlStr += "<table width=\"100%\" border=0 >"
				+ "<tr >"
				+ "<td>"
				+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
		htmlStr += "<tr class=\"tablecaption\"> "
			+ "<td width=\"40\">序号</td>"
				+ "<td width=\"160\">药物名称</td>" 
				+ "<td width=\"120\">给药方式</td>"
				+ "<td width=\"120\">药物剂型</td>" 
				+ "<td width=\"120\">给药时间</td>" 
				+ "<td width=\"200\">治则</td>" 
				+ "</tr>";
		htmlStr += ShowResultWithPageNo(lxbzList, this.getCurrentPage());

		htmlStr += "</table> " + "</td> " + "</tr> " + "</table>";
		return htmlStr;
	}

	public String GenerateAnLink(String str){
		return  "<span class=\"nhint1\" onclick=GetQueryResult(\""
		+ subjectName
		+ "\",\""
		+ subjectTitle
		+ "\",\""
		+ str
		+ "\",\"0\")>"
		+"<img align=\"absmiddle\" src=\"./public/images/gosearch.gif\"/>"+str+"</span>";
	}
	public String ShowResultWithPageNo(ArrayList<Xylf> lflist, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (lflist.size() < (pageNum + 1) * itemsInpage) ? lflist
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {		
			 String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + 
				"<td>" + (counter + 1)+ "</td>" 
				+"<td>"+lflist.get(counter).getYWMC()+"</td>" 
				+"<td>"+lflist.get(counter).getGYFS()+"</td>" 
				+"<td>"+lflist.get(counter).getYWJX()+"</td>" 
				+"<td>"+lflist.get(counter).getGYSJ()+"</td>" 
				+"<td>"+lflist.get(counter).getZLYZ()+"</td>" 				
				 + "</tr>";
		}
		return htmlStr;
	}

	private ArrayList<Xylf> GenerateZlfaList() {
		this.setTablePrefix();
		ZlfaDAO zlfaDAO = new ZlfaDAO();
		zlfaDAO.setTablePrefix(this.tablePrefix);
		zlfaDAO.setJBMC(this.subjectTitle);
		ArrayList<Xylf> lflist = zlfaDAO.GetXylfByJbmc();
		return lflist;
	}
}
