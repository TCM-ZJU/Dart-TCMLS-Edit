<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>
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
	<script type="text/javascript" src="/TFGWProject/public/js/dojo-release-1.1.0/dojo/dojo.js" djConfig="parseOnLoad:true, isDebug:true"></script>
	<script type='text/javascript' src='/TFGWProject/dwr/engine.js'></script>
  	<script type='text/javascript' src='/TFGWProject/dwr/util.js'></script>
  	<script type='text/javascript' src='/TFGWProject/dwr/interface/SearchDB.js'></script>
  	
	<script language="javascript">
		//导入所需的javascrilpt代码
		dojo.require("dojo.NodeList-fx");
		dojo.require("dojo.NodeList-fx");
		
		dojo.require("dijit.Toolbar");
		dojo.require("dijit.form.Button");
		dojo.require("dijit.form.TextBox");
		dojo.require("dijit.form.ComboBox");
		dojo.require("dijit.form.NumberTextBox");
		
		dojo.require("dijit.layout.BorderContainer");
		dojo.require("dijit.layout.AccordionContainer");
		dojo.require("dijit.layout.TabContainer");
	</script>
	
	<script language="javascript">
		dojo.addOnLoad(init);
		
		function init(){
		}
		
		//对表单信息进行检查，当信息不满足要求时，就拒绝提交
		function checkForm(type){
			var alertInf;
			var flag = true;
	
			//检查用户输入，输出对应错误信息
			if(type == "disease"){
				if( document.getElementById("disease.name").value == "" && 
				    document.getElementById("disease.pathogeny").value == "" &&
				    document.getElementById("disease.symptom").value == "" && 
				    document.getElementById("disease.semiotic").value == ""){
				    
					alertInf = "请输入至少一个查询信息！";
					flag = false;
				}			
			}
			else if(type == "expert"){
				if( document.getElementById("expert.name").value == "" && 
				    document.getElementById("expert.section").value == "" &&
				    document.getElementById("expert.disease").value == "" && 
				    document.getElementById("expert.workTimeCheck").checked == false){
				    
					alertInf = "请输入至少一个查询信息！";
					flag = false;
				}				
			}
			else if(type == "hospital"){
				var num1, num2;
				num1 = dijit.byId("hospital.bedspred1").value;
				num2 = dijit.byId("hospital.bedspred1").value;
			
				if( 
					document.getElementById("hospital.name").value == "" && 
				    document.getElementById("hospital.level").value == "" &&
				    document.getElementById("hospital.feature").value == "" && 
				    num1.length == 0 &&
				    num2.length == 0 ){
				    
					alertInf = "请输入至少一个查询信息！";
					flag = false;
				}
				
				if(num1.length > 0 || num2.length > 0){
					if(!isNumber(num1) || !isNumber(num2)){ 
						alertInf = "床位数应为数字 ";
						flag = false;
					}
					else if( num2<num1 || num1 < 0){
						alertInf = "床位数的值无效！";
						flag = false;
					}		
				}
				
			}

			if(flag == false){
				alert(alertInf);
			}
			else{
				window.parent.document.getElementById("infFrame").focus();
			}
			
			return flag;
		}
		
		//检查输入数值是否为数字
		function isNumber(str) { 
			var pattern = /^[0-9]/; 
			return pattern.test(str); 
		} 
	</script>
	
	<style type="text/css">

	</style>
	
  </head>
  <body class="soria" style="vertical-align:center;">
  	<div dojoType="dijit.layout.TabContainer" style="width:400px;height:300px"> 
		<div dojoType="dijit.layout.ContentPane" title="疾病" class="searchTab" style="padding:20px 20px 20px 20px;">
			<form method="post" target="infWin" action="/TFGWProject/public/jsp/searchProcess.jsp" onsubmit="return checkForm('disease');">
				<input type="text" id="disease.name" name="diseaseName" dojoType="dijit.form.TextBox" trim="true">疾病<br><br>
				<input type="text" id="disease.pathogeny" name="pathogeny" dojoType="dijit.form.TextBox" trim="true">病因<br><br>
				<input type="text" id="disease.symptom" name="symptom" dojoType="dijit.form.TextBox" trim="true">症状<br><br>
				<input type="text" id="disease.semiotic" name="semiotic" dojoType="dijit.form.TextBox" trim="true">症候<br><br>
				
				<input type="submit" value=" 搜 索 " />
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<input type="reset" value=" 重 置 "/>
				<input type="hidden" name="number" value="0"/>
				<input type="hidden" name="count" value="15"/>
				<input type="hidden" name="type" value="disease"/>
			</form>
		</div>
		
		<div dojoType="dijit.layout.ContentPane" title="专家" style="padding:20px 20px 20px 20px;">
			<form method="post" target="infWin" action="/TFGWProject/public/jsp/searchProcess.jsp" onsubmit="return checkForm('expert');">
				<input type="text" id="expert.name" name="expertName" dojoType="dijit.form.TextBox" trim="true">专家姓名<br><br>
				<input type="text" id="expert.section" name="section" dojoType="dijit.form.TextBox" trim="true">所属科室<br><br>
				<input type="text" id="expert.disease" name="disease" dojoType="dijit.form.TextBox" trim="true">主治疾病<br><br>	
				门诊时间	
				<select name="workTime" id="expertWorkTime" dojoType="dijit.form.ComboBox" style="width:170px">
					<option value="周一上午" selected="selected">周一上午</option>
					<option value="周一下午">周一下午</option>
					
					<option value="周二上午">周二上午</option>
					<option value="周二下午">周二下午</option>
					
					<option value="周三上午">周三上午</option>
					<option value="周三下午">周三下午</option>
					
					<option value="周四上午">周四上午</option>
					<option value="周四下午">周四下午</option>
					
					<option value="周五上午">周五上午</option>
					<option value="周五下午">周五下午</option>
					
					<option value="周六上午">周六上午</option>
					<option value="周六下午">周六下午</option>
					
					<option value="周日上午">周日上午</option>
					<option value="周日下午">周日下午</option>
				</select>		
				<input type="checkbox" id="expert.workTimeCheck" name="workTimeCheck" checked=true value="true"/>
				<br/><br/>
				
				<input type="submit" value=" 搜 索 " />
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<input type="reset" value=" 重 置 "/>
				
				<input type="hidden" name="number" value="0"/>
				<input type="hidden" name="count" value="15"/>
				
				<input type="hidden" name="type" value="expert"/>
			</form>
		</div>
		<div dojoType="dijit.layout.ContentPane" title="医院" style="padding:20px 20px 20px 20px;">
			<form method="post" target="infWin" action="/TFGWProject/public/jsp/searchProcess.jsp" onsubmit="return checkForm('hospital');">
				<input 
					name="hospitalName" 
					id="hospital.name"
					type="text"
					dojoType="dijit.form.TextBox"/>
				医院名称<br><br>
				
				<input id="hospital.level" name="hospitalLevel" type="text" dojoType="dijit.form.TextBox"/>
				医院等级<br><br>
				
				<input id="hospital.feature" name="feature" type="text" dojoType="dijit.form.TextBox"/>
				特色专科<br><br>

				床位数:
				<input id="hospital.bedspred1" name="bedspred1" type="text"  
				 	style="width:5em" 
					dojoType="dijit.form.NumberTextBox"
					invalidMessage="床位数应为数字！"
					constraints="{min:0,max:10000000,places:0}"/>
				至
				<input id="hospital.bedspred2" name="bedspred2" type="text"  
					dojoType="dijit.form.NumberTextBox"
					style="width:5em"
					invalidMessage="床位数应为数字！"
					constraints="{min:0,max:1000000,places:0}"/>			
				<br><br>	
				
				<input type="submit" value=" 搜 索 " />
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<input type="reset" value=" 重 置 "/>
				
				<input type="hidden" name="number" value="0"/>
				<input type="hidden" name="count" value="15"/>
				
				<input type="hidden" name="type" value="hospital"/>
			</form>
		</div>
	</div>  		
  </body>
</html>
