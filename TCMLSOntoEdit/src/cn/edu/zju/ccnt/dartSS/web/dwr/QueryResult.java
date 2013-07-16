package cn.edu.zju.ccnt.dartSS.web.dwr;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.web.innerHtml.QueryResultHtml;

/**
 * dwr的server端的主要入口之一，取查询结果
 * @author zhm
 */
public class QueryResult {

	static Logger mylogger = Logger.getLogger(QueryResult.class.getName());

	/**根据查询的类型调用dartgrid进行查询。
	 * 结果为一段html，以string返回给javascript中的回调函数，用于更新页面。
	 * 用于以疾病名称为关键词的查询，不用于关联导航查询，例如根据疾病名称查相关证候、
	 * 根据疾病名称查相关症状、根据疾病名称查临床研究。	
	 * @param subjectName 专题名称
	 * @param subjectTitle 专题编码
	 * @param queryType 查询类型
	 * @param PageNo 翻页信息
	 * @return
	 */
	public String getQueryResult(String subjectName, String subjectTitle,
			String queryType, String PageNo) {
		try {

			QueryResultHtml queryResultHtml = new QueryResultHtml();
			queryResultHtml.setSubjectName(subjectName);
			queryResultHtml.setSubjectTitle(subjectTitle);
			queryResultHtml.setQueryType(queryType);
			queryResultHtml.setPageNo(Integer.valueOf(PageNo));

			mylogger.info("get 查询结果 of: subjectName/" + subjectName
					+ " subjectTitle/" + subjectTitle + " queryType/"
					+ queryType + " PageNo/" + PageNo);
			return queryResultHtml.generateReusultHtml();
		} catch (Exception ec) {
			mylogger.error("专题查询发生异常：" + ec.getMessage()+"\r\n"+ec.toString());
			
			return "专题查询发生异常</br>" + ec.getMessage();
		}
	}	

	/**dwr的server端的主要入口之一，根据查询的类型构造复杂的SQl进行查询。
	 * 结果为一段html，以string返回给javascript中的回调函数，用于更新页面。
	 * 用于关联导航查询，例如：根据证候查症状，此时的类型是症状，关键字是证候的名称。	
	 * @param subjectName 专题名称
	 * @param subjectTitle 专题编码
	 * @param queryType 导航查询的类型
	 * @param KeyWord  用于导航查询的关键字
	 * @param PageNo   页号
	 * @return
	 */
	public String NavigateQuery(String subjectName, String subjectTitle,
			String queryType, String KeyWord, String PageNo) {
		try {

			QueryResultHtml queryResultHtml = new QueryResultHtml();
			queryResultHtml.setSubjectName(subjectName);
			queryResultHtml.setSubjectTitle(subjectTitle);
			queryResultHtml.setQueryType(queryType);
			queryResultHtml.setKeyWord(KeyWord);
			queryResultHtml.setPageNo(Integer.valueOf(PageNo));

			mylogger.info("get 导航查询 of: subjectName/" + subjectName
					+ " subjectTitle/" + subjectTitle + " queryType/"
					+ queryType + " KeyWord/" + KeyWord + " PageNo/" + PageNo);
			return queryResultHtml.generateNavigateQueryHtml();
		} catch (Exception ec) {
			mylogger.error("专题查询发生异常：" + ec.getMessage()+"\r\n"+ec.toString());
			return "专题查询发生异常</br>" + ec.getMessage();
		}
	}
}
