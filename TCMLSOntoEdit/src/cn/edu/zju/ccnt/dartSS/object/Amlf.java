package cn.edu.zju.ccnt.dartSS.object;

/**用户orm的按摩疗法类：包括 AMCS，JL，ZZ，ZLGC_ID，AMFF，AMSF，AMSJ
 *@author zhm
 */
public class Amlf {
	/**
	 * 按摩次数
	 */
	public String AMCS;

	/**
	 * 经络
	 */
	public String JL; 

	/**
	 * 治则
	 */
	public String ZZ; 

	/**
	 * 治疗过程_ID
	 */
	public String ZLGC_ID; 

	/**
	 * 按摩方法
	 */
	public String AMFF; 

	/**
	 * 按摩手法
	 */
	public String AMSF; 

	/**
	 * 按摩时间
	 */
	public String AMSJ; 

	public String getAMCS() {
		return AMCS;
	}

	public void setAMCS(String amcs) {
		AMCS = amcs;
	}

	public String getAMFF() {
		return AMFF;
	}

	public void setAMFF(String amff) {
		AMFF = amff;
	}

	public String getAMSF() {
		return AMSF;
	}

	public void setAMSF(String amsf) {
		AMSF = amsf;
	}

	public String getAMSJ() {
		return AMSJ;
	}

	public void setAMSJ(String amsj) {
		AMSJ = amsj;
	}

	public String getJL() {
		return JL;
	}

	public void setJL(String jl) {
		JL = jl;
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
