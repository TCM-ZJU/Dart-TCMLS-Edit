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
	//搜索条件和结果提示：
	String searchStr = "";
	String resultStr = "";
	
	String tret = "";
	String type = (new String(request.getParameter("type").getBytes("ISO8859-1"), "UTF-8"));
	String searchAttr = (new String(request.getParameter("searchAttr").getBytes("ISO8859-1"), "UTF-8"));
	int count = Integer.parseInt(request.getParameter("count"));
	int num = Integer.parseInt(request.getParameter("num"));
	
	if(request.getParameter("tret") != null){
		tret = (new String(request.getParameter("tret").getBytes("ISO8859-1"), "UTF-8"));
	}
	
	int diseaseCount = 0;
	int expertCount = 0;
	int hospitalCount = 0;
		
	ArrayList<DiseaseInf> diseaseInf = new ArrayList<DiseaseInf>();
	ArrayList<ExpertInf> expertInf = new ArrayList<ExpertInf>();
	ArrayList<HospitalInf> hospitalInf = new ArrayList<HospitalInf>();
	ArrayList<Object> tmpSet = new ArrayList<Object>();
	int tmpCount = 0;
	
	ApplicationContext factory = GetFactory.getFactory();
	MultiSearcher searcher = (MultiSearcher)factory.getBean("multiSearcher");
	
	//Get the result set, which is saved in 
	if(type.startsWith("disease")){
		DiseaseInf searchInf = new DiseaseInf();
		searchInf.setName(searchAttr);
		tmpSet = searcher.searchRelateInf(searchInf, type, num ,count);
		tmpCount = searcher.searchRelateInfCount(searchInf, type);
		
		searchStr += "根据疾病" + "<span class='params'>" + searchAttr + "</span>搜索得到";
	}
	else if(type.startsWith("expert")){
		ExpertInf searchInf = new ExpertInf();
		searchInf.setId(searchAttr);
		tmpSet = searcher.searchRelateInf(searchInf, type, num ,count);
		tmpCount = searcher.searchRelateInfCount(searchInf, type);
		
		searchStr += "根据专家" + "<span class='params'>" + tret + "</span>搜索得到";
	}
	else if(type.startsWith("hospital")){
		HospitalInf searchInf = new HospitalInf();
		searchInf.setId(searchAttr);
		tmpSet = searcher.searchRelateInf(searchInf, type, num ,count);
		tmpCount = searcher.searchRelateInfCount(searchInf, type);
		
		searchStr += "根据医院" + "<span class='params'>" + tret + "</span>搜索得到";
	}
	else{
		throw new Exception("搜索信息分类错误！");
	}

	//Put the result set in a real result set
	if(type.endsWith("expert")){
		for(int i = 0; i < tmpSet.size(); i++)
			expertInf.add((ExpertInf)tmpSet.get(i));
		expertCount = tmpCount;
		
		searchStr += "专家&nbsp;<span class='params'>" + tmpCount + "</span>&nbsp;个";
	}
	else if(type.endsWith("hospital")){
		for(int i = 0; i < tmpSet.size(); i++)
			hospitalInf.add((HospitalInf)tmpSet.get(i));	
		hospitalCount = tmpCount;
		
		searchStr += "医院&nbsp;<span class='params'>" + tmpCount + "</span>&nbsp;所";
	}
	else if(type.endsWith("disease")){
		for(int i = 0; i < tmpSet.size(); i++)
			diseaseInf.add((DiseaseInf)tmpSet.get(i));		
		diseaseCount = tmpCount;
		
		searchStr += "疾病信息&nbsp;<span class='params'>" + tmpCount + "</span>&nbsp;条";
	}
	else{
		throw new Exception("搜索信息分类错误！");
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
  	<script type='text/javascript' src='/TFGWProject/public/js/getRelative.js'></script>
  </head>
  
  <script type="text/javascript"> 
  		<%if(tmpCount <= 0){%>
			setTimeout("window.close()", 1500);
		<%resultStr="<h3 style='text-align:center'>对不起 没有有效的查询结果</h3><h3 style='text-align:center;'>该窗口将在三秒内自动<span style='color:red;text-decoration:underline;cursor:pointer;' onclick='window.close();'>关闭</span></h3>";
		}%>
		
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
		
		maxNum = <%=tmpCount%>;
  </script>
  
  <style type="text/css">
	  .params{
			font-weight:bold;
			color:red;
		}
  </style>
  
  <body id="displayWin" class="soria">
    <div id="topWin" dojoType="dijit.TitlePane" title="<%=searchStr.toString() %>" open="false">
		<%=searchStr %>
	</div>
	<%if(resultStr.length() > 0){ %>
		<%=resultStr %>
	<%} %>
	<div id="ctrlDiv">
	<%if(tmpCount > count){ %>
		<span align="left">
			<button dojoType="dijit.form.Button" onclick="reloadPage('<%=type %>','<%=searchAttr %>',<%=num-count %>, <%=count %>, '<%=tret %>')">上一页</button>
		
			第
			<select name="number" id="diseaseSelect" onchange="reloadPage('<%=type %>','<%=searchAttr %>',this.selectedIndex*<%=count %>, <%=count %>, '<%=tret %>')">
	  			<%int tmpInt = 0;
	  			  if(tmpCount%count > 0)
	  				  tmpInt = 1;
	  			  
	  			  for(int j = 1; j <= (tmpCount/count + tmpInt); j++){%>
	  				<%if((num/count + 1) == j){%>
	    				<option selected="selected"><%=j %></option>
	    			<%continue; 
	    			}%>
	    			
	      			<option><%=j %></option>
				<%}%>
			
			</select>
			页
	
			<button dojoType="dijit.form.Button" onclick="reloadPage('<%=type %>','<%=searchAttr %>',<%=num+count %>, <%=count %>, '<%=tret %>')">下一页</button>
		</span>
	<%} %>
	</div>	
	
	<div id="infDiv">
	<%if(type.endsWith("disease") && diseaseInf.size()>0){ %>
		<table class="infTable" id="diseaseTab" style="margin:0;">
			<tr class="infTitleTr">
				<th style="text-align:center">疾病名称</th>
				<th style="text-align:center">相关专家</th>
				<th style="text-align:center">相关医院</th>
			</tr>
			<%for(int j = 1; j <= diseaseInf.size(); j++){ %>
			<tr class="row<%=j%2%>">
				<td class="clickInf" onclick="getDetailValue('check', 'JBMC', '<%=diseaseInf.get(j - 1).getName() %>', <%=diseaseInf.get(j - 1).getCorrelation() %>, 0)">
					<%=diseaseInf.get(j - 1).getName() %>
				</td>		
				<td>
					<span class="relaSearch" onclick="getRelativeValue('disease.expert', '<%=diseaseInf.get(j - 1).getName()%>', 0, 20, '<%=diseaseInf.get(j - 1).getName()%>');">
						查看
					</span>
				</td>
   				<td>
   					<span class="relaSearch" onclick="getRelativeValue('disease.hospital', '<%=diseaseInf.get(j - 1).getName()%>', 0, 20, '<%=diseaseInf.get(j - 1).getName()%>');">
   						查看
   					</span>
   				</td>
			</tr>
			<%} %>
		</table>
	<%}else if(type.endsWith("expert") && expertInf.size()>0){ %>
		<table class="infTable" id="expertTab">
			<tr class="infTitleTr">
				<th>姓名</th>
				<th>主治疾病</th>
				<th>所属医院</th>
			</tr>
		  	<%for(int i = 1; i <= expertInf.size(); i++){%>
			<tr class="row<%=i%2%>">
				<td class="clickInf"  onclick="getDetailValue('expert',getSearchAttr('expert'),'<%=expertInf.get(i - 1).getId() %>', 1, 1)">
					<%=expertInf.get(i-1).getName() %>
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
	<%}else if(type.endsWith("hospital") && hospitalInf.size()>0){ %>
		<table class="infTable" id="hospitalTab">
			<tr class="infTitleTr">
				<th>医院名称</th>
				<th>相关专家</th>
				<th>相关疾病</th>
			</tr>
		  	<%for(int i = 1; i <= hospitalInf.size(); i++){	%>
			<tr class="row<%=i%2%>">
				<td class="clickInf" onclick="getDetailValue('hospital',getSearchAttr('hospital'),'<%=hospitalInf.get(i - 1).getId() %>',1,1)">
					<%=hospitalInf.get(i-1).getName() %>
				</td>
				<td>
					<span class="relaSearch" onclick="getRelativeValue('hospital.expert', '<%=hospitalInf.get(i - 1).getId()%>', 0, 20, '<%=hospitalInf.get(i - 1).getName()%>');">
						查看
					</span>
				</td>
   				<td>
   					<span class="relaSearch" onclick="getRelativeValue('hospital.disease', '<%=hospitalInf.get(i - 1).getId()%>', 0, 20, '<%=hospitalInf.get(i - 1).getName()%>');">
   						查看
   					</span>
   				</td>
			</tr>
		 	<%}%>
		</table>
	<%} %>
	</div>	
  </body>
</html>

