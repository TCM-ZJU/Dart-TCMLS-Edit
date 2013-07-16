package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的临床诊疗类
 * @author zhm
 *
 */
public class Lczl {
	public String LCZL_ID; //临床诊疗_ID    
	public String LCYJ_ID; //临床研究_ID    
	public String BZZMC  ; //病证症名称     
	public String ZDZBFL ; //诊断指标分类   
	public String ZBQZ   ; //指标权重       
	public String ZBXM   ; //指标项目       
	public String LXBZLY ; //疗效标准来源   
	public String LXBZ   ; //疗效标准       
	public String ZDBZLY ; //诊断标准来源   
	public String ZDYQ   ; //中医诊断仪器   
	public String ZYZDFF ; //诊断方法       
	public String ZYXL   ; //总有效率 
	public String LCYJMC;  //相关的临床研究名称
	public String getBZZMC() {
		return BZZMC;
	}
	public void setBZZMC(String bzzmc) {
		BZZMC = bzzmc;
	}
	public String getLCYJ_ID() {
		return LCYJ_ID;
	}
	public void setLCYJ_ID(String lcyj_id) {
		LCYJ_ID = lcyj_id;
	}
	public String getLCZL_ID() {
		return LCZL_ID;
	}
	public void setLCZL_ID(String lczl_id) {
		LCZL_ID = lczl_id;
	}
	public String getLXBZ() {
		return LXBZ;
	}
	public void setLXBZ(String lxbz) {
		LXBZ = lxbz;
	}
	public String getLXBZLY() {
		return LXBZLY;
	}
	public void setLXBZLY(String lxbzfl) {
		LXBZLY = lxbzfl;
	}
	public String getZBQZ() {
		return ZBQZ;
	}
	public void setZBQZ(String zbqz) {
		ZBQZ = zbqz;
	}
	public String getZDBZLY() {
		return ZDBZLY;
	}
	public void setZDBZLY(String zdbzly) {
		ZDBZLY = zdbzly;
	}
	public String getZBXM() {
		return ZBXM;
	}
	public void setZBXM(String zdxm) {
		ZBXM = zdxm;
	}
	public String getZDYQ() {
		return ZDYQ;
	}
	public void setZDYQ(String zdyq) {
		ZDYQ = zdyq;
	}
	public String getZDZBFL() {
		return ZDZBFL;
	}
	public void setZDZBFL(String zdzbfl) {
		ZDZBFL = zdzbfl;
	}
	public String getZYXL() {
		return ZYXL;
	}
	public void setZYXL(String zyxl) {
		ZYXL = zyxl;
	}
	public String getZYZDFF() {
		return ZYZDFF;
	}
	public void setZYZDFF(String zyzdff) {
		ZYZDFF = zyzdff;
	}
	public String getLCYJMC() {
		return LCYJMC;
	}
	public void setLCYJMC(String lcyjmc) {
		LCYJMC = lcyjmc;
	}

}
