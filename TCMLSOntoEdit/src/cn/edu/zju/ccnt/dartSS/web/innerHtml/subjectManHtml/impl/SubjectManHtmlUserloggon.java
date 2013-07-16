package cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.impl;

import cn.edu.zju.ccnt.dartSS.db.dao.UserDAO;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.subjectManHtml.SubjectManHtmlFactory;

/**管理员登录
 * @author zhm
 *  
 */
public class SubjectManHtmlUserloggon extends SubjectManHtmlFactory {
	public String userName = "";

	public String passWord = "";

	@Override
	public String setInnerHtml() {
		String htmlrStr = this.setHintHtml("<b>管理员登录</b> ");
		return htmlrStr += setLoggonHtml();
	}

	public String setLoggonHtml() {
		return "<br/><table>"
				+ "<tr>"
				+ "<td>用户名:</td>"
				+ "<td><input type=\"text\" id=\"subManUserName\" size=20/></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>密  码:</td>"
				+ "<td><input type=\"password\" id=\"subManUserPswd\" size=20/></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td></td>"
				+ "<td><input type=\"button\" id=\"subManUserLoggon\" onclick=userLoggon(subManUserName.value,subManUserPswd.value)  value=\" 登录 \" /></td>"
				+ "</tr>" + "</table>";
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean userAthorize() {
		UserDAO userDAO = new UserDAO();
		if (userDAO.isUserExist(userName, passWord))
			return true;
		else
			return false;
	}

	/**
	 * 专题管理的主界面
	 * 
	 * @return
	 */
	public String subManHomePage() {
		String HtmlStr = new SubjectManHtmlHomePage().setInnerHtml();
		HtmlStr += "<div id=\"subjectManBody\">欢迎登录!! </div>";
		return HtmlStr;
	}

	public String SetHtmlAfterLogged() {
		String HtmlStr = "";
		if (userAthorize())
			HtmlStr += subManHomePage();
		else
			HtmlStr += "<br/><div id=\"subjectManBody\">用户名或密码错误，请重新登录!!"
					+ "<br/>"
					+ "<input type=\"button\" onclick=subjectManagement(\"用户登录\") value=\"重新登录\"/> </div>";
		return HtmlStr;
	}

}
