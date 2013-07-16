package cn.edu.zju.ccnt.TFGW.object.xmlInf;

public class DiseaseInf {
	private String name;
	private int correlation;
	private String tableName;
	private String id;
	
	public DiseaseInf(){	
	}
	
	public DiseaseInf(String name, int correlation, String tableName, String id){
		this.name = name;
		this.correlation = correlation;
		this.tableName = tableName;
		this.id = id;
	}

	public boolean equal(DiseaseInf disease){
		if(this.id.equals("-1")){
			if(disease.getId().equals("-1") && this.name.equals(disease.getName())){
				return true;
			}
			return false;
		}
		else{
			if(this.id.equals(disease.id) && 
			   this.tableName.equals(disease.getTableName()) &&
			   this.name.equals(disease.getName())
			  ){
				return true;
			}
			return false;
		}
	}
	
	/**
	 * Add the correlation of two disease
	 * @param disease The disease whose correlation you want to add.
	 * @return The new correlation
	 */
	public int add(DiseaseInf disease){
		this.correlation += disease.getCorrelation();
		
		return this.correlation;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the correlation
	 */
	public int getCorrelation() {
		return correlation;
	}

	/**
	 * @param correlation the correlation to set
	 */
	public void setCorrelation(int correlation) {
		this.correlation = correlation;
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
}
