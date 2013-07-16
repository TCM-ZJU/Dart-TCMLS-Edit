package cn.edu.zju.ccnt.TFGW.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.object.YJYA;
import cn.edu.zju.ccnt.TFGW.object.ZYBZZLBZ;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.DataReader;


/**
 * 中医疾病治疗标准数据库DAO
 * @author 张小刚
 *
 */
public class ZYBZZLBZDAO {
	static Logger logger = Logger.getLogger(ZYBZZLBZDAO.class.getName());	
	
//	通过多重条件来搜索疾病
	public ArrayList<ZYBZZLBZ> search(String[] params){
		String sqlDisease = "select distinct BINGZMC from ZYBZZLBZ where BINGZMC like '%" + params[0] + "%'";
		String sqlBY = "select distinct BINGZMC from ZYBZZLBZ where BINGYBJ like '%" + params[1] + "%'";
		String sqlZZ = "select distinct BINGZMC from ZYBZZLBZ where ZHENDYJ like '%" + params[2] + "%'";		
		String sqlZH = "select distinct BINGZMC from ZYBZZLBZ where ZHENGHFL like '%" + params[3] + "%'";
		String sql = "select * from ZYBZZLBZ where BINGZMC in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlDisease + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlBY + ")";
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
			return new ArrayList<ZYBZZLBZ>();
		}
		
		logger.info("查询�?" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<ZYBZZLBZ> jibList = new ArrayList<ZYBZZLBZ>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZYBZZLBZ jib = new ZYBZZLBZ();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("BINGZMC") != null)
				jib.setBINGZMC(ResultArray.get(counter).get("BINGZMC").toString());
			else
				jib.setBINGZMC("&nbsp;");

			if (ResultArray.get(counter).get("BINGYBJ") != null)
				jib.setBINGYBJ(ResultArray.get(counter).get("BINGYBJ").toString());
			else
				jib.setBINGYBJ("&nbsp;");

			if (ResultArray.get(counter).get("ZHENDYJ") != null)
				jib.setZHENDYJ(ResultArray.get(counter).get("ZHENDYJ").toString());
			else
				jib.setZHENDYJ("&nbsp;");

			if (ResultArray.get(counter).get("ZHENGHFL") != null)
				jib.setZHENGHFL(ResultArray.get(counter).get("ZHENGHFL").toString());
			else
				jib.setZHENGHFL("&nbsp;");

			if (ResultArray.get(counter).get("LXPD_ZY") != null)
				jib.setLXPD_ZY(ResultArray.get(counter).get("LXPD_ZY").toString());
			else
				jib.setLXPD_ZY("&nbsp;");

			if (ResultArray.get(counter).get("LXPD_HZ") != null)
				jib.setLXPD_HZ(ResultArray.get(counter).get("LXPD_HZ").toString());
			else
				jib.setLXPD_HZ("&nbsp;");
			
			if (ResultArray.get(counter).get("LXPD_WY") != null)
				jib.setLXPD_WY(ResultArray.get(counter).get("LXPD_WY").toString());
			else
				jib.setLXPD_WY("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHONGYFK") != null)
				jib.setZHONGYFK(ResultArray.get(counter).get("ZHONGYFK").toString());
			else
				jib.setZHONGYFK("&nbsp;");
			
			if (ResultArray.get(counter).get("JIBFL") != null)
				jib.setJIBFL(ResultArray.get(counter).get("JIBFL").toString());
			else
				jib.setJIBFL("&nbsp;");
					
			jibList.add(jib);
		}
		
		return jibList;
	}
	
//	通过多重条件来搜索疾病
	public ArrayList<ZYBZZLBZ> searchByJibNames(String jibNames, int num, int count){
		String sql = "select ZYBZZLBZ.*,ROWNUM RN from ZYBZZLBZ where BINGZMC in " + jibNames;
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + count) +
			  ")" +
			  "where RN > " + num;		
		
		logger.info("查询:" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<ZYBZZLBZ> jibList = new ArrayList<ZYBZZLBZ>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZYBZZLBZ jib = new ZYBZZLBZ();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("BINGZMC") != null)
				jib.setBINGZMC(ResultArray.get(counter).get("BINGZMC").toString());
			else
				jib.setBINGZMC("&nbsp;");

			if (ResultArray.get(counter).get("BINGYBJ") != null)
				jib.setBINGYBJ(ResultArray.get(counter).get("BINGYBJ").toString());
			else
				jib.setBINGYBJ("&nbsp;");

			if (ResultArray.get(counter).get("ZHENDYJ") != null)
				jib.setZHENDYJ(ResultArray.get(counter).get("ZHENDYJ").toString());
			else
				jib.setZHENDYJ("&nbsp;");

			if (ResultArray.get(counter).get("ZHENGHFL") != null)
				jib.setZHENGHFL(ResultArray.get(counter).get("ZHENGHFL").toString());
			else
				jib.setZHENGHFL("&nbsp;");

			if (ResultArray.get(counter).get("LXPD_ZY") != null)
				jib.setLXPD_ZY(ResultArray.get(counter).get("LXPD_ZY").toString());
			else
				jib.setLXPD_ZY("&nbsp;");

			if (ResultArray.get(counter).get("LXPD_HZ") != null)
				jib.setLXPD_HZ(ResultArray.get(counter).get("LXPD_HZ").toString());
			else
				jib.setLXPD_HZ("&nbsp;");
			
			if (ResultArray.get(counter).get("LXPD_WY") != null)
				jib.setLXPD_WY(ResultArray.get(counter).get("LXPD_WY").toString());
			else
				jib.setLXPD_WY("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHONGYFK") != null)
				jib.setZHONGYFK(ResultArray.get(counter).get("ZHONGYFK").toString());
			else
				jib.setZHONGYFK("&nbsp;");
			
			if (ResultArray.get(counter).get("JIBFL") != null)
				jib.setJIBFL(ResultArray.get(counter).get("JIBFL").toString());
			else
				jib.setJIBFL("&nbsp;");
					
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	//通过多重条件来搜索疾�?
	public ArrayList<ZYBZZLBZ> search(String[] params, int num){
		String sqlDisease = "select distinct BINGZMC from ZYBZZLBZ where BINGZMC like '%" + params[0] + "%'";
		String sqlBY = "select distinct BINGZMC from ZYBZZLBZ where BINGYBJ like '%" + params[1] + "%'";
		String sqlZZ = "select distinct BINGZMC from ZYBZZLBZ where ZHENDYJ like '%" + params[2] + "%'";		
		String sqlZH = "select distinct BINGZMC from ZYBZZLBZ where ZHENGHFL like '%" + params[3] + "%'";
		String sql = "select ZYBZZLBZ.*,ROWNUM RN from ZYBZZLBZ where BINGZMC in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlDisease + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlBY + ")";
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
			return new ArrayList<ZYBZZLBZ>();
		}
		
		sql = "select * from (" +
			  	sql + 
			  	" and ROWNUM <= " + (num + 20) +
			  ")" +
			  "where RN > " + num;	
		
		logger.info("查询�?" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<ZYBZZLBZ> jibList = new ArrayList<ZYBZZLBZ>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			ZYBZZLBZ jib = new ZYBZZLBZ();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("BINGZMC") != null)
				jib.setBINGZMC(ResultArray.get(counter).get("BINGZMC").toString());
			else
				jib.setBINGZMC("&nbsp;");

			if (ResultArray.get(counter).get("BINGYBJ") != null)
				jib.setBINGYBJ(ResultArray.get(counter).get("BINGYBJ").toString());
			else
				jib.setBINGYBJ("&nbsp;");

			if (ResultArray.get(counter).get("ZHENDYJ") != null)
				jib.setZHENDYJ(ResultArray.get(counter).get("ZHENDYJ").toString());
			else
				jib.setZHENDYJ("&nbsp;");

			if (ResultArray.get(counter).get("ZHENGHFL") != null)
				jib.setZHENGHFL(ResultArray.get(counter).get("ZHENGHFL").toString());
			else
				jib.setZHENGHFL("&nbsp;");

			if (ResultArray.get(counter).get("LXPD_ZY") != null)
				jib.setLXPD_ZY(ResultArray.get(counter).get("LXPD_ZY").toString());
			else
				jib.setLXPD_ZY("&nbsp;");

			if (ResultArray.get(counter).get("LXPD_HZ") != null)
				jib.setLXPD_HZ(ResultArray.get(counter).get("LXPD_HZ").toString());
			else
				jib.setLXPD_HZ("&nbsp;");
			
			if (ResultArray.get(counter).get("LXPD_WY") != null)
				jib.setLXPD_WY(ResultArray.get(counter).get("LXPD_WY").toString());
			else
				jib.setLXPD_WY("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHONGYFK") != null)
				jib.setZHONGYFK(ResultArray.get(counter).get("ZHONGYFK").toString());
			else
				jib.setZHONGYFK("&nbsp;");
			
			if (ResultArray.get(counter).get("JIBFL") != null)
				jib.setJIBFL(ResultArray.get(counter).get("JIBFL").toString());
			else
				jib.setJIBFL("&nbsp;");
					
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * 得到搜索结果个数
	 * @param params
	 * @return
	 */
	public int getSearchCount(String[] params){
		String sqlDisease = "select distinct BINGZMC from ZYBZZLBZ where BINGZMC like '%" + params[0] + "%'";
		String sqlBY = "select distinct BINGZMC from ZYBZZLBZ where BINGYBJ like '%" + params[1] + "%'";
		String sqlZZ = "select distinct BINGZMC from ZYBZZLBZ where ZHENDYJ like '%" + params[2] + "%'";		
		String sqlZH = "select distinct BINGZMC from ZYBZZLBZ where ZHENGHFL like '%" + params[3] + "%'";
		String sql = "select count(*) COUNT from ZYBZZLBZ where BINGZMC in(";
		boolean flag = false;
		
		if(params[0].length() > 0){
			sql = sql +"(" + sqlDisease + ")";
			flag = true;
		}
		if(params[1].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlBY + ")";
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
		
		logger.info("查询�?" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}
	
	/**
	 * 得到搜索结果个数
	 * @param params
	 * @return
	 */
	public int getSearchCountOfJibNames(String names){
		String sql = "select count(*) COUNT from ZYBZZLBZ where BINGZMC in " + names;
		
		logger.info("查询�?" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
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
		String sql = " select ZYBZZLBZ.*,ROWNUM RN from ZYBZZLBZ " +
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
			ZYBZZLBZ jib = new ZYBZZLBZ();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("BINGZMC") != null)
				jib.setBINGZMC(ResultArray.get(counter).get("BINGZMC").toString());
			else
				jib.setBINGZMC("&nbsp;");

			if (ResultArray.get(counter).get("BINGYBJ") != null)
				jib.setBINGYBJ(ResultArray.get(counter).get("BINGYBJ").toString());
			else
				jib.setBINGYBJ("&nbsp;");

			if (ResultArray.get(counter).get("ZHENDYJ") != null)
				jib.setZHENDYJ(ResultArray.get(counter).get("ZHENDYJ").toString());
			else
				jib.setZHENDYJ("&nbsp;");

			if (ResultArray.get(counter).get("ZHENGHFL") != null)
				jib.setZHENGHFL(ResultArray.get(counter).get("ZHENGHFL").toString());
			else
				jib.setZHENGHFL("&nbsp;");

			if (ResultArray.get(counter).get("LXPD_ZY") != null)
				jib.setLXPD_ZY(ResultArray.get(counter).get("LXPD_ZY").toString());
			else
				jib.setLXPD_ZY("&nbsp;");

			if (ResultArray.get(counter).get("LXPD_HZ") != null)
				jib.setLXPD_HZ(ResultArray.get(counter).get("LXPD_HZ").toString());
			else
				jib.setLXPD_HZ("&nbsp;");
			
			if (ResultArray.get(counter).get("LXPD_WY") != null)
				jib.setLXPD_WY(ResultArray.get(counter).get("LXPD_WY").toString());
			else
				jib.setLXPD_WY("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHONGYFK") != null)
				jib.setZHONGYFK(ResultArray.get(counter).get("ZHONGYFK").toString());
			else
				jib.setZHONGYFK("&nbsp;");
			
			if (ResultArray.get(counter).get("JIBFL") != null)
				jib.setJIBFL(ResultArray.get(counter).get("JIBFL").toString());
			else
				jib.setJIBFL("&nbsp;");
					
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
		String sql = " select count(*) from ZYBZZLBZ" +
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
