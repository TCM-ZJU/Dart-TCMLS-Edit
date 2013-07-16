package cn.edu.zju.ccnt.TFGW.object;

/**
 * The class is used to save the disease infmation.
 * @author zxg
 *
 */
public class Disease {
	String tablename = "";
	String name = "";
	String id = "";
	int relativite = 0;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the relativite
	 */
	public int getRelativite() {
		return relativite;
	}
	/**
	 * @param relativite the relativite to set
	 */
	public void setRelativite(int relativite) {
		this.relativite = relativite;
	}
	
	
}
