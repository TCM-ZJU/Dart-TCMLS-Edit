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
  	    		  "&attrValue=" + encodeURI(attrValue) +
  	    		  "&num=" + 1 +
  	    		  "&maxNum=" + maxNum;
  	    

		showWin = window.open(url, "", "top=" + y + ", left=" + x + ", width=500,height=360,,scrollbars=yes");
		
		if(showWin == null){
			alert("显示窗口无法打开，请检查关闭浏览器弹出窗口过滤功能。");
		}
		
  	}
  	
  	//Search the detail value
  	function reloadPage(type, searchAttr, num, count, tret){		
  		if(num < 0 || num >= maxNum)
  			return;
  			
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
  	    
  	   
  	    location.replace(url); 
  	}
  	
  	//Search the detail value
  	function getRelativeValue(type, searchAttr, num, count, tret){
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
  	    
		showWin = window.open(url, "", "top=" + y + ", left=" + x + ", width=500,height=360,,scrollbars=yes");
		
		if(showWin == null){
			alert("显示窗口无法打开，请检查关闭浏览器弹出窗口过滤功能。");
		}
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
  		else{
  			return "JIBMC";
  		}
  		
  	}