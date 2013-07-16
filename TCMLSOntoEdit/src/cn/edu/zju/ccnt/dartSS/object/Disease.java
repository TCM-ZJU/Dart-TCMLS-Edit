package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的疾病类 
 * @author zhm
 * 
 */
public class Disease {
    /**
     * 所属专题
     */
    public String Subject;  
	/**
	 * 疾病名称
	 */
	public String JBMC;

	/**
	 * 原发病
	 */
	public String YFB;

	/**
	 * 并发症
	 */
	public String BFZ;

	/**
	 * 证候
	 */
	public String ZH;

	/**
	 * 症状
	 */
	public String ZZ;

	/**
	 * 疾病编号
	 */
	public String JB_ID;
	/**
	 * 出现次数
	 */
	public String RefNum;  
	public String getBFZ() {
		return BFZ;
	}

	public void setBFZ(String bfz) {
		BFZ = bfz;
	}

	public String getJBMC() {
		return JBMC;
	}

	public void setJBMC(String jbmc) {
		JBMC = jbmc;
	}

	public String getYFB() {
		return YFB;
	}

	public void setYFB(String yfb) {
		YFB = yfb;
	}

	public String getZH() {
		return ZH;
	}

	public void setZH(String zh) {
		ZH = zh;
	}

	public String getZZ() {
		return ZZ;
	}

	public void setZZ(String zz) {
		ZZ = zz;
	}

	public String getJB_ID() {
		return JB_ID;
	}

	public void setJB_ID(String jb_id) {
		JB_ID = jb_id;
	}

	public String getRefNum() {
		return RefNum;
	}

	public void setRefNum(String refNum) {
		RefNum = refNum;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}
}
