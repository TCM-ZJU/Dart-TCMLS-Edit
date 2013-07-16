package cn.edu.zju.ccnt.TFGW.DBConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cn.edu.zju.ccnt.ConnectPool.ConnectionPool;

import oracle.sql.CLOB;

public class SingleDBConn {


	static Logger logger = Logger.getLogger(SingleDBConn.class.getName());

	/**
	 * 连接池
	 */
	private ConnectionPool connPool;
	
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
	
	/**
	 * 最大连接数
	 */
	private int maxConn;
	
	/**
	 * 最小连接数
	 */
	private int minConn;
	
	/**
	 * 连接最大空闲时间
	 */
	private int idleTime;

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
	
	public int getMaxConn(){
		return maxConn;
	}
	
	public void setMaxConn(int maxConn){
		this.maxConn = maxConn;
	}

	public int getMinConn(){
		return minConn;
	}
	
	public void setMinConn(int minConn){
		this.minConn = minConn;
	}
	
	public int getIdleTime(){
		return idleTime;
	}
	
	public void setIdleTime(int idleTime){
		this.idleTime = idleTime;
	}

	/**
	 *从配置文件server.property中读入数据库的链接配置信息
	 */
	private void setDBConnInfo(){
		Resource source = new ClassPathResource("server.properties");
		
		Properties prop = new Properties();
		try {
			prop.load(source.getURL().openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("打开配置文件\"server.properties\"失败！");
		}
		
		this.setJdbcDriver(prop.getProperty("singleDB.driver"));
		this.setJdbcUrl(prop.getProperty("singleDB.url"));
		this.setUser(prop.getProperty("singleDB.user"));
		this.setPwd(prop.getProperty("singleDB.password"));
		this.setMaxConn( Integer.parseInt( prop.getProperty("singleDB.maxConn") ) );
		this.setMinConn( Integer.parseInt( prop.getProperty("singleDB.minConn") ) );
		this.setIdleTime( Integer.parseInt( prop.getProperty("singleDB.idleTime") ) );
		
		connPool = new ConnectionPool(jdbcUrl, user, pwd, jdbcDriver, maxConn, minConn, idleTime);
	}

	/**根据传入的SQL执行数据库的SQl操作。
	 * @param sqlString 执行select的查询操作
	 * @return ArrayList<LinkedHashMap> 真个记录集保存为一个Arraylist，每次记录为一个LinkedHashMap，
	 * 其中每个LinkedHashMap的结构为(key:value)<-->(列名：值)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<LinkedHashMap> DBSelect(String sqlString) {
		try {
			Connection conn = connPool.getConn();
			Statement stmt = conn.createStatement();
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
			connPool.releaseConn(conn);
			return list;
		} catch (SQLException se) {
			logger.error(sqlString);
			logger.error(se.getMessage());
		}
		
		return null;
	}

	public String searchClob(String sql) throws IOException{
		String result = "";
		
		try {
			Connection conn = connPool.getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				CLOB clob = (CLOB)rs.getClob(1);
				result = clob.getSubString(1,(int)clob.length());
			}
			stmt.close();
			connPool.releaseConn(conn);
			
			return result;
		} catch (SQLException se) {
			logger.error(sql);
			logger.error(se.getMessage());
		} 
		
		return result;
	}
	
	public SingleDBConn() {
		setDBConnInfo();
	}
}
