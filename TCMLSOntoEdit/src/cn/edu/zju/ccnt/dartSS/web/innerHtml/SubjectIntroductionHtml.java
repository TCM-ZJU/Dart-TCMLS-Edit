package cn.edu.zju.ccnt.dartSS.web.innerHtml;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;

/**专题介绍界面的实现类
 * @author zhm
 * 
 */
public class SubjectIntroductionHtml {
	public String subjectName;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * 产生提示信息栏
	 * @return
	 */
	public String generateSubjectIntroHtml() {
		String picName=getPicName();
		if(picName=="")
			picName="default.png";
		String htmlStr = "<br/><div>专题库:<b>"
				+ subjectName
				+ "</b> 简介</div><hr/>"
				+ "<img src=\"./public/images/subjectpics/"+picName+"\" ></img>";
		return htmlStr;
	}
	/**
	 * 取得专题介绍图片
	 * @return
	 */
	public String getPicName()
	{
		Dss_subjectDAO subjectDAO=new Dss_subjectDAO();
		return subjectDAO.GetPicnameBySubjectName(subjectName);
	}
}
