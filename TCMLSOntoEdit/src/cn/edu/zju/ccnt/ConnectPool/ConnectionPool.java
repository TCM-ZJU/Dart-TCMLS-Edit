/**
 * 浙江大学CCNT网格实验室
 * @author zxg "zhang0925@gmail.com"
 * 2007-11-27
 * To read configuration about the total ontology, concerning slots & clses. 
 */

package cn.edu.zju.ccnt.ConnectPool;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;

import org.apache.log4j.Logger;

public class ConnectionPool {
	static Logger logger = Logger.getLogger(ConnectionPool.class.getName());
	private String driver;
	private String url;
	private String user;
	private String pwd;
	private int maxConn;
	private int minConn;
	
	//The time left too release the connection
	private int timeCount;
	
	//The time intervel to release the connection
	private int idleTime;
	
	//The Timer used to release unused connection
	private Timer timer = new Timer(false);
	
	//The conn number in connection pool(include the conn has been distributed)
	private int connNum;

	private LinkedList<Connection> connPool = new LinkedList<Connection>();
	
	public ConnectionPool(String url, String userName, String pwd, String jdbcDriver, int max, int min, int idle){
		this.url = url;
		this.user = userName;
		this.pwd = pwd;
		this.maxConn = max;
		this.minConn = min;
		this.idleTime = idle;
		this.timeCount = idleTime;
		this.driver = jdbcDriver;
		
		this.connNum = 0;
		
		timer.schedule(new TimerTask(){
			
			public void run() {
				timeCount--;
				
				if(timeCount <= 0){
					//Reset the Timer
					timeCount = idleTime;
					
					if(connPool.size() > minConn){
						try {
							connPool.poll().close();
							connNum--;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							connNum--;
						}
					}
				}
			}
		}, 0, 1000);
	}
	
	//Just for test!!!
	/*
	public ConnectionPool(){
		timer.schedule(new TimerTask(){
			
			public void run() {
				System.out.print("time pause!");
			}
		}, 0, 1000);
	}
	
	public static void main(String args[]){
		ConnectionPool connPool = new ConnectionPool();
		
		System.out.println("here!");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public synchronized Connection getConn(){
		//Reset the timer
		timeCount = idleTime;
		
		//If the connectionPool is full and all connection have been used, return null.
		if(connNum >= maxConn){
			return null;
		}
	
		if(!connPool.isEmpty()){
			return connPool.poll();
		}
		else{
			try {
				Class.forName(driver).newInstance();
				Connection conn = DriverManager.getConnection(url, user, pwd);
				
				connNum++;
				return conn;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				logger.info(e.getStackTrace());
				logger.info(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				logger.info(e.getStackTrace());
				logger.info(e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				logger.info(e.getStackTrace());
				logger.info(e);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info(e.getStackTrace());
				logger.info(e);
			}	
		}
		
		return null;
	}
	
	public synchronized boolean releaseConn(Connection conn){
		
		try {
			if(conn.isClosed()){
				connNum--;
				return false;
			}
			else{
				connPool.offer(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void finalize(){
		Iterator iterator = connPool.iterator();
		
		while(iterator.hasNext()){
			try {
				((Connection)iterator.next()).close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info(e);
			}
		}
	}
}


