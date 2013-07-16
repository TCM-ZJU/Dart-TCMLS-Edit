package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM临床症状类
 * @author zhm
 *
 */
public class Lczz {
	public String LCZZ_ID; //临床症状_ID
	public String ZZMC   ; //症状名称 
	public String RefNum;  //出现次数
	public String getRefNum() {
		return RefNum;
	}
	public void setRefNum(String refNum) {
		RefNum = refNum;
	}
	public String getLCZZ_ID() {
		return LCZZ_ID;
	}
	public void setLCZZ_ID(String lczz_id) {
		LCZZ_ID = lczz_id;
	}
	public String getZZMC() {
		return ZZMC;
	}
	public void setZZMC(String zzmc) {
		ZZMC = zzmc;
	}
}
