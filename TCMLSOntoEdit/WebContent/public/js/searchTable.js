//读取搜索框的内容，调用相应的对象进行搜索操作。
function search(){
	if(searchFrame.isSearch){
		return;
	}
	
	//关闭关联按钮
	setDBBtn(-1);
	setInf(-1);
	
	var args = new Array(4);
	var text;
	var radio = searchDoc.getElementsByName("searchRadio");
	var i;
	var flag = false;
	 
	//读入搜索参数值，将结果储存在args数组中
	if(radio[0].checked){
		searchDB = "Disease";
		text = searchDoc.getElementsByName("textDisease");
		for(i = 0; i < text.length; i++){			
			args[i] = text[i].value;
			if(args[i] != ""){
				flag = true;
			}
		}		
		if(flag){
			searchFrame.isSearch = true;
			//记录搜索参数
			searchValue=args;
			
			//定位分页标示符
			JIBNum = 0;
			JMZNum = 0;
			ZDFZNum = 0;
			ZYBFZNum = 0;
			ZCFGNum = 0;
			YJYANum = 0;
			ZLBZNum = 0;
			
			//选择状态栏
			openView("JIB");
			document.getElementById("lableBtns").style.display = "";
			
			//选择显示对象		
			document.getElementById("showTableExpert").style.display = "none";
			document.getElementById("showTableHospital").style.display = "none";	
			
			//显示搜索状态条
			showPic(true);

			//搜索疾病数据库
			QueryData.searchJib(args, searchFrame.drawTable);
		}		
	}
	else if(radio[1].checked){
		searchDB = "BJWSZY_RENYUAN";
		text = searchDoc.getElementsByName("textExpert");
		for(i = 0; i < 3; i++){
			args[i] = text[i].value;
			if(args[i] != ""){
				flag = true;
			}
		}
		
		if(searchDoc.getElementById("expertDateCheckBox").checked){
			args[3] = text[3].value + text[4].value;
		}else{
			args[3] = "";
		}
		
		if(args[3] != ""){
			flag = true;
		}
		
		if(flag){
			searchFrame.isSearch = true;
			
			//记录搜索参数
			searchValue=args;
			
			//定位分页标示符
			ExpertNum = 0;
			
			//选择状态栏
			document.getElementById("lableBtns").style.display = "none";	
					
			//显示搜索状态条
			showPic(true);
			
			//查询数据
			QueryData.searchSingleJib(args, "Expert",  0, {
									callback:function(str){
										searchFrame.drawTableByType("Expert",str);
									}
								});
		}
	}
	else if(radio[2].checked){
		searchDB = "BJWSZY_YIYUAN";
		text = searchDoc.getElementsByName("textHospital");
		
		for(i = 0; i < text.length; i++){
			args[i] = text[i].value;
			if(args[i] != "" && i < 3){
				flag = true;
			}
		}	
		
		if(flag){
			if(!searchFrame.IsNumber(args[3]) || !searchFrame.IsNumber(args[4])){
				args[3] = args[4] = 0;
			}
			
			//搜索医院信息	
			searchFrame.isSearch = true;
			
			//记录搜索参数
			searchValue=args;
		
			//定位分页标示符
			HospitalNum = 0;
			
			//选择状态栏
			document.getElementById("lableBtns").style.display = "none";
			
			//显示搜索状态条
			showPic(true);
			QueryData.searchSingleJib(args, "Hospital",  0, {
							callback:function(str){
								searchFrame.drawTableByType("Hospital", str);
							}
						});
		}
	}
}

//返回首页
function searchByNum(type, maxLength, num){
	if(num > maxLength){
		return;
	}
	
	//读取搜索参数
	var args = searchValue;
	
	//把当前类型的显示页码号设为0
	setLocateNum(type, num);
	
	//显示搜索状态条
	showPic(true);
	
	//搜索对应的数据库
	if(searchDB == "BJWSZY_YIYUAN" && type != "Expert" && type != "Hospital"){
		QueryData.searchSingleJibByType("Hospital", type, args[0], num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});		
	}
	else if(searchDB == "BJWSZY_RENYUAN" && type != "Expert" && type != "Hospital"){
		QueryData.searchSingleJibByType("Expert", type, args[0], num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});			
	}
	else{
		QueryData.searchSingleJib(args, type, num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});
	}
}

//搜索下20条记录。
function searchNext(type, maxLength){
	var num = getLocateNum(type);
	
	if((num + 20) >= maxLength){
		return;
	}
	
	num += 20;
	
	//读取搜索参数
	var args = searchValue;
	setLocateNum(type, num);
	
	//显示搜索状态条
	showPic(true);

	//搜索对应的数据库	
	if(searchDB == "BJWSZY_YIYUAN" && type != "Expert" && type != "Hospital"){
		QueryData.searchSingleJibByType("Hospital", type, args[0], num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});		
	}
	else if(searchDB == "BJWSZY_RENYUAN" && type != "Expert" && type != "Hospital"){
		QueryData.searchSingleJibByType("Expert", type, args[0], num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});			
	}
	else{
		QueryData.searchSingleJib(args, type, num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});
	}
}

//搜索前20条记录
function searchBefore(type, maxLength){
	var num = getLocateNum(type);
	
	if((num - 20) < 0){
		return;
	}
	
	num -= 20;
	var args = searchValue;
	setLocateNum(type, num);
	
	//显示搜索状态条
	showPic(true);
	
	//搜索疾病数据库
	if(searchDB == "BJWSZY_YIYUAN" && type != "Expert" && type != "Hospital"){
		QueryData.searchSingleJibByType("Hospital", type, args[0], num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});		
	}
	else if(searchDB == "BJWSZY_RENYUAN" && type != "Expert" && type != "Hospital"){
		QueryData.searchSingleJibByType("Expert", type, args[0], num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});			
	}
	else{
		QueryData.searchSingleJib(args, type, num, {
								callback:function(str){
									searchFrame.drawTableByType(type, str);
								}});
	}
}

//通过数据库的种类找到当前显示位置
function getLocateNum(type){
	var num;
	
	if(type == "JIB"){
		num = JIBNum;
	}
	else if(type == "JMZ"){
		num = JMZNum;
	}
	else if(type == "ZDFZ"){
		num = ZDFZNum;
	}
	else if(type == "ZYBFZ"){
		num = ZYBFZNum;
	}
	else if(type == "ZCFG"){
		num = ZCFGNum;
	}
	else if(type == "YJYA"){
		num = YJYANum;
	}
	else if(type == "ZLBZ"){
		num = ZLBZNum;
	}
	else if(type == "Expert"){
		num = ExpertNum;
	}
	else if(type == "Hospital"){
		num = HospitalNum;
	}
	
	return num;
}

//通过数据库的种类设置当前显示位置
function setLocateNum(type, num){
	
	if(type == "JIB"){
		JIBNum = num;
	}
	else if(type == "JMZ"){
		JMZNum = num;
	}
	else if(type == "ZDFZ"){
		ZDFZNum = num;
	}
	else if(type == "ZYBFZ"){
		ZYBFZNum = num;
	}
	else if(type == "ZCFG"){
		ZCFGNum = num;
	}
	else if(type == "YJYA"){
		YJYANum = num;
	}
	else if(type == "ZLBZ"){
		ZLBZNum = num;
	}
	else if(type == "Expert"){
		ExpertNum = num;
	}
	else if(type == "Hospital"){
		HospitalNum = num;
	}
}