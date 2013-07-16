package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的具体治疗过程类
 * @author zhm
 * 
 */
public class Jtzlgc {
	/**
	 * 具体治疗过程_ID  
	 */
	public String JTZLGC_ID;       
	/**
	 * 临床研究_ID  
	 */
	public String LCYJ_ID  ;            
	/**
	 * 治疗时间 
	 */
	public String ZLSJ     ;               
	/**
	 * 治疗类型
	 */
	public String ZLLX     ;               
	/**
	 * 临床步骤序号
	 */
	public String LCBZXH   ;          
	public String getJTZLGC_ID() {
		return JTZLGC_ID;
	}
	public void setJTZLGC_ID(String jtzlgc_id) {
		JTZLGC_ID = jtzlgc_id;
	}
	public String getLCBZXH() {
		return LCBZXH;
	}
	public void setLCBZXH(String lcbzxh) {
		LCBZXH = lcbzxh;
	}
	public String getLCYJ_ID() {
		return LCYJ_ID;
	}
	public void setLCYJ_ID(String lcyj_id) {
		LCYJ_ID = lcyj_id;
	}
	public String getZLLX() {
		return ZLLX;
	}
	public void setZLLX(String zllx) {
		ZLLX = zllx;
	}
	public String getZLSJ() {
		return ZLSJ;
	}
	public void setZLSJ(String zlsj) {
		ZLSJ = zlsj;
	}

}
