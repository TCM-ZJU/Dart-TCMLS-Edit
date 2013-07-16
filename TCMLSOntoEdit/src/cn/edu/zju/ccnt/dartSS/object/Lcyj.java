package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的临床研究类
 * @author zhm
 *  
 */
public class Lcyj {
	public String LCYJ_ID; //临床研究_ID        
	public String LCYJMC ; //临床研究名称       
	public String BLCJSJ ; //病例采集时间       
	public String YJFL   ; //专题名             
	public String GCZZLS ; //观察组总例数       
	public String FGCLS  ; //女性观察例数       
	public String MGCLS  ; //男性观察例数       
	public String NLZ    ; //年龄组             
	public String YJFF   ; //研究方法           
	public String YJFZ   ; //研究分组           
	public String JG     ; //结果               
	public String LCYJDW ; //临床研究单位       
	public String J_BY   ; //急_病因            
	public String J_CRY  ; //急_传染源          
	public String J_CRTJ ; //急_传染途径        
	public String J_XGYS ; //急_相关因素        
	public String J_DQ   ; //急_地区 
	
	/**
	 * 数量
	 */
	private String NUM;
	/**
	 *文献作者单位
	 */
	private String DW;
	/**
	 *文献期刊名称
	 */
	private String QKMC;
	/**
	 *期刊期号 
	 */
	private String QS;

	public String getBLCJSJ() {
		return BLCJSJ;
	}
	public void setBLCJSJ(String blcjsj) {
		BLCJSJ = blcjsj;
	}
	public String getFGCLS() {
		return FGCLS;
	}
	public void setFGCLS(String fgcls) {
		FGCLS = fgcls;
	}
	public String getGCZZLS() {
		return GCZZLS;
	}
	public void setGCZZLS(String gczzls) {
		GCZZLS = gczzls;
	}
	public String getJ_BY() {
		return J_BY;
	}
	public void setJ_BY(String j_by) {
		J_BY = j_by;
	}
	public String getJ_CRTJ() {
		return J_CRTJ;
	}
	public void setJ_CRTJ(String j_crtj) {
		J_CRTJ = j_crtj;
	}
	public String getJ_CRY() {
		return J_CRY;
	}
	public void setJ_CRY(String j_cry) {
		J_CRY = j_cry;
	}
	public String getJ_DQ() {
		return J_DQ;
	}
	public void setJ_DQ(String j_dq) {
		J_DQ = j_dq;
	}
	public String getJ_XGYS() {
		return J_XGYS;
	}
	public void setJ_XGYS(String j_xgys) {
		J_XGYS = j_xgys;
	}
	public String getJG() {
		return JG;
	}
	public void setJG(String jg) {
		JG = jg;
	}
	public String getLCYJ_ID() {
		return LCYJ_ID;
	}
	public void setLCYJ_ID(String lcyj_id) {
		LCYJ_ID = lcyj_id;
	}
	public String getLCYJDW() {
		return LCYJDW;
	}
	public void setLCYJDW(String lcyjdw) {
		LCYJDW = lcyjdw;
	}
	public String getLCYJMC() {
		return LCYJMC;
	}
	public void setLCYJMC(String lcyjmc) {
		LCYJMC = lcyjmc;
	}
	public String getMGCLS() {
		return MGCLS;
	}
	public void setMGCLS(String mgcls) {
		MGCLS = mgcls;
	}
	public String getNLZ() {
		return NLZ;
	}
	public void setNLZ(String nlz) {
		NLZ = nlz;
	}
	public String getYJFF() {
		return YJFF;
	}
	public void setYJFF(String yjff) {
		YJFF = yjff;
	}
	public String getYJFL() {
		return YJFL;
	}
	public void setYJFL(String yjfl) {
		YJFL = yjfl;
	}
	public String getYJFZ() {
		return YJFZ;
	}
	public void setYJFZ(String yjfz) {
		YJFZ = yjfz;
	}
	public String getDW() {
		return DW;
	}
	public void setDW(String dw) {
		DW = dw;
	}
	public String getQKMC() {
		return QKMC;
	}
	public void setQKMC(String qkmc) {
		QKMC = qkmc;
	}
	public String getQS() {
		return QS;
	}
	public void setQS(String qs) {
		QS = qs;
	}
	public String getNUM() {
		return NUM;
	}
	public void setNUM(String num) {
		NUM = num;
	}       

	
}
