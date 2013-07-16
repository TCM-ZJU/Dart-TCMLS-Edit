package cn.edu.zju.ccnt.TFGW.innerHTML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cn.edu.zju.ccnt.TFGW.object.ZTC_TREE;

/**
 * 生成导航树,可以根据配置文件自动对展开结构进行管理
 * @author 张小刚 2007.10.19
 */
public class CreateTreeHTML{
	private Logger logger = Logger.getLogger(CreateTreeHTML.class);
	private String prefix;
	private String DBName;
	private int maxLevel;
	private ArrayList<String> nodesList = new ArrayList<String>();

	public CreateTreeHTML(String pre){
		loadProp(pre);
	}
	
	public CreateTreeHTML(){}
	
	/**
	 * 根据展开结点的层数,调用相应的绘图函数
	 * @param lev 结点层数
	 * @param nodes 结点信息
	 * @return
	 */
	public String CreateTree(int lev, ArrayList<String> nodes, ArrayList<String> nodesInf){
		if(lev < maxLevel){
			return createNodes(lev, nodes, nodesInf);
		}
		else
			return createLeaves(nodes);		
	}
	
	public String createTreeNodesByZTC(int level,ArrayList<ZTC_TREE> ztcList){
		StringBuffer result = new StringBuffer();
		String whiteImage = "<img align=\"absmiddle\" src=\"/TFGWProject/public/images/empty.gif\"></img>";
		String lineImage= "<img align=\"absmiddle\" src=\"/TFGWProject/public/images/treeLine.gif\"></img>";
		String headImage = "<img class=\"nodeOperator\" align=\"absmiddle\" src=\"/TFGWProject/public/images/Tplus.gif\" ";
		
		if(ztcList == null){
			return "";
		}
		
		for(int i = level; i > 1; i--){
			whiteImage += lineImage;
		}
		
		for(int i = 0; i < ztcList.size(); i++){	
			result.append("<div>" + whiteImage + headImage +
				"OnClick=openTree(this,\"" + DBName + "\",\"" + (level + 1) + "\",\"" + ztcList.get(i).getMN().toString() + "\")>" +
				"<img align=\"absmiddle\" src=\"/TFGWProject/public/images/spriteDivClose.gif\">");
			
			result.append("</img> <span  class=\"treeNodes\">"
				+ ztcList.get(i).getMHCHI().toString()
				+ "</span></div>");
		}
		
		return result.toString();			
	}
	
	public String createLeaves(ArrayList<String> nodes){
		StringBuffer result = new StringBuffer("");
		String whiteImage = "<img align=\"absmiddle\" src=\"/TFGWProject/public/images/empty.gif\"></img>";
		String lineImage= "<img align=\"absmiddle\" src=\"/TFGWProject/public/images/treeLine.gif\"></img>";
		String headImage = "<img align=\"absmiddle\" src=\"/TFGWProject/public/images/T.gif\"></img><img align=\"absmiddle\" src=\"/TFGWProject/public/images/leave.gif\"></img>";
		String endImage = "<img align=\"absmiddle\" src=\"/TFGWProject/public/images/L.gif\"></img><img align=\"absmiddle\" src=\"/TFGWProject/public/images/leave.gif\"></img>";
		
		for(int i = maxLevel; i > 1; i--){
			whiteImage += lineImage;
		}
		
		for(int i = 0; i < nodes.size(); i++){	
			if(i == (nodes.size() - 1)){
				headImage = endImage;
			}
			
			//数据库的数据问题,去掉/n和/r
			result.append(
					"<div>" + 
						whiteImage + 
						headImage + 
						"<span class=\"treeLeaves\" OnClick=treeSearch(\"" +
						DBName + "\",\"" +
						nodes.get(i).replace("\n","").replace("\r","").replace(" ","") + "\")>" + 
						nodes.get(i) + 
						"</span>" +
					"</div>");
		}
		
		return result.toString();	
	}
	
	private String createNodes(int level, ArrayList<String> nodes, ArrayList<String> nodesInf){
		StringBuffer result = new StringBuffer();
		String whiteImage = "<img align=\"absmiddle\" src=\"/TFGWProject/public/images/empty.gif\"></img>";
		String lineImage= "<img align=\"absmiddle\" src=\"/TFGWProject/public/images/treeLine.gif\"></img>";
		String headImage = "<img class=\"nodeOperator\" align=\"absmiddle\" src=\"/TFGWProject/public/images/Tplus.gif\" ";
		
		for(int i = level; i > 1; i--){
			whiteImage += lineImage;
		}
		
		for(int i = 0; i < nodes.size(); i++){	
			result.append( "<div>" + whiteImage + headImage +
				"OnClick=openTree(this,\"" + DBName + "\",\"" + (level + 1) + "\"");
			
			for(int j = 1; j < level; j++){
				result.append(",\"" + nodesInf.get(j) + "\"");
			}
			
			result.append(",\"" + nodes.get(i) + "\"");
			result.append( ")></img>" +
					"<img align=\"absmiddle\" src=\"/TFGWProject/public/images/spriteDivClose.gif\">" +
					" <span  class=\"treeNodes\">"
				+ nodes.get(i)
				+ "</span></div>");
		}
		
		return result.toString();		
	}
	
	/**
	 * 根据配置文件进行初始话
	 * @param pre
	 */
	public void loadProp(String pre){
		Resource url = new ClassPathResource("tree.properties");
		Properties prop = new Properties();
		prefix = pre;
		
		try {
			prop.load(url.getURL().openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("读取资源文件tree.properties失败!");
		}
		
		DBName = prop.getProperty(pre + ".DBname");
		maxLevel = Integer.parseInt(prop.getProperty(pre + ".level"));
		nodesList.add(DBName);
		for(int i = 1; i <= maxLevel; i++){
			nodesList.add(prop.getProperty(pre + ".level" + i));
		}
	}
	
}
