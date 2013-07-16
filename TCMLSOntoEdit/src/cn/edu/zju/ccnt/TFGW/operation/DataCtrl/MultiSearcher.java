package cn.edu.zju.ccnt.TFGW.operation.DataCtrl;

import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cn.edu.zju.ccnt.TFGW.GetFactory;
import cn.edu.zju.ccnt.TFGW.LoadConf;
import cn.edu.zju.ccnt.TFGW.DAO.ExpertDAO;
import cn.edu.zju.ccnt.TFGW.DAO.HospitalDAO;
import cn.edu.zju.ccnt.TFGW.DAO.YJYADAO;
import cn.edu.zju.ccnt.TFGW.DAO.ZDFZDAO;
import cn.edu.zju.ccnt.TFGW.DAO.ZYBFZDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jib.C_jibDAO;
import cn.edu.zju.ccnt.TFGW.DAO.jmz.JMZ_JBDAO;
import cn.edu.zju.ccnt.TFGW.DAO.*;
import cn.edu.zju.ccnt.TFGW.DBConnect.SingleDBConn;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.DataReader;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.DiseaseInf;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.ExpertInf;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.HospitalInf;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.TabInf;

/**
 * This class is used to do complex search.
 * @author ZhangXiaoGang 2008.9.3
 *
 */
public class MultiSearcher {
	static Logger logger = Logger.getLogger(MultiSearcher.class.getName());
	
	/**
	 * Get the disease table information
	 * @param params The search parameters
	 * @return The table information.
	 */
	public ArrayList<TabInf> searchTabInf(String[] params){
		ArrayList<TabInf> result = new ArrayList<TabInf>();
		ApplicationContext factory = GetFactory.getFactory();
		
		JMZ_JBDAO jmz_jbDAO = (JMZ_JBDAO) factory.getBean("JMZ_JBDAO");
/*		C_jibDAO c_jibDAO = (C_jibDAO) factory.getBean("C_JIBDAO")*/;
		ZDFZDAO zdfzDAO= (ZDFZDAO) factory.getBean("ZDFZDAO");
		ZYBFZDAO zybfzDAO= (ZYBFZDAO) factory.getBean("ZYBFZDAO");
		YJYADAO yjyaDAO= (YJYADAO) factory.getBean("YJYADAO");

		int jmzCount = jmz_jbDAO.getSearchCount(params);
		int distinctJmzCount = jmz_jbDAO.getDistinctSearchCount(params);
		
/*		int jibCount = c_jibDAO.getSearchCount(params);
		int distinctJibCount = c_jibDAO.getDistinctSearchCount(params);*/
		
		int zdfzCount = zdfzDAO.getSearchCount(params);
		int distinctZdfzCount = zdfzDAO.getDistinctSearchCount(params);
		
		int zybfzCount = zybfzDAO.getSearchCount(params);
		int distinctZybfzCount = zybfzDAO.getDisticntSearchCount(params);
		
		int yjyaCount = yjyaDAO.getSearchCount(params);
		int distinctYjyaCount = yjyaDAO.getDistinctSearchCount(params);
		
		if(jmzCount > 0){
			result.add(new TabInf("突发公卫信息", "JMZ_JB", jmzCount, distinctJmzCount));
		}
/*		if(jibCount > 0){
			result.add(new TabInf("中医基础疾病", "C_JIB", jibCount, distinctJibCount));
		}*/
		if(zdfzCount > 0){
			result.add(new TabInf("中毒防治信息", "ZDFZ", zdfzCount, distinctZdfzCount));
		}
		if(zybfzCount > 0){
			result.add(new TabInf("职业病信息", "ZYBFZ", zybfzCount, distinctZybfzCount));
		}
		if(yjyaCount > 0){
			result.add(new TabInf("应急预案", "YJYA", yjyaCount, distinctYjyaCount));
		}
		
		return result;
	}
	
	/**
	 * Get table information by given attribute, this is precision search
	 * @param attrName Search attrName, because the attrName are not the same, this value in fact is not used.
	 * @param attrValue Search attrValue
	 * @return Table information
	 */
	public ArrayList<TabInf> searchTabInfByAttr(String attrName, String attrValue){
		ArrayList<TabInf> result = new ArrayList<TabInf>();

		int distinctJmzCount = this.searchDataCount("JMZ_JB", "JBMC", attrValue);
		int jmzCount = distinctJmzCount;
		
/*		int distinctJibCount =this.searchDataCount("C_JIB", "JBMC", attrValue);
		int jibCount = distinctJibCount;*/
	
		int distinctZdfzCount = this.searchDataCount("ZDFZ", "JIBMC", attrValue);
		int zdfzCount = distinctZdfzCount;
		
		int distinctZybfzCount =this.searchDataCount("ZYBFZ", "JIBMC", attrValue);
		int zybfzCount = distinctZybfzCount;
		
		int distinctYjyaCount = this.searchDataCount("YJYA", "JIBMC", attrValue);
		int yjyaCount = distinctYjyaCount;
		
		if(jmzCount > 0){
			result.add(new TabInf("突发公卫信息", "JMZ_JB", jmzCount, distinctJmzCount));
		}
/*		if(jibCount > 0){
			result.add(new TabInf("中医基础疾病", "C_JIB", jibCount, distinctJibCount));
		}*/
		if(zdfzCount > 0){
			result.add(new TabInf("中毒防治信息", "ZDFZ", zdfzCount, distinctZdfzCount));
		}
		if(zybfzCount > 0){
			result.add(new TabInf("职业病信息", "ZYBFZ", zybfzCount, distinctZybfzCount));
		}
		if(yjyaCount > 0){
			result.add(new TabInf("应急预案", "YJYA", yjyaCount, distinctYjyaCount));
		}
		
		return result;
	}
	
	/**
	 * Get the disease table information by default search value
	 * @attrValue The default search value
	 * @return The table information.
	 */
	public TabInf searchTabInf(String tableName, String attrValue){	
		String name = "";
		int count = 0;
		ApplicationContext factory = GetFactory.getFactory();
		
		if(tableName.equals("ZYBZZLBZ")){
			ZYBZZLBZDAO zybzzlbzDAO = (ZYBZZLBZDAO) factory.getBean("ZYBZZLBZDAO");
			name = "中医病症诊疗标准";
			count = zybzzlbzDAO.getSearchCountOfJibNames("('" + attrValue + "')");
		}
		else if(tableName.equals("ZCFG")){
			ZCFGDAO zcfgDAO = (ZCFGDAO) factory.getBean("ZCFGDAO");
			name = "突发公卫政策法规";
			count = zcfgDAO.getSearchCountOfJibName(attrValue);
		}
		
		return new TabInf(name, tableName, count, 1);
	}
	
	/**
	 * Get the disease table information
	 * @param params The search parameters
	 * @count The count of the disease
	 * @num The index of the disease
	 * @return The table information.
	 */
	public ArrayList<DiseaseInf> searchDiseaseInf(String[] params, String type, int count, int num){
		ArrayList<DiseaseInf> result = new ArrayList<DiseaseInf>();
		ApplicationContext factory = GetFactory.getFactory();
		
		if(type.equals("JMZ_JB")){
			JMZ_JBDAO jmz_jbDAO = (JMZ_JBDAO) factory.getBean("JMZ_JBDAO");
			
			return jmz_jbDAO.searchDistinctDisease(params, num, count);
		}
		else if(type.equals("C_JIB")){
			C_jibDAO c_jibDAO = (C_jibDAO) factory.getBean("C_JIBDAO");
			
			return c_jibDAO.searchDistinctDisease(params, num, count);
		}
		else if(type.equals("ZDFZ")){
			ZDFZDAO zdfzDAO= (ZDFZDAO) factory.getBean("ZDFZDAO");
			
			return zdfzDAO.searchDistinctDisease(params, num, count);
		}
		else if(type.equals("ZYBFZ")){
			ZYBFZDAO zybfzDAO= (ZYBFZDAO) factory.getBean("ZYBFZDAO");
			
			return zybfzDAO.searchDistinctDisease(params, num, count);
		}
		else if(type.equals("YJYA")){
			YJYADAO yjyaDAO= (YJYADAO) factory.getBean("YJYADAO");
			
			return yjyaDAO.searchDistinctDisease(params, num, count);
		}
		
		return result;
	}
	
	/**
	 * Get the sorted disease get by given expert params 
	 * @param params The search parameters
	 * @count The count of the disease
	 * @num The index of the disease
	 * @return The table infmation.
	 */
	public ArrayList<DiseaseInf> searchDiseaseInfByExpert(String[] params, int count, int num){
		ApplicationContext factory = GetFactory.getFactory();
		
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		return expertDAO.getDiseaseInfByExpert(params, count, num);
	}
	
	/**
	 * Get the sorted disease get by given expert params 
	 * @param params The search parameters
	 * @count The count of the disease
	 * @num The index of the disease
	 * @return The disease count.
	 */
	public int searchDiseaseCountByExpert(String[] params){
		ApplicationContext factory = GetFactory.getFactory();
		
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		return expertDAO.getDiseaseCountByExpert(params);
	}
	
	/**
	 * Get the sorted disease get by given hospital params 
	 * @param params The search parameters
	 * @count The count of the disease
	 * @num The index of the disease
	 * @return The table infmation.
	 */
	public ArrayList<DiseaseInf> searchDiseaseInfByHospital(String[] params, int count, int num){
		ApplicationContext factory = GetFactory.getFactory();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		return hospitalDAO.searchDiseaseInfByHospital(params, num, count);
	}
	
	/**
	 * Get the sorted disease get by given hospital params 
	 * @param params The search parameters
	 * @count The count of the disease
	 * @num The index of the disease
	 * @return The disease count.
	 */
	public int searchDiseaseCountByHospital(String[] params){
		ApplicationContext factory = GetFactory.getFactory();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		return hospitalDAO.searchDiseaseCountByHospital(params);
	}
	
	/**
	 * Get expert infmaion by the given parameters.
	 * @param params The given parameters.
	 * @param count The number of the expert return.
	 * @param num The num where the expert start.
	 * @return The expert information
	 */
	public ArrayList<ExpertInf> searchExpertInf(String[] params, int count, int num){
		ApplicationContext factory = GetFactory.getFactory();
		ArrayList<ExpertInf> result = new ArrayList<ExpertInf>();
		
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		result = expertDAO.getExpertInf(params, num, count);
		
		return result;
	}
	
	/**
	 * Get the expert count by given parameters.
	 * @param params The parameters used to search the expert.
	 * @return The number of the expert.
	 */
	public int searchExpertCount(String[] params){		
		ApplicationContext factory = GetFactory.getFactory();
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		
		return expertDAO.getSearchCount(params);
	}
	
	/**
	 * Get expert infmaion by the given parameters.
	 * @param params The given parameters.
	 * @param count The number of the expert return.
	 * @param num The num where the expert start.
	 * @return The expert information
	 */
	public ArrayList<ExpertInf> searchExpertInfByDisease(String[] params, int count, int num){
		ApplicationContext factory = GetFactory.getFactory();
		ArrayList<ExpertInf> result = new ArrayList<ExpertInf>();
		
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		result = expertDAO.getExpertByDisease(this.getDistinctDisease(params), 
											 count, num);
		
		return result;
	}

	
	/**
	 * Get the expert count by the disease params given.
	 * @param params The parameters used to search the disease.
	 * @return The number of the expert.
	 */
	public int searchExpertCountByDisease(String[] params){		
		ApplicationContext factory = GetFactory.getFactory();
		ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO");
		return expertDAO.getExpertCountByDisease(this.getDistinctDisease(params));
	}

	/**
	 * Get expert infmaion by the given hsopital parameters.
	 * @param params The given parameters.
	 * @param count The number of the expert return.
	 * @param num The num where the expert start.
	 * @return The expert information
	 */
	public ArrayList<ExpertInf> searchExpertInfByHospital(String[] params, int count, int num){
		ApplicationContext factory = GetFactory.getFactory();
		ArrayList<ExpertInf> result = new ArrayList<ExpertInf>();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		result = hospitalDAO.searchExpertInfByHospital(params, num, count);
		
		return result;
	}

	
	/**
	 * Get the expert count by the disease params given.
	 * @param params The parameters used to search the disease.
	 * @return The number of the expert.
	 */
	public int searchExpertCountByHospital(String[] params){	
		
		ApplicationContext factory = GetFactory.getFactory();
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		return hospitalDAO.searchExpertCountByHospital(params);
		
	}
	
	/**
	 * Get hospital infmaion by the given parameters.
	 * @param params The given parameters used to search hospital infmation.
	 * @param count The number of the hospital return.
	 * @param num The num where the hospital start.
	 * @return The hospital information
	 */
	public ArrayList<HospitalInf> searchHospitalInf(String[] params, int count, int num){
		ArrayList<HospitalInf> result = new ArrayList<HospitalInf>();
		ApplicationContext factory = GetFactory.getFactory();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		result = hospitalDAO.searchHospitalInf(params, count, num);
		
		return result;
	}	
	
	/**
	 * Get hospital infmaion by the given parameters.
	 * @param params The given parameters used to search hospital infmation.
	 * @param count The number of the hospital return.
	 * @param num The num where the hospital start.
	 * @return The hospital number
	 */
	public int searchHospitalInfCount(String[] params){
		ApplicationContext factory = GetFactory.getFactory();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		return hospitalDAO.getSearchCount(params);
	}
	
	/**
	 * Get expert infmaion by the given parameters.
	 * @param params The given parameters.
	 * @param count The number of the expert return.
	 * @param num The num where the expert start.
	 * @return The expert information
	 */
	public ArrayList<HospitalInf> searchHospitalInfByDisease(String[] params, int count, int num){
		ArrayList<HospitalInf> result = new ArrayList<HospitalInf>();
		ApplicationContext factory = GetFactory.getFactory();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		result = hospitalDAO.searchHospitalInfByDisease(this.getDistinctDisease(params), 
											 count, num);
		
		return result;
	}	

	/**
	 * Get expert count by the given parameters.
	 * @param params The given parameters.
	 * @return The expert information
	 */
	public int searchHospitalCountByDisease(String[] params){
		ApplicationContext factory = GetFactory.getFactory();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		return hospitalDAO.searchHospitalCountByDisease(this.getDistinctDisease(params));
	}
	
	/**
	 * Get hospital infmaion by the given parameters.
	 * @param params The given parameters.
	 * @param count The number of the hospital return.
	 * @param num The num where the hospital start.
	 * @return The hospital information
	 */
	public ArrayList<HospitalInf> searchHospitalInfByExpert(String[] params, int count, int num){
		ArrayList<HospitalInf> result = new ArrayList<HospitalInf>();
		ApplicationContext factory = GetFactory.getFactory();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		result = hospitalDAO.searchHospitalInfByExpert(params, count, num);
		
		return result;
	}	

	/**
	 * Get hospital count by the given parameters.
	 * @param params The given parameters.
	 * @return The hospital information
	 */
	public int searchHospitalCountByExpert(String[] params){
		ApplicationContext factory = GetFactory.getFactory();
		
		HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
		return hospitalDAO.searchHospitalCountByExpert(params);
	}
	
	/**
	 * Search diseaseInf by given element 
	 * @param ID The id of the given element
	 * @param type The type of the given element
	 * @param count The count of the given element
	 * @param num The start index of the disease
	 * @return The result diseaseInf
	 */
	public ArrayList<DiseaseInf> searchDiseaseInfByType(String attrName, String attrValue, String type, int count, int num){
		ArrayList<DiseaseInf> result = new ArrayList<DiseaseInf>();
		ApplicationContext factory = GetFactory.getFactory();
		String[] params = new String[4];
		
		for(int i = 0; i < params.length; i++){
			params[i] = "";
		}
		
		if(type.equals("Expert")){
			ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO"); 
			return expertDAO.getDiseaseInfByExpertAttr(attrName, attrValue, num, count);
		}
		else if(type.equals("Hospital")){
			HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
			return hospitalDAO.searchDiseaseInfByHospitalAttr(attrName, attrValue, num, count);
		}
		return result;
	}
	
	/**
	 * Search diseaseInf by given element 
	 * @param ID The id of the given element
	 * @param type The type of the given element
	 * @param count The count of the given element
	 * @param num The start index of the disease
	 * @return The result diseaseInf
	 */
	public int searchDiseaseInfCountByType(String attrName, String attrValue, String type){
		ApplicationContext factory = GetFactory.getFactory();
		String[] params = new String[4];
		
		for(int i = 0; i < params.length; i++){
			params[i] = "";
		}
		
		if(type.equals("Expert")){
			ExpertDAO expertDAO = (ExpertDAO)factory.getBean("ExpertDAO"); 
			return expertDAO.getDiseaseInfCountByExpertAttr(attrName, attrValue);
		}
		else if(type.equals("Hospital")){
			HospitalDAO hospitalDAO = (HospitalDAO)factory.getBean("HospitalDAO");
			return hospitalDAO.searchDiseaseCountByHospitalAttr(attrName, attrValue);
		}
		return 0;
	}
	
	/**
	 * Get the element data,return a element at a time.The data is used to build the xml.
	 * @dbName  The database Name.
	 * @attrName The attribute name used to search the data.
	 * @attrValue The attribute value.
	 * @num The number of the current element.
	 * @return The disease information,once at a time.
	 */
	public DataReader searchData(String dbName, String attrName, String attrValue, int num){
		DataReader result = null;
		ApplicationContext factory = GetFactory.getFactory();
		
		if(dbName.equals("JMZ_JB")){
			JMZ_JBDAO jmz_jbDAO = (JMZ_JBDAO) factory.getBean("JMZ_JBDAO");
			
			return jmz_jbDAO.searchByAttr(attrName, attrValue, num);
		}
		else if(dbName.equals("C_JIB")){
			C_jibDAO c_jibDAO = (C_jibDAO) factory.getBean("C_JIBDAO");
			
			return c_jibDAO.searchByAttr(attrName, attrValue, num);
		}
		else if(dbName.equals("ZDFZ")){
			ZDFZDAO zdfzDAO= (ZDFZDAO) factory.getBean("ZDFZDAO");
			
			return zdfzDAO.searchByAttr(attrName, attrValue, num);
		}
		else if(dbName.equals("ZYBFZ")){
			ZYBFZDAO zybfzDAO= (ZYBFZDAO) factory.getBean("ZYBFZDAO");
			
			return zybfzDAO.searchByAttr(attrName, attrValue, num);
		}
		else if(dbName.equals("YJYA")){
			YJYADAO yjyaDAO= (YJYADAO) factory.getBean("YJYADAO");
			
			return yjyaDAO.searchByAttr(attrName, attrValue, num);
		}
		else if(dbName.equals("ZYBZZLBZ")){
			ZYBZZLBZDAO zybzzlbzDAO= (ZYBZZLBZDAO) factory.getBean("ZYBZZLBZDAO");
			
			return zybzzlbzDAO.searchByAttr(attrName, attrValue, num);
		}
		else if(dbName.equals("ZCFG")){
			ZCFGDAO zcfgDAO= (ZCFGDAO) factory.getBean("ZCFGDAO");
			
			return zcfgDAO.searchByAttr(attrName, attrValue, num);
		}
		else if(dbName.equals("expert")){
			ExpertDAO expertDAO= (ExpertDAO) factory.getBean("ExpertDAO");
			
			return expertDAO.searchByAttr(attrName, attrValue, num);
		}
		else if(dbName.equals("hospital")){
			HospitalDAO hospitalDAO= (HospitalDAO) factory.getBean("HospitalDAO");
			
			return hospitalDAO.searchByAttr(attrName, attrValue, num);
		}
		
		return result;
	}	
	
	/**
	 * Get the element data,return a element at a time.The data is used to build the xml.
	 * @dbName  The database Name.
	 * @attrName The attribute name used to search the data.
	 * @attrValue The attribute value.
	 * @num The number of the current element.
	 * @return The disease information,once at a time.
	 */
	public int searchDataCount(String dbName, String attrName, String attrValue){
		int result = 0;
		ApplicationContext factory = GetFactory.getFactory();
		
		if(dbName.equals("JMZ_JB")){
			JMZ_JBDAO jmz_jbDAO = (JMZ_JBDAO) factory.getBean("JMZ_JBDAO");
			
			return jmz_jbDAO.searchCountByAttr(attrName, attrValue);
		}
		else if(dbName.equals("C_JIB")){
			C_jibDAO c_jibDAO = (C_jibDAO) factory.getBean("C_JIBDAO");
			
			return c_jibDAO.searchCountByAttr(attrName, attrValue);
		}
		else if(dbName.equals("ZDFZ")){
			ZDFZDAO zdfzDAO= (ZDFZDAO) factory.getBean("ZDFZDAO");
			
			return zdfzDAO.searchCountByAttr(attrName, attrValue);
		}
		else if(dbName.equals("ZYBFZ")){
			ZYBFZDAO zybfzDAO= (ZYBFZDAO) factory.getBean("ZYBFZDAO");
			
			return zybfzDAO.searchCountByAttr(attrName, attrValue);
		}
		else if(dbName.equals("YJYA")){
			YJYADAO yjyaDAO= (YJYADAO) factory.getBean("YJYADAO");
			
			return yjyaDAO.searchCountByAttr(attrName, attrValue);
		}
		else if(dbName.equals("ZYBZZLBZ")){
			ZYBZZLBZDAO zybzzlbzDAO= (ZYBZZLBZDAO) factory.getBean("ZYBZZLBZDAO");
			
			return zybzzlbzDAO.searchCountByAttr(attrName, attrValue);
		}
		else if(dbName.equals("ZCFG")){
			ZCFGDAO zcfgDAO= (ZCFGDAO) factory.getBean("ZCFGDAO");
			
			return zcfgDAO.searchCountByAttr(attrName, attrValue);
		}
		else if(dbName.equals("expert")){
			ExpertDAO expertDAO= (ExpertDAO) factory.getBean("ExpertDAO");
			
			return expertDAO.searchCountByAttr(attrName, attrValue);
		}
		else if(dbName.equals("hospital")){
			HospitalDAO hospitalDAO= (HospitalDAO) factory.getBean("HospitalDAO");
			
			return hospitalDAO.searchCountByAttr(attrName, attrValue);
		}
		
		return result;
	}	
	
	/**
	 * Search relative information by given Object
	 * @param searchInf The search object which contain the search infmation
	 * @param type The type of the search, now there are 5(disease.exeprt, disease.hospital, expert.disease, hospital.disease, hospital.expert)
	 * @param num The index number of the result set
	 * @param count The count of the result set
	 * @return The result set 
	 */
	public ArrayList<Object> searchRelateInf(Object searchInf, String type, int num, int count){
		ArrayList<Object> result = new ArrayList<Object>();
		ApplicationContext factory = GetFactory.getFactory();
		
		if(type.startsWith("disease")){
			ArrayList<DiseaseInf> searchDisease = new ArrayList<DiseaseInf>();
			searchDisease.add((DiseaseInf)searchInf);
			
			if(type.endsWith("expert")){
				ExpertDAO expertDAO= (ExpertDAO)factory.getBean("ExpertDAO");
				result.addAll(expertDAO.getExpertByDisease(searchDisease, count , num));
			}
			else if(type.endsWith("hospital")){
				HospitalDAO hospitalDAO= (HospitalDAO)factory.getBean("HospitalDAO");
				result.addAll(hospitalDAO.searchHospitalInfByDisease(searchDisease, count , num));
			}
		}
		else if(type.startsWith("expert")){
			ExpertInf tmpExpert = (ExpertInf)searchInf;
			ArrayList<ExpertInf> searchExpert = new ArrayList<ExpertInf>();
			searchExpert.add(tmpExpert);
			
			if(type.endsWith("disease")){
				ExpertDAO expertDAO= (ExpertDAO)factory.getBean("ExpertDAO");
				result.addAll(expertDAO.getDiseaseInfByExpertAttr("ID", tmpExpert.getId(), num, count));
			}		
			else if(type.endsWith("hospital")){
				HospitalDAO hospitalDAO= (HospitalDAO)factory.getBean("HospitalDAO");
				result.addAll(hospitalDAO.searchHospitalInfByExpert(tmpExpert, num, count));
			}
		}
		else if(type.startsWith("hospital")){
			HospitalInf tmpHospital = (HospitalInf)searchInf;
			ArrayList<HospitalInf> searchHospital = new ArrayList<HospitalInf>();
			searchHospital.add(tmpHospital);
			
			if(type.endsWith("expert")){
				HospitalDAO hospitalDAO= (HospitalDAO)factory.getBean("HospitalDAO");
				result.addAll(hospitalDAO.searchExpertInfByHospitalAttr("ID", tmpHospital.getId(), num, count));
			}
			else if(type.endsWith("disease")){
				HospitalDAO hospitalDAO= (HospitalDAO)factory.getBean("HospitalDAO");
				result.addAll(hospitalDAO.searchDiseaseInfByHospitalAttr("ID", tmpHospital.getId(), num, count));
			}			
		}
		
		return result;
	}
	
	/**
	 * Search relative information count by given Object
	 * @param searchInf The search object which contain the search infmation
	 * @param type The type of the search, now there are 5(disease.exeprt, disease.hospital, expert.disease, hospital.disease, hospital.expert)
	 * @return The result set count
	 */
	public int searchRelateInfCount(Object searchInf, String type){
		int result = 0;
		ApplicationContext factory = GetFactory.getFactory();
		
		if(type.startsWith("disease")){
			ArrayList<DiseaseInf> searchDisease = new ArrayList<DiseaseInf>();
			searchDisease.add((DiseaseInf)searchInf);
			
			if(type.endsWith("expert")){
				ExpertDAO expertDAO= (ExpertDAO)factory.getBean("ExpertDAO");
				result = expertDAO.getExpertCountByDisease(searchDisease);
			}
			else if(type.endsWith("hospital")){
				HospitalDAO hospitalDAO= (HospitalDAO)factory.getBean("HospitalDAO");
				result = hospitalDAO.searchHospitalCountByDisease(searchDisease);
			}
		}
		else if(type.startsWith("expert")){
			ExpertInf tmpExpert = (ExpertInf)searchInf;
			ArrayList<ExpertInf> searchExpert = new ArrayList<ExpertInf>();
			searchExpert.add(tmpExpert);
			
			if(type.endsWith("disease")){
				ExpertDAO expertDAO= (ExpertDAO)factory.getBean("ExpertDAO");
				result = expertDAO.getDiseaseInfCountByExpertAttr("ID", tmpExpert.getId());
			}	
			else if(type.endsWith("hospital")){
				HospitalDAO hospitalDAO= (HospitalDAO)factory.getBean("HospitalDAO");
				result = hospitalDAO.searchHospitalInfCountByExpert(tmpExpert);
			}
		}
		else if(type.startsWith("hospital")){
			HospitalInf tmpHospital = (HospitalInf)searchInf;
			ArrayList<HospitalInf> searchHospital = new ArrayList<HospitalInf>();
			searchHospital.add(tmpHospital);
			
			if(type.endsWith("expert")){
				HospitalDAO hospitalDAO= (HospitalDAO)factory.getBean("HospitalDAO");
				result = hospitalDAO.searchExpertCountByHospitalAttr("ID", tmpHospital.getId());
			}
			else if(type.endsWith("disease")){
				HospitalDAO hospitalDAO= (HospitalDAO)factory.getBean("HospitalDAO");
				result = hospitalDAO.searchDiseaseCountByHospitalAttr("ID", tmpHospital.getId());
			}			
		}
		
		return result;
	}
	
	/**
	 * 查找Clob数据
	 * @param DBName The table name of database
	 * @param IDName The id name of database
	 * @param attribute The attrbute you need to search
	 * @param id The id of the attribute
	 * @return The clob string
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
	 * Get the disease which are relative to the give parameters.
	 * @param params Search parameters.
	 * @return The relative dieases,the number of the disease is set in the /WEB-INF/conf/serverResource.properties file.
	 */
	private ArrayList<DiseaseInf> getDistinctDisease(String[] params){
		LinkedHashMap<String, DiseaseInf> diseaseMap = new LinkedHashMap<String, DiseaseInf>();
		ArrayList<DiseaseInf> diseaseInf = new ArrayList<DiseaseInf>();
		ApplicationContext factory = GetFactory.getFactory();
		LoadConf conf = (LoadConf)factory.getBean("searchConf");
		
		JMZ_JBDAO jmz_jbDAO = (JMZ_JBDAO) factory.getBean("JMZ_JBDAO");
		C_jibDAO c_jibDAO = (C_jibDAO) factory.getBean("C_JIBDAO");
	//	ZDFZDAO zdfzDAO= (ZDFZDAO) factory.getBean("ZDFZDAO");
	//	ZYBFZDAO zybfzDAO= (ZYBFZDAO) factory.getBean("ZYBFZDAO");
	//	YJYADAO yjyaDAO= (YJYADAO) factory.getBean("YJYADAO");

		diseaseInf.addAll(jmz_jbDAO.searchDistinctDisease(params, 0, conf.getExpertDiseaseCount()));
		diseaseInf.addAll(c_jibDAO.searchDistinctDisease(params, 0, conf.getExpertDiseaseCount()));
		
		Iterator iterator = diseaseInf.iterator();
		while(iterator.hasNext()){
			DiseaseInf tmp = (DiseaseInf)iterator.next();
			if(diseaseMap.containsKey(tmp.getName())){
				diseaseMap.get(tmp.getName()).add(tmp);
			}
			else{
				diseaseMap.put(tmp.getName(), tmp);
			}
		}
		
		//Sorted the disease
		TreeMap<Integer, ArrayList<DiseaseInf>> sortedDisease = new TreeMap<Integer, ArrayList<DiseaseInf>>(Collections.reverseOrder());
		iterator = diseaseInf.iterator();
		while(iterator.hasNext()){
			DiseaseInf tmp = (DiseaseInf)iterator.next();
			
			if(sortedDisease.containsKey(tmp.getCorrelation())){
				ArrayList<DiseaseInf> tmpList = sortedDisease.get(tmp.getCorrelation());
				
				tmpList.add(tmp);
			}
			else{
				ArrayList<DiseaseInf> tmpList = new ArrayList<DiseaseInf>();
				tmpList.add(tmp);
				sortedDisease.put(tmp.getCorrelation(), tmpList);
			}
		}
		
		ArrayList<DiseaseInf> result = new ArrayList<DiseaseInf>();
		iterator = sortedDisease.keySet().iterator();
		
		for(int i = 0; i < conf.getExpertDiseaseCount() && iterator.hasNext(); ){
			int key = ((Integer)iterator.next()).intValue();
			ArrayList<DiseaseInf> tmpList = (ArrayList<DiseaseInf>)sortedDisease.get(key);
			
			if( (i + tmpList.size()) <= conf.getExpertDiseaseCount()){
				i += tmpList.size();
				result.addAll(tmpList);
			}
			else{
				for(int j = 0;(i + j) < conf.getExpertDiseaseCount(); j++){
					result.add(tmpList.get(j));
				}
				break;
			}				
		}
		
		return result;
	}
}
