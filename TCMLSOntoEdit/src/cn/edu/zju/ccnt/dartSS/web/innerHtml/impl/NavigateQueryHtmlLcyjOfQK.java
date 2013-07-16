package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;

import java.util.ArrayList;

import cn.edu.zju.ccnt.dartSS.db.dao.LcyjDAO;
import cn.edu.zju.ccnt.dartSS.web.innerHtml.NavigateQueryHtmlGetLcyj;

/**
 * 从证候查询相关的临床研究
 * 
 * @author zhm
 * 
 */
public class NavigateQueryHtmlLcyjOfQK extends NavigateQueryHtmlGetLcyj {

	
	@Override
	public void SetTitleHints() {
		this.setTitleString("当前疾病相关的、期刊名称为<b>《"+ this.getKeyWord() + "</b>》的<b>临床研究文献</b>列表<br/>");
		this.setCurrentNavigateType("期刊名称");
	}

	@Override
	public ArrayList<String> generateLcyjmcList() {
		LcyjDAO lcyjDAO = new LcyjDAO();		
		lcyjDAO.setTablePrefix("JMZ");
		lcyjDAO.setJBMC(this.subjectTitle);
		ArrayList<String> zhList = lcyjDAO.GetLCYJByJbmcAndQKMC(this.getKeyWord());
		
		return zhList;
	}
	
}
