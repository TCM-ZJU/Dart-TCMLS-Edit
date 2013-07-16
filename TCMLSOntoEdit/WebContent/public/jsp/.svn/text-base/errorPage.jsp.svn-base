<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>错误信息：</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<center><h2>Sorry 出错了！</h2></center>
  	<p>以下是错误的具体信息：</p>
	Exception: <%= exception %><br>
	Message: <%= exception.getMessage() %><br>
	Localized Message: <%= exception.getLocalizedMessage() %><br>
	Stack Trace: <% exception.printStackTrace(new java.io.PrintWriter(out)); %><br>
  </body>
</html>



