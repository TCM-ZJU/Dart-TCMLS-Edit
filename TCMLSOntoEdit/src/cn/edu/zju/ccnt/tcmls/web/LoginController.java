package cn.edu.zju.ccnt.tcmls.web;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.edu.zju.ccnt.tcmls.Connection.OracleConnection;

public class LoginController implements Controller {
	/**
	 * Logger for this class
	 */
//	private static final Log logger = LogFactory.getLog(LoginController.class);
	private final static Logger logger = Logger.getLogger(LoginController.class);

	private int privilege = -1;
	

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		privilege = -1;
		response.setCharacterEncoding("UTF-8");
//		Connection conn = OracleConnection.getConn();
		Connection conn = new OracleConnection().getConn();
		String sql = "select * from Account where Username = ? and Password = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, request.getParameter("username"));
		stmt.setString(2, request.getParameter("password"));
		try{
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				privilege = rs.getInt("Privilege");
			}
			else
				logger.debug("Wrong username or password by " + request.getParameter("username") + "@" + request.getParameter("password"));
			Map<String, String> map = new HashMap<String, String>();
			map.put("Username", request.getParameter("username"));
			map.put("Privilege", new Integer(privilege).toString());
			xmlOutput(response, MapToXML(map, "login"));
			rs.close();
		}catch(Exception e){
			logger.error("log in error: " + request.getParameter("username"));
		}finally{
			if(null != stmt)
				stmt.close();
			if(null != conn)
				conn.close();
		}
		return null;		
	}
	
	private Element MapToXML(Map<String, String> map, String pre) throws NumberFormatException, SQLException{
		Element root = new Element("TCMLS");
		root.setAttribute("Type", pre);
		for(Iterator<String> it=map.keySet().iterator(); it.hasNext(); ){
			String key = it.next();
			String value = map.get(key);
			Element ele = new Element(key).addContent(value);
			root.addContent(ele);
		}
		return root;
	}

	private void xmlOutput(HttpServletResponse response, Element ele) throws IOException{
		Document tcmls = new Document(ele);
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(outputter.getFormat().setEncoding("UTF-8"));
//		outputter.output(tcmls, System.out);
	    outputter.output(tcmls, response.getWriter());
	}
}
