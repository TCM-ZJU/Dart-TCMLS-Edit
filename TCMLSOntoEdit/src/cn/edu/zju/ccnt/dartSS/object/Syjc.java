package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的实验检查类
 * @author zhm
 *
 */
public class Syjc {
	public String SSJC_ID; //实验检查_ID 
	public String LCYJ_ID; //临床研究_ID 
	public String JCXM   ; //检查项目    
	public String JCSJ   ; //时间（疗程）
	public String BHZB   ; //变化指标    
	public String GCFZ   ; //观察分组    
	public String getBHZB() {
		return BHZB;
	}
	public void setBHZB(String bhzb) {
		BHZB = bhzb;
	}
	public String getGCFZ() {
		return GCFZ;
	}
	public void setGCFZ(String gcfz) {
		GCFZ = gcfz;
	}
	public String getJCSJ() {
		return JCSJ;
	}
	public void setJCSJ(String jcsj) {
		JCSJ = jcsj;
	}
	public String getJCXM() {
		return JCXM;
	}
	public void setJCXM(String jcxm) {
		JCXM = jcxm;
	}
	public String getLCYJ_ID() {
		return LCYJ_ID;
	}
	public void setLCYJ_ID(String lcyj_id) {
		LCYJ_ID = lcyj_id;
	}
	public String getSSJC_ID() {
		return SSJC_ID;
	}
	public void setSSJC_ID(String ssjc_id) {
		SSJC_ID = ssjc_id;
	}

}
