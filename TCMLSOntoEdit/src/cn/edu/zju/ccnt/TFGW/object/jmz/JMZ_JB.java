package cn.edu.zju.ccnt.TFGW.object.jmz;

import cn.edu.zju.ccnt.TFGW.object.objectInterface.DataReader;

/**
 * 中医急门诊疾病数据库疾病表
 * @author 张小刚
 *
 */
public class JMZ_JB implements DataReader {

	/**
	 * 疾病编号
	 */
	public String JB_ID;
	
	/**
	 * 疾病名称
	 */
	public String JBMC;

	/**
	 * 原发病
	 */
	public String YFB;

	/**
	 * 并发症
	 */
	public String BFZ;

	/**
	 * 症候
	 */
	public String ZH;
	
	/**
	 * 症状名称
	 */
	public String ZZ;

	
	public String getJB_ID() {
		return JB_ID;
	}
	public void setJB_ID(String jb_id) {
		JB_ID = jb_id;
	}
	
	public String getJBMC() {
		return JBMC;
	}
	public void setJBMC(String jbmc) {
		JBMC = jbmc;
	}
	
	public String getYFB() {
		return YFB;
	}
	public void setYFB(String yfb) {
		YFB = yfb;
	}
	
	public String getBFZ() {
		return BFZ;
	}
	public void setBFZ(String bfz) {
		BFZ = bfz;
	}

	public String getZZ() {
		return ZZ;
	}
	public void setZZ(String zz) {
		ZZ = zz;
	}
	
	public String getZH() {
		return ZH;
	}
	public void setZH(String zh) {
		ZH = zh;
	}
	
	/**
	 * Get attribute by given name.
	 * @param attrName The attribute name
	 * @return Attribute value
	 */
	public String getAttr(String attrName) {
		// TODO Auto-generated method stub
		if(attrName.equals("JB_ID")){
			return JB_ID;
		}
		else if(attrName.equals("JBMC")){
			return JBMC;
		}
		else if(attrName.equals("YFB")){
			return YFB;
		}
		else if(attrName.equals("BFZ")){
			return BFZ;
		}
		else if(attrName.equals("ZZ")){
			return ZZ;
		}
		else if(attrName.equals("ZH")){
			return ZH;
		}
	
		return null;
	}
}
