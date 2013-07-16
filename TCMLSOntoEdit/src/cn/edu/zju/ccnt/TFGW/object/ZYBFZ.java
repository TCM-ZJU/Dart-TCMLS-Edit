package cn.edu.zju.ccnt.TFGW.object;

import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;

/**
 * 职业病防治数据库
 * @author 张小刚
 *
 */
public class ZYBFZ implements DataReader{

	/**
	 * 疾病编号
	 */
	public String ID;
	
	/**
	 * 疾病名称
	 */
	public String JIBMC;

	/**
	 * 文题
	 */
	public String WENT;
	
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
		return null;
	}
	
}
