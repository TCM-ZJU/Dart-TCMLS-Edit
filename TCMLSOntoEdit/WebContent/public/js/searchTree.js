//类树控制
/**
* param[0]:本地imageID
* param[1]:查询的数据库表名
* param[2]:查询lev
* param[3].......param[n] 类树1到n级的值
*/
function openTree(){
	
	var nodes = new Array();
	var node;	//插入的目标ID
	var img = arguments[0];
	var DBName = arguments[1];
	var level = arguments[2];
	
	//设置类树
	nodes[0] = DBName;
	for(var i = 1; i < level; i++){
		nodes[i] = arguments[i + 2];	
	}
		
	//得到要插入的结点的地址
	var pNode = img.parentNode;
	for(var i = 0; i < pNode.childNodes.length; i++){
		if(pNode.childNodes[i].className == "treeNodes"){
			node = pNode.childNodes[i];
			break;
		}
	}

	//如果有子节点，说明该节点已经从数据库中读入了数据,就不需要再连接数据库
	if(node.childNodes.length > 1){
		if(node.childNodes[1].style.display == "none"){
			for(var i = 1; i < node.childNodes.length; i++){
				node.childNodes[i].style.display = "block";
			}
			img.src="/TFGWProject/public/images/Tminus.gif";
		}
		else{
			for(var i = 1; i < node.childNodes.length; i++){
				node.childNodes[i].style.display = "none";
			}
			img.src="/TFGWProject/public/images/Tplus.gif";
		}
	}
	else{
		
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
	var name = node.firstChild.nodeValue;

	str = name + str;
	alert(str);
	node.innerHTML = str;
	img.src="/TFGWProject/public/images/Tminus.gif";
}

//对树的根节点进行搜索
function treeSearch(dbName, infName){
	var url = "/TFGWProject/public/jsp/searchProcess.jsp";
	alert(url);
	if(dbName == "BJWSZY_RENYUAN"){
		url += "?type=expert" +
			   "&expertName=" + infName +
			   "&section=" + 
			   "&disease=" +
			   "&workTimeCheck=" + "false" +
			   "&workTime=" +
			   "&number=0" + 
			   "&count=15"; 
	}
	else if(dbName == "BJWSZY_YIYUAN"){
		url += "?type=hospital" +
			   "&hospitalName=" + infName +
			   "&hospitalLevel=" + 
			   "&feature=" +
			   "&bedspred1=" +
			   "&bedspred2" +
			   "&number=0" +
			   "&count=15"; 
	}
	else if(dbName == "JMZ_JB"){
		url = "/TFGWProject/Disease?disease=" + infName;
	}
	else{
		url += "?type=disease" +
			   "&diseaseName=" + infName +
			   "&pathogeny=" + 
			   "&symptom=" +
			   "&semiotic=" +
			   "&number=0" +
			   "&count=15"; 
	}

	document.getElementById("infFrame").src = encodeURI(url);
}