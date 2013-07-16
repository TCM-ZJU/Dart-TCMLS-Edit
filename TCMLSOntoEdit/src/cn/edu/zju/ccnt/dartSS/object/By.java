package cn.edu.zju.ccnt.dartSS.object;

/**用于orm的病因类
 * @author zhm
 * 
 */
public class By {
	/**
	 *  病因
	 */
	public String J_BY;
	/**
	 * 年龄组
	 */
	public String NLZ;
	/**
	 * 结果
	 */
	public String JG;
	/**
	 * 临床研究名称
	 */
	public String LCYJMC;
	
	public String getJ_BY() {
		return J_BY;
	}
	public void setJ_BY(String j_by) {
		J_BY = j_by;
	}
	public String getJG() {
		return JG;
	}
	public void setJG(String jg) {
		JG = jg;
	}
	public String getLCYJMC() {
		return LCYJMC;
	}
	public void setLCYJMC(String lcyjmc) {
		LCYJMC = lcyjmc;
	}
	public String getNLZ() {
		return NLZ;
	}
	public void setNLZ(String nlz) {
		NLZ = nlz;
	}
}
