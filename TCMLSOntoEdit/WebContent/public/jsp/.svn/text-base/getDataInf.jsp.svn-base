<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="org.w3c.dom.*" %>
<%@ page language="java" import="javax.xml.parsers.*"%>
<%@ page language="java" import="java.io.*"%>
<%@ page language="java" import="cn.edu.zju.ccnt.TFGW.object.objectInterface.*"%>

<jsp:directive.page import="org.springframework.context.ApplicationContext"/>
<jsp:directive.page import="cn.edu.zju.ccnt.TFGW.operation.DataCtrl.MultiSearcher"/>
<jsp:directive.page import="cn.edu.zju.ccnt.TFGW.object.xmlInf.*"/>
<jsp:directive.page import="cn.edu.zju.ccnt.TFGW.GetFactory"/>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String realPath = request.getRealPath("/");
%>

<%
	int num = 0;
	int maxNum = 0;
	int index = 0;
	String maxNumsStr = "";
	String dbNamesStr = "";
	
	String dbName = (new String(request.getParameter("dbName").getBytes("ISO8859-1"), "UTF-8"));
	String attrName = (new String(request.getParameter("attrName").getBytes("ISO8859-1"), "UTF-8"));
	String attrValue = new String(request.getParameter("attrValue").getBytes("ISO8859-1"), "UTF-8");
	num = Integer.parseInt(new String(request.getParameter("num").getBytes("ISO8859-1"), "UTF-8"));
	
	if(request.getParameter("index") != null){
		index = Integer.parseInt(new String(request.getParameter("index").getBytes("ISO8859-1"), "UTF-8"));
	}
	
	if(request.getParameter("maxNum") != null){
		maxNum = Integer.parseInt(new String(request.getParameter("maxNum").getBytes("ISO8859-1"), "UTF-8"));
	}
	
/********************************Read the configuration file****************************************/
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = docFactory.newDocumentBuilder();	
	Document xmlDoc = builder.parse(new File(realPath+"WEB-INF/conf/dataResource/searchResource.xml"));
	
/*******************************Get database ctrl instance***************************************************************/	
	ApplicationContext factory = GetFactory.getFactory();
	MultiSearcher searcher = (MultiSearcher)factory.getBean("multiSearcher");
	
/*******************************Public variate***************************************************************************/ 	
	Element ele;
	String titleStr;
	String[] dbNames;
	int[] maxNums;
	String searchType = dbName;
	ArrayList<TabInf> diseaseTabInf = new ArrayList<TabInf>();

/*******************************Get different value for different situation**********************************************/	
	if(dbName.equals("expert") || dbName.equals("hospital")){
		ele = (Element)xmlDoc.getElementsByTagName(dbName).item(0);
		titleStr = ele.getElementsByTagName("dbName").item(0).getTextContent();
		
		titleStr += "&nbsp&nbsp" + 
					"<span class='titleInf'>" + 
					num + 
					"</span>/" +
					"<span class='titleInf'>" + 
					maxNum + 
					"</span>";			
	}
	//If it is the first time to search the disease,then get the DB infmation, and read the first data in first disease DB
	else if(dbName.equals("check")){
		diseaseTabInf = searcher.searchTabInfByAttr(attrName, attrValue);
		
		if(diseaseTabInf.size() == 0){
			titleStr = "疾病<span class='titleInf'>" + attrValue + "</span>没有有效的结果";
			
			//Set the search params
			num = 0;
			maxNum = 0;
			dbName="JMZ_JB";
			attrName = "JBMC";
		}
		else{
			dbNames = new String[diseaseTabInf.size()];
			maxNums = new int[diseaseTabInf.size()];
			
			dbNamesStr = "";
			maxNumsStr = "";
			for(int i = 0; i < diseaseTabInf.size(); i++){
				dbNames[i] = diseaseTabInf.get(i).getTableName();
				maxNums[i] = diseaseTabInf.get(i).getDistinctCount();
				
				if(i == diseaseTabInf.size()){
					dbNamesStr += dbNames[i];
					maxNumsStr += maxNums[i];
				}
				else{
					dbNamesStr += dbNames[i] + ":";
					maxNumsStr += maxNums[i] + ":";
				}
			}
			
			//Set the search params
			num = 1;
			maxNum = maxNums[0];
			dbName = dbNames[0];
			searchType="change";
			
			ele = (Element)xmlDoc.getElementsByTagName(dbName).item(0);
			attrName = ele.getElementsByTagName("searchAttr").item(0).getTextContent();
			
			if(dbNames.length > 1){
				titleStr = "<select name='changeType' onchange='changeType(this.selectedIndex);'>";
				for(int i = 0; i < dbNames.length; i++){
					String tmpName = ((Element)xmlDoc.getElementsByTagName(dbNames[i]).item(0)).getElementsByTagName("dbName").item(0).getTextContent();
					
					if( i == index){
						titleStr += "<option selected='selected'>" + tmpName + "(" + maxNums[i] + ")</option>";
					}
					else{
						titleStr += "<option>" + tmpName + "(" + maxNums[i] + ")</option>";
					}
					
				}
				titleStr += "</select>";
			}
			else{
				titleStr = ele.getElementsByTagName("dbName").item(0).getTextContent();
			}
			
			titleStr += "<span class='titleInf'>" +
						ele.getElementsByTagName("searchInf").item(0).getTextContent() +
						"</span>为" +
						"<span class='titleInf'>" +
						attrValue +
						"</span>&nbsp&nbsp" + 
						"<span class='titleInf'>" + 
						1 + 
						"</span>/" +
						"<span class='titleInf'>" + 
						maxNums[0] + 
						"</span>";	
		}
	}
	else if(dbName.equals("change") || dbName.equals("stable")){
		dbNamesStr = (new String(request.getParameter("dbNames").getBytes("ISO8859-1"), "UTF-8"));
		maxNumsStr = (new String(request.getParameter("maxNums").getBytes("ISO8859-1"), "UTF-8"));
		
		dbNames = dbNamesStr.split(":");
		String[] tmpStr = maxNumsStr.split(":");
		
		maxNums = new int[tmpStr.length];
		
		for(int i = 0; i < tmpStr.length; i++){
			maxNums[i] = Integer.parseInt(tmpStr[i]);
		}
		
		maxNum = maxNums[index];
		dbName = dbNames[index];
		
		ele = (Element)xmlDoc.getElementsByTagName(dbName).item(0);
		attrName = ele.getElementsByTagName("searchAttr").item(0).getTextContent();
		
		titleStr = "<select name='changeType' onchange='changeType(this.selectedIndex);'>";
		for(int i = 0; i < dbNames.length; i++){
			String tmpName = ((Element)xmlDoc.getElementsByTagName(dbNames[i]).item(0)).getElementsByTagName("dbName").item(0).getTextContent();
			
			if( i == index){
				titleStr += "<option selected='selected'>" + tmpName + "(" + maxNums[i] + ")</option>";
			}
			else{
				titleStr += "<option>" + tmpName + "(" + maxNums[i] + ")</option>";
			}
			
		}
		titleStr += "</select>";
		
		titleStr += "<span class='titleInf'>" +
					ele.getElementsByTagName("searchInf").item(0).getTextContent() +
					"</span>为" +
					"<span class='titleInf'>" +
					attrValue +
					"</span>&nbsp&nbsp" + 
					"<span class='titleInf'>" + 
					num + 
					"</span>/" +
					"<span class='titleInf'>" + 
					maxNum + 
					"</span>";		
	}
	else if(dbName.equals("ZYBZZLBZ") || dbName.equals("ZCFG")){
		ele = (Element)xmlDoc.getElementsByTagName(dbName).item(0);
		titleStr = ele.getElementsByTagName("dbName").item(0).getTextContent();
		
		if(maxNum <= 0){
			TabInf tmpTabInf = searcher.searchTabInf(dbName, attrValue);
			maxNum = tmpTabInf.getNumber();
		}
		
		if(maxNum == 0){
			titleStr = "疾病<span class='titleInf'>" + attrValue + "</span>在<span class='titleInf'>" + ele.getElementsByTagName("dbName").item(0).getTextContent() + "</span>中没有有效的结果";
			
			//Set the search params
			num = 0;
			maxNum = 0;			
		}
		else{
			titleStr += "<span class='titleInf'>" +
						ele.getElementsByTagName("searchInf").item(0).getTextContent() +
						"</span>为" +
						"<span class='titleInf'>" +
						attrValue +
						"</span>&nbsp&nbsp" + 
						"<span class='titleInf'>" + 
						num + 
						"</span>/" +
						"<span class='titleInf'>" + 
						maxNum + 
						"</span>";		
		}
	}
	else{
		ele = (Element)xmlDoc.getElementsByTagName(dbName).item(0);
		titleStr = ele.getElementsByTagName("dbName").item(0).getTextContent();
		
		titleStr += "<span class='titleInf'>" +
					ele.getElementsByTagName("searchInf").item(0).getTextContent() +
					"</span>为" +
					"<span class='titleInf'>" +
					attrValue +
					"</span>&nbsp&nbsp" + 
					"<span class='titleInf'>" + 
					num + 
					"</span>/" +
					"<span class='titleInf'>" + 
					maxNum + 
					"</span>";		
	}
	
	
/*******************************Search disease detail infmation***************************************************************/	
	ele = (Element)xmlDoc.getElementsByTagName(dbName).item(0);
	DataReader data = searcher.searchData(dbName, attrName, attrValue, num);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>结果展示窗口</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style>
    	@import "/TFGWProject/public/js/dojo-release-1.1.0/dijit/themes/tundra/tundra.css";  
    	@import "/TFGWProject/public/js/dojo-release-1.1.0/dijit/themes/soria/soria.css";   
        @import "/TFGWProject/public/js/dojo-release-1.1.0/dojo/resources/dojo.css"
    </style>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/TFGWProject/public/js/dojo-release-1.1.0/dojo/dojo.js" djConfig="parseOnLoad:true, isDebug:false"></script>
	<script type='text/javascript' src='/TFGWProject/dwr/engine.js'></script>
  	<script type='text/javascript' src='/TFGWProject/dwr/util.js'></script>
  	<script type='text/javascript' src='/TFGWProject/dwr/interface/SearchDB.js'></script>
  	
	<script language="javascript">
		//导入所需的javascrilpt代码
		dojo.require("dojo.NodeList-fx");
		
		dojo.require("dijit.Toolbar");
		dojo.require("dijit.form.Button");
		dojo.require("dijit.form.TextBox");
		dojo.require("dijit.form.ComboBox");
		dojo.require("dijit.form.NumberTextBox");
		
		dojo.require("dijit.TitlePane");
		dojo.require("dijit.layout.ContentPane");
		dojo.require("dijit.layout.BorderContainer");

	</script>
	
	<style type="text/css">
	
	body{
		padding: 0;
		margin: 0;
	}
	
	 /*The layout*/
	 div#titleDiv{
		width:100%;
		height: 20px;
		
		padding: 0;
		margin: 0;
		
		text-align:left;
	}
	
	div#infDiv{
		margin: 0; 
		padding: 0; 
	}
	
	div#ctrlDiv{
		height:50px;
		width: 100%;
	}
	
	span.titleInf{
		color: red;
		font-weight: bold;
	}
	
/***********************Information table display************************/
	.infTable{
  		padding: 0;
  		margin: 0;
  		
  		width: 100%;
  		
  		background-color:#D5D5D5;
  	}
  	
  	.infTable tr{
  		height:20px;
  		
  		text-align:center;
  	}
  	
  	.infTable th{
  		height:20px;
  		width: 20%;
  		
  		background-color: #D1E1FB;
  		text-align:center;
  	}
  	
  	.infTable td{
  		height:20px;
  		width: 80%;
  		       
  		background-color: white;
  		vertical-align:top;
  		text-align:center;
  	}  	
	</style>
	
	<script type="text/javascript">
		<%if(maxNum <= 0){%>
			setTimeout("window.close()", 1500);
		<%titleStr +="</br><h3 style='text-align:center;'>该窗口将在三秒内自动<span class='titleInf' style='text-decoration:underline;cursor:pointer;' onclick='window.close();'>关闭</span></h3>";
		}%>
		var num = <%=num%>;
		var maxNum = <%=maxNum%>;
		var maxNums = '<%=maxNumsStr%>';
		var dbNames = '<%=dbNamesStr%>';
		
		function getDataInf(searchNum){                                                                                                                                                                                                               
			if(searchNum > maxNum || searchNum <= 0){
				return;
			}
			
			var url = "<%=basePath%>/public/jsp/getDataInf.jsp?" + 
				      "dbName=" + '<%=searchType%>' +
				      "&attrName=" + '<%=attrName%>' +
				      "&attrValue=" + '<%=attrValue%>' +
				      "&maxNum=" + maxNum +
				      "&num=" + searchNum +
				      "&dbNames=" + dbNames +
				      "&maxNums=" + maxNums +
				      "&index=" + <%=index%>;
			    
		    location.replace(url);
		}
		
		//Get clob data
		function openClob(node, dbName, idName, id, attr){				
				if(node.childNodes.length > 0){
					return;
				}
				
				SearchDB.searchClob(dbName, attr, idName, id, {
									callback:function(str){
										node.innerHTML="<pre style='white-space:normal; word-wrap:break-word;'>"+ str +"</pre>";
									}
								});
		}
		
		//Change search type
		function changeType(index){   
			                                                                                                                                                                                                        
			var url = "<%=basePath%>/public/jsp/getDataInf.jsp?" + 
				      "dbName=" + 'change' +
				      "&attrName=" + '<%=attrName%>' +
				      "&attrValue=" + '<%=attrValue%>' +
				      "&maxNum=" + maxNum +
				      "&num=" + 1 +
				      "&dbNames=" + dbNames +
				      "&maxNums=" + maxNums +
				      "&index=" + index;
				
		    location.replace(url);
		}
	</script>
  </head>
  
  <body class="soria">
  	
	<%-- Draw the table by the given xml configuration. --%>
	  	<div id="titleDiv" dojoType="dijit.Toolbar" region="top" style="padding: 3 0 1 10;">
			<h3 style="margin: 0;"><%=titleStr %></h3>
			<%if(dbName.equals("")){
				dbName = "JMZ_JB";
			%>
			<%} %>
		</div>
		
		<%--If the maxNum is 1, then we don't need the ctrl div --%>
		<%if(maxNum > 1){%>
		<div id="ctrlDiv">
	  		<center>
				<button dojoType="dijit.form.Button" onclick="getDataInf(num - 1)">上一条</button>
				
				第
   				<select name="number" onchange="getDataInf(this.selectedIndex + 1);">
	    			<%for(int j = 1; j <= maxNum; j++){%>
	    				<%if(num == j){%>
	    					<option selected="selected"><%=j %></option>
	    					
	    				<%continue;
	    				}%>	
	    				
			        	<option><%=j %></option>
	    			<%}%>
   				</select>
   				条
   				
    			<button dojoType="dijit.form.Button" onclick="getDataInf(num + 1)">下一条</button>
   			</center>
	  	</div>
	  	<%} %>
		<div id="infDiv">
		  	<table class="infTable">
		  		<%NodeList eleList = ele.getElementsByTagName("element");
		  		  for(int i = 0; i < eleList.getLength() && data != null; i++){ 
			    	Node tmpNode = eleList.item(i);
			    	
			    	if(tmpNode.getNodeType() != Element.ELEMENT_NODE)
			    		continue;			    	
	
			    	Element tmp = (Element)tmpNode;
			    	
			    	//Do not display the element whose display is false
			    	if(!tmp.getAttribute("display").equals("true"))
			    		continue;
			    	
			    	String type = tmp.getAttribute("type"); 
			    	String idName = ele.getElementsByTagName("id").item(0).getTextContent();
			    	String id = data.getAttr(idName);
			    	String tabName = ele.getElementsByTagName("tabName").item(0).getTextContent();
			    	%> 	
			    	
			    	<%if(type.equals("string")){%>
				    	<tr>
				    		<th><%=tmp.getElementsByTagName("attrTitle").item(0).getTextContent()%></th>
				    		<td><%=data.getAttr(tmp.getElementsByTagName("attrName").item(0).getTextContent()) %></td>
				    	</tr>
			    	<%}else if(type.equals("number")){%>
				    	<tr>
				    		<th><%=tmp.getElementsByTagName("attrTitle").item(0).getTextContent() %></th>
				    		<td><%=tmp.getElementsByTagName("attrName").item(0).getTextContent() %></td>
				    	</tr>
			    	<%}else if(type.equals("clob")){ %>
			    		<tr>
				    		<th><%=tmp.getElementsByTagName("attrTitle").item(0).getTextContent() %></th>
				    		<td onclick="openClob(document.getElementById('clobPane<%=i %>'), '<%=tabName %>','<%=idName%>','<%=id %>','<%=tmp.getElementsByTagName("attrName").item(0).getTextContent() %>' )">
								<div  dojoType="dijit.TitlePane" title="点击查看" open="false" >
									<span id="clobPane<%=i %>" style="text-align:left; width:100%; height: 100%; "></span>
								</div>
				    		</td>
				    	</tr>
			    	<%} %>
			    <%}%>
		  	</table>
	  	</div>

  </body>
</html>
