package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的穴位类
 * @author zhm
 *
 */
public class Xw {
	public String XW_ID  ; //穴位_ID        
	public String ZLGC_ID; //治疗过程_ID    
	public String XWMC   ; //穴位名称       
	public String XWLX   ; //穴位类型       
	public String getXW_ID() {
		return XW_ID;
	}
	public void setXW_ID(String xw_id) {
		XW_ID = xw_id;
	}
	public String getXWLX() {
		return XWLX;
	}
	public void setXWLX(String xwlx) {
		XWLX = xwlx;
	}
	public String getXWMC() {
		return XWMC;
	}
	public void setXWMC(String xwmc) {
		XWMC = xwmc;
	}
	public String getZLGC_ID() {
		return ZLGC_ID;
	}
	public void setZLGC_ID(String zlgc_id) {
		ZLGC_ID = zlgc_id;
	}

}
