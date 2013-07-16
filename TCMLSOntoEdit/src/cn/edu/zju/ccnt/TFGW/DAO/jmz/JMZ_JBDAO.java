package cn.edu.zju.ccnt.TFGW.DAO.jmz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.DBConnect.StructDBConnection;
import cn.edu.zju.ccnt.TFGW.object.jmz.JMZ_JB;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.DataReader;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.DiseaseInf;

/**
 * @author 张小刚
 *
 */
public class JMZ_JBDAO {
	static Logger logger = Logger.getLogger(JMZ_JBDAO.class.getName());	
	
	/**
	 * 根据指定参数来搜索疾病
	 * @param params
	 * @return
	 */
	public ArrayList<JMZ_JB> search(String[] params){
		String sqlDisease = "select distinct JBMC from JMZ_JB where JBMC like '%" + params[0] + "%'";
		String sqlZZ = "select distinct JBMC from JMZ_JB where ZZ like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from JMZ_JB where ZH like '%" + params[3] + "%'";
		String sql = "select * from JMZ_JB where JBMC in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlDisease + ")";
			flag = true;
		}
		//急门诊数据库暂不利用病因进行搜索
/*		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlBY + ")";
		}*/
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZZ + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZH + ")";
		}	
		sql += ")";
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<JMZ_JB>();
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ArrayList<JMZ_JB> jibList = new ArrayList<JMZ_JB>();
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return jibList;
		}
		
		logger.info("查询:" + sql);
		ResultArray = conn.DBSelect(sql);
		
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			JMZ_JB jib = new JMZ_JB();

			if (ResultArray.get(counter).get("JB_ID") != null)
				jib.setJB_ID(ResultArray.get(counter).get("JB_ID").toString());
			else
				jib.setJB_ID("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJBMC(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJBMC("&nbsp;");

			if (ResultArray.get(counter).get("YFB") != null)
				jib.setYFB(ResultArray.get(counter).get("YFB").toString());
			else
				jib.setYFB("&nbsp;");

			if (ResultArray.get(counter).get("BFZ") != null)
				jib.setBFZ(ResultArray.get(counter).get("BFZ").toString());
			else
				jib.setBFZ("&nbsp;");
			
			if (ResultArray.get(counter).get("ZH") != null)
				jib.setZH(ResultArray.get(counter).get("ZH").toString());
			else
				jib.setZH("&nbsp;");
			
			if (ResultArray.get(counter).get("ZZ") != null)
				jib.setZZ(ResultArray.get(counter).get("ZZ").toString());
			else
				jib.setZZ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * 根据指定参数来搜索疾病
	 * @param params
	 * @return
	 */
	public ArrayList<JMZ_JB> searchByJibNames(String jibNames, int num){
		String sql = "select JMZ_JB.*, ROWNUM RN  from JMZ_JB where JBMC in " + jibNames;
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + 20) +
			  ")" +
			  "where RN > " + num;	
		
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<JMZ_JB> jibList = new ArrayList<JMZ_JB>();
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			JMZ_JB jib = new JMZ_JB();

			if (ResultArray.get(counter).get("JB_ID") != null)
				jib.setJB_ID(ResultArray.get(counter).get("JB_ID").toString());
			else
				jib.setJB_ID("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJBMC(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJBMC("&nbsp;");

			if (ResultArray.get(counter).get("YFB") != null)
				jib.setYFB(ResultArray.get(counter).get("YFB").toString());
			else
				jib.setYFB("&nbsp;");

			if (ResultArray.get(counter).get("BFZ") != null)
				jib.setBFZ(ResultArray.get(counter).get("BFZ").toString());
			else
				jib.setBFZ("&nbsp;");
			
			if (ResultArray.get(counter).get("ZH") != null)
				jib.setZH(ResultArray.get(counter).get("ZH").toString());
			else
				jib.setZH("&nbsp;");
			
			if (ResultArray.get(counter).get("ZZ") != null)
				jib.setZZ(ResultArray.get(counter).get("ZZ").toString());
			else
				jib.setZZ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * 通过给定的sql语句搜索疾病
	 * @param sql
	 * @return
	 */
	public ArrayList<String> searchJibNames(String sql){
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection) factory.getBean("structDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<String> jibList = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("JBMC") != null)
				jibList.add(ResultArray.get(counter).get("JBMC").toString());
		}
		
		return jibList;		
	}

	/**
	 * Search JMZ disease by the given parameters
	 * @param params The parameters used to search the disease names.
	 * @param num The num where the search locate.
	 * @param count The number of disease name need to return.
	 * @return The disease names satisfy the given parameters.
	 */
	public ArrayList<DiseaseInf> searchDistinctDisease(String[] params, int num, int count){
		ArrayList<DiseaseInf> diseaseInf = new ArrayList<DiseaseInf>();
		String sql = "select JBMC, count(JBMC) JBMC_NUM  from JMZ_JB where JBMC in(";
		String sqlDisease = "select distinct JBMC from JMZ_JB where JBMC like '%" + params[0] + "%'";
		//	String sqlBY = "";
		String sqlZZ = "select distinct JBMC from JMZ_JB where ZZ like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from JMZ_JB where ZH like '%" + params[3] + "%'";
		
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlDisease + ")";
			flag = true;
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZZ + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZH + ")";
		}	
		sql += " ) " +
			   " group by JBMC " +
			   " order by JBMC_NUM desc ";
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<DiseaseInf>();
		}
		
		sql = " select JBMC, JBMC_NUM " +
			  " from " +
			  " ( " +
			  	" select JBMC, JBMC_NUM, rownum rn " +
			  	" from " +
			  		" ( " + sql + ")" +
			  	" where rownum <= " + (num + count) + 
			  " ) " +
			  " where RN > " + num;	
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection) factory.getBean("structDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询:" + sql);
		ResultArray = conn.DBSelect(sql);
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("JBMC") != null)
				diseaseInf.add(new DiseaseInf( ResultArray.get(counter).get("JBMC").toString(), 
											   Integer.parseInt(ResultArray.get(counter).get("JBMC_NUM").toString()),
											   "发热数据库",
											   "-1" ));
		}
		
		return diseaseInf;		
	}
	
	
	//通过多重条件来搜索疾病
	public ArrayList<JMZ_JB> search(String[] params, int num){
		String sqlDisease = "select distinct JBMC from JMZ_JB where JBMC like '%" + params[0] + "%'";
	//	String sqlBY = "";
		String sqlZZ = "select distinct JBMC from JMZ_JB where ZZ like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from JMZ_JB where ZH like '%" + params[3] + "%'";
		String sql = "select JMZ_JB.*, ROWNUM RN  from JMZ_JB where JBMC in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlDisease + ")";
			flag = true;
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZZ + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZH + ")";
		}	
		sql += ")";
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<JMZ_JB>();
		}
		
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + 20) +
			  ")" +
			  "where RN > " + num;	
		
		logger.info("查询�?" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);
		ArrayList<JMZ_JB> jibList = new ArrayList<JMZ_JB>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			JMZ_JB jib = new JMZ_JB();

			if (ResultArray.get(counter).get("JB_ID") != null)
				jib.setJB_ID(ResultArray.get(counter).get("JB_ID").toString());
			else
				jib.setJB_ID("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJBMC(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJBMC("&nbsp;");

			if (ResultArray.get(counter).get("YFB") != null)
				jib.setYFB(ResultArray.get(counter).get("YFB").toString());
			else
				jib.setYFB("&nbsp;");

			if (ResultArray.get(counter).get("BFZ") != null)
				jib.setBFZ(ResultArray.get(counter).get("BFZ").toString());
			else
				jib.setBFZ("&nbsp;");
			
			if (ResultArray.get(counter).get("ZH") != null)
				jib.setZH(ResultArray.get(counter).get("ZH").toString());
			else
				jib.setZH("&nbsp;");
			
			if (ResultArray.get(counter).get("ZZ") != null)
				jib.setZZ(ResultArray.get(counter).get("ZZ").toString());
			else
				jib.setZZ("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * Get disease count
	 * @param params The params used to search the disease
	 * @return The result count,include the disease with the same name.
	 */
	public int getSearchCount(String[] params){
		String sqlDisease = "select distinct JBMC from JMZ_JB where JBMC like '%" + params[0] + "%'";
	//	String sqlBY = "";
		String sqlZZ = "select distinct JBMC from JMZ_JB where ZZ like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from JMZ_JB where ZH like '%" + params[3] + "%'";
		String sql = "select count(*) count from JMZ_JB where JBMC in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlDisease + ")";
			flag = true;
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZZ + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZH + ")";
		}	
		sql += ")";
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return 0;
		}
		
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		return Integer.parseInt(conn.DBSelect(sql).get(0).get("COUNT").toString());
	}
	
	/**
	 * Get distinct disease count
	 * @param params The params used to search the disease
	 * @return The result count,include the disease with the same name.
	 */
	public int getDistinctSearchCount(String[] params){
		String sqlDisease = "select distinct JBMC from JMZ_JB where JBMC like '%" + params[0] + "%'";
	//	String sqlBY = "";
		String sqlZZ = "select distinct JBMC from JMZ_JB where ZZ like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from JMZ_JB where ZH like '%" + params[3] + "%'";
		String sql = "select count(distinct JBMC) count from JMZ_JB where JBMC in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlDisease + ")";
			flag = true;
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZZ + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlZH + ")";
		}	
		sql += ")";
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return 0;
		}
		
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		return Integer.parseInt(conn.DBSelect(sql).get(0).get("COUNT").toString());
	}
	
//	通过多重条件来搜索疾�?
	public int getSearchCount(String names){
		String sql = "select count(*) count from JMZ_JB where JBMC in " + names;
		
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		return Integer.parseInt(conn.DBSelect(sql).get(0).get("COUNT").toString());
	}
	
	/**
	 * Search disease by given attribute.
	 * @param attrName The attribute's name in database. 
	 * @param attrValue The attribute's value.
	 * @param num The number of the attrubute.
	 * @return The data result, one colum a time.
	 */
	public DataReader searchByAttr(String attrName, String attrValue, int num){
		String sql = "select JMZ_JB.*, rownum RN from JMZ_JB " +
					 " where " + attrName + " like '" + attrValue + "'";
		sql = "select * from (" +
			  	sql + 
			  ")" +
			  "where RN = " + num;	
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询:" + sql);
		ResultArray = conn.DBSelect(sql);
		DataReader result = null;
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			JMZ_JB jib = new JMZ_JB();

			if (ResultArray.get(counter).get("JB_ID") != null)
				jib.setJB_ID(ResultArray.get(counter).get("JB_ID").toString());
			else
				jib.setJB_ID("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJBMC(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJBMC("&nbsp;");

			if (ResultArray.get(counter).get("YFB") != null)
				jib.setYFB(ResultArray.get(counter).get("YFB").toString());
			else
				jib.setYFB("&nbsp;");

			if (ResultArray.get(counter).get("BFZ") != null)
				jib.setBFZ(ResultArray.get(counter).get("BFZ").toString());
			else
				jib.setBFZ("&nbsp;");
			
			if (ResultArray.get(counter).get("ZH") != null)
				jib.setZH(ResultArray.get(counter).get("ZH").toString());
			else
				jib.setZH("&nbsp;");
			
			if (ResultArray.get(counter).get("ZZ") != null)
				jib.setZZ(ResultArray.get(counter).get("ZZ").toString());
			else
				jib.setZZ("&nbsp;");
			
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
		String sql = " select count(*) NUMSUM from JMZ_JB " +
					 " where " + attrName + " like '" + attrValue + "'";
	
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		StructDBConnection conn = (StructDBConnection)factory.getBean("structDBConn");
		
		logger.info("查询:" + sql);
		ResultArray = conn.DBSelect(sql);
		
		int sum = 0;
		if (ResultArray.get(0).get("NUMSUM") != null){
			sum = Integer.parseInt(ResultArray.get(0).get("NUMSUM").toString());
		}	
		
		return sum;
	}
}
