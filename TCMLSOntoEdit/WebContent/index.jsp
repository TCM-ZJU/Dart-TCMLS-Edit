<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>
<%@ page language="java" import="org.w3c.dom.*" %>
<%@ page language="java" import="javax.xml.parsers.*"%>
<%@ page language="java" import="java.io.*"%>

<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String realPath = request.getRealPath("/");
	
/********************************Read the configuration file****************************************/
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = docFactory.newDocumentBuilder();	
	Document xmlDoc = builder.parse(new File(realPath+"WEB-INF/conf/treeConf.xml"));
	
/********************************Read the data from configuration file****************************************/
	Element expertNode =  (Element)xmlDoc.getElementsByTagName("expert").item(0);
	String expertName = expertNode.getElementsByTagName("dbName").item(0).getTextContent();
	NodeList expertNodes = expertNode.getElementsByTagName("node");
	
	Element hospitalNode =  (Element)xmlDoc.getElementsByTagName("hospital").item(0);
	String hospitalName = hospitalNode.getElementsByTagName("dbName").item(0).getTextContent();
	NodeList hospitalNodes = hospitalNode.getElementsByTagName("node");
%>
<html>
  <head>
    <title>index.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <style>
    	@import "/TFGWProject/public/css/index.css";
    	@import "/TFGWProject/public/js/dojo-release-1.1.0/dijit/themes/tundra/tundra.css";    
    	@import "/TFGWProject/public/js/dojo-release-1.1.0/dijit/themes/soria/soria.css";   
        @import "/TFGWProject/public/js/dojo-release-1.1.0/dojo/resources/dojo.css"
    </style>
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<script type="text/javascript" src="/TFGWProject/public/js/dojo-release-1.1.0/dojo/dojo.js" djConfig="parseOnLoad:true, isDebug:false"></script>
	<script type='text/javascript' src='/TFGWProject/dwr/engine.js'></script>
  	<script type='text/javascript' src='/TFGWProject/dwr/util.js'></script>
  	<script type='text/javascript' src='/TFGWProject/dwr/interface/SearchDB.js'></script>
  	<script type='text/javascript' src='/TFGWProject/dwr/interface/Tree.js'></script>
  	
  	<script type='text/javascript' src='/TFGWProject/public/js/searchTree.js'></script>
  	
	<script language="javascript">
		//导入所需的javascrilpt代码
		dojo.require("dojo.NodeList-fx");
		
		dojo.require("dijit.Toolbar");	
		dojo.require("dijit.form.Button");
		dojo.require("dijit.form.TextBox");
		dojo.require("dijit.form.NumberTextBox");
		dojo.require("dijit.Tree");
			
		//Containers
		dojo.require("dijit.Tooltip");
		dojo.require("dijit.Dialog");
		dojo.require("dijit.layout.ContentPane");
		dojo.require("dijit.layout.TabContainer");
		dojo.require("dijit.layout.BorderContainer");
		dojo.require("dijit.layout.AccordionContainer");
			
	</script>
	
	<script language="javascript">
		var curSrcName = 'disease';
		//dojo.addOnLoad("console.info('start');");
		
		function dojoInit(){

		//	var anim1 = dojo.fadeOut({node: "titleH",duration:1000});
    	//	var anim2 = dojo.fx.slideTo({ node: "titleH", top:10, left:750,duration:1000 });
			
		//	var display = dojo.fx.combine([anim1,anim2]);	
		//	display.play();
		//	alert(dojo.query("input[type='text']"));
		//	dojo.query("input[type='text']").addClass("inputText");
	
	//	var node = dojo.byId('leftDiv');
	//	alert(node);
	//	dojo.connect("dojo.byId('leftDiv')","selectChild","alert('select!')");
		}
		
		function changeSrc(srcName){
			if( srcName == curSrcName){
				return;
			}
			
		//	document.getElementById('infFrame').src= "/TFGWProject/public/jsp/introduction.jsp?srcType=" + srcName;	
			curSrcName = srcName;
		}
		
		function test(){
			var params = new Array(4);
			params[0] = "感冒";
			params[1] = "";
			params[2] = "发热";
			params[3] = "";
			
			alert("go!");
			SearchDB.searchDiseaseTabInf(params,20,0, onReturn);
		}
		
		function onReturn(xmlResult){
			console.info(xmlResult);
		}

	</script>
	
	<style type="text/css">
		.inputText{
			width:150px;
		}
	</style>
  </head>
  <body class="soria">		
  	<div id="mainDiv" dojoType="dijit.layout.BorderContainer">
  		<div id="titleDiv" dojoType="dijit.Toolbar" region="top">
  			<span align="left" style="font-size:32px;padding:6px 0px 0 20px;margin:0px;font-weight:bold;">突发公卫事件中医药信息资源共享平台</span>
  			<span style="font-weight:bold;font-size:18px;margin:14px 20px 13px 0;">北京市中医药科技项目 任务号:JJ-2006-9</span>
  		</div>
  		<div dojoType="dijit.layout.BorderContainer" region="center" >
  			<div id="leftDiv" dojoType="dijit.layout.TabContainer" 
				minSize="20" region="left" splitter="true">
  				<div class="infPane" dojoType="dijit.layout.ContentPane" title="疾病" selected="true" onfocus="changeSrc('disease');" style="overflow:scroll;padding: 3px 5px 10px 5px;">
					<div>
						<img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/RootPlus.gif" onclick=openTree(this,"JMZ_JB","1")></img><img name="iconImage" align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif"></img>
						<span class="treeNodes"> 突发公卫数据库</span>
					</div><!--		
					<div>
						<img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/RootPlus.gif" OnClick=openTree(this,"C_JIB","1","")></img><img name="iconImage" align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif"></img>
						<span class="treeNodes">中医基础疾病数据库</span>
					</div>	
					--><div>
						<img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/RootPlus.gif" OnClick=openTree(this,"TFGW_ZDFZ","1")></img><img name="iconImage" align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif"></img>
						<span class="treeNodes">常见中毒防治数据库</span>
					</div>	
					<div>
						<img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/RootPlus.gif" OnClick=openTree(this,"TFGW_ZYBFZ","1")></img><img name="iconImage" align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif"></img>
						<span class="treeNodes">常见职业病防治数据库</span>
					</div>		
					<div>
						<img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/RootPlus.gif" OnClick=openTree(this,"TFGW_YJYA","1")></img><img name="iconImage" align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif"></img>
						<span class="treeNodes">突发公卫应急预案数据库</span>
					</div>
					
  				</div>
  				
  				<div class="infPane" dojoType="dijit.layout.ContentPane" title="专家" onfocus="changeSrc('expert');"  style="overflow:scroll;padding: 3px 5px 10px 5px;">
					<div id = "expertTab">	
						<img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/Tminus.gif" ></img><img name="iconImage" align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif"></img>
						<span class="treeNodes">北京市名医专家数据库
							<%for(int i = 0; i < expertNodes.getLength(); i++){
							  Element ele = (Element)expertNodes.item(i);
							%>
								<div>			
									<img align="absmiddle" src="/TFGWProject/public/images/empty.gif"></img><img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/Tplus.gif" OnClick=openTree(this,"<%=expertName%>","<%=ele.getElementsByTagName("level").item(0).getTextContent() %>","<%=ele.getElementsByTagName("value").item(0).getTextContent() %>")></img><img align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif">
									<span class="treeNodes"><%=ele.getElementsByTagName("name").item(0).getTextContent()%></span>
								</div>
							<%} %>
						</span>
					</div>
  				</div>
  				
  				<div class="infPane" dojoType="dijit.layout.ContentPane" title="医院" onfocus="changeSrc('hospital');"  style="overflow:scroll;padding: 3px 5px 10px 5px;">
					<div id = "hospitalTab">	
						<img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/Tminus.gif" ></img><img name="iconImage" align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif"></img>
						<span class="treeNodes">医院数据库
							<%for(int i = 0; i < hospitalNodes.getLength(); i++){
							  Element ele = (Element)hospitalNodes.item(i);
							%>
								<div>			
									<img align="absmiddle" src="/TFGWProject/public/images/empty.gif"></img><img class="nodeOperator" align="absmiddle" src="/TFGWProject/public/images/Tplus.gif" OnClick=openTree(this,"<%=hospitalName%>","<%=ele.getElementsByTagName("level").item(0).getTextContent() %>","<%=ele.getElementsByTagName("value").item(0).getTextContent() %>")></img><img align="absmiddle" src="/TFGWProject/public/images/spriteDivClose.gif">
									<span class="treeNodes"><%=ele.getElementsByTagName("name").item(0).getTextContent()%></span>
								</div>
							<%} %>
						</span>
					</div>
  				</div>				
  			</div>
  			<div id="rightDiv" dojoType="dijit.layout.BorderContainer" region="center">
	  			<div dojoType="dijit.layout.ContentPane" region="top" style="width:100%;height:35px;">
	  				<div dojoType="dijit.form.DropDownButton" showLabel=true>
						<span><strong>高级搜索</strong></span>
						<div id="searchBtn" dojoType="dijit.TooltipDialog" id="tooltipDlg" title="高级搜索">
							<%@ include file="public/jsp/search.jsp" %>
						</div>
					</div> 
					<div dojoType="dijit.form.Button" onclick="window.open('http://cowork.cintcm.com/engine/login_do.jsp?u=guest&p=guest321&cnid=10140');">
						<span><strong>疾病诊疗数据</strong></span>
					</div>
				</div>
	  			
	  			<div dojoType="dijit.layout.ContentPane" region="center">
	  				<iframe id="infFrame" name="infWin" frameborder="0" style="width:100%;height:100%;padding:0;margin:0;" 
	  				><!--	  			
	  				<iframe id="infFrame" name="infWin" frameborder="0" style="width:100%;height:100%;padding:0;margin:0;" 
	  				src="/TFGWProject/public/jsp/introduction.jsp?srcType=disease">
	  				
	  				--></iframe>
	  			</div>
  			</div>
		</div>
		<div dojoType="dijit.Toolbar" region="bottom" style="text-align:center;height:50px;">	  			
	  		<span style="font-weight:bold;font-size:18px;margin:14px 20px 13px 0;">Powered By CCNT@Zhejiang University, All Right Reserved</span>
	  	</div>
  	</div>
  </body>
</html>
