/**************Get the infmation use the number*************************************/

/**************Get the data from server and draw them use AJAX.**********************************************/ 	
  	//Get data from the server use AJAX
  	function getData(dbName, searchCount, searchType, num, type){
  		var tmpNum = getNum(dbName, "normal");
  		var tmpMax = getNum(dbName, "max");
  		var searchNum = 0;
  		var type;
  				
  		if(searchType == "prev"){
  			searchNum = tmpNum - searchCount;	
  		}
  		else if(searchType == "next"){
  			searchNum = tmpNum + searchCount;
  		}
  		else if(searchType == "first"){
  			searchNum = 0;
  		}
  		else if(searchType == "last"){
  			searchNum = tmpMax - tmpMax%count;
  		}
  		else if(searchType == "num"){
  			searchNum = num;
  		}
  		
  		//If exceed the limit, then return.
  		if(searchNum >= tmpMax || searchNum < 0 || searchCount < 0){
  			return;
  		}
  		
  		SearchDB.searchDiseaseInf(params, dbName, type, searchCount, searchNum, {
								callback:function(str){
									drawTableByType(dbName, searchNum, str);
								}});
  	}
  	
  	//Search the detail value
  	function getDetailValue(dbName, attrName, attrValue, maxNum, num){
  		//得到鼠标坐标
  		var x = 250, y = 250;
  		if(window.event){
  			x = event.clientX;
  			y = event.clientY;
  			if(window.screenLeft || window.screenTop){
  				x += window.screenLeft;
  				y += window.screenTop;
  			}
  			
  			x -= 250;
  			y -= 180;
  		}

  		var showWin;	
  	    var url = "/TFGWProject/public/jsp/getDataInf.jsp?" + 
  	    		  "dbName=" + dbName + 
  	    		  "&attrName=" + attrName +
  	    		  "&num=" + 1 +
  	    		  "&maxNum=" + maxNum +
  	    		  "&attrValue=" + encodeURI(attrValue);
  	    
	   	if(dbName == "JMZ_JB"){
	   		url = "/TFGWProject/Disease?disease=" + encodeURI(attrValue);
	   		showWin = window.open(url, "", "top=" + y + ", left=" + x + ", width=800,height=600,scrollbars=yes,resizable=yes,fullscreen=no");
	   	}
	   	else{
	   		
	   	//	alert("(" + x + "," + y + ")");
			showWin = window.open(url, "", "top=" + y + ", left=" + x + ", width=500,height=360,,scrollbars=yes,resizable=yes,fullscreen=no");
		}
		
		if(showWin == null){
			alert("显示窗口无法打开，请检查关闭浏览器弹出窗口过滤功能。");
		}
		
  	}
  	
  	//Search the detail value
  	function getRelativeValue(type, searchAttr, num, count, tret){
  		var x = 250, y = 250;
  		if(window.event){
  			x = event.clientX;
  			y = event.clientY;
  			if(window.screenLeft || window.screenTop){
  				x += window.screenLeft;
  				y += window.screenTop;
  			}
  			
  			x -= 250;
  			y -= 180; 
  		}
  		
  		var showWin;	
  		var tmp;
  		
  		/*		
        for(var i = 0; i < arguments.length; i++){
  			tmp += "argument" + i + ":" + arguments[i] + "\n";
  		}
  		
  		alert(tmp);*/
  		
  	    var url = "/TFGWProject/public/jsp/getRelative.jsp?" + 
  	    		  "type=" + type + 
  	    		  "&searchAttr=" + encodeURI(searchAttr) +
  	    		  "&num=" + num +
  	    		  "&count=" + count +
  	    		  "&tret=" + encodeURI(tret);
  	    		  
		showWin = window.open(url, "", "top=" + y + ", left=" + x + ", width=500,height=360,,scrollbars=yes,fullscreen=false");
		
		if(showWin == null){
			alert("显示窗口无法打开，请检查关闭浏览器弹出窗口过滤功能。");
		}
  	}
  	
  	//Draw the html page use javascript
  	function drawTableByType(dbName, currentNum, str){
  		var tmpMax = getNum(dbName, "max");
  		var xmlDoc = parseXML(str);
  		var diseases;
  		
  		if(dbName == "expert"){
  			diseases = xmlDoc.getElementsByTagName("expert");
  		}
  		else if(dbName == "hospital"){
  			diseases = xmlDoc.getElementsByTagName("hospital");
  		}
  		else{
  			diseases = xmlDoc.getElementsByTagName("disease");
  		}
  		
  		var table = getDisplayTab(dbName);
  			
  		//Remove the old rows
  		var rows = table.rows;
		for(var i = (rows.length - 1); i >= 0 ; i--){
			if(rows[i].className != "infTitleTr"){
				table.deleteRow(i);
			}
		}
  		
  		//Draw the new rows
  		//!Because the insert order is reverse, so we reverse iterater the xml.The order is important!
  		for(var i = (diseases.length - 1); i >= 0 ; i--){
  			var row = table.insertRow(1);
  			row.className = "row" + (currentNum + i)%2;
  			var correlation;
  			
  			var cell0 = row.insertCell(0);
  			var cell1 = row.insertCell(1);
  			var cell2 = row.insertCell(2);
  			
	  			
  			if(dbName != "expert" && dbName != "hospital"){
  				var cell3 = row.insertCell(3);
  				var cell4 = row.insertCell(4);
  			
  				
  				var diseaseName = diseases[i].childNodes[0].firstChild.nodeValue;
  				correlation = diseases[i].childNodes[1].firstChild.nodeValue;
  				
  				var tmpDB = dbName;
  				if(dbName == "disease"){
  					tmpDB = 'check';
  				}
  				
				cell0.innerHTML = "<span onclick=getDetailValue('" + tmpDB + "'," +
												  "getSearchAttr('" + dbName + "')," +
												  "'" + diseaseName + "'," + 
												  correlation +
												  ",1)>" + 
								   	diseaseName + 
								   	"(" + correlation + ")" +
								   "</span>";
								   
				cell1.innerHTML = "<span class='relaSearch' onclick=getRelativeValue('disease.expert','" + 
								   diseaseName + "',0,20)>" + 
								  "查看</span>";
								   
				cell2.innerHTML = "<span class='relaSearch' onclick=getRelativeValue('disease.hospital','" +
							       diseaseName + "',0,20)>" +
		    					  "查看</span>";
		    	
		    	cell3.innerHTML = "<span onclick=getDetailValue('ZYBZZLBZ'," +
												  "getSearchAttr('ZYBZZLBZ')," +
												  "'" + diseaseName + "'," + 
												  0 +
												  ",1)>" + 
								   "查看</span>";
		    					  
		    	cell4.innerHTML = "<span onclick=getDetailValue('ZCFG'," +
												  "getSearchAttr('ZCFG')," +
												  "'" + diseaseName + "'," + 
												  0 +
												  ",1)>" + 
								   "查看</span>";
				
				cell0.className="clickInf";
				cell3.className="clickInf";
				cell4.className="clickInf";
			}else if(dbName == "expert"){
				var expertID = diseases[i].childNodes[0].firstChild.nodeValue;
				var expertName = diseases[i].childNodes[1].firstChild.nodeValue;
				correlation = diseases[i].childNodes[2].firstChild.nodeValue;
	
				cell0.innerHTML = "<span onclick=getDetailValue('" + dbName + "'," +
												  "getSearchAttr('" + dbName + "')," +
												  "'" + expertID + "'," + 
												  1 +
												  ",1)>" + 
								   	expertName + "(" + correlation+ ")" +			   	
								   "</span>";
				cell1.innerHTML = "<span class='relaSearch' onclick=getRelativeValue('expert.disease','" + 
								   expertID + "',0,20,'" + expertName + "')>" +
								   "查看</span>";
								   
				cell2.innerHTML = "<span class='relaSearch' onclick=getRelativeValue('expert.hospital','" +
							       expertID + "',0,20,'" + expertName + "')>" +
		    					  "查看</span>";
		    					  	
				cell0.className = "clickInf";		
			}
			else if(dbName == "hospital"){
				var hospitalID = diseases[i].childNodes[0].firstChild.nodeValue;
				var hospitalName = diseases[i].childNodes[1].firstChild.nodeValue;
				correlation = diseases[i].childNodes[2].firstChild.nodeValue;
				
				cell0.innerHTML = "<span onclick=getDetailValue('" + dbName + "'," +
												  "getSearchAttr('" + dbName + "')," +
												  "'" + hospitalID + "'," + 
												  1 +
												  ",1)>" + 
								   	hospitalName + "(" + correlation+ ")" +			   	
								   "</span>";
				cell1.innerHTML = "<span class='relaSearch' onclick=getRelativeValue('hospital.expert','" + 
								   hospitalID + "',0,20,'" + hospitalName +"')>" +
								   "查看</span>";
								   
				cell2.innerHTML = "<span class='relaSearch' onclick=getRelativeValue('hospital.disease','" +
							       hospitalID + "',0,20,'" + hospitalName + "')>" +
		    					  "查看</span>";
		    					  	
				cell0.className = "clickInf";		
			}
  		}
  		
  		//Set the selection current search number
  		setSelect(dbName, currentNum/count);
  		
  		//Set the database current search number
  		setNum(dbName, currentNum);
  	}
  	
  	//Parce the XML document
  	function parseXML(xmlData){
  		var xmlDoc;
  		
  		//Internet Explorer 7
  		try{
  			xmlDoc = new ActiveXObject("Msxml2.DOMDocument"); 
		    xmlDoc.async = false; 
		    xmlDoc.loadXML(xmlData); 
  		}
  		//Internet Explorer 6 and older
  		catch(e){
	  		try{
	  			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
	  			xmlDoc.async=false;
	  			xmlDoc.loadXML(text);
	  		}
	  		catch(e){
	  			//Firefox, Mozilla, Opera, etc.
	  			try{
	  				parser=new DOMParser();
	  				xmlDoc=parser.parseFromString(xmlData,"text/xml");
	  			}
	  			catch(e){
	  				alert("对不起，您使用的浏览器不支持现有的AJAX调用，请更换浏览器再试。\n 具体错误为：" + e.message);
	  				return null;
	  			}
	  		}
  		}
  		
		return xmlDoc;
  	}
  	
/****************The other functions***************************/
  	function getNum(dbName, numType){
  		if(dbName == "JMZ_JB"){
  			if(numType == "normal"){
  				return numJMZ_JB;	
  			}	
  			else if(numType == "max"){
  				return maxNumJMZ_JB;
  			}
  		}
  		else if(dbName == "C_JIB"){
  			if(numType == "normal"){
  				return numC_JIB;	
  			}	
  			else if(numType == "max"){
  				return maxNumC_JIB;
  			}
  		}
  		else if(dbName == "ZDFZ"){
  		  	if(numType == "normal"){
  				return numZDFZ;	
  			}	
  			else if(numType == "max"){
  				return maxNumZDFZ;
  			}
  		}
  		else if(dbName == "ZYBFZ"){
  		  	if(numType == "normal"){
  				return numZYBFZ;	
  			}	
  			else if(numType == "max"){
  				return maxNumZYBFZ;
  			}
  		}
  		else if(dbName == "YJYA"){
  		  	if(numType == "normal"){
  				return numYJYA;	
  			}	
  			else if(numType == "max"){
  				return maxNumYJYA;
  			}
  		}
  		else if(dbName == "disease"){
  		  	if(numType == "normal"){
  				return numDisease;	
  			}	
  			else if(numType == "max"){
  				return maxNumDisease;
  			}
  		}
  		else if(dbName == "check"){
  		  	if(numType == "normal"){
  				return numDisease;	
  			}	
  			else if(numType == "max"){
  				return maxNumDisease;
  			}
  		}
  		else if(dbName == "expert"){
  		  	if(numType == "normal"){
  				return numExpert;	
  			}	
  			else if(numType == "max"){
  				return maxNumExpert;
  			}
  		}
  		else if(dbName == "hospital"){
  		  	if(numType == "normal"){
  				return numHospital;	
  			}	
  			else if(numType == "max"){
  				return maxNumHospital;
  			}
  		}
  	}
  	
  	function setNum(dbName, num){
  		if(dbName == "JMZ_JB"){
  			numJMZ_JB = num;		
  		}
  		else if(dbName == "C_JIB"){
  			numC_JIB = num;
  		}
  		else if(dbName == "ZDFZ"){
  			numZDFZ = num;
  		}
  		else if(dbName == "ZYBFZ"){
  			numZYBFZ = num;
  		}
  		else if(dbName == "YJYA"){
  			numYJYA = num;
  		}
  		else if(dbName == "disease"){
  			numDisease = num;
  		}
  		else if(dbName == "check"){
  			numDisease = num;
  		}
  		else if(dbName == "expert"){
  			numExpert = num;
  		}
  		else if(dbName == "hospital"){
  			numHospital = num;
  		}
  	}
  	
  	//Get html table by type, which is used to draw the display window
	function getDisplayTab(dbName){
		if(dbName == "JMZ_JB"){
			return document.getElementById("JMZ_JBTab");
		}
		else if(dbName == "C_JIB"){
			return document.getElementById("C_JIBTab");
		}
		else if(dbName == "ZDFZ"){
		  	return document.getElementById("ZDFZTab");
		}
		else if(dbName == "ZYBFZ"){
		  	return document.getElementById("ZYBFZTab");
		}
		else if(dbName == "YJYA"){
		  	return document.getElementById("YJYATab");
		}
		else if(dbName == "disease"){
			return document.getElementById("diseaseTab");
		}
		else if(dbName == "check"){
			return document.getElementById("diseaseTab");
		}
		else if(dbName == "expert"){
		  	return document.getElementById("expertTab");
		}
		else if(dbName == "hospital"){
		  	return document.getElementById("hospitalTab");
		}
	}
  	
  	function setSelect(dbName, index){
  		var tmpSelect;
  		
  		if(dbName == "JMZ_JB"){
  			tmpSelect = document.getElementById("JMZ_JBSelect");		
  		}
  		else if(dbName == "C_JIB"){
  			tmpSelect = document.getElementById("C_JIBSelect");
  		}
  		else if(dbName == "ZDFZ"){
  			tmpSelect = document.getElementById("ZDFZSelect");
  		}
  		else if(dbName == "ZYBFZ"){
  			tmpSelect = document.getElementById("ZYBFZSelect");
  		}
  		else if(dbName == "YJYA"){
  			tmpSelect = document.getElementById("YJYASelect");
  		}
  		else if(dbName == "disease"){
  			tmpSelect = document.getElementById("diseaseSelect");
  		}
  		else if(dbName == "check"){
  			tmpSelect = document.getElementById("diseaseSelect");
  		}
  		else if(dbName == "expert"){
  			tmpSelect = document.getElementById("expertSelect");
  		}
  		else if(dbName == "hospital"){
  			tmpSelect = document.getElementById("hospitalSelect");
  		}  
  		
  		if(tmpSelect != null){
  			//If exceed limit
  			if(index < 0 || index >= tmpSelect.length){
  				throw "Select exceed limit!";
  			}
  			else{
  				tmpSelect.selectedIndex = index;
  				return true;
  			}
  		}		
  		
  		return false;
  	}
  	
  	function getSearchAttr(dbName){
  		if(dbName == "expert"){
  			return "ID";
  		}
  		else if(dbName == "hospital"){
  			return "ID";
  		}
  		else if(dbName == "JMZ_JB" || dbName == "C_JIB"){
  			return "JBMC";
  		}
  		else if(dbName == "ZYBZZLBZ"){
  			return "BINGZMC";
  		}
  		else if(dbName == "ZCFG"){
  			return "YIBMC";
  		}
  		else if(dbName == "check"){
  			return "JBMC";
  		}
  		else{
  			return "JIBMC";
  		}
  		
  	}
/****************Handle the error***************************/
  	dwr.engine.setErrorHandler(errorHandler);
  	
  	function errorHandler(errorString, exception){
  		document.body.innerHTML="<center><h1>Sorry 出错了</h1></center>" +
  							    "<p>The error is:" + errorString +  "</p>" +
  							    "<p>The exception is:" + exception +  "</p>";
  	}
