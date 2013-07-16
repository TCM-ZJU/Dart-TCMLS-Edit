package cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;
import cn.edu.zju.ccnt.dartSS.db.dao.SubjectTablesDAO;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.SubjectManHtmlFactory;

/**删除专题
 * @author zhm
 * 
 */
public class SubjectManHtmlDeleteSubject extends SubjectManHtmlFactory {
	@Override
	public String setInnerHtml() {
		String htmlrStr = this.setHintHtml("<b>删除专题</b> ");
		return htmlrStr += setDeleteSubjectHtml();
	}

	public String setDeleteSubjectHtml() {
		return "<table>"
				+ "<tr>"
				+ "<td>选择专题:</td>"
				+ "<td>"
				+ this.zhuanTiOptions()
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>提示信息:</td>"
				+ "<td><font color=red>专题删除需要重新创建才能恢复,请慎重操作</font></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td></td>"
				+ "<td><input type=\"button\" id=\"subManDeleteSubject\" "
				+ " onclick=subManDelete(zhuantiList.value) value=\" 删除专题 \" /></td>"
				+ "</tr><tr><td></td><td id=hiddeProcessBar></td></tr>"
				+ "</table>";
	}

	/**
	 * 删除一个专题
	 * 
	 * @param subjectName
	 * @return
	 */
	public String deleteSubject(String subjectName) {
		String errorString = "";
		String tablePrefix = "";
		Dss_subjectDAO subjectDAO = new Dss_subjectDAO();
		tablePrefix = subjectDAO.GetTagByName(subjectName);

		SubjectTablesDAO subjectTablesDAO = new SubjectTablesDAO();
		subjectTablesDAO.setTablePrefix(tablePrefix);
		// 删除专题相关表
		int errorflag = subjectTablesDAO.DropTablesOFSubject();
		if (errorflag <= 0)
			errorString = "1.删除专题相关子表发生错误。";
		else
			errorString = "1.删除专题相关子表成功。";
		// 删除专题
		subjectDAO.deleteSubjectByName(subjectName);
		return errorString += "</br>2.删除专题记录成功";
	}
}
