package cn.edu.zju.ccnt.dartSS.object;

/**用于ORM的证候类
 * @author zhm
 *
 */
public class Zh {
	public String ZH_ID; //证候_ID 
	public String ZHMC ; //证候名称
	public String RefNum;  //出现次数
	public String getZH_ID() {
		return ZH_ID;
	}
	public void setZH_ID(String zh_id) {
		ZH_ID = zh_id;
	}
	public String getZHMC() {
		return ZHMC;
	}
	public void setZHMC(String zhmc) {
		ZHMC = zhmc;
	}
	public String getRefNum() {
		return RefNum;
	}
	public void setRefNum(String refNum) {
		RefNum = refNum;
	}

}
