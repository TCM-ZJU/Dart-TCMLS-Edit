package cn.edu.zju.ccnt.TFGW.object.jib;

public class C_XYBY {
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
	 * 传染源
	 */
	private String CRY;

	/**
	 * 传染途径
	 */
	private String CRTJ;		
	
	/**
	 * 发病地区
	 */
	private String FBDQ;
	
	/**
	 * 发病人群
	 */
	private String FBRQ;
	
	/**
	 * 发病季节
	 */
	private String FBJJ;
	
	/**
	 * 发病率
	 */
	private String FBL;
		
	/**
	 * 发病相关因素
	 */
	private String FBXGYS;

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

	public String getCRTJ() {
		return CRTJ;
	}

	public void setCRTJ(String crtj) {
		CRTJ = crtj;
	}

	public String getCRY() {
		return CRY;
	}

	public void setCRY(String cry) {
		CRY = cry;
	}

	public String getFBDQ() {
		return FBDQ;
	}

	public void setFBDQ(String fbdq) {
		FBDQ = fbdq;
	}

	public String getFBJJ() {
		return FBJJ;
	}

	public void setFBJJ(String fbjj) {
		FBJJ = fbjj;
	}

	public String getFBL() {
		return FBL;
	}

	public void setFBL(String fbl) {
		FBL = fbl;
	}

	public String getFBRQ() {
		return FBRQ;
	}

	public void setFBRQ(String fbrq) {
		FBRQ = fbrq;
	}

	public String getFBXGYS() {
		return FBXGYS;
	}

	public void setFBXGYS(String fbxgys) {
		FBXGYS = fbxgys;
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

	public String getZBTD() {
		return ZBTD;
	}

	public void setZBTD(String zbtd) {
		ZBTD = zbtd;
	}
}
