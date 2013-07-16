package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.ZlfaDAO;
import cn.edu.zju.ccnt.dartSS.object.Xylf;
import cn.edu.zju.ccnt.dartSS.object.Yflf;
import cn.edu.zju.ccnt.dartSS.object.Yw;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

public class QueryResultHtml_zyyflf extends QueryResultHtmlFactory {
	@Override
	public String generateQueryResult() {
		String htmlStr = this.getPageHeader();
		ArrayList<Yflf> lxbzList = GenerateZlfaList();

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
				+ "</b>相关的</img><b>中药药方疗法:</b><span>";
		this.setItemsInpage(20);
		this.setTotalRecordNum(lxbzList.size());
		htmlStr += this.SetPageCounterHtml();


		htmlStr += "<table width=\"100%\" border=0 >"
				+ "<tr >"
				+ "<td>"
				+ "<table id=\"content\" width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\">";
		htmlStr += "<tr class=\"tablecaption\"> "
			+ "<td width=\"40\">序号</td>"
				+ "<td width=\"160\">药方名称</td>" 
				+ "<td width=\"200\">药方描述</td>" 
				+ "<td width=\"80\">给药方式</td>"
				+ "<td width=\"80\">药方剂型</td>" 
				+ "<td width=\"120\">治则</td>" 
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
	public String ShowResultWithPageNo(ArrayList<Yflf> lflist, int pageNum) {
		String htmlStr = "";
		int BeginPoint = pageNum * this.itemsInpage;
		int endPoint = (lflist.size() < (pageNum + 1) * itemsInpage) ? lflist
				.size()
				: (pageNum + 1) * itemsInpage;
		for (int counter = BeginPoint; counter < endPoint; counter++) {		
			 String tableClass=(counter%2==1)?"tablerow1":"tablerow2";
				htmlStr += "<tr class=\"" + tableClass + "\" >" + 
				"<td>" + (counter + 1)+ "</td>" 
				+"<td>"+lflist.get(counter).getYFMC()+"</td>" 
				+"<td>"+generateYFMS(lflist.get(counter).getZLGC_ID().toString())+"</td>" 
				+"<td>"+lflist.get(counter).getGYFS()+"</td>" 
				+"<td>"+lflist.get(counter).getYFJX()+"</td>" 
				+"<td>"+lflist.get(counter).getZZ()+"</td>" 				
				 + "</tr>";
		}
		return htmlStr;
	}

	public String generateYFMS(String yfid){
		ArrayList<Yw> ywlist= GenerateYwList(yfid);
		
		String str="";
		for(int counter=0;counter<ywlist.size();counter++)
			str+=ywlist.get(counter).getYWMC()
			+ywlist.get(counter).getYMJL()
			+ywlist.get(counter).getJLDW()
			+",";
		return str;
		
	}
	private ArrayList<Yflf> GenerateZlfaList() {
		this.setTablePrefix();
		ZlfaDAO zlfaDAO = new ZlfaDAO();
		zlfaDAO.setTablePrefix(this.tablePrefix);
		zlfaDAO.setJBMC(this.subjectTitle);
		ArrayList<Yflf> lflist = zlfaDAO.getYflfByJbmc();
		return lflist;
	}
	
	private ArrayList<Yw> GenerateYwList(String ywid) {
		this.setTablePrefix();
		ZlfaDAO zlfaDAO = new ZlfaDAO();
		zlfaDAO.setTablePrefix(this.tablePrefix);
		zlfaDAO.setJBMC(this.subjectTitle);
		ArrayList<Yw> lflist = zlfaDAO.getYwByYwID(ywid);
		return lflist;
	}
}
