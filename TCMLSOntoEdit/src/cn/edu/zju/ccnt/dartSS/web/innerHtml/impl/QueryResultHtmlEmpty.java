package cn.edu.zju.ccnt.dartSS.web.innerHtml.impl;


import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtmlFactory;

/**当没有查到任何信息时，返回该页面
 * @author zhm
 * 
 */
public class  QueryResultHtmlEmpty extends QueryResultHtmlFactory{
			@Override
			public String generateQueryResult() {

				// get the common header of the query result page
				String htmlStr = this.getPageHeader();
				htmlStr += " 此功能正在建设中";
				return htmlStr;
			}
			
			
}
