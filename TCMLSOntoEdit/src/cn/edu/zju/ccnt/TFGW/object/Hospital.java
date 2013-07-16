package cn.edu.zju.ccnt.TFGW.object;

import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;

public class Hospital implements DataReader{
	public String ID;		//ID
	public String SJZSDW;	//上级直属单位
	public String YIYMC;	//医院名称
	public String YIYBM;	//医院别名
	public String SUOSDQ;	//所属地区
	public String DANWXZ;	//单位性质
	public String YIYDZ;	//医院地址
	public String YOUZBM;	//邮政编码
	public String LIANXDH;	//联系电话
	public String CHUANGWS;	//床位数
	public String RIMZL;	//日门诊量
	public String YIYDJ;	//医院等级
	public String YUANZ;	//院长
	public String KES;		//科室
	public String TESZK;	//特色专科
	public String ZHUYSB;	//主要设备
	public String YOUX;		//邮箱
	public String WANGZ;	//网址
	public String CHENGCLX;	//乘车路线
	public String ZHIGRS;	//职工人数
	public String GJJSRYS;	//高级技术人员数
	public String BEIZ;		//备注	
	public String CHUC;		//出处	
	public String getID(){
		return ID;
	}
	public void setID (String id){
		ID = id;
	}
	
	public String getSJZSDW(){
		return SJZSDW;
	}
	public void setSJZSDW(String sjzsdw){
		SJZSDW = sjzsdw;
	}
	
	public String getYIYMC(){
		return YIYMC;
	}
	public void setYIYMC(String yiymc){
		YIYMC = yiymc;
	}
	
	public String getYIYBM(){
		return YIYBM;
	}
	public void setYIYBM(String yiybm){
		YIYBM = yiybm;
	}
	
	public String getSUOSDQ(){
		return SUOSDQ;
	}
	public void setSUOSDQ(String suosdq){
		SUOSDQ = suosdq;
	}
	
	public String getDANWXZ(){
		return DANWXZ;
	}
	public void setDANWXZ(String danwxz){
		DANWXZ = danwxz;
	}
	
	public String getYIYDZ(){
		return YIYDZ;
	}
	public void setYIYDZ(String yiydz){
		YIYDZ = yiydz;
	}
	
	public String getYOUZBM(){
		return YOUZBM;
	}
	public void setYOUZBM(String youzbm){
		YOUZBM = youzbm;
	}
	
	public String getLIANXDH(){
		return LIANXDH;
	}
	public void setLIANXDH(String lianxdh){
		LIANXDH = lianxdh;
	}
	
	public String getCHUANGWS(){
		return CHUANGWS;
	}
	public void setCHUANGWS(String chuangws){
		CHUANGWS = chuangws;
	}
	
	public String getRIMZL(){
		return RIMZL;
	}
	public void setRIMZL(String rimzl){
		RIMZL = rimzl;
	}
	
	public String getYIYDJ(){
		return YIYDJ;
	}
	public void setYIYDJ(String yiydj){
		YIYDJ = yiydj;
	}
	
	public String getYUANZ(){
		return YUANZ;
	}
	public void setYUANZ(String yuanz){
		YUANZ = yuanz;
	}
	
	public String getKES(){
		return KES;
	}
	public void setKES(String kes){
		KES = kes;
	}
	
	public String getTESZK(){
		return TESZK;
	}
	public void setTESZK(String teszk){
		TESZK = teszk;
	}
	
	public String getZHUYSB(){
		return ZHUYSB;
	}
	public void setZHUYSB(String zhuysb){
		ZHUYSB = zhuysb;
	}
	
	public String getYOUX(){
		return YOUX;
	}
	public void setYOUX(String youx){
		YOUX = youx;
	}
	
	public String getWANGZ(){
		return WANGZ;
	}
	public void setWANGZ(String wangz){
		WANGZ = wangz;
	}
	
	public String getCHENGCLX(){
		return CHENGCLX;
	}
	public void setCHENGCLX(String chengclx){
		CHENGCLX = chengclx;
	}	
	
	public String getZHIGRS(){
		return ZHIGRS;
	}
	public void setZHIGRS(String zhigrs){
		ZHIGRS = zhigrs;
	}
	
	public String getGJJSRYS(){
		return GJJSRYS;
	}
	public void setGJJSRYS(String gjjsrys){
		GJJSRYS = gjjsrys;
	}
	
	public String getBEIZ(){
		return BEIZ;
	}
	public void setBEIZ(String beiz){
		BEIZ = beiz;
	}
	
	public String getCHUC(){
		return CHUC;
	}
	public void setCHUC(String chuc){
		CHUC = chuc;
	}
	
	/**
	 * Get attribute by given name.
	 * @param attrName The attribute name
	 * @return Attribute value
	 */
	public String getAttr(String attrName) {
		// TODO Auto-generated method stub
		if(attrName.equals("ID")){
			return ID;
		}
		else if(attrName.equals("SJZSDW")){
			return SJZSDW;
		}
		else if(attrName.equals("YIYMC")){
			return YIYMC;
		}
		else if(attrName.equals("SUOSDQ")){
			return SUOSDQ;
		}
		else if(attrName.equals("DANWXZ")){
			return DANWXZ;
		}
		else if(attrName.equals("YIYDZ")){
			return YIYDZ;
		}
		else if(attrName.equals("YOUZBM")){
			return YOUZBM;
		} 
		else if(attrName.equals("LIANXDH")){
			return LIANXDH;
		}
		else if(attrName.equals("CHUANGWS")){
			return CHUANGWS;
		}
		else if(attrName.equals("RIMZL")){
			return RIMZL;
		}
		else if(attrName.equals("YIYDJ")){
			return YIYDJ;
		}
		else if(attrName.equals("YUANZ")){
			return YUANZ;
		}
		else if(attrName.equals("KES")){
			return KES;
		}
		else if(attrName.equals("TESZK")){
			return TESZK;
		}
		else if(attrName.equals("ZHUYSB")){
			return ZHUYSB;
		}
		else if(attrName.equals("YOUX")){
			return YOUX;
		}
		else if(attrName.equals("WANGZ")){
			return WANGZ;
		}
		else if(attrName.equals("CHENGCLX")){
			return CHENGCLX;
		}
		else if(attrName.equals("ZHIGRS")){
			return ZHIGRS;
		}
		else if(attrName.equals("GJJSRYS")){
			return GJJSRYS;
		}
		else if(attrName.equals("BEIZ")){
			return BEIZ;
		}
		else if(attrName.equals("CHUC")){
			return BEIZ;
		}
		
		return null;
	}
}
