package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的抽象临床研究对象类
 * @author zhm
 * 
 */
public class Cxlcyjdx {
	/**
	 * 抽象对象_ID
	 */
	public String CXDX_ID; 
	/**
	 * 临床研究_ID
	 */
	public String LCYJ_ID; 
	/**
	 * 病证症类型 
	 */
	public String BZZLX  ; 
	/**
	 * 病证名称 
	 */
	public String BZMC   ; 
	
	public String getBZMC() {
		return BZMC;
	}
	public void setBZMC(String bzmc) {
		BZMC = bzmc;
	}
	public String getBZZLX() {
		return BZZLX;
	}
	public void setBZZLX(String bzzlx) {
		BZZLX = bzzlx;
	}
	public String getCXDX_ID() {
		return CXDX_ID;
	}
	public void setCXDX_ID(String cxdx_id) {
		CXDX_ID = cxdx_id;
	}
	public String getLCYJ_ID() {
		return LCYJ_ID;
	}
	public void setLCYJ_ID(String lcyj_id) {
		LCYJ_ID = lcyj_id;
	}

}
