package cn.edu.zju.ccnt.TFGW.object;

import cn.edu.zju.ccnt.TFGW.object.objectInterface.DataReader;

/**
 * 中医疾病治疗标准数据库
 * @author 张小刚
 *
 */
public class ZYBZZLBZ implements DataReader{

	/**
	 * 疾病编号
	 */
	public String ID;
	
	/**
	 * 病证名称
	 */
	public String BINGZMC;

	/**
	 * 病因病机
	 */
	public String BINGYBJ;

	/**
	 * 诊断依据
	 */
	public String ZHENDYJ;
	
	/**
	 * 症候分类
	 */
	public String ZHENGHFL;

	/**
	 * 疗效评定-治愈
	 */
	public String LXPD_ZY;
	
	/**
	 * 疗效评定-好转
	 */
	public String LXPD_HZ;
	
	/**
	 * 疗效评定-未愈
	 */
	public String LXPD_WY;

	/**
	 * 中医分科
	 */
	public String ZHONGYFK;
	
	/**
	 * 疾病分类
	 */
	public String JIBFL;	
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	
	public String getBINGZMC() {
		return BINGZMC;
	}
	public void setBINGZMC(String bingzmc) {
		BINGZMC = bingzmc;
	}
	
	public String getBINGYBJ() {
		return BINGYBJ;
	}
	public void setBINGYBJ(String bingybj) {
		BINGYBJ = bingybj;
	}
	
	public String getZHENDYJ() {
		return ZHENDYJ;
	}
	public void setZHENDYJ(String zhendyj) {
		ZHENDYJ = zhendyj;
	}
	
	public String getZHENGHFL() {
		return ZHENGHFL;
	}
	public void setZHENGHFL(String zhenghfl) {
		ZHENGHFL = zhenghfl;
	}	
	
	public String getLXPD_ZY() {
		return LXPD_ZY;
	}
	public void setLXPD_ZY(String lxpd_zy) {
		LXPD_ZY = lxpd_zy;
	}
	
	public String getLXPD_HZ() {
		return LXPD_HZ;
	}
	public void setLXPD_HZ(String lxpd_hz) {
		LXPD_HZ = lxpd_hz;
	}
	
	public String getLXPD_WY() {
		return LXPD_WY;
	}
	public void setLXPD_WY(String lxpd_wy) {
		LXPD_WY = lxpd_wy;
	}
	
	public String getZHONGYFK() {
		return ZHONGYFK;
	}
	public void setZHONGYFK(String zhongyfk) {
		ZHONGYFK = zhongyfk;
	}	
	
	public String getJIBFL() {
		return JIBFL;
	}
	public void setJIBFL(String jibfl) {
		JIBFL = jibfl;
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
		else if(attrName.equals("BINGZMC")){
			return BINGZMC;
		}
		else if(attrName.equals("BINGYBJ")){
			return BINGYBJ;
		}
		else if(attrName.equals("ZHENDYJ")){
			return ZHENDYJ;
		}
		else if(attrName.equals("ZHENGHFL")){
			return ZHENGHFL;
		}
		else if(attrName.equals("LXPD_ZY")){
			return LXPD_ZY;
		}
		else if(attrName.equals("LXPD_HZ")){
			return LXPD_HZ;
		}
		else if(attrName.equals("LXPD_WY")){
			return LXPD_WY;
		}
		else if(attrName.equals("ZHONGYFK")){
			return ZHONGYFK;
		}
		else if(attrName.equals("JIBFL")){
			return JIBFL;
		}
		
		return null;
	}
}
