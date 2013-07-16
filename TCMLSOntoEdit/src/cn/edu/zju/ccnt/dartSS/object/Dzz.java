package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的对照组类
 * @author zhm
 * 
 */
public class Dzz {
	/**
	 * 对照组_ID 
	 */
	public String DZZ_ID ;           
	/**
	 * 临床研究_ID
	 */
	public String LCYJ_ID;          
	/**
	 * 对照组名称
	 */
	public String DZZMC  ;           
	/**
	 * 年龄
	 */
	public String NL     ;                 
	/**
	 * 例数
	 */
	public String LS     ;                 
	/**
	 * 治疗药物
	 */
	public String ZLYW   ;            
	/**
	 * 检测
	 */
	public String JC     ;                 
	public String getDZZ_ID() {
		return DZZ_ID;
	}
	public void setDZZ_ID(String dzz_id) {
		DZZ_ID = dzz_id;
	}
	public String getDZZMC() {
		return DZZMC;
	}
	public void setDZZMC(String dzzmc) {
		DZZMC = dzzmc;
	}
	public String getJC() {
		return JC;
	}
	public void setJC(String jc) {
		JC = jc;
	}
	public String getLCYJ_ID() {
		return LCYJ_ID;
	}
	public void setLCYJ_ID(String lcyj_id) {
		LCYJ_ID = lcyj_id;
	}
	public String getLS() {
		return LS;
	}
	public void setLS(String ls) {
		LS = ls;
	}
	public String getNL() {
		return NL;
	}
	public void setNL(String nl) {
		NL = nl;
	}
	public String getZLYW() {
		return ZLYW;
	}
	public void setZLYW(String zlyw) {
		ZLYW = zlyw;
	}

}
