package cn.edu.zju.ccnt.TFGW.dwr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DAO.jmz.JMZ_JBDAO;
import cn.edu.zju.ccnt.TFGW.DAO.ZTC_TREEDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_jibDAO;
import cn.edu.zju.ccnt.TFGW.DBConnect.JibDBConn;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.DBConnect.StructDBConnection;
import cn.edu.zju.ccnt.TFGW.innerHTML.CreateTreeHTML;


public class Tree{
	static Logger logger = Logger.getLogger(Tree.class.getName());
	
	private String prefix;
	private String DBName;
	private int maxLevel;
	private ArrayList<String> nodesList = new ArrayList<String>();
	
	private ArrayList<String> GetData(String sql, String nodeName){	
		ApplicationContext factory = GetFactory.getFactory(); 
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		if(DBName.equals("C_JIB")){
			JibDBConn conn = (JibDBConn)factory.getBean("jibDBConn");
			ResultArray = conn.DBSelect(sql);
		}
		else if(DBName.equals("JMZ_JB")){
			StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
			ResultArray = conn.DBSelect(sql);
		}
		else{
			SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
			ResultArray = conn.DBSelect(sql);
		}	

		ArrayList<String> dataList = new ArrayList<String>();
 		for(int counter = 0; counter < ResultArray.size(); counter++) {
			dataList.add(ResultArray.get(counter).get(nodeName).toString());
		}
		
		return dataList;			
	}
	
	/**
	 * 根据输入的结点信息和展开等级生成相应的查询语句
	 * @param lev 需要展开的等级
	 * @param nodes 从根节点到当前要展开结点的结点内容
	 * @return
	 */
	private String buildSql(int lev, ArrayList<String> nodesInf){
		if(nodesList.size() <= 0){
			this.loadProp(nodesInf.get(0));
		}
		
		String sql = "select distinct " + nodesList.get(lev) + " from " + DBName;
		
		if(lev > 1){
			sql = sql + " where ";
		}
		
		for(int i = 1; i < lev; i++){
			if( i == 1){
				sql = sql + nodesList.get(i) + " = '" + nodesInf.get(i) + "' ";
			}
			else{
				sql = sql + " and " + nodesList.get(i) + " = '" + nodesInf.get(i) + "' ";				
			}
		}
		
		if(lev <= 1){
			sql += " where " + nodesList.get(lev) + " is not null";
		}
		else{
			sql += " and " + nodesList.get(lev) + " is not null";
		}
		
		sql += " order by " + nodesList.get(lev) + " asc";
		
		return sql;
	}
	
/*	private String buildJibSql(String DBName, String mn, int lev){
		String sql;
		
		if(DBName.equals("C_JIB") || DBName.equals("JIB")){
			if(lev == 1){
				sql = "select ";
			}
		}
	}*/
	
	/**
	 * 利用主题词表进行类树展开
	 * @param lev
	 * @param DBName
	 * @param mn
	 * @return
	 */
	public String openJibNode(String lev, String DBName, String mn){
		int level = Integer.parseInt(lev);
		loadProp(DBName);
		System.out.println("熊一只");
		ApplicationContext factory = GetFactory.getFactory();
		CreateTreeHTML treeHTML = (CreateTreeHTML)factory.getBean("createTreeHTML");
		ZTC_TREEDAO ztcDAO = (ZTC_TREEDAO)factory.getBean("ZTC_TREEDAO");
		
		treeHTML.loadProp(prefix);
		
		try{
			if(level == maxLevel){
				if(DBName.equals("C_JIB")){
					String sql = "select distinct JBMC " +
								 "from C_JIB " +
								 "where JBMC in " +
								 getSet(ztcDAO.getJibByMn(mn));
					C_jibDAO jibDAO = (C_jibDAO)factory.getBean("C_JIBDAO");
					
					return treeHTML.createLeaves(jibDAO.searchJibNames(sql));
				}
				else if(DBName.equals("JMZ_JB")){
					String sql = "select distinct JBMC " +
								 "from JMZ_JB " +
								 "where JBMC in " +
								 getSet(ztcDAO.getJibByMn(mn));
					
					JMZ_JBDAO jibDAO = (JMZ_JBDAO)factory.getBean("JMZ_JBDAO");
					
					return treeHTML.createLeaves(jibDAO.searchJibNames(sql));
				}
				
				return "";
			}
			else{
				if(level == 1){
					return treeHTML.createTreeNodesByZTC(level, ztcDAO.searchFirstLevel(DBName));
				}
				else{			
					return treeHTML.createTreeNodesByZTC(level, ztcDAO.searchNextLevel(mn));
				}
			}
		}catch(Exception e){
			logger.error("在利用主题词表进行类树展开时发生错误，错误信息为：" + e.toString());
			return "";
		}
	}
	
	/**
	 * 导航数的入口函数，与客户端交互，动态生成导航树
	 * @param lev 需要展开结点所在等级（root为第一级，以此往下递增）
	 * @param nodesInf 沿途展开的结点的信息
	 * @return 返回展开的结点树
	 */
	public String openNode(String lev, ArrayList<String> nodesInf){
		int level = Integer.parseInt(lev);
		loadProp(nodesInf.get(0));
		String sql = buildSql(level, nodesInf);
		String result = "";
		logger.info("查询：" + sql);
		
		try{
			ApplicationContext factory = GetFactory.getFactory();
			CreateTreeHTML treeHTML = (CreateTreeHTML)factory.getBean("createTreeHTML");
			treeHTML.loadProp(prefix);
			result = treeHTML.CreateTree(level, this.GetData(sql, nodesList.get(level)), nodesInf);
		}catch (Exception e){
			logger.error("在类树展开时出现错误！错误信息为:" + e.toString());
			return "";
		}
		
		return result;
	}

	/**
	 * 根据配置文件进行初始话
	 * @param pre
	 */
	public void loadProp(String pre){
		Resource url = new ClassPathResource("tree.properties");
		Properties prop = new Properties();
		prefix = pre;
		
		try {
			prop.load(url.getURL().openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("读取资源文件tree.properties失败!");
		}
		
		DBName = prop.getProperty(pre + ".DBname");
		maxLevel = Integer.parseInt(prop.getProperty(pre + ".level"));
		nodesList.add(DBName);
		for(int i = 1; i <= maxLevel; i++){
			nodesList.add(prop.getProperty(pre + ".level" + i));
		}
	}
	
	private String getSet(ArrayList<String> list){
		if(list.size() <= 0){
			return "('')";
		}
		StringBuffer result = new StringBuffer("('" + list.get(0) + "'");
		
		for(int i = 1; i < list.size();i++){
			result.append(",'" + list.get(i) + "'");
		}
		result.append(")");
		
		return result.toString();
	}
	
	private String getSet(String[] list){
		if(list.length <= 0){
			return "('')";
		}
		StringBuffer result = new StringBuffer("('" + list[0] + "'");
		
		for(int i = 1; i < list.length;i++){
			result.append(",'" + list[i] + "'");
		}
		result.append(")");
		
		return result.toString();
	}
}