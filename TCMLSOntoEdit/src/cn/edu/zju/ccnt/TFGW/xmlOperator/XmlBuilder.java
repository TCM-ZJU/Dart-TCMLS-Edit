package cn.edu.zju.ccnt.TFGW.xmlOperator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import cn.edu.zju.ccnt.TFGW.object.xmlInf.*;

public class XmlBuilder {
	static private Logger logger = Logger.getLogger(XmlBuilder.class.getName());
	
	public static void main(String[] arg){
		
	}
	
	/**
	 * Build xml builder from a exit file
	 * @param file The file where xml saved. 
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public Document buildXml(File file) throws JDOMException, IOException{
		SAXBuilder sb = new SAXBuilder();
		Document tmpDoc = sb.build(file);
		return tmpDoc;
	}
	
	/**
	 * Build xml builder from xml data
	 * @param xmlData The xml data.
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public Document buildXml(String xmlData) throws JDOMException, IOException{	
		SAXBuilder sb = new SAXBuilder();
		Document tmpDoc = sb.build(xmlData);
		return tmpDoc;	
	}
	
	
	/**
	 * Build the disease xml, the xml structure is like that:
	 * <DiseaseInf>
	 *	<tabNum type="number"></tabNum>
	 *	<diseaseNum type="number"></diseaseNum>
	 *	<tableInf>
	 *		<tableName type="string"></tableName>
	 *		<diseaseNum type="number"></diseaseNum>
	 *	</tableInf>
	 * </DiseaseInf>
	 * @param tabs
	 * @return
	 */
	public Element buildTabXml(ArrayList<TabInf> tabs){
		TabInf tabInf;
		int sum = 0;
		
		Element root = new Element("DiseaseInf");
		
		Element tabNum = new Element("tabNum");
		tabNum.setText(String.valueOf(tabs.size()));
		tabNum.setAttribute("type", "number");
		
		Iterator iterator = tabs.iterator();
		while(iterator.hasNext()){
			tabInf = (TabInf)iterator.next();
			
			Element tableName = new Element("tableName");
			tableName.addContent(tabInf.getTableName());
			tableName.setAttribute("type", "String");
			
			Element diseaseNum = new Element("diseaseNum");
			diseaseNum.addContent(String.valueOf(tabInf.getNumber()));
			diseaseNum.setAttribute("type", "String");
			
			Element tableInf = new Element("tableInf");
			tableInf.addContent(tableName);
			tableInf.addContent(diseaseNum);
			
			//Add the tableInf into root.
			root.addContent(tableInf);
			sum += tabInf.getNumber();
		}
		
		Element diseaseNum = new Element("diseaseNum");
		diseaseNum.setText(String.valueOf(sum));
		diseaseNum.setAttribute("type", "number");
		
		//Build the dom tree
		root.addContent(tabNum);
		root.addContent(diseaseNum);
		
		logger.debug("Current xml is :" + this.xmlOutput(root));
		
		return root;
	}
	
	/**
	 * Build the disease xml, the xml structure is like that:
	 <diseaseData>
	 	<disease>
	 		<name type="string"></name>
	 		<correlation type="number"></correlation>
	 	</disease>
	 </diseaseData>
	 * @param diseases The disease need to build the xml.
	 * @return
	 */
	public Element buildDiseaseXml(ArrayList<DiseaseInf> diseases){
		DiseaseInf disease;
		Element root = new Element("diseaseData");
		
		Iterator iterator = diseases.iterator();
		while(iterator.hasNext()){
			disease = (DiseaseInf)iterator.next();
			Element tmpDisease = new Element("disease");
			
			Element name = new Element("name");
			name.addContent(disease.getName());
			name.setAttribute("type", "String");
			
			Element correlation = new Element("correlation");
			correlation.addContent(String.valueOf(disease.getCorrelation()));
			correlation.setAttribute("type", "number");
			
			//Add the tableInf into root.
			tmpDisease.addContent(name);
			tmpDisease.addContent(correlation);
			
			root.addContent(tmpDisease);
		}
		
		//Build the dom tree
		logger.debug("Current xml is :" + this.xmlOutput(root));
		
		return root;
	}
	
	/**
	 * Build the disease xml, the xml structure is like that:
	 <expertInf>
	 	<expertNum type="number"></expertNum>
	 	<expert>
	 		<expertID type="string"></expertID>
	 		<expertName type="string"></expertName>
	 		<correlation type="number"></correlation>
	 	</expert>
	 </expertInf>
	 * @param expert The epxerts informaion.
	 * @return The result xml.
	 */
	public Element buildExpertXml(ArrayList<ExpertInf> experts, int expertCount, int currentNum){
		Element root = new Element("expertInf");
		ExpertInf expertInf;

		Element expertNum = new Element("expertNum");
		expertNum.setText(String.valueOf(expertCount));
		expertNum.setAttribute("type", "number");
		
		Element currentNumNode = new Element("currentNum");
		expertNum.setText(String.valueOf(currentNum));
		expertNum.setAttribute("type", "number");
		
		//Build the dom tree
		root.addContent(expertNum);
		root.addContent(currentNumNode);
		
		Iterator iterator = experts.iterator();
		while(iterator.hasNext()){
			expertInf = (ExpertInf)iterator.next();
			
			Element expertID = new Element("expertID");
			expertID.addContent(expertInf.getId());
			expertID.setAttribute("type", "String");
			
			Element expertName = new Element("expertName");
			expertName.addContent(String.valueOf(expertInf.getName()));
			expertName.setAttribute("type", "String");

			Element correlation = new Element("correlation");
			correlation.addContent(String.valueOf(expertInf.getCorrelation()));
			correlation.setAttribute("type", "number");
			
			Element expertNode = new Element("expert");
			expertNode.addContent(expertID);
			expertNode.addContent(expertName);
			expertNode.addContent(correlation);
			
			//Add the tableInf into root.
			root.addContent(expertNode);
		
		}
	
		logger.debug("Current xml is :" + this.xmlOutput(root));
		return root;
	}
	
	/**
	 * Build the disease xml, the xml structure is like that:
	 <hospitalInf>
	 	<hospitalNum type="number"></hospitalNum>
	 	<hospital>
	 		<hospitalID type="string"></hospitalID>
	 		<hospitalName type="string"></hospitalName>
	 		<correlation type="number"></correlation>
	 	</hospital>
	 </hospitalInf>
	 * @param hospital The epxerts informaion.
	 * @return The result xml.
	 */
	public Element buildHospitalXml(ArrayList<HospitalInf> hospitals, int hospitalCount, int currentNum){
		Element root = new Element("hospitalInf");
		HospitalInf hospitalInf;

		Element hospitalNum = new Element("hospitalNum");
		hospitalNum.setText(String.valueOf(hospitalCount));
		hospitalNum.setAttribute("type", "number");
		
		Element currentNumNode = new Element("currentNum");
		hospitalNum.setText(String.valueOf(currentNum));
		hospitalNum.setAttribute("type", "number");
		
		//Build the dom tree
		root.addContent(hospitalNum);
		root.addContent(currentNumNode);
		
		Iterator iterator = hospitals.iterator();
		while(iterator.hasNext()){
			hospitalInf = (HospitalInf)iterator.next();
			
			Element hospitalID = new Element("hospitalID");
			hospitalID.addContent(hospitalInf.getId());
			hospitalID.setAttribute("type", "String");
			
			Element hospitalName = new Element("hospitalName");
			hospitalName.addContent(String.valueOf(hospitalInf.getName()));
			hospitalName.setAttribute("type", "String");

			Element correlation = new Element("correlation");
			correlation.addContent(String.valueOf(hospitalInf.getCorrelation()));
			correlation.setAttribute("type", "number");
			
			Element hospitalNode = new Element("hospital");
			hospitalNode.addContent(hospitalID);
			hospitalNode.addContent(hospitalName);
			hospitalNode.addContent(correlation);
			
			//Add the tableInf into root.
			root.addContent(hospitalNode);
		}
	
		logger.debug("Current xml is :" + this.xmlOutput(root));
		return root;
	}
	
	/**
	 * Return the string of the given element
	 * @param ele The element you need to output.
	 * @return The String of the given element.
	 */
	public String xmlOutput(Element ele){
		return (new XMLOutputter()).outputString(ele);
	}
}
