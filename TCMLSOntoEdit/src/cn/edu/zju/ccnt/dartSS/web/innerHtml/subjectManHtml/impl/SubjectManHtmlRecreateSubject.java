package cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;
import cn.edu.zju.ccnt.dartSS.db.dao.SubjectTablesDAO;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.SubjectManHtmlFactory;

/**重建专题
 * @author zhm
 * 
 */
public class SubjectManHtmlRecreateSubject extends SubjectManHtmlFactory {
	
	@Override
	public String setInnerHtml() {
		String htmlrStr = this.setHintHtml("<b>重建专题</b> ");
		return htmlrStr += setRecreateSubjectHtml();
	}

	
	public String setRecreateSubjectHtml() {
		return "<br/><table>"
				+ "<tr>"
				+ "<td>选择专题:</td>"
				+ "<td>"
				+ this.zhuanTiOptions()
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>提示信息:</td>"
				+ "<td><font color=red>这个过程将删除当前专题相关的数据表，并根据专题主题词产生条件，重新创建相关数据表</font></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td></td>"
				+ "<td><input type=\"button\" id=\"recreateSubject\" "
				+ "onclick=subManRecreate(zhuantiList.value) value=\"开始重建专题\" /></td>"
				+ "</tr><tr><td></td><td id=hiddeProcessBar></td></tr>" + "</table>";
	}

	/**
	 * 重建一个专题
	 * 
	 * @param subjectName
	 * @return
	 */
	public String recreateSubject(String subjectName) {
		String tablePrefix="";
		String errorStr="";
		String subjectCondition="";
		
		Dss_subjectDAO subjectDAO =new Dss_subjectDAO();
		tablePrefix = subjectDAO.GetTagByName(subjectName);
		subjectCondition=subjectDAO.GetConditionBySubjectName(subjectName);
		
		
		SubjectTablesDAO subjectTablesDAO =new SubjectTablesDAO();
		
		subjectTablesDAO.setSubCondition(subjectCondition);
		subjectTablesDAO.setTablePrefix(tablePrefix);
		
		//删除原有表
		int errorflag=subjectTablesDAO.DropTablesOFSubject();
		if(errorflag>=1)
			errorStr="1.删除原表成功";
		else
			errorStr="1.删除原表失败";
		//创建新表
		errorflag=subjectTablesDAO.CreateTables();
		if(errorflag>=1)
			errorStr+="</br>2.创建新表成功";
		else
			errorStr+="</br>2.创建新表失败";
		return errorStr;
	}


	


	
}
