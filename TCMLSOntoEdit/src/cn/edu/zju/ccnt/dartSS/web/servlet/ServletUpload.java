package cn.edu.zju.ccnt.dartSS.web.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.zju.ccnt.dartSS.db.dao.Dss_subjectDAO;
import cn.edu.zju.ccnt.dartSS.smart.SmartRequest;
import cn.edu.zju.ccnt.dartSS.smart.SmartUpload;

/** 一个用来向服务器上传专题介绍图片的servlet
 * @author zhm
 *
 */
public class ServletUpload extends HttpServlet{
	private ServletConfig config;
	private  Logger mylogger=Logger.getLogger(ServletUpload.class.getName());
	
	/** 
	 *  Init the servlet
	 */
	final public void init(ServletConfig config) throws ServletException{
			this.config=config;
	}
	
	final public ServletConfig getServletConfig(){
		return this.config;
	}
	
	/* 使用Get方法不能上传文件
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		mylogger.info("使用Get方法不能上传文件");
		response.setContentType("text/html; charset=gb2312");
		PrintWriter out=response.getWriter();
		
		out.println("<html>" +
				"<read>" +
				"<style>body{font:10pt verdana;color:#0000FF}</style>" +
				"<title>上传图片文件 中医药专题数据库系统</title>" +
				"</head>" +
				"<body>" +
				"<div>上传专题介绍图片:</div><hr/>" +
				"<div>HTML中form的方法必须为POST.</div>" +
				"</body>" +
				"</html>");		
	}
	
	/* 从post方法中接收文件数据流。
	 * 在上传文件时，如果使用组件上传的方式，需要在form中添加 multipart/form-data,并且必须是使用post的方式。
	 * 这样将无法直接得倒form中传递的数据。好在SmartUpload已经提供了相应的方法。
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		mylogger.info("上传专题介绍图片");
		response.setContentType("text/html; charset=gb2312");
		PrintWriter out=response.getWriter();
		
		String htmlStr="<html>" +
				"<read>" +
				"<style>body{font:10pt verdana;color:#0000FF}</style>" +
				"<title>上传图片文件 中医药专题数据库系统</title>" +
				"</head>" +
				"<body>" +
				"<div>上传专题介绍图片:</div><hr>";
		int  count=0;
		SmartUpload mySmartUpload =new SmartUpload();
		try{
				mySmartUpload.initialize(config,request,response);
				mySmartUpload.upload();
				/*
				 * 在上传文件时，如果使用组件上传的方式，需要在form中添加 multipart/form-data,并且必须是使用post的方式。
				 * 这样将无法直接得倒form中传递的数据。好在SmartUpload已经提供了相应的方法。
				 */				
				SmartRequest sr=mySmartUpload.getRequest();
				String subjectName=sr.getParameter("zhuantiList");
				String titlePic=mySmartUpload.getFiles().getFile(0).getFileName();
				String IntroPic=mySmartUpload.getFiles().getFile(1).getFileName();
				count=mySmartUpload.save("/public/images/subjectpics");
				
				htmlStr+="<div>专题名称："+subjectName+"</div><br>" +
						"<div>图片文件："+IntroPic+"</div><br>";
				
				if(count>0){
					htmlStr+="<div>1.文件已经上传</div><br>";
					if(UpdateSubjectIntr(subjectName,titlePic,IntroPic))
						htmlStr+="<div>2.已经将图片保存到专题</div><br>";
					else 
						htmlStr+="<div>2.将图片保存到专题失败</div><br>";
				}
				else{
					htmlStr+="<div>1.文件上传失败</div><br>";
				}
		}
		catch(Exception ex){
			htmlStr+="Unable to upload the file.<br>Error:"+ex.toString();
		}
		htmlStr+="</body></html>";
		out.println(htmlStr);
		return;			
	}
	
	/**
	 * 更新专题的介绍图片 在新创建专题时，专题的介绍图片为空。 如果为空，则后面的程序将使用默认值作为专题的介绍图片。
	 * 
	 * @param subjectName
	 *            专题名称
	 * @param picName
	 *            专题图片的名称
	 * @return
	 */
	private Boolean UpdateSubjectIntr(String subjectName, String  headerpic,String picName) {
		mylogger.info("保存专题的介绍图片：专题名称/" + subjectName + ",专题介绍图片/" + picName);
		Dss_subjectDAO subjectDAO = new Dss_subjectDAO();
		if (subjectDAO.updateSubjectIntro(subjectName,headerpic, picName) > 0)
			return true;
		else
			return false;

	}
}

