//类树控制
/**
* param[0]:本地imageID
* param[1]:查询的数据库表名
* param[2]:查询lev
* param[3].......param[n] 类树1到n级的值
*/
function openTree(){
	if(isSearch){
		//alert("类树展开中，请稍侯。。。。。。");
		return;
	}
	var nodes = new Array();
	var node;	//插入的目标ID
	var img = arguments[0];
	var DBName = arguments[1];
	var level = arguments[2];


	//如果在急门诊辅助浏览模式，则重置浏览栏
	if(isJMZ && (DBName != "JMZ_JB")){
		infDoc.getElementById("mainTableDiv").style.display = "";
		infDoc.getElementById("jmzDiv").style.display = "none";
		
		isJMZ = false;
	}
	
	//得到要插入的结点的地址
	var pNode = img.parentNode;
	for(var i = 0; i < pNode.childNodes.length; i++){
		if(pNode.childNodes[i].name == "insertPoint"){
			node = pNode.childNodes[i];
		}
	}
			
	//设置类树
	nodes[0] = DBName;
	for(var i = 1; i < level; i++){
		nodes[i] = arguments[i + 2];	
	}
	
	//如果有子节点，说明该节点已经从数据库中读入了数据,就不需要再连接数据库
	if(node.childNodes.length > 1){
		if(node.childNodes[1].style.display == "none"){
			for(var i = 1; i < node.childNodes.length; i++){
				node.childNodes[i].style.display = "block";
			}
			img.src="../images/Tminus.gif";
		}
		else{
			for(var i = 1; i < node.childNodes.length; i++){
				node.childNodes[i].style.display = "none";
			}
			img.src="../images/Tplus.gif";
		}
	}
	else{
		isSearch = true;
		
		infFrame.showPic(true);
		if(DBName == "C_JIB" || DBName == "JMZ_JB"){
			Tree.openJibNode(level, DBName, arguments[3],{
				callback:function(str){
					onNodeOpen(str, node, img);
				}
			});
		}
		else{
			Tree.openNode(level, nodes,{
				callback:function(str){
					onNodeOpen(str, node, img);
				}
			});
		}
	}
}

//类树展开后的绘制操作
function onNodeOpen(str, node, img){
	infFrame.showPic(false);
	var name = node.firstChild.nodeValue;
	alert(name);
	str = name + str;
	node.innerHTML = str;
	img.src="../images/Tminus.gif";
	isSearch = false;
}

//对树的根节点进行搜索
function treeSearch(DBName, disease){
	if(isSearch){
		return;
	}
	isSearch = true;
	infDoc.getElementById("lableBtns").style.display = "none";
	
	infFrame.searchValue[0] = disease;
	infFrame.searchValue[1] = "";
	infFrame.searchValue[2] = "";
	infFrame.searchValue[3] = "";
	infFrame.searchDB = DBName;
	
	//定位分页标示符
	JIBNum = 0;
	JMZNum = 0;
	ZDFZNum = 0;
	ZYBFZNum = 0;
	ZCFGNum = 0;
	YJYANum = 0;
	ZLBZNum = 0;
		
	//设置三种数据资源之间的转换按钮
	if(DBName == "BJWSZY_RENYUAN"){
		infFrame.setInf(1);	
	}
	else if(DBName == "BJWSZY_YIYUAN"){
		infFrame.setInf(2);
	}
	else{
		infFrame.setInf(0);
	}
	
	//如果在急门诊辅助浏览模式，则重置浏览栏
	if(isJMZ && (DBName != "JMZ_JB")){
		infDoc.getElementById("mainTableDiv").style.display = "";
		infDoc.getElementById("jmzDiv").style.display = "none";
		
		isJMZ = false;
	}
	
	//设置关联显示按钮（显示结构库中的其他附表）
	if(DBName == "JMZ_JB"){
		searchValue[0] = disease;
		searchValue[1] = "";
		searchValue[2] = "";
		searchValue[3] = "";
		infDoc.getElementById("jmzFrame").src=("../../Disease?disease=" + disease + "k");
		infDoc.getElementById("jmzDiv").style.display="";
		infDoc.getElementById("mainTableDiv").style.display="none";
		isJMZ = true;
		isSearch = false;
		return;
	}
	else if(DBName == "C_JIB"){
		infFrame.setDBBtn(1);
	}
	else{
		infFrame.setDBBtn(-1);
	}
	
	//显示搜索状态条
	infFrame.showPic(true);
	infFrame.searchDB = DBName;
	
	QueryData.treeSearch(DBName, disease, {
								callback:function(str){
									drawTableByType(DBName,str);
								}
							});	
}
		