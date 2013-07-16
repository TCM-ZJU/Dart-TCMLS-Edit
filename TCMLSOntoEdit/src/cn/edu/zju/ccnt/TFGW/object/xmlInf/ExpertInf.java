package cn.edu.zju.ccnt.TFGW.object.xmlInf;

/**
 * The class is used to save the expertInf.
 * @author ZhangXiaoGang
 *
 */
public class ExpertInf {
	private String name;
	private String id;
	private int correlation;
	private String hospital;
	private String disease;
	
	/**
	 * @return the disease
	 */
	public String getDisease() {
		return disease;
	}

	/**
	 * @param disease the disease to set
	 */
	public void setDisease(String disease) {
		this.disease = disease;
	}

	/**
	 * @return the hospital
	 */
	public String getHospital() {
		return hospital;
	}

	/**
	 * @param hospital the hospital to set
	 */
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public ExpertInf(){
	}
	
	public ExpertInf(String name, String id, int correlation){
		this.name = name;
		this.id = id;
		this.correlation = correlation;
	}

	public ExpertInf(String name, String id, int correlation, String hospital){
		this.name = name;
		this.id = id;
		this.correlation = correlation;
		this.hospital = hospital;
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
