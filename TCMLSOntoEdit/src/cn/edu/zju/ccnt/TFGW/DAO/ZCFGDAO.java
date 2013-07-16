package cn.edu.zju.ccnt.TFGW.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.object.YJYA;
import cn.edu.zju.ccnt.TFGW.object.ZCFG;
import cn.edu.zju.ccnt.TFGW.object.ZDFZ;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.DataReader;

public class ZCFGDAO {
	static Logger logger = Logger.getLogger(ZCFGDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<ZCFG> search(String[] params){
		String sqlDisease = "select distinct YIBMC from TFGW_ZCFG where YIBMC like '%" + params[0] + "%'";
		String sql = "select * from TFGW_ZCFG where YIBMC in(" + sqlDisease + ")";
		boolean flag = false;
		
		if(!params[0].equals("")){
			flag = true;
		}
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<ZCFG>();
		}
		
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<ZCFG> jibList = new ArrayList<ZCFG>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZCFG jib = new ZCFG();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("YIBMC") != null)
				jib.setYIBMC(ResultArray.get(counter).get("YIBMC").toString());
			else
				jib.setYIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			if (ResultArray.get(counter).get("PIZWH") != null)
				jib.setPIZWH(ResultArray.get(counter).get("PIZWH").toString());
			else
				jib.setPIZWH("&nbsp;");

			if (ResultArray.get(counter).get("PIZJG") != null)
				jib.setPIZJG(ResultArray.get(counter).get("PIZJG").toString());
			else
				jib.setPIZJG("&nbsp;");

			if (ResultArray.get(counter).get("FABRQ") != null)
				jib.setFABRQ(ResultArray.get(counter).get("FABRQ").toString());
			else
				jib.setFABRQ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
	
	/**
	 * Search ZCFG by disease names
	 * @param jibNames The given disease names.
	 * @param num The index of the result.
	 * @param count The count we need to search.
	 * @return The result set
	 */
	public ArrayList<ZCFG> searchByJibNames(String jibNames, int num, int count){
		String sql = "select TFGW_ZCFG.*, ROWNUM RN from TFGW_ZCFG where YIBMC in " + jibNames;
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + count) +
			  ")" +
			  "where RN > " + num;	
		
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<ZCFG> jibList = new ArrayList<ZCFG>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZCFG jib = new ZCFG();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("YIBMC") != null)
				jib.setYIBMC(ResultArray.get(counter).get("YIBMC").toString());
			else
				jib.setYIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			if (ResultArray.get(counter).get("PIZWH") != null)
				jib.setPIZWH(ResultArray.get(counter).get("PIZWH").toString());
			else
				jib.setPIZWH("&nbsp;");

			if (ResultArray.get(counter).get("PIZJG") != null)
				jib.setPIZJG(ResultArray.get(counter).get("PIZJG").toString());
			else
				jib.setPIZJG("&nbsp;");

			if (ResultArray.get(counter).get("FABRQ") != null)
				jib.setFABRQ(ResultArray.get(counter).get("FABRQ").toString());
			else
				jib.setFABRQ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
	
	/**
	 * Search ZCFG by single disease name
	 * @param jibName The given disease name.
	 * @param num The index of the result.
	 * @param count The count we need to search.
	 * @return The result set
	 */
	public ArrayList<ZCFG> searchByJibName(String jibName, int num, int count){
		String sql = "select TFGW_ZCFG.*, ROWNUM RN from TFGW_ZCFG where YIBMC like '" + jibName + "' ";
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + count) +
			  ")" +
			  "where RN > " + num;	
		
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<ZCFG> jibList = new ArrayList<ZCFG>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZCFG jib = new ZCFG();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("YIBMC") != null)
				jib.setYIBMC(ResultArray.get(counter).get("YIBMC").toString());
			else
				jib.setYIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			if (ResultArray.get(counter).get("PIZWH") != null)
				jib.setPIZWH(ResultArray.get(counter).get("PIZWH").toString());
			else
				jib.setPIZWH("&nbsp;");

			if (ResultArray.get(counter).get("PIZJG") != null)
				jib.setPIZJG(ResultArray.get(counter).get("PIZJG").toString());
			else
				jib.setPIZJG("&nbsp;");

			if (ResultArray.get(counter).get("FABRQ") != null)
				jib.setFABRQ(ResultArray.get(counter).get("FABRQ").toString());
			else
				jib.setFABRQ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
	
	//通过多重条件来搜索疾�?
	public ArrayList<ZCFG> search(String[] params, int num){
		String sqlDisease = "select distinct YIBMC from TFGW_ZCFG where YIBMC like '%" + params[0] + "%'";
		String sql = "select TFGW_ZCFG.*, ROWNUM RN from TFGW_ZCFG where YIBMC in(" + sqlDisease + ")";
		boolean flag = false;
		
		if(!params[0].equals("")){
			flag = true;
		}
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<ZCFG>();
		}
		
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + 20) +
			  ")" +
			  "where RN > " + num;	
		
		logger.info("查询�?" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<ZCFG> jibList = new ArrayList<ZCFG>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZCFG jib = new ZCFG();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("YIBMC") != null)
				jib.setYIBMC(ResultArray.get(counter).get("YIBMC").toString());
			else
				jib.setYIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			if (ResultArray.get(counter).get("PIZWH") != null)
				jib.setPIZWH(ResultArray.get(counter).get("PIZWH").toString());
			else
				jib.setPIZWH("&nbsp;");

			if (ResultArray.get(counter).get("PIZJG") != null)
				jib.setPIZJG(ResultArray.get(counter).get("PIZJG").toString());
			else
				jib.setPIZJG("&nbsp;");

			if (ResultArray.get(counter).get("FABRQ") != null)
				jib.setFABRQ(ResultArray.get(counter).get("FABRQ").toString());
			else
				jib.setFABRQ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	//通过多重条件来搜索疾病
	public int getSearchCount(String[] params){
		String sqlDisease = "select distinct YIBMC from TFGW_ZCFG where YIBMC like '%" + params[0] + "%'";
		String sql = "select count(*) COUNT from TFGW_ZCFG where YIBMC in(" + sqlDisease + ")";
		boolean flag = false;
		
		if(!params[0].equals("")){
			flag = true;
		}
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return 0;
		}
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}
	
	/**
	 * Search disease count by disease name.
	 * @param names Given disease names.
	 * @return The return result.
	 */
	public int getSearchCountOfJibNames(String names){
		String sql = "select count(*) COUNT from TFGW_ZCFG where YIBMC in " + names;

		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}
	
	/**
	 * Search disease count by single disease name.
	 * @param name Given disease name.
	 * @return The return result.
	 */
	public int getSearchCountOfJibName(String name){
		String sql = "select count(*) COUNT from TFGW_ZCFG where YIBMC like '" + name + "' ";

		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}
	
	/**
	 * Search disease by given attribute.
	 * @param attrName The attribute's name in database. 
	 * @param attrValue The attribute's value.
	 * @param num The number of the attribute.
	 * @return The data result, one attribute a time.
	 */
	public DataReader searchByAttr(String attrName, String attrValue, int num){
		String sql = " select TFGW_ZCFG.*,ROWNUM RN from TFGW_ZCFG" +
				     " where " + attrName + " like '" + attrValue + "'";
		sql = "select * from (" +
			  	sql + 
			  ")" +
			  "where RN = " + num;			
		
		logger.info("查询:" + sql);
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		DataReader result = null;
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZCFG jib = new ZCFG();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("YIBMC") != null)
				jib.setYIBMC(ResultArray.get(counter).get("YIBMC").toString());
			else
				jib.setYIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			if (ResultArray.get(counter).get("PIZWH") != null)
				jib.setPIZWH(ResultArray.get(counter).get("PIZWH").toString());
			else
				jib.setPIZWH("&nbsp;");

			if (ResultArray.get(counter).get("PIZJG") != null)
				jib.setPIZJG(ResultArray.get(counter).get("PIZJG").toString());
			else
				jib.setPIZJG("&nbsp;");

			if (ResultArray.get(counter).get("FABRQ") != null)
				jib.setFABRQ(ResultArray.get(counter).get("FABRQ").toString());
			else
				jib.setFABRQ("&nbsp;");
		
			result = jib;
			break;
		}
		
		return result;
	}

	/**
	 * Search disease by given attribute.
	 * @param attrName The attribute's name in database. 
	 * @param attrValue The attribute's value.
	 * @param num The number of the attribute.
	 * @return The data result count
	 */
	public int searchCountByAttr(String attrName, String attrValue){
		String sql = " select count(ID) NUMSUM from TFGW_ZCFG" +
				     " where " + attrName + " like '" + attrValue + "'";		
		
		logger.info("查询:" + sql);
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		
		int sum = 0;
		if (ResultArray.get(0).get("NUMSUM") != null){
			sum = Integer.parseInt(ResultArray.get(0).get("NUMSUM").toString());
		}	
		
		return sum;
	}
}
