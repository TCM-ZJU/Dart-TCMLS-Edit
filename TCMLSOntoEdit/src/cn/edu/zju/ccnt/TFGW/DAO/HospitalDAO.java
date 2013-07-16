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
import cn.edu.zju.ccnt.TFGW.object.Hospital;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.DiseaseInf;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.ExpertInf;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.HospitalInf;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;

public class HospitalDAO {
	static Logger logger = Logger.getLogger(HospitalDAO.class.getName());	
	
	/**
	 * Search hospital information by given parameters
	 * @param params The parameters used to search the Hospital
	 * @return The hospital infmation.
	 */
	public ArrayList<Hospital> search(String[] params){
	/*	int low = 0;
		int up = 100000;
		
		if(params[3].length()>0 && params[4].length()>0){
			low = Integer.parseInt(params[3]);
			up = Integer.parseInt(params[4]);
		}*/
		
		String sqlName = "select distinct YIYMC from BJWSZY_YIYUAN where YIYMC like '%" + params[0] + "%' or YIYBM like '%" + params[0] + "%'";
		String sqlLevel = "select distinct YIYMC from BJWSZY_YIYUAN where YIYDJ like '%" + params[1] + "%'";
		String sqlDisease = "select distinct YIYMC from BJWSZY_YIYUAN where TESZK like '%" + params[2] + "%'";
	//	String sqlTime = "select distinct YIYMC from BJWSZY_YIYUAN where CHUANGWS > " + low + " and CHUANGWS < " + up;
		String sql = "select * from BJWSZY_YIYUAN where YIYMC in(";
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
			sql = sql + "(" + sqlLevel + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
/*		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	*/
		sql += ")";
		
		logger.info("查询：" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<Hospital> jibList = new ArrayList<Hospital>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Hospital jib = new Hospital();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");

			if (ResultArray.get(counter).get("YIYMC") != null)
				jib.setYIYMC(ResultArray.get(counter).get("YIYMC").toString());
			else
				jib.setYIYMC("&nbsp;");

			if (ResultArray.get(counter).get("YIYBM") != null)
				jib.setYIYBM(ResultArray.get(counter).get("YIYBM").toString());
			else
				jib.setYIYBM("&nbsp;");

			if (ResultArray.get(counter).get("SUOSDQ") != null)
				jib.setSUOSDQ(ResultArray.get(counter).get("SUOSDQ").toString());
			else
				jib.setSUOSDQ("&nbsp;");

			if (ResultArray.get(counter).get("DANWXZ") != null)
				jib.setDANWXZ(ResultArray.get(counter).get("DANWXZ").toString());
			else
				jib.setDANWXZ("&nbsp;");

			if (ResultArray.get(counter).get("YIYDZ") != null)
				jib.setYIYDZ(ResultArray.get(counter).get("YIYDZ").toString());
			else
				jib.setYIYDZ("&nbsp;");
			
			if (ResultArray.get(counter).get("YOUZBM") != null)
				jib.setYOUZBM(ResultArray.get(counter).get("YOUZBM").toString());
			else
				jib.setYOUZBM("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUANGWS") != null)
				jib.setCHUANGWS(ResultArray.get(counter).get("CHUANGWS").toString());
			else
				jib.setCHUANGWS("&nbsp;");
			
			if (ResultArray.get(counter).get("RIMZL") != null)
				jib.setRIMZL(ResultArray.get(counter).get("RIMZL").toString());
			else
				jib.setRIMZL("&nbsp;");
			
			if (ResultArray.get(counter).get("YIYDJ") != null)
				jib.setYIYDJ(ResultArray.get(counter).get("YIYDJ").toString());
			else
				jib.setYIYDJ("&nbsp;");

			if (ResultArray.get(counter).get("YUANZ") != null)
				jib.setYUANZ(ResultArray.get(counter).get("YUANZ").toString());
			else
				jib.setYUANZ("&nbsp;");
			
			if (ResultArray.get(counter).get("KES") != null)
				jib.setKES(ResultArray.get(counter).get("KES").toString());
			else
				jib.setKES("&nbsp;");
			
			if (ResultArray.get(counter).get("TESZK") != null)
				jib.setTESZK(ResultArray.get(counter).get("TESZK").toString());
			else
				jib.setTESZK("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUYSB") != null)
				jib.setZHUYSB(ResultArray.get(counter).get("ZHUYSB").toString());
			else
				jib.setZHUYSB("&nbsp;");	
			
			if (ResultArray.get(counter).get("YOUX") != null)
				jib.setYOUX(ResultArray.get(counter).get("YOUX").toString());
			else
				jib.setYOUX("&nbsp;");
			
			if (ResultArray.get(counter).get("WANGZ") != null)
				jib.setWANGZ(ResultArray.get(counter).get("WANGZ").toString());
			else
				jib.setWANGZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHENGCLX") != null)
				jib.setCHENGCLX(ResultArray.get(counter).get("CHENGCLX").toString());
			else
				jib.setCHENGCLX("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHIGRS") != null)
				jib.setZHIGRS(ResultArray.get(counter).get("ZHIGRS").toString());
			else
				jib.setZHIGRS("&nbsp;");

			if (ResultArray.get(counter).get("GJJSRYS") != null)
				jib.setGJJSRYS(ResultArray.get(counter).get("GJJSRYS").toString());
			else
				jib.setGJJSRYS("&nbsp;");
			
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
	 * Search hospital information by given parameters
	 * @param params The parameters used to search the Hospital
	 * @param num The number where the result start in result set
	 * @return The hospital infmation.
	 */
	public ArrayList<Hospital> search(String[] params, int num){
		int low = 0;
		int up = 100000;
		
		if(params[3].length()>0 && params[4].length()>0){
			low = Integer.parseInt(params[3]);
			up = Integer.parseInt(params[4]);
		}
		
		String sqlName = "select distinct YIYMC from BJWSZY_YIYUAN where YIYMC like '%" + params[0] + "%' or YIYBM like '%" + params[0] + "%'";
		String sqlLevel = "select distinct YIYMC from BJWSZY_YIYUAN where YIYDJ like '%" + params[1] + "%'";
		String sqlDisease = "select distinct YIYMC from BJWSZY_YIYUAN where TESZK like '%" + params[2] + "%'";
	//	String sqlTime = "select distinct YIYMC from BJWSZY_YIYUAN where CHUANGWS > " + low + " and CHUANGWS < " + up;
		String sql = "select BJWSZY_YIYUAN.*, ROWNUM RN from BJWSZY_YIYUAN where YIYMC in(";
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
			sql = sql + "(" + sqlLevel + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
/*		if(params[3].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	*/
		sql += ")";
		
		sql = "select * from (" +
		  	sql + 
		  	" and ROWNUM <= " + (num + 20) +
		  ")" +
		  "where RN > " + num;	
		
		logger.info("查询：" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<Hospital> jibList = new ArrayList<Hospital>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Hospital jib = new Hospital();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");

			if (ResultArray.get(counter).get("YIYMC") != null)
				jib.setYIYMC(ResultArray.get(counter).get("YIYMC").toString());
			else
				jib.setYIYMC("&nbsp;");

			if (ResultArray.get(counter).get("YIYBM") != null)
				jib.setYIYBM(ResultArray.get(counter).get("YIYBM").toString());
			else
				jib.setYIYBM("&nbsp;");

			if (ResultArray.get(counter).get("SUOSDQ") != null)
				jib.setSUOSDQ(ResultArray.get(counter).get("SUOSDQ").toString());
			else
				jib.setSUOSDQ("&nbsp;");

			if (ResultArray.get(counter).get("DANWXZ") != null)
				jib.setDANWXZ(ResultArray.get(counter).get("DANWXZ").toString());
			else
				jib.setDANWXZ("&nbsp;");

			if (ResultArray.get(counter).get("YIYDZ") != null)
				jib.setYIYDZ(ResultArray.get(counter).get("YIYDZ").toString());
			else
				jib.setYIYDZ("&nbsp;");
			
			if (ResultArray.get(counter).get("YOUZBM") != null)
				jib.setYOUZBM(ResultArray.get(counter).get("YOUZBM").toString());
			else
				jib.setYOUZBM("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUANGWS") != null)
				jib.setCHUANGWS(ResultArray.get(counter).get("CHUANGWS").toString());
			else
				jib.setCHUANGWS("&nbsp;");
			
			if (ResultArray.get(counter).get("RIMZL") != null)
				jib.setRIMZL(ResultArray.get(counter).get("RIMZL").toString());
			else
				jib.setRIMZL("&nbsp;");
			
			if (ResultArray.get(counter).get("YIYDJ") != null)
				jib.setYIYDJ(ResultArray.get(counter).get("YIYDJ").toString());
			else
				jib.setYIYDJ("&nbsp;");

			if (ResultArray.get(counter).get("YUANZ") != null)
				jib.setYUANZ(ResultArray.get(counter).get("YUANZ").toString());
			else
				jib.setYUANZ("&nbsp;");
			
			if (ResultArray.get(counter).get("KES") != null)
				jib.setKES(ResultArray.get(counter).get("KES").toString());
			else
				jib.setKES("&nbsp;");
			
			if (ResultArray.get(counter).get("TESZK") != null)
				jib.setTESZK(ResultArray.get(counter).get("TESZK").toString());
			else
				jib.setTESZK("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUYSB") != null)
				jib.setZHUYSB(ResultArray.get(counter).get("ZHUYSB").toString());
			else
				jib.setZHUYSB("&nbsp;");	
			
			if (ResultArray.get(counter).get("YOUX") != null)
				jib.setYOUX(ResultArray.get(counter).get("YOUX").toString());
			else
				jib.setYOUX("&nbsp;");
			
			if (ResultArray.get(counter).get("WANGZ") != null)
				jib.setWANGZ(ResultArray.get(counter).get("WANGZ").toString());
			else
				jib.setWANGZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHENGCLX") != null)
				jib.setCHENGCLX(ResultArray.get(counter).get("CHENGCLX").toString());
			else
				jib.setCHENGCLX("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHIGRS") != null)
				jib.setZHIGRS(ResultArray.get(counter).get("ZHIGRS").toString());
			else
				jib.setZHIGRS("&nbsp;");

			if (ResultArray.get(counter).get("GJJSRYS") != null)
				jib.setGJJSRYS(ResultArray.get(counter).get("GJJSRYS").toString());
			else
				jib.setGJJSRYS("&nbsp;");
			
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
	
	//通过多重条件来搜索疾病
	public int getSearchCount(String[] params){
		int low = 0;
		int up = 100000;
		
		if(params[3].length()>0 && params[4].length()>0){
			low = Integer.parseInt(params[3]);
			up = Integer.parseInt(params[4]);
		}
		
		String sqlName = "select distinct YIYMC from BJWSZY_YIYUAN where YIYMC like '%" + params[0] + "%' or YIYBM like '%" + params[0] + "%'";
		String sqlLevel = "select distinct YIYMC from BJWSZY_YIYUAN where YIYDJ like '%" + params[1] + "%'";
		String sqlDisease = "select distinct YIYMC from BJWSZY_YIYUAN where TESZK like '%" + params[2] + "%'";
		String sqlTime = "select distinct YIYMC from BJWSZY_YIYUAN where CHUANGWS > " + low + " and CHUANGWS < " + up;
		String sql = "select count(*) COUNT from BJWSZY_YIYUAN where YIYMC in(";
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
			sql = sql + "(" + sqlLevel + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length()>0 && params[4].length()>0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		logger.info("查询医院数量：" + sql);
		return Integer.parseInt(conn.DBSelect(sql).get(0).get("COUNT").toString());
	}
	
	/**
	 * 根据疾病名称搜出所要的属性
	 * @param attrName 要搜索的属性名称
 	 * @param name 作为查询条件的疾病名称
	 * @return 搜索结果属性列表
	 */
	public ArrayList<String> searchEleByName(String attrName, String name){
		String sql = "select " + attrName  + " from BJWSZY_YIYUAN where YIYMC like '" + name + "'";
		ArrayList<LinkedHashMap> resultSet = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		resultSet = conn.DBSelect(sql);
		ArrayList<String> jib = new ArrayList<String>();
		for(int i = 0; i < resultSet.size(); i++){
			jib.add(resultSet.get(i).get(attrName).toString());
		}
		
		return jib;
	}
	
	/**
	 * 根据疾病名称搜出所要的属性
	 * @param attrName 要搜索的属性名称
 	 * @param name 作为查询条件的疾病名称
	 * @return 搜索结果属性列表
	 */
	public ArrayList<String> searchTSZK(String name){
		String sql = "select TESZK from BJWSZY_YIYUAN where YIYMC like '" + name + "'";
		ArrayList<LinkedHashMap> resultSet = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		
		resultSet = conn.DBSelect(sql);
		ArrayList<String> jib = new ArrayList<String>();
		String[] tmp;
		
		for(int i = 0; i < resultSet.size(); i++){
			if(resultSet.get(i).get("TESZK") == null)
				continue;
			
			tmp = resultSet.get(i).get("TESZK").toString().split("、");
			
			for(int j = 0; j < tmp.length; j++){
				jib.add(tmp[j]);
			}
		}
		
		return jib;
	}

	/**
	 * 根据指定的sql语句搜索出对应的医院信息
	 * @param sql  查询语句
	 * @return 医院信息列表
	 */
	public ArrayList<Hospital> searchHospital(String sql){	
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<Hospital> jibList = new ArrayList<Hospital>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Hospital jib = new Hospital();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");

			if (ResultArray.get(counter).get("YIYMC") != null)
				jib.setYIYMC(ResultArray.get(counter).get("YIYMC").toString());
			else
				jib.setYIYMC("&nbsp;");

			if (ResultArray.get(counter).get("YIYBM") != null)
				jib.setYIYBM(ResultArray.get(counter).get("YIYBM").toString());
			else
				jib.setYIYBM("&nbsp;");

			if (ResultArray.get(counter).get("SUOSDQ") != null)
				jib.setSUOSDQ(ResultArray.get(counter).get("SUOSDQ").toString());
			else
				jib.setSUOSDQ("&nbsp;");

			if (ResultArray.get(counter).get("DANWXZ") != null)
				jib.setDANWXZ(ResultArray.get(counter).get("DANWXZ").toString());
			else
				jib.setDANWXZ("&nbsp;");

			if (ResultArray.get(counter).get("YIYDZ") != null)
				jib.setYIYDZ(ResultArray.get(counter).get("YIYDZ").toString());
			else
				jib.setYIYDZ("&nbsp;");
			
			if (ResultArray.get(counter).get("YOUZBM") != null)
				jib.setYOUZBM(ResultArray.get(counter).get("YOUZBM").toString());
			else
				jib.setYOUZBM("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");
			
			if (ResultArray.get(counter).get("RIMZL") != null)
				jib.setRIMZL(ResultArray.get(counter).get("RIMZL").toString());
			else
				jib.setRIMZL("&nbsp;");
			
			if (ResultArray.get(counter).get("YIYDJ") != null)
				jib.setYIYDJ(ResultArray.get(counter).get("YIYDJ").toString());
			else
				jib.setYIYDJ("&nbsp;");

			if (ResultArray.get(counter).get("YUANZ") != null)
				jib.setYUANZ(ResultArray.get(counter).get("YUANZ").toString());
			else
				jib.setYUANZ("&nbsp;");
			
			if (ResultArray.get(counter).get("KES") != null)
				jib.setKES(ResultArray.get(counter).get("KES").toString());
			else
				jib.setKES("&nbsp;");
			
			if (ResultArray.get(counter).get("TESZK") != null)
				jib.setTESZK(ResultArray.get(counter).get("TESZK").toString());
			else
				jib.setTESZK("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUYSB") != null)
				jib.setZHUYSB(ResultArray.get(counter).get("ZHUYSB").toString());
			else
				jib.setZHUYSB("&nbsp;");	
			
			if (ResultArray.get(counter).get("YOUX") != null)
				jib.setYOUX(ResultArray.get(counter).get("YOUX").toString());
			else
				jib.setYOUX("&nbsp;");
			
			if (ResultArray.get(counter).get("WANGZ") != null)
				jib.setWANGZ(ResultArray.get(counter).get("WANGZ").toString());
			else
				jib.setWANGZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHENGCLX") != null)
				jib.setCHENGCLX(ResultArray.get(counter).get("CHENGCLX").toString());
			else
				jib.setCHENGCLX("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHIGRS") != null)
				jib.setZHIGRS(ResultArray.get(counter).get("ZHIGRS").toString());
			else
				jib.setZHIGRS("&nbsp;");

			if (ResultArray.get(counter).get("GJJSRYS") != null)
				jib.setGJJSRYS(ResultArray.get(counter).get("GJJSRYS").toString());
			else
				jib.setGJJSRYS("&nbsp;");
			
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
	 * Search hospital information by given parameters
	 * @param params The parameters used to search the Hospital
	 * @param count The count of the hospital to search.
	 * @param num The number where the result start in result set
	 * @return The hospital infmation.
	 */
	public ArrayList<HospitalInf> searchHospitalInf(String[] params, int count, int num){
		int low = 0;
		int up = 100000;
		
		if(params[3].length()>0 && params[4].length()>0){
			low = Integer.parseInt(params[3]);
			up = Integer.parseInt(params[4]);
		}
		
		String sqlName = "select distinct YIYMC from BJWSZY_YIYUAN where YIYMC like '%" + params[0] + "%' or YIYBM like '%" + params[0] + "%'";
		String sqlLevel = "select distinct YIYMC from BJWSZY_YIYUAN where YIYDJ like '%" + params[1] + "%'";
		String sqlDisease = "select distinct YIYMC from BJWSZY_YIYUAN where TESZK like '%" + params[2] + "%'";
		String sqlTime = "select distinct YIYMC from BJWSZY_YIYUAN where CHUANGWS > " + low + " and CHUANGWS < " + up;
		String sql = "select BJWSZY_YIYUAN.*, ROWNUM RN from BJWSZY_YIYUAN where YIYMC in(";
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
			sql = sql + "(" + sqlLevel + ")";
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
			logger.error("无效的查询参数！");
			return new ArrayList<HospitalInf>();
		}
		
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询医院信息：" + sql);
		ResultArray = conn.DBSelect(sql);

		ArrayList<HospitalInf> jibList = new ArrayList<HospitalInf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			HospitalInf jib = new HospitalInf();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setId(ResultArray.get(counter).get("ID").toString());
			else
				jib.setId("&nbsp;");

			if (ResultArray.get(counter).get("YIYMC") != null)
				jib.setName(ResultArray.get(counter).get("YIYMC").toString());
			else
				jib.setName("&nbsp;");

			jib.setCorrelation(1);
			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * Select Hospital infmation by disease search parameters.
	 * @param params The parameters need to search the disease.
	 * @param count The count of the hospital.
	 * @param num The number of the hospital at.
	 * @return Hospital informaion.
	 * The sql is like that:
	 * select * from(
		  select id, suosyy, hospital_correlation, rownum rn
		  from( 
		    select id, suosyy, hospital_correlation
		    from(
		     select suosyy, sum(sum_correlation) as hospital_correlation
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
		      )
		      where correlation_id = bjwszy_renyuan.id
		      group by suosyy
		    ),bjwszy_yiyuan
		    where suosyy = yiymc
		    order by hospital_correlation desc
		  )
		  where rownum <= 50
		)
	   where rn > 30
	 */
	public ArrayList<HospitalInf> searchHospitalInfByDisease(ArrayList<DiseaseInf> disease, int count, int num){
		/*The sql is like that
		select * from(
		  select id, suosyy, hospital_correlation, rownum rn
		  from( 
		    select id, suosyy, hospital_correlation
		    from(
		     select suosyy, sum(sum_correlation) as hospital_correlation
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
		      )
		      where correlation_id = bjwszy_renyuan.id
		      group by suosyy
		    ),bjwszy_yiyuan
		    where suosyy = yiymc
		    order by hospital_correlation desc
		  )
		  where rownum <= 50
		)
		where rn > 30*/
		ArrayList<HospitalInf> result = new ArrayList<HospitalInf>();
//		If there is no disease,then return a empty list.
		if(disease.size() <= 0){
			return result;
		}
		
		StringBuffer sql = new StringBuffer(" select * from(  " +
												" select id, suosyy, hospital_correlation, rownum rn " +
												" from( " +
													" select id, suosyy, hospital_correlation" +
													" from( " +
														" select suosyy, sum(sum_correlation) as hospital_correlation " +
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
			   				" )" + 
			   				" where correlation_id = bjwszy_renyuan.id " +
				   			" group by suosyy " +
			   			" ),bjwszy_yiyuan " +
			   			" where suosyy = yiymc " +
			   			" order by hospital_correlation desc " +
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
			HospitalInf tmp = new HospitalInf();
			
			if (resultArray.get(i).get("SUOSYY") != null)
				tmp.setName(resultArray.get(i).get("SUOSYY").toString());
			else
				tmp.setName("数据库信息缺失！");
			
			tmp.setId(resultArray.get(i).get("ID").toString());
			tmp.setCorrelation(Integer.parseInt(resultArray.get(i).get("HOSPITAL_CORRELATION").toString()) );
			
			result.add(tmp);
		}
		
		return result;
	}
	
	/**
	 * Select Hospital infmation by disease search parameters.
	 * @param params The parameters need to search the disease.
	 * @param count The count of the hospital.
	 * @param num The number of the hospital at.
	 * @return Hospital informaion.
	 * The sql is like that:
	   select count(distinct id) as sum_hospital
	   from(
		 select suosyy, sum(sum_correlation) as hospital_correlation
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
		  )
		  where correlation_id = bjwszy_renyuan.id
		  group by suosyy
		),bjwszy_yiyuan
	   where suosyy = yiymc
	 */
	public int searchHospitalCountByDisease(ArrayList<DiseaseInf> disease){
		/*The sql is like that
		   select count(distinct id) as sum_hospital
		   from(
			 select suosyy, sum(sum_correlation) as hospital_correlation
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
			  )
			  where correlation_id = bjwszy_renyuan.id
			  group by suosyy
			),bjwszy_yiyuan
		   where suosyy = yiymc*/
//		If there is no disease,then return a empty list.
		if(disease.size() <= 0){
			return 0;
		}
		StringBuffer sql = new StringBuffer(" select count(distinct id) as sum_hospital " +
											" from( " +
												" select suosyy, sum(sum_correlation) as hospital_correlation " +
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
	    
		sql.append(	   	   " ) " +
			   			   " group by ID " +
		   				" )" + 
		   				" where correlation_id = bjwszy_renyuan.id " +
			   			" group by suosyy " +
		   			" ),bjwszy_yiyuan " +
		   			" where suosyy = yiymc ");
		
		//Get expert information from the database
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		logger.info("查询：" + sql.toString());
		
		return Integer.parseInt((conn.DBSelect(sql.toString())).get(0).get("SUM_HOSPITAL").toString());
	}
	
	/**
	 * Search hospital inf by given expert search parameters
	 * @param params The parameters to search expert
	 * @param num The number of the hospital this search get.
	 * @param count The count of the hospital.
	 * @return The result hospital infmation.
	 */
	public ArrayList<HospitalInf> searchHospitalInfByExpert(String[] params, int count, int num){
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		String sqlName = "select distinct XINGM from BJWSZY_RENYUAN where XINGM like '%" + params[0] + "%'";
		String sqlSection = "select distinct XINGM from BJWSZY_RENYUAN where SUOZKS like '%" + params[1] + "%'";
		String sqlDisease = "select distinct XINGM from BJWSZY_RENYUAN where ZHUZJB like '%" + params[2] + "%'";
		String sqlTime = "select distinct XINGM from BJWSZY_RENYUAN where MENZSJ like '%" + params[3] + "%'";
		String sql = " select count(BJWSZY_RENYUAN.ID) CORRELATION, SUOSYY" +
					 " from BJWSZY_RENYUAN, BJWSZY_YIYUAN" +
					 " where YIYMC = SUOSYY and" +
					 " XINGM in(";
		
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
		if(params[3].length()>0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		sql = " select * " +
			  " from(" +
				  " select ID, YIYMC, CORRELATION, rownum rn " +
				  " from (" +
				  	 	sql + 
				  	 	" group by SUOSYY " +
				  " ), BJWSZY_YIYUAN " +
				  " where YIYMC = SUOSYY " +
				  " and rownum <= " + (count + num) +
				  " order by CORRELATION desc" +
			  " )" +
			  " where rn >" + num;	
			
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return new ArrayList<HospitalInf>();
		}
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		ArrayList<HospitalInf> result = new ArrayList<HospitalInf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			HospitalInf jib = new HospitalInf();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setId(ResultArray.get(counter).get("ID").toString());
			else
				jib.setId("&nbsp;");
			
			if (ResultArray.get(counter).get("YIYMC") != null)
				jib.setName(ResultArray.get(counter).get("YIYMC").toString());
			else
				jib.setName("&nbsp;");
			
			if (ResultArray.get(counter).get("CORRELATION") != null)
				jib.setCorrelation(Integer.parseInt(ResultArray.get(counter).get("CORRELATION").toString()));
			else
				jib.setCorrelation(-1);
	
			result.add(jib);
		}
		
		return result;
	}
	
	/**
	 * Search hospital inf by given expert search parameters
	 * @param ExpertInf The parameters to search expert
	 * @return The result hospital infmation.
	 */
	public ArrayList<HospitalInf> searchHospitalInfByExpert(ExpertInf expert, int num, int count){
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		String sql = " select BJWSZY_YIYUAN.*, ROWNUM RN " +
					 " from BJWSZY_RENYUAN, BJWSZY_YIYUAN" +
					 " where YIYMC = SUOSYY " +
					 " and BJWSZY_RENYUAN.ID = " + expert.getId();
			
		sql = " select * " +
			  " from(" +
					  sql + 
					 " and rownum <= " + (count + num) + ")"
					   +	
			  " where RN >" + num;	
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		ArrayList<HospitalInf> result = new ArrayList<HospitalInf>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			HospitalInf jib = new HospitalInf();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setId(ResultArray.get(counter).get("ID").toString());
			else
				jib.setId("&nbsp;");
			
			if (ResultArray.get(counter).get("YIYMC") != null)
				jib.setName(ResultArray.get(counter).get("YIYMC").toString());
			else
				jib.setName("&nbsp;");
			
			if (ResultArray.get(counter).get("CORRELATION") != null)
				jib.setCorrelation(Integer.parseInt(ResultArray.get(counter).get("CORRELATION").toString()));
			else
				jib.setCorrelation(-1);
	
			result.add(jib);
		}
		
		return result;
	}
	
	/**
	 * Search hospital inf by given expert search parameters
	 * @param ExpertInf The parameters to search expert
	 * @return The result hospital count.
	 */
	public int searchHospitalInfCountByExpert(ExpertInf expert){
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		String sql = " select count(BJWSZY_YIYUAN.ID) SUMNUM " +
					 " from BJWSZY_RENYUAN, BJWSZY_YIYUAN" +
					 " where YIYMC = SUOSYY " +
					 " and BJWSZY_RENYUAN.ID = " + expert.getId();
			
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		int result = 0;
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("SUMNUM") != null){
				result = Integer.parseInt(ResultArray.get(counter).get("SUMNUM").toString()); 
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Search hospital inf by given expert search parameters
	 * @param params The parameters to search expert
	 * @param num The number of the hospital this search get.
	 * @param count The count of the hospital.
	 * @return The result hospital count.
	 */
	public int searchHospitalCountByExpert(String[] params){
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		String sqlName = "select distinct XINGM from BJWSZY_RENYUAN where XINGM like '%" + params[0] + "%'";
		String sqlSection = "select distinct XINGM from BJWSZY_RENYUAN where SUOZKS like '%" + params[1] + "%'";
		String sqlDisease = "select distinct XINGM from BJWSZY_RENYUAN where ZHUZJB like '%" + params[2] + "%'";
		String sqlTime = "select distinct XINGM from BJWSZY_RENYUAN where MENZSJ like '%" + params[3] + "%'";
		String sql = " select count(BJWSZY_RENYUAN.ID) CORRELATION, SUOSYY" +
					 " from BJWSZY_RENYUAN, BJWSZY_YIYUAN" +
					 " where YIYMC = SUOSYY and" +
					 " XINGM in(";
		
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
		if(params[3].length()>0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		sql = " select count(id) SUMNUM " +
			  " from " +
			  " (" +
			  	 sql + 
			  	 " group by SUOSYY " +
			  " ), BJWSZY_YIYUAN" +
			  " where YIYMC = SUOSYY ";	
			
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return 0;
		}
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("SUMNUM") != null)
				return Integer.parseInt(ResultArray.get(counter).get("SUMNUM").toString());
			else 
				return 0;
		}
		
		return 0;
	}
	
	/**
	 * Search hospital inf by given expert search parameters
	 * @param params The parameters to search expert
	 * @param num The number of the hospital this search get.
	 * @param count The count of the hospital.
	 * @return The result hospital count.
	 */
	public ArrayList<ExpertInf> searchExpertInfByHospital(String[] params, int num, int count){
		int low = 0;
		int up = 1000000;
		
		if(params[3].length()>0 && params[4].length()>0){
			low = Integer.parseInt(params[3]);
			up = Integer.parseInt(params[4]);
		}
		
		String sqlName = "select distinct YIYMC from BJWSZY_YIYUAN where YIYMC like '%" + params[0] + "%' or YIYBM like '%" + params[0] + "%'";
		String sqlLevel = "select distinct YIYMC from BJWSZY_YIYUAN where YIYDJ like '%" + params[1] + "%'";
		String sqlDisease = "select distinct YIYMC from BJWSZY_YIYUAN where TESZK like '%" + params[2] + "%'";
		String sqlTime = "select distinct YIYMC from BJWSZY_YIYUAN where CHUANGWS > " + low + " and CHUANGWS < " + up;
		String sql = " select BJWSZY_RENYUAN.ID, BJWSZY_RENYUAN.XINGM, ZHUZJB, SUOSYY, ROWNUM RN " +
					 " from BJWSZY_RENYUAN, BJWSZY_YIYUAN " +
					 " where " +
					 " SUOSYY=YIYMC and " +
					 " YIYMC in(";
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
			sql = sql + "(" + sqlLevel + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length()>0 && params[4].length()>0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		sql = " select * from (" +
		  		 sql + 
		  		 " and ROWNUM <= " + (num + count) +
		  	  " )" +
		  	  " where RN > " + num;	
		
		if(!flag){
			logger.error("无效的查询参数！");
			return new ArrayList<ExpertInf>();
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("根据医院的查询参数查询专家信息：" + sql);
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
			
			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setDisease(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setDisease("&nbsp;");
			
			jib.setCorrelation(1);
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * Search hospital inf by given expert search parameters
	 * @param params The parameters to search expert
	 * @param num The number of the hospital this search get.
	 * @param count The count of the hospital.
	 * @return The result hospital count.
	 */
	public int searchExpertCountByHospital(String[] params){
		int low = 0;
		int up = 1000000;
		
		if(params[3].length()>0 && params[4].length()>0){
			low = Integer.parseInt(params[3]);
			up = Integer.parseInt(params[4]);
		}
		
		String sqlName = "select distinct YIYMC from BJWSZY_YIYUAN where YIYMC like '%" + params[0] + "%' or YIYBM like '%" + params[0] + "%'";
		String sqlLevel = "select distinct YIYMC from BJWSZY_YIYUAN where YIYDJ like '%" + params[1] + "%'";
		String sqlDisease = "select distinct YIYMC from BJWSZY_YIYUAN where TESZK like '%" + params[2] + "%'";
		String sqlTime = "select distinct YIYMC from BJWSZY_YIYUAN where CHUANGWS > " + low + " and CHUANGWS < " + up;
		String sql = " select count(BJWSZY_RENYUAN.ID) EXPERTSUM " +
					 " from BJWSZY_RENYUAN, BJWSZY_YIYUAN " +
					 " where " +
					 " SUOSYY=YIYMC and " +
					 " YIYMC in(";
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
			sql = sql + "(" + sqlLevel + ")";
		}
		if(params[2].length() > 0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlDisease + ")";
		}			
		if(params[3].length()>0 && params[4].length()>0){
			if(flag){
				sql = sql + "intersect";
			}
			flag = true;
			sql = sql + "(" + sqlTime + ")";
		}	
		sql += ")";
		
		if(!flag){
			logger.error("无效的查询参数！");
			return 0;
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("根据医院的查询参数查询相关专家的数量：" + sql);
		ResultArray = conn.DBSelect(sql);

		for (int counter = 0; counter < ResultArray.size(); counter++){
			if (ResultArray.get(counter).get("EXPERTSUM") != null)
				return Integer.parseInt( ResultArray.get(counter).get("EXPERTSUM").toString() );
			else
				return 0;
		}
		
		logger.error("根据医院的查询参数查询相关专家的数量时没有得到有效结果");
		return 0;
	}
	
	/**
	 * Search hospital's relate disease by given parameters
	 * @param params The parameters
	 * @param num The number of the disease.
	 * @param count The count of the disease. 
	 * @return The result disease.
	 */
	public ArrayList<DiseaseInf> searchDiseaseInfByHospital(String[] params, int num, int count){
		ApplicationContext factory = GetFactory.getFactory();
		LoadConf conf = (LoadConf)factory.getBean("searchConf");
		Iterator iterator;
		
		int expertCount = conf.getExpertCount();
		int diseaseExpertCount = conf.getDiseaseExpertCount();
		
		ArrayList<ExpertInf> expertInfArray = searchExpertInfByHospital(params, 0, expertCount);
		
		LinkedHashMap<String, Integer> tmpDiseases = new LinkedHashMap<String, Integer>();
		ArrayList<DiseaseInf> result = new ArrayList<DiseaseInf>();
		
		for (int counter = 0; counter < expertInfArray.size(); counter++) {
			String[] tmpStrs;
			
			if(expertInfArray.get(counter).getDisease().equals("$nbsp;"))
				continue;
			
			tmpStrs = expertInfArray.get(counter).getDisease().split(",");
			
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
	 * Search hospital's relate disease count by given parameters
	 * @param params The parameters
	 * @return The result disease count.
	 */
	public int searchDiseaseCountByHospital(String[] params){
		ApplicationContext factory = GetFactory.getFactory();
		LoadConf conf = (LoadConf)factory.getBean("searchConf");

		int expertCount = conf.getExpertCount();
		int diseaseExpertCount = conf.getDiseaseExpertCount();
		
		ArrayList<ExpertInf> expertInfArray = searchExpertInfByHospital(params, 0, expertCount);	
		LinkedHashMap<String, Integer> tmpDiseases = new LinkedHashMap<String, Integer>();
		
		int sum = 0;
		for (int counter = 0; counter < expertInfArray.size(); counter++) {
			String[] tmpStrs;
			
			tmpStrs = expertInfArray.get(counter).getDisease().split(",");
			for(int i = 0; i < tmpStrs.length; i++){
				if(!tmpDiseases.containsKey(tmpStrs[i])){
					sum++;
					tmpDiseases.put(tmpStrs[i], 1);
				}
				
				if(sum >= diseaseExpertCount)
					break;
			}
		}
		
		return sum;
	}
	
	/**
	 * Search hospital's relate disease by given parameters
	 * @param params The parameters
	 * @param num The number of the disease.
	 * @param count The count of the disease. 
	 * @return The result disease.
	 */
	public ArrayList<DiseaseInf> searchDiseaseInfByHospitalAttr(String attrName, String attrValue, int num, int count){
		ApplicationContext factory = GetFactory.getFactory();
		LoadConf conf = (LoadConf)factory.getBean("searchConf");
		Iterator iterator;
		
		int expertCount = conf.getExpertCount();
		int diseaseExpertCount = conf.getDiseaseExpertCount();
		
		ArrayList<ExpertInf> expertInfArray = searchExpertInfByHospitalAttr(attrName, attrValue, 0, expertCount);
		
		LinkedHashMap<String, Integer> tmpDiseases = new LinkedHashMap<String, Integer>();
		ArrayList<DiseaseInf> result = new ArrayList<DiseaseInf>();
		
		for (int counter = 0; counter < expertInfArray.size(); counter++) {
			String[] tmpStrs;
			
			if(expertInfArray.get(counter).getDisease().equals("$nbsp;"))
				continue;
			
			tmpStrs = expertInfArray.get(counter).getDisease().split(",");
			
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
	 * Search hospital's relate disease by given attrName and attrValue
	 * @param params The parameters
	 * @param num The number of the disease.
	 * @param count The count of the disease. 
	 * @return The result count.
	 */
	public int searchDiseaseCountByHospitalAttr(String attrName, String attrValue){
		ApplicationContext factory = GetFactory.getFactory();
		LoadConf conf = (LoadConf)factory.getBean("searchConf");

		int expertCount = conf.getExpertCount();
		int diseaseExpertCount = conf.getDiseaseExpertCount();
		
		ArrayList<ExpertInf> expertInfArray = searchExpertInfByHospitalAttr(attrName, attrValue, 0, expertCount);	
		LinkedHashMap<String, Integer> tmpDiseases = new LinkedHashMap<String, Integer>();
		
		int sum = 0;
		for (int counter = 0; counter < expertInfArray.size(); counter++) {
			String[] tmpStrs;
			
			tmpStrs = expertInfArray.get(counter).getDisease().split(",");
			for(int i = 0; i < tmpStrs.length; i++){
				if(!tmpDiseases.containsKey(tmpStrs[i])){
					sum++;
					tmpDiseases.put(tmpStrs[i], 1);
				}
				
				if(sum >= diseaseExpertCount)
					break;
			}
		}
		
		return sum;
	}
	
	/**
	 * Search hospital inf by given expert attrValue
	 * @param attrName The parameters to search expert
	 * @param attrValue The parameters to search expert
	 * @param num The number of the hospital this search get.
	 * @param count The count of the hospital.
	 * @return The result Expert Inf.
	 */
	public ArrayList<ExpertInf> searchExpertInfByHospitalAttr(String attrName, String attrValue, int num, int count){
		String sql = " select BJWSZY_RENYUAN.ID, BJWSZY_RENYUAN.XINGM, ZHUZJB, SUOSYY, ROWNUM RN " +
					 " from BJWSZY_RENYUAN, BJWSZY_YIYUAN " +
					 " where " +
					 " SUOSYY=YIYMC and " +
					 " BJWSZY_YIYUAN." + attrName + " like " + attrValue;
		
		sql = " select * from (" +
		  		 sql + 
		  		 " and ROWNUM <= " + (num + count) +
		  	  " )" +
		  	  " where RN > " + num;	
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("根据医院的查询参数查询专家信息：" + sql);
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
			
			if (ResultArray.get(counter).get("ZHUZJB") != null)
				jib.setDisease(ResultArray.get(counter).get("ZHUZJB").toString());
			else
				jib.setDisease("&nbsp;");
			
			jib.setCorrelation(1);
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	/**
	 * Search hospital inf by given expert attrValue
	 * @param attrName The parameters to search expert
	 * @param attrValue The parameters to search expert
	 * @param num The number of the hospital this search get.
	 * @param count The count of the hospital.
	 * @return The result Expert Count.
	 */
	public int searchExpertCountByHospitalAttr(String attrName, String attrValue){
		String sql = " select count(BJWSZY_RENYUAN.ID) EXPERTSUM" +
					 " from BJWSZY_RENYUAN, BJWSZY_YIYUAN " +
					 " where " +
					 " SUOSYY=YIYMC and " +
					 " BJWSZY_YIYUAN." + attrName + " like " + attrValue;
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("根据医院的查询参数查询相关专家的数量：" + sql);
		ResultArray = conn.DBSelect(sql);

		for (int counter = 0; counter < ResultArray.size(); counter++){
			if (ResultArray.get(counter).get("EXPERTSUM") != null)
				return Integer.parseInt( ResultArray.get(counter).get("EXPERTSUM").toString() );
			else
				return 0;
		}
		
		logger.error("根据医院的查询参数查询相关专家的数量时没有得到有效结果");
		return 0;
	}
	
	/**
	 * Search disease by given attribute.
	 * @param attrName The attribute's name in database. 
	 * @param attrValue The attribute's value.
	 * @param num The number of the attrubute.
	 * @return The data result, one colum a time.
	 */
	public DataReader searchByAttr(String attrName, String attrValue, int num){
		String sql = " select BJWSZY_YIYUAN.*, rownum RN from BJWSZY_YIYUAN " +
					 " where " + attrName + " like '" + attrValue + "'";
		sql = "select * from (" +
			   	sql + 
			  ")" +
			  "where RN = " + num;		
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn) factory.getBean("singleDBConn");
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		DataReader result = null;
		
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			Hospital jib = new Hospital();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setID(ResultArray.get(counter).get("ID").toString());
			else
				jib.setID("&nbsp;");

			if (ResultArray.get(counter).get("SJZSDW") != null)
				jib.setSJZSDW(ResultArray.get(counter).get("SJZSDW").toString());
			else
				jib.setSJZSDW("&nbsp;");

			if (ResultArray.get(counter).get("YIYMC") != null)
				jib.setYIYMC(ResultArray.get(counter).get("YIYMC").toString());
			else
				jib.setYIYMC("&nbsp;");

			if (ResultArray.get(counter).get("YIYBM") != null)
				jib.setYIYBM(ResultArray.get(counter).get("YIYBM").toString());
			else
				jib.setYIYBM("&nbsp;");

			if (ResultArray.get(counter).get("SUOSDQ") != null)
				jib.setSUOSDQ(ResultArray.get(counter).get("SUOSDQ").toString());
			else
				jib.setSUOSDQ("&nbsp;");

			if (ResultArray.get(counter).get("DANWXZ") != null)
				jib.setDANWXZ(ResultArray.get(counter).get("DANWXZ").toString());
			else
				jib.setDANWXZ("&nbsp;");

			if (ResultArray.get(counter).get("YIYDZ") != null)
				jib.setYIYDZ(ResultArray.get(counter).get("YIYDZ").toString());
			else
				jib.setYIYDZ("&nbsp;");
			
			if (ResultArray.get(counter).get("YOUZBM") != null)
				jib.setYOUZBM(ResultArray.get(counter).get("YOUZBM").toString());
			else
				jib.setYOUZBM("&nbsp;");
			
			if (ResultArray.get(counter).get("LIANXDH") != null)
				jib.setLIANXDH(ResultArray.get(counter).get("LIANXDH").toString());
			else
				jib.setLIANXDH("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUANGWS") != null)
				jib.setCHUANGWS(ResultArray.get(counter).get("CHUANGWS").toString());
			else
				jib.setCHUANGWS("&nbsp;");
			
			if (ResultArray.get(counter).get("RIMZL") != null)
				jib.setRIMZL(ResultArray.get(counter).get("RIMZL").toString());
			else
				jib.setRIMZL("&nbsp;");
			
			if (ResultArray.get(counter).get("YIYDJ") != null)
				jib.setYIYDJ(ResultArray.get(counter).get("YIYDJ").toString());
			else
				jib.setYIYDJ("&nbsp;");

			if (ResultArray.get(counter).get("YUANZ") != null)
				jib.setYUANZ(ResultArray.get(counter).get("YUANZ").toString());
			else
				jib.setYUANZ("&nbsp;");
			
			if (ResultArray.get(counter).get("KES") != null)
				jib.setKES(ResultArray.get(counter).get("KES").toString());
			else
				jib.setKES("&nbsp;");
			
			if (ResultArray.get(counter).get("TESZK") != null)
				jib.setTESZK(ResultArray.get(counter).get("TESZK").toString());
			else
				jib.setTESZK("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHUYSB") != null)
				jib.setZHUYSB(ResultArray.get(counter).get("ZHUYSB").toString());
			else
				jib.setZHUYSB("&nbsp;");	
			
			if (ResultArray.get(counter).get("YOUX") != null)
				jib.setYOUX(ResultArray.get(counter).get("YOUX").toString());
			else
				jib.setYOUX("&nbsp;");
			
			if (ResultArray.get(counter).get("WANGZ") != null)
				jib.setWANGZ(ResultArray.get(counter).get("WANGZ").toString());
			else
				jib.setWANGZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHENGCLX") != null)
				jib.setCHENGCLX(ResultArray.get(counter).get("CHENGCLX").toString());
			else
				jib.setCHENGCLX("&nbsp;");
			
			if (ResultArray.get(counter).get("ZHIGRS") != null)
				jib.setZHIGRS(ResultArray.get(counter).get("ZHIGRS").toString());
			else
				jib.setZHIGRS("&nbsp;");

			if (ResultArray.get(counter).get("GJJSRYS") != null)
				jib.setGJJSRYS(ResultArray.get(counter).get("GJJSRYS").toString());
			else
				jib.setGJJSRYS("&nbsp;");
			
			if (ResultArray.get(counter).get("BEIZ") != null)
				jib.setBEIZ(ResultArray.get(counter).get("BEIZ").toString());
			else
				jib.setBEIZ("&nbsp;");
			
			if (ResultArray.get(counter).get("CHUC") != null)
				jib.setCHUC(ResultArray.get(counter).get("CHUC").toString());
			else
				jib.setCHUC("&nbsp;");
			
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
		String sql = " select count(ID) NUMSUM from BJWSZY_YIYUAN" +
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
