<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>
<jsp:directive.page import="org.springframework.context.ApplicationContext"/>
<jsp:directive.page import="cn.edu.zju.ccnt.TFGW.operation.DataCtrl.MultiSearcher"/>
<jsp:directive.page import="cn.edu.zju.ccnt.TFGW.GetFactory"/>
<jsp:directive.page import="cn.edu.zju.ccnt.TFGW.object.xmlInf.*"/>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%--Get parameters from the given parameters --%>
<%
	//The paremeters use to control the layout of the three pane
	String layoutDisease = "left";
	String layoutExpert = "center";
	String layoutHospital = "right";
	
	//搜索条件和结果提示：
	StringBuffer searchStr = new StringBuffer("在按照");	
	
	String type = request.getParameter("type");
	if(request.getParameter("count") == null || request.getParameter("number") == null){
		response.sendRedirect("/TFGWProject/public/jsp/search.jsp");
	}
	
	int count = Integer.parseInt(request.getParameter("count"));
	int num = Integer.parseInt(request.getParameter("number"));
	
	String[] params; 
	boolean flag = false;
	
	if(type.equals("disease")){
		layoutDisease = "left";
		layoutExpert = "center";
		layoutHospital = "right";
		
		params = new String[4];
		flag = false;
		
		params[0] = (new String(request.getParameter("diseaseName").getBytes("ISO8859-1"), "UTF-8"));
		params[1] = (new String(request.getParameter("pathogeny").getBytes("ISO8859-1"), "UTF-8"));
		params[2] = (new String(request.getParameter("symptom").getBytes("ISO8859-1"), "UTF-8"));
		params[3] = (new String(request.getParameter("semiotic").getBytes("ISO8859-1"), "UTF-8"));
		
		for(int i = 0; i < params.length; i++){
			if(params[i].length() > 0){
				flag = true;
				break;
			}
		}
		
		if(flag == false){
			throw new Exception("参数不能全部为空！");
		}
		
		//生成搜索条件提示信息
		if(params[0].length() > 0){
			searchStr.append("疾病名称为" +"<span class='params'>" + params[0] + "&nbsp</span>");
		}
		if(params[1].length() > 0){
			searchStr.append("病因为" +"<span class='params'>" + params[1] + "&nbsp</span>");
		}
		if(params[2].length() > 0){
			searchStr.append("症状为" +"<span class='params'>" + params[2] + "&nbsp</span>");
		}
		if(params[3].length() > 0){
			searchStr.append("症候为" +"<span class='params'>" + params[3] + "&nbsp</span>");
		}
		
		searchStr.append("的限定条件查询<span class='params'>疾病信息</span>时，得到以下结果：");
	}
	else if(type.equals("expert")){
		layoutExpert = "left";
		layoutDisease = "center";
		layoutHospital = "right";
		
		params = new String[4];
		flag = false;
		
		params[0] = (new String(request.getParameter("expertName").getBytes("ISO8859-1"), "UTF-8"));
		params[1] = (new String(request.getParameter("section").getBytes("ISO8859-1"), "UTF-8"));
		params[2] = (new String(request.getParameter("disease").getBytes("ISO8859-1"), "UTF-8"));
		if(request.getParameter("workTimeCheck") != null && request.getParameter("workTimeCheck").equals("true")){
			params[3] = (new String(request.getParameter("workTime").getBytes("ISO8859-1"), "UTF-8"));
		}
		else{
			params[3] = "";
		}
		
		for(int i = 0; i < params.length; i++){
			if(params[i].length() > 0){
				flag = true;
				break;
			}
		}
		
		//生成搜索条件提示信息
		if(flag == false){
			throw new Exception("参数不能全部为空！");
		}
		
		if(params[0].length() > 0){
			searchStr.append("专家姓名为" +"<span class='params'>" + params[0] + "&nbsp</span>");
		}
		if(params[1].length() > 0){
			searchStr.append("所属科室为" +"<span class='params'>" + params[1] + "&nbsp</span>");
		}
		if(params[2].length() > 0){
			searchStr.append("主治疾病为" +"<span class='params'>" + params[2] + "&nbsp</span>");
		}
		if(params[3].length() > 0){
			searchStr.append("门诊时间为" +"<span class='params'>" + params[3] + "&nbsp</span>");
		}
		
		searchStr.append("的限定条件查询<span class='params'>专家信息</span>时，得到以下结果：");
	}
	else if(type.equals("hospital")){
		layoutHospital = "left";
		layoutExpert = "center";
		layoutDisease = "right";
		
		params = new String[5];
		flag = false;
		
		params[0] = (new String(request.getParameter("hospitalName").getBytes("ISO8859-1"), "UTF-8"));
		params[1] = (new String(request.getParameter("hospitalLevel").getBytes("ISO8859-1"), "UTF-8"));
		params[2] = (new String(request.getParameter("feature").getBytes("ISO8859-1"), "UTF-8"));
		params[3] = (new String(request.getParameter("bedspred1").getBytes("ISO8859-1"), "UTF-8"));
		params[4] = (new String(request.getParameter("bedspred2").getBytes("ISO8859-1"), "UTF-8"));
	
		
		for(int i = 0; i < params.length; i++){
			if(params[i].length() > 0){
				flag = true;
				break;
			}
		}
		
		if(flag == false){
			throw new Exception("参数不能全部为空！");
		}
		
		if(params[0].length() > 0){
			searchStr.append("医院名称为" +"<span class='params'>&nbsp;" + params[0] + "&nbsp;</span>");
		}
		if(params[1].length() > 0){
			searchStr.append("医院等级为" +"<span class='params'>&nbsp;" + params[1] + "&nbsp;</span>");
		}
		if(params[2].length() > 0){
			searchStr.append("特色专科为" +"<span class='params'>&nbsp;" + params[2] + "&nbsp;</span>");
		}
		if(params[3].length() > 0){
			searchStr.append("床位数为" +"<span class='params'>&nbsp;" + params[3] + "&nbsp;~</span>" + 
							 "<span class='params'>&nbsp;" + params[4] + "&nbsp;</span>");
		}
		
		searchStr.append("的限定条件查询<span class='params'>医院信息</span>时，得到以下结果：");
	}
	else{
		throw new Exception("搜索信息分类错误！");
	}
%>

<%
	int tabCount = 0;
	int diseaseCount = 0;
	int distinctDiseaseCount = 0;
	int expertCount = 0;
	int hospitalCount = 0;
	
	ArrayList<TabInf> diseaseTabInf = new ArrayList<TabInf>();
	LinkedHashMap<String, ArrayList<DiseaseInf>> diseaseInfMap = new LinkedHashMap<String, ArrayList<DiseaseInf>>();
	
	ArrayList<DiseaseInf> diseaseInf = new ArrayList<DiseaseInf>();
	ArrayList<ExpertInf> expertInf = new ArrayList<ExpertInf>();
	ArrayList<HospitalInf> hospitalInf = new ArrayList<HospitalInf>();
	
	ApplicationContext factory = GetFactory.getFactory();
	MultiSearcher searcher = (MultiSearcher)factory.getBean("multiSearcher");
	
	if(type.equals("disease")){
		
		diseaseTabInf = searcher.searchTabInf(params);
		for(int i = 0; i < diseaseTabInf.size(); i++){
			diseaseCount += diseaseTabInf.get(i).getNumber();
			distinctDiseaseCount += diseaseTabInf.get(i).getDistinctCount();
			
			diseaseInfMap.put( diseaseTabInf.get(i).getTableName(), 
							   searcher.searchDiseaseInf( params, 
							   							  diseaseTabInf.get(i).getTableName(), 
							   							  count, 
							   							  num
							   							 )
							 );
		}
		tabCount = diseaseTabInf.size();
		
		expertInf = searcher.searchExpertInfByDisease(params, count, num);
		expertCount = searcher.searchExpertCountByDisease(params);
		
		hospitalInf = searcher.searchHospitalInfByDisease(params, count, num);
		hospitalCount = searcher.searchHospitalCountByDisease(params);
	}
	else if(type.equals("expert")){
		expertInf = searcher.searchExpertInf(params, count, num);
		expertCount = searcher.searchExpertCount(params);
		
		diseaseInf = searcher.searchDiseaseInfByExpert(params, count, num);
		diseaseCount = searcher.searchDiseaseCountByExpert(params);
		
		hospitalInf = searcher.searchHospitalInfByExpert(params, count, num);
		hospitalCount = searcher.searchHospitalCountByExpert(params);
	}
	else if(type.equals("hospital")){
		hospitalInf = searcher.searchHospitalInf(params, count, num);
		hospitalCount = searcher.searchHospitalInfCount(params);
		
		expertInf = searcher.searchExpertInfByHospital(params, count, num);
		expertCount = searcher.searchExpertCountByHospital(params);
		
		diseaseInf = searcher.searchDiseaseInfByHospital(params, count, num);
		diseaseCount = searcher.searchDiseaseCountByHospital(params);
		
	}
	
	//生成搜索结果信息，并整合到searchInf中	
	StringBuffer searchInf = new StringBuffer("");
	String diseaseStr;
	String expertStr;
	String hospitalStr;
	
	if(type.equals("disease"))
		diseaseStr = "在<span class='resultInf'>&nbsp;" + tabCount + "&nbsp;</span>" +
				     "个疾病数据库中得到疾病信息" + "<span class='resultInf'>&nbsp;" + distinctDiseaseCount + "&nbsp;</span>条" +
				     "共" + "<span class='resultInf'>&nbsp;" + diseaseCount + "&nbsp;</span>条数据<br/>";
	else
		diseaseStr = "在疾病数据库中得到疾病信息" + "<span class='resultInf'>&nbsp;" + diseaseCount + "&nbsp;</span>条<br/>";

	expertStr = "在北京中医名家数据库中得到专家信息" + "<span class='resultInf'>&nbsp;" + expertCount + "&nbsp;</span>条<br/>";
    hospitalStr = "在北京医院数据库中得到医院信息" + "<span class='resultInf'>&nbsp;" + hospitalCount + "&nbsp;</span>条<br/>";
	
    
	if(type.equals("disease")){
		searchInf.append(diseaseStr + 
						 expertStr +  
						 hospitalStr);
	} 
	if(type.equals("expert")){
		searchInf.append(expertStr + 
						 hospitalStr + 
						 diseaseStr);
	} 
	if(type.equals("hospital")){
		searchInf.append(hospitalStr + 
						 expertStr + 
						 diseaseStr);
	} 
    
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>搜索</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
    	@import "/TFGWProject/public/css/searchProcess.css";
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
  	<script type='text/javascript' src='/TFGWProject/public/js/searchProcess.js'></script>
  </head>
  
  <script type="text/javascript"> 
  	//导入所需的javascrilpt代码
		dojo.require("dojo.NodeList-fx");
		
		dojo.require("dijit.Toolbar");
		dojo.require("dijit.form.Button");
		dojo.require("dijit.form.TextBox");
		dojo.require("dijit.form.ComboBox");
		dojo.require("dijit.form.NumberTextBox");
		
		dojo.require("dijit.TitlePane");
		dojo.require("dijit.layout.BorderContainer");
		dojo.require("dijit.layout.AccordionContainer");
		dojo.require("dijit.layout.TabContainer");
  </script>
  
  <script language="javascript">
  	var num = <%=num%>;
  	var count = <%=count%>;
  	var params = new Array(4);
  	var numExpert = <%=num%>; 
  	var maxNumExpert = <%=expertCount%>;
  	var numHospital = <%=num%>; 
  	var maxNumHospital = <%=hospitalCount%>;
  	<%if(!type.equals("disease")){%>
	 	var numDisease = <%=num%>;
		var maxNumDisease = <%=diseaseCount%>;
    <%}%>
    	
  	<%for(int i = 0; i < params.length; i++){%>
  		params[<%=i%>] = "<%=params[i]%>";
  	<%} %>
  	
  	//The number of current position
  	<%for(int i = 0; i < diseaseTabInf.size(); i++){
    	TabInf tmp = diseaseTabInf.get(i);%>
    	
    	<%if(tmp.getTableName().equals("JMZ_JB")){%>
    		var numJMZ_JB = num;
  			var maxNumJMZ_JB = <%=tmp.getDistinctCount()%>;
    	<%}%>
    	
    	<%if(tmp.getTableName().equals("C_JIB")){%>
    		var numC_JIB = num;
  			var maxNumC_JIB = <%=tmp.getDistinctCount()%>;
    	<%}%>
    	
    	<%if(tmp.getTableName().equals("ZDFZ")){%>
    		var numZDFZ = num;
  			var maxNumZDFZ = <%=tmp.getDistinctCount()%>;
    	<%}%>
    	
    	<%if(tmp.getTableName().equals("ZYBFZ")){%>
    		var numZYBFZ = num;
  			var maxNumZYBFZ = <%=tmp.getDistinctCount()%>;
    	<%}%>
    	
    	<%if(tmp.getTableName().equals("YJYA")){%>
    		var numZYBFZ = num;
  			var maxNumZYBFZ = <%=tmp.getDistinctCount()%>;
    	<%}%>
  	<%}%>
  	
  </script>
  
  <body id="displayWin" class="soria">
    <div id="topWin" dojoType="dijit.TitlePane" title="<%=searchStr.toString() %>" open="true">
			<%=searchInf.toString() %>
	</div>
	
    <div id="mainWin" dojoType="dijit.layout.BorderContainer">
    	<div id="leftWin" class="infPane" dojoType="dijit.layout.BorderContainer" region="<%=layoutDisease %>" splitter="true">
    		<div dojoType="dijit.Toolbar" class="infTitle" region="top">
    			<p class="infTitleP">疾病信息(<span class="resultInf"><%=diseaseCount %></span>)</p>
    		</div>
    		
    		<%if(type.equals("disease")){ %>
    		
    		<div dojoType="dijit.layout.AccordionContainer" 
    		region="center" minSize="20" style="height:95%;width:100%;">
    		
	   			<%for(int i = 0; i < diseaseTabInf.size(); i++){
	   				ArrayList<DiseaseInf> tmpDisease = diseaseInfMap.get(diseaseTabInf.get(i).getTableName());
	   			%>
				<div dojoType="dijit.layout.AccordionPane"  title="<%=diseaseTabInf.get(i).getName()%> <%=diseaseTabInf.get(i).getDistinctCount() %>疾病 <%=diseaseTabInf.get(i).getNumber()%>数据" <%if(i==diseaseTabInf.size()-1){ %>selected="true"<%} %>>				
					<%if(diseaseTabInf.get(i).getDistinctCount() > count){ %>
					<span align="left">
						<button dojoType="dijit.form.Button" onclick="getData('<%=diseaseTabInf.get(i).getTableName() %>',count,'prev',0, '<%=type %>');">上一页</button>
						
						第
	    				<select name="number" id="<%=diseaseTabInf.get(i).getTableName()%>Select" onchange="getData('<%=diseaseTabInf.get(i).getTableName() %>',count,'num',this.selectedIndex*count, '<%=type %>');">
			    			<%for(int j = 1; j <= diseaseTabInf.get(i).getDistinctCount()/count; j++){%>
					        <option><%=j %></option>
			    			<%}%>
			    			<%if(diseaseTabInf.get(i).getDistinctCount()%count > 0){%>
			    			<option><%=(diseaseTabInf.get(i).getDistinctCount()/count + 1) %></option>
			    			<%} %>
	    				</select>
	    				页

		    			<button dojoType="dijit.form.Button" onclick="getData('<%=diseaseTabInf.get(i).getTableName() %>',count,'next',0, '<%=type %>');">下一页</button>
	    			</span>
	    			<%} %>
	    			
					<table class="infTable" id="<%=diseaseTabInf.get(i).getTableName()%>Tab" style="margin:0;">
						<tr class="infTitleTr">
							<th style="text-align:center">疾病名称</th>
							<th style="text-align:center">相关专家</th>
							<th style="text-align:center">相关医院</th>
							<th style="text-align:center">行业标准</th>
							<th style="text-align:center">政策法规</th>
						</tr>
					<%for(int j = 1; j <= tmpDisease.size(); j++){ %>
						<tr class="row<%=j%2%>">
							<td class="clickInf" onclick="getDetailValue('<%=diseaseTabInf.get(i).getTableName() %>',getSearchAttr('<%=diseaseTabInf.get(i).getTableName() %>'),'<%=tmpDisease.get(j - 1).getName() %>',<%=tmpDisease.get(j - 1).getCorrelation() %>,1);">
								<%=tmpDisease.get(j - 1).getName()%>(<%=tmpDisease.get(j - 1).getCorrelation() %>)
							</td>		
							<td>
								<span class="relaSearch" onclick="getRelativeValue('disease.expert', '<%=tmpDisease.get(j - 1).getName()%>', 0, 20);">
									查看
								</span>
							</td>
		    				<td>
		    					<span class="relaSearch" onclick="getRelativeValue('disease.hospital', '<%=tmpDisease.get(j - 1).getName()%>', 0, 20);">
		    						查看
		    					</span>
		    				</td>
		    				<td class="clickInf" onclick="getDetailValue('ZYBZZLBZ',getSearchAttr('ZYBZZLBZ'),'<%=tmpDisease.get(j - 1).getName() %>',0,1);">
		    					查看				
		    				</td>
		    				<td class="clickInf" onclick="getDetailValue('ZCFG',getSearchAttr('ZCFG'),'<%=tmpDisease.get(j - 1).getName() %>',0,1);">
		    					查看
		    				</td>
						</tr>
					<%} %>
					</table>		
				</div>
			
	  			<%}%>
	  		</div>
	  		<%}else{%>
	  			<div dojoType="dijit.layout.ContentPane" region="center" class="infWin">
	  				<%if(diseaseCount > count){ %>
					<span align="left">
						<button dojoType="dijit.form.Button" onclick="getData('disease',count,'prev',0, '<%=type %>');">上一页</button>
						
						第
	    				<select name="number" id="diseaseSelect" onchange="getData('disease',count,'num',this.selectedIndex*count, '<%=type %>');">
			    			<%for(int j = 1; j <= diseaseCount/count; j++){%>
					        <option><%=j %></option>
			    			<%}%>
			    			<%if(diseaseCount%count > 0){%>
			    			<option><%=(diseaseCount/count + 1) %></option>
			    			<%} %>
	    				</select>
	    				页

		    			<button dojoType="dijit.form.Button" onclick="getData('disease',count,'next',0, '<%=type %>');">下一页</button>
	    			</span>
	    			<%} %>
	    			
					<table class="infTable" id="diseaseTab" style="margin:0;">
						<tr class="infTitleTr">
							<th style="text-align:center">疾病名称</th>
							<th style="text-align:center">相关专家</th>
							<th style="text-align:center">相关医院</th>
							<th style="text-align:center">行业标准</th>
							<th style="text-align:center">政策法规</th>
						</tr>
					<%for(int j = 1; j <= diseaseInf.size(); j++){ %>
						<tr class="row<%=j%2%>">
							<td class="clickInf" onclick="getDetailValue('check', 'JBMC', '<%=diseaseInf.get(j - 1).getName() %>', <%=diseaseInf.get(j - 1).getCorrelation() %>, 0);">
								<%=diseaseInf.get(j - 1).getName() %>(<%=diseaseInf.get(j - 1).getCorrelation() %>)
							</td>		
							<td>
								<span class="relaSearch" onclick="getRelativeValue('disease.expert', '<%=diseaseInf.get(j - 1).getName()%>', 0, 20);">
									查看
								</span>
							</td>
		    				<td>
		    					<span class="relaSearch" onclick="getRelativeValue('disease.hospital', '<%=diseaseInf.get(j - 1).getName()%>', 0, 20);">
		    						查看
		    					</span>
		    				</td>
		    				<td class="clickInf" onclick="getDetailValue('ZYBZZLBZ',getSearchAttr('ZYBZZLBZ'),'<%=diseaseInf.get(j - 1).getName() %>',0,1);">
		    					查看				
		    				</td>
		    				<td class="clickInf" onclick="getDetailValue('ZCFG',getSearchAttr('ZCFG'),'<%=diseaseInf.get(j - 1).getName() %>',0,1);">
		    					查看
		    				</td>
						</tr>
					<%} %>
					</table>
				</div>
			<%} %>
    	</div>
    	
    	<div id="midWin" class="infPane" dojoType="dijit.layout.BorderContainer" region="<%=layoutExpert %>" splitter="true">
    		<div dojoType="dijit.Toolbar" region="top" class="infTitle">
    			<p class="infTitleP">医生信息(<span class="resultInf"><%=expertCount %></span>)</p>
    		</div>
    		
    		<div dojoType="dijit.layout.ContentPane" region="center" class="infWin">  
		    	<%if(expertCount > count){ %>
					<span align="left">
						<button dojoType="dijit.form.Button" onclick="getData('expert',count,'prev',0, '<%=type %>');">上一页</button>
									
						第
	    				<select name="number" id="expertSelect" onchange="getData('expert',count,'num',this.selectedIndex*count, '<%=type %>');">
			    			<%for(int j = 1; j <= expertCount/count; j++){%>
					        <option><%=j %></option>
			    			<%}%>
			    			<%if(expertCount%count > 0){%>
			    			<option><%=(expertCount/count + 1) %></option>
			    			<%} %>
	    				</select>
	    				页
	    				
		    			<button dojoType="dijit.form.Button" onclick="getData('expert',count,'next',0, '<%=type %>');">下一页</button>
	    			</span>
    			<%} %>	
		    	<table class="infTable" id="expertTab">
					<tr class="infTitleTr">
						<th>姓名</th>
						<th>主治疾病</th>
						<th>所属医院</th>
					</tr>
			    	<%for(int i = 1; i <= expertInf.size(); i++){%>
		    		<tr class="row<%=i%2%>">
		    			<td class="clickInf"  onclick="getDetailValue('expert',getSearchAttr('expert'),'<%=expertInf.get(i - 1).getId() %>', 1, 1);">
		    				<%=expertInf.get(i-1).getName() %>(<%=expertInf.get(i-1).getCorrelation() %>)
		    			</td>
		    			<td>
							<span class="relaSearch" onclick="getRelativeValue('expert.disease', '<%=expertInf.get(i - 1).getId()%>', 0, 20, '<%=expertInf.get(i - 1).getName()%>');">
								查看
							</span>
						</td>
	    				<td>
	    					<span class="relaSearch" onclick="getRelativeValue('expert.hospital', '<%=expertInf.get(i - 1).getId()%>', 0, 20, '<%=expertInf.get(i - 1).getName()%>');">
	    						查看
	    					</span>
	    				</td>
		    		</tr>
			    	<%}%>
		    	</table>
	    	</div>
    	</div>
    	
    	<div id="rightWin" class="infPane" dojoType="dijit.layout.BorderContainer" region="<%=layoutHospital %>" splitter="true">
    		<div dojoType="dijit.Toolbar" region="top" class="infTitle">
    			<p class="infTitleP">医院信息(<span class="resultInf"><%=hospitalCount %></span>)</p>
    		</div>
    		<div dojoType="dijit.layout.ContentPane" region="center" class="infWin">
		    	<%if(hospitalCount > count){ %>
					<span align="left">
						<button dojoType="dijit.form.Button" onclick="getData('hospital',count,'prev',0, '<%=type %>');">上一页</button>
							
						第
	    				<select name="number" id="hospitalSelect" onchange="getData('hospital',count,'num',this.selectedIndex*count, '<%=type %>');">
			    			<%for(int j = 1; j <= hospitalCount/count; j++){%>
					        <option><%=j %></option>
			    			<%}%>
			    			<%if(hospitalCount%count > 0){%>
			    			<option><%=(hospitalCount/count + 1) %></option>
			    			<%} %>
	    				</select>
	    				页
	    			
		    			<button dojoType="dijit.form.Button" onclick="getData('hospital',count,'next',0, '<%=type %>');">下一页</button>
	    			</span>
    			<%} %>
    			
		    	<table class="infTable" id="hospitalTab">
					<tr class="infTitleTr">
						<th>医院名称</th>
						<th>相关专家</th>
						<th>相关疾病</th>
					</tr>
			    	<%for(int i = 1; i <= hospitalInf.size(); i++){	%>
		    		<tr class="row<%=i%2%>">
		    			<td class="clickInf" onclick="getDetailValue('hospital',getSearchAttr('hospital'),'<%=hospitalInf.get(i - 1).getId() %>',1,1);">
		    				<%=hospitalInf.get(i-1).getName() %>(<%=hospitalInf.get(i-1).getCorrelation() %>)
		    			</td>
		    			<td>
							<span class="relaSearch" onclick="getRelativeValue('hospital.expert', '<%=hospitalInf.get(i - 1).getId()%>', 0, 20, '<%=hospitalInf.get(i - 1).getName() %>');">
								查看
							</span>
						</td>
	    				<td>
	    					<span class="relaSearch" onclick="getRelativeValue('hospital.disease', '<%=hospitalInf.get(i - 1).getId()%>', 0, 20, '<%=hospitalInf.get(i - 1).getName() %>');">
	    						查看
	    					</span>
	    				</td>
		    		</tr>
			    	<%}%>
		    	</table>
	    	</div>
    	</div>
    </div>
  </body>
</html>
