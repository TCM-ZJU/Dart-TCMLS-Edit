<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.io.*"%>
<%@ page language="java" import="org.w3c.dom.*" %>
<%@ page language="java" import="javax.xml.parsers.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String realPath = request.getRealPath("/");
	
	String srcType = new String(request.getParameter("srcType").getBytes("ISO8859-1"),"UTF-8");
	String title = "";
	String inf = "";
	
	/********************************Read the configuration file****************************************/
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = docFactory.newDocumentBuilder();	
	Document xmlDoc = builder.parse(new File(realPath+"WEB-INF/conf/dataResource/introduction.xml"));
	Element ele = (Element)xmlDoc.getElementsByTagName(srcType).item(0);
%>

<% 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'introduction.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
    	@import "/TFGWProject/public/js/dojo-release-1.1.0/dijit/themes/soria/soria.css";   
        @import "/TFGWProject/public/js/dojo-release-1.1.0/dojo/resources/dojo.css"
    </style>
    
    <script type="text/javascript" src="/TFGWProject/public/js/dojo-release-1.1.0/dojo/dojo.js" djConfig="parseOnLoad:true, isDebug:true"></script>
  	<script language="javascript">
  	//导入所需的javascrilpt代码
		dojo.require("dijit.Toolbar");

		dojo.require("dijit.layout.ContentPane");
		dojo.require("dijit.layout.BorderContainer");
  	</script>
  </head>
  
  <body class="soria" style="text-align:center;">
  	<div dojoType="dijit.layout.BorderContainer">
    	<div dojoType="dijit.Toolbar" style="width:95%;height:36px;">
    		<span align="left" style="font-size:28px;padding:6px 0px 0 20px;margin:0px;font-weight:bold;"><%=ele.getElementsByTagName("title").item(0).getTextContent() %></span>
    	</div>
    	<div style="width:95%;font-size:20px;font-size:bold;line-height:200%;text-align:left;">
    		<p><%=ele.getElementsByTagName("inf").item(0).getTextContent() %></p>
    	</div>
    </div>
  </body>
</html>
