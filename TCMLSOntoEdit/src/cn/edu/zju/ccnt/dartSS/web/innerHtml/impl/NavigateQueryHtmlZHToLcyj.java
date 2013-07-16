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
public class NavigateQueryHtmlZHToLcyj extends NavigateQueryHtmlGetLcyj {

	
	@Override
	public void SetTitleHints() {
		this.setTitleString("与证候<b>"+ this.getKeyWord() + "</b>相关的<b>临床研究</b>列表");
		this.setCurrentNavigateType("证候名称");
	}

	@Override
	public ArrayList<String> generateLcyjmcList() {
		LcyjDAO lcyjDAO = new LcyjDAO();
		//lcyjDAO.setTablePrefix(this.getTablePrefix());
		lcyjDAO.setTablePrefix("JMZ");
		ArrayList<String> zhList = lcyjDAO.getLcyjmcByZhMC(this.getKeyWord());
		return zhList;
	}
	
}
