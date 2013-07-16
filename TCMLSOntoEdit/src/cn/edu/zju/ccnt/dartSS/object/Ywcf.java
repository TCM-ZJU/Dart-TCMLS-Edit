package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的药物成分类
 * @author zhm
 *
 */
public class Ywcf {
	public String ZLGC_ID; //治疗过程_ID          
	public String YWCF_ID; //药物成分_ID          
	public String YWCFMC ; //药物成分名称         
	public String YWCFJL ; //药物成分剂量         
	public String JLDW   ; //剂量单位             
	public String getJLDW() {
		return JLDW;
	}
	public void setJLDW(String jldw) {
		JLDW = jldw;
	}
	public String getYWCF_ID() {
		return YWCF_ID;
	}
	public void setYWCF_ID(String ywcf_id) {
		YWCF_ID = ywcf_id;
	}
	public String getYWCFJL() {
		return YWCFJL;
	}
	public void setYWCFJL(String ywcfjl) {
		YWCFJL = ywcfjl;
	}
	public String getYWCFMC() {
		return YWCFMC;
	}
	public void setYWCFMC(String ywcfmc) {
		YWCFMC = ywcfmc;
	}
	public String getZLGC_ID() {
		return ZLGC_ID;
	}
	public void setZLGC_ID(String zlgc_id) {
		ZLGC_ID = zlgc_id;
	}

}
