package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.DiseaseDAO;
import cn.edu.zju.ccnt.dartSS.db.dao.LcyjDAO;
import cn.edu.zju.ccnt.dartSS.object.Dzz;
import cn.edu.zju.ccnt.dartSS.object.Lcyj;
import cn.edu.zju.ccnt.dartSS.object.Lczl;
import cn.edu.zju.ccnt.dartSS.object.Syjc;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**
 * 查询临床研究的详细信息
 * @author zhm 
 */
public class NavigateQueryHtmlLcyjDetail extends QueryResultHtmlFactory {

	private ArrayList<String> DiseaseList;

	private Lcyj lcyj;

	private ArrayList<Syjc> syjcList;

	private ArrayList<Dzz> dzzList;

	private ArrayList<Lczl> lzclList;

	private ArrayList<String> yuanwenIDList;

	private void init() {

		LcyjDAO lcyjDAO = new LcyjDAO();
		lcyjDAO.setTablePrefix(this.getTablePrefix());
		lcyjDAO.setJBMC(this.subjectTitle);
		lcyj = lcyjDAO.getLcyjByLcyjmc(this.KeyWord);
		syjcList = lcyjDAO.GetSyjcByLcyjmc(KeyWord);
		dzzList = lcyjDAO.GetDzzByLcyjmc(KeyWord);
		lzclList = lcyjDAO.getLczlByLcyjmc(KeyWord);
		yuanwenIDList = new ArrayList<String>();
		yuanwenIDList = lcyjDAO.getYuanWenByLcyjmc(KeyWord);
		DiseaseDAO diseaseDAO = new DiseaseDAO();
		diseaseDAO.setTablePrefix(this.getTablePrefix());
		DiseaseList = diseaseDAO.getJBMCByLcyjmc(KeyWord);
	}

	@Override
	public String generateQueryResult() {

		String htmlStr = this.getPageHeader();

		htmlStr += "<span><img src=\"./public/images/menu_create.gif\"></img>临床研究<b>"
				+ this.KeyWord + "</b> 详细信息<span></br>";
		init();
		htmlStr += YWinfo();
		htmlStr += LcyjBasicInfo();
		htmlStr += DzzInfo();
		htmlStr += SYJCInfo();
		htmlStr += LCZLInfo();
		htmlStr += relatedDisease();

		return htmlStr;
	}

	private String relatedDisease() {
		String htmlStr = "<br/>该临床研究中所提到的<b>其它疾病</b>";
		htmlStr += "<table  width=\"100%\"  bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td bgcolor=\"#C5D1F3\">疾病名称</td>"
				+ "<td bgcolor=\"#C5D1F3\">查看疾病信息</td>" + "</tr>";

		for (int i = 0; i < DiseaseList.size(); i++) {
			String jbmc = DiseaseList.get(i).toString();
			if (!jbmc.equals(this.subjectTitle))
				htmlStr += "<tr bgcolor=\"#ffffff\">"
						+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">" + jbmc
						+ "</td>" + "<td>查看</td>" + "</tr>";
		}
		return htmlStr += "</table></br>";
	}

	private String LcyjBasicInfo() {
		String htmlStr = "<br/>该临床研究的<b>基本信息</b>";
		htmlStr += "<table width=\"100%\"  bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">临床研究名称</td>"
				+ "<td>"
				+ lcyj.getLCYJMC()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">研究分类</td>"
				+ "<td>"
				+ lcyj.getYJFL()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">病例采集时间</td>"
				+ "<td>"
				+ lcyj.getBLCJSJ()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">观察组总例数</td>"
				+ "<td>"
				+ lcyj.getGCZZLS()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">女性观察病例</td>"
				+ "<td>"
				+ lcyj.getFGCLS()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">男性观察病例</td>"
				+ "<td>"
				+ lcyj.getMGCLS()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">年龄组</td>"
				+ "<td>"
				+ lcyj.getNLZ()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">研究方法</td>"
				+ "<td>"
				+ lcyj.getYJFF()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">研究分组</td>"
				+ "<td>"
				+ lcyj.getYJFZ()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">结果</td>"
				+ "<td>"
				+ lcyj.getJG()
				+ "</td>"
				+ "</tr>"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td width=\"120\"  bgcolor=\"#C5D1F3\">临床研究单位</td>"
				+ "<td>"
				+ lcyj.getLCYJDW() + "</td>" + "</tr>" + "</table>";
		return htmlStr;
	}

	private String LCZLInfo() {
		String htmlStr = "<br/><b>临床诊疗信息</b>信息";
		htmlStr += "<table width=\"100%\"  bgcolor=\"#4371EB\" border=0 cellpadding=\"1\" cellspacing=\"1\">"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td bgcolor=\"#C5D1F3\">诊断标准来源</td>"
				+ "<td bgcolor=\"#C5D1F3\">中医诊断方法</td>"
				+ "<td bgcolor=\"#C5D1F3\">诊断指标分类</td>"
				+ "<td bgcolor=\"#C5D1F3\">指标项目</td>"
				+ "<td bgcolor=\"#C5D1F3\">疗效标准来源</td>"
				+ "<td bgcolor=\"#C5D1F3\">疗效标准</td>"
				+ "<td bgcolor=\"#C5D1F3\">总有效率</td>" + "</tr>";
		for (int i = 0; i < lzclList.size(); i++) {
			htmlStr += "<tr bgcolor=\"#ffffff\">" + "<td >"
					+ lzclList.get(i).getZDBZLY() + "</td>" + "<td >"
					+ lzclList.get(i).getZYZDFF() + "</td>" + "<td >"
					+ lzclList.get(i).getZDZBFL() + "</td>" + "<td >"
					+ lzclList.get(i).getZBXM() + "</td>" + "<td >"
					+ lzclList.get(i).getLXBZLY() + "</td>" + "<td >"
					+ lzclList.get(i).getLXBZ() + "</td>" + "<td >"
					+ lzclList.get(i).getZYXL() + "</td>" + "</tr>";
		}
		htmlStr += "</table>";
		return htmlStr;
	}

	private String DzzInfo() {
		String htmlStr = "<br/><b>对照组</b>信息";
		htmlStr += "<table width=\"100%\"  bgcolor=\"#4371EB\" "
				+ "border=0 cellpadding=\"1\" cellspacing=\"1\">"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td bgcolor=\"#C5D1F3\">对照组名称</td>"
				+ "<td bgcolor=\"#C5D1F3\">年龄</td>"
				+ "<td bgcolor=\"#C5D1F3\">例数</td>"
				+ "<td bgcolor=\"#C5D1F3\">治疗药物</td>"
				+ "<td bgcolor=\"#C5D1F3\">检测</td>" + "</tr>";
		for (int i = 0; i < dzzList.size(); i++) {
			htmlStr += "<tr bgcolor=\"#ffffff\">" + "<td >"
					+ dzzList.get(i).getDZZMC() + "</td>" + "<td >"
					+ dzzList.get(i).getNL() + "</td>" + "<td >"
					+ dzzList.get(i).getLS() + "</td>" + "<td >"
					+ dzzList.get(i).getZLYW() + "</td>" + "<td >"
					+ dzzList.get(i).getJC() + "</td>" + "</tr>";
		}
		htmlStr += "</table>";
		return htmlStr;
	}

	private String SYJCInfo() {
		String htmlStr = "<br/><b>实验检查</b>信息";
		htmlStr += "<table width=\"100%\"  bgcolor=\"#4371EB\""
				+ " border=0 cellpadding=\"1\" cellspacing=\"1\">"
				+ "<tr bgcolor=\"#ffffff\">"
				+ "<td bgcolor=\"#C5D1F3\">检查项目</td>"
				+ "<td bgcolor=\"#C5D1F3\">检查时间</td>"
				+ "<td bgcolor=\"#C5D1F3\">变化指标</td>"
				+ "<td bgcolor=\"#C5D1F3\">观察分组</td>" + "</tr>";
		for (int i = 0; i < syjcList.size(); i++) {
			htmlStr += "<tr bgcolor=\"#ffffff\">" + "<td  "
					+ syjcList.get(i).getJCXM() + "</td>" + "<td >"
					+ syjcList.get(i).getJCSJ() + "</td>" + "<td >"
					+ syjcList.get(i).getBHZB() + "</td>" + "<td >"
					+ syjcList.get(i).getGCFZ() + "</td>" + "</tr>";
		}
		htmlStr += "</table>";
		return htmlStr;
	}

	private String YWinfo() {
		String htmlStr = "<br/><b>查看相关原文</b>信息  [ ";
		if (this.yuanwenIDList.size() <= 0)
			htmlStr += "<span > 没有相关原文 </span>";
		else
			for (int i = 0; i < yuanwenIDList.size(); i++)
				/*
				 * htmlStr += "<span class=\"pageHint\"> 第" + (i + 1) + "页
				 * </span>";
				 */
				htmlStr += "<a href=showimage.pic?picId="
						+ yuanwenIDList.get(i).toString()
						+ " target=\"_blank\"> 第" + (i + 1) + "页</a>";
		return htmlStr += " ]<br/>";
	}

}
