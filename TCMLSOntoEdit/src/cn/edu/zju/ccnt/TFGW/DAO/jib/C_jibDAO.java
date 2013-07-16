package cn.edu.zju.ccnt.TFGW.DAO.jib;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DAO.DiseaseDAO;
import cn.edu.zju.ccnt.TFGW.DBConnect.JibDBConn;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.object.jib.C_jib;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.DiseaseInf;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;


public class C_jibDAO extends WebApplicationObjectSupport implements DiseaseDAO{	
	static Logger logger = Logger.getLogger(C_jibDAO.class.getName());	
	
	//通过多重条件来搜索疾病
	public ArrayList<C_jib> search(String[] params){
		String sqlDisease = "select distinct JBMC from C_JIB where JBMC like '%" + params[0] + "%' or YM like '%" + params[0] + "%'";
		String sqlBYC = "select distinct JBMC from C_ZHONGYBY where BYMC like '%" + params[1] + "%'";
		String sqlBYW = "select distinct JBMC from C_XIYBY where BYMC like '%" + params[1] + "%'";
		String sqlZZ = "select distinct JBMC from C_JIB where ZZMC like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from C_ZHENH where ZHMC like '%" + params[3] + "%'";
		String sql = "select * from C_JIB where JBMC in(";
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
			sql = sql + "(" + sqlBYC + ")";
			sql = sql + "union(" + sqlBYW + ")";
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
			return new ArrayList<C_jib>();
		}
		
		logger.info("查询：" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_jib> jibList = new ArrayList<C_jib>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_jib jib = new C_jib();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setId(ResultArray.get(counter).get("ID").toString());
			else
				jib.setId("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJbmc(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJbmc("&nbsp;");

			if (ResultArray.get(counter).get("YM") != null)
				jib.setYm(ResultArray.get(counter).get("YM").toString());
			else
				jib.setYm("&nbsp;");

			if (ResultArray.get(counter).get("FBFS") != null)
				jib.setFbfs(ResultArray.get(counter).get("FBFS").toString());
			else
				jib.setFbfs("&nbsp;");

			if (ResultArray.get(counter).get("YFB") != null)
				jib.setYfb(ResultArray.get(counter).get("YFB").toString());
			else
				jib.setYfb("&nbsp;");

			if (ResultArray.get(counter).get("BFZ") != null)
				jib.setBfz(ResultArray.get(counter).get("BFZ").toString());
			else
				jib.setBfz("&nbsp;");

			if (ResultArray.get(counter).get("ZZMC") != null)
				jib.setZzmc(ResultArray.get(counter).get("ZZMC").toString());
			else
				jib.setZzmc("&nbsp;");
			
			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBz(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBz("&nbsp;");
			
			if (ResultArray.get(counter).get("LRRY") != null)
				jib.setLrry(ResultArray.get(counter).get("LRRY").toString());
			else
				jib.setLrry("&nbsp;");
			
			if (ResultArray.get(counter).get("LRRQ") != null)
				jib.setLrrq(ResultArray.get(counter).get("LRRQ").toString());
			else
				jib.setLrrq("&nbsp;");
			
			if (ResultArray.get(counter).get("JGDW") != null)
				jib.setJgdw(ResultArray.get(counter).get("JGDW").toString());
			else
				jib.setJgdw("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
	//通过多重条件来搜索疾病
	public ArrayList<C_jib> searchByJibNames(String jibNames, int num){
		String sql = "select C_JIB.*, ROWNUM RN from C_JIB where JBMC in " + jibNames;
		
		sql = "select * from (" +
				  	sql + 
				  	" and ROWNUM <= " + (num + 20) +
				  ")" +
			  "where RN > " + num;	
		
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_jib> jibList = new ArrayList<C_jib>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_jib jib = new C_jib();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setId(ResultArray.get(counter).get("ID").toString());
			else
				jib.setId("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJbmc(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJbmc("&nbsp;");

			if (ResultArray.get(counter).get("YM") != null)
				jib.setYm(ResultArray.get(counter).get("YM").toString());
			else
				jib.setYm("&nbsp;");

			if (ResultArray.get(counter).get("FBFS") != null)
				jib.setFbfs(ResultArray.get(counter).get("FBFS").toString());
			else
				jib.setFbfs("&nbsp;");

			if (ResultArray.get(counter).get("YFB") != null)
				jib.setYfb(ResultArray.get(counter).get("YFB").toString());
			else
				jib.setYfb("&nbsp;");

			if (ResultArray.get(counter).get("BFZ") != null)
				jib.setBfz(ResultArray.get(counter).get("BFZ").toString());
			else
				jib.setBfz("&nbsp;");

			if (ResultArray.get(counter).get("ZZMC") != null)
				jib.setZzmc(ResultArray.get(counter).get("ZZMC").toString());
			else
				jib.setZzmc("&nbsp;");
			
			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBz(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBz("&nbsp;");
			
			if (ResultArray.get(counter).get("LRRY") != null)
				jib.setLrry(ResultArray.get(counter).get("LRRY").toString());
			else
				jib.setLrry("&nbsp;");
			
			if (ResultArray.get(counter).get("LRRQ") != null)
				jib.setLrrq(ResultArray.get(counter).get("LRRQ").toString());
			else
				jib.setLrrq("&nbsp;");
			
			if (ResultArray.get(counter).get("JGDW") != null)
				jib.setJgdw(ResultArray.get(counter).get("JGDW").toString());
			else
				jib.setJgdw("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}
	
    /**
     * Search disease by the given numer
     * @param params The parameters we used to search the disease
     * @param num The position where the disease start.
     * @return The disease which satisfy the condition.
     */
	public ArrayList<C_jib> search(String[] params, int num){
		String sqlDisease = "select distinct JBMC from C_JIB where JBMC like '%" + params[0] + "%' or YM like '%" + params[0] + "%'";
		String sqlBYC = "select distinct JBMC from C_ZHONGYBY where BYMC like '%" + params[1] + "%'";
		String sqlBYW = "select distinct JBMC from C_XIYBY where BYMC like '%" + params[1] + "%'";
		String sqlZZ = "select distinct JBMC from C_JIB where ZZMC like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from C_ZHENH where ZHMC like '%" + params[3] + "%'";
		String sql = "select C_JIB.*, ROWNUM RN from C_JIB where JBMC in(";
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
			sql = sql + "(" + sqlBYC + ")";
			sql = sql + "union(" + sqlBYW + ")";
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
			return new ArrayList<C_jib>();
		}
		
		sql = "select * from (" +
				  	sql + 
				  	" and ROWNUM <= " + (num + 20) +
				  ")" +
			  "where RN > " + num;	
		
		logger.info("查询：" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<C_jib> jibList = new ArrayList<C_jib>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_jib jib = new C_jib();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setId(ResultArray.get(counter).get("ID").toString());
			else
				jib.setId("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJbmc(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJbmc("&nbsp;");

			if (ResultArray.get(counter).get("YM") != null)
				jib.setYm(ResultArray.get(counter).get("YM").toString());
			else
				jib.setYm("&nbsp;");

			if (ResultArray.get(counter).get("FBFS") != null)
				jib.setFbfs(ResultArray.get(counter).get("FBFS").toString());
			else
				jib.setFbfs("&nbsp;");

			if (ResultArray.get(counter).get("YFB") != null)
				jib.setYfb(ResultArray.get(counter).get("YFB").toString());
			else
				jib.setYfb("&nbsp;");

			if (ResultArray.get(counter).get("BFZ") != null)
				jib.setBfz(ResultArray.get(counter).get("BFZ").toString());
			else
				jib.setBfz("&nbsp;");

			if (ResultArray.get(counter).get("ZZMC") != null)
				jib.setZzmc(ResultArray.get(counter).get("ZZMC").toString());
			else
				jib.setZzmc("&nbsp;");
			
			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBz(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBz("&nbsp;");
			
			if (ResultArray.get(counter).get("LRRY") != null)
				jib.setLrry(ResultArray.get(counter).get("LRRY").toString());
			else
				jib.setLrry("&nbsp;");
			
			if (ResultArray.get(counter).get("LRRQ") != null)
				jib.setLrrq(ResultArray.get(counter).get("LRRQ").toString());
			else
				jib.setLrrq("&nbsp;");
			
			if (ResultArray.get(counter).get("JGDW") != null)
				jib.setJgdw(ResultArray.get(counter).get("JGDW").toString());
			else
				jib.setJgdw("&nbsp;");
			
			jibList.add(jib);
		}
		
		return jibList;
	}

    /**
     * Search disease by the given numer
     * @param params The parameters we used to search the disease
     * @param num The position where the disease start.
     * @return The disease which satisfy the condition.
     */
	public ArrayList<DiseaseInf> searchDistinctDisease(String[] params, int num, int count){
		ArrayList<DiseaseInf> diseaseInf = new ArrayList<DiseaseInf>();
		String sqlDisease = "select distinct JBMC from C_JIB where JBMC like '%" + params[0] + "%' or YM like '%" + params[0] + "%'";
		String sqlBYC = "select distinct JBMC from C_ZHONGYBY where BYMC like '%" + params[1] + "%'";
		String sqlBYW = "select distinct JBMC from C_XIYBY where BYMC like '%" + params[1] + "%'";
		String sqlZZ = "select distinct JBMC from C_JIB where ZZMC like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from C_ZHENH where ZHMC like '%" + params[3] + "%'";
		String sql = "select JBMC, count(JBMC) JBMC_NUM from C_JIB where JBMC in(";
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
			sql = sql + "(" + sqlBYC + ")";
			sql = sql + "union(" + sqlBYW + ")";
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
		
		if(!flag){
			logger.info("无效的查询参数：" + params);
			return diseaseInf;
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		logger.info("查询：" + sql);
		ResultArray = conn.DBSelect(sql);

		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("JBMC") != null){
				diseaseInf.add(new DiseaseInf( ResultArray.get(counter).get("JBMC").toString(), 
						   	   Integer.parseInt(ResultArray.get(counter).get("JBMC_NUM").toString()),
						       "中医疾病数据库",
						       "-1" ));
			}
		}
		
		return diseaseInf;
	}
	
	//通过多重条件来搜索疾病
	public ArrayList<String> searchJibNames(String sql){
		logger.info("查询�?" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		ArrayList<String> jibList = new ArrayList<String>();
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			if (ResultArray.get(counter).get("JBMC") != null)
				jibList.add(ResultArray.get(counter).get("JBMC").toString());
		}
		
		return jibList;
	}
	
//	通过多重条件来搜索疾病
	public int getSearchCountOfJibNames(String names){
		String sql = "select COUNT(*) COUNT from C_JIB where JBMC in " + names;
		
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}
	
//	通过多重条件来搜索疾病
	public int getSearchCount(String[] params){
		String sqlDisease = "select distinct JBMC from C_JIB where JBMC like '%" + params[0] + "%' or YM like '%" + params[0] + "%'";
		String sqlBYC = "select distinct JBMC from C_ZHONGYBY where BYMC like '%" + params[1] + "%'";
		String sqlBYW = "select distinct JBMC from C_XIYBY where BYMC like '%" + params[1] + "%'";
		String sqlZZ = "select distinct JBMC from C_JIB where ZZMC like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from C_ZHENH where ZHMC like '%" + params[3] + "%'";
		String sql = "select COUNT(*) COUNT from C_JIB where JBMC in(";
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
			sql = sql + "(" + sqlBYC + ")";
			sql = sql + "union(" + sqlBYW + ")";
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
		
		logger.info("查询：" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}
	
	/**
	 * Get distinct disease count by given parameters
	 * @param params Given paramesters
	 * @return DistinctDiseaseCount
	 */
	public int getDistinctSearchCount(String[] params){
		String sqlDisease = "select distinct JBMC from C_JIB where JBMC like '%" + params[0] + "%' or YM like '%" + params[0] + "%'";
		String sqlBYC = "select distinct JBMC from C_ZHONGYBY where BYMC like '%" + params[1] + "%'";
		String sqlBYW = "select distinct JBMC from C_XIYBY where BYMC like '%" + params[1] + "%'";
		String sqlZZ = "select distinct JBMC from C_JIB where ZZMC like '%" + params[2] + "%'";		
		String sqlZH = "select distinct JBMC from C_ZHENH where ZHMC like '%" + params[3] + "%'";
		String sql = "select COUNT(distinct JBMC) COUNT from C_JIB where JBMC in(";
		
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
			sql = sql + "(" + sqlBYC + ")";
			sql = sql + "union(" + sqlBYW + ")";
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
		
		logger.info("查询：" + sql);
		
		logger.info(ClassLoader.getSystemResource(""));
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		return Integer.parseInt((conn.DBSelect(sql).get(0).get("COUNT").toString()));
	}
	
	/**
	 * Search disease by given attribute.
	 * @param attrName The attribute's name in database. 
	 * @param attrValue The attribute's value.
	 * @param num The number of the attribute.
	 * @return The data result, one column a time.
	 */
	public DataReader searchByAttr(String attrName, String attrValue, int num){
		String sql = " select C_JIB.*, ROWNUM RN from C_JIB " +
					 " where " + attrName + " like '" + attrValue + "'";
		
		sql = "select * from (" +
				  	sql + 
				  ")" +
			  "where RN = " + num;	
		
		logger.info("查询：" + sql);
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		ResultArray = conn.DBSelect(sql);

		DataReader result = null;
		for (int counter = 0; counter < ResultArray.size(); counter++) {
			C_jib jib = new C_jib();

			if (ResultArray.get(counter).get("ID") != null)
				jib.setId(ResultArray.get(counter).get("ID").toString());
			else
				jib.setId("&nbsp;");

			if (ResultArray.get(counter).get("JBMC") != null)
				jib.setJbmc(ResultArray.get(counter).get("JBMC").toString());
			else
				jib.setJbmc("&nbsp;");

			if (ResultArray.get(counter).get("YM") != null)
				jib.setYm(ResultArray.get(counter).get("YM").toString());
			else
				jib.setYm("&nbsp;");

			if (ResultArray.get(counter).get("FBFS") != null)
				jib.setFbfs(ResultArray.get(counter).get("FBFS").toString());
			else
				jib.setFbfs("&nbsp;");

			if (ResultArray.get(counter).get("YFB") != null)
				jib.setYfb(ResultArray.get(counter).get("YFB").toString());
			else
				jib.setYfb("&nbsp;");

			if (ResultArray.get(counter).get("BFZ") != null)
				jib.setBfz(ResultArray.get(counter).get("BFZ").toString());
			else
				jib.setBfz("&nbsp;");

			if (ResultArray.get(counter).get("ZZMC") != null)
				jib.setZzmc(ResultArray.get(counter).get("ZZMC").toString());
			else
				jib.setZzmc("&nbsp;");
			
			if (ResultArray.get(counter).get("BZ") != null)
				jib.setBz(ResultArray.get(counter).get("BZ").toString());
			else
				jib.setBz("&nbsp;");
			
			if (ResultArray.get(counter).get("LRRY") != null)
				jib.setLrry(ResultArray.get(counter).get("LRRY").toString());
			else
				jib.setLrry("&nbsp;");
			
			if (ResultArray.get(counter).get("LRRQ") != null)
				jib.setLrrq(ResultArray.get(counter).get("LRRQ").toString());
			else
				jib.setLrrq("&nbsp;");
			
			if (ResultArray.get(counter).get("JGDW") != null)
				jib.setJgdw(ResultArray.get(counter).get("JGDW").toString());
			else
				jib.setJgdw("&nbsp;");
			
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
		String sql = " select count(ID) NUMSUM from C_JIB " +
					 " where " + attrName + " like '" + attrValue + "'";
	
		ArrayList<LinkedHashMap> ResultArray = new ArrayList<LinkedHashMap>();
		
		ApplicationContext factory = GetFactory.getFactory();
		JibDBConn conn = (JibDBConn) factory.getBean("jibDBConn");
		
		logger.info("查询:" + sql);
		ResultArray = conn.DBSelect(sql);
		
		int sum = 0;
		if (ResultArray.get(0).get("NUMSUM") != null){
			sum = Integer.parseInt(ResultArray.get(0).get("NUMSUM").toString());
		}	
		
		return sum;
	}
}
