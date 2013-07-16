package cn.edu.zju.ccnt.dartSS.web.innerHtml;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;
import cn.edu.zju.ccnt.dartSS.web.dwr.QueryNavigate;

/**查询结果的抽象工厂类
 * @author zhm
 * 
 */
public class QueryResultHtmlFactory {
	 /**
	 * 是否带入口词
	 */
	public boolean withRkc = false;
	/**
	 * 专题名称
	 */
	public String subjectName;

	/**
	 * 主题词名称
	 */
	public String subjectTitle;

	/**
	 * 
	 */
	public String sqlString;

	/**
	 * 查询类型
	 */
	public String queryType;

	/**
	 * 表的前缀
	 */
	public String tablePrefix = "";

	/**
	 * 每页的条目数量
	 */
	public int itemsInpage;

	/**
	 * 共有的记录条数
	 */
	public int totalRecordNum;

	/**
	 * 当前第几页
	 */
	public int currentPage;

	/**
	 * 总有页数
	 */
	public int totalPages;
	/**
	 * 导航查询的关键词
	 */
	public String KeyWord;

	// this method must be override by the subber class
	public String generateQueryResult() {
		return "";
	}

	public String getSqlString() {
		return sqlString;
	}

	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {		
		this.subjectTitle = subjectTitle;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	/**
	 * @return 设置导航条
	 */
	public String getPageHeader() {
		return new QueryNavigate().getQueryNavigeteInfo(subjectName,
				subjectTitle,queryType);
	}

	public String getTablePrefix() {
		if (tablePrefix == "")
			setTablePrefix();
		return tablePrefix;
	}

	public void setTablePrefix() {
		this.tablePrefix=new Dss_subjectDAO().GetTagByName(subjectName);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getItemsInpage() {
		return itemsInpage;
	}

	public void setItemsInpage(int itemsInpage) {
		this.itemsInpage = itemsInpage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages() {
		int tt=this.totalRecordNum/this.itemsInpage;
		if(tt*itemsInpage<totalRecordNum)
			totalPages=tt+1;
		else
			totalPages=tt;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	/**设置页码信息,用于疾病查询
	 * @return 
	 */
	public String SetPageCounterHtml() {
		String HtmlStr = "<span class=\"tableinfo\"> 共有" 
				+ this.getTotalRecordNum() 
				+ "条记录，每页"
				+ this.getItemsInpage() 
				+ "条，共有" 
				+ this.getTotalPages()
				+ "页，当前第" 
				+ (this.getCurrentPage()+1) + "页 ";
	
		if(currentPage>0)
			HtmlStr+="<span class=\"pageHint\" onclick=GetQueryResult(\""
			+ subjectName
			+ "\",\""
			+ subjectTitle
			+ "\",\""
			+ queryType
			+ "\",\"0\")>       第一页 </span>";
		if(currentPage>=1)
			HtmlStr+="<span class=\"pageHint\" onclick=GetQueryResult(\""
				+ subjectName
				+ "\",\""
				+ subjectTitle
				+ "\",\""
				+ queryType
				+ "\",\""+(currentPage-1)+"\")> 上一页 </span>";
		if((currentPage+2)<=totalPages)
			HtmlStr+="<span class=\"pageHint\" onclick=GetQueryResult(\""
				+ subjectName
				+ "\",\""
				+ subjectTitle
				+ "\",\""
				+ queryType
				+ "\",\""+(currentPage+1)+"\")> 下一页 </span>";
		if((currentPage+1)<totalPages)
			HtmlStr+="<span class=\"pageHint\" onclick=GetQueryResult(\""
				+ subjectName
				+ "\",\""
				+ subjectTitle
				+ "\",\""
				+ queryType
				+ "\",\""+(totalPages-1)+"\")> 最后一页 </span>";
		
		HtmlStr+="";
		return HtmlStr;
	}

	/**
	 * 设置页码信息,用于导航查询
	 * @return 
	 */
	public String SetPageCounterHtmlWithKeyword() {
		String HtmlStr = "<span class=\"tableinfo\">      共有" + this.getTotalRecordNum() + "条记录，每页"
				+ this.getItemsInpage() + "条，共有" + this.getTotalPages()
				+ "页，当前第" + (this.getCurrentPage()+1) + "页 ";
	
		if(currentPage>0)
			HtmlStr+="<span class=\"pageHint\" onclick=GetNavigateQuery(\""
			+ subjectName
			+ "\",\""
			+ subjectTitle
			+ "\",\""
			+ queryType
			+ "\",\""
			+KeyWord
			+"\",\"0\")>       第一页 </span>";
		if(currentPage>=1)
			HtmlStr+="<span class=\"pageHint\" onclick=GetNavigateQuery(\""
				+ subjectName
				+ "\",\""
				+ subjectTitle
				+ "\",\""
				+ queryType
				+ "\",\""
				+KeyWord
				+ "\"," +
						"\""+(currentPage-1)+"\")> 上一页 </span>";
		if((currentPage+2)<=totalPages)
			HtmlStr+="<span class=\"pageHint\" onclick=GetNavigateQuery(\""
				+ subjectName
				+ "\",\""
				+ subjectTitle
				+ "\",\""
				+ queryType
				+ "\",\""
				+KeyWord
				+ "\",\""+(currentPage+1)+"\")> 下一页 </span>";
		if((currentPage+1)<totalPages)
			HtmlStr+="<span class=\"pageHint\" onclick=GetNavigateQuery(\""
				+ subjectName
				+ "\",\""
				+ subjectTitle
				+ "\",\""
				+ queryType
				+ "\",\""
				+KeyWord
				+ "\",\""+(totalPages-1)+"\")> 最后一页 </span>";
		
		HtmlStr+="";
		return HtmlStr;
	}
	public int getTotalRecordNum() {
		return totalRecordNum;
	}

	public void setTotalRecordNum(int totalRecordNum) {
		this.totalRecordNum = totalRecordNum;
		this.setTotalPages();
	}

	public String getKeyWord() {
		return KeyWord;
	}

	public void setKeyWord(String keyWord) {
		KeyWord = keyWord;
	}
	
	public void InintialTitleHints(){
		
	}

	public boolean isWithRkc() {
		return withRkc;
	}

	public void setWithRkc(boolean withRkc) {
		this.withRkc = withRkc;
	}
}
