package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的西药疗法类
 * @author zhm
 * 
 */
public class Xylf {
	public String ZLGC_ID; //治疗过程_ID
	public String GYFS   ; //给药方式   
	public String YWMC   ; //药物名称   
	public String YWJX   ; //药物剂型   
	public String GYSJ   ; //给药时间   
	public String ZLYZ   ; //治则 
	public String REFNUM; //被引用次数
	public String getGYFS() {
		return GYFS;
	}
	public void setGYFS(String gyfs) {
		GYFS = gyfs;
	}
	public String getGYSJ() {
		return GYSJ;
	}
	public void setGYSJ(String gysj) {
		GYSJ = gysj;
	}
	public String getYWJX() {
		return YWJX;
	}
	public void setYWJX(String ywjx) {
		YWJX = ywjx;
	}
	public String getYWMC() {
		return YWMC;
	}
	public void setYWMC(String ywmc) {
		YWMC = ywmc;
	}
	public String getZLGC_ID() {
		return ZLGC_ID;
	}
	public void setZLGC_ID(String zlgc_id) {
		ZLGC_ID = zlgc_id;
	}
	public String getZLYZ() {
		return ZLYZ;
	}
	public void setZLYZ(String zlyz) {
		ZLYZ = zlyz;
	}
	public String getREFNUM() {
		return REFNUM;
	}
	public void setREFNUM(String refnum) {
		REFNUM = refnum;
	}

}
