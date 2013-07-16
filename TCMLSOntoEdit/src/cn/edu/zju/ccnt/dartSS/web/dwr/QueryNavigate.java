package cn.edu.zju.ccnt.dartSS.web.dwr;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryNavigateHtml;

/**
 * 产生专题查询的是菜单栏,包括"基本信息 临床研究 相关症状 相关证候 相关病因 临床诊疗 治疗方案 诊疗标准 相关文献 汇总"
 * 
 * @author zhm *
 */
public class QueryNavigate {

	static Logger myLogger = Logger.getLogger(QueryNavigate.class.getName());

	/**
	 * 产生菜单栏
	 * 
	 * @param subjectName
	 *            专题名称
	 * @param subjectTitle
	 *            专题编码
	 * @return
	 */
	public String getQueryNavigeteInfo(String subjectName, String subjectTitle,String queryType) {
		try {
			QueryNavigateHtml queryNavigateHtml = new QueryNavigateHtml();
			queryNavigateHtml.setSubjectName(subjectName);
			queryNavigateHtml.setSubjectTitle(subjectTitle);
			myLogger.info("QueryNavigate: subjectName/" + subjectName
					+ ",subjectTitle/" + subjectTitle);
			// String HtmlStr = "<br/><div>所属专题库:<b>"
			// + queryNavigateHtml.getSubjectName() + "</b> 当前主题词/入口词:<b>"
			// + queryNavigateHtml.getSubjectTitle() + "</b></div><hr/>";
			String HtmlStr = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" "
					+ "cellspacing=\"0\">"
					+ "<tr>"
					+ "<td height=\"9\" colspan=\"4\">"
					+ "</td>"
					+ "</tr>"
					+ "<tr>" + "<td colspan=\"4\" id=\"menu\">" + "<ul>";
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("基本信息");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("临床研究");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("单位");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("期刊");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("日期");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("相关症状");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("相关证候");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("相关病因");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("临床诊疗");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("治疗方案");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("诊疗标准");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("参考标准");
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("疾病信息");			
			HtmlStr += queryNavigateHtml.generateNavigeteHtml("汇总");
			HtmlStr += "</ul>"
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td colspan=\"4\">"
					+ "<ul id=\"info-ul\">"
					+ "<li id=\"info-left\"></li>"
					+ "<li id=\"info\">"
					+ "<ul>"
					+ "<li>所属专题库:<span class=\"now-info\">"+queryNavigateHtml.getSubjectName()+"</span></li>"
					+ "<li>当前主题词/入口词:<span class=\"now-info\">"+queryNavigateHtml.getSubjectTitle()+"</span></li>"
					+ "<li>当前查询: <span class=\"now-info\">"+queryType+"</span></li>"
					+ "</ul>" 
					+ "</li>" 
					+ "<li id=\"info-right\"></li>"
					+ "</ul>" 
					+ "</td>" 
					+ "</tr>" 
					+ "</table>";
			return HtmlStr;
		} catch (Exception ec) {
			myLogger.info("查询专题信息发生异常：" + ec.getMessage() + "\r\n"
					+ ec.toString());
			return "查询专题信息发生异常</br>" + ec.getMessage();
		}
	}

}
