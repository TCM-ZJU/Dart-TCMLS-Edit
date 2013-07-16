<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<script type='text/javascript' src='/TFGWProject/dwr/interface/QueryData.js'></script>
  		<script type='text/javascript' src='/TFGWProject/dwr/interface/Tree.js'></script>
  		<script type='text/javascript' src='/TFGWProject/dwr/engine.js'></script>
  		<script type='text/javascript' src='/TFGWProject/dwr/util.js'></script>
  		<script type='text/javascript' src='/TFGWProject/public/js/searchTable.js'></script>
  		<script type='text/javascript' src='/TFGWProject/public/js/infCtrl.js'></script>
  		<link rel="stylesheet" href="../css/inf.css">
  		
  		<style type="text/css">
		  	#showPic {
				position:absolute;
				z-index:10;
				left:360px;
				top:200px;
				display:none;
			}
  		</style>
  		
		<script>
			var type = "";			 		 //当前显示的内容{疾病，专家，医院}
			var searchByType = "";
			var searchValue = new Array();   //当前搜索的关键字组
			var searchDB = "";				 //当前搜索的数据库
			var isSearch = false;
			var searchFrame = top.frames["searchFrame"];
			var infDoc=top.frames["infFrame"].document;
			var searchDoc=top.frames["searchFrame"].document;
			var btnColor="#88F1CF";
			var bkColor="#F3FFF8";
			
			var closeTimer = new Date(); 	 //clob关闭定时器
			
			//记录分页信息
			var JIBNum = 0;
			var JMZNum = 0;
			var ZDFZNum = 0;
			var ZYBFZNum = 0;
			var ZCFGNum = 0;
			var YJYANum = 0;
			var ZLBZNum = 0;
			var ExpertNum = 0;
			var HospitalNum = 0;
			
			//显示搜索提示条
			function showPic(isShow){
				var picEle = document.getElementById("showPic");
				
				//改变提示条的位置，使其总是在屏幕正中
				picEle.style.top = (document.body.clientHeight - 16)/2;
				picEle.style.left = (document.body.clientWidth - 246)/2;
				
				if(isShow){
					picEle.style.display = "block";
				}
				else{
					picEle.style.display = "none";
				}
			}
			
			function searchClob(DBName,IDName,attribute,id){
				if(isSearch){
					alert("数据查询中，请稍后。。。。。。");
					return;
				}
				
				isSearch = true;
				showPic(true);
				QueryData.searchClob(DBName,IDName,attribute,id,showClob);
			}
			
			function showClob(rs){
				showPic(false);
				isSearch = false;
				
				var showWin;	
				if(rs == ""){
					rs = "<h1 align=center>该字段无信息</h1>";
					showWin = window.open("","","top=200,left=200,width=300,height=200,scrollbars=yes");
				}
				else{
					showWin = window.open("","","top=200, left=200, width=500,height=360,,scrollbars=yes");
				}
				
				if(showWin != null){
					var str = "<script>setTimeout(\"window.close()\",800);<";
					
					if(rs == "<h1 align=center>该字段无信息</h1>"){
						window.document.focus();
						showWin.document.write(str + "/script>" + rs);
					}
					else{
						
						showWin.document.write("<pre style='width:100%; white-space:normal; word-wrap:break-word;'>" + rs +"</pre>");
						showWin.focus();
					}
				}
				else{
					alert("显示窗口无法打开，请检查关闭浏览器弹出窗口过滤功能。");
				}	
			}
			
			function searchDB(DBName,searchType){
				if(searchValue[0]== 0){
					return;
				}
			}
			
			//通用的关联显示查询接口
			function getInf(btnType){
				if(type == btnType){
					return;
				}
				
				var args = new Array();
		
				if(btnType=="jib.expert"){
					if(searchValue[0] == ""){
						searchValue[0] = prompt("请输入要查询的疾病名称","感冒");
					}
					args[0]="";
					args[1]="";
					args[2]=searchValue[0];
					args[3]="";
					searchByType = "disease.expert";
					showPic(true);
					QueryData.searchExpert(args, {
								callback:function(str){
									searchFrame.drawTableByType("Expert",str);
								}
							});
				}
				else if(btnType=="jib.jib"){		
					searchByType = "disease.disease";
					showPic(true);
					QueryData.treeSearch(searchDB, searchValue[0],{
							callback:function(str){
								searchFrame.drawTableByType(searchDB, str);
							}
						});
				}
				else if(btnType=="jib.hospital"){	
					searchByType = "disease.hospital";	
					showPic(true);
					QueryData.searchHospitalByJib(searchValue[0],{
							callback:function(str){
								searchFrame.drawTableByType("Hospital", str);
							}
						});
				}
				else if(btnType=="jib.law"){
					showPic(true);
					QueryData.treeSearch("TFGW_ZCFG", searchValue[0],{
							callback:function(str){
								searchFrame.drawTableByType("TFGW_ZCFG", str);
							}
						});
				}
				else if(btnType=="jib.standard"){
					showPic(true);
					QueryData.treeSearch("ZYBZZLBZ", searchValue[0],{
							callback:function(str){
								searchFrame.drawTableByType("ZYBZZLBZ", str);
							}
						});
				}
				else if(btnType=="expert.disease"){
					if(searchValue[0] == ""){
						searchValue[0] = prompt("请输入要查询的专家名称","");
					}
					
					//选择状态栏
					openView("JIB");
					document.getElementById("lableBtns").style.display = "";
					
					//选择显示对象		
					document.getElementById("showTableExpert").style.display = "none";
					document.getElementById("showTableHospital").style.display = "none";
						
					//定位分页标示符
					JIBNum = 0;
					JMZNum = 0;
					ZDFZNum = 0;
					ZYBFZNum = 0;
					ZCFGNum = 0;
					YJYANum = 0;
					ZLBZNum = 0;
					
					showPic(true);
					QueryData.searchJibByExpert(searchValue[0], searchFrame.drawTable);
				}
				else if(btnType=="expert.hospital"){
					if(searchValue[0] == ""){
						searchValue[0] = prompt("请输入要查询的专家名称","");
					}
					
					document.getElementById("lableBtns").style.display = "none";
					showPic(true);
					QueryData.searchHospitalByExpert(searchValue[0], {
								callback:function(str){
									searchFrame.drawTableByType("Hospital",str);
								}
							});
				}
				else if(btnType=="expert.expert"){
					document.getElementById("lableBtns").style.display = "none";
					showPic(true);
					QueryData.treeSearch(searchDB,searchValue[0],{
							callback:function(str){
								searchFrame.drawTableByType("Expert",str);
							}
						});

				}
				else if(btnType=="hospital.expert"){
					if(searchValue[0] == ""){
						searchValue[0] = prompt("请输入要查询的医院名称","");
					}
					
					document.getElementById("lableBtns").style.display = "none";
					showPic(true);
					QueryData.searchExpertByHospital(searchValue[0], {
								callback:function(str){
									searchFrame.drawTableByType("Expert",str);
								}
							});
				}
				else if(btnType=="hospital.disease"){
					if(searchValue[0] == ""){
						searchValue[0] = prompt("请输入要查询的医院名称","");
					}
					//选择状态栏
					openView("JIB");
					document.getElementById("lableBtns").style.display = "";
					
					//选择显示对象		
					document.getElementById("showTableExpert").style.display = "none";
					document.getElementById("showTableHospital").style.display = "none";	
					
					//定位分页标示符
					JIBNum = 0;
					JMZNum = 0;
					ZDFZNum = 0;
					ZYBFZNum = 0;
					ZCFGNum = 0;
					YJYANum = 0;
					ZLBZNum = 0;
					
					showPic(true);
					QueryData.searchJibByHospital(searchValue[0], searchFrame.drawTable);					
				}
				else if(btnType=="hospital.hospital"){
				document.getElementById("lableBtns").style.display = "none";
				showPic(true);
				QueryData.treeSearch(searchDB,searchValue[0],{
						callback:function(str){
							searchFrame.drawTableByType("Hospital",str);
						}
					});
				}	
			}
			
			function getJibInf(jibType){
				if(searchValue[0] == ""){
					return;
				}
				
				if(jibType == "zhenH"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
				else if(jibType == "zyby"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
				else if(jibType == "xyby"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
				else if(jibType == "zyzl"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
				else if(jibType == "xyzl"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
				else if(jibType == "amzl"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
				else if(jibType == "zgzl"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
				else if(jibType == "zjzl"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
				else if(jibType == "qtzl"){
					showPic(true);
					QueryData.searchJibInf("jib."+jibType,searchValue[0],{
								callback:function(str){
									searchFrame.drawTableByType("JIB",str);
								}
							});
				}
			
			}
					
			//设置查询相关信息
			function setInf(inf){
				var btn = new Array();
				btn[0] = document.getElementById("diseaseBtns");
				btn[1] = document.getElementById("expertBtns");				 
				btn[2] = document.getElementById("hospitalBtns");
				
				if(inf=="disease" || inf==0){
					type = "disease";
					searchByType = "disease.disease";
	  				btn[0].style.display="";
	  				btn[1].style.display="none";
	  				btn[2].style.display="none";		
	  			}
	  			else if(inf=="expert" || inf==1){
	  				type = "expert";
	  				searchByType = "expert.expert";
	  				btn[0].style.display="none";
	  				btn[1].style.display="";
	  				btn[2].style.display="none";
	  			}
	  			else if(inf=="hospital" || inf==2){
	  				type = "hospital";
	  				searchByType = "hospital.hospital";
	  				btn[0].style.display="none";
	  				btn[1].style.display="none";
	  				btn[2].style.display="";
	  			}	 	  			 			
	  			else if(inf == -1){
	  				btn[0].style.display="none";
	  				btn[1].style.display="none";
	  				btn[2].style.display="none";	  				
	  			}
  			}	
			
			function setDBBtn(DBName){
				var btn = new Array();
				btn[0] = document.getElementById("JMZBtns");
				btn[1] = document.getElementById("C_JIBBtns");
				
				if(DBName=="JMZ_JB"||DBName==0){
					type = "disease";
					btn[0].style.display="";
					btn[1].style.display="none";
				}
				else if(DBName=="C_JIB"||DBName==1){
					type = "disease";
					btn[0].style.display="none";
					btn[1].style.display="";
				}
				else if(DBName == -1){
					btn[0].style.display="none";
					btn[1].style.display="none";
				}		
			}
		</script>
  		
	</head>
	<body>
		<div id="mainTableDiv">
			<table id="mainTable" valign="top">
				<tr id="ctrlTab">
					<td>
						<span id="diseaseBtns" style="display:none">
							<input type="button" value="疾病信息" OnClick=getInf("jib.jib")>
							<input type="button" value="主治专家" OnClick=getInf("jib.expert")>
							<input type="button" value="相关医院" OnClick=getInf("jib.hospital")>
							<input type="button" value="政策法规" OnClick=getInf("jib.law")>
							<input type="button" value="诊疗标准" OnClick=getInf("jib.standard")>
						</span>
						<span id="expertBtns" style="display:none">
							<input type="button" value="基本信息" OnClick=getInf("expert.expert")>
							<input type="button" value="相关疾病" OnClick=getInf("expert.disease")>
							<input type="button" value="相关医院" OnClick=getInf("expert.hospital")>
						</span>
						<span id="hospitalBtns" style="display:none">
							<input type="button" value="基本信息" OnClick=getInf("hospital.hospital")>
							<input type="button" value="相关专家" OnClick=getInf("hospital.expert")>
							<input type="button" value="相关疾病" OnClick=getInf("hospital.disease")>
						</span>
						<span id="JMZBtns" style="display:none">
						</span>
						<span id="C_JIBBtns" style="display:none">
							<input type="button" value="中医病因" OnClick=getJibInf("zyby")>
							<input type="button" value="西医病因" OnClick=getJibInf("xyby")>
							<input type="button" value="中药治疗" OnClick=getJibInf("zyzl")>
							<input type="button" value="按摩治疗" OnClick=getJibInf("amzl")>
							<input type="button" value="正骨治疗" OnClick=getJibInf("zgzl")>
							<input type="button" value="针灸治疗" OnClick=getJibInf("zjzl")>
							<input type="button" value="西药治疗" OnClick=getJibInf("xyzl")>
							<!--  input type="button" value="其他治疗" OnClick=getJibInf("qtzl")-->
						</span>
					</td>
				</tr>
				
				<tr id="lableBtns" valign=top style="display:none;">
					<td>
						<div class="PromoTabs" style="padding-left: 10px">
	                          <ul class="PromoTabs">
	                            <li  class="Current">
	                              <h3><a id="ctrlBtnJIB" onclick=openView("JIB") href="javascript:void(0);">中医疾病</a></h3>
	                            </li>
	                            <li>
	                              <h3><a id="ctrlBtnJMZ" onclick=openView("JMZ") href="javascript:void(0);">发热数据</a></h3>
	                            </li>
	                            <li>
	                              <h3><a id="ctrlBtnZDFZ" onclick=openView("ZDFZ") href="javascript:void(0);">中毒防治</a></h3>
	                            </li>
	                            <li>
	                              <h3><a id="ctrlBtnZYBFZ" onclick=openView("ZYBFZ") href="javascript:void(0);">职业病防治</a></h3>
	                            </li>
	                            <li>
	                              <h3><a id="ctrlBtnZCFG" onclick=openView("ZCFG") href="javascript:void(0);">政策法规</a></h3>
	                            </li>
	                            <li>
	                              <h3><a id="ctrlBtnYJYA" onclick=openView("YJYA") href="javascript:void(0);">应急预案</a></h3>
	                            </li>
	                            <li>
	                              <h3><a id="ctrlBtnZLBZ" onclick=openView("ZLBZ") href="javascript:void(0);">诊疗标准</a></h3>
	                            </li>
	                          </ul>
	                	</div>
					</td>
				</tr>
				
				<tr valign="top">
					<td id="showTableJIB">
						
					</td>
					<td id="showTableJMZ" style="display:none">
						
					</td>
					<td id="showTableZDFZ" style="display:none">
						
					</td>
					<td id="showTableZYBFZ" style="display:none">
						
					</td>
					<td id="showTableZCFG" style="display:none">
						
					</td>
					<td id="showTableYJYA" style="display:none">
						
					</td>
					<td id="showTableZLBZ" style="display:none">
						
					</td>
					<td id="showTableExpert" style="display:none">
						
					</td>
					<td id="showTableHospital" style="display:none">
						
					</td>
				</tr>
			</table>
		</div>
		
		<div id="jmzDiv" style="display:none">
			<iframe id="jmzFrame"  src="/TFGWProject/Disease?disease=呼吸道感染k">
			</iframe>
		</div>
		
		<div id="showPic" align="center">
			<strong>数据读取中，请稍候。。。。。。</strong>
			<br>
			<img align="center" src="../images/progress.gif">
		</div>
	</body>
</html>