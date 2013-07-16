package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.ZlfaDAO;
import cn.edu.zju.ccnt.dartSS.object.Amlf;
import cn.edu.zju.ccnt.dartSS.object.Xw;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

public class QueryResultHtml_amlf extends QueryResultHtmlFactory {
	@Override
	public String generateQueryResult() {
		String htmlStr = this.getPageHeader();
		ArrayList<Amlf> lxbzList = GenerateZlfaList();

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
				+ "</b>相关的</img><b>按摩疗法:</b><span>";
		this.setItemsInpage(20);
		this.setTotalRecordNum(lxbzList.size());
		htmlStr += this.SetPageCounterHtml();


		htmlStr += "<table width=\"100%\" border=0 >"
				+ "<tr >"
				+ "<td>"
				+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
		htmlStr += "<tr class=\"tablecaption\"> "
			+ "<td width=\"40\">序号</td>"
				+ "<td width=\"200\">按摩方法</td>" 
				+ "<td width=\"200\">按摩手法</td>"
				+ "<td width=\"60\">穴位</td>" 
				+ "<td width=\"60\">经络</td>" 
				+ "<td width=\"100\">治则</td>" 
				+ "<td width=\"80\">按摩时间</td>" 
				+ "<td width=\"80\">按摩次数</td>" 
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
	public String ShowResultWithPageNo(ArrayList<Amlf> lflist, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (lflist.size() < (pageNum + 1) * itemsInpage) ? lflist
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {		
			 String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + 
				"<td>" + (counter + 1)+ "</td>" 
				+"<td>"+lflist.get(counter).getAMFF()+"</td>" 
				+"<td>"+lflist.get(counter).getAMSJ()+"</td>" 
				+"<td>"+generateXW(lflist.get(counter).getZLGC_ID().toString())+"</td>" 
				+"<td>"+lflist.get(counter).getJL()+"</td>" 
				+"<td>"+lflist.get(counter).getZZ()+"</td>" 
				+"<td>"+lflist.get(counter).getAMSF()+"</td>" 
				+"<td>"+lflist.get(counter).getAMCS()+"</td>" 
				 + "</tr>";
		}
		return htmlStr;
	}

	public String generateXW(String yfid){
		ArrayList<Xw> ywlist= GenerateYwList(yfid);
		
		String str="";
		for(int counter=0;counter<ywlist.size();counter++)
			str+=ywlist.get(counter).getXWMC()+",";
		return str;
		
	}
	private ArrayList<Xw> GenerateYwList(String id) {
		this.setTablePrefix();
		ZlfaDAO zlfaDAO = new ZlfaDAO();
		zlfaDAO.setTablePrefix(this.tablePrefix);
		zlfaDAO.setJBMC(this.subjectTitle);
		ArrayList<Xw> lflist = zlfaDAO.getXwByID(id);
		return lflist;
	}

	private ArrayList<Amlf> GenerateZlfaList() {
		this.setTablePrefix();
		ZlfaDAO zlfaDAO = new ZlfaDAO();
		zlfaDAO.setTablePrefix(this.tablePrefix);
		zlfaDAO.setJBMC(this.subjectTitle);
		ArrayList<Amlf> lflist = zlfaDAO.getAmlfByJbmc();
		return lflist;
	}
}
