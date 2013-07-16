package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的其他疗法类
 * @author zhm
 *
 */
public class Qtlf {
	public String ZLGC_ID; //治疗过程_ID    
	public String FFMC   ; //方法名称       
	public String FFMS   ; //方法描述       
	public String ZZ     ; //治则           
	public String getFFMC() {
		return FFMC;
	}
	public void setFFMC(String ffmc) {
		FFMC = ffmc;
	}
	public String getFFMS() {
		return FFMS;
	}
	public void setFFMS(String ffms) {
		FFMS = ffms;
	}
	public String getZLGC_ID() {
		return ZLGC_ID;
	}
	public void setZLGC_ID(String zlgc_id) {
		ZLGC_ID = zlgc_id;
	}
	public String getZZ() {
		return ZZ;
	}
	public void setZZ(String zz) {
		ZZ = zz;
	}

}
