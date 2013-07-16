package cn.edu.zju.ccnt.TFGW.object.jib;

public class C_ZYBY {
	/**
	 * 疾病ID
	 */
	private String ID;

	/**
	 * 疾病名称
	 */
	private String JBMC;

	/**
	 * 病因名称
	 */
	private String BYMC;

	/**
	 * 致病特点
	 */
	private String ZBTD;

	/**
	 * 致病途径
	 */
	private String ZBTJ;

	/**
	 * 转化
	 */
	private String ZH;

	/**
	 * 发病人群
	 */
	private String QTZBYS;

	/**
	 * 备注
	 */
	private String BZ;

	public String getBYMC() {
		return BYMC;
	}

	public void setBYMC(String bymc) {
		BYMC = bymc;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String bz) {
		BZ = bz;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getJBMC() {
		return JBMC;
	}

	public void setJBMC(String jbmc) {
		JBMC = jbmc;
	}

	public String getQTZBYS() {
		return QTZBYS;
	}

	public void setQTZBYS(String qtzbys) {
		QTZBYS = qtzbys;
	}

	public String getZBTD() {
		return ZBTD;
	}

	public void setZBTD(String zbtd) {
		ZBTD = zbtd;
	}

	public String getZBTJ() {
		return ZBTJ;
	}

	public void setZBTJ(String zbtj) {
		ZBTJ = zbtj;
	}

	public String getZH() {
		return ZH;
	}

	public void setZH(String zh) {
		ZH = zh;
	}
}
