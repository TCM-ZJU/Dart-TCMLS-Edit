package cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.DataBaseActivity;
import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.SubjectManHtmlFactory;

/**
 * 添加主题词
 * 
 * @author zhm
 * 
 */
public class SubjectManHtmAddZtc extends SubjectManHtmlFactory {
	static Logger myLogger = Logger.getLogger(SubjectManHtmAddZtc.class
			.getName());
	
	public String subjectName;
	public String zhuticiName;

	@Override
	public String setInnerHtml() {
		String htmlrStr = this.setHintHtml("<b>添加主题词</b> ");
		return htmlrStr += SetAddZtcPage();
	}

	public String SetAddZtcPage() {
		return "<table>"
				+ "<tr>"
				+ "<td>选择专题:</td>"
				+ "<td>"
				+ this.zhuanTiOptions()
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>独立的主题词:</td>"				
				+ "<td><input type=\"text\" id=subManAddedZTC size=\"25\"></td>"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>提示信息:</td>"
				+ "<td><font color=red>在此处可以单独添加利用\"主题词产生条件\"无法查出的主题词</font></td>"
				+ "</tr><tr>"
				+ "<td></td>"
				+ "<td><br/><input type=\"button\" id=\"subManAddZtc\" "
				+ "onclick=subManAddZtc(zhuantiList.value,subManAddedZTC.value) "
				+ "value=\"添加\" /></td>" + "</tr>" + "</table>";
	}

	/**
	 * 添加主题词
	 * @return
	 */
	public String addZhutici(){
		Dss_subjectDAO subjectDAO =new Dss_subjectDAO();
		if(subjectDAO.addZhuTiCi(subjectName,zhuticiName)>0)
			return "主题词添加成功";
		else
			return "主题词添加失败";
	}
	public void deleteDssRecord(String tag) {
		try {
			String sqlDeleteRec = "delete  from dss_subject where S_TAG= '"
					+ tag + "'";
			DataBaseActivity dba = new DataBaseActivity();
			dba.DBExecuteUpdate(sqlDeleteRec);
		} catch (Exception ex) {
		}
	}

	public String getZhuticiName() {
		return zhuticiName;
	}

	public void setZhuticiName(String zhuticiName) {
		this.zhuticiName = zhuticiName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
