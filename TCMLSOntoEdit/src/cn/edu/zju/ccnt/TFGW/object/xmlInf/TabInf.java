package cn.edu.zju.ccnt.TFGW.object.xmlInf;

public class TabInf {
	private String name;
	private String tableName;
	private int number;
	private int distinctCount;
	
	/**
	 * @return the distinctCount
	 */
	public int getDistinctCount() {
		return distinctCount;
	}

	/**
	 * @param distinctCount the distinctCount to set
	 */
	public void setDistinctCount(int distinctCount) {
		this.distinctCount = distinctCount;
	}

	/**
	 * Initialize diseaseInf.
	 * @param name The name of the disease
	 * @param number The number of the disease.
	 */
	public TabInf(String name, String tableName, int number, int distinctCount){
		this.name = name;
		this.tableName = tableName;
		this.number = number;
		this.distinctCount = distinctCount;
	}
	
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

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
}
