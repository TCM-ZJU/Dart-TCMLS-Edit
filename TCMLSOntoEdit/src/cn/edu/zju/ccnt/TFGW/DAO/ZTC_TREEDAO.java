package cn.edu.zju.ccnt.TFGW.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.StructDBConnection;
import cn.edu.zju.ccnt.TFGW.object.ZTC_TREE;


public class ZTC_TREEDAO {
	private static Logger logger = Logger.getLogger(ZTC_TREEDAO.class.getName());
	
	private String buildJibFirstSql(){
		String sql = "select * " +
					 "from ZTC_TREE " +
					 "where regexp_like(MN,'^((F)|(TF)|(C)|(CF))[^\\.]*[^+]$')";
		
		return sql;
	}
	
	private String buildJMZFirstSql(){
		String sql = "select * " +
		 "from ZTC_TREE " +
		 "where regexp_like(MN,'^((F)|(TF)|(C)|(CF))[^\\.]*[^+]$')";

		return sql;		
	}
	
	private String buildNextSql(String mn){
		String sql = "select * " +
					 "from ZTC_TREE " +
					 "where regexp_like(MN,'^" + mn + "\\.[^\\.]*[^+]$')";
		
		return sql;
	}
	
	private String buildLeavesSql(String mn){
		String sql = "select mhchi " +
					 "from ZTC_TREE " +
					 "where regexp_like(MN,'^" + mn + "\\..*[^+]')";
		
		return sql;
	}
	
	/**
	 * 通过类名得到该类下所有的实例
	 * @param mn 类名
	 * @return
	 */
	public ArrayList<String> getJibByMn(String mn){
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		ArrayList<LinkedHashMap> resultArray = conn.DBSelect(buildLeavesSql(mn));
		ArrayList<String> jibList = new ArrayList<String>();
		for(int counter = 0; counter < resultArray.size(); counter++){
			if(resultArray.get(counter).get("MHCHI") != null){
				jibList.add(resultArray.get(counter).get("MHCHI").toString());
			}
		}
		
		return jibList;
	}
	
	public ArrayList<ZTC_TREE> searchNextLevel(String mn){
		return search(buildNextSql(mn));
	}
	
	public ArrayList<ZTC_TREE> searchFirstLevel(String DBName){
		if(DBName.equals("JMZ_JB")){
			return search(buildJMZFirstSql());
		}
		else if(DBName.equals("C_JIB")){
			return search(buildJibFirstSql());
		}
		
		return null;
	}
	
	public ArrayList<ZTC_TREE> search(String sql){
		logger.info("查询" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<ZTC_TREE> jibList = new ArrayList<ZTC_TREE>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZTC_TREE jib = new ZTC_TREE();
	
			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");
	
			if (ResultArray.get(counter).get("MN") != null)
				jib.setMN(ResultArray.get(counter).get("MN").toString());
			else
				jib.setMN("&nbsp;");
	
			if (ResultArray.get(counter).get("MHCHI") != null)
				jib.setMHCHI(ResultArray.get(counter).get("MHCHI").toString());
			else
				jib.setMHCHI("&nbsp;");
	
			if (ResultArray.get(counter).get("MH") != null)
				jib.setMH(ResultArray.get(counter).get("MH").toString());
			else
				jib.setMH("&nbsp;");
			
			if (ResultArray.get(counter).get("FL") != null)
				jib.setFL(ResultArray.get(counter).get("FL").toString());
			else
				jib.setFL("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
}
