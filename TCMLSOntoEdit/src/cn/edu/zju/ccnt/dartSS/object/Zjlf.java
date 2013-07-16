package cn.edu.zju.ccnt.dartSS.object;

/**用户ORM的针灸疗法类
 * @author zhm
 *
 */
public class Zjlf {
	public String ZLGC_ID; //治疗过程_ID       
	public String ZJFF   ; //针灸方法          
	public String ZJQX   ; //针灸器械          
	public String ZCSF   ; //针刺手法          
	public String ZCCS   ; //针刺次数          
	public String ZZ     ; //治则              
	public String JL     ; //经络              
	public String getJL() {
		return JL;
	}
	public void setJL(String jl) {
		JL = jl;
	}
	public String getZCCS() {
		return ZCCS;
	}
	public void setZCCS(String zccs) {
		ZCCS = zccs;
	}
	public String getZCSF() {
		return ZCSF;
	}
	public void setZCSF(String zcsf) {
		ZCSF = zcsf;
	}
	public String getZJFF() {
		return ZJFF;
	}
	public void setZJFF(String zjff) {
		ZJFF = zjff;
	}
	public String getZJQX() {
		return ZJQX;
	}
	public void setZJQX(String zjqx) {
		ZJQX = zjqx;
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
