package cn.edu.zju.ccnt.dartSS.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.support.ResourceBundleMessageSource;


/**用来截获*.pic的servlet，用于显示原文文献图片
 * @author zhm 
 * 
 */
public class ShowImage extends HttpServlet {


	private static final Logger logger = Logger.getLogger(ShowImage.class);

	private static final String CONTENT_TYPE = "image/*";

	private int len = 10 * 1024 * 1024; // 定义字符数组长度

	private String jdbcDriver;

	private String jdbcUrl;

	private String user;

	private String pwd;

	private Connection conn;

	private Statement stmt;

	/**
	 * 得倒数据库链接的一些信息
	 */
	private void setDBConnectInfo() {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();

		rbms.setBasename("databaseinfo");

		this.setJdbcDriver(rbms.getMessage("driverClassName", null, null));
		this.setJdbcUrl(jdbcUrl = rbms.getMessage("url", null, null));
		this.setUser(rbms.getMessage("username", null, null));
		this.setPwd(rbms.getMessage("password", null, null));

		logger.debug("Database Connection Infomation:\n" + "DataBase driver:"
				+ jdbcDriver + "\n" + "DataBase uri:" + jdbcUrl + "\n"
				+ "Username and PassWord:" + user + ":" + pwd);
	}

	/**
	 * 打开数据库链接
	 */
	public void OpenDBConnection() {

		try {
			setDBConnectInfo();
			Class.forName(jdbcDriver).newInstance();
			conn = DriverManager.getConnection(jdbcUrl, user, pwd);
			stmt = conn.createStatement();
		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}

	/**
	 * 从数据库中得倒图片的数据流
	 * @param picId
	 * @return
	 */
	public InputStream GetStreamFromDS(String picId) {
		String SqlString="";
		try {
			
			SqlString = "select PIC from PIC_LCYJ where ID=" + picId;
			oracle.jdbc.OracleResultSet rs;
			rs = (oracle.jdbc.OracleResultSet) stmt.executeQuery(SqlString);

			if (rs.next()) {
				return (InputStream) rs.getBlob(1).getBinaryStream();
			} else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(SqlString);
			logger.error(e.getMessage());
			return null;
		}
	
	}

	/**
	 * 输出流之后，关闭数据库链接
	 */
	public void CloseDBConnection() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * Get方法的处理
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			logger.info("begin to invoke show image servlet");
			int picId = Integer.parseInt(request.getParameter("picId"));
			
			response.setContentType(CONTENT_TYPE);
			//返回在流中被标记过的位置 
			response.reset(); // 返回在流中被标记过的位置 
			// int len=in.available();//得到文件大小
			OutputStream toClient = response.getOutputStream();
			InputStream in;			
			OpenDBConnection();
			in=GetStreamFromDS(String.valueOf(picId));
			byte[] P_Buf = new byte[len];
			int i;
			while ((i = in.read(P_Buf)) != -1) {
				logger.debug("i is " + i);
				toClient.write(P_Buf, 0, i);
			}
			
			in.close();
			toClient.flush(); // 强制刷新缓冲区
			toClient.close();
			
			logger.info("finish to invoke show image servlet");
			return;
		}
		catch(Exception ec)
		{
			logger.error(ec.getMessage());			
		}
		finally{
			CloseDBConnection();
		}
		
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}


