package cn.edu.zju.ccnt.dartSS.web.tree;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;
import cn.edu.zju.ccnt.dartSS.object.Subject;

/**
 * 画专题树
 * 
 * @author zhm
 * 
 */
public class buildSubjectTree {

	private String subjectTag = "";
	private String subjectName = "";
	private String headerPicName = "";
	private String introPicName = "";

	public buildSubjectTree(String subTag) {
		this.setSubjectTag(subTag);
		this.setSubjectName(subjectTag);
		this.setHeaderPicName();
		this.setIntroPicName();
	}

	static Logger logger = Logger.getLogger(buildSubjectTree.class.getName());

	/**
	 * 取得丄1�7个专预1�7
	 * 
	 * @return
	 */
	public Subject GetSuject() {
		Dss_subjectDAO dss_subjectDao = new Dss_subjectDAO();
		Subject subject = dss_subjectDao.GetSubjectByTag(getSubjectTag());
		if (subject != null)
			logger.info("画专题树:" + subject.getS_NAME());
		else
			logger.warn("没有编码丄1�7" + getSubjectTag() + "的专预1�7,请联系管理员");
		return subject;
	}

	/**
	 * 取得丄1�7个专题的扄1�7有主题词
	 * 
	 * @param queryCondition
	 * @return
	 */
	private ArrayList<String> getSubTitles(String queryCondition) {
		Dss_subjectDAO dss_subjectDao = new Dss_subjectDAO();
		ArrayList<String> titles = dss_subjectDao
				.getZhuticilist(queryCondition);
		return titles;
	}
	
	/**
	 * 取得丄1�7个专题的扄1�7有主题词,直接在专题的_JB表中查询
	 * 
	 * @param queryCondition
	 * @return
	 */
	private ArrayList<String> getSubTitlesFromSubtable(String queryCondition) {
		Dss_subjectDAO dss_subjectDao = new Dss_subjectDAO();
		ArrayList<String> titles = dss_subjectDao
				.getZhuticiFromSubtable(queryCondition);
		return titles;
	}

	/**
	 * 画搜索框
	 * 
	 * @return
	 */
	public String DrawSearchBar() {

		String OutputString = "var newchild="
				+ "document.createElement(\"<div class=\\\"search\\\"></div>\") ;"
				+ "newchild.innerHTML=\"<div><input type=\\\"text\\\" "
				+ "size=20 border=1 id=searchtitle>"
				+ "<input type=\\\"button\\\" name=\\\"search\\\" "
				+ "value=\\\"search\\\" onclick=subjectTitleSearch('"
				+ this.getSubjectName() + "',searchtitle.value)></div>\";"
				+ "htmlBody.appendChild(newchild);";
		return OutputString;
	}

	/**
	 * 画操作按钮栏
	 * 
	 * @return
	 */
	public String DrawButtonBar() {
		// String OutputString = "var newchild="
		// + "document.createElement(\"<div class=\\\"buttunbar\\\" ></div>\")
		// ;"
		// + "newchild.innerHTML=\"<table border=0 BGCOLOR=\\\"C8C8C8\\\" "
		// + " width=\\\"93%\\\" cellpadding=\\\"0\\\" cellspacing=\\\"1\\\">"
		// + "<tr>"
		// + "<td><image class=imageIcon hight=16 width=16 title=\\\"收起扄1�7有树结点\\\"
		// "
		// + "src=\\\"./public/images/treeminus.gif\\\" "
		// + "onclick=collapseAllNodes()>"
		// + "</img></td>"
		// + "<td><image class=imageIcon hight=16 width=16 title=\\\"展开扄1�7有树结点\\\"
		// "
		// + "src=\\\"./public/images/treeplus.gif\\\" "
		// + "onclick=ExpandAllNodes()>"
		// + "</img></td>"
		// + "<td><a href=index.htm><image class=imageIcon title=\\\"刷新\\\" "
		// + "src=\\\"./public/images/refresh.gif\\\" " + "></a>"
		// + "</img></td>"
		// + "<td><image class=imageIcon title=\\\"专题管理\\\" "
		// + "src=\\\"./public/images/edititem.gif\\\" "
		// + "onclick=subjectManagement(\\\"用户登录\\\")>" + "</img></td>"
		//
		// + "<td width=60%></td>" + "</tr>" + "</table>\";"
		// + "htmlBody.appendChild(newchild);";

		String OutputString = "var newchild="
				+ "document.createElement(\"<div class=\\\"buttunbar\\\" ></div>\") ;"
				+ "newchild.innerHTML=\""
				+ "<table border=0 BGCOLOR=\\\"C8C8C8\\\" "
				+ " width=\\\"93%\\\" cellpadding=\\\"0\\\" cellspacing=\\\"1\\\">"
				+ "<tr>"
				+ "<td><image class=imageIcon title=\\\"刷新\\\" "
				+ "src=\\\"./public/images/refresh.bmp\\\" "
				+ "onclick=collapseAllNodes()>"
				+ "</img><a href=subject.html?subtag="
				+ this.subjectTag
				+ ">刷新</a>"
				+ "</td>"
				+ "<td><image class=imageIcon title=\\\"返回主页\\\" "
				+ "src=\\\"./public/images/home.bmp\\\" ><a href=index.htm>返回主页</a>"
				+ "</img>" + "</td>" + "</tr>" + "</table>\";"
				+ "htmlBody.appendChild(newchild);";
		return OutputString;
	}

	/**
	 * 专题树的根节炄1�7
	 * 
	 * @return
	 */
	public String DarwSujectRootNode() {
		String OutputString = "var newchild="
				+ "document.createElement(\"<div></div>\") ;"
				+ "newchild.innerHTML=\"<img align=\\\"absmiddle\\\" "
				+ "src=\\\"./public/images/Tminus.gif\\\" onclick=ExpandRootNode()></image>专题列表\";"
				+ "htmlBody.appendChild(newchild);	";
		return OutputString;
	}

	/**
	 * 画专题结点，即除了根节点之外的其他节点�1�7�1�7
	 * 
	 * @return
	 */
	public String DarwSujectNodes() {
		String OutputString = "";
		Subject subject = GetSuject();
		if (subject == null)
			return "不存在编码为" + getSubjectTag() + "的专预1�7,请联系管理员";

		try {
			String S_name = subject.getS_NAME();
			String S_tag = subject.getS_TAG();
			String S_note = subject.getNotes();
			logger.error("S_name:"+S_name+" "+"S_tag:"+S_tag+" "+"S_note:"+S_note);
			String queryCondition = subject.getCondition();
			String addedTitlesStr = "";

			if (subject.getAddedtitles() != null)
				addedTitlesStr = subject.getAddedtitles();

			String subjectId = "subject_" + S_tag;

			
			OutputString += DrawingSubject(subjectId, S_name, addedTitlesStr,
					queryCondition, true,S_note);
		} catch (Exception ex) {
			logger.error(ex.getMessage() + "\r\n" + ex.toString());
		}

		return OutputString;
	}

	/**
	 * 画一个专题节炄1�7
	 * 
	 * @param subjectId
	 *            专题ID，形式为 “subject_ 1�7+专题编码
	 * @param S_name
	 *            专题名称
	 * @param addedTitlesStr
	 *            该专题下用户自己添加主题评1�7
	 * @param queryCondition
	 *            该专题下主题词的产生条件
	 * @param isLastSubjectNode
	 *            是否是最后一个专题节炄1�7
	 * @return
	 */
	public String DrawingSubject(String subjectId, String S_name,
			String addedTitlesStr, String queryCondition,
			boolean isLastSubjectNode,String S_Note) {
		
		String OutputString = "var newBranchSpan="
				+ "document.createElement(\"<div class=\\\"branch\\\" "
				+ "style=\\\"display:block;\\\"></div>\") ;"
				+ "newBranchSpan.id=\"" + subjectId + "\";";

		if (!isLastSubjectNode) {
			OutputString += "newBranchSpan.innerHTML=\"<image  "
					+ "align=\\\"absmiddle\\\" src=\\\"./public/images/T.gif\\\" ";
		} else
			OutputString += "newBranchSpan.innerHTML=\"<image  "
					+ "align=\\\"absmiddle\\\" src=\\\"./public/images/empty.gif\\\" ";

		OutputString += "onclick=showBranch(\\\""
				+ subjectId
				+ "\\\")></image>"
				+ "<image id=\\\"changeableIcon\\\" align=\\\"absmiddle\\\"  "
				+ "src=\\\"./public/images/Tminus.gif\\\" "
				+ "onclick=showBranch(\\\""
				+ subjectId
				+ "\\\")>"
				+ "</image><span class=\\\"leaf\\\" onclick=subjectIntroduction(\\\""
				+ S_name + "\\\")>" + S_name + "的主题词列表</span>\";"
				+ "htmlBody.appendChild(newBranchSpan);	";

		OutputString += DrawSubjectTitleNodes(subjectId, S_name,
				queryCondition, addedTitlesStr, isLastSubjectNode,S_Note);
		return OutputString;
	}

	/**
	 * 画专题下的主题词节点
	 * 
	 * @param subjectId
	 *            专题ID
	 * @param SubjectName
	 *            专题名称
	 * @param queryCondition
	 *            该专题下主题词的产生条件
	 * @param addedTitlesStr
	 *            该专题下用户自己添加主题评1�7
	 * @param isLastSubjectNode
	 *            是否是最后一个专题节炄1�7
	 * @return
	 */
	public String DrawSubjectTitleNodes(String subjectId, String SubjectName,
			String queryCondition, String addedTitlesStr,
			boolean isLastSubjectNode,String S_Note) {

		logger.error("S_Note:"+S_Note);
		String OutputString = "";
		try
		{
			
			String subTitleId = subjectId + "_";// +主题词的名称
			ArrayList<String> titles =null;
			
			if(S_Note.toLowerCase().trim().equals("ads") || (SubjectName.toLowerCase().trim().equals("zwb"))){
				logger.error("single added subject");
				titles = getSubTitlesFromSubtable(queryCondition);				
			}
				
			else
			{
				logger.error("normal subject");
				titles = getSubTitles(queryCondition);
			}
				
	
			String atitles[] = addedTitlesStr.split("\\|");
			for (int i = 0; i < atitles.length; i++)
				if (!atitles[i].toString().trim().equals(""))
					titles.add(atitles[i].toString());
	
			logger.info("主题词数量：" + titles.size());
			// 画该专题下的主题词结炄1�7
			for (int i = 0; i < titles.size(); i++) {
	
				OutputString += "var newleafspan=document.createElement(\"<span class=\\\"leaf\\\"  "
						+ " style=\\\"display:block;\\\" ></span>\");"
						+ "newleafspan.id=\""
						+ subTitleId
						+ titles.get(i).toString()
						+ "\";"
						+ "newleafspan.innerHTML=\"";
	
				if (!isLastSubjectNode)
					OutputString += "<img  align=\\\"absmiddle\\\" src=\\\"./public/images/I.gif\\\"></img>";
				else
					OutputString += "<img  align=\\\"absmiddle\\\" src=\\\"./public/images/empty.gif\\\"></img>";
	
				if (i < (titles.size() - 1))
					OutputString += "<img align=\\\"absmiddle\\\"  src=\\\"./public/images/T.gif\\\"></img>";
				else
					OutputString += "<img align=\\\"absmiddle\\\"  src=\\\"./public/images/L.gif\\\"></img>";
	
				OutputString += "<span id=leaftext onclick=queryNavigation(\\\""
						+ SubjectName + "\\\",\\\"" + titles.get(i).toString()
						+ "\\\") >" + titles.get(i).toString() + "</span>\";" +
	
						"newBranchSpan.appendChild(newleafspan);";
			}
			
		}
		catch(Exception ex){
			String errStr="产生专题节点时发生错误："+"\r\n"+ex.getMessage() + "\r\n" + ex.toString();
			logger.error(errStr);
		}
		return OutputString;
	}

	/**
	 * 画专题树，依次调用DrawSearchBar()，DrawButtonBar()，DarwSujectRootNode()，DarwSujectNodes()〄1�7
	 * 
	 * @param out
	 * @return
	 * @throws Exception
	 */
	public ModelAndView writeSubjectTree(PrintWriter out) throws Exception {
		String OutputString = "<script language=\"JavaScript\"> "
				+ "var htmlBody=document.getElementById(\"mySubjectTree\"); ";
		OutputString += this.DrawSearchBar();
		OutputString += this.DrawButtonBar();
		// OutputString += this.DarwSujectRootNode();		
		OutputString += this.showSubjectHeaderPic();
		OutputString += this.showSubjectIntroPic();
		OutputString += this.DarwSujectNodes();
		OutputString += "</script>";

		logger.debug(OutputString);
		out.println(OutputString);
		return null;

	}

	/**
	 * 产生丄1�7段js，用于显现当前专题的标题图片
	 * 
	 * @return
	 */
	public String showSubjectHeaderPic() {
		String scriptString = "";
		scriptString += "	var headerpart = document.getElementById(\"headerpart\");"
				+ "	for (var i = 0; i < headerpart.childNodes.length; i++) {"
				+ "		headerpart.removeChild(headerpart.childNodes[i]);"
				+ "	}"
				+ "	var headerpic = document.createElement(\""
				+ "<div style=\\\"background-image: url(./public/images/subjectpics/"
				+ this.headerPicName
				+ ");\\\" id=\\\"top\\\">headerPic</div>\");"
				+ "	headerpart.appendChild(headerpic);";
		return scriptString;
	}

	/**
	 * 产生丄1�7段js，用于显示当前专题的专题介绍图片
	 * 
	 * @return
	 */
	public String showSubjectIntroPic() {
		String scriptString = "";
		scriptString += "	var intorPart = document.getElementById(\"mainPart\");"
				+ "	for (var i = 0; i < intorPart.childNodes.length; i++) {"
				+ "		intorPart.removeChild(intorPart.childNodes[i]);"
				+ "	}"
				+ "	var introPic = document.createElement(\""
				+ "<img align=\\\"top\\\" src=\\\"./public/images/subjectpics/"
				+ this.introPicName
				+ "\\\"></img>\");"
				+ "	intorPart.appendChild(introPic);";
		return scriptString;
	}

	public String getSubjectTag() {
		return subjectTag;
	}

	public void setSubjectTag(String subjectTag) {
		this.subjectTag = subjectTag;
	}

	public String getSubjectName() {
		if (this.subjectName == null)
			this.setSubjectName(subjectTag);
		return subjectName;
	}

	public void setSubjectName(String subjectTag) {
		Dss_subjectDAO dss_subjectDao = new Dss_subjectDAO();
		String sName = dss_subjectDao.GetNameByTag(subjectTag.toUpperCase());
		if (sName != null)
			this.subjectName = sName;
		else
			this.subjectName = "";

	}

	public String getHeaderPicName() {
		return headerPicName;
	}

	public void setHeaderPicName() {
		Dss_subjectDAO dss_subjectDao = new Dss_subjectDAO();
		String hpName = dss_subjectDao.GetHeaderPicBySubjectName(subjectName
				.toUpperCase());
		if (hpName != null)
			this.headerPicName = hpName;
		else
			this.headerPicName = "";
	}

	public String getIntroPicName() {
		return this.introPicName;

	}

	public void setIntroPicName() {
		Dss_subjectDAO dss_subjectDao = new Dss_subjectDAO();
		String ipName = dss_subjectDao.GetPicnameBySubjectName(subjectName
				.toUpperCase());
		if (ipName != null)
			this.introPicName = ipName;
		else
			this.introPicName = "";
	}

}
