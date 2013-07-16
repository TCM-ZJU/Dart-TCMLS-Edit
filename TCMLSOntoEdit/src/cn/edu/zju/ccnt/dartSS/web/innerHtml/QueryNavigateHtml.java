package cn.edu.zju.ccnt.dartSS.web.innerHtml;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;
import org.apache.log4j.Logger;

/**导航查询的抽象工厂类
 * @author zhm
 * 
 */
public class QueryNavigateHtml {
	static Logger logger = Logger.getLogger(QueryNavigateHtml.class.getName());

	/**
	 * 专题名称
	 */
	private String subjectName;

	/**
	 * 专题编码
	 */
	private String subjectTitle;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectNameStr) {
		if (subjectNameStr.indexOf("subject_") > -1) {
			String temptStrs[] = subjectNameStr.split("_");
			this.subjectName = (new Dss_subjectDAO())
					.GetNameByTag(temptStrs[1]);
		} else
			this.subjectName = subjectNameStr;
		logger.debug(subjectName);
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	/**产生导航的链接
	 * @param navigateInfo 导航关键字信息
	 * @return
	 */
	public String generateNavigeteHtml(String navigateInfo) {

		String htmlStr = "<li><span class=\"nhint\" onclick=GetQueryResult(\""
				+ subjectName
				+ "\",\""
				+ subjectTitle
				+ "\",\""
				+ navigateInfo
				+ "\",\"0\")>"
				//+ "<img src=\"./public/images/loading.gif\"></img>"
				+ navigateInfo+"</span></li>";
		return htmlStr;
	}
}
