package cn.edu.zju.ccnt.TFGW.object.jib;
import cn.edu.zju.ccnt.TFGW.object.objectInterface.*;

/**
 * 中医疾病数据库疾病表
 * @author 张小刚
 *
 */
public class C_jib implements DataReader{

	public String id;	//中医疾病ID
	public String jbmc;	//疾病名称
	public String ym;	//异名
	public String fbfs;	//发病方式
	public String yfb;	//原发病
	public String bfz;	//并发症
	public String zzmc;	//症状名称
	public String bz;	//备注
	public String lrry;	//录入人员
	public String lrrq;	//录入日期
	public String jgdw;	//加工单位

	public String getId(){
		return id;
	}
	public void setId(String ID){
		id = ID;
	}
	
	public String getJbmc(){
		return jbmc;
	}
	public void setJbmc(String JBMC){
		jbmc = JBMC;
	}
	
	public String getYm(){
		return ym;
	}
	public void setYm(String YM){
		ym = YM;
	}
	
	public String getFbfs(){
		return fbfs;
	}
	public void setFbfs(String FBFS){
		fbfs = FBFS;
	}
	
	public String getYfb(){
		return yfb;
	}
	public void setYfb(String YFB){
		yfb = YFB;
	}
	
	public String getBfz(){
		return bfz;
	}
	public void setBfz(String BFZ){
		bfz = BFZ;
	}
	
	public String getZzmc(){
		return zzmc;
	}
	public void setZzmc(String ZZMC){
		zzmc = ZZMC;
	}
	
	public String getBz(){
		return bz;
	}
	public void setBz(String BZ){
		bz = BZ;
	}
	
	public String getLrry(){
		return lrry;
	}
	public void setLrry(String LRRY){
		lrry = LRRY;
	}
	
	public String getLrrq(){
		return lrrq;
	}
	public void setLrrq(String LRRQ){
		lrrq = LRRQ;
	}
	
	public String getJgdw(){
		return jgdw;
	}
	public void setJgdw(String JGDW){
		jgdw = JGDW;
	}
	
	/**
	 * Get attribute by given name.
	 * @param attrName The attribute name
	 * @return Attribute value
	 */
	public String getAttr(String attrName) {
		// TODO Auto-generated method stub
		if(attrName.equals("ID")){
			return id;
		}
		else if(attrName.equals("JBMC")){
			return jbmc;
		}
		else if(attrName.equals("YM")){
			return ym;
		}
		else if(attrName.equals("FBFS")){
			return fbfs;
		}
		else if(attrName.equals("YFB")){
			return yfb;
		}
		else if(attrName.equals("BFZ")){
			return bfz;
		}
		else if(attrName.equals("ZZMC")){
			return zzmc;
		}
		else if(attrName.equals("BZ")){
			return bz;
		}
		else if(attrName.equals("LRRY")){
			return lrry;
		}
		else if(attrName.equals("LRRQ")){
			return lrrq;
		}
		else if(attrName.equals("JGDW")){
			return jgdw;
		}
		
		return null;
	}
}
