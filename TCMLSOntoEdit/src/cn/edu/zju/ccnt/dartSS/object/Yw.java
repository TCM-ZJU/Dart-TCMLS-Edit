package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的药物疗法类
 * @author zhm
 *
 */
public class Yw {
	public String YW_ID  ; //药物_ID     
	public String ZLGC_ID; //治疗过程_ID 
	public String YWMC   ; //药物名称    
	public String YMJL   ; //药物剂量    
	public String JLDW   ; //剂量单位    
	public String getJLDW() {
		return JLDW;
	}
	public void setJLDW(String jldw) {
		JLDW = jldw;
	}
	public String getYMJL() {
		return YMJL;
	}
	public void setYMJL(String ymjl) {
		YMJL = ymjl;
	}
	public String getYW_ID() {
		return YW_ID;
	}
	public void setYW_ID(String yw_id) {
		YW_ID = yw_id;
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

}
