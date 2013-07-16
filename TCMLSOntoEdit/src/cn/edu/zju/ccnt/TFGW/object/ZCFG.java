package cn.edu.zju.ccnt.TFGW.object;

import cn.edu.zju.ccnt.TFGW.object.objectInterface.DataReader;

/**
 * 突发公卫政策法规数据库
 * @author 张小刚
 *
 */
public class ZCFG implements DataReader{

	/**
	 * 疾病编号
	 */
	public String ID;
	
	/**
	 * 疫病名称
	 */
	public String YIBMC;

	/**
	 * 文题
	 */
	public String WENT;

	/**
	 * 批准文号
	 */
	public String PIZWH;

	/**
	 * 批准机构
	 */
	public String PIZJG;
	
	/**
	 * 发布日期
	 */
	public String FABRQ;	
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	
	public String getYIBMC() {
		return YIBMC;
	}
	public void setYIBMC(String yibmc) {
		YIBMC = yibmc;
	}
	
	public String getWENT() {
		return WENT;
	}
	public void setWENT(String went) {
		WENT = went;
	}
	
	public String getPIZWH() {
		return PIZWH;
	}
	public void setPIZWH(String pizwh) {
		PIZWH = pizwh;
	}

	public String getPIZJG() {
		return PIZJG;
	}
	public void setPIZJG(String pizjg) {
		PIZJG = pizjg;
	}
	
	public String getFABRQ() {
		return FABRQ;
	}
	public void setFABRQ(String fabrq) {
		FABRQ = fabrq;
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
		else if(attrName.equals("YIBMC")){
			return YIBMC;
		}
		else if(attrName.equals("WENT")){
			return WENT;
		}
		else if(attrName.equals("PIZWH")){
			return PIZWH;
		}
		else if(attrName.equals("PIZJG")){
			return PIZJG;
		}
		else if(attrName.equals("FABRQ")){
			return FABRQ;
		}
		
		return null;
	}
	
}
