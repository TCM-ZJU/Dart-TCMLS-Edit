function openView(type){
	var bg = "url('../images/Tabbg.gif')"
	var checked = "url('../images/mid_tab_checked_bg.gif')";

	tabs = new Array(7);
	tabViews = new Array(7);
	var i, j;
	tabs[0] = document.getElementById("ctrlBtnJIB");
	tabs[1] = document.getElementById("ctrlBtnJMZ");
	tabs[2] = document.getElementById("ctrlBtnZDFZ");
	tabs[3] = document.getElementById("ctrlBtnZYBFZ");
	tabs[4] = document.getElementById("ctrlBtnZCFG");
	tabs[5] = document.getElementById("ctrlBtnYJYA");
	tabs[6] = document.getElementById("ctrlBtnZLBZ");
	
	tabViews[0] = document.getElementById("showTableJIB");
	tabViews[1] = document.getElementById("showTableJMZ");
	tabViews[2] = document.getElementById("showTableZDFZ");
	tabViews[3] = document.getElementById("showTableZYBFZ");
	tabViews[4] = document.getElementById("showTableZCFG");
	tabViews[5] = document.getElementById("showTableYJYA");
	tabViews[6] = document.getElementById("showTableZLBZ");

	if(type == "JIB"){
		j = 0;
		
		tabs[j].style.background = checked;
		for(i = 0; i < tabs.length; i++){
			if(i != j){
				tabs[i].style.background = bg;
			}
		}
		
		tabViews[j].style.display = "";
		for(i = 0; i < tabViews.length; i++){
			if(i != j){
				tabViews[i].style.display = "none";
			}
		}
	}	
	else if(type == "JMZ"){
		j = 1;
		
		tabs[j].style.background = checked;
		for(i = 0; i < tabs.length; i+=1){
			if(i != j){
				tabs[i].style.background = bg;
			}
		}
		
		tabViews[j].style.display = "";
		for(i = 0; i < tabViews.length; i+=1){
			if(i != j){
				tabViews[i].style.display = "none";
			}
		}
	}
	else if(type == "ZDFZ"){
		j = 2;
		
		tabs[j].style.background = checked;
		for(i = 0; i < tabs.length; i+=1){
			if(i != j){
				tabs[i].style.background = bg;
			}
		}
		
		tabViews[j].style.display = "";
		for(i = 0; i < tabViews.length; i++){
			if(i != j){
				tabViews[i].style.display = "none";
			}
		}
	}
	else if(type == "ZYBFZ"){
		j = 3;
		
		tabs[j].style.background = checked;
		for(i = 0; i < tabs.length; i++){
			if(i != j){
				tabs[i].style.background = bg;
			}
		}
		
		tabViews[j].style.display = "";
		for(i = 0; i < tabViews.length; i++){
			if(i != j){
				tabViews[i].style.display = "none";
			}
		}
	}
	else if(type == "ZCFG"){
		j = 4;
		
		tabs[j].style.background = checked;
		for(i = 0; i < tabs.length; i++){
			if(i != j){
				tabs[i].style.background = bg;
			}
		}
		
		tabViews[j].style.display = "";
		for(i = 0; i < tabViews.length; i++){
			if(i != j){
				tabViews[i].style.display = "none";
			}
		}
	}
	else if(type == "YJYA"){
		j = 5;
		
		tabs[j].style.background = checked;
		for(i = 0; i < tabs.length; i++){
			if(i != j){
				tabs[i].style.background = bg;
			}
		}
		
		tabViews[j].style.display = "";
		for(i = 0; i < tabViews.length; i++){
			if(i != j){
				tabViews[i].style.display = "none";
			}
		}
	}
	else if(type == "ZLBZ"){
		j = 6;
		
		tabs[j].style.background = checked;
		for(i = 0; i < tabs.length; i++){
			if(i != j){
				tabs[i].style.background = bg;
			}
		}
		
		tabViews[j].style.display = "";
		for(i = 0; i < tabViews.length; i++){
			if(i != j){
				tabViews[i].style.display = "none";
			}
		}
	}
}