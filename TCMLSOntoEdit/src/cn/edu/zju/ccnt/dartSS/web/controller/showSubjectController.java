package cn.edu.zju.ccnt.dartSS.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.edu.zju.ccnt.dartSS.web.tree.*;

/**
 * 系统一个最主要的Controller,接收index.html,然后调用buildSubjectTree类,
 *  产生专题树，返回的view经过decorato修饰后，添加上header.vm和footer.vm后，形成系统的主界面
 * @author zhm 
 */
public class showSubjectController implements Controller {

	static Logger logger = Logger.getLogger(showSubjectController.class
			.getName());

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=gb2312");
		PrintWriter out = response.getWriter();
		try {
			// 产生专题树
			String subjectTag=request.getParameter("subtag");
			return new buildSubjectTree(subjectTag).writeSubjectTree(out);

		} catch (Exception ec) {
			logger.error("生成专题列表发生异常:" + ec.getMessage() + "\r\n"
					+ ec.toString());
			out.print("<font color=red>生成专题列表发生异常，请联系管理员。</font></br>"
					+ ec.getMessage()+"<br/>"+ec.toString());
			return null;
		}
	}

}