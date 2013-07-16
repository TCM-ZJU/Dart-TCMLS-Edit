package cn.edu.zju.ccnt.TFGW.dwr;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import cn.edu.zju.ccnt.TFGW.*;
import cn.edu.zju.ccnt.TFGW.operation.DataCtrl.MultiSearcher;
import cn.edu.zju.ccnt.TFGW.xmlOperator.XmlBuilder;

public class SearchDB {
	private static Logger logger = Logger.getLogger(SearchDB.class.getName());
	private ApplicationContext factory = GetFactory.getFactory();
	private MultiSearcher searcher = (MultiSearcher)factory.getBean("multiSearcher");
	private XmlBuilder xmlBuilder = (XmlBuilder)factory.getBean("xmlBuilder");
	enum searchType{disease, expert, hospital};
	
	public String searchDiseaseTabInf(String[] params, int count, int num){
		StringBuffer result = new StringBuffer();
		
		result.append(xmlBuilder.xmlOutput(xmlBuilder.buildTabXml(searcher.searchTabInf(params))));
		result.append(xmlBuilder.xmlOutput(xmlBuilder.buildExpertXml(searcher.searchExpertInfByDisease(params, count, num), searcher.searchExpertCountByDisease(params), num)));
		result.append(xmlBuilder.xmlOutput(xmlBuilder.buildHospitalXml(searcher.searchHospitalInfByDisease(params, count, num), searcher.searchHospitalCountByDisease(params), num)));
		
		logger.info("The xml is :" + result.toString());
		
		return result.toString();
	}
	
	public String searchDiseaseInf(String[] params, String dbName, String type, int count, int num){
		if(type.equals("disease")){
			if(dbName.equals("expert")){
				return xmlBuilder.xmlOutput(xmlBuilder.buildExpertXml(searcher.searchExpertInfByDisease(params, count, num), count, num));
			}
			else if(dbName.equals("hospital")){
				return xmlBuilder.xmlOutput(xmlBuilder.buildHospitalXml(searcher.searchHospitalInfByDisease(params, count, num), count, num));
			}
			else{
				return xmlBuilder.xmlOutput(xmlBuilder.buildDiseaseXml(searcher.searchDiseaseInf(params, dbName, count, num)));
			}
		}
		else if(type.equals("expert")){
			if(dbName.equals("expert")){
				return xmlBuilder.xmlOutput(xmlBuilder.buildExpertXml(searcher.searchExpertInf(params, count, num), count, num));
			}
			else if(dbName.equals("hospital")){
				return xmlBuilder.xmlOutput(xmlBuilder.buildHospitalXml(searcher.searchHospitalInfByExpert(params, count, num), count, num));
			}
			else{
				return xmlBuilder.xmlOutput(xmlBuilder.buildDiseaseXml(searcher.searchDiseaseInfByExpert(params, count, num)));
			}
		}
		else if(type.equals("hospital")){
			if(dbName.equals("expert")){
				return xmlBuilder.xmlOutput(xmlBuilder.buildExpertXml(searcher.searchExpertInfByHospital(params, count, num), count, num));
			}
			else if(dbName.equals("hospital")){
				return xmlBuilder.xmlOutput(xmlBuilder.buildHospitalXml(searcher.searchHospitalInf(params, count, num), count, num));
			}
			else{
				return xmlBuilder.xmlOutput(xmlBuilder.buildDiseaseXml(searcher.searchDiseaseInfByHospital(params, count, num)));
			}
		}
		
		return "Error";
	}
	
	/**
	 * Read clob value from the database
	 * @param dbName The database name
	 * @param attribute The clob attribute name
	 * @param idName The id attribute name
	 * @param id the id of the item the data in.
	 * @return The clob infmation
	 */
	public String searchClob(String dbName, String attribute, String idName, String id){
		return searcher.searchClob(dbName, idName, attribute, id);
	}
}
