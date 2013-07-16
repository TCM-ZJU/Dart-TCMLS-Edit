package cn.edu.zju.ccnt.TFGW.dwr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.DAO.ExpertDAO;
import cn.edu.zju.ccnt.TFGW.DAO.HospitalDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jmz.JMZ_JBDAO;
import cn.edu.zju.ccnt.TFGW.DAO.YJYADAO;
import cn.edu.zju.ccnt.TFGW.DAO.ZCFGDAO;
import cn.edu.zju.ccnt.TFGW.DAO.ZDFZDAO;
import cn.edu.zju.ccnt.TFGW.DAO.ZTC_TREEDAO;
import cn.edu.zju.ccnt.TFGW.DAO.ZYBFZDAO;
import cn.edu.zju.ccnt.TFGW.DAO.ZYBZZLBZDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_AMZLDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_QTZLDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_XYBYDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_XYZLDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_ZGZLDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_ZHENHDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_ZJZLDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_ZYBYDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_ZYZLDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_jibDAO;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.innerHTML.CreateTabHTML;
import cn.edu.zju.ccnt.TFGW.object.jib.C_jib;
import cn.edu.zju.ccnt.TFGW.object.jmz.JMZ_JB;


public class QueryData {
	static Logger logger = Logger.getLogger(QueryData.class.getName());	
	private ApplicationContext factory = GetFactory.getFactory();
	
	public void initial(){
	}
	
	/**
	 * 查找疾病数据�
	 * @param args 查找参数，是一个长度为4的数组
	 * @return 生成的疾病表，每个表是一个字符串
	 */
	public String[] searchJib(String[] args){
		logger.info("搜索参数:" + args.toString());
		//得到Spring容器
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		int maxLength;
		String[] table = new String[7];
	
		//在数据中搜索数据,并生成相应的页面
		//搜索中医疾病数据�?:搜索疾病名称、病因�?�症状�?�症�?
		C_jibDAO jibDAO = (C_jibDAO)factory.getBean("C_JIBDAO");
		maxLength = jibDAO.getSearchCount(args);
		
		table[0] = appendSearchBar(tabCreator.CreateC_JIBTab(jibDAO.search(args, 0), 0), "JIB", maxLength, 0);
		//搜索急门诊疾病数据库:搜索疾病名称,症状,症�??
		if((args[0].length() + args[2].length() + args[3].length()) > 0){
			JMZ_JBDAO jmzDAO = (JMZ_JBDAO)factory.getBean("JMZ_JBDAO");
			maxLength = jmzDAO.getSearchCount(args);
			
			table[1] = appendSearchBar(tabCreator.CreateJMZ_JBTab(jmzDAO.search(args, 0), 0), "JMZ", maxLength, 0);	
		}
		//搜索常见中毒防治数据�?:搜索疾病名称,病因,症状
		if((args[0].length() + args[1].length() + args[2].length()) > 0){
			ZDFZDAO zdfzDAO = (ZDFZDAO)factory.getBean("ZDFZDAO");
			maxLength = zdfzDAO.getSearchCount(args);
			
			table[2] = appendSearchBar(tabCreator.CreateZDFZTab(zdfzDAO.search(args, 0), 0), "ZDFZ", maxLength, 0);
		}
		//搜索常见职业病防治数据库:仅搜索疾病名�?
		if(args[0].length() > 0){
			ZYBFZDAO zybfzDAO = (ZYBFZDAO)factory.getBean("ZYBFZDAO");
			maxLength = zybfzDAO.getSearchCount(args);
			
			table[3] = appendSearchBar(tabCreator.CreateZYBFZTab(zybfzDAO.search(args, 0), 0), "ZYBFZ", maxLength, 0);
		}		
		//搜索突发公卫政策法规数据�?:仅搜索疾病名�?
		if(args[0].length() > 0){
			ZCFGDAO zcfgDAO = (ZCFGDAO)factory.getBean("ZCFGDAO");
			maxLength = zcfgDAO.getSearchCount(args);
			
			table[4] = appendSearchBar(tabCreator.CreateZCFGTab(zcfgDAO.search(args, 0), 0), "ZCFG", maxLength, 0);
		}
		//搜索突发公卫应�?�预案数据库:搜索疾病名称、症�?
		if((args[0].length() + args[2].length()) > 0){
			YJYADAO yjyaDAO = (YJYADAO)factory.getBean("YJYADAO");
			maxLength = yjyaDAO.getSearchCount(args);
			
			table[5] = appendSearchBar(tabCreator.CreateYJYATab(yjyaDAO.search(args, 0), 0), "YJYA", maxLength, 0);
		}		
		//搜索中医病症诊疗标准数据�?:搜索疾病名称、病因�?�症状�?�症�?
		ZYBZZLBZDAO zybzzlbzDAO = (ZYBZZLBZDAO)factory.getBean("ZYBZZLBZDAO");
		maxLength = zybzzlbzDAO.getSearchCount(args);
		
		table[6] = appendSearchBar(tabCreator.CreateZYBZZLBZTab(zybzzlbzDAO.search(args, 0), 0), "ZLBZ", maxLength, 0);
		
		return table;
	}
	
	
	/**
	 * 查找疾病数据�
	 * @param args 查找参数，是一个长度为4的数组
	 * @return 生成的疾病表，每个表是一个字符串
	 */
	public String searchSingleJib(String[] args, String type, String rowNum){
		logger.info("搜索参数�?:" + args.toString());
		
		int num = Integer.parseInt(rowNum);
		
		//得到Spring容器
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		int maxLength;
	
		//在数据中搜索数据,并生成相应的页面
		//搜索中医疾病数据�?:搜索疾病名称、病因�?�症状�?�症�?
		try{
			if(type.equals("JIB")){
				C_jibDAO jibDAO = (C_jibDAO)factory.getBean("C_JIBDAO");
				maxLength = jibDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateC_JIBTab(jibDAO.search(args, num), num), "JIB", maxLength, num);
			}
			//搜索急门诊疾病数据库:搜索疾病名称,症状,症�??
			else if(type.equals("JMZ")){
				JMZ_JBDAO jmzDAO = (JMZ_JBDAO)factory.getBean("JMZ_JBDAO");
				maxLength = jmzDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateJMZ_JBTab(jmzDAO.search(args, num), num), "JMZ", maxLength, num);	
			}
			//搜索常见中毒防治数据�?:搜索疾病名称,病因,症状
			else if(type.equals("ZDFZ")){
				ZDFZDAO zdfzDAO = (ZDFZDAO)factory.getBean("ZDFZDAO");
				maxLength = zdfzDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateZDFZTab(zdfzDAO.search(args, num), num), "ZDFZ", maxLength, num);
			}
			//搜索常见职业病防治数据库:仅搜索疾病名�?
			else if(type.equals("ZYBFZ")){
				ZYBFZDAO zybfzDAO = (ZYBFZDAO)factory.getBean("ZYBFZDAO");
				maxLength = zybfzDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateZYBFZTab(zybfzDAO.search(args, num), num), "ZYBFZ", maxLength, num);
			}		
			//搜索突发公卫政策法规数据�?:仅搜索疾病名�?
			else if(type.equals("ZCFG")){
				ZCFGDAO zcfgDAO = (ZCFGDAO)factory.getBean("ZCFGDAO");
				maxLength = zcfgDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateZCFGTab(zcfgDAO.search(args, num), num), "ZCFG", maxLength, num);
			}
			//搜索突发公卫应�?�预案数据库:搜索疾病名称、症�?
			else if(type.equals("YJYA")){
				YJYADAO yjyaDAO = (YJYADAO)factory.getBean("YJYADAO");
				maxLength = yjyaDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateYJYATab(yjyaDAO.search(args, num), num), "YJYA", maxLength, num);
			}		
			//搜索中医病症诊疗标准数据�?:搜索疾病名称、病因�?�症状�?�症�?
			else if(type.equals("ZLBZ")){
				ZYBZZLBZDAO zybzzlbzDAO = (ZYBZZLBZDAO)factory.getBean("ZYBZZLBZDAO");
				maxLength = zybzzlbzDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateZYBZZLBZTab(zybzzlbzDAO.search(args, num), num), "ZLBZ", maxLength, num);
			}
			else if(type.equals("Expert")){
				ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
				maxLength = expertDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateExpertTab(expertDAO.search(args, num), num), "Expert", maxLength, num);
			}
			else if(type.equals("Hospital")){
				HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
				maxLength = hospitalDAO.getSearchCount(args);
				
				return appendSearchBar(tabCreator.CreateHospitalTab(hospitalDAO.search(args, num), num), "Hospital", maxLength, num);	
			}
		}catch(Exception e){
			logger.error("查询发生异常：" + e.getStackTrace());
			logger.error("错误原因：" + e.getCause());
			return "<p>查询发生异常<br>" + e.getCause() + " </p>";
		}
		
		return "<p>错误的类型信息！！！</p>";
	}
	
	/**
	 * 查找专家数据�?
	 * @param args
	 * @return
	 */
	public String searchExpert(String[] args){
		logger.info("搜索参数�?:" + args.toString());
		
		//得到Spring容器
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		
		//在数据中搜索数据,并生成相应的页面
		//搜索专家数据�?:搜索专家名称、所在科室，主治疾病、门诊时�?
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		expertDAO.getSearchCount(args);
		
		return tabCreator.CreateExpertTab(expertDAO.search(args), 0);
	}
	
	/**
	 * 查找医院数据�?
	 * @param args
	 * @return
	 */
	public String searchHospital(String[] args){
		logger.info("搜索参数�?:" + args.toString());
		int maxLength;
		
		//得到Spring容器
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		
		//在数据中搜索数据,并生成相应的页面
		//搜索专家数据�?:搜索专家名称、所在科室，主治疾病、门诊时�?
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		maxLength = hospitalDAO.getSearchCount(args);
		
		return tabCreator.CreateHospitalTab(hospitalDAO.search(args), 0);		
	}
	
	/**
	 * 查询类树展开的相关信�?
	 * @param DBName
	 * @param disease
	 * @return
	 */
	public String treeSearch(String DBName, String disease){
		String[] args = new String[4];
		args[0] = disease;
		args[1] = "";
		args[2] = "";
		args[3] = "";
		String table = "<p>错误的类型信息！！！</p>";
		
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		try{
			if(DBName.equals("C_JIB")){
				C_jibDAO jibDAO = (C_jibDAO)factory.getBean("C_JIBDAO");
				table = tabCreator.CreateC_JIBTab(jibDAO.search(args), 0);
			}
			else if(DBName.equals("JMZ_JB")){
				JMZ_JBDAO jmzDAO = (JMZ_JBDAO)factory.getBean("JMZ_JBDAO");
				table = tabCreator.CreateJMZ_JBTab(jmzDAO.search(args), 0);		
			}
			else if(DBName.equals("TFGW_ZDFZ")){
				ZDFZDAO zdfzDAO = (ZDFZDAO)factory.getBean("ZDFZDAO");
				table = tabCreator.CreateZDFZTab(zdfzDAO.search(args), 0);		
			}
			else if(DBName.equals("TFGW_ZYBFZ")){
				ZYBFZDAO zybfzDAO = (ZYBFZDAO)factory.getBean("ZYBFZDAO");
				table = tabCreator.CreateZYBFZTab(zybfzDAO.search(args), 0);		
			}
			else if(DBName.equals("TFGW_ZCFG")){
				ZCFGDAO zcfgDAO = (ZCFGDAO)factory.getBean("ZCFGDAO");
				table = tabCreator.CreateZCFGTab(zcfgDAO.search(args), 0);		
			}		
			else if(DBName.equals("TFGW_YJYA")){
				YJYADAO yjyaDAO = (YJYADAO)factory.getBean("YJYADAO");
				table = tabCreator.CreateYJYATab(yjyaDAO.search(args), 0);		
			}		
			else if(DBName.equals("ZYBZZLBZ")){
				ZYBZZLBZDAO zybzzlbzDAO = (ZYBZZLBZDAO)factory.getBean("ZYBZZLBZDAO");
				table = tabCreator.CreateZYBZZLBZTab(zybzzlbzDAO.search(args), 0);		
			}		
			else if(DBName.equals("BJWSZY_RENYUAN")){
				ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
				table = tabCreator.CreateExpertTab(expertDAO.searchByName(disease), 0);		
			}	
			else if(DBName.equals("BJWSZY_YIYUAN")){
				HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
				table = tabCreator.CreateHospitalTab(hospitalDAO.search(args), 0);		
			}	
		}catch(Exception e){
			logger.error("在查询" + DBName + "数据库时出现错误!<br>错误信息为：" + e.getCause());
			return "在查询" + DBName + "数据库时出现错误!<br>错误信息为：" + e.getCause();
		}
		
		return table;
	}
	
	/**
	 * 查找Clob数据
	 * @param DBName 数据库名�?
	 * @param IDName 数据表中id的名�?
	 * @param attribute �?要查询的属�??
	 * @param id 查询的具体id
	 * @return
	 */
	public String searchClob(String DBName, String IDName, String attribute, String id){
		String result = "";
		String sql = "select " + attribute + 
			" from " + DBName + 
			" where " + IDName + 
			" = '" + id + "'";
		
		ApplicationContext factory = GetFactory.getFactory();
		SingleDBConn conn = (SingleDBConn)factory.getBean("singleDBConn");
		try {
			logger.info("Clog sql=" + sql);
			result = conn.searchClob(sql);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 查找疾病数据库中的单表库
	 * @return
	 */
	public String searchJibInf(String searchType, String jibName){
		String table = "";
		
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		if(searchType.equals("jib.zhenH")){
			C_ZHENHDAO c_zhenHDAO = (C_ZHENHDAO)factory.getBean("C_ZHENHDAO");
			table = tabCreator.CreateC_ZhenHTab(c_zhenHDAO.searchZHByJib(jibName), 0);					
		}
		else if(searchType.equals("jib.zyby")){
			C_ZYBYDAO c_zybyDAO = (C_ZYBYDAO)factory.getBean("C_ZYBYDAO");
			table = tabCreator.CreateZYBYTab(c_zybyDAO.searchByJib(jibName), 0);					
		}
		else if(searchType.equals("jib.xyby")){
			C_XYBYDAO c_xybyDAO = (C_XYBYDAO)factory.getBean("C_XYBYDAO");
			table = tabCreator.CreateXYBYTab(c_xybyDAO.searchByJib(jibName), 0);					
		}
		else if(searchType.equals("jib.zyzl")){
			C_ZYZLDAO c_zyzlDAO = (C_ZYZLDAO)factory.getBean("C_ZYZLDAO");
			table = tabCreator.CreateZYZLTab(c_zyzlDAO.searchByJib(jibName), 0);					
		}
		else if(searchType.equals("jib.amzl")){
			C_AMZLDAO c_zmzlDAO = (C_AMZLDAO)factory.getBean("C_AMZLDAO");
			table = tabCreator.CreateAMZLTab(c_zmzlDAO.searchByJib(jibName), 0);					
		}
		else if(searchType.equals("jib.zgzl")){
			C_ZGZLDAO c_zgzlDAO = (C_ZGZLDAO)factory.getBean("C_ZGZLDAO");
			table = tabCreator.CreateZGZLTab(c_zgzlDAO.searchByJib(jibName), 0);					
		}
		else if(searchType.equals("jib.zjzl")){
			C_ZJZLDAO c_zjzlDAO = (C_ZJZLDAO)factory.getBean("C_ZJZLDAO");
			table = tabCreator.CreateZJZLTab(c_zjzlDAO.searchByJib(jibName), 0);					
		}
		else if(searchType.equals("jib.xyzl")){
			C_XYZLDAO c_xyzlDAO = (C_XYZLDAO)factory.getBean("C_XYZLDAO");
			table = tabCreator.CreateXYZLTab(c_xyzlDAO.searchByJib(jibName), 0);					
		}
		else if(searchType.equals("jib.qtzl")){
			C_QTZLDAO c_qtzlDAO = (C_QTZLDAO)factory.getBean("C_QTZLDAO");
			table = tabCreator.CreateQTZLTab(c_qtzlDAO.searchByJib(jibName), 0);					
		}
		
		return table;		
	}
	
	/**
	 * 根据指定的查询方式和查询数据库来查询疾病
	 * @param type 查询方式{Expert, Hospital}
	 * @param dbName 疾病数据库名
	 * @param searchName 查询的信息
	 * @param rowNum 查询行号
	 * @return
	 */
	public String searchSingleJibByType(String type, String dbName, String searchName, String rowNum){
		int num = Integer.parseInt(rowNum);
		String result = "";
		
		if(type.equals("Expert")){
			ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
			ArrayList<String> tmpJibList = new ArrayList<String>(expertDAO.searchEleByName("ZHUZJB",searchName));
			
			ArrayList<String> jibList = new ArrayList<String>();
			for(int i = 0; i < tmpJibList.size(); i++){
				String[] tmp = tmpJibList.get(0).split(",");
				for(int j = 0; j < tmp.length; j++){
					jibList.add(tmp[j]);
				}
			}
			
			return this.searchJibList(jibList, dbName, num);
		}
		else if(type.equals("Hospital")){
			return this.searchJibList(this.getJibListByHospitalName(searchName), dbName, num);			
		}
		
		return result;
	}
	
	/**
	 * 查找Clob数据
	 * @param DBName 数据库名�?
	 * @param IDName 数据表中id的名�?
	 * @param attribute �?要查询的属�??
	 * @param id 查询的具体id
	 * @return
	 */
	public String[] searchJibByExpert(String expertName){
		String result[] = new String[7];
		
		for(int i = 0; i < 7; i++){
			result[i] = "";
		}
		
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		ArrayList<String> tmpJibList = new ArrayList<String>(expertDAO.searchEleByName("ZHUZJB",expertName));
		
		ArrayList<String> jibList = new ArrayList<String>();
		for(int i = 0; i < tmpJibList.size(); i++){
			String[] tmp = tmpJibList.get(0).split(",");
			for(int j = 0; j < tmp.length; j++){
				jibList.add(tmp[j]);
			}
		}
		
		return this.searchJibList(jibList);
	}
	
	/**
	 * 查找Clob数据
	 * @param DBName 数据库名�?
	 * @param IDName 数据表中id的名�?
	 * @param attribute �?要查询的属�??
	 * @param id 查询的具体id
	 * @return
	 */
	public String[] searchJibByHospital(String name){
		return this.searchJibList(this.getJibListByHospitalName(name));
	}
	
	public String searchHospitalByExpert(String expertName){
		String result = "";
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		ArrayList<String> eleList = new ArrayList<String>(expertDAO.searchEleByName("SUOSYY",expertName));
		String[] args = new String[4];
		
		if(eleList.size() > 0){
			args[0] = eleList.get(0);
			args[1] = "";
			args[2] = "";
			args[3] = "";
			
			result = this.searchHospital(args);
		//	result = this.searchJibList((String[]) jibList.toArray());
		}

		return result;		
	}
	
	public String searchHospitalByJib(String jibName){
		String result = "";
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		
		String sql = "select distinct suosyy " +
					 "from bjwszy_renyuan " +
					 "where zhuzjb like " +
					 "'%" + jibName + "%'";
		
		String hospitalList = this.getSet(expertDAO.searchEleBySql("SUOSYY", sql));
		sql = "select * " +
			  "from bjwszy_yiyuan " +
			  "where yiymc in " +
			  hospitalList;
		
		result = tabCreator.CreateHospitalTab(hospitalDAO.searchHospital(sql), 0);

		return result;		
	}
	
	public String searchExpertByHospital(String hospitalName){
		String sql = "select * from BJWSZY_RENYUAN where SUOSYY like '" + hospitalName + "'";
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");

		
		return tabCreator.CreateExpertTab(expertDAO.searchExpert(sql), 0);		
	}
	
	/**
	 * 查找单个疾病数据
	 * @param jibList
	 * @param type
	 * @return
	 */
	private String searchJibList(ArrayList<String> jibList, String type, int num){
		//得到Spring容器
		String jibNames = this.getSet(jibList);
		int maxLength;
		String table = "";
		
		if(jibList.size() == 0){
			return table;
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		//在数据中搜索数据,并生成相应的页面
		//搜索中医疾病数据�?:搜索疾病名称、病因�?�症状�?�症�?
		if(type.equals("JIB")){
			C_jibDAO jibDAO = (C_jibDAO)factory.getBean("C_JIBDAO");
			maxLength = jibDAO.getSearchCountOfJibNames(jibNames);
			
			table = appendSearchBar(tabCreator.CreateC_JIBTab(jibDAO.searchByJibNames(jibNames, num), num), "JIB", maxLength, num);
		}
		//搜索急门诊疾病数据库:搜索疾病名称,症状,症�??	
		else if(type.equals("JMZ")){
			JMZ_JBDAO jmzDAO = (JMZ_JBDAO)factory.getBean("JMZ_JBDAO");
			maxLength = jmzDAO.getSearchCount(jibNames);
			
			table = appendSearchBar(tabCreator.CreateJMZ_JBTab(jmzDAO.searchByJibNames(jibNames, num), num), "JMZ", maxLength, num);	
		}
		//搜索常见中毒防治数据�?:搜索疾病名称,病因,症状
		else if(type.equals("ZDFZ")){
			ZDFZDAO zdfzDAO = (ZDFZDAO)factory.getBean("ZDFZDAO");
			maxLength = zdfzDAO.getSearchCountOfJibNames(jibNames);
			
			table = appendSearchBar(tabCreator.CreateZDFZTab(zdfzDAO.searchByJibNames(jibNames, num), num), "ZDFZ", maxLength, num);
		}
		//搜索常见职业病防治数据库:仅搜索疾病名�?
		else if(type.equals("ZYBFZ")){
			ZYBFZDAO zybfzDAO = (ZYBFZDAO)factory.getBean("ZYBFZDAO");
			maxLength = zybfzDAO.getSearchCountOfJibNames(jibNames);
			
			table = appendSearchBar(tabCreator.CreateZYBFZTab(zybfzDAO.searchByJibNames(jibNames, num), num), "ZYBFZ", maxLength, num);
		}
		//搜索突发公卫政策法规数据�?:仅搜索疾病名�?
		else if(type.equals("ZCFG")){
			ZCFGDAO zcfgDAO = (ZCFGDAO)factory.getBean("ZCFGDAO");
			maxLength = zcfgDAO.getSearchCountOfJibNames(jibNames);
			
			table = appendSearchBar(tabCreator.CreateZCFGTab(zcfgDAO.searchByJibNames(jibNames, num, 20), num), "ZCFG", maxLength, num);
		}
		//搜索突发公卫应�?�预案数据库:搜索疾病名称、症�?
		else if(type.equals("YJYA")){
			YJYADAO yjyaDAO = (YJYADAO)factory.getBean("YJYADAO");
			maxLength = yjyaDAO.getSearchCountOfJibNames(jibNames);
			
			table = appendSearchBar(tabCreator.CreateYJYATab(yjyaDAO.searchByJibNames(jibNames, num), num), "YJYA", maxLength, num);
		}
		//搜索中医病症诊疗标准数据�?:搜索疾病名称、病因�?�症状�?�症�?
		else if(type.equals("ZLBZ")){
			ZYBZZLBZDAO zybzzlbzDAO = (ZYBZZLBZDAO)factory.getBean("ZYBZZLBZDAO");
			maxLength = zybzzlbzDAO.getSearchCountOfJibNames(jibNames);
			
			table = appendSearchBar(tabCreator.CreateZYBZZLBZTab(zybzzlbzDAO.searchByJibNames(jibNames, num, 20), num), "ZLBZ", maxLength, num);
		}
		
		return table;
	}
	
	/**
	 * 查找疾病数据�
	 * @param args
	 * @return
	 */
	private String[] searchJibList(String[] jibList){
		//得到Spring容器
		String jibNames = this.getSet(jibList);
		int maxLength;
		String[] table = new String[7];
		for(int j = 0; j < 7; j++){
			table[j] = "";
		}
		
		if(jibList.length == 0){
			return table;
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		
		//在数据中搜索数据,并生成相应的页面
		//搜索中医疾病数据�?:搜索疾病名称、病因�?�症状�?�症�?
		C_jibDAO jibDAO = (C_jibDAO)factory.getBean("C_JIBDAO");
		maxLength = jibDAO.getSearchCountOfJibNames(jibNames);
		
		table[0] = appendSearchBar(tabCreator.CreateC_JIBTab(jibDAO.searchByJibNames(jibNames, 0), 0), "JIB", maxLength, 0);
		
		//搜索急门诊疾病数据库:搜索疾病名称,症状,症�??	
		JMZ_JBDAO jmzDAO = (JMZ_JBDAO)factory.getBean("JMZ_JBDAO");
		maxLength = jmzDAO.getSearchCount(jibNames);
		
		table[1] = appendSearchBar(tabCreator.CreateJMZ_JBTab(jmzDAO.searchByJibNames(jibNames, 0), 0), "JMZ", maxLength, 0);	
		
		//搜索常见中毒防治数据�?:搜索疾病名称,病因,症状
		
		ZDFZDAO zdfzDAO = (ZDFZDAO)factory.getBean("ZDFZDAO");
		maxLength = zdfzDAO.getSearchCountOfJibNames(jibNames);
		
		table[2] = appendSearchBar(tabCreator.CreateZDFZTab(zdfzDAO.searchByJibNames(jibNames, 0), 0), "ZDFZ", maxLength, 0);
		
		//搜索常见职业病防治数据库:仅搜索疾病名�?
		ZYBFZDAO zybfzDAO = (ZYBFZDAO)factory.getBean("ZYBFZDAO");
		maxLength = zybfzDAO.getSearchCountOfJibNames(jibNames);
		
		table[3] = appendSearchBar(tabCreator.CreateZYBFZTab(zybfzDAO.searchByJibNames(jibNames, 0), 0), "ZYBFZ", maxLength, 0);
			
		//搜索突发公卫政策法规数据�?:仅搜索疾病名�?
		ZCFGDAO zcfgDAO = (ZCFGDAO)factory.getBean("ZCFGDAO");
		maxLength = zcfgDAO.getSearchCountOfJibNames(jibNames);
		
		table[4] = appendSearchBar(tabCreator.CreateZCFGTab(zcfgDAO.searchByJibNames(jibNames, 0, 20), 0), "ZCFG", maxLength, 0);
		
		//搜索突发公卫应�?�预案数据库:搜索疾病名称、症�?
		YJYADAO yjyaDAO = (YJYADAO)factory.getBean("YJYADAO");
		maxLength = yjyaDAO.getSearchCountOfJibNames(jibNames);
		
		table[5] = appendSearchBar(tabCreator.CreateYJYATab(yjyaDAO.searchByJibNames(jibNames, 0), 0), "YJYA", maxLength, 0);
		
		//搜索中医病症诊疗标准数据�?:搜索疾病名称、病因�?�症状�?�症�?
		ZYBZZLBZDAO zybzzlbzDAO = (ZYBZZLBZDAO)factory.getBean("ZYBZZLBZDAO");
		maxLength = zybzzlbzDAO.getSearchCountOfJibNames(jibNames);
		
		table[6] = appendSearchBar(tabCreator.CreateZYBZZLBZTab(zybzzlbzDAO.searchByJibNames(jibNames, 0, 20), 0), "ZLBZ", maxLength, 0);
		
		return table;
	}
	
	/**
	 * 查找疾病数据�
	 * @param args
	 * @return
	 */
	private String[] searchJibList(ArrayList<String> jibList){
		//得到Spring容器
		String jibNames = this.getSet(jibList);
		int maxLength;
		String[] table = new String[7];
		for(int j = 0; j < 7; j++){
			table[j] = "";
		}
		
		if(jibList.size() == 0){
			return table;
		}
		
		ApplicationContext factory = GetFactory.getFactory();
		CreateTabHTML tabCreator = (CreateTabHTML)factory.getBean("createTabHTML");
		
		//在数据中搜索数据,并生成相应的页面
		//搜索中医疾病数据�?:搜索疾病名称、病因�?�症状�?�症�?
		C_jibDAO jibDAO = (C_jibDAO)factory.getBean("C_JIBDAO");
		maxLength = jibDAO.getSearchCountOfJibNames(jibNames);
		
		table[0] = appendSearchBar(tabCreator.CreateC_JIBTab(jibDAO.searchByJibNames(jibNames, 0), 0), "JIB", maxLength, 0);
		
		//搜索急门诊疾病数据库:搜索疾病名称,症状,症�??	
		JMZ_JBDAO jmzDAO = (JMZ_JBDAO)factory.getBean("JMZ_JBDAO");
		maxLength = jmzDAO.getSearchCount(jibNames);
		
		table[1] = appendSearchBar(tabCreator.CreateJMZ_JBTab(jmzDAO.searchByJibNames(jibNames, 0), 0), "JMZ", maxLength, 0);	
		
		//搜索常见中毒防治数据�?:搜索疾病名称,病因,症状
		
		ZDFZDAO zdfzDAO = (ZDFZDAO)factory.getBean("ZDFZDAO");
		maxLength = zdfzDAO.getSearchCountOfJibNames(jibNames);
		
		table[2] = appendSearchBar(tabCreator.CreateZDFZTab(zdfzDAO.searchByJibNames(jibNames, 0), 0), "ZDFZ", maxLength, 0);
		
		//搜索常见职业病防治数据库:仅搜索疾病名�?
		ZYBFZDAO zybfzDAO = (ZYBFZDAO)factory.getBean("ZYBFZDAO");
		maxLength = zybfzDAO.getSearchCountOfJibNames(jibNames);
		
		table[3] = appendSearchBar(tabCreator.CreateZYBFZTab(zybfzDAO.searchByJibNames(jibNames, 0), 0), "ZYBFZ", maxLength, 0);
			
		//搜索突发公卫政策法规数据�?:仅搜索疾病名�?
		ZCFGDAO zcfgDAO = (ZCFGDAO)factory.getBean("ZCFGDAO");
		maxLength = zcfgDAO.getSearchCountOfJibNames(jibNames);
		
		table[4] = appendSearchBar(tabCreator.CreateZCFGTab(zcfgDAO.searchByJibNames(jibNames, 0, 20), 0), "ZCFG", maxLength, 0);
		
		//搜索突发公卫应�?�预案数据库:搜索疾病名称、症�?
		YJYADAO yjyaDAO = (YJYADAO)factory.getBean("YJYADAO");
		maxLength = yjyaDAO.getSearchCountOfJibNames(jibNames);
		
		table[5] = appendSearchBar(tabCreator.CreateYJYATab(yjyaDAO.searchByJibNames(jibNames, 0), 0), "YJYA", maxLength, 0);
		
		//搜索中医病症诊疗标准数据�?:搜索疾病名称、病因�?�症状�?�症�?
		ZYBZZLBZDAO zybzzlbzDAO = (ZYBZZLBZDAO)factory.getBean("ZYBZZLBZDAO");
		maxLength = zybzzlbzDAO.getSearchCountOfJibNames(jibNames);
		
		table[6] = appendSearchBar(tabCreator.CreateZYBZZLBZTab(zybzzlbzDAO.searchByJibNames(jibNames, 0, 20), 0), "ZLBZ", maxLength, 0);
		
		return table;
	}
	
	/**
	 * 为生成的表格添加表头
	 * @param dbHTML 生成的表格文件
	 * @param type 表格对应的数据库种类
	 * @param maxLength 查询结果的长度
	 * @param num 现在的查询位置
	 * @return
	 */
	private String appendSearchBar(String dbHTML, String type, int maxLength, int num){
		String rowInf = "<tr align=center class=\"dataInfRow\"><td>共有数据" + maxLength + "项 分" + (maxLength/20 + 1) + "页 现在是第" + (num/20 + 1) + "/" + (maxLength/20 + 1) + "页</td></tr>";
		String rowCtrl = "<tr class=\"dataCtrlRow\" align=center>" +
							"<td>" +
								"<input type=\"button\" style=\"cursor:hand\" value=\"首  页\" onclick=searchByNum(\"" + type + "\"," + maxLength + "," + 0 + ")>&nbsp;&nbsp;&nbsp;&nbsp;</input>" +
								"<input type=\"button\" style=\"cursor:hand\" value=\"上一页\" onclick=searchBefore(\"" + type + "\"," + maxLength + ")>&nbsp;&nbsp;&nbsp;&nbsp;</input>" +
								"<input type=\"button\" style=\"cursor:hand\" value=\"下一页\" onclick=searchNext(\"" + type + "\"," + maxLength + ")>&nbsp;&nbsp;&nbsp;&nbsp;</input>" + 
								"<input type=\"button\" style=\"cursor:hand\" value=\"尾  页\" onclick=searchByNum(\"" + type + "\"," + maxLength + "," + (maxLength - maxLength%20) + ")></input>" + 
							"</td>"  +
						 "</tr>";
		
		String infBefore = "<table width=\"100%\">" + rowInf + rowCtrl + "<tr valign=\"top\"><td>";
		
		String infAfter = "</td></tr></table>";
		
		return infBefore + dbHTML + infAfter;
	}
	
	private String getSet(ArrayList<String> list){
		if(list.size() <= 0){
			return "('')";
		}
		StringBuffer result = new StringBuffer("('" + list.get(0) + "'");
		
		for(int i = 1; i < list.size() && i < 1000;i++){
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
		
		for(int i = 1; i < list.length && i < 1000;i++){
			result.append(",'" + list[i] + "'");
		}
		result.append(")");
		
		return result.toString();
	}
	
	private ArrayList<String> getJibListByHospitalName(String name){
		String sql = "select ZHUZJB from BJWSZY_RENYUAN where SUOSYY like '" + name + "'";
		ApplicationContext factory = GetFactory.getFactory();
		
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		ArrayList<String> tmpJibList = expertDAO.searchEleBySql("ZHUZJB", sql);
		
		LinkedHashMap<String, Integer> jibList = new LinkedHashMap<String, Integer>();
		for(int i = 0; i < tmpJibList.size(); i++){
			String[] tmp = tmpJibList.get(i).split(",");
			for(int j = 0; j < tmp.length; j++){
				if(jibList.containsKey(tmp[j])){
					jibList.put(tmp[j], jibList.get(tmp[j]) + 1);
				}
				else{
					jibList.put(tmp[j], 1);
				}
			}
		}
		
		TreeMap<Integer, ArrayList<String>> jibs = new TreeMap<Integer, ArrayList<String>>();
		
		Iterator iterator = jibList.keySet().iterator();
		while(iterator.hasNext()){
			String tmp = (String) iterator.next();
			int freq = jibList.get(tmp);
			
			if(jibs.containsKey(-freq)){
				jibs.get(-freq).add(tmp);
			}
			else{
				jibs.put(-freq, new ArrayList<String>());
				jibs.get(-freq).add(tmp);
			}
		}
		
		ArrayList<String> resultJibs = new ArrayList<String>();
		iterator = jibs.keySet().iterator();
		for(int i = 0; i < 30 && iterator.hasNext();){
			Integer next = (Integer) iterator.next();
			
			resultJibs.addAll(jibs.get(next));
			i += jibs.get(next).size();
		}
		
		return resultJibs;
	}
}


