package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.object.By;
import cn.edu.zju.ccnt.dartSS.object.Lczz;
import cn.edu.zju.ccnt.dartSS.object.Xylf;
import cn.edu.zju.ccnt.dartSS.object.Yflf;
import cn.edu.zju.ccnt.dartSS.object.Zh;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;
import cn.edu.zju.ccnt.dartSS.db.dao.LcyjDAO;
import cn.edu.zju.ccnt.dartSS.db.dao.LczzDAO;
import cn.edu.zju.ccnt.dartSS.db.dao.XylfDAO;
import cn.edu.zju.ccnt.dartSS.db.dao.YflfDAO;
import cn.edu.zju.ccnt.dartSS.db.dao.ZhDAO;

/**对一个疾病的各方面信息的汇总
 * @author zhm
 * 
 */
public class QueryResultHtmlStatistic extends QueryResultHtmlFactory {

	private String lcyjSL = "";// 临床研究数

	private String zzSL = "";// 临床症状数

	private String zhSL = "";// 临床证候数

	private String bySL = "";// 病因数

	private String lczdSL = "";// 临床诊断数

	private String zdbzSL = "";// 诊断标准数

	private String zlfaSL = "";// 治疗方案数

	private String syzySL = "";// 中药药方数

	private String syxySL = "";// 西药药方数

	private ArrayList<Lczz> lczzList; // 临床症状列表

	private ArrayList<Zh> zhList; // 临床证候列表

	private ArrayList<By> byList; // 病因列表

	private ArrayList<Xylf> xylfList; // 西药疗法列表

	private ArrayList<Yflf> yflfList; // 中药药方疗法列表

	private void Init() {
		GetZZInfo();
		GetZHInfo();
		GetByInfo();
		GetZyyfInfo();
		GetXyyfInfo();
	}

	/**统计临床研究数，临床症状数，临床证候数，病因数，临床诊断数，诊断标准数,治疗方案数,中药药方数,西药药方数
	 * @return
	 */
	private String HtmlBlockStatic() {
		return "<table width=\"100%\"  bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">临床研究数</td>"
				+ "<td width=\"200\">"
				+ this.getLcyjsl()
				+ "</td>"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">临床症状数</td>"
				+ "<td width=\"200\">"
				+ zzSL
				+ "</td>"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">临床证候数</td>"
				+ "<td width=\"200\">"
				+ zhSL
				+ "</td></tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">病因数</td>"
				+ "<td width=\"200\">"
				+ bySL
				+ "</td>"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">临床诊断数</td>"
				+ "<td width=\"200\">"
				+ lczdSL
				+ "</td>"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">诊断标准数</td>"
				+ "<td width=\"200\">"
				+ zdbzSL
				+ "</td></tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">治疗方案数</td>"
				+ "<td width=\"200\">"
				+ zlfaSL
				+ "</td>"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">中药药方数</td>"
				+ "<td width=\"200\">"
				+ syzySL
				+ "</td>"
				+ "<td width=\"100\"  bgcolor=\"#C5D1F3\">西药药方数</td>"
				+ "<td width=\"200\">" + syxySL + "</td></tr>" + "</table> ";
	}

	/**
	 * 最常见的五个症状
	 * @return
	 */
	private String HtmlBlockSetZZ() {
		String HtmlStr = "<br/>最常见的五个<b>症状</b><br/>"
				+ "<table width=\"100%\" bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
				+ "<tr bgcolor=\"#C5D1F3\"> " 
				+ "<td width=\"40\">序号</td>"
				+ "<td width=\"100\">症状名称</td>" 
				+ "<td width=\"60\">出现次数</td>"
				+ "</tr>";

		int max = (lczzList.size() >= 5) ? 5 : lczzList.size();
		for (int i = 0; i < max; i++)
			HtmlStr += "<tr bgcolor=\"#ffffff\">" 
					+ "<td >" + (i + 1) + "</td>"
					+ "<td >" + lczzList.get(i).getZZMC() + "</td>" 
					+ "<td >"+ lczzList.get(i).getRefNum() + "</td>" 
					+"</tr>";

		HtmlStr += "</table> ";
		return HtmlStr;
	}

	/**最常见的五个证候
	 * @return
	 */
	private String HtmlBlockSetZh() {
		String HtmlStr = "<br/>最常见的五个<b>证候</b><br/>"
			+ "<table width=\"100%\" bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
			+ "<tr bgcolor=\"#C5D1F3\"> " 
			+ "<td width=\"40\">序号</td>"
			+ "<td width=\"100\">证候名称</td>" 
			+ "<td width=\"60\">出现次数</td>"
			+ "</tr>";

		int max = (zhList.size() >= 5) ? 5 : zhList.size();
		for (int i = 0; i < max; i++)
			HtmlStr += "<tr bgcolor=\"#ffffff\">" 
					+ "<td >" + (i + 1) + "</td>"
					+ "<td >" + zhList.get(i).getZHMC() + "</td>" 
					+ "<td >"+ zhList.get(i).getRefNum() + "</td>" 
					+"</tr>";
	
		HtmlStr += "</table> ";
		return HtmlStr;
	}

	/**最常见的五种病因
	 * @return
	 */
	private String HtmlBlockSetBy() {
		String HtmlStr = "<br/>最常见的五种<b>病因</b><br/>"
			+ "<table width=\"100%\" bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
			+ "<tr bgcolor=\"#C5D1F3\"> " 
			+ "<td width=\"30\">序号</td>"
			+ "<td width=\"60\">病因</td>" 
			+ "<td width=\"80\">年龄组</td>" 
			+ "<td width=\"100\">相关临床研究</td>"
			+ "<td width=\"200\">结果</td>"
			+ "</tr>";

		int max = (byList.size() >= 5) ? 5 : byList.size();
		for (int i = 0; i < max; i++)
			HtmlStr += "<tr bgcolor=\"#ffffff\">" 
					+ "<td >" + (i + 1) + "</td>"
					+ "<td >" + byList.get(i).getJ_BY() + "</td>" 
					+ "<td >"+ byList.get(i).getNLZ() + "</td>" 
					+ "<td >"+ byList.get(i).getLCYJMC()+ "</td>" 
					+ "<td >"+ byList.get(i).getJG()+ "</td>" 
					+"</tr>";
	
		HtmlStr += "</table> ";
		return HtmlStr;
	}

	/**最常见的五种中药药方
	 * @return
	 */
	private String HtmlBlockSetZyyf() {
		String HtmlStr = "<br/>最常用的五个<b>中药药方</b><br/>"
			+ "<table width=\"100%\" bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
			+ "<tr bgcolor=\"#C5D1F3\"> " 
			+ "<td width=\"40\">序号</td>"
			+ "<td width=\"100\">药方名称</td>" 
			+ "<td width=\"60\">使用次数</td>"
			+ "</tr>";

		int max = (yflfList.size() >= 5) ? 5 : yflfList.size();
		for (int i = 0; i < max; i++)
			HtmlStr += "<tr bgcolor=\"#ffffff\">" 
					+ "<td >" + (i + 1) + "</td>"
					+ "<td >" + yflfList.get(i).getYFMC() + "</td>" 
					+ "<td >"+ yflfList.get(i).getREFNUM() + "</td>" 
					+"</tr>";
	
		HtmlStr += "</table> ";
		return HtmlStr;
	}

	/**最常见的五种西药药方
	 * @return
	 */
	private String HtmlBlockSetXyyf() {
		String HtmlStr = "<br/>最常用的五个<b>西药药方</b><br/>"
			+ "<table width=\"100%\" bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
			+ "<tr bgcolor=\"#C5D1F3\"> " 
			+ "<td width=\"40\">序号</td>"
			+ "<td width=\"100\">药方名称</td>" 
			+ "<td width=\"60\">使用次数</td>"
			+ "</tr>";

		int max = (xylfList.size() >= 5) ? 5 : xylfList.size();
		for (int i = 0; i < max; i++)
			HtmlStr += "<tr bgcolor=\"#ffffff\">" 
					+ "<td >" + (i + 1) + "</td>"
					+ "<td >" + xylfList.get(i).getYWMC() + "</td>" 
					+ "<td >"+ xylfList.get(i).getREFNUM() + "</td>" 
					+"</tr>";
	
		HtmlStr += "</table> ";
		return HtmlStr;
	}

	public String generateQueryResult() {
		Init();
		String htmlStr = this.getPageHeader();

		htmlStr += "<span><img src=\"./public/images/menu_create.gif\">"
				+ "<b>" + this.subjectTitle + "</b>相关的信息</img><b>汇总</b><span>";

		htmlStr += HtmlBlockStatic();
		htmlStr += HtmlBlockSetZZ();
		htmlStr += HtmlBlockSetZh();
		htmlStr += HtmlBlockSetZyyf();
		htmlStr += HtmlBlockSetXyyf();
		htmlStr += HtmlBlockSetBy();
		htmlStr +="<br/>";
		return htmlStr;
	}

	public String getLcyjsl() {
		if (lcyjSL != "")
			return lcyjSL;
		else {
			setLcyjsl();
			return lcyjSL;
		}
	}

	public void setLcyjsl() {
		LcyjDAO lcyjDAO = new LcyjDAO();
		lcyjDAO.setJBMC(this.subjectTitle);
		lcyjDAO.setTablePrefix(this.getTablePrefix());
		lcyjSL = lcyjDAO.GetLcyjslByJBMC();
	}

	/**
	 * 获取症状信息
	 */
	private void GetZZInfo() {
		LczzDAO lczzDAO = new LczzDAO();
		lczzDAO.setTablePrefix(this.getTablePrefix());
		lczzDAO.setJBMC(this.subjectTitle);
		lczzList = lczzDAO.GetLczzByJbmc();
		this.zzSL = String.valueOf(lczzList.size());
	}

	/**
	 * 获取证候信息
	 */
	private void GetZHInfo() {
		ZhDAO zhDAO = new ZhDAO();
		zhDAO.setTablePrefix(this.getTablePrefix());
		zhDAO.setJBMC(this.subjectTitle);
		zhList = zhDAO.GetZhByJbmc();
		this.zhSL = String.valueOf(zhList.size());
	}

	/**
	 * 获得病因信息
	 */
	private void GetByInfo() {
		LcyjDAO lcyjDAO = new LcyjDAO();
		lcyjDAO.setTablePrefix(this.getTablePrefix());
		lcyjDAO.setJBMC(this.subjectTitle);
		byList = lcyjDAO.GetByByJbmc();
		bySL = String.valueOf(byList.size());
	}

	/**
	 * 获取中药药方信息
	 */
	private void GetZyyfInfo() {
		YflfDAO yflfDAO = new YflfDAO();
		yflfDAO.setTablePrefix(this.getTablePrefix());
		yflfDAO.setJBMC(this.subjectTitle);
		yflfList = yflfDAO.GetYflfByJbmc();
		syzySL = String.valueOf(yflfList.size());
	}

	/**
	 * 获取西药药方信息
	 */
	private void GetXyyfInfo() {
		XylfDAO xylfDAO = new XylfDAO();
		xylfDAO.setTablePrefix(this.getTablePrefix());
		xylfDAO.setJBMC(this.subjectTitle);
		xylfList = xylfDAO.GetYflfByJbmc();
		syxySL = String.valueOf(xylfList.size());
	}
}
