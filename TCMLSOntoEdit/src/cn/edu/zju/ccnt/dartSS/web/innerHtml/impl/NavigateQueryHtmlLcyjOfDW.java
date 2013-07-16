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
public class NavigateQueryHtmlLcyjOfDW extends NavigateQueryHtmlGetLcyj {

	
	@Override
	public void SetTitleHints() {
		this.setTitleString("与当前疾病相关的、作者单位为<b>"+ this.getKeyWord() + "</b>的<b>临床研究文献</b>列表<br/>");
		this.setCurrentNavigateType("作者单位");
	}

	@Override
	public ArrayList<String> generateLcyjmcList() {
		LcyjDAO lcyjDAO = new LcyjDAO();		
		lcyjDAO.setTablePrefix("JMZ");
		lcyjDAO.setJBMC(this.subjectTitle);
		ArrayList<String> zhList = lcyjDAO.GetLCYJMCByJbmcAndDW(this.getKeyWord());
		return zhList;
	}
	
}
