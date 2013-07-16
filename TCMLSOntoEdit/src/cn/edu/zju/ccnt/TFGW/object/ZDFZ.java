package cn.edu.zju.ccnt.TFGW.object;

import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;

/**
 * 中毒防治数据库
 * @author 张小刚
 *
 */
public class ZDFZ implements DataReader{

	/**
	 * 疾病编号
	 */
	public String ID;
	
	/**
	 * 疾病名称
	 */
	public String JIBMC;

	/**
	 * 中毒物
	 */
	public String ZHONGDW;

	/**
	 * 发病原因
	 */
	public String FABYY;

	/**
	 * 预防与控制
	 */
	public String YUFYKZ;
	
	/**
	 * 诊断
	 */
	public String ZHEND;
	
	/**
	 * 治疗
	 */
	public String ZHIL;
	
	/**
	 * 症状与体征
	 */
	public String ZHENGZYTZ;
	
	/**
	 * 中毒分类
	 */
	public String ZHONGDFL;
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	
	public String getJIBMC() {
		return JIBMC;
	}
	public void setJIBMC(String jibmc) {
		JIBMC = jibmc;
	}
	
	public String getZHONGDW() {
		return ZHONGDW;
	}
	public void setZHONGDW(String zhongdw) {
		ZHONGDW = zhongdw;
	}
	
	public String getFABYY() {
		return FABYY;
	}
	public void setFABYY(String fabyy) {
		FABYY = fabyy;
	}

	public String getYUFYKZ() {
		return YUFYKZ;
	}
	public void setYUFYKZ(String yufykz) {
		YUFYKZ = yufykz;
	}
	
	public String getZHEND() {
		return ZHEND;
	}
	public void setZHEND(String zhend) {
		ZHEND = zhend;
	}
	
	public String getZHIL() {
		return ZHIL;
	}
	public void setZHIL(String zhil) {
		ZHIL = zhil;
	}
	
	public String getZHENGZYTZ() {
		return ZHENGZYTZ;
	}
	public void setZHENGZYTZ(String zhengzytz) {
		ZHENGZYTZ = zhengzytz;
	}
	
	public String getZHONGDFL() {
		return ZHONGDFL;
	}
	public void setZHONGDFL(String zhongdfl) {
		ZHONGDFL = zhongdfl;
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
		else if(attrName.equals("JIBMC")){
			return JIBMC;
		}
		else if(attrName.equals("ZHONGDW")){
			return ZHONGDW;
		}
		else if(attrName.equals("FABYY")){
			return FABYY;
		}
		else if(attrName.equals("YUFYKZ")){
			return YUFYKZ;
		}
		else if(attrName.equals("ZHEND")){
			return ZHEND;
		}
		else if(attrName.equals("ZHIL")){
			return ZHIL;
		}
		else if(attrName.equals("ZHENGZYTZ")){
			return ZHENGZYTZ;
		}
		else if(attrName.equals("ZHONGDFL")){
			return ZHONGDFL;
		}
		
		return null;
	}
}
