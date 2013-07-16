package cn.edu.zju.ccnt.TFGW.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.LoadConf;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.object.Expert;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.DataReader;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.DiseaseInf;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.ExpertInf;

public class ExpertDAO {
	static Logger logger = Logger.getLogger(ExpertDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<Expert> search(String[] params){
		String sqlName = "select distinct XINGM from BJWSZY_RENYUAN where XINGM like '%" + params[0] + "%'";
		String sqlSection = "select distinct XINGM from BJWSZY_RENYUAN where SUOZKS like '%" + params[1] + "%'";
		String sqlDisease = "select distinct XINGM from BJWSZY_RENYUAN where ZHUZJB like '%" + params[2] + "%'";
		String sqlTime = "select distinct XINGM from BJWSZY_RENYUAN where MENZSJ like '%" + params[3] + "%'";
		String sql = "select * from BJWSZY_RENYUAN where XINGM in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlName + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlSection + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<Expert>();
		}
		
		logger.info("查询：" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<Expert> jibList = new ArrayList<Expert>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Expert jib = new Expert();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");
			
			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOSYY") != null)
				jib.setSUOSYY(ResultArray.get(counter).get("SUOSYY").toString());
			else
				jib.setSUOSYY("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOZKS") != null)
				jib.setSUOZKS(ResultArray.get(counter).get("SUOZKS").toString());
			else
				jib.setSUOZKS("&nbsp;");

			if (ResultArray.get(counter).get("XINGM") != null)
				jib.setXINGM(ResultArray.get(counter).get("XINGM").toString());
			else
				jib.setXINGM("&nbsp;");

			if (ResultArray.get(counter).get("XINGB") != null)
				jib.setXINGB(ResultArray.get(counter).get("XINGB").toString());
			else
				jib.setXINGB("&nbsp;");

			if (ResultArray.get(counter).get("ZHIW") != null)
				jib.setZHIW(ResultArray.get(counter).get("ZHIW").toString());
			else
				jib.setZHIW("&nbsp;");

			if (ResultArray.get(counter).get("ZHIC") != null)
				jib.setZHIC(ResultArray.get(counter).get("ZHIC").toString());
			else
				jib.setZHIC("&nbsp;");

			if (ResultArray.get(counter).get("XUEW") != null)
				jib.setXUEW(ResultArray.get(counter).get("XUEW").toString());
			else
				jib.setXUEW("&nbsp;");
			
			if (ResultArray.get(counter).get("BIYXX") != null)
				jib.setBIYXX(ResultArray.get(counter).get("BIYXX").toString());
			else
				jib.setBIYXX("&nbsp;");
			
			if (ResultArray.get(counter).get("MENZSJ") != null)
				jib.setMENZSJ(ResultArray.get(counter).get("MENZSJ").toString());
			else
				jib.setMENZSJ("&nbsp;");
			
			if (ResultArray.get(counter).get("YUYL") != null)
				jib.setYUYL(ResultArray.get(counter).get("YUYL").toString());
			else
				jib.setYUYL("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");

			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("BEIZ") != null)
				jib.setBEIZ(ResultArray.get(counter).get("BEIZ").toString());
			else
				jib.setBEIZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUC") != null)
				jib.setCHUC(ResultArray.get(counter).get("CHUC").toString());
			else
				jib.setCHUC("&nbsp;");			
			
			if (ResultArray.get(counter).get("ZHICJB") != null)
				jib.setZHICJB(ResultArray.get(counter).get("ZHICJB").toString());
			else
				jib.setZHICJB("&nbsp;");			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * Search expert inf by given parameters
	 * @param params The parameters
	 * @param num The number of the expert.
	 * @param count The count of the expert. 
	 * @return The result expert.
	 */
	public ArrayList<Expert> search(String[] params, int num){
		String sqlName = "select distinct XINGM from BJWSZY_RENYUAN where XINGM like '%" + params[0] + "%'";
		String sqlSection = "select distinct XINGM from BJWSZY_RENYUAN where SUOZKS like '%" + params[1] + "%'";
		String sqlDisease = "select distinct XINGM from BJWSZY_RENYUAN where ZHUZJB like '%" + params[2] + "%'";
		String sqlTime = "select distinct XINGM from BJWSZY_RENYUAN where MENZSJ like '%" + params[3] + "%'";
		String sql = "select BJWSZY_RENYUAN.*, ROWNUM RN from BJWSZY_RENYUAN where XINGM in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlName + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlSection + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + 20) +
			  ")" +
			  "where RN > " + num;	
			
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<Expert>();
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		ArrayList<Expert> jibList = new ArrayList<Expert>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Expert jib = new Expert();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");
			
			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOSYY") != null)
				jib.setSUOSYY(ResultArray.get(counter).get("SUOSYY").toString());
			else
				jib.setSUOSYY("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOZKS") != null)
				jib.setSUOZKS(ResultArray.get(counter).get("SUOZKS").toString());
			else
				jib.setSUOZKS("&nbsp;");

			if (ResultArray.get(counter).get("XINGM") != null)
				jib.setXINGM(ResultArray.get(counter).get("XINGM").toString());
			else
				jib.setXINGM("&nbsp;");

			if (ResultArray.get(counter).get("XINGB") != null)
				jib.setXINGB(ResultArray.get(counter).get("XINGB").toString());
			else
				jib.setXINGB("&nbsp;");

			if (ResultArray.get(counter).get("ZHIW") != null)
				jib.setZHIW(ResultArray.get(counter).get("ZHIW").toString());
			else
				jib.setZHIW("&nbsp;");

			if (ResultArray.get(counter).get("ZHIC") != null)
				jib.setZHIC(ResultArray.get(counter).get("ZHIC").toString());
			else
				jib.setZHIC("&nbsp;");

			if (ResultArray.get(counter).get("XUEW") != null)
				jib.setXUEW(ResultArray.get(counter).get("XUEW").toString());
			else
				jib.setXUEW("&nbsp;");
			
			if (ResultArray.get(counter).get("BIYXX") != null)
				jib.setBIYXX(ResultArray.get(counter).get("BIYXX").toString());
			else
				jib.setBIYXX("&nbsp;");
			
			if (ResultArray.get(counter).get("MENZSJ") != null)
				jib.setMENZSJ(ResultArray.get(counter).get("MENZSJ").toString());
			else
				jib.setMENZSJ("&nbsp;");
			
			if (ResultArray.get(counter).get("YUYL") != null)
				jib.setYUYL(ResultArray.get(counter).get("YUYL").toString());
			else
				jib.setYUYL("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");

			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("BEIZ") != null)
				jib.setBEIZ(ResultArray.get(counter).get("BEIZ").toString());
			else
				jib.setBEIZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUC") != null)
				jib.setCHUC(ResultArray.get(counter).get("CHUC").toString());
			else
				jib.setCHUC("&nbsp;");			
			
			if (ResultArray.get(counter).get("ZHICJB") != null)
				jib.setZHICJB(ResultArray.get(counter).get("ZHICJB").toString());
			else
				jib.setZHICJB("&nbsp;");			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
//	通过多重条件来搜索疾病
	public int getSearchCount(String[] params){
		String sqlName = "select distinct XINGM from BJWSZY_RENYUAN where XINGM like '%" + params[0] + "%'";
		String sqlSection = "select distinct XINGM from BJWSZY_RENYUAN where SUOZKS like '%" + params[1] + "%'";
		String sqlDisease = "select distinct XINGM from BJWSZY_RENYUAN where ZHUZJB like '%" + params[2] + "%'";
		String sqlTime = "select distinct XINGM from BJWSZY_RENYUAN where MENZSJ like '%" + params[3] + "%'";
		String sql = "select count(*) COUNT from BJWSZY_RENYUAN where XINGM in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlName + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlSection + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return 0;
		}
		
		logger.info("查询：" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		
		return Integer.parseInt(conn.DBSelect(sql).get(0).get("COUNT").toString());
	}
	
	public ArrayList<String> searchEleByName(String attrName, String name){
		String sql = "select " + attrName  + " from BJWSZY_RENYUAN where XINGM like '" + name + "'";
		ArrayList<LinkedHashMap> resultSet = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		resultSet = conn.DBSelect(sql);
		ArrayList<String> jib = new ArrayList<String>();
		for(int i = 0; i < resultSet.size(); i++){
			if(resultSet.get(i).get(attrName) != null){
				jib.add(resultSet.get(i).get(attrName).toString());
			}
		}
		
		return jib;
	}
	
	public ArrayList<String> searchEleBySql(String attrName, String sql){
		ArrayList<LinkedHashMap> resultSet = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		resultSet = conn.DBSelect(sql);
		ArrayList<String> jib = new ArrayList<String>();
		for(int i = 0; i < resultSet.size(); i++){
			if(resultSet.get(i).get(attrName) != null){
				jib.add(resultSet.get(i).get(attrName).toString());
			}
		}
		
		return jib;
	}
	
	public ArrayList<Expert> searchByName(String name){
		String sql = "select * from BJWSZY_RENYUAN where XINGM like '" + name + "'";
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		ResultArray = conn.DBSelect(sql);
		ArrayList<Expert> jibList = new ArrayList<Expert>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Expert jib = new Expert();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");
			
			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOSYY") != null)
				jib.setSUOSYY(ResultArray.get(counter).get("SUOSYY").toString());
			else
				jib.setSUOSYY("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOZKS") != null)
				jib.setSUOZKS(ResultArray.get(counter).get("SUOZKS").toString());
			else
				jib.setSUOZKS("&nbsp;");

			if (ResultArray.get(counter).get("XINGM") != null)
				jib.setXINGM(ResultArray.get(counter).get("XINGM").toString());
			else
				jib.setXINGM("&nbsp;");

			if (ResultArray.get(counter).get("XINGB") != null)
				jib.setXINGB(ResultArray.get(counter).get("XINGB").toString());
			else
				jib.setXINGB("&nbsp;");

			if (ResultArray.get(counter).get("ZHIW") != null)
				jib.setZHIW(ResultArray.get(counter).get("ZHIW").toString());
			else
				jib.setZHIW("&nbsp;");

			if (ResultArray.get(counter).get("ZHIC") != null)
				jib.setZHIC(ResultArray.get(counter).get("ZHIC").toString());
			else
				jib.setZHIC("&nbsp;");

			if (ResultArray.get(counter).get("XUEW") != null)
				jib.setXUEW(ResultArray.get(counter).get("XUEW").toString());
			else
				jib.setXUEW("&nbsp;");
			
			if (ResultArray.get(counter).get("BIYXX") != null)
				jib.setBIYXX(ResultArray.get(counter).get("BIYXX").toString());
			else
				jib.setBIYXX("&nbsp;");
			
			if (ResultArray.get(counter).get("MENZSJ") != null)
				jib.setMENZSJ(ResultArray.get(counter).get("MENZSJ").toString());
			else
				jib.setMENZSJ("&nbsp;");
			
			if (ResultArray.get(counter).get("YUYL") != null)
				jib.setYUYL(ResultArray.get(counter).get("YUYL").toString());
			else
				jib.setYUYL("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");

			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("BEIZ") != null)
				jib.setBEIZ(ResultArray.get(counter).get("BEIZ").toString());
			else
				jib.setBEIZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUC") != null)
				jib.setCHUC(ResultArray.get(counter).get("CHUC").toString());
			else
				jib.setCHUC("&nbsp;");			
			
			if (ResultArray.get(counter).get("ZHICJB") != null)
				jib.setZHICJB(ResultArray.get(counter).get("ZHICJB").toString());
			else
				jib.setZHICJB("&nbsp;");			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	public ArrayList<Expert> searchExpert(String sql){
		logger.info("查询：" + sql);
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<Expert> jibList = new ArrayList<Expert>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Expert jib = new Expert();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");
			
			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOSYY") != null)
				jib.setSUOSYY(ResultArray.get(counter).get("SUOSYY").toString());
			else
				jib.setSUOSYY("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOZKS") != null)
				jib.setSUOZKS(ResultArray.get(counter).get("SUOZKS").toString());
			else
				jib.setSUOZKS("&nbsp;");

			if (ResultArray.get(counter).get("XINGM") != null)
				jib.setXINGM(ResultArray.get(counter).get("XINGM").toString());
			else
				jib.setXINGM("&nbsp;");

			if (ResultArray.get(counter).get("XINGB") != null)
				jib.setXINGB(ResultArray.get(counter).get("XINGB").toString());
			else
				jib.setXINGB("&nbsp;");

			if (ResultArray.get(counter).get("ZHIW") != null)
				jib.setZHIW(ResultArray.get(counter).get("ZHIW").toString());
			else
				jib.setZHIW("&nbsp;");

			if (ResultArray.get(counter).get("ZHIC") != null)
				jib.setZHIC(ResultArray.get(counter).get("ZHIC").toString());
			else
				jib.setZHIC("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHICJB") != null)
				jib.setZHICJB(ResultArray.get(counter).get("ZHICJB").toString());
			else
				jib.setZHICJB("&nbsp;");
			
			if (ResultArray.get(counter).get("XUEW") != null)
				jib.setXUEW(ResultArray.get(counter).get("XUEW").toString());
			else
				jib.setXUEW("&nbsp;");
			
			if (ResultArray.get(counter).get("BIYXX") != null)
				jib.setBIYXX(ResultArray.get(counter).get("BIYXX").toString());
			else
				jib.setBIYXX("&nbsp;");
			
			if (ResultArray.get(counter).get("MENZSJ") != null)
				jib.setMENZSJ(ResultArray.get(counter).get("MENZSJ").toString());
			else
				jib.setMENZSJ("&nbsp;");
			
			if (ResultArray.get(counter).get("YUYL") != null)
				jib.setYUYL(ResultArray.get(counter).get("YUYL").toString());
			else
				jib.setYUYL("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");

			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("BEIZ") != null)
				jib.setBEIZ(ResultArray.get(counter).get("BEIZ").toString());
			else
				jib.setBEIZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUC") != null)
				jib.setCHUC(ResultArray.get(counter).get("CHUC").toString());
			else
				jib.setCHUC("&nbsp;");			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * Get experts by given disease;
	 * @param disease
	 * @return The relate expert infmation.
	 * the full sql is like that:
		select * from(  
		  select ID, XINGM, sum_correlation, rownum rn from(
		    select ID, XINGM, sum_correlation
		    from bjwszy_renyuan,(      
		        select ID as correlation_id, sum(correlation) as sum_correlation
		        from (
		          (select ID, 50 as correlation from bjwszy_renyuan 
		          where ZHUZJB like '%冠心病%')
		          union all
		          (select ID, 40 as correlation from bjwszy_renyuan 
		          where ZHUZJB like '%高血压%')
		        ) 
		        group by ID
		        order by sum_correlation desc
		    ) 
		    where bjwszy_renyuan.id = correlation_id 
		    order by sum_correlation desc, xingm asc
		   )
		   where rownum <= 50
		 )
		where rn > 10
	 */
	public ArrayList<ExpertInf> getExpertByDisease(ArrayList<DiseaseInf> disease, int count, int num){
	   /*Expample sql is like that:		
		select * from(  
		  select ID, XINGM, sum_correlation, rownum rn from(
		    select ID, XINGM, sum_correlation
		    from bjwszy_renyuan,(      
		        select ID as correlation_id, sum(correlation) as sum_correlation
		        from (
		          (select ID, 50 as correlation from bjwszy_renyuan 
		          where ZHUZJB like '%冠心病%')
		          union all
		          (select ID, 40 as correlation from bjwszy_renyuan 
		          where ZHUZJB like '%高血压%')
		        ) 
		        group by ID
		        order by sum_correlation desc
		    ) 
		    where bjwszy_renyuan.id = correlation_id 
		    order by sum_correlation desc, xingm asc
		   )
		   where rownum <= 50
		 )
		where rn > 10*/
		
		ArrayList<ExpertInf> result = new ArrayList<ExpertInf>();
		
		//If there is no disease,then return a empty list.
		if(disease.size() <= 0){
			return result;
		}
		
		StringBuffer sql = new StringBuffer(" select * from(  " +
												" select ID, XINGM, sum_correlation, SUOSYY, rownum rn from(" +
													" select ID, XINGM, sum_correlation, SUOSYY " +
													" from bjwszy_renyuan,( " +
														" select ID as correlation_id, sum(correlation) as sum_correlation " +
														" from ( ");
		String tmpSql;
		
		if(disease.size() >= 1){
			tmpSql = " (select ID, " + disease.get(0).getCorrelation() +" as correlation " +
					 " from bjwszy_renyuan " +
					 " where ZHUZJB like '%" + disease.get(0).getName() + "%')";
			
			sql.append(tmpSql);
		}
		
	    for(int i = 1; i < disease.size(); i++){
			tmpSql = " union all " +
					 " (select ID, " + disease.get(i).getCorrelation() +" as correlation " +
					 " from bjwszy_renyuan " +
					 " where ZHUZJB like '%" + disease.get(i).getName() + "%')";
			
			sql.append(tmpSql);
	    }
	    
	    sql.append(				" ) " +
				   				" group by ID " +
				   				" order by sum_correlation desc "+
			   				" )" + 
			   				" where bjwszy_renyuan.id = correlation_id " +
				   			" order by sum_correlation desc, XiNGM asc" +
			   			" )" +
			   			" where rownum <= " + (num + count) +
		   			" )" +
		   			" where rn > " + num );
		
		//Get expert information from the database
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> resultArray = new ArrayList<LinkedHashMap>();
		logger.info("查询：" + sql.toString());
		resultArray = conn.DBSelect(sql.toString());
		
		for(int i = 0; i < resultArray.size(); i++){
			ExpertInf tmp = new ExpertInf();
			
			if (resultArray.get(i).get("XINGM") != null)
				tmp.setName(resultArray.get(i).get("XINGM").toString());
			else
				tmp.setName("数据库信息缺失！");
			
			if (resultArray.get(i).get("SUOSYY") != null)
				tmp.setHospital(resultArray.get(i).get("SUOSYY").toString());
			else
				tmp.setHospital("$nbsp;");
			
			tmp.setId(resultArray.get(i).get("ID").toString());
			tmp.setCorrelation(Integer.parseInt(resultArray.get(i).get("SUM_CORRELATION").toString()) );
			
			result.add(tmp);
		}
		
		return result;
	}
	
	/**
	 * Get experts by given disease;
	 * @param disease
	 * @return The relate expert infmation.
	 * the full sql is like that:
		select count(distinct id)
		from (
		  (select ID, 50 as correlation from bjwszy_renyuan 
		  where ZHUZJB like '%冠心病%')
		  union all
		  (select ID, 40 as correlation from bjwszy_renyuan 
		  where ZHUZJB like '%心脏病%')
		) 
	 */
	public int getExpertCountByDisease(ArrayList<DiseaseInf> disease){
	   /*Expample sql is like that:		
		select count(distinct id)
		from (
		  (select ID, 50 as correlation from bjwszy_renyuan 
		  where ZHUZJB like '%冠心病%')
		  union all
		  (select ID, 40 as correlation from bjwszy_renyuan 
		  where ZHUZJB like '%心脏病%')
		) */
//		If there is no disease,then return a empty list.
		if(disease.size() <= 0){
			return 0;
		}
		
		StringBuffer sql = new StringBuffer(" select count(distinct id) as sum_expert " +
											" from(  ");
		String tmpSql;
		
		if(disease.size() >= 1){
			tmpSql = " (select ID, " + disease.get(0).getCorrelation() +" as correlation " +
					 " from bjwszy_renyuan " +
					 " where ZHUZJB like '%" + disease.get(0).getName() + "%')";
			
			sql.append(tmpSql);
		}
		
	    for(int i = 1; i < disease.size(); i++){
			tmpSql = " union all " +
					 " (select ID, " + disease.get(i).getCorrelation() +" as correlation " +
					 " from bjwszy_renyuan " +
					 " where ZHUZJB like '%" + disease.get(i).getName() + "%')";
			
			sql.append(tmpSql);
	    }
	    
	    sql.append(" ) "); 
		
		//Get expert information from the database
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		logger.info("查询：" + sql.toString());
		
		
		return Integer.parseInt((conn.DBSelect(sql.toString())).get(0).get("SUM_EXPERT").toString());
	}
	
	/**
	 * Search expert inf by given parameters
	 * @param params The parameters
	 * @param num The number of the expert.
	 * @param count The count of the expert. 
	 * @return The result expert.
	 */
	public ArrayList<ExpertInf> getExpertInf(String[] params, int num, int count){
		String sqlName = "select distinct XINGM from BJWSZY_RENYUAN where XINGM like '%" + params[0] + "%'";
		String sqlSection = "select distinct XINGM from BJWSZY_RENYUAN where SUOZKS like '%" + params[1] + "%'";
		String sqlDisease = "select distinct XINGM from BJWSZY_RENYUAN where ZHUZJB like '%" + params[2] + "%'";
		String sqlTime = "select distinct XINGM from BJWSZY_RENYUAN where MENZSJ like '%" + params[3] + "%'";
		String sql = "select BJWSZY_RENYUAN.XINGM, ID, SUOSYY, ROWNUM RN from BJWSZY_RENYUAN where XINGM in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlName + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlSection + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + count) +
			  ")" +
			  "where RN > " + num;	
			
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<ExpertInf>();
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		ArrayList<ExpertInf> jibList = new ArrayList<ExpertInf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ExpertInf jib = new ExpertInf();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setId(ResultArray.get(counter).get("ID").toString());
			else
				jib.setId("&nbsp;");
			
			if (ResultArray.get(counter).get("XINGM") != null)
				jib.setName(ResultArray.get(counter).get("XINGM").toString());
			else
				jib.setName("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOSYY") != null)
				jib.setHospital(ResultArray.get(counter).get("SUOSYY").toString());
			else
				jib.setHospital("&nbsp;");

			jib.setCorrelation(1);
			
			jibList.add(jib);
		}
		
		return jibList;
	}

	/**
	 * Search expert's relate disease by given parameters
	 * @param params The parameters
	 * @param num The number of the expert.
	 * @param count The count of the expert. 
	 * @return The result expert.
	 */
	public ArrayList<DiseaseInf> getDiseaseInfByExpert(String[] params, int count, int num){
		ApplicationContext factory = GetFactory.getFactory();
		LoadConf conf = (LoadConf)factory.getBean("searchConf");
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		Iterator iterator;
		
		int expertCount = conf.getExpertCount();
		int diseaseExpertCount = conf.getDiseaseExpertCount();
		String sqlName = "select distinct XINGM from BJWSZY_RENYUAN where XINGM like '%" + params[0] + "%'";
		String sqlSection = "select distinct XINGM from BJWSZY_RENYUAN where SUOZKS like '%" + params[1] + "%'";
		String sqlDisease = "select distinct XINGM from BJWSZY_RENYUAN where ZHUZJB like '%" + params[2] + "%'";
		String sqlTime = "select distinct XINGM from BJWSZY_RENYUAN where MENZSJ like '%" + params[3] + "%'";
		String sql = "select ZHUZJB, ROWNUM RN from BJWSZY_RENYUAN where XINGM in(";
		
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlName + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlSection + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		sql = "select * from (" +
			  	sql + 
			  	" and ZHUZJB is not null " +
			  	" and ROWNUM <= " + (0 + expertCount) +
			  ")" +
			  "where RN > " + 0;	
			
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<DiseaseInf>();
		}
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		LinkedHashMap<String, Integer> tmpDiseases = new LinkedHashMap<String, Integer>();
		ArrayList<DiseaseInf> result = new ArrayList<DiseaseInf>();
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			String[] tmpStrs;
			
			if (ResultArray.get(counter).get("ZHUZJB") != null){
				tmpStrs = ResultArray.get(counter).get("ZHUZJB").toString().split(",");
				
				for(int i = 0; i < tmpStrs.length; i++){
					String tmpStr = tmpStrs[i].trim();
					if(tmpDiseases.containsKey(tmpStr)){
						tmpDiseases.put(tmpStr, tmpDiseases.get(tmpStr) + 1);
					}
					else{
						tmpDiseases.put(tmpStr, 1);
					}
				}
			}
		}	
		
		//Sorted the disease
		TreeMap<Integer, ArrayList<String>> sortedDisease = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
		iterator = tmpDiseases.keySet().iterator();
		
		while(iterator.hasNext()){
			String tmpStr = (String)iterator.next();
			Integer tmpNum = tmpDiseases.get(tmpStr);
			
			if(sortedDisease.containsKey(tmpNum)){
				sortedDisease.get(tmpNum).add(tmpStr);
			}
			else{
				ArrayList<String> tmpList = new ArrayList<String>();
				tmpList.add(tmpStr);
				
				sortedDisease.put(tmpNum, tmpList);
			}
		}
		
		//Get the sorted disease
		iterator = sortedDisease.keySet().iterator();
		for(int i = 0; i < diseaseExpertCount && iterator.hasNext(); i++){
			Integer tmpInt = (Integer)iterator.next();
			ArrayList<String> tmpDiseaseArray = (ArrayList<String>)sortedDisease.get(tmpInt);

			if((tmpDiseaseArray.size() + i) >= diseaseExpertCount){
				for(int j = 0; (i + j) < diseaseExpertCount; j++){
					result.add(new DiseaseInf(tmpDiseaseArray.get(j), tmpInt, "", "-1"));
				}
			}
			else{
				for(int j = 0; j < tmpDiseaseArray.size(); j++){
					result.add(new DiseaseInf(tmpDiseaseArray.get(j), tmpInt, "", "-1"));
				}
			}
		}
		
		return new ArrayList<DiseaseInf> (result.subList(num, ( (result.size())<(num + count)?(result.size()):(num+count) )));
	}
	
	/**
	 * Search expert's relate disease by given parameters
	 * @param params The parameters
	 * @param num The number of the expert.
	 * @param count The count of the expert. 
	 * @return The result expert.
	 */
	public int getDiseaseCountByExpert(String[] params){
		ApplicationContext factory = GetFactory.getFactory();
		LoadConf conf = (LoadConf)factory.getBean("searchConf");
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		int expertCount = conf.getExpertCount();
		int diseaseExpertCount = conf.getDiseaseExpertCount();
		String sqlName = "select distinct XINGM from BJWSZY_RENYUAN where XINGM like '%" + params[0] + "%'";
		String sqlSection = "select distinct XINGM from BJWSZY_RENYUAN where SUOZKS like '%" + params[1] + "%'";
		String sqlDisease = "select distinct XINGM from BJWSZY_RENYUAN where ZHUZJB like '%" + params[2] + "%'";
		String sqlTime = "select distinct XINGM from BJWSZY_RENYUAN where MENZSJ like '%" + params[3] + "%'";
		String sql = "select ZHUZJB, ROWNUM RN from BJWSZY_RENYUAN where XINGM in(";
		
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlName + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlSection + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		sql = "select * from (" +
			  	sql + 
			  	" and ZHUZJB is not null " +
			  	" and ROWNUM <= " + (0 + expertCount) +
			  ")" +
			  "where RN > " + 0;	
			
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return 0;
		}
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		LinkedHashMap<String, Integer> tmpDiseases = new LinkedHashMap<String, Integer>();
		
		int sum = 0;
		for (int counter = 0; counter < ResultArray.size() && sum <= diseaseExpertCount; counter++) {
			String[] tmpStrs;
			
			if (ResultArray.get(counter).get("ZHUZJB") != null){
				tmpStrs = ResultArray.get(counter).get("ZHUZJB").toString().split(",");
				
				for(int i = 0; i < tmpStrs.length; i++){
					if(!tmpDiseases.containsKey(tmpStrs[i])){
						sum++;
						tmpDiseases.put(tmpStrs[i], 1);
					}
					
					if(sum >= diseaseExpertCount)
						break;
				}
			}
		}
		
		return sum;
	}
	
	/**
	 * Search expert's relate disease by given parameters
	 * @param params The parameters
	 * @param num The number of the expert.
	 * @param count The count of the expert. 
	 * @return The result expert.
	 */
	public ArrayList<DiseaseInf> getDiseaseInfByExpertAttr(String attrName, String attrValue, int num , int count){
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");

		String sql = "select ZHUZJB, ROWNUM RN from BJWSZY_RENYUAN where " + attrName + " like '" + attrValue + "'";
		
		sql = "select * from (" +
			  	sql + 
			  	" and ZHUZJB is not null " +
			  	" and ROWNUM <= " + (num + count) +
			  ")" +
			  "where RN > " + num;	
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);
		
		ArrayList<DiseaseInf> result = new ArrayList<DiseaseInf>();	
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			String[] tmpStrs;
			
			if (ResultArray.get(counter).get("ZHUZJB") != null){
				tmpStrs = ResultArray.get(counter).get("ZHUZJB").toString().split(",");
				
				for(int i = 0; i < tmpStrs.length; i++){
					result.add(new DiseaseInf(tmpStrs[i], 1, "", "-1"));
				}
				
				break;
			}
		}	
		
		return result;
	}
	
	/**
	 * Search expert's relate disease by given parameters
	 * @param params The parameters
	 * @param num The number of the expert.
	 * @param count The count of the expert. 
	 * @return The result disease count.
	 */
	public int getDiseaseInfCountByExpertAttr(String attrName, String attrValue){
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");

		String sql = "select ZHUZJB from BJWSZY_RENYUAN where " + attrName + " like '" + attrValue + "'";
		
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);
		
		int sum = 0;
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("ZHUZJB") != null){
				sum = ResultArray.get(counter).get("ZHUZJB").toString().split(",").length;
				break;
			}
		}		
		
		return sum;
	}
	
	/**
	 * Search disease by given attribute.
	 * @param attrName The attribute's name in database. 
	 * @param attrValue The attribute's value.
	 * @param num The number of the attrubute.
	 * @return The data result, one colum a time.
	 */
	public DataReader searchByAttr(String attrName, String attrValue, int num){
		String sql = " select BJWSZY_RENYUAN.*, rownum RN from BJWSZY_RENYUAN" +
					 " where " + attrName + " like '" + attrValue + "'";
		sql = "select * from (" +
			  	sql + 
			  ")" +
			  "where RN = " + num;	
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		logger.info("查询:" + sql);
		ResultArray = conn.DBSelect(sql);
		DataReader result = null;
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Expert jib = new Expert();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");
			
			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOSYY") != null)
				jib.setSUOSYY(ResultArray.get(counter).get("SUOSYY").toString());
			else
				jib.setSUOSYY("&nbsp;");
			
			if (ResultArray.get(counter).get("SUOZKS") != null)
				jib.setSUOZKS(ResultArray.get(counter).get("SUOZKS").toString());
			else
				jib.setSUOZKS("&nbsp;");

			if (ResultArray.get(counter).get("XINGM") != null)
				jib.setXINGM(ResultArray.get(counter).get("XINGM").toString());
			else
				jib.setXINGM("&nbsp;");

			if (ResultArray.get(counter).get("XINGB") != null)
				jib.setXINGB(ResultArray.get(counter).get("XINGB").toString());
			else
				jib.setXINGB("&nbsp;");

			if (ResultArray.get(counter).get("ZHIW") != null)
				jib.setZHIW(ResultArray.get(counter).get("ZHIW").toString());
			else
				jib.setZHIW("&nbsp;");

			if (ResultArray.get(counter).get("ZHIC") != null)
				jib.setZHIC(ResultArray.get(counter).get("ZHIC").toString());
			else
				jib.setZHIC("&nbsp;");

			if (ResultArray.get(counter).get("XUEW") != null)
				jib.setXUEW(ResultArray.get(counter).get("XUEW").toString());
			else
				jib.setXUEW("&nbsp;");
			
			if (ResultArray.get(counter).get("BIYXX") != null)
				jib.setBIYXX(ResultArray.get(counter).get("BIYXX").toString());
			else
				jib.setBIYXX("&nbsp;");
			
			if (ResultArray.get(counter).get("MENZSJ") != null)
				jib.setMENZSJ(ResultArray.get(counter).get("MENZSJ").toString());
			else
				jib.setMENZSJ("&nbsp;");
			
			if (ResultArray.get(counter).get("YUYL") != null)
				jib.setYUYL(ResultArray.get(counter).get("YUYL").toString());
			else
				jib.setYUYL("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");

			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setZHUZJB(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setZHUZJB("&nbsp;");
			
			if (ResultArray.get(counter).get("BEIZ") != null)
				jib.setBEIZ(ResultArray.get(counter).get("BEIZ").toString());
			else
				jib.setBEIZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUC") != null)
				jib.setCHUC(ResultArray.get(counter).get("CHUC").toString());
			else
				jib.setCHUC("&nbsp;");			
			
			if (ResultArray.get(counter).get("ZHICJB") != null)
				jib.setZHICJB(ResultArray.get(counter).get("ZHICJB").toString());
			else
				jib.setZHICJB("&nbsp;");			
			
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
		String sql = " select count(ID) NUMSUM from BJWSZY_RENYUAN" +
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
