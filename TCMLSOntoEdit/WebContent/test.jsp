<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
    	@import "/TFGWProject/public/js/dojo-release-1.1.0/dijit/themes/tundra/tundra.css";    
        @import "/TFGWProject/public/js/dojo-release-1.1.0/dojo/resources/dojo.css"
    </style>
    
	<script type="text/javascript" src="/TFGWProject/public/js/dojo-release-1.1.0/dojo/dojo.js" djConfig="parseOnLoad:true, isDebug:true"></script>
	
	<script language="javascript">
		//导入所需的javascrilpt代码
		dojo.require("dojo.NodeList-fx");
		dojo.require("dijit.Toolbar");
		dojo.require("dijit.form.Button");
		
		//Containers
		dojo.require("dijit.layout.StackContainer");
		dojo.require("dijit.layout.BorderContainer");
		dojo.require("dijit.layout.AccordionContainer");
	</script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <style type="text/css">
  	#displayWin #leftWin{
  		width:33%;
  		height:90%;
  	}
  	
  	#displayWin #midWin{
  		width:33%;
  		height:90%;
  	}
  	
  	#displayWin #rightWin{
  		width:33%;
  		height:90%;
  	}
  	
  	#displayWin #topWin{
  		width:33%;
  		height:100%;
  	}
  	
  	#displayWin #mainWin{
  		width:50%;
  		height:50%;
  	}
  </style>
  
  <body id="displayWin" class="tundra">
  	<button id="preBtn" dojoType="dijit.form.Button"></button>
    <div id="mainWin" dojoType="dijit.layout.BorderContainer">
    	<div id="leftWin" dojoType="dijit.layout.StackContainer" region="left">
    		<p dojoType="dijit.layout.ContentPane">
    			assssssssssssssssssss
    		</p>
    		<p dojoType="dijit.layout.ContentPane">
    			bssssssssssssssssssss
    		</p>
    		<p dojoType="dijit.layout.ContentPane">
    			cssssssssssssssssssss
    		</p>
    	</div>
    	<div id="midWin" dojoType="dijit.layout.StackContainer" region="center">
    	</div>
    	<div id="rightWin" dojoType="dijit.layout.StackContainer" region="right">
    	</div>
    </div>
  </body>
</html>
