package cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.SubjectManHtmlFactory;

/**
 * 添加新专题
 * 
 * @author zhm
 * 
 */
public class SubjectManHtmlAddsubject extends SubjectManHtmlFactory {
	static Logger myLogger = Logger.getLogger(SubjectManHtmlAddsubject.class
			.getName());

	@Override
	public String setInnerHtml() {
		String htmlrStr = this.setHintHtml("<b>创建专题</b> ");
		return htmlrStr += GenerateAddSubjectHtml();
	}

	public String GenerateAddSubjectHtml() {
		String htmlStr = "<table border=0>"
				+ "<tr>"
				+ "<td >专题 名 称：</td>"
				+ "<td><input type=\"text\" id=\"subjectName\" size=\"35\" border=\"1\"></input>"
				+ "<font color=red>例如：呼吸专题</font>"
				+ "</td></tr>"
				+ "<tr>"
				+ "<td >专题 编 码：</td>"
				+ "<td><input type=\"text\" id=\"subjectTag\" size=\"35\" border=\"1\"></input>"
				+ "<font color=red>例如：HXZT</font>"
				+ "</td></tr>"
				+ "<tr>"
				+ "<td  align=top>主题词条件：</td>"
				+ "<td><textarea  name=\"subjectCondition\" id=\"subjectCondition\" "
				+ "style=\"width:500px;height:60px\" ></textarea></td>"

				/*
				 * + "<td><input type=\"text\" readonly=\"true\"
				 * id=\"subjectCondition\" size=\"35\" border=\"1\"></input>" + "<input
				 * type=\"button\" id=\"saveSubject\" value=\"添加\" " +
				 * "onclick=ShowAddSubCondBox()></input>" + "<input
				 * type=\"button\" id=\"saveSubject\" value=\"清除\" " +
				 * "onclick=clearText()></input>" + "</td>"
				 */
				+ "</tr>"
				+ "<tr >"
				+ "<td></td><td>请确认输入正确的SQL语句,并确保查询结果是疾病名称<br/>"
				+ "<font color=red>例如：select distinct(mhchi) from ZTC_tree where mn like '%TC28.345%' order by mhchi asc </font> "
				+ "<br/>其中<font color=red>mhchi</font>是疾病名称</td>"
				+ "</tr>"
				+ "</tr>"
				+ "<tr >"
				+ "<td></td><td id=ColumnAddSubject></td>"
				+ "</tr>"
				/*+ "<tr>"
				+ "<td width=80>专题 图 片：</td>"
				+ "<td><input type=\"text\" id=\"subjectIntroPic\" size=\"35\" border=\"1\"></input>"
				+ "</td></tr><tr><td></td><td>"
				+ "请将专题介绍图片放至./public/images/subjectPics/目录下，"
				+ "并在上面的对话框中输入图片的名称。"
				+ "如果不输入任何名称，将选择默认图片default.png作为专题介绍图片。"
				+ "</td></tr>"*/
				+ "<tr>"
				+ "<td>独立的主题词:</td>"				
				+ "<td><input type=\"text\" id=subjectAddedZTC size=\"70\"></td>"
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td></td>"
				+ "<td><font color=red>在此处可以单独添加利用\"主题词条件\"无法产生的主题词，多个主题词用'|'分割</font></td>"
				+ "</tr>"
				+"<tr>"
				+ "<td >专题 说 明：</td>" +
						"<td><input type=\"text\" id=\"subjectNotes\"  size=\"70\"  border=\"1\"></input>"
				+ "</td></tr>"
				+ "<tr>"
				+ "<td COLSPAN=\"2\"><input type=\"button\" id=\"saveSubject\" value=\" 开始创建专题\" "
				+ "onclick=subManAdd(subjectName.value,subjectTag.value,subjectCondition.value,\"\",subjectNotes.value,subjectAddedZTC.value)></input></td>"
				+ "</tr>" + ""
				+ "<tr><td></td><td id=hiddeProcessBar></td></tr></table>";

		return htmlStr;
	}

	
	/**添加专题
	 * @param subjectName
	 * @param subjectTag
	 * @param subjectCondition
	 * @param subjectPicName
	 * @param subjectNotes
	 * @param subjectAddedZTC
	 * @return
	 */
	public String SaveNewSubject(String subjectName, String subjectTag,
			String subjectCondition, String subjectPicName, String subjectNotes,String subjectAddedZTC) {
		myLogger.info("begein to create a subjet: subjectName/" + subjectName
				+ ", subjectTag/" + subjectTag + ",subjectCondition/"
				+ subjectCondition + ", subjectPicName/" + subjectPicName
				+ ",subjectNotes/" + subjectNotes+",subjectAddedZTC/"+subjectAddedZTC);
		String feedBackStr = "";
		Dss_subjectDAO dss_subjectDAO = new Dss_subjectDAO();
		if (dss_subjectDAO.IsSubjectNameExist(subjectName))
			feedBackStr = "专题名称\"" + subjectName + "\"已经存在,不能创建重复的专题库";
		else if (dss_subjectDAO.IsSubjectTagExist(subjectTag))
			feedBackStr = "专题编码\"" + subjectTag + "\"已经存在，不能是用重复的专题编码";
		else {
			int errFlag = dss_subjectDAO.InsertNewSubject(subjectName,
					subjectTag, subjectPicName, subjectCondition, subjectNotes,subjectAddedZTC);
			if (errFlag >= 1)
				feedBackStr = "新专题已经创建";
			else if (errFlag < 0) {
				deleteDssRecord(subjectTag);
				feedBackStr = "创建新专题失败，请重新提交。";
			}

		}

		return feedBackStr;
	}

	public void deleteDssRecord(String tag) {
		try {
			Dss_subjectDAO dss_sujectDAO = new Dss_subjectDAO();
			dss_sujectDAO.deleteSubjectByTag(tag);
		} catch (Exception ex) {
			myLogger.info(ex.getMessage()+"\r\n"+ex.toString());
			return;
		}
	}

}
