package cn.edu.zju.ccnt.dartSS.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.web.dwr.QueryResult;

/**
 * this servlet is used to search from JMZ table group
 * the url mapping is /Disease?disease=diseasename
 * Servlet implementation class for Servlet: JMZDiseaseQuery
 * 
 */
public class JMZDiseaseQuery extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(JMZDiseaseQuery.class.getName());
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public JMZDiseaseQuery() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String diseaseName="";
		 diseaseName =new String(request.getParameter("disease").getBytes("ISO8859-1"),"UTF-8");
/*		 logger.info("diseaseName1:"+diseaseName);
		 String diseaseName2 =request.getParameter("disease");
		 logger.info("diseaseName2:"+diseaseName2);
		 String diseaseName3 =new String(request.getParameter("disease").getBytes("UTF-8"),"UTF-8");
		 logger.info("diseaseName3:"+diseaseName3);
		 String diseaseName4 =new String(request.getParameter("disease").getBytes("GB2312"),"UTF-8");
		 logger.info("diseaseName4:"+diseaseName4);
		 
		diseaseName=diseaseName.substring(0, diseaseName.length()-1);*/
		logger.info("item:"+diseaseName);		
		QueryResult queryResult = new QueryResult();
		String urlStr = queryResult.getQueryResult("急门诊", diseaseName, "基本信息", "0");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>"
						+ "<head>"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"
						+ "<script type='text/javascript' src='/TFGWProject/dwr/util.js'></script>"
						+ "<script type='text/javascript' src='/TFGWProject/dwr/engine.js'></script>"
						+ "<script type='text/javascript' src='/TFGWProject/dwr/interface/QueryNavigate.js'></script>"
						+ "<script type='text/javascript' src='/TFGWProject/dwr/interface/QueryResult.js'></script>"
						+ "<script type='text/javascript' src='/TFGWProject/dwr/interface/SubjectIntroduction.js'></script>"
						+ "<script type='text/javascript' src=\"./public/js/tree.js\"></script>"
						+ "<script type='text/javascript' src=\"./public/js/domOperation.js\"></script>"
						+ "<link rel=\"stylesheet\" href=\"./public/dartss.css\" type=\"text/css\">"
						+ "<link rel=\"stylesheet\" href=\"./public/css.css\" type=\"text/css\">"
						+ "<link rel=\"stylesheet\" href=\"./public/index.css\" type=\"text/css\">"
						+ "<title>中医药专题数据库系统</title>"
						+ "</head>"
						+ "<body><table width=\"100%\" align=\"top\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
						+ "<tr width=\"100%\">"
						+ "<td id=\"mainPart\" height=\"100%\" valign=\"top\"	class=\"ms-navframe\">"
						+ urlStr + "</span></td></tr></table></body>"
						+ "</html>");
		return;
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}

