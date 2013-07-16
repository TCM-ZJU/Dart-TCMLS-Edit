package cn.edu.zju.ccnt.TFGW.object;

import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;

public class Expert implements DataReader{
	public String ID;		//ID
	public String SJZSDW;	//上级直属单位
	public String SUOSYY;	//所属医院
	public String SUOZKS;	//所在科室
	public String XINGM;	//姓名
	public String XINGB;	//性别
	public String ZHIW;		//职务
	public String ZHIC;		//职称
	public String XUEW;		//学位
	public String BIYXX;	//毕业学校
	public String MENZSJ;	//门诊时间
	public String YUYL;		//预约量
	public String LIANXDH;	//联系电话
	public String ZHUZJB;	//主治疾病
	public String BEIZ;		//备注
	public String CHUC;		//出处
	public String ZHICJB;   //职称级别
	
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
	
	public String getSUOSYY(){
		return SUOSYY;
	}
	public void setSUOSYY(String suosyy){
		SUOSYY = suosyy;
	}

	public String getSUOZKS(){
		return SUOZKS;
	}
	public void setSUOZKS(String suozks){
		SUOZKS = suozks;
	}
	
	public String getXINGM(){
		return XINGM;
	}
	public void setXINGM(String xingm){
		XINGM = xingm;
	}
	
	public String getXINGB(){
		return XINGB;
	}
	public void setXINGB(String xingb){
		XINGB = xingb;
	}
	
	public String getZHIW(){
		return ZHIW;
	}
	public void setZHIW(String zhiw){
		ZHIW = zhiw;
	}
	
	public String getZHIC(){
		return ZHIC;
	}
	public void setZHIC(String zhic){
		ZHIC = zhic;
	}
	
	public String getXUEW(){
		return XUEW;
	}
	public void setXUEW(String xuew){
		XUEW = xuew;
	}
	
	public String getBIYXX(){
		return BIYXX;
	}
	public void setBIYXX(String biyxx){
		BIYXX = biyxx;
	}
	
	public String getMENZSJ(){
		return MENZSJ;
	}
	public void setMENZSJ(String menzsj){
		MENZSJ = menzsj;
	}
	
	public String getYUYL(){
		return YUYL;
	}
	public void setYUYL(String yuyl){
		YUYL = yuyl;
	}
	
	public String getLIANXDH(){
		return LIANXDH;
	}
	public void setLIANXDH(String lianxdh){
		LIANXDH = lianxdh;
	}
	
	public String getZHUZJB(){
		return ZHUZJB;
	}
	public void setZHUZJB(String zhuzjb){
		ZHUZJB = zhuzjb;
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
	
	public String getZHICJB(){
		return ZHICJB;
	}
	public void setZHICJB(String zhicjb){
		ZHICJB = zhicjb;
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
		else if(attrName.equals("SUOSYY")){
			return SUOSYY;
		}
		else if(attrName.equals("SUOZKS")){
			return SUOZKS;
		}
		else if(attrName.equals("XINGM")){
			return XINGM;
		}
		else if(attrName.equals("XINGB")){
			return XINGB;
		}
		else if(attrName.equals("ZHIW")){
			return ZHIW;
		}
		else if(attrName.equals("ZHIC")){
			return ZHIC;
		}
		else if(attrName.equals("XUEW")){
			return XUEW;
		}
		else if(attrName.equals("BIYXX")){
			return BIYXX;
		}
		else if(attrName.equals("MENZSJ")){
			return MENZSJ;
		}
		else if(attrName.equals("YUYL")){
			return YUYL;
		}
		else if(attrName.equals("LIANXDH")){
			return LIANXDH;
		}
		else if(attrName.equals("ZHUZJB")){
			return ZHUZJB;
		}
		else if(attrName.equals("BEIZ")){
			return BEIZ;
		}
		else if(attrName.equals("CHUC")){
			return CHUC;
		}
		else if(attrName.equals("ZHICJB")){
			return ZHICJB;
		}
		
		return null;
	}
}
