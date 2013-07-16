package cn.edu.zju.ccnt.dartSS.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.*;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.sql.ResultSetMetaData;

/**执行数据库的连接、查询、修改等活动
 * @author zhm
 * 
 */
public class DataBaseActivity {

	static Logger logger = Logger.getLogger(DataBaseActivity.class.getName());

	/**
	 * 数据库驱动
	 */
	private String jdbcDriver;

	/**
	 * 数据库的URL
	 */
	private String jdbcUrl;

	/**
	 * 用户名
	 */
	private String user;

	/**
	 * 密码
	 */
	private String pwd;
	
	private Connection conn=null;

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

	public String getUser() {
		return user;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}	

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 *从配置文件databaseinfo.property中读入数据库的链接配置信息
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
	
	private Connection getConnection(){
		if(this.conn!=null)
			return conn;
		else
		{
			try {
				this.setDBConnectInfo();
				Class.forName(jdbcDriver).newInstance();
				conn = DriverManager.getConnection(jdbcUrl, user, pwd);
			    return conn;				
			} catch (ClassNotFoundException e) {			
				logger.error(e.getMessage());
			} catch (SQLException se) {
				logger.error(se.getMessage());
			} catch (IllegalAccessException iae) {
				logger.error(iae.getMessage());
			} catch (InstantiationException ie) {
				logger.error(ie.getMessage());
			}
			return null;
		}
	}

	/**根据传入的SQL执行数据库的SQl操作。
	 * @param sqlString 执行select的查询操作
	 * @return ArrayList<LinkedHashMap> 真个记录集保存为一个Arraylist，每天记录为一个LinkedHashMap，
	 * 其中每个LinkedHashMap的结构为(key:value)<-->(列名：值)
	 */
	public ArrayList<LinkedHashMap> DBSelect(String sqlString) {
		try {			
			Connection myConn=this.getConnection();
			Statement stmt = myConn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			ResultSetMetaData metaData = rs.getMetaData();

			ArrayList<LinkedHashMap> list = new ArrayList<LinkedHashMap>();
			while (rs.next()) {
				LinkedHashMap v = new LinkedHashMap();
				for (int i = 1; i <= metaData.getColumnCount(); i++)
					v.put(metaData.getColumnLabel(i), rs.getObject(i));
				list.add(v);
			}
			stmt.close();			
			return list;
		
		} catch (SQLException se) {
			logger.error(sqlString);
			se.printStackTrace();	
			logger.error(se.getMessage());		
		}
		return null;

	}

	/**
	 * 根据传入的SQL，执行数据库的update操作
	 * @param sqlString 执行update的SQL语句 
	 * @return >=0:成功 =-1:失败
	 */
	public int DBExecuteUpdate(String sqlString) {
		try {
			
			Connection myConn=this.getConnection();
			Statement stmt = conn.createStatement();
			int UpdateRows = stmt.executeUpdate(sqlString);

			stmt.close();		
			return UpdateRows;
		} catch (SQLException se) {
			logger.error(sqlString);
			logger.error(se.getStackTrace().toString());
			se.printStackTrace();		
		}
		return -1;

	}

	public DataBaseActivity() {
		
	}
	
	/*protected void finalize()throws Throwable{
		if(this.conn!=null)
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}*/
}
