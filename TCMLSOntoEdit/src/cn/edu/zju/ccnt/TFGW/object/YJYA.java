package cn.edu.zju.ccnt.TFGW.object;

import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;
/**
 * 突发公卫应急预案数据库
 * @author 张小刚
 *
 */
public class YJYA implements DataReader{

	/**
	 * 疾病编号
	 */
	public String ID;
	
	/**
	 * 疫病名称
	 */
	public String JIBMC;

	/**
	 * 文题
	 */
	public String WENT;

	/**
	 * 发布日期
	 */
	public String FABRQ;		
	
	/**
	 * 症状体征
	 */
	public String ZHANGZTZ;

		
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
	
	public String getWENT() {
		return WENT;
	}
	public void setWENT(String went) {
		WENT = went;
	}
	
	public String getFABRQ() {
		return FABRQ;
	}
	public void setFABRQ(String fabrq) {
		FABRQ = fabrq;
	}
	
	public String getZHANGZTZ() {
		return ZHANGZTZ;
	}
	public void setZHANGZTZ(String zhangztz) {
		ZHANGZTZ = zhangztz;
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
		else if(attrName.equals("WENT")){
			return WENT;
		}
		else if(attrName.equals("FABRQ")){
			return FABRQ;
		}
		else if(attrName.equals("ZHANGZTZ")){
			return ZHANGZTZ;
		}
	
		return null;
	}
}
