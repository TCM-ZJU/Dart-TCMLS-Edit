package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的药方疗法类
 * @author zhm
 *
 */
public class Yflf {
	public String ZLGC_ID; //治疗过程_ID
	public String GYFS   ; //给药方式   
	public String YFMC   ; //药方名称   
	public String YFJX   ; //药方剂型   
	public String ZZ     ; //治则 
	public String REFNUM ;  //被引用次数
	public String getGYFS() {
		return GYFS;
	}
	public void setGYFS(String gyfs) {
		GYFS = gyfs;
	}
	public String getYFJX() {
		return YFJX;
	}
	public void setYFJX(String yfjx) {
		YFJX = yfjx;
	}
	public String getYFMC() {
		return YFMC;
	}
	public void setYFMC(String yfmc) {
		YFMC = yfmc;
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
	public String getREFNUM() {
		return REFNUM;
	}
	public void setREFNUM(String refnum) {
		REFNUM = refnum;
	}

}
