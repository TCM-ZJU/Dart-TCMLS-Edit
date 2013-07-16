package cn.edu.zju.ccnt.TFGW.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.object.ZYBFZ;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.DiseaseInf;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;

public class ZYBFZDAO {
	static Logger logger = Logger.getLogger(ZYBFZDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<ZYBFZ> search(String[] params){
		String sqlDisease = "select distinct JIBMC from TFGW_ZYBFZ where JIBMC like '%" + params[0] + "%' or WENT like '%" + params[0] + "%'";
		String sql = "select * from TFGW_ZYBFZ where JIBMC in(" + sqlDisease + ")";
		boolean flag = false;
		
		if(!params[0].equals("")){
			flag = true;
		}
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<ZYBFZ>();
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);
		
		ArrayList<ZYBFZ> jibList = new ArrayList<ZYBFZ>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZYBFZ jib = new ZYBFZ();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("JIBMC") != null)
				jib.setJIBMC(ResultArray.get(counter).get("JIBMC").toString());
			else
				jib.setJIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
	
	//通过多重条件来搜索疾病
	public ArrayList<ZYBFZ> searchByJibNames(String jibNames, int num){
		String sql = "select TFGW_ZYBFZ.*, ROWNUM RN from TFGW_ZYBFZ where JIBMC in " + jibNames;
		sql = "select * from (" +
				  	sql + 
				  	" and ROWNUM <= " + (num + 20) +
				  ")" +
			  "where RN > " + num;	
		
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<ZYBFZ> jibList = new ArrayList<ZYBFZ>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZYBFZ jib = new ZYBFZ();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("JIBMC") != null)
				jib.setJIBMC(ResultArray.get(counter).get("JIBMC").toString());
			else
				jib.setJIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	//通过多重条件来搜索疾病
	public ArrayList<ZYBFZ> search(String[] params, int num){
		String sqlDisease = "select distinct JIBMC from TFGW_ZYBFZ where JIBMC like '%" + params[0] + "%' or WENT like '%" + params[0] + "%'";
		String sql = "select TFGW_ZYBFZ.*, ROWNUM RN from TFGW_ZYBFZ where JIBMC in(" + sqlDisease + ")";
		boolean flag = false;
		
		if(!params[0].equals("")){
			flag = true;
		}
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<ZYBFZ>();
		}
		
		sql = "select * from (" +
				  	sql + 
				  	" and ROWNUM <= " + (num + 20) +
				  ")" +
			  "where RN > " + num;	
		
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<ZYBFZ> jibList = new ArrayList<ZYBFZ>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZYBFZ jib = new ZYBFZ();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("JIBMC") != null)
				jib.setJIBMC(ResultArray.get(counter).get("JIBMC").toString());
			else
				jib.setJIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}	
	
//	通过多重条件来搜索疾病
	public ArrayList<DiseaseInf> searchDistinctDisease(String[] params, int num, int count){
		ArrayList<DiseaseInf> diseaseInf = new ArrayList<DiseaseInf>();
		String sqlDisease = "select distinct JIBMC from TFGW_ZYBFZ where JIBMC like '%" + params[0] + "%' or WENT like '%" + params[0] + "%'";
		String sql = "select JIBMC, count(JIBMC) JBMC_NUM from TFGW_ZYBFZ where JIBMC in(" + sqlDisease + ")";
		boolean flag = false;
		
		if(!params[0].equals("")){
			flag = true;
		}
		
		sql += " group by JIBMC " +
		   	   " order by JBMC_NUM desc ";
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return diseaseInf;
		}
		
		sql = " select JIBMC, JBMC_NUM " +
			  " from " +
			  " ( " +
			  	" select JIBMC, JBMC_NUM, rownum rn " +
			  	" from " +
			  		" ( " + sql + ")" +
			  	" where rownum <= " + (num + count) + 
			  " ) " +
			  " where RN > " + num;	
		
		
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("JIBMC") != null)
				diseaseInf.add(new DiseaseInf( ResultArray.get(counter).get("JIBMC").toString(), 
											   Integer.parseInt(ResultArray.get(counter).get("JBMC_NUM").toString()),
											   "常见职业病防治数据库",
												"-1" ));
		}
		
		return diseaseInf;
	}	
	
	//通过多重条件来搜索疾病
	public int getSearchCount(String[] params){
		String sqlDisease = "select distinct JIBMC from TFGW_ZYBFZ where JIBMC like '%" + params[0] + "%' or WENT like '%" + params[0] + "%'";
		String sql = "select COUNT(*) COUNT from TFGW_ZYBFZ where JIBMC in(" + sqlDisease + ")";
		boolean flag = false;
		
		if(!params[0].equals("")){
			flag = true;
		}
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return 0;
		}
		
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}	
	
	/**
	 * Get distinct disease count
	 * @param params The params used to search the disease
	 * @return The distinct disease count
	 */	
	public int getDisticntSearchCount(String[] params){
		String sqlDisease = "select distinct JIBMC from TFGW_ZYBFZ where JIBMC like '%" + params[0] + "%' or WENT like '%" + params[0] + "%'";
		String sql = "select COUNT(distinct JIBMC) COUNT from TFGW_ZYBFZ where JIBMC in(" + sqlDisease + ")";
		boolean flag = false;
		
		if(!params[0].equals("")){
			flag = true;
		}
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return 0;
		}
		
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}	
	
	//通过多重条件来搜索疾病
	public int getSearchCountOfJibNames(String names){
		String sql = "select COUNT(*) COUNT from TFGW_ZYBFZ where JIBMC in " + names;

		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}	
	
	/**
	 * Search disease by given attribute.
	 * @param attrName The attribute's name in database. 
	 * @param attrValue The attribute's value.
	 * @param num The number of the attrubute.
	 * @return The data result, one colum a time.
	 */
	public DataReader searchByAttr(String attrName, String attrValue, int num){
		String sql = " select TFGW_ZYBFZ.*, ROWNUM RN from TFGW_ZYBFZ" +
				     " where " + attrName + " like '" + attrValue + "'";
		sql = "select * from (" +
				  	sql + 
				  ")" +
			  "where RN = " + num;	
		
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		DataReader result = null;
		
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZYBFZ jib = new ZYBFZ();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("JIBMC") != null)
				jib.setJIBMC(ResultArray.get(counter).get("JIBMC").toString());
			else
				jib.setJIBMC("&nbsp;");

			if (ResultArray.get(counter).get("WENT") != null)
				jib.setWENT(ResultArray.get(counter).get("WENT").toString());
			else
				jib.setWENT("&nbsp;");
			
			result = jib;
			break;
		}
		
		return result;
	}
	
	/**
	 * Search disease by given attribute.
	 * @param attrName The attribute's name in database. 
	 * @param attrValue The attribute's value.
	 * @param num The number of the attrubute.
	 * @return The data result count
	 */
	public int searchCountByAttr(String attrName, String attrValue){
		String sql = " select count(ID) NUMSUM from TFGW_ZYBFZ " +
					 " where " + attrName + " like '" + attrValue + "'";
	
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		logger.info("查询:" + sql);
		ResultArray = conn.DBSelect(sql);
		
		int sum = 0;
		if (ResultArray.get(0).get("NUMSUM") != null){
			sum = Integer.parseInt(ResultArray.get(0).get("NUMSUM").toString());
		}	
		
		return sum;
	}
}
